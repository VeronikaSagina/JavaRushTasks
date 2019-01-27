package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server running");

            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    //sending message to all users
    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        //info about other chat users
        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                String name = entry.getKey();
                if (!name.equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, name));
                }
            }
        }

        //server work______main
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message clientMassage = connection.receive();
                if (clientMassage.getType() == MessageType.TEXT) {
                    Message incomingMessage = new Message(MessageType.TEXT, userName + ": " + clientMassage.getData());
                    sendBroadcastMessage(incomingMessage);
                } else {
                    ConsoleHelper.writeMessage("Error! it's not a text.");
                }
            }
        }

        //communication with the client _________handshake
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME &&
                        !answer.getData().isEmpty() &&
                        !connectionMap.containsKey(answer.getData())) {
                    connectionMap.put(answer.getData(), connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    return answer.getData();
                }
            }
        }

        @Override
        public void run() {
            Connection connection = null;
            String userName = null;
            ConsoleHelper.writeMessage("Connection established: " + socket.getRemoteSocketAddress());
            try {
                connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            }catch (IOException | ClassNotFoundException e){
                //не обеспечено закрытие соединения
                e.printStackTrace();
            }
            if (userName != null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                ConsoleHelper.writeMessage("Connection is closed: " + connection.getRemoteSocketAddress());
            }
        }
    }
}
