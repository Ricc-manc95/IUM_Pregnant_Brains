// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;


public final class CalendarException extends RuntimeException
{

    public static final long serialVersionUID = 0x3770f968a1067863L;

    public CalendarException(String s)
    {
        super(s);
    }

    public CalendarException(Throwable throwable)
    {
        super(throwable);
    }
}
