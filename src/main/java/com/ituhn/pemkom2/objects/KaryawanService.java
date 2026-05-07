/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ituhn.pemkom2.objects;

import com.mongodb.client.model.Filters;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.bson.conversions.Bson;


/**
 *
 * @author mnish
 */
public class KaryawanService {
    // Inisialisasi GenericDAO khusus untuk entitas Karyawan
    // Menggunakan koleksi "karyawan" dan referensi Class Karyawan [3]
    private final GenericDAO<Karyawan> karyawanRepo;
    
    public KaryawanService() {
        this.karyawanRepo = new GenericDAO<>("karyawan", Karyawan.class);
    }
    
    /**
     * 1.CREATE: Fungsi untuk menyimpan data karyawan baru ke MongoDB [2], [3]
     * @param karyawanBaru
     */
    public void tambahKaryawan(Karyawan karyawanBaru) {
        karyawanRepo.save(karyawanBaru); // Memanggil insertOne melalui GenericDAO [3]
    }
    
    public void tambahKaryawan(String uidRfid, String idKaryawan,  String namaLengkap, String departemen) {
        Karyawan karyawanBaru = new Karyawan(uidRfid, idKaryawan, namaLengkap, departemen);
        karyawanRepo.save(karyawanBaru); // Memanggil insertOne melalui GenericDAO [3]
    }
    
    
    /**
     * 2. READ (All): Fungsi untuk mengambil semua data karyawan [5], [6]
     */
    public void tampilkanDaftarKaryawan() {
        List<Karyawan> daftar = karyawanRepo.findAll();
        System.out.println("--- Daftar Karyawan Bank ---");
        for (Karyawan k : daftar) {
            System.out.println(k.toString()); // Menggunakan format toString di sumber [7]
        }
    }
    
    /**
     * 2.READ (All): Fungsi untuk mengambil semua data karyawan [5], [6]
     * @param panelTarget
     */
 public void tampilkanDaftarKaryawan(JPanel panelTarget) {
        // 1. Mengambil data dari database menggunakan GenericDAO
        List<Karyawan> daftarKaryawan = karyawanRepo.findAll();

        // 2. Membersihkan panel target utama sebelum memuat data baru
        panelTarget.removeAll();
        
        // Mengubah layout panel target menjadi BorderLayout
        panelTarget.setLayout(new BorderLayout()); 
        // Mengatur warna background utama menjadi biru
        panelTarget.setBackground(new Color(68, 114, 196)); 

        
        
        // Membuat panel grid khusus untuk menampung kotak/card
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        gridPanel.setOpaque(false); // Transparan agar warna biru panelTarget terlihat
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Memberi jarak dari tepi layar
        
        // 3. Iterasi data dan menambahkannya ke panel grid
        for (Karyawan k : daftarKaryawan) {
            // Membuat panel 'Card' (box orange) untuk 1 karyawan
            // Layout 2 baris 1 kolom agar Nama di atas, Departemen di bawah
            JPanel cardPanel = new JPanel(new GridLayout(3, 1, 0, 15)); 
            cardPanel.setBackground(new Color(237, 125, 49)); // Warna background orange
            
            // Memberikan garis tepi tipis membulat (rounded) dan padding/jarak ke dalam
            cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));

            // Membuat Label Nama & Set warna teks jadi Putih
            JLabel lblNama = new JLabel("Nama: " + k.getNamaLengkap());
            lblNama.setForeground(Color.WHITE);
            
            // Membuat Label Departemen & Set warna teks jadi Putih
            JLabel lblDept = new JLabel("Departmen: " + k.getDepartemen());
            lblDept.setForeground(Color.WHITE);
            
            JButton tombolEdit = new JButton("Edit");
            tombolEdit.setBackground(Color.ORANGE); 
            tombolEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, k.getNamaLengkap());
                }
            });
            
            // Memasukkan label ke dalam cardPanel (box orange)
            cardPanel.add(lblNama);
            cardPanel.add(lblDept);
            cardPanel.add(tombolEdit);

            // Memasukkan cardPanel utuh ke dalam gridPanel
            gridPanel.add(cardPanel);
        }

        // Memasukkan gridPanel ke bagian ATAS (NORTH) dari panel target.
        panelTarget.add(gridPanel, BorderLayout.NORTH);

        // 4. Me-refresh panel agar perubahan muncul di GUI
        panelTarget.revalidate();
        panelTarget.repaint();
}

    /**
     * 3.READ (One): Mencari satu karyawan spesifik berdasarkan UID RFID [5], [6]
        Sangat krusial untuk alur Tap Kartu pada Pertemuan 14 [8].
     * @param uid
     * @return 
     */
    public Karyawan cariKaryawanByRFID(String uid) {
        Bson filter = Filters.eq("uidRfid", uid);
        return karyawanRepo.findOne(filter);
    }

    /**
     * 4.UPDATE: Memperbarui data karyawan menggunakan filter Bson [5], [6]
     * @param idK
     * @param departemenBaru
     */
    public void perbaruiDepartemen(String idK, String departemenBaru) {
        Bson filter = Filters.eq("idKaryawan", idK);
        Karyawan karyawan = karyawanRepo.findOne(filter);
        
        if (karyawan != null) {
            karyawan.setDepartemen(departemenBaru);
            karyawanRepo.update(filter, karyawan); // Menggunakan replaceOne [6]
            System.out.println("Data departemen berhasil diperbarui.");
        }
    }

    /**
     * 5.DELETE: Menghapus data karyawan dari database [5], [6]
     * @param idK
     */
    public void hapusKaryawan(String idK) {
        Bson filter = Filters.eq("idKaryawan", idK);
        karyawanRepo.delete(filter); // Menggunakan deleteOne [6]
        System.out.println("Data karyawan berhasil dihapus.");
    }
}
