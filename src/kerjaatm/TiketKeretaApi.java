/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

/**
 *
 * @author Firiontina Argandini
 */
public class TiketKeretaApi extends Transaction{
   private Keypad keypad; // reference to keypad

   // constant corresponding to menu option to cancel

   public TiketKeretaApi(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad) {
      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
   }

   // perform transaction
   @Override
   public void execute() {
        screen.displayMessage("\nSilahkan Masukan Kode Booking : ");
        int kode_booking = keypad.getInput();
        
        screen.displayMessage("\nSilahkan Masukan Nominal Pembayaran : ");
        int amount = keypad.getInput();
        
        double available = super.getBankDatabase().getAvailableBalance(super.getAccountNumber());
               
        if(available < amount){
            screen.displayMessageLine("Insuficient balance");
        } else{
            super.getBankDatabase().debit(super.getAccountNumber(), amount);
    //      super.getBankDatabase().credit(accountNumber, amount);
            screen.displayMessageLine("Pembayaran Tiket Kereta Api");
            screen.displayMessageLine("ID Pelanggan     : " +getAccountNumber());
            screen.displayMessageLine("Kode Booking     : " +kode_booking);
            screen.displayMessageLine("Nominal          : " +amount);  
        }
   }
}