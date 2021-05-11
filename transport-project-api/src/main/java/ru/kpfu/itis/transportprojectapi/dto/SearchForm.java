package ru.kpfu.itis.transportprojectapi.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchForm {
    private String cityFrom;
    private String cityTo;
    private String date;
    private int countOfPerson;
    private int sort;
}
