package com.ituhn.pemkom2;

import com.ituhn.pemkom2.objects.Karyawan;

public class MainApp {
    public static void main(String[] args) {
        Karyawan k = new Karyawan();
        k.setUidRfid("12345678");
        k.setIdKaryawan("321");
        k.setNamaLengkap("Galuh"); 
        k.setDepartemen("Teller"); 
        
//        String data = k.toString();
        System.err.println(k.toString());
        
        
        
        
    }
}
