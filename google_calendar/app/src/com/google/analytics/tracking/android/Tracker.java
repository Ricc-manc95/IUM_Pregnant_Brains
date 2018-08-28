// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

// Referenced classes of package com.google.analytics.tracking.android:
//            TrackerHandler

public final class Tracker
{

    private final TrackerHandler mHandler;
    private boolean mIsThrottlingEnabled;
    public volatile boolean mIsTrackerClosed;
    private volatile boolean mIsTrackingStarted;
    private long mLastTrackTime;
    public final SimpleModel mModel;
    private long mTokens;

    Tracker()
    {
        mIsTrackerClosed = false;
        mIsTrackingStarted = false;
        mTokens = 0x1d4c0L;
        mIsThrottlingEnabled = true;
        mHandler = null;
        mModel = null;
    }

    Tracker(String s, TrackerHandler trackerhandler)
    {
        mIsTrackerClosed = false;
        mIsTrackingStarted = false;
        mTokens = 0x1d4c0L;
        mIsThrottlingEnabled = true;
        if (s == null)
        {
            throw new IllegalArgumentException("trackingId cannot be null");
        } else
        {
            mHandler = trackerhandler;
            mModel = new SimpleModel();
            mModel.set("trackingId", s);
            mModel.set("sampleRate", "100");
            mModel.setForNextHit("sessionControl", "start");
            mModel.set("useSecure", Boolean.toString(true));
            return;
        }
    }

    private final boolean tokensAvailable()
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        boolean flag1 = mIsThrottlingEnabled;
        if (flag1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        long l;
        long l1;
        l = System.currentTimeMillis();
        if (mTokens >= 0x1d4c0L)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        l1 = l - mLastTrackTime;
        if (l1 <= 0L)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        mTokens = Math.min(0x1d4c0L, l1 + mTokens);
        mLastTrackTime = l;
        if (mTokens >= 2000L)
        {
            mTokens = mTokens - 2000L;
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_104;
        Exception exception;
        exception;
        throw exception;
        flag = false;
        if (true) goto _L1; else goto _L3
_L3:
    }

    public final void internalSend(String s, Map map)
    {
        mIsTrackingStarted = true;
        Object obj = map;
        if (map == null)
        {
            obj = new HashMap();
        }
        ((Map) (obj)).put("hitType", s);
        mModel.setAll(((Map) (obj)), Boolean.valueOf(true));
        if (tokensAvailable())
        {
            mHandler.sendHit(mModel.getKeysAndValues());
        }
        mModel.clearTemporaryValues();
    }

    static 
    {
        new DecimalFormat("0.######", new DecimalFormatSymbols(Locale.US));
    }

    private class SimpleModel
    {

        private Map permanentMap;
        private Map temporaryMap;

        public final void clearTemporaryValues()
        {
            this;
            JVM INSTR monitorenter ;
            temporaryMap.clear();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public final Map getKeysAndValues()
        {
            this;
            JVM INSTR monitorenter ;
            HashMap hashmap;
            hashmap = new HashMap(permanentMap);
            hashmap.putAll(temporaryMap);
            this;
            JVM INSTR monitorexit ;
            return hashmap;
            Exception exception;
            exception;
            throw exception;
        }

        public final void set(String s, String s1)
        {
            this;
            JVM INSTR monitorenter ;
            permanentMap.put(s, s1);
            this;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        public final void setAll(Map map, Boolean boolean1)
        {
            this;
            JVM INSTR monitorenter ;
            if (!boolean1.booleanValue()) goto _L2; else goto _L1
_L1:
            temporaryMap.putAll(map);
_L4:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            permanentMap.putAll(map);
            if (true) goto _L4; else goto _L3
_L3:
            map;
            throw map;
        }

        public final void setForNextHit(String s, String s1)
        {
            this;
            JVM INSTR monitorenter ;
            temporaryMap.put(s, s1);
            this;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        SimpleModel()
        {
            temporaryMap = new HashMap();
            permanentMap = new HashMap();
        }
    }

}
