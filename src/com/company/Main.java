package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

       DealFile df = new DealFile(FilePath.TEST);
       df.readFile();
    }
}