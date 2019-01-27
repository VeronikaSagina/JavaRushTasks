package com.javarush.task.task32.task3207;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* 
К серверу по RMI
*/
public class Solution {
    public static final String UNIC_BINDING_NAME = "double.string";
    public static Registry registry;

    //pretend we start rmi client as CLIENT_THREAD thread
    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                DoubleString string = (DoubleString) registry.lookup(UNIC_BINDING_NAME);
                String result = string.doubleString("WOLF");
                System.out.println(result);
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }
    });

    public static void main(String[] args) {
        //pretend we start rmi server as main thread
        try {
            registry = LocateRegistry.createRegistry(2099);
            final DoubleStringImpl service = new DoubleStringImpl();

            Remote stub = UnicastRemoteObject.exportObject(service, 0);
            registry.bind(UNIC_BINDING_NAME, stub);
            //start client
            CLIENT_THREAD.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UnicastRemoteObject.unexportObject(service, true);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}