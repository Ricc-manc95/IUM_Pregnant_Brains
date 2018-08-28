// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcelable;
import com.android.calendarcommon2.LogUtils;
import com.google.common.base.CharMatcher;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Ordering;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.api.event:
//            AutoValue_CpEventKey, AutoValue_V2AEventKey, CpEventKey, V2AEventKey, 
//            UncommittedEventKey

public abstract class EventKey
    implements Parcelable
{
    public static interface Persisted
    {
    }


    public static final Comparator COMPARATOR;
    public static final Splitter SERIALIZED_DATA_SPLITTER;
    private static final String TAG;

    EventKey()
    {
    }

    public static EventKey deserialize(String s)
    {
        String s2 = com/google/android/calendar/api/event/AutoValue_CpEventKey.getSimpleName();
        String s1 = com/google/android/calendar/api/event/AutoValue_V2AEventKey.getSimpleName();
        if (s.startsWith((new StringBuilder(String.valueOf(s2).length() + 1)).append(s2).append('|').toString()))
        {
            return CpEventKey.newInstance(s.substring(s2.length() + 1));
        }
        s2 = com/google/android/calendar/api/event/AutoValue_V2AEventKey.getSimpleName();
        if (s.startsWith((new StringBuilder(String.valueOf(s2).length() + 1)).append(s2).append('|').toString()))
        {
            return V2AEventKey.newInstance(s.substring(s1.length() + 1));
        } else
        {
            LogUtils.wtf(TAG, "Unknown key type serialized: %s", new Object[] {
                s
            });
            return null;
        }
    }

    static final int lambda$static$0$EventKey(EventKey eventkey, EventKey eventkey1)
    {
        int i = 0;
        int j = 0;
        if (eventkey == null || eventkey1 == null)
        {
            if (eventkey == null)
            {
                i = 0;
            } else
            {
                i = 1;
            }
            if (eventkey1 != null)
            {
                j = 1;
            }
            i -= j;
        } else
        {
            if (!eventkey.getClass().isAssignableFrom(eventkey1.getClass()) && !eventkey1.getClass().isAssignableFrom(eventkey.getClass()))
            {
                return eventkey.getClass().hashCode() - eventkey1.getClass().hashCode();
            }
            if (!(eventkey instanceof UncommittedEventKey))
            {
                if (eventkey instanceof CpEventKey)
                {
                    return CpEventKey.COMPARATOR.compare((CpEventKey)eventkey, (CpEventKey)eventkey1);
                }
                if (eventkey instanceof V2AEventKey)
                {
                    return V2AEventKey.ORDERING.compare((V2AEventKey)eventkey, (V2AEventKey)eventkey1);
                } else
                {
                    LogUtils.wtf(TAG, "Unable to compare %s %s", new Object[] {
                        eventkey, eventkey1
                    });
                    return 0;
                }
            }
        }
        return i;
    }

    public abstract void serializeInternal(StringBuilder stringbuilder);

    public abstract Optional uri();

    static 
    {
        CharMatcher charmatcher = CharMatcher.is('|');
        if (charmatcher == null)
        {
            throw new NullPointerException();
        } else
        {
            SERIALIZED_DATA_SPLITTER = new Splitter(new com.google.common.base.Splitter._cls1(charmatcher));
            TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/EventKey);
            class .Lambda._cls0
                implements Comparator
            {

                public static final Comparator $instance = new .Lambda._cls0();

                public final int compare(Object obj, Object obj1)
                {
                    return EventKey.lambda$static$0$EventKey((EventKey)obj, (EventKey)obj1);
                }


            private .Lambda._cls0()
            {
            }
            }

            COMPARATOR = .Lambda._cls0..instance;
        }
    }
}
