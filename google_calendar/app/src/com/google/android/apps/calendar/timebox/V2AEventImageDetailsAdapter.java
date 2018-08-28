// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.event.V2AEventAdapter;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.smartmail.V2ASmartMailInfoAdapter;
import com.google.caribou.smartmail.Address;
import com.google.caribou.smartmail.Image;
import com.google.protos.calendar.feapi.v1.Event;
import com.google.protos.calendar.feapi.v1.StructuredLocation;
import java.util.Iterator;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            AutoValue_EventImageDetails, EventImageDetails

public final class V2AEventImageDetailsAdapter
{

    static EventImageDetails createEventImageDetails(Event event, Image image, Address address, EventImageDetails.SmartMailType smartmailtype)
    {
        Object obj = null;
        String s = event.backgroundImageUrl_;
        if (image == null)
        {
            image = null;
        } else
        {
            image = image.imageUrl_;
        }
        if (address == null)
        {
            address = null;
        } else
        {
            address = V2ASmartMailInfoAdapter.toSmartMailAddress(address);
        }
        if ((event.bitField0_ & 0x1000) == 4096)
        {
            Iterator iterator;
            if (event.structuredLocation_ == null)
            {
                event = StructuredLocation.DEFAULT_INSTANCE;
            } else
            {
                event = event.structuredLocation_;
            }
            iterator = V2AEventAdapter.toStructuredLocation(event).getEventLocations().iterator();
            event = obj;
            if (iterator.hasNext())
            {
                event = ((Event) (iterator.next()));
            }
            event = (EventLocation)event;
        } else
        {
            event = null;
        }
        return new AutoValue_EventImageDetails(s, image, address, event, smartmailtype);
    }
}
