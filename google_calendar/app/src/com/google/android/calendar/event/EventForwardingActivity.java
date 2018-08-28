// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.accounts.Account;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.widget.Toast;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.common.base.Platform;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;
import java.util.Iterator;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.Sequence;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Transp;

// Referenced classes of package com.google.android.calendar.event:
//            EventIcsBuilder

public class EventForwardingActivity extends CalendarSupportActivity
{
    final class BuildIcsFileTask extends AsyncTask
    {

        private EventIcsBuilder eventIcsBuilder;
        private final EventForwardingActivity this$0;

        private final transient Result doInBackground(Pair apair[])
        {
            if (!isCancelled() && apair.length > 0 && apair[0] != null) goto _L2; else goto _L1
_L1:
            return null;
_L2:
            Object obj1;
            Object obj2;
            Object obj4;
            Object obj5;
            obj4 = (EventDescriptor)apair[0].first;
            obj1 = (CalendarDescriptor)apair[0].second;
            if (!AccountUtil.isGoogleExchangeType(((CalendarDescriptor) (obj1)).account.type))
            {
                break MISSING_BLOCK_LABEL_624;
            }
            obj2 = getContentResolver();
            apair = android.provider.CalendarContract.Events.CONTENT_URI;
            long l = ((CpEventKey)((EventDescriptor) (obj4)).getKey()).localId();
            obj5 = ((ContentResolver) (obj2)).query(apair, new String[] {
                "_id AS _id", "sync_data2 AS uid", "_sync_id AS serverItemId"
            }, "_id = ?", new String[] {
                String.valueOf(l)
            }, null);
            if (obj5 == null) goto _L4; else goto _L3
_L3:
            if (!((Cursor) (obj5)).moveToFirst()) goto _L4; else goto _L5
_L5:
            Object obj;
            obj = ((Cursor) (obj5)).getString(1);
            apair = ((Cursor) (obj5)).getString(2);
_L12:
            if (obj5 != null)
            {
                ((Cursor) (obj5)).close();
            }
            obj5 = android.provider.CalendarContract.Calendars.CONTENT_URI;
            long l1 = ((CalendarDescriptor) (obj1)).calendarKey.getLocalId();
            obj2 = ((ContentResolver) (obj2)).query(((Uri) (obj5)), new String[] {
                "_sync_id AS serverCollectionId"
            }, "_id = ?", new String[] {
                String.valueOf(l1)
            }, null);
            if (obj2 == null) goto _L7; else goto _L6
_L6:
            if (!((Cursor) (obj2)).moveToFirst()) goto _L7; else goto _L8
_L8:
            obj1 = ((Cursor) (obj2)).getString(0);
_L11:
            if (obj2 != null)
            {
                ((Cursor) (obj2)).close();
                obj2 = obj1;
                obj1 = obj;
                obj = apair;
                apair = ((Pair []) (obj2));
            } else
            {
                Object obj3 = obj;
                obj = apair;
                apair = ((Pair []) (obj1));
                obj1 = obj3;
            }
_L13:
            if (isCancelled()) goto _L1; else goto _L9
_L9:
            try
            {
                obj4 = (Event)CalendarApi.Events.read(((EventDescriptor) (obj4))).get();
            }
            // Misplaced declaration of an exception variable
            catch (Pair apair[])
            {
                return null;
            }
            if (obj4 == null) goto _L1; else goto _L10
_L10:
            obj2 = eventIcsBuilder;
            class Result
            {

                public final Event event;
                public final String ownerEmail;
                public final String serverCollectionId;
                public final String serverItemId;
                public final String subject;
                public final String uid;
                public final Uri uri;

                public Result(Uri uri1, String s, String s1, String s2, String s3, String s4, Event event1)
                {
                    uri = uri1;
                    subject = s;
                    uid = s1;
                    ownerEmail = s2;
                    serverCollectionId = s3;
                    serverItemId = s4;
                    event = event1;
                }
            }

            if (((EventIcsBuilder) (obj2)).context == null || obj4 == null)
            {
                obj2 = null;
            } else
            {
                PropertyList propertylist = EventIcsBuilder.createCalendarPropertyList();
                PropertyList propertylist1 = new PropertyList();
                EventIcsBuilder.addTimeProperties(propertylist1, ((Event) (obj4)).isAllDayEvent(), ((Event) (obj4)).getStartMillis(), ((Event) (obj4)).getEndMillis(), ((Event) (obj4)).getTimeZoneId());
                EventIcsBuilder.addRRule(propertylist1, ((Event) (obj4)));
                propertylist1.add(new Organizer(EventIcsBuilder.getEmailWithScheme(((Event) (obj4)).getOrganizer().email)));
                EventIcsBuilder.addUid(propertylist1);
                EventIcsBuilder.addAttendees(propertylist1, ((Event) (obj4)));
                propertylist1.add(new Description(((Event) (obj4)).getDescription()));
                propertylist1.add(new Location(Platform.nullToEmpty(EventIcsBuilder.extractFirstLocation(((Event) (obj4))))));
                propertylist1.add(new Sequence(((Event) (obj4)).getSequenceNumber()));
                EventIcsBuilder.addEventStatus(propertylist1, ((Event) (obj4)).getStatus());
                propertylist1.add(new Summary(((Event) (obj4)).getSummary()));
                propertylist1.add(Transp.OPAQUE);
                EventIcsBuilder.addAttachments(propertylist1, ((Event) (obj4)));
                obj2 = ((EventIcsBuilder) (obj2)).saveProperties(propertylist1, propertylist);
            }
            return new Result(((Uri) (obj2)), ((Event) (obj4)).getSummary(), ((String) (obj1)), ((Event) (obj4)).getCalendar().account.name, apair, ((String) (obj)), ((Event) (obj4)));
            apair;
            if (obj5 != null)
            {
                ((Cursor) (obj5)).close();
            }
            throw apair;
            apair;
            if (obj2 != null)
            {
                ((Cursor) (obj2)).close();
            }
            throw apair;
_L7:
            obj1 = null;
              goto _L11
_L4:
            apair = null;
            obj = null;
              goto _L12
            apair = null;
            obj = null;
            obj1 = null;
              goto _L13
        }

        protected final volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Pair[])aobj);
        }

        protected final void onPostExecute(Object obj)
        {
            obj = (Result)obj;
            if (obj == null)
            {
                finish();
                return;
            }
            EventForwardingActivity eventforwardingactivity = EventForwardingActivity.this;
            Uri uri = ((Result) (obj)).uri;
            String s1 = EventIcsBuilder.getEventFileType();
            Object obj1 = ((Result) (obj)).subject;
            String s = ((Result) (obj)).uid;
            String s2 = ((Result) (obj)).ownerEmail;
            String s3 = ((Result) (obj)).serverCollectionId;
            String s4 = ((Result) (obj)).serverItemId;
            Object obj2 = EventForwardingActivity.this;
            Event event = ((Result) (obj)).event;
            byte byte0 = 18;
            if (!event.isAllDayEvent())
            {
                byte0 = 19;
            }
            obj = DateTimeFormatHelper.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            String s5 = ((DateTimeFormatHelper)obj).getDateRangeText(event.getStartMillis(), event.getEndMillis(), byte0);
            String s6 = Platform.nullToEmpty(event.getOrganizer().email);
            String s7 = Platform.nullToEmpty(event.getSummary());
            String s8 = Platform.nullToEmpty(event.getCalendar().account.name);
            obj = event.getLocation().getEventLocations();
            if (obj == null || ((Collection) (obj)).isEmpty())
            {
                obj = null;
            } else
            {
                obj = ((EventLocation)((Collection) (obj)).iterator().next()).name;
            }
            obj2 = ((EventForwardingActivity) (obj2)).getString(0x7f130245, new Object[] {
                s6, s7, s8, s5, Platform.nullToEmpty(((String) (obj))), Platform.nullToEmpty(event.getDescription())
            });
            if (uri != null)
            {
                if (obj1 == null)
                {
                    obj = "";
                } else
                {
                    obj = obj1;
                }
                obj = (new Intent("android.intent.action.SEND")).setType(s1).putExtra("android.intent.extra.STREAM", uri).putExtra("android.intent.extra.SUBJECT", eventforwardingactivity.getString(0x7f130244, new Object[] {
                    obj
                })).putExtra("fromAccountString", s2).putExtra("com.android.mail.intent.extra.FORWARD_EVENT_COLLECTION_ID", s3).putExtra("com.android.mail.intent.extra.FORWARD_EVENT_ITEM_ID", s4).putExtra("android.intent.extra.TEXT", ((String) (obj2))).setFlags(1);
                if (s != null)
                {
                    ((Intent) (obj)).putExtra("com.android.mail.intent.extra.FORWARD_EVENT_UID", s);
                }
                obj1 = new android.content.ClipData.Item(uri);
                ((Intent) (obj)).setClipData(new ClipData("iCalendar", new String[] {
                    "text/calendar"
                }, ((android.content.ClipData.Item) (obj1))));
                if (((Intent) (obj)).resolveActivity(eventforwardingactivity.getPackageManager()) != null)
                {
                    eventforwardingactivity.startActivity(Intent.createChooser(((Intent) (obj)), eventforwardingactivity.getString(0x7f130243)));
                } else
                {
                    Toast.makeText(eventforwardingactivity, 0x7f130246, 1).show();
                }
            }
            eventforwardingactivity.finish();
        }

        protected final void onPreExecute()
        {
            super.onPreExecute();
            Toast.makeText(EventForwardingActivity.this, 0x7f130247, 0).show();
        }

        public BuildIcsFileTask()
        {
            this$0 = EventForwardingActivity.this;
            super();
            eventIcsBuilder = new EventIcsBuilder(EventForwardingActivity.this);
        }
    }


    private CalendarDescriptor calendarDescriptor;
    private EventDescriptor descriptor;
    private BuildIcsFileTask task;

    public EventForwardingActivity()
    {
        task = new BuildIcsFileTask();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = getIntent();
        if (bundle == null)
        {
            finish();
            return;
        }
        if (!AndroidPermissionUtils.hasMandatoryPermissions(this))
        {
            Toast.makeText(this, 0x7f13034d, 1).show();
            finish();
            return;
        } else
        {
            descriptor = (EventDescriptor)bundle.getParcelableExtra("eventDescriptor");
            calendarDescriptor = (CalendarDescriptor)bundle.getParcelableExtra("calendarDescriptor");
            return;
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if (task != null)
        {
            task.cancel(true);
        }
    }

    protected void onResume()
    {
        super.onResume();
        if (task.getStatus() != android.os.AsyncTask.Status.RUNNING)
        {
            task.execute(new Pair[] {
                new Pair(descriptor, calendarDescriptor)
            });
        }
    }
}
