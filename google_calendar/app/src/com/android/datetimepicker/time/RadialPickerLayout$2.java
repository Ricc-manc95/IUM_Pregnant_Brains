// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;


// Referenced classes of package com.android.datetimepicker.time:
//            RadialPickerLayout

final class val.isInnerCircle
    implements Runnable
{

    private final RadialPickerLayout this$0;
    private final Boolean val$isInnerCircle[];

    public final void run()
    {
        doingMove = true;
        int i = reselectSelector(downDegrees, val$isInnerCircle[0].booleanValue(), false, true);
        lastValueSelected = i;
        listener.onValueSelected(getCurrentItemShowing(), i, false);
    }

    ValueSelectedListener()
    {
        this$0 = final_radialpickerlayout;
        val$isInnerCircle = _5B_Ljava.lang.Boolean_3B_.this;
        super();
    }
}
