// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.time.DateTimeUtils;
import com.google.android.calendar.timely.SuggestionRow;
import com.google.calendar.v2.client.service.api.time.DateTime;
import com.google.calendar.v2.client.service.api.time.Period;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely.findatime.ui:
//            FindTime2UiSuggestionFragment, DurationTimeframe

final class this._cls0
    implements android.widget..FindTime2UiSuggestionFragment._cls2
{

    private final FindTime2UiSuggestionFragment this$0;

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        if (view.getId() == 0x7f1001a5)
        {
            onClick(view);
        } else
        {
            adapterview = (SuggestionRow)listView.getItemAtPosition(i);
            switch (((SuggestionRow) (adapterview)).type)
            {
            case 1: // '\001'
            case 2: // '\002'
            default:
                return;

            case 0: // '\0'
                view = FindTime2UiSuggestionFragment.this;
                if (adapterview != null)
                {
                    ((FindTime2UiSuggestionFragment) (view)).listener.TimeSlotSelected(adapterview);
                    return;
                } else
                {
                    ((FindTime2UiSuggestionFragment) (view)).listener.Cancelled();
                    return;
                }

            case 3: // '\003'
                adapterview = FindTime2UiSuggestionFragment.this;
                view = AnalyticsLoggerHolder.instance;
                break;
            }
            if (view == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            ((AnalyticsLogger)view).trackEvent(((FindTime2UiSuggestionFragment) (adapterview)).context, "find_a_time", "suggestion_view", "try_later", null);
            adapterview = DateTimeUtils.getNowDateTime(timezone.getID());
            adapterview = durationTimeframe.getTimeframeEnd(adapterview).plusPeriod(new Period(0, 0, 0, 1, 0, 0, 0L));
            durationTimeframe.timeframeOption = 3;
            durationTimeframe.customDate = DateTimeUtils.getNowDateTime(timezone.getID()).withDate(adapterview.getYear(), adapterview.getMonthOfYear(), adapterview.getDayOfMonth());
            setTimeframeDurationLabel();
            adapterview = FindTime2UiSuggestionFragment.this;
            if (((FindTime2UiSuggestionFragment) (adapterview)).listener != null)
            {
                ((FindTime2UiSuggestionFragment) (adapterview)).listener.Query(((FindTime2UiSuggestionFragment) (adapterview)).durationTimeframe);
                return;
            }
        }
    }

    ()
    {
        this$0 = FindTime2UiSuggestionFragment.this;
        super();
    }
}
