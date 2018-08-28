// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon.external.service;

import android.content.Context;
import android.os.AsyncTask;
import android.os.RemoteException;
import com.android.mail.log.LogUtils;

// Referenced classes of package com.android.emailcommon.external.service:
//            ServiceProxy

final class this._cls1 extends AsyncTask
{

    private final  this$1;

    private final transient Void doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPFAPNMIP1R0()
    {
        task.();
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

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
