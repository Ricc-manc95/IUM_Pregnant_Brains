// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import android.os.health.HealthStats;
import android.os.health.TimerStat;
import logs.proto.wireless.performance.mobile.nano.HashedString;
import logs.proto.wireless.performance.mobile.nano.ProcessHealthProto;
import logs.proto.wireless.performance.mobile.nano.ServiceHealthProto;
import logs.proto.wireless.performance.mobile.nano.Timer;

public final class HealthStatsProtos
{

    static Long getMeasurement(HealthStats healthstats, int i)
    {
        if (healthstats != null && healthstats.hasMeasurement(i))
        {
            healthstats = Long.valueOf(healthstats.getMeasurement(i));
        } else
        {
            healthstats = null;
        }
        if (healthstats == null || healthstats.longValue() <= 0L)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            return null;
        } else
        {
            return healthstats;
        }
    }

    static boolean isZero(ProcessHealthProto processhealthproto)
    {
        Long long1 = processhealthproto.userTimeMs;
        boolean flag;
        if (long1 == null || long1.longValue() <= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Long long2 = processhealthproto.systemTimeMs;
            if (long2 == null || long2.longValue() <= 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Long long3 = processhealthproto.anrCount;
                if (long3 == null || long3.longValue() <= 0L)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    Long long4 = processhealthproto.crashesCount;
                    if (long4 == null || long4.longValue() <= 0L)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        Long long5 = processhealthproto.startsCount;
                        if (long5 == null || long5.longValue() <= 0L)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            processhealthproto = processhealthproto.foregroundMs;
                            if (processhealthproto == null || processhealthproto.longValue() <= 0L)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    static boolean isZero(ServiceHealthProto servicehealthproto)
    {
        Integer integer = servicehealthproto.startServiceCount;
        boolean flag;
        if (integer == null || integer.longValue() <= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            servicehealthproto = servicehealthproto.launchCount;
            if (servicehealthproto == null || servicehealthproto.longValue() <= 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isZero(Timer timer1)
    {
        return (timer1.count == null || timer1.count.intValue() == 0) && (timer1.durationMs == null || timer1.durationMs.longValue() == 0L);
    }

    static Integer subtract(Integer integer, Integer integer1)
    {
        if (integer == null || integer1 == null)
        {
            return integer;
        }
        int i = integer.intValue() - integer1.intValue();
        if (i == 0)
        {
            return null;
        } else
        {
            return Integer.valueOf(i);
        }
    }

    public static Long subtract(Long long1, Long long2)
    {
        if (long1 == null || long2 == null)
        {
            return long1;
        }
        long l = long1.longValue() - long2.longValue();
        if (l == 0L)
        {
            return null;
        } else
        {
            return Long.valueOf(l);
        }
    }

    public static Timer subtract(Timer timer1, Timer timer2)
    {
        if (timer1 == null || timer2 == null)
        {
            return timer1;
        }
        Timer timer3 = new Timer();
        timer3.name = timer1.name;
        timer3.count = Integer.valueOf(timer1.count.intValue() - timer2.count.intValue());
        timer3.durationMs = Long.valueOf(timer1.durationMs.longValue() - timer2.durationMs.longValue());
        if (isZero(timer3))
        {
            return null;
        } else
        {
            return timer3;
        }
    }

    static Timer timer(String s, TimerStat timerstat)
    {
        Timer timer1 = new Timer();
        timer1.count = Integer.valueOf(timerstat.getCount());
        if (timer1.count.intValue() < 0)
        {
            timer1.count = Integer.valueOf(0);
        }
        timer1.durationMs = Long.valueOf(timerstat.getTime());
        if (s == null)
        {
            s = null;
        } else
        {
            timerstat = new HashedString();
            timerstat.unhashedName = s;
            s = timerstat;
        }
        timer1.name = s;
        if (isZero(timer1))
        {
            return null;
        } else
        {
            return timer1;
        }
    }
}
