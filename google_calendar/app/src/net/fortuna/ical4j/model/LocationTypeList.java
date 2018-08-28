// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;

public final class LocationTypeList
    implements Serializable
{

    public static final long serialVersionUID = 0x8093ec2a9779e728L;
    private List locationTypes;

    public LocationTypeList()
    {
        locationTypes = new CopyOnWriteArrayList();
    }

    public LocationTypeList(String s)
    {
        locationTypes = new CopyOnWriteArrayList();
        for (s = new StringTokenizer(s, ","); s.hasMoreTokens(); locationTypes.add(s.nextToken())) { }
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator = locationTypes.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            stringbuffer.append(iterator.next());
            if (iterator.hasNext())
            {
                stringbuffer.append(',');
            }
        } while (true);
        return stringbuffer.toString();
    }
}
