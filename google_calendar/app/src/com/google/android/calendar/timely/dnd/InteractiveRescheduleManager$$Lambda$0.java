// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.dnd;

import android.app.Activity;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.newapi.overflow.GuestNotificationDialogUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.calendar.timely.dnd:
//            InteractiveRescheduleManager

final class arg._cls1
    implements AsyncFunction
{

    private final InteractiveRescheduleManager arg$1;

    public final ListenableFuture apply(Object obj)
    {
        InteractiveRescheduleManager interactivereschedulemanager = arg$1;
        obj = (Event)obj;
        Object obj1;
        boolean flag;
        if (obj != null && AttendeeUtils.hasGuests(((Event) (obj))) && AccountUtil.isGoogleAccount(((Event) (obj)).getCalendar().account))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj1 = Features.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj1).notify_guests();
        if (!flag)
        {
            if (flag)
            {
                obj = com.google.android.calendar.api.event.ECIDED;
            } else
            {
                obj = null;
            }
            if (obj == null)
            {
                return com.google.common.util.concurrent.re.NULL;
            } else
            {
                return new com.google.common.util.concurrent.re(obj);
            }
        }
        obj1 = new SettableFuture();
        if (obj == null)
        {
            ((AbstractFuture) (obj1)).cancel(true);
            return ((ListenableFuture) (obj1));
        } else
        {
            String s = GuestNotificationDialogUtils.maybeExtendDialogText(((Event) (obj)), interactivereschedulemanager.activity, interactivereschedulemanager.activity.getString(0x7f1302e1));
            return GuestNotificationDialogUtils.showNotificationDialogAsync(((Event) (obj)), interactivereschedulemanager.activity, s, "update_dnd");
        }
    }

    (InteractiveRescheduleManager interactivereschedulemanager)
    {
        arg$1 = interactivereschedulemanager;
    }
}
