// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.protobuf.lite;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import io.grpc.Drainable;
import io.grpc.KnownLength;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// Referenced classes of package io.grpc.protobuf.lite:
//            ProtoLiteUtils

final class ProtoInputStream extends InputStream
    implements Drainable, KnownLength
{

    public MessageLite message;
    public final Parser parser;
    private ByteArrayInputStream partial;

    ProtoInputStream(MessageLite messagelite, Parser parser1)
    {
        message = messagelite;
        parser = parser1;
    }

    public final int available()
    {
        if (message != null)
        {
            return message.getSerializedSize();
        }
        if (partial != null)
        {
            return partial.available();
        } else
        {
            return 0;
        }
    }

    public final int drainTo(OutputStream outputstream)
        throws IOException
    {
        if (message != null)
        {
            int i = message.getSerializedSize();
            message.writeTo(outputstream);
            message = null;
            return i;
        }
        if (partial != null)
        {
            int j = (int)ProtoLiteUtils.copy(partial, outputstream);
            partial = null;
            return j;
        } else
        {
            return 0;
        }
    }

    public final int read()
        throws IOException
    {
        if (message != null)
        {
            partial = new ByteArrayInputStream(message.toByteArray());
            message = null;
        }
        if (partial != null)
        {
            return partial.read();
        } else
        {
            return -1;
        }
    }

    public final int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if (message == null) goto _L2; else goto _L1
_L1:
        int k = message.getSerializedSize();
        if (k != 0) goto _L4; else goto _L3
_L3:
        message = null;
        partial = null;
_L6:
        return -1;
_L4:
        if (j >= k)
        {
            abyte0 = CodedOutputStream.newInstance(abyte0, i, k);
            message.writeTo(abyte0);
            abyte0.flush();
            if (abyte0.spaceLeft() != 0)
            {
                throw new IllegalStateException("Did not write as much data as expected.");
            } else
            {
                message = null;
                partial = null;
                return k;
            }
        }
        partial = new ByteArrayInputStream(message.toByteArray());
        message = null;
_L2:
        if (partial != null)
        {
            return partial.read(abyte0, i, j);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }
}
