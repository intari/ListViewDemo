package com.viorsan.listviewdemo.Models;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Date;

/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 26.01.16.
 */
public class Visitor {
    public static final String TAG = Visitor.class.getName();
    String name; //ФИО посетителя
    DATE_TYPE dateType;//тип даты последнего посещения
    Date date;
    /***
     * Создает посетителя
     * @param name ФИО
     * @param date Когда был
     */
    public Visitor(String name,  Date date) {
        this.name=name;
        this.date=date;

        //посчитаем а собственно собственно прошло времени. опять наше спасибо JodaTime за упрощение кода
        DateTime now=new DateTime(new Date());

        DateTime dt=new DateTime(date);
        int daysBetween= Days.daysBetween(dt,now).getDays();
        if (daysBetween<1) {
            this.dateType=DATE_TYPE.TODAY;
        } else if (daysBetween<2) {
            this.dateType=DATE_TYPE.YESTERDAY;
        } else if (daysBetween<3) {
            this.dateType=DATE_TYPE.TWO_DAYS_AGO;
        } else if (daysBetween<4) {
            this.dateType=DATE_TYPE.THREE_DAYS_AGO;
        } else {
            this.dateType=DATE_TYPE.BEFORE;
        }


    }
    public String getName() {
        return name;
    }
    public DATE_TYPE getDateType () {
        return dateType;
    }
    public Date getDate() {
        return date;
    }

}
