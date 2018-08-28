// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;


// Referenced classes of package com.android.datetimepicker.time:
//            RadialPickerLayout, AmPmCirclesView

final class this._cls0
    implements Runnable
{

    private final RadialPickerLayout this$0;

    public final void run()
    {
        amPmCirclesView.setAmOrPmPressed(isTouchingAmOrPm);
        amPmCirclesView.invalidate();
    }

    ()
    {
        this$0 = RadialPickerLayout.this;
        super();
    }
}
