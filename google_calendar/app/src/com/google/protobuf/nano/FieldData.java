// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.nano;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.protobuf.nano:
//            MessageNano, UnknownFieldData, CodedOutputByteBufferNano, Extension

final class FieldData
    implements Cloneable
{

    public Extension cachedExtension;
    public List unknownFieldData;
    public Object value;

    FieldData()
    {
        unknownFieldData = new ArrayList();
    }

    public final FieldData clone()
    {
        Object obj = new FieldData();
        obj.cachedExtension = cachedExtension;
        if (unknownFieldData != null) goto _L2; else goto _L1
_L1:
        obj.unknownFieldData = null;
_L7:
        if (value == null) goto _L4; else goto _L3
_L3:
        if (!(value instanceof MessageNano)) goto _L6; else goto _L5
_L5:
        obj.value = (MessageNano)((MessageNano)value).clone();
        return ((FieldData) (obj));
_L2:
        try
        {
            ((FieldData) (obj)).unknownFieldData.addAll(unknownFieldData);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw new AssertionError(obj);
        }
          goto _L7
_L6:
        if (!(value instanceof byte[]))
        {
            break MISSING_BLOCK_LABEL_117;
        }
        obj.value = ((byte[])value).clone();
        return ((FieldData) (obj));
        if (!(value instanceof byte[][])) goto _L9; else goto _L8
_L8:
        Object obj1;
        Object obj2;
        obj1 = (byte[][])value;
        obj2 = new byte[obj1.length][];
        obj.value = obj2;
        int i = 0;
_L10:
        if (i >= obj1.length)
        {
            break; /* Loop/switch isn't completed */
        }
        obj2[i] = (byte[])obj1[i].clone();
        i++;
        if (true) goto _L10; else goto _L4
_L9:
        if (!(value instanceof boolean[]))
        {
            break MISSING_BLOCK_LABEL_205;
        }
        obj.value = ((boolean[])value).clone();
        return ((FieldData) (obj));
        if (!(value instanceof int[]))
        {
            break MISSING_BLOCK_LABEL_231;
        }
        obj.value = ((int[])value).clone();
        return ((FieldData) (obj));
        if (!(value instanceof long[]))
        {
            break MISSING_BLOCK_LABEL_257;
        }
        obj.value = ((long[])value).clone();
        return ((FieldData) (obj));
        if (!(value instanceof float[]))
        {
            break MISSING_BLOCK_LABEL_283;
        }
        obj.value = ((float[])value).clone();
        return ((FieldData) (obj));
        if (!(value instanceof double[]))
        {
            break MISSING_BLOCK_LABEL_309;
        }
        obj.value = ((double[])value).clone();
        return ((FieldData) (obj));
        if (!(value instanceof MessageNano[])) goto _L4; else goto _L11
_L11:
        obj1 = (MessageNano[])value;
        obj2 = new MessageNano[obj1.length];
        obj.value = obj2;
        i = 0;
_L12:
        if (i >= obj1.length)
        {
            break; /* Loop/switch isn't completed */
        }
        obj2[i] = (MessageNano)obj1[i].clone();
        i++;
        if (true) goto _L12; else goto _L4
_L4:
        return ((FieldData) (obj));
    }

    public final volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    final int computeSerializedSize()
    {
        if (value != null)
        {
            throw new NoSuchMethodError();
        }
        Iterator iterator = unknownFieldData.iterator();
        UnknownFieldData unknownfielddata;
        int i;
        int j;
        for (i = 0; iterator.hasNext(); i = unknownfielddata.bytes.length + (j + 0) + i)
        {
            unknownfielddata = (UnknownFieldData)iterator.next();
            j = CodedOutputByteBufferNano.computeRawVarint32Size(unknownfielddata.tag);
        }

        return i;
    }

    public final boolean equals(Object obj)
    {
        boolean flag2 = false;
        boolean flag;
        if (obj == this)
        {
            flag = true;
        } else
        {
            flag = flag2;
            if (obj instanceof FieldData)
            {
                obj = (FieldData)obj;
                if (value != null && ((FieldData) (obj)).value != null)
                {
                    flag = flag2;
                    if (cachedExtension == ((FieldData) (obj)).cachedExtension)
                    {
                        if (!cachedExtension.clazz.isArray())
                        {
                            return value.equals(((FieldData) (obj)).value);
                        }
                        if (value instanceof byte[])
                        {
                            return Arrays.equals((byte[])value, (byte[])((FieldData) (obj)).value);
                        }
                        if (value instanceof int[])
                        {
                            return Arrays.equals((int[])value, (int[])((FieldData) (obj)).value);
                        }
                        if (value instanceof long[])
                        {
                            return Arrays.equals((long[])value, (long[])((FieldData) (obj)).value);
                        }
                        if (value instanceof float[])
                        {
                            return Arrays.equals((float[])value, (float[])((FieldData) (obj)).value);
                        }
                        if (value instanceof double[])
                        {
                            return Arrays.equals((double[])value, (double[])((FieldData) (obj)).value);
                        }
                        if (value instanceof boolean[])
                        {
                            return Arrays.equals((boolean[])value, (boolean[])((FieldData) (obj)).value);
                        } else
                        {
                            return Arrays.deepEquals((Object[])value, (Object[])((FieldData) (obj)).value);
                        }
                    }
                } else
                {
                    if (unknownFieldData != null && ((FieldData) (obj)).unknownFieldData != null)
                    {
                        return unknownFieldData.equals(((FieldData) (obj)).unknownFieldData);
                    }
                    boolean flag1;
                    try
                    {
                        byte abyte0[] = new byte[computeSerializedSize()];
                        writeTo(new CodedOutputByteBufferNano(abyte0, 0, abyte0.length));
                        byte abyte1[] = new byte[((FieldData) (obj)).computeSerializedSize()];
                        ((FieldData) (obj)).writeTo(new CodedOutputByteBufferNano(abyte1, 0, abyte1.length));
                        flag1 = Arrays.equals(abyte0, abyte1);
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj)
                    {
                        throw new IllegalStateException(((Throwable) (obj)));
                    }
                    return flag1;
                }
            }
        }
        return flag;
    }

    public final int hashCode()
    {
        int i;
        try
        {
            byte abyte0[] = new byte[computeSerializedSize()];
            writeTo(new CodedOutputByteBufferNano(abyte0, 0, abyte0.length));
            i = Arrays.hashCode(abyte0);
        }
        catch (IOException ioexception)
        {
            throw new IllegalStateException(ioexception);
        }
        return i + 527;
    }

    final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (value != null)
        {
            throw new NoSuchMethodError();
        }
        for (Iterator iterator = unknownFieldData.iterator(); iterator.hasNext();)
        {
            UnknownFieldData unknownfielddata = (UnknownFieldData)iterator.next();
            codedoutputbytebuffernano.writeRawVarint32(unknownfielddata.tag);
            byte abyte0[] = unknownfielddata.bytes;
            int i = abyte0.length;
            if (codedoutputbytebuffernano.buffer.remaining() >= i)
            {
                codedoutputbytebuffernano.buffer.put(abyte0, 0, i);
            } else
            {
                throw new CodedOutputByteBufferNano.OutOfSpaceException(codedoutputbytebuffernano.buffer.position(), codedoutputbytebuffernano.buffer.limit());
            }
        }

    }
}
