// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.database;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Pair;
import com.google.android.apps.calendar.util.function.Consumer;

public final class val.receiver extends ContentObserver
{

    private final Consumer val$receiver;

    public final boolean deliverSelfNotifications()
    {
        return true;
    }

    public final void onChange(boolean flag)
    {
        val$receiver.accept(Pair.create(Boolean.valueOf(flag), null));
    }

    public final void onChange(boolean flag, Uri uri)
    {
        val$receiver.accept(Pair.create(Boolean.valueOf(flag), uri));
    }

    public (Consumer consumer)
    {
        val$receiver = consumer;
        super(final_handler);
    }
}
