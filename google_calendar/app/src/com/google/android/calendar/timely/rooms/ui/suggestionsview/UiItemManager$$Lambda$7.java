// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.AddedRoom;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview:
//            UiItemManager

final class arg._cls1
    implements Consumer
{

    private final UiItemManager arg$1;

    public final void accept(Object obj)
    {
        UiItemManager uiitemmanager = arg$1;
        obj = (AddedRoom)obj;
        if (uiitemmanager.listener != null)
        {
            uiitemmanager.listener.emoveRoom(((AddedRoom) (obj)));
        }
    }

    Y(UiItemManager uiitemmanager)
    {
        arg$1 = uiitemmanager;
    }
}
