package crdoperation;

import crdoperation.Database;
import crdoperation.KeyId;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreationOfPair {
    static BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
    public Database determineKeyValue(Database db, String key) throws Exception{
        Json m=new Json();
        Pair n=new Pair();
            if (KeyId.isExit(key)) {
                throw new Exception("the  key is already exist,please enter the a new key");
            }
            else if (!KeyId.isExit(key) && key.length() <= 32) {
                n.setKey(key);
                KeyId.keys.add(key);
                KeyId.write(key);
                m.setJsonObj(getValue());
                n.setValue(m);
                db.addAndWrite(n);
            } else {
                throw new Exception("the given  key  is either invalid or the length of  the key is not capped 32 char");
            }
            Print.print();
            System.out.println(n.toString()+" this is how the data wil be stored in the file");
            Print.print();
        return db;
    }
    public Map<String,Object> getValue() throws Exception{
        Map<String,Object> value=new LinkedHashMap<>();
        char c='y';
        String pr="""
                     Enter the values to store in  the database
                     if you want to enter the values  press y
                     if you don't want to enter the value , then  press n 
                     """;
        System.out.print(pr);
        Print.print();
        String com="";
        do {
            c = Character.toLowerCase(input.readLine().charAt(0));
            if (c == 'y' && sizeOfValue(com)) {
                System.out.println("the data you enter must be like [key-value datastore] for values ");
                System.out.println("enter the key-Value");
                String s = input.readLine();
                System.out.println("enter the value-Value");
                String v = input.readLine();
                System.out.println("press y to add more Value or press n");
                com+=s+v;
                value.put(s,v);
            }
            if(!sizeOfValue(com)){
                throw new Exception("your value must be within 16kb");
            }
            if(c!='y' && c!='n'){
                throw  new Exception("you enter invalid function option please enter the correct input to continue");

            }
        }
        while(c=='y');
        return value;
    }
    public boolean sizeOfValue(String s){
        return s == null || s.getBytes().length < 16000;
    }

}

