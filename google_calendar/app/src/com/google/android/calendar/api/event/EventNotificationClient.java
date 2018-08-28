// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;

public interface EventNotificationClient
{

    public abstract ListenableFuture getNotifications(Iterable iterable, long l, long l1);

    public abstract void initialize(Context context);
}
