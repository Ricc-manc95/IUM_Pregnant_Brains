// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.calendar.timely.CustomUserSuggestionListenerHolder;
import com.google.android.calendar.timely.TimelineSuggestion;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridDayView, FindTimeGridDayView

final class val.customSuggestion
    implements Runnable
{

    private final d this$1;
    private final TimelineSuggestion val$customSuggestion;

    public final void run()
    {
        onUpdate(val$customSuggestion, getJulianDay(), timezone);
        Object obj = CustomUserSuggestionListenerHolder.INSTANCE;
        TimelineSuggestion timelinesuggestion = val$customSuggestion;
        obj = ((CustomUserSuggestionListenerHolder) (obj)).createCustomSuggestionChangedListeners.iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            com.google.android.calendar.timely.tomSuggestionListener tomsuggestionlistener = (com.google.android.calendar.timely.tomSuggestionListener)((Iterator) (obj)).next();
            if (tomsuggestionlistener != null)
            {
                tomsuggestionlistener.onCreateCustomUserSuggestionChanged(timelinesuggestion);
            }
        } while (true);
    }

    ionListener()
    {
        this$1 = final_ionlistener;
        val$customSuggestion = TimelineSuggestion.this;
        super();
    }
}
