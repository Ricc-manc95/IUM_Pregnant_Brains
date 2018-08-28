// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model.property;

import java.net.URI;
import java.net.URISyntaxException;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryImpl;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.util.Strings;
import net.fortuna.ical4j.util.Uris;

public final class Url extends Property
{

    public static final long serialVersionUID = 0xf299c72fa7979a9L;
    private URI uri;

    public Url()
    {
        super("URL", PropertyFactoryImpl.instance);
    }

    public final String getValue()
    {
        return Uris.decode(Strings.valueOf(uri));
    }

    public final void setValue(String s)
        throws URISyntaxException
    {
        uri = Uris.create(s);
    }

    public final void validate()
        throws ValidationException
    {
    }
}
