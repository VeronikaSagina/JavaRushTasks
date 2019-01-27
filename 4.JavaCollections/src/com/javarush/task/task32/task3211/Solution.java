package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject("test string");
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        byte[] bytesOS = byteArrayOutputStream.toByteArray();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] arrayStr = messageDigest.digest(bytesOS);
        StringBuilder builder = new StringBuilder();
        for (byte b : arrayStr){
            builder.append(String.format("%02x", b));
        }
        String res = builder.toString();
        return res.equals(md5);
    }
}
