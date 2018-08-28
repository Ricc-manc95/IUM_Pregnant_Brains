// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;


public abstract class VersionedItem
{

    public VersionedItem()
    {
    }

    public abstract Object getItem();

    public abstract int getVersion();
}
