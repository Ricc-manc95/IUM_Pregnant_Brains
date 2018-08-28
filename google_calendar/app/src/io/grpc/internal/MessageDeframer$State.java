// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


final class  extends Enum
{

    private static final BODY $VALUES[];
    public static final BODY BODY;
    public static final BODY HEADER;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        HEADER = new <init>("HEADER", 0);
        BODY = new <init>("BODY", 1);
        $VALUES = (new .VALUES[] {
            HEADER, BODY
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
