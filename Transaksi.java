import java.util.InputMismatchException;
import java.util.Scanner;
public class Transaksi {
    
    public static void main(String[] args) {
        Config.connection();
        System.out.println("===========================WELCOME===============================");
        Scanner scanner = new Scanner(System.in);
        try{
            while(true){
            System.out.print("\n====== MENU ======\n"
            + "1. Register\n"
            + "2. Login\n"
            + "3. create table\n"
            + "4. add transaction\n"
            + "5. view transaction record\n"
            + "6. delete transaction record\n"
            + "7. Update transaction record\n"
            + "0. Exit\n"
            + "Pilih[1/2/3/4/5/0] : ");

           

            String pilihan = scanner.next();

            if( pilihan.equalsIgnoreCase("0") ){
                System.out.println("Thank You!!");
                break;
            }

            switch (pilihan) {
                case "1" :
                    View.register();
                    break;
                case "2" :
                    View.login();
                    break;
                case "3" :
                    View.createTable();
                    break;
                case "4" :
                    View.tambahData();
                    break;
                case "5" :
                    View.getAllData();
                    break;
                case "6" :
                    View.deleteData();
                    break;
                case "7" :
                    View.updateData();
                    break;
                default:
                    System.out.println("Incorrect Choice!!");
                    break;
            }

        }
    } catch (InputMismatchException e){
            System.out.println("Kesalahan Input, Tipe Data tidak sesuai!");
        }
        scanner.close();
    }
    

    
}


