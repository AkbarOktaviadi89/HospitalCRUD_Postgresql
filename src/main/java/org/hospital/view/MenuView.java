package org.hospital.view;

import javafx.scene.control.Separator;
import org.hospital.controller.UserInputController;
import org.hospital.model.Dokter;
import org.hospital.model.Obat;
import org.hospital.model.Pasien;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class MenuView {
    public static final String SEPARATOR = "=================================================================================";

    public static void menuUtama() {
        String SEPARATOR = "========================================";
        String title = "SELAMAT DATANG DI RUMAH SAKIT CERIA";

        System.out.println(SEPARATOR);
        System.out.println(centerText(title, SEPARATOR.length()));
        System.out.println(SEPARATOR);

        System.out.println("1. Lihat Data Pasien");
        System.out.println("2. Lihat Data Dokter");
        System.out.println("3. Lihat Data Obat");
        System.out.println("4. Keluar");
        System.out.print("=> ");
    }

    public static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            centeredText.append(" ");
        }
        centeredText.append(text);
        for (int i = 0; i < padding; i++) {
            centeredText.append(" ");
        }
        if (centeredText.length() < width) {
            centeredText.append(" ");
        }
        return centeredText.toString();
    }

    public static void menuPasien(List<Pasien> listPasien) {
        System.out.println("=================================================================================" +
                                        "\n\t\t\t\t\t\t\tMENU PASIEN\n"+
                            "=================================================================================\n");
        System.out.println(" ----------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-20s | %-15s | %-12s | %s |%n"," No ", "     Nama Pasien     ", "     Alamat     ", " Jenis Kelamin ", " Umur ");
        System.out.println(" ----------------------------------------------------------------------------");
        listPasien.forEach(val -> {
            System.out.printf("| %-4s | %-21s | %-16s | %-15s | %d     |%n", (listPasien.indexOf(val) + 1) ,val.getNamaPasien(), val.getAlamat(), val.getJenisKelamin(), val.getUmur());
        });
        System.out.println("\n1. Tambah Data Pasien");
        System.out.println("2. Ubah Data Pasien");
        System.out.println("3. Hapus Data Pasien");
        System.out.println("4. Kembali");
        System.out.print("=> ");
    }


    public static void menuDokter(List<Dokter> listDokter) {
        System.out.println("==================================" + "\n\t\tMENU DOKTER" + "==================================" + "\n");
        System.out.println("Nama Dokter" + " | " + "Spesialis");
        listDokter.forEach(val -> {
            System.out.println(val.getNamaDokter() + val.getSpesialis());
        });
        System.out.println("\n1. Tambah Data Dokter");
        System.out.println("2. Ubah Data Dokter");
        System.out.println("3. Hapus Data Dokter");
        System.out.println("4. Kembali");
        System.out.print("=> ");
    }

    public static void menuObat(List<Obat> listObat) {
        System.out.println("==================================" + "\n\t\tMENU OBAT" + "==================================" + "\n");
        System.out.println("Nama Obat" + " | " + "Harga");
        listObat.forEach(val -> {
            System.out.println(val.getNamaObat() + val.getHarga());
        });
        System.out.println("\n1. Tambah Data Obat");
        System.out.println("2. Ubah Data Obat");
        System.out.println("3. Hapus Data Obat");
        System.out.println("4. Kembali");
        System.out.print("=> ");
    }

    public static void showError(){
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("WARNING INPUT TIDAK VALID");
        System.out.println("(Y) Untuk Lanjut\n(N) Untuk Kembali");
        System.out.print("=> ");
        String inputError = Optional.ofNullable(scanner.nextLine()).orElse("N");
        Optional.of(inputError)
                .map(String::toUpperCase)
                .ifPresent(val -> {
                    if(val.equals("Y")){
                        UserInputController.inputMainMenu();
                    }else{
                        System.exit(0);
                    }
                });
    }
}
