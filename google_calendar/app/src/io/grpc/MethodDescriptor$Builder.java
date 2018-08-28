// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            MethodDescriptor

public final class pe
{

    public String fullMethodName;
    public boolean idempotent;
    public er requestMarshaller;
    public er responseMarshaller;
    public boolean safe;
    public boolean sampledToLocalTracing;
    public Object schemaDescriptor;
    public pe type;

    public final MethodDescriptor build()
    {
        return new MethodDescriptor(type, fullMethodName, requestMarshaller, responseMarshaller, schemaDescriptor, idempotent, safe, sampledToLocalTracing);
    }

    public pe()
    {
    }
}
