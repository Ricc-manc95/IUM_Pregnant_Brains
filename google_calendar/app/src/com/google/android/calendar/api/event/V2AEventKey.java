// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.utils.proto.ProtoAdapter;
import com.google.calendar.v2a.shared.storage.proto.AccountKey;
import com.google.calendar.v2a.shared.storage.proto.CalendarKey;
import com.google.calendar.v2a.shared.storage.proto.EventKey;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.ByFunctionOrdering;
import com.google.common.collect.CompoundOrdering;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.Ordering;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.util.Comparator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventKey, AutoValue_V2AEventKey

public abstract class V2AEventKey extends com.google.android.calendar.api.event.EventKey
    implements EventKey.Persisted
{
    public static final class EventKeyAdapter extends ProtoAdapter
    {

        protected final MessageLite parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (EventKey)GeneratedMessageLite.parseFrom(EventKey.DEFAULT_INSTANCE, abyte0);
        }

        public EventKeyAdapter()
        {
        }
    }


    public static final Ordering ORDERING;

    V2AEventKey()
    {
    }

    public static V2AEventKey newInstance(CalendarKey calendarkey, String s)
    {
        com.google.calendar.v2a.shared.storage.proto.EventKey.Builder builder = (com.google.calendar.v2a.shared.storage.proto.EventKey.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)EventKey.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        builder.copyOnWrite();
        EventKey eventkey = (EventKey)builder.instance;
        if (calendarkey == null)
        {
            throw new NullPointerException();
        }
        eventkey.calendarKey_ = calendarkey;
        eventkey.bitField0_ = eventkey.bitField0_ | 1;
        builder.copyOnWrite();
        calendarkey = (EventKey)builder.instance;
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            calendarkey.bitField0_ = ((EventKey) (calendarkey)).bitField0_ | 2;
            calendarkey.eventId_ = s;
            return new AutoValue_V2AEventKey((EventKey)(GeneratedMessageLite)builder.build());
        }
    }

    public static V2AEventKey newInstance(EventKey eventkey)
    {
        return new AutoValue_V2AEventKey(eventkey);
    }

    static V2AEventKey newInstance(String s)
    {
        s = SERIALIZED_DATA_SPLITTER.splitToList(s);
        com.google.calendar.v2a.shared.storage.proto.CalendarKey.Builder builder = (com.google.calendar.v2a.shared.storage.proto.CalendarKey.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarKey.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj = (com.google.calendar.v2a.shared.storage.proto.AccountKey.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)AccountKey.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj1 = (String)s.get(0);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        AccountKey accountkey = (AccountKey)((com.google.calendar.v2a.shared.storage.proto.AccountKey.Builder) (obj)).instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        accountkey.bitField0_ = accountkey.bitField0_ | 1;
        accountkey.accountId_ = ((String) (obj1));
        obj = (AccountKey)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
        builder.copyOnWrite();
        obj1 = (CalendarKey)builder.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj1.accountKey_ = ((AccountKey) (obj));
        obj1.bitField0_ = ((CalendarKey) (obj1)).bitField0_ | 1;
        obj = (String)s.get(1);
        builder.copyOnWrite();
        obj1 = (CalendarKey)builder.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            obj1.bitField0_ = ((CalendarKey) (obj1)).bitField0_ | 2;
            obj1.calendarId_ = ((String) (obj));
            return newInstance((CalendarKey)(GeneratedMessageLite)builder.build(), (String)s.get(2));
        }
    }

    final String getAccountId()
    {
        Object obj = getV2Key();
        if (((EventKey) (obj)).calendarKey_ == null)
        {
            obj = CalendarKey.DEFAULT_INSTANCE;
        } else
        {
            obj = ((EventKey) (obj)).calendarKey_;
        }
        if (((CalendarKey) (obj)).accountKey_ == null)
        {
            obj = AccountKey.DEFAULT_INSTANCE;
        } else
        {
            obj = ((CalendarKey) (obj)).accountKey_;
        }
        return ((AccountKey) (obj)).accountId_;
    }

    public abstract EventKey getV2Key();

    protected final void serializeInternal(StringBuilder stringbuilder)
    {
        StringBuilder stringbuilder1 = stringbuilder.append(getAccountId()).append('|');
        stringbuilder = getV2Key();
        if (((EventKey) (stringbuilder)).calendarKey_ == null)
        {
            stringbuilder = CalendarKey.DEFAULT_INSTANCE;
        } else
        {
            stringbuilder = ((EventKey) (stringbuilder)).calendarKey_;
        }
        stringbuilder1.append(((CalendarKey) (stringbuilder)).calendarId_).append('|').append(getV2Key().eventId_);
    }

    public final Optional uri()
    {
        return Absent.INSTANCE;
    }

    static 
    {
        Object obj = NaturalOrdering.INSTANCE;
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj2)
            {
                return ((V2AEventKey)obj2).getAccountId();
            }


            private .Lambda._cls0()
            {
            }
        }

        obj = new ByFunctionOrdering(.Lambda._cls0..instance, ((Ordering) (obj)));
        Object obj1 = NaturalOrdering.INSTANCE;
        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj2)
            {
                obj2 = ((V2AEventKey)obj2).getV2Key();
                if (((EventKey) (obj2)).calendarKey_ == null)
                {
                    obj2 = CalendarKey.DEFAULT_INSTANCE;
                } else
                {
                    obj2 = ((EventKey) (obj2)).calendarKey_;
                }
                return ((CalendarKey) (obj2)).calendarId_;
            }


            private .Lambda._cls1()
            {
            }
        }

        obj1 = new ByFunctionOrdering(.Lambda._cls1..instance, ((Ordering) (obj1)));
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        obj = new CompoundOrdering(((Comparator) (obj)), (Comparator)obj1);
        obj1 = NaturalOrdering.INSTANCE;
        class .Lambda._cls2
            implements Function
        {

            public static final Function $instance = new .Lambda._cls2();

            public final Object apply(Object obj2)
            {
                return ((V2AEventKey)obj2).getV2Key().eventId_;
            }


            private .Lambda._cls2()
            {
            }
        }

        obj1 = new ByFunctionOrdering(.Lambda._cls2..instance, ((Ordering) (obj1)));
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            ORDERING = new CompoundOrdering(((Comparator) (obj)), (Comparator)obj1);
        }
    }
}
