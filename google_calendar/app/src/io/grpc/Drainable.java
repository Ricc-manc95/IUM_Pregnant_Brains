// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.io.IOException;
import java.io.OutputStream;

public interface Drainable
{

    public abstract int drainTo(OutputStream outputstream)
        throws IOException;
}
