// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.NoSuchElementException;

// Referenced classes of package com.google.protobuf:
//            ByteString

final class ze
    implements teIterator
{

    private final int limit;
    private int position;
    private final ByteString this$0;

    public final boolean hasNext()
    {
        return position < limit;
    }

    public final Object next()
    {
        return Byte.valueOf(nextByte());
    }

    public final byte nextByte()
    {
        byte byte0;
        try
        {
            ByteString bytestring = ByteString.this;
            int i = position;
            position = i + 1;
            byte0 = bytestring.byteAt(i);
        }
        catch (IndexOutOfBoundsException indexoutofboundsexception)
        {
            throw new NoSuchElementException(indexoutofboundsexception.getMessage());
        }
        return byte0;
    }

    public final void remove()
    {
        throw new UnsupportedOperationException();
    }

    on()
    {
        this$0 = ByteString.this;
        super();
        position = 0;
        limit = size();
    }
}
