// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.protobuf:
//            Schema, ExtensionSchema, UnknownFieldSchema, FieldSet, 
//            SmallSortedMap, Reader, WireFormat, InvalidProtocolBufferException, 
//            SchemaUtil, GeneratedMessageLite, UnknownFieldSetLite, ArrayDecoders, 
//            ByteString, MessageLite, LazyField, LazyFieldLite, 
//            Writer, ExtensionRegistryLite

final class MessageSetSchema
    implements Schema
{

    private final MessageLite defaultInstance;
    private final ExtensionSchema extensionSchema;
    private final boolean hasExtensions;
    private final UnknownFieldSchema unknownFieldSchema;

    MessageSetSchema(UnknownFieldSchema unknownfieldschema, ExtensionSchema extensionschema, MessageLite messagelite)
    {
        unknownFieldSchema = unknownfieldschema;
        hasExtensions = extensionschema.hasExtensions(messagelite);
        extensionSchema = extensionschema;
        defaultInstance = messagelite;
    }

    public final boolean equals(Object obj, Object obj1)
    {
        if (!unknownFieldSchema.getFromMessage(obj).equals(unknownFieldSchema.getFromMessage(obj1)))
        {
            return false;
        }
        if (hasExtensions)
        {
            return extensionSchema.getExtensions(obj).equals(extensionSchema.getExtensions(obj1));
        } else
        {
            return true;
        }
    }

    public final int getSerializedSize(Object obj)
    {
        UnknownFieldSchema unknownfieldschema = unknownFieldSchema;
        int k = unknownfieldschema.getSerializedSizeAsMessageSet(unknownfieldschema.getFromMessage(obj)) + 0;
        if (hasExtensions)
        {
            obj = extensionSchema.getExtensions(obj);
            int j = 0;
            int i = 0;
            for (; j < ((FieldSet) (obj)).fields.entryList.size(); j++)
            {
                i += FieldSet.getMessageSetSerializedSize((java.util.Map.Entry)((FieldSet) (obj)).fields.entryList.get(j));
            }

            obj = ((FieldSet) (obj)).fields;
            if (((SmallSortedMap) (obj)).overflowEntries.isEmpty())
            {
                obj = SmallSortedMap.EmptySet.ITERABLE;
            } else
            {
                obj = ((SmallSortedMap) (obj)).overflowEntries.entrySet();
            }
            for (obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
            {
                i += FieldSet.getMessageSetSerializedSize((java.util.Map.Entry)((Iterator) (obj)).next());
            }

            return k + i;
        } else
        {
            return k;
        }
    }

    public final int hashCode(Object obj)
    {
        int j = unknownFieldSchema.getFromMessage(obj).hashCode();
        int i = j;
        if (hasExtensions)
        {
            i = j * 53 + extensionSchema.getExtensions(obj).hashCode();
        }
        return i;
    }

    public final boolean isInitialized(Object obj)
    {
        return extensionSchema.getExtensions(obj).isInitialized();
    }

    public final void makeImmutable(Object obj)
    {
        unknownFieldSchema.makeImmutable(obj);
        extensionSchema.makeImmutable(obj);
    }

    public final void mergeFrom(Object obj, Reader reader, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        UnknownFieldSchema unknownfieldschema;
        Object obj2;
        ExtensionSchema extensionschema;
        FieldSet fieldset;
        unknownfieldschema = unknownFieldSchema;
        extensionschema = extensionSchema;
        obj2 = unknownfieldschema.getBuilderFromMessage(obj);
        fieldset = extensionschema.getMutableExtensions(obj);
_L6:
        int i = reader.getFieldNumber();
        if (i == 0x7fffffff)
        {
            unknownfieldschema.setBuilderToMessage(obj, obj2);
            return;
        }
        i = reader.getTag();
        if (i == WireFormat.MESSAGE_SET_ITEM_TAG)
        {
            break MISSING_BLOCK_LABEL_321;
        }
        if ((i & 7) != 2) goto _L2; else goto _L1
_L1:
        Object obj1 = extensionschema.findExtensionByNumber(extensionregistrylite, defaultInstance, i >>> 3);
        if (obj1 == null) goto _L4; else goto _L3
_L3:
        extensionschema.parseLengthPrefixedMessageSetItem(reader, obj1, extensionregistrylite, fieldset);
_L13:
        boolean flag = true;
_L7:
        if (flag) goto _L6; else goto _L5
_L5:
        unknownfieldschema.setBuilderToMessage(obj, obj2);
        return;
_L4:
        flag = unknownfieldschema.mergeOneFieldFrom(obj2, reader);
          goto _L7
_L2:
        flag = reader.skipField();
          goto _L7
_L11:
        int j;
        while (reader.getFieldNumber() != 0x7fffffff) 
        {
            j = reader.getTag();
            if (j != WireFormat.MESSAGE_SET_TYPE_ID_TAG)
            {
                break MISSING_BLOCK_LABEL_202;
            }
            i = reader.readUInt32();
            obj1 = extensionschema.findExtensionByNumber(extensionregistrylite, defaultInstance, i);
        }
          goto _L8
        if (j != WireFormat.MESSAGE_SET_MESSAGE_TAG) goto _L10; else goto _L9
_L9:
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_240;
        }
        extensionschema.parseLengthPrefixedMessageSetItem(reader, obj1, extensionregistrylite, fieldset);
          goto _L11
        reader;
        unknownfieldschema.setBuilderToMessage(obj, obj2);
        throw reader;
        ByteString bytestring = reader.readBytes();
          goto _L11
_L10:
        if (reader.skipField()) goto _L11; else goto _L8
_L8:
        if (reader.getTag() != WireFormat.MESSAGE_SET_ITEM_END_TAG)
        {
            throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
        }
        if (bytestring == null) goto _L13; else goto _L12
_L12:
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_307;
        }
        extensionschema.parseMessageSetItem(bytestring, obj1, extensionregistrylite, fieldset);
          goto _L13
        unknownfieldschema.addLengthDelimited(obj2, i, bytestring);
          goto _L13
        bytestring = null;
        i = 0;
        obj1 = null;
          goto _L11
    }

    public final void mergeFrom(Object obj, Object obj1)
    {
        SchemaUtil.mergeUnknownFields(unknownFieldSchema, obj, obj1);
        if (hasExtensions)
        {
            SchemaUtil.mergeExtensions(extensionSchema, obj, obj1);
        }
    }

    public final void mergeFrom(Object obj, byte abyte0[], int i, int j, ArrayDecoders.Registers registers)
        throws IOException
    {
        UnknownFieldSetLite unknownfieldsetlite;
        int k;
        UnknownFieldSetLite unknownfieldsetlite1 = ((GeneratedMessageLite)obj).unknownFields;
        unknownfieldsetlite = unknownfieldsetlite1;
        k = i;
        if (unknownfieldsetlite1 == UnknownFieldSetLite.DEFAULT_INSTANCE)
        {
            unknownfieldsetlite = new UnknownFieldSetLite();
            ((GeneratedMessageLite)obj).unknownFields = unknownfieldsetlite;
            k = i;
        }
_L12:
        int l;
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        i = k + 1;
        k = abyte0[k];
        if (k >= 0)
        {
            registers.int1 = k;
        } else
        {
            i = ArrayDecoders.decodeVarint32(k, abyte0, i, registers);
        }
        k = registers.int1;
        if (k != WireFormat.MESSAGE_SET_ITEM_TAG)
        {
            if ((k & 7) == 2)
            {
                k = ArrayDecoders.decodeUnknownField(k, abyte0, i, j, unknownfieldsetlite, registers);
            } else
            {
                k = ArrayDecoders.skipField(k, abyte0, i, j, registers);
            }
            continue; /* Loop/switch isn't completed */
        }
        l = 0;
        obj = null;
_L8:
        if (i >= j) goto _L2; else goto _L1
_L1:
        k = i + 1;
        i = abyte0[i];
        int i1;
        if (i >= 0)
        {
            registers.int1 = i;
            i = k;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, k, registers);
        }
        i1 = registers.int1;
        k = i1 & 7;
        i1 >>> 3;
        JVM INSTR tableswitch 2 3: default 220
    //                   2 259
    //                   3 309;
           goto _L3 _L4 _L5
_L3:
        k = i;
        if (i1 == WireFormat.MESSAGE_SET_ITEM_END_TAG) goto _L7; else goto _L6
_L6:
        i = ArrayDecoders.skipField(i1, abyte0, i, j, registers);
          goto _L8
_L4:
        if (k != 0) goto _L3; else goto _L9
_L9:
        k = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = k;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, k, registers);
        }
        l = registers.int1;
          goto _L8
_L5:
        if (k != 2) goto _L3; else goto _L10
_L10:
        i = ArrayDecoders.decodeBytes(abyte0, i, registers);
        obj = (ByteString)registers.object1;
          goto _L8
_L2:
        k = i;
_L7:
        if (obj != null)
        {
            unknownfieldsetlite.storeField(l << 3 | 2, obj);
        }
        if (true) goto _L12; else goto _L11
_L11:
        if (k != j)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        } else
        {
            return;
        }
    }

    public final Object newInstance()
    {
        return defaultInstance.newBuilderForType().buildPartial();
    }

    public final void writeTo(Object obj, Writer writer)
        throws IOException
    {
        for (Iterator iterator = extensionSchema.getExtensions(obj).iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            FieldSet.FieldDescriptorLite fielddescriptorlite = (FieldSet.FieldDescriptorLite)entry.getKey();
            if (fielddescriptorlite.getLiteJavaType() != WireFormat.JavaType.MESSAGE || fielddescriptorlite.isRepeated() || fielddescriptorlite.isPacked())
            {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof LazyField.LazyEntry)
            {
                writer.writeMessageSetItem(fielddescriptorlite.getNumber(), ((LazyField)((LazyField.LazyEntry)entry).entry.getValue()).toByteString());
            } else
            {
                writer.writeMessageSetItem(fielddescriptorlite.getNumber(), entry.getValue());
            }
        }

        UnknownFieldSchema unknownfieldschema = unknownFieldSchema;
        unknownfieldschema.writeAsMessageSetTo(unknownfieldschema.getFromMessage(obj), writer);
    }
}
