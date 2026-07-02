package com.ituhn.pemkom2.objects;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class Karyawan {
    @BsonId // Menandakan bahwa field ini adalah "_id" di MongoDB
    private ObjectId id;
    
    private String uidRfid;
    private String idKaryawan;
    private String namaLengkap;
    private String departemen;

    public Karyawan() {
    }

// Constructor untuk insert data baru (ID belum ada/auto-generated dari Mongo)
    public Karyawan(String uidRfid, String idKaryawan, String namaLengkap, String departemen) {
        this.uidRfid = uidRfid;
        this.idKaryawan = idKaryawan;
        this.namaLengkap = namaLengkap;
        this.departemen = departemen;
    }

    // Constructor lengkap (Dipakai saat memuat data yang sudah memiliki ID)
    public Karyawan(ObjectId id, String uidRfid, String idKaryawan, String namaLengkap, String departemen) {
        this.id = id;
        this.uidRfid = uidRfid;
        this.idKaryawan = idKaryawan;
        this.namaLengkap = namaLengkap;
        this.departemen = departemen;
    }

    

    @Override
    public String toString() {
        return "Karyawan{" + 
                "id=" + (id != null ? id.toHexString() : "null") +
                "uidRfid=" + uidRfid + 
                ", idKaryawan=" + idKaryawan + 
                ", namaLengkap=" + namaLengkap + 
                ", departemen=" + departemen + '}';
    }
    
    
    // GETTER DAN SETTER UNTUK OBJECTID
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUidRfid() {
        return uidRfid;
    }

    public void setUidRfid(String uidRfid) {
        this.uidRfid = uidRfid;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }
}
