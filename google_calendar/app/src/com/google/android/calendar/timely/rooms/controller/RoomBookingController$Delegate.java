// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import java.util.List;

interface 
{

    public abstract boolean isOnline();

    public abstract void onConnectionError(Throwable throwable);

    public abstract void onFinish(List list);

    public abstract void onWindowStateChanged();
}
