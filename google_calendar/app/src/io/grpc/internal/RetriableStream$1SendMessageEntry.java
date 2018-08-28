// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.MethodDescriptor;

// Referenced classes of package io.grpc.internal:
//            RetriableStream, ClientStream

final class val.message
    implements val.message
{

    private final RetriableStream this$0;
    private final Object val$message;

    public final void runWith(val.message message1)
    {
        message1 = message1.message;
        MethodDescriptor methoddescriptor = method;
        Object obj = val$message;
        message1.writeMessage(methoddescriptor.requestMarshaller.(obj));
    }

    ()
    {
        this$0 = final_retriablestream;
        val$message = Object.this;
        super();
    }
}
