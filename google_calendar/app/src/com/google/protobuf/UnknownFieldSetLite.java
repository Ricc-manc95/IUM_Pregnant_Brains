// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.util.Arrays;

// Referenced classes of package com.google.protobuf:
//            Writer, ByteString, CodedOutputStream

public final class UnknownFieldSetLite
{

    public static final UnknownFieldSetLite DEFAULT_INSTANCE = new UnknownFieldSetLite(0, new int[0], new Object[0], false);
    public int count;
    public boolean isMutable;
    public int memoizedSerializedSize;
    public Object objects[];
    public int tags[];

    UnknownFieldSetLite()
    {
        this(0, new int[8], new Object[8], true);
    }

    private UnknownFieldSetLite(int i, int ai[], Object aobj[], boolean flag)
    {
        memoizedSerializedSize = -1;
        count = i;
        tags = ai;
        objects = aobj;
        isMutable = flag;
    }

    static UnknownFieldSetLite mutableCopyOf(UnknownFieldSetLite unknownfieldsetlite, UnknownFieldSetLite unknownfieldsetlite1)
    {
        int i = unknownfieldsetlite.count + unknownfieldsetlite1.count;
        int ai[] = Arrays.copyOf(unknownfieldsetlite.tags, i);
        System.arraycopy(unknownfieldsetlite1.tags, 0, ai, unknownfieldsetlite.count, unknownfieldsetlite1.count);
        Object aobj[] = Arrays.copyOf(unknownfieldsetlite.objects, i);
        System.arraycopy(((Object) (unknownfieldsetlite1.objects)), 0, ((Object) (aobj)), unknownfieldsetlite.count, unknownfieldsetlite1.count);
        return new UnknownFieldSetLite(i, ai, aobj, true);
    }

    private static void writeField(int i, Object obj, Writer writer)
        throws IOException
    {
        int j = i >>> 3;
        switch (i & 7)
        {
        case 4: // '\004'
        default:
            throw new RuntimeException(new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type."));

        case 0: // '\0'
            writer.writeInt64(j, ((Long)obj).longValue());
            return;

        case 5: // '\005'
            writer.writeFixed32(j, ((Integer)obj).intValue());
            return;

        case 1: // '\001'
            writer.writeFixed64(j, ((Long)obj).longValue());
            return;

        case 2: // '\002'
            writer.writeBytes(j, (ByteString)obj);
            return;

        case 3: // '\003'
            break;
        }
        if (writer.fieldOrder$50KKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULRID5Q6ASH48PKMAR349TP68PBI7C______0() == android.support.v4.content.ModernAsyncTask.Status.ASCENDING$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65TBN4QBKCLP28HJ9CLM68JRICHIN4EO_0)
        {
            writer.writeStartGroup(j);
            ((UnknownFieldSetLite)obj).writeTo(writer);
            writer.writeEndGroup(j);
            return;
        } else
        {
            writer.writeEndGroup(j);
            ((UnknownFieldSetLite)obj).writeTo(writer);
            writer.writeStartGroup(j);
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        int ai[];
        int ai1[];
        int i;
        int j;
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof UnknownFieldSetLite))
        {
            return false;
        }
        obj = (UnknownFieldSetLite)obj;
        if (count != ((UnknownFieldSetLite) (obj)).count)
        {
            break; /* Loop/switch isn't completed */
        }
        ai = tags;
        ai1 = ((UnknownFieldSetLite) (obj)).tags;
        j = count;
        i = 0;
_L10:
        if (i >= j) goto _L4; else goto _L3
_L3:
        if (ai[i] == ai1[i]) goto _L6; else goto _L5
_L5:
        i = 0;
_L11:
        if (i == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        ai = ((int []) (objects));
        obj = ((Object) (((UnknownFieldSetLite) (obj)).objects));
        j = count;
        i = 0;
_L12:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_157;
        }
        if (ai[i].equals(obj[i])) goto _L8; else goto _L7
_L7:
        i = 0;
_L13:
        if (i != 0) goto _L1; else goto _L9
_L9:
        return false;
_L6:
        i++;
          goto _L10
_L4:
        i = 1;
          goto _L11
_L8:
        i++;
          goto _L12
        i = 1;
          goto _L13
    }

    public final int getSerializedSize()
    {
        int i;
        int j;
        i = memoizedSerializedSize;
        if (i != -1)
        {
            return i;
        }
        j = 0;
        i = 0;
_L8:
        int k;
        int i1;
        if (j >= count)
        {
            break MISSING_BLOCK_LABEL_218;
        }
        k = tags[j];
        i1 = k >>> 3;
        k & 7;
        JVM INSTR tableswitch 0 5: default 80
    //                   0 97
    //                   1 147
    //                   2 170
    //                   3 190
    //                   4 80
    //                   5 124;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6
_L5:
        break MISSING_BLOCK_LABEL_190;
_L6:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalStateException(new InvalidProtocolBufferException.InvalidWireTypeException("Protocol message tag had invalid wire type."));
_L2:
        i += CodedOutputStream.computeUInt64Size(i1, ((Long)objects[j]).longValue());
_L9:
        j++;
        if (true) goto _L8; else goto _L7
_L7:
        i += CodedOutputStream.computeFixed32Size(i1, ((Integer)objects[j]).intValue());
          goto _L9
_L3:
        i += CodedOutputStream.computeFixed64Size(i1, ((Long)objects[j]).longValue());
          goto _L9
_L4:
        i += CodedOutputStream.computeBytesSize(i1, (ByteString)objects[j]);
          goto _L9
        int l = CodedOutputStream.computeTagSize(i1);
        i += ((UnknownFieldSetLite)objects[j]).getSerializedSize() + (l << 1);
          goto _L9
        memoizedSerializedSize = i;
        return i;
    }

    public final int hashCode()
    {
        int l = 17;
        boolean flag = false;
        int i1 = count;
        int ai[] = tags;
        int j1 = count;
        int j = 0;
        int i = 17;
        for (; j < j1; j++)
        {
            i = i * 31 + ai[j];
        }

        ai = ((int []) (objects));
        j1 = count;
        for (int k = ((flag) ? 1 : 0); k < j1; k++)
        {
            l = l * 31 + ai[k].hashCode();
        }

        return ((i1 + 527) * 31 + i) * 31 + l;
    }

    final void storeField(int i, Object obj)
    {
        if (!isMutable)
        {
            throw new UnsupportedOperationException();
        }
        if (count == tags.length)
        {
            int j;
            if (count < 4)
            {
                j = 8;
            } else
            {
                j = count >> 1;
            }
            j += count;
            tags = Arrays.copyOf(tags, j);
            objects = Arrays.copyOf(objects, j);
        }
        tags[count] = i;
        objects[count] = obj;
        count = count + 1;
    }

    public final void writeTo(Writer writer)
        throws IOException
    {
        if (count != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (writer.fieldOrder$50KKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULRID5Q6ASH48PKMAR349TP68PBI7C______0() != android.support.v4.content.ModernAsyncTask.Status.ASCENDING$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65TBN4QBKCLP28HJ9CLM68JRICHIN4EO_0)
        {
            break; /* Loop/switch isn't completed */
        }
        int i = 0;
        while (i < count) 
        {
            writeField(tags[i], objects[i], writer);
            i++;
        }
        if (true) goto _L1; else goto _L3
_L3:
        int j = count - 1;
        while (j >= 0) 
        {
            writeField(tags[j], objects[j], writer);
            j--;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

}
