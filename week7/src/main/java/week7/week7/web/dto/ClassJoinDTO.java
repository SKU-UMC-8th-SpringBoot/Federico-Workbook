package week7.week7.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ClassJoinDTO {
    String name;
    Integer gender;
    Integer birthYear;
    Integer birthMonth;
    Integer birthDay;
    String address;
    String specAddress;
    List<Long> preferCategory;
}
