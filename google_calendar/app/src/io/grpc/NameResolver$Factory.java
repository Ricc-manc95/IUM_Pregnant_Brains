// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.net.URI;

// Referenced classes of package io.grpc:
//            Attributes, NameResolver

public abstract class 
{

    public static final  PARAMS_DEFAULT_PORT = new ("params-default-port");

    public abstract String getDefaultScheme();

    public abstract NameResolver newNameResolver(URI uri, Attributes attributes);


    public ()
    {
    }
}
