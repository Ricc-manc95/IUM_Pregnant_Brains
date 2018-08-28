// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.model;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

public final class ProtoParcels
{

    public static MessageLite getMessage(MessageLite messagelite, byte abyte0[])
    {
        try
        {
            messagelite = messagelite.toBuilder().mergeFrom(abyte0).build();
        }
        // Misplaced declaration of an exception variable
        catch (MessageLite messagelite)
        {
            throw new IllegalStateException(messagelite);
        }
        return messagelite;
    }
}
