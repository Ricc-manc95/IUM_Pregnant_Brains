// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;

import java.util.Locale;

// Referenced classes of package org.joda.time:
//            DurationField, DateTimeFieldType

public abstract class DateTimeField
{

    public DateTimeField()
    {
    }

    public abstract long add(long l, int i);

    public abstract long add(long l, long l1);

    public abstract int get(long l);

    public abstract String getAsShortText(int i, Locale locale);

    public abstract String getAsShortText(long l, Locale locale);

    public abstract String getAsText(int i, Locale locale);

    public abstract String getAsText(long l, Locale locale);

    public abstract DurationField getDurationField();

    public abstract DurationField getLeapDurationField();

    public abstract int getMaximumTextLength(Locale locale);

    public abstract int getMaximumValue();

    public abstract int getMaximumValue(long l);

    public abstract int getMinimumValue();

    public abstract String getName();

    public abstract DurationField getRangeDurationField();

    public abstract DateTimeFieldType getType();

    public abstract boolean isLeap(long l);

    public abstract boolean isSupported();

    public abstract long remainder(long l);

    public abstract long roundFloor(long l);

    public abstract long set(long l, int i);

    public abstract long set(long l, String s, Locale locale);
}
