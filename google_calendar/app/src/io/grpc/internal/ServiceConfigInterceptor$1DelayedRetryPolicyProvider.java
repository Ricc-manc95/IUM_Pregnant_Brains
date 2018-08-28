// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.MethodDescriptor;

// Referenced classes of package io.grpc.internal:
//            ServiceConfigInterceptor, RetryPolicy

final class val.method
    implements val.method
{

    private final ServiceConfigInterceptor this$0;
    private final MethodDescriptor val$method;

    public final RetryPolicy get()
    {
        if (!nameResolveComplete)
        {
            return RetryPolicy.DEFAULT;
        }
        val.method method1 = getMethodInfo(val$method);
        if (method1 == null || method1.method == null)
        {
            return RetryPolicy.DEFAULT;
        } else
        {
            return method1.method;
        }
    }

    ()
    {
        this$0 = final_serviceconfiginterceptor;
        val$method = MethodDescriptor.this;
        super();
    }
}
