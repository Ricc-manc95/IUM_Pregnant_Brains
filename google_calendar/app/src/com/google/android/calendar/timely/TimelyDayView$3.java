// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.View;
import com.google.android.calendar.Utils;
import com.google.android.calendar.event.EditHelper;
import com.google.android.calendar.viewedit.callbacks.OnLaunchEdit;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelyDayView, TimelyDayHeaderView

final class this._cls0
    implements android.view.ener
{

    private final TimelyDayView this$0;

    public final void onClick(View view)
    {
        for (view = view.getContext(); !(view instanceof OnLaunchEdit) && (view instanceof ContextWrapper); view = ((ContextWrapper)view).getBaseContext()) { }
        if (view instanceof OnLaunchEdit)
        {
            Bundle bundle = Utils.getExtraEventBundle(EditHelper.constructDefaultStartTime(EditHelper.constructDefaultStartTimeWithoutCorrection(dayHeaderView.displayedTime, view)), null, false);
            bundle.putString("createEditSource", "grid");
            ((OnLaunchEdit)view).onLaunchInsertOrEdit(bundle);
        }
    }

    iew()
    {
        this$0 = TimelyDayView.this;
        super();
    }
}
