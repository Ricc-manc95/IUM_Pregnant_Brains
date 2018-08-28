// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventDescriptor, UncommittedEventKey, EventKey

public class UncommittedEventDescriptor
    implements EventDescriptor
{

    public static final android.os.Parcelable.Creator CREATOR;
    public static final UncommittedEventDescriptor INSTANCE = new UncommittedEventDescriptor();

    private UncommittedEventDescriptor()
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public final EventKey getKey()
    {
        return new UncommittedEventKey();
    }

    public final long getOriginalStart()
    {
        return 0L;
    }

    public final boolean isCommitted()
    {
        return false;
    }

    public final boolean isRecurring()
    {
        return false;
    }

    public final boolean isRecurringException()
    {
        return false;
    }

    public final boolean isRecurringPhantom()
    {
        return false;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
    }

    static 
    {
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj)
            {
                return UncommittedEventDescriptor.INSTANCE;
            }


            private .Lambda._cls0()
            {
            }
        }

        CREATOR = new com.google.android.apps.calendar.util.android.Creators._cls1(.Lambda._cls0..instance, com/google/android/calendar/api/event/UncommittedEventDescriptor);
    }
}
