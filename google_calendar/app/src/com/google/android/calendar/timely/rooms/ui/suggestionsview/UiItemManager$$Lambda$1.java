// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import com.google.android.calendar.timely.rooms.data.AttendeeGroup;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview:
//            UiItemManager

final class arg._cls2
    implements Runnable
{

    private final UiItemManager arg$1;
    private final AttendeeGroup arg$2;

    public final void run()
    {
        UiItemManager uiitemmanager = arg$1;
        AttendeeGroup attendeegroup = arg$2;
        if (((Boolean)uiitemmanager.nodeExpansionStateByHierarchyNodeId.get(attendeegroup.getHierarchyNodeId())).booleanValue())
        {
            uiitemmanager.collapseLocation(attendeegroup);
        } else
        {
            uiitemmanager.nodeExpansionStateByHierarchyNodeId.put(attendeegroup.getHierarchyNodeId(), Boolean.valueOf(true));
            if (uiitemmanager.listener != null)
            {
                uiitemmanager.listener.xpandSection();
                return;
            }
        }
    }

    Y(UiItemManager uiitemmanager, AttendeeGroup attendeegroup)
    {
        arg$1 = uiitemmanager;
        arg$2 = attendeegroup;
    }
}
