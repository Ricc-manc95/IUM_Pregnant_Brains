// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.event.location.StructuredLocationTimelyStoreUtils;
import com.google.android.calendar.api.event.smartmail.SmartMailStoreUtils;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.api.services.calendar.model.Image;
import com.google.api.services.calendar.model.SmartMailAddress;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            AutoValue_EventImageDetails, EventImageDetails

final class EventImageDetailsAdapter
{

    static EventImageDetails createEventImageDetails(TimelyEventData timelyeventdata, Image image, SmartMailAddress smartmailaddress, EventImageDetails.SmartMailType smartmailtype)
    {
        Object obj = null;
        String s;
        com.google.android.calendar.api.event.location.EventLocation eventlocation;
        if (timelyeventdata == null)
        {
            s = null;
        } else
        {
            s = timelyeventdata.backgroundImageUrl;
        }
        if (image == null)
        {
            image = null;
        } else
        {
            image = image.imageUrl;
        }
        if (smartmailaddress == null)
        {
            smartmailaddress = null;
        } else
        {
            smartmailaddress = SmartMailStoreUtils.toApiAddress(smartmailaddress);
        }
        eventlocation = obj;
        if (timelyeventdata != null)
        {
            if (timelyeventdata.getFirstEventLocation() == null)
            {
                eventlocation = obj;
            } else
            {
                eventlocation = StructuredLocationTimelyStoreUtils.toApiEventLocation(timelyeventdata.getFirstEventLocation());
            }
        }
        return new AutoValue_EventImageDetails(s, image, smartmailaddress, eventlocation, smartmailtype);
    }
}
