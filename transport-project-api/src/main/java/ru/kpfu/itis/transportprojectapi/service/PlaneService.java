package ru.kpfu.itis.transportprojectapi.service;

import ru.kpfu.itis.transportprojectapi.dto.PlaneDto;

import java.util.List;

public interface PlaneService<PlaneDto, Long> {
    List<ru.kpfu.itis.transportprojectapi.dto.PlaneDto> findAll();

    ru.kpfu.itis.transportprojectapi.dto.PlaneDto findByName(String name);

    ru.kpfu.itis.transportprojectapi.dto.PlaneDto findById(java.lang.Long id);
}
