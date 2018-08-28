// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.google.android.gms.internal:
//            zzzv

final class  extends Handler
{

    private final zzzv zzaML;

    public final void handleMessage(Message message)
    {
        boolean flag = true;
        if (message.what != 1)
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        Object obj = zzaML;
        message = (zzaML)message.obj;
        obj = ((zzzv) (obj)).mListener;
        if (obj == null)
        {
            message.zzxk();
            return;
        }
        try
        {
            message.zzw(obj);
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            message.zzxk();
            throw runtimeexception;
        }
    }
}
