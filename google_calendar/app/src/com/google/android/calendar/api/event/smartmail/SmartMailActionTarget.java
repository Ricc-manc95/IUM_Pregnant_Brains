// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

public class SmartMailActionTarget
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final int goToType;
    private final String text;
    public final String uri;

    public SmartMailActionTarget(int i, String s, String s1)
    {
        goToType = checkGoToType(i);
        if (s == null)
        {
            throw new NullPointerException();
        }
        uri = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        } else
        {
            text = (String)s1;
            return;
        }
    }

    SmartMailActionTarget(Parcel parcel)
    {
        this(checkGoToType(parcel.readInt()), parcel.readString(), parcel.readString());
    }

    private static int checkGoToType(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder(41)).append("Type ").append(i).append(" is not a valid goToType.").toString());

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
            return i;
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
                    obj = (SmartMailActionTarget)obj;
                    flag = flag1;
                    if (goToType == ((SmartMailActionTarget) (obj)).goToType)
                    {
                        flag = flag1;
                        if (uri.equals(((SmartMailActionTarget) (obj)).uri))
                        {
                            return text.equals(((SmartMailActionTarget) (obj)).text);
                        }
                    }
                }
            }
        }
        return flag;
    }

    public int hashCode()
    {
        return (goToType * 31 + uri.hashCode()) * 31 + text.hashCode();
    }

    public String toString()
    {
        int i = goToType;
        i;
        JVM INSTR tableswitch 1 11: default 64
    //                   1 119
    //                   2 125
    //                   3 131
    //                   4 137
    //                   5 143
    //                   6 149
    //                   7 155
    //                   8 161
    //                   9 167
    //                   10 173
    //                   11 179;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
        String s = (new StringBuilder(21)).append("UNKNOWN (").append(i).append(")").toString();
_L14:
        return String.format("GoTo{goToType=%s, uri='%s', text='%s'}", new Object[] {
            s, uri, text
        });
_L2:
        s = "UNKNOWN";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "CHECK_IN";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "EDIT";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "TRACK";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "VIEW";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "SHARE";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "LISTEN";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "STRUCTURED";
        continue; /* Loop/switch isn't completed */
_L10:
        s = "VIDEO_MEETING";
        continue; /* Loop/switch isn't completed */
_L11:
        s = "PROVIDER";
        continue; /* Loop/switch isn't completed */
_L12:
        s = "PAY";
        if (true) goto _L14; else goto _L13
_L13:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(goToType);
        parcel.writeString(uri);
        parcel.writeString(text);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SmartMailActionTarget(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SmartMailActionTarget[i];
        }

        _cls1()
        {
        }
    }

}
