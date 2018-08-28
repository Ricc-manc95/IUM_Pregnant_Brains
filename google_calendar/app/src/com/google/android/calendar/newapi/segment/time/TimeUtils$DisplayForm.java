// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;


public final class  extends Enum
{

    private static final HEURISTIC $VALUES[];
    public static final HEURISTIC HEURISTIC;
    public static final HEURISTIC LONG;
    public static final HEURISTIC SHORT;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        SHORT = new <init>("SHORT", 0);
        LONG = new <init>("LONG", 1);
        HEURISTIC = new <init>("HEURISTIC", 2);
        $VALUES = (new .VALUES[] {
            SHORT, LONG, HEURISTIC
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
