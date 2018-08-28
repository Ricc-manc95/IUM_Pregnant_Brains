// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.android.calendarcommon2.LogUtils;
import com.android.datetimepicker.date.MonthView;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.Utils;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipViewModelFactory;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.callbacks.OnLaunchDetailsHandler;
import com.google.android.calendar.timely.geometry.GridPartitionItemGeometry;
import com.google.android.calendar.timely.geometry.SimplePartitionItem;
import com.google.android.calendar.utils.a11y.A11yHelper;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.common.util.concurrent.FluentFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthViewFrame, TimelineItem, MonthViewPartitionItem, MonthData, 
//            MonthViewUtil, DataFactory

public class CalendarMonthView extends ViewGroup
    implements com.google.android.calendar.timeline.chip.Chip.ChipActionListener
{
    public abstract class OnMonthlyUpdateListener
        implements RangedData.OnUpdateListener
    {

        private int dataListenerTag;
        public boolean disabled;
        public final CalendarMonthView this$0;

        public final int getListenerTag()
        {
            return dataListenerTag;
        }

        public final void postUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NKQRREEHK48OBKC4TKIMICCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FA9GMSPR5CH262T314HAN0P31EHIKCQBED5PMGPB49HKN6T35DPIN4EP9AO______0(MonthData monthdata, int i, RangedData.UpdateFinishedListener updatefinishedlistener)
        {
            if (getListenerTagType() != 2)
            {
                updatefinishedlistener.notifyUpdateFinished();
            }
            class .Lambda._cls0
                implements Runnable
            {

                private final OnMonthlyUpdateListener arg$1;
                private final MonthData arg$2;
                private final int arg$3;
                private final RangedData.UpdateFinishedListener arg$4;

                public final void run()
                {
                    OnMonthlyUpdateListener onmonthlyupdatelistener = arg$1;
                    MonthData monthdata1 = arg$2;
                    int j = arg$3;
                    RangedData.UpdateFinishedListener updatefinishedlistener1 = arg$4;
                    if (onmonthlyupdatelistener.disabled)
                    {
                        LogUtils.e(CalendarMonthView.TAG, "onUpdate called after unregistering", new Object[0]);
                    } else
                    {
                        onmonthlyupdatelistener._fld0.onUpdate(monthdata1, j);
                        if (onmonthlyupdatelistener.getListenerTagType() == 2)
                        {
                            updatefinishedlistener1.notifyUpdateFinished();
                            return;
                        }
                    }
                }

                .Lambda._cls0(MonthData monthdata, int i, RangedData.UpdateFinishedListener updatefinishedlistener)
                {
                    arg$1 = OnMonthlyUpdateListener.this;
                    arg$2 = monthdata;
                    arg$3 = i;
                    arg$4 = updatefinishedlistener;
                }
            }

            monthdata = (FluentFuture)CalendarExecutor.MAIN.submit(new .Lambda._cls0(monthdata, i, updatefinishedlistener));
        }

        public void setListenerTag(int i)
        {
            dataListenerTag = i;
        }

        protected OnMonthlyUpdateListener()
        {
            this$0 = CalendarMonthView.this;
            super();
        }
    }


    private static final ChipFragmentInfo CHIP_FRAGMENT_INFO;
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/CalendarMonthView);
    private final GridPartitionItemGeometry chipGeometry;
    public final int chipHorizontalMargin;
    public Recycler chipRecycler;
    public final int chipVerticalMargin;
    private final ChipViewModelFactory chipViewModelFactory;
    public final Map chips = new HashMap();
    public final OnMonthlyUpdateListener currentMonthListener = new _cls1();
    public DataFactory dataFactory;
    public final SparseArray daysToItems = new SparseArray();
    public final int eventChipHeight;
    private final boolean isRtl;
    private final boolean isTablet;
    private int maxCountOfChipsPerDay;
    public MonthViewFrame monthViewFrame;
    public final OnMonthlyUpdateListener nextMonthListener = new _cls3();
    public final ArrayList partitionItemsByWeek = new ArrayList();
    public final OnMonthlyUpdateListener previousMonthListener = new _cls2();

    public CalendarMonthView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        attributeset = context.getResources();
        chipViewModelFactory = new ChipViewModelFactory(context);
        eventChipHeight = attributeset.getDimensionPixelSize(0x7f0e02a5);
        chipHorizontalMargin = attributeset.getDimensionPixelSize(0x7f0e02a6);
        chipVerticalMargin = attributeset.getDimensionPixelSize(0x7f0e02a7);
        chipGeometry = new GridPartitionItemGeometry(context);
        isRtl = RtlUtils.isLayoutDirectionRtl(context);
        isTablet = context.getResources().getBoolean(0x7f0c0016);
    }

    private final void updateOverflowButtons(List list)
    {
        int j = monthViewFrame.mFirstJulianDay;
        int k = monthViewFrame.getFirstDayOffset();
        int i = 0;
        j -= k;
        for (; i < list.size(); i++)
        {
            for (int l = 0; l < ((List)list.get(i)).size(); l++)
            {
                MonthViewFrame monthviewframe = monthViewFrame;
                int i1 = ((Integer)((List)list.get(i)).get(l)).intValue();
                int ai[] = monthviewframe.numHiddenChips;
                int j1 = monthviewframe.mFirstJulianDay;
                ai[monthviewframe.getFirstDayOffset() + ((j + l) - j1)] = i1;
            }

            j += 7;
        }

        monthViewFrame.invalidate();
    }

    final void clearChips(int i)
    {
        for (int j = i; j < getChildCount(); j++)
        {
            chipRecycler.recycle((Chip)getChildAt(j));
        }

        removeViews(i, getChildCount() - i);
    }

    public final void onChipPrimaryAction(Chip chip)
    {
        Context context = getContext();
        Object obj = chip.getTag(0x7f100028);
        if (!(obj instanceof TimelineItem) || !(context instanceof MonthFragment.OnLaunchDayDetailsHandler))
        {
            LogUtils.wtf(TAG, "Not handling chip primary action: tag or context is invalid. Tag: %s, context: %s", new Object[] {
                obj, context
            });
            return;
        }
        obj = (TimelineItem)obj;
        Object obj1 = VisualElementHolder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)obj1).recordChipTap(getContext(), chip);
            obj1 = CHIP_FRAGMENT_INFO;
            chip = new EventInfoAnimationData(chip, chip.viewModel, ((ChipFragmentInfo) (obj1)), 0);
            ((OnLaunchDetailsHandler)context).onLaunchDetails(((TimelineItem) (obj)), chip);
            return;
        }
    }

    public final void onChipSecondaryAction(Chip chip)
    {
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        monthViewFrame = (MonthViewFrame)findViewById(0x7f100384);
        monthViewFrame.setRtlEnabled(true);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        ArrayList arraylist;
        monthViewFrame.layout(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        if (monthViewFrame.maxNumChipsPerDay != maxCountOfChipsPerDay)
        {
            maxCountOfChipsPerDay = monthViewFrame.maxNumChipsPerDay;
            arraylist = new ArrayList();
            Object obj;
            Iterator iterator1;
            MonthViewPartitionItem monthviewpartitionitem1;
            Chip chip1;
            TimelineItem timelineitem;
            if (isTablet)
            {
label0:
                {
                    A11yHelper.getInstance();
                    if (!A11yHelper.isAccessibilityEnabled(getContext()))
                    {
                        i = 1;
                        break label0;
                    }
                }
            }
            i = 0;
            continue; /* Loop/switch isn't completed */
        }
          goto _L1
_L3:
        obj = monthViewFrame;
        k = ((MonthViewFrame) (obj)).mFirstJulianDay - ((MonthView) (obj)).findDayOffset();
        for (j = 0; j < partitionItemsByWeek.size(); j++)
        {
            arraylist.add(new ArrayList(Collections.nCopies(7, Integer.valueOf(0))));
            obj = Utils.computePartitionItemsToHide((Iterable)partitionItemsByWeek.get(j), k, (k + 7) - 1, maxCountOfChipsPerDay, (List)arraylist.get(j));
            iterator1 = ((List)partitionItemsByWeek.get(j)).iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                monthviewpartitionitem1 = (MonthViewPartitionItem)iterator1.next();
                flag = ((Set) (obj)).contains(monthviewpartitionitem1);
                chip1 = (Chip)chips.get(monthviewpartitionitem1);
                if (chip1 != null)
                {
                    if (flag)
                    {
                        l = 4;
                    } else
                    {
                        l = 0;
                    }
                    chip1.setVisibility(l);
                }
                if (!flag && chip1 == null)
                {
                    chip1 = (Chip)chipRecycler.getOrCreateObject();
                    timelineitem = monthviewpartitionitem1.item;
                    chip1.partitionInfo = monthviewpartitionitem1;
                    chip1.setViewModel(chipViewModelFactory.buildViewModel(timelineitem, CHIP_FRAGMENT_INFO, 0));
                    chip1.setTag(0x7f100028, timelineitem);
                    if (i != 0)
                    {
                        chip1.setActionListener(this);
                    }
                    addViewInLayout(chip1, -1, generateDefaultLayoutParams(), true);
                    chips.put(monthviewpartitionitem1, chip1);
                }
            } while (true);
            k += 7;
        }

        updateOverflowButtons(arraylist);
_L1:
        int i1 = chipVerticalMargin;
        int j1 = maxCountOfChipsPerDay;
        int k1 = eventChipHeight;
        int l1 = chipVerticalMargin;
        i = monthViewFrame.mFirstJulianDay;
        k = monthViewFrame.getFirstDayOffset();
        j = 0;
        for (i -= k; j < partitionItemsByWeek.size(); i += 7)
        {
            Iterator iterator = ((List)partitionItemsByWeek.get(j)).iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                MonthViewPartitionItem monthviewpartitionitem = (MonthViewPartitionItem)iterator.next();
                Chip chip = (Chip)chips.get(monthviewpartitionitem);
                if (chip != null && chip.getVisibility() == 0)
                {
                    l = Math.max(i, ((SimplePartitionItem) (monthviewpartitionitem)).startDay);
                    Rect rect;
                    Rect rect1;
                    int i2;
                    int j2;
                    if (((SimplePartitionItem) (monthviewpartitionitem)).spansAtLeastOneDay)
                    {
                        k = Math.min((i + 7) - 1, ((SimplePartitionItem) (monthviewpartitionitem)).endDay);
                    } else
                    {
                        k = l;
                    }
                    rect = monthViewFrame.getCellPosition(l, getPaddingTop(), getPaddingLeft());
                    rect1 = monthViewFrame.getCellPosition(k, getPaddingTop(), getPaddingLeft());
                    i2 = rect.bottom + (i1 - j1 * (k1 + l1)) + ((SimplePartitionItem) (monthviewpartitionitem)).partitionIndex * (eventChipHeight + chipVerticalMargin);
                    j2 = eventChipHeight;
                    if (isRtl)
                    {
                        k = rect1.left;
                    } else
                    {
                        k = rect.left;
                    }
                    if (isRtl)
                    {
                        l = rect.right;
                    } else
                    {
                        l = rect1.right;
                    }
                    chip.layout(k + chipHorizontalMargin, i2, l - chipHorizontalMargin, i2 + j2);
                }
            } while (true);
            j++;
        }

        return;
        if (true) goto _L3; else goto _L2
_L2:
    }

    final void onUpdate(MonthData monthdata, int i)
    {
        Trace.beginSection("CalendarMonthView-onUpdate");
        int j;
        int k;
        int l;
        MonthViewFrame monthviewframe = monthViewFrame;
        if (i < monthviewframe.mFirstJulianDay - monthviewframe.findDayOffset())
        {
            break MISSING_BLOCK_LABEL_64;
        }
        monthviewframe = monthViewFrame;
        j = monthviewframe.mFirstJulianDay;
        k = monthviewframe.findDayOffset();
        l = monthviewframe.mNumRows;
        if (i <= (l * 7 + (j - k)) - 1)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        Trace.endSection();
        return;
        if (i >= monthViewFrame.mFirstJulianDay) goto _L2; else goto _L1
_L1:
        MonthViewFrame monthviewframe1 = monthViewFrame;
        i = monthviewframe1.mFirstJulianDay;
        k = monthviewframe1.findDayOffset();
        j = monthViewFrame.mFirstJulianDay;
        i -= k;
        j--;
_L3:
        if (i > j)
        {
            break MISSING_BLOCK_LABEL_320;
        }
        Object obj;
        Object obj1;
        obj = monthdata.getItems(i);
        obj1 = daysToItems;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_302;
        }
        obj = Collections.EMPTY_LIST;
_L4:
        ((SparseArray) (obj1)).put(i, obj);
        Collections.sort((List)daysToItems.get(i), TimelineItem.ItemComparatorMultidayFirst);
        i++;
          goto _L3
_L2:
        obj = monthViewFrame;
        j = ((MonthViewFrame) (obj)).mFirstJulianDay;
        if (i > (((MonthViewFrame) (obj)).mNumCells + j) - 1)
        {
            break MISSING_BLOCK_LABEL_236;
        }
        i = monthViewFrame.mFirstJulianDay;
        obj = monthViewFrame;
        j = ((MonthViewFrame) (obj)).mFirstJulianDay;
        k = ((MonthViewFrame) (obj)).mNumCells;
        j = (k + j) - 1;
          goto _L3
        int i1;
        obj = monthViewFrame;
        i = ((MonthViewFrame) (obj)).mFirstJulianDay;
        i1 = ((MonthViewFrame) (obj)).mNumCells;
        obj = monthViewFrame;
        j = ((MonthViewFrame) (obj)).mFirstJulianDay;
        k = ((MonthView) (obj)).findDayOffset();
        l = ((MonthViewFrame) (obj)).mNumRows;
        i = ((i1 + i) - 1) + 1;
        j = (l * 7 + (j - k)) - 1;
          goto _L3
        obj = new ArrayList(((java.util.Collection) (obj)));
          goto _L4
        monthdata;
        Trace.endSection();
        throw monthdata;
        Trace.beginSection("CalendarMonthView-createChips");
        partitionItemsByWeek.clear();
        chips.clear();
        obj = new ArrayList();
        monthdata = monthViewFrame;
        j = ((MonthViewFrame) (monthdata)).mFirstJulianDay - monthdata.findDayOffset();
        i = 1;
_L9:
        monthdata = monthViewFrame;
        k = ((MonthViewFrame) (monthdata)).mFirstJulianDay;
        l = monthdata.findDayOffset();
        if (j > (((MonthViewFrame) (monthdata)).mNumRows * 7 + (k - l)) - 1) goto _L6; else goto _L5
_L5:
        monthdata = new ArrayList();
        MonthViewUtil.computePartitionItemsInWeek(daysToItems, j, monthdata);
        partitionItemsByWeek.add(monthdata);
        obj1 = new ArrayList(Collections.nCopies(7, Integer.valueOf(0)));
        GridPartitionItemGeometry.doComputePositions(monthdata, 0L, true, true, false);
        if (!isTablet)
        {
            break MISSING_BLOCK_LABEL_761;
        }
        A11yHelper.getInstance();
        if (A11yHelper.isAccessibilityEnabled(getContext()))
        {
            break MISSING_BLOCK_LABEL_761;
        }
        k = 1;
_L10:
        Set set;
        Iterator iterator;
        set = Utils.computePartitionItemsToHide(monthdata, j, (j + 7) - 1, maxCountOfChipsPerDay, ((List) (obj1)));
        iterator = monthdata.iterator();
_L7:
        MonthViewPartitionItem monthviewpartitionitem;
        TimelineItem timelineitem;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_706;
            }
            monthviewpartitionitem = (MonthViewPartitionItem)iterator.next();
        } while (set.contains(monthviewpartitionitem));
        timelineitem = monthviewpartitionitem.item;
        if (i < getChildCount())
        {
            break MISSING_BLOCK_LABEL_637;
        }
        monthdata = (Chip)chipRecycler.getOrCreateObject();
        monthdata.partitionInfo = monthviewpartitionitem;
        monthdata.setViewModel(chipViewModelFactory.buildViewModel(timelineitem, CHIP_FRAGMENT_INFO, 0));
        addView(monthdata);
_L8:
        monthdata.setTag(0x7f100028, timelineitem);
        if (!k)
        {
            break MISSING_BLOCK_LABEL_617;
        }
        monthdata.setActionListener(this);
        chips.put(monthviewpartitionitem, monthdata);
        i++;
          goto _L7
        monthdata = (Chip)getChildAt(i);
        Recycler recycler = chipRecycler;
        recycler.recyclerManager.clean(monthdata);
        recycler.recyclerManager.prepareToReuse(monthdata);
        monthdata.partitionInfo = monthviewpartitionitem;
        monthdata.setViewModel(chipViewModelFactory.buildViewModel(timelineitem, CHIP_FRAGMENT_INFO, 0));
          goto _L8
        monthdata;
        Trace.endSection();
        throw monthdata;
        ((List) (obj)).add(obj1);
        j += 7;
          goto _L9
_L6:
        if (i < getChildCount())
        {
            clearChips(i);
        }
        updateOverflowButtons(((List) (obj)));
        requestLayout();
        invalidate();
        Trace.endSection();
        Trace.endSection();
        return;
        k = 0;
          goto _L10
    }

    public final void updateView()
    {
        int j = monthViewFrame.mFirstJulianDay;
        Object obj = monthViewFrame;
        int i = ((MonthViewFrame) (obj)).mFirstJulianDay;
        i = (((MonthViewFrame) (obj)).mNumCells + i) - 1;
        obj = currentMonthListener;
        MonthData monthdata = dataFactory.getData(j, false);
        monthdata.registerListener(j, ((RangedData.OnUpdateListener) (obj)));
        onUpdate(monthdata, j);
        obj.disabled = false;
        obj = monthViewFrame;
        if (j != ((MonthViewFrame) (obj)).mFirstJulianDay - ((MonthView) (obj)).findDayOffset())
        {
            j--;
            obj = previousMonthListener;
            MonthData monthdata1 = dataFactory.getData(j, false);
            monthdata1.registerListener(j, ((RangedData.OnUpdateListener) (obj)));
            onUpdate(monthdata1, j);
            obj.disabled = false;
        }
        obj = monthViewFrame;
        j = ((MonthViewFrame) (obj)).mFirstJulianDay;
        int k = ((MonthView) (obj)).findDayOffset();
        if (i != (((MonthViewFrame) (obj)).mNumRows * 7 + (j - k)) - 1)
        {
            i++;
            OnMonthlyUpdateListener onmonthlyupdatelistener = nextMonthListener;
            MonthData monthdata2 = dataFactory.getData(i, false);
            monthdata2.registerListener(i, onmonthlyupdatelistener);
            onUpdate(monthdata2, i);
            onmonthlyupdatelistener.disabled = false;
        }
    }

    static 
    {
        com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder builder = new com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder();
        builder.viewType = Integer.valueOf(2);
        CHIP_FRAGMENT_INFO = builder.build();
    }

    private class _cls1 extends OnMonthlyUpdateListener
    {

        public final int getListenerTagType()
        {
            return 2;
        }

        _cls1()
        {
        }
    }


    private class _cls2 extends OnMonthlyUpdateListener
    {

        public final int getListenerTagType()
        {
            return 3;
        }

        _cls2()
        {
        }
    }


    private class _cls3 extends OnMonthlyUpdateListener
    {

        public final int getListenerTagType()
        {
            return 4;
        }

        _cls3()
        {
        }
    }

}
