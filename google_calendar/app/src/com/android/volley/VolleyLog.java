// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class VolleyLog
{
    public static final class MarkerLog
    {

        public static final boolean ENABLED;
        private boolean mFinished;
        private final List mMarkers = new ArrayList();

        public final void add(String s, long l)
        {
            this;
            JVM INSTR monitorenter ;
            if (mFinished)
            {
                throw new IllegalStateException("Marker added to finished log");
            }
            break MISSING_BLOCK_LABEL_24;
            s;
            this;
            JVM INSTR monitorexit ;
            throw s;
            class Marker
            {

                public final String name;
                public final long thread;
                public final long time;

                public Marker(String s, long l, long l1)
                {
                    name = s;
                    thread = l;
                    time = l1;
                }
            }

            mMarkers.add(new Marker(s, l, SystemClock.elapsedRealtime()));
            this;
            JVM INSTR monitorexit ;
        }

        protected final void finalize()
            throws Throwable
        {
            if (!mFinished)
            {
                finish("Request on the loose");
                Log.e(VolleyLog.TAG, VolleyLog.buildMessage("Marker log finalized without finish() - uncaught exit point for request", new Object[0]));
            }
        }

        public final void finish(String s)
        {
            this;
            JVM INSTR monitorenter ;
            int i;
            mFinished = true;
            i = mMarkers.size();
            if (i != 0) goto _L2; else goto _L1
_L1:
            long l = 0L;
_L9:
            if (l > 0L) goto _L4; else goto _L3
_L3:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            l = ((Marker)mMarkers.get(0)).time;
            l = ((Marker)mMarkers.get(mMarkers.size() - 1)).time - l;
            continue; /* Loop/switch isn't completed */
_L4:
            long l1;
            l1 = ((Marker)mMarkers.get(0)).time;
            VolleyLog.d("(%-4d ms) %s", new Object[] {
                Long.valueOf(l), s
            });
            s = mMarkers.iterator();
            l = l1;
_L6:
            if (!s.hasNext())
            {
                break; /* Loop/switch isn't completed */
            }
            Marker marker = (Marker)s.next();
            l1 = marker.time;
            VolleyLog.d("(+%-4d) [%2d] %s", new Object[] {
                Long.valueOf(l1 - l), Long.valueOf(marker.thread), marker.name
            });
            l = l1;
            if (true) goto _L6; else goto _L5
_L5:
            if (true) goto _L3; else goto _L7
_L7:
            s;
            throw s;
            if (true) goto _L9; else goto _L8
_L8:
        }

        static 
        {
            ENABLED = VolleyLog.DEBUG;
        }

        MarkerLog()
        {
            mFinished = false;
        }
    }


    private static final String CLASS_NAME = com/android/volley/VolleyLog.getName();
    public static boolean DEBUG = false;
    public static String TAG = "Volley";

    public VolleyLog()
    {
    }

    public static transient String buildMessage(String s, Object aobj[])
    {
        int i;
        String s1;
        if (aobj != null)
        {
            s = String.format(Locale.US, s, aobj);
        }
        aobj = (new Throwable()).fillInStackTrace().getStackTrace();
        i = 2;
_L3:
        if (i >= aobj.length)
        {
            break MISSING_BLOCK_LABEL_174;
        }
        if (((StackTraceElement) (aobj[i])).getClassName().equals(CLASS_NAME)) goto _L2; else goto _L1
_L1:
        s1 = ((StackTraceElement) (aobj[i])).getClassName();
        s1 = s1.substring(s1.lastIndexOf('.') + 1);
        s1 = s1.substring(s1.lastIndexOf('$') + 1);
        aobj = ((StackTraceElement) (aobj[i])).getMethodName();
        aobj = (new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(((Object) (aobj))).length())).append(s1).append(".").append(((String) (aobj))).toString();
_L4:
        return String.format(Locale.US, "[%d] %s: %s", new Object[] {
            Long.valueOf(Thread.currentThread().getId()), aobj, s
        });
_L2:
        i++;
          goto _L3
        aobj = "<unknown>";
          goto _L4
    }

    public static transient void d(String s, Object aobj[])
    {
        buildMessage(s, aobj);
    }

    public static transient void v(String s, Object aobj[])
    {
    }

}
