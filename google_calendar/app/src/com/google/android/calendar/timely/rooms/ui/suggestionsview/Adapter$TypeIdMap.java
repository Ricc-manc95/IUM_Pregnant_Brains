// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

final class 
{

    private int nextViewType;
    private final BiMap viewTypes = new HashBiMap(16);

    public final Class getClass(int i)
    {
        this;
        JVM INSTR monitorenter ;
        Class class1 = (Class)viewTypes.inverse().get(Integer.valueOf(i));
        this;
        JVM INSTR monitorexit ;
        return class1;
        Exception exception;
        exception;
        throw exception;
    }

    public final int getId(Class class1)
    {
        this;
        JVM INSTR monitorenter ;
        Integer integer1 = (Integer)viewTypes.get(class1);
        Integer integer;
        integer = integer1;
        if (integer1 != null)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        int i = nextViewType;
        nextViewType = i + 1;
        integer = Integer.valueOf(i);
        viewTypes.put(class1, integer);
        int j = integer.intValue();
        this;
        JVM INSTR monitorexit ;
        return j;
        class1;
        throw class1;
    }

    ()
    {
    }
}
