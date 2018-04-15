package com.company;

public class CreateProductSql extends AppendFile{

    public CreateProductSql(String path) {
        super(path);
    }

    public void writeSql(String asin, String title, int salerank, String group) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO product values(");
        sb.append("'" + asin + "', ");
        sb.append("'" + title + "', ");
        sb.append(salerank + ", ");
        sb.append("'" + group + "');");

        super.writeSql(sb.toString());
    }

    public void close() {
        super.close();
    }

}
