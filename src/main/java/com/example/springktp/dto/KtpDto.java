//package com.example.springktp.dto;
//import lombok.Data;
//import java.time.LocalDate;
//
//@Data
//public class KtpDto {
//    private String nomorKtp;
//    private String namaLengkap;
//    private String alamat;
//    private LocalDate tanggalLahir;
//    private String jenisKelamin;
//}

package com.example.springktp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class KtpDto {

    @NotBlank(message = "Nomor KTP tidak boleh kosong")
    @Size(min = 16, max = 16, message = "Nomor KTP harus 16 digit")
    private String nomorKtp;

    @NotBlank(message = "Nama Lengkap tidak boleh kosong")
    private String namaLengkap;

    @NotBlank(message = "Alamat tidak boleh kosong")
    private String alamat;

    @NotNull(message = "Tanggal Lahir tidak boleh kosong")
    private LocalDate tanggalLahir;

    @NotBlank(message = "Jenis Kelamin tidak boleh kosong")
    private String jenisKelamin;
}