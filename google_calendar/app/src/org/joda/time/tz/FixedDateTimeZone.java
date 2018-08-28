// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;

import org.joda.time.DateTimeZone;

public final class FixedDateTimeZone extends DateTimeZone
{

    public static final long serialVersionUID = 0xcf3f4667c8adc9fcL;
    private final String iNameKey;
    private final int iStandardOffset;
    private final int iWallOffset;

    public FixedDateTimeZone(String s, String s1, int i, int j)
    {
        super(s);
        iNameKey = s1;
        iWallOffset = i;
        iStandardOffset = j;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj instanceof FixedDateTimeZone)
            {
                if (!super.iID.equals(((DateTimeZone) (obj = (FixedDateTimeZone)obj)).iID) || iStandardOffset != ((FixedDateTimeZone) (obj)).iStandardOffset || iWallOffset != ((FixedDateTimeZone) (obj)).iWallOffset)
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final String getNameKey(long l)
    {
        return iNameKey;
    }

    public final int getOffset(long l)
    {
        return iWallOffset;
    }

    public final int getOffsetFromLocal(long l)
    {
        return iWallOffset;
    }

    public final int getStandardOffset(long l)
    {
        return iStandardOffset;
    }

    public final int hashCode()
    {
        return super.iID.hashCode() + iStandardOffset * 37 + iWallOffset * 31;
    }

    public final boolean isFixed()
    {
        return true;
    }

    public final long nextTransition(long l)
    {
        return l;
    }

    public final long previousTransition(long l)
    {
        return l;
    }
}
