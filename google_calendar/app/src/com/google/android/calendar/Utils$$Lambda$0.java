// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.os.Bundle;

// Referenced classes of package com.google.android.calendar:
//            Utils

final class arg._cls7
    implements Runnable
{

    private final Context arg$1;
    private final Bundle arg$2;
    private final Bundle arg$3;
    private final Bundle arg$4;
    private final Bundle arg$5;
    private final Bundle arg$6;
    private final Bundle arg$7;

    public final void run()
    {
        Utils.lambda$schedulePeriodicSyncs$0$Utils(arg$1, arg$2, arg$3, arg$4, arg$5, arg$6, arg$7);
    }

    (Context context, Bundle bundle, Bundle bundle1, Bundle bundle2, Bundle bundle3, Bundle bundle4, Bundle bundle5)
    {
        arg$1 = context;
        arg$2 = bundle;
        arg$3 = bundle1;
        arg$4 = bundle2;
        arg$5 = bundle3;
        arg$6 = bundle4;
        arg$7 = bundle5;
    }
}
