import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class View {
    public static Scanner input = new Scanner(System.in);
    private static HashMap<String, String> users = new HashMap<>();
    private static HashMap<String, String> captchas = new HashMap<>();
    private static final int CAPTCHA_LENGTH = 6;
    
  
  public static void createTable() {
    // Memanggil method dari kelas Config untuk menciptakan tabel
    Config.createTransaksiTable();
    System.out.println("Transaction Table Has Been Successfully Created .");
    System.out.println("\nClick Enter to Continue...");
    try {
        System.in.read();
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
  
  public static void tambahData() {
    Date HariSekarang = new Date();
    
      System.out.print("input invoiceNumber: ");
      String invoiceNumber = input.nextLine();

      System.out.print("imput customerName: ");
      String customerName = input.nextLine();

      System.out.print("imput NoHP: ");
      String NoHP = input.nextLine();

      System.out.print("input Address: ");
      String address = input.nextLine();

      System.out.print("input itemName: ");
      String itemName = input.nextLine();

      System.out.print("input price: ");
      long price = input.nextLong();
      input.nextLine();

      System.out.print("input quantity: ");
      int quantity = input.nextInt();
      input.nextLine();

      Faktur invoice = new Faktur(invoiceNumber, customerName, itemName, price, quantity, NoHP, address);
      SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
      
      System.out.println("=================NRN_COMPUTER=================");
      System.out.println("Date \t\t: " + ft.format(HariSekarang));
      System.out.println("Time\t\t\t: " + format.format(HariSekarang));
      System.out.println("Invoice Number\t\t: " + invoice.getInvoiceNumber());
      System.out.println("==========Data Pelanggan==========");
      System.out.println("CustomerName\t\t: " + invoice.getCustomerName());
      System.out.println("Phone Number\t\t: " + invoice.getnoHP());
      System.out.println("Address\t\t\t: " + invoice.getaddress());
      System.out.println("============Data Barang============");
      System.out.println("Item Name\t\t: " + invoice.getItemName());
      System.out.println("Price\t\t\t: " + invoice.getPrice());
      System.out.println("Quantity\t\t: " + invoice.getQuantity());
      System.out.println("Total Payment\t\t: " + invoice.calculateTotal());
      System.out.println("===============Cashier===============");
      String myString = "Nabil";
      String uppercaseString = myString.toUpperCase();
      System.out.println("Cashier\t\t\t: " + uppercaseString);

      if (Config.tambahData(invoiceNumber, customerName, itemName, price, quantity, NoHP, address)) {
        System.out.println("Transaction has been saved successfully!");
        
    } else {
        System.out.println("Failed to save transaction!!");
    }
  
  

    
}
    public static void getAllData()
  {
    //  pesan header
    System.out.println("\n::: TRANSACTION RECORD :::");
    System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------|");

    // Header untuk data transaksi
    System.out.printf("|%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %n",
        "Invoice Number",
        "Customer Name",
        "Contact",
        "Address",
        "Item Name",
        "Price",
        "Quantity"
  );
    System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------|");
    // data semua barangnya
    System.out.println(Config.getAllData());
  }

  public static void deleteData()
  {
    System.out.println("\n:::DELETE TRANSACTION RECORD :::");
    System.out.print("Input Invoice : ");
    int invoiceNumber = input.nextInt();

    if( Config.deleteData(invoiceNumber) ){
      System.out.println("Transaction has been deleted successfully!!");
      getAllData();
    }else{
      System.out.println("failed to delete transaction!!");
    }

  }

  public static void updateData()
  {
    System.out.println("\n::: UPDATE TRANSACTION RECORDS :::");
    System.out.print("input invoiceNumber : ");
    int invoiceNumber = input.nextInt();
    System.out.println("\nChange data item\n");
    System.out.print("item Name (Leave blank if you do not want to change the data) : ");
    String itemName = input.nextLine();
    itemName = input.nextLine();

    System.out.print("quantity (enter 0 if you do not want to change the data) : ");
    int quantity = input.nextInt();
    
    System.out.print("price (enter 0 if you do not want to change the data)) : ");
    int price = input.nextInt();

    if( Config.updateData(invoiceNumber, itemName, quantity, price) ){
      System.out.println("Update Success!!");
      View.getAllData();
    }else{
      System.out.println("Update Failed");
    }

  }
  public static void register() {
        System.out.print("Input username: ");
        String username = input.nextLine();
        System.out.print("Input password: ");
        String password = input.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username Already Take");
        } else {
            users.put(username, password);
            System.out.println("Registration Success!!");
        }
    }


    public static void login() {
        System.out.print("Input username: ");
        String username = input.nextLine();
        System.out.print("Input password: ");
        String password = input.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            String captcha = generateCaptcha();
            captchas.put(username, captcha);
            System.out.println("CAPTCHA : " + captcha);
            System.out.print("Input CAPTCHA for verification: ");
            String inputCaptcha = input.nextLine();

            if (inputCaptcha.equalsIgnoreCase(captchas.get(username))) {
                System.out.println("Login success!!");
            } else {
                System.out.println("Verification CAPTCHA failed!");
            }
        } else {
            System.out.println("Incorrect Username password !");
        }
        
    }

    public static String generateCaptcha() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            captcha.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return captcha.toString();
    }
   
}
