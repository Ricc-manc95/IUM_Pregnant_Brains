// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.contrib.android;

import android.os.Parcel;

final class 
    implements android.os.arsers.InternalDontUse._cls1
{

    public final Object createFromParcel(Parcel parcel)
    {
        byte abyte0[] = new byte[parcel.readInt()];
        parcel.readByteArray(abyte0);
        return new nit>(abyte0, null);
    }

    public final Object[] newArray(int i)
    {
        return new nit>[i];
    }

    ()
    {
    }
}
