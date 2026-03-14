package com.example.springktp.mapper;
import com.example.springktp.dto.KtpDto;
import com.example.springktp.entity.Ktp;
import org.springframework.stereotype.Component;

@Component
public class KtpMapper {
    public Ktp toEntity(KtpDto dto) {
        Ktp ktp = new Ktp();
        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());
        return ktp;
    }
}