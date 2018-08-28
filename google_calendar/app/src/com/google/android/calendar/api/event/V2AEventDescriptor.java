// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.calendar.v2a.shared.storage.proto.CalendarKey;
import com.google.calendar.v2a.shared.storage.proto.EventBundle;
import com.google.calendar.v2a.shared.storage.proto.EventInstance;
import com.google.calendar.v2a.shared.storage.proto.EventKey;
import com.google.calendar.v2a.shared.storage.proto.InstanceTimes;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protos.calendar.feapi.v1.DateOrDateTime;
import com.google.protos.calendar.feapi.v1.Event;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventDescriptor, V2AEventKey, V2AEventAdapter, EventKey

public abstract class V2AEventDescriptor
    implements EventDescriptor
{

    public V2AEventDescriptor()
    {
    }

    public static V2AEventDescriptor newInstance(EventBundle eventbundle, EventInstance eventinstance)
    {
        Event event;
        Object obj;
        .AutoValue_V2AEventDescriptor.Builder builder1;
        com.google.calendar.v2a.shared.storage.proto.EventKey.Builder builder2;
        EventKey eventkey;
        if (eventbundle.baseEvent_ == null)
        {
            event = Event.DEFAULT_INSTANCE;
        } else
        {
            event = eventbundle.baseEvent_;
        }
        builder1 = new .AutoValue_V2AEventDescriptor.Builder();
        builder2 = (com.google.calendar.v2a.shared.storage.proto.EventKey.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)EventKey.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        if (eventbundle.calendarKey_ == null)
        {
            obj = CalendarKey.DEFAULT_INSTANCE;
        } else
        {
            obj = eventbundle.calendarKey_;
        }
        builder2.copyOnWrite();
        eventkey = (EventKey)builder2.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        eventkey.calendarKey_ = ((CalendarKey) (obj));
        eventkey.bitField0_ = eventkey.bitField0_ | 1;
        obj = eventinstance.eventId_;
        builder2.copyOnWrite();
        eventkey = (EventKey)builder2.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        eventkey.bitField0_ = eventkey.bitField0_ | 2;
        eventkey.eventId_ = ((String) (obj));
        builder1.key(V2AEventKey.newInstance((EventKey)(GeneratedMessageLite)builder2.build()));
        if ((eventbundle.bitField0_ & 4) == 4)
        {
            boolean flag;
            if (eventbundle.baseEvent_ == null)
            {
                eventbundle = Event.DEFAULT_INSTANCE;
            } else
            {
                eventbundle = eventbundle.baseEvent_;
            }
            if ((((Event) (eventbundle)).bitField0_ & 0x100000) == 0x100000)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                eventinstance = builder1.recurringException(true).recurringPhantom(false);
                if (event.originalStartTime_ == null)
                {
                    eventbundle = DateOrDateTime.DEFAULT_INSTANCE;
                } else
                {
                    eventbundle = event.originalStartTime_;
                }
                eventinstance.originalStart(V2AEventAdapter.toUtcMillis(eventbundle));
            } else
            {
                Builder builder = builder1.recurringException(false).recurringPhantom(true);
                if (eventinstance.localTimes_ == null)
                {
                    eventbundle = InstanceTimes.DEFAULT_INSTANCE;
                } else
                {
                    eventbundle = eventinstance.localTimes_;
                }
                builder.originalStart(((InstanceTimes) (eventbundle)).rawStartMs_);
            }
        } else
        {
            builder1.recurringException(false).recurringPhantom(false).originalStart(0L);
        }
        return builder1.build();
    }

    public int describeContents()
    {
        return 0;
    }

    public volatile com.google.android.calendar.api.event.EventKey getKey()
    {
        return getKey();
    }

    public abstract V2AEventKey getKey();

    public final boolean isCommitted()
    {
        return true;
    }

    public final boolean isRecurring()
    {
        return isRecurringException() || isRecurringPhantom();
    }

    private class Builder
    {

        public abstract V2AEventDescriptor build();

        public abstract Builder key(V2AEventKey v2aeventkey);

        public abstract Builder originalStart(long l);

        public abstract Builder recurringException(boolean flag);

        public abstract Builder recurringPhantom(boolean flag);

        Builder()
        {
        }
    }

}
