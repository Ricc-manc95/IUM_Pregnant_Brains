// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Strings;
import com.google.common.math.LongMath;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class ServiceConfigUtil
{

    private static final long NANOS_PER_SECOND;

    private static List checkObjectList(List list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (!(list.get(i) instanceof Map))
            {
                throw new ClassCastException(String.format("value %s for idx %d in %s is not object", new Object[] {
                    list.get(i), Integer.valueOf(i), list
                }));
            }
        }

        return list;
    }

    static List checkStringList(List list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (!(list.get(i) instanceof String))
            {
                throw new ClassCastException(String.format("value %s for idx %d in %s is not string", new Object[] {
                    list.get(i), Integer.valueOf(i), list
                }));
            }
        }

        return list;
    }

    static Double getBackoffMultiplierFromRetryPolicy(Map map)
    {
        if (!map.containsKey("backoffMultiplier"))
        {
            return null;
        } else
        {
            return getDouble(map, "backoffMultiplier");
        }
    }

    static Double getDouble(Map map, String s)
    {
        Object obj = map.get(s);
        if (obj == null)
        {
            throw new NullPointerException(Strings.lenientFormat("no such key %s", new Object[] {
                s
            }));
        }
        if (obj instanceof Double)
        {
            return (Double)obj;
        } else
        {
            throw new ClassCastException(String.format("value %s for key %s in %s is not Double", new Object[] {
                obj, s, map
            }));
        }
    }

    static Long getInitialBackoffNanosFromRetryPolicy(Map map)
    {
        if (!map.containsKey("initialBackoff"))
        {
            return null;
        }
        map = getString(map, "initialBackoff");
        long l;
        try
        {
            l = parseDuration(map);
        }
        // Misplaced declaration of an exception variable
        catch (Map map)
        {
            throw new RuntimeException(map);
        }
        return Long.valueOf(l);
    }

    static List getList(Map map, String s)
    {
        Object obj = map.get(s);
        if (obj == null)
        {
            throw new NullPointerException(Strings.lenientFormat("no such key %s", new Object[] {
                s
            }));
        }
        if (obj instanceof List)
        {
            return (List)obj;
        } else
        {
            throw new ClassCastException(String.format("value %s for key %s in %s is not List", new Object[] {
                obj, s, map
            }));
        }
    }

    public static String getLoadBalancingPolicyFromServiceConfig(Map map)
    {
        if (!map.containsKey("loadBalancingPolicy"))
        {
            return null;
        } else
        {
            return getString(map, "loadBalancingPolicy");
        }
    }

    static Integer getMaxAttemptsFromRetryPolicy(Map map)
    {
        if (!map.containsKey("maxAttempts"))
        {
            return null;
        } else
        {
            return Integer.valueOf(getDouble(map, "maxAttempts").intValue());
        }
    }

    static Long getMaxBackoffNanosFromRetryPolicy(Map map)
    {
        if (!map.containsKey("maxBackoff"))
        {
            return null;
        }
        map = getString(map, "maxBackoff");
        long l;
        try
        {
            l = parseDuration(map);
        }
        // Misplaced declaration of an exception variable
        catch (Map map)
        {
            throw new RuntimeException(map);
        }
        return Long.valueOf(l);
    }

    static Integer getMaxRequestMessageBytesFromMethodConfig(Map map)
    {
        if (!map.containsKey("maxRequestMessageBytes"))
        {
            return null;
        } else
        {
            return Integer.valueOf(getDouble(map, "maxRequestMessageBytes").intValue());
        }
    }

    static Integer getMaxResponseMessageBytesFromMethodConfig(Map map)
    {
        if (!map.containsKey("maxResponseMessageBytes"))
        {
            return null;
        } else
        {
            return Integer.valueOf(getDouble(map, "maxResponseMessageBytes").intValue());
        }
    }

    static List getMethodConfigFromServiceConfig(Map map)
    {
        if (!map.containsKey("methodConfig"))
        {
            return null;
        } else
        {
            return checkObjectList(getList(map, "methodConfig"));
        }
    }

    static String getMethodFromName(Map map)
    {
        if (!map.containsKey("method"))
        {
            return null;
        } else
        {
            return getString(map, "method");
        }
    }

    static List getNameListFromMethodConfig(Map map)
    {
        if (!map.containsKey("name"))
        {
            return null;
        } else
        {
            return checkObjectList(getList(map, "name"));
        }
    }

    static Map getObject(Map map, String s)
    {
        Object obj = map.get(s);
        if (obj == null)
        {
            throw new NullPointerException(Strings.lenientFormat("no such key %s", new Object[] {
                s
            }));
        }
        if (obj instanceof Map)
        {
            return (Map)obj;
        } else
        {
            throw new ClassCastException(String.format("value %s for key %s in %s is not object", new Object[] {
                obj, s, map
            }));
        }
    }

    static Map getRetryPolicyFromMethodConfig(Map map)
    {
        if (!map.containsKey("retryPolicy"))
        {
            return null;
        } else
        {
            return getObject(map, "retryPolicy");
        }
    }

    static List getRetryableStatusCodesFromRetryPolicy(Map map)
    {
        if (!map.containsKey("retryableStatusCodes"))
        {
            return null;
        } else
        {
            return checkStringList(getList(map, "retryableStatusCodes"));
        }
    }

    static String getServiceFromName(Map map)
    {
        if (!map.containsKey("service"))
        {
            return null;
        } else
        {
            return getString(map, "service");
        }
    }

    private static String getString(Map map, String s)
    {
        Object obj = map.get(s);
        if (obj == null)
        {
            throw new NullPointerException(Strings.lenientFormat("no such key %s", new Object[] {
                s
            }));
        }
        if (obj instanceof String)
        {
            return (String)obj;
        } else
        {
            throw new ClassCastException(String.format("value %s for key %s in %s is not String", new Object[] {
                obj, s, map
            }));
        }
    }

    static RetriableStream.Throttle getThrottlePolicy(Map map)
    {
        boolean flag1 = true;
        if (map == null || !map.containsKey("retryThrottling"))
        {
            return null;
        }
        map = getObject(map, "retryThrottling");
        float f = getDouble(map, "maxTokens").floatValue();
        float f1 = getDouble(map, "tokenRatio").floatValue();
        boolean flag;
        if (f > 0.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("maxToken should be greater than zero"));
        }
        if (f1 > 0.0F)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("tokenRatio should be greater than zero"));
        } else
        {
            return new RetriableStream.Throttle(f, f1);
        }
    }

    static Long getTimeoutFromMethodConfig(Map map)
    {
        if (!map.containsKey("timeout"))
        {
            return null;
        }
        map = getString(map, "timeout");
        long l;
        try
        {
            l = parseDuration(map);
        }
        // Misplaced declaration of an exception variable
        catch (Map map)
        {
            throw new RuntimeException(map);
        }
        return Long.valueOf(l);
    }

    static Boolean getWaitForReadyFromMethodConfig(Map map)
    {
        if (!map.containsKey("waitForReady"))
        {
            return null;
        }
        Object obj = map.get("waitForReady");
        if (obj == null)
        {
            throw new NullPointerException(Strings.lenientFormat("no such key %s", new Object[] {
                "waitForReady"
            }));
        }
        if (obj instanceof Boolean)
        {
            return (Boolean)obj;
        } else
        {
            throw new ClassCastException(String.format("value %s for key %s in %s is not Boolean", new Object[] {
                obj, "waitForReady", map
            }));
        }
    }

    private static long parseDuration(String s)
        throws ParseException
    {
        int i;
        int j;
        long l1;
        boolean flag = true;
        if (s.isEmpty() || s.charAt(s.length() - 1) != 's')
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Invalid duration string: ".concat(s);
            } else
            {
                s = new String("Invalid duration string: ");
            }
            throw new ParseException(s, 0);
        }
        String s1;
        String s2;
        String s3;
        long l2;
        long l3;
        if (s.charAt(0) == '-')
        {
            s = s.substring(1);
            j = 1;
        } else
        {
            j = 0;
        }
        s3 = s.substring(0, s.length() - 1);
        s2 = "";
        i = s3.indexOf('.');
        s1 = s3;
        if (i != -1)
        {
            s2 = s3.substring(i + 1);
            s1 = s3.substring(0, i);
        }
        l1 = Long.parseLong(s1);
        if (s2.isEmpty())
        {
            i = 0;
        } else
        {
            int k = 0;
            i = 0;
            while (k < 9) 
            {
                int l = i * 10;
                i = l;
                if (k < s2.length())
                {
                    if (s2.charAt(k) < '0' || s2.charAt(k) > '9')
                    {
                        throw new ParseException("Invalid nanoseconds.", 0);
                    }
                    i = l + (s2.charAt(k) - 48);
                }
                k++;
            }
        }
        if (l1 < 0L)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Invalid duration string: ".concat(s);
            } else
            {
                s = new String("Invalid duration string: ");
            }
            throw new ParseException(s, 0);
        }
        if (j != 0)
        {
            l1 = -l1;
            j = -i;
        } else
        {
            j = i;
        }
        l2 = j;
        if (l2 <= -NANOS_PER_SECOND)
        {
            break MISSING_BLOCK_LABEL_340;
        }
        i = j;
        l2 = l1;
        if ((long)j < NANOS_PER_SECOND)
        {
            break MISSING_BLOCK_LABEL_364;
        }
        l2 = LongMath.checkedAdd(l1, (long)j / NANOS_PER_SECOND);
        i = (int)((long)j % NANOS_PER_SECOND);
        j = i;
        l1 = l2;
        if (l2 > 0L)
        {
            j = i;
            l1 = l2;
            if (i < 0)
            {
                try
                {
                    j = (int)((long)i + NANOS_PER_SECOND);
                }
                // Misplaced declaration of an exception variable
                catch (String s)
                {
                    throw new ParseException("Duration value is out of range.", 0);
                }
                l1 = l2 - 1L;
            }
        }
        if (l1 >= 0L || j <= 0) goto _L2; else goto _L1
_L1:
        j = (int)((long)j - NANOS_PER_SECOND);
        l1++;
          goto _L2
_L10:
        if (i != 0) goto _L4; else goto _L3
_L3:
        throw new IllegalArgumentException(String.format("Duration is not valid. See proto definition for valid values. Seconds (%s) must be in range [-315,576,000,000, +315,576,000,000]. Nanos (%s) must be in range [-999,999,999, +999,999,999]. Nanos must have the same sign as seconds", new Object[] {
            Long.valueOf(l1), Integer.valueOf(j)
        }));
_L8:
        if ((long)j < 0xffffffffc4653601L) goto _L6; else goto _L5
_L5:
        l2 = j;
        if (l2 < NANOS_PER_SECOND) goto _L7; else goto _L6
_L4:
        l1 = TimeUnit.SECONDS.toNanos(l1);
        l2 = j;
        l3 = l1 + l2;
        if ((l2 ^ l1) < 0L)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if ((l1 ^ l3) >= 0L)
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            j = 0;
        }
        if ((i | j) != 0)
        {
            return l3;
        } else
        {
            return 0x7fffffffffffffffL + (l3 >>> 63 ^ 1L);
        }
_L2:
        if (l1 < 0xffffffb686346200L || l1 > 0x4979cb9e00L)
        {
            i = 0;
            continue; /* Loop/switch isn't completed */
        }
          goto _L8
_L6:
        i = 0;
        continue; /* Loop/switch isn't completed */
_L7:
        if ((l1 < 0L || j < 0) && (l1 > 0L || j > 0))
        {
            i = 0;
        } else
        {
            i = 1;
        }
        if (true) goto _L10; else goto _L9
_L9:
    }

    static 
    {
        NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1L);
    }
}
