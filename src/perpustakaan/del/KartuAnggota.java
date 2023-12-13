/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan.del;

/**
 *
 * @author Kelompok 12
 */
public class KartuAnggota extends Anggota{
    public Role role = Role.anggota;
    public String nama;
    public int nim;

    public KartuAnggota(int nim, String nama){
        super(nim, nama);
        super.role = Role.anggota;
    }

    @Override
    public int getnim() {
        return super.getnim();
    }

    @Override
    public String getNama() {
        return super.getNama();
    }

    private static void clearScreen(){
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (Exception ex){
            System.err.println("tidak bisa clear screen");
        }
    }

    public String cetakKartuAnggota(){
        clearScreen();
        return  "\n==== KARTU ANGGOTA PERPUSTAKAAN ABCD ====\n" +
                "Nama : " + this.nama+
                "\nNIM   : " + this.nim +
                "\nSebagai : " + this.role +
                "\n=========================================" +"\n";
    }
}