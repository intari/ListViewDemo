package com.viorsan.listviewdemo.Models;

import android.content.Context;
import android.util.Log;

import com.viorsan.listviewdemo.R;

/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 26.01.16.
 */
public enum DATE_TYPE {
    TODAY,
    YESTERDAY,
    TWO_DAYS_AGO,
    THREE_DAYS_AGO,
    BEFORE;
    public static final String TAG = DATE_TYPE.class.getName();

    /***
     * Возвращает локализованное строковое представление значения enum'а
     * @param context контект для работы
     * @return строковое представление
     */
    public String getStringValue(Context context) {
        switch (this) {
            case TODAY:
                return context.getString(R.string.today);
            case YESTERDAY:
                return context.getString(R.string.yesterday);
            case TWO_DAYS_AGO:
                return context.getString(R.string.twodaysago);
            case THREE_DAYS_AGO:
                return context.getString(R.string.threedaysago);
            case BEFORE:
                return context.getString(R.string.before);

            default:
                //по хорошему не надо но а вдруг забуду где то
                Log.d(TAG,"Requested string value of DATE_TYPE which was not setup, will return unknown");
                return context.getString(R.string.unknown);
        }
    }
}
