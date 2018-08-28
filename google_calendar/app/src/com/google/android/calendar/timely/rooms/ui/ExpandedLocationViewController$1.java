// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;


// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            ExpandedLocationViewController, RoomUiItem

final class this._cls0
    implements this._cls0
{

    private final ExpandedLocationViewController this$0;

    public final void onItemClicked(RoomUiItem roomuiitem)
    {
label0:
        {
            if (listener != null)
            {
                if (roomuiitem.type != 3)
                {
                    break label0;
                }
                listener.onRoomRemoved(roomuiitem.room);
            }
            return;
        }
        listener.onRoomSelected(roomuiitem.room);
    }

    public final void onNextPageRequested()
    {
        if (listener != null)
        {
            listener.onNextPageRequested();
        }
    }

    stener()
    {
        this$0 = ExpandedLocationViewController.this;
        super();
    }
}
