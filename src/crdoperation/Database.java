package crdoperation;

import crdoperation.KeyId;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Database {
    private final String path_db;
    public Database(String s){
        path_db=s;
    }
     private final List<Pair> fresh_db=new LinkedList<>();
    public void readData(File f) throws Exception {
        List<String> lines= Files.readAllLines(Path.of(f.getPath()));
        if(!checkSize1gb(lines.toString())){
            throw  new Exception(" data  in the database is more 1 Gb!,please delete some data to contionue");
        }
        for(String x:lines){
            String str[]= x.split("=");
            if(str.length==2){
            KeyId.add(str[0]);
            addToDb(str[0],str[1]);
            }
            else{
                //error in add to db
                System.out.println("Something went wrong in adding the data from file to localdb.\ncheck the input.txt for entering the data in file");
            }
        }
        System.out.println("reading data is successfull");
    }
    public boolean checkSize1gb(String s){
       return ((s.getBytes().length / 1024) / 1024) / 1024 < 1;
    }
    public void get(String key){
        fresh_db.stream().filter(i-> i.containsKey(key)).forEach(i->System.out.println(i.getValue()));
    }
    public void addAndWrite(Pair pair) throws Exception {
        if(checkSize1gb(fresh_db.toString())) {
            fresh_db.add(pair);
            write(pair.toString());
        }
        else{
            throw new Exception("you cant add the data to file. cuz, the data consist in the file is more than 1Gb");
        }
    }
    public void addToDb(String key,String  value){
        Pair n= new Pair();
        n.setKey(key);
        String s=value.replaceAll("[\\{ \\}]","");
        String str[]=s.split(",");
        Map<String,Object> val=new LinkedHashMap<>();
        for(String x:str){
            String ans[]=x.split(":");
            val.put(ans[0],ans[1]);
        }
        Json m=new Json();
        m.setJsonObj(val);
        n.setValue(m);
        fresh_db.add(n);
    }
    public void remove(String key) throws Exception {
        fresh_db.removeIf(x->x.containsKey(key));
        removeFromFile(key);
    }
    public void removeFromFile(String key) throws Exception {
        File f=new File(path_db);
        f.delete();
        if(!f.exists()) {
            f.createNewFile();
             addToFile();
        }
        else{
            System.out.println("Couldn't remove the "+key+"-values ");

        }
    }
    public  void addToFile() throws Exception{
                for (Pair x : fresh_db) {
                    String v = x.toString();
                    write(v);
                }
        //System.out.println("the data you wanted to store in successful.");
    }


    public void  write(String s) throws Exception{
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(String.valueOf(path_db), true));
            w.write(s + "\n");
            w.close();
        }
        catch(Exception e){
            throw new Exception("the data you wanted to store in unsuccessful.");
        }

    }
}
