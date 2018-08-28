// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.contract;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.Data;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.api.services.calendar.model.EventAttachment;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventLocation;
import com.google.api.services.calendar.model.SmartMailInfo;
import com.google.api.services.calendar.model.StructuredLocation;
import com.google.api.services.calendar.model.TitleContactAnnotation;
import com.google.common.base.Platform;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class TimelyEventData
    implements Parcelable, Serializable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public transient List attachments;
    public transient List attendees;
    public String backgroundImageUrl;
    public transient ConferenceData conferenceData;
    public com.google.api.services.calendar.model.Event.Gadget eventGadget;
    public transient com.google.api.services.calendar.model.Event.Source eventSource;
    public transient String participantStatus;
    public transient com.google.api.services.calendar.model.Event.ResponseSummary responseSummary;
    public transient SmartMailInfo smartMailInfo;
    public transient StructuredLocation structuredLocation;
    public transient List titleContactAnnotations;

    public TimelyEventData()
    {
    }

    TimelyEventData(Parcel parcel)
    {
        this(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
    }

    public TimelyEventData(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8, String s9, String s10)
    {
        AndroidJsonFactory androidjsonfactory = new AndroidJsonFactory();
        structuredLocation = (StructuredLocation)create(androidjsonfactory, s, com/google/api/services/calendar/model/StructuredLocation);
        smartMailInfo = (SmartMailInfo)create(androidjsonfactory, s1, com/google/api/services/calendar/model/SmartMailInfo);
        titleContactAnnotations = createList(androidjsonfactory, s3, com/google/api/services/calendar/model/TitleContactAnnotation);
        eventGadget = (com.google.api.services.calendar.model.Event.Gadget)create(androidjsonfactory, s4, com/google/api/services/calendar/model/Event$Gadget);
        eventSource = (com.google.api.services.calendar.model.Event.Source)create(androidjsonfactory, s5, com/google/api/services/calendar/model/Event$Source);
        backgroundImageUrl = s2;
        attachments = createList(androidjsonfactory, s6, com/google/api/services/calendar/model/EventAttachment);
        conferenceData = (ConferenceData)create(androidjsonfactory, s7, com/google/api/services/calendar/model/ConferenceData);
        responseSummary = (com.google.api.services.calendar.model.Event.ResponseSummary)create(androidjsonfactory, s8, com/google/api/services/calendar/model/Event$ResponseSummary);
        participantStatus = s9;
        attendees = createList(androidjsonfactory, s10, com/google/api/services/calendar/model/EventAttendee);
    }

    public static String asString(boolean flag, String s, Object obj)
    {
        if (flag)
        {
            return "[]";
        }
        Object obj2 = new AndroidJsonFactory();
        Object obj1;
        try
        {
            obj1 = new ByteArrayOutputStream();
            obj2 = ((JsonFactory) (obj2)).createJsonGenerator(((java.io.OutputStream) (obj1)), Charsets.UTF_8);
            ((JsonGenerator) (obj2)).serialize(false, obj);
            ((JsonGenerator) (obj2)).flush();
            obj1 = ((ByteArrayOutputStream) (obj1)).toString("UTF-8");
        }
        catch (IOException ioexception)
        {
            LogUtils.e("Timely", ioexception, "failed to serialize %s: %s", new Object[] {
                s, obj
            });
            return "[]";
        }
        return ((String) (obj1));
    }

    private static Object create(JsonFactory jsonfactory, String s, Class class1)
    {
        if (Platform.stringIsNullOrEmpty(s))
        {
            return null;
        }
        jsonfactory = ((JsonFactory) (jsonfactory.createJsonParser(s).parse(class1, false, null)));
        return jsonfactory;
        jsonfactory;
_L2:
        LogUtils.e("Timely", jsonfactory, "failed to parse %s: %s", new Object[] {
            class1, s
        });
        return null;
        jsonfactory;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static List createAttachments(JsonFactory jsonfactory, String s)
    {
        return createList(jsonfactory, s, com/google/api/services/calendar/model/EventAttachment);
    }

    public static List createAttendees(JsonFactory jsonfactory, String s)
    {
        return createList(jsonfactory, s, com/google/api/services/calendar/model/EventAttendee);
    }

    private static List createList(JsonFactory jsonfactory, String s, Class class1)
    {
        if (Platform.stringIsNullOrEmpty(s))
        {
            return null;
        }
        jsonfactory = jsonfactory.createJsonParser(s);
        java.util.Collection collection = Data.newCollectionInstance(java/util/List);
        jsonfactory.parseArray(null, collection, class1, new ArrayList(), null);
        jsonfactory = (List)collection;
        return jsonfactory;
        jsonfactory;
_L2:
        LogUtils.e("Timely", jsonfactory, "failed to parse list of %s: %s", new Object[] {
            class1, s
        });
        return null;
        jsonfactory;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static StructuredLocation createStructuredLocation(JsonFactory jsonfactory, String s)
    {
        return (StructuredLocation)create(jsonfactory, s, com/google/api/services/calendar/model/StructuredLocation);
    }

    public static List createTitleContactAnnotations(JsonFactory jsonfactory, String s)
    {
        return createList(jsonfactory, s, com/google/api/services/calendar/model/TitleContactAnnotation);
    }

    private final void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        objectinputstream = (String[])objectinputstream.readObject();
        AndroidJsonFactory androidjsonfactory = new AndroidJsonFactory();
        setTransientVariables(androidjsonfactory, objectinputstream[0], objectinputstream[1]);
        backgroundImageUrl = objectinputstream[2];
        titleContactAnnotations = createList(androidjsonfactory, objectinputstream[3], com/google/api/services/calendar/model/TitleContactAnnotation);
        eventGadget = (com.google.api.services.calendar.model.Event.Gadget)create(androidjsonfactory, objectinputstream[4], com/google/api/services/calendar/model/Event$Gadget);
        attachments = createList(androidjsonfactory, objectinputstream[6], com/google/api/services/calendar/model/EventAttachment);
        conferenceData = (ConferenceData)create(androidjsonfactory, objectinputstream[7], com/google/api/services/calendar/model/ConferenceData);
        responseSummary = (com.google.api.services.calendar.model.Event.ResponseSummary)create(androidjsonfactory, objectinputstream[8], com/google/api/services/calendar/model/Event$ResponseSummary);
        participantStatus = objectinputstream[9];
        attendees = createList(androidjsonfactory, objectinputstream[10], com/google/api/services/calendar/model/EventAttendee);
    }

    private final void setTransientVariables(JsonFactory jsonfactory, String s, String s1)
    {
        structuredLocation = (StructuredLocation)create(jsonfactory, s, com/google/api/services/calendar/model/StructuredLocation);
        smartMailInfo = (SmartMailInfo)create(jsonfactory, s1, com/google/api/services/calendar/model/SmartMailInfo);
    }

    private final void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        String s5 = null;
        objectoutputstream.defaultWriteObject();
        String s;
        String s1;
        String s2;
        String s3;
        String s4;
        String s6;
        String s7;
        String s8;
        if (structuredLocation != null)
        {
            s = structuredLocation.toString();
        } else
        {
            s = null;
        }
        if (smartMailInfo != null)
        {
            s1 = smartMailInfo.toString();
        } else
        {
            s1 = null;
        }
        s6 = backgroundImageUrl;
        s7 = titleContactAnnotationsAsString();
        if (eventGadget != null)
        {
            s2 = eventGadget.toString();
        } else
        {
            s2 = null;
        }
        if (eventSource != null)
        {
            s3 = eventSource.toString();
        } else
        {
            s3 = null;
        }
        s8 = attachmentsAsString();
        if (conferenceData != null)
        {
            s4 = conferenceData.toString();
        } else
        {
            s4 = null;
        }
        if (responseSummary != null)
        {
            s5 = responseSummary.toString();
        }
        objectoutputstream.writeObject(new String[] {
            s, s1, s6, s7, s2, s3, s8, s4, s5, participantStatus, 
            attendeesAsString()
        });
    }

    public final String attachmentsAsString()
    {
        boolean flag;
        if (attachments == null || attachments.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return asString(flag, "attachments", attachments);
    }

    public final String attendeesAsString()
    {
        boolean flag;
        if (attendees == null || attendees.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return asString(flag, "attendees", attendees);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final EventLocation getFirstEventLocation()
    {
        Object obj = structuredLocation;
        if (obj != null)
        {
            if ((obj = ((StructuredLocation) (obj)).locations) != null && !((List) (obj)).isEmpty())
            {
                return (EventLocation)((List) (obj)).get(0);
            }
        }
        return null;
    }

    public final boolean hasTimelyData()
    {
        return structuredLocation != null || smartMailInfo != null || !TextUtils.isEmpty(backgroundImageUrl) || titleContactAnnotations != null && !titleContactAnnotations.isEmpty() || eventGadget != null || attachments != null && !attachments.isEmpty() || conferenceData != null || responseSummary != null || participantStatus != null || attendees != null && !attendees.isEmpty();
    }

    public final String titleContactAnnotationsAsString()
    {
        boolean flag;
        if (titleContactAnnotations == null || titleContactAnnotations.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return asString(flag, "titleContactAnnotations", titleContactAnnotations);
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (structuredLocation != null)
        {
            stringbuilder.append("location: ");
            stringbuilder.append(structuredLocation);
            stringbuilder.append("\n");
        }
        if (smartMailInfo != null)
        {
            stringbuilder.append("smart mail: ");
            stringbuilder.append(smartMailInfo);
            stringbuilder.append("\n");
        }
        if (!TextUtils.isEmpty(backgroundImageUrl))
        {
            stringbuilder.append("event background: ");
            stringbuilder.append(backgroundImageUrl);
        }
        if (titleContactAnnotations != null)
        {
            stringbuilder.append("associated contacts: ");
            stringbuilder.append(titleContactAnnotations);
        }
        if (eventGadget != null)
        {
            stringbuilder.append("event gadget: ");
            stringbuilder.append(eventGadget);
        }
        if (eventSource != null)
        {
            stringbuilder.append("event source: ");
            stringbuilder.append(eventSource);
        }
        if (attachments != null)
        {
            stringbuilder.append("attachments: ");
            stringbuilder.append(attachments);
        }
        if (conferenceData != null)
        {
            stringbuilder.append("conference: ");
            stringbuilder.append(conferenceData);
            stringbuilder.append("\n");
        }
        if (responseSummary != null)
        {
            stringbuilder.append("response summary: ");
            stringbuilder.append(responseSummary);
        }
        if (participantStatus != null)
        {
            stringbuilder.append("participant status: ");
            stringbuilder.append(participantStatus);
        }
        if (attendees != null)
        {
            stringbuilder.append("attendees: ");
            stringbuilder.append(attendees);
        }
        return stringbuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        Object obj = null;
        String s;
        if (structuredLocation != null)
        {
            s = structuredLocation.toString();
        } else
        {
            s = null;
        }
        parcel.writeString(s);
        if (smartMailInfo != null)
        {
            s = smartMailInfo.toString();
        } else
        {
            s = null;
        }
        parcel.writeString(s);
        parcel.writeString(backgroundImageUrl);
        parcel.writeString(titleContactAnnotationsAsString());
        if (eventGadget != null)
        {
            s = eventGadget.toString();
        } else
        {
            s = null;
        }
        parcel.writeString(s);
        if (eventSource != null)
        {
            s = eventSource.toString();
        } else
        {
            s = null;
        }
        parcel.writeString(s);
        parcel.writeString(attachmentsAsString());
        if (conferenceData != null)
        {
            s = conferenceData.toString();
        } else
        {
            s = null;
        }
        parcel.writeString(s);
        s = obj;
        if (responseSummary != null)
        {
            s = responseSummary.toString();
        }
        parcel.writeString(s);
        parcel.writeString(participantStatus);
        parcel.writeString(attendeesAsString());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TimelyEventData(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TimelyEventData[i];
        }

        _cls1()
        {
        }
    }

}
