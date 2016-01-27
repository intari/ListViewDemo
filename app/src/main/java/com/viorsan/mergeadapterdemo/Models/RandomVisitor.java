package com.viorsan.mergeadapterdemo.Models;

import android.util.Log;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.Random;

/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 26.01.16.
 * Генерация 'случайного' посетителя
 */
public class RandomVisitor extends Visitor {
    public static final String TAG = RandomVisitor.class.getName();
    //массив случайных имен для генерации
    private static String[] names= {
            "Таня",
            "Лена",
            "Инга",
            "Зульфия",
            "Оксана",
            "Анхела",
            "Ольга",
            "Елена",
            "Виктория",
            "Анна",
            "Мария",
            "Ася",
            "Вика",
            "Тая",
            "Лера",
            "Агнесса",
            "Аврора",
            "Ада",
            "Анита",
            "Белла",
            "Галина",
            "Гелла",
            "Дана",
            "Лиза",
            "Зина",
            "Кира",
            "Клара",
            "Марта",
            "Майя",
            "Оля"
    };

    /**
     * Случайное имя
     * @return
     */
    private static String getRandomName() {
        Random random=new Random();
        int nameId=random.nextInt(names.length);
        return names[nameId];
    }

    /**
     * Дата на заданое количество дней в прошлом
     * @param howFar на сколько
     * @return
     */
    private static Date getDateInPast(int howFar) {

        DateTime dt=new DateTime();
        DateTime result=dt.minusDays(howFar);
        return result.toDate();
    }
    private static Date getRandomDateByDt(DATE_TYPE dt) {
        switch (dt) {
            case TODAY:
                return getDateInPast(0);
            case YESTERDAY:
                return getDateInPast(1);
            case TWO_DAYS_AGO:
                return getDateInPast(2);
            case THREE_DAYS_AGO:
                return getDateInPast(3);
            case BEFORE:
                return getDateInPast(4);
            default:
                return getDateInPast(4);
        }
    }

    /**
     * Случайный посетитель заданоне количество дней назад
     * @param dt
     */
    public RandomVisitor(DATE_TYPE dt) {
        super(getRandomName(), getRandomDateByDt(dt));
    }

}
