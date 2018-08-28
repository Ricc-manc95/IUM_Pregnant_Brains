// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.services.calendar.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.api.services.calendar.model:
//            ConferenceData, EventDateTime, EventHabitInstance, PrivateEventData, 
//            SharedEventData, StructuredLocation

public final class Event extends GenericJson
{
    public static final class Creator extends GenericJson
    {

        public String displayName;
        public String email;
        public String id;
        public Boolean self;

        public final volatile GenericJson clone()
        {
            return (Creator)clone();
        }

        public final volatile GenericData clone()
        {
            return (Creator)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (Creator)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (Creator)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (Creator)super.set(s, obj);
        }

        public Creator()
        {
        }
    }

    public static final class ExtendedProperties extends GenericJson
    {

        public Map private__;
        public Map shared;

        public final volatile GenericJson clone()
        {
            return (ExtendedProperties)clone();
        }

        public final volatile GenericData clone()
        {
            return (ExtendedProperties)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (ExtendedProperties)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (ExtendedProperties)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (ExtendedProperties)super.set(s, obj);
        }

        public ExtendedProperties()
        {
        }
    }

    public static final class Gadget extends GenericJson
    {

        public String display;
        public Integer height;
        public String iconLink;
        public String link;
        public Map preferences;
        public String title;
        public String type;
        public Integer width;

        public final volatile GenericJson clone()
        {
            return (Gadget)clone();
        }

        public final volatile GenericData clone()
        {
            return (Gadget)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (Gadget)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (Gadget)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (Gadget)super.set(s, obj);
        }

        public Gadget()
        {
        }
    }

    public static final class Organizer extends GenericJson
    {

        public String displayName;
        public String email;
        public String id;
        public Boolean self;

        public final volatile GenericJson clone()
        {
            return (Organizer)clone();
        }

        public final volatile GenericData clone()
        {
            return (Organizer)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (Organizer)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (Organizer)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (Organizer)super.set(s, obj);
        }

        public Organizer()
        {
        }
    }

    public static final class Reminders extends GenericJson
    {

        public List overrides;
        public Boolean useDefault;

        public final volatile GenericJson clone()
        {
            return (Reminders)clone();
        }

        public final volatile GenericData clone()
        {
            return (Reminders)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (Reminders)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (Reminders)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (Reminders)super.set(s, obj);
        }

        public Reminders()
        {
        }
    }

    public static final class ResponseSummary extends GenericJson
    {

        public Integer numAccepted;
        public Integer numDeclined;
        public Integer numNeedsAction;
        public Integer numTentative;

        public final volatile GenericJson clone()
        {
            return (ResponseSummary)clone();
        }

        public final volatile GenericData clone()
        {
            return (ResponseSummary)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (ResponseSummary)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (ResponseSummary)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (ResponseSummary)super.set(s, obj);
        }

        public ResponseSummary()
        {
        }
    }

    public static final class Source extends GenericJson
    {

        public String title;
        public String url;

        public final volatile GenericJson clone()
        {
            return (Source)clone();
        }

        public final volatile GenericData clone()
        {
            return (Source)clone();
        }

        public final Object clone()
            throws CloneNotSupportedException
        {
            return (Source)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (Source)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (Source)super.set(s, obj);
        }

        public Source()
        {
        }
    }


    public Boolean allFollowing;
    public Boolean anyoneCanAddSelf;
    public List attachments;
    public List attendees;
    public Boolean attendeesOmitted;
    public String backgroundImageUrl;
    public String colorId;
    public ConferenceData conferenceData;
    public DateTime created;
    public Creator creator;
    public String description;
    public EventDateTime end;
    public Boolean endTimeUnspecified;
    public String etag;
    public ExtendedProperties extendedProperties;
    public String fingerprint;
    public Gadget gadget;
    public Boolean guestsCanInviteOthers;
    public Boolean guestsCanModify;
    public Boolean guestsCanSeeOtherGuests;
    public EventHabitInstance habitInstance;
    public String hangoutLink;
    public String htmlLink;
    public String iCalUID;
    public String id;
    public Boolean includeHangout;
    public List invitationNotes;
    public String kind;
    public String location;
    public Boolean locked;
    public Organizer organizer;
    public EventDateTime originalStartTime;
    public String participantStatusSerialized;
    public Boolean phantom;
    public Boolean privateCopy;
    public PrivateEventData privateEventData;
    public String rangeEventId;
    public List recurrence;
    public String recurringEventId;
    public Reminders reminders;
    public ResponseSummary responseSummary;
    public Integer sequence;
    public SharedEventData sharedEventData;
    public Source source;
    public EventDateTime start;
    public String status;
    public StructuredLocation structuredLocation;
    public String summary;
    public String transparency;
    public DateTime updated;
    public String visibility;

    public Event()
    {
    }

    public final volatile GenericJson clone()
    {
        return (Event)clone();
    }

    public final volatile GenericData clone()
    {
        return (Event)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (Event)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (Event)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (Event)super.set(s, obj);
    }
}
