// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.text.MessageFormat;

public final class ValidationException extends Exception
{

    public static final long serialVersionUID = 0x44aa8f43c41f6f0L;

    public ValidationException()
    {
    }

    public ValidationException(String s)
    {
        super(s);
    }

    public ValidationException(String s, Object aobj[])
    {
        super(MessageFormat.format(s, aobj));
    }
}
