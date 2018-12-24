/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

/**
 *
 * @author Acer
 */
public class Deposit extends Transaction {
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private DepositSlot depositSlot; // reference to deposit slot
   private final static int CANCELED = 0; // constant for cancel option

   // Deposit constructor
   public Deposit(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      DepositSlot atmDepositSlot) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
      

   } 

   // perform transaction
   @Override
   public void execute() {
       amount = promptForDepositAmount();
    if(amount>0){
       getBankDatabase().credit(getAccountNumber(),amount);
       if (cl.printMsg() == 1){screen.displayMessageLine("Your account ("+getAccountNumber()+") deposit has successfully increased in amount of $"+amount);}
       if (cl.printMsg() == 2){screen.displayMessageLine("Akun anda ("+getAccountNumber()+") Berhasil Menambah Deposit sebesar $"+amount);}
    } else {
        if (cl.printMsg() == 1){screen.displayMessageLine("Deposit cannot be less than $0");}
        if (cl.printMsg() == 2){screen.displayMessageLine("Deposit tidak boleh kurang dari $0");}
    }
       
   }

   // prompt user to enter a deposit amount in cents 
   private double promptForDepositAmount() {
      Screen screen = getScreen(); // get reference to screen

      // display the prompt
      if (cl.printMsg() == 1){screen.displayMessage("\nPlease enter a deposit amount in " + "CENTS (or 0 to cancel): ");}
      if (cl.printMsg() == 2){screen.displayMessage("\nMasukan jumlah deposit dalam satuan " + "SEN (atau 0 untuk kembali): ");}
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
