// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.support.v4.app.DialogFragment;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.common.view.overlay.OverlayFragment;

// Referenced classes of package com.google.android.calendar.ical:
//            NewICalActivity

final class arg._cls2
    implements Runnable
{

    private final NewICalActivity arg$1;
    private final DialogFragment arg$2;

    public final void run()
    {
        arg$1.finishDismissOverlay((OverlayFragment)arg$2);
    }

    ment(NewICalActivity newicalactivity, DialogFragment dialogfragment)
    {
        arg$1 = newicalactivity;
        arg$2 = dialogfragment;
    }
}
