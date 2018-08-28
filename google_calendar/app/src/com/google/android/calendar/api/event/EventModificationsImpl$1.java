// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventModificationsImpl

final class 
    implements android.os.t.EventModificationsImpl._cls1
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new EventModificationsImpl(parcel);
    }

    public final Object[] newArray(int i)
    {
        return new EventModificationsImpl[i];
    }

    ()
    {
    }
}
