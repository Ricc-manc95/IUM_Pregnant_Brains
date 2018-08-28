// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.parameter;

import java.net.URI;
import java.net.URISyntaxException;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterFactoryImpl;
import net.fortuna.ical4j.util.Strings;
import net.fortuna.ical4j.util.Uris;

public final class SentBy extends Parameter
{

    public static final long serialVersionUID = 0xefc568f0b63acbb1L;
    private URI address;

    public SentBy(String s)
        throws URISyntaxException
    {
        this(Uris.create(Strings.unquote(s)));
    }

    private SentBy(URI uri)
    {
        super("SENT-BY", ParameterFactoryImpl.instance);
        address = uri;
    }

    public final String getValue()
    {
        return Uris.decode(Strings.valueOf(address));
    }
}
