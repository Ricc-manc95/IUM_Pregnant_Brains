// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;


final class Transformer
    implements Transformer
{

    public final String transform$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIIJ3AC5R62BRCC5N6EBQJEHP6IRJ77C______0(String s)
    {
        int i;
        if (s != null && s.startsWith("http://www.google.com/calendar/feeds/"))
        {
            if ((i = s.indexOf("/events/", 37)) >= 0)
            {
                return s.substring(i + 8);
            }
        }
        return s;
    }

    Transformer()
    {
    }
}
