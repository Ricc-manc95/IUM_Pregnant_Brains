// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import com.google.android.calendar.timely.rooms.data.Room;

public interface Y
{

    public abstract void onNextPageRequested();

    public abstract void onRoomRemoved(Room room);

    public abstract void onRoomSelected(Room room);
}
