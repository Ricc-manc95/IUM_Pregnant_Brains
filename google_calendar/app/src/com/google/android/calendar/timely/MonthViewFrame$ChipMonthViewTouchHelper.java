// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.calendar.Utils;
import com.google.android.calendar.alternatecalendar.AlternateCalendarUtils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.timely.settings.common.PreferencesConstants;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthViewFrame, CalendarMonthView

final class secondSharedRect extends com.android.datetimepicker.date.er
{

    private Rect secondSharedRect;
    private Rect sharedRect;
    private final MonthViewFrame this$0;

    private final Chip asVisibleChip(View view)
    {
        if (view.getVisibility() == 0 && (view instanceof Chip))
        {
            view.getHitRect(sharedRect);
            if (!sharedRect.isEmpty())
            {
                return (Chip)view;
            }
        }
        return null;
    }

    protected final CharSequence getDateDescription(int i)
    {
        StringBuilder stringbuilder = new StringBuilder(3);
        stringbuilder.append(super.scription(i));
        if (AlternateCalendarUtils.isAlternateCalendarEnabled(getContext()))
        {
            stringbuilder.append(", ");
            stringbuilder.append(AlternateCalendarUtils.getAlternateFullDate(Utils.getJulianDay(mYear, mMonth, i), getResources(), PreferencesConstants.getAlternateCalendarPref(getContext())));
        }
        return stringbuilder;
    }

    protected final int getVirtualViewAt(float f, float f1)
    {
        Object obj = getJulianDayFromLocation(f, f1);
        MonthViewFrame monthviewframe;
        int i;
        int j;
        int k;
        boolean flag;
        if (obj != null)
        {
            i = ((Integer) (obj)).intValue();
        } else
        {
            i = mFirstJulianDay;
        }
        obj = recycleTimeUtc;
        i = Integer.valueOf(i).intValue();
        j = mFirstJulianDay;
        monthviewframe = MonthViewFrame.this;
        k = monthviewframe.mFirstJulianDay;
        k = (monthviewframe.mNumCells + k) - 1;
        if (j <= k)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Preconditions.checkArgument(flag, "min (%s) must be less than or equal to max (%s)", j, k);
        ((Time) (obj)).setJulianDaySafe(Math.min(Math.max(i, j), k));
        return recycleTimeUtc.monthDay;
    }

    protected final void getVisibleVirtualViews(List list)
    {
        if (owner != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ArrayList arraylist;
        ArrayList arraylist1;
        Rect rect;
        Rect rect1;
        Rect rect2;
        int i;
        int l;
        int k1;
        int j = mFirstJulianDay;
        k1 = owner.getChildCount();
        arraylist1 = new ArrayList();
        arraylist = new ArrayList();
        for (i = 0; i < k1; i++)
        {
            Chip chip = asVisibleChip(owner.getChildAt(i));
            if (chip == null)
            {
                continue;
            }
            if (chip.partitionInfo.getStartDay() >= mFirstJulianDay)
            {
                break;
            }
            if (chip.partitionInfo.getEndDay() > j - 1)
            {
                arraylist1.add(chip);
            }
        }

        rect = new Rect();
        rect1 = new Rect();
        rect2 = new Rect();
        l = 1;
_L9:
        int k;
        int l1;
        if (l > mNumCells)
        {
            continue; /* Loop/switch isn't completed */
        }
        list.add(Integer.valueOf(MonthViewFrame.idFromIndex(0, l, 0)));
        unds(l, rect);
        l1 = arraylist1.size();
        boolean flag = false;
        k = i;
        i = ((flag) ? 1 : 0);
        do
        {
            if (k >= k1)
            {
                break;
            }
            Chip chip1 = asVisibleChip(owner.getChildAt(k));
            int i1 = i;
            if (chip1 != null)
            {
                chip1.getHitRect(rect1);
                if (!Rect.intersects(rect1, rect))
                {
                    break;
                }
                do
                {
                    if (i >= l1)
                    {
                        break;
                    }
                    Chip chip3 = (Chip)arraylist1.get(i);
                    chip3.getHitRect(rect2);
                    if (rect2.top > rect1.top)
                    {
                        break;
                    }
                    if (rect2.right > rect.right)
                    {
                        arraylist.add(chip3);
                    }
                    i1 = owner.indexOfChild(chip3);
                    if (i1 != -1)
                    {
                        i1 = MonthViewFrame.idFromIndex(1, i1, l);
                    } else
                    {
                        i1 = -1;
                    }
                    list.add(Integer.valueOf(i1));
                    i++;
                } while (true);
                if (rect1.right > rect.right)
                {
                    arraylist.add(chip1);
                }
                i1 = owner.indexOfChild(chip1);
                if (i1 != -1)
                {
                    i1 = MonthViewFrame.idFromIndex(1, i1, l);
                } else
                {
                    i1 = -1;
                }
                list.add(Integer.valueOf(i1));
                i1 = i;
            }
            k++;
            i = i1;
        } while (true);
          goto _L3
_L7:
        int j1 = -1;
_L5:
        Chip chip2;
        list.add(Integer.valueOf(j1));
        if (rect2.right > rect.right)
        {
            arraylist.add(chip2);
        }
        i++;
_L3:
label0:
        {
            if (i >= l1)
            {
                break label0;
            }
            chip2 = (Chip)arraylist1.get(i);
            chip2.getHitRect(rect2);
            j1 = owner.indexOfChild(chip2);
            if (j1 == -1)
            {
                break; /* Loop/switch isn't completed */
            }
            j1 = MonthViewFrame.idFromIndex(1, j1, l);
        }
        if (true) goto _L5; else goto _L4
_L4:
        if (true) goto _L7; else goto _L6
_L6:
        arraylist1.clear();
        if (numHiddenChips != null && numHiddenChips[(l - 1) + getFirstDayOffset()] > 0)
        {
            list.add(Integer.valueOf(MonthViewFrame.idFromIndex(2, l, 0)));
        }
        l++;
        i = k;
        ArrayList arraylist2 = arraylist;
        arraylist = arraylist1;
        arraylist1 = arraylist2;
        if (true) goto _L9; else goto _L8
_L8:
        if (true) goto _L1; else goto _L10
_L10:
    }

    protected final boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
    {
        return !super.ActionForVirtualView(i, j, bundle) && j == 16;
    }

    protected final void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
    {
        Chip chip = maybeGetChipById(i);
        if (chip != null)
        {
            accessibilityevent.setContentDescription(chip.getContentDescription());
            return;
        } else
        {
            super.eEventForVirtualView(i, accessibilityevent);
            return;
        }
    }

    protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        Object obj = maybeGetChipById(i);
        if (obj != null)
        {
            CharSequence charsequence = ((Chip) (obj)).getContentDescription();
            accessibilitynodeinfocompat.mInfo.setContentDescription(charsequence);
            ((Chip) (obj)).getHitRect(sharedRect);
            sharedRect.inset(-owner.chipHorizontalMargin, -owner.chipVerticalMargin);
            i = MonthViewFrame.auxIndexFromId(i);
            if (i > 0)
            {
                unds(i, secondSharedRect);
                sharedRect.intersect(secondSharedRect);
            }
            obj = sharedRect;
            accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
            accessibilitynodeinfocompat.mInfo.addAction(16);
            return;
        }
        if (MonthViewFrame.maybeIndexFromId(2, i) != -1)
        {
            i = MonthViewFrame.maybeIndexFromId(2, i);
            super.eNodeForVirtualView(MonthViewFrame.idFromIndex(0, i, 0), accessibilitynodeinfocompat);
            Object obj1 = sharedRect;
            accessibilitynodeinfocompat.mInfo.getBoundsInParent(((Rect) (obj1)));
            sharedRect.top = sharedRect.bottom - owner.eventChipHeight;
            obj1 = sharedRect;
            accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj1)));
            i = numHiddenChips[(i - 1) + getFirstDayOffset()];
            obj1 = resources.getQuantityString(0x7f120025, i, new Object[] {
                Integer.valueOf(i)
            });
            accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj1)));
            return;
        } else
        {
            super.eNodeForVirtualView(i, accessibilitynodeinfocompat);
            Rect rect = sharedRect;
            accessibilitynodeinfocompat.mInfo.getBoundsInParent(rect);
            sharedRect.inset(-owner.chipHorizontalMargin, -owner.chipVerticalMargin);
            rect = sharedRect;
            accessibilitynodeinfocompat.mInfo.setBoundsInParent(rect);
            return;
        }
    }

    public (View view)
    {
        this$0 = MonthViewFrame.this;
        super(MonthViewFrame.this, view);
        sharedRect = new Rect();
        secondSharedRect = new Rect();
    }
}
