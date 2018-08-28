// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.view.View;
import android.widget.AdapterView;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomListView, RoomUiItem

final class arg._cls1
    implements android.widget.Listener
{

    private final RoomListView arg$1;

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        arg$1.listener.nItemClicked((RoomUiItem)view.getTag());
    }

    (RoomListView roomlistview)
    {
        arg$1 = roomlistview;
    }
}
