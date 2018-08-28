// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.corp.android.volley.proto;

import com.android.volley.Request;
import com.google.common.util.concurrent.AbstractFuture;

// Referenced classes of package com.google.corp.android.volley.proto:
//            MessageLiteVolleyRequest

final class this._cls0
    implements Runnable
{

    private final MessageLiteVolleyRequest this$0;

    public final void run()
    {
        if (future.isCancelled())
        {
            cancel();
        }
    }

    ()
    {
        this$0 = MessageLiteVolleyRequest.this;
        super();
    }
}
