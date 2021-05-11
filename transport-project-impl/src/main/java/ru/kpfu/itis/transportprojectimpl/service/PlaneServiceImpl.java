package ru.kpfu.itis.transportprojectimpl.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.PlaneDto;
import ru.kpfu.itis.transportprojectapi.service.PlaneService;
import ru.kpfu.itis.transportprojectimpl.aspect.Cacheable;
import ru.kpfu.itis.transportprojectimpl.entity.PlaneEntity;
import ru.kpfu.itis.transportprojectimpl.repository.PlaneRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService<PlaneDto, Long> {
    @Autowired
    private PlaneRepository planeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable
    public List<PlaneDto> findAll() {
        List<PlaneEntity> entities = planeRepository.findAll();
        List<PlaneDto> planeDtos = new ArrayList<>();
        for (PlaneEntity en : entities) {
            planeDtos.add(modelMapper.map(en, PlaneDto.class));
        }
        return planeDtos;
    }

    @Override
    public PlaneDto findByName(String name) {
        return modelMapper.map(planeRepository.findByName(name), PlaneDto.class);
    }

    @Override
    public PlaneDto findById(Long id) {
        return modelMapper.map(planeRepository.findById(id).get(), PlaneDto.class);
    }
}
