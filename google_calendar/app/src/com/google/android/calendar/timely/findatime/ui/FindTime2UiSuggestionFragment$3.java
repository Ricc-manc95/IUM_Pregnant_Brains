// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.view.View;
import android.widget.AdapterView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timely.SuggestionRow;

// Referenced classes of package com.google.android.calendar.timely.findatime.ui:
//            FindTime2UiSuggestionFragment

final class this._cls0
    implements android.widget..FindTime2UiSuggestionFragment._cls3
{

    private final FindTime2UiSuggestionFragment this$0;

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        if (adapter.getItemViewType(i) == 1)
        {
            LogUtils.wtf(FindTime2UiSuggestionFragment.TAG, "Cannot get the suggestion for a header %d", new Object[] {
                Integer.valueOf(i)
            });
            return;
        } else
        {
            listener.TimeSlotMoreSelected(((SuggestionRow)adapter.getItem(i)).suggestion);
            return;
        }
    }

    ggestionAdapter()
    {
        this$0 = FindTime2UiSuggestionFragment.this;
        super();
    }
}
