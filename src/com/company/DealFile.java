package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class DealFile {
    private String path;
    private LinkedList<String> block;
    DataInfo dataInfo;
    CreateSqlFile sqlFile;
    HashMap<Integer, String> catHash;
    HashSet<String> customer;

    public DealFile(String path) {
        this.path = path;
        block = new LinkedList<>();
        sqlFile = new CreateSqlFile();
        catHash = new HashMap<>();
        customer = new HashSet<>();
    }

    public void readFile() {
        BufferedReader br = null;
        FileReader fr = null;
        String line;
        Boolean readBlock = false;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            int i = 0;
            while ((line = br.readLine()) != null) {

                line = line.trim();
                if (line.startsWith("Id:")) {
                    readBlock = true;
                    continue;
                }
                if (readBlock) {
                    if (line.length() == 0) {
                        dataInfo = readBlock();
                        sqlFile.setData(dataInfo);
                        sqlFile.writeFile();
                        block.clear();
                        dataInfo.print();
                        readBlock = false;
                    }
                    block.add(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (br != null) {
                br.close();
            }

            if (fr != null) {
                fr.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        sqlFile.setCategory(catHash, customer);
        sqlFile.writeList();

        sqlFile.close();
    }

    /*private boolean contains(ArrayList<Category> cat, Category c) {
        for (int i = 0; i < cat.size(); ++i) {
            if (cat.get(i).catid == c.catid) {
                return true;
            }
        }
        return false;
    }*/

    /*  |Books[283155]|Subjects[1000]|Arts & Photography[1]|Photography[2020]|Photo Essays[2082] */
    private void extractCat(String line, HashMap<Integer, String> cat) {

        String[] term = line.split("\\|");
        String tmp;
        for (int i = 0; i < term.length; ++i) {

            if (term[i].length() == 0) continue;

            tmp = term[i].replace("[", " ");
            tmp = tmp.replace("]", "").trim();

            String[] s = tmp.split(" ");
            String name = "";
            int j = 0;
            for (; j < s.length - 1; ++j) {
                name += s[j];
                name += " ";
            }

            int id = Integer.parseInt(s[j]);
            name = name.replace("'", "").trim();

            if (!cat.containsKey(id)) {
                cat.put(id, name);
            }
            if (!catHash.containsKey(id)) {
                catHash.put(id, name);
            }
        }
    }

    private ArrayList<String> removeNull(String[] term) {

        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < term.length; ++i) {
            if (term[i].length() != 0) {
                arr.add(term[i]);
            }
        }

        return arr;
    }

    /*private boolean containCid(LinkedList<String> cList, String cid) {
        for (int i = 0; i < cList.size(); ++i) {
            if (cid.equals(cList.get(i))) {
                return true;
            }
        }
        return false;
    }*/
    /* 2000-2-8  cutomer: A2ESGJTTLJWIAK  rating: 5  votes:  17  helpful:  11 */
    private void extractReview(String line, String asin, ArrayList<Review> review) {

        String[] term = line.split(" ");
        Review r = new Review();
        ArrayList<String> arr = removeNull(term);

        //System.out.println(arr.size());
        if (arr.size() != 9) {
            return;
        }
        r.asin = asin;
        r.date = arr.get(0);
        r.cid = arr.get(2);
        r.rating = Integer.parseInt(arr.get(4));
        r.vote = Integer.parseInt(arr.get(6));
        customer.add(r.cid);
        review.add(r);
    }

    private DataInfo readBlock() {
        DataInfo data = new DataInfo();
        String tmp;
        String line;
        int catcnt = 0;
        int reviewcnt = 0;
        for (int i = 0; i < block.size(); ++i) {
            tmp = block.get(i);
            if (catcnt > 0) {
                extractCat(tmp, data.cat);
                catcnt--;
                continue;
            }

            if (reviewcnt > 0) {
                extractReview(tmp, data.asin, data.review);
                reviewcnt--;
                continue;
            }

            if (tmp.startsWith("ASIN")) {
                data.asin = tmp.replace("ASIN:", "").trim();
            } else if (tmp.startsWith("title:")) {
                tmp = tmp.replace("title:", "").trim();
                data.title = tmp.replace("'", "").trim();
            } else if (tmp.startsWith("group:")) {
                data.group = tmp.replace("group:", "").trim();
            } else if (tmp.startsWith("salesrank:")) {
                String rank = tmp.replace("salesrank:", "").trim();
                data.salerank = Integer.parseInt(rank);
            } else if (tmp.startsWith("similar:")) {
                String[] similar = tmp.split(" ");
                if (similar.length > 2) {
                    for (int j = 2; j < similar.length; ++j) {
                        if (similar[j].length() != 0) {
                            data.similar.add(similar[j]);
                        }
                    }
                }
            } else if (tmp.startsWith("categories:")) {
                line = tmp.replace("categories:", "").trim();
                catcnt = Integer.parseInt(line);
            } else if (tmp.startsWith("reviews:")) {
               String[] term = tmp.split(" ");
               if (term.length > 3) {
                   reviewcnt = Integer.parseInt(term[2]);
               }

            }
        }

        return data;
    }

}
