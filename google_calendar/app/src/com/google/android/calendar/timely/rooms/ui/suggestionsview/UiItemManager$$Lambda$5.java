// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timely.rooms.data.AttendeeGroup;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.SuggestedRoom;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview:
//            UiItemManager

public final class arg._cls2
    implements Consumer
{

    private final UiItemManager arg$1;
    private final AttendeeGroup arg$2;

    public final void accept(Object obj)
    {
        UiItemManager uiitemmanager = arg$1;
        AttendeeGroup attendeegroup = arg$2;
        obj = (SuggestedRoom)obj;
        if (uiitemmanager.listener != null)
        {
            uiitemmanager.listener.cceptSuggestion(attendeegroup, ((SuggestedRoom) (obj)));
        }
    }

    public Y(UiItemManager uiitemmanager, AttendeeGroup attendeegroup)
    {
        arg$1 = uiitemmanager;
        arg$2 = attendeegroup;
    }
}
