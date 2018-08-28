// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;
import com.google.common.base.Absent;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventKey

public final class UncommittedEventKey extends EventKey
{

    public static final android.os.Parcelable.Creator CREATOR;

    public UncommittedEventKey()
    {
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        return obj instanceof UncommittedEventKey;
    }

    public final int hashCode()
    {
        return 0;
    }

    protected final void serializeInternal(StringBuilder stringbuilder)
    {
    }

    public final Optional uri()
    {
        return Absent.INSTANCE;
    }

    public final void writeToParcel(Parcel parcel, int i)
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
                return new UncommittedEventKey();
            }


            private .Lambda._cls0()
            {
            }
        }

        CREATOR = new com.google.android.apps.calendar.util.android.Creators._cls1(.Lambda._cls0..instance, com/google/android/calendar/api/event/UncommittedEventKey);
    }
}
