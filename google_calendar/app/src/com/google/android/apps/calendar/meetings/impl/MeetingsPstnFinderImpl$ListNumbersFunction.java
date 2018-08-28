// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.meetings.impl;

import com.google.android.calendar.event.conference.AutoValue_PhoneNumberDetails;
import com.google.android.calendar.event.conference.Availability;
import com.google.android.calendar.event.conference.LocalPhoneSource;
import com.google.common.base.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.apps.calendar.meetings.impl:
//            MeetingsPstnFinderImpl

final class pin
    implements Function
{

    private final String pin;
    private final MeetingsPstnFinderImpl this$0;

    public final Object apply(Object obj)
    {
        ArrayList arraylist;
        Iterator iterator;
        obj = (com.google.protos.communication.meetings.proto.vailability)obj;
        arraylist = new ArrayList();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_256;
        }
        iterator = Collections.unmodifiableMap(((com.google.protos.communication.meetings.proto.vailability) (obj)).vailability).entrySet().iterator();
_L2:
        String s;
        String s1;
        String s2;
        com.google.protos.communication.meetings.proto.pin pin1;
        do
        {
            do
            {
                if (!iterator.hasNext())
                {
                    break MISSING_BLOCK_LABEL_256;
                }
                obj = (java.util.ility)iterator.next();
                s = (String)((java.util.ility) (obj)).ility();
                obj = (com.google.protos.communication.meetings.proto.vailability)((java.util.ility) (obj)).ility();
            } while (((com.google.protos.communication.meetings.proto.vailability) (obj)).vailability.phoneNumbers_() == 0);
            pin1 = (com.google.protos.communication.meetings.proto.vailability)((com.google.protos.communication.meetings.proto.vailability) (obj)).vailability.phoneNumbers_(0);
            com.google.protos.communication.meetings.proto.vailability vailability = com.google.protos.communication.meetings.proto.vailability.forNumber(pin1.vailability_);
            obj = vailability;
            if (vailability == null)
            {
                obj = com.google.protos.communication.meetings.proto.vailability.UNRECOGNIZED;
            }
        } while (obj == com.google.protos.communication.meetings.proto.vailability.NONE);
        s1 = pin1._fld164_;
        s2 = pin;
        obj = MeetingsPstnFinderImpl.this;
        com.google.protos.communication.meetings.proto.vailability vailability1 = com.google.protos.communication.meetings.proto.vailability.forNumber(pin1.vailability_);
        obj = vailability1;
        if (vailability1 == null)
        {
            obj = com.google.protos.communication.meetings.proto.vailability.UNRECOGNIZED;
        }
        switch (((com.google.protos.communication.meetings.proto.vailability) (obj)).ordinal())
        {
        default:
            obj = Availability.NONE;
            break;

        case 2: // '\002'
        case 3: // '\003'
            break; /* Loop/switch isn't completed */

        case 4: // '\004'
            break MISSING_BLOCK_LABEL_249;
        }
_L3:
        LocalPhoneSource localphonesource = LocalPhoneSource.FULL_LIST;
        arraylist.add(new AutoValue_PhoneNumberDetails(s1, s2, new Locale("", s), ((Availability) (obj)), localphonesource));
        if (true) goto _L2; else goto _L1
_L1:
        obj = Availability.INTERNAL_ONLY;
          goto _L3
        obj = Availability.PUBLIC;
          goto _L3
        return arraylist;
    }

    vailability(String s)
    {
        this$0 = MeetingsPstnFinderImpl.this;
        super();
        pin = s;
    }
}
