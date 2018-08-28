// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


public final class  extends Enum
{

    private static final RECV $VALUES[];
    public static final RECV RECV;
    public static final RECV SENT;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        SENT = new <init>("SENT", 0);
        RECV = new <init>("RECV", 1);
        $VALUES = (new .VALUES[] {
            SENT, RECV
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
