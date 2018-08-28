// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.DateTimeZone;

// Referenced classes of package org.joda.time.tz:
//            Provider, DateTimeZoneBuilder

public final class ZoneInfoProvider
    implements Provider
{

    private final File iFileDir;
    private final ClassLoader iLoader;
    private final String iResourcePath;
    private final Map iZoneInfoMap;

    public ZoneInfoProvider(String s)
        throws IOException
    {
        this(s, null, false);
    }

    private ZoneInfoProvider(String s, ClassLoader classloader, boolean flag)
        throws IOException
    {
        if (s == null)
        {
            throw new IllegalArgumentException("No resource path provided");
        }
        classloader = s;
        if (!s.endsWith("/"))
        {
            s = String.valueOf(s);
            classloader = (new StringBuilder(String.valueOf(s).length() + 1)).append(s).append('/').toString();
        }
        iFileDir = null;
        iResourcePath = classloader;
        iLoader = getClass().getClassLoader();
        iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
    }

    private final DateTimeZone loadZoneData(String s)
    {
        InputStream inputstream1 = openResource(s);
        InputStream inputstream = inputstream1;
        if (!(inputstream1 instanceof DataInput)) goto _L2; else goto _L1
_L1:
        inputstream = inputstream1;
        Object obj = DateTimeZoneBuilder.readFrom((DataInput)inputstream1, s);
_L4:
        inputstream = inputstream1;
        iZoneInfoMap.put(s, new SoftReference(obj));
        Thread thread;
        if (inputstream1 != null)
        {
            try
            {
                inputstream1.close();
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return ((DateTimeZone) (obj));
            }
        }
        return ((DateTimeZone) (obj));
_L2:
        inputstream = inputstream1;
        obj = DateTimeZoneBuilder.readFrom(new DataInputStream(inputstream1), s);
        if (true) goto _L4; else goto _L3
_L3:
        obj;
        inputstream1 = null;
_L8:
        inputstream = inputstream1;
        thread = Thread.currentThread();
        inputstream = inputstream1;
        thread.getThreadGroup().uncaughtException(thread, ((Throwable) (obj)));
        inputstream = inputstream1;
        iZoneInfoMap.remove(s);
        if (inputstream1 != null)
        {
            try
            {
                inputstream1.close();
            }
            // Misplaced declaration of an exception variable
            catch (String s) { }
        }
        return null;
        s;
        inputstream = null;
_L6:
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            catch (IOException ioexception) { }
        }
        throw s;
        s;
        if (true) goto _L6; else goto _L5
_L5:
        obj;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private static Map loadZoneInfoMap(InputStream inputstream)
        throws IOException
    {
        Object obj;
        boolean flag;
        flag = false;
        obj = new ConcurrentHashMap();
        inputstream = new DataInputStream(inputstream);
        String as[];
        int j;
        j = inputstream.readUnsignedShort();
        as = new String[j];
        int i = 0;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        as[i] = inputstream.readUTF().intern();
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        j = inputstream.readUnsignedShort();
        i = ((flag) ? 1 : 0);
_L4:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        ((Map) (obj)).put(as[inputstream.readUnsignedShort()], as[inputstream.readUnsignedShort()]);
        i++;
        if (true) goto _L4; else goto _L3
        obj;
        throw new IOException("Corrupt zone info map");
        obj;
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream) { }
        throw obj;
_L3:
        try
        {
            inputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (InputStream inputstream) { }
        ((Map) (obj)).put("UTC", new SoftReference(DateTimeZone.UTC));
        return ((Map) (obj));
    }

    private final InputStream openResource(String s)
        throws IOException
    {
        Object obj = iResourcePath.concat(s);
        if (iLoader != null)
        {
            s = iLoader.getResourceAsStream(((String) (obj)));
        } else
        {
            s = ClassLoader.getSystemResourceAsStream(((String) (obj)));
        }
        if (s == null)
        {
            obj = (new StringBuilder(40)).append("Resource not found: \"").append(((String) (obj))).append("\" ClassLoader: ");
            if (iLoader != null)
            {
                s = iLoader.toString();
            } else
            {
                s = "system";
            }
            throw new IOException(((StringBuilder) (obj)).append(s).toString());
        } else
        {
            return s;
        }
    }

    public final Set getAvailableIDs()
    {
        return new TreeSet(iZoneInfoMap.keySet());
    }

    public final DateTimeZone getZone(String s)
    {
_L6:
        if (s != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        return ((DateTimeZone) (obj));
_L2:
        obj = iZoneInfoMap.get(s);
        if (obj == null)
        {
            return null;
        }
        if (s.equals(obj))
        {
            return loadZoneData(s);
        }
        if (!(obj instanceof SoftReference))
        {
            break; /* Loop/switch isn't completed */
        }
        DateTimeZone datetimezone = (DateTimeZone)((SoftReference)obj).get();
        obj = datetimezone;
        if (datetimezone == null)
        {
            return loadZoneData(s);
        }
        if (true) goto _L4; else goto _L3
_L3:
        s = (String)obj;
        if (true) goto _L6; else goto _L5
_L5:
    }
}
