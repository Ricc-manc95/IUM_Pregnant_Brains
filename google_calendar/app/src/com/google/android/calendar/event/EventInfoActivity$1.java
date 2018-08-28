// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.newapi.screen.AbstractEventViewScreenController;
import com.google.android.calendar.utils.ActivitySingletonCache;

// Referenced classes of package com.google.android.calendar.event:
//            EventInfoActivity

final class this._cls0 extends ContentObserver
{

    private final EventInfoActivity this$0;

    public final boolean deliverSelfNotifications()
    {
        return true;
    }

    public final void onChange(boolean flag)
    {
        Object obj = EventInfoActivity.this;
        obj = (CalendarController)CalendarController.instances.getInstance(((android.app.Activity) (obj)));
        EventInfoActivity eventinfoactivity = EventInfoActivity.this;
        ((CalendarController) (obj)).executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(new com.google.android.calendar.ommand(128L));
    }

    public final void onChange(boolean flag, Uri uri)
    {
        onChange(flag);
        AbstractEventViewScreenController.notifyContentProviderUpdateIfAvailable(mFragments.mHost.mFragmentManager, uri);
    }

    tViewScreenController(Handler handler)
    {
        this$0 = EventInfoActivity.this;
        super(handler);
    }
}
