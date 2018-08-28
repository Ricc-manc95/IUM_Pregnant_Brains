// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineSuggestion

final class 
    implements android.os.ly.TimelineSuggestion._cls2
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new TimelineSuggestion(parcel);
    }

    public final Object[] newArray(int i)
    {
        return new TimelineSuggestion[i];
    }

    ()
    {
    }
}
