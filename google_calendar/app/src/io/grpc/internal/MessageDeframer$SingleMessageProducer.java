// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.InputStream;

final class message
    implements message
{

    private InputStream message;

    public final InputStream next()
    {
        InputStream inputstream = message;
        message = null;
        return inputstream;
    }

    (InputStream inputstream)
    {
        message = inputstream;
    }
}
