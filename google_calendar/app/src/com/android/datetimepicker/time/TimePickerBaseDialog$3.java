// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.view.View;
import com.android.datetimepicker.HapticFeedbackController;

// Referenced classes of package com.android.datetimepicker.time:
//            TimePickerBaseDialog, RadialPickerLayout

final class this._cls0
    implements android.view.PickerBaseDialog._cls3
{

    private final TimePickerBaseDialog this$0;

    public final void onClick(View view)
    {
        if (inKbMode && isTypedTimeFullyLegal())
        {
            finishKbMode(false);
        } else
        {
            hapticFeedbackController.tryVibrate();
        }
        if (callback != null)
        {
            callback._mth51666RRD5TGMSP3IDTKM8BR4C5Q6AT39DLIN0QB3DDIN4BRKD5MMABQIC5I6IOBCA1KM6QR5E9662UBFELQ3MIA955B0____0(timePicker.currentHoursOfDay, timePicker.currentMinutes);
        }
        picker.dismiss();
    }

    smissiblePicker()
    {
        this$0 = TimePickerBaseDialog.this;
        super();
    }
}
