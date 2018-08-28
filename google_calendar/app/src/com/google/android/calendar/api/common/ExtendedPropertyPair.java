// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import android.text.TextUtils;
import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.api.event.conference.ConferenceStoreUtils;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.utils.json.JsonUtils;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.api.services.calendar.model.Address;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.api.services.calendar.model.ConferenceRequestStatus;
import com.google.api.services.calendar.model.ConferenceSolution;
import com.google.api.services.calendar.model.CreateConferenceRequest;
import com.google.api.services.calendar.model.EventAttachment;
import com.google.api.services.calendar.model.GeoCoordinates;
import com.google.api.services.calendar.model.StructuredLocation;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ExtendedPropertyPair
{

    public final String key;
    public final String value;

    public ExtendedPropertyPair(String s, String s1)
    {
        key = s;
        value = s1;
    }

    public static ExtendedPropertyPair createForAttachments(List list)
    {
        TimelyEventData timelyeventdata = new TimelyEventData();
        if (list == null)
        {
            list = null;
        } else
        {
            if (list == null)
            {
                throw new NullPointerException();
            }
            int j = ((List)list).size();
            ArrayList arraylist = new ArrayList(j);
            for (int i = 0; i < j; i++)
            {
                Attachment attachment = (Attachment)list.get(i);
                EventAttachment eventattachment = new EventAttachment();
                eventattachment.fileId = attachment.fileId;
                eventattachment.fileUrl = attachment.fileUrl;
                eventattachment.iconLink = attachment.iconLink;
                eventattachment.mimeType = attachment.mimeType;
                eventattachment.title = attachment.title;
                arraylist.add(eventattachment);
            }

            list = arraylist;
        }
        timelyeventdata.attachments = list;
        return new ExtendedPropertyPair("attachmentsExtra", timelyeventdata.attachmentsAsString());
    }

    public static ExtendedPropertyPair createForConferenceData(com.google.android.calendar.api.event.conference.ConferenceData conferencedata)
    {
        Object obj;
        ConferenceData conferencedata1;
        conferencedata1 = new ConferenceData();
        obj = conferencedata.getCreateConferenceRequest();
        if (obj == null) goto _L2; else goto _L1
_L1:
        Object obj1;
        obj1 = new CreateConferenceRequest();
        if (((com.google.android.calendar.api.event.conference.CreateConferenceRequest) (obj)).getConferenceSolutionKey() != null)
        {
            obj1.conferenceSolutionKey = ConferenceStoreUtils.toStoreConferenceSolutionKey(((com.google.android.calendar.api.event.conference.CreateConferenceRequest) (obj)).getConferenceSolutionKey());
        }
        if (!Platform.stringIsNullOrEmpty(((com.google.android.calendar.api.event.conference.CreateConferenceRequest) (obj)).getRequestId()))
        {
            obj1.requestId = ((com.google.android.calendar.api.event.conference.CreateConferenceRequest) (obj)).getRequestId();
        }
        if (((com.google.android.calendar.api.event.conference.CreateConferenceRequest) (obj)).getConferenceRequestStatus() == null) goto _L4; else goto _L3
_L3:
        Object obj2;
        obj = ((com.google.android.calendar.api.event.conference.CreateConferenceRequest) (obj)).getConferenceRequestStatus();
        obj2 = new ConferenceRequestStatus();
        if (((com.google.android.calendar.api.event.conference.CreateConferenceRequest.ConferenceRequestStatus) (obj)).getStatusCode() == 0) goto _L6; else goto _L5
_L5:
        ((com.google.android.calendar.api.event.conference.CreateConferenceRequest.ConferenceRequestStatus) (obj)).getStatusCode();
        JVM INSTR tableswitch 1 3: default 120
    //                   1 708
    //                   2 715
    //                   3 722;
           goto _L7 _L8 _L9 _L10
_L7:
        obj = "unknown";
_L24:
        obj2.statusCode = ((String) (obj));
_L6:
        obj1.status = ((ConferenceRequestStatus) (obj2));
_L4:
        conferencedata1.createRequest = ((CreateConferenceRequest) (obj1));
_L2:
        if (conferencedata.getConferenceSolution() != null)
        {
            obj = conferencedata.getConferenceSolution();
            obj1 = new ConferenceSolution();
            obj1.key = ConferenceStoreUtils.toStoreConferenceSolutionKey(((com.google.android.calendar.api.event.conference.ConferenceSolution) (obj)).getKey());
            obj1.name = ((com.google.android.calendar.api.event.conference.ConferenceSolution) (obj)).getName();
            if (!Platform.stringIsNullOrEmpty(((com.google.android.calendar.api.event.conference.ConferenceSolution) (obj)).getIconUri()))
            {
                obj1.iconUri = ((com.google.android.calendar.api.event.conference.ConferenceSolution) (obj)).getIconUri();
            }
            conferencedata1.conferenceSolution = ((ConferenceSolution) (obj1));
        }
        if (conferencedata.getConferences().isEmpty()) goto _L12; else goto _L11
_L11:
        int i;
        int j;
        obj1 = new ArrayList(conferencedata.getConferences().size());
        obj2 = (ImmutableList)conferencedata.getConferences();
        j = ((ImmutableList) (obj2)).size();
        obj = (UnmodifiableIterator)null;
        i = 0;
_L21:
        if (i >= j) goto _L14; else goto _L13
_L13:
        Conference conference;
        com.google.api.services.calendar.model.Conference conference1;
        conference = (Conference)((ImmutableList) (obj2)).get(i);
        conference1 = new com.google.api.services.calendar.model.Conference();
        conference.getType();
        JVM INSTR tableswitch 1 5: default 320
    //                   1 729
    //                   2 736
    //                   3 743
    //                   4 750
    //                   5 757;
           goto _L15 _L16 _L17 _L18 _L19 _L20
_L15:
        obj = "unknown";
_L22:
        conference1.type = ((String) (obj));
        obj = conference.getUri();
        com.google.android.calendar.api.event.conference.ConferenceStoreUtils.ConferenceStoreSetter conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls0.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        obj = conference.getName();
        conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls1.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        obj = conference.getPassCode();
        conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls2.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        obj = conference.getRegionCode();
        conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls3.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        obj = conference.getUri();
        conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls4.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        obj = conference.getAccessCode();
        conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls5.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        if (!"unknown".equals(conference.getEntryPointType()))
        {
            conference1.entryPointType = conference.getEntryPointType();
        }
        obj = conference.getLabel();
        conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls6.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        obj = conference.getMeetingCode();
        conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls7.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        obj = conference.getPasscode();
        conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls8.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        obj = conference.getPassword();
        conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls9.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        obj = conference.getPin();
        conferencestoresetter = com.google.android.calendar.api.event.conference.ConferenceStoreUtils..Lambda._cls10.$instance;
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            conferencestoresetter.setValue(conference1, obj);
        }
        if (conference.getGatewayAccess() != 0)
        {
            boolean flag;
            if (conference.getGatewayAccess() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            conference1.gatewayAccessEnabled = Boolean.valueOf(flag);
        }
        ((List) (obj1)).add(conference1);
        i++;
          goto _L21
_L8:
        obj = "pending";
        continue; /* Loop/switch isn't completed */
_L9:
        obj = "success";
        continue; /* Loop/switch isn't completed */
_L10:
        obj = "failure";
        continue; /* Loop/switch isn't completed */
_L16:
        obj = "eventHangout";
          goto _L22
_L17:
        obj = "eventNamedHangout";
          goto _L22
_L18:
        obj = "meeting";
          goto _L22
_L19:
        obj = "meetingPhoneNumber";
          goto _L22
_L20:
        obj = "meetingPhoneNumbersLink";
          goto _L22
_L14:
        conferencedata1.conferences = ((List) (obj1));
_L12:
        if (!Platform.stringIsNullOrEmpty(conferencedata.getConferenceId()))
        {
            conferencedata1.conferenceId = conferencedata.getConferenceId();
        }
        if (!Platform.stringIsNullOrEmpty(conferencedata.getNotes()))
        {
            conferencedata1.notes = conferencedata.getNotes();
        }
        if (!Platform.stringIsNullOrEmpty(conferencedata.getSignature()))
        {
            conferencedata1.signature = conferencedata.getSignature();
        }
        return new ExtendedPropertyPair("conferenceData", JsonUtils.toString(conferencedata1));
        if (true) goto _L24; else goto _L23
_L23:
    }

    public static ExtendedPropertyPair createForLocation(com.google.android.calendar.api.event.location.StructuredLocation structuredlocation)
    {
        boolean flag = false;
        Object obj = null;
        TimelyEventData timelyeventdata = new TimelyEventData();
        if (structuredlocation == null)
        {
            structuredlocation = obj;
        } else
        {
            ArrayList arraylist = new ArrayList();
            EventLocation aeventlocation[] = (EventLocation[])structuredlocation.getEventLocations().toArray(new EventLocation[structuredlocation.getEventLocations().size()]);
            int i = 0;
            while (i < structuredlocation.getEventLocations().size()) 
            {
                EventLocation eventlocation1 = aeventlocation[i];
                Object obj1;
                if (eventlocation1 == null)
                {
                    obj1 = null;
                } else
                {
                    com.google.api.services.calendar.model.EventLocation eventlocation = new com.google.api.services.calendar.model.EventLocation();
                    eventlocation.url = Platform.emptyToNull(eventlocation1.url);
                    eventlocation.placeId = Platform.emptyToNull(eventlocation1.placeId);
                    eventlocation.mapsClusterId = Platform.emptyToNull(eventlocation1.mapsClusterId);
                    eventlocation.name = Platform.emptyToNull(eventlocation1.name);
                    Object obj2 = eventlocation1.address;
                    if (obj2 == null)
                    {
                        obj1 = null;
                    } else
                    {
                        obj1 = new Address();
                        obj1.formattedAddress = Platform.emptyToNull(((com.google.android.calendar.api.event.location.Address) (obj2)).formattedAddress);
                        obj1.country = Platform.emptyToNull(((com.google.android.calendar.api.event.location.Address) (obj2)).country);
                        obj1.locality = Platform.emptyToNull(((com.google.android.calendar.api.event.location.Address) (obj2)).locality);
                        obj1.postalCode = Platform.emptyToNull(((com.google.android.calendar.api.event.location.Address) (obj2)).postalCode);
                        obj1.postOfficeBoxNumber = Platform.emptyToNull(((com.google.android.calendar.api.event.location.Address) (obj2)).postOfficeBoxNumber);
                        obj1.region = Platform.emptyToNull(((com.google.android.calendar.api.event.location.Address) (obj2)).region);
                        obj1.streetAddress = Platform.emptyToNull(((com.google.android.calendar.api.event.location.Address) (obj2)).streetAddress);
                    }
                    eventlocation.address = ((Address) (obj1));
                    obj2 = eventlocation1.geo;
                    if (obj2 == null)
                    {
                        obj1 = null;
                    } else
                    {
                        obj1 = new GeoCoordinates();
                        obj1.latitude = Double.valueOf(((com.google.android.calendar.api.event.location.GeoCoordinates) (obj2)).latitude);
                        obj1.longitude = Double.valueOf(((com.google.android.calendar.api.event.location.GeoCoordinates) (obj2)).longitude);
                    }
                    eventlocation.geo = ((GeoCoordinates) (obj1));
                    eventlocation.serverGeocoded = Boolean.valueOf(eventlocation1.serverGeocoded);
                    obj1 = eventlocation;
                }
                arraylist.add(obj1);
                i++;
            }
            structuredlocation = new StructuredLocation();
            structuredlocation.locations = arraylist;
        }
        timelyeventdata.structuredLocation = structuredlocation;
        if (timelyeventdata.structuredLocation == null || timelyeventdata.structuredLocation.isEmpty())
        {
            flag = true;
        }
        return new ExtendedPropertyPair("locationExtra", TimelyEventData.asString(flag, "structured location", timelyeventdata.structuredLocation));
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag;
        if (this == obj)
        {
            flag = true;
        } else
        {
            flag = flag1;
            if (obj != null)
            {
                flag = flag1;
                if (getClass() == obj.getClass())
                {
                    obj = (ExtendedPropertyPair)obj;
                    flag = flag1;
                    if (key.equals(((ExtendedPropertyPair) (obj)).key))
                    {
                        return value.equals(((ExtendedPropertyPair) (obj)).value);
                    }
                }
            }
        }
        return flag;
    }

    public final int hashCode()
    {
        return key.hashCode() * 31 + value.hashCode();
    }
}
