package com.ituhn.pemkom2;

import com.ituhn.pemkom2.objects.Karyawan;

public class MainApp {
    public static void main(String[] args) {
        Karyawan KR = new Karyawan();
        if(KR instanceof Karyawan){
            System.err.println("Karyawan");
        }else {
            System.err.println("Something else");
        }// //
        
    }
}
