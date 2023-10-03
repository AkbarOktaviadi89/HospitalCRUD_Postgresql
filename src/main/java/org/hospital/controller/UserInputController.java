package org.hospital.controller;

import org.hospital.model.Dokter;
import org.hospital.model.Obat;
import org.hospital.model.Pasien;
import org.hospital.view.MenuView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserInputController {
    static Scanner scanner = new Scanner(System.in);
    static List<Dokter> listDokter = new ArrayList<>();

    public static void inputMainMenu(){
        MenuView.menuUtama();
        Integer input = Optional.of(scanner.nextInt()).orElse(0);
        scanner.nextLine();
        switch (input){
            case 1:
                PasienController.inputMenuPasien();
                break;
            case 2:
                MenuView.menuDokter(listDokter);
        }
    }
}
