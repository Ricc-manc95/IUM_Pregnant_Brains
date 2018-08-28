// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;


// Referenced classes of package org.joda.time:
//            DateTimeFieldType

public final class IllegalFieldValueException extends IllegalArgumentException
{

    public static final long serialVersionUID = 0x578263712b904f39L;
    public String iMessage;

    public IllegalFieldValueException(DateTimeFieldType datetimefieldtype, Number number, Number number1, Number number2)
    {
        super(createMessage(datetimefieldtype.iName, number, number1, number2, null));
        datetimefieldtype = datetimefieldtype.iName;
        iMessage = super.getMessage();
    }

    public IllegalFieldValueException(DateTimeFieldType datetimefieldtype, Number number, String s)
    {
        super(createMessage(datetimefieldtype.iName, number, null, null, s));
        datetimefieldtype = datetimefieldtype.iName;
        iMessage = super.getMessage();
    }

    public IllegalFieldValueException(DateTimeFieldType datetimefieldtype, String s)
    {
        String s1 = datetimefieldtype.iName;
        StringBuffer stringbuffer = new StringBuffer("Value ");
        if (s == null)
        {
            stringbuffer.append("null");
        } else
        {
            stringbuffer.append('"');
            stringbuffer.append(s);
            stringbuffer.append('"');
        }
        stringbuffer.append(" for ").append(s1).append(' ').append("is not supported");
        super(stringbuffer.toString());
        datetimefieldtype = datetimefieldtype.iName;
        iMessage = super.getMessage();
    }

    private static String createMessage(String s, Number number, Number number1, Number number2, String s1)
    {
        s = (new StringBuilder("Value ")).append(number).append(" for ").append(s).append(' ');
        if (number1 == null)
        {
            if (number2 == null)
            {
                s.append("is not supported");
            } else
            {
                s.append("must not be larger than ").append(number2);
            }
        } else
        if (number2 == null)
        {
            s.append("must not be smaller than ").append(number1);
        } else
        {
            s.append("must be in the range [").append(number1).append(',').append(number2).append(']');
        }
        if (s1 != null)
        {
            s.append(": ").append(s1);
        }
        return s.toString();
    }

    public final String getMessage()
    {
        return iMessage;
    }
}
