// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            ByteString

final class val.input
    implements teSequence
{

    private final ByteString val$input;

    public final byte byteAt(int i)
    {
        return val$input.byteAt(i);
    }

    public final int size()
    {
        return val$input.size();
    }

    teSequence()
    {
        val$input = bytestring;
        super();
    }
}
