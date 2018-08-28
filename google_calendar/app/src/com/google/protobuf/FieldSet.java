// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.protobuf:
//            SmallSortedMap, MutableMessageLite, CodedOutputStream, MessageLite, 
//            Internal, ByteString, LazyField

public final class FieldSet
{

    public static final FieldSet DEFAULT_INSTANCE = new FieldSet((byte)0);
    public final SmallSortedMap fields;
    public boolean hasLazyField;
    public boolean isImmutable;

    private FieldSet()
    {
        hasLazyField = false;
        fields = new SmallSortedMap._cls1(16);
    }

    private FieldSet(byte byte0)
    {
        hasLazyField = false;
        fields = new SmallSortedMap._cls1(0);
        if (!isImmutable)
        {
            fields.makeImmutable();
            isImmutable = true;
        }
    }

    private static Object cloneIfMutable(Object obj)
    {
        Object obj1;
        if (obj instanceof MutableMessageLite)
        {
            obj1 = ((MutableMessageLite)obj).clone();
        } else
        {
            obj1 = obj;
            if (obj instanceof byte[])
            {
                obj = (byte[])obj;
                byte abyte0[] = new byte[obj.length];
                System.arraycopy(obj, 0, abyte0, 0, obj.length);
                return abyte0;
            }
        }
        return obj1;
    }

    static int computeElementSize(WireFormat.FieldType fieldtype, int i, Object obj)
    {
        i = CodedOutputStream.computeTagSize(i);
        if (fieldtype == WireFormat.FieldType.GROUP && !Internal.isProto1Group((MessageLite)obj))
        {
            i <<= 1;
        }
        return i + computeElementSizeNoTag(fieldtype, obj);
    }

    private static int computeElementSizeNoTag(WireFormat.FieldType fieldtype, Object obj)
    {
        switch (fieldtype.ordinal())
        {
        default:
            throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");

        case 0: // '\0'
            ((Double)obj).doubleValue();
            return CodedOutputStream.computeDoubleSizeNoTag$5122II8_0();

        case 1: // '\001'
            ((Float)obj).floatValue();
            return CodedOutputStream.computeFloatSizeNoTag$5132II8_0();

        case 2: // '\002'
            return CodedOutputStream.computeInt64SizeNoTag(((Long)obj).longValue());

        case 3: // '\003'
            return CodedOutputStream.computeUInt64SizeNoTag(((Long)obj).longValue());

        case 4: // '\004'
            return CodedOutputStream.computeInt32SizeNoTag(((Integer)obj).intValue());

        case 5: // '\005'
            ((Long)obj).longValue();
            return CodedOutputStream.computeFixed64SizeNoTag$5152II8_0();

        case 6: // '\006'
            ((Integer)obj).intValue();
            return CodedOutputStream.computeFixed32SizeNoTag$514III8_0();

        case 7: // '\007'
            ((Boolean)obj).booleanValue();
            return CodedOutputStream.computeBoolSizeNoTag$51D2II8_0();

        case 9: // '\t'
            return CodedOutputStream.computeGroupSizeNoTag((MessageLite)obj);

        case 11: // '\013'
            if (obj instanceof ByteString)
            {
                return CodedOutputStream.computeBytesSizeNoTag((ByteString)obj);
            } else
            {
                return CodedOutputStream.computeByteArraySizeNoTag((byte[])obj);
            }

        case 8: // '\b'
            if (obj instanceof ByteString)
            {
                return CodedOutputStream.computeBytesSizeNoTag((ByteString)obj);
            } else
            {
                return CodedOutputStream.computeStringSizeNoTag((String)obj);
            }

        case 12: // '\f'
            return CodedOutputStream.computeUInt32SizeNoTag(((Integer)obj).intValue());

        case 14: // '\016'
            ((Integer)obj).intValue();
            return CodedOutputStream.computeSFixed32SizeNoTag$514III8_0();

        case 15: // '\017'
            ((Long)obj).longValue();
            return CodedOutputStream.computeSFixed64SizeNoTag$5152II8_0();

        case 16: // '\020'
            return CodedOutputStream.computeSInt32SizeNoTag(((Integer)obj).intValue());

        case 17: // '\021'
            return CodedOutputStream.computeSInt64SizeNoTag(((Long)obj).longValue());

        case 10: // '\n'
            if (obj instanceof LazyField)
            {
                return CodedOutputStream.computeLazyFieldSizeNoTag((LazyField)obj);
            } else
            {
                return CodedOutputStream.computeMessageSizeNoTag((MessageLite)obj);
            }

        case 13: // '\r'
            break;
        }
        if (obj instanceof Internal.EnumLite)
        {
            return CodedOutputStream.computeEnumSizeNoTag(((Internal.EnumLite)obj).getNumber());
        } else
        {
            return CodedOutputStream.computeEnumSizeNoTag(((Integer)obj).intValue());
        }
    }

    public static int computeFieldSize(FieldDescriptorLite fielddescriptorlite, Object obj)
    {
        WireFormat.FieldType fieldtype;
        int i;
        int k;
        int l;
        k = 0;
        i = 0;
        fieldtype = fielddescriptorlite.getLiteType();
        l = fielddescriptorlite.getNumber();
        if (!fielddescriptorlite.isRepeated())
        {
            break MISSING_BLOCK_LABEL_137;
        }
        if (!fielddescriptorlite.isPacked()) goto _L2; else goto _L1
_L1:
        for (fielddescriptorlite = ((List)obj).iterator(); fielddescriptorlite.hasNext();)
        {
            i += computeElementSizeNoTag(fieldtype, fielddescriptorlite.next());
        }

        k = CodedOutputStream.computeTagSize(l);
        k = CodedOutputStream.computeRawVarint32Size(i) + (k + i);
_L4:
        return k;
_L2:
        fielddescriptorlite = ((List)obj).iterator();
        int j = k;
        do
        {
            k = j;
            if (!fielddescriptorlite.hasNext())
            {
                break;
            }
            j += computeElementSize(fieldtype, l, fielddescriptorlite.next());
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        return computeElementSize(fieldtype, l, obj);
    }

    static int getMessageSetSerializedSize(java.util.Map.Entry entry)
    {
        FieldDescriptorLite fielddescriptorlite = (FieldDescriptorLite)entry.getKey();
        Object obj = entry.getValue();
        if (fielddescriptorlite.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !fielddescriptorlite.isRepeated() && !fielddescriptorlite.isPacked())
        {
            if (obj instanceof LazyField)
            {
                return CodedOutputStream.computeLazyFieldMessageSetExtensionSize(((FieldDescriptorLite)entry.getKey()).getNumber(), (LazyField)obj);
            } else
            {
                return CodedOutputStream.computeMessageSetExtensionSize(((FieldDescriptorLite)entry.getKey()).getNumber(), (MessageLite)obj);
            }
        } else
        {
            return computeFieldSize(fielddescriptorlite, obj);
        }
    }

    private static boolean isInitialized(java.util.Map.Entry entry)
    {
label0:
        {
            FieldDescriptorLite fielddescriptorlite = (FieldDescriptorLite)entry.getKey();
            if (fielddescriptorlite.getLiteJavaType() != WireFormat.JavaType.MESSAGE)
            {
                break label0;
            }
            if (fielddescriptorlite.isRepeated())
            {
                entry = ((List)entry.getValue()).iterator();
                do
                {
                    if (!entry.hasNext())
                    {
                        break label0;
                    }
                } while (((MessageLite)entry.next()).isInitialized());
                return false;
            }
            entry = ((java.util.Map.Entry) (entry.getValue()));
            if (entry instanceof MessageLite)
            {
                if (!((MessageLite)entry).isInitialized())
                {
                    return false;
                }
            } else
            if (entry instanceof LazyField)
            {
                return true;
            } else
            {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    static void verifyType(WireFormat.FieldType fieldtype, Object obj)
    {
        boolean flag;
        flag = false;
        Internal.checkNotNull(obj);
        fieldtype.javaType.ordinal();
        JVM INSTR tableswitch 0 8: default 64
    //                   0 79
    //                   1 87
    //                   2 95
    //                   3 103
    //                   4 111
    //                   5 119
    //                   6 127
    //                   7 146
    //                   8 165;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        break; /* Loop/switch isn't completed */
_L10:
        break MISSING_BLOCK_LABEL_165;
_L11:
        if (!flag)
        {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        } else
        {
            return;
        }
_L2:
        flag = obj instanceof Integer;
          goto _L11
_L3:
        flag = obj instanceof Long;
          goto _L11
_L4:
        flag = obj instanceof Float;
          goto _L11
_L5:
        flag = obj instanceof Double;
          goto _L11
_L6:
        flag = obj instanceof Boolean;
          goto _L11
_L7:
        flag = obj instanceof String;
          goto _L11
_L8:
        if ((obj instanceof ByteString) || (obj instanceof byte[]))
        {
            flag = true;
        }
          goto _L11
_L9:
        if ((obj instanceof Integer) || (obj instanceof Internal.EnumLite))
        {
            flag = true;
        }
          goto _L11
        if ((obj instanceof MessageLite) || (obj instanceof LazyField))
        {
            flag = true;
        }
          goto _L11
    }

    static void writeElement(CodedOutputStream codedoutputstream, WireFormat.FieldType fieldtype, int i, Object obj)
        throws IOException
    {
        boolean flag = true;
        if (fieldtype == WireFormat.FieldType.GROUP)
        {
            if (Internal.isProto1Group((MessageLite)obj))
            {
                codedoutputstream.writeTag(i, 3);
                ((MessageLite)obj).writeTo(codedoutputstream);
                return;
            } else
            {
                fieldtype = (MessageLite)obj;
                codedoutputstream.writeTag(i, 3);
                fieldtype.writeTo(codedoutputstream);
                codedoutputstream.writeTag(i, 4);
                return;
            }
        }
        codedoutputstream.writeTag(i, fieldtype.wireType);
        switch (fieldtype.ordinal())
        {
        default:
            return;

        case 0: // '\0'
            codedoutputstream.writeFixed64NoTag(Double.doubleToRawLongBits(((Double)obj).doubleValue()));
            return;

        case 1: // '\001'
            codedoutputstream.writeFixed32NoTag(Float.floatToRawIntBits(((Float)obj).floatValue()));
            return;

        case 2: // '\002'
            codedoutputstream.writeUInt64NoTag(((Long)obj).longValue());
            return;

        case 3: // '\003'
            codedoutputstream.writeUInt64NoTag(((Long)obj).longValue());
            return;

        case 4: // '\004'
            codedoutputstream.writeInt32NoTag(((Integer)obj).intValue());
            return;

        case 5: // '\005'
            codedoutputstream.writeFixed64NoTag(((Long)obj).longValue());
            return;

        case 6: // '\006'
            codedoutputstream.writeFixed32NoTag(((Integer)obj).intValue());
            return;

        case 7: // '\007'
            if (((Boolean)obj).booleanValue())
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            codedoutputstream.write((byte)i);
            return;

        case 9: // '\t'
            ((MessageLite)obj).writeTo(codedoutputstream);
            return;

        case 10: // '\n'
            codedoutputstream.writeMessageNoTag((MessageLite)obj);
            return;

        case 8: // '\b'
            if (obj instanceof ByteString)
            {
                codedoutputstream.writeBytesNoTag((ByteString)obj);
                return;
            } else
            {
                codedoutputstream.writeStringNoTag((String)obj);
                return;
            }

        case 11: // '\013'
            if (obj instanceof ByteString)
            {
                codedoutputstream.writeBytesNoTag((ByteString)obj);
                return;
            } else
            {
                fieldtype = (byte[])obj;
                codedoutputstream.writeByteArrayNoTag(fieldtype, 0, fieldtype.length);
                return;
            }

        case 12: // '\f'
            codedoutputstream.writeUInt32NoTag(((Integer)obj).intValue());
            return;

        case 14: // '\016'
            codedoutputstream.writeFixed32NoTag(((Integer)obj).intValue());
            return;

        case 15: // '\017'
            codedoutputstream.writeFixed64NoTag(((Long)obj).longValue());
            return;

        case 16: // '\020'
            i = ((Integer)obj).intValue();
            codedoutputstream.writeUInt32NoTag(i >> 31 ^ i << 1);
            return;

        case 17: // '\021'
            long l = ((Long)obj).longValue();
            codedoutputstream.writeUInt64NoTag(l << 1 ^ l >> 63);
            return;

        case 13: // '\r'
            break;
        }
        if (obj instanceof Internal.EnumLite)
        {
            codedoutputstream.writeInt32NoTag(((Internal.EnumLite)obj).getNumber());
            return;
        } else
        {
            codedoutputstream.writeInt32NoTag(((Integer)obj).intValue());
            return;
        }
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        FieldSet fieldset = new FieldSet();
        for (int i = 0; i < fields.entryList.size(); i++)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)fields.entryList.get(i);
            fieldset.setField((FieldDescriptorLite)entry.getKey(), entry.getValue());
        }

        Object obj = fields;
        java.util.Map.Entry entry1;
        if (((SmallSortedMap) (obj)).overflowEntries.isEmpty())
        {
            obj = SmallSortedMap.EmptySet.ITERABLE;
        } else
        {
            obj = ((SmallSortedMap) (obj)).overflowEntries.entrySet();
        }
        for (obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext(); fieldset.setField((FieldDescriptorLite)entry1.getKey(), entry1.getValue()))
        {
            entry1 = (java.util.Map.Entry)((Iterator) (obj)).next();
        }

        fieldset.hasLazyField = hasLazyField;
        return fieldset;
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof FieldSet))
        {
            return false;
        } else
        {
            obj = (FieldSet)obj;
            return fields.equals(((FieldSet) (obj)).fields);
        }
    }

    public final int hashCode()
    {
        return fields.hashCode();
    }

    public final boolean isInitialized()
    {
        for (int i = 0; i < fields.entryList.size(); i++)
        {
            if (!isInitialized((java.util.Map.Entry)fields.entryList.get(i)))
            {
                return false;
            }
        }

        Object obj = fields;
        if (((SmallSortedMap) (obj)).overflowEntries.isEmpty())
        {
            obj = SmallSortedMap.EmptySet.ITERABLE;
        } else
        {
            obj = ((SmallSortedMap) (obj)).overflowEntries.entrySet();
        }
        for (obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            if (!isInitialized((java.util.Map.Entry)((Iterator) (obj)).next()))
            {
                return false;
            }
        }

        return true;
    }

    public final Iterator iterator()
    {
        if (hasLazyField)
        {
            return new LazyField.LazyIterator(fields.entrySet().iterator());
        } else
        {
            return fields.entrySet().iterator();
        }
    }

    final void mergeFromField(java.util.Map.Entry entry)
    {
        FieldDescriptorLite fielddescriptorlite = (FieldDescriptorLite)entry.getKey();
        entry = ((java.util.Map.Entry) (entry.getValue()));
        Object obj = entry;
        if (entry instanceof LazyField)
        {
            obj = LazyField.getValue();
        }
        if (fielddescriptorlite.isRepeated())
        {
            Object obj1 = fields.get(fielddescriptorlite);
            entry = ((java.util.Map.Entry) (obj1));
            if (obj1 instanceof LazyField)
            {
                entry = LazyField.getValue();
            }
            obj1 = entry;
            if (entry == null)
            {
                obj1 = new ArrayList();
            }
            for (entry = ((List)obj).iterator(); entry.hasNext(); ((List)obj1).add(cloneIfMutable(obj)))
            {
                obj = entry.next();
            }

            fields.put(fielddescriptorlite, obj1);
            return;
        }
        if (fielddescriptorlite.getLiteJavaType() == WireFormat.JavaType.MESSAGE)
        {
            Object obj2 = fields.get(fielddescriptorlite);
            entry = ((java.util.Map.Entry) (obj2));
            if (obj2 instanceof LazyField)
            {
                entry = LazyField.getValue();
            }
            if (entry == null)
            {
                fields.put(fielddescriptorlite, cloneIfMutable(obj));
                return;
            }
            if (entry instanceof MutableMessageLite)
            {
                entry = fielddescriptorlite._mth51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQTBKC5H6OPADCLPN6OB7CL66IT357D666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQTBKC5H6OPADCLPN6OB7CL66IT357CKKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UJBLEHGM4R359LIN6SR1CTIKOQBKCKTG____0();
            } else
            {
                entry = fielddescriptorlite.internalMergeFrom(((MessageLite)entry).toBuilder(), (MessageLite)obj).build();
            }
            fields.put(fielddescriptorlite, entry);
            return;
        } else
        {
            fields.put(fielddescriptorlite, cloneIfMutable(obj));
            return;
        }
    }

    public final void setField(FieldDescriptorLite fielddescriptorlite, Object obj)
    {
        if (fielddescriptorlite.isRepeated())
        {
            if (!(obj instanceof List))
            {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arraylist = new ArrayList();
            arraylist.addAll((List)obj);
            ArrayList arraylist1 = (ArrayList)arraylist;
            int j = arraylist1.size();
            int i = 0;
            do
            {
                obj = arraylist;
                if (i >= j)
                {
                    break;
                }
                obj = arraylist1.get(i);
                i++;
                verifyType(fielddescriptorlite.getLiteType(), obj);
            } while (true);
        } else
        {
            verifyType(fielddescriptorlite.getLiteType(), obj);
        }
        if (obj instanceof LazyField)
        {
            hasLazyField = true;
        }
        fields.put(fielddescriptorlite, obj);
    }


    private class FieldDescriptorLite
        implements Comparable
    {

        public abstract WireFormat.JavaType getLiteJavaType();

        public abstract WireFormat.FieldType getLiteType();

        public abstract int getNumber();

        public abstract MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messagelite);

        public abstract MutableMessageLite internalMergeFrom$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQTBKC5H6OPADCLPN6OB7CL66IT357D666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQTBKC5H6OPADCLPN6OB7CL66IT357CKKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UJBLEHGM4R359LIN6SR1CTIKOQBKCKTG____0();

        public abstract boolean isPacked();

        public abstract boolean isRepeated();
    }

}
