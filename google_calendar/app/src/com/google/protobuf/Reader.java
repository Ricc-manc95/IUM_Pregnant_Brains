// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.protobuf:
//            ByteString, ExtensionRegistryLite, Schema

interface Reader
{

    public abstract int getFieldNumber()
        throws IOException;

    public abstract int getTag();

    public abstract boolean readBool()
        throws IOException;

    public abstract void readBoolList(List list)
        throws IOException;

    public abstract ByteString readBytes()
        throws IOException;

    public abstract void readBytesList(List list)
        throws IOException;

    public abstract double readDouble()
        throws IOException;

    public abstract void readDoubleList(List list)
        throws IOException;

    public abstract int readEnum()
        throws IOException;

    public abstract void readEnumList(List list)
        throws IOException;

    public abstract int readFixed32()
        throws IOException;

    public abstract void readFixed32List(List list)
        throws IOException;

    public abstract long readFixed64()
        throws IOException;

    public abstract void readFixed64List(List list)
        throws IOException;

    public abstract float readFloat()
        throws IOException;

    public abstract void readFloatList(List list)
        throws IOException;

    public abstract Object readGroup(Class class1, ExtensionRegistryLite extensionregistrylite)
        throws IOException;

    public abstract Object readGroupBySchemaWithCheck(Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException;

    public abstract void readGroupList(List list, Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException;

    public abstract int readInt32()
        throws IOException;

    public abstract void readInt32List(List list)
        throws IOException;

    public abstract long readInt64()
        throws IOException;

    public abstract void readInt64List(List list)
        throws IOException;

    public abstract void readMap(Map map, MapEntryLite.Metadata metadata, ExtensionRegistryLite extensionregistrylite)
        throws IOException;

    public abstract Object readMessage(Class class1, ExtensionRegistryLite extensionregistrylite)
        throws IOException;

    public abstract Object readMessageBySchemaWithCheck(Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException;

    public abstract void readMessageList(List list, Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException;

    public abstract int readSFixed32()
        throws IOException;

    public abstract void readSFixed32List(List list)
        throws IOException;

    public abstract long readSFixed64()
        throws IOException;

    public abstract void readSFixed64List(List list)
        throws IOException;

    public abstract int readSInt32()
        throws IOException;

    public abstract void readSInt32List(List list)
        throws IOException;

    public abstract long readSInt64()
        throws IOException;

    public abstract void readSInt64List(List list)
        throws IOException;

    public abstract String readString()
        throws IOException;

    public abstract void readStringList(List list)
        throws IOException;

    public abstract void readStringListRequireUtf8(List list)
        throws IOException;

    public abstract String readStringRequireUtf8()
        throws IOException;

    public abstract int readUInt32()
        throws IOException;

    public abstract void readUInt32List(List list)
        throws IOException;

    public abstract long readUInt64()
        throws IOException;

    public abstract void readUInt64List(List list)
        throws IOException;

    public abstract boolean skipField()
        throws IOException;
}
