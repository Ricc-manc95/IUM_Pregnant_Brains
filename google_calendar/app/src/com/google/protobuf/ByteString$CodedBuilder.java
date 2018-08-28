// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            CodedOutputStream, ByteString

final class buffer
{

    public final byte buffer[];
    public final CodedOutputStream output;

    public final ByteString build()
    {
        if (output.spaceLeft() != 0)
        {
            throw new IllegalStateException("Did not write as much data as expected.");
        } else
        {
            return new ring(buffer);
        }
    }

    ring(int i)
    {
        buffer = new byte[i];
        output = CodedOutputStream.newInstance(buffer);
    }
}
