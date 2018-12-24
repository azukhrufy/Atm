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
public class Withdrawal extends Transaction {
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;

   // Withdrawal constructor
   public Withdrawal(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
      
   }

   // perform transaction
   @Override
   public void execute() {
      amount = displayMenuOfAmounts();
      if(amount<getBankDatabase().getAvailableBalance(getAccountNumber()) && amount != 0){
      if(cl.printMsg() == 1){screen.displayMessageLine("Your account "+getAccountNumber()+" has withdrawn in amount of $" + amount);}
      if(cl.printMsg() == 2){screen.displayMessageLine("Akun anda "+getAccountNumber()+" Berhasil di Withdraw sebesar $" + amount);}
      getBankDatabase().debit(getAccountNumber(),amount);
      }
      else {
          if(amount != 0){
                            if(cl.printMsg() == 1){screen.displayMessageLine("Failed To Withdraw Because of Your Account Balance Is Insufficient");}
                            if(cl.printMsg() == 2){screen.displayMessageLine("Gagal Withdraw karena Balance pada Akun anda tidak mencukupi");}
                         }
          else {
              if(cl.printMsg() == 1){screen.displayMessageLine("Cancelling....");}
              if(cl.printMsg() == 2){screen.displayMessageLine("Membatalkan....");}
          }
          }
   } 

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   private int displayMenuOfAmounts() {
      int userChoice = 99; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 20, 40, 60, 100, 200};

      // loop while no valid choice has been made
      while (userChoice == 99) {
         // display the withdrawal menu
         if(cl.printMsg() == 1){
         screen.displayMessageLine("\nWithdrawal Menu:");
         screen.displayMessageLine("1 - $20");
         screen.displayMessageLine("2 - $40");
         screen.displayMessageLine("3 - $60");
         screen.displayMessageLine("4 - $100");
         screen.displayMessageLine("5 - $200");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a withdrawal amount: ");
         }
         if(cl.printMsg() == 2){
         screen.displayMessageLine("\nMenu Penarikan:");
         screen.displayMessageLine("1 - $20");
         screen.displayMessageLine("2 - $40");
         screen.displayMessageLine("3 - $60");
         screen.displayMessageLine("4 - $100");
         screen.displayMessageLine("5 - $200");
         screen.displayMessageLine("6 - Batal/kembali");
         screen.displayMessage("\nPilih jumlah penarikan: ");
         }


         int inputs = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch (inputs) {
            case 1: 
                userChoice = amounts[1];
                break;// if the user chose a withdrawal amount 
            case 2: 
                userChoice = amounts[2];
                break;// (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: 
                userChoice = amounts[3];
                break;// corresponding amount from amounts array
            case 4:
                userChoice = amounts[4];
                break;
            case 5:
               userChoice = amounts[5]; // save user's choice
               break;       
            case CANCELED: // the user chose to cancel
               userChoice = amounts[0]; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               if(cl.printMsg() == 1){screen.displayMessageLine(
                  "\nInvalid selection. Try again.");}
               if(cl.printMsg() == 2){screen.displayMessageLine(
                  "\nPilihan invalid. Coba lagi.");}
               break;
         } 
      } 

      return userChoice; // return withdrawal amount or CANCELED
   }
} 
