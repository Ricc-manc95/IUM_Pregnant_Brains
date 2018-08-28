// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.format:
//            DateTimeParser, DateTimePrinter, DateTimeParserBucket, FormatUtils

public final class iMaxFields
    implements DateTimeParser, DateTimePrinter
{

    private final int iMaxFields;
    private final int iMinFields;
    private final boolean iShowSeparators;
    private final String iZeroOffsetParseText;
    private final String iZeroOffsetPrintText;

    private static int digitCount(String s, int i, int j)
    {
        j = Math.min(s.length() - i, j);
        int k = 0;
        do
        {
            if (j <= 0)
            {
                break;
            }
            char c = s.charAt(i + k);
            if (c < '0' || c > '9')
            {
                break;
            }
            k++;
            j--;
        } while (true);
        return k;
    }

    public final int estimateParsedLength()
    {
        return estimatePrintedLength();
    }

    public final int estimatePrintedLength()
    {
        int j = iMinFields + 1 << 1;
        int i = j;
        if (iShowSeparators)
        {
            i = j + (iMinFields - 1);
        }
        j = i;
        if (iZeroOffsetPrintText != null)
        {
            j = i;
            if (iZeroOffsetPrintText.length() > i)
            {
                j = iZeroOffsetPrintText.length();
            }
        }
        return j;
    }

    public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
    {
        int j;
        boolean flag;
        boolean flag1;
        int l1;
        int i2;
        int j2;
label0:
        {
            flag1 = false;
            j = s.length() - i;
            if (iZeroOffsetParseText == null)
            {
                break label0;
            }
            if (iZeroOffsetParseText.length() == 0)
            {
                if (j > 0)
                {
                    char c = s.charAt(i);
                    if (c == '-' || c == '+')
                    {
                        break label0;
                    }
                }
                datetimeparserbucket.iSavedState = null;
                datetimeparserbucket.iOffset = Integer.valueOf(0);
                return i;
            }
            if (s.regionMatches(true, i, iZeroOffsetParseText, 0, iZeroOffsetParseText.length()))
            {
                datetimeparserbucket.iSavedState = null;
                datetimeparserbucket.iOffset = Integer.valueOf(0);
                return i + iZeroOffsetParseText.length();
            }
        }
        if (j <= 1)
        {
            return ~i;
        }
        int k = s.charAt(i);
        if (k == '-')
        {
            flag = true;
        } else
        if (k == '+')
        {
            flag = false;
        } else
        {
            return ~i;
        }
        i++;
        if (digitCount(s, i, 2) < 2)
        {
            return ~i;
        }
        k = FormatUtils.parseTwoDigits(s, i);
        if (k > 23)
        {
            return ~i;
        }
        j2 = k * 0x36ee80;
        l1 = j - 1 - 2;
        i2 = i + 2;
        i = i2;
        j = j2;
        if (l1 <= 0) goto _L2; else goto _L1
_L1:
        int k2 = s.charAt(i2);
        if (k2 != ':') goto _L4; else goto _L3
_L3:
        int l;
        l1--;
        l = i2 + 1;
        flag1 = true;
_L10:
        i2 = digitCount(s, l, 2);
        if (i2 != 0) goto _L6; else goto _L5
_L5:
        i = l;
        j = j2;
        if (!flag1) goto _L2; else goto _L6
_L6:
        if (i2 < 2)
        {
            return ~l;
        }
        i = FormatUtils.parseTwoDigits(s, l);
        if (i > 59)
        {
            return ~l;
        }
        j2 += i * 60000;
        k2 = l1 - 2;
        i2 = l + 2;
        i = i2;
        j = j2;
          goto _L7
_L4:
        i = i2;
        j = j2;
        if (k2 < '0') goto _L2; else goto _L8
_L8:
        l = i2;
        if (k2 <= '9') goto _L10; else goto _L9
_L9:
        j = j2;
        i = i2;
_L2:
        l = j;
        j = i;
        i = l;
_L14:
        if (flag)
        {
            i = -i;
        }
        datetimeparserbucket.iSavedState = null;
        datetimeparserbucket.iOffset = Integer.valueOf(i);
        return j;
_L7:
        if (k2 <= 0) goto _L2; else goto _L11
_L11:
        l1 = k2;
        l = i2;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_452;
        }
        i = i2;
        j = j2;
        if (s.charAt(i2) != ':') goto _L2; else goto _L12
_L12:
        l1 = k2 - 1;
        l = i2 + 1;
        i2 = digitCount(s, l, 2);
        if (i2 != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        i = l;
        j = j2;
        if (!flag1) goto _L2; else goto _L13
_L13:
label1:
        {
            if (i2 < 2)
            {
                return ~l;
            }
            i = FormatUtils.parseTwoDigits(s, l);
            if (i > 59)
            {
                return ~l;
            }
            i2 = j2 + i * 1000;
            l += 2;
            i = l;
            if (l1 - 2 <= 0)
            {
                break label1;
            }
            j = l;
            if (flag1)
            {
                if (s.charAt(l) != '.')
                {
                    i = l;
                    if (s.charAt(l) != ',')
                    {
                        break label1;
                    }
                }
                j = l + 1;
            }
            l1 = digitCount(s, j, 3);
            if (l1 == 0)
            {
                i = j;
                if (!flag1)
                {
                    break label1;
                }
            }
            if (l1 <= 0)
            {
                return ~j;
            }
            int k1 = j + 1;
            i = (s.charAt(j) - 48) * 100 + i2;
            if (l1 > 1)
            {
                int i1 = k1 + 1;
                k1 = (s.charAt(k1) - 48) * 10 + i;
                i = k1;
                j = i1;
                if (l1 > 2)
                {
                    i = k1 + (s.charAt(i1) - 48);
                    j = i1 + 1;
                }
            } else
            {
                j = k1;
            }
        }
          goto _L14
        j = i2;
        int j1 = i;
        i = j;
        j = j1;
          goto _L14
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
    {
        if (datetimezone != null)
        {
            if (i == 0 && iZeroOffsetPrintText != null)
            {
                stringbuffer.append(iZeroOffsetPrintText);
                return;
            }
            int j;
            if (i >= 0)
            {
                stringbuffer.append('+');
            } else
            {
                stringbuffer.append('-');
                i = -i;
            }
            j = i / 0x36ee80;
            FormatUtils.appendPaddedInteger(stringbuffer, j, 2);
            if (iMaxFields != 1)
            {
                i -= j * 0x36ee80;
                if (i != 0 || iMinFields > 1)
                {
                    int k = i / 60000;
                    if (iShowSeparators)
                    {
                        stringbuffer.append(':');
                    }
                    FormatUtils.appendPaddedInteger(stringbuffer, k, 2);
                    if (iMaxFields != 2)
                    {
                        i -= k * 60000;
                        if (i != 0 || iMinFields > 2)
                        {
                            int i1 = i / 1000;
                            if (iShowSeparators)
                            {
                                stringbuffer.append(':');
                            }
                            FormatUtils.appendPaddedInteger(stringbuffer, i1, 2);
                            if (iMaxFields != 3)
                            {
                                i -= i1 * 1000;
                                if (i != 0 || iMinFields > 3)
                                {
                                    if (iShowSeparators)
                                    {
                                        stringbuffer.append('.');
                                    }
                                    FormatUtils.appendPaddedInteger(stringbuffer, i, 3);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public (String s, String s1, boolean flag, int i, int j)
    {
        byte byte0 = 4;
        super();
        iZeroOffsetPrintText = s;
        iZeroOffsetParseText = s1;
        iShowSeparators = flag;
        if (i <= 0 || j < i)
        {
            throw new IllegalArgumentException();
        }
        if (i > 4)
        {
            j = 4;
            i = byte0;
        }
        iMinFields = i;
        iMaxFields = j;
    }
}
