// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.smartmail;

import android.view.View;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.smartmail.FlightReservation;
import com.google.android.calendar.api.event.smartmail.SmartMailAction;
import com.google.android.calendar.api.event.smartmail.SmartMailActionTarget;
import com.google.android.calendar.api.event.smartmail.SmartMailInfo;
import com.google.android.calendar.newapi.commandbar.BottomBar;
import com.google.android.calendar.newapi.commandbar.BottomBarController;
import com.google.android.calendar.newapi.commandbar.CommandBar;
import com.google.android.calendar.newapi.commandbar.CommandBarController;
import com.google.android.calendar.newapi.model.BasicViewScreenModel;
import com.google.android.calendar.time.clock.Clock;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen.smartmail:
//            SmartMailViewScreenModel

public final class SmartMailCommandBarController extends CommandBarController
{

    public SmartMailCommandBarController(Callback callback)
    {
        super(callback);
    }

    protected final int getActionsLayout()
    {
        return 0x7f0500e7;
    }

    protected final int[] getPrimaryActionIds()
    {
        return (new int[] {
            0x7f100297
        });
    }

    protected final void onCommandBarActionClick(Object obj, int i)
    {
        ((Callback)obj).onCheckInClicked(((SmartMailAction)((BasicViewScreenModel) ((SmartMailViewScreenModel)super.model)).event.getSmartMailInfo().actions.get(0)).goToAction.uri);
    }

    protected final void onModelChanged(Object obj)
    {
        obj = (SmartMailViewScreenModel)obj;
        Object obj1 = ((BasicViewScreenModel) (obj)).event.getSmartMailInfo();
        obj = ((BasicViewScreenModel) (obj)).event;
        BottomBar bottombar;
        int i;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        if (l - 0x36ee80L < ((Event) (obj)).getStartMillis())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (obj1 == null || ((SmartMailInfo) (obj1)).flightReservations.isEmpty())
        {
            obj = null;
        } else
        {
            obj = (FlightReservation)((SmartMailInfo) (obj1)).flightReservations.get(0);
        }
        if (obj1 == null || ((SmartMailInfo) (obj1)).actions.isEmpty())
        {
            obj1 = null;
        } else
        {
            obj1 = (SmartMailAction)((SmartMailInfo) (obj1)).actions.get(0);
        }
        bottombar = super.commandBar;
        if (i != 0 && obj != null && obj1 != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (bottombar != null)
        {
            if (i != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            bottombar.setVisibility(i);
        }
    }

    protected final void setupCommandBar(BottomBar bottombar)
    {
        ((CommandBar)bottombar).setDescription(null);
    }

    private class Callback
    {

        public abstract void onCheckInClicked(String s);
    }

}
