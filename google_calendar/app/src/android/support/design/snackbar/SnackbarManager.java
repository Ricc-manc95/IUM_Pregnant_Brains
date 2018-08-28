// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

final class SnackbarManager
{

    public static SnackbarManager snackbarManager;
    public SnackbarRecord currentSnackbar;
    public final Handler handler = new Handler(Looper.getMainLooper(), new _cls1());
    public final Object lock = new Object();
    public SnackbarRecord nextSnackbar;

    SnackbarManager()
    {
    }

    final boolean cancelSnackbarLocked(SnackbarRecord snackbarrecord, int i)
    {
        Callback callback = (Callback)snackbarrecord.callback.get();
        if (callback != null)
        {
            handler.removeCallbacksAndMessages(snackbarrecord);
            callback.dismiss(i);
            return true;
        } else
        {
            return false;
        }
    }

    public final void dismiss(Callback callback, int i)
    {
        boolean flag1 = true;
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (currentSnackbar == null) goto _L2; else goto _L1
_L1:
        SnackbarRecord snackbarrecord = currentSnackbar;
        if (callback == null) goto _L4; else goto _L3
_L3:
        if (snackbarrecord.callback.get() != callback) goto _L4; else goto _L5
_L5:
        boolean flag = true;
          goto _L6
_L19:
        if (!flag) goto _L8; else goto _L7
_L7:
        cancelSnackbarLocked(currentSnackbar, i);
_L16:
        obj;
        JVM INSTR monitorexit ;
        return;
_L8:
        if (nextSnackbar == null) goto _L10; else goto _L9
_L9:
        snackbarrecord = nextSnackbar;
        if (callback == null) goto _L12; else goto _L11
_L11:
        if (snackbarrecord.callback.get() != callback) goto _L12; else goto _L13
_L13:
        flag = true;
          goto _L14
_L17:
        if (!flag) goto _L16; else goto _L15
_L15:
        cancelSnackbarLocked(nextSnackbar, i);
          goto _L16
        callback;
        obj;
        JVM INSTR monitorexit ;
        throw callback;
_L12:
        flag = false;
          goto _L14
_L10:
        flag = false;
          goto _L17
_L6:
        if (!flag) goto _L2; else goto _L18
_L18:
        flag = true;
          goto _L19
_L4:
        flag = false;
          goto _L6
_L2:
        flag = false;
          goto _L19
_L14:
        if (!flag) goto _L10; else goto _L20
_L20:
        flag = flag1;
          goto _L17
    }

    public final boolean isCurrentOrNext(Callback callback)
    {
        boolean flag1 = false;
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (currentSnackbar == null) goto _L2; else goto _L1
_L1:
        SnackbarRecord snackbarrecord = currentSnackbar;
        if (callback == null) goto _L4; else goto _L3
_L3:
        if (snackbarrecord.callback.get() != callback) goto _L4; else goto _L5
_L5:
        boolean flag = true;
          goto _L6
_L14:
        if (flag) goto _L8; else goto _L7
_L7:
        if (nextSnackbar == null)
        {
            break MISSING_BLOCK_LABEL_139;
        }
        snackbarrecord = nextSnackbar;
        if (callback == null) goto _L10; else goto _L9
_L9:
        if (snackbarrecord.callback.get() != callback) goto _L10; else goto _L11
_L11:
        flag = true;
          goto _L12
_L15:
        obj;
        JVM INSTR monitorexit ;
        return flag1;
        callback;
        obj;
        JVM INSTR monitorexit ;
        throw callback;
_L6:
        if (!flag) goto _L2; else goto _L13
_L13:
        flag = true;
          goto _L14
_L12:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_139;
        }
        flag = true;
_L16:
        if (!flag) goto _L15; else goto _L8
_L8:
        flag1 = true;
          goto _L15
_L4:
        flag = false;
          goto _L6
_L2:
        flag = false;
          goto _L14
_L10:
        flag = false;
          goto _L12
        flag = false;
          goto _L16
    }

    public final void pauseTimeout(Callback callback)
    {
        boolean flag1 = true;
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (currentSnackbar == null) goto _L2; else goto _L1
_L1:
        SnackbarRecord snackbarrecord = currentSnackbar;
        if (callback == null) goto _L4; else goto _L3
_L3:
        if (snackbarrecord.callback.get() != callback) goto _L4; else goto _L5
_L5:
        boolean flag = true;
          goto _L6
_L8:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        if (!currentSnackbar.paused)
        {
            currentSnackbar.paused = true;
            handler.removeCallbacksAndMessages(currentSnackbar);
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        callback;
        obj;
        JVM INSTR monitorexit ;
        throw callback;
_L6:
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        continue; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L6; else goto _L2
_L2:
        flag = false;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void restoreTimeoutIfPaused(Callback callback)
    {
        boolean flag1 = true;
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (currentSnackbar == null) goto _L2; else goto _L1
_L1:
        SnackbarRecord snackbarrecord = currentSnackbar;
        if (callback == null) goto _L4; else goto _L3
_L3:
        if (snackbarrecord.callback.get() != callback) goto _L4; else goto _L5
_L5:
        boolean flag = true;
          goto _L6
_L8:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        if (currentSnackbar.paused)
        {
            currentSnackbar.paused = false;
            scheduleTimeoutLocked(currentSnackbar);
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        callback;
        obj;
        JVM INSTR monitorexit ;
        throw callback;
_L6:
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        continue; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L6; else goto _L2
_L2:
        flag = false;
        if (true) goto _L8; else goto _L7
_L7:
    }

    final void scheduleTimeoutLocked(SnackbarRecord snackbarrecord)
    {
        int i;
        if (snackbarrecord.duration == -2)
        {
            return;
        }
        i = 2750;
        if (snackbarrecord.duration <= 0) goto _L2; else goto _L1
_L1:
        i = snackbarrecord.duration;
_L4:
        handler.removeCallbacksAndMessages(snackbarrecord);
        handler.sendMessageDelayed(Message.obtain(handler, 0, snackbarrecord), i);
        return;
_L2:
        if (snackbarrecord.duration == -1)
        {
            i = 1500;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    final void showNextSnackbarLocked()
    {
label0:
        {
            if (nextSnackbar != null)
            {
                currentSnackbar = nextSnackbar;
                nextSnackbar = null;
                Callback callback = (Callback)currentSnackbar.callback.get();
                if (callback == null)
                {
                    break label0;
                }
                callback.show();
            }
            return;
        }
        currentSnackbar = null;
    }

    private class _cls1
        implements android.os.Handler.Callback
    {

        private final SnackbarManager this$0;

        public final boolean handleMessage(Message message)
        {
            SnackbarManager snackbarmanager;
            switch (message.what)
            {
            default:
                return false;

            case 0: // '\0'
                snackbarmanager = SnackbarManager.this;
                break;
            }
            SnackbarRecord snackbarrecord = (SnackbarRecord)message.obj;
            synchronized (snackbarmanager.lock)
            {
                if (snackbarmanager.currentSnackbar == snackbarrecord || snackbarmanager.nextSnackbar == snackbarrecord)
                {
                    snackbarmanager.cancelSnackbarLocked(snackbarrecord, 2);
                }
            }
            return true;
            exception;
            message;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls1()
        {
            this$0 = SnackbarManager.this;
            super();
        }
    }


    private class SnackbarRecord
    {

        public final WeakReference callback;
        public int duration;
        public boolean paused;

        SnackbarRecord(int i, Callback callback1)
        {
            callback = new WeakReference(callback1);
            duration = i;
        }
    }


    private class Callback
    {

        public abstract void dismiss(int i);

        public abstract void show();
    }

}
