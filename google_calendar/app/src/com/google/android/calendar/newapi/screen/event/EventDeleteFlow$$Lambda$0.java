// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.support.v4.app.Fragment;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.newapi.overflow.GuestNotificationDialogUtils;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventDeleteFlow

final class arg._cls1
    implements AsyncFunction
{

    private final EventDeleteFlow arg$1;

    public final ListenableFuture apply(Object obj)
    {
        EventDeleteFlow eventdeleteflow = arg$1;
        obj = (Event)obj;
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).notify_guests();
        if (!GuestNotificationDialogUtils.shouldShowDialogForEventDeletion(eventdeleteflow.event))
        {
            obj = com.google.android.calendar.api.event.ification.UNDECIDED;
            if (obj == null)
            {
                return com.google.common.util.concurrent.uccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.uccessfulFuture(obj);
            }
        }
        if (obj != null)
        {
            eventdeleteflow.event = ((Event) (obj));
        }
        obj = GuestNotificationDialogUtils.getGuestNotificationDialogStringForDeletion(eventdeleteflow.event, eventdeleteflow.getContext());
        return GuestNotificationDialogUtils.showNotificationDialogAsync(eventdeleteflow.event, eventdeleteflow.getContext(), ((String) (obj)), "delete");
    }

    e(EventDeleteFlow eventdeleteflow)
    {
        arg$1 = eventdeleteflow;
    }
}
