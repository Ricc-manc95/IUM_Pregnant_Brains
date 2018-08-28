// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.database;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import com.google.android.apps.calendar.util.concurrent.Subscription;

public final class arg._cls2
    implements Subscription
{

    private final Context arg$1;
    private final ContentObserver arg$2;

    public final void cancel(boolean flag)
    {
        Context context = arg$1;
        ContentObserver contentobserver = arg$2;
        context.getContentResolver().unregisterContentObserver(contentobserver);
    }

    public (Context context, ContentObserver contentobserver)
    {
        arg$1 = context;
        arg$2 = contentobserver;
    }
}
