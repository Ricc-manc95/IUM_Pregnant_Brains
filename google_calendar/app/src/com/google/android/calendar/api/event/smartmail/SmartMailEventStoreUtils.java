// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import com.google.api.services.calendar.model.Event2;
import com.google.api.services.calendar.model.Image;
import com.google.common.base.Platform;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            SmartMailStoreUtils, SmartMailEvent, SmartMailImage

public final class SmartMailEventStoreUtils
{

    public static SmartMailEvent toApiSmartMailEvent(Event2 event2)
    {
        Object obj = null;
        if (event2 == null)
        {
            return null;
        }
        String s = Platform.nullToEmpty(event2.name);
        SmartMailTime smartmailtime = SmartMailStoreUtils.toApiTime(event2.startTime);
        SmartMailTime smartmailtime1 = SmartMailStoreUtils.toApiTime(event2.endTime);
        SmartMailAddress smartmailaddress = SmartMailStoreUtils.toApiAddress(event2.address);
        event2 = event2.image;
        if (event2 == null)
        {
            event2 = obj;
        } else
        {
            event2 = new SmartMailImage(Platform.nullToEmpty(((Image) (event2)).imageUrl), Platform.nullToEmpty(((Image) (event2)).imageMetadataUrl));
        }
        return new SmartMailEvent(s, smartmailtime, smartmailtime1, smartmailaddress, event2);
    }
}
