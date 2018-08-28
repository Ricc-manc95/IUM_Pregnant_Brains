// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import com.google.api.services.calendar.model.CalendarGoTo;
import com.google.api.services.calendar.model.Organization;
import com.google.api.services.calendar.model.SmartMailAddress;
import com.google.api.services.calendar.model.Time;
import com.google.common.base.Platform;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            SmartMailActionTarget, SmartMailAddress, Organization, SmartMailTime

public final class SmartMailStoreUtils
{

    public static SmartMailActionTarget toApiActionTarget(CalendarGoTo calendargoto)
    {
        String s;
        int i;
        boolean flag;
        flag = true;
        if (calendargoto == null)
        {
            return null;
        }
        s = calendargoto.type;
        i = ((flag) ? 1 : 0);
        if (s == null) goto _L2; else goto _L1
_L1:
        byte byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 11: default 124
    //                   -1102508601: 298
    //                   -987494927: 343
    //                   -284840886: 214
    //                   110760: 358
    //                   3108362: 242
    //                   3619493: 270
    //                   109400031: 284
    //                   110621003: 256
    //                   185106769: 313
    //                   742313037: 228
    //                   794877248: 328;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L3:
        i = ((flag) ? 1 : 0);
        byte0;
        JVM INSTR tableswitch 0 10: default 188
    //                   0 191
    //                   1 373
    //                   2 378
    //                   3 383
    //                   4 388
    //                   5 393
    //                   6 399
    //                   7 405
    //                   8 411
    //                   9 417
    //                   10 423;
           goto _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26
_L26:
        break MISSING_BLOCK_LABEL_423;
_L16:
        break; /* Loop/switch isn't completed */
_L15:
        i = ((flag) ? 1 : 0);
_L2:
        return new SmartMailActionTarget(i, Platform.nullToEmpty(calendargoto.uri), Platform.nullToEmpty(calendargoto.text));
_L6:
        if (s.equals("unknown"))
        {
            byte0 = 0;
        }
          goto _L3
_L13:
        if (s.equals("checkIn"))
        {
            byte0 = 1;
        }
          goto _L3
_L8:
        if (s.equals("edit"))
        {
            byte0 = 2;
        }
          goto _L3
_L11:
        if (s.equals("track"))
        {
            byte0 = 3;
        }
          goto _L3
_L9:
        if (s.equals("view"))
        {
            byte0 = 4;
        }
          goto _L3
_L10:
        if (s.equals("share"))
        {
            byte0 = 5;
        }
          goto _L3
_L4:
        if (s.equals("listen"))
        {
            byte0 = 6;
        }
          goto _L3
_L12:
        if (s.equals("structured"))
        {
            byte0 = 7;
        }
          goto _L3
_L14:
        if (s.equals("videoMeeting"))
        {
            byte0 = 8;
        }
          goto _L3
_L5:
        if (s.equals("provider"))
        {
            byte0 = 9;
        }
          goto _L3
_L7:
        if (s.equals("pay"))
        {
            byte0 = 10;
        }
          goto _L3
_L17:
        i = 2;
          goto _L2
_L18:
        i = 3;
          goto _L2
_L19:
        i = 4;
          goto _L2
_L20:
        i = 5;
          goto _L2
_L21:
        i = 6;
          goto _L2
_L22:
        i = 7;
          goto _L2
_L23:
        i = 8;
          goto _L2
_L24:
        i = 9;
          goto _L2
_L25:
        i = 10;
          goto _L2
        i = 11;
          goto _L2
    }

    public static com.google.android.calendar.api.event.smartmail.SmartMailAddress toApiAddress(SmartMailAddress smartmailaddress)
    {
        if (smartmailaddress == null)
        {
            return null;
        } else
        {
            return new com.google.android.calendar.api.event.smartmail.SmartMailAddress(Platform.nullToEmpty(smartmailaddress.name), Platform.nullToEmpty(smartmailaddress.streetAddress), Platform.nullToEmpty(smartmailaddress.locality), Platform.nullToEmpty(smartmailaddress.region), Platform.nullToEmpty(smartmailaddress.postalCode), toApiActionTarget(smartmailaddress.googleMapLink), Platform.nullToEmpty(smartmailaddress.latitude), Platform.nullToEmpty(smartmailaddress.longitude));
        }
    }

    public static com.google.android.calendar.api.event.smartmail.Organization toApiOrganization(Organization organization)
    {
        if (organization == null)
        {
            return null;
        } else
        {
            return new com.google.android.calendar.api.event.smartmail.Organization(organization.name, toApiAddress(organization.address), organization.phoneNumbers, organization.url);
        }
    }

    public static SmartMailTime toApiTime(Time time)
    {
        boolean flag = false;
        if (time == null || time.timeMs == null || time.timeMs.longValue() < 0L)
        {
            return null;
        }
        long l = time.timeMs.longValue();
        int i;
        if (time.timeZoneOffsetMinutes == null)
        {
            i = 0;
        } else
        {
            i = time.timeZoneOffsetMinutes.intValue();
        }
        if (time.dateOnly != null)
        {
            flag = time.dateOnly.booleanValue();
        }
        return new SmartMailTime(l, i, flag);
    }
}
