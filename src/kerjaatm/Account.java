/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class Account {
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available & pending deposits
   /* Tambahan <-------------------------*/
   public double limit=9999;
   public ArrayList<String> History = new ArrayList<String>();
   /* Tambahan <-------------------------*/

   // Account constructor initializes attributes
   public Account(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance) {
      accountNumber = theAccountNumber;
      pin = thePIN;
      availableBalance = theAvailableBalance;
      totalBalance = theTotalBalance;
   }

   // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN(int userPIN) {
      if (userPIN != pin) {
         return false;
      }
      else {
         return true;
      }
   } 

   // returns available balance
   public double getAvailableBalance() {
      return availableBalance;
   } 

   // returns the total balance
   public double getTotalBalance() {
      return totalBalance;
   } 

   public void credit(double amount) {
     this.totalBalance+=amount;
     this.availableBalance+=amount;
     
   }

   public void debit(double amount) {
            if(this.availableBalance>amount){
                this.availableBalance-=amount;
                this.totalBalance-=amount;
            }
   }

   public int getAccountNumber() {
      return accountNumber;  
   }

    void belipulsa(double amount) {
        if(this.totalBalance>amount){
                this.totalBalance-=amount;
            }
    }
    
    /* Tambahan <-------------------------*/
   public double getLimit(){
      return limit;
   }
   public void setLimit(double amount){
      limit = limit - amount;
   }
   public void resetLimit(){
      limit = 9999;
   }

   public void addHistory(String word){
         History.add(word);
   }

   public void showHistory(){
      for (int i = 0 ; i< History.size() ; i++ ){
         System.out.println(History.get(i));
      }

   }
   /* Tambahan <-------------------------*/
} 
