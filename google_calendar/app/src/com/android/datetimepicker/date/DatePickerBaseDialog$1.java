// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.view.View;
import com.android.datetimepicker.HapticFeedbackController;

// Referenced classes of package com.android.datetimepicker.date:
//            DatePickerBaseDialog

final class this._cls0
    implements android.view.PickerBaseDialog._cls1
{

    private final DatePickerBaseDialog this$0;

    public final void onClick(View view)
    {
        hapticFeedbackController.tryVibrate();
        dialog.onDateSet(calendar);
        dialog.dismiss();
    }

    smissibleDialog()
    {
        this$0 = DatePickerBaseDialog.this;
        super();
    }
}
