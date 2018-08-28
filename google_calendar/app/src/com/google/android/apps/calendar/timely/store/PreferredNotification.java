// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

public final class PreferredNotification
    implements Parcelable
{

    public static final android.os.Parcelable.ClassLoaderCreator CREATOR = new _cls1();
    public final int allDay;
    public final int method;
    public final int minutes;

    public PreferredNotification(int i, int j, int k)
    {
        allDay = i;
        minutes = j;
        method = k;
    }

    public PreferredNotification(Parcel parcel)
    {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        boolean flag3 = true;
        if (obj != null && (obj instanceof PreferredNotification)) goto _L2; else goto _L1
_L1:
        boolean flag2 = false;
_L4:
        return flag2;
_L2:
        flag2 = flag3;
        if (obj == this) goto _L4; else goto _L3
_L3:
        obj = (PreferredNotification)obj;
        boolean flag;
        boolean flag1;
        if (((PreferredNotification) (obj)).allDay > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (allDay > 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag != flag1 || ((PreferredNotification) (obj)).method != method)
        {
            break; /* Loop/switch isn't completed */
        }
        flag2 = flag3;
        if (((PreferredNotification) (obj)).minutes == minutes) goto _L4; else goto _L5
_L5:
        return false;
    }

    public final int hashCode()
    {
        boolean flag = true;
        int i;
        if (allDay > 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        return ((i + 527) * 31 + method) * 31 + minutes;
    }

    public final ContentValues toContentValues()
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("allday", Integer.valueOf(allDay));
        contentvalues.put("minutes", Integer.valueOf(minutes));
        contentvalues.put("method", Integer.valueOf(method));
        return contentvalues;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        StringBuilder stringbuilder1 = stringbuilder.append("All day? ");
        String s;
        if (allDay != 0)
        {
            s = "true";
        } else
        {
            s = "false";
        }
        stringbuilder1.append(s).append("\n");
        stringbuilder.append("Minutes: ").append(minutes).append("\n");
        stringbuilder.append("Method: ").append(method);
        return stringbuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(allDay);
        parcel.writeInt(minutes);
        parcel.writeInt(method);
    }


    private class _cls1
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new PreferredNotification(parcel);
        }

        public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new PreferredNotification(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new PreferredNotification[i];
        }

        _cls1()
        {
        }
    }

}
