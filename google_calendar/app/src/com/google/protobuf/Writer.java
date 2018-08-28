// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.protobuf:
//            ByteString, Schema

interface Writer
{

    public abstract int fieldOrder$50KKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULRID5Q6ASH48PKMAR349TP68PBI7C______0();

    public abstract void writeBool(int i, boolean flag)
        throws IOException;

    public abstract void writeBoolList(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeBytes(int i, ByteString bytestring)
        throws IOException;

    public abstract void writeBytesList(int i, List list)
        throws IOException;

    public abstract void writeDouble(int i, double d)
        throws IOException;

    public abstract void writeDoubleList(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeEndGroup(int i)
        throws IOException;

    public abstract void writeEnum(int i, int j)
        throws IOException;

    public abstract void writeEnumList(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeFixed32(int i, int j)
        throws IOException;

    public abstract void writeFixed32List(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeFixed64(int i, long l)
        throws IOException;

    public abstract void writeFixed64List(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeFloat(int i, float f)
        throws IOException;

    public abstract void writeFloatList(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeGroup(int i, Object obj, Schema schema)
        throws IOException;

    public abstract void writeGroupList(int i, List list, Schema schema)
        throws IOException;

    public abstract void writeInt32(int i, int j)
        throws IOException;

    public abstract void writeInt32List(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeInt64(int i, long l)
        throws IOException;

    public abstract void writeInt64List(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeMap(int i, MapEntryLite.Metadata metadata, Map map)
        throws IOException;

    public abstract void writeMessage(int i, Object obj, Schema schema)
        throws IOException;

    public abstract void writeMessageList(int i, List list, Schema schema)
        throws IOException;

    public abstract void writeMessageSetItem(int i, Object obj)
        throws IOException;

    public abstract void writeSFixed32(int i, int j)
        throws IOException;

    public abstract void writeSFixed32List(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeSFixed64(int i, long l)
        throws IOException;

    public abstract void writeSFixed64List(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeSInt32(int i, int j)
        throws IOException;

    public abstract void writeSInt32List(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeSInt64(int i, long l)
        throws IOException;

    public abstract void writeSInt64List(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeStartGroup(int i)
        throws IOException;

    public abstract void writeString(int i, String s)
        throws IOException;

    public abstract void writeStringList(int i, List list)
        throws IOException;

    public abstract void writeUInt32(int i, int j)
        throws IOException;

    public abstract void writeUInt32List(int i, List list, boolean flag)
        throws IOException;

    public abstract void writeUInt64(int i, long l)
        throws IOException;

    public abstract void writeUInt64List(int i, List list, boolean flag)
        throws IOException;
}
