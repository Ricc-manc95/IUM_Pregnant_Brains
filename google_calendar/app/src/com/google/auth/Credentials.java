// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.auth;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.auth:
//            RequestMetadataCallback

public abstract class Credentials
    implements Serializable
{

    public static final long serialVersionUID = 0xb38a2d78c3d9081L;

    public Credentials()
    {
    }

    public void getRequestMetadata(final URI uri, Executor executor, final RequestMetadataCallback callback)
    {
        executor.execute(new _cls1());
    }

    public abstract Map getRequestMetadata$5166KOBMC4NMSPBK5TAL4I9R5566KOBMC4NNAT39DGNKQOBG7C______0()
        throws IOException;

    private class _cls1
        implements Runnable
    {

        private final Credentials this$0;
        private final RequestMetadataCallback val$callback;
        private final URI val$uri;

        public final void run()
        {
            Object obj1 = Credentials.this;
            Object obj = uri;
            obj = callback;
            try
            {
                obj1 = ((Credentials) (obj1)).getRequestMetadata$5166KOBMC4NMSPBK5TAL4I9R5566KOBMC4NNAT39DGNKQOBG7C______0();
            }
            catch (Throwable throwable)
            {
                ((RequestMetadataCallback) (obj)).onFailure(throwable);
                return;
            }
            ((RequestMetadataCallback) (obj)).onSuccess(((Map) (obj1)));
        }

        _cls1()
        {
            this$0 = Credentials.this;
            uri = uri1;
            callback = requestmetadatacallback;
            super();
        }
    }

}
