// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.flashmanagement.storagestats;

import android.content.Context;
import java.io.File;

public final class DataPartitionSize
{

    private final Context context;
    private File dataPartition;
    private final Object lock = new Object();

    public DataPartitionSize(Context context1)
    {
        context = context1.getApplicationContext();
    }

    public final File getDataPartition()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (dataPartition == null)
        {
            if (android.os.Build.VERSION.SDK_INT < 24)
            {
                break MISSING_BLOCK_LABEL_42;
            }
            dataPartition = context.getDataDir();
        }
_L1:
        File file = dataPartition;
        return file;
        dataPartition = context.getDatabasePath("dps-dummy").getParentFile().getParentFile();
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
