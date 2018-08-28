// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.executors.ThrottlingExecutor;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            RoomBookingController

final class throttlingExecutor
    implements com.google.android.calendar.common.view.edittexttoolbar.ttlingExecutor
{

    public final RoomBookingController this$0;
    private final Executor throttlingExecutor;

    public final void backPressed()
    {
        onBack();
    }

    public final void onFocus(String s)
    {
        if (state == 0)
        {
            updateRequestAndLog(s);
            s = RoomBookingController.this;
            s.persistViewControllerState();
            s.detachView();
            s.state = 4;
            s.attachView();
        }
    }

    public final void onRightButtonPressed()
    {
        onBack();
    }

    public final void searchStringChanged(String s)
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final RoomBookingController.ToolbarCallback arg$1;
            private final String arg$2;

            public final void run()
            {
                Object obj1 = arg$1;
                Object obj = arg$2;
                obj1 = ((RoomBookingController.ToolbarCallback) (obj1)).this$0;
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

            .Lambda._cls0(String s)
            {
                arg$1 = RoomBookingController.ToolbarCallback.this;
                arg$2 = s;
            }
        }

        throttlingExecutor.execute(new .Lambda._cls0(s));
    }

    .Lambda._cls0()
    {
        this$0 = RoomBookingController.this;
        super();
        throttlingExecutor = new ThrottlingExecutor(CalendarExecutor.MAIN, 500L);
    }
}
