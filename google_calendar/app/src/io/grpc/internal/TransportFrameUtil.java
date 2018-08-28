// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class TransportFrameUtil
{

    private static final byte binaryHeaderSuffixBytes[];
    private static final Logger logger = Logger.getLogger(io/grpc/internal/TransportFrameUtil.getName());

    private TransportFrameUtil()
    {
    }

    private static boolean endsWith(byte abyte0[], byte abyte1[])
    {
        int j = abyte0.length - abyte1.length;
        if (j < 0)
        {
            return false;
        }
        for (int i = j; i < abyte0.length; i++)
        {
            if (abyte0[i] != abyte1[i - j])
            {
                return false;
            }
        }

        return true;
    }

    public static byte[][] toHttp2Headers(Metadata metadata)
    {
        int i;
        int j;
        metadata = InternalMetadata.serialize(metadata);
        if (metadata == null)
        {
            return new byte[0][];
        }
        j = 0;
        i = 0;
_L2:
        byte abyte0[];
        byte abyte1[];
        if (j >= metadata.length)
        {
            break MISSING_BLOCK_LABEL_259;
        }
        abyte1 = metadata[j];
        abyte0 = metadata[j + 1];
        if (!endsWith(abyte1, binaryHeaderSuffixBytes))
        {
            break; /* Loop/switch isn't completed */
        }
        metadata[i] = abyte1;
        metadata[i + 1] = BaseEncoding.BASE64.encode(abyte0, 0, abyte0.length).getBytes(Charsets.US_ASCII);
        i += 2;
_L5:
        j += 2;
        if (true) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        l = abyte0.length;
        k = 0;
_L6:
        byte byte0;
        if (k >= l)
        {
            break MISSING_BLOCK_LABEL_163;
        }
        byte0 = abyte0[k];
        if (byte0 >= 32 && byte0 <= 126) goto _L4; else goto _L3
_L3:
        k = 0;
_L7:
        if (k != 0)
        {
            metadata[i] = abyte1;
            metadata[i + 1] = abyte0;
            i += 2;
        } else
        {
            String s1 = new String(abyte1, Charsets.US_ASCII);
            Logger logger1 = logger;
            Level level = Level.WARNING;
            String s = Arrays.toString(abyte0);
            logger1.logp(level, "io.grpc.internal.TransportFrameUtil", "toHttp2Headers", (new StringBuilder(String.valueOf(s1).length() + 55 + String.valueOf(s).length())).append("Metadata key=").append(s1).append(", value=").append(s).append(" contains invalid ASCII characters").toString());
        }
          goto _L5
_L4:
        k++;
          goto _L6
        k = 1;
          goto _L7
        if (i == metadata.length)
        {
            return metadata;
        }
        return (byte[][])Arrays.copyOfRange(metadata, 0, i);
          goto _L5
    }

    public static byte[][] toRawSerializedHeaders(byte abyte0[][])
    {
        for (int i = 0; i < abyte0.length; i += 2)
        {
            byte abyte1[] = abyte0[i];
            byte abyte2[] = abyte0[i + 1];
            abyte0[i] = abyte1;
            if (endsWith(abyte1, binaryHeaderSuffixBytes))
            {
                abyte0[i + 1] = BaseEncoding.BASE64.decode(new String(abyte2, Charsets.US_ASCII));
            }
        }

        return abyte0;
    }

    static 
    {
        binaryHeaderSuffixBytes = "-bin".getBytes(Charsets.US_ASCII);
    }
}
