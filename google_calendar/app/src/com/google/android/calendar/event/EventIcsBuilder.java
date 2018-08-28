// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.Context;
import android.net.Uri;
import android.os.Process;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.event.time.RecurRulePart;
import com.google.android.calendar.api.event.time.Recurrence;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.CuType;
import net.fortuna.ical4j.model.parameter.PartStat;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.property.Attach;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStamp;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.HostInfo;
import net.fortuna.ical4j.util.SimpleHostInfo;
import net.fortuna.ical4j.util.UidGenerator;

public class EventIcsBuilder
{

    private static final Property CALENDAR_CAL_SCALE;
    private static final Property CALENDAR_METHOD;
    private static final Property CALENDAR_PROD_ID = new ProdId("-//Google Inc//Google Calendar 70.9054//EN");
    private static final Property CALENDAR_VERSION;
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/event/EventIcsBuilder);
    public final Context context;

    EventIcsBuilder(Context context1)
    {
        context = context1;
    }

    static void addAttachments(PropertyList propertylist, Event event)
    {
        event = event.getAttachments();
        if (event != null && !event.isEmpty())
        {
            event = (ImmutableList)event;
            int j = event.size();
            for (int i = 0; i < j;)
            {
                Object obj = event.get(i);
                i++;
                propertylist.add(new Attach(URI.create(Uri.encode(((Attachment)obj).fileUrl))));
            }

        }
    }

    static void addAttendees(PropertyList propertylist, Event event)
    {
        if (!event.getAttendees().isEmpty()) goto _L2; else goto _L1
_L1:
        propertylist.add(buildAttendee(event.getOrganizer().email, event.getOrganizer().email, 1, 1, 1));
_L4:
        return;
_L2:
        int i;
        int k;
        event = (ImmutableList)event.getAttendees();
        k = event.size();
        i = 0;
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
_L9:
        if (i >= k) goto _L4; else goto _L3
_L3:
        Object obj;
        String s;
        String s1;
        int j;
        int l;
        int i1;
        obj = event.get(i);
        j = i + 1;
        obj = (Attendee)obj;
        s = ((Attendee) (obj)).attendeeDescriptor.email;
        s1 = ((Attendee) (obj)).displayName;
        l = ((Attendee) (obj)).role;
        i1 = ((Attendee) (obj)).type;
        ((Attendee) (obj)).response.status.ordinal();
        JVM INSTR tableswitch 1 3: default 152
    //                   1 179
    //                   2 191
    //                   3 185;
           goto _L5 _L6 _L7 _L8
_L7:
        break MISSING_BLOCK_LABEL_191;
_L5:
        i = 3;
_L10:
        propertylist.add(buildAttendee(s, s1, l, i1, i));
        i = j;
          goto _L9
_L6:
        i = 1;
          goto _L10
_L8:
        i = 2;
          goto _L10
        i = 4;
          goto _L10
    }

    static void addEventStatus(PropertyList propertylist, int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 29
    //                   1 40
    //                   2 47;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        Status status = Status.VEVENT_TENTATIVE;
_L6:
        propertylist.add(status);
        return;
_L3:
        status = Status.VEVENT_CONFIRMED;
        continue; /* Loop/switch isn't completed */
_L4:
        status = Status.VEVENT_CANCELLED;
        if (true) goto _L6; else goto _L5
_L5:
    }

    static void addRRule(PropertyList propertylist, Event event)
    {
        boolean flag;
        if (event.isRecurring() && !event.getRecurrence().rrules.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        propertylist.add(new RRule(((RecurRulePart)event.getRecurrence().rrules.get(0)).toRfc5545String()));
        return;
        propertylist;
        LogUtils.e(TAG, propertylist, "Failed to parse RRule", new Object[0]);
        return;
    }

    static void addTimeProperties(PropertyList propertylist, boolean flag, long l, long l1, String s)
    {
        Object obj;
        if (flag)
        {
            s = GregorianCalendar.getInstance();
            s.setTimeZone(TimeZone.getTimeZone("UTC"));
            s.setTimeInMillis(l);
            s.clear(11);
            s.clear(12);
            s.clear(13);
            s.clear(14);
            obj = new Date(s.getTimeInMillis());
            s.setTimeInMillis(l1);
            s.clear(11);
            s.clear(12);
            s.clear(13);
            s.clear(14);
            s = new Date(s.getTimeInMillis());
        } else
        {
            DateTime datetime = new DateTime(l);
            DateTime datetime1 = new DateTime(l1);
            obj = null;
            if (!TextUtils.isEmpty(s))
            {
                TimeZoneRegistry timezoneregistry = TimeZoneRegistryFactory.instance.createRegistry();
                obj = timezoneregistry.getTimeZone(s);
                timezoneregistry.clear();
            }
            if (obj == null)
            {
                datetime.setUtc(true);
                datetime1.setUtc(true);
                s = datetime1;
                obj = datetime;
            } else
            {
                datetime.setTimeZone(((net.fortuna.ical4j.model.TimeZone) (obj)));
                datetime1.setTimeZone(((net.fortuna.ical4j.model.TimeZone) (obj)));
                s = datetime1;
                obj = datetime;
            }
        }
        propertylist.add(new DtStart(((Date) (obj))));
        propertylist.add(new DtEnd(s));
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        propertylist.add(new DtStamp(new DateTime(l)));
    }

    static void addUid(PropertyList propertylist)
    {
        int i = Process.myPid();
        UidGenerator uidgenerator = new UidGenerator(new SimpleHostInfo("calendar.android.google.com"), Integer.toString(i));
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(UidGenerator.uniqueTimestamp());
        stringbuffer.append('-');
        stringbuffer.append(uidgenerator.pid);
        if (uidgenerator.hostInfo != null)
        {
            stringbuffer.append('@');
            stringbuffer.append(uidgenerator.hostInfo.getHostName());
        }
        propertylist.add(new Uid(stringbuffer.toString()));
    }

    private static net.fortuna.ical4j.model.property.Attendee buildAttendee(String s, String s1, int i, int j, int k)
    {
        net.fortuna.ical4j.model.property.Attendee attendee;
        attendee = new net.fortuna.ical4j.model.property.Attendee(getEmailWithScheme(s));
        s = ((Property) (attendee)).parameters;
        s1 = new Cn(s1);
        if (s1 == null)
        {
            throw new IllegalArgumentException("Trying to add null Parameter");
        }
        ((ParameterList) (s)).parameters.add(s1);
        k;
        JVM INSTR tableswitch 1 4: default 88
    //                   1 100
    //                   2 125
    //                   3 132
    //                   4 139;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if (!RemoteFeatureConfig.THIRD_PARTY_RESOURCE_SUPPORT.enabled())
        {
            return attendee;
        }
        break; /* Loop/switch isn't completed */
_L2:
        s = PartStat.ACCEPTED;
_L7:
        s1 = ((Property) (attendee)).parameters;
        if (s == null)
        {
            throw new IllegalArgumentException("Trying to add null Parameter");
        }
        break; /* Loop/switch isn't completed */
_L3:
        s = PartStat.DECLINED;
        continue; /* Loop/switch isn't completed */
_L4:
        s = PartStat.NEEDS_ACTION;
        continue; /* Loop/switch isn't completed */
_L5:
        s = PartStat.TENTATIVE;
        if (true) goto _L7; else goto _L6
_L6:
        ((ParameterList) (s1)).parameters.add(s);
        if (true) goto _L1; else goto _L8
_L8:
        if (i == 2)
        {
            s = ((Property) (attendee)).parameters;
            s1 = Role.OPT_PARTICIPANT;
            if (s1 == null)
            {
                throw new IllegalArgumentException("Trying to add null Parameter");
            }
            ((ParameterList) (s)).parameters.add(s1);
        } else
        {
            s = ((Property) (attendee)).parameters;
            s1 = Role.REQ_PARTICIPANT;
            if (s1 == null)
            {
                throw new IllegalArgumentException("Trying to add null Parameter");
            }
            ((ParameterList) (s)).parameters.add(s1);
        }
        j;
        JVM INSTR tableswitch 1 3: default 228
    //                   1 348
    //                   2 309
    //                   3 270;
           goto _L9 _L10 _L11 _L12
_L9:
        return attendee;
_L12:
        s = ((Property) (attendee)).parameters;
        s1 = CuType.RESOURCE;
        if (s1 == null)
        {
            throw new IllegalArgumentException("Trying to add null Parameter");
        }
        ((ParameterList) (s)).parameters.add(s1);
        continue; /* Loop/switch isn't completed */
_L11:
        s = ((Property) (attendee)).parameters;
        s1 = CuType.GROUP;
        if (s1 == null)
        {
            throw new IllegalArgumentException("Trying to add null Parameter");
        }
        ((ParameterList) (s)).parameters.add(s1);
        continue; /* Loop/switch isn't completed */
_L10:
        s = ((Property) (attendee)).parameters;
        s1 = CuType.INDIVIDUAL;
        if (s1 == null)
        {
            throw new IllegalArgumentException("Trying to add null Parameter");
        }
        ((ParameterList) (s)).parameters.add(s1);
        if (true) goto _L9; else goto _L13
_L13:
    }

    static PropertyList createCalendarPropertyList()
    {
        PropertyList propertylist = new PropertyList();
        propertylist.add(CALENDAR_PROD_ID);
        propertylist.add(CALENDAR_VERSION);
        propertylist.add(CALENDAR_CAL_SCALE);
        propertylist.add(CALENDAR_METHOD);
        return propertylist;
    }

    static String extractFirstLocation(Event event)
    {
        event = event.getLocation().getEventLocations();
        if (event.isEmpty())
        {
            return null;
        } else
        {
            return ((EventLocation)event.iterator().next()).name;
        }
    }

    static URI getEmailWithScheme(String s)
    {
        String s1 = String.valueOf("mailto:");
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = s1.concat(s);
        } else
        {
            s = new String(s1);
        }
        return URI.create(s);
    }

    static String getEventFileType()
    {
        return "text/calendar";
    }

    final Uri saveProperties(PropertyList propertylist, PropertyList propertylist1)
    {
        propertylist = new VEvent(propertylist);
        ComponentList componentlist = new ComponentList();
        componentlist.add(propertylist);
        propertylist = new net.fortuna.ical4j.model.Calendar(propertylist1, componentlist);
        propertylist1 = new File(context.getFilesDir(), "ics");
        propertylist1.mkdirs();
        propertylist1 = new File(propertylist1, "invite.ics");
        FileOutputStream fileoutputstream = new FileOutputStream(propertylist1);
        (new CalendarOutputter()).output(propertylist, new OutputStreamWriter(fileoutputstream, CalendarOutputter.DEFAULT_CHARSET));
        propertylist = FileProvider.getUriForFile(context, "com.google.android.calendar.fileprovider", propertylist1);
        return propertylist;
        propertylist;
_L2:
        LogUtils.e(TAG, propertylist, "Failed to create file", new Object[0]);
        return null;
        propertylist;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static 
    {
        CALENDAR_VERSION = Version.VERSION_2_0;
        CALENDAR_CAL_SCALE = CalScale.GREGORIAN;
        CALENDAR_METHOD = Method.REQUEST;
    }
}
