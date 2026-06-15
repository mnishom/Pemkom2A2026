package com.ituhn.pemkom2.services;

import com.ituhn.pemkom2.dao.GenericDAO;
import com.ituhn.pemkom2.objects.LogAbsensi;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author mnish
 */
public class LogAbsensiService {
    // Inisialisasi DAO untuk koleksi "log_absensi" [7]
    private final GenericDAO<LogAbsensi> logDAO = new GenericDAO<>("log_absensi", LogAbsensi.class);
    
    public void simpanLog(String hashedUid, String status) {
        // Membuat objek LogAbsensi sesuai parameter di sumber [6]
        LogAbsensi log = new LogAbsensi(
            UUID.randomUUID().toString(), 
            hashedUid, 
            LocalDateTime.now(), 
            status
        );
        logDAO.save(log); // Menyimpan ke MongoDB [7]
    }
    
    
}
