// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;


// Referenced classes of package com.android.datetimepicker.date:
//            YearPickerView

final class val.offset
    implements Runnable
{

    private final YearPickerView this$0;
    private final int val$offset;
    private final int val$position;

    public final void run()
    {
        setSelectionFromTop(val$position, val$offset);
        requestLayout();
    }

    ()
    {
        this$0 = final_yearpickerview;
        val$position = i;
        val$offset = I.this;
        super();
    }
}
