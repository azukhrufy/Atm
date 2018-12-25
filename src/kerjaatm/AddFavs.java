/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

import java.util.*;

/**
 *
 * @author Rinaldi
 */
public class AddFavs {

    private boolean userAuthenticated;
    private int accountNumber;
    private Screen screen;
    private Keypad keypad;
    public ChangeLang cl;
    //rivate int[] favAccounts;
//    public ArrayList<Integer> favAccounts;
//    private ArrayList<Integer> accFavDB;
    //private ArrayList<ArrayList<Integer><ArrayList<Integer>> favAccountDB;
    private BankDatabase bankDatabase;
    Map< Integer, Integer> hm = new HashMap< Integer, Integer>();
    public DuplicateMap<Integer, Integer> dm = new DuplicateMap<>();

    public AddFavs(int currentAccountNumber) {
        accountNumber = currentAccountNumber;
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad 
        bankDatabase = new BankDatabase();
        cl = new ChangeLang();
        //favAccounts = new int[10];
        //ArrayList<Integer> favAccounts = new ArrayList<Integer>();
//        favAccounts = new ArrayList<Integer>();
//        accFavDB = new ArrayList<Integer>();
        //favAccountDB = new ArrayList<ArrayList<Integer>>();

    }

    public class DuplicateMap<K, V> {

        private Map<K, ArrayList<V>> m = new HashMap<>();

        public void put(K k, V v) {
            if (m.containsKey(k)) {
                m.get(k).add(v);
            } else {
                ArrayList<V> arr = new ArrayList<>();
                arr.add(v);
                m.put(k, arr);
            }
        }

        public void remove(K k, V v) {
            if (m.containsKey(k)) {
                m.get(k).remove(v);
            }
        }
        
        public void removeKey(K k) {
            if (m.containsKey(k)) {
                m.remove(k);
            }
        }

        public ArrayList<V> get(K k) {
            return m.get(k);
        }

        public V get(K k, int index) {
            return m.get(k).size() - 1 < index ? null : m.get(k).get(index);
        }

        public boolean contain(K k) {
            return m.containsKey(k);
        }

        public int sizee() {
            return m.size();
        }

        public boolean isItEmpty() {
            return m.isEmpty();
        }

        public boolean containV(K k, V v) {
            return m.get(k).contains(v);
        }
        
        public boolean containAnyV(K k) {
            return m.get(k).isEmpty();
        }
    }

    public void addFavoriteAcc(int accNum) {
//        if (!accFavDB.contains(accNum)) {
//            accFavDB.add(accNum);
//        }
//        if (!dm.contain(accNum)) {
//            dm.put(accNum, null);
//        }
        if (cl.printMsg() == 1) {
            screen.displayMessage("Input the desired account number :");
        }
        if (cl.printMsg() == 2) {
            screen.displayMessage("Masukkan nomor rekening yang ingin anda tambahkan :");
        }
        int tujuan = keypad.getInput();
        if (tujuan == 0) {
            return;
        } else if (bankDatabase.accountexist(tujuan) == true && bankDatabase.getAccountNumber(tujuan) != accNum) {
            if (dm.get(accNum) != null && dm.get(accNum).contains(tujuan)) {
                if (cl.printMsg() == 1) {
                    screen.displayMessage("That account number is already on your list");
                }
                if (cl.printMsg() == 2) {
                    screen.displayMessage("Nomor akun tersebut sudah terdaftar");
                }
            } else if (dm.get(accNum) == null) {
                dm.put(accNum, tujuan);
                if (cl.printMsg() == 1) {
                    screen.displayMessage("The account was succesfuly added to the list");
                }
                if (cl.printMsg() == 2) {
                    screen.displayMessage("Akun tersebut berhasil didaftarkan");
                }
                //favAccountDB.add(accFavDB.get(accNum)favAccounts.add(bankDatabase.getAccountNumber(tujuan)));
            } else if (!dm.get(accNum).contains(tujuan)) {
                dm.put(accNum, tujuan);
                if (cl.printMsg() == 1) {
                    screen.displayMessage("The account was succesfuly added to the list");
                }
                if (cl.printMsg() == 2) {
                    screen.displayMessage("Akun tersebut berhasil didaftarkan");
                }
            } else {
                if (cl.printMsg() == 1) {
                    screen.displayMessage("That account is already on your list");
                }
                if (cl.printMsg() == 2) {
                    screen.displayMessage("Akun tersebut sudah ada di daftar favorit anda");
                }
            }
        } else {
            if (cl.printMsg() == 1) {
                screen.displayMessage("That account number is either non existent or invalid");
            }
            if (cl.printMsg() == 2) {
                screen.displayMessage("Nomor tersebut tidak dapat ditemukan atau invalid");
            }
        }
    }

    public void delFavoriteAcc(int accNum) {
        boolean fin = false;
        if (dm == null || dm.isItEmpty() || dm.get(accNum) == null) {
            if (cl.printMsg() == 1) {
                screen.displayMessage("Your list is empty");
            }
            if (cl.printMsg() == 2) {
                screen.displayMessage("Daftar favorit anda kosong");
            }   
        } else {
            this.dispFav(accNum);
            if (cl.printMsg() == 1) {
                screen.displayMessage("Input your desired account number to be deleted:");
            }
            if (cl.printMsg() == 2) {
                screen.displayMessage("Masukkan nomor rekening yang ingin anda hapus :");
            }
            int tujuan = keypad.getInput();
            //for (int i = 0; i < hm.size(); i++) {
            //if (tujuan == dm.get(accNum)) {
            if (dm.containV(accNum, tujuan)) {
                if (cl.printMsg() == 1) {
                    screen.displayMessage("Are you sure? (Y or N)");
                    keypad.getString();
                }
                if (cl.printMsg() == 2) {
                    screen.displayMessage("Hapus akun ini? (Y atau N)");
                    keypad.getString();
                }
                String chc = keypad.getString();
                while (!fin) {
                    switch (chc) {
                        case "y":
                        case "Y":
                            dm.remove(accNum, tujuan);
                            if (dm.containAnyV(accNum)){
                                dm.removeKey(accNum);
                            }
                            if (cl.printMsg() == 1) {
                                screen.displayMessage("Account " +tujuan+ " has been removed from your list");
                            }
                            if (cl.printMsg() == 2) {
                                screen.displayMessage("Account " +tujuan+ " telah dihapus dari daftar anda");
                            }
                            fin = true;
                            break;
                        case "n":
                        case "N":
                            fin = true;
                            break;
                        default :
                            break;
                    }
                }
            } else {
                if (cl.printMsg() == 1) {
                    screen.displayMessage("That account number is not here");
                }
                if (cl.printMsg() == 2) {
                    screen.displayMessage("Akun tersebut tidak dapat ditemukan");
                }
            }   //}
        }
    }

    public void dispFav(int accNum) {
        boolean once = false;
        String tabFormat = "%-10.10s  %-10.10s%n";
        if (cl.printMsg() == 1) {
            screen.displayMessage("Favorite List : \n");
        }
        if (cl.printMsg() == 2) {
            screen.displayMessage("Daftar akun favorit: \n");
        }
        if (dm == null || dm.isItEmpty() || dm.get(accNum) == null) {
            screen.displayMessage("List is empty");
        } else {
            for (int i = 0; i < ((bankDatabase.getAccountsLength())-1); i++) {
                if (dm.get(accNum, i) != null) {
                    if ((i+1) % 2 == 0) {
                        System.out.printf(tabFormat, "", "[" + (i + 1) + ".]" + dm.get(accNum, i));
                    } else {
                        System.out.print("[" + (i + 1) + ".]" + dm.get(accNum, i));                        
                    }
                } else if (dm.get(accNum, i) != null && !once){
                    System.out.print("...");
                    once = true;
                }
            }System.out.println();
        }
    }
}
