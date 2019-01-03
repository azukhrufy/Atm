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
public class DataTiketPesawat {
    private TiketPesawat[] dtTiketPesawat; // array of Accounts
   
   public DataTiketPesawat() {
      dtTiketPesawat = new TiketPesawat[2]; // just 2 accounts for testing
      dtTiketPesawat[0] = new TiketPesawat(77733, "Rara", 1, 100.0, "JT 325 X BDJ-CGK 07/09 12:45");
      dtTiketPesawat[1] = new TiketPesawat(57575, "Riri", 2, 200.0, "JT 322 X CGK-BDJ 29/11 09:00");  
   }
   
   public TiketPesawat getTiketPesawat(int kodePesan) {
       for (int i=0;i<dtTiketPesawat.length;i++){
           if (kodePesan == dtTiketPesawat[i].getKodeBayar()){
              return dtTiketPesawat[i];
           }
       }
       return null; 
   }
   
   
   
   
   public int getKodeBayar(int kdPesan){
        return getTiketPesawat(kdPesan).getKodeBayar();
    }
   
   public String getNamaPemesan(int kdPesan){
       return getTiketPesawat(kdPesan).getNamaPemesan();
   }
   
   public int getJumlahSeat(int kdPesan){
        return getTiketPesawat(kdPesan).getJumlahSeat();
    }
   
   public double getJumlahBayar(int kdPesan){
       return getTiketPesawat(kdPesan).getJumlahBayar();
   }
   
   public String getNoPenerbangan(int kdPesan){
       return getTiketPesawat(kdPesan).getNoPenerbangan();
   }
   
   public boolean cekKodePesan(int kdPesan){
       if(getTiketPesawat(kdPesan) != null){
           return true;
       }
       else {
           return false;
       }
   }
}
