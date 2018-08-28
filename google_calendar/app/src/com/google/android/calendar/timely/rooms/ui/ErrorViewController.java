// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.calendar.timely.rooms.ui.roomtile.meetings.StructuredRoomTileFactoryImpl;
import com.google.android.calendar.timely.widgets.fullscreenerror.FullScreenErrorPage;
import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            RoomBookingHeaderAdapter, RoomUiItemViewProvider, RoomUiItemFactory

public final class ErrorViewController
{

    public final RoomBookingHeaderAdapter addedRoomsList;
    public final ViewGroup container;
    public final View contentView;
    public final FullScreenErrorPage errorPage;
    public final Predicate isRoomRemovable;
    public Listener listener;
    private final RoomUiItemFactory roomUiItemFactory;

    public ErrorViewController(Context context, ViewGroup viewgroup, Predicate predicate)
    {
        container = viewgroup;
        isRoomRemovable = predicate;
        contentView = LayoutInflater.from(context).inflate(0x7f050142, viewgroup, false);
        class .Lambda._cls0
            implements Consumer
        {

            private final ErrorViewController arg$1;

            public final void accept(Object obj)
            {
                ErrorViewController errorviewcontroller = arg$1;
                obj = (Room)obj;
                errorviewcontroller.listener.onRoomRemoved(((Room) (obj)));
            }

            .Lambda._cls0()
            {
                arg$1 = ErrorViewController.this;
            }

            private class Listener
            {

                public abstract void onRoomRemoved(Room room);

                public abstract void onTryAgainClicked();
            }

        }

        addedRoomsList = new RoomBookingHeaderAdapter((LinearLayout)contentView.findViewById(0x7f10033f), new RoomUiItemViewProvider(context, new StructuredRoomTileFactoryImpl(context, false)), new .Lambda._cls0());
        roomUiItemFactory = new RoomUiItemFactory(context);
        errorPage = (FullScreenErrorPage)contentView.findViewById(0x1020004);
        context = errorPage;
        class .Lambda._cls1
            implements android.view.View.OnClickListener
        {

            private final ErrorViewController arg$1;

            public final void onClick(View view)
            {
                view = arg$1;
                if (((ErrorViewController) (view)).listener != null)
                {
                    ((ErrorViewController) (view)).listener.onTryAgainClicked();
                }
            }

            .Lambda._cls1()
            {
                arg$1 = ErrorViewController.this;
            }
        }

        viewgroup = new .Lambda._cls1();
        ((FullScreenErrorPage) (context)).tryAgainButton.setVisibility(0);
        ((FullScreenErrorPage) (context)).tryAgainButton.setOnClickListener(viewgroup);
    }
}
