package ru.kpfu.itis.transportprojectimpl.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.util.*;
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
            if (flightDto.getTimeArr() == null) {
                System.out.println(flightDto.getDistance());
                double duration = (double) flightDto.getDistance() / 840 * 60 * 60 * 1000;
                long second = (long) ((duration / 1000) % 60);
                long minute = (long) ((duration / (1000 * 60)) % 60);
                long hour = (long) ((duration / (1000 * 60 * 60)) % 24);

                String time = String.format("%02d:%02d:%02d", hour, minute, second);
                dateDep = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightDto.getDateDep() + " " + flightDto.getTimeDep());
                DateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date date = format.parse(time);
                long time1 = dateDep.getTime();
                long time2 = (long) duration;
                time2 = time1 + time2;
                dateArr = dateDep;
                dateArr.setTime(time2);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
                time = df2.format(dateArr);
                dateArr = new SimpleDateFormat("yyyy-MM-dd").parse(df.format(dateArr));
                timeArr = LocalTime.parse(time);
                dateDep = new SimpleDateFormat("yyyy-MM-dd").parse(flightDto.getDateDep());
                timeDep = LocalTime.parse(flightDto.getTimeDep());

            }


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
    public Page<FlightDto> findAll(Pageable pageable) {
        return flightRepository.findAll(pageable).map(flightEntity -> modelMapper.map(flightEntity, FlightDto.class));
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

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public Optional<FlightDto> findById(Long id) {
        Optional<FlightEntity> flightOptional = flightRepository.findById(id);
        return flightOptional.map(flightEntity -> Optional.of(modelMapper.map(flightEntity, FlightDto.class))).orElse(null);
    }
}
