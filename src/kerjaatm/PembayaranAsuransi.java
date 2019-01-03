/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class PembayaranAsuransi extends Transaction {
   private double amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private DataAsuransi asuransi;

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;

   // Withdrawal constructor
   public PembayaranAsuransi(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, DataAsuransi atmAsuransi) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
      asuransi = atmAsuransi;
        }

    @Override
    public void execute() {
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      if(cl.printMsg() == 1){screen.displayMessage("Input your Insurance Policy number :");}
      if(cl.printMsg() == 2){screen.displayMessage("Masukkan nomor Polis Asuransi anda :");}
      int noPolis = keypad.getInput();
       amount = jumlahTransfer();
       if(getBankDatabase().getAvailableBalance(getAccountNumber()) > amount){
           getBankDatabase().transfer(getAccountNumber(), amount,noPolis);
           if(cl.printMsg() == 1){screen.displayMessageLine("Insurance payment in amount of: "+amount+" Policy Number: "+noPolis+" is successful");}
           if(cl.printMsg() == 2){screen.displayMessageLine("Pembayaran asuransi sebesar: "+amount+" Nomor Polis: "+noPolis+" Telah berhasil");}
       }
       else {
           if(cl.printMsg() == 1){screen.displayMessageLine("Insufficient balance..");}
           if(cl.printMsg() == 2){screen.displayMessageLine("Saldo tidak mencukupi..");}
       }
    }
    
    private double jumlahTransfer(){
    Screen screen = getScreen(); // get reference to screen

      // display the prompt
    if(cl.printMsg() == 1){screen.displayMessage("\nInput payment amount: " + "CENTS (or 0 to cancel): ");}
    if(cl.printMsg() == 2){screen.displayMessage("\nMasukan Jumlah Premi: " + "SEN (ketik 0 untuk cancel): ");}
    int input = keypad.getInput(); // receive input of deposit amount
      
      // check whether the user canceled or entered a valid amount
    if (input == CANCELED) {
         return CANCELED;
      }else {
         return (double) input / 100; // return dollar amount
      }
   }
    
   
}
