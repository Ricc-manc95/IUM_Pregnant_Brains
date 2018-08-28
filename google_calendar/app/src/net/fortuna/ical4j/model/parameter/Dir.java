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

public final class Dir extends Parameter
{

    public static final long serialVersionUID = 0x88e6f3125c806aefL;
    private URI uri;

    public Dir(String s)
        throws URISyntaxException
    {
        this(Uris.create(Strings.unquote(s)));
    }

    private Dir(URI uri1)
    {
        super("DIR", ParameterFactoryImpl.instance);
        uri = uri1;
    }

    public final String getValue()
    {
        return Uris.decode(Strings.valueOf(uri));
    }
}
