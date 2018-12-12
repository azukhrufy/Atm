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
      screen.displayMessage("Masukkan jumlah rekening yang akan anda kirim :");
      int jumkir = keypad.getInput();
      if(jumkir>1){
      int[] tujuans = new int[jumkir];
      int i = 0;
      while(i<jumkir){
          screen.displayMessage("Masukkan nomor rekening tujuan ke["+i+"] :");
          int tujuan = keypad.getInput();
          if(tujuan>999 && tujuan<10000){
     if(getBankDatabase().accountexist(tujuan) == true){
         tujuans[i] = tujuan;
       i++;
     }
     else {
         screen.displayMessageLine("Nomor rekening tujuan tidak valid");
     }
     }
          else{
              screen.displayMessageLine("Akun harus merupakan 4 digit angka");
          }
      }
      i=0;
      transferamount = jumlahTransfer();
      if(transferamount>0){
      if(getBankDatabase().getAvailableBalance(getAccountNumber()) > transferamount*tujuans.length){
    while(i < tujuans.length){
           getBankDatabase().transfer(getAccountNumber(), transferamount,tujuans[i]);
           screen.displayMessageLine("Transfer sebesar $"+transferamount+" pada Akun "+tujuans[i]+" Telah berhasil");
       i++;
    }
    }
      else{
           screen.displayMessageLine("Saldo tidak mencukupi..");
      }
      }
      else{
          screen.displayMessageLine("Hanya dapat mengirim lebih dari $0");
      }
      }
      else{
          screen.displayMessageLine("Harus mengirim pada lebih dari 1 Akun");
      }
   } 
   private double jumlahTransfer(){
       Screen screen = getScreen(); // get reference to screen

      // display the prompt
      screen.displayMessage("\nPlease enter transfer amount in " + 
         "CENTS (or 0 to cancel): ");
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
