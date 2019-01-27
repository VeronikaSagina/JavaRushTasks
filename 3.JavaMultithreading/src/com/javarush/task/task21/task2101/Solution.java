package com.javarush.task.task21.task2101;

/* 
Определяем адрес сети
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(new byte[]{(byte)128, (byte)-127, (byte)-3, (byte)-4, (byte)-1});
        System.out.printf("%d %d %d %d", (byte)128, (byte)-3, (byte)-4, (byte)-1);
/*
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
*/


    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] result = new byte[ip.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (ip[i] & mask[i]);
        }
        return result;
    }

    public static void print(byte[] bytes) {
        for (byte b : bytes) {
            System.out.print(getByte(b) + " ");
        }
        System.out.println();
    }

    private static String byt(byte b) {
        String s = String.format("%8s", Integer.toBinaryString(b & 255));
        s = s.replaceAll(" ", "0");
        return s;
    }

    private static String getByte(byte b) {
        StringBuilder stringBuilder = new StringBuilder(8);
        int x = 1;
        for (int i = 0; i < 8; i++) {
            if ((x & b) > 0) {
                stringBuilder.append(1);
            } else {
                stringBuilder.append(0);
            }
            x = x * 2;
        }
        return String.valueOf(stringBuilder.reverse());
    }
}
