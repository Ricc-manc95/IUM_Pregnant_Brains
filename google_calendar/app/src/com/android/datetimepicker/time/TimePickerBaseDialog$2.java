// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.view.View;
import com.android.datetimepicker.HapticFeedbackController;

// Referenced classes of package com.android.datetimepicker.time:
//            TimePickerBaseDialog

final class this._cls0
    implements android.view.PickerBaseDialog._cls2
{

    private final TimePickerBaseDialog this$0;

    public final void onClick(View view)
    {
        setCurrentItemShowing(1, true, false, true);
        hapticFeedbackController.tryVibrate();
    }

    ()
    {
        this$0 = TimePickerBaseDialog.this;
        super();
    }
}
