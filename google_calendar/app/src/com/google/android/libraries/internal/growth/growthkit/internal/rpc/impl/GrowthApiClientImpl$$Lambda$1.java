// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.common.base.Function;
import io.grpc.CallOptions;
import io.grpc.auth.GoogleAuthLibraryCallCredentials;
import io.grpc.stub.AbstractStub;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl:
//            GrowthApiClientImpl

final class arg._cls2
    implements Function
{

    private final GrowthApiClientImpl arg$1;
    private final com.google.internal.identity.growth.v1.ServiceFutureStub arg$2;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj1 = arg$2;
        obj = new GoogleAuthLibraryCallCredentials(new OAuth2Credentials(new AccessToken((String)obj, null)));
        io.grpc.Channel channel = ((AbstractStub) (obj1)).channel;
        CallOptions calloptions = new CallOptions(((AbstractStub) (obj1)).callOptions);
        calloptions.credentials = ((io.grpc.CallCredentials) (obj));
        return (com.google.internal.identity.growth.v1.ServiceFutureStub)((AbstractStub) (obj1)).build(channel, calloptions);
    }

    (GrowthApiClientImpl growthapiclientimpl, com.google.internal.identity.growth.v1.ServiceFutureStub servicefuturestub)
    {
        arg$1 = growthapiclientimpl;
        arg$2 = servicefuturestub;
    }
}
