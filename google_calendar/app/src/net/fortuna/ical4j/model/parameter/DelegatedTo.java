// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import java.net.URISyntaxException;
import net.fortuna.ical4j.model.AddressList;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;

public final class DelegatedTo extends Parameter
{

    public static final long serialVersionUID = 0x7e07047036564d5L;
    private AddressList delegatees;

    public DelegatedTo(String s)
        throws URISyntaxException
    {
        this(new AddressList(Strings.unquote(s)));
    }

    private DelegatedTo(AddressList addresslist)
    {
        super("DELEGATED-TO", ParameterFactoryImpl.instance);
        delegatees = addresslist;
    }

    public final String getValue()
    {
        return delegatees.toString();
    }

    protected final boolean isQuotable()
    {
        return false;
    }
}
