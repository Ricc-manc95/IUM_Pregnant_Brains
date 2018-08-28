// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.support.v7.app.AppCompatActivity;

// Referenced classes of package com.google.android.calendar:
//            Utils, AllInOneCalendarActivity

final class this._cls0
    implements Runnable
{

    private final AllInOneCalendarActivity this$0;

    public final void run()
    {
        timeZone = Utils.getTimeZoneId(AllInOneCalendarActivity.this);
        invalidateOptionsMenu();
        Utils.setMidnightUpdater(handler, timeChangesUpdater, timeZone);
    }

    ()
    {
        this$0 = AllInOneCalendarActivity.this;
        super();
    }
}
