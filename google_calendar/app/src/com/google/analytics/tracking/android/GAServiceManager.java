// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

// Referenced classes of package com.google.analytics.tracking.android:
//            ServiceManager, GAUsage, AnalyticsThread, PersistentAnalyticsStore, 
//            GANetworkReceiver, AnalyticsStoreStateListener, AnalyticsStore

public final class GAServiceManager
    implements ServiceManager
{

    public static final Object MSG_OBJECT = new Object();
    public static GAServiceManager instance;
    public boolean connected;
    private Context ctx;
    public int dispatchPeriodInSeconds;
    public Handler handler;
    private boolean listenForNetwork;
    private AnalyticsStoreStateListener listener;
    private GANetworkReceiver networkReceiver;
    private boolean pendingDispatch;
    private AnalyticsStore store;
    public boolean storeIsEmpty;
    private volatile AnalyticsThread thread;

    GAServiceManager()
    {
        dispatchPeriodInSeconds = 1800;
        pendingDispatch = true;
        connected = true;
        listenForNetwork = true;
        listener = new _cls1();
        storeIsEmpty = false;
    }

    public final void dispatch()
    {
        this;
        JVM INSTR monitorenter ;
        if (thread != null) goto _L2; else goto _L1
_L1:
        Log.w("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("dispatch call queued.  Need to call GAServiceManager.getInstance().initialize().").toString());
        pendingDispatch = true;
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        GAUsage.INSTANCE.setUsage(GAUsage.Field.DISPATCH);
        thread.dispatch();
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    final AnalyticsStore getStore()
    {
        this;
        JVM INSTR monitorenter ;
        if (store != null)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        if (ctx == null)
        {
            throw new IllegalStateException("Cant get a store unless we have a context");
        }
        break MISSING_BLOCK_LABEL_31;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        store = new PersistentAnalyticsStore(listener, ctx);
        AnalyticsStore analyticsstore;
        if (handler == null)
        {
            handler = new Handler(ctx.getMainLooper(), new _cls2());
            if (dispatchPeriodInSeconds > 0)
            {
                handler.sendMessageDelayed(handler.obtainMessage(1, MSG_OBJECT), dispatchPeriodInSeconds * 1000);
            }
        }
        if (networkReceiver == null && listenForNetwork)
        {
            networkReceiver = new GANetworkReceiver(this);
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            ctx.registerReceiver(networkReceiver, intentfilter);
        }
        analyticsstore = store;
        this;
        JVM INSTR monitorexit ;
        return analyticsstore;
    }

    final void initialize(Context context, AnalyticsThread analyticsthread)
    {
        this;
        JVM INSTR monitorenter ;
        Context context1 = ctx;
        if (context1 == null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        ctx = context.getApplicationContext();
        if (thread == null)
        {
            thread = analyticsthread;
            if (pendingDispatch)
            {
                analyticsthread.dispatch();
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
        context;
        throw context;
    }

    public final void updateConnectivityStatus(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        updatePowerSaveMode(storeIsEmpty, flag);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    final void updatePowerSaveMode(boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        if (storeIsEmpty != flag) goto _L2; else goto _L1
_L1:
        boolean flag2 = connected;
        if (flag2 != flag1) goto _L2; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (!flag && flag1)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        if (dispatchPeriodInSeconds > 0)
        {
            handler.removeMessages(1, MSG_OBJECT);
        }
        if (flag || !flag1)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        if (dispatchPeriodInSeconds > 0)
        {
            handler.sendMessageDelayed(handler.obtainMessage(1, MSG_OBJECT), dispatchPeriodInSeconds * 1000);
        }
        StringBuilder stringbuilder = new StringBuilder("PowerSaveMode ");
        Object obj;
        if (!flag && flag1)
        {
            obj = "terminated.";
        } else
        {
            obj = "initiated.";
        }
        stringbuilder.append(((String) (obj))).toString();
        storeIsEmpty = flag;
        connected = flag1;
        if (true) goto _L3; else goto _L4
_L4:
        obj;
        throw obj;
    }


    private class _cls1
        implements AnalyticsStoreStateListener
    {

        private final GAServiceManager this$0;

        public final void reportStoreIsEmpty(boolean flag)
        {
            updatePowerSaveMode(flag, connected);
        }

        _cls1()
        {
            this$0 = GAServiceManager.this;
            super();
        }
    }


    private class _cls2
        implements android.os.Handler.Callback
    {

        private final GAServiceManager this$0;

        public final boolean handleMessage(Message message)
        {
            if (1 == message.what && GAServiceManager.MSG_OBJECT.equals(message.obj))
            {
                GAUsage.INSTANCE.setDisableUsage(true);
                dispatch();
                GAUsage.INSTANCE.setDisableUsage(false);
                if (dispatchPeriodInSeconds > 0 && !storeIsEmpty)
                {
                    handler.sendMessageDelayed(handler.obtainMessage(1, GAServiceManager.MSG_OBJECT), dispatchPeriodInSeconds * 1000);
                }
            }
            return true;
        }

        _cls2()
        {
            this$0 = GAServiceManager.this;
            super();
        }
    }

}
