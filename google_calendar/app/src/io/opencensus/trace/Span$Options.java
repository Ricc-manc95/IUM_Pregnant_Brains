// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


// Referenced classes of package io.opencensus.trace:
//            Span

public static final class  extends Enum
{

    private static final RECORD_EVENTS $VALUES[];
    public static final RECORD_EVENTS RECORD_EVENTS;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        RECORD_EVENTS = new <init>("RECORD_EVENTS", 0);
        $VALUES = (new .VALUES[] {
            RECORD_EVENTS
        });
    }

    private (String s, int i)
    {
        super(s, 0);
    }
}
