// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.grid.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.geometry.GridChipGeometry;
import com.google.android.calendar.timely.gridviews.geometry.PositionOnGrid;
import com.google.android.calendar.timely.interaction.helper.DelayedUpdateHelper;
import com.google.android.calendar.timely.timeline.TimelineItemCollection;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class ProposeNewTimeGridDayView extends GridDayView
{
    final class GestureListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        private final ProposeNewTimeGridDayView this$0;

        public final boolean onDown(MotionEvent motionevent)
        {
            return tapListener != null;
        }

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            if (tapListener == null)
            {
                return false;
            }
            ProposeNewTimeGridDayView proposenewtimegriddayview = ProposeNewTimeGridDayView.this;
            int j = Math.min(((int)motionevent.getY() * 24) / proposenewtimegriddayview.getHeight(), 23);
            proposenewtimegriddayview = ProposeNewTimeGridDayView.this;
            float f = ((float)(int)motionevent.getY() * 24F) / (float)proposenewtimegriddayview.getHeight();
            int k = (int)((f - (float)(int)f) * 60F);
            int i = j * 60 + k;
            boolean flag;
            if (currentProposal != null && i < currentProposal.getStartTime())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (currentProposal != null && currentProposal.getEndDay() == getJulianDay() && currentProposal.getEndTime() <= i)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (currentProposal == null || flag || i)
            {
                playSoundEffect(0);
                tapListener.onTap(j, k);
            }
            return true;
        }

        GestureListener()
        {
            this$0 = ProposeNewTimeGridDayView.this;
            super();
        }
    }

    public static interface TapListener
    {

        public abstract void onTap(int i, int j);
    }


    private final RectF borderRect = new RectF();
    public TimelineItem currentProposal;
    private final int disabledShadeCornerRadius = getResources().getDimensionPixelSize(0x7f0e035f);
    private final int disabledShadeLeftPadding = getResources().getDimensionPixelSize(0x7f0e01db);
    private final Paint disabledShadePaint = new Paint();
    public boolean isDisabled;
    public boolean longEventVisibility;
    public TapListener tapListener;

    public ProposeNewTimeGridDayView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        longEventVisibility = false;
        mGestureDetector = new GestureDetector(context, new GestureListener());
        disabledShadePaint.setColor(ContextCompat.getColor(getContext(), 0x7f0d0214));
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        return mGestureDetector.onTouchEvent(motionevent);
    }

    protected final DelayedUpdateHelper getDelayedUpdateHelper()
    {
        return null;
    }

    protected void onDraw(Canvas canvas)
    {
        if (isDisabled)
        {
            borderRect.set(disabledShadeLeftPadding, 0.0F, getWidth(), getHeight());
            canvas.drawRoundRect(borderRect, disabledShadeCornerRadius, disabledShadeCornerRadius, disabledShadePaint);
        }
        super.onDraw(canvas);
    }

    public final void postUpdate(MonthData monthdata, int i, com.google.android.calendar.timely.RangedData.UpdateFinishedListener updatefinishedlistener)
    {
        throw new UnsupportedOperationException("Class doesn't support delayed updates");
    }

    public final void setChips(int i, List list)
    {
        isDisabled = false;
        setJulianDay(i);
        clearChips();
        Object obj = new com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder();
        obj.showMultidayAnnotations = true;
        obj.viewType = Integer.valueOf(1);
        obj = ((com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder) (obj)).build();
        list = list.iterator();
        do
        {
            if (!list.hasNext())
            {
                break;
            }
            TimelineItem timelineitem = (TimelineItem)list.next();
            if (!timelineitem.spansAtLeastOneDay() || longEventVisibility)
            {
                addChip(timelineitem, ((com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo) (obj)), getJulianDay(), false);
            }
        } while (true);
        list = super.items.allChipsView;
        super.chipGeometry.updateChipsLayoutParams(Lists.newArrayList(list));
        list = super.items.allChipsView.iterator();
        do
        {
            if (!list.hasNext())
            {
                break;
            }
            Chip chip = (Chip)list.next();
            PositionOnGrid positionongrid = (PositionOnGrid)((com.google.android.calendar.timely.gridviews.GridDayView.LayoutParams)chip.getLayoutParams()).position;
            if (chip.viewModel.getBorderColor() == 0)
            {
                Iterator iterator = super.items.allChipsView.iterator();
                i = 0;
                while (iterator.hasNext()) 
                {
                    Chip chip1 = (Chip)iterator.next();
                    PositionOnGrid positionongrid1 = (PositionOnGrid)((com.google.android.calendar.timely.gridviews.GridDayView.LayoutParams)chip1.getLayoutParams()).position;
                    boolean flag;
                    if (chip != chip1 && positionongrid.overlaps(positionongrid1) && positionongrid.z > positionongrid1.z && chip1.viewModel.getBorderColor() == 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    i = flag | i;
                }
                if (i != 0)
                {
                    i = -1;
                } else
                {
                    i = 0;
                }
                chip.setViewModel(chip.viewModel.toBuilder().setOuterBorderColor(i).build());
            }
        } while (true);
        requestLayout();
    }
}
