// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.auth;

import com.google.auth.RequestMetadataCallback;
import io.grpc.Status;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package io.grpc.auth:
//            GoogleAuthLibraryCallCredentials

final class val.applier
    implements RequestMetadataCallback
{

    private final GoogleAuthLibraryCallCredentials this$0;
    private final io.grpc.ls val$applier;

    public final void onFailure(Throwable throwable)
    {
        if (throwable instanceof IOException)
        {
            val$applier.l(Status.UNAVAILABLE.withDescription("Credentials failed to obtain metadata").withCause(throwable));
            return;
        } else
        {
            val$applier.l(Status.UNAUTHENTICATED.withDescription("Failed computing credential metadata").withCause(throwable));
            return;
        }
    }

    public final void onSuccess(Map map)
    {
        synchronized (GoogleAuthLibraryCallCredentials.this)
        {
            if (lastMetadata == null || lastMetadata != map)
            {
                lastHeaders = GoogleAuthLibraryCallCredentials.toHeaders(map);
                lastMetadata = map;
            }
            map = lastHeaders;
        }
        val$applier.ly(map);
        return;
        map;
        googleauthlibrarycallcredentials;
        JVM INSTR monitorexit ;
        try
        {
            throw map;
        }
        // Misplaced declaration of an exception variable
        catch (Map map)
        {
            val$applier.l(Status.UNAUTHENTICATED.withDescription("Failed to convert credential metadata").withCause(map));
        }
        return;
    }

    ()
    {
        this$0 = final_googleauthlibrarycallcredentials;
        val$applier = io.grpc.ls._cls1.val.applier.this;
        super();
    }
}
