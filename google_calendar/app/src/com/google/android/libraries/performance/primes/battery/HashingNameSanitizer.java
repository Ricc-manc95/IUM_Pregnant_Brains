// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.battery;

import com.google.android.libraries.performance.primes.Hashing;
import com.google.android.libraries.performance.primes.PrimesLog;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logs.proto.wireless.performance.mobile.nano.HashedString;
import logs.proto.wireless.performance.mobile.nano.Timer;

public final class HashingNameSanitizer
{

    private static final Pattern SYSTEM_TASK_PATTERN = Pattern.compile("^(\\*[a-z]+\\*).*");
    private final ConcurrentHashMap hashHashMap = new ConcurrentHashMap();

    HashingNameSanitizer()
    {
    }

    private static String sanitizeSyncName(String s)
    {
        String as[] = s.split("/");
        if (as == null || as.length != 3)
        {
            PrimesLog.log(3, "HashingNameSanitizer", "malformed sync name: %s", new Object[] {
                s
            });
            return "MALFORMED";
        } else
        {
            return as[0];
        }
    }

    final void hashRawTimerNames(NameType nametype, Timer atimer[])
    {
        if (atimer != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        int j;
        j = atimer.length;
        i = 0;
_L12:
        if (i >= j) goto _L1; else goto _L3
_L3:
        Timer timer = atimer[i];
        if (timer == null || timer.name == null || timer.name.unhashedName == null) goto _L5; else goto _L4
_L4:
        String s;
        HashedString hashedstring;
        long l;
        hashedstring = timer.name;
        s = timer.name.unhashedName;
        l = Hashing.hash(s).longValue();
        if (hashHashMap.containsKey(Long.valueOf(l))) goto _L7; else goto _L6
_L6:
        nametype.ordinal();
        JVM INSTR tableswitch 0 2: default 120
    //                   0 226
    //                   1 351
    //                   2 360;
           goto _L8 _L9 _L10 _L11
_L11:
        break MISSING_BLOCK_LABEL_360;
_L8:
        Object obj = s;
_L13:
        Long long1 = Hashing.hash(((String) (obj)));
        PrimesLog.log(3, "HashingNameSanitizer", "Sanitized Hash: [%s] %s -> %s", new Object[] {
            nametype, obj, long1
        });
        PrimesLog.log(2, "HashingNameSanitizer", "Raw Hash: [%s] %s -> %s", new Object[] {
            nametype, s, Long.valueOf(l)
        });
        hashHashMap.putIfAbsent(Long.valueOf(l), long1);
_L7:
        hashedstring.hash = Long.valueOf(l);
        timer.name.unhashedName = null;
_L5:
        i++;
          goto _L12
_L9:
        obj = SYSTEM_TASK_PATTERN.matcher(s);
        if (((Matcher) (obj)).matches())
        {
            if (s.startsWith("*sync*/"))
            {
                obj = String.valueOf("*sync*/");
                String s1 = String.valueOf(sanitizeSyncName(s.substring(7)));
                if (s1.length() != 0)
                {
                    obj = ((String) (obj)).concat(s1);
                } else
                {
                    obj = new String(((String) (obj)));
                }
            } else
            {
                obj = ((Matcher) (obj)).group(1);
                PrimesLog.log(3, "HashingNameSanitizer", "non-sync system task wakelock: %s", new Object[] {
                    obj
                });
            }
        } else
        {
            PrimesLog.log(3, "HashingNameSanitizer", "wakelock: %s", new Object[] {
                s
            });
            obj = s;
        }
          goto _L13
_L10:
        obj = sanitizeSyncName(s);
          goto _L13
        obj = "--";
          goto _L13
    }

    public final void sanitizeHashedTimerNames$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TO6ASJ6DTP6QOBECDIIUS3ID5MMASPFC9GN8T35E9SIUI31EDK6IRJ79PGMQPAJC5N6IT39F9IN492EC5MMAL3PE1IJMMQCDHNMESPFE1P6UT3F5TRMISJ5DHIN6SPFE1IN4PJFE9MM2RJ3CKNMQRR2D5M6ABREC5N6UBQKD5MMASHR55B0____0(Timer atimer[])
    {
        if (atimer != null)
        {
            int j = atimer.length;
            int i = 0;
            while (i < j) 
            {
                Timer timer = atimer[i];
                if (timer != null && timer.name != null && timer.name.hash != null)
                {
                    timer.name.hash = (Long)hashHashMap.get(timer.name.hash);
                }
                i++;
            }
        }
    }


    private class NameType extends Enum
    {

        private static final NameType $VALUES[];
        public static final NameType JOB;
        public static final NameType PROCESS;
        public static final NameType SENSOR;
        public static final NameType SYNC;
        public static final NameType WAKELOCK;

        public static NameType[] values()
        {
            return (NameType[])$VALUES.clone();
        }

        static 
        {
            WAKELOCK = new NameType("WAKELOCK", 0);
            SYNC = new NameType("SYNC", 1);
            JOB = new NameType("JOB", 2);
            PROCESS = new NameType("PROCESS", 3);
            SENSOR = new NameType("SENSOR", 4);
            $VALUES = (new NameType[] {
                WAKELOCK, SYNC, JOB, PROCESS, SENSOR
            });
        }

        private NameType(String s, int i)
        {
            super(s, i);
        }
    }

}
