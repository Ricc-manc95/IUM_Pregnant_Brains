package com.unica.pregnantbrains.ddgridmanager.adapters;

import android.graphics.Color;

public class ColorPicker_Item {
    private int color;
    private String s;

    public ColorPicker_Item(){

    }
    public ColorPicker_Item(int color,String pippo){
        this.color = color;
        this.s = pippo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

}
