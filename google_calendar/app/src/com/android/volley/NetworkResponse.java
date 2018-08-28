// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// Referenced classes of package com.android.volley:
//            Header

public final class NetworkResponse
{

    public final List allHeaders;
    public final byte data[];
    public final Map headers;
    public final boolean notModified;

    private NetworkResponse(int i, byte abyte0[], Map map, List list, boolean flag, long l)
    {
        data = abyte0;
        headers = map;
        if (list == null)
        {
            allHeaders = null;
        } else
        {
            allHeaders = Collections.unmodifiableList(list);
        }
        notModified = flag;
    }

    private NetworkResponse(int i, byte abyte0[], Map map, boolean flag, long l)
    {
        if (map != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        this(200, abyte0, map, ((List) (obj)), false, 0L);
        return;
_L2:
        if (!map.isEmpty())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = Collections.emptyList();
        if (true) goto _L4; else goto _L3
_L3:
        ArrayList arraylist = new ArrayList(map.size());
        Iterator iterator = map.entrySet().iterator();
        do
        {
            obj = arraylist;
            if (!iterator.hasNext())
            {
                continue;
            }
            obj = (java.util.Map.Entry)iterator.next();
            arraylist.add(new Header((String)((java.util.Map.Entry) (obj)).getKey(), (String)((java.util.Map.Entry) (obj)).getValue()));
        } while (true);
        if (true) goto _L4; else goto _L5
_L5:
    }

    public NetworkResponse(int i, byte abyte0[], boolean flag, long l, List list)
    {
        if (list != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L4:
        this(i, abyte0, ((Map) (obj)), list, flag, l);
        return;
_L2:
        if (!list.isEmpty())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = Collections.emptyMap();
        if (true) goto _L4; else goto _L3
_L3:
        TreeMap treemap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        Iterator iterator = list.iterator();
        do
        {
            obj = treemap;
            if (!iterator.hasNext())
            {
                continue;
            }
            obj = (Header)iterator.next();
            treemap.put(((Header) (obj)).mName, ((Header) (obj)).mValue);
        } while (true);
        if (true) goto _L4; else goto _L5
_L5:
    }

    public NetworkResponse(byte abyte0[])
    {
        this(200, abyte0, false, 0L, Collections.emptyList());
    }

    public NetworkResponse(byte abyte0[], Map map)
    {
        this(200, abyte0, map, false, 0L);
    }
}
