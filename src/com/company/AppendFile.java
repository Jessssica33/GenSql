package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
public class AppendFile {

    private String path;
    protected BufferedWriter writer;
    public AppendFile(String path) {
        this.path = path;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.append("START TRANSACTION;");
            writer.newLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSql(String str) {
        try {
            writer.append(str);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        try {
            writer.append("COMMIT;");
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
