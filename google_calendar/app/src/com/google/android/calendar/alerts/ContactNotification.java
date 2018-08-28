// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.PendingIntent;

public interface ContactNotification
{

    public abstract PendingIntent createTrampolineIntent();

    public abstract int getIconResource();

    public abstract int getLabelResource();

    public abstract boolean isValid();

    public abstract void startActivity();
}
