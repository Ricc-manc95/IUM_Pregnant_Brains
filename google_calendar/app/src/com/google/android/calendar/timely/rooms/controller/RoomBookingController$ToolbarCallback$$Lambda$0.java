// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import com.google.android.calendar.timely.rooms.net.RoomRequest;
import com.google.android.calendar.timely.rooms.ui.RoomListView;
import com.google.android.calendar.timely.rooms.ui.RoomListViewController;
import com.google.android.calendar.timely.widgets.fullscreenerror.FullScreenErrorPage;
import java.util.Collections;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            RoomBookingController

final class arg._cls2
    implements Runnable
{

    private final  arg$1;
    private final String arg$2;

    public final void run()
    {
        Object obj1 = arg$1;
        Object obj = arg$2;
        obj1 = ((arg._cls2) (obj1))._fld2;
        if (((RoomBookingController) (obj1)).state == 0 && !TextUtils.isEmpty(((CharSequence) (obj))))
        {
            ((RoomBookingController) (obj1)).persistViewControllerState();
            ((RoomBookingController) (obj1)).detachView();
            obj1.state = 4;
            ((RoomBookingController) (obj1)).attachView();
        }
        if (((RoomBookingController) (obj1)).state == 4)
        {
            boolean flag;
            if (!TextUtils.isEmpty(((RoomBookingController) (obj1)).request.getQuery()))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                RoomListViewController roomlistviewcontroller = ((RoomBookingController) (obj1)).roomListViewController;
                roomlistviewcontroller.savedScrollPosition = roomlistviewcontroller.roomList.listView.getFirstVisiblePosition();
            }
        }
        ((RoomBookingController) (obj1)).updateRequestAndLog(((String) (obj)));
        obj = ((RoomBookingController) (obj1)).roomListViewController;
        ((RoomListViewController) (obj)).mainView.setVisibility(0);
        ((RoomListViewController) (obj)).emptyStateView.setVisibility(8);
        ((RoomListViewController) (obj)).roomList.setItems(Collections.emptyList());
        obj.hasShowMoreItem = false;
        ((RoomListViewController) (obj)).roomList.setFooterMode(2);
        ((RoomBookingController) (obj1)).sendRoomsRequest(false);
    }

    i(i i, String s)
    {
        arg$1 = i;
        arg$2 = s;
    }
}
