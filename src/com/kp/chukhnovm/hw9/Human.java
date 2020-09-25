package com.kp.chukhnovm.hw9;

import com.kp.chukhnovm.hw9.Enums.Gender;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Human implements Comparable<Human>, Serializable {

    private static final long serialVersionUID = 1L;
    protected String name;
    protected Date birthDay;
    protected Gender gender;

    public Human() {
    }

    public Human(String name, Date birthDay, Gender gender) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthDay(String birthDay) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date date = df.parse(birthDay);
        this.setBirthDay(date);
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return this.birthDay;
    }

    public String getBirthday(String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(this.birthDay);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender.toUpperCase());
    }

    /**
     * Get age in days
     * @return Int
     */
    public long getAgeInDays() {

        Date d1 = this.getBirthday();
        Date d2 = new Date();

        return d1 == null || d2 == null
                ? 0
                : (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * Get age in days
     * @return Int
     */
    public long getAgeInYears() {
        LocalDate bd = this.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();

        return Period.between(bd, now).getYears();
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", gender=" + gender +
                '}';
    }

    @Override
    public int compareTo(com.kp.chukhnovm.hw9.Human o) {
        if(o == null) return -1;
        return name.compareTo(o.name);
    }
}
