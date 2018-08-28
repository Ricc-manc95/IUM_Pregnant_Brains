// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;

// Referenced classes of package com.google.protobuf:
//            InvalidProtocolBufferException, ByteString, IntArrayList, Internal, 
//            Utf8, UnknownFieldSetLite

final class ArrayDecoders
{

    static int decodeBytes(byte abyte0[], int i, Registers registers)
        throws InvalidProtocolBufferException
    {
        int j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = decodeVarint32(i, abyte0, j, registers);
        }
        j = registers.int1;
        if (j < 0)
        {
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (j > abyte0.length - i)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        if (j == 0)
        {
            registers.object1 = ByteString.EMPTY;
            return i;
        } else
        {
            registers.object1 = ByteString.copyFrom(abyte0, i, j);
            return i + j;
        }
    }

    static int decodeFixed32(byte abyte0[], int i)
    {
        return abyte0[i] & 0xff | (abyte0[i + 1] & 0xff) << 8 | (abyte0[i + 2] & 0xff) << 16 | (abyte0[i + 3] & 0xff) << 24;
    }

    static long decodeFixed64(byte abyte0[], int i)
    {
        return (long)abyte0[i] & 255L | ((long)abyte0[i + 1] & 255L) << 8 | ((long)abyte0[i + 2] & 255L) << 16 | ((long)abyte0[i + 3] & 255L) << 24 | ((long)abyte0[i + 4] & 255L) << 32 | ((long)abyte0[i + 5] & 255L) << 40 | ((long)abyte0[i + 6] & 255L) << 48 | ((long)abyte0[i + 7] & 255L) << 56;
    }

    static int decodePackedVarint32List(byte abyte0[], int i, Internal.ProtobufList protobuflist, Registers registers)
        throws IOException
    {
        protobuflist = (IntArrayList)protobuflist;
        int j = i + 1;
        i = abyte0[i];
        int k;
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = decodeVarint32(i, abyte0, j, registers);
        }
        k = i + registers.int1;
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
                i = decodeVarint32(i, abyte0, j, registers);
            }
            j = registers.int1;
            protobuflist.addInt(((IntArrayList) (protobuflist)).size, j);
        }
        if (i != k)
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else
        {
            return i;
        }
    }

    static int decodeString(byte abyte0[], int i, Registers registers)
        throws InvalidProtocolBufferException
    {
        int j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = decodeVarint32(i, abyte0, j, registers);
        }
        j = registers.int1;
        if (j < 0)
        {
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (j == 0)
        {
            registers.object1 = "";
            return i;
        } else
        {
            registers.object1 = new String(abyte0, i, j, Internal.UTF_8);
            return i + j;
        }
    }

    static int decodeStringRequireUtf8(byte abyte0[], int i, Registers registers)
        throws InvalidProtocolBufferException
    {
        boolean flag = false;
        int j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i;
            i = j;
        } else
        {
            i = decodeVarint32(i, abyte0, j, registers);
        }
        j = registers.int1;
        if (j < 0)
        {
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        if (j == 0)
        {
            registers.object1 = "";
            return i;
        }
        if (Utf8.processor.partialIsValidUtf8(0, abyte0, i, i + j) == 0)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
        } else
        {
            registers.object1 = new String(abyte0, i, j, Internal.UTF_8);
            return i + j;
        }
    }

    static int decodeUnknownField(int i, byte abyte0[], int j, int k, UnknownFieldSetLite unknownfieldsetlite, Registers registers)
        throws InvalidProtocolBufferException
    {
        UnknownFieldSetLite unknownfieldsetlite1;
        int l;
        int k1;
        if (i >>> 3 == 0)
        {
            throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
        }
        switch (i & 7)
        {
        case 4: // '\004'
        default:
            throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");

        case 0: // '\0'
            j = decodeVarint64(abyte0, j, registers);
            unknownfieldsetlite.storeField(i, Long.valueOf(registers.long1));
            return j;

        case 5: // '\005'
            unknownfieldsetlite.storeField(i, Integer.valueOf(decodeFixed32(abyte0, j)));
            return j + 4;

        case 1: // '\001'
            unknownfieldsetlite.storeField(i, Long.valueOf(decodeFixed64(abyte0, j)));
            return j + 8;

        case 2: // '\002'
            k = j + 1;
            j = abyte0[j];
            if (j >= 0)
            {
                registers.int1 = j;
                j = k;
            } else
            {
                j = decodeVarint32(j, abyte0, k, registers);
            }
            k = registers.int1;
            if (k < 0)
            {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            if (k > abyte0.length - j)
            {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            if (k == 0)
            {
                unknownfieldsetlite.storeField(i, ByteString.EMPTY);
            } else
            {
                unknownfieldsetlite.storeField(i, ByteString.copyFrom(abyte0, j, k));
            }
            return j + k;

        case 3: // '\003'
            unknownfieldsetlite1 = new UnknownFieldSetLite();
            k1 = i & -8 | 4;
            boolean flag = false;
            l = j;
            j = ((flag) ? 1 : 0);
            break;
        }
_L5:
        if (l >= k) goto _L2; else goto _L1
_L1:
        j = l + 1;
        l = abyte0[l];
        int i1;
        if (l >= 0)
        {
            registers.int1 = l;
        } else
        {
            j = decodeVarint32(l, abyte0, j, registers);
        }
        l = registers.int1;
        if (l == k1) goto _L4; else goto _L3
_L3:
        i1 = decodeUnknownField(l, abyte0, j, k, unknownfieldsetlite1, registers);
        j = l;
        l = i1;
          goto _L5
_L2:
        int j1;
        j1 = j;
        j = l;
_L7:
        if (j > k || j1 != k1)
        {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        } else
        {
            unknownfieldsetlite.storeField(i, unknownfieldsetlite1);
            return j;
        }
_L4:
        j1 = l;
        if (true) goto _L7; else goto _L6
_L6:
    }

    static int decodeVarint32(int i, byte abyte0[], int j, Registers registers)
    {
        int k = i & 0x7f;
        i = j + 1;
        j = abyte0[j];
        if (j >= 0)
        {
            registers.int1 = k | j << 7;
            return i;
        }
        k = (j & 0x7f) << 7 | k;
        j = i + 1;
        i = abyte0[i];
        if (i >= 0)
        {
            registers.int1 = i << 14 | k;
            return j;
        }
        i = (i & 0x7f) << 14 | k;
        int l = j + 1;
        j = abyte0[j];
        if (j >= 0)
        {
            registers.int1 = i | j << 21;
            return l;
        }
        k = (j & 0x7f) << 21 | i;
        i = l + 1;
        l = abyte0[l];
        if (l >= 0)
        {
            registers.int1 = k | l << 28;
            return i;
        }
        do
        {
            j = i + 1;
            if (abyte0[i] >= 0)
            {
                registers.int1 = (l & 0x7f) << 28 | k;
                return j;
            }
            i = j;
        } while (true);
    }

    static int decodeVarint32List(int i, byte abyte0[], int j, int k, Internal.ProtobufList protobuflist, Registers registers)
    {
        protobuflist = (IntArrayList)protobuflist;
        int l = j + 1;
        j = abyte0[j];
        if (j >= 0)
        {
            registers.int1 = j;
            j = l;
        } else
        {
            j = decodeVarint32(j, abyte0, l, registers);
        }
        l = registers.int1;
        protobuflist.addInt(((IntArrayList) (protobuflist)).size, l);
        do
        {
            if (j >= k)
            {
                break;
            }
            l = j + 1;
            int i1 = abyte0[j];
            if (i1 >= 0)
            {
                registers.int1 = i1;
            } else
            {
                l = decodeVarint32(i1, abyte0, l, registers);
            }
            if (i != registers.int1)
            {
                break;
            }
            j = l + 1;
            l = abyte0[l];
            if (l >= 0)
            {
                registers.int1 = l;
            } else
            {
                j = decodeVarint32(l, abyte0, j, registers);
            }
            l = registers.int1;
            protobuflist.addInt(((IntArrayList) (protobuflist)).size, l);
        } while (true);
        return j;
    }

    static int decodeVarint64(byte abyte0[], int i, Registers registers)
    {
        int j = 7;
        int k = i + 1;
        long l = abyte0[i];
        if (l >= 0L)
        {
            registers.long1 = l;
            return k;
        }
        byte byte0 = abyte0[k];
        long l1 = byte0 & 0x7f;
        i = k + 1;
        l = l & 127L | l1 << 7;
        while (byte0 < 0) 
        {
            byte0 = abyte0[i];
            j += 7;
            l |= (long)(byte0 & 0x7f) << j;
            i++;
        }
        registers.long1 = l;
        return i;
    }

    static int skipField(int i, byte abyte0[], int j, int k, Registers registers)
        throws InvalidProtocolBufferException
    {
        if (i >>> 3 == 0)
        {
            throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
        }
        i & 7;
        JVM INSTR tableswitch 0 5: default 60
    //                   0 70
    //                   1 84
    //                   2 89
    //                   3 127
    //                   4 60
    //                   5 80;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6
_L1:
        throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
_L2:
        j = decodeVarint64(abyte0, j, registers);
_L8:
        return j;
_L6:
        return j + 4;
_L3:
        return j + 8;
_L4:
        i = j + 1;
        j = abyte0[j];
        if (j >= 0)
        {
            registers.int1 = j;
        } else
        {
            i = decodeVarint32(j, abyte0, i, registers);
        }
        return i + registers.int1;
_L5:
        int l;
        int j1;
        j1 = i & -8 | 4;
        i = 0;
        while (j < k) 
        {
            i = j + 1;
            j = abyte0[j];
            int i1;
            if (j >= 0)
            {
                registers.int1 = j;
            } else
            {
                i = decodeVarint32(j, abyte0, i, registers);
            }
            i1 = registers.int1;
            j = i;
            l = i1;
            if (i1 == j1)
            {
                continue; /* Loop/switch isn't completed */
            }
            j = skipField(i1, abyte0, i, k, registers);
            i = i1;
        }
        l = i;
        if (j <= k && l == j1) goto _L8; else goto _L7
_L7:
        throw new InvalidProtocolBufferException("Failed to parse the message.");
    }

    private class Registers
    {

        public int int1;
        public long long1;
        public Object object1;

        Registers()
        {
        }
    }

}
