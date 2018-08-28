// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.gms.internal:
//            zzys

public final class <init> extends Handler
{

    public final void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            int i = message.what;
            Log.wtf("BasePendingResult", (new StringBuilder(45)).append("Don't know how to handle message: ").append(i).toString(), new Exception());
            return;

        case 1: // '\001'
            Object obj = (Pair)message.obj;
            message = (ResultCallback)((Pair) (obj)).first;
            obj = (Result)((Pair) (obj)).second;
            try
            {
                message.onResult(((Result) (obj)));
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Message message)
            {
                zzys.zzd(((Result) (obj)));
            }
            throw message;

        case 2: // '\002'
            ((zzys)message.obj).zzN(Status.zzaJw);
            return;
        }
    }

    public allback()
    {
        this(Looper.getMainLooper());
    }

    public <init>(Looper looper)
    {
        super(looper);
    }
}
