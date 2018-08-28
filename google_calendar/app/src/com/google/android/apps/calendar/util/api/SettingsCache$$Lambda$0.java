// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.api;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.common.base.Function;
import java.util.Arrays;

public final class arg._cls3
    implements Function
{

    private final Context arg$1;
    private final Uri arg$2;
    private final Function arg$3;

    public final Object apply(Object obj)
    {
        Context context = arg$1;
        Uri uri = arg$2;
        Function function = arg$3;
        obj = (Runnable)obj;
        Object obj1 = new com.google.android.apps.calendar.util.function.>(((Runnable) (obj)));
        obj1 = new com.google.android.apps.calendar.util.database.>(new Handler(Looper.getMainLooper()), ((com.google.android.apps.calendar.util.function.Consumer) (obj1)));
        context.getContentResolver().registerContentObserver(uri, true, ((android.database.ContentObserver) (obj1)));
        return new com.google.android.apps.calendar.util.concurrent.init>(Arrays.asList(new Subscription[] {
            new com.google.android.apps.calendar.util.database.._cls0(context, ((android.database.ContentObserver) (obj1))), (Subscription)function.apply(new com.google.android.apps.calendar.util.function.>(((Runnable) (obj))))
        }));
    }

    public ambda._cls0(Context context, Uri uri, Function function)
    {
        arg$1 = context;
        arg$2 = uri;
        arg$3 = function;
    }
}
