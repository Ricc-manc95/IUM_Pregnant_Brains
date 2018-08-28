// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            Decompressor

public final class advertised
{

    public final boolean advertised;
    public final Decompressor decompressor;

    (Decompressor decompressor1, boolean flag)
    {
        if (decompressor1 == null)
        {
            throw new NullPointerException(String.valueOf("decompressor"));
        } else
        {
            decompressor = (Decompressor)decompressor1;
            advertised = flag;
            return;
        }
    }
}
