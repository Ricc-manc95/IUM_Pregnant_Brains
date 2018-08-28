// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.protobuf:
//            Writer, Internal, CodedOutputStream, ByteString, 
//            MessageLite, Schema, FieldSet, LazyStringList

final class CodedOutputStreamWriter
    implements Writer
{

    private final CodedOutputStream output;

    CodedOutputStreamWriter(CodedOutputStream codedoutputstream)
    {
        output = (CodedOutputStream)Internal.checkNotNull(codedoutputstream, "output");
        output.wrapper = this;
    }

    public final int fieldOrder$50KKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULRID5Q6ASH48PKMAR349TP68PBI7C______0()
    {
        return android.support.v4.content.ModernAsyncTask.Status.ASCENDING$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65TBN4QBKCLP28HJ9CLM68JRICHIN4EO_0;
    }

    public final void writeBool(int i, boolean flag)
        throws IOException
    {
        output.writeBool(i, flag);
    }

    public final void writeBoolList(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                ((Boolean)list.get(i)).booleanValue();
                j += CodedOutputStream.computeBoolSizeNoTag$51D2II8_0();
            }

            output.writeUInt32NoTag(j);
            i = 0;
            while (i < list.size()) 
            {
                CodedOutputStream codedoutputstream = output;
                if (((Boolean)list.get(i)).booleanValue())
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                codedoutputstream.write((byte)j);
                i++;
            }
        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeBool(i, ((Boolean)list.get(j)).booleanValue());
            }

        }
    }

    public final void writeBytes(int i, ByteString bytestring)
        throws IOException
    {
        output.writeBytes(i, bytestring);
    }

    public final void writeBytesList(int i, List list)
        throws IOException
    {
        for (int j = 0; j < list.size(); j++)
        {
            output.writeBytes(i, (ByteString)list.get(j));
        }

    }

    public final void writeDouble(int i, double d)
        throws IOException
    {
        output.writeFixed64(i, Double.doubleToRawLongBits(d));
    }

    public final void writeDoubleList(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                ((Double)list.get(i)).doubleValue();
                j += CodedOutputStream.computeDoubleSizeNoTag$5122II8_0();
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeFixed64NoTag(Double.doubleToRawLongBits(((Double)list.get(i)).doubleValue()));
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeFixed64(i, Double.doubleToRawLongBits(((Double)list.get(j)).doubleValue()));
            }

        }
    }

    public final void writeEndGroup(int i)
        throws IOException
    {
        output.writeTag(i, 4);
    }

    public final void writeEnum(int i, int j)
        throws IOException
    {
        output.writeInt32(i, j);
    }

    public final void writeEnumList(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                j += CodedOutputStream.computeEnumSizeNoTag(((Integer)list.get(i)).intValue());
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeInt32NoTag(((Integer)list.get(i)).intValue());
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeInt32(i, ((Integer)list.get(j)).intValue());
            }

        }
    }

    public final void writeFixed32(int i, int j)
        throws IOException
    {
        output.writeFixed32(i, j);
    }

    public final void writeFixed32List(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                ((Integer)list.get(i)).intValue();
                j += CodedOutputStream.computeFixed32SizeNoTag$514III8_0();
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeFixed32NoTag(((Integer)list.get(i)).intValue());
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeFixed32(i, ((Integer)list.get(j)).intValue());
            }

        }
    }

    public final void writeFixed64(int i, long l)
        throws IOException
    {
        output.writeFixed64(i, l);
    }

    public final void writeFixed64List(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                ((Long)list.get(i)).longValue();
                j += CodedOutputStream.computeFixed64SizeNoTag$5152II8_0();
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeFixed64NoTag(((Long)list.get(i)).longValue());
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeFixed64(i, ((Long)list.get(j)).longValue());
            }

        }
    }

    public final void writeFloat(int i, float f)
        throws IOException
    {
        output.writeFixed32(i, Float.floatToRawIntBits(f));
    }

    public final void writeFloatList(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                ((Float)list.get(i)).floatValue();
                j += CodedOutputStream.computeFloatSizeNoTag$5132II8_0();
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeFixed32NoTag(Float.floatToRawIntBits(((Float)list.get(i)).floatValue()));
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeFixed32(i, Float.floatToRawIntBits(((Float)list.get(j)).floatValue()));
            }

        }
    }

    public final void writeGroup(int i, Object obj, Schema schema)
        throws IOException
    {
        CodedOutputStream codedoutputstream = output;
        obj = (MessageLite)obj;
        codedoutputstream.writeTag(i, 3);
        schema.writeTo(obj, codedoutputstream.wrapper);
        codedoutputstream.writeTag(i, 4);
    }

    public final void writeGroupList(int i, List list, Schema schema)
        throws IOException
    {
        for (int j = 0; j < list.size(); j++)
        {
            writeGroup(i, list.get(j), schema);
        }

    }

    public final void writeInt32(int i, int j)
        throws IOException
    {
        output.writeInt32(i, j);
    }

    public final void writeInt32List(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                j += CodedOutputStream.computeInt32SizeNoTag(((Integer)list.get(i)).intValue());
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeInt32NoTag(((Integer)list.get(i)).intValue());
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeInt32(i, ((Integer)list.get(j)).intValue());
            }

        }
    }

    public final void writeInt64(int i, long l)
        throws IOException
    {
        output.writeUInt64(i, l);
    }

    public final void writeInt64List(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                j += CodedOutputStream.computeInt64SizeNoTag(((Long)list.get(i)).longValue());
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeUInt64NoTag(((Long)list.get(i)).longValue());
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeUInt64(i, ((Long)list.get(j)).longValue());
            }

        }
    }

    public final void writeMap(int i, MapEntryLite.Metadata metadata, Map map)
        throws IOException
    {
        CodedOutputStream codedoutputstream = output;
        Object obj;
        CodedOutputStream codedoutputstream1;
        for (map = map.entrySet().iterator(); map.hasNext(); FieldSet.writeElement(codedoutputstream1, metadata.valueType, 2, obj))
        {
            obj = (java.util.Map.Entry)map.next();
            output.writeTag(i, 2);
            codedoutputstream1 = output;
            Object obj1 = ((java.util.Map.Entry) (obj)).getKey();
            Object obj2 = ((java.util.Map.Entry) (obj)).getValue();
            codedoutputstream1.writeUInt32NoTag(FieldSet.computeElementSize(metadata.keyType, 1, obj1) + FieldSet.computeElementSize(metadata.valueType, 2, obj2));
            codedoutputstream1 = output;
            obj1 = ((java.util.Map.Entry) (obj)).getKey();
            obj = ((java.util.Map.Entry) (obj)).getValue();
            FieldSet.writeElement(codedoutputstream1, metadata.keyType, 1, obj1);
        }

    }

    public final void writeMessage(int i, Object obj, Schema schema)
        throws IOException
    {
        output.writeMessage(i, (MessageLite)obj, schema);
    }

    public final void writeMessageList(int i, List list, Schema schema)
        throws IOException
    {
        for (int j = 0; j < list.size(); j++)
        {
            Object obj = list.get(j);
            output.writeMessage(i, (MessageLite)obj, schema);
        }

    }

    public final void writeMessageSetItem(int i, Object obj)
        throws IOException
    {
        if (obj instanceof ByteString)
        {
            output.writeRawMessageSetExtension(i, (ByteString)obj);
            return;
        } else
        {
            output.writeMessageSetExtension(i, (MessageLite)obj);
            return;
        }
    }

    public final void writeSFixed32(int i, int j)
        throws IOException
    {
        output.writeFixed32(i, j);
    }

    public final void writeSFixed32List(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                ((Integer)list.get(i)).intValue();
                j += CodedOutputStream.computeSFixed32SizeNoTag$514III8_0();
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeFixed32NoTag(((Integer)list.get(i)).intValue());
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeFixed32(i, ((Integer)list.get(j)).intValue());
            }

        }
    }

    public final void writeSFixed64(int i, long l)
        throws IOException
    {
        output.writeFixed64(i, l);
    }

    public final void writeSFixed64List(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                ((Long)list.get(i)).longValue();
                j += CodedOutputStream.computeSFixed64SizeNoTag$5152II8_0();
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeFixed64NoTag(((Long)list.get(i)).longValue());
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeFixed64(i, ((Long)list.get(j)).longValue());
            }

        }
    }

    public final void writeSInt32(int i, int j)
        throws IOException
    {
        output.writeUInt32(i, j << 1 ^ j >> 31);
    }

    public final void writeSInt32List(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                j += CodedOutputStream.computeSInt32SizeNoTag(((Integer)list.get(i)).intValue());
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                CodedOutputStream codedoutputstream = output;
                j = ((Integer)list.get(i)).intValue();
                codedoutputstream.writeUInt32NoTag(j >> 31 ^ j << 1);
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                CodedOutputStream codedoutputstream1 = output;
                int k = ((Integer)list.get(j)).intValue();
                codedoutputstream1.writeUInt32(i, k >> 31 ^ k << 1);
            }

        }
    }

    public final void writeSInt64(int i, long l)
        throws IOException
    {
        output.writeUInt64(i, l << 1 ^ l >> 63);
    }

    public final void writeSInt64List(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                j += CodedOutputStream.computeSInt64SizeNoTag(((Long)list.get(i)).longValue());
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                CodedOutputStream codedoutputstream = output;
                long l = ((Long)list.get(i)).longValue();
                codedoutputstream.writeUInt64NoTag(l >> 63 ^ l << 1);
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                CodedOutputStream codedoutputstream1 = output;
                long l1 = ((Long)list.get(j)).longValue();
                codedoutputstream1.writeUInt64(i, l1 >> 63 ^ l1 << 1);
            }

        }
    }

    public final void writeStartGroup(int i)
        throws IOException
    {
        output.writeTag(i, 3);
    }

    public final void writeString(int i, String s)
        throws IOException
    {
        output.writeString(i, s);
    }

    public final void writeStringList(int i, List list)
        throws IOException
    {
        int j = 0;
        if (list instanceof LazyStringList)
        {
            LazyStringList lazystringlist = (LazyStringList)list;
            j = 0;
            while (j < list.size()) 
            {
                Object obj = lazystringlist.getRaw(j);
                if (obj instanceof String)
                {
                    output.writeString(i, (String)obj);
                } else
                {
                    output.writeBytes(i, (ByteString)obj);
                }
                j++;
            }
        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeString(i, (String)list.get(j));
            }

        }
    }

    public final void writeUInt32(int i, int j)
        throws IOException
    {
        output.writeUInt32(i, j);
    }

    public final void writeUInt32List(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                j += CodedOutputStream.computeUInt32SizeNoTag(((Integer)list.get(i)).intValue());
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeUInt32NoTag(((Integer)list.get(i)).intValue());
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeUInt32(i, ((Integer)list.get(j)).intValue());
            }

        }
    }

    public final void writeUInt64(int i, long l)
        throws IOException
    {
        output.writeUInt64(i, l);
    }

    public final void writeUInt64List(int i, List list, boolean flag)
        throws IOException
    {
        int j = 0;
        boolean flag1 = false;
        if (flag)
        {
            output.writeTag(i, 2);
            i = 0;
            j = 0;
            for (; i < list.size(); i++)
            {
                j += CodedOutputStream.computeUInt64SizeNoTag(((Long)list.get(i)).longValue());
            }

            output.writeUInt32NoTag(j);
            for (i = ((flag1) ? 1 : 0); i < list.size(); i++)
            {
                output.writeUInt64NoTag(((Long)list.get(i)).longValue());
            }

        } else
        {
            for (; j < list.size(); j++)
            {
                output.writeUInt64(i, ((Long)list.get(j)).longValue());
            }

        }
    }
}
