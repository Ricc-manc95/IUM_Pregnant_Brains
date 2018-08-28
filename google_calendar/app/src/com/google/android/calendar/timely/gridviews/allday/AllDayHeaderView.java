// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.geometry.GridPartitionItemGeometry;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.timely.geometry.SimplePartitionItem;
import com.google.android.calendar.utils.NumberUtils;
import com.google.android.calendar.utils.a11y.A11yHelper;
import com.google.android.calendar.utils.recycler.Recycler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.timely.gridviews.allday:
//            ExpandableChipColumnView, StickyAllDayManager

public class AllDayHeaderView extends ExpandableChipColumnView
{
    final class TouchHelper extends ExploreByTouchHelper
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

        public TouchHelper(AllDayHeaderView alldayheaderview1)
        {
            this$0 = AllDayHeaderView.this;
            super(alldayheaderview1);
            subtreeNotificationPending = false;
        }
    }


    private static final Comparator REGISTRY_ALL_DAY_COMPARATOR;
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/gridviews/allday/AllDayHeaderView);
    private final com.google.android.calendar.timeline.chip.Chip.ChipActionListener chipActionListener;
    private final GridPartitionItemGeometry chipGeometry;
    private int expandingDay;
    public int firstJulianDay;
    public StickyAllDayManager stateManager;
    private final TouchHelper touchHelper;

    public AllDayHeaderView(Context context)
    {
        this(context, null);
    }

    public AllDayHeaderView(Context context, AttributeSet attributeset)
    {
        int i1 = 0;
        Resources resources = context.getResources();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.google.android.calendar.R.styleable.AllDayHeaderView);
        int i;
        int j;
        int k;
        int l;
        int j1;
        int k1;
        boolean flag;
        if (typedarray == null)
        {
            j = 1;
        } else
        {
            j = typedarray.getInteger(com.google.android.calendar.R.styleable.AllDayHeaderView_count_days, 1);
            typedarray.recycle();
        }
        flag = context.getResources().getBoolean(0x7f0c0016);
        if (j == 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 && !flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!flag && j == 7 && resources.getConfiguration().orientation == 1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            j = 0x7f120046;
        } else
        {
            j = 0x7f120025;
        }
        if (i != 0)
        {
            k = resources.getDimensionPixelOffset(0x7f0e0309);
        } else
        {
            k = 0;
        }
        j1 = resources.getDimensionPixelSize(0x7f0e01d0);
        if (i != 0)
        {
            l = 0x7f0e0308;
        } else
        {
            l = 0x7f0e01cf;
        }
        k1 = resources.getDimensionPixelSize(l);
        if (i != 0)
        {
            l = i1;
        } else
        {
            l = resources.getDimensionPixelOffset(0x7f0e0050);
        }
        if (i != 0)
        {
            i1 = 0x7f0e0360;
        } else
        {
            i1 = 0x7f0e0095;
        }
        i1 = resources.getDimensionPixelSize(i1);
        if (i != 0)
        {
            i = 0x7f0e039f;
        } else
        {
            i = 0x7f0e0438;
        }
        super(context, attributeset, new ExpandableChipColumnView.Config(j, k, j1, k1, l, i1, resources.getDimensionPixelSize(i), 3));
        expandingDay = -1;
        chipActionListener = new _cls1();
        attributeset = context.obtainStyledAttributes(attributeset, com.google.android.calendar.R.styleable.AllDayHeaderView);
        if (attributeset == null)
        {
            i = 1;
        } else
        {
            i = attributeset.getInteger(com.google.android.calendar.R.styleable.AllDayHeaderView_count_days, 1);
            attributeset.recycle();
        }
        setColumnCount(i);
        touchHelper = new TouchHelper(this);
        ViewCompat.setAccessibilityDelegate(this, touchHelper);
        ViewCompat.setImportantForAccessibility(this, 1);
        firstJulianDay = 0x80000000;
        chipGeometry = new GridPartitionItemGeometry(context);
    }

    static final PartitionItem lambda$makePartitionItemsAndResolveConflicts$2$AllDayHeaderView(ExpandableChipColumnView.Registry registry)
    {
        return (PartitionItem)registry.partitionInfo;
    }

    static final int lambda$static$0$AllDayHeaderView(ExpandableChipColumnView.Registry registry, ExpandableChipColumnView.Registry registry1)
    {
        return TimelineItem.AllDayComparator.compare(registry.timelineItem, registry1.timelineItem);
    }

    public void addChildrenForAccessibility(ArrayList arraylist)
    {
    }

    public final void clear()
    {
        super.clear();
        touchHelper.postSubtreeChanged();
    }

    final ChipFragmentInfo createChipFragmentInfo()
    {
        boolean flag = true;
        com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder builder = new com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder();
        byte byte0;
        if (super.columnCount == 1)
        {
            byte0 = 3;
        } else
        {
            byte0 = 1;
        }
        builder.viewType = Integer.valueOf(byte0);
        if (super.columnCount > 1)
        {
            flag = false;
        }
        builder.showMultidayAnnotations = flag;
        return builder.build();
    }

    public boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        return touchHelper.dispatchHoverEvent(motionevent) || super.dispatchHoverEvent(motionevent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        int i = ((ExploreByTouchHelper) (touchHelper)).mAccessibilityFocusedVirtualViewId;
        if (i != 0x80000000)
        {
            TouchHelper touchhelper = touchHelper;
            if (TouchHelper.getChildIndex(i) < 0)
            {
                return false;
            }
        }
        return super.dispatchTouchEvent(motionevent);
    }

    protected final com.google.android.calendar.timeline.chip.Chip.ChipActionListener getChipActionListener()
    {
        return chipActionListener;
    }

    protected final int getFirstJulianDayForCollisions()
    {
        return firstJulianDay;
    }

    protected final int getLeftmostColumnForItem(PartitionItem partitionitem)
    {
        return NumberUtils.clamp(partitionitem.getStartDay() - firstJulianDay, 0, super.columnCount - 1);
    }

    protected final int getRightmostColumnForItem(PartitionItem partitionitem)
    {
        return NumberUtils.clamp(partitionitem.getEndDay() - firstJulianDay, 0, super.columnCount - 1);
    }

    protected final void onExpandButtonClicked(int i)
    {
        StickyAllDayManager stickyalldaymanager;
label0:
        {
            if (stateManager != null)
            {
                expandingDay = i;
                stickyalldaymanager = stateManager;
                if (stickyalldaymanager.sharedState != -1)
                {
                    break label0;
                }
                stickyalldaymanager.changeState((StickyAllDayManager.AllDayInfo)stickyalldaymanager.allDayInfo.get(this));
            }
            return;
        }
        stickyalldaymanager.setSharedState((stickyalldaymanager.sharedState + 1) % 2);
    }

    protected final void onExpandStateChanged$51D2ILG_0()
    {
        if (expandingDay == -1) goto _L2; else goto _L1
_L1:
        A11yHelper.getInstance();
        if (!A11yHelper.isAccessibilityEnabled(getContext())) goto _L2; else goto _L3
_L3:
        int i;
        int j;
        int k;
        int k1;
        k1 = getChildCount();
        i = 64;
        k = 0;
        j = -1;
_L5:
        if (k >= k1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!(getChildAt(k) instanceof Chip))
        {
            break MISSING_BLOCK_LABEL_240;
        }
        Chip chip = (Chip)getChildAt(k);
        int l = firstJulianDay + expandingDay;
        if (chip.partitionInfo.getStartDay() > l || l > chip.partitionInfo.getEndDay() || chip.partitionInfo.getPartition() < super.config.maxChipsIfCollapsed - 1 || chip.partitionInfo.getPartition() >= i)
        {
            break MISSING_BLOCK_LABEL_240;
        }
        i = touchHelper._fld0.firstJulianDay;
        int l1 = chip.partitionInfo.getPartition();
        j = chip.partitionInfo.getPartition();
        i = l - i << 20 | (k + 1 | l1 << 10);
_L6:
        int i1 = k + 1;
        k = j;
        j = i;
        i = k;
        k = i1;
        if (true) goto _L5; else goto _L4
_L4:
        if (j != -1)
        {
            touchHelper.sendEventForVirtualView(j, 32768);
        }
_L2:
        expandingDay = -1;
        touchHelper.postSubtreeChanged();
        return;
        int j1 = i;
        i = j;
        j = j1;
          goto _L6
    }

    public final void onUpdate(MonthData monthdata, int i)
    {
        boolean flag1 = false;
        boolean flag = false;
        if (i < firstJulianDay || i >= firstJulianDay + super.columnCount)
        {
            return;
        }
        int j = i - firstJulianDay;
        Object obj = super.items.iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            Object obj2 = (ExpandableChipColumnView.Registry)((Iterator) (obj)).next();
            if (getLeftmostColumnForItem((PartitionItem)((ExpandableChipColumnView.Registry) (obj2)).partitionInfo) <= j && getRightmostColumnForItem((PartitionItem)((ExpandableChipColumnView.Registry) (obj2)).partitionInfo) >= j)
            {
                ((Iterator) (obj)).remove();
                obj2 = ((ExpandableChipColumnView.Registry) (obj2)).chip;
                removeView(((View) (obj2)));
                super.chipRecycler.recycle(obj2);
            }
        } while (true);
        super.expandButtons[j].setVisibility(8);
        requestLayout();
        obj = monthdata.getItems(i);
        if (obj != null)
        {
            monthdata = new HashMap(((List) (obj)).size());
            obj = ((List) (obj)).iterator();
            do
            {
                if (!((Iterator) (obj)).hasNext())
                {
                    break;
                }
                TimelineItem timelineitem = (TimelineItem)((Iterator) (obj)).next();
                if (timelineitem != null && timelineitem.spansAtLeastOneDay())
                {
                    monthdata.put(timelineitem, new SimplePartitionItem(timelineitem));
                }
            } while (true);
            if (!monthdata.isEmpty())
            {
                addPartitionItems(monthdata, createChipFragmentInfo(), i);
                monthdata = new ArrayList(Collections.unmodifiableList(super.items));
                Collections.sort(monthdata, REGISTRY_ALL_DAY_COMPARATOR);
                Object obj1 = chipGeometry;
                class .Lambda._cls1
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls1();

                    public final Object apply(Object obj3)
                    {
                        return AllDayHeaderView.lambda$makePartitionItemsAndResolveConflicts$2$AllDayHeaderView((ExpandableChipColumnView.Registry)obj3);
                    }


            private .Lambda._cls1()
            {
            }
                }

                obj1 = .Lambda._cls1..instance;
                if (monthdata == null)
                {
                    throw new NullPointerException();
                }
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                GridPartitionItemGeometry.doComputePositions(new com.google.common.collect.Iterables._cls5(monthdata, ((Function) (obj1))), 0L, true, false, false);
                super.partitionCount = 0;
                for (monthdata = super.items.iterator(); monthdata.hasNext();)
                {
                    ExpandableChipColumnView.Registry registry = (ExpandableChipColumnView.Registry)monthdata.next();
                    super.partitionCount = Math.max(super.partitionCount, ((PartitionItem)registry.partitionInfo).getPartition() + 1);
                }

                super.applyExpandedOrCollapsedState();
            }
        }
        if (stateManager != null)
        {
            monthdata = stateManager;
            i = super.partitionCount;
            StickyAllDayManager.AllDayInfo alldayinfo = monthdata.getInfo(this);
            if (alldayinfo != null && alldayinfo.countOfChips != i)
            {
                alldayinfo.countOfChips = i;
                if (((StickyAllDayManager) (monthdata)).sharedState == -1)
                {
                    if (((ExpandableChipColumnView) (alldayinfo.allDayContent)).isExpanded)
                    {
                        i = ((flag) ? 1 : 0);
                    } else
                    {
                        i = 1;
                    }
                    monthdata.setNonSharedState(alldayinfo, i, alldayinfo.countOfChips);
                } else
                {
                    if (((ExpandableChipColumnView) (alldayinfo.allDayContent)).isExpanded)
                    {
                        i = ((flag1) ? 1 : 0);
                    } else
                    {
                        i = 1;
                    }
                    monthdata.setSharedState(i);
                }
            }
        }
        touchHelper.postSubtreeChanged();
    }

    public void setFirstJulianDay(int i)
    {
        if (i == firstJulianDay)
        {
            return;
        } else
        {
            firstJulianDay = i;
            clear();
            return;
        }
    }

    static 
    {
        class .Lambda._cls2
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls2();

            public final int compare(Object obj, Object obj1)
            {
                return AllDayHeaderView.lambda$static$0$AllDayHeaderView((ExpandableChipColumnView.Registry)obj, (ExpandableChipColumnView.Registry)obj1);
            }


            private .Lambda._cls2()
            {
            }
        }

        REGISTRY_ALL_DAY_COMPARATOR = .Lambda._cls2..instance;
    }

    private class _cls1
        implements com.google.android.calendar.timeline.chip.Chip.ChipActionListener
    {

        private final AllDayHeaderView this$0;

        public final void onChipPrimaryAction(Chip chip)
        {
            Object obj = items.iterator();
_L4:
            if (!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
            ExpandableChipColumnView.Registry registry = (ExpandableChipColumnView.Registry)((Iterator) (obj)).next();
            if (registry.chip != chip) goto _L4; else goto _L3
_L3:
            obj = registry.timelineItem;
_L6:
            if (obj == null)
            {
                LogUtils.w(AllDayHeaderView.TAG, "Not propagating chip primary action, getItemForChip() returned null.", new Object[0]);
                return;
            }
            break; /* Loop/switch isn't completed */
_L2:
            obj = null;
            if (true) goto _L6; else goto _L5
_L5:
            Object obj1 = VisualElementHolder.instance;
            if (obj1 == null)
            {
                throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
            } else
            {
                ((VisualElementAttacher)obj1).recordChipTap(getContext(), chip);
                obj1 = createChipFragmentInfo();
                int i = chip.partitionInfo.getStartDay();
                chip = new EventInfoAnimationData(chip, chip.viewModel, ((ChipFragmentInfo) (obj1)), i);
                ((OnLaunchDetailsHandler)getContext()).onLaunchDetails(((TimelineItem) (obj)), chip);
                CreateNewEventView.removeSelectedTime();
                return;
            }
        }

        public final void onChipSecondaryAction(Chip chip)
        {
        }

        _cls1()
        {
            this$0 = AllDayHeaderView.this;
            super();
        }
    }

}
