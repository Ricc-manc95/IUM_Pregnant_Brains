// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal.framed;

import java.io.Closeable;
import java.io.IOException;

public interface FrameReader
    extends Closeable
{

    public abstract boolean nextFrame(Handler handler)
        throws IOException;
}
