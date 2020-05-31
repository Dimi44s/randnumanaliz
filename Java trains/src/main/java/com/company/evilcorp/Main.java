package com.company.evilcorp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static Gson gson = new Gson();
    public static String CH = "";
    public static String N = "";

    public static void main(String[] args) {
        Random random = new Random();
        Train trainFirst = new Train(random.nextInt(100), random.nextInt(100), random.nextInt(100),
                random.nextInt(100), random.nextInt(100));
        pullData(trainFirst, 1);
        Train trainSecond = new Train(random.nextInt(100), random.nextInt(100), random.nextInt(100),
                random.nextInt(100), random.nextInt(100));
        pullData(trainSecond, 2);
        readData("train1.json", "train2.json");
    }

    private static void pullData(Train train, int number) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("train" + number +".json"));
            gson.toJson(train, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void readData(String fileName1, String fileName2) {
        try {
            Reader reader1 = Files.newBufferedReader(Paths.get(fileName1));
            Train tr1 = gson.fromJson(reader1, Train.class);
            Reader reader2 = Files.newBufferedReader(Paths.get(fileName2));
            Train tr2 = gson.fromJson(reader2, Train.class);
            reader1.close();
            reader2.close();

            if (tr1.Weight > tr2.Weight) {
                CH = "two yellow";
                N = "yellow";
            } else {
                CH = "test";
            }
            System.out.println(CH + N);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
