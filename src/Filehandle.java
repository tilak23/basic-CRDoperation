import com.sun.tools.javac.Main;
import crdoperation.Database;
import crdoperation.KeyId;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


class FileHandle {
    static String path=System.getProperty("user.home");

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public synchronized void createFile() throws IOException {
        File f1=new File("fresh_db.dat");
        System.out.println("Enter the path for "+f1+", to create data-store."+"or press enter for default location");
        String user_path=br.readLine();
        File f_path;
        if(user_path!=null ){
            f_path = new File(user_path + "\\" + f1);
            if(!f_path.exists()){
                f_path.createNewFile();
            }
            else if(f_path.exists()) {
                do {
                    System.out.println(f_path + " is already exist..\npress\n 1).to delete the existing file and write new file" +
                            "\n2).to exit ");
                    int n = Integer.parseInt(br.readLine());
                    if (n == 1) {
                        System.out.println("The entire data wil  be deleted.\n would you like to continue?\npress" +
                                " 'y' for yes or 'n' for exit.");
                        if(Character.toLowerCase(br.readLine().charAt(0))=='y') {
                            deleteFile(f_path);
                            KeyId.delete();
                            KeyId.createFile();
                            f_path.createNewFile();
                        }
                            break;

                    }
                    else if (n == 2) {

                        return;
                    }
                    else
                        System.out.println(" the input you enter is not in the option.enter the correct input to delete");
                }while(true);
            }
        }
        else {
            f_path = new File(path + "\\" + f1);
            f_path.createNewFile();
        }
        System.out.println(f_path.exists() ? "file successfully created" : "something wrong in creating,please check the  file");
    }
    public synchronized void deleteFile(File path){
        path.delete();
        if(!path.exists()){
            System.out.println("file has been deleted successfully...");
        }
        else{
            System.out.println("something is wrong in deleting file");
        }
    }
    public synchronized Database loadFile() throws Exception {
        System.out.println("Enter the path to load the file\n(Ex:C:\\user\\public\\document\\db.txt)");
        String lf=br.readLine();
        File n=new File(lf);
        if(n.exists()){
            Database db= new Database(lf);
             db.readData(n);
             return db;
        }
        else{
            System.out.print("the file you entered isn't exist..");
        }
       return  null;
    }

}
