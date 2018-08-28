// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Platform;

public class ContactId
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final String identity;
    public final String namespace;

    ContactId(Parcel parcel)
    {
        this(parcel.readString(), parcel.readString());
    }

    public ContactId(String s, String s1)
    {
        boolean flag;
        if (!Platform.stringIsNullOrEmpty(s) && !Platform.stringIsNullOrEmpty(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            identity = Platform.nullToEmpty(s);
            namespace = Platform.nullToEmpty(s1);
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
            if (obj instanceof ContactId)
            {
                obj = (ContactId)obj;
                flag = flag1;
                if (identity.equals(((ContactId) (obj)).identity))
                {
                    return namespace.equals(((ContactId) (obj)).namespace);
                }
            }
        }
        return flag;
    }

    public int hashCode()
    {
        return identity.hashCode() * 31 + namespace.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(identity);
        parcel.writeString(namespace);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new ContactId(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new ContactId[i];
        }

        _cls1()
        {
        }
    }

}
