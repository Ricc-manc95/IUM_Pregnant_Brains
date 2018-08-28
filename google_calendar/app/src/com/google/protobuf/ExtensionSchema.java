// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;

// Referenced classes of package com.google.protobuf:
//            ExtensionRegistryLite, MessageLite, FieldSet, Reader, 
//            UnknownFieldSchema, ByteString, Writer

abstract class ExtensionSchema
{

    ExtensionSchema()
    {
    }

    abstract int extensionNumber(java.util.Map.Entry entry);

    abstract Object findExtensionByNumber(ExtensionRegistryLite extensionregistrylite, MessageLite messagelite, int i);

    abstract FieldSet getExtensions(Object obj);

    abstract FieldSet getMutableExtensions(Object obj);

    abstract boolean hasExtensions(MessageLite messagelite);

    abstract void makeImmutable(Object obj);

    abstract Object parseExtension(Reader reader, Object obj, ExtensionRegistryLite extensionregistrylite, FieldSet fieldset, Object obj1, UnknownFieldSchema unknownfieldschema)
        throws IOException;

    abstract void parseLengthPrefixedMessageSetItem(Reader reader, Object obj, ExtensionRegistryLite extensionregistrylite, FieldSet fieldset)
        throws IOException;

    abstract void parseMessageSetItem(ByteString bytestring, Object obj, ExtensionRegistryLite extensionregistrylite, FieldSet fieldset)
        throws IOException;

    abstract void serializeExtension(Writer writer, java.util.Map.Entry entry)
        throws IOException;

    abstract void setExtensions(Object obj, FieldSet fieldset);
}
