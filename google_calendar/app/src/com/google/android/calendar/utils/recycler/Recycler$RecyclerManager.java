// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.recycler;


public interface I
{

    public abstract void clean(Object obj);

    public abstract Object createObject();

    public abstract void prepareToReuse(Object obj);
}
