// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;


public final class XLogLevel extends Enum
{

    private static final XLogLevel $VALUES[];
    public static final XLogLevel DEBUG;
    public static final XLogLevel ERROR;
    public static final XLogLevel INFO;
    public static final XLogLevel VERBOSE;
    public static final XLogLevel WARN;

    private XLogLevel(String s, int i)
    {
        super(s, i);
    }

    public static XLogLevel[] values()
    {
        return (XLogLevel[])$VALUES.clone();
    }

    static 
    {
        VERBOSE = new XLogLevel("VERBOSE", 0);
        DEBUG = new XLogLevel("DEBUG", 1);
        INFO = new XLogLevel("INFO", 2);
        WARN = new XLogLevel("WARN", 3);
        ERROR = new XLogLevel("ERROR", 4);
        $VALUES = (new XLogLevel[] {
            VERBOSE, DEBUG, INFO, WARN, ERROR
        });
    }
}
