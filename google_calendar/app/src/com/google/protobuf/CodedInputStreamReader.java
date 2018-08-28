// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.protobuf:
//            Reader, Internal, CodedInputStream, Schema, 
//            InvalidProtocolBufferException, LazyStringList, BooleanArrayList, DoubleArrayList, 
//            IntArrayList, LongArrayList, FloatArrayList, Protobuf, 
//            ExtensionRegistryLite, ByteString

final class CodedInputStreamReader
    implements Reader
{

    private int endGroupTag;
    private final CodedInputStream input;
    private int nextTag;
    private int tag;

    CodedInputStreamReader(CodedInputStream codedinputstream)
    {
        nextTag = 0;
        input = (CodedInputStream)Internal.checkNotNull(codedinputstream, "input");
        input.wrapper = this;
    }

    private final Object readField(WireFormat.FieldType fieldtype, Class class1, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        switch (fieldtype.ordinal())
        {
        case 9: // '\t'
        default:
            throw new RuntimeException("unsupported field type.");

        case 7: // '\007'
            return Boolean.valueOf(readBool());

        case 11: // '\013'
            return readBytes();

        case 0: // '\0'
            return Double.valueOf(readDouble());

        case 13: // '\r'
            return Integer.valueOf(readEnum());

        case 6: // '\006'
            return Integer.valueOf(readFixed32());

        case 5: // '\005'
            return Long.valueOf(readFixed64());

        case 1: // '\001'
            return Float.valueOf(readFloat());

        case 4: // '\004'
            return Integer.valueOf(readInt32());

        case 2: // '\002'
            return Long.valueOf(readInt64());

        case 10: // '\n'
            return readMessage(class1, extensionregistrylite);

        case 14: // '\016'
            return Integer.valueOf(readSFixed32());

        case 15: // '\017'
            return Long.valueOf(readSFixed64());

        case 16: // '\020'
            return Integer.valueOf(readSInt32());

        case 17: // '\021'
            return Long.valueOf(readSInt64());

        case 8: // '\b'
            return readStringRequireUtf8();

        case 12: // '\f'
            return Integer.valueOf(readUInt32());

        case 3: // '\003'
            return Long.valueOf(readUInt64());
        }
    }

    private final Object readGroup(Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        int i;
        i = endGroupTag;
        endGroupTag = (tag >>> 3) << 3 | 4;
        Object obj;
        obj = schema.newInstance();
        schema.mergeFrom(obj, this, extensionregistrylite);
        schema.makeImmutable(obj);
        if (tag != endGroupTag)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        break MISSING_BLOCK_LABEL_73;
        schema;
        endGroupTag = i;
        throw schema;
        endGroupTag = i;
        return obj;
    }

    private final Object readMessage(Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        int i = input.readUInt32();
        if (input.recursionDepth >= input.recursionLimit)
        {
            throw new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        } else
        {
            i = input.pushLimit(i);
            Object obj = schema.newInstance();
            CodedInputStream codedinputstream = input;
            codedinputstream.recursionDepth = codedinputstream.recursionDepth + 1;
            schema.mergeFrom(obj, this, extensionregistrylite);
            schema.makeImmutable(obj);
            input.checkLastTagWas(0);
            schema = input;
            schema.recursionDepth = ((CodedInputStream) (schema)).recursionDepth - 1;
            input.popLimit(i);
            return obj;
        }
    }

    private final void readStringListInternal(List list, boolean flag)
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        if (!(list instanceof LazyStringList) || flag) goto _L2; else goto _L1
_L1:
        list = (LazyStringList)list;
_L6:
        list.add(readBytes());
        if (!input.isAtEnd()) goto _L4; else goto _L3
_L3:
        return;
_L4:
        int i = input.readTag();
        if (i == tag) goto _L6; else goto _L5
_L5:
        nextTag = i;
        return;
_L2:
        String s;
        int j;
        if (flag)
        {
            s = readStringRequireUtf8();
        } else
        {
            s = readString();
        }
        list.add(s);
        if (input.isAtEnd()) goto _L3; else goto _L7
_L7:
        j = input.readTag();
        if (j != tag)
        {
            nextTag = j;
            return;
        }
          goto _L2
    }

    public final int getFieldNumber()
        throws IOException
    {
        if (nextTag != 0)
        {
            tag = nextTag;
            nextTag = 0;
        } else
        {
            tag = input.readTag();
        }
        if (tag == 0 || tag == endGroupTag)
        {
            return 0x7fffffff;
        } else
        {
            return tag >>> 3;
        }
    }

    public final int getTag()
    {
        return tag;
    }

    public final boolean readBool()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readBool();
        }
    }

    public final void readBoolList(List list)
        throws IOException
    {
        if (!(list instanceof BooleanArrayList)) goto _L2; else goto _L1
_L1:
        list = (BooleanArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 113
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            list.addBoolean(input.readBool());
        } while (input.getTotalBytesRead() < i);
        if (input.getTotalBytesRead() != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        list.addBoolean(input.readBool());
        if (!input.isAtEnd()) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = input.readTag();
        if (j == tag) goto _L4; else goto _L8
_L8:
        nextTag = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 192
    //                   0 267
    //                   1 192
    //                   2 202;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        j = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            list.add(Boolean.valueOf(input.readBool()));
        } while (input.getTotalBytesRead() < j);
        if (input.getTotalBytesRead() == j) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Boolean.valueOf(input.readBool()));
        if (input.isAtEnd()) goto _L6; else goto _L13
_L13:
        int k = input.readTag();
        if (k != tag)
        {
            nextTag = k;
            return;
        }
          goto _L10
    }

    public final ByteString readBytes()
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readBytes();
        }
    }

    public final void readBytesList(List list)
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        int i;
        do
        {
            list.add(readBytes());
            if (input.isAtEnd())
            {
                return;
            }
            i = input.readTag();
        } while (i == tag);
        nextTag = i;
    }

    public final double readDouble()
        throws IOException
    {
        if ((tag & 7) != 1)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readDouble();
        }
    }

    public final void readDoubleList(List list)
        throws IOException
    {
        if (!(list instanceof DoubleArrayList)) goto _L2; else goto _L1
_L1:
        list = (DoubleArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 1 2: default 40
    //                   1 119
    //                   2 50;
           goto _L3 _L4 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32();
        if ((i & 7) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int i1 = input.getTotalBytesRead();
        do
        {
            double d = input.readDouble();
            list.addDouble(((DoubleArrayList) (list)).size, d);
        } while (input.getTotalBytesRead() < i + i1);
_L7:
        return;
_L4:
        int j;
        do
        {
            double d1 = input.readDouble();
            list.addDouble(((DoubleArrayList) (list)).size, d1);
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            j = input.readTag();
        } while (j == tag);
        nextTag = j;
        return;
_L2:
        switch (tag & 7)
        {
        default:
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");

        case 2: // '\002'
            int k = input.readUInt32();
            if ((k & 7) != 0)
            {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            int j1 = input.getTotalBytesRead();
            do
            {
                list.add(Double.valueOf(input.readDouble()));
            } while (input.getTotalBytesRead() < k + j1);
            return;

        case 1: // '\001'
            break;
        }
        do
        {
            list.add(Double.valueOf(input.readDouble()));
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            int l = input.readTag();
            if (l != tag)
            {
                nextTag = l;
                return;
            }
        } while (true);
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final int readEnum()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readEnum();
        }
    }

    public final void readEnumList(List list)
        throws IOException
    {
        if (!(list instanceof IntArrayList)) goto _L2; else goto _L1
_L1:
        list = (IntArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 119
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            int i1 = input.readEnum();
            list.addInt(((IntArrayList) (list)).size, i1);
        } while (input.getTotalBytesRead() < i);
        if (input.getTotalBytesRead() != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        int j = input.readEnum();
        list.addInt(((IntArrayList) (list)).size, j);
        if (!input.isAtEnd()) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int k = input.readTag();
        if (k == tag) goto _L4; else goto _L8
_L8:
        nextTag = k;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 204
    //                   0 279
    //                   1 204
    //                   2 214;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        k = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            list.add(Integer.valueOf(input.readEnum()));
        } while (input.getTotalBytesRead() < k);
        if (input.getTotalBytesRead() == k) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Integer.valueOf(input.readEnum()));
        if (input.isAtEnd()) goto _L6; else goto _L13
_L13:
        int l = input.readTag();
        if (l != tag)
        {
            nextTag = l;
            return;
        }
          goto _L10
    }

    public final int readFixed32()
        throws IOException
    {
        if ((tag & 7) != 5)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readFixed32();
        }
    }

    public final void readFixed32List(List list)
        throws IOException
    {
        if (!(list instanceof IntArrayList)) goto _L2; else goto _L1
_L1:
        list = (IntArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 2 5: default 48
    //                   2 58
    //                   3 48
    //                   4 48
    //                   5 123;
           goto _L3 _L4 _L3 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L4:
        int i = input.readUInt32();
        if ((i & 3) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int i1 = input.getTotalBytesRead();
        do
        {
            int k1 = input.readFixed32();
            list.addInt(((IntArrayList) (list)).size, k1);
        } while (input.getTotalBytesRead() < i + i1);
_L7:
        return;
_L5:
        int j;
        do
        {
            j = input.readFixed32();
            list.addInt(((IntArrayList) (list)).size, j);
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            j = input.readTag();
        } while (j == tag);
        nextTag = j;
        return;
_L2:
        switch (tag & 7)
        {
        case 3: // '\003'
        case 4: // '\004'
        default:
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");

        case 2: // '\002'
            int k = input.readUInt32();
            if ((k & 3) != 0)
            {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            int j1 = input.getTotalBytesRead();
            do
            {
                list.add(Integer.valueOf(input.readFixed32()));
            } while (input.getTotalBytesRead() < k + j1);
            return;

        case 5: // '\005'
            break;
        }
        do
        {
            list.add(Integer.valueOf(input.readFixed32()));
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            int l = input.readTag();
            if (l != tag)
            {
                nextTag = l;
                return;
            }
        } while (true);
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final long readFixed64()
        throws IOException
    {
        if ((tag & 7) != 1)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readFixed64();
        }
    }

    public final void readFixed64List(List list)
        throws IOException
    {
        if (!(list instanceof LongArrayList)) goto _L2; else goto _L1
_L1:
        list = (LongArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 1 2: default 40
    //                   1 116
    //                   2 50;
           goto _L3 _L4 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32();
        if ((i & 7) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int i1 = input.getTotalBytesRead();
        do
        {
            long l1 = input.readFixed64();
            list.addLong(((LongArrayList) (list)).size, l1);
        } while (input.getTotalBytesRead() < i + i1);
_L7:
        return;
_L4:
        int j;
        do
        {
            long l2 = input.readFixed64();
            list.addLong(((LongArrayList) (list)).size, l2);
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            j = input.readTag();
        } while (j == tag);
        nextTag = j;
        return;
_L2:
        switch (tag & 7)
        {
        default:
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");

        case 2: // '\002'
            int k = input.readUInt32();
            if ((k & 7) != 0)
            {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            int j1 = input.getTotalBytesRead();
            do
            {
                list.add(Long.valueOf(input.readFixed64()));
            } while (input.getTotalBytesRead() < k + j1);
            return;

        case 1: // '\001'
            break;
        }
        do
        {
            list.add(Long.valueOf(input.readFixed64()));
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            int l = input.readTag();
            if (l != tag)
            {
                nextTag = l;
                return;
            }
        } while (true);
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final float readFloat()
        throws IOException
    {
        if ((tag & 7) != 5)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readFloat();
        }
    }

    public final void readFloatList(List list)
        throws IOException
    {
        if (!(list instanceof FloatArrayList)) goto _L2; else goto _L1
_L1:
        list = (FloatArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 2 5: default 48
    //                   2 58
    //                   3 48
    //                   4 48
    //                   5 123;
           goto _L3 _L4 _L3 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L4:
        int i = input.readUInt32();
        if ((i & 3) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int i1 = input.getTotalBytesRead();
        do
        {
            float f = input.readFloat();
            list.addFloat(((FloatArrayList) (list)).size, f);
        } while (input.getTotalBytesRead() < i + i1);
_L7:
        return;
_L5:
        int j;
        do
        {
            float f1 = input.readFloat();
            list.addFloat(((FloatArrayList) (list)).size, f1);
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            j = input.readTag();
        } while (j == tag);
        nextTag = j;
        return;
_L2:
        switch (tag & 7)
        {
        case 3: // '\003'
        case 4: // '\004'
        default:
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");

        case 2: // '\002'
            int k = input.readUInt32();
            if ((k & 3) != 0)
            {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            int j1 = input.getTotalBytesRead();
            do
            {
                list.add(Float.valueOf(input.readFloat()));
            } while (input.getTotalBytesRead() < k + j1);
            return;

        case 5: // '\005'
            break;
        }
        do
        {
            list.add(Float.valueOf(input.readFloat()));
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            int l = input.readTag();
            if (l != tag)
            {
                nextTag = l;
                return;
            }
        } while (true);
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final Object readGroup(Class class1, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        if ((tag & 7) != 3)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return readGroup(Protobuf.INSTANCE.schemaFor(class1), extensionregistrylite);
        }
    }

    public final Object readGroupBySchemaWithCheck(Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        if ((tag & 7) != 3)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return readGroup(schema, extensionregistrylite);
        }
    }

    public final void readGroupList(List list, Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        if ((tag & 7) != 3)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        int i = tag;
        int j;
        do
        {
            list.add(readGroup(schema, extensionregistrylite));
            if (input.isAtEnd() || nextTag != 0)
            {
                return;
            }
            j = input.readTag();
        } while (j == i);
        nextTag = j;
    }

    public final int readInt32()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readInt32();
        }
    }

    public final void readInt32List(List list)
        throws IOException
    {
        if (!(list instanceof IntArrayList)) goto _L2; else goto _L1
_L1:
        list = (IntArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 119
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            int i1 = input.readInt32();
            list.addInt(((IntArrayList) (list)).size, i1);
        } while (input.getTotalBytesRead() < i);
        if (input.getTotalBytesRead() != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        int j = input.readInt32();
        list.addInt(((IntArrayList) (list)).size, j);
        if (!input.isAtEnd()) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int k = input.readTag();
        if (k == tag) goto _L4; else goto _L8
_L8:
        nextTag = k;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 204
    //                   0 279
    //                   1 204
    //                   2 214;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        k = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            list.add(Integer.valueOf(input.readInt32()));
        } while (input.getTotalBytesRead() < k);
        if (input.getTotalBytesRead() == k) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Integer.valueOf(input.readInt32()));
        if (input.isAtEnd()) goto _L6; else goto _L13
_L13:
        int l = input.readTag();
        if (l != tag)
        {
            nextTag = l;
            return;
        }
          goto _L10
    }

    public final long readInt64()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readInt64();
        }
    }

    public final void readInt64List(List list)
        throws IOException
    {
        if (!(list instanceof LongArrayList)) goto _L2; else goto _L1
_L1:
        list = (LongArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 119
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            long l = input.readInt64();
            list.addLong(((LongArrayList) (list)).size, l);
        } while (input.getTotalBytesRead() < i);
        if (input.getTotalBytesRead() != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        long l1 = input.readInt64();
        list.addLong(((LongArrayList) (list)).size, l1);
        if (!input.isAtEnd()) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = input.readTag();
        if (j == tag) goto _L4; else goto _L8
_L8:
        nextTag = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 204
    //                   0 279
    //                   1 204
    //                   2 214;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        j = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            list.add(Long.valueOf(input.readInt64()));
        } while (input.getTotalBytesRead() < j);
        if (input.getTotalBytesRead() == j) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Long.valueOf(input.readInt64()));
        if (input.isAtEnd()) goto _L6; else goto _L13
_L13:
        int k = input.readTag();
        if (k != tag)
        {
            nextTag = k;
            return;
        }
          goto _L10
    }

    public final void readMap(Map map, MapEntryLite.Metadata metadata, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        Object obj;
        Object obj1;
        int i;
        if ((tag & 7) != 2)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        i = input.readUInt32();
        i = input.pushLimit(i);
        obj = metadata.defaultKey;
        obj1 = metadata.defaultValue;
_L5:
        Object obj2 = obj;
        int j = getFieldNumber();
        if (j == 0x7fffffff)
        {
            break; /* Loop/switch isn't completed */
        }
        boolean flag = input.isAtEnd();
        if (flag)
        {
            break; /* Loop/switch isn't completed */
        }
        j;
        JVM INSTR tableswitch 1 2: default 108
    //                   1 166
    //                   2 181;
           goto _L1 _L2 _L3
_L1:
        obj = obj2;
        try
        {
            if (!skipField())
            {
                throw new InvalidProtocolBufferException("Unable to parse map entry.");
            }
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj = obj2;
        }
        finally
        {
            input.popLimit(i);
            throw map;
        }
        if (!skipField())
        {
            throw new InvalidProtocolBufferException("Unable to parse map entry.");
        }
        continue; /* Loop/switch isn't completed */
_L2:
        obj = readField(metadata.keyType, null, null);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = readField(metadata.valueType, metadata.defaultValue.getClass(), extensionregistrylite);
        obj1 = obj;
        obj = obj2;
        if (true) goto _L5; else goto _L4
_L4:
        map.put(obj2, obj1);
        input.popLimit(i);
        return;
    }

    public final Object readMessage(Class class1, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return readMessage(Protobuf.INSTANCE.schemaFor(class1), extensionregistrylite);
        }
    }

    public final Object readMessageBySchemaWithCheck(Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return readMessage(schema, extensionregistrylite);
        }
    }

    public final void readMessageList(List list, Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        int i = tag;
        int j;
        do
        {
            list.add(readMessage(schema, extensionregistrylite));
            if (input.isAtEnd() || nextTag != 0)
            {
                return;
            }
            j = input.readTag();
        } while (j == i);
        nextTag = j;
    }

    public final int readSFixed32()
        throws IOException
    {
        if ((tag & 7) != 5)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readSFixed32();
        }
    }

    public final void readSFixed32List(List list)
        throws IOException
    {
        if (!(list instanceof IntArrayList)) goto _L2; else goto _L1
_L1:
        list = (IntArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 2 5: default 48
    //                   2 58
    //                   3 48
    //                   4 48
    //                   5 123;
           goto _L3 _L4 _L3 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L4:
        int i = input.readUInt32();
        if ((i & 3) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int i1 = input.getTotalBytesRead();
        do
        {
            int k1 = input.readSFixed32();
            list.addInt(((IntArrayList) (list)).size, k1);
        } while (input.getTotalBytesRead() < i + i1);
_L7:
        return;
_L5:
        int j;
        do
        {
            j = input.readSFixed32();
            list.addInt(((IntArrayList) (list)).size, j);
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            j = input.readTag();
        } while (j == tag);
        nextTag = j;
        return;
_L2:
        switch (tag & 7)
        {
        case 3: // '\003'
        case 4: // '\004'
        default:
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");

        case 2: // '\002'
            int k = input.readUInt32();
            if ((k & 3) != 0)
            {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            int j1 = input.getTotalBytesRead();
            do
            {
                list.add(Integer.valueOf(input.readSFixed32()));
            } while (input.getTotalBytesRead() < k + j1);
            return;

        case 5: // '\005'
            break;
        }
        do
        {
            list.add(Integer.valueOf(input.readSFixed32()));
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            int l = input.readTag();
            if (l != tag)
            {
                nextTag = l;
                return;
            }
        } while (true);
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final long readSFixed64()
        throws IOException
    {
        if ((tag & 7) != 1)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readSFixed64();
        }
    }

    public final void readSFixed64List(List list)
        throws IOException
    {
        if (!(list instanceof LongArrayList)) goto _L2; else goto _L1
_L1:
        list = (LongArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 1 2: default 40
    //                   1 116
    //                   2 50;
           goto _L3 _L4 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32();
        if ((i & 7) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int i1 = input.getTotalBytesRead();
        do
        {
            long l1 = input.readSFixed64();
            list.addLong(((LongArrayList) (list)).size, l1);
        } while (input.getTotalBytesRead() < i + i1);
_L7:
        return;
_L4:
        int j;
        do
        {
            long l2 = input.readSFixed64();
            list.addLong(((LongArrayList) (list)).size, l2);
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            j = input.readTag();
        } while (j == tag);
        nextTag = j;
        return;
_L2:
        switch (tag & 7)
        {
        default:
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");

        case 2: // '\002'
            int k = input.readUInt32();
            if ((k & 7) != 0)
            {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            int j1 = input.getTotalBytesRead();
            do
            {
                list.add(Long.valueOf(input.readSFixed64()));
            } while (input.getTotalBytesRead() < k + j1);
            return;

        case 1: // '\001'
            break;
        }
        do
        {
            list.add(Long.valueOf(input.readSFixed64()));
            if (input.isAtEnd())
            {
                continue; /* Loop/switch isn't completed */
            }
            int l = input.readTag();
            if (l != tag)
            {
                nextTag = l;
                return;
            }
        } while (true);
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final int readSInt32()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readSInt32();
        }
    }

    public final void readSInt32List(List list)
        throws IOException
    {
        if (!(list instanceof IntArrayList)) goto _L2; else goto _L1
_L1:
        list = (IntArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 119
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            int i1 = input.readSInt32();
            list.addInt(((IntArrayList) (list)).size, i1);
        } while (input.getTotalBytesRead() < i);
        if (input.getTotalBytesRead() != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        int j = input.readSInt32();
        list.addInt(((IntArrayList) (list)).size, j);
        if (!input.isAtEnd()) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int k = input.readTag();
        if (k == tag) goto _L4; else goto _L8
_L8:
        nextTag = k;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 204
    //                   0 279
    //                   1 204
    //                   2 214;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        k = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            list.add(Integer.valueOf(input.readSInt32()));
        } while (input.getTotalBytesRead() < k);
        if (input.getTotalBytesRead() == k) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Integer.valueOf(input.readSInt32()));
        if (input.isAtEnd()) goto _L6; else goto _L13
_L13:
        int l = input.readTag();
        if (l != tag)
        {
            nextTag = l;
            return;
        }
          goto _L10
    }

    public final long readSInt64()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readSInt64();
        }
    }

    public final void readSInt64List(List list)
        throws IOException
    {
        if (!(list instanceof LongArrayList)) goto _L2; else goto _L1
_L1:
        list = (LongArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 119
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            long l = input.readSInt64();
            list.addLong(((LongArrayList) (list)).size, l);
        } while (input.getTotalBytesRead() < i);
        if (input.getTotalBytesRead() != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        long l1 = input.readSInt64();
        list.addLong(((LongArrayList) (list)).size, l1);
        if (!input.isAtEnd()) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = input.readTag();
        if (j == tag) goto _L4; else goto _L8
_L8:
        nextTag = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 204
    //                   0 279
    //                   1 204
    //                   2 214;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        j = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            list.add(Long.valueOf(input.readSInt64()));
        } while (input.getTotalBytesRead() < j);
        if (input.getTotalBytesRead() == j) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Long.valueOf(input.readSInt64()));
        if (input.isAtEnd()) goto _L6; else goto _L13
_L13:
        int k = input.readTag();
        if (k != tag)
        {
            nextTag = k;
            return;
        }
          goto _L10
    }

    public final String readString()
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readString();
        }
    }

    public final void readStringList(List list)
        throws IOException
    {
        readStringListInternal(list, false);
    }

    public final void readStringListRequireUtf8(List list)
        throws IOException
    {
        readStringListInternal(list, true);
    }

    public final String readStringRequireUtf8()
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readStringRequireUtf8();
        }
    }

    public final int readUInt32()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readUInt32();
        }
    }

    public final void readUInt32List(List list)
        throws IOException
    {
        if (!(list instanceof IntArrayList)) goto _L2; else goto _L1
_L1:
        list = (IntArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 119
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            int i1 = input.readUInt32();
            list.addInt(((IntArrayList) (list)).size, i1);
        } while (input.getTotalBytesRead() < i);
        if (input.getTotalBytesRead() != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        int j = input.readUInt32();
        list.addInt(((IntArrayList) (list)).size, j);
        if (!input.isAtEnd()) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int k = input.readTag();
        if (k == tag) goto _L4; else goto _L8
_L8:
        nextTag = k;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 204
    //                   0 279
    //                   1 204
    //                   2 214;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        k = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            list.add(Integer.valueOf(input.readUInt32()));
        } while (input.getTotalBytesRead() < k);
        if (input.getTotalBytesRead() == k) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Integer.valueOf(input.readUInt32()));
        if (input.isAtEnd()) goto _L6; else goto _L13
_L13:
        int l = input.readTag();
        if (l != tag)
        {
            nextTag = l;
            return;
        }
          goto _L10
    }

    public final long readUInt64()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return input.readUInt64();
        }
    }

    public final void readUInt64List(List list)
        throws IOException
    {
        if (!(list instanceof LongArrayList)) goto _L2; else goto _L1
_L1:
        list = (LongArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 119
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            long l = input.readUInt64();
            list.addLong(((LongArrayList) (list)).size, l);
        } while (input.getTotalBytesRead() < i);
        if (input.getTotalBytesRead() != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        long l1 = input.readUInt64();
        list.addLong(((LongArrayList) (list)).size, l1);
        if (!input.isAtEnd()) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = input.readTag();
        if (j == tag) goto _L4; else goto _L8
_L8:
        nextTag = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 204
    //                   0 279
    //                   1 204
    //                   2 214;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        j = input.readUInt32() + input.getTotalBytesRead();
        do
        {
            list.add(Long.valueOf(input.readUInt64()));
        } while (input.getTotalBytesRead() < j);
        if (input.getTotalBytesRead() == j) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Long.valueOf(input.readUInt64()));
        if (input.isAtEnd()) goto _L6; else goto _L13
_L13:
        int k = input.readTag();
        if (k != tag)
        {
            nextTag = k;
            return;
        }
          goto _L10
    }

    public final boolean skipField()
        throws IOException
    {
        if (input.isAtEnd() || tag == endGroupTag)
        {
            return false;
        } else
        {
            return input.skipField(tag);
        }
    }
}
