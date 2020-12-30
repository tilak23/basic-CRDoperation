
import crdoperation.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//m->object of Main
//br->input
public class Main extends IOException  {
    static FileHandle file=new FileHandle();
    private Database db;
     public static int i=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Main m=new Main();
        Print.print();
        System.out.println("Press the key to do the following process");
        System.out.println("1).Create a file \n      or\n 2).load the existing file");
        int value = Integer.parseInt(br.readLine());

        if(!KeyId.fileExist(KeyId.def_path)) {
            KeyId.createFile();
        }
        Print.print();
        if(value==1){
            file.createFile();
            KeyId.showKey();
            Print.print();
        }
        else if(value==2){
           m.db= file.loadFile();
            Print.print();
            KeyId.showKey();

        }
        else{
            System.out.println("the input you entered is not in option,please check and try again..");
        }
        if(i==0) {
            char choice = 'y';
            do {
                Print.print();
                System.out.println("1).create\n2).read\n3).delete");
                int a = Integer.parseInt(br.readLine());
                if (a == 1) {
                    m.db = new Operation().create(m.db);
                    System.out.println("The key-values are created successfully....");
                } else if (a == 2) {
                    new Operation().read(m.db);
                    System.out.println("the  values are read successfully....");
                    Print.print();
                } else if (a == 3) {
                    m.db = new Operation().delete(m.db);
                    System.out.println("the key-value has been successfully removed....");
                    Print.print();
                } else {
                    System.out.print("The input you given is invalid....");
                }
                System.out.println("to repeat the process press 'y' or 'n' to exit");
                choice = br.readLine().charAt(0);
                Print.print();
                KeyId.showKey();
            }
            while (choice == 'y');
        }
        Print.print();
        System.out.println("                      thank you                     ");
        Print.print();

    }
}
