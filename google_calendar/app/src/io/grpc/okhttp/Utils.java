// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;

import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.internal.TransportFrameUtil;
import io.grpc.okhttp.internal.framed.Header;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import okio.ByteString;

class Utils
{

    private Utils()
    {
    }

    public static Metadata convertHeaders(List list)
    {
        return InternalMetadata.newMetadata(convertHeadersToArray(list));
    }

    private static byte[][] convertHeadersToArray(List list)
    {
        byte abyte0[][] = new byte[list.size() << 1][];
        list = list.iterator();
        int i = 0;
        while (list.hasNext()) 
        {
            Header header = (Header)list.next();
            int j = i + 1;
            abyte0[i] = header.name.toByteArray();
            i = j + 1;
            abyte0[j] = header.value.toByteArray();
        }
        return TransportFrameUtil.toRawSerializedHeaders(abyte0);
    }

    public static Metadata convertTrailers(List list)
    {
        return InternalMetadata.newMetadata(convertHeadersToArray(list));
    }

    static 
    {
        Logger.getLogger(io/grpc/okhttp/Utils.getName());
    }
}
