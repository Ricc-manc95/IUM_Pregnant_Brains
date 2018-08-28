// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.api;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.common.base.Function;
import java.util.Arrays;

public final class arg._cls4
    implements Function
{

    private final Context arg$1;
    private final Function arg$2;
    private final Function arg$3;
    private final Function arg$4;

    public final Object apply(Object obj)
    {
        Context context = arg$1;
        Function function = arg$2;
        Function function1 = arg$3;
        Function function2 = arg$4;
        obj = (Runnable)obj;
        android.net.Uri uri = android.provider.T_URI;
        Object obj1 = new com.google.android.apps.calendar.util.function.(((Runnable) (obj)));
        obj1 = new com.google.android.apps.calendar.util.database.(new Handler(Looper.getMainLooper()), ((com.google.android.apps.calendar.util.function.Consumer) (obj1)));
        context.getContentResolver().registerContentObserver(uri, true, ((android.database.ContentObserver) (obj1)));
        return new com.google.android.apps.calendar.util.concurrent.tia_01_(Arrays.asList(new Subscription[] {
            new com.google.android.apps.calendar.util.database.(context, ((android.database.ContentObserver) (obj1))), (Subscription)function.apply(new com.google.android.apps.calendar.util.function.(((Runnable) (obj)))), (Subscription)function1.apply(new com.google.android.apps.calendar.util.function.(((Runnable) (obj)))), (Subscription)function2.apply(new com.google.android.apps.calendar.util.function.(((Runnable) (obj))))
        }));
    }

    public (Context context, Function function, Function function1, Function function2)
    {
        arg$1 = context;
        arg$2 = function;
        arg$3 = function1;
        arg$4 = function2;
    }
}
