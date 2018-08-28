// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;

// Referenced classes of package org.joda.time.format:
//            DateTimeParser, DateTimePrinter, DateTimeParserBucket

final class iMaxDigits
    implements DateTimeParser, DateTimePrinter
{

    private final DateTimeFieldType iFieldType;
    private int iMaxDigits;
    private int iMinDigits;

    private final void printTo(StringBuffer stringbuffer, Writer writer, long l, Chronology chronology)
        throws IOException
    {
        int i;
        writer = iFieldType.getField(chronology);
        i = iMinDigits;
        long l2 = writer.remainder(l);
        if (l2 != 0L) goto _L2; else goto _L1
_L1:
        int k = i;
        if (stringbuffer != null)
        {
            do
            {
                i--;
                if (i < 0)
                {
                    break;
                }
                stringbuffer.append('0');
            } while (true);
        } else
        {
            i = k - 1;
            if (i >= 0)
            {
                throw new NullPointerException();
            }
        }
          goto _L3
        writer;
        if (stringbuffer != null)
        {
            do
            {
                i--;
                if (i < 0)
                {
                    break;
                }
                stringbuffer.append('\uFFFD');
            } while (true);
        } else
        {
            i--;
            if (i >= 0)
            {
                throw new NullPointerException();
            }
        }
          goto _L3
_L2:
        int i1;
        long l1;
        l1 = writer.getDurationField().getUnitMillis();
        i1 = iMaxDigits;
_L31:
        i1;
        JVM INSTR tableswitch 1 18: default 228
    //                   1 251
    //                   2 258
    //                   3 265
    //                   4 272
    //                   5 279
    //                   6 286
    //                   7 293
    //                   8 300
    //                   9 307
    //                   10 314
    //                   11 321
    //                   12 328
    //                   13 335
    //                   14 342
    //                   15 349
    //                   16 356
    //                   17 363
    //                   18 370;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22
_L4:
        l = 1L;
_L24:
        if ((l1 * l) / l == l1)
        {
            break; /* Loop/switch isn't completed */
        }
        i1--;
        continue; /* Loop/switch isn't completed */
_L5:
        l = 10L;
        continue; /* Loop/switch isn't completed */
_L6:
        l = 100L;
        continue; /* Loop/switch isn't completed */
_L7:
        l = 1000L;
        continue; /* Loop/switch isn't completed */
_L8:
        l = 10000L;
        continue; /* Loop/switch isn't completed */
_L9:
        l = 0x186a0L;
        continue; /* Loop/switch isn't completed */
_L10:
        l = 0xf4240L;
        continue; /* Loop/switch isn't completed */
_L11:
        l = 0x989680L;
        continue; /* Loop/switch isn't completed */
_L12:
        l = 0x5f5e100L;
        continue; /* Loop/switch isn't completed */
_L13:
        l = 0x3b9aca00L;
        continue; /* Loop/switch isn't completed */
_L14:
        l = 0x2540be400L;
        continue; /* Loop/switch isn't completed */
_L15:
        l = 0x174876e800L;
        continue; /* Loop/switch isn't completed */
_L16:
        l = 0xe8d4a51000L;
        continue; /* Loop/switch isn't completed */
_L17:
        l = 0x9184e72a000L;
        continue; /* Loop/switch isn't completed */
_L18:
        l = 0x5af3107a4000L;
        continue; /* Loop/switch isn't completed */
_L19:
        l = 0x38d7ea4c68000L;
        continue; /* Loop/switch isn't completed */
_L20:
        l = 0x2386f26fc10000L;
        continue; /* Loop/switch isn't completed */
_L21:
        l = 0x16345785d8a0000L;
        continue; /* Loop/switch isn't completed */
_L22:
        l = 0xde0b6b3a7640000L;
        if (true) goto _L24; else goto _L23
_L23:
        int j1;
        writer = new long[2];
        writer[0] = (l * l2) / l1;
        writer[1] = i1;
        l = writer[0];
        int k1 = (int)writer[1];
        if ((0x7fffffffL & l) == l)
        {
            writer = Integer.toString((int)l);
        } else
        {
            writer = Long.toString(l);
        }
        j1 = writer.length();
        i1 = i;
        for (i = k1; j1 < i;)
        {
            if (stringbuffer != null)
            {
                stringbuffer.append('0');
                i1--;
                i--;
            } else
            {
                throw new NullPointerException();
            }
        }

        if (i1 >= i) goto _L26; else goto _L25
_L25:
        for (; i1 < i && j1 > 1 && writer.charAt(j1 - 1) == '0'; j1--)
        {
            i--;
        }

        if (j1 >= writer.length()) goto _L26; else goto _L27
_L27:
        if (stringbuffer != null)
        {
            for (int j = 0; j < j1; j++)
            {
                stringbuffer.append(writer.charAt(j));
            }

        } else
        if (j1 < 0)
        {
            writer.charAt(0);
            throw new NullPointerException();
        }
          goto _L3
_L26:
        if (stringbuffer == null) goto _L29; else goto _L28
_L28:
        stringbuffer.append(writer);
_L3:
        return;
_L29:
        throw new NullPointerException();
        if (true) goto _L31; else goto _L30
_L30:
    }

    public final int estimateParsedLength()
    {
        return iMaxDigits;
    }

    public final int estimatePrintedLength()
    {
        return iMaxDigits;
    }

    public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
    {
        DateTimeField datetimefield = iFieldType.getField(datetimeparserbucket.iChrono);
        int k = Math.min(iMaxDigits, s.length() - i);
        long l = 0L;
        long l1 = datetimefield.getDurationField().getUnitMillis() * 10L;
        int j = 0;
        do
        {
            if (j >= k)
            {
                break;
            }
            char c = s.charAt(i + j);
            if (c < '0' || c > '9')
            {
                break;
            }
            j++;
            l1 /= 10L;
            l += (long)(c - 48) * l1;
        } while (true);
        l /= 10L;
        if (j == 0)
        {
            return ~i;
        }
        if (l > 0x7fffffffL)
        {
            return ~i;
        } else
        {
            datetimeparserbucket.saveField(new nit>(new PreciseDateTimeField(DateTimeFieldType.MILLIS_OF_SECOND_TYPE, MillisDurationField.INSTANCE, datetimefield.getDurationField()), (int)l));
            return j + i;
        }
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
    {
        try
        {
            printTo(stringbuffer, null, l, chronology);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (StringBuffer stringbuffer)
        {
            return;
        }
    }

    protected (DateTimeFieldType datetimefieldtype, int i, int j)
    {
        iFieldType = datetimefieldtype;
        int k = j;
        if (j > 18)
        {
            k = 18;
        }
        iMinDigits = i;
        iMaxDigits = k;
    }
}
