package org.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pasien {
    private String namaPasien;
    private String alamat;
    private String jenisKelamin;
    private Integer umur;
}
