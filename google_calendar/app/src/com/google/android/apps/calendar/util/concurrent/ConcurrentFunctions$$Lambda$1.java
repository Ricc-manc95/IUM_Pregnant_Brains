// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.concurrent;

import java.util.Map;

final class arg._cls2
    implements Runnable
{

    private final Map arg$1;
    private final Object arg$2;

    public final void run()
    {
        Map map = arg$1;
        Object obj = arg$2;
        map;
        JVM INSTR monitorenter ;
        map.remove(obj);
        map;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    (Map map, Object obj)
    {
        arg$1 = map;
        arg$2 = obj;
    }
}
