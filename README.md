Freshworks – Backend Assignment

Build a file-based key-value data store that supports the basic CRD (create, read, and delete) operations. This data store is meant to be used as a local storage for one single process on one laptop. The data store must be exposed as a library to clients that can instantiate a class and work with the data store.

-----------------------------------------------------------------------------------------------------
                            GUIDELINE
---------------------------------------------------------------------------------------------  
------------------------------------------------------------------------------------------------
-------------------------------db_folder--------------------------------------------------------
this  folder  consist of the data which i was working on "Fresh_db.dat".                        
------------------------------------------------------------------------------------------------
------------------------fresh_db.dat------------------------------------------------------------
keyId=// the value can be anything (ex:1234456dfahd,thilak) but the keyID is capped within 32 char
value={
        //it wil be in a json object, the Json Object  is not imported from any lib. hence the value in json object must be key-value  base.
....    // the  Json Object is created , a key and value input must given. 
        }
please check pic_of_op for any clarification.
--------------------------------------------------------------------------------------------------
--------------------------------How to consume the DataStore library?-----------------------------                                    
 Inorder to consume my lib, please download the jar which i  have uploaded in the name of Datastore.jar
add this jar file to your IDE engine (Path:"lib/dependencies/+key/add more jar file").             

EX:                                                                                                               
import crdoperation.Database;                                                                 
import crdoperation.Json;
import crdoperation.Operation;                                                                    

import java.io.File;                                                                                   

public class Main {                                                                           
    public static void main(String[] args) throws Exception {                                    
        //if you have create a file.txt .before entering the data.                             
        String Path="A:\\freshworks_datastore\\fresh_db.dat";                             
        Database db=new Database(Path);                                                                        
        // you can you  use read method to read the data from file to db.                                     
        db.readData(new File(Path));                                                                                                
        //this Path  were a file wil be created wil be the  until end.                                                          
        //didn't create an empty constructor.                                                                                 
        Operation n=new Operation();                                                           
        //Operation object must be created to CRD(create,read,delete) operation.                           
        //db=n.create(db);                                                                   
        //for create method,                                                                                                                                                  
             //an Database object must be given                                              
             // create method  returns Database object                                                    
        //db.get("thilak");                                                                                
        n.read(db);                                                                                         
        //for read method,                                                                                    
              // an Database object must be given                                                                         
              // no return statement;                                                                                    
              //this method wil automatically  print if the key exist or throw error.                             
       //  db=n.delete(db);                                                                                   
         //for delete method,                                                                                             
                 //an Database object must be given                                                                      
                  // create method  returns Database object                                                                               
        //--------------------- note------------------------------                                                     
        // each method  wil be called through  creating the object for Operation and invoking the method.
        //once operation  method  called, according  to the need the data must be given by the user.
        // pic_of_op folder has some screenshot of the input and output.                                       
        //-------------------------------------------------------------------
                                                                                                                             
    }
}
--------------------------------------------------------------------------------------------------------------
-------------------------------pic_of_op-----------------------------------------------------------------
this folder wil consist of the output which was taken in my  console(intellij idea).
-----------------------------------------------------------------------------------------------------
------------------------------src--------------------------------------------------------------------
this folder consist  of all my code which can be downloaded or can be executed ............
----------------------------------------------------------------------------------------------------
