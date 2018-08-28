// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.auth;

import java.net.URI;

// Referenced classes of package com.google.auth:
//            Credentials, RequestMetadataCallback

final class aCallback
    implements Runnable
{

    private final Credentials this$0;
    private final RequestMetadataCallback val$callback;
    private final URI val$uri;

    public final void run()
    {
        Object obj1 = Credentials.this;
        Object obj = val$uri;
        obj = val$callback;
        try
        {
            obj1 = ((Credentials) (obj1)).getRequestMetadata$5166KOBMC4NMSPBK5TAL4I9R5566KOBMC4NNAT39DGNKQOBG7C______0();
        }
        catch (Throwable throwable)
        {
            ((RequestMetadataCallback) (obj)).onFailure(throwable);
            return;
        }
        ((RequestMetadataCallback) (obj)).onSuccess(((java.util.Map) (obj1)));
    }

    aCallback()
    {
        this$0 = final_credentials;
        val$uri = uri1;
        val$callback = RequestMetadataCallback.this;
        super();
    }
}
