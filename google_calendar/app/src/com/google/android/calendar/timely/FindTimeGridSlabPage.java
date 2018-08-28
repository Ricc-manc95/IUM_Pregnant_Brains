// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.util.string.StringUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeGridSlabItem, FindTimeUtil, TimelineEvent, TimelineSuggestion

public final class FindTimeGridSlabPage extends RelativeLayout
{

    public int baseHeight;
    public Context context;
    public ImageView doneFab;
    public int extraLineHeight;
    public FindTimeGridSlabItem itemView;
    public OnSlabPageUpdatedListener listener;
    public FrameLayout slabBar;
    private final TimeZone timezone;

    public FindTimeGridSlabPage(Context context1, TimeZone timezone1)
    {
        super(context1);
        context = context1;
        timezone = timezone1;
        baseHeight = context.getResources().getDimensionPixelOffset(0x7f0e017f);
        extraLineHeight = context.getResources().getDimensionPixelOffset(0x7f0e0181);
        inflate(context, 0x7f05006c, this);
        slabBar = (FrameLayout)findViewById(0x7f1001a3);
        itemView = new FindTimeGridSlabItem(getContext(), timezone);
        itemView.listener = new _cls1();
        context1 = new android.widget.FrameLayout.LayoutParams(-1, -2, 16);
        slabBar.addView(itemView, context1);
        doneFab = (ImageView)findViewById(0x7f100146);
        doneFab.getViewTreeObserver().addOnGlobalLayoutListener(new _cls2());
    }

    public final void setTimelineSuggestion(TimelineSuggestion timelinesuggestion, String s, boolean flag)
    {
        Object obj;
label0:
        {
label1:
            {
                Object obj1 = new StringBuilder();
                StringBuilder stringbuilder = new StringBuilder();
                obj = FindTimeUtil.getInstance(getContext());
                Object obj3 = timezone.getID();
                long l1 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcStartMillis();
                long l3 = ((TimelineEvent) (timelinesuggestion)).timeRange.getUtcEndMillis();
                Resources resources;
                boolean flag1;
                long l;
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                Utils.getDisplayedDateTimesInTimezone(l1, l3, l, ((String) (obj3)), timelinesuggestion.isAllDay(), 16, ((FindTimeUtil) (obj)).context, ((StringBuilder) (obj1)), stringbuilder);
                obj3 = itemView;
                resources = getResources();
                obj = "";
                if (!flag)
                {
                    if (RtlUtils.isLayoutDirectionRtl(context))
                    {
                        obj = resources.getString(0x7f130064);
                    } else
                    {
                        obj = resources.getString(0x7f130063);
                    }
                }
                ((FindTimeGridSlabItem) (obj3)).setContentDescription(resources.getString(0x7f130062, new Object[] {
                    resources.getString(0x7f13005d, new Object[] {
                        obj1, stringbuilder, s
                    }), obj
                }));
                obj = itemView;
                obj1 = ((FindTimeGridSlabItem) (obj)).suggestion;
                if (obj1 == timelinesuggestion || obj1 != null && obj1.equals(timelinesuggestion))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    Object obj2 = ((FindTimeGridSlabItem) (obj)).suggestionDescription;
                    TimelineSuggestion timelinesuggestion1;
                    String s1;
                    long l2;
                    long l4;
                    if (obj2 == s || obj2 != null && obj2.equals(s))
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag1)
                    {
                        break label1;
                    }
                }
                obj.suggestion = timelinesuggestion;
                obj.suggestionDescription = s;
                timelinesuggestion = new StringBuilder();
                s = new StringBuilder();
                obj2 = FindTimeUtil.getInstance(((FindTimeGridSlabItem) (obj)).getContext());
                timelinesuggestion1 = ((FindTimeGridSlabItem) (obj)).suggestion;
                s1 = ((FindTimeGridSlabItem) (obj)).timezone.getID();
                l2 = ((TimelineEvent) (timelinesuggestion1)).timeRange.getUtcStartMillis();
                l4 = ((TimelineEvent) (timelinesuggestion1)).timeRange.getUtcEndMillis();
                if (Clock.mockedTimestamp > 0L)
                {
                    l = Clock.mockedTimestamp;
                } else
                {
                    l = System.currentTimeMillis();
                }
                Utils.getDisplayedDateTimesInTimezone(l2, l4, l, s1, timelinesuggestion1.isAllDay(), 16, ((FindTimeUtil) (obj2)).context, timelinesuggestion, s);
                if (((FindTimeGridSlabItem) (obj)).dateTimeView != null)
                {
                    timelinesuggestion = ((FindTimeGridSlabItem) (obj)).getResources().getString(0x7f13020d, new Object[] {
                        timelinesuggestion, s
                    });
                    ((FindTimeGridSlabItem) (obj)).dateTimeView.setText(StringUtils.capitalizeStandalone(timelinesuggestion, Locale.getDefault()));
                } else
                {
                    ((FindTimeGridSlabItem) (obj)).dateView.setText(StringUtils.capitalizeStandalone(timelinesuggestion.toString(), Locale.getDefault()));
                    ((FindTimeGridSlabItem) (obj)).timeView.setText(StringUtils.capitalizeStandalone(s.toString(), Locale.getDefault()));
                }
                ((FindTimeGridSlabItem) (obj)).descriptionView.getViewTreeObserver().addOnGlobalLayoutListener(new FindTimeGridSlabItem._cls1(((FindTimeGridSlabItem) (obj))));
                if (!TextUtils.isEmpty(((FindTimeGridSlabItem) (obj)).suggestionDescription))
                {
                    break label0;
                }
                ((FindTimeGridSlabItem) (obj)).descriptionView.setVisibility(8);
            }
            return;
        }
        ((FindTimeGridSlabItem) (obj)).descriptionView.setText(((FindTimeGridSlabItem) (obj)).suggestionDescription);
        ((FindTimeGridSlabItem) (obj)).descriptionView.setVisibility(0);
    }

    private class _cls1
        implements FindTimeGridSlabItem.OnSlabItemUpdatedListener
    {

        public final FindTimeGridSlabPage this$0;

        public final void onDescriptionNumLinesUpdated(int i)
        {
            class _cls1
                implements android.view.ViewTreeObserver.OnGlobalLayoutListener
            {

                private final _cls1 this$1;

                public final void onGlobalLayout()
                {
                    slabBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    if (listener != null)
                    {
                        listener.onSlabBarHeightUpdated(_fld0);
                    }
                }

                _cls1()
                {
                    this$1 = _cls1.this;
                    super();
                }

                private class OnSlabPageUpdatedListener
                {

                    public abstract void onSlabBarHeightUpdated(FindTimeGridSlabPage findtimegridslabpage);
                }

            }

            slabBar.getViewTreeObserver().addOnGlobalLayoutListener(new _cls1());
            FrameLayout framelayout = slabBar;
            FindTimeGridSlabPage findtimegridslabpage = FindTimeGridSlabPage.this;
            int j = findtimegridslabpage.baseHeight;
            framelayout.setMinimumHeight(findtimegridslabpage.extraLineHeight * i + j);
        }

        _cls1()
        {
            this$0 = FindTimeGridSlabPage.this;
            super();
        }
    }


    private class _cls2
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        private final FindTimeGridSlabPage this$0;

        public final void onGlobalLayout()
        {
            doneFab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)doneFab.getLayoutParams();
            marginlayoutparams.topMargin = (doneFab.getHeight() * -1) / 2;
            doneFab.setLayoutParams(marginlayoutparams);
        }

        _cls2()
        {
            this$0 = FindTimeGridSlabPage.this;
            super();
        }
    }

}
