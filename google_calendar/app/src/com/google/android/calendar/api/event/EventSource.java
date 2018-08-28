// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;
import android.os.Parcelable;

public class EventSource
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final String title;
    public final String url;

    EventSource(Parcel parcel)
    {
        this(parcel.readString(), parcel.readString());
    }

    public EventSource(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        url = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        } else
        {
            title = (String)s1;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag;
        if (this == obj)
        {
            flag = true;
        } else
        {
            flag = flag1;
            if (obj != null)
            {
                flag = flag1;
                if (getClass() == obj.getClass())
                {
                    obj = (EventSource)obj;
                    flag = flag1;
                    if (url.equals(((EventSource) (obj)).url))
                    {
                        return title.equals(((EventSource) (obj)).title);
                    }
                }
            }
        }
        return flag;
    }

    public int hashCode()
    {
        return url.hashCode() * 31 + title.hashCode();
    }

    public String toString()
    {
        return String.format("EventSource{url='%s', title='%s'}", new Object[] {
            url, title
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(url);
        parcel.writeString(title);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EventSource(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new EventSource[i];
        }

        _cls1()
        {
        }
    }

}
