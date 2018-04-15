package com.company;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CreateCategorySql extends AppendFile{
    public CreateCategorySql(String path) {
        super(path);
    }

    public void writeSql(HashMap<Integer, String> catHash) {
        StringBuilder sb = new StringBuilder();
        Iterator it = catHash.entrySet().iterator();

        //while (it.hasNext()) {
        for (int key : catHash.keySet()) {
            sb.setLength(0);

            Map.Entry pair = (Map.Entry) it.next();
            sb.append("INSERT INTO category VALUES(");
            sb.append(key + ", ");
            sb.append("'" + catHash.get(key) + "');");
            super.writeSql(sb.toString());
        }
    }

    public void close() {
        super.close();
    }
}
