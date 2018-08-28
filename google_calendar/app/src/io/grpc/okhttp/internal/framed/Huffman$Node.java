// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal.framed;


final class terminalBits
{

    public final terminalBits children[];
    public final int symbol;
    public final int terminalBits;

    ()
    {
        children = new children[256];
        symbol = 0;
        terminalBits = 0;
    }

    terminalBits(int i, int j)
    {
        children = null;
        symbol = i;
        j &= 7;
        i = j;
        if (j == 0)
        {
            i = 8;
        }
        terminalBits = i;
    }
}
