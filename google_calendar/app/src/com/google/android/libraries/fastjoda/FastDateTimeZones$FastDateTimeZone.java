// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.fastjoda;

import java.util.TimeZone;
import org.joda.time.DateTimeZone;

public final class timeZone extends DateTimeZone
{

    private TransitionInterval cachedInterval;
    private final TimeZone timeZone;

    private final long findDstTransitionBound(long l, boolean flag, boolean flag1)
    {
        int i;
        int j;
        if (flag1)
        {
            i = 1;
        } else
        {
            i = -1;
        }
        j = 1;
        do
        {
label0:
            {
                long l1 = l;
                if (j < 5)
                {
                    l1 = (long)(j * i) * 90L * 0x15180L * 1000L + l;
                    if (timeZone.getOffset(l1) != timeZone.getRawOffset())
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (flag1 == flag)
                    {
                        break label0;
                    }
                }
                return l1;
            }
            j++;
        } while (true);
    }

    private final long findNextTransition(long l, long l1, boolean flag)
    {
        if (l1 <= l)
        {
            throw new AssertionError("upperBound must be greater than instant");
        }
        boolean flag1;
        if (timeZone.getOffset(l1) != timeZone.getRawOffset())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag == flag1)
        {
            throw new AssertionError("instant and upperBound must have different time zone offsets");
        }
        l /= 1000L;
        l1 /= 1000L;
        long l3;
        do
        {
            long l2 = (l1 + l) / 2L;
            if (timeZone.getOffset(1000L * l2) != timeZone.getRawOffset())
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 == flag)
            {
                l3 = l2;
                l2 = l1;
            } else
            {
                l3 = l;
            }
            l1 = l2;
            l = l3;
        } while (l2 - l3 > 1L);
        if (timeZone.getOffset(1000L * l3) != timeZone.getRawOffset())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 == flag)
        {
            return l2 * 1000L;
        } else
        {
            return 1000L * l3;
        }
    }

    private final TransitionInterval findTransitionInterval(long l)
    {
        long l1;
        boolean flag;
        if (timeZone.getOffset(l) != timeZone.getRawOffset())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        l1 = findDstTransitionBound(l, flag, true);
        if (l1 == l)
        {
            return null;
        }
        long l2 = findDstTransitionBound(l, flag, false);
        if (l2 == l)
        {
            return null;
        }
        boolean flag1;
        if (!flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        return new TransitionInterval(findNextTransition(l2, l, flag1), findNextTransition(l, l1, flag));
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag;
        if (this == obj)
        {
            flag = true;
        } else
        {
            flag = flag1;
            if (obj != null)
            {
                flag = flag1;
                if (getClass() == obj.getClass())
                {
                    obj = (findNextTransition)obj;
                    return timeZone.equals(((timeZone) (obj)).timeZone);
                }
            }
        }
        return flag;
    }

    public final String getNameKey(long l)
    {
        return timeZone.getID();
    }

    public final int getOffset(long l)
    {
        return timeZone.getOffset(l);
    }

    public final int getStandardOffset(long l)
    {
        return timeZone.getRawOffset();
    }

    public final int hashCode()
    {
        return timeZone.hashCode();
    }

    public final boolean isFixed()
    {
        return false;
    }

    public final long nextTransition(long l)
    {
        TransitionInterval transitioninterval = cachedInterval;
        boolean flag;
        if (l >= transitioninterval.from && l < transitioninterval.to)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            l = transitioninterval.to;
        } else
        {
            TransitionInterval transitioninterval1 = findTransitionInterval(l);
            if (transitioninterval1 != null)
            {
                cachedInterval = transitioninterval1;
                return transitioninterval1.to;
            }
        }
        return l;
    }

    public final long previousTransition(long l)
    {
        TransitionInterval transitioninterval = cachedInterval;
        boolean flag;
        if (l >= transitioninterval.from && l < transitioninterval.to)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            l = transitioninterval.from - 1L;
        } else
        {
            TransitionInterval transitioninterval1 = findTransitionInterval(l);
            if (transitioninterval1 != null)
            {
                cachedInterval = transitioninterval1;
                return transitioninterval1.from - 1L;
            }
        }
        return l;
    }

    TransitionInterval.to(TimeZone timezone)
    {
        super(timezone.getID());
        class TransitionInterval
        {

            public final long from;
            public final long to;

            TransitionInterval(long l, long l1)
            {
                from = l;
                to = l1;
            }
        }

        cachedInterval = new TransitionInterval(0x8000000000000000L, 0x8000000000000000L);
        timeZone = timezone;
    }
}
