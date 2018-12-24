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
      if(cl.printMsg() == 1){screen.displayMessage("Input your desired account number :");}
      if(cl.printMsg() == 2){screen.displayMessage("Masukkan nomor rekening tujuan anda :");}
      int tujuan = keypad.getInput();
    if(tujuan>999 && tujuan<10000){
       if(getBankDatabase().accountexist(tujuan) == true){
       transferamount = jumlahTransfer();
     if(transferamount>0){
       if(getBankDatabase().getAvailableBalance(getAccountNumber()) > transferamount){
           getBankDatabase().transfer(getAccountNumber(), transferamount,tujuan);
           if(cl.printMsg() == 1){screen.displayMessageLine("Transfer in amount of "+transferamount+" to Account "+tujuan+" is successful");}
           if(cl.printMsg() == 2){screen.displayMessageLine("Transfer sebesar "+transferamount+" pada Akun "+tujuan+" Telah berhasil");}

           /* Tambahan <-------------------------*/
           getBankDatabase().setLimit(getAccountNumber(),transferamount);
           printStruk(getAccountNumber(),tujuan,transferamount);

           Date date = new Date();
           String DateCL = "";
           if(cl.printMsg() == 1){DateCL = "MM/dd/yyyy       hh:mm:ss a";}
           if(cl.printMsg() == 2){DateCL = "dd/MM/yyyy       HH:mm:ss";}
           String strDateFormat = DateCL;
           DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
           String formattedDate= dateFormat.format(date);
           if(cl.printMsg() == 1){getBankDatabase().addHistory(getAccountNumber(),formattedDate + "--->Transfer in amount of "+transferamount+" to Account "+tujuan);}
           if(cl.printMsg() == 2){getBankDatabase().addHistory(getAccountNumber(),formattedDate + "--->Transfer sebesar "+transferamount+" pada Akun "+tujuan);}
           /* Tambahan <-------------------------*/
       }
       else {
           if(cl.printMsg() == 1){screen.displayMessageLine("Insufficient balance..");}
           if(cl.printMsg() == 2){screen.displayMessageLine("Saldo tidak mencukupi..");}
       }
     }
     else{
         if(cl.printMsg() == 1){screen.displayMessageLine("Transfer amount cannot be less than 0");}
         if(cl.printMsg() == 2){screen.displayMessageLine("Jumlah transfer tidak boleh dibawah 0");}
     }
     }
     else {
         if(cl.printMsg() == 1){screen.displayMessageLine("That account number is invalid");}
         if(cl.printMsg() == 2){screen.displayMessageLine("Nomor rekening tujuan tidak valid");}
     }
    }
    else {
        if(cl.printMsg() == 1){screen.displayMessageLine("Account number have to be 4 digits");}
        if(cl.printMsg() == 2){screen.displayMessageLine("Nomor rekening tujuan harus 4 digit");}
    }
   } 
   private double jumlahTransfer(){
       Screen screen = getScreen(); // get reference to screen

      // display the prompt
      if(cl.printMsg() == 1){screen.displayMessage("\nPlease enter transfer amount in " + 
         "CENTS (or 0 to cancel): ");}
      if(cl.printMsg() == 2){screen.displayMessage("\nMasukkan jumlah transfer dalam satuan " + 
         "SEN (atau 0 untuk cancel): ");}
      int input = keypad.getInput(); // receive input of deposit amount

       /* Tambahan <-------------------------*/
       if (getBankDatabase().getLimit(getAccountNumber()) < input){
           if(cl.printMsg() == 1){screen.displayMessage("\n You have reached the transfer limit, please wait and try again after 24 hours");}
           if(cl.printMsg() == 2){screen.displayMessage("\n Anda telah mencapai limit transfer, silahkan menunggu dan coba kembali setelah 24 jam");}
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
        String DateCL = "";
        if(cl.printMsg() == 1){DateCL = "MM/dd/yyyy       hh:mm:ss a";}
        if(cl.printMsg() == 2){DateCL = "dd/MM/yyyy       HH:mm:ss";}
        String strDateFormat = DateCL;
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        if(cl.printMsg() == 1){
        screen.displayMessage("\n----------------BUNGUL's ATM--------------\n");
        System.out.println(formattedDate);
        System.out.println("TRANSFER SUCCESSFULLY DONE");
        screen.displayMessage("\n\n\n");
        System.out.println("Sender Account   : " + from);
        System.out.println("Receiver Account : " + to);
        System.out.println("Amount           : $" + amount);
        screen.displayMessage("\n\n\n");
        screen.displayMessage("Thank you for using our service");
        screen.displayMessage("\n----------------BUNGUL's ATM--------------\n");
        }
        if(cl.printMsg() == 2){
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
    }
    /* Tambahan <-------------------------*/
}
