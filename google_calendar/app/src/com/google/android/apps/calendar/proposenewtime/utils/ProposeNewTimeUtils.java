// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.utils;

import android.content.Context;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.event.scope.AutoValue_ScopeSelectionDialog_Scope;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.RandomAccess;

public final class ProposeNewTimeUtils
{

    public static List createProposeNewTimeScopes()
    {
        ArrayList arraylist = new ArrayList(Arrays.asList(new Integer[] {
            Integer.valueOf(com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED.ordinal()), Integer.valueOf(com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED.ordinal()), Integer.valueOf(com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE.ordinal())
        }));
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj)
            {
                return ProposeNewTimeUtils.lambda$createProposeNewTimeScopes$0$ProposeNewTimeUtils((Integer)obj);
            }


            private .Lambda._cls0()
            {
            }
        }

        Function function = .Lambda._cls0..instance;
        if (arraylist instanceof RandomAccess)
        {
            return new com.google.common.collect.Lists.TransformingRandomAccessList(arraylist, function);
        } else
        {
            return new com.google.common.collect.Lists.TransformingSequentialList(arraylist, function);
        }
    }

    public static String getProposalString(Response response, long l, Context context)
    {
        if (response == null || response.proposedStartTimeMillis == null || response.proposedEndTimeMillis == null)
        {
            return null;
        }
        Object obj = Utils.getTimeZone(context);
        long l1 = response.proposedStartTimeMillis.longValue();
        Calendar calendar = Calendar.getInstance(((java.util.TimeZone) (obj)));
        calendar.setTimeInMillis(l1);
        obj = Calendar.getInstance(((java.util.TimeZone) (obj)));
        ((Calendar) (obj)).setTimeInMillis(l);
        int i;
        boolean flag;
        if (calendar.get(1) == ((Calendar) (obj)).get(1) && calendar.get(6) == ((Calendar) (obj)).get(6))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = 0x10011;
        } else
        {
            i = 1;
        }
        if (calendar.get(1) == ((Calendar) (obj)).get(1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            i |= 4;
        }
        l = response.proposedStartTimeMillis.longValue();
        l1 = response.proposedEndTimeMillis.longValue();
        return context.getString(0x7f1303c1, new Object[] {
            Utils.tZUtils.formatDateRange(context, l, l1, i)
        });
    }

    public static com.google.android.calendar.api.event.attendee.Response.ResponseStatus indexToResponseStatus(int i)
    {
        if (i < 0 || i >= com.google.android.calendar.api.event.attendee.Response.ResponseStatus.values().length)
        {
            throw new IllegalArgumentException("Unrecognized ResponseStatus.");
        } else
        {
            return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.values()[i];
        }
    }

    static final com.google.android.calendar.event.scope.ScopeSelectionDialog.Scope lambda$createProposeNewTimeScopes$0$ProposeNewTimeUtils(Integer integer)
    {
        int ai[];
        int i;
        i = integer.intValue();
        ai = _cls1..SwitchMap.com.google.android.calendar.api.event.attendee.Response.ResponseStatus;
        if (i < 0 || i >= com.google.android.calendar.api.event.attendee.Response.ResponseStatus.values().length)
        {
            throw new IllegalArgumentException("Unrecognized ResponseStatus.");
        }
        ai[com.google.android.calendar.api.event.attendee.Response.ResponseStatus.values()[i].ordinal()];
        JVM INSTR tableswitch 1 3: default 68
    //                   1 78
    //                   2 94
    //                   3 100;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalArgumentException("Unrecognized ResponseStatus.");
_L2:
        i = 0x7f1303fb;
_L6:
        return new AutoValue_ScopeSelectionDialog_Scope(i, integer.intValue());
_L3:
        i = 0x7f1303f9;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 0x7f1303f8;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static boolean responseHasProposal(Response response)
    {
        return response != null && response.proposedStartTimeMillis != null && response.proposedEndTimeMillis != null;
    }

    static 
    {
        new ArrayList(Arrays.asList(new com.google.android.calendar.api.event.attendee.Response.ResponseStatus[] {
            com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED, com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE
        }));
    }

    private class _cls1
    {

        public static final int $SwitchMap$com$google$android$calendar$api$event$attendee$Response$ResponseStatus[];

        static 
        {
            $SwitchMap$com$google$android$calendar$api$event$attendee$Response$ResponseStatus = new int[com.google.android.calendar.api.event.attendee.Response.ResponseStatus.values().length];
            try
            {
                $SwitchMap$com$google$android$calendar$api$event$attendee$Response$ResponseStatus[com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror2) { }
            try
            {
                $SwitchMap$com$google$android$calendar$api$event$attendee$Response$ResponseStatus[com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror1) { }
            try
            {
                $SwitchMap$com$google$android$calendar$api$event$attendee$Response$ResponseStatus[com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror)
            {
                return;
            }
        }
    }

}
