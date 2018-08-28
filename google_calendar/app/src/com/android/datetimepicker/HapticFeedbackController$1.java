// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

// Referenced classes of package com.android.datetimepicker:
//            HapticFeedbackController

final class this._cls0 extends ContentObserver
{

    private final HapticFeedbackController this$0;

    public final void onChange(boolean flag)
    {
        flag = true;
        HapticFeedbackController hapticfeedbackcontroller = HapticFeedbackController.this;
        if (android.provider.troller(context.getContentResolver(), "haptic_feedback_enabled", 0) != 1)
        {
            flag = false;
        }
        hapticfeedbackcontroller.isGloballyEnabled = flag;
    }

    (Handler handler)
    {
        this$0 = HapticFeedbackController.this;
        super(null);
    }
}
