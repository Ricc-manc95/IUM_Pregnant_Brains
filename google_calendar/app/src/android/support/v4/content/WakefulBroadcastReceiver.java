// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.util.SparseArray;

public abstract class WakefulBroadcastReceiver extends BroadcastReceiver
{

    private static int mNextId = 1;
    private static final SparseArray sActiveWakeLocks = new SparseArray();

    public WakefulBroadcastReceiver()
    {
    }

    public static boolean completeWakefulIntent(Intent intent)
    {
        int i;
        i = intent.getIntExtra("android.support.content.wakelockid", 0);
        if (i == 0)
        {
            return false;
        }
        intent = sActiveWakeLocks;
        intent;
        JVM INSTR monitorenter ;
        android.os.PowerManager.WakeLock wakelock = (android.os.PowerManager.WakeLock)sActiveWakeLocks.get(i);
        if (wakelock == null)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        wakelock.release();
        sActiveWakeLocks.remove(i);
        intent;
        JVM INSTR monitorexit ;
        return true;
        Log.w("WakefulBroadcastReceiv.", (new StringBuilder("No active wake lock id #")).append(i).toString());
        intent;
        JVM INSTR monitorexit ;
        return true;
        Exception exception;
        exception;
        intent;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static ComponentName startWakefulService(Context context, Intent intent)
    {
        SparseArray sparsearray = sActiveWakeLocks;
        sparsearray;
        JVM INSTR monitorenter ;
        int i;
        int j;
        i = mNextId;
        j = mNextId + 1;
        mNextId = j;
        if (j > 0)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        mNextId = 1;
        intent.putExtra("android.support.content.wakelockid", i);
        intent = context.startService(intent);
        if (intent != null)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        sparsearray;
        JVM INSTR monitorexit ;
        return null;
        context = ((PowerManager)context.getSystemService("power")).newWakeLock(1, (new StringBuilder("androidx.core:wake:")).append(intent.flattenToShortString()).toString());
        context.setReferenceCounted(false);
        context.acquire(60000L);
        sActiveWakeLocks.put(i, context);
        sparsearray;
        JVM INSTR monitorexit ;
        return intent;
        context;
        sparsearray;
        JVM INSTR monitorexit ;
        throw context;
    }

}