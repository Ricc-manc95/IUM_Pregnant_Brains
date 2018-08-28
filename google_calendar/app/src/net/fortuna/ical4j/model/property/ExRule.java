// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.text.ParseException;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.ValidationException;

public final class ExRule extends Property
{

    public static final long serialVersionUID = 0x80b95fd42e665a22L;
    private Recur recur;

    public ExRule()
    {
        super("EXRULE", PropertyFactoryImpl.instance);
        recur = new Recur("DAILY", 1);
    }

    public final String getValue()
    {
        return recur.toString();
    }

    public final void setValue(String s)
        throws ParseException
    {
        recur = new Recur(s);
    }

    public final void validate()
        throws ValidationException
    {
    }
}
