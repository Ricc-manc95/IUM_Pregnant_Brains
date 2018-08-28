// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Supplier;
import java.net.ProxySelector;

final class 
    implements Supplier
{

    public final Object get()
    {
        return ProxySelector.getDefault();
    }

    ()
    {
    }
}
