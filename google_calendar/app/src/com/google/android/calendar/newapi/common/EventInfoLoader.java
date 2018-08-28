// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import android.content.Context;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.newapi.meetings.LocalPhoneNumberLoader;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            CompositeLoader, AsyncTaskLoader

final class EventInfoLoader extends CompositeLoader
{

    public final Context context;
    public LocalPhoneNumberLoader localPhoneLoader;

    EventInfoLoader(Context context1)
    {
        context = context1.getApplicationContext();
    }

    public final Object getResult()
    {
        EventInfoResult eventinforesult = new EventInfoResult();
        if (localPhoneLoader != null)
        {
            eventinforesult.localPhone = (PhoneNumberDetails)localPhoneLoader.getResult();
        }
        return eventinforesult;
    }

    private class EventInfoResult
    {

        public PhoneNumberDetails localPhone;

        EventInfoResult()
        {
        }
    }

}
