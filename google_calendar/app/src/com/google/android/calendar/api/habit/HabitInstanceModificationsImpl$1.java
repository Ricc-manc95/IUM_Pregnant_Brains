// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitInstanceModificationsImpl

final class 
    implements android.os.nstanceModificationsImpl._cls1
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new HabitInstanceModificationsImpl(parcel);
    }

    public final Object[] newArray(int i)
    {
        return new HabitInstanceModificationsImpl[i];
    }

    ()
    {
    }
}