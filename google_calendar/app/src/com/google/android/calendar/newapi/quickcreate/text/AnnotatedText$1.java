// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.os.Parcel;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.text:
//            AnnotatedText

final class I
    implements android.os.or
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new AnnotatedText(parcel);
    }

    public final Object[] newArray(int i)
    {
        return new AnnotatedText[i];
    }

    I()
    {
    }
}
