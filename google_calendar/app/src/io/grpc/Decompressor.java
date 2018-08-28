// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.io.IOException;
import java.io.InputStream;

public interface Decompressor
{

    public abstract InputStream decompress(InputStream inputstream)
        throws IOException;

    public abstract String getMessageEncoding();
}
