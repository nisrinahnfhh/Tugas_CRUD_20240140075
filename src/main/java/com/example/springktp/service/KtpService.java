package com.example.springktp.service;
import com.example.springktp.dto.KtpDto;
import com.example.springktp.entity.Ktp;
import java.util.List;

public interface KtpService {
    Ktp create(KtpDto request);
    List<Ktp> getAll();
    Ktp getById(Integer id);
    Ktp update(Integer id, KtpDto request);
    void delete(Integer id);
}