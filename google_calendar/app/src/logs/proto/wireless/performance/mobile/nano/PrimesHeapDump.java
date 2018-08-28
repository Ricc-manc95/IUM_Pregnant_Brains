// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

// Referenced classes of package logs.proto.wireless.performance.mobile.nano:
//            ClassInfo, ClassInstance, ArrayInstance, PrimitiveArrayInstance, 
//            CollapsedArrayInstance, Root, HeapDumpContext

public final class PrimesHeapDump extends ExtendableMessageNano
{

    public ArrayInstance arrayInstance[];
    public ClassInfo classInfo[];
    public ClassInstance classInstance[];
    private CollapsedArrayInstance collapsedArrayInstance[];
    public HeapDumpContext context;
    public PrimitiveArrayInstance primitiveArrayInstance[];
    public Root roots[];
    private Integer totalPss;

    public PrimesHeapDump()
    {
        classInfo = ClassInfo.emptyArray();
        classInstance = ClassInstance.emptyArray();
        arrayInstance = ArrayInstance.emptyArray();
        primitiveArrayInstance = PrimitiveArrayInstance.emptyArray();
        collapsedArrayInstance = CollapsedArrayInstance.emptyArray();
        roots = Root.emptyArray();
        totalPss = null;
        context = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int j = super.computeSerializedSize();
        int i = j;
        if (classInfo != null)
        {
            i = j;
            if (classInfo.length > 0)
            {
                i = j;
                for (j = 0; j < classInfo.length;)
                {
                    ClassInfo classinfo = classInfo[j];
                    int l = i;
                    if (classinfo != null)
                    {
                        l = i + CodedOutputByteBufferNano.computeMessageSize(1, classinfo);
                    }
                    j++;
                    i = l;
                }

            }
        }
        j = i;
        if (classInstance != null)
        {
            j = i;
            if (classInstance.length > 0)
            {
                for (j = 0; j < classInstance.length;)
                {
                    ClassInstance classinstance = classInstance[j];
                    int i1 = i;
                    if (classinstance != null)
                    {
                        i1 = i + CodedOutputByteBufferNano.computeMessageSize(2, classinstance);
                    }
                    j++;
                    i = i1;
                }

                j = i;
            }
        }
        i = j;
        if (arrayInstance != null)
        {
            i = j;
            if (arrayInstance.length > 0)
            {
                i = j;
                for (j = 0; j < arrayInstance.length;)
                {
                    ArrayInstance arrayinstance = arrayInstance[j];
                    int j1 = i;
                    if (arrayinstance != null)
                    {
                        j1 = i + CodedOutputByteBufferNano.computeMessageSize(3, arrayinstance);
                    }
                    j++;
                    i = j1;
                }

            }
        }
        j = i;
        if (primitiveArrayInstance != null)
        {
            j = i;
            if (primitiveArrayInstance.length > 0)
            {
                for (j = 0; j < primitiveArrayInstance.length;)
                {
                    PrimitiveArrayInstance primitivearrayinstance = primitiveArrayInstance[j];
                    int k1 = i;
                    if (primitivearrayinstance != null)
                    {
                        k1 = i + CodedOutputByteBufferNano.computeMessageSize(4, primitivearrayinstance);
                    }
                    j++;
                    i = k1;
                }

                j = i;
            }
        }
        i = j;
        if (totalPss != null)
        {
            i = j + CodedOutputByteBufferNano.computeInt32Size(5, totalPss.intValue());
        }
        j = i;
        if (roots != null)
        {
            j = i;
            if (roots.length > 0)
            {
                for (j = 0; j < roots.length;)
                {
                    Root root = roots[j];
                    int l1 = i;
                    if (root != null)
                    {
                        l1 = i + CodedOutputByteBufferNano.computeMessageSize(6, root);
                    }
                    j++;
                    i = l1;
                }

                j = i;
            }
        }
        i = j;
        if (context != null)
        {
            i = j + CodedOutputByteBufferNano.computeMessageSize(7, context);
        }
        int i2 = i;
        if (collapsedArrayInstance != null)
        {
            i2 = i;
            if (collapsedArrayInstance.length > 0)
            {
                int k = ((flag) ? 1 : 0);
                do
                {
                    i2 = i;
                    if (k >= collapsedArrayInstance.length)
                    {
                        break;
                    }
                    CollapsedArrayInstance collapsedarrayinstance = collapsedArrayInstance[k];
                    i2 = i;
                    if (collapsedarrayinstance != null)
                    {
                        i2 = i + CodedOutputByteBufferNano.computeMessageSize(8, collapsedarrayinstance);
                    }
                    k++;
                    i = i2;
                } while (true);
            }
        }
        return i2;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        do
        {
            int i = codedinputbytebuffernano.readTag();
            switch (i)
            {
            default:
                if (super.storeUnknownField(codedinputbytebuffernano, i))
                {
                    continue;
                }
                // fall through

            case 0: // '\0'
                return this;

            case 10: // '\n'
                int l1 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 10);
                ClassInfo aclassinfo[];
                int j;
                if (classInfo == null)
                {
                    j = 0;
                } else
                {
                    j = classInfo.length;
                }
                aclassinfo = new ClassInfo[l1 + j];
                l1 = j;
                if (j != 0)
                {
                    System.arraycopy(classInfo, 0, aclassinfo, 0, j);
                    l1 = j;
                }
                for (; l1 < aclassinfo.length - 1; l1++)
                {
                    aclassinfo[l1] = new ClassInfo();
                    codedinputbytebuffernano.readMessage(aclassinfo[l1]);
                    codedinputbytebuffernano.readTag();
                }

                aclassinfo[l1] = new ClassInfo();
                codedinputbytebuffernano.readMessage(aclassinfo[l1]);
                classInfo = aclassinfo;
                break;

            case 18: // '\022'
                int i2 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 18);
                ClassInstance aclassinstance[];
                int k;
                if (classInstance == null)
                {
                    k = 0;
                } else
                {
                    k = classInstance.length;
                }
                aclassinstance = new ClassInstance[i2 + k];
                i2 = k;
                if (k != 0)
                {
                    System.arraycopy(classInstance, 0, aclassinstance, 0, k);
                    i2 = k;
                }
                for (; i2 < aclassinstance.length - 1; i2++)
                {
                    aclassinstance[i2] = new ClassInstance();
                    codedinputbytebuffernano.readMessage(aclassinstance[i2]);
                    codedinputbytebuffernano.readTag();
                }

                aclassinstance[i2] = new ClassInstance();
                codedinputbytebuffernano.readMessage(aclassinstance[i2]);
                classInstance = aclassinstance;
                break;

            case 26: // '\032'
                int j2 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 26);
                ArrayInstance aarrayinstance[];
                int l;
                if (arrayInstance == null)
                {
                    l = 0;
                } else
                {
                    l = arrayInstance.length;
                }
                aarrayinstance = new ArrayInstance[j2 + l];
                j2 = l;
                if (l != 0)
                {
                    System.arraycopy(arrayInstance, 0, aarrayinstance, 0, l);
                    j2 = l;
                }
                for (; j2 < aarrayinstance.length - 1; j2++)
                {
                    aarrayinstance[j2] = new ArrayInstance();
                    codedinputbytebuffernano.readMessage(aarrayinstance[j2]);
                    codedinputbytebuffernano.readTag();
                }

                aarrayinstance[j2] = new ArrayInstance();
                codedinputbytebuffernano.readMessage(aarrayinstance[j2]);
                arrayInstance = aarrayinstance;
                break;

            case 34: // '"'
                int k2 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 34);
                PrimitiveArrayInstance aprimitivearrayinstance[];
                int i1;
                if (primitiveArrayInstance == null)
                {
                    i1 = 0;
                } else
                {
                    i1 = primitiveArrayInstance.length;
                }
                aprimitivearrayinstance = new PrimitiveArrayInstance[k2 + i1];
                k2 = i1;
                if (i1 != 0)
                {
                    System.arraycopy(primitiveArrayInstance, 0, aprimitivearrayinstance, 0, i1);
                    k2 = i1;
                }
                for (; k2 < aprimitivearrayinstance.length - 1; k2++)
                {
                    aprimitivearrayinstance[k2] = new PrimitiveArrayInstance();
                    codedinputbytebuffernano.readMessage(aprimitivearrayinstance[k2]);
                    codedinputbytebuffernano.readTag();
                }

                aprimitivearrayinstance[k2] = new PrimitiveArrayInstance();
                codedinputbytebuffernano.readMessage(aprimitivearrayinstance[k2]);
                primitiveArrayInstance = aprimitivearrayinstance;
                break;

            case 40: // '('
                totalPss = Integer.valueOf(codedinputbytebuffernano.readRawVarint32());
                break;

            case 50: // '2'
                int l2 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 50);
                Root aroot[];
                int j1;
                if (roots == null)
                {
                    j1 = 0;
                } else
                {
                    j1 = roots.length;
                }
                aroot = new Root[l2 + j1];
                l2 = j1;
                if (j1 != 0)
                {
                    System.arraycopy(roots, 0, aroot, 0, j1);
                    l2 = j1;
                }
                for (; l2 < aroot.length - 1; l2++)
                {
                    aroot[l2] = new Root();
                    codedinputbytebuffernano.readMessage(aroot[l2]);
                    codedinputbytebuffernano.readTag();
                }

                aroot[l2] = new Root();
                codedinputbytebuffernano.readMessage(aroot[l2]);
                roots = aroot;
                break;

            case 58: // ':'
                if (context == null)
                {
                    context = new HeapDumpContext();
                }
                codedinputbytebuffernano.readMessage(context);
                break;

            case 66: // 'B'
                int i3 = WireFormatNano.getRepeatedFieldArrayLength(codedinputbytebuffernano, 66);
                CollapsedArrayInstance acollapsedarrayinstance[];
                int k1;
                if (collapsedArrayInstance == null)
                {
                    k1 = 0;
                } else
                {
                    k1 = collapsedArrayInstance.length;
                }
                acollapsedarrayinstance = new CollapsedArrayInstance[i3 + k1];
                i3 = k1;
                if (k1 != 0)
                {
                    System.arraycopy(collapsedArrayInstance, 0, acollapsedarrayinstance, 0, k1);
                    i3 = k1;
                }
                for (; i3 < acollapsedarrayinstance.length - 1; i3++)
                {
                    acollapsedarrayinstance[i3] = new CollapsedArrayInstance();
                    codedinputbytebuffernano.readMessage(acollapsedarrayinstance[i3]);
                    codedinputbytebuffernano.readTag();
                }

                acollapsedarrayinstance[i3] = new CollapsedArrayInstance();
                codedinputbytebuffernano.readMessage(acollapsedarrayinstance[i3]);
                collapsedArrayInstance = acollapsedarrayinstance;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        boolean flag = false;
        if (classInfo != null && classInfo.length > 0)
        {
            for (int i = 0; i < classInfo.length; i++)
            {
                ClassInfo classinfo = classInfo[i];
                if (classinfo != null)
                {
                    codedoutputbytebuffernano.writeMessage(1, classinfo);
                }
            }

        }
        if (classInstance != null && classInstance.length > 0)
        {
            for (int j = 0; j < classInstance.length; j++)
            {
                ClassInstance classinstance = classInstance[j];
                if (classinstance != null)
                {
                    codedoutputbytebuffernano.writeMessage(2, classinstance);
                }
            }

        }
        if (arrayInstance != null && arrayInstance.length > 0)
        {
            for (int k = 0; k < arrayInstance.length; k++)
            {
                ArrayInstance arrayinstance = arrayInstance[k];
                if (arrayinstance != null)
                {
                    codedoutputbytebuffernano.writeMessage(3, arrayinstance);
                }
            }

        }
        if (primitiveArrayInstance != null && primitiveArrayInstance.length > 0)
        {
            for (int l = 0; l < primitiveArrayInstance.length; l++)
            {
                PrimitiveArrayInstance primitivearrayinstance = primitiveArrayInstance[l];
                if (primitivearrayinstance != null)
                {
                    codedoutputbytebuffernano.writeMessage(4, primitivearrayinstance);
                }
            }

        }
        if (totalPss != null)
        {
            codedoutputbytebuffernano.writeInt32(5, totalPss.intValue());
        }
        if (roots != null && roots.length > 0)
        {
            for (int i1 = 0; i1 < roots.length; i1++)
            {
                Root root = roots[i1];
                if (root != null)
                {
                    codedoutputbytebuffernano.writeMessage(6, root);
                }
            }

        }
        if (context != null)
        {
            codedoutputbytebuffernano.writeMessage(7, context);
        }
        if (collapsedArrayInstance != null && collapsedArrayInstance.length > 0)
        {
            for (int j1 = ((flag) ? 1 : 0); j1 < collapsedArrayInstance.length; j1++)
            {
                CollapsedArrayInstance collapsedarrayinstance = collapsedArrayInstance[j1];
                if (collapsedarrayinstance != null)
                {
                    codedoutputbytebuffernano.writeMessage(8, collapsedarrayinstance);
                }
            }

        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
