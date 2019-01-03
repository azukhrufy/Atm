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
public class TiketPesawat {
   private int kodeBayar;
   private String nama; 
   private int jumlahSeat;
   private double jumlahPembayaran;
   private String nomorPenerbangan;

   public TiketPesawat(int kBayar, String namaPemesan, int jmlSeat,
      double jmlPembayaran, String noPenerbangan) {
      kodeBayar = kBayar;
      nama = namaPemesan;
      jumlahSeat = jmlSeat;
      jumlahPembayaran = jmlPembayaran;
      nomorPenerbangan = noPenerbangan;
   }
   
   public boolean validateKodePembayaran(int kdBayar){
      if (kdBayar != kodeBayar) {
         return false;
      } else {
         return true;
      }
  }
   
   public int getKodeBayar(){
      return kodeBayar;
  }
   
   public String getNamaPemesan(){
      return nama;
  }
   
    public int getJumlahSeat(){
      return jumlahSeat;
  }
   
   public double getJumlahBayar(){
      return jumlahPembayaran;
  }
   
   public String getNoPenerbangan(){
      return nomorPenerbangan;
  }
}
