// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal.safeparcel;

import android.os.Parcel;

public final class  extends RuntimeException
{

    public (String s, Parcel parcel)
    {
        int i = parcel.dataPosition();
        int j = parcel.dataSize();
        super((new StringBuilder(String.valueOf(s).length() + 41)).append(s).append(" Parcel: pos=").append(i).append(" size=").append(j).toString());
    }
}
