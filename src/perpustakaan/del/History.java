/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan.del;

/**
 *
 * @author Kelompok 12
 */
import java.util.ArrayList;

public class History extends Buku{
    ArrayList<Buku> bukuPinjam = new ArrayList<Buku>();

    public History(){
        super();
    }

    private static void clearScreen(){
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (Exception ex){
            System.err.println("tidak bisa clear screen");
        }
    }

    public void tampilkanRiwayat(){
        clearScreen();
        System.out.println("===== RIWAYAT PEMINJAMAN =====\n");
        int num = 0;
        for (Buku bukuPinjam : bukuPinjam){
            ++num;
            System.out.println("Peminjaman Ke-"+num);
            System.out.println("Pada               : "+bukuPinjam.getTanggalPinjam());
            System.out.println("Batas Pengembalian : "+bukuPinjam.getTanggalKembali()+ " 23:59:59");
            System.out.println("Nomor Buku         : "+bukuPinjam.getNomorBuku());
            System.out.println("Judul              : "+bukuPinjam.getJudul());
            System.out.println("");
        }
    }
}

