// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

// Referenced classes of package com.firebase.jobdispatcher:
//            JobCallback

final class GooglePlayMessengerCallback
    implements JobCallback
{

    private final Messenger messenger;
    private final String tag;

    GooglePlayMessengerCallback(Messenger messenger1, String s)
    {
        messenger = messenger1;
        tag = s;
    }

    public final void jobFinished(int i)
    {
        try
        {
            Messenger messenger1 = messenger;
            Message message = Message.obtain();
            message.what = 3;
            message.arg1 = i;
            Bundle bundle = new Bundle();
            bundle.putString("tag", tag);
            message.setData(bundle);
            messenger1.send(message);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeException(remoteexception);
        }
    }
}
