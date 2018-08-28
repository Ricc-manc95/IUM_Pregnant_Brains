// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.android;

import android.os.Parcel;

// Referenced classes of package com.google.android.apps.calendar.util.android:
//            Blob

final class 
    implements android.os.ble.Creator
{

    public final Object createFromParcel(Parcel parcel)
    {
        byte abyte0[] = new byte[parcel.readInt()];
        parcel.readByteArray(abyte0);
        return new Blob(abyte0);
    }

    public final Object[] newArray(int i)
    {
        return new Blob[i];
    }

    ()
    {
    }
}
