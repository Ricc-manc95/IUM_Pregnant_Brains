// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Multimap
{

    public abstract Map asMap();

    public abstract void clear();

    public abstract boolean containsEntry(Object obj, Object obj1);

    public abstract Collection get(Object obj);

    public abstract Set keySet();

    public abstract boolean put(Object obj, Object obj1);

    public abstract boolean remove(Object obj, Object obj1);

    public abstract int size();
}
