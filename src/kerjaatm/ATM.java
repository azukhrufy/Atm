/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class ATM {
   private boolean userAuthenticated; // whether user is authenticated
   private int currentAccountNumber; // current user's account number
   private Screen screen; // ATM's screen
   private Keypad keypad; // ATM's keypad
   private CashDispenser cashDispenser; // ATM's cash dispenser
   private BankDatabase bankDatabase; // account information database
   private DepositSlot depositSlot;
   private PembayaranEcommerce eCommerce;
   public ChangeLang cl;
   
    private static final int Language = 1;
    private static final int Bahasa = 2;
    private boolean check_1 = false;
    public int testt = 1;
   
   public String dateNow;

   // constants corresponding to main menu options
   private static final int BALANCE_INQUIRY = 1;
   private static final int WITHDRAWAL = 2;
   private static final int DEPOSIT = 3;
   private static final int TRANSFER = 4;
   //private static final int BELIPULSA = 5;
   //private static final int MATAUANG = 6;
   //private static final int PEMBAYARANASURANSI =7;
   //private static final int PEMBAYARANTIKETPESAWAT =8;
  // private static final int PEMBAYARANECOMMERCE =9;
//   private static final int TRANSFERBANYAK = 10;
   private static final int ANOTHER_PAYMENT = 5;
   private static final int HISTORY = 6;
   private static final int EXIT = 7;

   // no-argument ATM constructor initializes instance variables
   public ATM() {
      userAuthenticated = false; // user is not authenticated to start
      currentAccountNumber = 0; // no current account number to start
      screen = new Screen(); // create screen
      keypad = new Keypad(); // create keypad 
      cashDispenser = new CashDispenser(); // create cash dispenser
      bankDatabase = new BankDatabase();// create acct info database
      depositSlot = new DepositSlot();
      eCommerce = new PembayaranEcommerce();
      cl = new ChangeLang();
   }

   // start ATM 
   public void run() {
      // welcome and authenticate user; perform transactions
      while (true) {
         run_for_language();
         // loop while user is not yet authenticated
         while (!userAuthenticated) {
                if (cl.printMsg() == 1) {screen.displayMessageLine("\nWelcome!");}
                if (cl.printMsg() == 2) {screen.displayMessageLine("\nSelamat Datang!");}  
            authenticateUser(); // authenticate user
         }
         
         performTransactions(); // user is now authenticated
         userAuthenticated = false; // reset before next ATM session
         currentAccountNumber = 0; // reset before next ATM session
         if (cl.printMsg() == 1) {screen.displayMessageLine("\nThank you! Goodbye!");}
         if (cl.printMsg() == 2) {screen.displayMessageLine("\nTerimakasih telah menggunakan layanan kami!");}  
      }
   }

   // attempts to authenticate user against database
    private void authenticateUser() {
        ATM theATM = new ATM();
       int tries = 0;
        while(true) {
            if (cl.printMsg() == 1) {screen.displayMessage("\nPlease enter your account number : ");}
            if (cl.printMsg() == 2) {screen.displayMessage("\nSilahkan masukkan nomor akun anda: ");}
            int accountNumber;

               if(keypad.IsInteger()){
                   accountNumber = keypad.getInput();
                   if(accountNumber>999 && accountNumber<10000 ){
          if(accountNumber>999 && accountNumber<10000){
            if (cl.printMsg() == 1) {screen.displayMessage("\nEnter your PIN : ");}
            if (cl.printMsg() == 2) {screen.displayMessage("\nMasukkan nomor PIN anda: ");}
            if(keypad.IsInteger()){
            int pin = keypad.getInput();
                if(pin>999 && pin<10000){
            userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
       
            if(userAuthenticated){
                currentAccountNumber = accountNumber;
                break;
            }
            else{
                if (cl.printMsg() == 1) {
                    screen.displayMessageLine("Invalid Account Number or PIN.");
                    screen.displayMessageLine("Please Try Again.");
                }
                if (cl.printMsg() == 2) {
                    screen.displayMessageLine("Nomor Akun atau Pin salah.");
                    screen.displayMessageLine("Coba lagi.");
                }
                tries++;
            }
            if(tries > 3) {
                if (cl.printMsg() == 1) {screen.displayMessageLine("Your bank account is blocked!");}
                if (cl.printMsg() == 2) {screen.displayMessageLine("Akun anda diblokir!");}
                System.exit(0);
            }
          }
          else
          {
              if (cl.printMsg() == 1) {screen.displayMessageLine("PIN must be 4 digits, you directed to input account Number again");}
              if (cl.printMsg() == 2) {screen.displayMessageLine("PIN harus 4 digit, anda dikembalikan ke laman nomor akun kembali");}
          }
          } else {
                screen.displayMessageLine("Input harus Angka!");
                theATM.run();
            }
          } else
          {
              if (cl.printMsg() == 1) {screen.displayMessageLine("Account number must be 4 digits");}
              if (cl.printMsg() == 2) {screen.displayMessageLine("Nomor akun harus 4 digit!");}
          }
          
        }
     }
         else {
                   screen.displayMessageLine("Input harus Angka!");
                   theATM.run();

               }   
   }
               
    }
    
    public void run_for_language() {
        boolean userExited = false;
        screen.displayMessageLine("\nWelcome | Selamat Datang");
        screen.displayMessageLine("\nChoose your language | Pilih bahasa");
        screen.displayMessageLine("\n1. English");
        screen.displayMessageLine("2. Bahasa");
        int LB = keypad.getInput();
        while (LB != 0) {
            switch (LB) {
                case Language:
                    cl.Sel(1); //menentukan
                    LB = 0; //untuk escape
                    break;
                case Bahasa:
                    cl.Sel(2);
                    LB = 0;
                    break;
                default:
                    break;
            }
        }
        if (LB == 0) {
            userExited = true;
        }
    }

   // display the main menu and perform transactions
   private void performTransactions() {
      // local variable to store transaction currently being processed
      Transaction currentTransaction = null;
      boolean userExited = false; // user has not chosen to exit

      // loop while user has not chosen option to exit system
      while (!userExited) {
         // show main menu and get user selection
         int mainMenuSelection = displayMainMenu();

         // decide how to proceed based on user's menu selection
         switch (mainMenuSelection) {
            // user chose to perform one of three transaction types
            case BALANCE_INQUIRY:         

               // initialize as new object of chosen type
               currentTransaction = 
                  createTransaction(mainMenuSelection);
                if(currentTransaction != createTransaction(0)){
                    
                    currentTransaction.execute();
                }
                 // execute transaction
               break; 
            case WITHDRAWAL:
                
                 currentTransaction =
                         createTransaction(mainMenuSelection);
                 
                 currentTransaction.execute();
                 break;
            case DEPOSIT:
                
                 currentTransaction =
                         createTransaction(mainMenuSelection);
                 
                 currentTransaction.execute();
                 break;
             case TRANSFER:
                
                 currentTransaction =
                         createTransaction(mainMenuSelection);
                 if(currentTransaction != createTransaction(0)){
                    
                    currentTransaction.execute();
                }
                break;
             case ANOTHER_PAYMENT :
                currentTransaction = 
                createTransaction(mainMenuSelection);
                if(currentTransaction != createTransaction(0)){
                    
                    currentTransaction.execute();
                }
                break;
             case HISTORY:

               currentTransaction =
                       createTransaction(mainMenuSelection);

               currentTransaction.execute();
               break;
            case EXIT: // user chose to terminate session
               if (cl.printMsg() == 1) {screen.displayMessageLine("\nExiting the system...");}
               if (cl.printMsg() == 2) {screen.displayMessageLine("\nMenutup sistem...");}
               userExited = true;
               break;
            default: // 
               if (cl.printMsg() == 1) {screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");}
               if (cl.printMsg() == 2) {screen.displayMessageLine("\nPilihan tidak valid. Coba lagi.");}
               break;
         }
      } 
   } 

   // display the main menu and return an input selection
   private int displayMainMenu() {
       if (cl.printMsg() == 1) {
      screen.displayMessageLine("\nMain menu:");
      screen.displayMessageLine("1 - View my balance");
      screen.displayMessageLine("2 - Withdraw cash");
      screen.displayMessageLine("3 - Deposit funds");
      screen.displayMessageLine("4 - Transfer");
//      screen.displayMessageLine("5 - Beli Pulsa");
//      screen.displayMessageLine("6 - View My Balance (dalam Rupiah)");
//      screen.displayMessageLine("7 - Pembayaran Asuransi");
//      screen.displayMessageLine("8 - Pembayaran Tiket Pesawat");
//      screen.displayMessageLine("9 - Pembayaran E Commerce");
//      screen.displayMessageLine("10 - Transfer ke banyak account");
      screen.displayMessageLine("5 - Another Payment");
      screen.displayMessageLine("6 - Show History");
      screen.displayMessageLine("7 - Exit\n");
       }
       if (cl.printMsg() == 2) {
            screen.displayMessageLine("\nMenu:");
            screen.displayMessageLine("1 - Info saldo");
            screen.displayMessageLine("2 - Penarikan saldo");
            screen.displayMessageLine("3 - Deposit");
            screen.displayMessageLine("4 - Transfer");
//      screen.displayMessageLine("5 - Beli Pulsa");
//      screen.displayMessageLine("6 - View My Balance (dalam Rupiah)");
//      screen.displayMessageLine("7 - Pembayaran Asuransi");
//      screen.displayMessageLine("8 - Pembayaran Tiket Pesawat");
//      screen.displayMessageLine("9 - Pembayaran E Commerce");
//      screen.displayMessageLine("10 - Transfer ke banyak account");
        screen.displayMessageLine("5 - Pembayaran Lainnya");
        screen.displayMessageLine("6 - Tampilkan history transaksi");
        screen.displayMessageLine("7 - Keluar\n");
        }
      
      /* Tambahan <-------------------------*/
      Date date = new Date();
      String strDateFormat = "yyyy/MM/dd       hh:mm:ss a";
      String dates = "hh";
      DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
      DateFormat justDate = new SimpleDateFormat(dates);
      String formattedDate= dateFormat.format(date);
      if (dateNow != justDate.format(date)){
         bankDatabase.resetLimit();
      }
      dateNow = justDate.format(date);
      if (cl.printMsg() == 1) {System.out.println("Timestamp : " + formattedDate);}
      if (cl.printMsg() == 2) {System.out.println("Waktu Setempat : " + formattedDate);}

      /* Tambahan <-------------------------*/
      
      if (cl.printMsg() == 1) {screen.displayMessage("Enter a choice: ");}
      if (cl.printMsg() == 2) {screen.displayMessage("Masukkan Pilihan: ");}
      return keypad.getInput();
       // return user's selection
   } 
         
   private Transaction createTransaction(int type) {
      Transaction temp = null; 
      int pilih;
       
              switch (type) {
         case BALANCE_INQUIRY:
             if (cl.printMsg() == 1) {
            screen.displayMessageLine("ANOTHER PAYMENT");
            screen.displayMessageLine("1 - View My Balance (in $)");
            screen.displayMessageLine("2 - View My Balance (in Rupiah)");
            screen.displayMessageLine("0 - Exit\n");
            screen.displayMessage("Enter a choice: ");
             }
             if (cl.printMsg() == 2) {
            screen.displayMessageLine("Pembayaran Lain");
            screen.displayMessageLine("1 - Lihat saldo (dalam satuan Dollar)");
            screen.displayMessageLine("2 - Lihat saldo (dalam satuan Rupiah)");
            screen.displayMessageLine("0 - Keluar\n");
            screen.displayMessage("Masukkan pilihan: ");
             }
            pilih = 0;
            pilih = keypad.getInput();
            switch(pilih) {
                case 1 : 
                temp = new BalanceInquiry(
               currentAccountNumber, screen, bankDatabase);
            break;
                case 2 : 
                    
                temp = new KonversiUang(currentAccountNumber, screen, bankDatabase);
                break;
                case 0: // user chose to terminate session
               if (cl.printMsg() == 1) {screen.displayMessageLine("\nExiting..");}
               if (cl.printMsg() == 2) {screen.displayMessageLine("\nSedang menutup...");}
               break;
                default: // 
               if (cl.printMsg() == 1) {screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");}
               if (cl.printMsg() == 2) {screen.displayMessageLine("\nAnda tidak memasukan pilihan yang valid. Coba lagi");}
               break;
            }
            break;
         case WITHDRAWAL:
             temp = new Withdrawal(currentAccountNumber,screen,bankDatabase,keypad,cashDispenser);
             break;
         case DEPOSIT:
             temp = new Deposit(currentAccountNumber,screen,bankDatabase,keypad,depositSlot);
             break;
         case TRANSFER:
             if (cl.printMsg() == 1) {
                 screen.displayMessageLine("TRANSFER :");
                 screen.displayMessageLine("1 - Transfer to 1 account");
                 screen.displayMessageLine("2 - Transfer to many accounts");
                 screen.displayMessageLine("0 - Exit\n");
                 screen.displayMessage("Enter a choice: ");
             }
             if (cl.printMsg() == 2) {
                 screen.displayMessageLine("TRANSFER :");
                 screen.displayMessageLine("1 - Transfer ke 1 akun");
                 screen.displayMessageLine("2 - Transfer ke banyak akun");
                 screen.displayMessageLine("0 - Kembali\n");
                 screen.displayMessage("Pilihan: ");
             }
            pilih = 0;
            pilih = keypad.getInput();
            switch(pilih) {
                case 1 : 
                    temp = new Transfer(currentAccountNumber,screen,bankDatabase,keypad,cashDispenser,depositSlot);
                    break;
                case 2 : 
                    
                    temp = new TransferBanyak(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser, depositSlot);
                    break;
                case 0: // user chose to terminate session
               if (cl.printMsg() == 1) {screen.displayMessageLine("\nExiting..");}
               if (cl.printMsg() == 2) {screen.displayMessageLine("\nSedang keluar..");}
               break;
                default: // 
               if (cl.printMsg() == 1) {screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");}
               if (cl.printMsg() == 2) {screen.displayMessageLine("\nPilihan yang anda masukkan tidak valid. Coba lagi");}
               break;
            }
            break;
//         case TRANSFERBANYAK:
//             temp = new TransferBanyak(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser, depositSlot);
//             break;
         case ANOTHER_PAYMENT :
            if (cl.printMsg() == 1) {
            screen.displayMessageLine("ANOTHER PAYMENT");
            screen.displayMessageLine("1 - Pulsa Listrik");
            screen.displayMessageLine("2 - Pajak");
            screen.displayMessageLine("3 - Tiket Kereta Api");
            screen.displayMessageLine("4 - Pulsa");
            screen.displayMessageLine("5 - Asuransi");
            screen.displayMessageLine("6 - Tiket Pesawat");
            screen.displayMessageLine("7 - e - Commerce");
            screen.displayMessageLine("0 - Exit\n");
            screen.displayMessage("Enter a choice: ");
            }
            if (cl.printMsg() == 2) {
                screen.displayMessageLine("PEMBAYARAN LAIN");
                screen.displayMessageLine("1 - Pulsa Listrik");
                screen.displayMessageLine("2 - Pajak");
                screen.displayMessageLine("3 - Tiket Kereta Api");
                screen.displayMessageLine("4 - Pulsa");
                screen.displayMessageLine("5 - Asuransi");
                screen.displayMessageLine("6 - Tiket Pesawat");
                screen.displayMessageLine("7 - e - Commerce");
                screen.displayMessageLine("0 - Kembali\n");
                screen.displayMessage("Pilihan: ");
            }
            pilih = 0;
            pilih = keypad.getInput(); // return user's selection
            
            switch(pilih) {
                case 1 : 
                    temp = new PulsaListrik (currentAccountNumber, screen, bankDatabase, keypad);
                    break;
                case 2 : 
                    temp = new Pajak (currentAccountNumber, screen, bankDatabase, keypad);
                    break;
                case 3 : 
                    temp = new TiketKeretaApi (currentAccountNumber, screen, bankDatabase, keypad);
                    break;
                case 4 : 
                    temp = new Bayar_Pulsa(currentAccountNumber, screen, bankDatabase, keypad);
                    break;
                case 5 : 
                    temp = new PembayaranAsuransi(currentAccountNumber, screen, bankDatabase, keypad);
                    break;
                case 6 : 
                    temp = new PembayaranTiketPesawat(currentAccountNumber, screen, bankDatabase, keypad);
                    break;
                case 7 : 
                    temp = new EcommerceScreen(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser, depositSlot, eCommerce);
                    break;
                case 0: // user chose to terminate session
                    if (cl.printMsg() == 1) {screen.displayMessageLine("\nExiting the system...");}
                    if (cl.printMsg() == 2) {screen.displayMessageLine("\nMenutup sistem...");}
               break;
                default: // 
                        if (cl.printMsg() == 1)screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
                        if (cl.printMsg() == 2)screen.displayMessageLine("\nPilihan anda tidak valid. Coba lagi.");
               break;
            }
            break;
         case HISTORY:
             temp = new History(currentAccountNumber,screen,bankDatabase);
             break;
      
          }
      

      return temp;
   } 
}
