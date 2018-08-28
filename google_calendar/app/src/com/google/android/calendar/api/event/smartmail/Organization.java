// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Platform;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            SmartMailAddress

public class Organization
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public final SmartMailAddress address;
    public final String name;
    public final List phoneNumbers;
    private final String url;

    Organization(Parcel parcel)
    {
        this(parcel.readString(), (SmartMailAddress)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailAddress.getClassLoader()), ((List) (parcel.createStringArrayList())), parcel.readString());
    }

    public Organization(String s, SmartMailAddress smartmailaddress, List list, String s1)
    {
        name = Platform.nullToEmpty(s);
        address = smartmailaddress;
        s = list;
        if (list == null)
        {
            s = Collections.emptyList();
        }
        phoneNumbers = s;
        url = Platform.nullToEmpty(s1);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = false;
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag1;
        if (obj == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (getClass() != obj.getClass())
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (Organization)obj;
        if (address == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (!address.equals(((Organization) (obj)).address))
        {
            continue; /* Loop/switch isn't completed */
        }
_L5:
        flag = flag1;
        if (name.equals(((Organization) (obj)).name))
        {
            flag = flag1;
            if (phoneNumbers.equals(((Organization) (obj)).phoneNumbers))
            {
                return url.equals(((Organization) (obj)).url);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (((Organization) (obj)).address != null)
        {
            return false;
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    public int hashCode()
    {
        int i;
        if (address != null)
        {
            i = address.hashCode();
        } else
        {
            i = 0;
        }
        return ((i * 31 + name.hashCode()) * 31 + phoneNumbers.hashCode()) * 31 + url.hashCode();
    }

    public String toString()
    {
        return String.format("Organization{address=%s, name='%s', phoneNumbers=%s, url='%s'}", new Object[] {
            address, name, phoneNumbers, url
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeParcelable(address, i);
        parcel.writeStringList(phoneNumbers);
        parcel.writeString(url);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new Organization(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new Organization[i];
        }

        _cls1()
        {
        }
    }

}
