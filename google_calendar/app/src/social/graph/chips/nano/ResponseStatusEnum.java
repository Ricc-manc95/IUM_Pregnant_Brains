// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package social.graph.chips.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class ResponseStatusEnum extends ExtendableMessageNano
{

    public static int checkResponseStatusOrThrow(int i)
    {
        if (i >= 0 && i <= 7)
        {
            return i;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder(46)).append(i).append(" is not a valid enum ResponseStatus").toString());
        }
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        throw new NoSuchMethodError();
    }
}
