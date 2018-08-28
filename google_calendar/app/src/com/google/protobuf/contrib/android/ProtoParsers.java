// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.contrib.android;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

public final class ProtoParsers
{

    public static MessageLite mergeFrom(byte abyte0[], MessageLite messagelite)
    {
        try
        {
            abyte0 = messagelite.toBuilder().mergeFrom(abyte0).build();
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new RuntimeException(abyte0);
        }
        return abyte0;
    }
}
