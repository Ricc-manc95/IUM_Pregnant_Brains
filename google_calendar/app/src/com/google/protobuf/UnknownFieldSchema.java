// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;

// Referenced classes of package com.google.protobuf:
//            Reader, InvalidProtocolBufferException, ByteString, Writer

abstract class UnknownFieldSchema
{

    UnknownFieldSchema()
    {
    }

    abstract void addFixed32(Object obj, int i, int j);

    abstract void addFixed64(Object obj, int i, long l);

    abstract void addGroup(Object obj, int i, Object obj1);

    abstract void addLengthDelimited(Object obj, int i, ByteString bytestring);

    abstract void addVarint(Object obj, int i, long l);

    abstract Object getBuilderFromMessage(Object obj);

    abstract Object getFromMessage(Object obj);

    abstract int getSerializedSize(Object obj);

    abstract int getSerializedSizeAsMessageSet(Object obj);

    abstract void makeImmutable(Object obj);

    abstract Object merge(Object obj, Object obj1);

    final boolean mergeOneFieldFrom(Object obj, Reader reader)
        throws IOException
    {
        int i = reader.getTag();
        int j = i >>> 3;
        switch (i & 7)
        {
        default:
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");

        case 0: // '\0'
            addVarint(obj, j, reader.readInt64());
            return true;

        case 5: // '\005'
            addFixed32(obj, j, reader.readFixed32());
            return true;

        case 1: // '\001'
            addFixed64(obj, j, reader.readFixed64());
            return true;

        case 2: // '\002'
            addLengthDelimited(obj, j, reader.readBytes());
            return true;

        case 3: // '\003'
            Object obj1;
            for (obj1 = newBuilder(); reader.getFieldNumber() != 0x7fffffff && mergeOneFieldFrom(obj1, reader);) { }
            if ((4 | j << 3) != reader.getTag())
            {
                throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
            } else
            {
                addGroup(obj, j, toImmutable(obj1));
                return true;
            }

        case 4: // '\004'
            return false;
        }
    }

    abstract Object newBuilder();

    abstract void setBuilderToMessage(Object obj, Object obj1);

    abstract void setToMessage(Object obj, Object obj1);

    abstract boolean shouldDiscardUnknownFields$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONL4PB1CHIN4EP9B8______0();

    abstract Object toImmutable(Object obj);

    abstract void writeAsMessageSetTo(Object obj, Writer writer)
        throws IOException;

    abstract void writeTo(Object obj, Writer writer)
        throws IOException;
}
