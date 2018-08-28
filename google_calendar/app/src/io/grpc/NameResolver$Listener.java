// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.List;

// Referenced classes of package io.grpc:
//            Attributes, Status

public interface 
{

    public abstract void onAddresses(List list, Attributes attributes);

    public abstract void onError(Status status);
}
