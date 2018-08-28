// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.support.v4.widget.ExploreByTouchHelper;

// Referenced classes of package com.android.datetimepicker.date:
//            MonthView, DatePickerController

final class arg._cls1
    implements arg._cls1
{

    private final arg._cls1 arg$1;

    public final void invoke(arg._cls1 _pcls1)
    {
        MonthView monthview;
        boolean flag1;
        flag1 = false;
        monthview = arg$1._fld1;
        if (monthview.mController == null) goto _L2; else goto _L1
_L1:
        java.util.Calendar calendar = monthview.mController.getMinDate();
        if (calendar == null || _pcls1._mth1(new arg._cls1(calendar)) >= 0) goto _L2; else goto _L3
_L3:
        boolean flag = true;
_L7:
        if (flag) goto _L5; else goto _L4
_L4:
        if (monthview.mController == null)
        {
            break MISSING_BLOCK_LABEL_145;
        }
        java.util.Calendar calendar1 = monthview.mController.getMaxDate();
        if (calendar1 == null || _pcls1._mth1(new arg._cls1(calendar1)) <= 0)
        {
            break MISSING_BLOCK_LABEL_145;
        }
        flag = true;
_L8:
        if (!flag) goto _L6; else goto _L5
_L5:
        flag1 = true;
_L6:
        if (!flag1)
        {
            if (monthview.mOnDayClickListener != null)
            {
                monthview.mOnDayClickListener._mth1(monthview, _pcls1);
            }
            monthview.mTouchHelper.sendEventForVirtualView(_pcls1._fld1, 1);
        }
        return;
_L2:
        flag = false;
          goto _L7
        flag = false;
          goto _L8
    }

    ( )
    {
        arg$1 = ;
    }
}
