/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

/**
 *
 * @author Azukhrufy
 */
public class TransferBanyak extends Transaction {
    private double transferamount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser
   private final static int CANCELED = 0;
   
   public TransferBanyak(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser,DepositSlot atmDepositSlot) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
      
   }
   @Override
   public void execute() {
       BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      if(cl.printMsg() == 1){screen.displayMessage("Input the number of accounts that you want to transfer :");}
      if(cl.printMsg() == 2){screen.displayMessage("Masukkan jumlah rekening yang akan anda kirim :");}
      int jumkir = keypad.getInput();
      if(jumkir>1){
      int[] tujuans = new int[jumkir];
      int i = 0;
      while(i<jumkir){
          if(cl.printMsg() == 1){screen.displayMessage("Input the account number of No.["+i+"] :");}
          if(cl.printMsg() == 2){screen.displayMessage("Masukkan nomor rekening tujuan ke["+i+"] :");}
          int tujuan = keypad.getInput();
          if(tujuan>999 && tujuan<10000){
     if(getBankDatabase().accountexist(tujuan) == true){
         tujuans[i] = tujuan;
       i++;
     }
     else {
         if(cl.printMsg() == 1){screen.displayMessageLine("That account number is invalid");}
         if(cl.printMsg() == 2){screen.displayMessageLine("Nomor rekening tujuan tidak valid");}
     }
     }
          else{
              if(cl.printMsg() == 1){screen.displayMessageLine("Account number have to be 4 digits of number");}
              if(cl.printMsg() == 2){screen.displayMessageLine("Akun harus merupakan 4 digit angka");}
          }
      }
      i=0;
      transferamount = jumlahTransfer();
      if(transferamount>0){
      if(getBankDatabase().getAvailableBalance(getAccountNumber()) > transferamount*tujuans.length){
    while(i < tujuans.length){
           getBankDatabase().transfer(getAccountNumber(), transferamount,tujuans[i]);
           if(cl.printMsg() == 1){screen.displayMessageLine("Transfer in amount of $"+transferamount+" to account "
                   +tujuans[i]+" is successful");}
           if(cl.printMsg() == 2){screen.displayMessageLine("Transfer sebesar $"+transferamount+" pada Akun "
                   +tujuans[i]+" Telah berhasil");}
       i++;
    }
    }
      else{
          if(cl.printMsg() == 1){screen.displayMessageLine("Insufficient balance..");}
          if(cl.printMsg() == 2){screen.displayMessageLine("Saldo tidak mencukupi..");}
      }
      }
      else{
          if(cl.printMsg() == 1){screen.displayMessageLine("Can only transfer for more than $0");}
          if(cl.printMsg() == 2){screen.displayMessageLine("Hanya dapat mengirim lebih dari $0");}
      }
      }
      else{
          if(cl.printMsg() == 1){screen.displayMessageLine("More than 1 account is required to transfer");}
          if(cl.printMsg() == 2){screen.displayMessageLine("Harus mengirim pada lebih dari 1 Akun");}
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
      
      // check whether the user canceled or entered a valid amount
      if (input == CANCELED) {
         return CANCELED;
      }
      else {
         return (double) input / 100; // return dollar amount
      }
   }
}
