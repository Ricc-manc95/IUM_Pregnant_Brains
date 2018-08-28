// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import org.joda.time.DateTimeFieldType;

// Referenced classes of package org.joda.time.format:
//            DateTimeParser, DateTimePrinter, DateTimeParserBucket

abstract class iSigned
    implements DateTimeParser, DateTimePrinter
{

    public final DateTimeFieldType iFieldType;
    public final int iMaxParsedDigits;
    public final boolean iSigned;

    public final int estimateParsedLength()
    {
        return iMaxParsedDigits;
    }

    public int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
    {
        int j;
        int k;
        int l;
        int i1 = Math.min(iMaxParsedDigits, s.length() - i);
        k = 0;
        j = i;
        l = 0;
        i = k;
        k = l;
        do
        {
            l = i;
            if (k >= i1)
            {
                break;
            }
            char c = s.charAt(j + k);
            if (k == 0 && (c == '-' || c == '+') && iSigned)
            {
                if (c == '-')
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                l = i;
                if (k + 1 >= i1)
                {
                    break;
                }
                c = s.charAt(j + k + 1);
                l = i;
                if (c < '0')
                {
                    break;
                }
                l = i;
                if (c > '9')
                {
                    break;
                }
                if (i != 0)
                {
                    k++;
                } else
                {
                    j++;
                }
                i1 = Math.min(i1 + 1, s.length() - j);
                continue;
            }
            l = i;
            if (c < '0')
            {
                break;
            }
            l = i;
            if (c > '9')
            {
                break;
            }
            k++;
        } while (true);
        if (k == 0)
        {
            return ~j;
        }
        if (k < 9) goto _L2; else goto _L1
_L1:
        k = j + k;
        j = Integer.parseInt(s.substring(j, k));
_L4:
        datetimeparserbucket.saveField(new iSigned(iFieldType.getField(datetimeparserbucket.iChrono), j));
        return k;
_L2:
        int j1;
        int k1;
        if (l != 0)
        {
            i = j + 1;
        } else
        {
            i = j;
        }
        k1 = i + 1;
        try
        {
            i = s.charAt(i);
        }
        // Misplaced declaration of an exception variable
        catch (DateTimeParserBucket datetimeparserbucket)
        {
            return ~j;
        }
        j1 = j + k;
        i -= 48;
        for (j = k1; j < j1; j++)
        {
            i = (s.charAt(j) + ((i << 3) + (i << 1))) - 48;
        }

        j = i;
        k = j1;
        if (l != 0)
        {
            j = -i;
            k = j1;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    (DateTimeFieldType datetimefieldtype, int i, boolean flag)
    {
        iFieldType = datetimefieldtype;
        iMaxParsedDigits = i;
        iSigned = flag;
    }
}
