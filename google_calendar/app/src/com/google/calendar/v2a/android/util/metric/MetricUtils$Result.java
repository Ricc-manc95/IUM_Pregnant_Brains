// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;

import com.google.common.base.Absent;
import com.google.common.base.Optional;

// Referenced classes of package com.google.calendar.v2a.android.util.metric:
//            MetricUtils, AutoValue_MetricUtils_Result

public static abstract class Status
{

    public static final Status CANCEL;
    public static final Status FAILURE;
    public static final Status SUCCESS;

    public static Status cancel(Optional optional, Optional optional1)
    {
        class Status extends Enum
        {

            private static final Status $VALUES[];
            public static final Status CANCEL;
            public static final Status FAILURE;
            public static final Status SUCCESS;

            public static Status[] values()
            {
                return (Status[])$VALUES.clone();
            }

            static 
            {
                SUCCESS = new Status("SUCCESS", 0);
                FAILURE = new Status("FAILURE", 1);
                CANCEL = new Status("CANCEL", 2);
                $VALUES = (new Status[] {
                    SUCCESS, FAILURE, CANCEL
                });
            }

            private Status(String s, int i)
            {
                super(s, i);
            }
        }

        Status status1 = Status.CANCEL;
        java/lang/Enum.getClass();
        class .Lambda._cls6
            implements Function
        {

            private final Class arg$1;

            public final Object apply(Object obj)
            {
                return arg$1.cast((Enum)obj);
            }

            .Lambda._cls6(Class class1)
            {
                arg$1 = class1;
            }
        }

        optional = optional.transform(new .Lambda._cls6(java/lang/Enum));
        java/lang/Enum.getClass();
        class .Lambda._cls7
            implements Function
        {

            private final Class arg$1;

            public final Object apply(Object obj)
            {
                return arg$1.cast((Enum)obj);
            }

            .Lambda._cls7(Class class1)
            {
                arg$1 = class1;
            }
        }

        return new AutoValue_MetricUtils_Result(status1, optional, optional1.transform(new .Lambda._cls7(java/lang/Enum)));
    }

    public static .Lambda._cls7 failure(Optional optional, Optional optional1)
    {
        Status status1 = Status.FAILURE;
        java/lang/Enum.getClass();
        class .Lambda._cls4
            implements Function
        {

            private final Class arg$1;

            public final Object apply(Object obj)
            {
                return arg$1.cast((Enum)obj);
            }

            .Lambda._cls4(Class class1)
            {
                arg$1 = class1;
            }
        }

        optional = optional.transform(new .Lambda._cls4(java/lang/Enum));
        java/lang/Enum.getClass();
        class .Lambda._cls5
            implements Function
        {

            private final Class arg$1;

            public final Object apply(Object obj)
            {
                return arg$1.cast((Enum)obj);
            }

            .Lambda._cls5(Class class1)
            {
                arg$1 = class1;
            }
        }

        return new AutoValue_MetricUtils_Result(status1, optional, optional1.transform(new .Lambda._cls5(java/lang/Enum)));
    }

    public abstract Optional code();

    public abstract Optional source();

    public abstract Status status();

    static 
    {
        SUCCESS = new AutoValue_MetricUtils_Result(Status.SUCCESS, Absent.INSTANCE, Absent.INSTANCE);
        CANCEL = new AutoValue_MetricUtils_Result(Status.CANCEL, Absent.INSTANCE, Absent.INSTANCE);
        FAILURE = new AutoValue_MetricUtils_Result(Status.FAILURE, Absent.INSTANCE, Absent.INSTANCE);
    }

    public Status()
    {
    }
}
