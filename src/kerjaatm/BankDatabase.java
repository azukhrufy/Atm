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
public class BankDatabase {
   private Account[] accounts; // array of Accounts
   
   public BankDatabase() {
      accounts = new Account[5]; // just 2 accounts for testing
      accounts[0] = new Account(1234, 4321, 1000.0, 1200.0);
      accounts[1] = new Account(8765, 5678, 200.0, 200.0);  
      accounts[2] = new Account(2222, 3434, 5000.0, 5000.0);
      accounts[3] = new Account(3231, 3536, 2000.0, 2200.0);
      accounts[4] = new Account(1244, 7655, 1000.0, 1000.0);
   }
   
   private Account getAccount(int accountNumber) {
       for(int i=0;i<accounts.length;i++){
           if(accounts[i].getAccountNumber() == accountNumber){
               return accounts[i];
           }
       }
      return null; // if no matching account was found, return null
   } 
   /* Tambahan <-------------------------*/
   public void resetLimit(){
       for(int i=0;i<2;i++){
           accounts[i].resetLimit();
       }
   }
    /* Tambahan <-------------------------*/

   public boolean authenticateUser(int userAccountNumber, int userPIN) {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount(userAccountNumber);

      // if account exists, return result of Account method validatePIN
      if (userAccount != null) {
         return userAccount.validatePIN(userPIN);
      }
      else {
         return false; // account number not found, so return false
      }
   } 

   public double getAvailableBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getAvailableBalance();
   } 

   public double getTotalBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getTotalBalance();
   } 

   public void credit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).credit(amount);
   }

   public void debit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).debit(amount);
   } 
   public void belipulsa(int userAccountNumber,double amount){
       getAccount(userAccountNumber).belipulsa(amount);
   }
   public void transfer(int userAccountNumber,double amount,int targetAccountNumber){
       Account a = getAccount(userAccountNumber);
       Account b = getAccount(targetAccountNumber);
       if(b!= null){
           a.debit(amount);
           b.credit(amount);
       }
   }
   public boolean accountexist(int userAccountNumber){
       if(getAccount(userAccountNumber) != null){
           return true;
       }
       else {
           return false;
       }
   }
   
   /* Tambahan <-------------------------*/
    public void setLimit(int userAccountNumber, double amount){ getAccount(userAccountNumber).setLimit(amount); }

    public double getLimit(int userAccountNumber) { return getAccount(userAccountNumber).getLimit(); }

    public void addHistory(int userAccountNumber, String word) { getAccount(userAccountNumber).addHistory(word); }

    public void showHistory(int userAccountNumber) { getAccount(userAccountNumber).showHistory(); }
    /* Tambahan <-------------------------*/
} 