// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.nio.charset.Charset;

// Referenced classes of package io.grpc:
//            Metadata

public final class InternalMetadata
{

    public static final Charset US_ASCII = Charset.forName("US-ASCII");

    public static int headerCount(Metadata metadata)
    {
        return metadata.size;
    }

    public static Metadata.Key keyOf(String s, TrustedAsciiMarshaller trustedasciimarshaller)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (!s.isEmpty())
        {
            flag = flag1;
            if (s.charAt(0) == ':')
            {
                flag = true;
            }
        }
        return Metadata.Key.of(s, flag, trustedasciimarshaller);
    }

    public static transient Metadata newMetadata(byte abyte0[][])
    {
        return new Metadata(abyte0);
    }

    public static byte[][] serialize(Metadata metadata)
    {
        int j = metadata.size;
        int i;
        if (metadata.namesAndValues != null)
        {
            i = metadata.namesAndValues.length;
        } else
        {
            i = 0;
        }
        if (j << 1 == i)
        {
            return metadata.namesAndValues;
        } else
        {
            byte abyte0[][] = new byte[metadata.size << 1][];
            System.arraycopy(metadata.namesAndValues, 0, abyte0, 0, metadata.size << 1);
            return abyte0;
        }
    }

}
