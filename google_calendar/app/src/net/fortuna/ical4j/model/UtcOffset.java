// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.apache.commons.lang.builder.HashCodeBuilder;

public final class UtcOffset
    implements Serializable
{

    private static final NumberFormat HOUR_FORMAT = new DecimalFormat("00");
    private static final NumberFormat MINUTE_FORMAT = new DecimalFormat("00");
    private static final NumberFormat SECOND_FORMAT = new DecimalFormat("00");
    public static final long serialVersionUID = 0x51a50330e26d0b50L;
    public long offset;

    public UtcOffset(String s)
    {
        if (s.length() < 5)
        {
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 58)).append("Invalid UTC offset [").append(s).append("] - must be of the form: (+/-)HHMM[SS]").toString());
        }
        boolean flag;
        if (s.charAt(0) == '-')
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag && s.charAt(0) != '+')
        {
            throw new IllegalArgumentException("UTC offset value must be signed");
        }
        offset = 0L;
        offset = offset + (long)Integer.parseInt(s.substring(1, 3)) * 0x36ee80L;
        offset = offset + (long)Integer.parseInt(s.substring(3, 5)) * 60000L;
        if (s.length() == 7)
        {
            offset = offset + (long)Integer.parseInt(s.substring(5, 7)) * 1000L;
        }
        if (flag)
        {
            offset = -offset;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof UtcOffset)
        {
            return offset == ((UtcOffset)obj).offset;
        } else
        {
            return super.equals(obj);
        }
    }

    public final int hashCode()
    {
        HashCodeBuilder hashcodebuilder = new HashCodeBuilder();
        long l = offset;
        hashcodebuilder.iTotal = hashcodebuilder.iTotal * hashcodebuilder.iConstant + (int)(l ^ l >> 32);
        return hashcodebuilder.iTotal;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        long l = Math.abs(offset);
        if (offset < 0L)
        {
            stringbuffer.append('-');
        } else
        {
            stringbuffer.append('+');
        }
        stringbuffer.append(HOUR_FORMAT.format(l / 0x36ee80L));
        l %= 0x36ee80L;
        stringbuffer.append(MINUTE_FORMAT.format(l / 60000L));
        l %= 60000L;
        if (l > 0L)
        {
            stringbuffer.append(SECOND_FORMAT.format(l / 1000L));
        }
        return stringbuffer.toString();
    }

}
