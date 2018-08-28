// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;

import java.security.PrivilegedExceptionAction;
import javax.net.ssl.SSLEngine;

final class tion
    implements PrivilegedExceptionAction
{

    public final Object run()
        throws Exception
    {
        return javax/net/ssl/SSLEngine.getMethod("getApplicationProtocol", new Class[0]);
    }

    tion()
    {
    }
}
