// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzbqq
{

    private static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static String bA[] = new String[0];
    private static final Uri bt = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern bu = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern bv = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    public static final AtomicBoolean bw = new AtomicBoolean();
    private static HashMap bx;
    private static Object by;
    private static boolean bz;

    public zzbqq()
    {
    }

    public static int getInt(ContentResolver contentresolver, String s, int i)
    {
        contentresolver = zza(contentresolver, s, ((String) (null)));
        int j = i;
        if (contentresolver != null)
        {
            try
            {
                j = Integer.parseInt(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch (ContentResolver contentresolver)
            {
                return i;
            }
        }
        return j;
    }

    public static long getLong(ContentResolver contentresolver, String s, long l)
    {
        contentresolver = zza(contentresolver, s, ((String) (null)));
        long l1 = l;
        if (contentresolver != null)
        {
            try
            {
                l1 = Long.parseLong(contentresolver);
            }
            // Misplaced declaration of an exception variable
            catch (ContentResolver contentresolver)
            {
                return l;
            }
        }
        return l1;
    }

    public static String getString(ContentResolver contentresolver, String s)
    {
        return zza(contentresolver, s, ((String) (null)));
    }

    public static String zza(ContentResolver contentresolver, String s, String s1)
    {
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorenter ;
        Object obj;
        zza(contentresolver);
        obj = by;
        if (!bx.containsKey(s))
        {
            break MISSING_BLOCK_LABEL_44;
        }
        contentresolver = (String)bx.get(s);
        if (contentresolver != null)
        {
            s1 = contentresolver;
        }
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorexit ;
        return s1;
        String as[];
        int j;
        as = bA;
        j = as.length;
        int i = 0;
_L2:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        if (!s.startsWith(as[i]))
        {
            break MISSING_BLOCK_LABEL_284;
        }
        if (bz && !bx.isEmpty())
        {
            break MISSING_BLOCK_LABEL_145;
        }
        as = bA;
        bx.putAll(zza(contentresolver, as));
        bz = true;
        if (!bx.containsKey(s))
        {
            break MISSING_BLOCK_LABEL_145;
        }
        contentresolver = (String)bx.get(s);
        if (contentresolver != null)
        {
            s1 = contentresolver;
        }
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorexit ;
        return s1;
        contentresolver;
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorexit ;
        throw contentresolver;
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorexit ;
        return s1;
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorexit ;
        Cursor cursor;
        cursor = contentresolver.query(CONTENT_URI, null, null, new String[] {
            s
        }, null);
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_188;
        }
        if (cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_211;
        }
        zza(obj, s, ((String) (null)));
        contentresolver = s1;
        if (cursor != null)
        {
            cursor.close();
            return s1;
        }
        break MISSING_BLOCK_LABEL_282;
        String s2 = cursor.getString(1);
        contentresolver = s2;
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_238;
        }
        contentresolver = s2;
        if (s2.equals(s1))
        {
            contentresolver = s1;
        }
        zza(obj, s, ((String) (contentresolver)));
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
        break MISSING_BLOCK_LABEL_282;
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

    private static transient Map zza(ContentResolver contentresolver, String as[])
    {
        contentresolver = contentresolver.query(bt, null, null, as, null);
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

    private static void zza(ContentResolver contentresolver)
    {
        if (bx == null)
        {
            bw.set(false);
            bx = new HashMap();
            by = new Object();
            bz = false;
            contentresolver.registerContentObserver(CONTENT_URI, true, new _cls1(null));
        } else
        if (bw.getAndSet(false))
        {
            bx.clear();
            by = new Object();
            bz = false;
            return;
        }
    }

    private static void zza(Object obj, String s, String s1)
    {
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorenter ;
        if (obj == by)
        {
            bx.put(s, s1);
        }
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorexit ;
        return;
        obj;
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public static boolean zza(ContentResolver contentresolver, String s, boolean flag)
    {
        contentresolver = zza(contentresolver, s, ((String) (null)));
        if (contentresolver == null || contentresolver.equals(""))
        {
            return flag;
        }
        if (bu.matcher(contentresolver).matches())
        {
            return true;
        }
        if (bv.matcher(contentresolver).matches())
        {
            return false;
        } else
        {
            Log.w("Gservices", (new StringBuilder(String.valueOf(s).length() + 52 + String.valueOf(contentresolver).length())).append("attempt to read gservices key ").append(s).append(" (value \"").append(contentresolver).append("\") as boolean").toString());
            return flag;
        }
    }

    public static transient void zzb(ContentResolver contentresolver, String as[])
    {
        int i;
        i = 0;
        if (as.length == 0)
        {
            return;
        }
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorenter ;
        HashSet hashset;
        ArrayList arraylist;
        int j;
        zza(contentresolver);
        hashset = new HashSet((bA.length + as.length << 2) / 3 + 1);
        hashset.addAll(Arrays.asList(bA));
        arraylist = new ArrayList();
        j = as.length;
_L9:
        String s;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        s = as[i];
        if (hashset.add(s))
        {
            arraylist.add(s);
        }
        break MISSING_BLOCK_LABEL_206;
        if (!arraylist.isEmpty()) goto _L2; else goto _L1
_L1:
        as = new String[0];
_L5:
        if (bz && !bx.isEmpty()) goto _L4; else goto _L3
_L3:
        as = bA;
        bx.putAll(zza(contentresolver, as));
        bz = true;
_L7:
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorexit ;
        return;
        contentresolver;
        com/google/android/gms/internal/zzbqq;
        JVM INSTR monitorexit ;
        throw contentresolver;
_L2:
        bA = (String[])hashset.toArray(new String[hashset.size()]);
        as = (String[])arraylist.toArray(new String[arraylist.size()]);
          goto _L5
_L4:
        if (as.length == 0) goto _L7; else goto _L6
_L6:
        bx.putAll(zza(contentresolver, as));
        bz = true;
          goto _L7
        i++;
        if (true) goto _L9; else goto _L8
_L8:
    }


    private class _cls1 extends ContentObserver
    {

        public final void onChange(boolean flag)
        {
            zzbqq.bw.set(true);
        }

        _cls1(Handler handler)
        {
            super(null);
        }
    }

}
