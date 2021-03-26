package ru.kpfu.itis.transportprojectimpl.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.service.FlightService;
import ru.kpfu.itis.transportprojectimpl.entity.FlightEntity;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;
import ru.kpfu.itis.transportprojectimpl.repository.FlightRepository;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class FlightServiceImpl implements FlightService<FlightDto, Long> {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(FlightDto flightDto) {
        Date dateDep = null;
        Date dateArr = null;
        LocalTime timeDep = null;
        LocalTime timeArr = null;
        try {
//            String timeDepPr = flightDto.getTimeDep() + ":00";
//            String timeArrPr = flightDto.getTimeArr() + ":00";
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
            flightRepository.save(flightEntity);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }
}
