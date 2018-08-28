// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.view.View;
import com.android.datetimepicker.HapticFeedbackController;

// Referenced classes of package com.android.datetimepicker.time:
//            TimePickerBaseDialog, RadialPickerLayout

final class this._cls0
    implements android.view.PickerBaseDialog._cls4
{

    private final TimePickerBaseDialog this$0;

    public final void onClick(View view)
    {
        int i;
        boolean flag = true;
        hapticFeedbackController.tryVibrate();
        view = timePicker;
        if (((RadialPickerLayout) (view)).currentHoursOfDay < 12)
        {
            i = 0;
        } else
        if (((RadialPickerLayout) (view)).currentHoursOfDay < 24)
        {
            i = 1;
        } else
        {
            i = -1;
        }
        if (i != 0) goto _L2; else goto _L1
_L1:
        i = ((flag) ? 1 : 0);
_L4:
        updateAmPmDisplay(i);
        timePicker.setAmOrPm(i);
        return;
_L2:
        if (i == 1)
        {
            i = 0;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    ()
    {
        this$0 = TimePickerBaseDialog.this;
        super();
    }
}
