package ru.kpfu.itis.transportprojectimpl.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.SearchForm;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;
import ru.kpfu.itis.transportprojectimpl.repository.FlightRepository;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FlightServiceImpl implements FlightService<FlightDto, Long> {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FlightDto save(FlightDto flightDto) {
        Date dateDep = null;
        Date dateArr = null;
        LocalTime timeDep = null;
        LocalTime timeArr = null;
        try {
            dateDep = new SimpleDateFormat("yyyy-MM-dd").parse(flightDto.getDateDep());
            dateArr = new SimpleDateFormat("yyyy-MM-dd").parse(flightDto.getDateArr());
            timeDep = LocalTime.parse(flightDto.getTimeDep());
            timeArr = LocalTime.parse(flightDto.getTimeArr());

            FlightEntity flightEntity = new FlightEntity();
            modelMapper.map(flightDto, flightEntity);
            flightEntity.setId(null);
            flightEntity.setDateArr(dateArr);
            flightEntity.setDateDep(dateDep);
            flightEntity.setTimeDep(timeDep);
            flightEntity.setTimeArr(timeArr);
            FlightEntity flightEntity1 = flightRepository.save(flightEntity);
            modelMapper.map(flightEntity1, flightDto);
            return flightDto;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightDto> search(SearchForm searchForm) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(searchForm.getDate());
            List<FlightEntity> flightEntities = flightRepository.findAllByCityFromAndCityToAndDateDepAndCountOfPlacesGreaterThanEqual(searchForm.getCityFrom(), searchForm.getCityTo(), date, searchForm.getCountOfPerson());
            List<FlightDto> flightDtoList = new ArrayList<>();
            System.out.println(flightEntities);
            for (FlightEntity flight : flightEntities) {
                FlightDto flightDto = new FlightDto();
                modelMapper.map(flight, flightDto);
                flightDtoList.add(flightDto);
            }

            return flightDtoList;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
