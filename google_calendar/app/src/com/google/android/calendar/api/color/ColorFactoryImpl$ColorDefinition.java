// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.color;


final class eventKey extends Enum
{

    private static final BIRCH $VALUES[];
    private static final BIRCH AMETHYST;
    private static final BIRCH AVOCADO;
    private static final BIRCH BANANA;
    private static final BIRCH BASIL;
    private static final BIRCH BIRCH;
    private static final BIRCH BLUEBERRY;
    private static final BIRCH CHERRY_BLOSSOM;
    private static final BIRCH CITRON;
    public static final BIRCH COBALT;
    private static final BIRCH COCOA;
    public static final BIRCH EUCALYPTUS;
    private static final BIRCH FLAMINGO;
    private static final BIRCH GRAPE;
    private static final BIRCH GRAPHITE;
    private static final BIRCH LAVENDER;
    private static final BIRCH MANGO;
    private static final BIRCH PEACOCK;
    private static final BIRCH PISTACHIO;
    private static final BIRCH PUMPKIN;
    private static final BIRCH RADICCIO;
    private static final BIRCH SAGE;
    private static final BIRCH TANGERINE;
    private static final BIRCH TOMATO;
    private static final BIRCH WISTERIA;
    public final String calendarKey;
    public final String eventKey;
    public final int value;

    public static eventKey[] values()
    {
        return (eventKey[])$VALUES.clone();
    }

    static 
    {
        TOMATO = new <init>("TOMATO", 0, 0xffd50000, "3", "11");
        TANGERINE = new <init>("TANGERINE", 1, 0xfff4511e, "4", "6");
        PUMPKIN = new <init>("PUMPKIN", 2, 0xffef6c00, "5", "N/A");
        MANGO = new <init>("MANGO", 3, 0xfff09300, "6", "N/A");
        BANANA = new <init>("BANANA", 4, 0xfff6bf26, "12", "5");
        CITRON = new <init>("CITRON", 5, 0xffe4c441, "11", "N/A");
        AVOCADO = new <init>("AVOCADO", 6, 0xffc0ca33, "10", "N/A");
        PISTACHIO = new <init>("PISTACHIO", 7, 0xff7cb342, "9", "N/A");
        BASIL = new <init>("BASIL", 8, 0xff0b8043, "8", "10");
        EUCALYPTUS = new <init>("EUCALYPTUS", 9, 0xff009688, "7", "N/A");
        SAGE = new <init>("SAGE", 10, 0xff33b679, "13", "2");
        PEACOCK = new <init>("PEACOCK", 11, 0xff039be5, "14", "7");
        COBALT = new <init>("COBALT", 12, 0xff4285f4, "15", "N/A");
        BLUEBERRY = new <init>("BLUEBERRY", 13, 0xff3f51b5, "16", "9");
        LAVENDER = new <init>("LAVENDER", 14, 0xff7986cb, "17", "1");
        WISTERIA = new <init>("WISTERIA", 15, 0xffb39ddb, "18", "N/A");
        AMETHYST = new <init>("AMETHYST", 16, 0xff9e69af, "24", "N/A");
        GRAPE = new <init>("GRAPE", 17, 0xff8e24aa, "23", "3");
        RADICCIO = new <init>("RADICCIO", 18, 0xffad1457, "21", "N/A");
        CHERRY_BLOSSOM = new <init>("CHERRY_BLOSSOM", 19, 0xffd81b60, "22", "N/A");
        FLAMINGO = new <init>("FLAMINGO", 20, 0xffe67c73, "2", "4");
        COCOA = new <init>("COCOA", 21, 0xff795548, "1", "N/A");
        GRAPHITE = new <init>("GRAPHITE", 22, 0xff616161, "19", "8");
        BIRCH = new <init>("BIRCH", 23, 0xffa79b8e, "20", "N/A");
        $VALUES = (new .VALUES[] {
            TOMATO, TANGERINE, PUMPKIN, MANGO, BANANA, CITRON, AVOCADO, PISTACHIO, BASIL, EUCALYPTUS, 
            SAGE, PEACOCK, COBALT, BLUEBERRY, LAVENDER, WISTERIA, AMETHYST, GRAPE, RADICCIO, CHERRY_BLOSSOM, 
            FLAMINGO, COCOA, GRAPHITE, BIRCH
        });
    }

    private (String s, int i, int j, String s1, String s2)
    {
        super(s, i);
        value = j;
        calendarKey = s1;
        eventKey = s2;
    }
}
