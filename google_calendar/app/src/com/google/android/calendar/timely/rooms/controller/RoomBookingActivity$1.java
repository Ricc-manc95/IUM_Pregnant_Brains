// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timely.net.AuthExceptionUtils;
import com.google.android.calendar.timely.rooms.data.Room;
import com.google.android.calendar.utils.network.NetworkUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            RoomBookingActivity

final class this._cls0
    implements Delegate
{

    private final RoomBookingActivity this$0;

    public final boolean isOnline()
    {
        return NetworkUtil.isConnectedToInternet(RoomBookingActivity.this);
    }

    public final void onConnectionError(Throwable throwable)
    {
        if (AuthExceptionUtils.isUserRecoverableAuthException(throwable))
        {
            startActivityForResult(AuthExceptionUtils.getAuthIntent(throwable), 2001);
            return;
        } else
        {
            LogUtils.e(RoomBookingActivity.TAG, throwable, "Unrecoverable connection error", new Object[0]);
            return;
        }
    }

    public final void onFinish(List list)
    {
        RoomBookingActivity roombookingactivity = RoomBookingActivity.this;
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        Room room;
        for (list = list.iterator(); list.hasNext(); arraylist1.add(room.getEmail()))
        {
            room = (Room)list.next();
            arraylist.add(room.getName());
        }

        roombookingactivity.setResult(-1, (new Intent()).putStringArrayListExtra("intent_key_room_emails", arraylist1).putStringArrayListExtra("intent_key_room_names", arraylist));
        finish();
    }

    public final void onWindowStateChanged()
    {
        getWindow().getDecorView().sendAccessibilityEvent(32);
    }

    Delegate()
    {
        this$0 = RoomBookingActivity.this;
        super();
    }
}
