package crdoperation;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class KeyId {
    public static final  String def_path=System.getProperty("user.home")+"\\keysId.txt";

    static Scanner in=new Scanner(System.in);
    static List<String > keys=new LinkedList<>();
    public static boolean fileExist(String file){
        return new File(file).exists();
    }
    public static void createFile() throws IOException {
        File f=new File(def_path);
        if(!fileExist(def_path)) {
            f.createNewFile();
            System.out.println(f.exists() ? "key file is created successfully.." : "something went wrong in creating Key..");
        }
        else{
            f.delete();
            f.createNewFile();
        }
    }
    static void  write(String s) throws Exception{
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(def_path, true));
            String v=s + "\n";
            w.write(v);
            w.close();
        }
        catch(Exception e){
            throw new Exception("the key  you wanted to store in unsuccessful.");
        }
    }
    public static boolean isExit(String key){
        return  keys.contains(key);
    }
    public static  void read(List<String > keys_db){
        keys.addAll(keys_db);
    }
    public static void read() throws Exception {
        List<String> lines = Files.readAllLines(Path.of(def_path));
        keys.addAll(lines);
    }
    public static  void add(String key){
        keys.add(key);
    }
    public static  void showKey(){
        System.out.println("the available keys to read or write");
        if(keys.isEmpty()){
            System.out.println("No Keys available");
        }
        for(String x:keys){
            System.out.println(x);
        }
    }
    public static  void  remove(String key) throws Exception{
        keys.remove(key);
        deleteFromFile();
    }
     public static  void delete(){
        new File(def_path).delete();
        keys.clear();
     }

    // once the key's time is expired ,remove from the file which is been updated for the next time use.
    static  void deleteFromFile() throws Exception {
        File file=new File(def_path);
        file.delete();
        if(!file.exists()){
            file.createNewFile();
            FileWriter w=new FileWriter(file,true);
            for(String x:keys){
                String v=x+"\n";
                w.write(v);
            }
            w.close();
            System.out.println("the key is removed from the file...");
        }
        else{
            System.out.println("the key you wanted to remove from the file  is unsuccessful");
        }
    }
}
