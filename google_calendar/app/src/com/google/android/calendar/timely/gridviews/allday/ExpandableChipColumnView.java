// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.calendar.Utils;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipViewModelFactory;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class ExpandableChipColumnView extends ViewGroup
{

    public Recycler chipRecycler;
    private final ChipViewModelFactory chipViewModelFactory;
    public int columnCount;
    public int columnLeftPositions[];
    public final Config config;
    public TextView expandButtons[];
    public boolean isExpanded;
    private final boolean isRtl;
    public final List items = new ArrayList();
    private final int maxHeightIfCollapsed;
    public int partitionCount;

    protected ExpandableChipColumnView(Context context, AttributeSet attributeset, Config config1)
    {
        super(context, attributeset);
        chipViewModelFactory = new ChipViewModelFactory(context);
        isRtl = RtlUtils.isLayoutDirectionRtl(context);
        config = config1;
        maxHeightIfCollapsed = config.maxChipsIfCollapsed * (config.chipHeight + config.chipVerticalSpacing) + config.topMargin;
    }

    protected final void addPartitionItems(Map map, ChipFragmentInfo chipfragmentinfo, int i)
    {
        for (map = map.entrySet().iterator(); map.hasNext();)
        {
            Object obj = (java.util.Map.Entry)map.next();
            Chip chip = (Chip)chipRecycler.getOrCreateObject();
            obj = new Registry((PartitionItem)((java.util.Map.Entry) (obj)).getValue(), (TimelineItem)((java.util.Map.Entry) (obj)).getKey(), chip);
            chip.setViewModel(chipViewModelFactory.buildViewModel(((Registry) (obj)).timelineItem, chipfragmentinfo, i));
            chip.partitionInfo = (PartitionItem)((Registry) (obj)).partitionInfo;
            chip.setActionListener(getChipActionListener());
            class .Lambda._cls1
                implements com.google.android.calendar.timeline.chip.Chip.ChipLongPressListener
            {

                private final ExpandableChipColumnView arg$1;

                public final boolean onChipLongPress(Chip chip1, Point point)
                {
                    DndAnalytics.logDndFailedStartWrongView(arg$1.getContext(), "long_press_timeline_chip_grid_allday");
                    return false;
                }

            .Lambda._cls1()
            {
                arg$1 = ExpandableChipColumnView.this;
            }
            }

            chip.longPressListener = new .Lambda._cls1();
            chip.updateInteractionListeners();
            addView(chip);
            items.add(obj);
            partitionCount = Math.max(partitionCount, ((PartitionItem)((Registry) (obj)).partitionInfo).getPartition() + 1);
        }

        applyExpandedOrCollapsedState();
    }

    public final void applyExpandedOrCollapsedState()
    {
        List list = items;
        int i;
        if (isExpanded)
        {
            i = partitionCount;
        } else
        {
            i = config.maxChipsIfCollapsed;
        }
        list = hideOrShowItems(list, i);
        i = 0;
        while (i < columnCount) 
        {
            if (((Integer)list.get(i)).intValue() > 0)
            {
                if (expandButtons[i].getParent() == null)
                {
                    addView(expandButtons[i]);
                }
                expandButtons[i].setText(getResources().getQuantityString(config.xMoreString, ((Integer)list.get(i)).intValue(), new Object[] {
                    list.get(i)
                }));
                expandButtons[i].bringToFront();
                expandButtons[i].setVisibility(0);
            } else
            if (expandButtons[i].getParent() != null)
            {
                removeView(expandButtons[i]);
            }
            i++;
        }
        requestLayout();
    }

    public void clear()
    {
        removeAllViews();
        Registry registry;
        for (Iterator iterator = items.iterator(); iterator.hasNext(); chipRecycler.recycle(registry.chip))
        {
            registry = (Registry)iterator.next();
        }

        items.clear();
        partitionCount = 0;
        applyExpandedOrCollapsedState();
    }

    protected abstract com.google.android.calendar.timeline.chip.Chip.ChipActionListener getChipActionListener();

    protected final int getDesiredHeight()
    {
        int i = partitionCount * (config.chipHeight + config.chipVerticalSpacing) + config.topMargin;
        if (isExpanded)
        {
            return i;
        } else
        {
            return Math.min(i, maxHeightIfCollapsed);
        }
    }

    protected abstract int getFirstJulianDayForCollisions();

    protected abstract int getLeftmostColumnForItem(PartitionItem partitionitem);

    protected abstract int getRightmostColumnForItem(PartitionItem partitionitem);

    protected List hideOrShowItems(List list, int i)
    {
        if (i >= partitionCount)
        {
            for (list = list.iterator(); list.hasNext(); ((Registry)list.next()).chip.setVisibility(0)) { }
            return Collections.nCopies(columnCount, Integer.valueOf(0));
        }
        list = items;
        class .Lambda._cls2
            implements Function
        {

            public static final Function $instance = new .Lambda._cls2();

            public final Object apply(Object obj)
            {
                obj = (Registry)obj;
                if (((Registry) (obj)).asPair == null)
                {
                    obj.asPair = new Pair(((Registry) (obj)).partitionInfo, ((Registry) (obj)).chip);
                }
                return ((Registry) (obj)).asPair;
            }


            private .Lambda._cls2()
            {
            }
        }

        Function function = .Lambda._cls2..instance;
        if (list == null)
        {
            throw new NullPointerException();
        }
        if (function == null)
        {
            throw new NullPointerException();
        } else
        {
            return Utils.hideAllDayChips(new com.google.common.collect.Iterables._cls5(list, function), getFirstJulianDayForCollisions(), getFirstJulianDayForCollisions() + columnCount, config.maxChipsIfCollapsed);
        }
    }

    protected void onExpandButtonClicked(int i)
    {
        boolean flag;
        if (!isExpanded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag != isExpanded)
        {
            isExpanded = flag;
            applyExpandedOrCollapsedState();
            onExpandStateChanged$51D2ILG_0();
        }
    }

    public void onExpandStateChanged$51D2ILG_0()
    {
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        l = 0;
        i = 0;
        while (i < columnCount) 
        {
            if (isRtl)
            {
                j = columnCount - 1 - i;
            } else
            {
                j = i;
            }
            columnLeftPositions[i] = (int)((float)j * ((float)getMeasuredWidth() / (float)columnCount));
            i++;
        }
        Iterator iterator = items.iterator();
        do
        {
            i = l;
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = (Registry)iterator.next();
            PartitionItem partitionitem = (PartitionItem)((Registry) (obj)).partitionInfo;
            i = config.topMargin;
            j = config.chipHeight;
            k = config.chipVerticalSpacing;
            int i1 = i + partitionitem.getPartition() * (j + k);
            i = getLeftmostColumnForItem((PartitionItem)((Registry) (obj)).partitionInfo);
            j = getRightmostColumnForItem((PartitionItem)((Registry) (obj)).partitionInfo);
            int ai[] = columnLeftPositions;
            int j1;
            int k1;
            if (isRtl)
            {
                k = j;
            } else
            {
                k = i;
            }
            k = ai[k];
            ai = columnLeftPositions;
            if (!isRtl)
            {
                i = j;
            }
            j1 = ai[i];
            obj = ((Registry) (obj)).chip;
            if (isRtl)
            {
                i = 0;
            } else
            {
                i = config.horizontalMargin;
            }
            k1 = (int)((float)getMeasuredWidth() / (float)columnCount);
            if (isRtl)
            {
                j = config.horizontalMargin;
            } else
            {
                j = 0;
            }
            ((Chip) (obj)).layout(k + i, i1, (j1 + k1) - j, config.chipHeight + i1);
        } while (true);
        for (; i < columnCount; i++)
        {
            if (expandButtons[i].getParent() == this)
            {
                expandButtons[i].layout((columnLeftPositions[i] + config.horizontalMargin) - 1, maxHeightIfCollapsed - config.chipHeight - config.chipVerticalSpacing, ((columnLeftPositions[i] + (int)((float)getMeasuredWidth() / (float)columnCount)) - config.horizontalMargin) + 1, maxHeightIfCollapsed);
            }
        }

    }

    protected void onMeasure(int i, int j)
    {
        if (android.view.View.MeasureSpec.getMode(i) == 0)
        {
            i = getWidth();
        } else
        {
            i = android.view.View.MeasureSpec.getSize(i);
        }
        setMeasuredDimension(i, getDesiredHeight());
    }

    public final void setColumnCount(int i)
    {
        if (i == columnCount)
        {
            return;
        }
        Resources resources = getResources();
        int k = resources.getColor(0x7f0d0331);
        int l = resources.getColor(0x7f0d0313);
        int i1 = resources.getDimensionPixelSize(0x7f0e0093);
        int j = Math.min(columnCount, i);
        TextView atextview[] = new TextView[i];
        if (j > 0)
        {
            System.arraycopy(expandButtons, 0, atextview, 0, j);
        }
        while (j < i) 
        {
            atextview[j] = new TextView(getContext());
            atextview[j].setPadding(i1, config.showMoreVerticalPadding, 0, 0);
            atextview[j].setBackgroundColor(l);
            atextview[j].setTextColor(k);
            atextview[j].setTextSize(0, config.showMoreTextSize);
            TextView textview = atextview[j];
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final ExpandableChipColumnView arg$1;

                public final void onClick(View view)
                {
                    ExpandableChipColumnView expandablechipcolumnview;
                    int j1;
                    expandablechipcolumnview = arg$1;
                    j1 = 0;
_L3:
                    if (j1 >= expandablechipcolumnview.columnCount)
                    {
                        break MISSING_BLOCK_LABEL_38;
                    }
                    if (view != expandablechipcolumnview.expandButtons[j1]) goto _L2; else goto _L1
_L1:
                    expandablechipcolumnview.onExpandButtonClicked(j1);
                    return;
_L2:
                    j1++;
                      goto _L3
                    j1 = -1;
                      goto _L1
                }

            .Lambda._cls0()
            {
                arg$1 = ExpandableChipColumnView.this;
            }
            }

            Typeface typeface;
            if (Material.robotoMedium != null)
            {
                typeface = Material.robotoMedium;
            } else
            {
                typeface = Typeface.create("sans-serif-medium", 0);
                Material.robotoMedium = typeface;
            }
            textview.setTypeface(typeface);
            atextview[j].setOnClickListener(new .Lambda._cls0());
            j++;
        }
        columnCount = i;
        columnLeftPositions = new int[columnCount];
        expandButtons = atextview;
        requestLayout();
    }

    private class Config
    {

        public final int chipHeight;
        public final int chipVerticalSpacing;
        public final int horizontalMargin;
        public final int maxChipsIfCollapsed = 3;
        public final float showMoreTextSize;
        public final int showMoreVerticalPadding;
        public final int topMargin;
        public final int xMoreString;

        public Config(int i, int j, int k, int l, int i1, int j1, float f, 
                int k1)
        {
            xMoreString = i;
            topMargin = j;
            chipHeight = k;
            chipVerticalSpacing = l;
            horizontalMargin = i1;
            showMoreVerticalPadding = j1;
            showMoreTextSize = f;
        }
    }


    private class Registry
    {

        public Pair asPair;
        public final Chip chip;
        public final Object partitionInfo;
        public final TimelineItem timelineItem;

        Registry(Object obj, TimelineItem timelineitem, Chip chip1)
        {
            partitionInfo = obj;
            timelineItem = timelineitem;
            chip = chip1;
        }
    }

}
