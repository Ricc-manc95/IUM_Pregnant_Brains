// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.content.Context;

// Referenced classes of package com.google.android.calendar.alerts:
//            AlertActionIntentBuilder

public final class AlertBuilder
{

    public final Context context;
    public final AlertActionIntentBuilder intentBuilder;

    public AlertBuilder(Context context1)
    {
        context = context1;
        intentBuilder = new AlertActionIntentBuilder(context1);
    }
}
