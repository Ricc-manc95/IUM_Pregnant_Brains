// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import android.content.Context;
import android.util.Log;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package com.google.analytics.tracking.android:
//            ServiceProxy, AnalyticsClient, AnalyticsGmsCoreClient, AnalyticsStore, 
//            AnalyticsThread, Clock, GAServiceManager

final class GAServiceProxy
    implements AnalyticsGmsCoreClient.OnConnectedListener, AnalyticsGmsCoreClient.OnConnectionFailedListener, ServiceProxy
{

    private volatile AnalyticsClient client;
    public Clock clock;
    private volatile int connectTries;
    private final Context ctx;
    public volatile Timer disconnectCheckTimer;
    private volatile Timer failedConnectTimer;
    public long idleTimeout;
    public volatile long lastRequestTime;
    private boolean pendingClearHits;
    private boolean pendingDispatch;
    public final Queue queue;
    private volatile Timer reConnectTimer;
    public volatile int state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
    private AnalyticsStore store;
    private AnalyticsStore testStore;
    private final AnalyticsThread thread;

    GAServiceProxy(Context context, AnalyticsThread analyticsthread)
    {
        this(context, analyticsthread, null);
    }

    private GAServiceProxy(Context context, AnalyticsThread analyticsthread, AnalyticsStore analyticsstore)
    {
        queue = new ConcurrentLinkedQueue();
        idleTimeout = 0x493e0L;
        testStore = null;
        ctx = context;
        thread = analyticsthread;
        clock = new _cls1();
        connectTries = 0;
        state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.DISCONNECTED$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
    }

    private final void clearAllTimers()
    {
        Timer timer = reConnectTimer;
        if (timer != null)
        {
            timer.cancel();
        }
        reConnectTimer = null;
        timer = failedConnectTimer;
        if (timer != null)
        {
            timer.cancel();
        }
        failedConnectTimer = null;
        timer = disconnectCheckTimer;
        if (timer != null)
        {
            timer.cancel();
        }
        disconnectCheckTimer = null;
    }

    private final void fireReconnectAttempt()
    {
        Timer timer = reConnectTimer;
        if (timer != null)
        {
            timer.cancel();
        }
        reConnectTimer = null;
        reConnectTimer = new Timer("Service Reconnect");
        reConnectTimer.schedule(new ReconnectTask(), 5000L);
    }

    final void connectToService()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        int j;
        if (client == null)
        {
            break MISSING_BLOCK_LABEL_144;
        }
        i = state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
        j = android.support.v4.content.ModernAsyncTask.Status.CONNECTED_LOCAL$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
        if (i == j)
        {
            break MISSING_BLOCK_LABEL_144;
        }
        Timer timer;
        connectTries = connectTries + 1;
        timer = failedConnectTimer;
        if (timer == null)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        timer.cancel();
        state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.CONNECTING$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
        failedConnectTimer = new Timer("Failed Connect");
        failedConnectTimer.schedule(new FailedConnectTask(), 3000L);
        client.connect();
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        Log.w("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("security exception on connectToService").toString());
        useStore();
          goto _L1
        obj;
        throw obj;
        Log.w("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("client not initialized.").toString());
        useStore();
          goto _L1
    }

    public final void createService()
    {
        if (client != null)
        {
            return;
        } else
        {
            client = new AnalyticsGmsCoreClient(ctx, this, this);
            connectToService();
            return;
        }
    }

    final void disconnectFromService()
    {
        this;
        JVM INSTR monitorenter ;
        if (client != null && state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.CONNECTED_SERVICE$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0)
        {
            state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.PENDING_DISCONNECT$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
            client.disconnect();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void dispatch()
    {
        switch (state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 - 1)
        {
        default:
            pendingDispatch = true;
            // fall through

        case 1: // '\001'
            return;

        case 2: // '\002'
            store.dispatch();
            break;
        }
        pendingDispatch = false;
    }

    public final void onConnected()
    {
        this;
        JVM INSTR monitorenter ;
        Timer timer = failedConnectTimer;
        if (timer == null)
        {
            break MISSING_BLOCK_LABEL_15;
        }
        timer.cancel();
        failedConnectTimer = null;
        connectTries = 0;
        state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.CONNECTED_SERVICE$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
        sendQueue();
        timer = disconnectCheckTimer;
        if (timer == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        timer.cancel();
        disconnectCheckTimer = null;
        disconnectCheckTimer = new Timer("disconnect check");
        disconnectCheckTimer.schedule(new DisconnectCheckTask(), idleTimeout);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void onConnectionFailed$514KOOBECHP6UQB45THMURJKCLN78BQ9DPQ6ARJK7CKLC___0(int i)
    {
        this;
        JVM INSTR monitorenter ;
        state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.PENDING_CONNECTION$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
        if (connectTries >= 2) goto _L2; else goto _L1
_L1:
        String s = (new StringBuilder("Service unavailable (code=")).append(i).append("), will retry.").toString();
        Log.w("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(s).toString());
        fireReconnectAttempt();
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        String s1 = (new StringBuilder("Service unavailable (code=")).append(i).append("), using local store.").toString();
        Log.w("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(s1).toString());
        useStore();
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public final void onDisconnected()
    {
        this;
        JVM INSTR monitorenter ;
        if (state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 != android.support.v4.content.ModernAsyncTask.Status.PENDING_DISCONNECT$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0) goto _L2; else goto _L1
_L1:
        clearAllTimers();
        state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.DISCONNECTED$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.PENDING_CONNECTION$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
        if (connectTries >= 2)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        fireReconnectAttempt();
          goto _L3
        Exception exception;
        exception;
        throw exception;
        useStore();
          goto _L3
    }

    public final void putHit(Map map, long l, String s, List list)
    {
        queue.add(new HitParams(map, l, s, list));
        sendQueue();
    }

    final void sendQueue()
    {
        this;
        JVM INSTR monitorenter ;
        if (Thread.currentThread().equals(thread.getThread())) goto _L2; else goto _L1
_L1:
        thread.getQueue().add(new _cls2());
_L8:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (!pendingClearHits) goto _L4; else goto _L3
_L3:
        queue.clear();
        state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 - 1;
        JVM INSTR tableswitch 1 2: default 351
    //                   1 222
    //                   2 204;
           goto _L5 _L6 _L7
_L5:
        pendingClearHits = true;
_L4:
        state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 - 1;
        JVM INSTR tableswitch 1 6: default 354
    //                   1 136
    //                   2 239
    //                   3 354
    //                   4 354
    //                   5 354
    //                   6 332;
           goto _L8 _L9 _L10 _L8 _L8 _L8 _L11
_L9:
        for (; !queue.isEmpty(); queue.poll())
        {
            HitParams hitparams = (HitParams)queue.peek();
            client.sendHit(hitparams.wireFormatParams, hitparams.hitTimeInMilliseconds, hitparams.path, hitparams.commands);
        }

          goto _L12
        Exception exception;
        exception;
        throw exception;
_L7:
        store.clearHits(0L);
        pendingClearHits = false;
          goto _L4
_L6:
        client.clearHits();
        pendingClearHits = false;
          goto _L4
_L10:
        HitParams hitparams1;
        for (; !queue.isEmpty(); store.putHit(hitparams1.wireFormatParams, hitparams1.hitTimeInMilliseconds, hitparams1.path, hitparams1.commands))
        {
            hitparams1 = (HitParams)queue.poll();
        }

        if (!pendingDispatch) goto _L8; else goto _L13
_L13:
        store.dispatch();
        pendingDispatch = false;
          goto _L8
_L12:
        lastRequestTime = clock.currentTimeMillis();
          goto _L8
_L11:
        if (queue.isEmpty()) goto _L8; else goto _L14
_L14:
        connectToService();
          goto _L8
    }

    final void useStore()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        int j;
        i = state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
        j = android.support.v4.content.ModernAsyncTask.Status.CONNECTED_LOCAL$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
        if (i != j) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        clearAllTimers();
        if (testStore == null)
        {
            break; /* Loop/switch isn't completed */
        }
        store = testStore;
_L4:
        state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.CONNECTED_LOCAL$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0;
        sendQueue();
        if (true) goto _L1; else goto _L3
        Exception exception;
        exception;
        throw exception;
_L3:
        if (GAServiceManager.instance == null)
        {
            GAServiceManager.instance = new GAServiceManager();
        }
        GAServiceManager gaservicemanager = GAServiceManager.instance;
        gaservicemanager.initialize(ctx, thread);
        store = gaservicemanager.getStore();
          goto _L4
    }

    private class _cls1
        implements Clock
    {

        public final long currentTimeMillis()
        {
            return System.currentTimeMillis();
        }

        _cls1()
        {
        }
    }


    private class ReconnectTask extends TimerTask
    {

        private final GAServiceProxy this$0;

        public final void run()
        {
            connectToService();
        }

        ReconnectTask()
        {
            this$0 = GAServiceProxy.this;
            super();
        }
    }


    private class FailedConnectTask extends TimerTask
    {

        private final GAServiceProxy this$0;

        public final void run()
        {
            if (state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.CONNECTING$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0)
            {
                useStore();
            }
        }

        FailedConnectTask()
        {
            this$0 = GAServiceProxy.this;
            super();
        }
    }


    private class DisconnectCheckTask extends TimerTask
    {

        private final GAServiceProxy this$0;

        public final void run()
        {
            if (state$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.CONNECTED_SERVICE$9HHMUR9FCTNMUPRCCKNM2RJ1DHSN8QB3ECNN8SJ1CDLMIRJ75TGMSP3IDTKM8BQ7859MASJMD5HMAK3IDTS7I923DTN6SPB3EH9N8OBKCKTG____0 && queue.isEmpty() && lastRequestTime + idleTimeout < clock.currentTimeMillis())
            {
                disconnectFromService();
                return;
            } else
            {
                disconnectCheckTimer.schedule(new DisconnectCheckTask(), idleTimeout);
                return;
            }
        }

        DisconnectCheckTask()
        {
            this$0 = GAServiceProxy.this;
            super();
        }
    }


    private class HitParams
    {

        public final List commands;
        public final long hitTimeInMilliseconds;
        public final String path;
        public final Map wireFormatParams;

        public HitParams(Map map, long l, String s, List list)
        {
            wireFormatParams = map;
            hitTimeInMilliseconds = l;
            path = s;
            commands = list;
        }
    }


    private class _cls2
        implements Runnable
    {

        private final GAServiceProxy this$0;

        public final void run()
        {
            sendQueue();
        }

        _cls2()
        {
            this$0 = GAServiceProxy.this;
            super();
        }
    }

}
