package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateBelongToSql extends  AppendFile{

    public CreateBelongToSql(String path) {
        super(path);
    }

    public void writeSql(String asin, HashMap<Integer, String> cat) {
        StringBuilder sb = new StringBuilder();

        //for (int i = 0; i < cat.size(); ++i) {
        for (int key: cat.keySet()) {
            sb.setLength(0);
            sb.append("INSERT INTO belongTo VALUES(");
            sb.append("'" + asin + "', ");
            sb.append(key + ");");
            super.writeSql(sb.toString());
        }
    }

    public void close() {
        super.close();
    }
}
