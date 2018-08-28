// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import java.util.HashMap;
import java.util.Map;

public final class permanentMap
{

    private Map permanentMap;
    private Map temporaryMap;

    public final void clearTemporaryValues()
    {
        this;
        JVM INSTR monitorenter ;
        temporaryMap.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final Map getKeysAndValues()
    {
        this;
        JVM INSTR monitorenter ;
        HashMap hashmap;
        hashmap = new HashMap(permanentMap);
        hashmap.putAll(temporaryMap);
        this;
        JVM INSTR monitorexit ;
        return hashmap;
        Exception exception;
        exception;
        throw exception;
    }

    public final void set(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        permanentMap.put(s, s1);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public final void setAll(Map map, Boolean boolean1)
    {
        this;
        JVM INSTR monitorenter ;
        if (!boolean1.booleanValue()) goto _L2; else goto _L1
_L1:
        temporaryMap.putAll(map);
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        permanentMap.putAll(map);
        if (true) goto _L4; else goto _L3
_L3:
        map;
        throw map;
    }

    public final void setForNextHit(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        temporaryMap.put(s, s1);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    ()
    {
        temporaryMap = new HashMap();
        permanentMap = new HashMap();
    }
}
