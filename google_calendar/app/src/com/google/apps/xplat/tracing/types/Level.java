// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing.types;


public final class Level extends Enum
{

    private static final Level $VALUES[];
    public static final Level CRITICAL;
    public static final Level DEBUG;
    public static final Level INFO;
    public static final Level NONE;
    public static final Level VERBOSE;
    public final int number;

    private Level(String s, int i, int j)
    {
        super(s, i);
        number = j;
    }

    public static Level[] values()
    {
        return (Level[])$VALUES.clone();
    }

    static 
    {
        NONE = new Level("NONE", 0, 0);
        VERBOSE = new Level("VERBOSE", 1, 1);
        DEBUG = new Level("DEBUG", 2, 2);
        INFO = new Level("INFO", 3, 3);
        CRITICAL = new Level("CRITICAL", 4, 4);
        $VALUES = (new Level[] {
            NONE, VERBOSE, DEBUG, INFO, CRITICAL
        });
    }
}
