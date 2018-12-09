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
public class Transfer extends Transaction {
    private double transferamount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser
   private final static int CANCELED = 0;
   
   
    public Transfer(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser,DepositSlot atmDepositSlot) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
      
   }
    
       // perform transaction
   @Override
   public void execute() {
       BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      screen.displayMessage("Masukkan nomor rekening tujuan anda :");
      int tujuan = keypad.getInput();
       if(getBankDatabase().accountexist(tujuan) == true){
       transferamount = jumlahTransfer();
       if(getBankDatabase().getAvailableBalance(getAccountNumber()) > transferamount){
           getBankDatabase().transfer(getAccountNumber(), transferamount,tujuan);
           screen.displayMessageLine("Transfer sebesar "+transferamount+" pada Akun "+tujuan+" Telah berhasil");

           /* Tambahan <-------------------------*/
           getBankDatabase().setLimit(getAccountNumber(),transferamount);
           printStruk(getAccountNumber(),tujuan,transferamount);

           Date date = new Date();
           String strDateFormat = "yyyy/MM/dd       hh:mm:ss a";
           DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
           String formattedDate= dateFormat.format(date);
           getBankDatabase().addHistory(getAccountNumber(),formattedDate + "--->Transfer sebesar "+transferamount+" pada Akun "+tujuan);
           /* Tambahan <-------------------------*/
       }
       else {
           screen.displayMessageLine("Saldo tidak mencukupi..");
       }
     }
     else {
         screen.displayMessageLine("Nomor rekening tujuan tidak valid");
     }
   } 
   private double jumlahTransfer(){
       Screen screen = getScreen(); // get reference to screen

      // display the prompt
      screen.displayMessage("\nPlease enter transfer amount in " + 
         "CENTS (or 0 to cancel): ");
      int input = keypad.getInput(); // receive input of deposit amount

       /* Tambahan <-------------------------*/
       if (getBankDatabase().getLimit(getAccountNumber()) < input){
           screen.displayMessage("\n Anda telah mencapai limit transfer, silahkan menunggu kembali setelah 24 jam");
           return  CANCELED; }
       /* Tambahan <-------------------------*/

       // check whether the user canceled or entered a valid amount
       if (input == CANCELED) {
         return CANCELED;
      }

      else {
         return (double) input / 100; // return dollar amount
      }
   }

    /* Tambahan <-------------------------*/
    private void printStruk(int from,int to,double amount){
        Screen screen = getScreen();
        Date date = new Date();
        String strDateFormat = "yyyy/MM/dd       hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        screen.displayMessage("\n----------------PT. ATM BUNGUL--------------\n");
        System.out.println(formattedDate);
        System.out.println("TRANSFER BERHASIL DILAKUKAN");
        screen.displayMessage("\n\n\n");
        System.out.println("Dari Rekening   : " + from);
        System.out.println("Rekening Tujuan : " + to);
        System.out.println("Jumlah          : $" + amount);
        screen.displayMessage("\n\n\n");
        screen.displayMessage("Terimakasih telah menggunakan layanan kami");
        screen.displayMessage("\n----------------PT. ATM BUNGUL--------------\n");
    }
    /* Tambahan <-------------------------*/
}
