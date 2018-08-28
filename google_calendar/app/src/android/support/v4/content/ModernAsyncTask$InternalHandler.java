// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.support.v4.content:
//            ModernAsyncTask

static final class  extends Handler
{

    public final void handleMessage(Message message)
    {
        Object obj = ()message.obj;
        switch (message.what)
        {
        default:
            return;

        case 1: // '\001'
            message = (() (obj)).mTask;
            obj = ((mTask) (obj)).mData[0];
            if (((ModernAsyncTask) (message)).mCancelled.get())
            {
                message.onCancelled(obj);
            } else
            {
                message.onPostExecute(obj);
            }
            message.mStatus$9HGMSP3IDTKM8BRJELO70RRIEGNNCD1FCDNMST35DPQ2UJBFCHIN4RI1EDSMSOQKC5PMM92JEHGN8TBJ7C______0 = _fld9HGMSP3IDTKM8BRJELO70RRIEGNNCD1FCDNMST35DPQ2UJBFCHIN4RI1EDSMSOQKC5PMM92JEHGN8TBJ7C______0;
            return;

        case 2: // '\002'
            ModernAsyncTask.onProgressUpdate$51DKOQJ1EPGIUR31DPJIUJR2D9IM6T1R55B0____0();
            return;
        }
    }

    ()
    {
        super(Looper.getMainLooper());
    }
}
