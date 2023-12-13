/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan.del;

/**
 *
 * @author RIVAEL S S
 */
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PerpustakaanDriver{
    private static boolean getYesorNo(String message){
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\n"+message+" (y/n)? ");
        String pilihanUser = terminalInput.next();

        while(!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.err.println("Pilihan anda bukan y atau n");
            System.out.print("\n"+message+" (y/n)? ");
            pilihanUser = terminalInput.next();
        }

        return pilihanUser.equalsIgnoreCase("y");

    }

    private static void clearScreen(){
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (Exception ex){
            System.err.println("tidak bisa clear screen");
        }
    }

    public static void main(String[] args) throws IOException {
        boolean isLanjut  = true;
        Buku bukubfr  =new Buku();
        Anggota Anggota = new Anggota(bukubfr);
        KartuAnggota kartuAnggota = new KartuAnggota(Anggota.getnim(), Anggota.getNama());
        Anggota.setKartu(kartuAnggota);
        Pengurus Admin = new Pengurus(11422005, "Rivael Sagala");
        Pengumuman pengumuman = new Pengumuman();
            do {
                try {
                clearScreen();
                System.out.println("===============================================");
                System.out.println("===== SISTEM INFORMASI PERPUSTAKAAN DEL =====");
                System.out.println("==================  SELAMAT DATANG  ================");
                Scanner starting = new Scanner(System.in);
                System.out.println("1. Login Sebagai Mahasiswa.");
                System.out.println("2. Register");
                System.out.println("3. Login sebagai Admin.");
                System.out.print("Masukkan Pilihan Anda :");

                int start = starting.nextInt();
                switch (start) {
                    case 1:
                        clearScreen();
                        if(Anggota.getUsername() == null) {
                            System.out.println("Anda belum terdaftar, Silahkan mendaftar dahulu ...");
                            Anggota.register();
                        }
                        clearScreen();
                        Anggota.login(Admin);
                        break;
                    case 2: 
                        clearScreen();
                        if(Anggota.getUsername() != null){
                            System.out.println("Akun anda sudah terdaftar !");
                            if(getYesorNo("Apakah Anda Ingin Lanjut Login ") == true){
                                clearScreen();
                                Anggota.login(Admin);
                            }
                        }
                        Anggota.register();
                        if(getYesorNo("Apakah Anda Ingin Lanjut Login ") == true){
                            clearScreen();
                            Anggota.login(Admin);
                        }
                        break;
                    case 3:
                        clearScreen();
                        Admin.login(Anggota);
                        break;
                    default:
                        System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-3]");
                    }
                isLanjut = getYesorNo("Apakah Anda ingin LOGOUT (y => LOGOUT), ( x=> ExitProgram)");
                }catch (InputMismatchException e){
                    System.err.println("Terjadi error : Input Anda Seharusnya Dalam Bentuk Angka. Anda Kembali Ke Halaman Beranda.");
                    clearScreen();
                    isLanjut = getYesorNo("Apakah Anda Ingin Melanjutkan (y => Melanjutkan Program), ( x=> ExitProgram)");
                }
            }while(isLanjut == true);

    }
}
