// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.Strings;

public final class Range extends Parameter
{

    public static final Range THISANDFUTURE = new Range("THISANDFUTURE");
    public static final Range THISANDPRIOR = new Range("THISANDPRIOR");
    public static final long serialVersionUID = 0xd5917759c5828e50L;
    private String value;

    public Range(String s)
    {
        super("RANGE", ParameterFactoryImpl.instance);
        value = Strings.unquote(s);
        if (!CompatibilityHints.isHintEnabled("ical4j.compatibility.notes") && !"THISANDPRIOR".equals(value) && !"THISANDFUTURE".equals(value))
        {
            s = value;
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 16)).append("Invalid value [").append(s).append("]").toString());
        } else
        {
            return;
        }
    }

    public final String getValue()
    {
        return value;
    }

}
