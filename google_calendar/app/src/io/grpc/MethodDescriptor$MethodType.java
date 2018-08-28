// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


public final class  extends Enum
{

    private static final UNKNOWN $VALUES[];
    private static final UNKNOWN BIDI_STREAMING;
    private static final UNKNOWN CLIENT_STREAMING;
    public static final UNKNOWN SERVER_STREAMING;
    public static final UNKNOWN UNARY;
    private static final UNKNOWN UNKNOWN;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        UNARY = new <init>("UNARY", 0);
        CLIENT_STREAMING = new <init>("CLIENT_STREAMING", 1);
        SERVER_STREAMING = new <init>("SERVER_STREAMING", 2);
        BIDI_STREAMING = new <init>("BIDI_STREAMING", 3);
        UNKNOWN = new <init>("UNKNOWN", 4);
        $VALUES = (new .VALUES[] {
            UNARY, CLIENT_STREAMING, SERVER_STREAMING, BIDI_STREAMING, UNKNOWN
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
