// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.FormatUtils;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.FixedDateTimeZone;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;
import org.joda.time.tz.UTCProvider;
import org.joda.time.tz.ZoneInfoProvider;

// Referenced classes of package org.joda.time:
//            IllegalInstantException

public abstract class DateTimeZone
    implements Serializable
{
    static final class Stub
        implements Serializable
    {

        public static final long serialVersionUID = 0xa62f019a7c321ae3L;
        private transient String iID;

        private final void readObject(ObjectInputStream objectinputstream)
            throws IOException
        {
            iID = objectinputstream.readUTF();
        }

        private final Object readResolve()
            throws ObjectStreamException
        {
            return DateTimeZone.forID(iID);
        }

        private final void writeObject(ObjectOutputStream objectoutputstream)
            throws IOException
        {
            objectoutputstream.writeUTF(iID);
        }

        Stub(String s)
        {
            iID = s;
        }
    }


    public static final DateTimeZone UTC;
    public static Set cAvailableIDs;
    private static volatile DateTimeZone cDefault;
    public static NameProvider cNameProvider;
    private static DateTimeFormatter cOffsetFormatter;
    private static Provider cProvider;
    private static Map cZoneIdConversion;
    private static Map iFixedOffsetCache;
    public static final long serialVersionUID = 0x4cf893d49479892aL;
    public final String iID;

    public DateTimeZone(String s)
    {
        if (s == null)
        {
            throw new IllegalArgumentException("Id must not be null");
        } else
        {
            iID = s;
            return;
        }
    }

    private static DateTimeZone fixedOffsetZone(String s, int i)
    {
        org/joda/time/DateTimeZone;
        JVM INSTR monitorenter ;
        if (i != 0) goto _L2; else goto _L1
_L1:
        Object obj = UTC;
_L4:
        org/joda/time/DateTimeZone;
        JVM INSTR monitorexit ;
        return ((DateTimeZone) (obj));
_L2:
        if (iFixedOffsetCache == null)
        {
            iFixedOffsetCache = new HashMap();
        }
        obj = (Reference)iFixedOffsetCache.get(s);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        DateTimeZone datetimezone = (DateTimeZone)((Reference) (obj)).get();
        obj = datetimezone;
        if (datetimezone != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = new FixedDateTimeZone(s, null, i, i);
        iFixedOffsetCache.put(s, new SoftReference(obj));
        if (true) goto _L4; else goto _L3
_L3:
        s;
        throw s;
    }

    public static DateTimeZone forID(String s)
    {
        DateTimeZone datetimezone;
        if (s == null)
        {
            datetimezone = getDefault();
        } else
        {
            if (s.equals("UTC"))
            {
                return UTC;
            }
            DateTimeZone datetimezone1 = cProvider.getZone(s);
            datetimezone = datetimezone1;
            if (datetimezone1 == null)
            {
                if (s.startsWith("+") || s.startsWith("-"))
                {
                    _cls1 _lcls1 = new _cls1();
                    int i = -(int)offsetFormatter().withChronology(_lcls1).parseMillis(s);
                    if ((long)i == 0L)
                    {
                        return UTC;
                    } else
                    {
                        return fixedOffsetZone(printOffset(i), i);
                    }
                } else
                {
                    throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 41)).append("The datetime zone id '").append(s).append("' is not recognised").toString());
                }
            }
        }
        return datetimezone;
    }

    public static DateTimeZone forTimeZone(TimeZone timezone)
    {
        DateTimeZone datetimezone1;
        if (timezone == null)
        {
            datetimezone1 = getDefault();
        } else
        {
            String s = timezone.getID();
            if (s.equals("UTC"))
            {
                return UTC;
            }
            datetimezone1 = null;
            String s1 = getConvertedId(s);
            if (s1 != null)
            {
                datetimezone1 = cProvider.getZone(s1);
            }
            DateTimeZone datetimezone = datetimezone1;
            if (datetimezone1 == null)
            {
                datetimezone = cProvider.getZone(s);
            }
            datetimezone1 = datetimezone;
            if (datetimezone == null)
            {
                if (s1 == null)
                {
                    timezone = timezone.getID();
                    if (timezone.startsWith("GMT+") || timezone.startsWith("GMT-"))
                    {
                        timezone = timezone.substring(3);
                        _cls1 _lcls1 = new _cls1();
                        int i = -(int)offsetFormatter().withChronology(_lcls1).parseMillis(timezone);
                        if ((long)i == 0L)
                        {
                            return UTC;
                        } else
                        {
                            return fixedOffsetZone(printOffset(i), i);
                        }
                    }
                }
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 41)).append("The datetime zone id '").append(s).append("' is not recognised").toString());
            }
        }
        return datetimezone1;
    }

    private static String getConvertedId(String s)
    {
        org/joda/time/DateTimeZone;
        JVM INSTR monitorenter ;
        Map map = cZoneIdConversion;
        Object obj;
        obj = map;
        if (map != null)
        {
            break MISSING_BLOCK_LABEL_425;
        }
        obj = new HashMap();
        ((Map) (obj)).put("GMT", "UTC");
        ((Map) (obj)).put("WET", "WET");
        ((Map) (obj)).put("CET", "CET");
        ((Map) (obj)).put("MET", "CET");
        ((Map) (obj)).put("ECT", "CET");
        ((Map) (obj)).put("EET", "EET");
        ((Map) (obj)).put("MIT", "Pacific/Apia");
        ((Map) (obj)).put("HST", "Pacific/Honolulu");
        ((Map) (obj)).put("AST", "America/Anchorage");
        ((Map) (obj)).put("PST", "America/Los_Angeles");
        ((Map) (obj)).put("MST", "America/Denver");
        ((Map) (obj)).put("PNT", "America/Phoenix");
        ((Map) (obj)).put("CST", "America/Chicago");
        ((Map) (obj)).put("EST", "America/New_York");
        ((Map) (obj)).put("IET", "America/Indiana/Indianapolis");
        ((Map) (obj)).put("PRT", "America/Puerto_Rico");
        ((Map) (obj)).put("CNT", "America/St_Johns");
        ((Map) (obj)).put("AGT", "America/Argentina/Buenos_Aires");
        ((Map) (obj)).put("BET", "America/Sao_Paulo");
        ((Map) (obj)).put("ART", "Africa/Cairo");
        ((Map) (obj)).put("CAT", "Africa/Harare");
        ((Map) (obj)).put("EAT", "Africa/Addis_Ababa");
        ((Map) (obj)).put("NET", "Asia/Yerevan");
        ((Map) (obj)).put("PLT", "Asia/Karachi");
        ((Map) (obj)).put("IST", "Asia/Kolkata");
        ((Map) (obj)).put("BST", "Asia/Dhaka");
        ((Map) (obj)).put("VST", "Asia/Ho_Chi_Minh");
        ((Map) (obj)).put("CTT", "Asia/Shanghai");
        ((Map) (obj)).put("JST", "Asia/Tokyo");
        ((Map) (obj)).put("ACT", "Australia/Darwin");
        ((Map) (obj)).put("AET", "Australia/Sydney");
        ((Map) (obj)).put("SST", "Pacific/Guadalcanal");
        ((Map) (obj)).put("NST", "Pacific/Auckland");
        cZoneIdConversion = ((Map) (obj));
        s = (String)((Map) (obj)).get(s);
        org/joda/time/DateTimeZone;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public static DateTimeZone getDefault()
    {
        Object obj = cDefault;
        if (obj != null) goto _L2; else goto _L1
_L1:
        org/joda/time/DateTimeZone;
        JVM INSTR monitorenter ;
        Object obj1 = cDefault;
        obj = obj1;
        if (obj1 != null) goto _L4; else goto _L3
_L3:
        DateTimeZone datetimezone;
        datetimezone = null;
        obj1 = null;
        obj = datetimezone;
        String s = System.getProperty("user.timezone");
        obj = obj1;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        obj = datetimezone;
        datetimezone = forID(s);
        obj = datetimezone;
_L5:
        obj1 = obj;
        if (obj == null)
        {
            try
            {
                obj1 = forTimeZone(TimeZone.getDefault());
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1)
            {
                obj1 = obj;
            }
            finally { }
        }
        obj = obj1;
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_72;
        }
        obj = UTC;
        cDefault = ((DateTimeZone) (obj));
_L4:
        org/joda/time/DateTimeZone;
        JVM INSTR monitorexit ;
        return ((DateTimeZone) (obj));
        org/joda/time/DateTimeZone;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        obj = obj1;
        if (true) goto _L5; else goto _L2
_L2:
        return ((DateTimeZone) (obj));
    }

    private static NameProvider getDefaultNameProvider()
    {
        Object obj = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
        if (obj == null) goto _L2; else goto _L1
_L1:
        obj = (NameProvider)Class.forName(((String) (obj))).newInstance();
_L4:
        Object obj1 = obj;
        if (obj == null)
        {
            obj1 = new DefaultNameProvider();
        }
        return ((NameProvider) (obj1));
        obj;
        Thread thread = Thread.currentThread();
        thread.getThreadGroup().uncaughtException(thread, ((Throwable) (obj)));
_L2:
        obj = null;
        continue; /* Loop/switch isn't completed */
        SecurityException securityexception;
        securityexception;
        securityexception = null;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static Provider getDefaultProvider()
    {
        Object obj1 = null;
        String s = System.getProperty("org.joda.time.DateTimeZone.Provider");
        Object obj;
        obj = obj1;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        obj = (Provider)Class.forName(s).newInstance();
_L1:
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        obj1 = new ZoneInfoProvider("org/joda/time/tz/data");
        obj = obj1;
_L2:
        obj1 = obj;
        if (obj == null)
        {
            obj1 = new UTCProvider();
        }
        return ((Provider) (obj1));
        obj;
        Thread thread = Thread.currentThread();
        thread.getThreadGroup().uncaughtException(thread, ((Throwable) (obj)));
        obj = obj1;
          goto _L1
        SecurityException securityexception;
        securityexception;
        securityexception = ((SecurityException) (obj1));
          goto _L1
        Exception exception;
        exception;
        Thread thread1 = Thread.currentThread();
        thread1.getThreadGroup().uncaughtException(thread1, exception);
          goto _L2
    }

    private static DateTimeFormatter offsetFormatter()
    {
        org/joda/time/DateTimeZone;
        JVM INSTR monitorenter ;
        DateTimeFormatter datetimeformatter;
        if (cOffsetFormatter == null)
        {
            DateTimeFormatterBuilder datetimeformatterbuilder = new DateTimeFormatterBuilder();
            org.joda.time.format.DateTimeFormatterBuilder.TimeZoneOffset timezoneoffset = new org.joda.time.format.DateTimeFormatterBuilder.TimeZoneOffset(null, null, true, 2, 4);
            datetimeformatterbuilder.iFormatter = null;
            datetimeformatterbuilder.iElementPairs.add(timezoneoffset);
            datetimeformatterbuilder.iElementPairs.add(timezoneoffset);
            cOffsetFormatter = datetimeformatterbuilder.toFormatter();
        }
        datetimeformatter = cOffsetFormatter;
        org/joda/time/DateTimeZone;
        JVM INSTR monitorexit ;
        return datetimeformatter;
        Exception exception;
        exception;
        throw exception;
    }

    public static String printOffset(int i)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int j;
        if (i >= 0)
        {
            stringbuffer.append('+');
        } else
        {
            stringbuffer.append('-');
            i = -i;
        }
        j = i / 0x36ee80;
        FormatUtils.appendPaddedInteger(stringbuffer, j, 2);
        i -= j * 0x36ee80;
        j = i / 60000;
        stringbuffer.append(':');
        FormatUtils.appendPaddedInteger(stringbuffer, j, 2);
        i -= j * 60000;
        if (i == 0)
        {
            return stringbuffer.toString();
        }
        j = i / 1000;
        stringbuffer.append(':');
        FormatUtils.appendPaddedInteger(stringbuffer, j, 2);
        i -= j * 1000;
        if (i == 0)
        {
            return stringbuffer.toString();
        } else
        {
            stringbuffer.append('.');
            FormatUtils.appendPaddedInteger(stringbuffer, i, 3);
            return stringbuffer.toString();
        }
    }

    public final long convertLocalToUTC(long l, boolean flag)
    {
        int i;
        int j;
        long l2;
        l2 = 0x7fffffffffffffffL;
        i = getOffset(l);
        j = getOffset(l - (long)i);
        if (i == j || !flag && i >= 0) goto _L2; else goto _L1
_L1:
        long l3 = nextTransition(l - (long)i);
        long l1 = l3;
        if (l3 == l - (long)i)
        {
            l1 = 0x7fffffffffffffffL;
        }
        l3 = nextTransition(l - (long)j);
        if (l3 != l - (long)j)
        {
            l2 = l3;
        }
        if (l1 == l2) goto _L2; else goto _L3
_L3:
        if (flag)
        {
            throw new IllegalInstantException(l, iID);
        }
_L5:
        l1 = l - (long)i;
        if ((l ^ l1) < 0L && ((long)i ^ l) < 0L)
        {
            throw new ArithmeticException("Subtracting time zone offset caused overflow");
        } else
        {
            return l1;
        }
_L2:
        i = j;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final long convertLocalToUTC(long l, boolean flag, long l1)
    {
        int i = getOffset(l1);
        l1 = l - (long)i;
        if (getOffset(l1) == i)
        {
            return l1;
        } else
        {
            return convertLocalToUTC(l, false);
        }
    }

    public final long convertUTCToLocal(long l)
    {
        int i = getOffset(l);
        long l1 = (long)i + l;
        if ((l ^ l1) < 0L && ((long)i ^ l) >= 0L)
        {
            throw new ArithmeticException("Adding time zone offset caused overflow");
        } else
        {
            return l1;
        }
    }

    public abstract boolean equals(Object obj);

    public abstract String getNameKey(long l);

    public abstract int getOffset(long l);

    public int getOffsetFromLocal(long l)
    {
        int i = getOffset(l);
        long l1 = l - (long)i;
        int j = getOffset(l1);
        if (i != j)
        {
            if (i - j < 0 && nextTransition(l1) != nextTransition(l - (long)j))
            {
                return i;
            }
        } else
        if (i >= 0)
        {
            l = previousTransition(l1);
            if (l < l1)
            {
                int k = getOffset(l);
                if (l1 - l <= (long)(k - i))
                {
                    return k;
                }
            }
        }
        return j;
    }

    public abstract int getStandardOffset(long l);

    public int hashCode()
    {
        return iID.hashCode() + 57;
    }

    public abstract boolean isFixed();

    public abstract long nextTransition(long l);

    public abstract long previousTransition(long l);

    public String toString()
    {
        return iID;
    }

    protected Object writeReplace()
        throws ObjectStreamException
    {
        return new Stub(iID);
    }

    static 
    {
        UTC = new FixedDateTimeZone("UTC", "UTC", 0, 0);
        Provider provider = getDefaultProvider();
        Set set = provider.getAvailableIDs();
        if (set == null || set.size() == 0)
        {
            throw new IllegalArgumentException("The provider doesn't have any available ids");
        }
        if (!set.contains("UTC"))
        {
            throw new IllegalArgumentException("The provider doesn't support UTC");
        }
        if (!UTC.equals(provider.getZone("UTC")))
        {
            throw new IllegalArgumentException("Invalid UTC zone provided");
        } else
        {
            cProvider = provider;
            cAvailableIDs = set;
            cNameProvider = getDefaultNameProvider();
        }
    }

    private class _cls1 extends BaseChronology
    {

        public static final long serialVersionUID = 0xd4947ab9cf0bf864L;

        public final DateTimeZone getZone()
        {
            return null;
        }

        public final String toString()
        {
            return getClass().getName();
        }

        public final Chronology withUTC()
        {
            return this;
        }

        public final Chronology withZone(DateTimeZone datetimezone)
        {
            return this;
        }

        _cls1()
        {
        }
    }

}
