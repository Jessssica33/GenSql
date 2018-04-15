package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class CreateCustomerSql extends AppendFile{

    public CreateCustomerSql(String path) {
        super(path);
    }

    public void writeSql(HashSet<String> customer) {

        StringBuilder sb = new StringBuilder();
        String c;


        //for (int i = 0; i < customerList.size(); ++i) {:

        for (String s : customer) {

            sb.setLength(0);

            sb.append("INSERT INTO customer VALUES(");
            sb.append("'" + s + "');");

            super.writeSql(sb.toString());
        }
    }

    public void close() {
        super.close();
    }
}
