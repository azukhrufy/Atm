/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;
import java.util.Random;

/**
 *
 * @author Azukhrufy
 */
public class Bayar_Pulsa extends Transaction{
    private double pulsaamount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser
   private final static int CANCELED = 6;

    public Bayar_Pulsa(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmkeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmkeypad;
    }

    @Override
    public void execute() {
        pulsaamount = BeliPulsa();
        double amount;
        Random rand = new Random();
        int max = 0;
        int min = 0;
        String provider = null;
        if(pulsaamount == CANCELED){
           screen.displayMessageLine("Canceling transaction...");
       }
       else{
        while(provider == null){
            screen.displayMessageLine("\nPilih Provider:");
         screen.displayMessageLine("1 - Indosat");
         screen.displayMessageLine("2 - Telkomsel");
         screen.displayMessageLine("3 - XL/Axis");
         screen.displayMessageLine("4 - 3 (Three)");
         screen.displayMessageLine("5 - Smartfren");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nPilihan: ");
          int input = keypad.getInput();
          switch (input) {
            case 1: 
                max = 599999999;
                min = 500000000;
                provider = "Indosat";
                break;// if the user chose a withdrawal amount 
            case 2: 
                 max = 699999999;
                 min = 600000000;
                 provider = "Telkomsel";
                break;// (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: 
                 max = 799999999;
                 min = 700000000;
                 provider = "XL/Axis";
                break;// corresponding amount from amounts array
            case 4:
                 max = 899999999;
                 min = 800000000;
                 provider = "3 (Three)";
                break;
            case 5:
                max = 999999999;
                min = 900000000; // save user's choice
                provider = "Smartfren";
               break;       
            case CANCELED: // the user chose to cancel
               pulsaamount = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
               break;
            } 
        }
         
          if(pulsaamount != CANCELED){
                amount = pulsaamount/15;
               if(super.getBankDatabase().getAvailableBalance(super.getAccountNumber())>= amount){
                   super.getBankDatabase().belipulsa(super.getAccountNumber(), amount);
                   
                   screen.displayMessageLine("Pembelian Voucher "+ provider + " Sebesar "+pulsaamount+ " ribu Berhasil :");    
                   int value =rand.nextInt((max - min) + 1) + min;
                   screen.displayMessageLine("kode voucher : "+ value);
                    screen.displayMessageLine("Lakukan pengisian pulsa dengan cara : *123*[kode voucher]#");
                    screen.displayMessageLine("Lalu tekan Yes atau Ok");
               }
          } 
       }
    }
    private double BeliPulsa() {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 10, 20, 60, 100, 150};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nWithdrawal Menu:");
         screen.displayMessageLine("1 - Pulsa 10 ribu");
         screen.displayMessageLine("2 - Pulsa 20 ribu");
         screen.displayMessageLine("3 - Pulsa 60 ribu");
         screen.displayMessageLine("4 - Pulsa 100 ribu");
         screen.displayMessageLine("5 - Pulsa 150 ribu");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a withdrawal amount: ");

         int inputs = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch (inputs) {
            case 1: 
                userChoice = amounts[inputs];
                break;// if the user chose a withdrawal amount 
            case 2: 
                userChoice = amounts[inputs];
                break;// (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: 
                userChoice = amounts[inputs];
                break;// corresponding amount from amounts array
            case 4:
                userChoice = amounts[inputs];
                break;
            case 5:
               userChoice = amounts[inputs]; // save user's choice
               break;       
            case CANCELED: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
               break;
         } 
      } 

      return userChoice; // return withdrawal amount or CANCELED
   }
}
