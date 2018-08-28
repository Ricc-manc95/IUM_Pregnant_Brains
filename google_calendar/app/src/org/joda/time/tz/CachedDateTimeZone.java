// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;

import org.joda.time.DateTimeZone;

public final class CachedDateTimeZone extends DateTimeZone
{

    private static final int cInfoCacheMask;
    public static final long serialVersionUID = 0x4bf18272d9b4ccbdL;
    private final Info iInfoCache[];
    private final DateTimeZone iZone;

    private CachedDateTimeZone(DateTimeZone datetimezone)
    {
        super(datetimezone.iID);
        iInfoCache = new Info[cInfoCacheMask + 1];
        iZone = datetimezone;
    }

    public static CachedDateTimeZone forZone(DateTimeZone datetimezone)
    {
        if (datetimezone instanceof CachedDateTimeZone)
        {
            return (CachedDateTimeZone)datetimezone;
        } else
        {
            return new CachedDateTimeZone(datetimezone);
        }
    }

    private final Info getInfo(long l)
    {
        Info info;
label0:
        {
            int i = (int)(l >> 32);
            Info ainfo[] = iInfoCache;
            int j = i & cInfoCacheMask;
            Info info1 = ainfo[j];
            if (info1 != null)
            {
                info = info1;
                if ((int)(info1.iPeriodStart >> 32) == i)
                {
                    break label0;
                }
            }
            long l1 = l & 0xffffffff00000000L;
            info = new Info(iZone, l1);
            info1 = info;
            l = l1;
            do
            {
                long l2 = iZone.nextTransition(l);
                if (l2 == l || l2 > (l1 | 0xffffffffL))
                {
                    break;
                }
                Info info2 = new Info(iZone, l2);
                info1.iNextInfo = info2;
                info1 = info2;
                l = l2;
            } while (true);
            ainfo[j] = info;
        }
        return info;
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj instanceof CachedDateTimeZone)
        {
            return iZone.equals(((CachedDateTimeZone)obj).iZone);
        } else
        {
            return false;
        }
    }

    public final String getNameKey(long l)
    {
        return getInfo(l).getNameKey(l);
    }

    public final int getOffset(long l)
    {
        return getInfo(l).getOffset(l);
    }

    public final int getStandardOffset(long l)
    {
        return getInfo(l).getStandardOffset(l);
    }

    public final int hashCode()
    {
        return iZone.hashCode();
    }

    public final boolean isFixed()
    {
        return iZone.isFixed();
    }

    public final long nextTransition(long l)
    {
        return iZone.nextTransition(l);
    }

    public final long previousTransition(long l)
    {
        return iZone.previousTransition(l);
    }

    static 
    {
        Object obj;
        int i;
        try
        {
            obj = Integer.getInteger("org.joda.time.tz.CachedDateTimeZone.size");
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj = null;
        }
        if (obj == null)
        {
            i = 512;
        } else
        {
            i = ((Integer) (obj)).intValue() - 1;
            int j = 0;
            for (; i > 0; i >>= 1)
            {
                j++;
            }

            i = 1 << j;
        }
        cInfoCacheMask = i - 1;
    }

    private class Info
    {

        private String iNameKey;
        public Info iNextInfo;
        private int iOffset;
        public final long iPeriodStart;
        private int iStandardOffset;
        private final DateTimeZone iZoneRef;

        public final String getNameKey(long l)
        {
            Info info = this;
            do
            {
                if (info.iNextInfo == null || l < info.iNextInfo.iPeriodStart)
                {
                    if (info.iNameKey == null)
                    {
                        info.iNameKey = info.iZoneRef.getNameKey(info.iPeriodStart);
                    }
                    return info.iNameKey;
                }
                info = info.iNextInfo;
            } while (true);
        }

        public final int getOffset(long l)
        {
            Info info = this;
            do
            {
                if (info.iNextInfo == null || l < info.iNextInfo.iPeriodStart)
                {
                    if (info.iOffset == 0x80000000)
                    {
                        info.iOffset = info.iZoneRef.getOffset(info.iPeriodStart);
                    }
                    return info.iOffset;
                }
                info = info.iNextInfo;
            } while (true);
        }

        public final int getStandardOffset(long l)
        {
            Info info = this;
            do
            {
                if (info.iNextInfo == null || l < info.iNextInfo.iPeriodStart)
                {
                    if (info.iStandardOffset == 0x80000000)
                    {
                        info.iStandardOffset = info.iZoneRef.getStandardOffset(info.iPeriodStart);
                    }
                    return info.iStandardOffset;
                }
                info = info.iNextInfo;
            } while (true);
        }

        Info(DateTimeZone datetimezone, long l)
        {
            iOffset = 0x80000000;
            iStandardOffset = 0x80000000;
            iPeriodStart = l;
            iZoneRef = datetimezone;
        }
    }

}
