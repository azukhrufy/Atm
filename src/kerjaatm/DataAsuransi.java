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
public class DataAsuransi {
   private Asuransi[] dtAsuransi; // array of Accounts
   
   public DataAsuransi() {
      dtAsuransi = new Asuransi[2]; // just 2 accounts for testing
      dtAsuransi[0] = new Asuransi(77777, "Rara", 100.0);
      dtAsuransi[1] = new Asuransi(73737, "Riri", 200.0);  
   }
   
   public Asuransi getAsuransi(int nomorPolis) {
       for (int i=0;i<dtAsuransi.length;i++){
           if (nomorPolis == dtAsuransi[i].getNomorPolis()){
              return dtAsuransi[i];
           }
       }
       return null; // if no matching account was found, return null
   }
   
   
   
   
   public int getNomorPolis(int noPolisPelanggan){
        return getAsuransi(noPolisPelanggan).getNomorPolis();
    }
   
   public String getNamaPelAsuransi(int noPolisPelanggan){
       return getAsuransi(noPolisPelanggan).getNamaPelAsuransi();
   }
   
   public double getJumlahPremi(int noPolisPelanggan){
       return getAsuransi(noPolisPelanggan).getJumlahPremi();
   }
   
   public boolean cekNoPolis(int noPolisPelanggan){
       if(getAsuransi(noPolisPelanggan) != null){
           return true;
       }
       else {
           return false;
       }
   }
}
