// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            MethodDescriptor, CallOptions, Channel, ClientCall

public interface ClientInterceptor
{

    public abstract ClientCall interceptCall(MethodDescriptor methoddescriptor, CallOptions calloptions, Channel channel);
}
