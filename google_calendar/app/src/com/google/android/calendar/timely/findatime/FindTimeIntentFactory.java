// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.event.FindTimeActivity;
import com.google.android.calendar.newapi.model.TimeZoneHolder;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.model.edit.EventReferenceIdHolder;
import com.google.android.calendar.time.DateTimeUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.accounts.api.AccountData;
import com.google.android.gms.identity.accounts.api.AccountDataUtil;
import com.google.android.gms.identity.accounts.api.zzb;
import com.google.calendar.v2.client.service.api.time.DateTime;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.TimeZone;

public final class FindTimeIntentFactory
{

    public static Intent create(Context context, EditScreenModel editscreenmodel)
    {
        com.google.android.calendar.api.event.EventModifications eventmodifications = ((EventModificationsHolder)editscreenmodel).getEventModifications();
        Object obj = ((TimeZoneHolder)editscreenmodel).getTimeZoneId(context);
        int k = editscreenmodel.getColor().getValue();
        String s = ((EventReferenceIdHolder)editscreenmodel).getEventReferenceId();
        long l4 = eventmodifications.getStartMillis();
        long l2 = eventmodifications.getEndMillis();
        boolean flag;
        if (!eventmodifications.isEndTimeUnspecified())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = TimeZone.getTimeZone(((String) (obj)));
        if (flag && !DateTimeUtils.isValidEventTime(l4, ((TimeZone) (obj)), l2, ((TimeZone) (obj)), eventmodifications.isAllDayEvent()))
        {
            return null;
        }
        Object obj1 = DateTimeUtils.getNowDateTime(context);
        AccountData accountdata;
        zzb zzb1;
        long l1;
        long l3;
        if (obj1 == null)
        {
            editscreenmodel = null;
        } else
        {
            editscreenmodel = ((DateTime) (obj1)).withMillisOfDay(0);
        }
        l3 = l4;
        l1 = l2;
        if (l4 < editscreenmodel.getMillis())
        {
            l1 = l2;
            if (flag)
            {
                l1 = ((DateTime) (obj1)).getMillis() + (l2 - l4);
            }
            l3 = ((DateTime) (obj1)).getMillis();
        }
        obj = (new Intent(context, com/google/android/calendar/event/FindTimeActivity)).putExtra("start_millis", l3).putExtra("timezone", ((TimeZone) (obj)).getID());
        if (flag)
        {
            ((Intent) (obj)).putExtra("end_millis", l1);
        }
        obj1 = eventmodifications.getCalendar().account;
        accountdata = AccountData.forAccount(((Account) (obj1)).name);
        zzb1 = AccountDataUtil.zzbxL;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Context must not be null."));
        }
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Intent must not be null."));
        }
        if (accountdata == null)
        {
            throw new NullPointerException(String.valueOf("Account data must not be null."));
        }
        editscreenmodel = ((Intent) (obj)).getComponent();
        int i;
        int l;
        if (editscreenmodel != null)
        {
            editscreenmodel = editscreenmodel.getPackageName();
        } else
        {
            editscreenmodel = ((Intent) (obj)).getPackage();
        }
        if (editscreenmodel != null && zzb1.zzbxM.zzG(context, editscreenmodel))
        {
            context = Parcel.obtain();
            accountdata.writeToParcel(context, 0);
            editscreenmodel = context.marshall();
            context.recycle();
            ((Intent) (obj)).putExtra("com.google.android.gms.accounts.ACCOUNT_DATA", editscreenmodel);
        }
        ((Intent) (obj)).putExtra("account_type", ((Account) (obj1)).type);
        if (eventmodifications.getGoogleSyncId() != null)
        {
            ((Intent) (obj)).putExtra("existing_event_id", eventmodifications.getGoogleSyncId());
            ((Intent) (obj)).putExtra("existing_event_calendar_id", eventmodifications.getCalendar().calendarId);
        }
        context = new ArrayList();
        editscreenmodel = (ImmutableList)eventmodifications.getAttendees();
        l = editscreenmodel.size();
        i = 0;
        obj1 = (UnmodifiableIterator)null;
        do
        {
            if (i >= l)
            {
                break;
            }
            Object obj2 = editscreenmodel.get(i);
            int j = i + 1;
            obj2 = (Attendee)obj2;
            i = j;
            if (!TextUtils.isEmpty(((Attendee) (obj2)).attendeeDescriptor.email))
            {
                context.add(((Attendee) (obj2)).attendeeDescriptor.email);
                i = j;
            }
        } while (true);
        editscreenmodel = eventmodifications.getOrganizer().email;
        if (!TextUtils.isEmpty(editscreenmodel))
        {
            if (!context.contains(editscreenmodel))
            {
                context.add(editscreenmodel);
            }
            ((Intent) (obj)).putExtra("organizer", editscreenmodel);
        }
        ((Intent) (obj)).putStringArrayListExtra("attendees", context);
        class .Lambda._cls0
            implements Function
        {

            private final Event arg$1;

            public final Object apply(Object obj3)
            {
                Object obj4 = arg$1;
                obj3 = (String)obj3;
                obj4 = ((Event) (obj4)).getAttendees();
                class .Lambda._cls1
                    implements Predicate
                {

                    private final String arg$1;

                    public final boolean apply(Object obj6)
                    {
                        return arg$1.equals(((Attendee)obj6).attendeeDescriptor.email);
                    }

                        .Lambda._cls1(String s)
                        {
                            arg$1 = s;
                        }
                }

                obj3 = new .Lambda._cls1(((String) (obj3)));
                obj4 = ((Iterable) (obj4)).iterator();
                if (obj4 == null)
                {
                    throw new NullPointerException();
                }
                if (obj3 == null)
                {
                    throw new NullPointerException();
                }
                Object obj5;
                do
                {
                    if (!((Iterator) (obj4)).hasNext())
                    {
                        break MISSING_BLOCK_LABEL_118;
                    }
                    obj5 = ((Iterator) (obj4)).next();
                } while (!((Predicate) (obj3)).apply(obj5));
                if (obj5 == null)
                {
                    throw new NullPointerException();
                }
                obj3 = new Present(obj5);
_L1:
                obj3 = (Attendee)((Optional) (obj3)).orNull();
                if (obj3 == null)
                {
                    return null;
                } else
                {
                    return Platform.emptyToNull(((Attendee) (obj3)).displayName);
                }
                obj3 = Absent.INSTANCE;
                  goto _L1
            }

            .Lambda._cls0(Event event)
            {
                arg$1 = event;
            }
        }

        editscreenmodel = new .Lambda._cls0(eventmodifications);
        if (context instanceof RandomAccess)
        {
            context = new com.google.common.collect.Lists.TransformingRandomAccessList(context, editscreenmodel);
        } else
        {
            context = new com.google.common.collect.Lists.TransformingSequentialList(context, editscreenmodel);
        }
        ((Intent) (obj)).putStringArrayListExtra("attendee_display_names", new ArrayList(context));
        ((Intent) (obj)).putExtra("event_color", k);
        ((Intent) (obj)).putExtra("event_reference_id", s);
        ((Intent) (obj)).putExtra("is_recurring_event", eventmodifications.isRecurring());
        return ((Intent) (obj));
    }
}
