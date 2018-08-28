// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.api;

import com.google.android.apps.calendar.util.gms.ListenableFuturePendingResult;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractTransformFuture;

public final class Future
    implements Supplier
{

    public static final Supplier $instance = new <init>();

    public final Object get()
    {
        Object obj = CalendarApi.Settings.list();
        com.google.common.base.Function function = .instance;
        if (obj instanceof ListenableFuturePendingResult)
        {
            obj = ((ListenableFuturePendingResult)obj).getFuture();
        } else
        {
            obj = new com.google.android.apps.calendar.util.gms.Future(((com.google.android.gms.common.api.PendingResult) (obj)));
        }
        return AbstractTransformFuture.create(((com.google.common.util.concurrent.ListenableFuture) (obj)), new com.google.android.apps.calendar.util.gms.it>(function), com.google.common.util.concurrent.utor.INSTANCE);
    }


    private Future()
    {
    }
}
