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
public class Ecommerce {
    private int nomortransaksi;
    private double jumlahpembayaran;
    private boolean status;
    
    public Ecommerce(int notrans,double jumyar){
        nomortransaksi = notrans;
        jumlahpembayaran = jumyar;
        status = false;
    }
    
    
  public boolean validatetransaksi(int notrans){
      if (nomortransaksi != notrans) {
         return false;
      }
      else {
         return true;
      }
  }
  
  public int getNomortransaksi(){
      return nomortransaksi;
  }
  
  public double getJumlahpembayaran(){
      return jumlahpembayaran;
  }
  
  public boolean getStatus(){
      return status;
  }
  public void changeStat(double jumkir){
      if(jumkir == jumlahpembayaran){
          status = true;
      }
  }
}
