// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.recycler;

import java.util.ArrayList;
import java.util.List;

public final class Recycler
{

    private final int maxCountOfObjects;
    private final List recycledObjects;
    public final RecyclerManager recyclerManager;

    public Recycler(RecyclerManager recyclermanager)
    {
        this(recyclermanager, -1);
    }

    public Recycler(RecyclerManager recyclermanager, int i)
    {
        recyclerManager = recyclermanager;
        recycledObjects = new ArrayList();
        maxCountOfObjects = i;
    }

    public final Object getOrCreateObject()
    {
        this;
        JVM INSTR monitorenter ;
        if (!recycledObjects.isEmpty()) goto _L2; else goto _L1
_L1:
        Object obj = recyclerManager.createObject();
_L4:
        this;
        JVM INSTR monitorexit ;
        return obj;
_L2:
        obj = recycledObjects.remove(recycledObjects.size() - 1);
        recyclerManager.prepareToReuse(obj);
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final void recycle(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        recyclerManager.clean(obj);
        if (maxCountOfObjects < 0 || recycledObjects.size() < maxCountOfObjects)
        {
            recycledObjects.add(obj);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    private class RecyclerManager
    {

        public abstract void clean(Object obj);

        public abstract Object createObject();

        public abstract void prepareToReuse(Object obj);
    }

}
