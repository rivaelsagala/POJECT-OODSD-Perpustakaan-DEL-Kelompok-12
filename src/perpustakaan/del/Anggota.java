/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perpustakaan.del;

/**
 *
 * @author Kelompok 12
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Anggota {
    ArrayList<Buku> buku   = new ArrayList<Buku>();
    ArrayList<Buku> bukuPinjam = new ArrayList<Buku>();

    public int nim;
    public  String nama;
    private String username;
    private String password;
    Role role = Role.anggota;
    Buku bukuperpustakaan;
    History history;
    Feedback feedback;

    KartuAnggota kartuAnggota;

    public Anggota (Buku bukuperpustakaan) throws IOException{
        this.bukuperpustakaan = bukuperpustakaan;
        generate(bukuperpustakaan);
    }
    public Anggota(int nim, String nama){
        this.nim = nim;
        this.nama = nama;
        this.role = Role.anggota;
    }
    //setters
    public void setKartuAnggota(KartuAnggota kartuAnggota) {
        this.kartuAnggota = kartuAnggota;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public void setNama() {
        System.out.print("Masukkan Nama : ");
        Scanner terminalInput = new Scanner(System.in);
        this.nama = terminalInput.nextLine();
    }

    public void setnim() {
        System.out.print("Masukkan Nim : ");
        Scanner terminalInput = new Scanner(System.in);
        this.nim = terminalInput.nextInt();
    }
    public void setKartu(KartuAnggota kartuAnggota){
        this.kartuAnggota = kartuAnggota;
    }

    public void setRole() {
        this.role = Role.anggota;
    }

    public void setUsername() {
        System.out.print("Masukkan Username Akun: ");
        Scanner terminalInput = new Scanner(System.in);
        this.username = terminalInput.nextLine();
    }

    public void setPassword() {
        System.out.print("Masukkan Password Akun: ");
        Scanner terminalInput = new Scanner(System.in);
        this.password = terminalInput.nextLine();
    }
    
    
    //getter
    public void getFeedback() {
        feedback.display();
    }

    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }

    public int getnim() {
        return nim;
    }

    public Role getRole() {
        return Role.anggota;
    }

    public int getBukuperpustakaan() {
        return bukuperpustakaan.getNoBukuPerpustakaan();
    }
    
    
     // Metode untuk menyimpan data anggota ke dalam file
    public void simpanKeFileAnggota(String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile, true))) {
            // Format data anggota sesuai dengan yang diminta
            String dataAnggota = String.format("%d|%s|%s|%s|%s|%s|%s", nim, nama, username, password, role, kartuAnggota, feedback);
            
            // Tulis data ke dalam file
            writer.write(dataAnggota);
            writer.newLine();  // Tambahkan baris baru setelah setiap data anggota
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data anggota ke dalam file: " + e.getMessage());
        }
    }
    
    
    public void register(){
        System.out.println("\n\n==== FORM REGISTRASI ====");
        setnim();
        setNama();
        setUsername();
        setPassword();
        
        simpanKeFileAnggota("akun.txt");
        
        this.kartuAnggota.nim = getnim();
        this.kartuAnggota.nama =getNama();
        this.kartuAnggota.role = getRole();
        System.out.println("==== AKUN BERHASIL DIBUAT ====");
    }

     public void login(Pengurus admin){
         System.out.println("\n\n==== USER LOGIN ====");
         String uname;
         String pword;
         System.out.print("Masukkan Username : ");
         Scanner terminalInput = new Scanner(System.in);
         uname = terminalInput.nextLine();
         System.out.print("Masukkan Password : ");
         pword = terminalInput.nextLine();

         if (this.username.equals(uname) && this.password.equals(pword)){
             this.mainMenuMember(admin);
         }else {
             clearScreen();
             System.out.println("Autentikasi Gagal, Silahkan Periksa Kembali Username dan Password Anda");
             this.login(admin);
         }
    }
     
     
    
    // Fungsi untuk mendapatkan daftar buku dari kelas Anggota
    public ArrayList<Buku> getBukuList() {
        return this.buku;
    }

    private void loadBooks() {
        try {
            Path filePath = Paths.get("buku.txt");
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    Buku buku = new Buku(parts[0], parts[1], parts[2], parts[3]);
                    this.buku.add(buku);
                }
            }
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat memuat data buku: " + e.getMessage());
        }
    }

    private void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("buku.txt"))) {
            for (Buku buku : this.buku) {
                String line = String.format("%s|%s|%s|%s", buku.getNomorBuku(), buku.getPenerbit(), buku.getJudul(), buku.getKategori());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menyimpan data buku: " + e.getMessage());
        }
    }
     
    
        
    public void tampilkanPengumuman() {
        try (BufferedReader reader = new BufferedReader(new FileReader("pengumuman.txt"))) {
            clearScreen();
            System.out.println("\n===== PENGUMUMAN =====");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("=======================");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file pengumuman: " + e.getMessage());
        }
    }
    
    public void mainMenuMember(Pengurus admin){
        System.out.println("\n===== Login Berhasil =====");
        Scanner terminalInput = new Scanner(System.in);
        boolean isLanjutkan = true;
        do {
            clearScreen();
            try {
                tampilkanPengumuman();
            }catch (Exception e){
                System.out.println("\n\nBelum ada Pengumuman dari Petugas Perpustakaan");
            }
            System.out.println("\n\n=============================");
            System.out.println("====== Halaman Mahasiswa =====\n");
            System.out.println("1. Pinjam Buku");
            System.out.println("2. Sumbang Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Berikan Feedback");
            System.out.println("5. Tampilkan Buku yang Dipinjam");
            System.out.println("6. History Peminjaman Buku");
            System.out.println("7. Cetak Kartu Anggota");
            System.out.println("8. LogOut");


            System.out.print("\n\nPilihan anda: ");
            int pilihanUser = terminalInput.nextInt();
            switch (pilihanUser){
                case 1:
                    System.out.println("\n=================");
                    System.out.println(" PEMINJAMAN BUKU  ");
                    System.out.println("=================");
                    pinjamBuku();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("\n=================");
                    System.out.println(" PENYUMBANGAN BUKU ");
                    System.out.println("=================");
                    sumbangBuku(bukuperpustakaan);
                    break;
                case 3:
                    clearScreen();
                    System.out.println("\n=================");
                    System.out.println("  PENCARIAN  BUKU  ");
                    System.out.println("=================");
                    cariBuku();
                    break;
                case 4:
                    clearScreen();
                    System.out.println("\n=================");
                    System.out.println("  INPUT  FEEDBACK  ");
                    System.out.println("=================");
                    try {
                    Feedback feedback = new Feedback();
                    setFeedback(feedback);
                    System.out.println("Silahkan Masukkan Feedback Anda : ");
                    this.feedback.setFeedback();
                    System.out.println("");
                    System.out.println("Terima Kasih Atas Feedback Anda :)");
                    }catch (Exception e) {
                        System.err.println("Gagal Membuat Feedback!");
                    }
                    break;
                case 5:
                    clearScreen();
                    System.out.println("\n=================");
                    System.out.println("  BUKU DIPINJAM  ");
                    System.out.println("=================");
                    menuDipinjam();
                    break;
                case 6:
                    System.out.println("\n===================");
                    System.out.println("RIWAYAT PINJAM BUKU");
                    System.out.println("===================");
                    history.tampilkanRiwayat();
                    clearScreen();
                    break;
                case 7:
                    System.out.println("\n===================");
                    System.out.println("CETAK KARTU ANGGOTA");
                    System.out.println("===================");
                    System.out.println(this.kartuAnggota.cetakKartuAnggota());
                    break;
                case 8:
                    clearScreen();
                    isLanjutkan = false; 
                    break;
                default:
                    clearScreen();
                    System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-7]");
            }

        }while(isLanjutkan == true);
    }
    
    private static void clearScreen(){
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (Exception ex){
            System.err.println("tidak bisa clear screen");
        }
    }
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
    
    
     // Metode untuk menyimpan data buku ke dalam file
    public void simpanBukuKeFile(String namaFile, Buku buku) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile, true))) {
            // Format data buku sesuai dengan yang diminta
            String dataBuku = String.format("%s|%s|%s|%s", buku.getKategori(),
                    buku.getJudul(), buku.getPenulis(), buku.getPenerbit());

            // Tulis data ke dalam file
            writer.write(dataBuku);
            writer.newLine();  // Tambahkan baris baru setelah setiap data buku
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data buku ke dalam file: " + e.getMessage());
        }
    }
    
    
   public void sumbangBuku(Buku bukuperpustakaan) {
    Scanner sc = new Scanner(System.in);
    boolean isLanjutkan = true;
    do {
        Buku buku1 = new Buku();
        System.out.print("Masukkan Kategori Buku : ");
        String kategori = sc.nextLine();
        System.out.print("Masukkan Judul Buku : ");
        String judul = sc.nextLine();
        System.out.print("Masukkan Penulis Buku : ");
        String penulis = sc.nextLine();
        System.out.print("Masukkan Penerbit Buku : ");
        String penerbit = sc.nextLine();

        buku1.setNomorBuku(++bukuperpustakaan.noBukuPerpustakaan);
        buku1.setKategori(kategori);
        buku1.setJudul(judul);
        buku1.setPenulis(penulis);
        buku1.setPenerbit(penerbit);

        buku.add(buku1);
        
        simpanBukuKeFile("buku.txt", buku1);  // Menyimpan data buku ke dalam file

        System.out.println("===========================");
        System.out.println("Berhasil Menyumbang Buku " + buku1.getJudul());
        System.out.println("Terimakasih " + getNama());
        System.out.println("===========================");

        isLanjutkan = getYesorNo("Apakah Anda ingin Menyumbang Buku Lagi");
    } while (isLanjutkan);
}

    public void menuBuku(){
            System.out.println("==== MENU BUKU ====");
            for (Buku buku : buku) {
                System.out.println("Nomor Buku : "+ buku.nomorBuku);
                System.out.println("Kategori : " + buku.kategori);
                System.out.println("Judul : " + buku.judul);
                System.out.println("Penulis : " + buku.penulis);
                System.out.println("Penerbit : " + buku.penerbit);
                System.out.println("===================");
            }    
        }
        public void generate(Buku bukuperpustakaan) throws IOException {
            FileReader fileInput;
            BufferedReader bufferInput;

            try {
                fileInput = new FileReader("buku.txt");
                bufferInput = new BufferedReader(fileInput);
            } catch (Exception e){
                System.err.println("Menu Buku Tidak ditemukan");
                return;
            }
            String data = bufferInput.readLine();
            bukuperpustakaan.noBukuPerpustakaan = 0 ;
            while(data != null){
                StringTokenizer token = new StringTokenizer(data,"|");
                Buku buku1 =  new Buku();
                ++bukuperpustakaan.noBukuPerpustakaan;
                buku1.setNomorBuku(getBukuperpustakaan());
                buku1.setPenulis(token.nextToken());
                buku1.setPenerbit(token.nextToken());
                buku1.setJudul(token.nextToken());
                buku1.setKategori(token.nextToken());
                buku.add(buku1);
                data = bufferInput.readLine();
            }
        }

    public void pinjamBuku(){
        clearScreen();
        boolean isLanjutkan = true;
        Scanner input = new Scanner(System.in);
        try {
            do {
                menuBuku();
                System.out.print("Masukkan Nomor Buku Yang Akan Dipinjam : ");
                int pilih = input.nextInt();
                for (Buku buku : buku) {
                    if(buku.nomorBuku == pilih){
                        buku.setTanggalPinjam();
                        buku.setTanggalKembali();
                        bukuPinjam.add(buku);
                        System.out.println("Buku Dengan Judul "+buku.judul+ " Berhasil dipinjam");
                        break;
                    }else {
                        continue;
                    }
                }
                isLanjutkan = getYesorNo("Apakah Anda Menambah Buku Yang Ingin Dipinjam ?");
            }while (isLanjutkan == true);
        }catch (Exception e){
            System.err.println("Gagal Meminjam Buku");
        }
    }

    public void menuDipinjam(){
        clearScreen();
        System.out.println("==== BUKU DIPINJAM ====");
        for (Buku bukuPinjam : bukuPinjam){
            System.out.println("No. Buku : "+ bukuPinjam.nomorBuku);
            System.out.println("Kategori : " + bukuPinjam.kategori);
            System.out.println("Judul : " + bukuPinjam.judul);
            System.out.println("Penulis : " + bukuPinjam.penulis);
            System.out.println("Penerbit : " + bukuPinjam.penerbit);
            System.out.println("===================");
        }
    }

    public void cariBuku(){
        clearScreen();
        
        boolean isLanjutkan = true;
        do {
            System.out.println("=== KATEGORI ===");
            System.out.println("1. Programming");
            System.out.println("2. Novel");
            System.out.println("3. Matematika");
            System.out.println("4. Wiraswasta");
            System.out.println("5. Kategori Lainnya");
            Scanner sc = new Scanner(System.in);
            System.out.print("Pilih Kategori :");
            int input = sc.nextInt();
            switch (input){
                case 1:
                    clearScreen();
                    System.out.println("\nKategori Programming : ");
                    for (Buku buku: buku) {
                        if(buku.getKategori().equalsIgnoreCase("Programming")) {
                            System.out.println("No. Buku : " + buku.getNomorBuku());
                            System.out.println("Judul : " + buku.getJudul());
                            System.out.println("=======================\n");
                        }
                    }
                    break;
                case 2:
                    clearScreen();
                    System.out.println("\nKategori Novel : ");
                    for (Buku buku: buku) {
                        if(buku.getKategori().equalsIgnoreCase("Novel")) {
                            System.out.println("No. Buku : " + buku.getNomorBuku());
                            System.out.println("Judul : " + buku.getJudul());
                            System.out.println("=======================\n");
                        }
                    }
                    break;
                    case 3:
                        clearScreen();
                        System.out.println("\nKategori Matematika : ");
                    for (Buku buku: buku) {
                        if(buku.getKategori().equalsIgnoreCase("Matematika")) {
                            System.out.println("No. Buku : " + buku.getNomorBuku());
                            System.out.println("Judul : " + buku.getJudul());
                            System.out.println("=======================\n");
                        }
                    }
                    break;
                    case 4:
                        clearScreen();
                        System.out.println("\nKategori Wiraswasta : ");
                    for (Buku buku: buku) {
                        if(buku.getKategori().equalsIgnoreCase("Wiraswasta")) {
                            System.out.println("No. Buku : " + buku.getNomorBuku());
                            System.out.println("Judul : " + buku.getJudul());
                            System.out.println("=======================\n");
                        }
                    }
                    break;
                    case 5:
                    clearScreen();
                    System.out.println("\nKategori Lainnya : ");
                    for (Buku buku : buku) {
                        String kategori = buku.getKategori();
                        if (!kategori.equalsIgnoreCase("Programming") &&
                            !kategori.equalsIgnoreCase("Novel") &&
                            !kategori.equalsIgnoreCase("Matematika") &&
                            !kategori.equalsIgnoreCase("Wiraswasta")) {

                            System.out.println("No. Buku : " + buku.getNomorBuku());
                            System.out.println("Judul : " + buku.getJudul());
                            System.out.println("=======================\n");
                        }
                    }
                    break;
                default:
                    System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-5]");
            }
            System.out.println("===== PILIHAN =====");
            System.out.println("1. Lanjut Melakukan Cari Buku ");
            System.out.println("2. Kembali ke menu sebelumnya ");

            int input2 = sc.nextInt();
            switch (input2){
                case 2:
                    isLanjutkan = false;
                    break;
            }
        }while (isLanjutkan == true);
    }

    

    public void update() {
    clearScreen();
    boolean isLanjutkan = true;
    Scanner inputuser = new Scanner(System.in);
    try {
        do {
            menuBuku();
            System.out.print("Masukkan Nomor Buku Yang Akan Diupdate : ");
            int pilih = inputuser.nextInt();

            Buku buku1 = new Buku();
            buku1.setNomorBuku(pilih);
            inputuser.nextLine(); // Konsumsi newline agar tidak terjadi skip pada input selanjutnya
            System.out.println("Masukkan Judul Buku :");
            buku1.setJudul(inputuser.nextLine());
            System.out.println("Masukkan Penulis Buku :");
            buku1.setPenulis(inputuser.nextLine());
            System.out.println("Masukkan Penerbit Buku :");
            buku1.setPenerbit(inputuser.nextLine());
            System.out.println("Masukkan Kategori Buku :");
            buku1.setKategori(inputuser.nextLine());
            buku.set(pilih - 1, buku1);

            // Menulis ulang seluruh data buku ke dalam file setelah perubahan
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("buku.txt", false))) {
                for (Buku b : buku) {
                    String dataBuku = String.format("%s|%s|%s|%s", b.getPenulis(), b.getPenerbit(), b.getJudul(), b.getKategori());
                    writer.write(dataBuku);
                    writer.newLine();
                }
                System.out.println("Data buku berhasil di Update");
            } catch (IOException e) {
                System.out.println("Terjadi kesalahan saat menyimpan data ke dalam file: " + e.getMessage());
            }

            isLanjutkan = getYesorNo("Apakah Anda Mengedit Buku Yang Lain ?");
        } while (isLanjutkan);
    } catch (Exception e) {
        System.err.println("Gagal Mengedit Buku");
    }
}



public void delete() {
    clearScreen();
    boolean isLanjutkan = true;
    Scanner input = new Scanner(System.in);

    try {
        do {
            menuBuku();
            System.out.print("Masukkan Nomor Buku Yang Akan Dihapus : ");
            int pilih = input.nextInt();

            if (pilih >= 1 && pilih <= buku.size()) {
                Buku bukuDihapus = buku.remove(pilih - 1);
                System.out.println("Berhasil Menghapus Buku");
                hapusDataBukuDariFile(bukuDihapus);
            }
             else {
                System.err.println("Nomor Buku tidak valid.");
            }

            isLanjutkan = getYesorNo("Apakah Anda Menghapus Buku Yang Lain ?");
        } while (isLanjutkan);
    } catch (Exception e) {
        System.err.println("Gagal Menghapus Buku");
    }
}

    private void hapusDataBukuDariFile(Buku bukuDihapus) {
        try {
            Path filePath = Paths.get("buku.txt");
            List<String> lines = Files.readAllLines(filePath);

            // Buat data buku yang akan dihapus
            String dataBukuDihapus = String.format("%s|%s|%s|%s",
                    bukuDihapus.getPenulis(), bukuDihapus.getPenerbit(), bukuDihapus.getJudul(), bukuDihapus.getKategori());

            // Hapus data buku dari list
            lines.remove(dataBukuDihapus);

            // Tulis kembali data ke dalam file
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menghapus data buku dari file: " + e.getMessage());
        }
    }
    
    
    public void buatPengumuman() {
        clearScreen();
        Scanner sc = new Scanner(System.in);

        System.out.println("==== BUAT PENGUMUMAN ====");
        System.out.print("Masukkan Pengumuman Anda: ");
        String pesanPengumuman = sc.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pengumuman.txt", true))) {
            writer.write(pesanPengumuman);
            writer.newLine();
            System.out.println("\nPengumuman Telah Berhasil Dibuat dan Disimpan.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data pengumuman ke dalam file: " + e.getMessage());
        }
    }


    public void add(Buku bukuperpustakaan){
        clearScreen();
        Scanner sc = new Scanner(System.in);
        boolean isLanjutkan = true;
        do {
            Buku buku1 = new Buku();
            System.out.print("Masukkan Kategori Buku : ");
            String kategori = sc.nextLine();
            System.out.print("Masukkan Judul Buku : ");
            String judul = sc.nextLine();
            System.out.print("Masukkan Penulis Buku : ");
            String penulis = sc.nextLine();
            System.out.print("Masukkan Penerbit Buku : ");
            String penerbit = sc.nextLine();

            buku1.setNomorBuku(++bukuperpustakaan.noBukuPerpustakaan);
            buku1.setKategori(kategori);
            buku1.setJudul(judul);
            buku1.setPenulis(penulis);
            buku1.setPenerbit(penerbit);

            buku.add(buku1);
            
             simpanBukuKeFile("buku.txt", buku1);  // Menyimpan data buku ke dalam file
             
            System.out.println("===========================");
            System.out.println("Berhasil Menambah Buku " + buku1.getJudul());
            System.out.println("===========================");

            isLanjutkan = getYesorNo("Apakah Anda ingin Menambah Buku Lagi");
        }while(isLanjutkan == true);
    }
}