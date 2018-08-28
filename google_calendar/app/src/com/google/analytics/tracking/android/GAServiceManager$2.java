// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.google.analytics.tracking.android:
//            GAServiceManager, GAUsage

final class this._cls0
    implements android.os.android.GAServiceManager._cls2
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

    ()
    {
        this$0 = GAServiceManager.this;
        super();
    }
}
