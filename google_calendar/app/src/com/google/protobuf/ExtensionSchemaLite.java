// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.protobuf:
//            ExtensionSchema, ExtensionRegistryLite, FieldSet, SmallSortedMap, 
//            Reader, SchemaUtil, LazyField, Internal, 
//            MessageLite, ByteString, Protobuf, Schema, 
//            InvalidProtocolBufferException, Writer, UnknownFieldSchema

final class ExtensionSchemaLite extends ExtensionSchema
{

    ExtensionSchemaLite()
    {
    }

    final int extensionNumber(java.util.Map.Entry entry)
    {
        return ((GeneratedMessageLite.ExtensionDescriptor)entry.getKey()).number;
    }

    final Object findExtensionByNumber(ExtensionRegistryLite extensionregistrylite, MessageLite messagelite, int i)
    {
        return extensionregistrylite.findLiteExtensionByNumber(messagelite, i);
    }

    final FieldSet getExtensions(Object obj)
    {
        return ((GeneratedMessageLite.ExtendableMessage)obj).extensions;
    }

    final FieldSet getMutableExtensions(Object obj)
    {
        FieldSet fieldset1 = getExtensions(obj);
        FieldSet fieldset = fieldset1;
        if (fieldset1.isImmutable)
        {
            fieldset = (FieldSet)fieldset1.clone();
            setExtensions(obj, fieldset);
        }
        return fieldset;
    }

    final boolean hasExtensions(MessageLite messagelite)
    {
        return messagelite instanceof GeneratedMessageLite.ExtendableMessage;
    }

    final void makeImmutable(Object obj)
    {
        obj = getExtensions(obj);
        if (!((FieldSet) (obj)).isImmutable)
        {
            ((FieldSet) (obj)).fields.makeImmutable();
            obj.isImmutable = true;
        }
    }

    final Object parseExtension(Reader reader, Object obj, ExtensionRegistryLite extensionregistrylite, FieldSet fieldset, Object obj1, UnknownFieldSchema unknownfieldschema)
        throws IOException
    {
        GeneratedMessageLite.GeneratedExtension generatedextension;
        int i;
        generatedextension = (GeneratedMessageLite.GeneratedExtension)obj;
        i = generatedextension.descriptor.number;
        if (!generatedextension.descriptor.isRepeated || !generatedextension.descriptor.isPacked) goto _L2; else goto _L1
_L1:
        generatedextension.descriptor.type.ordinal();
        JVM INSTR tableswitch 0 17: default 136
    //                   0 185
    //                   1 216
    //                   2 236
    //                   3 256
    //                   4 276
    //                   5 296
    //                   6 316
    //                   7 336
    //                   8 136
    //                   9 136
    //                   10 136
    //                   11 136
    //                   12 356
    //                   13 456
    //                   14 376
    //                   15 396
    //                   16 416
    //                   17 436;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L3 _L3 _L3 _L3 _L12 _L13 _L14 _L15 _L16 _L17
_L3:
        reader = String.valueOf(generatedextension.descriptor.type);
        throw new IllegalStateException((new StringBuilder(String.valueOf(reader).length() + 23)).append("Type cannot be packed: ").append(reader).toString());
_L4:
        obj = new ArrayList();
        reader.readDoubleList(((List) (obj)));
        reader = ((Reader) (obj));
_L18:
        fieldset.setField(generatedextension.descriptor, reader);
        return obj1;
_L5:
        obj = new ArrayList();
        reader.readFloatList(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new ArrayList();
        reader.readInt64List(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L7:
        obj = new ArrayList();
        reader.readUInt64List(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L8:
        obj = new ArrayList();
        reader.readInt32List(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L9:
        obj = new ArrayList();
        reader.readFixed64List(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L10:
        obj = new ArrayList();
        reader.readFixed32List(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L11:
        obj = new ArrayList();
        reader.readBoolList(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L12:
        obj = new ArrayList();
        reader.readUInt32List(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L14:
        obj = new ArrayList();
        reader.readSFixed32List(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L15:
        obj = new ArrayList();
        reader.readSFixed64List(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L16:
        obj = new ArrayList();
        reader.readSInt32List(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L17:
        obj = new ArrayList();
        reader.readSInt64List(((List) (obj)));
        reader = ((Reader) (obj));
        continue; /* Loop/switch isn't completed */
_L13:
        obj = new ArrayList();
        reader.readEnumList(((List) (obj)));
        obj1 = SchemaUtil.filterUnknownEnumList(i, ((List) (obj)), generatedextension.descriptor.enumTypeMap, obj1, unknownfieldschema);
        reader = ((Reader) (obj));
        if (true) goto _L18; else goto _L2
_L2:
        if (generatedextension.descriptor.type == WireFormat.FieldType.ENUM)
        {
            int j = reader.readInt32();
            if (generatedextension.descriptor.enumTypeMap.findValueByNumber(j) == null)
            {
                return SchemaUtil.storeUnknownEnum(i, j, obj1, unknownfieldschema);
            }
            reader = Integer.valueOf(j);
            break MISSING_BLOCK_LABEL_554;
        }
        switch (generatedextension.descriptor.type.ordinal())
        {
        default:
            reader = null;
            break;

        case 0: // '\0'
            reader = Double.valueOf(reader.readDouble());
            break;

        case 1: // '\001'
            reader = Float.valueOf(reader.readFloat());
            break;

        case 2: // '\002'
            reader = Long.valueOf(reader.readInt64());
            break;

        case 3: // '\003'
            reader = Long.valueOf(reader.readUInt64());
            break;

        case 4: // '\004'
            reader = Integer.valueOf(reader.readInt32());
            break;

        case 5: // '\005'
            reader = Long.valueOf(reader.readFixed64());
            break;

        case 6: // '\006'
            reader = Integer.valueOf(reader.readFixed32());
            break;

        case 7: // '\007'
            reader = Boolean.valueOf(reader.readBool());
            break;

        case 11: // '\013'
            reader = reader.readBytes();
            break;

        case 12: // '\f'
            reader = Integer.valueOf(reader.readUInt32());
            break;

        case 14: // '\016'
            reader = Integer.valueOf(reader.readSFixed32());
            break;

        case 15: // '\017'
            reader = Long.valueOf(reader.readSFixed64());
            break;

        case 16: // '\020'
            reader = Integer.valueOf(reader.readSInt32());
            break;

        case 17: // '\021'
            reader = Long.valueOf(reader.readSInt64());
            break;

        case 8: // '\b'
            reader = reader.readString();
            break;

        case 9: // '\t'
            reader = ((Reader) (reader.readGroup(generatedextension.messageDefaultInstance.getClass(), extensionregistrylite)));
            break;

        case 10: // '\n'
            reader = ((Reader) (reader.readMessage(generatedextension.messageDefaultInstance.getClass(), extensionregistrylite)));
            break;

        case 13: // '\r'
            throw new IllegalStateException("Shouldn't reach here.");
        }
        if (true) goto _L20; else goto _L19
_L20:
        if (!generatedextension.descriptor.isRepeated)
        {
            break; /* Loop/switch isn't completed */
        }
        unknownfieldschema = generatedextension.descriptor;
        if (!unknownfieldschema.isRepeated())
        {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }
        FieldSet.verifyType(unknownfieldschema.getLiteType(), reader);
        extensionregistrylite = ((ExtensionRegistryLite) (fieldset.fields.get(unknownfieldschema)));
        obj = extensionregistrylite;
        if (extensionregistrylite instanceof LazyField)
        {
            obj = LazyField.getValue();
        }
        if (obj == null)
        {
            obj = new ArrayList();
            fieldset.fields.put(unknownfieldschema, obj);
        } else
        {
            obj = (List)obj;
        }
        ((List) (obj)).add(reader);
        return obj1;
_L19:
        generatedextension.descriptor.type.ordinal();
        JVM INSTR tableswitch 9 10: default 1044
    //                   9 1060
    //                   10 1060;
           goto _L21 _L22 _L22
_L21:
        extensionregistrylite = reader;
_L24:
        fieldset.setField(generatedextension.descriptor, extensionregistrylite);
        return obj1;
_L22:
        obj = generatedextension.descriptor;
        extensionregistrylite = ((ExtensionRegistryLite) (fieldset.fields.get(obj)));
        obj = extensionregistrylite;
        if (extensionregistrylite instanceof LazyField)
        {
            obj = LazyField.getValue();
        }
        extensionregistrylite = reader;
        if (obj != null)
        {
            extensionregistrylite = ((ExtensionRegistryLite) (Internal.mergeMessage(obj, reader)));
        }
        if (true) goto _L24; else goto _L23
_L23:
    }

    final void parseLengthPrefixedMessageSetItem(Reader reader, Object obj, ExtensionRegistryLite extensionregistrylite, FieldSet fieldset)
        throws IOException
    {
        obj = (GeneratedMessageLite.GeneratedExtension)obj;
        reader = ((Reader) (reader.readMessage(((GeneratedMessageLite.GeneratedExtension) (obj)).messageDefaultInstance.getClass(), extensionregistrylite)));
        fieldset.setField(((GeneratedMessageLite.GeneratedExtension) (obj)).descriptor, reader);
    }

    final void parseMessageSetItem(ByteString bytestring, Object obj, ExtensionRegistryLite extensionregistrylite, FieldSet fieldset)
        throws IOException
    {
        GeneratedMessageLite.GeneratedExtension generatedextension = (GeneratedMessageLite.GeneratedExtension)obj;
        MessageLite messagelite = generatedextension.messageDefaultInstance.newBuilderForType().buildPartial();
        int i = bytestring.size();
        if (i == 0)
        {
            bytestring = Internal.EMPTY_BYTE_ARRAY;
        } else
        {
            obj = new byte[i];
            bytestring.copyToInternal(((byte []) (obj)), 0, 0, i);
            bytestring = ((ByteString) (obj));
        }
        bytestring = ByteBuffer.wrap(bytestring);
        if (bytestring.hasArray())
        {
            bytestring = new BinaryReader.SafeHeapReader(bytestring, true);
            Protobuf.INSTANCE.schemaFor(messagelite.getClass()).mergeFrom(messagelite, bytestring, extensionregistrylite);
            fieldset.setField(generatedextension.descriptor, messagelite);
            if (bytestring.getFieldNumber() != 0x7fffffff)
            {
                throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
            } else
            {
                return;
            }
        } else
        {
            throw new IllegalArgumentException("Direct buffers not yet supported");
        }
    }

    final void serializeExtension(Writer writer, java.util.Map.Entry entry)
        throws IOException
    {
        GeneratedMessageLite.ExtensionDescriptor extensiondescriptor = (GeneratedMessageLite.ExtensionDescriptor)entry.getKey();
        if (!extensiondescriptor.isRepeated) goto _L2; else goto _L1
_L1:
        extensiondescriptor.type.ordinal();
        JVM INSTR tableswitch 0 17: default 112
    //                   0 113
    //                   1 135
    //                   2 157
    //                   3 179
    //                   4 201
    //                   5 223
    //                   6 245
    //                   7 267
    //                   8 439
    //                   9 457
    //                   10 518
    //                   11 289
    //                   12 307
    //                   13 417
    //                   14 329
    //                   15 351
    //                   16 373
    //                   17 395;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21
_L3:
        return;
_L4:
        SchemaUtil.writeDoubleList(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L5:
        SchemaUtil.writeFloatList(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L6:
        SchemaUtil.writeInt64List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L7:
        SchemaUtil.writeUInt64List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L8:
        SchemaUtil.writeInt32List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L9:
        SchemaUtil.writeFixed64List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L10:
        SchemaUtil.writeFixed32List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L11:
        SchemaUtil.writeBoolList(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L15:
        SchemaUtil.writeBytesList(extensiondescriptor.number, (List)entry.getValue(), writer);
        return;
_L16:
        SchemaUtil.writeUInt32List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L18:
        SchemaUtil.writeSFixed32List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L19:
        SchemaUtil.writeSFixed64List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L20:
        SchemaUtil.writeSInt32List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L21:
        SchemaUtil.writeSInt64List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L17:
        SchemaUtil.writeInt32List(extensiondescriptor.number, (List)entry.getValue(), writer, extensiondescriptor.isPacked);
        return;
_L12:
        SchemaUtil.writeStringList(extensiondescriptor.number, (List)entry.getValue(), writer);
        return;
_L13:
        List list = (List)entry.getValue();
        if (list != null && !list.isEmpty())
        {
            SchemaUtil.writeGroupList(extensiondescriptor.number, (List)entry.getValue(), writer, Protobuf.INSTANCE.schemaFor(list.get(0).getClass()));
            return;
        }
          goto _L3
_L14:
        List list1 = (List)entry.getValue();
        if (list1 == null || list1.isEmpty()) goto _L3; else goto _L22
_L22:
        SchemaUtil.writeMessageList(extensiondescriptor.number, (List)entry.getValue(), writer, Protobuf.INSTANCE.schemaFor(list1.get(0).getClass()));
        return;
_L2:
        switch (extensiondescriptor.type.ordinal())
        {
        default:
            return;

        case 0: // '\0'
            writer.writeDouble(extensiondescriptor.number, ((Double)entry.getValue()).doubleValue());
            return;

        case 1: // '\001'
            writer.writeFloat(extensiondescriptor.number, ((Float)entry.getValue()).floatValue());
            return;

        case 2: // '\002'
            writer.writeInt64(extensiondescriptor.number, ((Long)entry.getValue()).longValue());
            return;

        case 3: // '\003'
            writer.writeUInt64(extensiondescriptor.number, ((Long)entry.getValue()).longValue());
            return;

        case 4: // '\004'
            writer.writeInt32(extensiondescriptor.number, ((Integer)entry.getValue()).intValue());
            return;

        case 5: // '\005'
            writer.writeFixed64(extensiondescriptor.number, ((Long)entry.getValue()).longValue());
            return;

        case 6: // '\006'
            writer.writeFixed32(extensiondescriptor.number, ((Integer)entry.getValue()).intValue());
            return;

        case 7: // '\007'
            writer.writeBool(extensiondescriptor.number, ((Boolean)entry.getValue()).booleanValue());
            return;

        case 11: // '\013'
            writer.writeBytes(extensiondescriptor.number, (ByteString)entry.getValue());
            return;

        case 12: // '\f'
            writer.writeUInt32(extensiondescriptor.number, ((Integer)entry.getValue()).intValue());
            return;

        case 14: // '\016'
            writer.writeSFixed32(extensiondescriptor.number, ((Integer)entry.getValue()).intValue());
            return;

        case 15: // '\017'
            writer.writeSFixed64(extensiondescriptor.number, ((Long)entry.getValue()).longValue());
            return;

        case 16: // '\020'
            writer.writeSInt32(extensiondescriptor.number, ((Integer)entry.getValue()).intValue());
            return;

        case 17: // '\021'
            writer.writeSInt64(extensiondescriptor.number, ((Long)entry.getValue()).longValue());
            return;

        case 13: // '\r'
            writer.writeInt32(extensiondescriptor.number, ((Integer)entry.getValue()).intValue());
            return;

        case 8: // '\b'
            writer.writeString(extensiondescriptor.number, (String)entry.getValue());
            return;

        case 9: // '\t'
            writer.writeGroup(extensiondescriptor.number, entry.getValue(), Protobuf.INSTANCE.schemaFor(entry.getValue().getClass()));
            return;

        case 10: // '\n'
            writer.writeMessage(extensiondescriptor.number, entry.getValue(), Protobuf.INSTANCE.schemaFor(entry.getValue().getClass()));
            return;
        }
    }

    final void setExtensions(Object obj, FieldSet fieldset)
    {
        ((GeneratedMessageLite.ExtendableMessage)obj).extensions = fieldset;
    }
}
