// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Attributes;

// Referenced classes of package io.grpc.internal:
//            ManagedClientTransport

public interface ConnectionClientTransport
    extends ManagedClientTransport
{

    public abstract Attributes getAttributes();
}
