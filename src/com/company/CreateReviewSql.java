package com.company;

import java.util.ArrayList;

public class CreateReviewSql extends AppendFile{

    public CreateReviewSql(String path) {
        super(path);
    }

    public void writeSql(ArrayList<Review> reivew) {

        StringBuilder sb = new StringBuilder();
        Review r;
        for (int i = 0; i < reivew.size(); ++i) {
            sb.setLength(0);
            r = reivew.get(i);
            sb.append("INSERT INTO review(asin, cid, review_date, rating, vote) VALUES(");
            sb.append("'" + r.asin + "', ");
            sb.append("'" + r.cid + "', ");
            sb.append("'" + r.date + "', ");
            sb.append(r.rating + ", ");
            sb.append(r.vote + ");");
            super.writeSql(sb.toString());
        }
    }

    public void close() {
        super.close();
    }
}
