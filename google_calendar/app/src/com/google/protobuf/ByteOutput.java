// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;

public abstract class ByteOutput
{

    public ByteOutput()
    {
    }

    public abstract void writeLazy(byte abyte0[], int i, int j)
        throws IOException;
}