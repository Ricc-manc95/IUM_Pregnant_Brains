// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import com.google.protobuf.MessageLiteOrBuilder;

// Referenced classes of package com.google.android.calendar.task:
//            CalendarAssistance

public static final class INSTANCE extends com.google.protobuf.r
    implements MessageLiteOrBuilder
{

    public final INSTANCE setTaskAnnotationHint(String s)
    {
        copyOnWrite();
        CalendarAssistance calendarassistance = (CalendarAssistance)instance;
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            calendarassistance.bitField0_ = calendarassistance.bitField0_ | 1;
            calendarassistance.taskAnnotationHint_ = s;
            return this;
        }
    }

    A()
    {
        super(CalendarAssistance.DEFAULT_INSTANCE);
    }
}
