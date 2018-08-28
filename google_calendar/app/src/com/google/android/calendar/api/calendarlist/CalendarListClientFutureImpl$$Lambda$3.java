// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.content.ContentResolver;
import android.text.TextUtils;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.LoadDetailsConstants;
import com.google.common.base.VerifyException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarListClientFutureImpl, CalendarListApiStoreImpl, CalendarListFilterOptions, CalendarAccessLevel

final class arg._cls2
    implements Callable
{

    private final CalendarListClientFutureImpl arg$1;
    private final CalendarListFilterOptions arg$2;

    public final Object call()
    {
        Object obj = arg$1;
        CalendarListFilterOptions calendarlistfilteroptions = arg$2;
        CalendarListApiStoreImpl calendarlistapistoreimpl = ((CalendarListClientFutureImpl) (obj)).api;
        Object obj1 = new ArrayList();
        if (calendarlistapistoreimpl.shouldFilterOutGoogleAccounts)
        {
            ((ArrayList) (obj1)).add("account_type".concat("!=?"));
        }
        ArrayList arraylist;
        boolean flag;
        int i;
        if (calendarlistfilteroptions == null || calendarlistfilteroptions.isEmptyFilter())
        {
            if (((ArrayList) (obj1)).isEmpty())
            {
                obj1 = null;
            } else
            {
                obj1 = TextUtils.join(" AND ", ((Iterable) (obj1)));
            }
        } else
        {
            if (calendarlistfilteroptions.googleCalendarsOnly != null && calendarlistfilteroptions.googleCalendarsOnly.booleanValue())
            {
                ((ArrayList) (obj1)).add("account_type".concat("=?"));
            }
            if (calendarlistfilteroptions.primary != null)
            {
                if (calendarlistfilteroptions.primary.booleanValue())
                {
                    obj = "1";
                } else
                {
                    obj = "0";
                }
                ((ArrayList) (obj1)).add("(COALESCE(isPrimary, ownerAccount=account_name))".concat("=").concat(((String) (obj))));
            }
            if (calendarlistfilteroptions.visible != null)
            {
                ((ArrayList) (obj1)).add("visible".concat("=?"));
            }
            if (calendarlistfilteroptions.writable != null)
            {
                if (calendarlistfilteroptions.writable.booleanValue())
                {
                    obj = ">=?";
                } else
                {
                    obj = "<?";
                }
                ((ArrayList) (obj1)).add("calendar_access_level".concat(((String) (obj))));
            }
            obj1 = TextUtils.join(" AND ", ((Iterable) (obj1)));
        }
        arraylist = new ArrayList();
        if (calendarlistapistoreimpl.shouldFilterOutGoogleAccounts)
        {
            arraylist.add("com.google");
        }
        if (calendarlistfilteroptions == null || calendarlistfilteroptions.isEmptyFilter())
        {
            if (arraylist.isEmpty())
            {
                obj = null;
            } else
            {
                obj = (String[])arraylist.toArray(new String[arraylist.size()]);
            }
        } else
        {
            if (calendarlistfilteroptions.googleCalendarsOnly != null && calendarlistfilteroptions.googleCalendarsOnly.booleanValue())
            {
                arraylist.add("com.google");
            }
            if (calendarlistfilteroptions.visible != null)
            {
                if (calendarlistfilteroptions.visible.booleanValue())
                {
                    obj = "1";
                } else
                {
                    obj = "0";
                }
                arraylist.add(obj);
            }
            if (calendarlistfilteroptions.writable != null)
            {
                arraylist.add(Integer.toString(CalendarAccessLevel.WRITER.level));
            }
            if (arraylist.isEmpty())
            {
                obj = null;
            } else
            {
                obj = (String[])arraylist.toArray(new String[arraylist.size()]);
            }
        }
        i = Cursors.count(CalendarApi.getApiContentResolver().query(android.provider.tResolver, LoadDetailsConstants.CALENDARS_PROJECTION, ((String) (obj1)), ((String []) (obj)), null), "CalendarList count");
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new VerifyException();
        } else
        {
            return Integer.valueOf(i);
        }
    }

    Y(CalendarListClientFutureImpl calendarlistclientfutureimpl, CalendarListFilterOptions calendarlistfilteroptions)
    {
        arg$1 = calendarlistclientfutureimpl;
        arg$2 = calendarlistfilteroptions;
    }
}
