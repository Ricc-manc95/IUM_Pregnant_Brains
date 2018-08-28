// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;


// Referenced classes of package com.android.datetimepicker.date:
//            DayPickerView

final class val.position
    implements Runnable
{

    private final DayPickerView this$0;
    private final int val$position;

    public final void run()
    {
        setSelection(val$position);
    }

    ()
    {
        this$0 = final_daypickerview;
        val$position = I.this;
        super();
    }
}
