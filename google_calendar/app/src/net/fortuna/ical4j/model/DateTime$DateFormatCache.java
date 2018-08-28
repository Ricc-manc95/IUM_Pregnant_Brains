// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.DateFormat;
import java.util.Map;
import java.util.WeakHashMap;

final class templateFormat
{

    private final DateFormat templateFormat;
    private final Map threadMap = new WeakHashMap();

    public final DateFormat get()
    {
        DateFormat dateformat1 = (DateFormat)threadMap.get(Thread.currentThread());
        DateFormat dateformat = dateformat1;
        if (dateformat1 == null)
        {
            dateformat = (DateFormat)templateFormat.clone();
            threadMap.put(Thread.currentThread(), dateformat);
        }
        return dateformat;
    }

    (DateFormat dateformat)
    {
        templateFormat = dateformat;
    }
}
