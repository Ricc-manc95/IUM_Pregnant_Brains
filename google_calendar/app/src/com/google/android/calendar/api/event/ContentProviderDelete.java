// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.common.CalendarProviderHelper;
import com.google.android.calendar.api.common.ContentProviderOperator;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.calendar.v2a.android.util.provider.Batch;
import com.google.common.base.Function;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.api.event:
//            Event, EventStoreUtils, GooglePrivateData

final class ContentProviderDelete
{

    static boolean deleteStemEvent(Event event, GooglePrivateData.GuestNotification guestnotification)
        throws IOException
    {
        EventDescriptor eventdescriptor = event.getDescriptor();
        if (event.getCalendar() == null)
        {
            throw new NullPointerException();
        }
        long l = EventStoreUtils.localId(eventdescriptor);
        android.accounts.Account account = event.getCalendar().account;
        ContentProviderOperator contentprovideroperator = new ContentProviderOperator();
        Object obj = ContentProviderOperation.newDelete(ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, l)).build();
        Batch batch = contentprovideroperator.batch;
        batch.operations.add(obj);
        batch.operations.size();
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).notify_guests();
        if (AccountUtil.isGoogleAccount(account))
        {
            Function function = GooglePrivateDataHelper..Lambda._cls0.$instance;
            GooglePrivateData googleprivatedata = event.getGooglePrivateData();
            event = googleprivatedata;
            if (googleprivatedata == null)
            {
                event = GooglePrivateData.DEFAULT;
            }
            event = (GooglePrivateData.GuestNotification)function.apply(event);
            if (guestnotification != event)
            {
                if (guestnotification == GooglePrivateData.GuestNotification.ENABLED || event == GooglePrivateData.GuestNotification.ENABLED)
                {
                    guestnotification = GooglePrivateData.GuestNotification.ENABLED;
                } else
                {
                    guestnotification = GooglePrivateData.GuestNotification.DISABLED;
                }
            }
            event = ContentProviderOperation.newUpdate(CalendarProviderHelper.withAppendedSyncAdapterFlags(ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, EventStoreUtils.localId(eventdescriptor)), account)).withValue("sync_data6", Integer.valueOf(guestnotification.ordinal())).build();
            guestnotification = contentprovideroperator.batch;
            ((Batch) (guestnotification)).operations.add(event);
            ((Batch) (guestnotification)).operations.size();
        }
        return contentprovideroperator.batch.apply(CalendarApi.getApiContentResolver(), "com.android.calendar").hasAnyRowChanged();
    }
}
