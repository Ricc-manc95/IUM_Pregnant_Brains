// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package com.google.protobuf:
//            MessageLite, CodedOutputStream, ByteString

public abstract class AbstractMessageLite
    implements MessageLite
{

    public int memoizedHashCode;

    public AbstractMessageLite()
    {
        memoizedHashCode = 0;
    }

    int getMemoizedSerializedSize()
    {
        throw new UnsupportedOperationException();
    }

    void setMemoizedSerializedSize(int i)
    {
        throw new UnsupportedOperationException();
    }

    public final byte[] toByteArray()
    {
        Object obj;
        try
        {
            obj = new byte[getSerializedSize()];
            CodedOutputStream codedoutputstream = CodedOutputStream.newInstance(((byte []) (obj)));
            writeTo(codedoutputstream);
            if (codedoutputstream.spaceLeft() != 0)
            {
                throw new IllegalStateException("Did not write as much data as expected.");
            }
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            String s = getClass().getName();
            throw new RuntimeException((new StringBuilder(String.valueOf(s).length() + 62 + String.valueOf("byte array").length())).append("Serializing ").append(s).append(" to a ").append("byte array").append(" threw an IOException (should never happen).").toString(), ((Throwable) (obj)));
        }
        return ((byte []) (obj));
    }

    public final ByteString toByteString()
    {
        Object obj;
        try
        {
            obj = ByteString.newCodedBuilder(getSerializedSize());
            writeTo(((ByteString.CodedBuilder) (obj)).output);
            if (((ByteString.CodedBuilder) (obj)).output.spaceLeft() != 0)
            {
                throw new IllegalStateException("Did not write as much data as expected.");
            }
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            String s = getClass().getName();
            throw new RuntimeException((new StringBuilder(String.valueOf(s).length() + 62 + String.valueOf("ByteString").length())).append("Serializing ").append(s).append(" to a ").append("ByteString").append(" threw an IOException (should never happen).").toString(), ((Throwable) (obj)));
        }
        obj = new ByteString.LiteralByteString(((ByteString.CodedBuilder) (obj)).buffer);
        return ((ByteString) (obj));
    }

    public final void writeTo(OutputStream outputstream)
        throws IOException
    {
        outputstream = CodedOutputStream.newInstance(outputstream, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
        writeTo(((CodedOutputStream) (outputstream)));
        outputstream.flush();
    }
}
