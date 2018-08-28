// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.view.View;
import java.lang.ref.WeakReference;

// Referenced classes of package com.google.android.calendar:
//            SpeedDialLayout

final class arg._cls1
    implements android.view.yout..Lambda._cls1
{

    private final SpeedDialLayout arg$1;

    public final void onClick(View view)
    {
        view = arg$1;
        view.setExpanded(false, 300L);
        if (((SpeedDialLayout) (view)).speedDialActivity.get() != null)
        {
            ((ctivity)((SpeedDialLayout) (view)).speedDialActivity.get()).onCreateGrooveClicked();
        }
    }

    ctivity(SpeedDialLayout speeddiallayout)
    {
        arg$1 = speeddiallayout;
    }
}
