package crdoperation;

import crdoperation.CreationOfPair;
import crdoperation.Database;
import crdoperation.KeyId;
import crdoperation.TimeToLive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Operation {
    static BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
    public synchronized Database create(Database db) throws Exception{
        System.out.println("enter the a key within 32 chars");
        String key = input.readLine();
        Print.print();
        if (KeyId.isExit(key)) {
            throw new Exception("the  key is already exist,please enter the a new key");
        }
        if (!KeyId.isExit(key) && key.length() <= 32) {
            db= new CreationOfPair().determineKeyValue(db,key);
            System.out.println("Do you want to set TTL? (Y/N)");
            char choice = Character.toUpperCase(input.readLine().charAt(0));

            if (choice == 'Y') {
                TimeToLive TTLObject = new TimeToLive();
                int timeToLive;
                long createdTime;

                System.out.print("Specify TTL (in seconds): ");
                timeToLive = Integer.parseInt(input.readLine()) * 1000;       //seconds converted to milliseconds

                createdTime = System.currentTimeMillis();
                TTLObject.removeKey(key, createdTime, timeToLive);   //sent to Threaded class for removing key after its time-to-live expires
            }
        } else {
            throw new Exception("the given  key  is either invalid or the length of  the key is not capped 32 char");
        }
        System.out.println("the key you generated is successfully stored in  the database");
        return db;
    }
    public synchronized void read(Database db)throws Exception{
        System.out.println("enter the a key within 32 chars");
        String key = input.readLine();
         Print.print();
        if (!KeyId.isExit(key)) {
            throw new Exception("the  key is not available, Enter  the correct Key!");
        }
        if(KeyId.isExit(key)){
            db.get(key);
        }

    }

    public synchronized Database delete(Database db) throws Exception{
        System.out.println("enter the a key within 32 chars");
        String key = input.readLine();
        if (!KeyId.isExit(key)) {
           throw new Exception("the  key is not available, Enter  the correct Key!");
        }
        else {
            KeyId.remove(key);
            TimeToRemove ttr=new TimeToRemove();
            long createdTime=System.currentTimeMillis();
            db=ttr.die(db,createdTime,10000,key);
            System.out.println("the values of "+key+"Id will be removed from database in 10000sec....");
        }
        return db;
    }
    }

