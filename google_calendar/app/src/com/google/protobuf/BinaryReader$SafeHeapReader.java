// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.protobuf:
//            BinaryReader, Schema, InvalidProtocolBufferException, Utf8, 
//            Internal, LazyStringList, BooleanArrayList, ByteString, 
//            DoubleArrayList, IntArrayList, LongArrayList, FloatArrayList, 
//            Protobuf, ExtensionRegistryLite

final class limit extends BinaryReader
{

    private final byte buffer[];
    private final boolean bufferIsImmutable;
    private int endGroupTag;
    private int limit;
    private int pos;
    private int tag;

    private final Object readField(limit limit1, Class class1, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        switch (limit1.())
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
            return readStringInternal(true);

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

    private final int readLittleEndian32_NoCheck()
    {
        int i = pos;
        byte abyte0[] = buffer;
        pos = i + 4;
        byte byte0 = abyte0[i];
        byte byte1 = abyte0[i + 1];
        byte byte2 = abyte0[i + 2];
        return (abyte0[i + 3] & 0xff) << 24 | (byte0 & 0xff | (byte1 & 0xff) << 8 | (byte2 & 0xff) << 16);
    }

    private final long readLittleEndian64_NoCheck()
    {
        int i = pos;
        byte abyte0[] = buffer;
        pos = i + 8;
        long l = abyte0[i];
        long l1 = abyte0[i + 1];
        long l2 = abyte0[i + 2];
        long l3 = abyte0[i + 3];
        long l4 = abyte0[i + 4];
        long l5 = abyte0[i + 5];
        long l6 = abyte0[i + 6];
        return ((long)abyte0[i + 7] & 255L) << 56 | (l & 255L | (l1 & 255L) << 8 | (l2 & 255L) << 16 | (l3 & 255L) << 24 | (l4 & 255L) << 32 | (l5 & 255L) << 40 | (l6 & 255L) << 48);
    }

    private final Object readMessage(Schema schema, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        int i;
        int j;
        j = readVarint32();
        if (j < 0 || j > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        i = limit;
        j += pos;
        limit = j;
        Object obj;
        obj = schema.newInstance();
        schema.mergeFrom(obj, this, extensionregistrylite);
        schema.makeImmutable(obj);
        if (pos != j)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        break MISSING_BLOCK_LABEL_107;
        schema;
        limit = i;
        throw schema;
        limit = i;
        return obj;
    }

    private final String readStringInternal(boolean flag)
        throws IOException
    {
        boolean flag1 = false;
        if ((tag & 7) != 2)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        int i = readVarint32();
        if (i == 0)
        {
            return "";
        }
        if (i < 0 || i > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (flag)
        {
            byte abyte0[] = buffer;
            int j = pos;
            int k = pos;
            if (Utf8.processor.dUtf8(0, abyte0, j, k + i) == 0)
            {
                flag1 = true;
            }
            if (!flag1)
            {
                throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
            }
        }
        String s = new String(buffer, pos, i, Internal.UTF_8);
        pos = i + pos;
        return s;
    }

    private final void readStringListInternal(List list, boolean flag)
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        if (!(list instanceof LazyStringList) || flag) goto _L2; else goto _L1
_L1:
        list = (LazyStringList)list;
_L6:
        list.add(readBytes());
        if (pos != limit) goto _L4; else goto _L3
_L3:
        return;
_L4:
        int i = pos;
        if (readVarint32() == tag) goto _L6; else goto _L5
_L5:
        pos = i;
        return;
_L2:
        list.add(readStringInternal(flag));
        if (pos == limit) goto _L3; else goto _L7
_L7:
        int j = pos;
        if (readVarint32() != tag)
        {
            pos = j;
            return;
        }
          goto _L2
    }

    private final int readVarint32()
        throws IOException
    {
        int i;
        int l;
        i = pos;
        if (limit == pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        byte abyte0[] = buffer;
        int j = i + 1;
        l = abyte0[i];
        if (l >= 0)
        {
            pos = j;
            return l;
        }
        if (limit - j < 9)
        {
            return (int)readVarint64SlowPath();
        }
        abyte0 = buffer;
        i = j + 1;
        l ^= abyte0[j] << 7;
        if (l >= 0) goto _L2; else goto _L1
_L1:
        int k = l ^ 0xffffff80;
_L4:
        pos = i;
        return k;
_L2:
        byte abyte1[] = buffer;
        k = i + 1;
        l ^= abyte1[i] << 14;
        if (l >= 0)
        {
            l ^= 0x3f80;
            i = k;
            k = l;
            continue; /* Loop/switch isn't completed */
        }
        abyte1 = buffer;
        i = k + 1;
        l ^= abyte1[k] << 21;
        if (l < 0)
        {
            k = l ^ 0xffe03f80;
            continue; /* Loop/switch isn't completed */
        }
        abyte1 = buffer;
        int i1 = i + 1;
        k = abyte1[i];
        l = l ^ k << 28 ^ 0xfe03f80;
        i = i1;
        if (k < 0)
        {
            byte abyte2[] = buffer;
            int j1 = i1 + 1;
            k = l;
            i = j1;
            if (abyte2[i1] >= 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            abyte2 = buffer;
            i1 = j1 + 1;
            i = i1;
            if (abyte2[j1] < 0)
            {
                byte abyte3[] = buffer;
                int k1 = i1 + 1;
                k = l;
                i = k1;
                if (abyte3[i1] >= 0)
                {
                    continue; /* Loop/switch isn't completed */
                }
                abyte3 = buffer;
                i1 = k1 + 1;
                i = i1;
                if (abyte3[k1] < 0)
                {
                    byte abyte4[] = buffer;
                    i = i1 + 1;
                    k = l;
                    if (abyte4[i1] < 0)
                    {
                        throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
                    }
                    continue; /* Loop/switch isn't completed */
                }
            }
        }
        k = l;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final long readVarint64()
        throws IOException
    {
        byte abyte0[];
        int i;
        int j1;
        i = pos;
        if (limit == i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        abyte0 = buffer;
        int j = i + 1;
        j1 = abyte0[i];
        if (j1 >= 0)
        {
            pos = j;
            return (long)j1;
        }
        if (limit - j < 9)
        {
            return readVarint64SlowPath();
        }
        i = j + 1;
        j1 ^= abyte0[j] << 7;
        if (j1 >= 0) goto _L2; else goto _L1
_L1:
        long l1 = j1 ^ 0xffffff80;
_L4:
        pos = i;
        return l1;
_L2:
        int k = i + 1;
        j1 ^= abyte0[i] << 14;
        if (j1 >= 0)
        {
            l1 = j1 ^ 0x3f80;
            i = k;
        } else
        {
            i = k + 1;
            k = j1 ^ abyte0[k] << 21;
            if (k < 0)
            {
                l1 = k ^ 0xffe03f80;
            } else
            {
                l1 = k;
                k = i + 1;
                l1 ^= (long)abyte0[i] << 28;
                if (l1 >= 0L)
                {
                    l1 ^= 0xfe03f80L;
                    i = k;
                } else
                {
                    i = k + 1;
                    l1 ^= (long)abyte0[k] << 35;
                    if (l1 < 0L)
                    {
                        l1 ^= 0xfffffff80fe03f80L;
                    } else
                    {
                        int l = i + 1;
                        l1 ^= (long)abyte0[i] << 42;
                        if (l1 >= 0L)
                        {
                            l1 ^= 0x3f80fe03f80L;
                            i = l;
                        } else
                        {
                            i = l + 1;
                            l1 ^= (long)abyte0[l] << 49;
                            if (l1 < 0L)
                            {
                                l1 ^= 0xfffe03f80fe03f80L;
                            } else
                            {
                                int i1 = i + 1;
                                l1 = l1 ^ (long)abyte0[i] << 56 ^ 0xfe03f80fe03f80L;
                                if (l1 < 0L)
                                {
                                    i = i1 + 1;
                                    if ((long)abyte0[i1] < 0L)
                                    {
                                        throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
                                    }
                                } else
                                {
                                    i = i1;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final long readVarint64SlowPath()
        throws IOException
    {
        long l = 0L;
        for (int i = 0; i < 64; i += 7)
        {
            if (pos == limit)
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            byte abyte0[] = buffer;
            int j = pos;
            pos = j + 1;
            j = abyte0[j];
            l |= (long)(j & 0x7f) << i;
            if ((j & 0x80) == 0)
            {
                return l;
            }
        }

        throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
    }

    public final int getFieldNumber()
        throws IOException
    {
        if (pos != limit)
        {
            tag = readVarint32();
            if (tag != endGroupTag)
            {
                return tag >>> 3;
            }
        }
        return 0x7fffffff;
    }

    public final int getTag()
    {
        return tag;
    }

    public final boolean readBool()
        throws IOException
    {
        boolean flag = false;
        if ((tag & 7) != 0)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        if (readVarint32() != 0)
        {
            flag = true;
        }
        return flag;
    }

    public final void readBoolList(List list)
        throws IOException
    {
        if (!(list instanceof BooleanArrayList)) goto _L2; else goto _L1
_L1:
        list = (BooleanArrayList)list;
        tag & 7;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 118
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = readVarint32();
        i = pos + i;
        while (pos < i) 
        {
            boolean flag;
            if (readVarint32() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            list.addBoolean(((BooleanArrayList) (list)).size, flag);
        }
        if (pos != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        boolean flag1 = readBool();
        list.addBoolean(((BooleanArrayList) (list)).size, flag1);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 200
    //                   0 276
    //                   1 200
    //                   2 210;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        j = readVarint32();
        j = pos + j;
        while (pos < j) 
        {
            boolean flag2;
            if (readVarint32() != 0)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            list.add(Boolean.valueOf(flag2));
        }
        if (pos == j) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Boolean.valueOf(readBool()));
        if (pos == limit) goto _L6; else goto _L13
_L13:
        int k = pos;
        if (readVarint32() != tag)
        {
            pos = k;
            return;
        }
          goto _L10
    }

    public final ByteString readBytes()
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        int i = readVarint32();
        if (i == 0)
        {
            return ByteString.EMPTY;
        }
        if (i < 0 || i > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        ByteString bytestring;
        if (bufferIsImmutable)
        {
            bytestring = ByteString.wrap(buffer, pos, i);
        } else
        {
            bytestring = ByteString.copyFrom(buffer, pos, i);
        }
        pos = i + pos;
        return bytestring;
    }

    public final void readBytesList(List list)
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        int i;
        do
        {
            list.add(readBytes());
            if (pos == limit)
            {
                return;
            }
            i = pos;
        } while (readVarint32() == tag);
        pos = i;
    }

    public final double readDouble()
        throws IOException
    {
        if ((tag & 7) != 1)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        if (8 > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            return Double.longBitsToDouble(readLittleEndian64_NoCheck());
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
    //                   1 141
    //                   2 50;
           goto _L3 _L4 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = readVarint32();
        if (i < 0 || i > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((i & 7) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        for (int i1 = pos; pos < i + i1;)
        {
            double d = Double.longBitsToDouble(readLittleEndian64_NoCheck());
            list.addDouble(((DoubleArrayList) (list)).size, d);
        }

        break; /* Loop/switch isn't completed */
_L4:
        double d1 = readDouble();
        list.addDouble(((DoubleArrayList) (list)).size, d1);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 1 2: default 220
    //                   1 321
    //                   2 230;
           goto _L9 _L10 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        int k = readVarint32();
        if (k < 0 || k > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((k & 7) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int j1 = pos;
        while (pos < k + j1) 
        {
            list.add(Double.valueOf(Double.longBitsToDouble(readLittleEndian64_NoCheck())));
        }
          goto _L6
_L10:
        list.add(Double.valueOf(readDouble()));
        if (pos == limit) goto _L6; else goto _L12
_L12:
        int l = pos;
        if (readVarint32() != tag)
        {
            pos = l;
            return;
        }
          goto _L10
    }

    public final int readEnum()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return readVarint32();
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
    //                   0 93
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = readVarint32();
        for (int j1 = pos; pos < i + j1;)
        {
            int l1 = readVarint32();
            list.addInt(((IntArrayList) (list)).size, l1);
        }

        break; /* Loop/switch isn't completed */
_L4:
        int j = readEnum();
        list.addInt(((IntArrayList) (list)).size, j);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int k = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = k;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 176
    //                   0 223
    //                   1 176
    //                   2 186;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        int l = readVarint32();
        int k1 = pos;
        while (pos < l + k1) 
        {
            list.add(Integer.valueOf(readVarint32()));
        }
          goto _L6
_L10:
        list.add(Integer.valueOf(readEnum()));
        if (pos == limit) goto _L6; else goto _L12
_L12:
        int i1 = pos;
        if (readVarint32() != tag)
        {
            pos = i1;
            return;
        }
          goto _L10
    }

    public final int readFixed32()
        throws IOException
    {
        if ((tag & 7) != 5)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        if (4 > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            return readLittleEndian32_NoCheck();
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
    //                   5 140;
           goto _L3 _L4 _L3 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L4:
        int i = readVarint32();
        if (i < 0 || i > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((i & 3) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        for (int j1 = pos; pos < i + j1;)
        {
            int l1 = readLittleEndian32_NoCheck();
            list.addInt(((IntArrayList) (list)).size, l1);
        }

        break; /* Loop/switch isn't completed */
_L5:
        int j = readFixed32();
        list.addInt(((IntArrayList) (list)).size, j);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int k = pos;
        if (readVarint32() == tag) goto _L5; else goto _L8
_L8:
        pos = k;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 2 5: default 224
    //                   2 234
    //                   3 224
    //                   4 224
    //                   5 314;
           goto _L9 _L10 _L9 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L10:
        int l = readVarint32();
        if (l < 0 || l > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((l & 3) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int k1 = pos;
        while (pos < l + k1) 
        {
            list.add(Integer.valueOf(readLittleEndian32_NoCheck()));
        }
          goto _L6
_L11:
        list.add(Integer.valueOf(readFixed32()));
        if (pos == limit) goto _L6; else goto _L12
_L12:
        int i1 = pos;
        if (readVarint32() != tag)
        {
            pos = i1;
            return;
        }
          goto _L11
    }

    public final long readFixed64()
        throws IOException
    {
        if ((tag & 7) != 1)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        if (8 > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            return readLittleEndian64_NoCheck();
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
    //                   1 133
    //                   2 50;
           goto _L3 _L4 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = readVarint32();
        if (i < 0 || i > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((i & 7) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        for (int i1 = pos; pos < i + i1;)
        {
            long l1 = readLittleEndian64_NoCheck();
            list.addLong(((LongArrayList) (list)).size, l1);
        }

        break; /* Loop/switch isn't completed */
_L4:
        long l2 = readFixed64();
        list.addLong(((LongArrayList) (list)).size, l2);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 1 2: default 212
    //                   1 303
    //                   2 222;
           goto _L9 _L10 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        int k = readVarint32();
        if (k < 0 || k > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((k & 7) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int j1 = pos;
        while (pos < k + j1) 
        {
            list.add(Long.valueOf(readLittleEndian64_NoCheck()));
        }
          goto _L6
_L10:
        list.add(Long.valueOf(readFixed64()));
        if (pos == limit) goto _L6; else goto _L12
_L12:
        int l = pos;
        if (readVarint32() != tag)
        {
            pos = l;
            return;
        }
          goto _L10
    }

    public final float readFloat()
        throws IOException
    {
        if ((tag & 7) != 5)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        if (4 > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            return Float.intBitsToFloat(readLittleEndian32_NoCheck());
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
    //                   5 143;
           goto _L3 _L4 _L3 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L4:
        int i = readVarint32();
        if (i < 0 || i > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((i & 3) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        for (int i1 = pos; pos < i + i1;)
        {
            float f = Float.intBitsToFloat(readLittleEndian32_NoCheck());
            list.addFloat(((FloatArrayList) (list)).size, f);
        }

        break; /* Loop/switch isn't completed */
_L5:
        float f1 = readFloat();
        list.addFloat(((FloatArrayList) (list)).size, f1);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = pos;
        if (readVarint32() == tag) goto _L5; else goto _L8
_L8:
        pos = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 2 5: default 228
    //                   2 238
    //                   3 228
    //                   4 228
    //                   5 323;
           goto _L9 _L10 _L9 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L10:
        int k = readVarint32();
        if (k < 0 || k > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((k & 3) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int j1 = pos;
        while (pos < k + j1) 
        {
            list.add(Float.valueOf(Float.intBitsToFloat(readLittleEndian32_NoCheck())));
        }
          goto _L6
_L11:
        list.add(Float.valueOf(readFloat()));
        if (pos == limit) goto _L6; else goto _L12
_L12:
        int l = pos;
        if (readVarint32() != tag)
        {
            pos = l;
            return;
        }
          goto _L11
    }

    public final Object readGroup(Class class1, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        if ((tag & 7) != 3)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
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
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
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
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        int i = tag;
        int j;
        do
        {
            list.add(readGroup(schema, extensionregistrylite));
            if (pos == limit)
            {
                return;
            }
            j = pos;
        } while (readVarint32() == i);
        pos = j;
    }

    public final int readInt32()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return readVarint32();
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
    //                   0 107
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i;
        for (i = readVarint32() + pos; pos < i;)
        {
            int i1 = readVarint32();
            list.addInt(((IntArrayList) (list)).size, i1);
        }

        if (pos != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        int j = readInt32();
        list.addInt(((IntArrayList) (list)).size, j);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int k = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = k;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 188
    //                   0 251
    //                   1 188
    //                   2 198;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        for (k = readVarint32() + pos; pos < k;)
        {
            list.add(Integer.valueOf(readVarint32()));
        }

        if (pos == k) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Integer.valueOf(readInt32()));
        if (pos == limit) goto _L6; else goto _L13
_L13:
        int l = pos;
        if (readVarint32() != tag)
        {
            pos = l;
            return;
        }
          goto _L10
    }

    public final long readInt64()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return readVarint64();
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
    //                   0 107
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i;
        for (i = readVarint32() + pos; pos < i;)
        {
            long l = readVarint64();
            list.addLong(((LongArrayList) (list)).size, l);
        }

        if (pos != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        long l1 = readInt64();
        list.addLong(((LongArrayList) (list)).size, l1);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 188
    //                   0 251
    //                   1 188
    //                   2 198;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        for (j = readVarint32() + pos; pos < j;)
        {
            list.add(Long.valueOf(readVarint64()));
        }

        if (pos == j) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Long.valueOf(readInt64()));
        if (pos == limit) goto _L6; else goto _L13
_L13:
        int k = pos;
        if (readVarint32() != tag)
        {
            pos = k;
            return;
        }
          goto _L10
    }

    public final void readMap(Map map, pos pos1, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        int i;
        if ((tag & 7) != 2)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        int j = readVarint32();
        if (j < 0 || j > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        i = limit;
        limit = j + pos;
        Object obj;
        Object obj1;
        obj = pos1.tKey;
        obj1 = pos1.tValue;
_L5:
        Object obj2 = obj;
        int k = getFieldNumber();
        if (k == 0x7fffffff)
        {
            break; /* Loop/switch isn't completed */
        }
        k;
        JVM INSTR tableswitch 1 2: default 128
    //                   1 183
    //                   2 198;
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
            limit = i;
            throw map;
        }
        if (!skipField())
        {
            throw new InvalidProtocolBufferException("Unable to parse map entry.");
        }
        continue; /* Loop/switch isn't completed */
_L2:
        obj = readField(pos1.e, null, null);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = readField(pos1.ype, pos1.tValue.getClass(), extensionregistrylite);
        obj1 = obj;
        obj = obj2;
        if (true) goto _L5; else goto _L4
_L4:
        map.put(obj2, obj1);
        limit = i;
        return;
    }

    public final Object readMessage(Class class1, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        if ((tag & 7) != 2)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
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
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
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
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        int i = tag;
        int j;
        do
        {
            list.add(readMessage(schema, extensionregistrylite));
            if (pos == limit)
            {
                return;
            }
            j = pos;
        } while (readVarint32() == i);
        pos = j;
    }

    public final int readSFixed32()
        throws IOException
    {
        if ((tag & 7) != 5)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        if (4 > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            return readLittleEndian32_NoCheck();
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
    //                   5 140;
           goto _L3 _L4 _L3 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L4:
        int i = readVarint32();
        if (i < 0 || i > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((i & 3) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        for (int j1 = pos; pos < i + j1;)
        {
            int l1 = readLittleEndian32_NoCheck();
            list.addInt(((IntArrayList) (list)).size, l1);
        }

        break; /* Loop/switch isn't completed */
_L5:
        int j = readSFixed32();
        list.addInt(((IntArrayList) (list)).size, j);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int k = pos;
        if (readVarint32() == tag) goto _L5; else goto _L8
_L8:
        pos = k;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 2 5: default 224
    //                   2 234
    //                   3 224
    //                   4 224
    //                   5 314;
           goto _L9 _L10 _L9 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L10:
        int l = readVarint32();
        if (l < 0 || l > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((l & 3) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int k1 = pos;
        while (pos < l + k1) 
        {
            list.add(Integer.valueOf(readLittleEndian32_NoCheck()));
        }
          goto _L6
_L11:
        list.add(Integer.valueOf(readSFixed32()));
        if (pos == limit) goto _L6; else goto _L12
_L12:
        int i1 = pos;
        if (readVarint32() != tag)
        {
            pos = i1;
            return;
        }
          goto _L11
    }

    public final long readSFixed64()
        throws IOException
    {
        if ((tag & 7) != 1)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        }
        if (8 > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            return readLittleEndian64_NoCheck();
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
    //                   1 133
    //                   2 50;
           goto _L3 _L4 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = readVarint32();
        if (i < 0 || i > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((i & 7) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        for (int i1 = pos; pos < i + i1;)
        {
            long l1 = readLittleEndian64_NoCheck();
            list.addLong(((LongArrayList) (list)).size, l1);
        }

        break; /* Loop/switch isn't completed */
_L4:
        long l2 = readSFixed64();
        list.addLong(((LongArrayList) (list)).size, l2);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 1 2: default 212
    //                   1 303
    //                   2 222;
           goto _L9 _L10 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        int k = readVarint32();
        if (k < 0 || k > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if ((k & 7) != 0)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int j1 = pos;
        while (pos < k + j1) 
        {
            list.add(Long.valueOf(readLittleEndian64_NoCheck()));
        }
          goto _L6
_L10:
        list.add(Long.valueOf(readSFixed64()));
        if (pos == limit) goto _L6; else goto _L12
_L12:
        int l = pos;
        if (readVarint32() != tag)
        {
            pos = l;
            return;
        }
          goto _L10
    }

    public final int readSInt32()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            int i = readVarint32();
            return -(i & 1) ^ i >>> 1;
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
    //                   0 105
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = readVarint32();
        for (int j1 = pos; pos < i + j1;)
        {
            int l1 = readVarint32();
            int j2 = -(l1 & 1);
            list.addInt(((IntArrayList) (list)).size, j2 ^ l1 >>> 1);
        }

        break; /* Loop/switch isn't completed */
_L4:
        int j = readSInt32();
        list.addInt(((IntArrayList) (list)).size, j);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int k = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = k;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 188
    //                   0 247
    //                   1 188
    //                   2 198;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        int l = readVarint32();
        int k1 = pos;
        while (pos < l + k1) 
        {
            int i2 = readVarint32();
            list.add(Integer.valueOf(-(i2 & 1) ^ i2 >>> 1));
        }
          goto _L6
_L10:
        list.add(Integer.valueOf(readSInt32()));
        if (pos == limit) goto _L6; else goto _L12
_L12:
        int i1 = pos;
        if (readVarint32() != tag)
        {
            pos = i1;
            return;
        }
          goto _L10
    }

    public final long readSInt64()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            long l = readVarint64();
            return -(l & 1L) ^ l >>> 1;
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
    //                   0 105
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = readVarint32();
        for (int i1 = pos; pos < i + i1;)
        {
            long l1 = readVarint64();
            long l4 = -(l1 & 1L);
            list.addLong(((LongArrayList) (list)).size, l4 ^ l1 >>> 1);
        }

        break; /* Loop/switch isn't completed */
_L4:
        long l2 = readSInt64();
        list.addLong(((LongArrayList) (list)).size, l2);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 188
    //                   0 247
    //                   1 188
    //                   2 198;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        int k = readVarint32();
        int j1 = pos;
        while (pos < k + j1) 
        {
            long l3 = readVarint64();
            list.add(Long.valueOf(-(l3 & 1L) ^ l3 >>> 1));
        }
          goto _L6
_L10:
        list.add(Long.valueOf(readSInt64()));
        if (pos == limit) goto _L6; else goto _L12
_L12:
        int l = pos;
        if (readVarint32() != tag)
        {
            pos = l;
            return;
        }
          goto _L10
    }

    public final String readString()
        throws IOException
    {
        return readStringInternal(false);
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
        return readStringInternal(true);
    }

    public final int readUInt32()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return readVarint32();
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
    //                   0 93
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i = readVarint32();
        for (int j1 = pos; pos < i + j1;)
        {
            int l1 = readVarint32();
            list.addInt(((IntArrayList) (list)).size, l1);
        }

        break; /* Loop/switch isn't completed */
_L4:
        int j = readUInt32();
        list.addInt(((IntArrayList) (list)).size, j);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int k = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = k;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 176
    //                   0 223
    //                   1 176
    //                   2 186;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        int l = readVarint32();
        int k1 = pos;
        while (pos < l + k1) 
        {
            list.add(Integer.valueOf(readVarint32()));
        }
          goto _L6
_L10:
        list.add(Integer.valueOf(readUInt32()));
        if (pos == limit) goto _L6; else goto _L12
_L12:
        int i1 = pos;
        if (readVarint32() != tag)
        {
            pos = i1;
            return;
        }
          goto _L10
    }

    public final long readUInt64()
        throws IOException
    {
        if ((tag & 7) != 0)
        {
            throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
        } else
        {
            return readVarint64();
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
    //                   0 107
    //                   1 44
    //                   2 54;
           goto _L3 _L4 _L3 _L5
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L5:
        int i;
        for (i = readVarint32() + pos; pos < i;)
        {
            long l = readVarint64();
            list.addLong(((LongArrayList) (list)).size, l);
        }

        if (pos != i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        break; /* Loop/switch isn't completed */
_L4:
        long l1 = readUInt64();
        list.addLong(((LongArrayList) (list)).size, l1);
        if (pos != limit) goto _L7; else goto _L6
_L6:
        return;
_L7:
        int j = pos;
        if (readVarint32() == tag) goto _L4; else goto _L8
_L8:
        pos = j;
        return;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 2: default 188
    //                   0 251
    //                   1 188
    //                   2 198;
           goto _L9 _L10 _L9 _L11
_L9:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L11:
        for (j = readVarint32() + pos; pos < j;)
        {
            list.add(Long.valueOf(readVarint64()));
        }

        if (pos == j) goto _L6; else goto _L12
_L12:
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
_L10:
        list.add(Long.valueOf(readUInt64()));
        if (pos == limit) goto _L6; else goto _L13
_L13:
        int k = pos;
        if (readVarint32() != tag)
        {
            pos = k;
            return;
        }
          goto _L10
    }

    public final boolean skipField()
        throws IOException
    {
        boolean flag1;
        boolean flag2;
        boolean flag3;
        flag1 = false;
        flag3 = true;
        boolean flag;
        if (pos == limit)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag && tag != endGroupTag) goto _L2; else goto _L1
_L1:
        flag2 = false;
_L11:
        return flag2;
_L2:
        tag & 7;
        JVM INSTR tableswitch 0 5: default 92
    //                   0 102
    //                   1 242
    //                   2 279
    //                   3 358
    //                   4 92
    //                   5 323;
           goto _L3 _L4 _L5 _L6 _L7 _L3 _L8
_L3:
        throw new on.InvalidWireTypeException("Protocol message tag had invalid wire type.");
_L4:
        int j1;
        j1 = ((flag1) ? 1 : 0);
        if (limit - pos >= 10)
        {
            byte abyte0[] = buffer;
            int i1 = pos;
            int i = 0;
            do
            {
                j1 = ((flag1) ? 1 : 0);
                if (i >= 10)
                {
                    break;
                }
                j1 = i1 + 1;
                if (abyte0[i1] >= 0)
                {
                    pos = j1;
                    return true;
                }
                i++;
                i1 = j1;
            } while (true);
        }
          goto _L9
        if (abyte1[j] >= 0) goto _L11; else goto _L10
_L10:
        j1++;
_L9:
        if (j1 < 10)
        {
            if (pos == limit)
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
        } else
        {
            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
        }
        abyte1 = buffer;
        j = pos;
        pos = j + 1;
        flag2 = flag3;
        continue; /* Loop/switch isn't completed */
_L5:
        if (8 > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            pos = 8 + pos;
            return true;
        }
_L6:
        int k = readVarint32();
        if (k < 0 || k > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            pos = k + pos;
            return true;
        }
_L8:
        if (4 > limit - pos)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            pos = pos + 4;
            return true;
        }
_L7:
        int l = endGroupTag;
        endGroupTag = (tag >>> 3) << 3 | 4;
        while (getFieldNumber() != 0x7fffffff && skipField()) ;
        if (tag != endGroupTag)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        } else
        {
            endGroupTag = l;
            return true;
        }
    }

    public on.InvalidWireTypeException(ByteBuffer bytebuffer, boolean flag)
    {
        bufferIsImmutable = flag;
        buffer = bytebuffer.array();
        pos = bytebuffer.arrayOffset() + bytebuffer.position();
        limit = bytebuffer.arrayOffset() + bytebuffer.limit();
    }
}
