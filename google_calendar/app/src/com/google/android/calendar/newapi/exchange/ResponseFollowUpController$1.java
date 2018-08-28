// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.exchange;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.google.android.calendar.newapi.exchange:
//            ResponseFollowUpController

final class this._cls0 extends Handler
{

    private final ResponseFollowUpController this$0;

    public final void handleMessage(Message message)
    {
        saveDelayedResponses();
    }

    ()
    {
        this$0 = ResponseFollowUpController.this;
        super();
    }
}
