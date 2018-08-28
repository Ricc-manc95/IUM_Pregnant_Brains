// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.format.DateFormat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Calendar;
import java.util.List;

// Referenced classes of package com.android.datetimepicker.date:
//            MonthView, DatePickerController

public class this._cls0 extends ExploreByTouchHelper
{

    private final Calendar tempCalendar = Calendar.getInstance();
    private final Rect tempRect = new Rect();
    public final MonthView this$0;

    public CharSequence getDateDescription(int i)
    {
        tempCalendar.set(mYear, mMonth, i);
        return DateFormat.format("dd MMMM yyyy", tempCalendar.getTimeInMillis());
    }

    public void getItemBounds(int i, Rect rect)
    {
        int i1 = getInternalStartPadding();
        int l = getMonthHeaderSize();
        int j = mRowHeight;
        int k = (mWidth - getEdgePadding()) / mNumDays;
        int j1 = (i - 1) + findDayOffset();
        i = j1 / mNumDays;
        i1 += getRtlCompatibleColumnIndex(j1 % mNumDays) * k;
        i = l + i * j;
        rect.set(i1, i, k + i1, j + i);
    }

    public CharSequence getItemDescription(int i)
    {
        CharSequence charsequence = getDateDescription(i);
        Object obj = charsequence;
        if (i == mSelectedDay)
        {
            obj = getContext().getString(0x7f130311, new Object[] {
                charsequence
            });
        }
        return ((CharSequence) (obj));
    }

    public int getVirtualViewAt(float f, float f1)
    {
        getDateDescription getdatedescription = getDayFromLocation(f, f1);
        if (getdatedescription != null)
        {
            return getdatedescription.getDateDescription;
        } else
        {
            return 0x80000000;
        }
    }

    public void getVisibleVirtualViews(List list)
    {
        for (int i = 1; i <= mNumCells; i++)
        {
            list.add(Integer.valueOf(i));
        }

    }

    public boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = true;
        j;
        JVM INSTR lookupswitch 2: default 32
    //                   16: 38
    //                   32: 213;
           goto _L1 _L2 _L3
_L1:
        flag1 = false;
_L13:
        return flag1;
_L2:
        getDateDescription getdatedescription;
        bundle = MonthView.this;
        getdatedescription = new (mYear, mMonth, i);
        if (((MonthView) (bundle)).mController == null) goto _L5; else goto _L4
_L4:
        Calendar calendar = ((MonthView) (bundle)).mController.getMinDate();
        if (calendar == null || getdatedescription.eTo(new (calendar)) >= 0) goto _L5; else goto _L6
_L6:
        i = 1;
_L14:
        if (i != 0) goto _L8; else goto _L7
_L7:
        if (((MonthView) (bundle)).mController == null) goto _L10; else goto _L9
_L9:
        calendar = ((MonthView) (bundle)).mController.getMaxDate();
        if (calendar == null || getdatedescription.eTo(new (calendar)) <= 0) goto _L10; else goto _L11
_L11:
        i = 1;
_L15:
        j = ((flag) ? 1 : 0);
        if (i == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
_L8:
        j = 1;
        if (j != 0) goto _L13; else goto _L12
_L12:
        if (((MonthView) (bundle)).mOnDayClickListener != null)
        {
            ((MonthView) (bundle)).mOnDayClickListener.DayClick(bundle, getdatedescription);
        }
        ((MonthView) (bundle)).mTouchHelper.sendEventForVirtualView(getdatedescription.ualView, 1);
        return true;
_L5:
        i = 0;
          goto _L14
_L10:
        i = 0;
          goto _L15
_L3:
        onDayLongClick(new (mYear, mMonth, i));
        return true;
          goto _L14
    }

    public void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
    {
        accessibilityevent.setContentDescription(getItemDescription(i));
    }

    public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        getItemBounds(i, tempRect);
        Object obj = getItemDescription(i);
        accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj)));
        obj = tempRect;
        accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
        accessibilitynodeinfocompat.mInfo.addAction(16);
        accessibilitynodeinfocompat.mInfo.addAction(32);
        if (i == mSelectedDay)
        {
            accessibilitynodeinfocompat.mInfo.setSelected(true);
        }
    }

    public at(View view)
    {
        this$0 = MonthView.this;
        super(view);
    }
}
