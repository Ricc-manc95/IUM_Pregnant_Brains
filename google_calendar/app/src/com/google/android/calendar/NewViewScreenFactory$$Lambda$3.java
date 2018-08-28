// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.newapi.screen.EventViewScreenController;
import com.google.android.calendar.newapi.screen.smartmail.SmartMailViewScreenController;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.common.base.Function;

final class arg._cls3
    implements Function
{

    private final TimelineEvent arg$1;
    private final EventInfoAnimationData arg$2;
    private final Bundle arg$3;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        EventInfoAnimationData eventinfoanimationdata = arg$2;
        Bundle bundle = arg$3;
        obj = (EventDescriptor)obj;
        if (((TimelineEvent) (obj1)).hasSmartMail)
        {
            obj1 = SmartMailViewScreenController.prepare(new SmartMailViewScreenController(), ((com.google.android.calendar.timely.TimelineItem) (obj1)), eventinfoanimationdata, bundle);
            ((Fragment) (obj1)).getArguments().putParcelable("EventDescriptorKey", ((android.os.Parcelable) (obj)));
            return obj1;
        } else
        {
            obj1 = EventViewScreenController.prepare(new EventViewScreenController(), ((com.google.android.calendar.timely.TimelineItem) (obj1)), eventinfoanimationdata, bundle);
            ((Fragment) (obj1)).getArguments().putParcelable("EventDescriptorKey", ((android.os.Parcelable) (obj)));
            return obj1;
        }
    }

    ationData(TimelineEvent timelineevent, EventInfoAnimationData eventinfoanimationdata, Bundle bundle)
    {
        arg$1 = timelineevent;
        arg$2 = eventinfoanimationdata;
        arg$3 = bundle;
    }
}
