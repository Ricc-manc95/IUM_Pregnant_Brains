// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;

import android.os.Parcelable;

public abstract class RoomHierarchyNode
    implements Parcelable
{

    public static final RoomHierarchyNode DEFAULT = null;

    public RoomHierarchyNode()
    {
    }

    public abstract String getId();

    public abstract String getName();

    public abstract int getType();

}
