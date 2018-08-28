// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.time;


// Referenced classes of package com.google.android.calendar.newapi.segment.time:
//            EventTimeEditSegmentController

static final class a extends Enum
{

    private static final END $VALUES[];
    public static final END END;
    public static final END START;

    public static a[] values()
    {
        return (a[])$VALUES.clone();
    }

    static 
    {
        START = new <init>("START", 0);
        END = new <init>("END", 1);
        $VALUES = (new .VALUES[] {
            START, END
        });
    }

    private a(String s, int i)
    {
        super(s, i);
    }
}
