// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.io.InputStream;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceLoader
{

    private static final Log LOG = LogFactory.getLog(net/fortuna/ical4j/util/ResourceLoader);

    public ResourceLoader()
    {
    }

    public static URL getResource(String s)
    {
        Object obj;
        URL url;
        try
        {
            obj = Thread.currentThread().getContextClassLoader().getResource(s);
        }
        catch (SecurityException securityexception)
        {
            Log log = LOG;
            securityexception = String.valueOf(securityexception.getMessage());
            if (securityexception.length() != 0)
            {
                securityexception = "Unable to access context classloader, using default. ".concat(securityexception);
            } else
            {
                securityexception = new String("Unable to access context classloader, using default. ");
            }
            log.info(securityexception);
            securityexception = null;
        }
        url = ((URL) (obj));
        if (obj == null)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "/".concat(s);
            } else
            {
                s = new String("/");
            }
            url = net/fortuna/ical4j/util/ResourceLoader.getResource(s);
        }
        return url;
    }

    public static InputStream getResourceAsStream(String s)
    {
        Object obj;
        InputStream inputstream;
        try
        {
            obj = Thread.currentThread().getContextClassLoader().getResourceAsStream(s);
        }
        catch (SecurityException securityexception)
        {
            Log log = LOG;
            securityexception = String.valueOf(securityexception.getMessage());
            if (securityexception.length() != 0)
            {
                securityexception = "Unable to access context classloader, using default. ".concat(securityexception);
            } else
            {
                securityexception = new String("Unable to access context classloader, using default. ");
            }
            log.info(securityexception);
            securityexception = null;
        }
        inputstream = ((InputStream) (obj));
        if (obj == null)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "/".concat(s);
            } else
            {
                s = new String("/");
            }
            inputstream = net/fortuna/ical4j/util/ResourceLoader.getResourceAsStream(s);
        }
        return inputstream;
    }

}
