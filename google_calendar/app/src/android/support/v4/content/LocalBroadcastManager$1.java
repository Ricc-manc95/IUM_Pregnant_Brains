// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package android.support.v4.content:
//            LocalBroadcastManager

final class this._cls0 extends Handler
{

    private final LocalBroadcastManager this$0;

    public final void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            super.handleMessage(message);
            return;

        case 1: // '\001'
            executePendingBroadcasts();
            break;
        }
    }

    (Looper looper)
    {
        this$0 = LocalBroadcastManager.this;
        super(looper);
    }
}
