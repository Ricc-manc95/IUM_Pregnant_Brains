// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.intention.habit.client.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class cachedSize extends ExtendableMessageNano
{

    public String color;
    public initialInstanceData contract;
    public String customName;
    private tanceData initialInstanceData;
    public tanceData reminders;
    public String summary;
    private int transparency;
    public int type;
    public int visibility;

    private final cachedSize mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
_L13:
        int i = codedinputbytebuffernano.readTag();
        i;
        JVM INSTR lookupswitch 10: default 96
    //                   0: 105
    //                   8: 107
    //                   18: 276
    //                   26: 287
    //                   42: 316
    //                   48: 327
    //                   56: 419
    //                   66: 511
    //                   74: 540
    //                   82: 551;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L1:
        if (super.storeUnknownField(codedinputbytebuffernano, i))
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        return this;
_L3:
        int j;
        int k;
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        int l = codedinputbytebuffernano.readRawVarint32();
        while (l >= 0 && l <= 0 || l >= 100 && l <= 114 || l >= 200 && l <= 212 || l >= 300 && l <= 312 || l >= 400 && l <= 413 || l >= 500 && l <= 512) 
        {
            try
            {
                type = l;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(36)).append(l).append(" is not a valid enum Type").toString());
_L4:
        customName = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L5:
        if (contract == null)
        {
            contract = new init>();
        }
        codedinputbytebuffernano.readMessage(contract);
        continue; /* Loop/switch isn't completed */
_L6:
        color = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L7:
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 2)
        {
            try
            {
                visibility = l;
            }
            catch (IllegalArgumentException illegalargumentexception1)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(42)).append(l).append(" is not a valid enum Visibility").toString());
_L8:
        j = codedinputbytebuffernano.bufferPos;
        k = codedinputbytebuffernano.bufferStart;
        l = codedinputbytebuffernano.readRawVarint32();
        if (l >= 0 && l <= 1)
        {
            try
            {
                transparency = l;
            }
            catch (IllegalArgumentException illegalargumentexception2)
            {
                codedinputbytebuffernano.rewindToPositionAndTag(j - k, codedinputbytebuffernano.lastTag);
                storeUnknownField(codedinputbytebuffernano, i);
            }
            continue; /* Loop/switch isn't completed */
        }
        throw new IllegalArgumentException((new StringBuilder(44)).append(l).append(" is not a valid enum Transparency").toString());
_L9:
        if (reminders == null)
        {
            reminders = new <init>();
        }
        codedinputbytebuffernano.readMessage(reminders);
        continue; /* Loop/switch isn't completed */
_L10:
        summary = codedinputbytebuffernano.readString();
        continue; /* Loop/switch isn't completed */
_L11:
        if (initialInstanceData == null)
        {
            initialInstanceData = new tanceData();
        }
        codedinputbytebuffernano.readMessage(initialInstanceData);
        if (true) goto _L13; else goto _L12
_L12:
    }

    protected final int computeSerializedSize()
    {
        int k = 10;
        int j = super.computeSerializedSize();
        if (type != 0)
        {
            int i = type;
            int l = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            Object obj;
            int i1;
            if (i >= 0)
            {
                i = CodedOutputByteBufferNano.computeRawVarint32Size(i);
            } else
            {
                i = 10;
            }
            j = i + l + j;
        }
        i = j;
        if (customName != null)
        {
            i = j;
            if (!customName.equals(""))
            {
                obj = customName;
                i = CodedOutputByteBufferNano.computeRawVarint32Size(16);
                l = CodedOutputByteBufferNano.encodedLength(((CharSequence) (obj)));
                i = j + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + i);
            }
        }
        j = i;
        if (contract != null)
        {
            obj = contract;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            l = ((MessageNano) (obj)).computeSerializedSize();
            obj.cachedSize = l;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(l) + l + j);
        }
        i = j;
        if (color != null)
        {
            i = j;
            if (!color.equals(""))
            {
                obj = color;
                i = CodedOutputByteBufferNano.computeRawVarint32Size(40);
                l = CodedOutputByteBufferNano.encodedLength(((CharSequence) (obj)));
                i = j + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + i);
            }
        }
        j = i;
        if (visibility != 0)
        {
            j = visibility;
            l = CodedOutputByteBufferNano.computeRawVarint32Size(48);
            if (j >= 0)
            {
                j = CodedOutputByteBufferNano.computeRawVarint32Size(j);
            } else
            {
                j = 10;
            }
            j = i + (j + l);
        }
        i = j;
        if (transparency != 0)
        {
            i1 = transparency;
            l = CodedOutputByteBufferNano.computeRawVarint32Size(56);
            i = k;
            if (i1 >= 0)
            {
                i = CodedOutputByteBufferNano.computeRawVarint32Size(i1);
            }
            i = j + (i + l);
        }
        j = i;
        if (reminders != null)
        {
            obj = reminders;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(64);
            k = ((MessageNano) (obj)).computeSerializedSize();
            obj.cachedSize = k;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(k) + k + j);
        }
        i = j;
        if (summary != null)
        {
            i = j;
            if (!summary.equals(""))
            {
                obj = summary;
                i = CodedOutputByteBufferNano.computeRawVarint32Size(72);
                k = CodedOutputByteBufferNano.encodedLength(((CharSequence) (obj)));
                i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
            }
        }
        j = i;
        if (initialInstanceData != null)
        {
            obj = initialInstanceData;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(80);
            k = ((MessageNano) (obj)).computeSerializedSize();
            obj.cachedSize = k;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(k) + k + j);
        }
        return j;
    }

    public final volatile MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        return mergeFrom(codedinputbytebuffernano);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        int i;
        if (type != 0)
        {
            i = type;
            codedoutputbytebuffernano.writeRawVarint32(8);
            Object obj;
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (customName != null && !customName.equals(""))
        {
            obj = customName;
            codedoutputbytebuffernano.writeRawVarint32(18);
            codedoutputbytebuffernano.writeStringNoTag(((String) (obj)));
        }
        if (contract != null)
        {
            obj = contract;
            codedoutputbytebuffernano.writeRawVarint32(26);
            if (((MessageNano) (obj)).cachedSize < 0)
            {
                obj.cachedSize = ((MessageNano) (obj)).computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (obj)).cachedSize);
            ((MessageNano) (obj)).writeTo(codedoutputbytebuffernano);
        }
        if (color != null && !color.equals(""))
        {
            obj = color;
            codedoutputbytebuffernano.writeRawVarint32(42);
            codedoutputbytebuffernano.writeStringNoTag(((String) (obj)));
        }
        if (visibility != 0)
        {
            i = visibility;
            codedoutputbytebuffernano.writeRawVarint32(48);
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (transparency != 0)
        {
            i = transparency;
            codedoutputbytebuffernano.writeRawVarint32(56);
            if (i >= 0)
            {
                codedoutputbytebuffernano.writeRawVarint32(i);
            } else
            {
                codedoutputbytebuffernano.writeRawVarint64(i);
            }
        }
        if (reminders != null)
        {
            obj = reminders;
            codedoutputbytebuffernano.writeRawVarint32(66);
            if (((MessageNano) (obj)).cachedSize < 0)
            {
                obj.cachedSize = ((MessageNano) (obj)).computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (obj)).cachedSize);
            ((MessageNano) (obj)).writeTo(codedoutputbytebuffernano);
        }
        if (summary != null && !summary.equals(""))
        {
            obj = summary;
            codedoutputbytebuffernano.writeRawVarint32(74);
            codedoutputbytebuffernano.writeStringNoTag(((String) (obj)));
        }
        if (initialInstanceData != null)
        {
            obj = initialInstanceData;
            codedoutputbytebuffernano.writeRawVarint32(82);
            if (((MessageNano) (obj)).cachedSize < 0)
            {
                obj.cachedSize = ((MessageNano) (obj)).computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (obj)).cachedSize);
            ((MessageNano) (obj)).writeTo(codedoutputbytebuffernano);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public tanceData()
    {
        type = 0;
        customName = "";
        summary = "";
        contract = null;
        color = "";
        visibility = 0;
        transparency = 0;
        reminders = null;
        initialInstanceData = null;
        unknownFieldData = null;
        cachedSize = -1;
    }
}
