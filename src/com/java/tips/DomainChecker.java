package com.java.tips;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DomainChecker {
    public static void main(String[] args) {
        int symbolCount = 2;

        // find all variants
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz0123456789-".toCharArray();
        int indexes[] = new int[symbolCount];
        int activeIndex = indexes.length - 1;
        StringBuilder stringBuilder = new StringBuilder();
        List<String> allPossibleDomainVariant = new ArrayList<>((int) Math.pow(alphabet.length, symbolCount));
        while (true) {
            //check index
            if (indexes[activeIndex] + 1 > alphabet.length) {
                if (activeIndex - 1 < 0) {
                    break;
                }
                activeIndex--;
                indexes[activeIndex]++;
            } else {
                // if reached the limit in the column
                for (int i = activeIndex + 1; i < indexes.length; i++) {
                    indexes[i] = 0;
                }
                activeIndex = indexes.length - 1;

                // print string
                stringBuilder.delete(0, stringBuilder.length());
                for (int indexe : indexes) {
                    stringBuilder.append(alphabet[indexe]);
                }
                allPossibleDomainVariant.add(stringBuilder.toString());

                // increment index
                indexes[activeIndex]++;
            }
        }

        // check url addresses
        List<String> availableDomainNames = new ArrayList<>();
        for (String domainName : allPossibleDomainVariant) {
            // check is a possibility to connect to the server
            String urlAddress = "http://" + domainName + ".com";
            System.out.println("------------------------------------------------------------");
            System.out.println("Checking URL: " + urlAddress);
            URL url = null;
            try {
                url = new URL(urlAddress);
            } catch (MalformedURLException e) {
                System.out.println("Incorrect URL: " + urlAddress);
            }
            if (url != null) {
                HttpURLConnection urlConnection;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    System.out.println(String.format("URL: %s, Status: %s", urlAddress, urlConnection.getResponseCode()));
                } catch (IOException e) {
                    System.out.println("Connection error. URL: " + urlAddress + ". Checking domain on whois.");

                    // check domain on whois
                    URL whoisURL = null;
                    try {
                        whoisURL = new URL("http://whois.com?" + urlAddress);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                    BufferedReader bufferedReader = null;
                    if (whoisURL != null) {
                        try {
                            bufferedReader = new BufferedReader(
                                    new InputStreamReader(whoisURL.openStream()));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        if (bufferedReader != null) {
                            String inputLine;
                            try {
                                while ((inputLine = bufferedReader.readLine()) != null) {
                                    if (inputLine.contains("Available")) {
                                        System.out.println("Domain is available.");
                                        availableDomainNames.add(urlAddress);
                                        break;
                                    } else if (inputLine.contains("Registered")) {
                                        System.out.println("Domain purchased.");
                                        break;
                                    }
                                }
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("------------------------------------------------------------");
        System.out.println("------------------------------------------------------------");
        System.out.println("Available domains:");
        System.out.println(availableDomainNames);
    }
}