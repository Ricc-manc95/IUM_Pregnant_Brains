// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp;


public final class _cls9 extends Enum
{

    private static final PLAINTEXT $VALUES[];
    public static final PLAINTEXT PLAINTEXT;
    public static final PLAINTEXT TLS;

    public static _cls9[] values()
    {
        return (_cls9[])$VALUES.clone();
    }

    static 
    {
        TLS = new <init>("TLS", 0);
        PLAINTEXT = new <init>("PLAINTEXT", 1);
        $VALUES = (new .VALUES[] {
            TLS, PLAINTEXT
        });
    }

    private _cls9(String s, int i)
    {
        super(s, i);
    }
}
