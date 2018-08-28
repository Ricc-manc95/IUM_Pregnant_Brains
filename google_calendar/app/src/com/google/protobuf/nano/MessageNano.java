// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.nano;

import java.io.IOException;
import java.nio.ByteBuffer;

// Referenced classes of package com.google.protobuf.nano:
//            InvalidProtocolBufferNanoException, CodedInputByteBufferNano, CodedOutputByteBufferNano, MessageNanoPrinter

public abstract class MessageNano
{

    public volatile int cachedSize;

    public MessageNano()
    {
        cachedSize = -1;
    }

    public static final MessageNano mergeFrom(MessageNano messagenano, byte abyte0[], int i, int j)
        throws InvalidProtocolBufferNanoException
    {
        try
        {
            abyte0 = new CodedInputByteBufferNano(abyte0, i, j);
            messagenano.mergeFrom(((CodedInputByteBufferNano) (abyte0)));
            if (((CodedInputByteBufferNano) (abyte0)).lastTag != 0)
            {
                throw new InvalidProtocolBufferNanoException("Protocol message end-group tag did not match expected tag.");
            }
        }
        // Misplaced declaration of an exception variable
        catch (MessageNano messagenano)
        {
            throw messagenano;
        }
        // Misplaced declaration of an exception variable
        catch (MessageNano messagenano)
        {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", messagenano);
        }
        return messagenano;
    }

    public static final void toByteArray(MessageNano messagenano, byte abyte0[], int i, int j)
    {
        try
        {
            abyte0 = new CodedOutputByteBufferNano(abyte0, 0, j);
            messagenano.writeTo(abyte0);
            if (((CodedOutputByteBufferNano) (abyte0)).buffer.remaining() != 0)
            {
                throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[] {
                    Integer.valueOf(((CodedOutputByteBufferNano) (abyte0)).buffer.remaining())
                }));
            }
        }
        // Misplaced declaration of an exception variable
        catch (MessageNano messagenano)
        {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", messagenano);
        }
    }

    public MessageNano clone()
        throws CloneNotSupportedException
    {
        return (MessageNano)super.clone();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int computeSerializedSize()
    {
        return 0;
    }

    public abstract MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException;

    public String toString()
    {
        return MessageNanoPrinter.print(this);
    }

    public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
    }
}
