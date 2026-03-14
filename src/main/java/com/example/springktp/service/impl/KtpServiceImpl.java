package com.example.springktp.service.impl;

import com.example.springktp.dto.KtpDto;
import com.example.springktp.entity.Ktp;
import com.example.springktp.mapper.KtpMapper;
import com.example.springktp.repository.KtpRepository;
import com.example.springktp.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springktp.util.ValidationUtil;
import java.util.List;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository repository;
    @Autowired
    private KtpMapper mapper;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public Ktp create(KtpDto request) {
        validationUtil.validate(request);

        if (repository.existsByNomorKtp(request.getNomorKtp())) {
            throw new RuntimeException("Nomor KTP sudah terdaftar!");
        }
        Ktp ktp = mapper.toEntity(request);
        return repository.save(ktp);
    }

    @Override
    public List<Ktp> getAll() {
        return repository.findAll();
    }

    @Override
    public Ktp getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan!"));
    }

    @Override
    public Ktp update(Integer id, KtpDto request) {
        validationUtil.validate(request);
        Ktp ktp = getById(id);

        if(!ktp.getNomorKtp().equals(request.getNomorKtp()) && repository.existsByNomorKtp(request.getNomorKtp())){
            throw new RuntimeException("Nomor KTP sudah terdaftar!");
        }

        ktp.setNomorKtp(request.getNomorKtp());
        ktp.setNamaLengkap(request.getNamaLengkap());
        ktp.setAlamat(request.getAlamat());
        ktp.setTanggalLahir(request.getTanggalLahir());
        ktp.setJenisKelamin(request.getJenisKelamin());
        return repository.save(ktp);
    }

    @Override
    public void delete(Integer id) {
        Ktp ktp = getById(id);
        repository.delete(ktp);
    }
}