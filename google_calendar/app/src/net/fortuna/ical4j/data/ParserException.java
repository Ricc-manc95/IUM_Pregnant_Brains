// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.text.MessageFormat;

public final class ParserException extends Exception
{

    public static final long serialVersionUID = 0x54e2af88d2f160a6L;

    public ParserException(String s, int i)
    {
        String s1 = String.valueOf(MessageFormat.format("Error at line {0}:", new Object[] {
            new Integer(i)
        }));
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = s1.concat(s);
        } else
        {
            s = new String(s1);
        }
        super(s);
    }

    public ParserException(String s, int i, Throwable throwable)
    {
        String s1 = String.valueOf(MessageFormat.format("Error at line {0}:", new Object[] {
            new Integer(i)
        }));
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = s1.concat(s);
        } else
        {
            s = new String(s1);
        }
        super(s, throwable);
    }
}
