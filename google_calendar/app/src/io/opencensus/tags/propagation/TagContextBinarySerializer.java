// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags.propagation;

import io.opencensus.tags.TagContext;

// Referenced classes of package io.opencensus.tags.propagation:
//            TagContextDeserializationException, TagContextSerializationException

public abstract class TagContextBinarySerializer
{

    public TagContextBinarySerializer()
    {
    }

    public abstract TagContext fromByteArray(byte abyte0[])
        throws TagContextDeserializationException;

    public abstract byte[] toByteArray(TagContext tagcontext)
        throws TagContextSerializationException;
}
