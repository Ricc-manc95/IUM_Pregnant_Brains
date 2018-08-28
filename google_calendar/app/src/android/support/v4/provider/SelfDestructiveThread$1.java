// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

// Referenced classes of package android.support.v4.provider:
//            SelfDestructiveThread

final class this._cls0
    implements android.os.tructiveThread._cls1
{

    private final SelfDestructiveThread this$0;

    public final boolean handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return true;

        case 1: // '\001'
            SelfDestructiveThread selfdestructivethread = SelfDestructiveThread.this;
            ((Runnable)message.obj).run();
            synchronized (selfdestructivethread.mLock)
            {
                selfdestructivethread.mHandler.removeMessages(0);
                selfdestructivethread.mHandler.sendMessageDelayed(selfdestructivethread.mHandler.obtainMessage(0), selfdestructivethread.mDestructAfterMillisec);
            }
            return true;

        case 0: // '\0'
            obj = SelfDestructiveThread.this;
            break;
        }
        break MISSING_BLOCK_LABEL_97;
        exception;
        message;
        JVM INSTR monitorexit ;
        throw exception;
        synchronized (((SelfDestructiveThread) (obj)).mLock)
        {
            if (!((SelfDestructiveThread) (obj)).mHandler.hasMessages(1))
            {
                break MISSING_BLOCK_LABEL_124;
            }
        }
        return true;
        obj;
        message;
        JVM INSTR monitorexit ;
        throw obj;
        ((SelfDestructiveThread) (obj)).mThread.quit();
        obj.mThread = null;
        obj.mHandler = null;
        message;
        JVM INSTR monitorexit ;
        return true;
    }

    _cls9()
    {
        this$0 = SelfDestructiveThread.this;
        super();
    }
}
