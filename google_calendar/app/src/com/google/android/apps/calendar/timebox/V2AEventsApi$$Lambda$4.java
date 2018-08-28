// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.api.habit.HabitContract;
import com.google.calendar.v2a.shared.storage.proto.EventBundle;
import com.google.common.base.Predicate;
import com.google.protos.calendar.feapi.v1.DateOrDateTime;
import com.google.protos.calendar.feapi.v1.DateTime;
import com.google.protos.calendar.feapi.v1.Event;
import com.google.protos.calendar.feapi.v1.HabitInstanceData;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            V2AToEntryAdapter

final class arg._cls3
    implements Predicate
{

    private final Map arg$1;
    private final boolean arg$2;
    private final String arg$3;

    public final boolean apply(Object obj)
    {
        Object obj1;
        String s;
        boolean flag;
        boolean flag2;
        Object obj2 = arg$1;
        flag2 = arg$2;
        s = arg$3;
        obj = (EventBundle)obj;
        long l;
        if (((EventBundle) (obj)).baseEvent_ == null)
        {
            obj = Event.DEFAULT_INSTANCE;
        } else
        {
            obj = ((EventBundle) (obj)).baseEvent_;
        }
        if ((((Event) (obj)).bitField1_ & 0x20000) != 0x20000) goto _L2; else goto _L1
_L1:
        if (((Event) (obj)).habitInstance_ == null)
        {
            obj1 = com.google.protos.calendar.feapi.v1.AULT_INSTANCE;
        } else
        {
            obj1 = ((Event) (obj)).habitInstance_;
        }
        obj1 = ((com.google.protos.calendar.feapi.v1.AULT_INSTANCE) (obj1)).itId_;
        if (!((Map) (obj2)).containsKey(obj1)) goto _L4; else goto _L3
_L3:
        l = ((Habit)((Map) (obj2)).get(obj1)).getContract().getUntilMillisUtc();
        if (l == 0L) goto _L4; else goto _L5
_L5:
        if (((Event) (obj)).start_ == null)
        {
            obj1 = DateOrDateTime.DEFAULT_INSTANCE;
        } else
        {
            obj1 = ((Event) (obj)).start_;
        }
        if (((DateOrDateTime) (obj1)).dateTime_ == null)
        {
            obj1 = DateTime.DEFAULT_INSTANCE;
        } else
        {
            obj1 = ((DateOrDateTime) (obj1)).dateTime_;
        }
        if (((DateTime) (obj1)).timeMs_ < l)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L4; else goto _L6
_L6:
        flag = true;
_L7:
        if (!flag)
        {
            if (((Event) (obj)).habitInstance_ == null)
            {
                obj1 = com.google.protos.calendar.feapi.v1.AULT_INSTANCE;
            } else
            {
                obj1 = ((Event) (obj)).habitInstance_;
            }
            if (((com.google.protos.calendar.feapi.v1.AULT_INSTANCE) (obj1)).itInstanceData_ == null)
            {
                obj1 = HabitInstanceData.DEFAULT_INSTANCE;
            } else
            {
                obj1 = ((com.google.protos.calendar.feapi.v1.LT_INSTANCE) (obj1)).itInstanceData_;
            }
            obj2 = com.google.protos.calendar.feapi.v1.s.forNumber(((HabitInstanceData) (obj1)).status_);
            obj1 = obj2;
            if (obj2 == null)
            {
                obj1 = com.google.protos.calendar.feapi.v1.s.UNKNOWN;
            }
            if (obj1 != com.google.protos.calendar.feapi.v1.s.DEFERRAL_REQUESTED)
            {
                break; /* Loop/switch isn't completed */
            }
        }
        return false;
_L4:
        flag = false;
        if (true) goto _L7; else goto _L2
_L2:
        if (flag2 && V2AToEntryAdapter.toSelfAttendeeStatus(((Event) (obj))) == com.google.android.calendar.api.event.attendee..DECLINED)
        {
            return false;
        }
        if (s != null)
        {
            boolean flag1;
            if (((Event) (obj)).habitInstance_ == null)
            {
                obj = com.google.protos.calendar.feapi.v1.AULT_INSTANCE;
            } else
            {
                obj = ((Event) (obj)).habitInstance_;
            }
            obj = ((com.google.protos.calendar.feapi.v1.AULT_INSTANCE) (obj)).itId_;
            if (s == obj || s != null && s.equals(obj))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                return false;
            }
        }
        return true;
    }

    Status(Map map, boolean flag, String s)
    {
        arg$1 = map;
        arg$2 = flag;
        arg$3 = s;
    }
}
