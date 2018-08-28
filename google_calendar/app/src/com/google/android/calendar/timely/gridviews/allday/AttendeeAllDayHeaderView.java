// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews.allday;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.geometry.GridPartitionItemGeometry;
import com.google.android.calendar.timely.geometry.PartitionItem;
import com.google.android.calendar.timely.geometry.SimplePartitionItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.timely.gridviews.allday:
//            ExpandableChipColumnView, AttendeePartitionItem

public class AttendeeAllDayHeaderView extends ExpandableChipColumnView
{
    public static interface OnMeasurementChangedListener
    {

        public abstract void onMeasurementChanged(int i, int j);
    }


    private final GridPartitionItemGeometry chipGeometry;
    private final Paint gridLinePaint = new Paint();
    public OnMeasurementChangedListener measurementChangedListener;

    public AttendeeAllDayHeaderView(Context context, AttributeSet attributeset)
    {
        Resources resources = context.getResources();
        super(context, attributeset, new ExpandableChipColumnView.Config(0x7f120046, 0, resources.getDimensionPixelSize(0x7f0e01d0), resources.getDimensionPixelSize(0x7f0e01cf), resources.getDimensionPixelOffset(0x7f0e0050), resources.getDimensionPixelSize(0x7f0e0095), resources.getDimensionPixelSize(0x7f0e0438), 3));
        setWillNotDraw(false);
        attributeset = context.getResources();
        gridLinePaint.setStrokeWidth(attributeset.getDimensionPixelSize(0x7f0e01dc));
        gridLinePaint.setColor(attributeset.getColor(0x7f0d011a));
        gridLinePaint.setAntiAlias(false);
        chipGeometry = new GridPartitionItemGeometry(context);
    }

    private final void updateMeasurementListener()
    {
        if (measurementChangedListener == null)
        {
            return;
        }
        int i;
        if (super.partitionCount <= super.config.maxChipsIfCollapsed)
        {
            i = 0;
        } else
        if (super.isExpanded)
        {
            i = 1;
        } else
        {
            i = 2;
        }
        measurementChangedListener.onMeasurementChanged(getDesiredHeight(), i);
    }

    public final void clear()
    {
        super.clear();
        setColumnCount(0);
    }

    protected final com.google.android.calendar.timeline.chip.Chip.ChipActionListener getChipActionListener()
    {
        return null;
    }

    protected final int getFirstJulianDayForCollisions()
    {
        return 0;
    }

    protected final int getLeftmostColumnForItem(PartitionItem partitionitem)
    {
        return ((AttendeePartitionItem)partitionitem).attendeeIndex;
    }

    protected final int getRightmostColumnForItem(PartitionItem partitionitem)
    {
        return ((AttendeePartitionItem)partitionitem).attendeeIndex;
    }

    protected final List hideOrShowItems(List list, int i)
    {
        if (i >= super.partitionCount)
        {
            return super.hideOrShowItems(list, i);
        }
        ArrayList arraylist = new ArrayList(Collections.nCopies(super.columnCount, Integer.valueOf(0)));
        Iterator iterator = list.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj1 = (ExpandableChipColumnView.Registry)iterator.next();
            AttendeePartitionItem attendeepartitionitem = (AttendeePartitionItem)((ExpandableChipColumnView.Registry) (obj1)).partitionInfo;
            int j;
            int k;
            if (((SimplePartitionItem) (attendeepartitionitem)).partitionIndex >= i)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            obj1 = ((ExpandableChipColumnView.Registry) (obj1)).chip;
            if (j == 0)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (obj1 != null)
            {
                if (k != 0)
                {
                    k = 0;
                } else
                {
                    k = 8;
                }
                ((View) (obj1)).setVisibility(k);
            }
            if (j != 0)
            {
                j = attendeepartitionitem.attendeeIndex;
                arraylist.set(j, Integer.valueOf(((Integer)arraylist.get(j)).intValue() + 1));
            }
        } while (true);
        list = list.iterator();
        do
        {
            if (!list.hasNext())
            {
                break;
            }
            Object obj = (ExpandableChipColumnView.Registry)list.next();
            AttendeePartitionItem attendeepartitionitem1 = (AttendeePartitionItem)((ExpandableChipColumnView.Registry) (obj)).partitionInfo;
            int i1 = attendeepartitionitem1.attendeeIndex;
            boolean flag;
            int l;
            if (((SimplePartitionItem) (attendeepartitionitem1)).partitionIndex == i - 1 && ((Integer)arraylist.get(i1)).intValue() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj = ((ExpandableChipColumnView.Registry) (obj)).chip;
            if (!flag)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            if (obj != null)
            {
                if (l != 0)
                {
                    l = 0;
                } else
                {
                    l = 8;
                }
                ((View) (obj)).setVisibility(l);
            }
            if (flag)
            {
                arraylist.set(i1, Integer.valueOf(((Integer)arraylist.get(i1)).intValue() + 1));
            }
        } while (true);
        return arraylist;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int j = getHeight();
        for (int i = super.columnCount - 1; i >= 0; i--)
        {
            canvas.drawLine(super.columnLeftPositions[i], 0.0F, super.columnLeftPositions[i], j, gridLinePaint);
        }

    }

    protected final void onExpandStateChanged$51D2ILG_0()
    {
        updateMeasurementListener();
    }

    public final void onUpdate$5166KOBMC4NNAT39DGNKOQBJEGTKOQJ1EPGIUTBKD5M2UJ39EDQ3MIA994KLC___0(List list, List list1, int i, int j)
    {
        int k = list.size() + j;
        if (k > super.columnCount)
        {
            setColumnCount(k);
        }
        k = 0;
        while (k < list.size()) 
        {
            Object obj = (List)list.get(k);
            if (obj == null)
            {
                obj = Collections.EMPTY_MAP;
            } else
            {
                Object obj1 = new ArrayList(((List) (obj)).size());
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
                        ((List) (obj1)).add(timelineitem);
                    }
                } while (true);
                if (((List) (obj1)).isEmpty())
                {
                    obj = Collections.EMPTY_MAP;
                } else
                {
                    Collections.sort(((List) (obj1)), TimelineItem.AllDayComparator);
                    obj = new LinkedHashMap(((List) (obj1)).size());
                    obj1 = (ArrayList)obj1;
                    int i1 = ((ArrayList) (obj1)).size();
                    for (int l = 0; l < i1;)
                    {
                        Object obj2 = ((ArrayList) (obj1)).get(l);
                        l++;
                        obj2 = (TimelineItem)obj2;
                        ((LinkedHashMap) (obj)).put(obj2, new AttendeePartitionItem(((TimelineItem) (obj2)), j + k));
                    }

                    obj1 = chipGeometry;
                    GridPartitionItemGeometry.doComputePositions(((LinkedHashMap) (obj)).values(), 0L, true, false, false);
                }
            }
            if (!((Map) (obj)).isEmpty())
            {
                com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder builder = new com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder();
                builder.viewType = Integer.valueOf(1);
                builder.showMultidayAnnotations = true;
                builder.contentDescriptionPrefix = (String)list1.get(k);
                addPartitionItems(((Map) (obj)), builder.build(), i);
            }
            k++;
        }
        updateMeasurementListener();
    }
}
