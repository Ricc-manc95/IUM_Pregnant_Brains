// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Map;

public final class Maps
{

    static boolean safeContainsKey(Map map, Object obj)
    {
        if (map == null)
        {
            throw new NullPointerException();
        }
        boolean flag = map.containsKey(obj);
        return flag;
        map;
_L2:
        return false;
        map;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static Object safeGet(Map map, Object obj)
    {
        if (map == null)
        {
            throw new NullPointerException();
        }
        map = ((Map) (map.get(obj)));
        return map;
        map;
_L2:
        return null;
        map;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static Object safeRemove(Map map, Object obj)
    {
        if (map == null)
        {
            throw new NullPointerException();
        }
        map = ((Map) (map.remove(obj)));
        return map;
        map;
_L2:
        return null;
        map;
        if (true) goto _L2; else goto _L1
_L1:
    }
}
