// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gsf;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gservices
{

    private static final Uri CONTENT_PREFIX_URI = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    private static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Pattern FALSE_PATTERN = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    public static final Pattern TRUE_PATTERN = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    private static final HashMap sBooleanCache = new HashMap();
    private static HashMap sCache;
    private static final HashMap sFloatCache = new HashMap();
    private static final HashMap sIntCache = new HashMap();
    public static final AtomicBoolean sInvalidateCache = new AtomicBoolean();
    private static final HashMap sLongCache = new HashMap();
    private static boolean sPreloaded;
    private static String sPreloadedPrefixes[] = new String[0];
    private static Object sVersionToken;

    public Gservices()
    {
    }

    private static void ensureCacheInitializedLocked(ContentResolver contentresolver)
    {
        if (sCache == null)
        {
            sInvalidateCache.set(false);
            sCache = new HashMap();
            sVersionToken = new Object();
            sPreloaded = false;
            contentresolver.registerContentObserver(CONTENT_URI, true, new _cls1(null));
        } else
        if (sInvalidateCache.getAndSet(false))
        {
            sCache.clear();
            sBooleanCache.clear();
            sIntCache.clear();
            sLongCache.clear();
            sFloatCache.clear();
            sVersionToken = new Object();
            sPreloaded = false;
            return;
        }
    }

    public static boolean getBoolean(ContentResolver contentresolver, String s, boolean flag)
    {
        Object obj = getVersionToken(contentresolver);
        Boolean boolean1 = (Boolean)getValue(sBooleanCache, s, Boolean.valueOf(flag));
        if (boolean1 != null)
        {
            return boolean1.booleanValue();
        }
        String s1 = getString(contentresolver, s, null);
        contentresolver = boolean1;
        boolean flag1 = flag;
        if (s1 != null)
        {
            if (s1.equals(""))
            {
                flag1 = flag;
                contentresolver = boolean1;
            } else
            if (TRUE_PATTERN.matcher(s1).matches())
            {
                contentresolver = Boolean.valueOf(true);
                flag1 = true;
            } else
            if (FALSE_PATTERN.matcher(s1).matches())
            {
                contentresolver = Boolean.valueOf(false);
                flag1 = false;
            } else
            {
                Log.w("Gservices", (new StringBuilder("attempt to read gservices key ")).append(s).append(" (value \"").append(s1).append("\") as boolean").toString());
                contentresolver = boolean1;
                flag1 = flag;
            }
        }
        putValueAndRemoveFromStringCache(obj, sBooleanCache, s, contentresolver);
        return flag1;
    }

    public static int getInt(ContentResolver contentresolver, String s, int i)
    {
        Integer integer;
        Object obj;
        obj = getVersionToken(contentresolver);
        integer = (Integer)getValue(sIntCache, s, Integer.valueOf(i));
        if (integer != null)
        {
            return integer.intValue();
        }
        contentresolver = getString(contentresolver, s, null);
        if (contentresolver != null) goto _L2; else goto _L1
_L1:
        contentresolver = integer;
_L4:
        putValueAndRemoveFromStringCache(obj, sIntCache, s, contentresolver);
        return i;
_L2:
        int j = Integer.parseInt(contentresolver);
        contentresolver = Integer.valueOf(j);
        i = j;
        continue; /* Loop/switch isn't completed */
        contentresolver;
        contentresolver = integer;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static long getLong(ContentResolver contentresolver, String s, long l)
    {
        Long long1;
        Object obj;
        obj = getVersionToken(contentresolver);
        long1 = (Long)getValue(sLongCache, s, Long.valueOf(l));
        if (long1 != null)
        {
            return long1.longValue();
        }
        contentresolver = getString(contentresolver, s, null);
        if (contentresolver != null) goto _L2; else goto _L1
_L1:
        contentresolver = long1;
_L4:
        putValueAndRemoveFromStringCache(obj, sLongCache, s, contentresolver);
        return l;
_L2:
        long l1 = Long.parseLong(contentresolver);
        contentresolver = Long.valueOf(l1);
        l = l1;
        continue; /* Loop/switch isn't completed */
        contentresolver;
        contentresolver = long1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static String getString(ContentResolver contentresolver, String s, String s1)
    {
        com/google/android/gsf/Gservices;
        JVM INSTR monitorenter ;
        Object obj;
        ensureCacheInitializedLocked(contentresolver);
        obj = sVersionToken;
        if (!sCache.containsKey(s))
        {
            break MISSING_BLOCK_LABEL_44;
        }
        contentresolver = (String)sCache.get(s);
        if (contentresolver != null)
        {
            s1 = contentresolver;
        }
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        return s1;
        String as[];
        int j;
        as = sPreloadedPrefixes;
        j = as.length;
        int i = 0;
_L2:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        if (!s.startsWith(as[i]))
        {
            break MISSING_BLOCK_LABEL_300;
        }
        if (sPreloaded && !sCache.isEmpty())
        {
            break MISSING_BLOCK_LABEL_145;
        }
        as = sPreloadedPrefixes;
        sCache.putAll(getStringsByPrefix(contentresolver, as));
        sPreloaded = true;
        if (!sCache.containsKey(s))
        {
            break MISSING_BLOCK_LABEL_145;
        }
        contentresolver = (String)sCache.get(s);
        if (contentresolver != null)
        {
            s1 = contentresolver;
        }
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        return s1;
        contentresolver;
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        throw contentresolver;
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        return s1;
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        Cursor cursor;
        cursor = contentresolver.query(CONTENT_URI, null, null, new String[] {
            s
        }, null);
        if (cursor == null)
        {
            contentresolver = s1;
            if (cursor != null)
            {
                cursor.close();
                return s1;
            }
            break MISSING_BLOCK_LABEL_298;
        }
        if (cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_227;
        }
        putStringCache(obj, s, null);
        contentresolver = s1;
        if (cursor != null)
        {
            cursor.close();
            return s1;
        }
        break MISSING_BLOCK_LABEL_298;
        String s2 = cursor.getString(1);
        contentresolver = s2;
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_254;
        }
        contentresolver = s2;
        if (s2.equals(s1))
        {
            contentresolver = s1;
        }
        putStringCache(obj, s, contentresolver);
        if (contentresolver != null)
        {
            s1 = contentresolver;
        }
        contentresolver = s1;
        if (cursor != null)
        {
            cursor.close();
            return s1;
        }
        break MISSING_BLOCK_LABEL_298;
        contentresolver;
        if (cursor != null)
        {
            cursor.close();
        }
        throw contentresolver;
        return contentresolver;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static transient Map getStringsByPrefix(ContentResolver contentresolver, String as[])
    {
        contentresolver = contentresolver.query(CONTENT_PREFIX_URI, null, null, as, null);
        as = new TreeMap();
        if (contentresolver == null)
        {
            return as;
        }
        for (; contentresolver.moveToNext(); as.put(contentresolver.getString(0), contentresolver.getString(1))) { }
        break MISSING_BLOCK_LABEL_66;
        as;
        contentresolver.close();
        throw as;
        contentresolver.close();
        return as;
    }

    private static Object getValue(HashMap hashmap, String s, Object obj)
    {
        com/google/android/gsf/Gservices;
        JVM INSTR monitorenter ;
        if (!hashmap.containsKey(s))
        {
            break MISSING_BLOCK_LABEL_26;
        }
        hashmap = ((HashMap) (hashmap.get(s)));
        if (hashmap == null)
        {
            hashmap = ((HashMap) (obj));
        }
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        return hashmap;
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        return null;
        hashmap;
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        throw hashmap;
    }

    public static Object getVersionToken(ContentResolver contentresolver)
    {
        com/google/android/gsf/Gservices;
        JVM INSTR monitorenter ;
        ensureCacheInitializedLocked(contentresolver);
        contentresolver = ((ContentResolver) (sVersionToken));
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        return contentresolver;
        contentresolver;
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        throw contentresolver;
    }

    private static void putStringCache(Object obj, String s, String s1)
    {
        com/google/android/gsf/Gservices;
        JVM INSTR monitorenter ;
        if (obj == sVersionToken)
        {
            sCache.put(s, s1);
        }
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        return;
        obj;
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        throw obj;
    }

    private static void putValueAndRemoveFromStringCache(Object obj, HashMap hashmap, String s, Object obj1)
    {
        com/google/android/gsf/Gservices;
        JVM INSTR monitorenter ;
        if (obj == sVersionToken)
        {
            hashmap.put(s, obj1);
            sCache.remove(s);
        }
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        return;
        obj;
        com/google/android/gsf/Gservices;
        JVM INSTR monitorexit ;
        throw obj;
    }


    private class _cls1 extends ContentObserver
    {

        public final void onChange(boolean flag)
        {
            Gservices.sInvalidateCache.set(true);
        }

        _cls1(Handler handler)
        {
            super(null);
        }
    }

}
