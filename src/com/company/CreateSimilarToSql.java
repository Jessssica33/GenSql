package com.company;

import java.util.ArrayList;

public class CreateSimilarToSql extends AppendFile{

    public CreateSimilarToSql(String path) {
        super(path);
    }

    public void writeSql(String asin, ArrayList<String> similar) {
        StringBuilder sb = new StringBuilder();
        String asin1;
        String asin2;
        String tmp;
        for (int i = 0; i < similar.size(); ++i) {
            sb.setLength(0);
            tmp = similar.get(i);
            if (asin.compareTo(tmp) < 0) {
                asin1 = asin;
                asin2 = tmp;
            } else {
                asin1 = tmp;
                asin2 = asin;
            }

            sb.append("INSERT INTO similarTo VALUES (");
            sb.append("'" + asin1 + "', ");
            sb.append("'" + asin2 + "');");
            super.writeSql(sb.toString());
        }
    }

    public void close(){
        super.close();
    }
}
