// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.attendee;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event.attendee:
//            Response

public static final class  extends Enum
{

    private static final DECLINED $VALUES[];
    public static final DECLINED ACCEPTED;
    public static final DECLINED DECLINED;
    public static final DECLINED NEEDS_ACTION;
    public static final DECLINED TENTATIVE;

    public static  createFromInteger(int i)
    {
         a[] = values();
        int k = a.length;
        for (int j = 0; j < k; j++)
        {
              = a[j];
            if (.ordinal() == i)
            {
                return ;
            }
        }

        throw new IllegalStateException("Invalid attendeeDescriptor type value");
    }

    public static ordinal createFromParcel(Parcel parcel)
    {
        return createFromInteger(parcel.readInt());
    }

    public static createFromInteger valueOf(String s)
    {
        return (createFromInteger)Enum.valueOf(com/google/android/calendar/api/event/attendee/Response$ResponseStatus, s);
    }

    public static createFromInteger[] values()
    {
        return (createFromInteger[])$VALUES.clone();
    }

    public static void writeToParcel(Parcel parcel, s_3B_.clone clone)
    {
        parcel.writeInt(clone.ordinal());
    }

    static 
    {
        NEEDS_ACTION = new <init>("NEEDS_ACTION", 0);
        ACCEPTED = new <init>("ACCEPTED", 1);
        TENTATIVE = new <init>("TENTATIVE", 2);
        DECLINED = new <init>("DECLINED", 3);
        $VALUES = (new .VALUES[] {
            NEEDS_ACTION, ACCEPTED, TENTATIVE, DECLINED
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
