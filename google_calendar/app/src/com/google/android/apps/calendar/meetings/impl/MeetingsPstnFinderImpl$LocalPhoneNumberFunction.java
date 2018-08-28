// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.meetings.impl;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.google.android.calendar.event.conference.AutoValue_PhoneNumberDetails;
import com.google.android.calendar.event.conference.Availability;
import com.google.android.calendar.event.conference.LocalPhoneSource;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.base.Present;
import java.util.Locale;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.meetings.impl:
//            MeetingsPstnFinderImpl

final class pin
    implements Function
{

    private final String excludedRegionCode;
    private final String pin;
    private final MeetingsPstnFinderImpl this$0;

    private final Optional findPhoneForCountry(String s, com.google.protos.communication.meetings.proto.pin pin1, boolean flag, LocalPhoneSource localphonesource)
    {
        String s1;
        String s2;
        String s3;
        do
        {
            if (TextUtils.isEmpty(s))
            {
                return Absent.INSTANCE;
            }
            s1 = s.toUpperCase(Locale.ENGLISH);
            s = excludedRegionCode;
            boolean flag1;
            if (s1 == s || s1 != null && s1.equals(s))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                return Absent.INSTANCE;
            }
            if (s1 == null)
            {
                throw new NullPointerException();
            }
            s = pin1.excludedRegionCode;
            if (s.containsKey(s1))
            {
                s = (com.google.protos.communication.meetings.proto.excludedRegionCode)s.get(s1);
            } else
            {
                s = null;
            }
            if (s == null)
            {
                s = Absent.INSTANCE;
            } else
            {
                s = new Present(s);
            }
            if (!s.isPresent())
            {
                return Absent.INSTANCE;
            }
            if (!flag)
            {
                break;
            }
            s = ((com.google.protos.communication.meetings.proto.excludedRegionCode)s.get()).excludedRegionCode;
            flag = false;
        } while (true);
        if (((com.google.protos.communication.meetings.proto.excludedRegionCode)s.get()).excludedRegionCode.phoneNumbers_() == 0)
        {
            return Absent.INSTANCE;
        }
        com.google.protos.communication.meetings.proto.pin pin2 = (com.google.protos.communication.meetings.proto.excludedRegionCode)((com.google.protos.communication.meetings.proto.excludedRegionCode)s.get()).excludedRegionCode.phoneNumbers_(0);
        pin1 = com.google.protos.communication.meetings.proto.bility.forNumber(pin2.bility_);
        s = pin1;
        if (pin1 == null)
        {
            s = com.google.protos.communication.meetings.proto.bility.UNRECOGNIZED;
        }
        if (s == com.google.protos.communication.meetings.proto.bility.NONE)
        {
            return Absent.INSTANCE;
        }
        s2 = pin2.NONE;
        s3 = pin;
        s = MeetingsPstnFinderImpl.this;
        pin1 = com.google.protos.communication.meetings.proto.bility.forNumber(pin2.bility_);
        s = pin1;
        if (pin1 == null)
        {
            s = com.google.protos.communication.meetings.proto.bility.UNRECOGNIZED;
        }
        s.ordinal();
        JVM INSTR tableswitch 2 4: default 300
    //                   2 342
    //                   3 342
    //                   4 349;
           goto _L1 _L2 _L2 _L3
_L3:
        break MISSING_BLOCK_LABEL_349;
_L1:
        s = Availability.NONE;
_L4:
        s = new AutoValue_PhoneNumberDetails(s2, s3, new Locale("", s1), s, localphonesource);
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(s);
        }
_L2:
        s = Availability.INTERNAL_ONLY;
          goto _L4
        s = Availability.PUBLIC;
          goto _L4
    }

    public final Object apply(Object obj)
    {
        obj = (com.google.protos.communication.meetings.proto.bility.ordinal)obj;
        Object obj2 = (TelephonyManager)context.getSystemService("phone");
        Object obj1 = ((TelephonyManager) (obj2)).getNetworkCountryIso();
        obj2 = ((TelephonyManager) (obj2)).getSimCountryIso();
        Optional optional = findPhoneForCountry(((String) (obj1)), ((com.google.protos.communication.meetings.proto.findPhoneForCountry) (obj)), false, LocalPhoneSource.LOCAL_NETWORK);
        if (optional.isPresent())
        {
            return (PhoneNumberDetails)optional.get();
        }
        optional = findPhoneForCountry(((String) (obj2)), ((com.google.protos.communication.meetings.proto.findPhoneForCountry) (obj)), false, LocalPhoneSource.LOCAL_SIM);
        if (optional.isPresent())
        {
            return (PhoneNumberDetails)optional.get();
        }
        obj1 = findPhoneForCountry(((String) (obj1)), ((com.google.protos.communication.meetings.proto.findPhoneForCountry) (obj)), true, LocalPhoneSource.REGIONAL_NETWORK);
        if (((Optional) (obj1)).isPresent())
        {
            return (PhoneNumberDetails)((Optional) (obj1)).get();
        }
        obj = findPhoneForCountry(((String) (obj2)), ((com.google.protos.communication.meetings.proto.findPhoneForCountry) (obj)), true, LocalPhoneSource.REGIONAL_SIM);
        if (((Optional) (obj)).isPresent())
        {
            return (PhoneNumberDetails)((Optional) (obj)).get();
        } else
        {
            return null;
        }
    }

    bility(String s, String s1)
    {
        this$0 = MeetingsPstnFinderImpl.this;
        super();
        excludedRegionCode = Platform.nullToEmpty(s).toUpperCase();
        pin = s1;
    }
}
