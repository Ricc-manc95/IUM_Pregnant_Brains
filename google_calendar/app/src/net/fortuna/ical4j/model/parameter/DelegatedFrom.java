// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.AddressList;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class DelegatedFrom extends Parameter
{

    public static final long serialVersionUID = 0xf4f4321bc8174f38L;
    private AddressList delegators;

    public DelegatedFrom(String s)
        throws URISyntaxException
    {
        this(new AddressList(Strings.unquote(s)));
    }

    private DelegatedFrom(AddressList addresslist)
    {
        super("DELEGATED-FROM", ParameterFactoryImpl.instance);
        delegators = addresslist;
    }

    public final String getValue()
    {
        return delegators.toString();
    }

    protected final boolean isQuotable()
    {
        return false;
    }
}
