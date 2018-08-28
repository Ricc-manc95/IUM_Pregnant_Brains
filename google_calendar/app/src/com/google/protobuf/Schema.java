// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;

// Referenced classes of package com.google.protobuf:
//            Reader, ExtensionRegistryLite, Writer

public interface Schema
{

    public abstract boolean equals(Object obj, Object obj1);

    public abstract int getSerializedSize(Object obj);

    public abstract int hashCode(Object obj);

    public abstract boolean isInitialized(Object obj);

    public abstract void makeImmutable(Object obj);

    public abstract void mergeFrom(Object obj, Reader reader, ExtensionRegistryLite extensionregistrylite)
        throws IOException;

    public abstract void mergeFrom(Object obj, Object obj1);

    public abstract void mergeFrom(Object obj, byte abyte0[], int i, int j, ArrayDecoders.Registers registers)
        throws IOException;

    public abstract Object newInstance();

    public abstract void writeTo(Object obj, Writer writer)
        throws IOException;
}
