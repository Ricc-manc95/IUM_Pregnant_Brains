// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;


// Referenced classes of package org.joda.time.format:
//            DateTimeParser, DateTimeParserBucket

final class iParsedLengthEstimate
    implements DateTimeParser
{

    private final int iParsedLengthEstimate;
    private final DateTimeParser iParsers[];

    public final int estimateParsedLength()
    {
        return iParsedLengthEstimate;
    }

    public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
    {
        Object obj;
        DateTimeParser adatetimeparser[];
        Object obj1;
        int j;
        int k;
        int l;
        int j1;
        adatetimeparser = iParsers;
        j1 = adatetimeparser.length;
        if (datetimeparserbucket.iSavedState == null)
        {
            datetimeparserbucket.iSavedState = new iParsers(datetimeparserbucket);
        }
        obj1 = datetimeparserbucket.iSavedState;
        obj = null;
        l = 0;
        k = i;
        j = i;
_L3:
        Object obj2;
        if (l >= j1)
        {
            break MISSING_BLOCK_LABEL_414;
        }
        obj2 = adatetimeparser[l];
        if (obj2 != null) goto _L2; else goto _L1
_L1:
        if (k <= i)
        {
            return i;
        }
        l = 1;
_L4:
        if (k > i || k == i && l != 0)
        {
            if (obj != null && (obj instanceof iParsers))
            {
                s = (iParsers)obj;
                int i1;
                if (datetimeparserbucket != ((iParsers) (s)).iParsers)
                {
                    i = 0;
                } else
                {
                    datetimeparserbucket.iZone = ((iParsers) (s)).iParsers;
                    datetimeparserbucket.iOffset = ((iParsers) (s)).iParsers;
                    datetimeparserbucket.iSavedFields = ((iParsers) (s)).elds;
                    if (((elds) (s)).eldsCount < datetimeparserbucket.iSavedFieldsCount)
                    {
                        datetimeparserbucket.iSavedFieldsShared = true;
                    }
                    datetimeparserbucket.iSavedFieldsCount = ((eldsCount) (s)).eldsCount;
                    i = 1;
                }
                if (i != 0)
                {
                    datetimeparserbucket.iSavedState = obj;
                }
            }
            return k;
        } else
        {
            return ~j;
        }
_L2:
        i1 = ((DateTimeParser) (obj2)).parseInto(datetimeparserbucket, s, i);
        if (i1 >= i)
        {
            if (i1 > k)
            {
                if (i1 >= s.length() || l + 1 >= j1 || adatetimeparser[l + 1] == null)
                {
                    return i1;
                }
                if (datetimeparserbucket.iSavedState == null)
                {
                    datetimeparserbucket.iSavedState = new eldsCount(datetimeparserbucket);
                }
                obj = datetimeparserbucket.iSavedState;
                k = i1;
            }
        } else
        if (i1 < 0)
        {
            i1 = ~i1;
            if (i1 > j)
            {
                j = i1;
            }
        }
        if (obj1 instanceof eldsCount)
        {
            obj2 = (eldsCount)obj1;
            if (datetimeparserbucket != ((eldsCount) (obj2)).eldsCount)
            {
                i1 = 0;
            } else
            {
                datetimeparserbucket.iZone = ((eldsCount) (obj2)).eldsCount;
                datetimeparserbucket.iOffset = ((eldsCount) (obj2)).eldsCount;
                datetimeparserbucket.iSavedFields = ((eldsCount) (obj2)).elds;
                if (((elds) (obj2)).eldsCount < datetimeparserbucket.iSavedFieldsCount)
                {
                    datetimeparserbucket.iSavedFieldsShared = true;
                }
                datetimeparserbucket.iSavedFieldsCount = ((eldsCount) (obj2)).eldsCount;
                i1 = 1;
            }
            if (i1 != 0)
            {
                datetimeparserbucket.iSavedState = obj1;
            }
        }
        l++;
          goto _L3
        l = 0;
          goto _L4
    }

    (DateTimeParser adatetimeparser[])
    {
        iParsers = adatetimeparser;
        int i = 0;
        int j = adatetimeparser.length;
        do
        {
            j--;
            if (j < 0)
            {
                break;
            }
            DateTimeParser datetimeparser = adatetimeparser[j];
            if (datetimeparser != null)
            {
                int k = datetimeparser.estimateParsedLength();
                if (k > i)
                {
                    i = k;
                }
            }
        } while (true);
        iParsedLengthEstimate = i;
    }
}
