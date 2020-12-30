package crdoperation;

public class TimeToLive extends Thread implements Runnable{
        public void removeKey(String key, long createdTime, int timeToLive) {       //removes key after TTL expires

            Thread keyTimeToLive = new Thread()                     //a new thread is created for every key with TTL, which runs concurrently in the background
            {
                @Override
                public void run()  {
                    while (System.currentTimeMillis() <= (createdTime + timeToLive)) {
                        //runs till the specified TTL value in System clock
                    }
                    System.out.println(key + " removed in " + (System.currentTimeMillis() - createdTime) / 1000 + " seconds!!");
                    KeyId.keys.remove(key);     //removes the key from dataStore
                    try {
                        KeyId.deleteFromFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            keyTimeToLive.start();
        }
    }

