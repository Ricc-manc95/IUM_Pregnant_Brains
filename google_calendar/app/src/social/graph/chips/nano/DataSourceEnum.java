// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package social.graph.chips.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class DataSourceEnum extends ExtendableMessageNano
{

    public static int checkDataSourceOrThrow(int i)
    {
        if (i >= 0 && i <= 2)
        {
            return i;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder(42)).append(i).append(" is not a valid enum DataSource").toString());
        }
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        throw new NoSuchMethodError();
    }
}
