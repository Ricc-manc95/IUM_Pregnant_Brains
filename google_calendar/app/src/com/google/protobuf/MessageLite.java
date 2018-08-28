// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package com.google.protobuf:
//            MessageLiteOrBuilder, Parser, ByteString, CodedOutputStream

public interface MessageLite
    extends MessageLiteOrBuilder
{

    public abstract Parser getParserForType();

    public abstract int getSerializedSize();

    public abstract Builder newBuilderForType();

    public abstract Builder toBuilder();

    public abstract byte[] toByteArray();

    public abstract ByteString toByteString();

    public abstract void writeTo(CodedOutputStream codedoutputstream)
        throws IOException;

    public abstract void writeTo(OutputStream outputstream)
        throws IOException;
}
