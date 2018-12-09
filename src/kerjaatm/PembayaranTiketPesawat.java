/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

/**
 *
 * @author user
 */
public class PembayaranTiketPesawat extends Transaction {
   private double amount; // amount to withdraw
   private Keypad keypad; // reference to keypad

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;

   // Withdrawal constructor
   public PembayaranTiketPesawat(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
       }

        @Override
    public void execute() {
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      screen.displayMessage("Masukkan Kode Pembayaran :");
      int kodePembayaran = keypad.getInput();
       amount = jumlahTransfer();
       if(getBankDatabase().getAvailableBalance(getAccountNumber()) > amount){
           getBankDatabase().transfer(getAccountNumber(), amount,kodePembayaran);
           screen.displayMessageLine("Pembayaran tiket pesawat dengan kode Pembayaran "+kodePembayaran+" Telah berhasil");
       }
       else {
           screen.displayMessageLine("Saldo tidak mencukupi..");
       }
    }
    
    private double jumlahTransfer(){
    Screen screen = getScreen(); // get reference to screen

      // display the prompt
    screen.displayMessage("\nMasukan Jumlah Pembayaran: " + 
       "CENTS (ketik 0 untuk cancel): ");
    int input = keypad.getInput(); // receive input of deposit amount
      
      // check whether the user canceled or entered a valid amount
    if (input == CANCELED) {
         return CANCELED;
      }else {
         return (double) input / 100; // return dollar amount
      }
   }
    
}
