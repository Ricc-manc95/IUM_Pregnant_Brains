// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.nano;

import java.io.IOException;

public final class InvalidProtocolBufferNanoException extends IOException
{

    public static final long serialVersionUID = 0xe9924688c2f20054L;

    public InvalidProtocolBufferNanoException(String s)
    {
        super(s);
    }

    public InvalidProtocolBufferNanoException(String s, Exception exception)
    {
        super(s, exception);
    }
}
