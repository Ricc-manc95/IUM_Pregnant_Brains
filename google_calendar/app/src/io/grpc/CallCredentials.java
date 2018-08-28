// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.concurrent.Executor;

// Referenced classes of package io.grpc:
//            MethodDescriptor, Attributes

public interface CallCredentials
{

    public static final Attributes.Key ATTR_AUTHORITY = new Attributes.Key("io.grpc.CallCredentials.authority");
    public static final Attributes.Key ATTR_SECURITY_LEVEL = new Attributes.Key("io.grpc.CallCredentials.securityLevel");

    public abstract void applyRequestMetadata(MethodDescriptor methoddescriptor, Attributes attributes, Executor executor, MetadataApplier metadataapplier);

}
