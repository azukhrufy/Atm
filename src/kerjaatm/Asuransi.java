/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Asuransi {
   private int nomorPolis;
   private String nama; 
   private double jumlahPremi; 

   public Asuransi(int polisPelanggan, String namaPelanggan, 
      double jmlPremi) {
      nomorPolis = polisPelanggan;
      nama = namaPelanggan;
      jumlahPremi = jmlPremi;
   }
   
   public boolean validateNomorPolis(int noPolPelanggan){
      if (noPolPelanggan != nomorPolis) {
         return false;
      } else {
         return true;
      }
  }
   
   public int getNomorPolis(){
      return nomorPolis;
  }
   
   public String getNamaPelAsuransi(){
      return nama;
  }
   
   public double getJumlahPremi(){
      return jumlahPremi;
  }
}
