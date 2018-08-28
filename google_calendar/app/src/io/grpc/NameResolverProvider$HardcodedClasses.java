// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package io.grpc:
//            NameResolverProvider

static final class 
    implements Iterable
{

    public final Iterator iterator()
    {
        ArrayList arraylist = new ArrayList();
        try
        {
            arraylist.add(Class.forName("io.grpc.internal.DnsNameResolverProvider"));
        }
        catch (ClassNotFoundException classnotfoundexception) { }
        return arraylist.iterator();
    }

    ()
    {
    }
}
