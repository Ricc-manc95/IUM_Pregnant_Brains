// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference;

import android.content.Context;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.api.event.conference.ConferenceData;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;

public final class ConferenceTypes
{

    private static final ImmutableCollection VIDEO_CONFERENCE_TYPES = ImmutableList.of(Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(3));

    static final boolean bridge$lambda$0$ConferenceTypes(Conference conference)
    {
        return "stream".equals(conference.getEntryPointType());
    }

    public static Conference getLiveStreamConference(Context context, ConferenceData conferencedata)
    {
        if (!ExperimentalOptions.isBroadcastingEnabled(context))
        {
            return null;
        }
        conferencedata = conferencedata.getConferences();
        class .Lambda._cls1
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls1();

            public final boolean apply(Object obj1)
            {
                return ConferenceTypes.bridge$lambda$0$ConferenceTypes((Conference)obj1);
            }


            private .Lambda._cls1()
            {
            }
        }

        context = .Lambda._cls1..instance;
        conferencedata = conferencedata.iterator();
        if (conferencedata == null)
        {
            throw new NullPointerException();
        }
        if (context == null)
        {
            throw new NullPointerException();
        }
_L4:
        if (!conferencedata.hasNext()) goto _L2; else goto _L1
_L1:
        Object obj = conferencedata.next();
        if (!context.apply(obj)) goto _L4; else goto _L3
_L3:
        if (obj == null)
        {
            throw new NullPointerException();
        }
        context = new Present(obj);
_L6:
        return (Conference)context.orNull();
_L2:
        context = Absent.INSTANCE;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Conference getPhoneConference(ConferenceData conferencedata)
    {
        Object obj;
        obj = conferencedata.getConferences();
        class .Lambda._cls2
            implements Predicate
        {

            private final int arg$1;

            public final boolean apply(Object obj2)
            {
                return ConferenceTypes.lambda$firstConferenceWithType$1$ConferenceTypes(arg$1, (Conference)obj2);
            }

            .Lambda._cls2(int i)
            {
                arg$1 = i;
            }
        }

        conferencedata = new .Lambda._cls2(4);
        obj = ((Iterable) (obj)).iterator();
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (conferencedata == null)
        {
            throw new NullPointerException();
        }
_L4:
        if (!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
        Object obj1 = ((Iterator) (obj)).next();
        if (!conferencedata.apply(obj1)) goto _L4; else goto _L3
_L3:
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        conferencedata = new Present(obj1);
_L6:
        return (Conference)conferencedata.orNull();
_L2:
        conferencedata = Absent.INSTANCE;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Conference getPhoneNumbersLinkConference(ConferenceData conferencedata)
    {
        Object obj;
        obj = conferencedata.getConferences();
        conferencedata = new .Lambda._cls2(5);
        obj = ((Iterable) (obj)).iterator();
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (conferencedata == null)
        {
            throw new NullPointerException();
        }
_L4:
        if (!((Iterator) (obj)).hasNext()) goto _L2; else goto _L1
_L1:
        Object obj1 = ((Iterator) (obj)).next();
        if (!conferencedata.apply(obj1)) goto _L4; else goto _L3
_L3:
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        conferencedata = new Present(obj1);
_L6:
        return (Conference)conferencedata.orNull();
_L2:
        conferencedata = Absent.INSTANCE;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Conference getVideoConference(Context context, ConferenceData conferencedata)
    {
        conferencedata = conferencedata.getConferences();
        class .Lambda._cls0
            implements Predicate
        {

            private final Context arg$1;

            public final boolean apply(Object obj1)
            {
                return ConferenceTypes.lambda$getVideoConference$0$ConferenceTypes(arg$1, (Conference)obj1);
            }

            .Lambda._cls0(Context context)
            {
                arg$1 = context;
            }
        }

        context = new .Lambda._cls0(context);
        conferencedata = conferencedata.iterator();
        if (conferencedata == null)
        {
            throw new NullPointerException();
        }
        if (context == null)
        {
            throw new NullPointerException();
        }
_L4:
        if (!conferencedata.hasNext()) goto _L2; else goto _L1
_L1:
        Object obj = conferencedata.next();
        if (!context.apply(obj)) goto _L4; else goto _L3
_L3:
        if (obj == null)
        {
            throw new NullPointerException();
        }
        context = new Present(obj);
_L6:
        return (Conference)context.orNull();
_L2:
        context = Absent.INSTANCE;
        if (true) goto _L6; else goto _L5
_L5:
    }

    static final boolean lambda$firstConferenceWithType$1$ConferenceTypes(int i, Conference conference)
    {
        return conference.getType() == i;
    }

    static final boolean lambda$getVideoConference$0$ConferenceTypes(Context context, Conference conference)
    {
        if (!ExperimentalOptions.isBroadcastingEnabled(context))
        {
            return VIDEO_CONFERENCE_TYPES.contains(Integer.valueOf(conference.getType()));
        } else
        {
            return "video".equals(conference.getEntryPointType());
        }
    }

}
