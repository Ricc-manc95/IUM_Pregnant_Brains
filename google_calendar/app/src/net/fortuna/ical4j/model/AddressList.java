// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.Strings;
import net.fortuna.ical4j.util.Uris;

public final class AddressList
    implements Serializable
{

    public static final long serialVersionUID = 0x12121a3e534e1c1L;
    private List addresses;

    public AddressList()
    {
        addresses = new CopyOnWriteArrayList();
    }

    public AddressList(String s)
        throws URISyntaxException
    {
        addresses = new CopyOnWriteArrayList();
        for (s = new StringTokenizer(s, ","); s.hasMoreTokens();)
        {
            try
            {
                addresses.add(new URI(Uris.encode(Strings.unquote(s.nextToken()))));
            }
            catch (URISyntaxException urisyntaxexception)
            {
                if (!CompatibilityHints.isHintEnabled("ical4j.parsing.relaxed"))
                {
                    throw urisyntaxexception;
                }
            }
        }

    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator = addresses.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            stringbuffer.append(Strings.quote(Uris.decode(Strings.valueOf(iterator.next()))));
            if (iterator.hasNext())
            {
                stringbuffer.append(',');
            }
        } while (true);
        return stringbuffer.toString();
    }
}
