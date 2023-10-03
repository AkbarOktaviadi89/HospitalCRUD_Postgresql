package org.hospital.controller;

import org.hospital.Dao.DBConnection;
import org.hospital.Dao.DaoPasien;
import org.hospital.model.Pasien;
import org.hospital.view.MenuView;

import java.sql.Connection;
import java.util.*;

public class PasienController {
    static DBConnection db = new DBConnection();
    static DaoPasien dao = new DaoPasien();
    static Connection conn = db.connect();
    static Scanner scanner = new Scanner(System.in);

    public static void inputMenuPasien() {
        try {
            dao.readData(conn, "pasien");
            Integer input = Optional.of(scanner.nextInt()).orElse(0);
            switch (input) {
                case 1:
                    tambahDataPasien();
                    break;
                case 2:
                    ubahDataPasien();
                    break;
                case 3:
                    hapusDataPasien();
                    break;
                default:
                    UserInputController.inputMainMenu();
            }
        }
        catch (Exception e){
            MenuView.showError();
        }
    }

    public static void tambahDataPasien() {
        try {
            scanner.nextLine();
            System.out.print("Masukkan nama pasien \t=> ");
            String namaPasien = scanner.nextLine();

            System.out.print("Masukkan alamat pasien \t=> ");
            String alamat = scanner.nextLine();

            System.out.print("Masukkan jenis kelamin \t=> ");
            String jenisKelamin = scanner.nextLine();

            System.out.print("Masukkan umur pasien \t=> ");
            Integer umur = scanner.nextInt();
            scanner.nextLine();

            dao.insertData(conn, "pasien", namaPasien, alamat, jenisKelamin, umur);
            inputMenuPasien();
            scanner.close();
        }catch (Exception e){
            MenuView.showError();
        }
    }

    public static void ubahDataPasien() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nomor urut pasien yang ingin diubah => ");
        try {
            int nomorUrut = scanner.nextInt();
            scanner.nextLine();
            if (nomorUrut > 0) {

                System.out.print("Nama Pasien \t: ");
                String namaPasien = scanner.nextLine();

                System.out.print("Alamat \t\t\t: ");
                String alamat = scanner.nextLine();

                System.out.print("Jenis Kelamin \t: ");
                String jenisKelamin = scanner.nextLine();

                System.out.print("Umur \t\t\t: ");
                int umur = scanner.nextInt();
                scanner.nextLine();

                dao.updateData(conn, "pasien", namaPasien, alamat, jenisKelamin, umur, nomorUrut);
            } else {
                System.out.println("Nomor urut tidak valid");
            }
        }catch (Exception e){
           MenuView.showError();
        }
        inputMenuPasien();
        scanner.close();
    }

    public static void hapusDataPasien(){
        System.out.print("Masukkan nomor urut pasien yang ingin dihapus => ");
        try {
            int inputHapus = scanner.nextInt();
            scanner.nextLine();
            dao.deleteData(conn, "pasien", inputHapus);
            inputMenuPasien();
        }catch (Exception e){
            MenuView.showError();
        }
    }
}
