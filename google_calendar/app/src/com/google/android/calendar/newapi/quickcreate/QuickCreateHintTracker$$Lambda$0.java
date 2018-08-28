// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.content.Context;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            QuickCreateHintTracker

final class arg._cls2
    implements Runnable
{

    private final QuickCreateHintTracker arg$1;
    private final Context arg$2;

    public final void run()
    {
        QuickCreateHintTracker.lambda$saveToSharedPreferences$0$QuickCreateHintTracker(arg$1, arg$2);
    }

    (QuickCreateHintTracker quickcreatehinttracker, Context context)
    {
        arg$1 = quickcreatehinttracker;
        arg$2 = context;
    }
}
