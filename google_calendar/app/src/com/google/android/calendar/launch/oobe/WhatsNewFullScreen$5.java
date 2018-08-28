// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import com.google.android.calendar.utils.viewpager.LayoutDirectionAwareViewPager;

// Referenced classes of package com.google.android.calendar.launch.oobe:
//            WhatsNewFullScreen

final class this._cls0
    implements Runnable
{

    private final WhatsNewFullScreen this$0;

    public final void run()
    {
        if (viewPager.hasFocus())
        {
            viewPager.clearFocus();
        }
        viewPager.requestFocus();
    }

    areViewPager()
    {
        this$0 = WhatsNewFullScreen.this;
        super();
    }
}
