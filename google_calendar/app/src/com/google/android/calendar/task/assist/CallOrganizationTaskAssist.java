// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.google.android.calendar.time.clock.Clock;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.OrganizationAssistance;
import com.google.personalization.assist.annotate.api.PhoneNumber;
import com.google.personalization.assist.annotate.api.TimeSchedule;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.task.assist:
//            CallTaskAssist, ParsedOpeningHours

public final class CallOrganizationTaskAssist extends CallTaskAssist
{

    public CallOrganizationTaskAssist(Assistance assistance, GrammarRuleType grammarruletype, String s, String s1)
    {
        super(assistance, grammarruletype, s, s1);
        mAnalyticsLabel = "callOrganization";
    }

    public final String getAssistInfoText(Context context)
    {
        return getPhoneNumber().phoneNumber_;
    }

    public final String getDisplayText(Context context)
    {
        return getPhoneNumber().phoneNumber_;
    }

    protected final PhoneNumber getPhoneNumber()
    {
        Object obj = assistance;
        if (((Assistance) (obj)).organizationAssistance_ == null)
        {
            obj = OrganizationAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).organizationAssistance_;
        }
        if (((OrganizationAssistance) (obj)).phoneNumber_ == null)
        {
            return PhoneNumber.DEFAULT_INSTANCE;
        } else
        {
            return ((OrganizationAssistance) (obj)).phoneNumber_;
        }
    }

    public final SpannableString getSecondaryDisplayText(Context context)
    {
        Object obj;
        int i;
        long l1;
        obj = assistance;
        if (((Assistance) (obj)).organizationAssistance_ == null)
        {
            obj = OrganizationAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Assistance) (obj)).organizationAssistance_;
        }
        if (((OrganizationAssistance) (obj)).operatingHours_ == null)
        {
            obj = TimeSchedule.DEFAULT_INSTANCE;
        } else
        {
            obj = ((OrganizationAssistance) (obj)).operatingHours_;
        }
        if (obj == null) goto _L2; else goto _L1
_L1:
        obj = new ParsedOpeningHours(((TimeSchedule) (obj)));
        if (!((ParsedOpeningHours) (obj)).isValidSchedule()) goto _L2; else goto _L3
_L3:
        ParsedOpeningHours.NextTime nexttime;
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        nexttime = ((ParsedOpeningHours) (obj)).getNextTime(l1);
        if (nexttime != null)
        {
            if ((long)nexttime.duration == TimeUnit.DAYS.toSeconds(7L))
            {
                i = android.support.v4.content.ModernAsyncTask.Status.ALWAYS_OPEN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKC5PMMBR1EDPMISRK5T862SJJCLI4US35DPKMSPQ8DTQN4SP49TO6ARIJEHGN8P9R0;
            } else
            if (nexttime.isOpen)
            {
                i = android.support.v4.content.ModernAsyncTask.Status.OPEN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKC5PMMBR1EDPMISRK5T862SJJCLI4US35DPKMSPQ8DTQN4SP49TO6ARIJEHGN8P9R0;
            } else
            {
                i = android.support.v4.content.ModernAsyncTask.Status.CLOSED$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKC5PMMBR1EDPMISRK5T862SJJCLI4US35DPKMSPQ8DTQN4SP49TO6ARIJEHGN8P9R0;
            }
        } else
        {
            i = android.support.v4.content.ModernAsyncTask.Status.CLOSED$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKC5PMMBR1EDPMISRK5T862SJJCLI4US35DPKMSPQ8DTQN4SP49TO6ARIJEHGN8P9R0;
        }
        i - 1;
        JVM INSTR tableswitch 0 2: default 124
    //                   0 197
    //                   1 187
    //                   2 197;
           goto _L2 _L4 _L5 _L4
_L2:
        obj = null;
_L7:
        if (obj == null)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L5:
        obj = context.getString(0x7f1300fe);
        continue; /* Loop/switch isn't completed */
_L4:
        Object obj1 = ((ParsedOpeningHours) (obj)).getNextTime(l1);
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        int l = ((ParsedOpeningHours.NextTime)obj1).weekSecond - ((ParsedOpeningHours) (obj)).getSecondOfWeek(l1);
        int k = l;
        if (l <= 0)
        {
            k = (int)((long)l + TimeUnit.DAYS.toSeconds(7L));
        }
        l1 += TimeUnit.SECONDS.toMillis(k);
        obj1 = (new com.google.android.calendar.time.TimeUtils.TimeZoneUtils()).formatDateRange(context, l1, l1, 16385);
        if (i == android.support.v4.content.ModernAsyncTask.Status.OPEN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKC5PMMBR1EDPMISRK5T862SJJCLI4US35DPKMSPQ8DTQN4SP49TO6ARIJEHGN8P9R0)
        {
            obj = context.getString(0x7f1300fe);
        } else
        {
            obj = context.getString(0x7f1300fc);
        }
        obj = context.getString(0x7f1300fd, new Object[] {
            obj, obj1
        });
        if (true) goto _L7; else goto _L6
_L6:
        boolean flag = ((String) (obj)).contains(context.getString(0x7f1300fe));
        String s;
        int j;
        if (flag)
        {
            s = context.getString(0x7f1300fe);
        } else
        {
            s = context.getString(0x7f1300fc);
        }
        if (flag)
        {
            j = 0x7f0d01e6;
        } else
        {
            j = 0x7f0d01f5;
        }
        obj = new SpannableString(((CharSequence) (obj)));
        ((SpannableString) (obj)).setSpan(new ForegroundColorSpan(context.getResources().getColor(j)), 0, s.length(), 33);
        return ((SpannableString) (obj));
    }
}
