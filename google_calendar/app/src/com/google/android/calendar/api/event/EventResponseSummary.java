// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcelable;

public abstract class EventResponseSummary
    implements Parcelable
{

    public EventResponseSummary()
    {
    }

    public abstract int getNumAccepted();

    public abstract int getNumDeclined();

    public abstract int getNumNeedAction();

    public abstract int getNumTentative();
}
