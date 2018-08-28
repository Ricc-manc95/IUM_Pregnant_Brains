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

public final class AltRep extends Parameter
{

    public static final long serialVersionUID = 0xde0e4d4421b4ba42L;
    private URI uri;

    public AltRep(String s)
        throws URISyntaxException
    {
        this(Uris.create(Strings.unquote(s)));
    }

    private AltRep(URI uri1)
    {
        super("ALTREP", ParameterFactoryImpl.instance);
        uri = uri1;
    }

    public final String getValue()
    {
        return Uris.decode(Strings.valueOf(uri));
    }
}
