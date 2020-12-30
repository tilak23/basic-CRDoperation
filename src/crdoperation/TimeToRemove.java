package crdoperation;

import crdoperation.Database;

public class TimeToRemove extends Thread implements Runnable{
    public Database die(Database db, long createdTime, int timeToLive, String key) {       //removes key after TTL expires

        Thread keyTimeToLive = new Thread()                     //a new thread is created for every key with TTL, which runs concurrently in the background
        {
            @Override
            public void run()  {
                while (System.currentTimeMillis() <= (createdTime + timeToLive)) {
                    //runs till the specified TTL value in System clock
                }
                System.out.println(key + "-VALUES removed in " + (System.currentTimeMillis() - createdTime) / 1000 + " seconds!!");
                try {
                      db.remove(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        keyTimeToLive.start();
        return db;
    }
}


