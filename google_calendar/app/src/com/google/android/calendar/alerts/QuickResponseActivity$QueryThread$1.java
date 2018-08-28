// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.widget.Toast;

// Referenced classes of package com.google.android.calendar.alerts:
//            QuickResponseActivity

final class this._cls1
    implements Runnable
{

    private final is._cls0 this$1;

    public final void run()
    {
        Toast.makeText(_fld0, 0x7f1303cc, 1).show();
        finish();
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
