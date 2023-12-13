/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan.del;

/**
 *
 * @author Kelompok 12
 */
public enum Role {
    pengurus, anggota;

    @Override
    public String toString() {
        switch (this){
            case pengurus :
                return "Pengurus";
            default:
                return "Anggota";
            }
        }
}