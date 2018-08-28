// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.proto;

import android.os.Parcel;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

public abstract class ProtoAdapter
{

    public ProtoAdapter()
    {
    }

    public final MessageLite fromParcel(Parcel parcel)
    {
        try
        {
            parcel = parseFrom(parcel.createByteArray());
        }
        // Misplaced declaration of an exception variable
        catch (Parcel parcel)
        {
            throw new RuntimeException(parcel);
        }
        return parcel;
    }

    public abstract MessageLite parseFrom(byte abyte0[])
        throws InvalidProtocolBufferException;
}
