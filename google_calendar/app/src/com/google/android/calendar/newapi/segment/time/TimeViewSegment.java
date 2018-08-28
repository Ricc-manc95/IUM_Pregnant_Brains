// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.calendar.util.string.StringUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.newapi.model.RecurrenceHolder;
import com.google.android.calendar.newapi.model.TimeHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.newapi.segment.time:
//            TimeUtils

public final class TimeViewSegment extends TextTileView
    implements ViewSegment
{

    private final TimeHolder model;

    public TimeViewSegment(Context context, TimeHolder timeholder)
    {
        super(context);
        model = timeholder;
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        setIconDrawable(0x7f02022e);
        super.denseMode = false;
        setFocusable(true);
    }

    public final void updateUi()
    {
        Object obj;
        Object obj1;
        Object obj2;
        Joiner joiner;
        boolean flag;
        long l;
        boolean flag1;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        obj1 = Utils.getTimeZoneId(getContext());
        setSecondaryText(new CharSequence[] {
            null
        });
        obj2 = new StringBuilder();
        obj = new StringBuilder();
        flag1 = Utils.getDisplayedDateTimes(model.getStart(getContext()), model.getEnd(getContext()), l, ((String) (obj1)), model.isAllDay(), 0, getContext(), ((StringBuilder) (obj2)), ((StringBuilder) (obj)));
        if (flag1)
        {
            ((StringBuilder) (obj2)).append(getResources().getString(0x7f130150));
        }
        obj2 = StringUtils.capitalizeStandalone(((StringBuilder) (obj2)).toString(), Locale.getDefault());
        if (!flag1)
        {
            obj = StringUtils.capitalizeStandalone(((StringBuilder) (obj)).toString(), Locale.getDefault());
        }
        setPrimaryText(new CharSequence[] {
            obj2, obj
        });
        if (((RecurrenceHolder)model).getRecurrence() != null)
        {
            obj = getResources();
            obj2 = (RecurRulePart)((RecurrenceHolder)model).getRecurrence().rrules.get(0);
            obj = ((Resources) (obj)).getString(0x7f1303eb, new Object[] {
                TimeUtils.getRecurrenceString(getResources(), ((RecurRulePart) (obj2)), true, TimeUtils.DisplayForm.HEURISTIC)
            });
        } else
        {
            obj = null;
        }
        setSecondaryText(new CharSequence[] {
            obj
        });
        obj1 = Utils.getAccessibilityDateTimes(getContext(), 0, model.isAllDay(), model.getStart(getContext()), model.getEnd(getContext()), ((String) (obj1)));
        obj2 = getContext().getString(0x7f13016a);
        obj = obj1;
        if (obj1 == null)
        {
            obj = super.primaryLine.getText();
        }
        if (super.secondaryLine != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj1 = getSecondaryTextView().getText();
        } else
        {
            obj1 = null;
        }
        joiner = (new Joiner(String.valueOf('\n'))).skipNulls();
        obj = Arrays.asList(new CharSequence[] {
            obj2, obj, obj1
        }).iterator();
        setContentDescription(joiner.appendTo(new StringBuilder(), ((java.util.Iterator) (obj))).toString());
    }
}
