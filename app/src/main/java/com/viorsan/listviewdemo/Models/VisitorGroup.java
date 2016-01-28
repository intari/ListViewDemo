package com.viorsan.listviewdemo.Models;

import java.util.ArrayList;

/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 28.01.16.
 */
public class VisitorGroup {
    private DATE_TYPE dateType;
    private ArrayList<Visitor> visitors;
    public VisitorGroup(DATE_TYPE dateType,ArrayList<Visitor> visitors) {
        this.dateType=dateType;
        this.visitors=visitors;
    }

    public DATE_TYPE getDateType() {
        return dateType;
    }

    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }
}
