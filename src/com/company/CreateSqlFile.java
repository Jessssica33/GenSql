package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class CreateSqlFile {

    CreateProductSql product;
    CreateSimilarToSql similarTo;
    CreateReviewSql review;
    CreateBelongToSql belongTo;
    CreateCustomerSql customer;
    CreateCategorySql category;

    DataInfo data;
    HashMap<Integer, String> catHash;
    HashSet<String> customerHash;

    public CreateSqlFile() {

        product = new CreateProductSql(FilePath.PRODUCT);
        similarTo = new CreateSimilarToSql(FilePath.SIMILARTO);
        review = new CreateReviewSql(FilePath.REVIEW);
        belongTo = new CreateBelongToSql(FilePath.BELONGTO);
        customer = new CreateCustomerSql(FilePath.CUSTOMER);
        category = new CreateCategorySql(FilePath.CATEGORY);
    }

    public void setData(DataInfo data) {
        this.data = data;
    }

    public void setCategory(HashMap<Integer, String> cat, HashSet<String> customer) {
        catHash = cat;
        customerHash = customer;
    }

    public void writeFile() {
        product.writeSql(data.asin, data.title, data.salerank, data.group);
        similarTo.writeSql(data.asin, data.similar);
        review.writeSql(data.review);
        belongTo.writeSql(data.asin, data.cat);
    }

    public void writeList() {
        category.writeSql(catHash);
        customer.writeSql(customerHash);
    }



    public void close() {
        product.close();
        similarTo.close();
        review.close();
        belongTo.close();
        customer.close();
        category.close();
    }

}
