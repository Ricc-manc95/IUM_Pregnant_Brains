// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.external.service;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Debug;
import android.os.IBinder;
import com.android.mail.utils.ThreadUtils;

public abstract class ServiceProxy
{

    public final ServiceConnection connection = new ProxyConnection();
    public final Context context;
    private final Intent intent;
    public final String tag = getClass().getSimpleName();
    public ProxyTask task;
    public boolean taskCompleted;
    private boolean taskSet;
    private int timeout;

    public ServiceProxy(Context context1, Intent intent1)
    {
        timeout = 45;
        taskSet = false;
        taskCompleted = false;
        context = context1;
        intent = intent1;
        if (Debug.isDebuggerConnected())
        {
            timeout = timeout << 2;
        }
    }

    public abstract void onConnected(IBinder ibinder);

    protected final boolean setTask(ProxyTask proxytask, String s)
        throws IllegalStateException
    {
        if (taskSet)
        {
            throw new IllegalStateException("Cannot call setTask twice on the same ServiceProxy.");
        } else
        {
            taskSet = true;
            task = proxytask;
            System.currentTimeMillis();
            return context.bindService(intent, connection, 1);
        }
    }

    protected final void waitForCompletion()
    {
        ThreadUtils.throwExceptionIfUiThread();
        ServiceConnection serviceconnection = connection;
        serviceconnection;
        JVM INSTR monitorenter ;
        System.currentTimeMillis();
        Exception exception;
        try
        {
            if (!taskCompleted)
            {
                connection.wait((long)timeout * 1000L);
            }
        }
        catch (InterruptedException interruptedexception) { }
        return;
        exception;
        serviceconnection;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private class ProxyConnection
        implements ServiceConnection
    {

        public final ServiceProxy this$0;

        public final void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            onConnected(ibinder);
            class _cls1 extends AsyncTask
            {

                private final ProxyConnection this$1;

                private final transient Void doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPFAPNMIP1R0()
                {
                    task.run();
                    try
                    {
                        context.unbindService(connection);
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj)
                    {
                        LogUtils.e(tag, ((Throwable) (obj)), "RuntimeException when trying to unbind from service", new Object[0]);
                    }
_L2:
                    synchronized (connection)
                    {
                        taskCompleted = true;
                        connection.notify();
                    }
                    return null;
                    obj;
                    LogUtils.e(tag, ((Throwable) (obj)), "RemoteException thrown running mTask!", new Object[0]);
                    try
                    {
                        context.unbindService(connection);
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj)
                    {
                        LogUtils.e(tag, ((Throwable) (obj)), "RuntimeException when trying to unbind from service", new Object[0]);
                    }
                    if (true) goto _L2; else goto _L1
_L1:
                    obj;
                    try
                    {
                        context.unbindService(connection);
                    }
                    catch (RuntimeException runtimeexception)
                    {
                        LogUtils.e(tag, runtimeexception, "RuntimeException when trying to unbind from service", new Object[0]);
                    }
                    throw obj;
                    exception;
                    obj;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                protected final volatile Object doInBackground(Object aobj[])
                {
                    return doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPFAPNMIP1R0();
                }

                _cls1()
                {
                    this$1 = ProxyConnection.this;
                    super();
                }

                private class ProxyTask
                {

                    public abstract void run()
                        throws RemoteException;
                }

            }

            (new _cls1()).execute(new Void[0]);
        }

        public final void onServiceDisconnected(ComponentName componentname)
        {
        }

        ProxyConnection()
        {
            this$0 = ServiceProxy.this;
            super();
        }
    }

}
