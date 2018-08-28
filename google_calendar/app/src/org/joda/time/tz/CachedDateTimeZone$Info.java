// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;

import org.joda.time.DateTimeZone;

final class iZoneRef
{

    private String iNameKey;
    public iNextInfo iNextInfo;
    private int iOffset;
    public final long iPeriodStart;
    private int iStandardOffset;
    private final DateTimeZone iZoneRef;

    public final String getNameKey(long l)
    {
        iZoneRef izoneref = this;
        do
        {
            if (izoneref.iNextInfo == null || l < izoneref.iNextInfo.iPeriodStart)
            {
                if (izoneref.iNameKey == null)
                {
                    izoneref.iNameKey = izoneref.iZoneRef.getNameKey(izoneref.iPeriodStart);
                }
                return izoneref.iNameKey;
            }
            izoneref = izoneref.iNextInfo;
        } while (true);
    }

    public final int getOffset(long l)
    {
        iNextInfo inextinfo = this;
        do
        {
            if (inextinfo.iNextInfo == null || l < inextinfo.iNextInfo.iPeriodStart)
            {
                if (inextinfo.iOffset == 0x80000000)
                {
                    inextinfo.iOffset = inextinfo.iZoneRef.getOffset(inextinfo.iPeriodStart);
                }
                return inextinfo.iOffset;
            }
            inextinfo = inextinfo.iNextInfo;
        } while (true);
    }

    public final int getStandardOffset(long l)
    {
        iNextInfo inextinfo = this;
        do
        {
            if (inextinfo.iNextInfo == null || l < inextinfo.iNextInfo.iPeriodStart)
            {
                if (inextinfo.iStandardOffset == 0x80000000)
                {
                    inextinfo.iStandardOffset = inextinfo.iZoneRef.getStandardOffset(inextinfo.iPeriodStart);
                }
                return inextinfo.iStandardOffset;
            }
            inextinfo = inextinfo.iNextInfo;
        } while (true);
    }

    (DateTimeZone datetimezone, long l)
    {
        iOffset = 0x80000000;
        iStandardOffset = 0x80000000;
        iPeriodStart = l;
        iZoneRef = datetimezone;
    }
}
