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
public class PembayaranEcommerce {
    private Ecommerce[] Ecommerces;
    
    public PembayaranEcommerce() {
        Ecommerces = new Ecommerce[3];
        Ecommerces[0] = new Ecommerce(677543,200.0);
        Ecommerces[1] = new Ecommerce(677542,1000.0);
        Ecommerces[2] = new Ecommerce(677541,50.0);
    }
    
    private Ecommerce getEcommerce(int idtrans) {
       for(int i=0;i<Ecommerces.length;i++){
           if(Ecommerces[i].getNomortransaksi() == idtrans){
               return Ecommerces[i];
           }
       }
      return null; // if no matching account was found, return null
   } 
   
    public int getNomortransaksi(int notrans){
        return getEcommerce(notrans).getNomortransaksi();
    }
    
    public double getJumlahpembayaran(int notrans){
        return getEcommerce(notrans).getJumlahpembayaran();
    }
    
    public void BayarEcommerce(int userAccountNumber,int notrans,double amount){
        getEcommerce(notrans).changeStat(amount);
    }
    
    public boolean getStatus(int notrans){
        return getEcommerce(notrans).getStatus();
    }
    
    public boolean idtransexist(int notrans){
       if(getEcommerce(notrans) != null){
           return true;
       }
       else {
           return false;
       }
   }
    
}
