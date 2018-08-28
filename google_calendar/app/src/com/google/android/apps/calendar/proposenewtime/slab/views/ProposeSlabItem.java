// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.slab.views;

import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;
import com.google.android.apps.calendar.util.string.StringUtils;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.common.view.NinjaEditText;
import com.google.android.calendar.time.clock.Clock;
import java.util.Calendar;
import java.util.Locale;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime.slab.views:
//            SlabItem

public final class ProposeSlabItem extends SlabItem
{

    public OnCommentChangeListener commentChangeListener;
    private final NinjaEditText commentView = (NinjaEditText)findViewById(0x7f100307);
    private final TextView endDate = (TextView)findViewById(0x7f100304);
    private final TextView endTime = (TextView)findViewById(0x7f100306);
    private final TextView startDate = (TextView)findViewById(0x7f100303);
    private final TextView startTime = (TextView)findViewById(0x7f100305);

    public ProposeSlabItem(Context context)
    {
        super(context);
        inflate(context, 0x7f050131, this);
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            startTime.setAccessibilityTraversalAfter(startDate.getId());
            endTime.setAccessibilityTraversalAfter(endDate.getId());
        }
        commentView.addTextChangedListener(new _cls1());
    }

    public final void setCommentChangeListener(OnCommentChangeListener oncommentchangelistener)
    {
        commentChangeListener = oncommentchangelistener;
    }

    public final void setEndDateClickListener(android.view.View.OnClickListener onclicklistener)
    {
        endDate.setOnClickListener(onclicklistener);
        endDate.setClickable(true);
    }

    public final void setEndTimeClickListener(android.view.View.OnClickListener onclicklistener)
    {
        endTime.setOnClickListener(onclicklistener);
        endTime.setClickable(true);
    }

    public final void setStartDateClickListener(android.view.View.OnClickListener onclicklistener)
    {
        startDate.setOnClickListener(onclicklistener);
        startDate.setClickable(true);
    }

    public final void setStartTimeClickListener(android.view.View.OnClickListener onclicklistener)
    {
        startTime.setOnClickListener(onclicklistener);
        startTime.setClickable(true);
    }

    public final void setTimeProposal(TimeProposal timeproposal)
    {
        boolean flag = true;
        Object obj = Utils.getTimeZoneId(getContext(), null);
        Object obj1 = Utils.formatDateTime(getContext(), timeproposal.getStartTimeMillis(), 0x18016, ((String) (obj)));
        startDate.setText(StringUtils.capitalizeStandalone(((String) (obj1)), Locale.getDefault()));
        startDate.setContentDescription(getResources().getString(0x7f13006d, new Object[] {
            obj1
        }));
        obj1 = DateTimeFormatHelper.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        }
        obj1 = ((DateTimeFormatHelper)obj1).getTimeRangeText(timeproposal.getStartTimeMillis(), timeproposal.getStartTimeMillis(), 0);
        startTime.setText(StringUtils.capitalizeStandalone(((String) (obj1)), Locale.getDefault()));
        startTime.setContentDescription(getResources().getString(0x7f13006e, new Object[] {
            obj1
        }));
        obj = Utils.formatDateTime(getContext(), timeproposal.getEndTimeMillis(), 0x18016, ((String) (obj)));
        endDate.setText(StringUtils.capitalizeStandalone(((String) (obj)), Locale.getDefault()));
        endDate.setContentDescription(getResources().getString(0x7f13006b, new Object[] {
            obj
        }));
        obj = DateTimeFormatHelper.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        }
        obj = ((DateTimeFormatHelper)obj).getTimeRangeText(timeproposal.getEndTimeMillis(), timeproposal.getEndTimeMillis(), 0);
        endTime.setText(StringUtils.capitalizeStandalone(((String) (obj)), Locale.getDefault()));
        endTime.setContentDescription(getResources().getString(0x7f13006c, new Object[] {
            obj
        }));
        if (!timeproposal.getComment().contentEquals(commentView.getText()))
        {
            obj = commentView;
            obj1 = timeproposal.getComment();
            obj.stealth = true;
            ((NinjaEditText) (obj)).setText(((CharSequence) (obj1)));
            obj.stealth = false;
        }
        int i;
        long l;
        boolean flag1;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        if (l < timeproposal.getStartTimeMillis())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        obj = Utils.getTimeZone(getContext());
        l = timeproposal.getStartTimeMillis();
        obj = Calendar.getInstance(((java.util.TimeZone) (obj)));
        ((Calendar) (obj)).setTimeInMillis(l);
        obj1 = Utils.getTimeZone(getContext());
        l = timeproposal.getEndTimeMillis();
        timeproposal = Calendar.getInstance(((java.util.TimeZone) (obj1)));
        timeproposal.setTimeInMillis(l);
        flag1 = Utils.isValidEventTime(((Calendar) (obj)), timeproposal, false);
        if (i != 0 && flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = getResources().getColor(0x7f0d00aa);
        } else
        {
            i = getResources().getColor(0x7f0d00a9);
        }
        startDate.setTextColor(i);
        startTime.setTextColor(i);
    }

    private class _cls1
        implements TextWatcher
    {

        private final ProposeSlabItem this$0;

        public final void afterTextChanged(Editable editable)
        {
            ProposeSlabItem proposeslabitem = ProposeSlabItem.this;
            editable = editable.toString();
            proposeslabitem.commentChangeListener.onCommentChanged(editable);
        }

        public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        _cls1()
        {
            this$0 = ProposeSlabItem.this;
            super();
        }

        private class OnCommentChangeListener
        {

            public abstract void onCommentChanged(String s);
        }

    }

}
