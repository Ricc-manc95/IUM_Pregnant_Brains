// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.timeline.chip.Chip;
import com.google.android.calendar.timeline.chip.ChipViewModel;
import com.google.android.calendar.timely.FindTimeAttendee;
import com.google.android.calendar.timely.FindTimeUtil;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.timely.TimelineSuggestion;
import com.google.android.calendar.timely.gridviews.geometry.GridChipGeometry;
import com.google.android.calendar.timely.gridviews.geometry.PositionOnGrid;
import com.google.android.calendar.timely.timeline.TimelineItemCollection;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView

public class FindTimeGridDayView extends GridDayView
    implements com.google.android.calendar.timely.CustomUserSuggestionListenerHolder.OnCreateCustomSuggestionListener
{
    final class FindTimeGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public final FindTimeGridDayView this$0;

        public final boolean onDown(MotionEvent motionevent)
        {
            return isSuggestionView;
        }

        public final boolean onSingleTapUp(MotionEvent motionevent)
        {
            if (!isSuggestionView)
            {
                return false;
            }
            final TimelineSuggestion customSuggestion = FindTimeGridDayView.this;
            int j = Math.min(((int)motionevent.getY() * 24) / customSuggestion.getHeight(), 23);
            customSuggestion = FindTimeGridDayView.this;
            float f = ((float)(int)motionevent.getY() * 24F) / (float)customSuggestion.getHeight();
            int i = (int)((f - (float)(int)f) * 60F);
            int l = j * 60 + i;
            String s;
            TimelineSuggestion timelinesuggestion;
            FindTimeUtil findtimeutil;
            int i1;
            long l1;
            long l2;
            long l3;
            if (i < 30)
            {
                i = 0;
            } else
            {
                i = 30;
            }
            findtimeutil = FindTimeUtil.getInstance(context);
            s = accountType;
            timelinesuggestion = suggestion;
            i1 = getJulianDay();
            customSuggestion = timezone;
            motionevent = customSuggestion;
            if (customSuggestion == null)
            {
                motionevent = Utils.getTimeZone(findtimeutil.context);
            }
            customSuggestion = new Time();
            customSuggestion.timezone = motionevent.getID();
            l1 = Time.calculateDayHourMinuteMillis(i1, j, i, ((Time) (customSuggestion)).timezone, customSuggestion);
            l2 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcEndMillis();
            l3 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcStartMillis();
            customSuggestion = new TimelineSuggestion(timelinesuggestion);
            customSuggestion.isCustom = true;
            customSuggestion.timeRange = TimeRange.newInstance(motionevent, false, l1, (l2 - l3) + l1);
            if (AccountUtil.isGoogleExchangeType(s) || AccountUtil.isGoogleExchangeGoAccount(s))
            {
                ImmutableList immutablelist = customSuggestion.attendees;
                motionevent = new ArrayList();
                immutablelist = (ImmutableList)immutablelist;
                int k = immutablelist.size();
                i = 0;
label0:
                do
                {
                    if (i >= k)
                    {
                        break;
                    }
                    Object obj = immutablelist.get(i);
                    i++;
                    obj = (FindTimeAttendee)obj;
                    int j1 = ((TimelineEvent) (customSuggestion)).timeRange.getStartDay();
                    Object obj1 = (List)((FindTimeAttendee) (obj)).daysToEvents.get(j1);
                    if (obj1 == null)
                    {
                        continue;
                    }
                    obj1 = ((List) (obj1)).iterator();
                    TimelineEvent timelineevent;
                    do
                    {
                        if (!((Iterator) (obj1)).hasNext())
                        {
                            continue label0;
                        }
                        timelineevent = (TimelineEvent)((Iterator) (obj1)).next();
                    } while (!((TimelineEvent) (customSuggestion)).timeRange.intersects(timelineevent.timeRange));
                    motionevent.add(obj);
                } while (true);
                customSuggestion.attendeeExplanations = FindTimeUtil.convertToExplanationForExchange(motionevent);
            }
            boolean flag;
            if (l < ((TimelineEvent) (suggestion)).timeRange.getStartMinute())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (((TimelineEvent) (suggestion)).timeRange.getEndDay() == getJulianDay())
            {
                if (((TimelineEvent) (suggestion)).timeRange.getEndMinute() <= l)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
            } else
            {
                i = 0;
            }
            if (flag || i)
            {
                playSoundEffect(0);
                class _cls1
                    implements Runnable
                {

                    private final FindTimeGestureListener this$1;
                    private final TimelineSuggestion val$customSuggestion;

                    public final void run()
                    {
                        onUpdate(customSuggestion, getJulianDay(), timezone);
                        Object obj2 = CustomUserSuggestionListenerHolder.INSTANCE;
                        TimelineSuggestion timelinesuggestion1 = customSuggestion;
                        obj2 = ((CustomUserSuggestionListenerHolder) (obj2)).createCustomSuggestionChangedListeners.iterator();
                        do
                        {
                            if (!((Iterator) (obj2)).hasNext())
                            {
                                break;
                            }
                            com.google.android.calendar.timely.CustomUserSuggestionListenerHolder.OnCreateCustomSuggestionListener oncreatecustomsuggestionlistener = (com.google.android.calendar.timely.CustomUserSuggestionListenerHolder.OnCreateCustomSuggestionListener)((Iterator) (obj2)).next();
                            if (oncreatecustomsuggestionlistener != null)
                            {
                                oncreatecustomsuggestionlistener.onCreateCustomUserSuggestionChanged(timelinesuggestion1);
                            }
                        } while (true);
                    }

                _cls1()
                {
                    this$1 = FindTimeGestureListener.this;
                    customSuggestion = timelinesuggestion;
                    super();
                }
                }

                ((ViewGroup)getParent()).getHandler().post(new _cls1());
            }
            return true;
        }

        FindTimeGestureListener()
        {
            this$0 = FindTimeGridDayView.this;
            super();
        }
    }


    public static final Interpolator AUTO_SCROLL_INTERPOLATOR = new FastOutSlowInInterpolator();
    public String accountName;
    public String accountType;
    private RectF borderRect;
    public final Context context;
    public boolean isMoreAttendeeColumn;
    public boolean isSuggestionView;
    private final int moreAttendeeShadeCornerRadius;
    private final int moreAttendeeShadeLeftPadding;
    private final Paint moreAttendeeShadePaint = new Paint();
    public TimelineSuggestion suggestion;
    public TimeZone timezone;

    public FindTimeGridDayView(Context context1, AttributeSet attributeset)
    {
        super(context1, attributeset);
        borderRect = new RectF();
        attributeset = context1.getResources();
        context = context1;
        mGestureDetector = new GestureDetector(context1, new FindTimeGestureListener());
        moreAttendeeShadePaint.setColor(attributeset.getColor(0x7f0d0214));
        moreAttendeeShadeLeftPadding = attributeset.getDimensionPixelSize(0x7f0e01db);
        moreAttendeeShadeCornerRadius = attributeset.getDimensionPixelSize(0x7f0e035f);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        return mGestureDetector.onTouchEvent(motionevent);
    }

    public final void onCreateCustomUserSuggestionChanged(TimelineSuggestion timelinesuggestion)
    {
        if (isMoreAttendeeColumn)
        {
            return;
        } else
        {
            onUpdate(new ArrayList(super.items.persistentItemsView), getJulianDay(), timelinesuggestion, null);
            return;
        }
    }

    protected void onDraw(Canvas canvas)
    {
        if (isMoreAttendeeColumn)
        {
            borderRect.set(moreAttendeeShadeLeftPadding, 0.0F, getWidth(), getHeight());
            canvas.drawRoundRect(borderRect, moreAttendeeShadeCornerRadius, moreAttendeeShadeCornerRadius, moreAttendeeShadePaint);
        }
        super.onDraw(canvas);
    }

    public final void onUpdate(MonthData monthdata, int i)
    {
        throw new UnsupportedOperationException("GridDayView's onUpdate should not be used on FindTimeGridDayView.");
    }

    public final void onUpdate(TimelineSuggestion timelinesuggestion, int i, TimeZone timezone1)
    {
        isSuggestionView = true;
        timezone = timezone1;
        timezone1 = new ArrayList();
        timezone1.add(timelinesuggestion);
        onUpdate(((List) (timezone1)), i, timelinesuggestion, null);
    }

    public final void onUpdate(List list, int i, TimelineSuggestion timelinesuggestion, String s)
    {
        isMoreAttendeeColumn = false;
        setJulianDay(i);
        suggestion = timelinesuggestion;
        clearChips();
        if (list == null)
        {
            return;
        }
        com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder builder = new com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfo.Builder();
        builder.showMultidayAnnotations = true;
        builder.viewType = Integer.valueOf(1);
        builder.contentDescriptionPrefix = s;
        s = builder.build();
        Iterator iterator1 = list.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            TimelineItem timelineitem = (TimelineItem)iterator1.next();
            if (timelineitem != null && (!timelineitem.spansAtLeastOneDay() || !TimelineItemUtil.isTransparent(timelineitem)))
            {
                if (timelineitem == timelinesuggestion)
                {
                    builder.contentDescriptionPrefix = getResources().getString(0x7f130471);
                    builder.contentDescriptionSuffix = FindTimeUtil.getInstance(context).getDescription(timelinesuggestion, accountName, accountType);
                    list = builder.build();
                } else
                {
                    list = s;
                }
                addChip(timelineitem, list, i, false);
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
            timelinesuggestion = (Chip)list.next();
            s = (PositionOnGrid)((GridDayView.LayoutParams)timelinesuggestion.getLayoutParams()).position;
            if (((Chip) (timelinesuggestion)).viewModel.getBorderColor() == 0)
            {
                Iterator iterator = super.items.allChipsView.iterator();
                i = 0;
                while (iterator.hasNext()) 
                {
                    Chip chip = (Chip)iterator.next();
                    PositionOnGrid positionongrid = (PositionOnGrid)((GridDayView.LayoutParams)chip.getLayoutParams()).position;
                    boolean flag;
                    if (timelinesuggestion != chip && s.overlaps(positionongrid) && ((PositionOnGrid) (s)).z > positionongrid.z && chip.viewModel.getBorderColor() == 0)
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
                timelinesuggestion.setViewModel(((Chip) (timelinesuggestion)).viewModel.toBuilder().setOuterBorderColor(i).build());
            }
        } while (true);
        requestLayout();
    }

    public final void updateCreateNewEventView()
    {
    }

}
