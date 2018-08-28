// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            Schema, UnsafeUtil, GeneratedMessageLite, ExtensionSchema, 
//            NewInstanceSchema, ArrayDecoders, Protobuf, InvalidProtocolBufferException, 
//            MapFieldSchema, UnknownFieldSchema, FieldSet, ByteString, 
//            UnknownFieldSetLite, FieldType, CodedOutputStream, SchemaUtil, 
//            MessageLite, SmallSortedMap, Internal, RawMessageInfo, 
//            StructuralMessageInfo, Utf8, DoubleArrayList, FloatArrayList, 
//            LongArrayList, IntArrayList, BooleanArrayList, Reader, 
//            Writer, ListFieldSchema, MessageInfo, ExtensionRegistryLite

final class MessageSchema
    implements Schema
{

    private static final int EMPTY_INT_ARRAY[] = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();
    private final int buffer[];
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema extensionSchema;
    private final boolean hasExtensions;
    private final int intArray[];
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object objects[];
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema unknownFieldSchema;
    private final boolean useCachedSizeField;

    private MessageSchema(int ai[], Object aobj[], int i, int j, MessageLite messagelite, boolean flag, boolean flag1, 
            int ai1[], int k, int l, NewInstanceSchema newinstanceschema, ListFieldSchema listfieldschema, UnknownFieldSchema unknownfieldschema, ExtensionSchema extensionschema, 
            MapFieldSchema mapfieldschema)
    {
        buffer = ai;
        objects = aobj;
        minFieldNumber = i;
        maxFieldNumber = j;
        lite = messagelite instanceof GeneratedMessageLite;
        proto3 = flag;
        if (extensionschema != null && extensionschema.hasExtensions(messagelite))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hasExtensions = flag;
        useCachedSizeField = flag1;
        intArray = ai1;
        checkInitializedCount = k;
        repeatedFieldOffsetStart = l;
        newInstanceSchema = newinstanceschema;
        listFieldSchema = listfieldschema;
        unknownFieldSchema = unknownfieldschema;
        extensionSchema = extensionschema;
        defaultInstance = messagelite;
        mapFieldSchema = mapfieldschema;
    }

    private static int decodeGroupField(Schema schema, byte abyte0[], int i, int j, int k, ArrayDecoders.Registers registers)
        throws IOException
    {
        schema = (MessageSchema)schema;
        Object obj = ((MessageSchema) (schema)).newInstanceSchema.newInstance(((MessageSchema) (schema)).defaultInstance);
        i = schema.parseProto2Message(obj, abyte0, i, j, k, registers);
        schema.makeImmutable(obj);
        registers.object1 = obj;
        return i;
    }

    private static int decodeMapEntryValue(byte abyte0[], int i, int j, WireFormat.FieldType fieldtype, Class class1, ArrayDecoders.Registers registers)
        throws IOException
    {
        boolean flag = true;
        switch (fieldtype.ordinal())
        {
        case 9: // '\t'
        default:
            throw new RuntimeException("unsupported field type.");

        case 7: // '\007'
            i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
            if (registers.long1 == 0L)
            {
                flag = false;
            }
            registers.object1 = Boolean.valueOf(flag);
            return i;

        case 11: // '\013'
            return ArrayDecoders.decodeBytes(abyte0, i, registers);

        case 0: // '\0'
            registers.object1 = Double.valueOf(Double.longBitsToDouble(ArrayDecoders.decodeFixed64(abyte0, i)));
            return i + 8;

        case 6: // '\006'
        case 14: // '\016'
            registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(abyte0, i));
            return i + 4;

        case 5: // '\005'
        case 15: // '\017'
            registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(abyte0, i));
            return i + 8;

        case 1: // '\001'
            registers.object1 = Float.valueOf(Float.intBitsToFloat(ArrayDecoders.decodeFixed32(abyte0, i)));
            return i + 4;

        case 4: // '\004'
        case 12: // '\f'
        case 13: // '\r'
            j = i + 1;
            i = abyte0[i];
            if (i >= 0)
            {
                registers.int1 = i;
                i = j;
            } else
            {
                i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
            }
            registers.object1 = Integer.valueOf(registers.int1);
            return i;

        case 2: // '\002'
        case 3: // '\003'
            i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
            registers.object1 = Long.valueOf(registers.long1);
            return i;

        case 10: // '\n'
            return decodeMessageField(Protobuf.INSTANCE.schemaFor(class1), abyte0, i, j, registers);

        case 16: // '\020'
            j = i + 1;
            i = abyte0[i];
            if (i >= 0)
            {
                registers.int1 = i;
                i = j;
            } else
            {
                i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
            }
            j = registers.int1;
            registers.object1 = Integer.valueOf(-(j & 1) ^ j >>> 1);
            return i;

        case 17: // '\021'
            i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
            long l = registers.long1;
            registers.object1 = Long.valueOf(-(l & 1L) ^ l >>> 1);
            return i;

        case 8: // '\b'
            return ArrayDecoders.decodeStringRequireUtf8(abyte0, i, registers);
        }
    }

    private static int decodeMessageField(Schema schema, byte abyte0[], int i, int j, ArrayDecoders.Registers registers)
        throws IOException
    {
        int l = i + 1;
        int k = abyte0[i];
        if (k < 0)
        {
            i = ArrayDecoders.decodeVarint32(k, abyte0, l, registers);
            k = registers.int1;
        } else
        {
            i = l;
        }
        if (k < 0 || k > j - i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            Object obj = schema.newInstance();
            schema.mergeFrom(obj, abyte0, i, i + k, registers);
            schema.makeImmutable(obj);
            registers.object1 = obj;
            return i + k;
        }
    }

    private static int decodeMessageList(Schema schema, int i, byte abyte0[], int j, int k, Internal.ProtobufList protobuflist, ArrayDecoders.Registers registers)
        throws IOException
    {
        j = decodeMessageField(schema, abyte0, j, k, registers);
        protobuflist.add(registers.object1);
        do
        {
            if (j >= k)
            {
                break;
            }
            int l = j + 1;
            int i1 = abyte0[j];
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
            }
            if (i != registers.int1)
            {
                break;
            }
            j = decodeMessageField(schema, abyte0, l, k, registers);
            protobuflist.add(registers.object1);
        } while (true);
        return j;
    }

    private final Object filterMapUnknownEnumValues(Object obj, int i, Object obj1, UnknownFieldSchema unknownfieldschema)
    {
        int j = buffer[i];
        long l = buffer[i + 1] & 0xfffff;
        obj = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l);
        Internal.EnumVerifier enumverifier;
        if (obj != null)
        {
            if ((enumverifier = (Internal.EnumVerifier)objects[(i / 3 << 1) + 1]) != null)
            {
                return filterUnknownEnumMap(i, j, mapFieldSchema.forMutableMapData(obj), enumverifier, obj1, unknownfieldschema);
            }
        }
        return obj1;
    }

    private final Object filterUnknownEnumMap(int i, int j, Map map, Internal.EnumVerifier enumverifier, Object obj, UnknownFieldSchema unknownfieldschema)
    {
        MapEntryLite.Metadata metadata = mapFieldSchema.forMapMetadata(objects[i / 3 << 1]);
        Iterator iterator = map.entrySet().iterator();
        map = ((Map) (obj));
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            obj = (java.util.Map.Entry)iterator.next();
            if (!enumverifier.isInRange(((Integer)((java.util.Map.Entry) (obj)).getValue()).intValue()))
            {
                if (map == null)
                {
                    map = ((Map) (unknownfieldschema.newBuilder()));
                }
                Object obj1 = ((java.util.Map.Entry) (obj)).getKey();
                Object obj2 = ((java.util.Map.Entry) (obj)).getValue();
                obj1 = ByteString.newCodedBuilder(FieldSet.computeElementSize(metadata.keyType, 1, obj1) + FieldSet.computeElementSize(metadata.valueType, 2, obj2));
                obj2 = ((ByteString.CodedBuilder) (obj1)).output;
                try
                {
                    Object obj3 = ((java.util.Map.Entry) (obj)).getKey();
                    obj = ((java.util.Map.Entry) (obj)).getValue();
                    FieldSet.writeElement(((CodedOutputStream) (obj2)), metadata.keyType, 1, obj3);
                    FieldSet.writeElement(((CodedOutputStream) (obj2)), metadata.valueType, 2, obj);
                }
                // Misplaced declaration of an exception variable
                catch (Map map)
                {
                    throw new RuntimeException(map);
                }
                unknownfieldschema.addLengthDelimited(map, j, ((ByteString.CodedBuilder) (obj1)).build());
                iterator.remove();
            }
        } while (true);
        return map;
    }

    private final Schema getMessageFieldSchema(int i)
    {
        i = i / 3 << 1;
        Schema schema = (Schema)objects[i];
        if (schema != null)
        {
            return schema;
        } else
        {
            Schema schema1 = Protobuf.INSTANCE.schemaFor((Class)objects[i + 1]);
            objects[i] = schema1;
            return schema1;
        }
    }

    private static UnknownFieldSetLite getMutableUnknownFields(Object obj)
    {
        UnknownFieldSetLite unknownfieldsetlite1 = ((GeneratedMessageLite)obj).unknownFields;
        UnknownFieldSetLite unknownfieldsetlite = unknownfieldsetlite1;
        if (unknownfieldsetlite1 == UnknownFieldSetLite.DEFAULT_INSTANCE)
        {
            unknownfieldsetlite = new UnknownFieldSetLite();
            ((GeneratedMessageLite)obj).unknownFields = unknownfieldsetlite;
        }
        return unknownfieldsetlite;
    }

    private final int getSerializedSizeProto2(Object obj)
    {
        Object obj1;
        int k;
        int l;
        int k1;
        int l1;
        obj1 = UNSAFE;
        l = -1;
        l1 = 0;
        k1 = 0;
        k = 0;
_L71:
        if (k1 >= buffer.length)
        {
            break MISSING_BLOCK_LABEL_3520;
        }
        int j2 = buffer[k1 + 1];
        int i2 = buffer[k1];
        int k2 = (0xff00000 & j2) >>> 20;
        int i1 = 0;
        Object obj2;
        int j;
        int j1;
        long l2;
        long l3;
        if (k2 <= 17)
        {
            i1 = buffer[k1 + 2];
            j1 = 0xfffff & i1;
            int i = l;
            if (j1 != l)
            {
                k = ((Unsafe) (obj1)).getInt(obj, j1);
                i = j1;
            }
            j1 = 1 << (i1 >>> 20);
            l = i;
        } else
        if (useCachedSizeField && k2 >= FieldType.DOUBLE_LIST_PACKED.id && k2 <= FieldType.SINT64_LIST_PACKED.id)
        {
            i1 = buffer[k1 + 2] & 0xfffff;
            j1 = 0;
        } else
        {
            j1 = 0;
        }
        l2 = 0xfffff & j2;
        k2;
        JVM INSTR tableswitch 0 68: default 424
    //                   0 490
    //                   1 516
    //                   2 542
    //                   3 574
    //                   4 606
    //                   5 638
    //                   6 664
    //                   7 690
    //                   8 716
    //                   9 777
    //                   10 815
    //                   11 850
    //                   12 882
    //                   13 914
    //                   14 940
    //                   15 966
    //                   16 998
    //                   17 1030
    //                   18 1071
    //                   19 1095
    //                   20 1119
    //                   21 1143
    //                   22 1167
    //                   23 1191
    //                   24 1215
    //                   25 1239
    //                   26 1263
    //                   27 1286
    //                   28 1315
    //                   29 1338
    //                   30 1362
    //                   31 1386
    //                   32 1410
    //                   33 1434
    //                   34 1458
    //                   35 1482
    //                   36 1545
    //                   37 1608
    //                   38 1671
    //                   39 1734
    //                   40 1797
    //                   41 1860
    //                   42 1923
    //                   43 1986
    //                   44 2049
    //                   45 2112
    //                   46 2175
    //                   47 2238
    //                   48 2301
    //                   49 2364
    //                   50 2393
    //                   51 2430
    //                   52 2480
    //                   53 2530
    //                   54 2597
    //                   55 2664
    //                   56 2731
    //                   57 2781
    //                   58 2831
    //                   59 2881
    //                   60 2966
    //                   61 3028
    //                   62 3087
    //                   63 3154
    //                   64 3221
    //                   65 3271
    //                   66 3321
    //                   67 3388
    //                   68 3455;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70
_L70:
        break MISSING_BLOCK_LABEL_3455;
_L1:
        j = l1;
_L72:
        k1 += 3;
        l1 = j;
          goto _L71
_L2:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeDoubleSize(i2, 0.0D);
        }
          goto _L72
_L3:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeFloatSize(i2, 0.0F);
        }
          goto _L72
_L4:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeInt64Size(i2, ((Unsafe) (obj1)).getLong(obj, l2));
        }
          goto _L72
_L5:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeUInt64Size(i2, ((Unsafe) (obj1)).getLong(obj, l2));
        }
          goto _L72
_L6:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeInt32Size(i2, ((Unsafe) (obj1)).getInt(obj, l2));
        }
          goto _L72
_L7:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeFixed64Size(i2, 0L);
        }
          goto _L72
_L8:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeFixed32Size(i2, 0);
        }
          goto _L72
_L9:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeBoolSize(i2, true);
        }
          goto _L72
_L10:
        j = l1;
        if ((j1 & k) != 0)
        {
            obj2 = ((Unsafe) (obj1)).getObject(obj, l2);
            if (obj2 instanceof ByteString)
            {
                j = l1 + CodedOutputStream.computeBytesSize(i2, (ByteString)obj2);
            } else
            {
                j = l1 + CodedOutputStream.computeStringSize(i2, (String)obj2);
            }
        }
          goto _L72
_L11:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + SchemaUtil.computeSizeMessage(i2, ((Unsafe) (obj1)).getObject(obj, l2), getMessageFieldSchema(k1));
        }
          goto _L72
_L12:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeBytesSize(i2, (ByteString)((Unsafe) (obj1)).getObject(obj, l2));
        }
          goto _L72
_L13:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeUInt32Size(i2, ((Unsafe) (obj1)).getInt(obj, l2));
        }
          goto _L72
_L14:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeEnumSize(i2, ((Unsafe) (obj1)).getInt(obj, l2));
        }
          goto _L72
_L15:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeSFixed32Size(i2, 0);
        }
          goto _L72
_L16:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeSFixed64Size(i2, 0L);
        }
          goto _L72
_L17:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeSInt32Size(i2, ((Unsafe) (obj1)).getInt(obj, l2));
        }
          goto _L72
_L18:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeSInt64Size(i2, ((Unsafe) (obj1)).getLong(obj, l2));
        }
          goto _L72
_L19:
        j = l1;
        if ((j1 & k) != 0)
        {
            j = l1 + CodedOutputStream.computeGroupSize(i2, (MessageLite)((Unsafe) (obj1)).getObject(obj, l2), getMessageFieldSchema(k1));
        }
          goto _L72
_L20:
        j = l1 + SchemaUtil.computeSizeFixed64List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L21:
        j = l1 + SchemaUtil.computeSizeFixed32List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L22:
        j = l1 + SchemaUtil.computeSizeInt64List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L23:
        j = l1 + SchemaUtil.computeSizeUInt64List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L24:
        j = l1 + SchemaUtil.computeSizeInt32List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L25:
        j = l1 + SchemaUtil.computeSizeFixed64List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L26:
        j = l1 + SchemaUtil.computeSizeFixed32List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L27:
        j = l1 + SchemaUtil.computeSizeBoolList(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L28:
        j = l1 + SchemaUtil.computeSizeStringList(i2, (List)((Unsafe) (obj1)).getObject(obj, l2));
          goto _L72
_L29:
        j = l1 + SchemaUtil.computeSizeMessageList(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), getMessageFieldSchema(k1));
          goto _L72
_L30:
        j = l1 + SchemaUtil.computeSizeByteStringList(i2, (List)((Unsafe) (obj1)).getObject(obj, l2));
          goto _L72
_L31:
        j = l1 + SchemaUtil.computeSizeUInt32List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L32:
        j = l1 + SchemaUtil.computeSizeEnumList(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L33:
        j = l1 + SchemaUtil.computeSizeFixed32List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L34:
        j = l1 + SchemaUtil.computeSizeFixed64List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L35:
        j = l1 + SchemaUtil.computeSizeSInt32List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L36:
        j = l1 + SchemaUtil.computeSizeSInt64List(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), false);
          goto _L72
_L37:
        j1 = SchemaUtil.computeSizeFixed64ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L38:
        j1 = SchemaUtil.computeSizeFixed32ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L39:
        j1 = SchemaUtil.computeSizeInt64ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L40:
        j1 = SchemaUtil.computeSizeUInt64ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L41:
        j1 = SchemaUtil.computeSizeInt32ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L42:
        j1 = SchemaUtil.computeSizeFixed64ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L43:
        j1 = SchemaUtil.computeSizeFixed32ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L44:
        j1 = SchemaUtil.computeSizeBoolListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L45:
        j1 = SchemaUtil.computeSizeUInt32ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L46:
        j1 = SchemaUtil.computeSizeEnumListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L47:
        j1 = SchemaUtil.computeSizeFixed32ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L48:
        j1 = SchemaUtil.computeSizeFixed64ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L49:
        j1 = SchemaUtil.computeSizeSInt32ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L50:
        j1 = SchemaUtil.computeSizeSInt64ListNoTag((List)((Unsafe) (obj1)).getObject(obj, l2));
        j = l1;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                ((Unsafe) (obj1)).putInt(obj, i1, j1);
            }
            j = l1 + (j1 + (CodedOutputStream.computeTagSize(i2) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L51:
        j = l1 + SchemaUtil.computeSizeGroupList(i2, (List)((Unsafe) (obj1)).getObject(obj, l2), getMessageFieldSchema(k1));
          goto _L72
_L52:
        j = l1 + mapFieldSchema.getSerializedSize(i2, ((Unsafe) (obj1)).getObject(obj, l2), objects[k1 / 3 << 1]);
          goto _L72
_L53:
        l2 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == i2)
        {
            j = l1 + CodedOutputStream.computeDoubleSize(i2, 0.0D);
        }
          goto _L72
_L54:
        l2 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == i2)
        {
            j = l1 + CodedOutputStream.computeFloatSize(i2, 0.0F);
        }
          goto _L72
_L55:
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            j = l1 + CodedOutputStream.computeInt64Size(i2, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).longValue());
        }
          goto _L72
_L56:
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            j = l1 + CodedOutputStream.computeUInt64Size(i2, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).longValue());
        }
          goto _L72
_L57:
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            j = l1 + CodedOutputStream.computeInt32Size(i2, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).intValue());
        }
          goto _L72
_L58:
        l2 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == i2)
        {
            j = l1 + CodedOutputStream.computeFixed64Size(i2, 0L);
        }
          goto _L72
_L59:
        l2 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == i2)
        {
            j = l1 + CodedOutputStream.computeFixed32Size(i2, 0);
        }
          goto _L72
_L60:
        l2 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == i2)
        {
            j = l1 + CodedOutputStream.computeBoolSize(i2, true);
        }
          goto _L72
_L61:
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            obj2 = ((Unsafe) (obj1)).getObject(obj, l2);
            if (obj2 instanceof ByteString)
            {
                j = l1 + CodedOutputStream.computeBytesSize(i2, (ByteString)obj2);
            } else
            {
                j = l1 + CodedOutputStream.computeStringSize(i2, (String)obj2);
            }
        }
          goto _L72
_L62:
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            j = l1 + SchemaUtil.computeSizeMessage(i2, ((Unsafe) (obj1)).getObject(obj, l2), getMessageFieldSchema(k1));
        }
          goto _L72
_L63:
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            j = l1 + CodedOutputStream.computeBytesSize(i2, (ByteString)((Unsafe) (obj1)).getObject(obj, l2));
        }
          goto _L72
_L64:
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            j = l1 + CodedOutputStream.computeUInt32Size(i2, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).intValue());
        }
          goto _L72
_L65:
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            j = l1 + CodedOutputStream.computeEnumSize(i2, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).intValue());
        }
          goto _L72
_L66:
        l2 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == i2)
        {
            j = l1 + CodedOutputStream.computeSFixed32Size(i2, 0);
        }
          goto _L72
_L67:
        l2 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == i2)
        {
            j = l1 + CodedOutputStream.computeSFixed64Size(i2, 0L);
        }
          goto _L72
_L68:
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            j = l1 + CodedOutputStream.computeSInt32Size(i2, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).intValue());
        }
          goto _L72
_L69:
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            j = l1 + CodedOutputStream.computeSInt64Size(i2, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).longValue());
        }
          goto _L72
        l3 = buffer[k1 + 2] & 0xfffff;
        j = l1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == i2)
        {
            j = l1 + CodedOutputStream.computeGroupSize(i2, (MessageLite)((Unsafe) (obj1)).getObject(obj, l2), getMessageFieldSchema(k1));
        }
          goto _L72
        obj1 = unknownFieldSchema;
        l = l1 + ((UnknownFieldSchema) (obj1)).getSerializedSize(((UnknownFieldSchema) (obj1)).getFromMessage(obj));
        if (hasExtensions)
        {
            obj = extensionSchema.getExtensions(obj);
            k = 0;
            for (j = 0; k < ((FieldSet) (obj)).fields.entryList.size(); j += i1)
            {
                obj1 = (java.util.Map.Entry)((FieldSet) (obj)).fields.entryList.get(k);
                i1 = FieldSet.computeFieldSize((FieldSet.FieldDescriptorLite)((java.util.Map.Entry) (obj1)).getKey(), ((java.util.Map.Entry) (obj1)).getValue());
                k++;
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
                obj1 = (java.util.Map.Entry)((Iterator) (obj)).next();
                j += FieldSet.computeFieldSize((FieldSet.FieldDescriptorLite)((java.util.Map.Entry) (obj1)).getKey(), ((java.util.Map.Entry) (obj1)).getValue());
            }

            return l + j;
        } else
        {
            return l;
        }
    }

    private final boolean isFieldPresent(Object obj, int i)
    {
        if (proto3)
        {
            i = buffer[i + 1];
            long l = i & 0xfffff;
            switch ((i & 0xff00000) >>> 20)
            {
            default:
                throw new IllegalArgumentException();

            case 0: // '\0'
                return UnsafeUtil.MEMORY_ACCESSOR.getDouble(obj, l) != 0.0D;

            case 1: // '\001'
                return UnsafeUtil.MEMORY_ACCESSOR.getFloat(obj, l) != 0.0F;

            case 2: // '\002'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l) != 0L;

            case 3: // '\003'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l) != 0L;

            case 4: // '\004'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l) != 0;

            case 5: // '\005'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l) != 0L;

            case 6: // '\006'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l) != 0;

            case 7: // '\007'
                return UnsafeUtil.MEMORY_ACCESSOR.getBoolean(obj, l);

            case 8: // '\b'
                obj = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l);
                if (obj instanceof String)
                {
                    return !((String)obj).isEmpty();
                }
                if (obj instanceof ByteString)
                {
                    return !ByteString.EMPTY.equals(obj);
                } else
                {
                    throw new IllegalArgumentException();
                }

            case 9: // '\t'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l) != null;

            case 10: // '\n'
                return !ByteString.EMPTY.equals(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l));

            case 11: // '\013'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l) != 0;

            case 12: // '\f'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l) != 0;

            case 13: // '\r'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l) != 0;

            case 14: // '\016'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l) != 0L;

            case 15: // '\017'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l) != 0;

            case 16: // '\020'
                return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l) != 0L;

            case 17: // '\021'
                break;
            }
            return UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l) != null;
        }
        i = buffer[i + 2];
        long l1 = i & 0xfffff;
        return (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1) & 1 << (i >>> 20)) != 0;
    }

    private final void mergeMessage(Object obj, Object obj1, int i)
    {
        long l = buffer[i + 1] & 0xfffff;
        if (isFieldPresent(obj1, i))
        {
            Object obj2 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l);
            obj1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l);
            if (obj2 != null && obj1 != null)
            {
                obj1 = Internal.mergeMessage(obj2, obj1);
                UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, obj1);
                setFieldPresent(obj, i);
                return;
            }
            if (obj1 != null)
            {
                UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, obj1);
                setFieldPresent(obj, i);
                return;
            }
        }
    }

    private final void mergeOneofMessage(Object obj, Object obj1, int i)
    {
        int j = buffer[i + 1];
        int k = buffer[i];
        long l = j & 0xfffff;
        long l1 = buffer[i + 2] & 0xfffff;
        boolean flag;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l1) == k)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Object obj2 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l);
            obj1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l);
            if (obj2 != null && obj1 != null)
            {
                obj1 = Internal.mergeMessage(obj2, obj1);
                UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, obj1);
                l = buffer[i + 2] & 0xfffff;
                UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l, k);
                return;
            }
            if (obj1 != null)
            {
                UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, obj1);
                l = buffer[i + 2] & 0xfffff;
                UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l, k);
                return;
            }
        }
    }

    static MessageSchema newSchema$5166KOBMC4NMOOBECSNK6R31EDPJMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQDCLPN6OB7CL4MSPJF7D666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKSPBN95N76T31DPHMAKR3D1IMQO9R9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T66ISRK8PKMAR34ADHMGPBDC4TKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULBEDDN6UTRE8PKMAR34ADHMGPBDC4TKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UHBOEHIMSSR9DTN56OR8CLMM2EQCCDNMQBR7DTNMER355TO74RRKDTH7APHF9LGN0HJ9CLM68KR3D1IMQO9R55666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQPBJEDGMEPAJCDK6ARB17C______0(MessageInfo messageinfo, NewInstanceSchema newinstanceschema, ListFieldSchema listfieldschema, UnknownFieldSchema unknownfieldschema, ExtensionSchema extensionschema, MapFieldSchema mapfieldschema)
    {
        if (messageinfo instanceof RawMessageInfo)
        {
            RawMessageInfo rawmessageinfo = (RawMessageInfo)messageinfo;
            String s;
            int i;
            int l1;
            int k3;
            int k6;
            boolean flag1;
            if ((rawmessageinfo.flags & 1) == 1)
            {
                i = android.support.v4.content.ModernAsyncTask.Status.PROTO2$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T874RRKDT9NIRJKC5S3M___0;
            } else
            {
                i = android.support.v4.content.ModernAsyncTask.Status.PROTO3$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T874RRKDT9NIRJKC5S3M___0;
            }
            if (i == android.support.v4.content.ModernAsyncTask.Status.PROTO3$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T874RRKDT9NIRJKC5S3M___0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            s = rawmessageinfo.info;
            k6 = s.length();
            l1 = 1;
            k3 = s.charAt(0);
            Unsafe unsafe;
            Object aobj[];
            Class class1;
            int ai[];
            Object aobj1[];
            int l;
            int k1;
            boolean flag;
            int j2;
            int k2;
            int l2;
            int l3;
            int i4;
            int j4;
            int k4;
            int l4;
            if (k3 >= 55296)
            {
                int i1 = k3 & 0x1fff;
                int j = 13;
                do
                {
                    i = l1 + 1;
                    l1 = s.charAt(l1);
                    if (l1 < '\uD800')
                    {
                        break;
                    }
                    i1 |= (l1 & 0x1fff) << j;
                    j += 13;
                    l1 = i;
                } while (true);
                k3 = l1 << j | i1;
            } else
            {
                i = 1;
            }
            l1 = i + 1;
            i = s.charAt(i);
            if (i >= 55296)
            {
                int j1 = i & 0x1fff;
                int k = 13;
                do
                {
                    i = l1 + 1;
                    l1 = s.charAt(l1);
                    if (l1 < 55296)
                    {
                        break;
                    }
                    j1 |= (l1 & 0x1fff) << k;
                    k += 13;
                    l1 = i;
                } while (true);
                k = l1 << k | j1;
                j2 = i;
                i = k;
            } else
            {
                j2 = l1;
            }
            if (i == 0)
            {
                k1 = 0;
                messageinfo = EMPTY_INT_ARRAY;
                i = 0;
                k4 = 0;
                j4 = 0;
                l3 = 0;
                l = 0;
                i4 = 0;
            } else
            {
                l1 = j2 + 1;
                i = s.charAt(j2);
                Object obj;
                int j3;
                int j5;
                int k5;
                int l5;
                int i6;
                int j6;
                int l6;
                if (i >= 55296)
                {
                    k1 = i & 0x1fff;
                    l = 13;
                    do
                    {
                        i = l1 + 1;
                        l1 = s.charAt(l1);
                        if (l1 < 55296)
                        {
                            break;
                        }
                        k1 |= (l1 & 0x1fff) << l;
                        l += 13;
                        l1 = i;
                    } while (true);
                    l = l1 << l | k1;
                    k1 = i;
                    i = l;
                } else
                {
                    k1 = l1;
                }
                l = k1 + 1;
                k1 = s.charAt(k1);
                i3 = k1;
                l1 = l;
                if (k1 >= 55296)
                {
                    l1 = k1 & 0x1fff;
                    k1 = 13;
                    i2 = l;
                    do
                    {
                        l = i2 + 1;
                        i2 = s.charAt(i2);
                        if (i2 < 55296)
                        {
                            break;
                        }
                        l1 |= (i2 & 0x1fff) << k1;
                        k1 += 13;
                        i2 = l;
                    } while (true);
                    i3 = i2 << k1 | l1;
                    l1 = l;
                }
                k1 = l1 + 1;
                l1 = s.charAt(l1);
                l = l1;
                i2 = k1;
                if (l1 >= 55296)
                {
                    l1 &= 0x1fff;
                    l = 13;
                    i2 = k1;
                    k1 = l;
                    do
                    {
                        l = i2 + 1;
                        i2 = s.charAt(i2);
                        if (i2 < 55296)
                        {
                            break;
                        }
                        l1 |= (i2 & 0x1fff) << k1;
                        k1 += 13;
                        i2 = l;
                    } while (true);
                    k1 = i2 << k1 | l1;
                    i2 = l;
                    l = k1;
                }
                l1 = i2 + 1;
                i2 = s.charAt(i2);
                k1 = i2;
                j2 = l1;
                if (i2 >= 55296)
                {
                    i2 &= 0x1fff;
                    k1 = 13;
                    j2 = l1;
                    l1 = k1;
                    do
                    {
                        k1 = j2 + 1;
                        j2 = s.charAt(j2);
                        if (j2 < 55296)
                        {
                            break;
                        }
                        i2 |= (j2 & 0x1fff) << l1;
                        l1 += 13;
                        j2 = k1;
                    } while (true);
                    l1 = j2 << l1 | i2;
                    j2 = k1;
                    k1 = l1;
                }
                i2 = j2 + 1;
                j2 = s.charAt(j2);
                l1 = j2;
                k2 = i2;
                if (j2 >= 55296)
                {
                    j2 &= 0x1fff;
                    l1 = 13;
                    k2 = i2;
                    i2 = l1;
                    do
                    {
                        l1 = k2 + 1;
                        k2 = s.charAt(k2);
                        if (k2 < 55296)
                        {
                            break;
                        }
                        j2 |= (k2 & 0x1fff) << i2;
                        i2 += 13;
                        k2 = l1;
                    } while (true);
                    i2 = k2 << i2 | j2;
                    k2 = l1;
                    l1 = i2;
                }
                j2 = k2 + 1;
                l3 = s.charAt(k2);
                i2 = l3;
                k2 = j2;
                if (l3 >= 55296)
                {
                    k2 = l3 & 0x1fff;
                    i2 = 13;
                    l3 = j2;
                    j2 = i2;
                    do
                    {
                        i2 = l3 + 1;
                        l3 = s.charAt(l3);
                        if (l3 < 55296)
                        {
                            break;
                        }
                        k2 |= (l3 & 0x1fff) << j2;
                        j2 += 13;
                        l3 = i2;
                    } while (true);
                    j2 = l3 << j2 | k2;
                    k2 = i2;
                    i2 = j2;
                }
                j2 = k2 + 1;
                i4 = s.charAt(k2);
                l3 = i4;
                k2 = j2;
                if (i4 >= 55296)
                {
                    l3 = i4 & 0x1fff;
                    k2 = 13;
                    i4 = j2;
                    do
                    {
                        j2 = i4 + 1;
                        i4 = s.charAt(i4);
                        if (i4 < 55296)
                        {
                            break;
                        }
                        l3 |= (i4 & 0x1fff) << k2;
                        k2 += 13;
                        i4 = j2;
                    } while (true);
                    l3 = i4 << k2 | l3;
                    k2 = j2;
                }
                i4 = k2 + 1;
                j4 = s.charAt(k2);
                k2 = j4;
                j2 = i4;
                if (j4 >= 55296)
                {
                    j2 = j4 & 0x1fff;
                    k2 = 13;
                    j4 = i4;
                    i4 = j2;
                    do
                    {
                        j2 = j4 + 1;
                        j4 = s.charAt(j4);
                        if (j4 < 55296)
                        {
                            break;
                        }
                        i4 |= (j4 & 0x1fff) << k2;
                        k2 += 13;
                        j4 = j2;
                    } while (true);
                    k2 = j4 << k2 | i4;
                }
                messageinfo = new int[l3 + (k2 + i2)];
                j4 = i;
                i = i3 + (i << 1);
                l3 = l;
                l = l1;
                i4 = k1;
                k1 = k2;
                k4 = i2;
            }
            unsafe = UNSAFE;
            aobj = rawmessageinfo.objects;
            l1 = 0;
            class1 = rawmessageinfo.defaultInstance.getClass();
            ai = new int[l * 3];
            aobj1 = new Object[l << 1];
            l2 = k1 + k4;
            l4 = 0;
            l = k1;
            flag = i;
            k2 = j2;
            j2 = l1;
            l1 = l2;
            i = l;
            l = ((flag) ? 1 : 0);
            while (k2 < k6) 
            {
label0:
                {
                    {
                        int i2 = k2 + 1;
                        int i5 = s.charAt(k2);
                        if (i5 >= 55296)
                        {
                            int i3 = i5 & 0x1fff;
                            k2 = 13;
                            i5 = i2;
                            do
                            {
                                i2 = i5 + 1;
                                i5 = s.charAt(i5);
                                if (i5 < 55296)
                                {
                                    break;
                                }
                                i3 |= (i5 & 0x1fff) << k2;
                                k2 += 13;
                                i5 = i2;
                            } while (true);
                            i5 = i5 << k2 | i3;
                            k2 = i2;
                        } else
                        {
                            k2 = i2;
                        }
                        i2 = k2 + 1;
                        l5 = s.charAt(k2);
                        if (l5 >= 55296)
                        {
                            j3 = l5 & 0x1fff;
                            k2 = 13;
                            k5 = i2;
                            do
                            {
                                i2 = k5 + 1;
                                k5 = s.charAt(k5);
                                if (k5 < '\uD800')
                                {
                                    break;
                                }
                                j3 |= (k5 & 0x1fff) << k2;
                                k2 += 13;
                                k5 = i2;
                            } while (true);
                            l5 = k5 << k2 | j3;
                        }
                        l6 = l5 & 0xff;
                        k5 = j2;
                        if ((l5 & 0x400) != 0)
                        {
                            messageinfo[j2] = l4;
                            k5 = j2 + 1;
                        }
                        if (l6 <= FieldType.MAP.id)
                        {
                            break label0;
                        }
                        j3 = i2 + 1;
                        j2 = s.charAt(i2);
                        if (j2 >= 55296)
                        {
                            k2 = j2 & 0x1fff;
                            j2 = 13;
                            do
                            {
                                i2 = j3 + 1;
                                j3 = s.charAt(j3);
                                if (j3 < 55296)
                                {
                                    break;
                                }
                                k2 |= (j3 & 0x1fff) << j2;
                                j2 += 13;
                                j3 = i2;
                            } while (true);
                            j2 = j3 << j2 | k2;
                        } else
                        {
                            i2 = j3;
                        }
                        if (l6 == FieldType.MESSAGE.id + 51 || l6 == FieldType.GROUP.id + 51)
                        {
                            j3 = l4 / 3;
                            k2 = l + 1;
                            aobj1[(j3 << 1) + 1] = aobj[l];
                            l = k2;
                        } else
                        if (l6 == FieldType.ENUM.id + 51 && (k3 & 1) == 1)
                        {
                            j3 = l4 / 3;
                            k2 = l + 1;
                            aobj1[(j3 << 1) + 1] = aobj[l];
                            l = k2;
                        }
                        j2 <<= 1;
                        obj = aobj[j2];
                        if (obj instanceof Field)
                        {
                            obj = (Field)obj;
                        } else
                        {
                            obj = reflectField(class1, (String)obj);
                            aobj[j2] = obj;
                        }
                        j3 = (int)unsafe.objectFieldOffset(((Field) (obj)));
                        j2++;
                        obj = aobj[j2];
                        if (obj instanceof Field)
                        {
                            obj = (Field)obj;
                        } else
                        {
                            obj = reflectField(class1, (String)obj);
                            aobj[j2] = obj;
                        }
                        j2 = (int)unsafe.objectFieldOffset(((Field) (obj)));
                        k2 = 0;
                        i6 = i2;
                        i2 = i;
                        i = l;
                        l = i6;
                    }
                    if (l6 >= 18 && l6 <= 49)
                    {
                        i6 = l1 + 1;
                        messageinfo[l1] = j3;
                        l1 = i6;
                    }
                    i6 = l4 + 1;
                    ai[l4] = i5;
                    j6 = i6 + 1;
                    if ((l5 & 0x200) != 0)
                    {
                        l4 = 0x20000000;
                    } else
                    {
                        l4 = 0;
                    }
                    if ((l5 & 0x100) != 0)
                    {
                        j5 = 0x10000000;
                    } else
                    {
                        j5 = 0;
                    }
                    ai[i6] = j5 | l4 | l6 << 20 | j3;
                    ai[j6] = k2 << 20 | j2;
                    l4 = j6 + 1;
                    k2 = l;
                    l = i;
                    i = i2;
                    j2 = k5;
                }
            }
            return new MessageSchema(ai, aobj1, l3, i4, rawmessageinfo.defaultInstance, flag1, false, messageinfo, k1, k4 + k1, newinstanceschema, listfieldschema, unknownfieldschema, extensionschema, mapfieldschema);
        } else
        {
            messageinfo = (StructuralMessageInfo)messageinfo;
            throw new NoSuchMethodError();
        }
        j2 = l + 1;
        obj = reflectField(class1, (String)aobj[l]);
        if (l6 == FieldType.MESSAGE.id || l6 == FieldType.GROUP.id)
        {
            aobj1[(l4 / 3 << 1) + 1] = ((Field) (obj)).getType();
            l = j2;
        } else
        if (l6 == FieldType.MESSAGE_LIST.id || l6 == FieldType.GROUP_LIST.id)
        {
            aobj1[(l4 / 3 << 1) + 1] = aobj[j2];
            l = j2 + 1;
        } else
        if (l6 == FieldType.ENUM.id || l6 == FieldType.ENUM_LIST.id || l6 == FieldType.ENUM_LIST_PACKED.id)
        {
            if ((k3 & 1) != 1)
            {
                break MISSING_BLOCK_LABEL_2716;
            }
            aobj1[(l4 / 3 << 1) + 1] = aobj[j2];
            l = j2 + 1;
        } else
        {
            if (l6 != FieldType.MAP.id)
            {
                break MISSING_BLOCK_LABEL_2716;
            }
            l = i + 1;
            messageinfo[i] = l4;
            i = l4 / 3;
            k2 = j2 + 1;
            aobj1[i << 1] = aobj[j2];
            if ((l5 & 0x800) != 0)
            {
                aobj1[(l4 / 3 << 1) + 1] = aobj[k2];
                j2 = k2 + 1;
                i = l;
                l = j2;
            } else
            {
                i = l;
                l = k2;
            }
        }
_L2:
        j2 = (int)unsafe.objectFieldOffset(((Field) (obj)));
        if ((k3 & 1) == 1 && l6 <= FieldType.GROUP.id)
        {
            k2 = i2 + 1;
            i2 = s.charAt(i2);
            if (i2 >= 55296)
            {
                j3 = i2 & 0x1fff;
                i2 = 13;
                i6 = k2;
                k2 = i2;
                do
                {
                    i2 = i6 + 1;
                    i6 = s.charAt(i6);
                    if (i6 < '\uD800')
                    {
                        break;
                    }
                    j3 |= (i6 & 0x1fff) << k2;
                    k2 += 13;
                    i6 = i2;
                } while (true);
                k2 = i6 << k2 | j3;
            } else
            {
                j3 = k2;
                k2 = i2;
                i2 = j3;
            }
            j3 = k2 / 32 + (j4 << 1);
            obj = aobj[j3];
            if (obj instanceof Field)
            {
                obj = (Field)obj;
            } else
            {
                obj = reflectField(class1, (String)obj);
                aobj[j3] = obj;
            }
            j6 = (int)unsafe.objectFieldOffset(((Field) (obj)));
            i6 = k2 % 32;
            j3 = j2;
            k2 = l;
            j2 = j6;
            l = i2;
            i2 = i;
            i = k2;
            k2 = i6;
        } else
        {
            k2 = 0;
            j3 = j2;
            j2 = i;
            i6 = 0;
            i = l;
            l = i2;
            i2 = j2;
            j2 = i6;
        }
        break MISSING_BLOCK_LABEL_1845;
        l = j2;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final int parseMapField(Object obj, byte abyte0[], int i, int j, int k, long l, 
            ArrayDecoders.Registers registers)
        throws IOException
    {
        Unsafe unsafe = UNSAFE;
        Object obj3 = objects[k / 3 << 1];
        Object obj1 = unsafe.getObject(obj, l);
        MapEntryLite.Metadata metadata;
        int i1;
        byte byte0;
        int j1;
        if (mapFieldSchema.isImmutable(obj1))
        {
            Object obj2 = mapFieldSchema.newMapField$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0();
            mapFieldSchema.mergeFrom(obj2, obj1);
            unsafe.putObject(obj, l, obj2);
            obj = obj2;
        } else
        {
            obj = obj1;
        }
        metadata = mapFieldSchema.forMapMetadata(obj3);
        obj3 = mapFieldSchema.forMutableMapData(obj);
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
        k = registers.int1;
        if (k < 0 || k > j - i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        j1 = i + k;
        obj = metadata.defaultKey;
        obj1 = metadata.defaultValue;
        if (i >= j1)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = i + 1;
        byte0 = abyte0[i];
        k = byte0;
        i = i1;
        if (byte0 < 0)
        {
            i = ArrayDecoders.decodeVarint32(byte0, abyte0, i1, registers);
            k = registers.int1;
        }
        i1 = k & 7;
        k >>> 3;
        JVM INSTR tableswitch 1 2: default 268
    //                   1 283
    //                   2 321;
           goto _L1 _L2 _L3
_L1:
        i = ArrayDecoders.skipField(k, abyte0, i, j, registers);
        continue; /* Loop/switch isn't completed */
_L2:
        if (i1 != metadata.keyType.wireType) goto _L1; else goto _L4
_L4:
        i = decodeMapEntryValue(abyte0, i, j, metadata.keyType, null, registers);
        obj = registers.object1;
        continue; /* Loop/switch isn't completed */
_L3:
        if (i1 != metadata.valueType.wireType) goto _L1; else goto _L5
_L5:
        i = decodeMapEntryValue(abyte0, i, j, metadata.valueType, metadata.defaultValue.getClass(), registers);
        obj1 = registers.object1;
        if (true) goto _L7; else goto _L6
_L7:
        break MISSING_BLOCK_LABEL_190;
_L6:
        if (i != j1)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        } else
        {
            ((Map) (obj3)).put(obj, obj1);
            return j1;
        }
    }

    private final int parseOneofField(Object obj, byte abyte0[], int i, int j, int k, int l, int i1, 
            int j1, int k1, long l1, int i2, ArrayDecoders.Registers registers)
        throws IOException
    {
        Unsafe unsafe;
        long l2;
        unsafe = UNSAFE;
        l2 = buffer[i2 + 2] & 0xfffff;
        k1;
        JVM INSTR tableswitch 51 68: default 108
    //                   51 110
    //                   52 152
    //                   53 184
    //                   54 184
    //                   55 216
    //                   56 275
    //                   57 305
    //                   58 334
    //                   59 382
    //                   60 538
    //                   61 636
    //                   62 216
    //                   63 666
    //                   64 305
    //                   65 275
    //                   66 787
    //                   67 858
    //                   68 902;
           goto _L1 _L2 _L3 _L4 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L5 _L12 _L7 _L6 _L13 _L14 _L15
_L1:
        return i;
_L2:
        if (i1 != 1) goto _L1; else goto _L16
_L16:
        unsafe.putObject(obj, l1, Double.valueOf(Double.longBitsToDouble(ArrayDecoders.decodeFixed64(abyte0, i))));
        i += 8;
_L18:
        unsafe.putInt(obj, l2, l);
        return i;
_L3:
        if (i1 != 5) goto _L1; else goto _L17
_L17:
        unsafe.putObject(obj, l1, Float.valueOf(Float.intBitsToFloat(ArrayDecoders.decodeFixed32(abyte0, i))));
        i += 4;
          goto _L18
_L4:
        if (i1 != 0) goto _L1; else goto _L19
_L19:
        i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
        unsafe.putObject(obj, l1, Long.valueOf(registers.long1));
          goto _L18
_L5:
        if (i1 != 0) goto _L1; else goto _L20
_L20:
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        unsafe.putObject(obj, l1, Integer.valueOf(registers.int1));
          goto _L18
_L6:
        if (i1 != 1) goto _L1; else goto _L21
_L21:
        unsafe.putObject(obj, l1, Long.valueOf(ArrayDecoders.decodeFixed64(abyte0, i)));
        i += 8;
          goto _L18
_L7:
        if (i1 != 5) goto _L1; else goto _L22
_L22:
        unsafe.putObject(obj, l1, Integer.valueOf(ArrayDecoders.decodeFixed32(abyte0, i)));
        i += 4;
          goto _L18
_L8:
        if (i1 != 0) goto _L1; else goto _L23
_L23:
        i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
        boolean flag;
        if (registers.long1 != 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        unsafe.putObject(obj, l1, Boolean.valueOf(flag));
          goto _L18
_L9:
        if (i1 != 2) goto _L1; else goto _L24
_L24:
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        k = registers.int1;
        if (k == 0)
        {
            unsafe.putObject(obj, l1, "");
        } else
        {
            if ((0x20000000 & j1) != 0)
            {
                if (Utf8.processor.partialIsValidUtf8(0, abyte0, i, i + k) == 0)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j == 0)
                {
                    throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
                }
            }
            unsafe.putObject(obj, l1, new String(abyte0, i, k, Internal.UTF_8));
            i += k;
        }
        unsafe.putInt(obj, l2, l);
        return i;
_L10:
        if (i1 != 2) goto _L1; else goto _L25
_L25:
        i = decodeMessageField(getMessageFieldSchema(i2), abyte0, i, j, registers);
        if (unsafe.getInt(obj, l2) == l)
        {
            abyte0 = ((byte []) (unsafe.getObject(obj, l1)));
        } else
        {
            abyte0 = null;
        }
        if (abyte0 == null)
        {
            unsafe.putObject(obj, l1, registers.object1);
        } else
        {
            unsafe.putObject(obj, l1, Internal.mergeMessage(abyte0, registers.object1));
        }
        unsafe.putInt(obj, l2, l);
        return i;
_L11:
        if (i1 != 2) goto _L1; else goto _L26
_L26:
        i = ArrayDecoders.decodeBytes(abyte0, i, registers);
        unsafe.putObject(obj, l1, registers.object1);
          goto _L18
_L12:
        if (i1 != 0) goto _L1; else goto _L27
_L27:
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        j = registers.int1;
        abyte0 = (Internal.EnumVerifier)objects[(i2 / 3 << 1) + 1];
        if (abyte0 == null || abyte0.isInRange(j))
        {
            unsafe.putObject(obj, l1, Integer.valueOf(j));
            unsafe.putInt(obj, l2, l);
            return i;
        } else
        {
            getMutableUnknownFields(obj).storeField(k, Long.valueOf(j));
            return i;
        }
_L13:
        if (i1 != 0) goto _L1; else goto _L28
_L28:
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        j = registers.int1;
        unsafe.putObject(obj, l1, Integer.valueOf(-(j & 1) ^ j >>> 1));
          goto _L18
_L14:
        if (i1 != 0) goto _L1; else goto _L29
_L29:
        i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
        long l3 = registers.long1;
        unsafe.putObject(obj, l1, Long.valueOf(-(l3 & 1L) ^ l3 >>> 1));
          goto _L18
_L15:
        if (i1 != 3) goto _L1; else goto _L30
_L30:
        i = decodeGroupField(getMessageFieldSchema(i2), abyte0, i, j, k & -8 | 4, registers);
        if (unsafe.getInt(obj, l2) == l)
        {
            abyte0 = ((byte []) (unsafe.getObject(obj, l1)));
        } else
        {
            abyte0 = null;
        }
        if (abyte0 == null)
        {
            unsafe.putObject(obj, l1, registers.object1);
        } else
        {
            unsafe.putObject(obj, l1, Internal.mergeMessage(abyte0, registers.object1));
        }
          goto _L18
    }

    private final int parseProto2Message(Object obj, byte abyte0[], int i, int j, int k, ArrayDecoders.Registers registers)
        throws IOException
    {
        Unsafe unsafe;
        int l;
        int i1;
        int j1;
        int l1;
        int i2;
        unsafe = UNSAFE;
        j1 = -1;
        boolean flag = false;
        l1 = 0;
        i2 = 0;
        i1 = -1;
        l = i;
        i = ((flag) ? 1 : 0);
_L7:
        if (l >= j) goto _L2; else goto _L1
_L1:
        int k1;
        int j3;
        int j2 = l + 1;
        l = abyte0[l];
        k1 = j2;
        l1 = l;
        if (l < 0)
        {
            k1 = ArrayDecoders.decodeVarint32(l, abyte0, j2, registers);
            l1 = registers.int1;
        }
        l = l1 >>> 3;
        j3 = l1 & 7;
        if (l > i1)
        {
            i1 = i2 / 3;
            if (l >= minFieldNumber && l <= maxFieldNumber)
            {
                i1 = slowPositionForFieldNumber(l, i1);
            } else
            {
                i1 = -1;
            }
            i2 = i1;
        } else
        {
            if (l >= minFieldNumber && l <= maxFieldNumber)
            {
                i1 = slowPositionForFieldNumber(l, 0);
            } else
            {
                i1 = -1;
            }
            i2 = i1;
        }
        if (i2 != -1) goto _L4; else goto _L3
_L3:
        i2 = 0;
        i1 = j1;
        j1 = k1;
_L25:
        if (l1 == k && k != 0) goto _L6; else goto _L5
_L5:
        k1 = ArrayDecoders.decodeUnknownField(l1, abyte0, j1, j, getMutableUnknownFields(obj), registers);
        j1 = i1;
        i1 = l;
        l = k1;
          goto _L7
_L4:
        int k3;
        int l3;
        long l4;
        k3 = buffer[i2 + 1];
        l3 = (0xff00000 & k3) >>> 20;
        l4 = 0xfffff & k3;
        if (l3 > 17) goto _L9; else goto _L8
_L8:
        int k2;
        int l2;
        int i3;
        i1 = buffer[i2 + 2];
        i3 = 1 << (i1 >>> 20);
        i1 &= 0xfffff;
        l2 = i;
        k2 = j1;
        if (i1 != j1)
        {
            if (j1 != -1)
            {
                unsafe.putInt(obj, j1, i);
            }
            l2 = unsafe.getInt(obj, i1);
            k2 = i1;
        }
        l3;
        JVM INSTR tableswitch 0 17: default 440
    //                   0 454
    //                   1 510
    //                   2 565
    //                   3 565
    //                   4 616
    //                   5 694
    //                   6 742
    //                   7 789
    //                   8 857
    //                   9 930
    //                   10 1025
    //                   11 616
    //                   12 1077
    //                   13 742
    //                   14 694
    //                   15 1226
    //                   16 1316
    //                   17 1379;
           goto _L10 _L11 _L12 _L13 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L14 _L21 _L16 _L15 _L22 _L23 _L24
_L10:
        i1 = k2;
        j1 = k1;
        i = l2;
          goto _L25
_L11:
        if (j3 != 1) goto _L10; else goto _L26
_L26:
        double d = Double.longBitsToDouble(ArrayDecoders.decodeFixed64(abyte0, k1));
        UnsafeUtil.MEMORY_ACCESSOR.putDouble(obj, l4, d);
        j1 = k1 + 8;
        i = l2 | i3;
        i1 = l;
        l = j1;
        j1 = k2;
          goto _L7
_L12:
        if (j3 != 5) goto _L10; else goto _L27
_L27:
        float f = Float.intBitsToFloat(ArrayDecoders.decodeFixed32(abyte0, k1));
        UnsafeUtil.MEMORY_ACCESSOR.putFloat(obj, l4, f);
        j1 = k1 + 4;
        i = l2 | i3;
        i1 = l;
        l = j1;
        j1 = k2;
          goto _L7
_L13:
        if (j3 != 0) goto _L10; else goto _L28
_L28:
        i = ArrayDecoders.decodeVarint64(abyte0, k1, registers);
        unsafe.putLong(obj, l4, registers.long1);
        j1 = l2 | i3;
        i1 = l;
        l = i;
        i = j1;
        j1 = k2;
          goto _L7
_L14:
        if (j3 != 0) goto _L10; else goto _L29
_L29:
        i = k1 + 1;
        i1 = abyte0[k1];
        if (i1 >= 0)
        {
            registers.int1 = i1;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i1, abyte0, i, registers);
        }
        unsafe.putInt(obj, l4, registers.int1);
        j1 = l2 | i3;
        i1 = l;
        l = i;
        i = j1;
        j1 = k2;
          goto _L7
_L15:
        if (j3 != 1) goto _L10; else goto _L30
_L30:
        unsafe.putLong(obj, l4, ArrayDecoders.decodeFixed64(abyte0, k1));
        j1 = k1 + 8;
        i = l2 | i3;
        i1 = l;
        l = j1;
        j1 = k2;
          goto _L7
_L16:
        if (j3 != 5) goto _L10; else goto _L31
_L31:
        unsafe.putInt(obj, l4, ArrayDecoders.decodeFixed32(abyte0, k1));
        j1 = k1 + 4;
        i = l2 | i3;
        i1 = l;
        l = j1;
        j1 = k2;
          goto _L7
_L17:
        if (j3 != 0) goto _L10; else goto _L32
_L32:
        i = ArrayDecoders.decodeVarint64(abyte0, k1, registers);
        boolean flag1;
        if (registers.long1 != 0L)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        UnsafeUtil.MEMORY_ACCESSOR.putBoolean(obj, l4, flag1);
        j1 = l2 | i3;
        i1 = l;
        l = i;
        i = j1;
        j1 = k2;
          goto _L7
_L18:
        if (j3 != 2) goto _L10; else goto _L33
_L33:
        if ((0x20000000 & k3) == 0)
        {
            i = ArrayDecoders.decodeString(abyte0, k1, registers);
        } else
        {
            i = ArrayDecoders.decodeStringRequireUtf8(abyte0, k1, registers);
        }
        unsafe.putObject(obj, l4, registers.object1);
        j1 = l2 | i3;
        i1 = l;
        l = i;
        i = j1;
        j1 = k2;
          goto _L7
_L19:
        if (j3 != 2) goto _L10; else goto _L34
_L34:
        i = decodeMessageField(getMessageFieldSchema(i2), abyte0, k1, j, registers);
        if ((l2 & i3) == 0)
        {
            unsafe.putObject(obj, l4, registers.object1);
        } else
        {
            unsafe.putObject(obj, l4, Internal.mergeMessage(unsafe.getObject(obj, l4), registers.object1));
        }
        j1 = l2 | i3;
        i1 = l;
        l = i;
        i = j1;
        j1 = k2;
          goto _L7
_L20:
        if (j3 != 2) goto _L10; else goto _L35
_L35:
        i = ArrayDecoders.decodeBytes(abyte0, k1, registers);
        unsafe.putObject(obj, l4, registers.object1);
        j1 = l2 | i3;
        i1 = l;
        l = i;
        i = j1;
        j1 = k2;
          goto _L7
_L21:
        if (j3 != 0) goto _L10; else goto _L36
_L36:
        i = k1 + 1;
        i1 = abyte0[k1];
        Internal.EnumVerifier enumverifier;
        if (i1 >= 0)
        {
            registers.int1 = i1;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i1, abyte0, i, registers);
        }
        i1 = registers.int1;
        enumverifier = (Internal.EnumVerifier)objects[(i2 / 3 << 1) + 1];
        if (enumverifier == null || enumverifier.isInRange(i1))
        {
            unsafe.putInt(obj, l4, i1);
            j1 = l2 | i3;
            i1 = l;
            l = i;
            i = j1;
            j1 = k2;
        } else
        {
            getMutableUnknownFields(obj).storeField(l1, Long.valueOf(i1));
            i1 = l;
            l = i;
            i = l2;
            j1 = k2;
        }
          goto _L7
_L22:
        if (j3 != 0) goto _L10; else goto _L37
_L37:
        i = k1 + 1;
        i1 = abyte0[k1];
        if (i1 >= 0)
        {
            registers.int1 = i1;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i1, abyte0, i, registers);
        }
        i1 = registers.int1;
        unsafe.putInt(obj, l4, -(i1 & 1) ^ i1 >>> 1);
        j1 = l2 | i3;
        i1 = l;
        l = i;
        i = j1;
        j1 = k2;
          goto _L7
_L23:
        if (j3 != 0) goto _L10; else goto _L38
_L38:
        i = ArrayDecoders.decodeVarint64(abyte0, k1, registers);
        long l5 = registers.long1;
        unsafe.putLong(obj, l4, l5 >>> 1 ^ -(l5 & 1L));
        j1 = l2 | i3;
        i1 = l;
        l = i;
        i = j1;
        j1 = k2;
          goto _L7
_L24:
        if (j3 != 3) goto _L10; else goto _L39
_L39:
        i = decodeGroupField(getMessageFieldSchema(i2), abyte0, k1, j, l << 3 | 4, registers);
        if ((l2 & i3) == 0)
        {
            unsafe.putObject(obj, l4, registers.object1);
        } else
        {
            unsafe.putObject(obj, l4, Internal.mergeMessage(unsafe.getObject(obj, l4), registers.object1));
        }
        j1 = l2 | i3;
        i1 = l;
        l = i;
        i = j1;
        j1 = k2;
          goto _L7
_L9:
        if (l3 != 27) goto _L41; else goto _L40
_L40:
        if (j3 != 2) goto _L43; else goto _L42
_L42:
        Internal.ProtobufList protobuflist = (Internal.ProtobufList)unsafe.getObject(obj, l4);
        if (!protobuflist.isModifiable())
        {
            i1 = protobuflist.size();
            if (i1 == 0)
            {
                i1 = 10;
            } else
            {
                i1 <<= 1;
            }
            protobuflist = protobuflist.mutableCopyWithCapacity(i1);
            unsafe.putObject(obj, l4, protobuflist);
        }
        k1 = decodeMessageList(getMessageFieldSchema(i2), l1, abyte0, k1, j, protobuflist, registers);
        i1 = l;
        l = k1;
          goto _L7
_L41:
        if (l3 > 49) goto _L45; else goto _L44
_L44:
        i1 = parseRepeatedField(obj, abyte0, k1, j, l1, l, j3, i2, k3, l3, l4, registers);
        k2 = i1;
        if (i1 != k1) goto _L47; else goto _L46
_L46:
        k1 = j1;
        j1 = i1;
        i1 = k1;
          goto _L25
_L45:
        if (l3 != 50) goto _L49; else goto _L48
_L48:
        if (j3 != 2) goto _L43; else goto _L50
_L50:
        i1 = parseMapField(obj, abyte0, k1, j, i2, l4, registers);
        k2 = i1;
        if (i1 != k1) goto _L47; else goto _L51
_L51:
        k1 = j1;
        j1 = i1;
        i1 = k1;
          goto _L25
_L49:
        i1 = parseOneofField(obj, abyte0, k1, j, l1, l, j3, k3, l3, l4, i2, registers);
        k2 = i1;
        if (i1 != k1) goto _L47; else goto _L52
_L52:
        k1 = j1;
        j1 = i1;
        i1 = k1;
          goto _L25
_L6:
        l = j1;
_L53:
        if (i1 != -1)
        {
            unsafe.putInt(obj, i1, i);
        }
        i = checkInitializedCount;
        abyte0 = null;
        for (; i < repeatedFieldOffsetStart; i++)
        {
            abyte0 = (UnknownFieldSetLite)filterMapUnknownEnumValues(obj, intArray[i], abyte0, unknownFieldSchema);
        }

        if (abyte0 != null)
        {
            unknownFieldSchema.setBuilderToMessage(obj, abyte0);
        }
        if (k == 0)
        {
            if (l != j)
            {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
        } else
        if (l > j || l1 != k)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        return l;
_L47:
        i1 = l;
        l = k2;
          goto _L7
_L43:
        i1 = j1;
        j1 = k1;
          goto _L25
_L2:
        i1 = j1;
          goto _L53
    }

    private final int parseRepeatedField(Object obj, byte abyte0[], int i, int j, int k, int l, int i1, 
            int j1, long l1, int k1, long l2, ArrayDecoders.Registers registers)
        throws IOException
    {
        Internal.ProtobufList protobuflist;
        protobuflist = (Internal.ProtobufList)UNSAFE.getObject(obj, l2);
        if (!protobuflist.isModifiable())
        {
            int i2 = protobuflist.size();
            if (i2 == 0)
            {
                i2 = 10;
            } else
            {
                i2 <<= 1;
            }
            protobuflist = protobuflist.mutableCopyWithCapacity(i2);
            UNSAFE.putObject(obj, l2, protobuflist);
        }
        k1;
        JVM INSTR tableswitch 18 49: default 208
    //                   18 223
    //                   19 461
    //                   20 696
    //                   21 696
    //                   22 932
    //                   23 970
    //                   24 1199
    //                   25 1423
    //                   26 1695
    //                   27 2339
    //                   28 2368
    //                   29 932
    //                   30 2672
    //                   31 1199
    //                   32 970
    //                   33 2776
    //                   34 3129
    //                   35 223
    //                   36 461
    //                   37 696
    //                   38 696
    //                   39 932
    //                   40 970
    //                   41 1199
    //                   42 1423
    //                   43 932
    //                   44 2672
    //                   45 1199
    //                   46 970
    //                   47 2776
    //                   48 3129
    //                   49 3401;
           goto _L1 _L2 _L3 _L4 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L5 _L12 _L7 _L6 _L13 _L14 _L2 _L3 _L4 _L4 _L5 _L6 _L7 _L8 _L5 _L12 _L7 _L6 _L13 _L14 _L15
_L1:
        k1 = i;
_L17:
        return k1;
_L2:
        if (i1 != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (DoubleArrayList)protobuflist;
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        for (j = registers.int1 + i; i < j; i += 8)
        {
            double d = Double.longBitsToDouble(ArrayDecoders.decodeFixed64(abyte0, i));
            ((DoubleArrayList) (obj)).addDouble(((DoubleArrayList) (obj)).size, d);
        }

        k1 = i;
        if (i != j)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (true) goto _L17; else goto _L16
_L16:
        k1 = i;
        if (i1 != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (DoubleArrayList)protobuflist;
        double d1 = Double.longBitsToDouble(ArrayDecoders.decodeFixed64(abyte0, i));
        ((DoubleArrayList) (obj)).addDouble(((DoubleArrayList) (obj)).size, d1);
        i += 8;
        do
        {
            k1 = i;
            if (i >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            i1 = abyte0[i];
            double d2;
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
            }
            k1 = i;
            if (k != registers.int1)
            {
                continue; /* Loop/switch isn't completed */
            }
            d2 = Double.longBitsToDouble(ArrayDecoders.decodeFixed64(abyte0, l));
            ((DoubleArrayList) (obj)).addDouble(((DoubleArrayList) (obj)).size, d2);
            i = l + 8;
        } while (true);
_L3:
        if (i1 != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (FloatArrayList)protobuflist;
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        for (j = registers.int1 + i; i < j; i += 4)
        {
            float f = Float.intBitsToFloat(ArrayDecoders.decodeFixed32(abyte0, i));
            ((FloatArrayList) (obj)).addFloat(((FloatArrayList) (obj)).size, f);
        }

        k1 = i;
        if (i != j)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (true) goto _L17; else goto _L18
_L18:
        k1 = i;
        if (i1 != 5)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (FloatArrayList)protobuflist;
        float f1 = Float.intBitsToFloat(ArrayDecoders.decodeFixed32(abyte0, i));
        ((FloatArrayList) (obj)).addFloat(((FloatArrayList) (obj)).size, f1);
        i += 4;
        do
        {
            k1 = i;
            if (i >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            i1 = abyte0[i];
            float f2;
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
            }
            k1 = i;
            if (k != registers.int1)
            {
                continue; /* Loop/switch isn't completed */
            }
            f2 = Float.intBitsToFloat(ArrayDecoders.decodeFixed32(abyte0, l));
            ((FloatArrayList) (obj)).addFloat(((FloatArrayList) (obj)).size, f2);
            i = l + 4;
        } while (true);
_L4:
        if (i1 != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (LongArrayList)protobuflist;
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        for (j = registers.int1 + i; i < j;)
        {
            i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
            l1 = registers.long1;
            ((LongArrayList) (obj)).addLong(((LongArrayList) (obj)).size, l1);
        }

        k1 = i;
        if (i != j)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (true) goto _L17; else goto _L19
_L19:
        k1 = i;
        if (i1 != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (LongArrayList)protobuflist;
        i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
        l1 = registers.long1;
        ((LongArrayList) (obj)).addLong(((LongArrayList) (obj)).size, l1);
        do
        {
            k1 = i;
            if (i >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            i1 = abyte0[i];
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
            }
            k1 = i;
            if (k != registers.int1)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = ArrayDecoders.decodeVarint64(abyte0, l, registers);
            l1 = registers.long1;
            ((LongArrayList) (obj)).addLong(((LongArrayList) (obj)).size, l1);
        } while (true);
_L5:
        if (i1 == 2)
        {
            return ArrayDecoders.decodePackedVarint32List(abyte0, i, protobuflist, registers);
        }
        k1 = i;
        if (i1 == 0)
        {
            return ArrayDecoders.decodeVarint32List(k, abyte0, i, j, protobuflist, registers);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (i1 != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (LongArrayList)protobuflist;
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        for (j = registers.int1 + i; i < j; i += 8)
        {
            l1 = ArrayDecoders.decodeFixed64(abyte0, i);
            ((LongArrayList) (obj)).addLong(((LongArrayList) (obj)).size, l1);
        }

        k1 = i;
        if (i != j)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (true) goto _L17; else goto _L20
_L20:
        k1 = i;
        if (i1 != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (LongArrayList)protobuflist;
        l1 = ArrayDecoders.decodeFixed64(abyte0, i);
        ((LongArrayList) (obj)).addLong(((LongArrayList) (obj)).size, l1);
        i += 8;
        do
        {
            k1 = i;
            if (i >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            i1 = abyte0[i];
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
            }
            k1 = i;
            if (k != registers.int1)
            {
                continue; /* Loop/switch isn't completed */
            }
            l1 = ArrayDecoders.decodeFixed64(abyte0, l);
            ((LongArrayList) (obj)).addLong(((LongArrayList) (obj)).size, l1);
            i = l + 8;
        } while (true);
_L7:
        if (i1 != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (IntArrayList)protobuflist;
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        for (j = registers.int1 + i; i < j; i += 4)
        {
            k = ArrayDecoders.decodeFixed32(abyte0, i);
            ((IntArrayList) (obj)).addInt(((IntArrayList) (obj)).size, k);
        }

        k1 = i;
        if (i != j)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (true) goto _L17; else goto _L21
_L21:
        k1 = i;
        if (i1 != 5)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (IntArrayList)protobuflist;
        l = ArrayDecoders.decodeFixed32(abyte0, i);
        ((IntArrayList) (obj)).addInt(((IntArrayList) (obj)).size, l);
        i += 4;
        do
        {
            k1 = i;
            if (i >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            i1 = abyte0[i];
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
            }
            k1 = i;
            if (k != registers.int1)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = ArrayDecoders.decodeFixed32(abyte0, l);
            ((IntArrayList) (obj)).addInt(((IntArrayList) (obj)).size, i);
            i = l + 4;
        } while (true);
_L8:
        if (i1 != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (BooleanArrayList)protobuflist;
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        j = registers.int1 + i;
        while (i < j) 
        {
            i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
            boolean flag;
            if (registers.long1 != 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            ((BooleanArrayList) (obj)).addBoolean(((BooleanArrayList) (obj)).size, flag);
        }
        k1 = i;
        if (i != j)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (true) goto _L17; else goto _L22
_L22:
        k1 = i;
        if (i1 != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (BooleanArrayList)protobuflist;
        i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
        boolean flag1;
        if (registers.long1 != 0L)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        ((BooleanArrayList) (obj)).addBoolean(((BooleanArrayList) (obj)).size, flag1);
        do
        {
            k1 = i;
            if (i >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            i1 = abyte0[i];
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
            }
            k1 = i;
            if (k != registers.int1)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = ArrayDecoders.decodeVarint64(abyte0, l, registers);
            if (registers.long1 != 0L)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            ((BooleanArrayList) (obj)).addBoolean(((BooleanArrayList) (obj)).size, flag1);
        } while (true);
_L9:
        k1 = i;
        if (i1 != 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        if ((0x20000000L & l1) == 0L)
        {
            l = i + 1;
            i = abyte0[i];
            if (i >= 0)
            {
                registers.int1 = i;
                i = l;
            } else
            {
                i = ArrayDecoders.decodeVarint32(i, abyte0, l, registers);
            }
            l = registers.int1;
            if (l < 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            if (l == 0)
            {
                protobuflist.add("");
            } else
            {
                protobuflist.add(new String(abyte0, i, l, Internal.UTF_8));
                i += l;
            }
            do
            {
                k1 = i;
                if (i >= j)
                {
                    continue; /* Loop/switch isn't completed */
                }
                l = i + 1;
                i1 = abyte0[i];
                if (i1 >= 0)
                {
                    registers.int1 = i1;
                } else
                {
                    l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
                }
                k1 = i;
                if (k != registers.int1)
                {
                    continue; /* Loop/switch isn't completed */
                }
                i = l + 1;
                l = abyte0[l];
                if (l >= 0)
                {
                    registers.int1 = l;
                } else
                {
                    i = ArrayDecoders.decodeVarint32(l, abyte0, i, registers);
                }
                l = registers.int1;
                if (l < 0)
                {
                    throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                }
                if (l == 0)
                {
                    protobuflist.add("");
                } else
                {
                    protobuflist.add(new String(abyte0, i, l, Internal.UTF_8));
                    i += l;
                }
            } while (true);
        }
        l = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = l;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, l, registers);
        }
        i1 = registers.int1;
        if (i1 < 0)
        {
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (i1 == 0)
        {
            protobuflist.add("");
        } else
        {
            if (Utf8.processor.partialIsValidUtf8(0, abyte0, i, i + i1) == 0)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            if (l == 0)
            {
                throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
            }
            protobuflist.add(new String(abyte0, i, i1, Internal.UTF_8));
            i += i1;
        }
        do
        {
            k1 = i;
            if (i >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            i1 = abyte0[i];
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
            }
            k1 = i;
            if (k != registers.int1)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = l + 1;
            l = abyte0[l];
            if (l >= 0)
            {
                registers.int1 = l;
            } else
            {
                i = ArrayDecoders.decodeVarint32(l, abyte0, i, registers);
            }
            i1 = registers.int1;
            if (i1 < 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            if (i1 == 0)
            {
                protobuflist.add("");
            } else
            {
                if (Utf8.processor.partialIsValidUtf8(0, abyte0, i, i + i1) == 0)
                {
                    l = 1;
                } else
                {
                    l = 0;
                }
                if (l == 0)
                {
                    throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
                }
                protobuflist.add(new String(abyte0, i, i1, Internal.UTF_8));
                i += i1;
            }
        } while (true);
_L10:
        k1 = i;
        if (i1 == 2)
        {
            return decodeMessageList(getMessageFieldSchema(j1), k, abyte0, i, j, protobuflist, registers);
        }
        continue; /* Loop/switch isn't completed */
_L11:
        k1 = i;
        if (i1 != 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        l = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = l;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, l, registers);
        }
        l = registers.int1;
        if (l < 0)
        {
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (l > abyte0.length - i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (l == 0)
        {
            protobuflist.add(ByteString.EMPTY);
        } else
        {
            protobuflist.add(ByteString.copyFrom(abyte0, i, l));
            i += l;
        }
        do
        {
            k1 = i;
            if (i >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            i1 = abyte0[i];
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
            }
            k1 = i;
            if (k != registers.int1)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = l + 1;
            l = abyte0[l];
            if (l >= 0)
            {
                registers.int1 = l;
            } else
            {
                i = ArrayDecoders.decodeVarint32(l, abyte0, i, registers);
            }
            l = registers.int1;
            if (l < 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            if (l > abyte0.length - i)
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            if (l == 0)
            {
                protobuflist.add(ByteString.EMPTY);
            } else
            {
                protobuflist.add(ByteString.copyFrom(abyte0, i, l));
                i += l;
            }
        } while (true);
_L12:
        if (i1 == 2)
        {
            i = ArrayDecoders.decodePackedVarint32List(abyte0, i, protobuflist, registers);
        } else
        {
            k1 = i;
            if (i1 != 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = ArrayDecoders.decodeVarint32List(k, abyte0, i, j, protobuflist, registers);
        }
        abyte0 = ((GeneratedMessageLite)obj).unknownFields;
        if (abyte0 == UnknownFieldSetLite.DEFAULT_INSTANCE)
        {
            abyte0 = null;
        }
        abyte0 = (UnknownFieldSetLite)SchemaUtil.filterUnknownEnumList(l, protobuflist, (Internal.EnumVerifier)objects[(j1 / 3 << 1) + 1], abyte0, unknownFieldSchema);
        if (abyte0 != null)
        {
            ((GeneratedMessageLite)obj).unknownFields = abyte0;
            return i;
        } else
        {
            return i;
        }
_L13:
        if (i1 != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (IntArrayList)protobuflist;
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        k = registers.int1 + i;
        while (i < k) 
        {
            j = i + 1;
            i = abyte0[i];
            if (i >= 0)
            {
                registers.int1 = i;
                i = j;
            } else
            {
                i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
            }
            j = registers.int1;
            l = -(j & 1);
            ((IntArrayList) (obj)).addInt(((IntArrayList) (obj)).size, l ^ j >>> 1);
        }
        k1 = i;
        if (i != k)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (true) goto _L17; else goto _L23
_L23:
        k1 = i;
        if (i1 != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (IntArrayList)protobuflist;
        l = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = l;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, l, registers);
        }
        l = registers.int1;
        i1 = -(l & 1);
        ((IntArrayList) (obj)).addInt(((IntArrayList) (obj)).size, i1 ^ l >>> 1);
        do
        {
            k1 = i;
            if (i >= j)
            {
                continue; /* Loop/switch isn't completed */
            }
            l = i + 1;
            i1 = abyte0[i];
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
            }
            k1 = i;
            if (k != registers.int1)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = l + 1;
            l = abyte0[l];
            if (l >= 0)
            {
                registers.int1 = l;
            } else
            {
                i = ArrayDecoders.decodeVarint32(l, abyte0, i, registers);
            }
            l = registers.int1;
            i1 = -(l & 1);
            ((IntArrayList) (obj)).addInt(((IntArrayList) (obj)).size, i1 ^ l >>> 1);
        } while (true);
_L14:
        if (i1 != 2)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (LongArrayList)protobuflist;
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = ArrayDecoders.decodeVarint32(i, abyte0, j, registers);
        }
        for (j = registers.int1 + i; i < j;)
        {
            i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
            l1 = registers.long1;
            l2 = -(l1 & 1L);
            ((LongArrayList) (obj)).addLong(((LongArrayList) (obj)).size, l2 ^ l1 >>> 1);
        }

        k1 = i;
        if (i != j)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (true) goto _L17; else goto _L24
_L24:
        k1 = i;
        if (i1 != 0) goto _L17; else goto _L25
_L25:
        obj = (LongArrayList)protobuflist;
        i = ArrayDecoders.decodeVarint64(abyte0, i, registers);
        l1 = registers.long1;
        l2 = -(l1 & 1L);
        ((LongArrayList) (obj)).addLong(((LongArrayList) (obj)).size, l2 ^ l1 >>> 1);
_L28:
        k1 = i;
        if (i >= j) goto _L17; else goto _L26
_L26:
        l = i + 1;
        i1 = abyte0[i];
        if (i1 >= 0)
        {
            registers.int1 = i1;
        } else
        {
            l = ArrayDecoders.decodeVarint32(i1, abyte0, l, registers);
        }
        k1 = i;
        if (k != registers.int1) goto _L17; else goto _L27
_L27:
        i = ArrayDecoders.decodeVarint64(abyte0, l, registers);
        l1 = registers.long1;
        l2 = -(l1 & 1L);
        ((LongArrayList) (obj)).addLong(((LongArrayList) (obj)).size, l2 ^ l1 >>> 1);
          goto _L28
_L15:
        k1 = i;
        if (i1 != 3) goto _L17; else goto _L29
_L29:
        obj = getMessageFieldSchema(j1);
        i1 = k & -8 | 4;
        i = decodeGroupField(((Schema) (obj)), abyte0, i, j, i1, registers);
        protobuflist.add(registers.object1);
_L32:
        k1 = i;
        if (i >= j) goto _L17; else goto _L30
_L30:
        l = i + 1;
        j1 = abyte0[i];
        if (j1 >= 0)
        {
            registers.int1 = j1;
        } else
        {
            l = ArrayDecoders.decodeVarint32(j1, abyte0, l, registers);
        }
        k1 = i;
        if (k != registers.int1) goto _L17; else goto _L31
_L31:
        i = decodeGroupField(((Schema) (obj)), abyte0, l, j, i1, registers);
        protobuflist.add(registers.object1);
          goto _L32
    }

    private final void readString(Object obj, int i, Reader reader)
        throws IOException
    {
        boolean flag;
        if ((0x20000000 & i) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            long l = i & 0xfffff;
            reader = reader.readStringRequireUtf8();
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, reader);
            return;
        }
        if (lite)
        {
            long l1 = i & 0xfffff;
            reader = reader.readString();
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l1, reader);
            return;
        } else
        {
            long l2 = i & 0xfffff;
            reader = reader.readBytes();
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, reader);
            return;
        }
    }

    private static Field reflectField(Class class1, String s)
    {
        Field field = class1.getDeclaredField(s);
_L2:
        return field;
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        Field afield[];
        afield = class1.getDeclaredFields();
        int j = afield.length;
        int i = 0;
label0:
        do
        {
label1:
            {
                if (i >= j)
                {
                    break label1;
                }
                Field field1 = afield[i];
                nosuchfieldexception = field1;
                if (s.equals(field1.getName()))
                {
                    break label0;
                }
                i++;
            }
        } while (true);
        if (true) goto _L2; else goto _L1
_L1:
        class1 = class1.getName();
        String s1 = Arrays.toString(afield);
        throw new RuntimeException((new StringBuilder(String.valueOf(s).length() + 40 + String.valueOf(class1).length() + String.valueOf(s1).length())).append("Field ").append(s).append(" for ").append(class1).append(" not found. Known fields are ").append(s1).toString());
    }

    private final void setFieldPresent(Object obj, int i)
    {
        if (proto3)
        {
            return;
        } else
        {
            i = buffer[i + 2];
            long l = i & 0xfffff;
            int j = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l, j | 1 << (i >>> 20));
            return;
        }
    }

    private final int slowPositionForFieldNumber(int i, int j)
    {
        for (int k = buffer.length / 3 - 1; j <= k;)
        {
            int l = k + j >>> 1;
            int i1 = l * 3;
            int j1 = buffer[i1];
            if (i == j1)
            {
                return i1;
            }
            if (i < j1)
            {
                k = l - 1;
            } else
            {
                j = l + 1;
            }
        }

        return -1;
    }

    private final void writeFieldsInAscendingOrderProto2(Object obj, Writer writer)
        throws IOException
    {
        Object obj1;
        Iterator iterator;
        Unsafe unsafe;
        int i;
        int j;
        int k;
        int l1;
        unsafe = null;
        Object obj2 = null;
        obj1 = obj2;
        iterator = unsafe;
        if (hasExtensions)
        {
            FieldSet fieldset = extensionSchema.getExtensions(obj);
            obj1 = obj2;
            iterator = unsafe;
            if (!fieldset.fields.isEmpty())
            {
                iterator = fieldset.iterator();
                obj1 = (java.util.Map.Entry)iterator.next();
            }
        }
        l1 = buffer.length;
        unsafe = UNSAFE;
        k = 0;
        j = -1;
        i = 0;
_L73:
        if (k >= l1) goto _L2; else goto _L1
_L1:
        int i2 = buffer[k + 1];
        int j2 = buffer[k];
        int k2 = (0xff00000 & i2) >>> 20;
        int k1 = 0;
        int i1;
        long l2;
        long l3;
        if (!proto3 && k2 <= 17)
        {
            k1 = buffer[k + 2];
            int l = 0xfffff & k1;
            if (l != j)
            {
                i = unsafe.getInt(obj, l);
                j = l;
            }
            k1 = 1 << (k1 >>> 20);
            l = i;
            i = j;
            j = l;
        } else
        {
            int j1 = j;
            j = i;
            i = j1;
        }
        while (obj1 != null && extensionSchema.extensionNumber(((java.util.Map.Entry) (obj1))) <= j2) 
        {
            extensionSchema.serializeExtension(writer, ((java.util.Map.Entry) (obj1)));
            if (iterator.hasNext())
            {
                obj1 = (java.util.Map.Entry)iterator.next();
            } else
            {
                obj1 = null;
            }
        }
        l2 = 0xfffff & i2;
        k2;
        JVM INSTR tableswitch 0 68: default 560
    //                   0 585
    //                   1 613
    //                   2 641
    //                   3 668
    //                   4 695
    //                   5 722
    //                   6 749
    //                   7 776
    //                   8 804
    //                   9 829
    //                   10 862
    //                   11 892
    //                   12 919
    //                   13 946
    //                   14 973
    //                   15 1000
    //                   16 1027
    //                   17 1054
    //                   18 1087
    //                   19 1113
    //                   20 1139
    //                   21 1165
    //                   22 1191
    //                   23 1217
    //                   24 1243
    //                   25 1269
    //                   26 1295
    //                   27 1320
    //                   28 1351
    //                   29 1376
    //                   30 1402
    //                   31 1428
    //                   32 1454
    //                   33 1480
    //                   34 1506
    //                   35 1532
    //                   36 1558
    //                   37 1584
    //                   38 1610
    //                   39 1636
    //                   40 1662
    //                   41 1688
    //                   42 1714
    //                   43 1740
    //                   44 1766
    //                   45 1792
    //                   46 1818
    //                   47 1844
    //                   48 1870
    //                   49 1896
    //                   50 1927
    //                   51 1947
    //                   52 2008
    //                   53 2069
    //                   54 2130
    //                   55 2191
    //                   56 2252
    //                   57 2313
    //                   58 2374
    //                   59 2435
    //                   60 2484
    //                   61 2541
    //                   62 2595
    //                   63 2656
    //                   64 2717
    //                   65 2778
    //                   66 2839
    //                   67 2900
    //                   68 2961;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70 _L71 _L72
_L3:
        break; /* Loop/switch isn't completed */
_L72:
        break MISSING_BLOCK_LABEL_2961;
_L74:
        i1 = k + 3;
        k = i;
        i = j;
        j = k;
        k = i1;
          goto _L73
_L4:
        if ((j & k1) != 0)
        {
            writer.writeDouble(j2, UnsafeUtil.MEMORY_ACCESSOR.getDouble(obj, l2));
        }
          goto _L74
_L5:
        if ((j & k1) != 0)
        {
            writer.writeFloat(j2, UnsafeUtil.MEMORY_ACCESSOR.getFloat(obj, l2));
        }
          goto _L74
_L6:
        if ((j & k1) != 0)
        {
            writer.writeInt64(j2, unsafe.getLong(obj, l2));
        }
          goto _L74
_L7:
        if ((j & k1) != 0)
        {
            writer.writeUInt64(j2, unsafe.getLong(obj, l2));
        }
          goto _L74
_L8:
        if ((j & k1) != 0)
        {
            writer.writeInt32(j2, unsafe.getInt(obj, l2));
        }
          goto _L74
_L9:
        if ((j & k1) != 0)
        {
            writer.writeFixed64(j2, unsafe.getLong(obj, l2));
        }
          goto _L74
_L10:
        if ((j & k1) != 0)
        {
            writer.writeFixed32(j2, unsafe.getInt(obj, l2));
        }
          goto _L74
_L11:
        if ((j & k1) != 0)
        {
            writer.writeBool(j2, UnsafeUtil.MEMORY_ACCESSOR.getBoolean(obj, l2));
        }
          goto _L74
_L12:
        if ((j & k1) != 0)
        {
            writeString(j2, unsafe.getObject(obj, l2), writer);
        }
          goto _L74
_L13:
        if ((j & k1) != 0)
        {
            writer.writeMessage(j2, unsafe.getObject(obj, l2), getMessageFieldSchema(k));
        }
          goto _L74
_L14:
        if ((j & k1) != 0)
        {
            writer.writeBytes(j2, (ByteString)unsafe.getObject(obj, l2));
        }
          goto _L74
_L15:
        if ((j & k1) != 0)
        {
            writer.writeUInt32(j2, unsafe.getInt(obj, l2));
        }
          goto _L74
_L16:
        if ((j & k1) != 0)
        {
            writer.writeEnum(j2, unsafe.getInt(obj, l2));
        }
          goto _L74
_L17:
        if ((j & k1) != 0)
        {
            writer.writeSFixed32(j2, unsafe.getInt(obj, l2));
        }
          goto _L74
_L18:
        if ((j & k1) != 0)
        {
            writer.writeSFixed64(j2, unsafe.getLong(obj, l2));
        }
          goto _L74
_L19:
        if ((j & k1) != 0)
        {
            writer.writeSInt32(j2, unsafe.getInt(obj, l2));
        }
          goto _L74
_L20:
        if ((j & k1) != 0)
        {
            writer.writeSInt64(j2, unsafe.getLong(obj, l2));
        }
          goto _L74
_L21:
        if ((j & k1) != 0)
        {
            writer.writeGroup(j2, unsafe.getObject(obj, l2), getMessageFieldSchema(k));
        }
          goto _L74
_L22:
        SchemaUtil.writeDoubleList(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L23:
        SchemaUtil.writeFloatList(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L24:
        SchemaUtil.writeInt64List(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L25:
        SchemaUtil.writeUInt64List(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L26:
        SchemaUtil.writeInt32List(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L27:
        SchemaUtil.writeFixed64List(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L28:
        SchemaUtil.writeFixed32List(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L29:
        SchemaUtil.writeBoolList(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L30:
        SchemaUtil.writeStringList(buffer[k], (List)unsafe.getObject(obj, l2), writer);
          goto _L74
_L31:
        SchemaUtil.writeMessageList(buffer[k], (List)unsafe.getObject(obj, l2), writer, getMessageFieldSchema(k));
          goto _L74
_L32:
        SchemaUtil.writeBytesList(buffer[k], (List)unsafe.getObject(obj, l2), writer);
          goto _L74
_L33:
        SchemaUtil.writeUInt32List(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L34:
        SchemaUtil.writeEnumList(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L35:
        SchemaUtil.writeSFixed32List(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L36:
        SchemaUtil.writeSFixed64List(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L37:
        SchemaUtil.writeSInt32List(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L38:
        SchemaUtil.writeSInt64List(buffer[k], (List)unsafe.getObject(obj, l2), writer, false);
          goto _L74
_L39:
        SchemaUtil.writeDoubleList(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L40:
        SchemaUtil.writeFloatList(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L41:
        SchemaUtil.writeInt64List(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L42:
        SchemaUtil.writeUInt64List(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L43:
        SchemaUtil.writeInt32List(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L44:
        SchemaUtil.writeFixed64List(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L45:
        SchemaUtil.writeFixed32List(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L46:
        SchemaUtil.writeBoolList(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L47:
        SchemaUtil.writeUInt32List(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L48:
        SchemaUtil.writeEnumList(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L49:
        SchemaUtil.writeSFixed32List(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L50:
        SchemaUtil.writeSFixed64List(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L51:
        SchemaUtil.writeSInt32List(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L52:
        SchemaUtil.writeSInt64List(buffer[k], (List)unsafe.getObject(obj, l2), writer, true);
          goto _L74
_L53:
        SchemaUtil.writeGroupList(buffer[k], (List)unsafe.getObject(obj, l2), writer, getMessageFieldSchema(k));
          goto _L74
_L54:
        writeMapHelper(writer, j2, unsafe.getObject(obj, l2), k);
          goto _L74
_L55:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeDouble(j2, ((Double)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).doubleValue());
        }
          goto _L74
_L56:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeFloat(j2, ((Float)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).floatValue());
        }
          goto _L74
_L57:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeInt64(j2, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).longValue());
        }
          goto _L74
_L58:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeUInt64(j2, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).longValue());
        }
          goto _L74
_L59:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeInt32(j2, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).intValue());
        }
          goto _L74
_L60:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeFixed64(j2, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).longValue());
        }
          goto _L74
_L61:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeFixed32(j2, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).intValue());
        }
          goto _L74
_L62:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeBool(j2, ((Boolean)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).booleanValue());
        }
          goto _L74
_L63:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writeString(j2, unsafe.getObject(obj, l2), writer);
        }
          goto _L74
_L64:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeMessage(j2, unsafe.getObject(obj, l2), getMessageFieldSchema(k));
        }
          goto _L74
_L65:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeBytes(j2, (ByteString)unsafe.getObject(obj, l2));
        }
          goto _L74
_L66:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeUInt32(j2, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).intValue());
        }
          goto _L74
_L67:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeEnum(j2, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).intValue());
        }
          goto _L74
_L68:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeSFixed32(j2, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).intValue());
        }
          goto _L74
_L69:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeSFixed64(j2, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).longValue());
        }
          goto _L74
_L70:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeSInt32(j2, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).intValue());
        }
          goto _L74
_L71:
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeSInt64(j2, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)).longValue());
        }
          goto _L74
        l3 = buffer[k + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l3) == j2)
        {
            writer.writeGroup(j2, unsafe.getObject(obj, l2), getMessageFieldSchema(k));
        }
          goto _L74
_L76:
        obj1 = null;
_L2:
label0:
        do
        {
label1:
            {
                if (obj1 == null)
                {
                    break label1;
                }
                extensionSchema.serializeExtension(writer, ((java.util.Map.Entry) (obj1)));
                if (!iterator.hasNext())
                {
                    break label0;
                }
                obj1 = (java.util.Map.Entry)iterator.next();
            }
        } while (true);
        if (true) goto _L76; else goto _L75
_L75:
        obj1 = unknownFieldSchema;
        ((UnknownFieldSchema) (obj1)).writeTo(((UnknownFieldSchema) (obj1)).getFromMessage(obj), writer);
        return;
    }

    private final void writeFieldsInAscendingOrderProto3(Object obj, Writer writer)
        throws IOException
    {
        java.util.Map.Entry entry;
        Iterator iterator;
        int i;
        int j;
        Object obj1 = null;
        Object obj2 = null;
        entry = obj2;
        iterator = obj1;
        if (hasExtensions)
        {
            FieldSet fieldset = extensionSchema.getExtensions(obj);
            entry = obj2;
            iterator = obj1;
            if (!fieldset.fields.isEmpty())
            {
                iterator = fieldset.iterator();
                entry = (java.util.Map.Entry)iterator.next();
            }
        }
        j = buffer.length;
        i = 0;
_L73:
        java.util.Map.Entry entry1 = entry;
        if (i >= j) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        k = buffer[i + 1];
        for (l = buffer[i]; entry != null && extensionSchema.extensionNumber(entry) <= l;)
        {
            extensionSchema.serializeExtension(writer, entry);
            if (iterator.hasNext())
            {
                entry = (java.util.Map.Entry)iterator.next();
            } else
            {
                entry = null;
            }
        }

        (0xff00000 & k) >>> 20;
        JVM INSTR tableswitch 0 68: default 460
    //                   0 469
    //                   1 507
    //                   2 545
    //                   3 586
    //                   4 627
    //                   5 668
    //                   6 709
    //                   7 750
    //                   8 788
    //                   9 827
    //                   10 874
    //                   11 918
    //                   12 959
    //                   13 1000
    //                   14 1041
    //                   15 1082
    //                   16 1123
    //                   17 1164
    //                   18 1211
    //                   19 1253
    //                   20 1295
    //                   21 1337
    //                   22 1379
    //                   23 1421
    //                   24 1463
    //                   25 1505
    //                   26 1547
    //                   27 1588
    //                   28 1635
    //                   29 1676
    //                   30 1718
    //                   31 1760
    //                   32 1802
    //                   33 1844
    //                   34 1886
    //                   35 1928
    //                   36 1970
    //                   37 2012
    //                   38 2054
    //                   39 2096
    //                   40 2138
    //                   41 2180
    //                   42 2222
    //                   43 2264
    //                   44 2306
    //                   45 2348
    //                   46 2390
    //                   47 2432
    //                   48 2474
    //                   49 2516
    //                   50 2563
    //                   51 2595
    //                   52 2664
    //                   53 2733
    //                   54 2802
    //                   55 2871
    //                   56 2940
    //                   57 3009
    //                   58 3078
    //                   59 3147
    //                   60 3208
    //                   61 3277
    //                   62 3343
    //                   63 3412
    //                   64 3481
    //                   65 3550
    //                   66 3619
    //                   67 3688
    //                   68 3757;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70 _L71 _L72
_L3:
        break; /* Loop/switch isn't completed */
_L72:
        break MISSING_BLOCK_LABEL_3757;
_L74:
        i += 3;
          goto _L73
_L4:
        if (isFieldPresent(obj, i))
        {
            long l1 = 0xfffff & k;
            writer.writeDouble(l, UnsafeUtil.MEMORY_ACCESSOR.getDouble(obj, l1));
        }
          goto _L74
_L5:
        if (isFieldPresent(obj, i))
        {
            long l2 = 0xfffff & k;
            writer.writeFloat(l, UnsafeUtil.MEMORY_ACCESSOR.getFloat(obj, l2));
        }
          goto _L74
_L6:
        if (isFieldPresent(obj, i))
        {
            long l3 = 0xfffff & k;
            writer.writeInt64(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l3));
        }
          goto _L74
_L7:
        if (isFieldPresent(obj, i))
        {
            long l4 = 0xfffff & k;
            writer.writeUInt64(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l4));
        }
          goto _L74
_L8:
        if (isFieldPresent(obj, i))
        {
            long l5 = 0xfffff & k;
            writer.writeInt32(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l5));
        }
          goto _L74
_L9:
        if (isFieldPresent(obj, i))
        {
            long l6 = 0xfffff & k;
            writer.writeFixed64(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l6));
        }
          goto _L74
_L10:
        if (isFieldPresent(obj, i))
        {
            long l7 = 0xfffff & k;
            writer.writeFixed32(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l7));
        }
          goto _L74
_L11:
        if (isFieldPresent(obj, i))
        {
            long l8 = 0xfffff & k;
            writer.writeBool(l, UnsafeUtil.MEMORY_ACCESSOR.getBoolean(obj, l8));
        }
          goto _L74
_L12:
        if (isFieldPresent(obj, i))
        {
            long l9 = 0xfffff & k;
            writeString(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l9), writer);
        }
          goto _L74
_L13:
        if (isFieldPresent(obj, i))
        {
            long l10 = 0xfffff & k;
            writer.writeMessage(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l10), getMessageFieldSchema(i));
        }
          goto _L74
_L14:
        if (isFieldPresent(obj, i))
        {
            long l11 = 0xfffff & k;
            writer.writeBytes(l, (ByteString)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l11));
        }
          goto _L74
_L15:
        if (isFieldPresent(obj, i))
        {
            long l12 = 0xfffff & k;
            writer.writeUInt32(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l12));
        }
          goto _L74
_L16:
        if (isFieldPresent(obj, i))
        {
            long l13 = 0xfffff & k;
            writer.writeEnum(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l13));
        }
          goto _L74
_L17:
        if (isFieldPresent(obj, i))
        {
            long l14 = 0xfffff & k;
            writer.writeSFixed32(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l14));
        }
          goto _L74
_L18:
        if (isFieldPresent(obj, i))
        {
            long l15 = 0xfffff & k;
            writer.writeSFixed64(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l15));
        }
          goto _L74
_L19:
        if (isFieldPresent(obj, i))
        {
            long l16 = 0xfffff & k;
            writer.writeSInt32(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l16));
        }
          goto _L74
_L20:
        if (isFieldPresent(obj, i))
        {
            long l17 = 0xfffff & k;
            writer.writeSInt64(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l17));
        }
          goto _L74
_L21:
        if (isFieldPresent(obj, i))
        {
            long l18 = 0xfffff & k;
            writer.writeGroup(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l18), getMessageFieldSchema(i));
        }
          goto _L74
_L22:
        l = buffer[i];
        long l19 = 0xfffff & k;
        SchemaUtil.writeDoubleList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l19), writer, false);
          goto _L74
_L23:
        l = buffer[i];
        long l20 = 0xfffff & k;
        SchemaUtil.writeFloatList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l20), writer, false);
          goto _L74
_L24:
        l = buffer[i];
        long l21 = 0xfffff & k;
        SchemaUtil.writeInt64List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l21), writer, false);
          goto _L74
_L25:
        l = buffer[i];
        long l22 = 0xfffff & k;
        SchemaUtil.writeUInt64List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l22), writer, false);
          goto _L74
_L26:
        l = buffer[i];
        long l23 = 0xfffff & k;
        SchemaUtil.writeInt32List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l23), writer, false);
          goto _L74
_L27:
        l = buffer[i];
        long l24 = 0xfffff & k;
        SchemaUtil.writeFixed64List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l24), writer, false);
          goto _L74
_L28:
        l = buffer[i];
        long l25 = 0xfffff & k;
        SchemaUtil.writeFixed32List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l25), writer, false);
          goto _L74
_L29:
        l = buffer[i];
        long l26 = 0xfffff & k;
        SchemaUtil.writeBoolList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l26), writer, false);
          goto _L74
_L30:
        l = buffer[i];
        long l27 = 0xfffff & k;
        SchemaUtil.writeStringList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l27), writer);
          goto _L74
_L31:
        l = buffer[i];
        long l28 = 0xfffff & k;
        SchemaUtil.writeMessageList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l28), writer, getMessageFieldSchema(i));
          goto _L74
_L32:
        l = buffer[i];
        long l29 = 0xfffff & k;
        SchemaUtil.writeBytesList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l29), writer);
          goto _L74
_L33:
        l = buffer[i];
        long l30 = 0xfffff & k;
        SchemaUtil.writeUInt32List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l30), writer, false);
          goto _L74
_L34:
        l = buffer[i];
        long l31 = 0xfffff & k;
        SchemaUtil.writeEnumList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l31), writer, false);
          goto _L74
_L35:
        l = buffer[i];
        long l32 = 0xfffff & k;
        SchemaUtil.writeSFixed32List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l32), writer, false);
          goto _L74
_L36:
        l = buffer[i];
        long l33 = 0xfffff & k;
        SchemaUtil.writeSFixed64List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l33), writer, false);
          goto _L74
_L37:
        l = buffer[i];
        long l34 = 0xfffff & k;
        SchemaUtil.writeSInt32List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l34), writer, false);
          goto _L74
_L38:
        l = buffer[i];
        long l35 = 0xfffff & k;
        SchemaUtil.writeSInt64List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l35), writer, false);
          goto _L74
_L39:
        l = buffer[i];
        long l36 = 0xfffff & k;
        SchemaUtil.writeDoubleList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l36), writer, true);
          goto _L74
_L40:
        l = buffer[i];
        long l37 = 0xfffff & k;
        SchemaUtil.writeFloatList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l37), writer, true);
          goto _L74
_L41:
        l = buffer[i];
        long l38 = 0xfffff & k;
        SchemaUtil.writeInt64List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l38), writer, true);
          goto _L74
_L42:
        l = buffer[i];
        long l39 = 0xfffff & k;
        SchemaUtil.writeUInt64List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l39), writer, true);
          goto _L74
_L43:
        l = buffer[i];
        long l40 = 0xfffff & k;
        SchemaUtil.writeInt32List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l40), writer, true);
          goto _L74
_L44:
        l = buffer[i];
        long l41 = 0xfffff & k;
        SchemaUtil.writeFixed64List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l41), writer, true);
          goto _L74
_L45:
        l = buffer[i];
        long l42 = 0xfffff & k;
        SchemaUtil.writeFixed32List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l42), writer, true);
          goto _L74
_L46:
        l = buffer[i];
        long l43 = 0xfffff & k;
        SchemaUtil.writeBoolList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l43), writer, true);
          goto _L74
_L47:
        l = buffer[i];
        long l44 = 0xfffff & k;
        SchemaUtil.writeUInt32List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l44), writer, true);
          goto _L74
_L48:
        l = buffer[i];
        long l45 = 0xfffff & k;
        SchemaUtil.writeEnumList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l45), writer, true);
          goto _L74
_L49:
        l = buffer[i];
        long l46 = 0xfffff & k;
        SchemaUtil.writeSFixed32List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l46), writer, true);
          goto _L74
_L50:
        l = buffer[i];
        long l47 = 0xfffff & k;
        SchemaUtil.writeSFixed64List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l47), writer, true);
          goto _L74
_L51:
        l = buffer[i];
        long l48 = 0xfffff & k;
        SchemaUtil.writeSInt32List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l48), writer, true);
          goto _L74
_L52:
        l = buffer[i];
        long l49 = 0xfffff & k;
        SchemaUtil.writeSInt64List(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l49), writer, true);
          goto _L74
_L53:
        l = buffer[i];
        long l50 = 0xfffff & k;
        SchemaUtil.writeGroupList(l, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l50), writer, getMessageFieldSchema(i));
          goto _L74
_L54:
        long l51 = 0xfffff & k;
        writeMapHelper(writer, l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l51), i);
          goto _L74
_L55:
        long l52 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l52) == l)
        {
            long l53 = 0xfffff & k;
            writer.writeDouble(l, ((Double)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l53)).doubleValue());
        }
          goto _L74
_L56:
        long l54 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l54) == l)
        {
            long l55 = 0xfffff & k;
            writer.writeFloat(l, ((Float)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l55)).floatValue());
        }
          goto _L74
_L57:
        long l56 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l56) == l)
        {
            long l57 = 0xfffff & k;
            writer.writeInt64(l, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l57)).longValue());
        }
          goto _L74
_L58:
        long l58 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l58) == l)
        {
            long l59 = 0xfffff & k;
            writer.writeUInt64(l, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l59)).longValue());
        }
          goto _L74
_L59:
        long l60 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l60) == l)
        {
            long l61 = 0xfffff & k;
            writer.writeInt32(l, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l61)).intValue());
        }
          goto _L74
_L60:
        long l62 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l62) == l)
        {
            long l63 = 0xfffff & k;
            writer.writeFixed64(l, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l63)).longValue());
        }
          goto _L74
_L61:
        long l64 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l64) == l)
        {
            long l65 = 0xfffff & k;
            writer.writeFixed32(l, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l65)).intValue());
        }
          goto _L74
_L62:
        long l66 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l66) == l)
        {
            long l67 = 0xfffff & k;
            writer.writeBool(l, ((Boolean)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l67)).booleanValue());
        }
          goto _L74
_L63:
        long l68 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l68) == l)
        {
            long l69 = 0xfffff & k;
            writeString(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l69), writer);
        }
          goto _L74
_L64:
        long l70 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l70) == l)
        {
            long l71 = 0xfffff & k;
            writer.writeMessage(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l71), getMessageFieldSchema(i));
        }
          goto _L74
_L65:
        long l72 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l72) == l)
        {
            long l73 = 0xfffff & k;
            writer.writeBytes(l, (ByteString)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l73));
        }
          goto _L74
_L66:
        long l74 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l74) == l)
        {
            long l75 = 0xfffff & k;
            writer.writeUInt32(l, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l75)).intValue());
        }
          goto _L74
_L67:
        long l76 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l76) == l)
        {
            long l77 = 0xfffff & k;
            writer.writeEnum(l, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l77)).intValue());
        }
          goto _L74
_L68:
        long l78 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l78) == l)
        {
            long l79 = 0xfffff & k;
            writer.writeSFixed32(l, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l79)).intValue());
        }
          goto _L74
_L69:
        long l80 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l80) == l)
        {
            long l81 = 0xfffff & k;
            writer.writeSFixed64(l, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l81)).longValue());
        }
          goto _L74
_L70:
        long l82 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l82) == l)
        {
            long l83 = 0xfffff & k;
            writer.writeSInt32(l, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l83)).intValue());
        }
          goto _L74
_L71:
        long l84 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l84) == l)
        {
            long l85 = 0xfffff & k;
            writer.writeSInt64(l, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l85)).longValue());
        }
          goto _L74
        long l86 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l86) == l)
        {
            long l87 = 0xfffff & k;
            writer.writeGroup(l, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l87), getMessageFieldSchema(i));
        }
          goto _L74
_L76:
        entry1 = null;
_L2:
label0:
        do
        {
label1:
            {
                if (entry1 == null)
                {
                    break label1;
                }
                extensionSchema.serializeExtension(writer, entry1);
                if (!iterator.hasNext())
                {
                    break label0;
                }
                entry1 = (java.util.Map.Entry)iterator.next();
            }
        } while (true);
        if (true) goto _L76; else goto _L75
_L75:
        UnknownFieldSchema unknownfieldschema = unknownFieldSchema;
        unknownfieldschema.writeTo(unknownfieldschema.getFromMessage(obj), writer);
        return;
    }

    private final void writeMapHelper(Writer writer, int i, Object obj, int j)
        throws IOException
    {
        if (obj != null)
        {
            writer.writeMap(i, mapFieldSchema.forMapMetadata(objects[j / 3 << 1]), mapFieldSchema.forMapData(obj));
        }
    }

    private static void writeString(int i, Object obj, Writer writer)
        throws IOException
    {
        if (obj instanceof String)
        {
            writer.writeString(i, (String)obj);
            return;
        } else
        {
            writer.writeBytes(i, (ByteString)obj);
            return;
        }
    }

    public final boolean equals(Object obj, Object obj1)
    {
        int i;
        int j;
        j = buffer.length;
        i = 0;
_L28:
        if (i >= j) goto _L2; else goto _L1
_L1:
        int k;
        long l1;
        k = buffer[i + 1];
        l1 = k & 0xfffff;
        (k & 0xff00000) >>> 20;
        JVM INSTR tableswitch 0 68: default 332
    //                   0 342
    //                   1 397
    //                   2 451
    //                   3 506
    //                   4 561
    //                   5 615
    //                   6 670
    //                   7 724
    //                   8 772
    //                   9 829
    //                   10 886
    //                   11 943
    //                   12 997
    //                   13 1051
    //                   14 1105
    //                   15 1160
    //                   16 1214
    //                   17 1269
    //                   18 1326
    //                   19 1326
    //                   20 1326
    //                   21 1326
    //                   22 1326
    //                   23 1326
    //                   24 1326
    //                   25 1326
    //                   26 1326
    //                   27 1326
    //                   28 1326
    //                   29 1326
    //                   30 1326
    //                   31 1326
    //                   32 1326
    //                   33 1326
    //                   34 1326
    //                   35 1326
    //                   36 1326
    //                   37 1326
    //                   38 1326
    //                   39 1326
    //                   40 1326
    //                   41 1326
    //                   42 1326
    //                   43 1326
    //                   44 1326
    //                   45 1326
    //                   46 1326
    //                   47 1326
    //                   48 1326
    //                   49 1326
    //                   50 1358
    //                   51 1390
    //                   52 1390
    //                   53 1390
    //                   54 1390
    //                   55 1390
    //                   56 1390
    //                   57 1390
    //                   58 1390
    //                   59 1390
    //                   60 1390
    //                   61 1390
    //                   62 1390
    //                   63 1390
    //                   64 1390
    //                   65 1390
    //                   66 1390
    //                   67 1390
    //                   68 1390;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L23 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L24
_L3:
        boolean flag = true;
_L27:
        if (flag) goto _L26; else goto _L25
_L25:
        return false;
_L4:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L5:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L6:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L7:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L8:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L9:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L10:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L11:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.getBoolean(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.getBoolean(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L12:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && SchemaUtil.safeEquals(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1), UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l1)))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L13:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && SchemaUtil.safeEquals(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1), UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l1)))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L14:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && SchemaUtil.safeEquals(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1), UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l1)))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L15:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L16:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L17:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L18:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L19:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L20:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1) == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L21:
        if (isFieldPresent(obj, i) == isFieldPresent(obj1, i) && SchemaUtil.safeEquals(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1), UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l1)))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L22:
        flag = SchemaUtil.safeEquals(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1), UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l1));
          goto _L27
_L23:
        flag = SchemaUtil.safeEquals(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1), UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l1));
          goto _L27
_L24:
        int l = buffer[i + 2];
        long l2 = l & 0xfffff;
        int i1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2);
        l2 = l & 0xfffff;
        if (i1 == UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l2) && SchemaUtil.safeEquals(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1), UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l1)))
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L27
_L26:
        i += 3;
          goto _L28
_L2:
        if (!unknownFieldSchema.getFromMessage(obj).equals(unknownFieldSchema.getFromMessage(obj1))) goto _L25; else goto _L29
_L29:
        if (hasExtensions)
        {
            return extensionSchema.getExtensions(obj).equals(extensionSchema.getExtensions(obj1));
        }
        return true;
          goto _L27
    }

    public final int getSerializedSize(Object obj)
    {
        Unsafe unsafe;
        int j;
        int k;
        if (!proto3)
        {
            break MISSING_BLOCK_LABEL_3673;
        }
        unsafe = UNSAFE;
        k = 0;
        j = 0;
_L71:
        int i;
        int l;
        int i1;
        long l4;
        if (j >= buffer.length)
        {
            break MISSING_BLOCK_LABEL_3655;
        }
        l = buffer[j + 1];
        i = (0xff00000 & l) >>> 20;
        i1 = buffer[j];
        l4 = l & 0xfffff;
        if (i >= FieldType.DOUBLE_LIST_PACKED.id && i <= FieldType.SINT64_LIST_PACKED.id)
        {
            l = buffer[j + 2] & 0xfffff;
        } else
        {
            l = 0;
        }
        i;
        JVM INSTR tableswitch 0 68: default 396
    //                   0 419
    //                   1 447
    //                   2 475
    //                   3 514
    //                   4 553
    //                   5 592
    //                   6 620
    //                   7 648
    //                   8 676
    //                   9 744
    //                   10 789
    //                   11 831
    //                   12 870
    //                   13 909
    //                   14 937
    //                   15 965
    //                   16 1004
    //                   17 1043
    //                   18 1091
    //                   19 1120
    //                   20 1149
    //                   21 1178
    //                   22 1207
    //                   23 1236
    //                   24 1265
    //                   25 1294
    //                   26 1323
    //                   27 1351
    //                   28 1385
    //                   29 1413
    //                   30 1442
    //                   31 1471
    //                   32 1500
    //                   33 1529
    //                   34 1558
    //                   35 1587
    //                   36 1650
    //                   37 1713
    //                   38 1776
    //                   39 1839
    //                   40 1902
    //                   41 1965
    //                   42 2028
    //                   43 2091
    //                   44 2154
    //                   45 2217
    //                   46 2280
    //                   47 2343
    //                   48 2406
    //                   49 2469
    //                   50 2503
    //                   51 2545
    //                   52 2595
    //                   53 2645
    //                   54 2712
    //                   55 2779
    //                   56 2846
    //                   57 2896
    //                   58 2946
    //                   59 2996
    //                   60 3086
    //                   61 3153
    //                   62 3217
    //                   63 3284
    //                   64 3351
    //                   65 3401
    //                   66 3451
    //                   67 3518
    //                   68 3585;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70
_L70:
        break MISSING_BLOCK_LABEL_3585;
_L1:
        i = k;
_L72:
        j += 3;
        k = i;
          goto _L71
_L2:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeDoubleSize(i1, 0.0D);
        }
          goto _L72
_L3:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeFloatSize(i1, 0.0F);
        }
          goto _L72
_L4:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeInt64Size(i1, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l4));
        }
          goto _L72
_L5:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeUInt64Size(i1, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l4));
        }
          goto _L72
_L6:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeInt32Size(i1, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4));
        }
          goto _L72
_L7:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeFixed64Size(i1, 0L);
        }
          goto _L72
_L8:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeFixed32Size(i1, 0);
        }
          goto _L72
_L9:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeBoolSize(i1, true);
        }
          goto _L72
_L10:
        i = k;
        if (isFieldPresent(obj, j))
        {
            Object obj1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4);
            if (obj1 instanceof ByteString)
            {
                i = k + CodedOutputStream.computeBytesSize(i1, (ByteString)obj1);
            } else
            {
                i = k + CodedOutputStream.computeStringSize(i1, (String)obj1);
            }
        }
          goto _L72
_L11:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + SchemaUtil.computeSizeMessage(i1, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), getMessageFieldSchema(j));
        }
          goto _L72
_L12:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeBytesSize(i1, (ByteString)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4));
        }
          goto _L72
_L13:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeUInt32Size(i1, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4));
        }
          goto _L72
_L14:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeEnumSize(i1, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4));
        }
          goto _L72
_L15:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeSFixed32Size(i1, 0);
        }
          goto _L72
_L16:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeSFixed64Size(i1, 0L);
        }
          goto _L72
_L17:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeSInt32Size(i1, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4));
        }
          goto _L72
_L18:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeSInt64Size(i1, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l4));
        }
          goto _L72
_L19:
        i = k;
        if (isFieldPresent(obj, j))
        {
            i = k + CodedOutputStream.computeGroupSize(i1, (MessageLite)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), getMessageFieldSchema(j));
        }
          goto _L72
_L20:
        i = k + SchemaUtil.computeSizeFixed64List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L21:
        i = k + SchemaUtil.computeSizeFixed32List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L22:
        i = k + SchemaUtil.computeSizeInt64List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L23:
        i = k + SchemaUtil.computeSizeUInt64List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L24:
        i = k + SchemaUtil.computeSizeInt32List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L25:
        i = k + SchemaUtil.computeSizeFixed64List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L26:
        i = k + SchemaUtil.computeSizeFixed32List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L27:
        i = k + SchemaUtil.computeSizeBoolList(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L28:
        i = k + SchemaUtil.computeSizeStringList(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4));
          goto _L72
_L29:
        i = k + SchemaUtil.computeSizeMessageList(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), getMessageFieldSchema(j));
          goto _L72
_L30:
        i = k + SchemaUtil.computeSizeByteStringList(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4));
          goto _L72
_L31:
        i = k + SchemaUtil.computeSizeUInt32List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L32:
        i = k + SchemaUtil.computeSizeEnumList(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L33:
        i = k + SchemaUtil.computeSizeFixed32List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L34:
        i = k + SchemaUtil.computeSizeFixed64List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L35:
        i = k + SchemaUtil.computeSizeSInt32List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L36:
        i = k + SchemaUtil.computeSizeSInt64List(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), false);
          goto _L72
_L37:
        int j1 = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (j1 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, j1);
            }
            i = k + (j1 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(j1)));
        }
          goto _L72
_L38:
        int k1 = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (k1 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, k1);
            }
            i = k + (k1 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(k1)));
        }
          goto _L72
_L39:
        int l1 = SchemaUtil.computeSizeInt64ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (l1 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, l1);
            }
            i = k + (l1 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(l1)));
        }
          goto _L72
_L40:
        int i2 = SchemaUtil.computeSizeUInt64ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (i2 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, i2);
            }
            i = k + (i2 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i2)));
        }
          goto _L72
_L41:
        int j2 = SchemaUtil.computeSizeInt32ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (j2 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, j2);
            }
            i = k + (j2 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(j2)));
        }
          goto _L72
_L42:
        int k2 = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (k2 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, k2);
            }
            i = k + (k2 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(k2)));
        }
          goto _L72
_L43:
        int l2 = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (l2 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, l2);
            }
            i = k + (l2 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(l2)));
        }
          goto _L72
_L44:
        int i3 = SchemaUtil.computeSizeBoolListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (i3 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, i3);
            }
            i = k + (i3 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i3)));
        }
          goto _L72
_L45:
        int j3 = SchemaUtil.computeSizeUInt32ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (j3 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, j3);
            }
            i = k + (j3 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(j3)));
        }
          goto _L72
_L46:
        int k3 = SchemaUtil.computeSizeEnumListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (k3 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, k3);
            }
            i = k + (k3 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(k3)));
        }
          goto _L72
_L47:
        int l3 = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (l3 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, l3);
            }
            i = k + (l3 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(l3)));
        }
          goto _L72
_L48:
        int i4 = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (i4 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, i4);
            }
            i = k + (i4 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(i4)));
        }
          goto _L72
_L49:
        int j4 = SchemaUtil.computeSizeSInt32ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (j4 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, j4);
            }
            i = k + (j4 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(j4)));
        }
          goto _L72
_L50:
        int k4 = SchemaUtil.computeSizeSInt64ListNoTag((List)unsafe.getObject(obj, l4));
        i = k;
        if (k4 > 0)
        {
            if (useCachedSizeField)
            {
                unsafe.putInt(obj, l, k4);
            }
            i = k + (k4 + (CodedOutputStream.computeTagSize(i1) + CodedOutputStream.computeUInt32SizeNoTag(k4)));
        }
          goto _L72
_L51:
        i = k + SchemaUtil.computeSizeGroupList(i1, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), getMessageFieldSchema(j));
          goto _L72
_L52:
        i = k + mapFieldSchema.getSerializedSize(i1, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), objects[j / 3 << 1]);
          goto _L72
_L53:
        l4 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4) == i1)
        {
            i = k + CodedOutputStream.computeDoubleSize(i1, 0.0D);
        }
          goto _L72
_L54:
        l4 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4) == i1)
        {
            i = k + CodedOutputStream.computeFloatSize(i1, 0.0F);
        }
          goto _L72
_L55:
        long l5 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l5) == i1)
        {
            i = k + CodedOutputStream.computeInt64Size(i1, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4)).longValue());
        }
          goto _L72
_L56:
        long l6 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l6) == i1)
        {
            i = k + CodedOutputStream.computeUInt64Size(i1, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4)).longValue());
        }
          goto _L72
_L57:
        long l7 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l7) == i1)
        {
            i = k + CodedOutputStream.computeInt32Size(i1, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4)).intValue());
        }
          goto _L72
_L58:
        l4 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4) == i1)
        {
            i = k + CodedOutputStream.computeFixed64Size(i1, 0L);
        }
          goto _L72
_L59:
        l4 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4) == i1)
        {
            i = k + CodedOutputStream.computeFixed32Size(i1, 0);
        }
          goto _L72
_L60:
        l4 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4) == i1)
        {
            i = k + CodedOutputStream.computeBoolSize(i1, true);
        }
          goto _L72
_L61:
        long l8 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l8) == i1)
        {
            Object obj2 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4);
            if (obj2 instanceof ByteString)
            {
                i = k + CodedOutputStream.computeBytesSize(i1, (ByteString)obj2);
            } else
            {
                i = k + CodedOutputStream.computeStringSize(i1, (String)obj2);
            }
        }
          goto _L72
_L62:
        long l9 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l9) == i1)
        {
            i = k + SchemaUtil.computeSizeMessage(i1, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), getMessageFieldSchema(j));
        }
          goto _L72
_L63:
        long l10 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l10) == i1)
        {
            i = k + CodedOutputStream.computeBytesSize(i1, (ByteString)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4));
        }
          goto _L72
_L64:
        long l11 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l11) == i1)
        {
            i = k + CodedOutputStream.computeUInt32Size(i1, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4)).intValue());
        }
          goto _L72
_L65:
        long l12 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l12) == i1)
        {
            i = k + CodedOutputStream.computeEnumSize(i1, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4)).intValue());
        }
          goto _L72
_L66:
        l4 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4) == i1)
        {
            i = k + CodedOutputStream.computeSFixed32Size(i1, 0);
        }
          goto _L72
_L67:
        l4 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4) == i1)
        {
            i = k + CodedOutputStream.computeSFixed64Size(i1, 0L);
        }
          goto _L72
_L68:
        long l13 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l13) == i1)
        {
            i = k + CodedOutputStream.computeSInt32Size(i1, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4)).intValue());
        }
          goto _L72
_L69:
        long l14 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l14) == i1)
        {
            i = k + CodedOutputStream.computeSInt64Size(i1, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4)).longValue());
        }
          goto _L72
        long l15 = buffer[j + 2] & 0xfffff;
        i = k;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l15) == i1)
        {
            i = k + CodedOutputStream.computeGroupSize(i1, (MessageLite)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l4), getMessageFieldSchema(j));
        }
          goto _L72
        UnknownFieldSchema unknownfieldschema = unknownFieldSchema;
        return unknownfieldschema.getSerializedSize(unknownfieldschema.getFromMessage(obj)) + k;
        return getSerializedSizeProto2(obj);
    }

    public final int hashCode(Object obj)
    {
        int i;
        int j;
        int i1;
        i1 = buffer.length;
        j = 0;
        i = 0;
_L41:
        int k;
        int j1;
        long l1;
        if (j >= i1)
        {
            break MISSING_BLOCK_LABEL_1916;
        }
        k = buffer[j + 1];
        j1 = buffer[j];
        l1 = k & 0xfffff;
        (k & 0xff00000) >>> 20;
        JVM INSTR tableswitch 0 68: default 348
    //                   0 357
    //                   1 381
    //                   2 402
    //                   3 426
    //                   4 450
    //                   5 471
    //                   6 495
    //                   7 516
    //                   8 537
    //                   9 564
    //                   10 598
    //                   11 622
    //                   12 643
    //                   13 664
    //                   14 685
    //                   15 709
    //                   16 730
    //                   17 754
    //                   18 788
    //                   19 788
    //                   20 788
    //                   21 788
    //                   22 788
    //                   23 788
    //                   24 788
    //                   25 788
    //                   26 788
    //                   27 788
    //                   28 788
    //                   29 788
    //                   30 788
    //                   31 788
    //                   32 788
    //                   33 788
    //                   34 788
    //                   35 788
    //                   36 788
    //                   37 788
    //                   38 788
    //                   39 788
    //                   40 788
    //                   41 788
    //                   42 788
    //                   43 788
    //                   44 788
    //                   45 788
    //                   46 788
    //                   47 788
    //                   48 788
    //                   49 788
    //                   50 812
    //                   51 836
    //                   52 901
    //                   53 963
    //                   54 1025
    //                   55 1087
    //                   56 1146
    //                   57 1208
    //                   58 1267
    //                   59 1329
    //                   60 1388
    //                   61 1444
    //                   62 1500
    //                   63 1559
    //                   64 1618
    //                   65 1677
    //                   66 1739
    //                   67 1798
    //                   68 1860;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39
_L39:
        break MISSING_BLOCK_LABEL_1860;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L42:
        j += 3;
        if (true) goto _L41; else goto _L40
_L40:
        i = i * 53 + Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.MEMORY_ACCESSOR.getDouble(obj, l1)));
          goto _L42
_L3:
        i = i * 53 + Float.floatToIntBits(UnsafeUtil.MEMORY_ACCESSOR.getFloat(obj, l1));
          goto _L42
_L4:
        i = i * 53 + Internal.hashLong(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1));
          goto _L42
_L5:
        i = i * 53 + Internal.hashLong(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1));
          goto _L42
_L6:
        i = i * 53 + UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1);
          goto _L42
_L7:
        i = i * 53 + Internal.hashLong(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1));
          goto _L42
_L8:
        i = i * 53 + UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1);
          goto _L42
_L9:
        i = i * 53 + Internal.hashBoolean(UnsafeUtil.MEMORY_ACCESSOR.getBoolean(obj, l1));
          goto _L42
_L10:
        i = ((String)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).hashCode() + i * 53;
          goto _L42
_L11:
        Object obj1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1);
        int l;
        long l2;
        if (obj1 != null)
        {
            l = obj1.hashCode();
        } else
        {
            l = 37;
        }
        i = l + i * 53;
          goto _L42
_L12:
        i = i * 53 + UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1).hashCode();
          goto _L42
_L13:
        i = i * 53 + UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1);
          goto _L42
_L14:
        i = i * 53 + UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1);
          goto _L42
_L15:
        i = i * 53 + UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1);
          goto _L42
_L16:
        i = i * 53 + Internal.hashLong(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1));
          goto _L42
_L17:
        i = i * 53 + UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l1);
          goto _L42
_L18:
        i = i * 53 + Internal.hashLong(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l1));
          goto _L42
_L19:
        obj1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1);
        if (obj1 != null)
        {
            l = obj1.hashCode();
        } else
        {
            l = 37;
        }
        i = l + i * 53;
          goto _L42
_L20:
        i = i * 53 + UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1).hashCode();
          goto _L42
_L21:
        i = i * 53 + UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1).hashCode();
          goto _L42
_L22:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = Internal.hashLong(Double.doubleToLongBits(((Double)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).doubleValue())) + i * 53;
        }
          goto _L42
_L23:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = Float.floatToIntBits(((Float)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).floatValue()) + i * 53;
        }
          goto _L42
_L24:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = Internal.hashLong(((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).longValue()) + i * 53;
        }
          goto _L42
_L25:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = Internal.hashLong(((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).longValue()) + i * 53;
        }
          goto _L42
_L26:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).intValue() + i * 53;
        }
          goto _L42
_L27:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = Internal.hashLong(((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).longValue()) + i * 53;
        }
          goto _L42
_L28:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).intValue() + i * 53;
        }
          goto _L42
_L29:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = Internal.hashBoolean(((Boolean)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).booleanValue()) + i * 53;
        }
          goto _L42
_L30:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = ((String)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).hashCode() + i * 53;
        }
          goto _L42
_L31:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1).hashCode() + i * 53;
        }
          goto _L42
_L32:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = i * 53 + UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1).hashCode();
        }
          goto _L42
_L33:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).intValue() + i * 53;
        }
          goto _L42
_L34:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).intValue() + i * 53;
        }
          goto _L42
_L35:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).intValue() + i * 53;
        }
          goto _L42
_L36:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = Internal.hashLong(((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).longValue()) + i * 53;
        }
          goto _L42
_L37:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).intValue() + i * 53;
        }
          goto _L42
_L38:
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = Internal.hashLong(((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1)).longValue()) + i * 53;
        }
          goto _L42
        l2 = buffer[j + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == j1)
        {
            i = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1).hashCode() + i * 53;
        }
          goto _L42
        j = i * 53 + unknownFieldSchema.getFromMessage(obj).hashCode();
        i = j;
        if (hasExtensions)
        {
            i = j * 53 + extensionSchema.getExtensions(obj).hashCode();
        }
        return i;
    }

    public final boolean isInitialized(Object obj)
    {
        int i;
        int j;
        int l;
        l = 0;
        j = -1;
        i = 0;
_L19:
        if (l >= checkInitializedCount) goto _L2; else goto _L1
_L1:
        int j1 = intArray[l];
        int k1 = buffer[j1];
        int l1 = buffer[j1 + 1];
        Object obj1;
        Object obj2;
        Iterator iterator;
        Object obj3;
        int k;
        boolean flag;
        long l2;
        boolean flag1;
        if (!proto3)
        {
            k = buffer[j1 + 2];
            int i1 = k & 0xfffff;
            k = 1 << (k >>> 20);
            if (i1 != j)
            {
                i = UNSAFE.getInt(obj, i1);
                j = i1;
            }
        } else
        {
            k = 0;
        }
        if ((0x10000000 & l1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L3
_L3:
        if (proto3)
        {
            flag1 = isFieldPresent(obj, j1);
        } else
        if ((i & k) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L4; else goto _L5
_L5:
        return false;
_L4:
        (0xff00000 & l1) >>> 20;
        JVM INSTR lookupswitch 7: default 244
    //                   9: 253
    //                   17: 253
    //                   27: 332
    //                   49: 332
    //                   50: 498
    //                   60: 428
    //                   68: 428;
           goto _L6 _L7 _L7 _L8 _L8 _L9 _L10 _L10
_L6:
        l++;
        continue; /* Loop/switch isn't completed */
_L7:
        if (proto3)
        {
            flag1 = isFieldPresent(obj, j1);
        } else
        if ((i & k) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            obj1 = getMessageFieldSchema(j1);
            l2 = l1 & 0xfffff;
            if (!((Schema) (obj1)).isInitialized(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)))
            {
                return false;
            }
        }
        continue; /* Loop/switch isn't completed */
_L8:
        l2 = l1 & 0xfffff;
        obj1 = (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2);
        if (((List) (obj1)).isEmpty())
        {
            break MISSING_BLOCK_LABEL_422;
        }
        obj2 = getMessageFieldSchema(j1);
        k = 0;
_L13:
        if (k >= ((List) (obj1)).size())
        {
            break MISSING_BLOCK_LABEL_422;
        }
        if (((Schema) (obj2)).isInitialized(((List) (obj1)).get(k))) goto _L12; else goto _L11
_L11:
        k = 0;
_L14:
        if (k == 0)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L12:
        k++;
          goto _L13
        k = 1;
          goto _L14
_L10:
        l2 = buffer[j1 + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) == k1)
        {
            obj1 = getMessageFieldSchema(j1);
            l2 = l1 & 0xfffff;
            if (!((Schema) (obj1)).isInitialized(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2)))
            {
                return false;
            }
        }
        if (true) goto _L6; else goto _L9
_L9:
        obj1 = mapFieldSchema;
        l2 = l1 & 0xfffff;
        obj2 = ((MapFieldSchema) (obj1)).forMapData(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2));
        if (((Map) (obj2)).isEmpty())
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = objects[j1 / 3 << 1];
        if (mapFieldSchema.forMapMetadata(obj1).valueType.javaType != WireFormat.JavaType.MESSAGE)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = null;
        iterator = ((Map) (obj2)).values().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break; /* Loop/switch isn't completed */
            }
            obj3 = iterator.next();
            obj2 = obj1;
            if (obj1 == null)
            {
                obj2 = Protobuf.INSTANCE.schemaFor(obj3.getClass());
            }
            obj1 = obj2;
        } while (((Schema) (obj2)).isInitialized(obj3));
        k = 0;
_L16:
        if (k == 0)
        {
            return false;
        }
        if (true) goto _L6; else goto _L15
_L15:
        k = 1;
          goto _L16
        if (true) goto _L6; else goto _L2
_L2:
        if (hasExtensions && !extensionSchema.getExtensions(obj).isInitialized()) goto _L5; else goto _L17
_L17:
        return true;
        if (true) goto _L19; else goto _L18
_L18:
    }

    public final void makeImmutable(Object obj)
    {
        for (int i = checkInitializedCount; i < repeatedFieldOffsetStart; i++)
        {
            int k = intArray[i];
            long l1 = buffer[k + 1] & 0xfffff;
            Object obj1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l1);
            if (obj1 != null)
            {
                obj1 = mapFieldSchema.toImmutable(obj1);
                UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l1, obj1);
            }
        }

        int l = intArray.length;
        for (int j = repeatedFieldOffsetStart; j < l; j++)
        {
            listFieldSchema.makeImmutableListAt(obj, intArray[j]);
        }

        unknownFieldSchema.makeImmutable(obj);
        if (hasExtensions)
        {
            extensionSchema.makeImmutable(obj);
        }
    }

    public final void mergeFrom(Object obj, Reader reader, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        Object obj1;
        FieldSet fieldset;
        UnknownFieldSchema unknownfieldschema;
        ExtensionSchema extensionschema;
        if (extensionregistrylite == null)
        {
            throw new NullPointerException();
        }
        unknownfieldschema = unknownFieldSchema;
        extensionschema = extensionSchema;
        obj1 = null;
        fieldset = null;
_L11:
        Object obj2 = obj1;
        int j = reader.getFieldNumber();
        obj2 = obj1;
        if (j < minFieldNumber) goto _L2; else goto _L1
_L1:
        obj2 = obj1;
        if (j > maxFieldNumber) goto _L2; else goto _L3
_L3:
        obj2 = obj1;
        int i = slowPositionForFieldNumber(j, 0);
_L8:
        if (i >= 0) goto _L5; else goto _L4
_L4:
        if (j != 0x7fffffff) goto _L7; else goto _L6
_L2:
        i = -1;
          goto _L8
_L6:
        for (i = checkInitializedCount; i < repeatedFieldOffsetStart; i++)
        {
            obj1 = filterMapUnknownEnumValues(obj, intArray[i], obj1, unknownfieldschema);
        }

        if (obj1 != null)
        {
            unknownfieldschema.setBuilderToMessage(obj, obj1);
        }
_L16:
        return;
_L7:
        obj2 = obj1;
        if (hasExtensions) goto _L10; else goto _L9
_L9:
        Object obj3 = null;
_L12:
        FieldSet fieldset1;
        if (obj3 == null)
        {
            break MISSING_BLOCK_LABEL_245;
        }
        fieldset1 = fieldset;
        if (fieldset != null)
        {
            break MISSING_BLOCK_LABEL_196;
        }
        obj2 = obj1;
        fieldset1 = extensionschema.getMutableExtensions(obj);
        obj2 = obj1;
        obj1 = extensionschema.parseExtension(reader, obj3, extensionregistrylite, fieldset1, obj1, unknownfieldschema);
        fieldset = fieldset1;
          goto _L11
_L10:
        obj2 = obj1;
        obj3 = extensionschema.findExtensionByNumber(extensionregistrylite, defaultInstance, j);
          goto _L12
        obj2 = obj1;
        unknownfieldschema.shouldDiscardUnknownFields$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONL4PB1CHIN4EP9B8______0();
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_272;
        }
        obj2 = obj1;
        obj1 = unknownfieldschema.getBuilderFromMessage(obj);
        obj2 = obj1;
        boolean flag = unknownfieldschema.mergeOneFieldFrom(obj1, reader);
        obj2 = obj1;
        if (flag) goto _L14; else goto _L13
_L13:
        for (i = checkInitializedCount; i < repeatedFieldOffsetStart; i++)
        {
            obj1 = filterMapUnknownEnumValues(obj, intArray[i], obj1, unknownfieldschema);
        }

        if (obj1 == null) goto _L16; else goto _L15
_L15:
        unknownfieldschema.setBuilderToMessage(obj, obj1);
        return;
_L5:
        obj2 = obj1;
        int k = buffer[i + 1];
        (0xff00000 & k) >>> 20;
        JVM INSTR tableswitch 0 68: default 664
    //                   0 749
    //                   1 892
    //                   2 984
    //                   3 1036
    //                   4 1088
    //                   5 1140
    //                   6 1192
    //                   7 1244
    //                   8 1293
    //                   9 1319
    //                   10 1463
    //                   11 1515
    //                   12 1567
    //                   13 1686
    //                   14 1738
    //                   15 1790
    //                   16 1842
    //                   17 1894
    //                   18 2038
    //                   19 2065
    //                   20 2092
    //                   21 2119
    //                   22 2146
    //                   23 2173
    //                   24 2200
    //                   25 2227
    //                   26 4960
    //                   27 2313
    //                   28 2359
    //                   29 2386
    //                   30 2413
    //                   31 2485
    //                   32 2512
    //                   33 2539
    //                   34 2566
    //                   35 2593
    //                   36 2620
    //                   37 2647
    //                   38 2674
    //                   39 2701
    //                   40 2728
    //                   41 2755
    //                   42 2782
    //                   43 2809
    //                   44 2836
    //                   45 2908
    //                   46 2935
    //                   47 2962
    //                   48 2989
    //                   49 3016
    //                   50 3062
    //                   51 3266
    //                   52 3347
    //                   53 3428
    //                   54 3509
    //                   55 3590
    //                   56 3671
    //                   57 3752
    //                   58 3833
    //                   59 3914
    //                   60 3966
    //                   61 4173
    //                   62 4251
    //                   63 4332
    //                   64 4480
    //                   65 4561
    //                   66 4642
    //                   67 4723
    //                   68 4804;
           goto _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70 _L71 _L72 _L73 _L74 _L75 _L76 _L77 _L78 _L79 _L80 _L81 _L82 _L83 _L84 _L85 _L86
_L17:
        if (obj1 != null) goto _L88; else goto _L87
_L87:
        obj2 = obj1;
        obj3 = unknownfieldschema.newBuilder();
        obj1 = obj3;
_L88:
        obj2 = obj1;
        flag = unknownfieldschema.mergeOneFieldFrom(obj1, reader);
        obj2 = obj1;
        if (!flag)
        {
            for (i = checkInitializedCount; i < repeatedFieldOffsetStart; i++)
            {
                obj1 = filterMapUnknownEnumValues(obj, intArray[i], obj1, unknownfieldschema);
            }

            continue; /* Loop/switch isn't completed */
        }
          goto _L14
_L18:
        long l1;
        l1 = 0xfffff & k;
        obj2 = obj1;
        double d = reader.readDouble();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.putDouble(obj, l1, d);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
        obj2;
_L113:
        obj2 = obj1;
        unknownfieldschema.shouldDiscardUnknownFields$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONL4PB1CHIN4EP9B8______0();
        if (obj1 != null) goto _L90; else goto _L89
_L89:
        obj2 = obj1;
        obj1 = unknownfieldschema.getBuilderFromMessage(obj);
_L90:
        obj2 = obj1;
        flag = unknownfieldschema.mergeOneFieldFrom(obj1, reader);
        obj2 = obj1;
        if (!flag)
        {
            for (i = checkInitializedCount; i < repeatedFieldOffsetStart; i++)
            {
                obj1 = filterMapUnknownEnumValues(obj, intArray[i], obj1, unknownfieldschema);
            }

            continue; /* Loop/switch isn't completed */
        }
          goto _L14
_L19:
        l1 = 0xfffff & k;
        obj2 = obj1;
        float f = reader.readFloat();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.putFloat(obj, l1, f);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
        reader;
          goto _L91
_L20:
        l2 = 0xfffff & k;
        obj2 = obj1;
        l3 = reader.readInt64();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putLong(obj, l2, l3);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L21:
        l2 = 0xfffff & k;
        obj2 = obj1;
        l3 = reader.readUInt64();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putLong(obj, l2, l3);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L22:
        l2 = 0xfffff & k;
        obj2 = obj1;
        j = reader.readInt32();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L23:
        l2 = 0xfffff & k;
        obj2 = obj1;
        l3 = reader.readFixed64();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putLong(obj, l2, l3);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L24:
        l2 = 0xfffff & k;
        obj2 = obj1;
        j = reader.readFixed32();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L25:
        l2 = 0xfffff & k;
        obj2 = obj1;
        flag1 = reader.readBool();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.putBoolean(obj, l2, flag1);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L26:
        obj2 = obj1;
        readString(obj, k, reader);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L27:
        obj2 = obj1;
        if (!isFieldPresent(obj, i)) goto _L93; else goto _L92
_L92:
        l2 = 0xfffff & k;
        obj2 = obj1;
        obj4 = Internal.mergeMessage(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(i), extensionregistrylite));
        l2 = 0xfffff & k;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
          goto _L11
_L93:
        l2 = 0xfffff & k;
        obj2 = obj1;
        obj4 = reader.readMessageBySchemaWithCheck(getMessageFieldSchema(i), extensionregistrylite);
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L28:
        l2 = 0xfffff & k;
        obj2 = obj1;
        obj4 = reader.readBytes();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L29:
        l2 = 0xfffff & k;
        obj2 = obj1;
        j = reader.readUInt32();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L30:
        obj2 = obj1;
        l = reader.readEnum();
        obj2 = obj1;
        obj4 = (Internal.EnumVerifier)objects[(i / 3 << 1) + 1];
        if (obj4 == null) goto _L95; else goto _L94
_L94:
        obj2 = obj1;
        if (!((Internal.EnumVerifier) (obj4)).isInRange(l)) goto _L96; else goto _L95
_L95:
        l2 = 0xfffff & k;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, l);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L96:
        obj2 = obj1;
        obj4 = SchemaUtil.storeUnknownEnum(j, l, obj1, unknownfieldschema);
        obj1 = obj4;
          goto _L11
_L31:
        l2 = 0xfffff & k;
        obj2 = obj1;
        j = reader.readSFixed32();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L32:
        l2 = 0xfffff & k;
        obj2 = obj1;
        l3 = reader.readSFixed64();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putLong(obj, l2, l3);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L33:
        l2 = 0xfffff & k;
        obj2 = obj1;
        j = reader.readSInt32();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L34:
        l2 = 0xfffff & k;
        obj2 = obj1;
        l3 = reader.readSInt64();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putLong(obj, l2, l3);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L35:
        obj2 = obj1;
        if (!isFieldPresent(obj, i)) goto _L98; else goto _L97
_L97:
        l2 = 0xfffff & k;
        obj2 = obj1;
        obj4 = Internal.mergeMessage(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(i), extensionregistrylite));
        l2 = 0xfffff & k;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
          goto _L11
_L98:
        l2 = 0xfffff & k;
        obj2 = obj1;
        obj4 = reader.readGroupBySchemaWithCheck(getMessageFieldSchema(i), extensionregistrylite);
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L11
_L36:
        obj2 = obj1;
        reader.readDoubleList(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L37:
        obj2 = obj1;
        reader.readFloatList(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L38:
        obj2 = obj1;
        reader.readInt64List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L39:
        obj2 = obj1;
        reader.readUInt64List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L40:
        obj2 = obj1;
        reader.readInt32List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L41:
        obj2 = obj1;
        reader.readFixed64List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L42:
        obj2 = obj1;
        reader.readFixed32List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L43:
        obj2 = obj1;
        reader.readBoolList(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L114:
        if (i == 0) goto _L100; else goto _L99
_L99:
        obj2 = obj1;
        reader.readStringListRequireUtf8(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L100:
        obj2 = obj1;
        reader.readStringList(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L45:
        obj2 = obj1;
        obj4 = getMessageFieldSchema(i);
        l2 = 0xfffff & k;
        obj2 = obj1;
        reader.readMessageList(listFieldSchema.mutableListAt(obj, l2), ((Schema) (obj4)), extensionregistrylite);
          goto _L11
_L46:
        obj2 = obj1;
        reader.readBytesList(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L47:
        obj2 = obj1;
        reader.readUInt32List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L48:
        obj2 = obj1;
        obj4 = listFieldSchema.mutableListAt(obj, k & 0xfffff);
        obj2 = obj1;
        reader.readEnumList(((List) (obj4)));
        obj2 = obj1;
        obj4 = SchemaUtil.filterUnknownEnumList(j, ((List) (obj4)), (Internal.EnumVerifier)objects[(i / 3 << 1) + 1], obj1, unknownfieldschema);
        obj1 = obj4;
          goto _L11
_L49:
        obj2 = obj1;
        reader.readSFixed32List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L50:
        obj2 = obj1;
        reader.readSFixed64List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L51:
        obj2 = obj1;
        reader.readSInt32List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L52:
        obj2 = obj1;
        reader.readSInt64List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L53:
        obj2 = obj1;
        reader.readDoubleList(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L54:
        obj2 = obj1;
        reader.readFloatList(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L55:
        obj2 = obj1;
        reader.readInt64List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L56:
        obj2 = obj1;
        reader.readUInt64List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L57:
        obj2 = obj1;
        reader.readInt32List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L58:
        obj2 = obj1;
        reader.readFixed64List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L59:
        obj2 = obj1;
        reader.readFixed32List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L60:
        obj2 = obj1;
        reader.readBoolList(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L61:
        obj2 = obj1;
        reader.readUInt32List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L62:
        obj2 = obj1;
        obj4 = listFieldSchema.mutableListAt(obj, k & 0xfffff);
        obj2 = obj1;
        reader.readEnumList(((List) (obj4)));
        obj2 = obj1;
        obj4 = SchemaUtil.filterUnknownEnumList(j, ((List) (obj4)), (Internal.EnumVerifier)objects[(i / 3 << 1) + 1], obj1, unknownfieldschema);
        obj1 = obj4;
          goto _L11
_L63:
        obj2 = obj1;
        reader.readSFixed32List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L64:
        obj2 = obj1;
        reader.readSFixed64List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L65:
        obj2 = obj1;
        reader.readSInt32List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L66:
        obj2 = obj1;
        reader.readSInt64List(listFieldSchema.mutableListAt(obj, 0xfffff & k));
          goto _L11
_L67:
        l2 = 0xfffff & k;
        obj2 = obj1;
        obj4 = getMessageFieldSchema(i);
        obj2 = obj1;
        reader.readGroupList(listFieldSchema.mutableListAt(obj, l2), ((Schema) (obj4)), extensionregistrylite);
          goto _L11
_L68:
        obj2 = obj1;
        obj6 = objects[i / 3 << 1];
        obj2 = obj1;
        l2 = buffer[i + 1] & 0xfffff;
        obj2 = obj1;
        obj4 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2);
        if (obj4 != null) goto _L102; else goto _L101
_L101:
        obj2 = obj1;
        obj4 = mapFieldSchema.newMapField$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
_L104:
        obj2 = obj1;
        reader.readMap(mapFieldSchema.forMutableMapData(obj4), mapFieldSchema.forMapMetadata(obj6), extensionregistrylite);
          goto _L11
_L102:
        obj2 = obj1;
        if (!mapFieldSchema.isImmutable(obj4)) goto _L104; else goto _L103
_L103:
        obj2 = obj1;
        obj5 = mapFieldSchema.newMapField$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0();
        obj2 = obj1;
        mapFieldSchema.mergeFrom(obj5, obj4);
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj5);
        obj4 = obj5;
          goto _L104
_L69:
        l2 = 0xfffff & k;
        obj2 = obj1;
        d1 = reader.readDouble();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Double.valueOf(d1));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L70:
        l2 = 0xfffff & k;
        obj2 = obj1;
        f1 = reader.readFloat();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Float.valueOf(f1));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L71:
        l2 = 0xfffff & k;
        obj2 = obj1;
        l3 = reader.readInt64();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Long.valueOf(l3));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L72:
        l2 = 0xfffff & k;
        obj2 = obj1;
        l3 = reader.readUInt64();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Long.valueOf(l3));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L73:
        l2 = 0xfffff & k;
        obj2 = obj1;
        k = reader.readInt32();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Integer.valueOf(k));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L74:
        l2 = 0xfffff & k;
        obj2 = obj1;
        l3 = reader.readFixed64();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Long.valueOf(l3));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L75:
        l2 = 0xfffff & k;
        obj2 = obj1;
        k = reader.readFixed32();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Integer.valueOf(k));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L76:
        l2 = 0xfffff & k;
        obj2 = obj1;
        flag1 = reader.readBool();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Boolean.valueOf(flag1));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L77:
        obj2 = obj1;
        readString(obj, k, reader);
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L78:
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l2) != j) goto _L106; else goto _L105
_L105:
        l2 = 0xfffff & k;
        obj2 = obj1;
        obj4 = Internal.mergeMessage(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l2), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(i), extensionregistrylite));
        l2 = k & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
_L107:
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L106:
        l2 = 0xfffff & k;
        obj2 = obj1;
        obj4 = reader.readMessageBySchemaWithCheck(getMessageFieldSchema(i), extensionregistrylite);
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
        obj2 = obj1;
        setFieldPresent(obj, i);
          goto _L107
_L79:
        l2 = 0xfffff & k;
        obj2 = obj1;
        obj4 = reader.readBytes();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L80:
        l2 = 0xfffff & k;
        obj2 = obj1;
        k = reader.readUInt32();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Integer.valueOf(k));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L81:
        obj2 = obj1;
        l = reader.readEnum();
        obj2 = obj1;
        obj4 = (Internal.EnumVerifier)objects[(i / 3 << 1) + 1];
        if (obj4 == null) goto _L109; else goto _L108
_L108:
        obj2 = obj1;
        if (!((Internal.EnumVerifier) (obj4)).isInRange(l)) goto _L110; else goto _L109
_L109:
        l2 = 0xfffff & k;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Integer.valueOf(l));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L110:
        obj2 = obj1;
        obj4 = SchemaUtil.storeUnknownEnum(j, l, obj1, unknownfieldschema);
        obj1 = obj4;
          goto _L11
_L82:
        l2 = 0xfffff & k;
        obj2 = obj1;
        k = reader.readSFixed32();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Integer.valueOf(k));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L83:
        l2 = 0xfffff & k;
        obj2 = obj1;
        l3 = reader.readSFixed64();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Long.valueOf(l3));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L84:
        l2 = 0xfffff & k;
        obj2 = obj1;
        k = reader.readSInt32();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Integer.valueOf(k));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L85:
        l2 = 0xfffff & k;
        obj2 = obj1;
        l3 = reader.readSInt64();
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, Long.valueOf(l3));
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
_L86:
        l2 = 0xfffff & k;
        obj2 = obj1;
        obj4 = reader.readGroupBySchemaWithCheck(getMessageFieldSchema(i), extensionregistrylite);
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
        obj2 = obj1;
        l2 = buffer[i + 2] & 0xfffff;
        obj2 = obj1;
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j);
          goto _L11
        if (obj1 == null) goto _L16; else goto _L111
_L111:
        unknownfieldschema.setBuilderToMessage(obj, obj1);
        return;
        if (obj1 == null) goto _L16; else goto _L112
_L112:
        unknownfieldschema.setBuilderToMessage(obj, obj1);
        return;
_L91:
        double d1;
        float f1;
        Object obj4;
        Object obj5;
        Object obj6;
        int l;
        long l2;
        long l3;
        boolean flag1;
        for (i = checkInitializedCount; i < repeatedFieldOffsetStart; i++)
        {
            obj2 = filterMapUnknownEnumValues(obj, intArray[i], obj2, unknownfieldschema);
        }

        if (obj2 != null)
        {
            unknownfieldschema.setBuilderToMessage(obj, obj2);
        }
        throw reader;
        reader;
          goto _L91
        obj2;
          goto _L113
_L14:
        obj1 = obj2;
          goto _L11
_L44:
        if ((0x20000000 & k) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L114
    }

    public final void mergeFrom(Object obj, Object obj1)
    {
        int i;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        i = 0;
_L27:
        int j;
        int i2;
        long l2;
        if (i >= buffer.length)
        {
            break MISSING_BLOCK_LABEL_1365;
        }
        j = buffer[i + 1];
        l2 = j & 0xfffff;
        i2 = buffer[i];
        (j & 0xff00000) >>> 20;
        JVM INSTR tableswitch 0 68: default 352
    //                   0 361
    //                   1 401
    //                   2 443
    //                   3 491
    //                   4 539
    //                   5 587
    //                   6 635
    //                   7 683
    //                   8 725
    //                   9 773
    //                   10 784
    //                   11 832
    //                   12 880
    //                   13 928
    //                   14 976
    //                   15 1024
    //                   16 1072
    //                   17 1120
    //                   18 1131
    //                   19 1131
    //                   20 1131
    //                   21 1131
    //                   22 1131
    //                   23 1131
    //                   24 1131
    //                   25 1131
    //                   26 1131
    //                   27 1131
    //                   28 1131
    //                   29 1131
    //                   30 1131
    //                   31 1131
    //                   32 1131
    //                   33 1131
    //                   34 1131
    //                   35 1131
    //                   36 1131
    //                   37 1131
    //                   38 1131
    //                   39 1131
    //                   40 1131
    //                   41 1131
    //                   42 1131
    //                   43 1131
    //                   44 1131
    //                   45 1131
    //                   46 1131
    //                   47 1131
    //                   48 1131
    //                   49 1131
    //                   50 1145
    //                   51 1159
    //                   52 1159
    //                   53 1159
    //                   54 1159
    //                   55 1159
    //                   56 1159
    //                   57 1159
    //                   58 1159
    //                   59 1159
    //                   60 1251
    //                   61 1262
    //                   62 1262
    //                   63 1262
    //                   64 1262
    //                   65 1262
    //                   66 1262
    //                   67 1262
    //                   68 1354;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L20 _L21 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L22 _L23 _L24 _L24 _L24 _L24 _L24 _L24 _L24 _L25
_L25:
        break MISSING_BLOCK_LABEL_1354;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L28:
        i += 3;
        if (true) goto _L27; else goto _L26
_L26:
        if (isFieldPresent(obj1, i))
        {
            double d = UnsafeUtil.MEMORY_ACCESSOR.getDouble(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.putDouble(obj, l2, d);
            setFieldPresent(obj, i);
        }
          goto _L28
_L3:
        if (isFieldPresent(obj1, i))
        {
            float f = UnsafeUtil.MEMORY_ACCESSOR.getFloat(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.putFloat(obj, l2, f);
            setFieldPresent(obj, i);
        }
          goto _L28
_L4:
        if (isFieldPresent(obj1, i))
        {
            long l3 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putLong(obj, l2, l3);
            setFieldPresent(obj, i);
        }
          goto _L28
_L5:
        if (isFieldPresent(obj1, i))
        {
            long l4 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putLong(obj, l2, l4);
            setFieldPresent(obj, i);
        }
          goto _L28
_L6:
        if (isFieldPresent(obj1, i))
        {
            int k = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, k);
            setFieldPresent(obj, i);
        }
          goto _L28
_L7:
        if (isFieldPresent(obj1, i))
        {
            long l5 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putLong(obj, l2, l5);
            setFieldPresent(obj, i);
        }
          goto _L28
_L8:
        if (isFieldPresent(obj1, i))
        {
            int l = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, l);
            setFieldPresent(obj, i);
        }
          goto _L28
_L9:
        if (isFieldPresent(obj1, i))
        {
            boolean flag = UnsafeUtil.MEMORY_ACCESSOR.getBoolean(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.putBoolean(obj, l2, flag);
            setFieldPresent(obj, i);
        }
          goto _L28
_L10:
        if (isFieldPresent(obj1, i))
        {
            Object obj2 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj2);
            setFieldPresent(obj, i);
        }
          goto _L28
_L11:
        mergeMessage(obj, obj1, i);
          goto _L28
_L12:
        if (isFieldPresent(obj1, i))
        {
            Object obj3 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj3);
            setFieldPresent(obj, i);
        }
          goto _L28
_L13:
        if (isFieldPresent(obj1, i))
        {
            int i1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, i1);
            setFieldPresent(obj, i);
        }
          goto _L28
_L14:
        if (isFieldPresent(obj1, i))
        {
            int j1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, j1);
            setFieldPresent(obj, i);
        }
          goto _L28
_L15:
        if (isFieldPresent(obj1, i))
        {
            int k1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, k1);
            setFieldPresent(obj, i);
        }
          goto _L28
_L16:
        if (isFieldPresent(obj1, i))
        {
            long l6 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putLong(obj, l2, l6);
            setFieldPresent(obj, i);
        }
          goto _L28
_L17:
        if (isFieldPresent(obj1, i))
        {
            int l1 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, l1);
            setFieldPresent(obj, i);
        }
          goto _L28
_L18:
        if (isFieldPresent(obj1, i))
        {
            long l7 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putLong(obj, l2, l7);
            setFieldPresent(obj, i);
        }
          goto _L28
_L19:
        mergeMessage(obj, obj1, i);
          goto _L28
_L20:
        listFieldSchema.mergeListsAt(obj, obj1, l2);
          goto _L28
_L21:
        SchemaUtil.mergeMap(mapFieldSchema, obj, obj1, l2);
          goto _L28
_L22:
        long l8 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l8) == i2)
        {
            Object obj4 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj4);
            l2 = buffer[i + 2] & 0xfffff;
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, i2);
        }
          goto _L28
_L23:
        mergeOneofMessage(obj, obj1, i);
          goto _L28
_L24:
        long l9 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj1, l9) == i2)
        {
            Object obj5 = UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l2);
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l2, obj5);
            l2 = buffer[i + 2] & 0xfffff;
            UnsafeUtil.MEMORY_ACCESSOR.unsafe.putInt(obj, l2, i2);
        }
          goto _L28
        mergeOneofMessage(obj, obj1, i);
          goto _L28
        if (!proto3)
        {
            SchemaUtil.mergeUnknownFields(unknownFieldSchema, obj, obj1);
            if (hasExtensions)
            {
                SchemaUtil.mergeExtensions(extensionSchema, obj, obj1);
            }
        }
        return;
    }

    public final void mergeFrom(Object obj, byte abyte0[], int i, int j, ArrayDecoders.Registers registers)
        throws IOException
    {
        if (!proto3) goto _L2; else goto _L1
_L1:
        Unsafe unsafe;
        int k;
        int i1;
        unsafe = UNSAFE;
        i1 = 0;
        byte byte0 = -1;
        k = i;
        i = byte0;
_L4:
        int l;
        int j1;
        int k1;
        int l1;
        long l2;
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        j1 = k + 1;
        k = abyte0[k];
        k1 = k;
        l = j1;
        if (k < 0)
        {
            l = ArrayDecoders.decodeVarint32(k, abyte0, j1, registers);
            k1 = registers.int1;
        }
        k = k1 >>> 3;
        j1 = k1 & 7;
        if (k > i)
        {
            i = i1 / 3;
            if (k >= minFieldNumber && k <= maxFieldNumber)
            {
                i = slowPositionForFieldNumber(k, i);
            } else
            {
                i = -1;
            }
            i1 = i;
        } else
        {
            if (k >= minFieldNumber && k <= maxFieldNumber)
            {
                i = slowPositionForFieldNumber(k, 0);
            } else
            {
                i = -1;
            }
            i1 = i;
        }
        if (i1 == -1)
        {
            j1 = 0;
            i = l;
        } else
        {
label0:
            {
                i = buffer[i1 + 1];
                l1 = (0xff00000 & i) >>> 20;
                l2 = 0xfffff & i;
                if (l1 > 17)
                {
                    break label0;
                }
                switch (l1)
                {
                default:
                    i = l;
                    j1 = i1;
                    break;

                case 0: // '\0'
                    if (j1 != 1)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    double d = Double.longBitsToDouble(ArrayDecoders.decodeFixed64(abyte0, l));
                    UnsafeUtil.MEMORY_ACCESSOR.putDouble(obj, l2, d);
                    l += 8;
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 1: // '\001'
                    if (j1 != 5)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    float f = Float.intBitsToFloat(ArrayDecoders.decodeFixed32(abyte0, l));
                    UnsafeUtil.MEMORY_ACCESSOR.putFloat(obj, l2, f);
                    l += 4;
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 2: // '\002'
                case 3: // '\003'
                    if (j1 != 0)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    l = ArrayDecoders.decodeVarint64(abyte0, l, registers);
                    unsafe.putLong(obj, l2, registers.long1);
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 4: // '\004'
                case 11: // '\013'
                    if (j1 != 0)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    i = l + 1;
                    l = abyte0[l];
                    if (l >= 0)
                    {
                        registers.int1 = l;
                    } else
                    {
                        i = ArrayDecoders.decodeVarint32(l, abyte0, i, registers);
                    }
                    unsafe.putInt(obj, l2, registers.int1);
                    l = i;
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 5: // '\005'
                case 14: // '\016'
                    if (j1 != 1)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    unsafe.putLong(obj, l2, ArrayDecoders.decodeFixed64(abyte0, l));
                    l += 8;
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 6: // '\006'
                case 13: // '\r'
                    if (j1 != 5)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    unsafe.putInt(obj, l2, ArrayDecoders.decodeFixed32(abyte0, l));
                    l += 4;
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 7: // '\007'
                    if (j1 != 0)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    l = ArrayDecoders.decodeVarint64(abyte0, l, registers);
                    boolean flag;
                    if (registers.long1 != 0L)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    UnsafeUtil.MEMORY_ACCESSOR.putBoolean(obj, l2, flag);
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 8: // '\b'
                    if (j1 != 2)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    if ((0x20000000 & i) == 0)
                    {
                        i = ArrayDecoders.decodeString(abyte0, l, registers);
                    } else
                    {
                        i = ArrayDecoders.decodeStringRequireUtf8(abyte0, l, registers);
                    }
                    unsafe.putObject(obj, l2, registers.object1);
                    l = i;
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 9: // '\t'
                    if (j1 != 2)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    l = decodeMessageField(getMessageFieldSchema(i1), abyte0, l, j, registers);
                    Object obj1 = unsafe.getObject(obj, l2);
                    if (obj1 == null)
                    {
                        unsafe.putObject(obj, l2, registers.object1);
                        i = k;
                        k = l;
                    } else
                    {
                        unsafe.putObject(obj, l2, Internal.mergeMessage(obj1, registers.object1));
                        i = k;
                        k = l;
                    }
                    continue; /* Loop/switch isn't completed */

                case 10: // '\n'
                    if (j1 != 2)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    l = ArrayDecoders.decodeBytes(abyte0, l, registers);
                    unsafe.putObject(obj, l2, registers.object1);
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 12: // '\f'
                    if (j1 != 0)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    i = l + 1;
                    l = abyte0[l];
                    if (l >= 0)
                    {
                        registers.int1 = l;
                    } else
                    {
                        i = ArrayDecoders.decodeVarint32(l, abyte0, i, registers);
                    }
                    unsafe.putInt(obj, l2, registers.int1);
                    l = i;
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 15: // '\017'
                    if (j1 != 0)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    i = l + 1;
                    l = abyte0[l];
                    if (l >= 0)
                    {
                        registers.int1 = l;
                    } else
                    {
                        i = ArrayDecoders.decodeVarint32(l, abyte0, i, registers);
                    }
                    l = registers.int1;
                    unsafe.putInt(obj, l2, -(l & 1) ^ l >>> 1);
                    l = i;
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */

                case 16: // '\020'
                    if (j1 != 0)
                    {
                        break MISSING_BLOCK_LABEL_1343;
                    }
                    l = ArrayDecoders.decodeVarint64(abyte0, l, registers);
                    long l3 = registers.long1;
                    unsafe.putLong(obj, l2, l3 >>> 1 ^ -(l3 & 1L));
                    i = k;
                    k = l;
                    continue; /* Loop/switch isn't completed */
                }
            }
        }
_L8:
        l = ArrayDecoders.decodeUnknownField(k1, abyte0, i, j, getMutableUnknownFields(obj), registers);
        i = k;
        k = l;
        i1 = j1;
        if (true) goto _L4; else goto _L3
        if (l1 == 27)
        {
            if (j1 != 2)
            {
                break MISSING_BLOCK_LABEL_1343;
            }
            Internal.ProtobufList protobuflist = (Internal.ProtobufList)unsafe.getObject(obj, l2);
            if (!protobuflist.isModifiable())
            {
                i = protobuflist.size();
                if (i == 0)
                {
                    i = 10;
                } else
                {
                    i <<= 1;
                }
                protobuflist = protobuflist.mutableCopyWithCapacity(i);
                unsafe.putObject(obj, l2, protobuflist);
            }
            l = decodeMessageList(getMessageFieldSchema(i1), k1, abyte0, l, j, protobuflist, registers);
            i = k;
            k = l;
            continue; /* Loop/switch isn't completed */
        }
        if (l1 > 49) goto _L6; else goto _L5
_L5:
        l1 = parseRepeatedField(obj, abyte0, l, j, k1, k, j1, i1, i, l1, l2, registers);
        i = l1;
        j1 = i1;
        if (l1 == l) goto _L8; else goto _L7
_L7:
        j1 = l1;
_L10:
        i = k;
        k = j1;
        continue; /* Loop/switch isn't completed */
_L6:
        if (l1 != 50)
        {
            break MISSING_BLOCK_LABEL_1269;
        }
        if (j1 != 2)
        {
            break MISSING_BLOCK_LABEL_1343;
        }
        i = parseMapField(obj, abyte0, l, j, i1, l2, registers);
        j1 = i;
        if (i != l) goto _L10; else goto _L9
_L9:
        j1 = i1;
          goto _L8
        i = parseOneofField(obj, abyte0, l, j, k1, k, j1, i, l1, l2, i1, registers);
        j1 = i;
        if (i != l) goto _L10; else goto _L11
_L11:
        j1 = i1;
          goto _L8
_L3:
        if (k != j)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
          goto _L12
_L2:
        parseProto2Message(obj, abyte0, i, j, 0, registers);
_L12:
        return;
        i = l;
        j1 = i1;
          goto _L8
        if (true) goto _L4; else goto _L13
_L13:
    }

    public final Object newInstance()
    {
        return newInstanceSchema.newInstance(defaultInstance);
    }

    public final void writeTo(Object obj, Writer writer)
        throws IOException
    {
        if (writer.fieldOrder$50KKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULRID5Q6ASH48PKMAR349TP68PBI7C______0() != android.support.v4.content.ModernAsyncTask.Status.DESCENDING$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65TBN4QBKCLP28HJ9CLM68JRICHIN4EO_0) goto _L2; else goto _L1
_L1:
        UnknownFieldSchema unknownfieldschema = unknownFieldSchema;
        unknownfieldschema.writeTo(unknownfieldschema.getFromMessage(obj), writer);
        if (!hasExtensions) goto _L4; else goto _L3
_L3:
        Object obj1 = extensionSchema.getExtensions(obj);
        if (((FieldSet) (obj1)).fields.isEmpty()) goto _L4; else goto _L5
_L5:
        Object obj2;
        Object obj3;
        int i;
        int j;
        int k;
        if (((FieldSet) (obj1)).hasLazyField)
        {
            obj1 = ((FieldSet) (obj1)).fields;
            if (((SmallSortedMap) (obj1)).lazyDescendingEntrySet == null)
            {
                obj1.lazyDescendingEntrySet = new SmallSortedMap.DescendingEntrySet(((SmallSortedMap) (obj1)));
            }
            obj1 = new LazyField.LazyIterator(((SmallSortedMap) (obj1)).lazyDescendingEntrySet.iterator());
        } else
        {
            obj1 = ((FieldSet) (obj1)).fields;
            if (((SmallSortedMap) (obj1)).lazyDescendingEntrySet == null)
            {
                obj1.lazyDescendingEntrySet = new SmallSortedMap.DescendingEntrySet(((SmallSortedMap) (obj1)));
            }
            obj1 = ((SmallSortedMap) (obj1)).lazyDescendingEntrySet.iterator();
        }
        obj2 = (java.util.Map.Entry)((Iterator) (obj1)).next();
        obj3 = obj1;
        obj1 = obj2;
_L85:
        i = buffer.length - 3;
_L78:
        obj2 = obj1;
        if (i < 0) goto _L7; else goto _L6
_L6:
        j = buffer[i + 1];
        for (k = buffer[i]; obj1 != null && extensionSchema.extensionNumber(((java.util.Map.Entry) (obj1))) > k;)
        {
            extensionSchema.serializeExtension(writer, ((java.util.Map.Entry) (obj1)));
            if (((Iterator) (obj3)).hasNext())
            {
                obj1 = (java.util.Map.Entry)((Iterator) (obj3)).next();
            } else
            {
                obj1 = null;
            }
        }

        (0xff00000 & j) >>> 20;
        JVM INSTR tableswitch 0 68: default 548
    //                   0 557
    //                   1 595
    //                   2 633
    //                   3 674
    //                   4 715
    //                   5 756
    //                   6 797
    //                   7 838
    //                   8 876
    //                   9 915
    //                   10 962
    //                   11 1006
    //                   12 1047
    //                   13 1088
    //                   14 1129
    //                   15 1170
    //                   16 1211
    //                   17 1252
    //                   18 1299
    //                   19 1341
    //                   20 1383
    //                   21 1425
    //                   22 1467
    //                   23 1509
    //                   24 1551
    //                   25 1593
    //                   26 1635
    //                   27 1676
    //                   28 1723
    //                   29 1764
    //                   30 1806
    //                   31 1848
    //                   32 1890
    //                   33 1932
    //                   34 1974
    //                   35 2016
    //                   36 2058
    //                   37 2100
    //                   38 2142
    //                   39 2184
    //                   40 2226
    //                   41 2268
    //                   42 2310
    //                   43 2352
    //                   44 2394
    //                   45 2436
    //                   46 2478
    //                   47 2520
    //                   48 2562
    //                   49 2604
    //                   50 2651
    //                   51 2683
    //                   52 2752
    //                   53 2821
    //                   54 2890
    //                   55 2959
    //                   56 3028
    //                   57 3097
    //                   58 3166
    //                   59 3235
    //                   60 3296
    //                   61 3365
    //                   62 3431
    //                   63 3500
    //                   64 3569
    //                   65 3638
    //                   66 3707
    //                   67 3776
    //                   68 3845;
           goto _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70 _L71 _L72 _L73 _L74 _L75 _L76 _L77
_L8:
        break; /* Loop/switch isn't completed */
_L77:
        break MISSING_BLOCK_LABEL_3845;
_L79:
        i -= 3;
          goto _L78
_L9:
        if (isFieldPresent(obj, i))
        {
            long l = j & 0xfffff;
            writer.writeDouble(k, UnsafeUtil.MEMORY_ACCESSOR.getDouble(obj, l));
        }
          goto _L79
_L10:
        if (isFieldPresent(obj, i))
        {
            long l1 = j & 0xfffff;
            writer.writeFloat(k, UnsafeUtil.MEMORY_ACCESSOR.getFloat(obj, l1));
        }
          goto _L79
_L11:
        if (isFieldPresent(obj, i))
        {
            long l2 = j & 0xfffff;
            writer.writeInt64(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l2));
        }
          goto _L79
_L12:
        if (isFieldPresent(obj, i))
        {
            long l3 = j & 0xfffff;
            writer.writeUInt64(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l3));
        }
          goto _L79
_L13:
        if (isFieldPresent(obj, i))
        {
            long l4 = j & 0xfffff;
            writer.writeInt32(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l4));
        }
          goto _L79
_L14:
        if (isFieldPresent(obj, i))
        {
            long l5 = j & 0xfffff;
            writer.writeFixed64(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l5));
        }
          goto _L79
_L15:
        if (isFieldPresent(obj, i))
        {
            long l6 = j & 0xfffff;
            writer.writeFixed32(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l6));
        }
          goto _L79
_L16:
        if (isFieldPresent(obj, i))
        {
            long l7 = j & 0xfffff;
            writer.writeBool(k, UnsafeUtil.MEMORY_ACCESSOR.getBoolean(obj, l7));
        }
          goto _L79
_L17:
        if (isFieldPresent(obj, i))
        {
            long l8 = j & 0xfffff;
            writeString(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l8), writer);
        }
          goto _L79
_L18:
        if (isFieldPresent(obj, i))
        {
            long l9 = j & 0xfffff;
            writer.writeMessage(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l9), getMessageFieldSchema(i));
        }
          goto _L79
_L19:
        if (isFieldPresent(obj, i))
        {
            long l10 = j & 0xfffff;
            writer.writeBytes(k, (ByteString)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l10));
        }
          goto _L79
_L20:
        if (isFieldPresent(obj, i))
        {
            long l11 = j & 0xfffff;
            writer.writeUInt32(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l11));
        }
          goto _L79
_L21:
        if (isFieldPresent(obj, i))
        {
            long l12 = j & 0xfffff;
            writer.writeEnum(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l12));
        }
          goto _L79
_L22:
        if (isFieldPresent(obj, i))
        {
            long l13 = j & 0xfffff;
            writer.writeSFixed32(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l13));
        }
          goto _L79
_L23:
        if (isFieldPresent(obj, i))
        {
            long l14 = j & 0xfffff;
            writer.writeSFixed64(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l14));
        }
          goto _L79
_L24:
        if (isFieldPresent(obj, i))
        {
            long l15 = j & 0xfffff;
            writer.writeSInt32(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l15));
        }
          goto _L79
_L25:
        if (isFieldPresent(obj, i))
        {
            long l16 = j & 0xfffff;
            writer.writeSInt64(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getLong(obj, l16));
        }
          goto _L79
_L26:
        if (isFieldPresent(obj, i))
        {
            long l17 = j & 0xfffff;
            writer.writeGroup(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l17), getMessageFieldSchema(i));
        }
          goto _L79
_L27:
        k = buffer[i];
        long l18 = j & 0xfffff;
        SchemaUtil.writeDoubleList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l18), writer, false);
          goto _L79
_L28:
        k = buffer[i];
        long l19 = j & 0xfffff;
        SchemaUtil.writeFloatList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l19), writer, false);
          goto _L79
_L29:
        k = buffer[i];
        long l20 = j & 0xfffff;
        SchemaUtil.writeInt64List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l20), writer, false);
          goto _L79
_L30:
        k = buffer[i];
        long l21 = j & 0xfffff;
        SchemaUtil.writeUInt64List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l21), writer, false);
          goto _L79
_L31:
        k = buffer[i];
        long l22 = j & 0xfffff;
        SchemaUtil.writeInt32List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l22), writer, false);
          goto _L79
_L32:
        k = buffer[i];
        long l23 = j & 0xfffff;
        SchemaUtil.writeFixed64List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l23), writer, false);
          goto _L79
_L33:
        k = buffer[i];
        long l24 = j & 0xfffff;
        SchemaUtil.writeFixed32List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l24), writer, false);
          goto _L79
_L34:
        k = buffer[i];
        long l25 = j & 0xfffff;
        SchemaUtil.writeBoolList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l25), writer, false);
          goto _L79
_L35:
        k = buffer[i];
        long l26 = j & 0xfffff;
        SchemaUtil.writeStringList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l26), writer);
          goto _L79
_L36:
        k = buffer[i];
        long l27 = j & 0xfffff;
        SchemaUtil.writeMessageList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l27), writer, getMessageFieldSchema(i));
          goto _L79
_L37:
        k = buffer[i];
        long l28 = j & 0xfffff;
        SchemaUtil.writeBytesList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l28), writer);
          goto _L79
_L38:
        k = buffer[i];
        long l29 = j & 0xfffff;
        SchemaUtil.writeUInt32List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l29), writer, false);
          goto _L79
_L39:
        k = buffer[i];
        long l30 = j & 0xfffff;
        SchemaUtil.writeEnumList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l30), writer, false);
          goto _L79
_L40:
        k = buffer[i];
        long l31 = j & 0xfffff;
        SchemaUtil.writeSFixed32List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l31), writer, false);
          goto _L79
_L41:
        k = buffer[i];
        long l32 = j & 0xfffff;
        SchemaUtil.writeSFixed64List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l32), writer, false);
          goto _L79
_L42:
        k = buffer[i];
        long l33 = j & 0xfffff;
        SchemaUtil.writeSInt32List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l33), writer, false);
          goto _L79
_L43:
        k = buffer[i];
        long l34 = j & 0xfffff;
        SchemaUtil.writeSInt64List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l34), writer, false);
          goto _L79
_L44:
        k = buffer[i];
        long l35 = j & 0xfffff;
        SchemaUtil.writeDoubleList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l35), writer, true);
          goto _L79
_L45:
        k = buffer[i];
        long l36 = j & 0xfffff;
        SchemaUtil.writeFloatList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l36), writer, true);
          goto _L79
_L46:
        k = buffer[i];
        long l37 = j & 0xfffff;
        SchemaUtil.writeInt64List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l37), writer, true);
          goto _L79
_L47:
        k = buffer[i];
        long l38 = j & 0xfffff;
        SchemaUtil.writeUInt64List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l38), writer, true);
          goto _L79
_L48:
        k = buffer[i];
        long l39 = j & 0xfffff;
        SchemaUtil.writeInt32List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l39), writer, true);
          goto _L79
_L49:
        k = buffer[i];
        long l40 = j & 0xfffff;
        SchemaUtil.writeFixed64List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l40), writer, true);
          goto _L79
_L50:
        k = buffer[i];
        long l41 = j & 0xfffff;
        SchemaUtil.writeFixed32List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l41), writer, true);
          goto _L79
_L51:
        k = buffer[i];
        long l42 = j & 0xfffff;
        SchemaUtil.writeBoolList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l42), writer, true);
          goto _L79
_L52:
        k = buffer[i];
        long l43 = j & 0xfffff;
        SchemaUtil.writeUInt32List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l43), writer, true);
          goto _L79
_L53:
        k = buffer[i];
        long l44 = j & 0xfffff;
        SchemaUtil.writeEnumList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l44), writer, true);
          goto _L79
_L54:
        k = buffer[i];
        long l45 = j & 0xfffff;
        SchemaUtil.writeSFixed32List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l45), writer, true);
          goto _L79
_L55:
        k = buffer[i];
        long l46 = j & 0xfffff;
        SchemaUtil.writeSFixed64List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l46), writer, true);
          goto _L79
_L56:
        k = buffer[i];
        long l47 = j & 0xfffff;
        SchemaUtil.writeSInt32List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l47), writer, true);
          goto _L79
_L57:
        k = buffer[i];
        long l48 = j & 0xfffff;
        SchemaUtil.writeSInt64List(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l48), writer, true);
          goto _L79
_L58:
        k = buffer[i];
        long l49 = j & 0xfffff;
        SchemaUtil.writeGroupList(k, (List)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l49), writer, getMessageFieldSchema(i));
          goto _L79
_L59:
        long l50 = j & 0xfffff;
        writeMapHelper(writer, k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l50), i);
          goto _L79
_L60:
        long l51 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l51) == k)
        {
            long l52 = j & 0xfffff;
            writer.writeDouble(k, ((Double)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l52)).doubleValue());
        }
          goto _L79
_L61:
        long l53 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l53) == k)
        {
            long l54 = j & 0xfffff;
            writer.writeFloat(k, ((Float)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l54)).floatValue());
        }
          goto _L79
_L62:
        long l55 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l55) == k)
        {
            long l56 = j & 0xfffff;
            writer.writeInt64(k, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l56)).longValue());
        }
          goto _L79
_L63:
        long l57 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l57) == k)
        {
            long l58 = j & 0xfffff;
            writer.writeUInt64(k, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l58)).longValue());
        }
          goto _L79
_L64:
        long l59 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l59) == k)
        {
            long l60 = j & 0xfffff;
            writer.writeInt32(k, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l60)).intValue());
        }
          goto _L79
_L65:
        long l61 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l61) == k)
        {
            long l62 = j & 0xfffff;
            writer.writeFixed64(k, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l62)).longValue());
        }
          goto _L79
_L66:
        long l63 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l63) == k)
        {
            long l64 = j & 0xfffff;
            writer.writeFixed32(k, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l64)).intValue());
        }
          goto _L79
_L67:
        long l65 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l65) == k)
        {
            long l66 = j & 0xfffff;
            writer.writeBool(k, ((Boolean)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l66)).booleanValue());
        }
          goto _L79
_L68:
        long l67 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l67) == k)
        {
            long l68 = j & 0xfffff;
            writeString(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l68), writer);
        }
          goto _L79
_L69:
        long l69 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l69) == k)
        {
            long l70 = j & 0xfffff;
            writer.writeMessage(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l70), getMessageFieldSchema(i));
        }
          goto _L79
_L70:
        long l71 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l71) == k)
        {
            long l72 = j & 0xfffff;
            writer.writeBytes(k, (ByteString)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l72));
        }
          goto _L79
_L71:
        long l73 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l73) == k)
        {
            long l74 = j & 0xfffff;
            writer.writeUInt32(k, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l74)).intValue());
        }
          goto _L79
_L72:
        long l75 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l75) == k)
        {
            long l76 = j & 0xfffff;
            writer.writeEnum(k, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l76)).intValue());
        }
          goto _L79
_L73:
        long l77 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l77) == k)
        {
            long l78 = j & 0xfffff;
            writer.writeSFixed32(k, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l78)).intValue());
        }
          goto _L79
_L74:
        long l79 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l79) == k)
        {
            long l80 = j & 0xfffff;
            writer.writeSFixed64(k, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l80)).longValue());
        }
          goto _L79
_L75:
        long l81 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l81) == k)
        {
            long l82 = j & 0xfffff;
            writer.writeSInt32(k, ((Integer)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l82)).intValue());
        }
          goto _L79
_L76:
        long l83 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l83) == k)
        {
            long l84 = j & 0xfffff;
            writer.writeSInt64(k, ((Long)UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l84)).longValue());
        }
          goto _L79
        long l85 = buffer[i + 2] & 0xfffff;
        if (UnsafeUtil.MEMORY_ACCESSOR.unsafe.getInt(obj, l85) == k)
        {
            long l86 = j & 0xfffff;
            writer.writeGroup(k, UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l86), getMessageFieldSchema(i));
        }
          goto _L79
_L81:
        obj2 = null;
_L7:
        do
        {
            if (obj2 == null)
            {
                break; /* Loop/switch isn't completed */
            }
            extensionSchema.serializeExtension(writer, ((java.util.Map.Entry) (obj2)));
            if (!((Iterator) (obj3)).hasNext())
            {
                continue; /* Loop/switch isn't completed */
            }
            obj2 = (java.util.Map.Entry)((Iterator) (obj3)).next();
        } while (true);
        if (true) goto _L81; else goto _L80
_L2:
        if (!proto3) goto _L83; else goto _L82
_L82:
        writeFieldsInAscendingOrderProto3(obj, writer);
_L80:
        return;
_L83:
        writeFieldsInAscendingOrderProto2(obj, writer);
        return;
_L4:
        obj1 = null;
        obj3 = null;
        if (true) goto _L85; else goto _L84
_L84:
    }

}
