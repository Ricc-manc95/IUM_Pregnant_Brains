// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.InternalMetadata;

final class 
    implements io.grpc.aller
{

    public final Object parseAsciiString(byte abyte0[])
    {
        if (abyte0.length >= 3)
        {
            return Integer.valueOf((abyte0[0] - 48) * 100 + (abyte0[1] - 48) * 10 + (abyte0[2] - 48));
        }
        abyte0 = String.valueOf(new String(abyte0, InternalMetadata.US_ASCII));
        if (abyte0.length() != 0)
        {
            abyte0 = "Malformed status code ".concat(abyte0);
        } else
        {
            abyte0 = new String("Malformed status code ");
        }
        throw new NumberFormatException(abyte0);
    }

    public final byte[] toAsciiString(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    ()
    {
    }
}
