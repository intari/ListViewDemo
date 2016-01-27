package com.viorsan.mergeadapterdemo.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 26.01.16.
 * обычный синглтон хранящий наший данные
 */
public class Visitors {
    public static final String TAG = Visitors.class.getName();

    public static final int NUM_VISITS_TO_GENERATE=2;//сколько у нас будет посетителей в каждый интервал
    private static Visitors staticVisitors;
    private ArrayList<Visitor> people;
    private Visitors() {
        people=new ArrayList<Visitor>();
    }
    /***
     * Получение значения нашего синглтона и инициализаиция если нужно
     * @return
     */
    public static Visitors get() {
        if (staticVisitors == null) {
            staticVisitors = new Visitors();
            staticVisitors.generateRandomPeople();
        }
        return staticVisitors;
    }

    /***
     * Сортируем по дате
     * Правда учитывая как работает генерация в RandomVisitor то смысла в этой сортировке - нет
     */
    public void sort() {
        Collections.sort(people, new Comparator<Visitor>() {
            @Override
            public int compare(Visitor lhs, Visitor rhs) {

                if (lhs.date.after(rhs.date)) {
                    return 1;
                }
                if (lhs.date.before(rhs.date)) {
                    return -1;
                }
                return 0;
            }
        });
    }

    /**
     * Возвращает общий список всех посетителей
     * @return
     */
    public ArrayList<Visitor> getPeople() {
        return people;
    }

    /***
     * Возвращает список посетителей в заданную дату
     * @param date_type какая дата нам интеречна
     * @return
     */
    public ArrayList<Visitor> getPeopleVisitedAt(DATE_TYPE date_type) {
        ArrayList<Visitor> result=new ArrayList<>();
        for (Visitor v:people) {
            if (v.dateType==date_type) {
                result.add(v);
            }
        }
        return result;
    }


    /***
     * Создаем случайных посетителей
     * Учитывая как работает генерация, они будут все на одно и то же (текущее) время суток но возможно в прошлом
     */
    private void generateRandomPeople() {
        for (int i=0;i<NUM_VISITS_TO_GENERATE;i++) {
            people.add(new RandomVisitor(DATE_TYPE.TODAY));
            people.add(new RandomVisitor(DATE_TYPE.YESTERDAY));
            people.add(new RandomVisitor(DATE_TYPE.TWO_DAYS_AGO));
            people.add(new RandomVisitor(DATE_TYPE.THREE_DAYS_AGO));
            people.add(new RandomVisitor(DATE_TYPE.BEFORE));
        }

    }

}
