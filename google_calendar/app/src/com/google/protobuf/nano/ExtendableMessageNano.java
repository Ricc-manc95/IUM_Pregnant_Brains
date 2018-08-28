// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.nano;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.protobuf.nano:
//            MessageNano, InternalNano, FieldArray, FieldData, 
//            CodedInputByteBufferNano, WireFormatNano, UnknownFieldData, CodedOutputByteBufferNano, 
//            InvalidProtocolBufferNanoException

public abstract class ExtendableMessageNano extends MessageNano
{

    public FieldArray unknownFieldData;

    public ExtendableMessageNano()
    {
    }

    public final volatile MessageNano clone()
        throws CloneNotSupportedException
    {
        return (ExtendableMessageNano)clone();
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        ExtendableMessageNano extendablemessagenano = (ExtendableMessageNano)super.clone();
        InternalNano.cloneUnknownFieldData(this, extendablemessagenano);
        return extendablemessagenano;
    }

    public int computeSerializedSize()
    {
        int j = 0;
        int k;
        if (unknownFieldData != null)
        {
            int i = 0;
            do
            {
                k = i;
                if (j >= unknownFieldData.mSize)
                {
                    break;
                }
                i += unknownFieldData.mData[j].computeSerializedSize();
                j++;
            } while (true);
        } else
        {
            k = 0;
        }
        return k;
    }

    public final boolean storeUnknownField(CodedInputByteBufferNano codedinputbytebuffernano, int i)
        throws IOException
    {
        Object obj;
        int l = codedinputbytebuffernano.bufferPos - codedinputbytebuffernano.bufferStart;
        if (!codedinputbytebuffernano.skipField(i))
        {
            return false;
        }
        int j = i >>> 3;
        int k1 = codedinputbytebuffernano.bufferPos - codedinputbytebuffernano.bufferStart - l;
        if (k1 == 0)
        {
            codedinputbytebuffernano = WireFormatNano.EMPTY_BYTES;
        } else
        {
            obj = new byte[k1];
            int l1 = codedinputbytebuffernano.bufferStart;
            System.arraycopy(codedinputbytebuffernano.buffer, l + l1, obj, 0, k1);
            codedinputbytebuffernano = ((CodedInputByteBufferNano) (obj));
        }
        obj = new UnknownFieldData(i, codedinputbytebuffernano);
        if (unknownFieldData == null)
        {
            unknownFieldData = new FieldArray();
            codedinputbytebuffernano = null;
        } else
        {
            codedinputbytebuffernano = unknownFieldData;
            i = codedinputbytebuffernano.binarySearch(j);
            if (i < 0 || ((FieldArray) (codedinputbytebuffernano)).mData[i] == FieldArray.DELETED)
            {
                codedinputbytebuffernano = null;
            } else
            {
                codedinputbytebuffernano = ((FieldArray) (codedinputbytebuffernano)).mData[i];
            }
        }
        if (codedinputbytebuffernano == null)
        {
            codedinputbytebuffernano = new FieldData();
            FieldArray fieldarray = unknownFieldData;
            i = fieldarray.binarySearch(j);
            if (i >= 0)
            {
                fieldarray.mData[i] = codedinputbytebuffernano;
            } else
            {
                i = ~i;
                if (i < fieldarray.mSize && fieldarray.mData[i] == FieldArray.DELETED)
                {
                    fieldarray.mFieldNumbers[i] = j;
                    fieldarray.mData[i] = codedinputbytebuffernano;
                } else
                {
                    if (fieldarray.mSize >= fieldarray.mFieldNumbers.length)
                    {
                        int i1 = FieldArray.idealByteArraySize(fieldarray.mSize + 1 << 2) / 4;
                        int ai[] = new int[i1];
                        FieldData afielddata[] = new FieldData[i1];
                        System.arraycopy(fieldarray.mFieldNumbers, 0, ai, 0, fieldarray.mFieldNumbers.length);
                        System.arraycopy(fieldarray.mData, 0, afielddata, 0, fieldarray.mData.length);
                        fieldarray.mFieldNumbers = ai;
                        fieldarray.mData = afielddata;
                    }
                    if (fieldarray.mSize - i != 0)
                    {
                        System.arraycopy(fieldarray.mFieldNumbers, i, fieldarray.mFieldNumbers, i + 1, fieldarray.mSize - i);
                        System.arraycopy(fieldarray.mData, i, fieldarray.mData, i + 1, fieldarray.mSize - i);
                    }
                    fieldarray.mFieldNumbers[i] = j;
                    fieldarray.mData[i] = codedinputbytebuffernano;
                    fieldarray.mSize = fieldarray.mSize + 1;
                }
            }
        }
_L6:
        if (((FieldData) (codedinputbytebuffernano)).unknownFieldData == null) goto _L2; else goto _L1
_L1:
        ((FieldData) (codedinputbytebuffernano)).unknownFieldData.add(obj);
_L4:
        return true;
_L2:
        if (!(((FieldData) (codedinputbytebuffernano)).value instanceof MessageNano))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = ((UnknownFieldData) (obj)).bytes;
        CodedInputByteBufferNano codedinputbytebuffernano1 = new CodedInputByteBufferNano(((byte []) (obj)), 0, obj.length);
        int k = codedinputbytebuffernano1.readRawVarint32();
        int j1 = obj.length;
        if (k >= 0)
        {
            i = CodedOutputByteBufferNano.computeRawVarint32Size(k);
        } else
        {
            i = 10;
        }
        if (k != j1 - i)
        {
            throw new InvalidProtocolBufferNanoException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
        }
        obj = ((MessageNano)((FieldData) (codedinputbytebuffernano)).value).mergeFrom(codedinputbytebuffernano1);
        codedinputbytebuffernano.cachedExtension = ((FieldData) (codedinputbytebuffernano)).cachedExtension;
        codedinputbytebuffernano.value = obj;
        codedinputbytebuffernano.unknownFieldData = null;
        if (true) goto _L4; else goto _L3
_L3:
        if (((FieldData) (codedinputbytebuffernano)).value instanceof MessageNano[])
        {
            Collections.singletonList(obj);
            throw new NoSuchMethodError();
        }
        Collections.singletonList(obj);
        throw new NoSuchMethodError();
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (unknownFieldData != null)
        {
            int i = 0;
            while (i < unknownFieldData.mSize) 
            {
                unknownFieldData.mData[i].writeTo(codedoutputbytebuffernano);
                i++;
            }
        }
    }
}
