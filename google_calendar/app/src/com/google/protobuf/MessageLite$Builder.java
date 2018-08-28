// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            MessageLiteOrBuilder, InvalidProtocolBufferException, MessageLite, ExtensionRegistryLite

public interface rException
    extends MessageLiteOrBuilder, Cloneable
{

    public abstract MessageLite build();

    public abstract MessageLite buildPartial();

    public abstract  mergeFrom(MessageLite messagelite);

    public abstract  mergeFrom(byte abyte0[])
        throws InvalidProtocolBufferException;

    public abstract e mergeFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException;
}
