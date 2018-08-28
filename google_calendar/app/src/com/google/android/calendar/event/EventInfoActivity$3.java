// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.common.view.overlay.OverlayFragment;

// Referenced classes of package com.google.android.calendar.event:
//            EventInfoActivity

final class yFragment
    implements Runnable
{

    private final EventInfoActivity this$0;
    private final OverlayFragment val$fragment;

    public final void run()
    {
        finishDismissOverlay(val$fragment);
    }

    yFragment()
    {
        this$0 = final_eventinfoactivity;
        val$fragment = OverlayFragment.this;
        super();
    }
}
