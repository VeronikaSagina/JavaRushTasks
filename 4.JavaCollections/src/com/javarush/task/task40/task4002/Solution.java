package com.javarush.task.task40.task4002;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* 
Опять POST, а не GET
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("http://requestb.in/1h4qhvv1", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("User-Agent", "Mozilla/5.0");

        List<NameValuePair> valuePairs = new ArrayList<>();
        String[] s = urlParameters.split("&");
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            valuePairs.add(new BasicNameValuePair(s1.substring(0, s1.indexOf("=")), s1.substring(s1.indexOf("=") + 1)));
        }

        httpPost.setEntity(new StringEntity(urlParameters, Charset.forName("UTF-8")));
        httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));
        HttpResponse response = client.execute(httpPost);
        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }

        System.out.println("Response: " + result.toString());
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
