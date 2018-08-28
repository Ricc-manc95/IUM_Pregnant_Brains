// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


// Referenced classes of package io.grpc:
//            MethodDescriptor, CallOptions, ClientCall

public abstract class Channel
{

    public Channel()
    {
    }

    public abstract String authority();

    public abstract ClientCall newCall(MethodDescriptor methoddescriptor, CallOptions calloptions);
}
