/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan.del;

/**
 *
 * @author Kelompok 12
 */
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Buku { 
    public int nomorBuku;
    public String kategori;
    public String judul;
    public String penulis;
    public String penerbit;
    public String tanggalPinjam;
    public String tanggalKembali;
    public int noBukuPerpustakaan;


    public Buku( String judul, String penulis, String penerbit, String kategori){
        this.kategori = kategori;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
    }
    public Buku(){

    }
    // setter
    public void setTanggalPinjam() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date tgl = cal.getTime();
        this.tanggalPinjam = formatter.format(tgl);
    }

    public void setTanggalKembali() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,7);
        Date tgl = cal.getTime();
        this.tanggalKembali = formatter.format(tgl);
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setNomorBuku(int nomorBuku) {
        this.nomorBuku = nomorBuku;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

   


    //getter

    public int getNomorBuku() {
        return nomorBuku;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public int getNoBukuPerpustakaan() {
        return noBukuPerpustakaan;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public String getKategori() {
        return kategori;
    }

    public String getTanggalPinjam() {

        return tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }


}
