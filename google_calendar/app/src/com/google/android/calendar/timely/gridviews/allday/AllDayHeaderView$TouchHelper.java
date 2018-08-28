// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.utils.a11y.A11yHelper;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.gridviews.allday:
//            AllDayHeaderView, ExpandableChipColumnView

final class subtreeNotificationPending extends ExploreByTouchHelper
    implements Runnable
{

    private boolean subtreeNotificationPending;
    private final Rect tempRect = new Rect();
    private final Time tempTime = new Time("UTC");
    public final AllDayHeaderView this$0;

    private final View getChildByVirtualId(int i)
    {
        int j = (i & 0x3ff) - 1;
        boolean flag;
        if (j >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        View view = getChildAt(j);
        if (view == null)
        {
            LogUtils.i(AllDayHeaderView.TAG, "No child found for d:%d p:%d i:%d", new Object[] {
                Integer.valueOf(i >> 20), Integer.valueOf((0xfffff & i) >> 10), Integer.valueOf((i & 0x3ff) - 1)
            });
        }
        return view;
    }

    static int getChildIndex(int i)
    {
        return (i & 0x3ff) - 1;
    }

    private static CharSequence getContentDescription(View view)
    {
        if (view instanceof Chip)
        {
            return ((Chip)view).getContentDescription();
        }
        if (view != null && !TextUtils.isEmpty(view.getContentDescription()))
        {
            return view.getContentDescription();
        }
        if (view instanceof TextView)
        {
            return ((TextView)view).getText();
        } else
        {
            LogUtils.wtf(AllDayHeaderView.TAG, "Cannot determine content description for %s", new Object[] {
                view
            });
            return "";
        }
    }

    private final String getDayContentDescription(int i)
    {
        int j = firstJulianDay;
        tempTime.setJulianDaySafe((i >> 20) + j);
        Object obj = tempTime;
        ((Time) (obj)).writeFieldsToImpl();
        long l = ((Time) (obj)).impl.toMillis(true);
        obj = DateTimeFormatHelper.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        } else
        {
            return ((DateTimeFormatHelper)obj).getDateRangeText(l, l, 16);
        }
    }

    private final int getVirtualId(View view, int i)
    {
        AllDayHeaderView alldayheaderview;
        int j;
        boolean flag1 = false;
        boolean flag;
        if (!(view instanceof Chip))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        alldayheaderview = AllDayHeaderView.this;
        j = ((flag1) ? 1 : 0);
_L3:
        if (j >= ((ExpandableChipColumnView) (alldayheaderview)).columnCount)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        if (view != ((ExpandableChipColumnView) (alldayheaderview)).expandButtons[j]) goto _L2; else goto _L1
_L1:
        if (j >= 0)
        {
            return j << 20 | (i + 1 | 0x10000);
        } else
        {
            view = String.valueOf(view);
            throw new UnsupportedOperationException((new StringBuilder(String.valueOf(view).length() + 19)).append("Unsupported child: ").append(view).toString());
        }
_L2:
        j++;
          goto _L3
        j = -1;
          goto _L1
    }

    protected final int getVirtualViewAt(float f, float f1)
    {
        int j = getChildCount();
        for (int i = 0; i < j; i++)
        {
            Object obj = getChildAt(i);
            if (((View) (obj)).getVisibility() != 0)
            {
                continue;
            }
            ((View) (obj)).getHitRect(tempRect);
            if (!tempRect.contains((int)f, (int)f1))
            {
                continue;
            }
            if (obj instanceof Chip)
            {
                obj = (Chip)obj;
                int k = ((Chip) (obj)).partitionInfo.getStartDay();
                int l = firstJulianDay;
                return ((Chip) (obj)).partitionInfo.getPartition() << 10 | i + 1 | k - l << 20;
            } else
            {
                return getVirtualId(((View) (obj)), i);
            }
        }

        return -1;
    }

    protected final void getVisibleVirtualViews(List list)
    {
        getHitRect(tempRect);
        if (tempRect.isEmpty())
        {
            return;
        }
        int l = getChildCount();
        for (int i = 0; i < l; i++)
        {
            Object obj = getChildAt(i);
            if (((View) (obj)).getVisibility() != 0)
            {
                continue;
            }
            if (obj instanceof Chip)
            {
                obj = (Chip)obj;
                for (int k = ((Chip) (obj)).partitionInfo.getStartDay(); k <= ((Chip) (obj)).partitionInfo.getEndDay(); k++)
                {
                    list.add(Integer.valueOf(k - firstJulianDay << 20 | (i + 1 | ((Chip) (obj)).partitionInfo.getPartition() << 10)));
                }

            } else
            {
                list.add(Integer.valueOf(getVirtualId(((View) (obj)), i)));
            }
        }

        if (columnCount > 1)
        {
            for (int j = 0; j < columnCount; j++)
            {
                list.add(Integer.valueOf(j << 20));
            }

        }
        Collections.sort(list);
    }

    protected final boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
    {
        if (j == 16 && (i & 0x3ff) - 1 >= 0)
        {
            return getChildByVirtualId(i).performClick();
        } else
        {
            return false;
        }
    }

    protected final void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
    {
        if ((i & 0x3ff) - 1 >= 0)
        {
            View view = getChildByVirtualId(i);
            if (view != null)
            {
                accessibilityevent.setContentDescription(getContentDescription(view));
                return;
            } else
            {
                accessibilityevent.setContentDescription("");
                return;
            }
        } else
        {
            accessibilityevent.setContentDescription(getDayContentDescription(i));
            return;
        }
    }

    protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
label0:
        {
            Object obj;
            String s;
            if ((i & 0x3ff) - 1 < 0)
            {
                obj = getDayContentDescription(i);
                int j = columnLeftPositions[i >> 20];
                Rect rect = tempRect;
                AllDayHeaderView alldayheaderview = AllDayHeaderView.this;
                rect.set(j, 0, (int)((float)alldayheaderview.getMeasuredWidth() / (float)((ExpandableChipColumnView) (alldayheaderview)).columnCount) + j, getHeight());
            } else
            {
                obj = getChildByVirtualId(i);
                if (obj != null)
                {
                    ((View) (obj)).getHitRect(tempRect);
                    obj = getContentDescription(((View) (obj)));
                } else
                {
                    LogUtils.i(AllDayHeaderView.TAG, "onPopulateNodeForVirtualView for a missing node", new Object[0]);
                    tempRect.set(0, 0, 1, 1);
                    obj = "";
                }
            }
            if (obj != null)
            {
                s = ((String) (obj));
                if (!tempRect.isEmpty())
                {
                    break label0;
                }
            }
            LogUtils.wtf(AllDayHeaderView.TAG, "Incorrectly populated node (%s, %s) for d:%d p:%d i:%d", new Object[] {
                obj, tempRect, Integer.valueOf(i >> 20), Integer.valueOf((0xfffff & i) >> 10), Integer.valueOf((i & 0x3ff) - 1)
            });
            tempRect.set(0, 0, 1, 1);
            s = "";
        }
        accessibilitynodeinfocompat.mInfo.setContentDescription(s);
        obj = tempRect;
        accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
        accessibilitynodeinfocompat.mInfo.addAction(16);
    }

    final void postSubtreeChanged()
    {
        A11yHelper.getInstance();
        if (A11yHelper.isAccessibilityEnabled(getContext()) && !subtreeNotificationPending)
        {
            subtreeNotificationPending = true;
            post(this);
        }
    }

    public final void run()
    {
        subtreeNotificationPending = false;
        A11yHelper.getInstance();
        AllDayHeaderView alldayheaderview = AllDayHeaderView.this;
        if (A11yHelper.isAccessibilityEnabled(alldayheaderview.getContext()))
        {
            AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(2048);
            accessibilityevent.setContentChangeTypes(1);
            alldayheaderview.sendAccessibilityEventUnchecked(accessibilityevent);
        }
    }

    public (AllDayHeaderView alldayheaderview1)
    {
        this$0 = AllDayHeaderView.this;
        super(alldayheaderview1);
        subtreeNotificationPending = false;
    }
}
