// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


public final class  extends Enum
{

    private static final RECEIVED $VALUES[];
    public static final RECEIVED RECEIVED;
    public static final RECEIVED SENT;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        SENT = new <init>("SENT", 0);
        RECEIVED = new <init>("RECEIVED", 1);
        $VALUES = (new .VALUES[] {
            SENT, RECEIVED
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
