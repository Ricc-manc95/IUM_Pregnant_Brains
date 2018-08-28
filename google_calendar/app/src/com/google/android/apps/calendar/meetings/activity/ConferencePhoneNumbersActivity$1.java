// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.meetings.activity;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.event.conference.Availability;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.common.util.concurrent.FutureCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.meetings.activity:
//            ConferencePhoneNumbersActivity, PhoneNumberInfoAdapter

final class this._cls0
    implements FutureCallback
{

    private final ConferencePhoneNumbersActivity this$0;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e("PhoneNumbersActivity", throwable, "No phone number to display", new Object[0]);
        finish();
    }

    public final void onSuccess(Object obj)
    {
        Object obj1 = (List)obj;
        obj = adapter;
        ((PhoneNumberInfoAdapter) (obj)).phoneList.clear();
        ArrayList arraylist = new ArrayList();
        obj1 = ((List) (obj1)).iterator();
        do
        {
            if (!((Iterator) (obj1)).hasNext())
            {
                break;
            }
            PhoneNumberDetails phonenumberdetails = (PhoneNumberDetails)((Iterator) (obj1)).next();
            if (phonenumberdetails.availability() != Availability.NONE)
            {
                arraylist.add(phonenumberdetails);
            }
        } while (true);
        Collections.sort(arraylist, .instance);
        ((PhoneNumberInfoAdapter) (obj)).phoneList.addAll(arraylist);
        ((android.support.v7.widget.berInfoAdapter.phoneList) (obj)).phoneList.notifyChanged();
    }

    Y()
    {
        this$0 = ConferencePhoneNumbersActivity.this;
        super();
    }
}
