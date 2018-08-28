// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.event.conference.AutoValue_PhoneNumberDetails;
import com.google.android.calendar.event.conference.Availability;
import com.google.android.calendar.event.conference.LocalPhoneSource;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.newapi.model.ConferenceEvent;
import java.util.Locale;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference:
//            ConferenceTypes

public final class PhoneNumberDetailsFactory
{

    static PhoneNumberDetails create(ConferenceEvent conferenceevent, Event event)
    {
        conferenceevent = conferenceevent.getLocalPhoneNumber();
        if (conferenceevent != null)
        {
            return conferenceevent;
        }
        event = ConferenceTypes.getPhoneConference(event.getConferenceData());
        if (event == null)
        {
            return null;
        }
        String s = event.getRegionCode();
        conferenceevent = Uri.parse(event.getUri());
        String s1 = event.getPassCode();
        Availability availability = Availability.PUBLIC;
        LocalPhoneSource localphonesource = LocalPhoneSource.EVENT_DEFAULT;
        if (!TextUtils.equals(conferenceevent.getScheme(), "tel"))
        {
            throw new IllegalArgumentException();
        }
        event = conferenceevent.getSchemeSpecificPart();
        int i = event.indexOf(';');
        conferenceevent = event;
        if (i >= 0)
        {
            conferenceevent = event.substring(0, i);
        }
        return new AutoValue_PhoneNumberDetails(conferenceevent, s1, new Locale("", s), availability, localphonesource);
    }
}
