// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            UnknownFieldSetLiteSchema, CodedOutputStream, ByteString, IntArrayList, 
//            MessageLite, LongArrayList, LazyFieldLite, LazyStringList, 
//            UnknownFieldSchema, ExtensionSchema, FieldSet, SmallSortedMap, 
//            UnsafeUtil, MapFieldSchema, GeneratedMessageLite, Writer, 
//            Schema

final class SchemaUtil
{

    private static final Class GENERATED_MESSAGE_CLASS = getGeneratedMessageClass();
    public static final UnknownFieldSchema PROTO2_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(false);
    public static final UnknownFieldSchema PROTO3_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(true);
    public static final UnknownFieldSchema UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();

    static int computeSizeBoolList(int i, List list, boolean flag)
    {
        int j = list.size();
        if (j == 0)
        {
            return 0;
        } else
        {
            return j * CodedOutputStream.computeBoolSize(i, true);
        }
    }

    static int computeSizeBoolListNoTag(List list)
    {
        return list.size();
    }

    static int computeSizeByteStringList(int i, List list)
    {
        int j = list.size();
        if (j == 0)
        {
            return 0;
        }
        j *= CodedOutputStream.computeTagSize(i);
        for (i = 0; i < list.size(); i++)
        {
            j += CodedOutputStream.computeBytesSizeNoTag((ByteString)list.get(i));
        }

        return j;
    }

    static int computeSizeEnumList(int i, List list, boolean flag)
    {
        int j = list.size();
        if (j == 0)
        {
            return 0;
        } else
        {
            int k = computeSizeEnumListNoTag(list);
            return j * CodedOutputStream.computeTagSize(i) + k;
        }
    }

    static int computeSizeEnumListNoTag(List list)
    {
        int i;
        int i1;
        int j1;
        i = 0;
        i1 = 0;
        j1 = list.size();
        if (j1 != 0) goto _L2; else goto _L1
_L1:
        return i1;
_L2:
label0:
        {
            if (!(list instanceof IntArrayList))
            {
                break label0;
            }
            list = (IntArrayList)list;
            int k = 0;
            do
            {
                i1 = i;
                if (k >= j1)
                {
                    break;
                }
                i1 = CodedOutputStream.computeEnumSizeNoTag(list.getInt(k));
                k++;
                i = i1 + i;
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        int j = 0;
        int l = 0;
        for (; j < j1; j++)
        {
            l += CodedOutputStream.computeEnumSizeNoTag(((Integer)list.get(j)).intValue());
        }

        return l;
    }

    static int computeSizeFixed32List(int i, List list, boolean flag)
    {
        int j = list.size();
        if (j == 0)
        {
            return 0;
        } else
        {
            return CodedOutputStream.computeFixed32Size(i, 0) * j;
        }
    }

    static int computeSizeFixed32ListNoTag(List list)
    {
        return list.size() << 2;
    }

    static int computeSizeFixed64List(int i, List list, boolean flag)
    {
        int j = list.size();
        if (j == 0)
        {
            return 0;
        } else
        {
            return j * CodedOutputStream.computeFixed64Size(i, 0L);
        }
    }

    static int computeSizeFixed64ListNoTag(List list)
    {
        return list.size() << 3;
    }

    static int computeSizeGroupList(int i, List list, Schema schema)
    {
        int l = list.size();
        if (l == 0)
        {
            return 0;
        }
        int j = 0;
        int k = 0;
        for (; j < l; j++)
        {
            k += CodedOutputStream.computeGroupSize(i, (MessageLite)list.get(j), schema);
        }

        return k;
    }

    static int computeSizeInt32List(int i, List list, boolean flag)
    {
        int j = list.size();
        if (j == 0)
        {
            return 0;
        } else
        {
            int k = computeSizeInt32ListNoTag(list);
            return j * CodedOutputStream.computeTagSize(i) + k;
        }
    }

    static int computeSizeInt32ListNoTag(List list)
    {
        int i;
        int i1;
        int j1;
        i = 0;
        i1 = 0;
        j1 = list.size();
        if (j1 != 0) goto _L2; else goto _L1
_L1:
        return i1;
_L2:
label0:
        {
            if (!(list instanceof IntArrayList))
            {
                break label0;
            }
            list = (IntArrayList)list;
            int k = 0;
            do
            {
                i1 = i;
                if (k >= j1)
                {
                    break;
                }
                i1 = CodedOutputStream.computeInt32SizeNoTag(list.getInt(k));
                k++;
                i = i1 + i;
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        int j = 0;
        int l = 0;
        for (; j < j1; j++)
        {
            l += CodedOutputStream.computeInt32SizeNoTag(((Integer)list.get(j)).intValue());
        }

        return l;
    }

    static int computeSizeInt64List(int i, List list, boolean flag)
    {
        if (list.size() == 0)
        {
            return 0;
        } else
        {
            return computeSizeInt64ListNoTag(list) + list.size() * CodedOutputStream.computeTagSize(i);
        }
    }

    static int computeSizeInt64ListNoTag(List list)
    {
        int i;
        int i1;
        int j1;
        i = 0;
        i1 = 0;
        j1 = list.size();
        if (j1 != 0) goto _L2; else goto _L1
_L1:
        return i1;
_L2:
label0:
        {
            if (!(list instanceof LongArrayList))
            {
                break label0;
            }
            list = (LongArrayList)list;
            int k = 0;
            do
            {
                i1 = i;
                if (k >= j1)
                {
                    break;
                }
                i1 = CodedOutputStream.computeInt64SizeNoTag(list.getLong(k));
                k++;
                i = i1 + i;
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        int j = 0;
        int l = 0;
        for (; j < j1; j++)
        {
            l += CodedOutputStream.computeInt64SizeNoTag(((Long)list.get(j)).longValue());
        }

        return l;
    }

    static int computeSizeMessage(int i, Object obj, Schema schema)
    {
        if (obj instanceof LazyFieldLite)
        {
            return CodedOutputStream.computeLazyFieldSize(i, (LazyFieldLite)obj);
        } else
        {
            return CodedOutputStream.computeMessageSize(i, (MessageLite)obj, schema);
        }
    }

    static int computeSizeMessageList(int i, List list, Schema schema)
    {
        int k = list.size();
        if (k == 0)
        {
            return 0;
        }
        i = CodedOutputStream.computeTagSize(i) * k;
        int j = 0;
        while (j < k) 
        {
            Object obj = list.get(j);
            if (obj instanceof LazyFieldLite)
            {
                i = CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite)obj) + i;
            } else
            {
                i = CodedOutputStream.computeMessageSizeNoTag((MessageLite)obj, schema) + i;
            }
            j++;
        }
        return i;
    }

    static int computeSizeSInt32List(int i, List list, boolean flag)
    {
        int j = list.size();
        if (j == 0)
        {
            return 0;
        } else
        {
            int k = computeSizeSInt32ListNoTag(list);
            return j * CodedOutputStream.computeTagSize(i) + k;
        }
    }

    static int computeSizeSInt32ListNoTag(List list)
    {
        int i;
        int i1;
        int j1;
        i = 0;
        i1 = 0;
        j1 = list.size();
        if (j1 != 0) goto _L2; else goto _L1
_L1:
        return i1;
_L2:
label0:
        {
            if (!(list instanceof IntArrayList))
            {
                break label0;
            }
            list = (IntArrayList)list;
            int k = 0;
            do
            {
                i1 = i;
                if (k >= j1)
                {
                    break;
                }
                i1 = CodedOutputStream.computeSInt32SizeNoTag(list.getInt(k));
                k++;
                i = i1 + i;
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        int j = 0;
        int l = 0;
        for (; j < j1; j++)
        {
            l += CodedOutputStream.computeSInt32SizeNoTag(((Integer)list.get(j)).intValue());
        }

        return l;
    }

    static int computeSizeSInt64List(int i, List list, boolean flag)
    {
        int j = list.size();
        if (j == 0)
        {
            return 0;
        } else
        {
            int k = computeSizeSInt64ListNoTag(list);
            return j * CodedOutputStream.computeTagSize(i) + k;
        }
    }

    static int computeSizeSInt64ListNoTag(List list)
    {
        int i;
        int i1;
        int j1;
        i = 0;
        i1 = 0;
        j1 = list.size();
        if (j1 != 0) goto _L2; else goto _L1
_L1:
        return i1;
_L2:
label0:
        {
            if (!(list instanceof LongArrayList))
            {
                break label0;
            }
            list = (LongArrayList)list;
            int k = 0;
            do
            {
                i1 = i;
                if (k >= j1)
                {
                    break;
                }
                i1 = CodedOutputStream.computeSInt64SizeNoTag(list.getLong(k));
                k++;
                i = i1 + i;
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        int j = 0;
        int l = 0;
        for (; j < j1; j++)
        {
            l += CodedOutputStream.computeSInt64SizeNoTag(((Long)list.get(j)).longValue());
        }

        return l;
    }

    static int computeSizeStringList(int i, List list)
    {
        int l = list.size();
        if (l == 0)
        {
            return 0;
        }
        i = CodedOutputStream.computeTagSize(i) * l;
        if (list instanceof LazyStringList)
        {
            list = (LazyStringList)list;
            int j = 0;
            while (j < l) 
            {
                Object obj = list.getRaw(j);
                if (obj instanceof ByteString)
                {
                    i = CodedOutputStream.computeBytesSizeNoTag((ByteString)obj) + i;
                } else
                {
                    i = CodedOutputStream.computeStringSizeNoTag((String)obj) + i;
                }
                j++;
            }
            return i;
        }
        int k = 0;
        while (k < l) 
        {
            Object obj1 = list.get(k);
            if (obj1 instanceof ByteString)
            {
                i = CodedOutputStream.computeBytesSizeNoTag((ByteString)obj1) + i;
            } else
            {
                i = CodedOutputStream.computeStringSizeNoTag((String)obj1) + i;
            }
            k++;
        }
        return i;
    }

    static int computeSizeUInt32List(int i, List list, boolean flag)
    {
        int j = list.size();
        if (j == 0)
        {
            return 0;
        } else
        {
            int k = computeSizeUInt32ListNoTag(list);
            return j * CodedOutputStream.computeTagSize(i) + k;
        }
    }

    static int computeSizeUInt32ListNoTag(List list)
    {
        int i;
        int i1;
        int j1;
        i = 0;
        i1 = 0;
        j1 = list.size();
        if (j1 != 0) goto _L2; else goto _L1
_L1:
        return i1;
_L2:
label0:
        {
            if (!(list instanceof IntArrayList))
            {
                break label0;
            }
            list = (IntArrayList)list;
            int k = 0;
            do
            {
                i1 = i;
                if (k >= j1)
                {
                    break;
                }
                i1 = CodedOutputStream.computeUInt32SizeNoTag(list.getInt(k));
                k++;
                i = i1 + i;
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        int j = 0;
        int l = 0;
        for (; j < j1; j++)
        {
            l += CodedOutputStream.computeUInt32SizeNoTag(((Integer)list.get(j)).intValue());
        }

        return l;
    }

    static int computeSizeUInt64List(int i, List list, boolean flag)
    {
        int j = list.size();
        if (j == 0)
        {
            return 0;
        } else
        {
            int k = computeSizeUInt64ListNoTag(list);
            return j * CodedOutputStream.computeTagSize(i) + k;
        }
    }

    static int computeSizeUInt64ListNoTag(List list)
    {
        int i;
        int i1;
        int j1;
        i = 0;
        i1 = 0;
        j1 = list.size();
        if (j1 != 0) goto _L2; else goto _L1
_L1:
        return i1;
_L2:
label0:
        {
            if (!(list instanceof LongArrayList))
            {
                break label0;
            }
            list = (LongArrayList)list;
            int k = 0;
            do
            {
                i1 = i;
                if (k >= j1)
                {
                    break;
                }
                i1 = CodedOutputStream.computeUInt64SizeNoTag(list.getLong(k));
                k++;
                i = i1 + i;
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        int j = 0;
        int l = 0;
        for (; j < j1; j++)
        {
            l += CodedOutputStream.computeUInt64SizeNoTag(((Long)list.get(j)).longValue());
        }

        return l;
    }

    static Object filterUnknownEnumList(int i, List list, Internal.EnumLiteMap enumlitemap, Object obj, UnknownFieldSchema unknownfieldschema)
    {
        if (enumlitemap == null)
        {
            return obj;
        }
        int l = list.size();
        int k = 0;
        int j = 0;
        while (k < l) 
        {
            int i1 = ((Integer)list.get(k)).intValue();
            if (enumlitemap.findValueByNumber(i1) != null)
            {
                if (k != j)
                {
                    list.set(j, Integer.valueOf(i1));
                }
                j++;
            } else
            {
                if (obj == null)
                {
                    obj = unknownfieldschema.newBuilder();
                }
                unknownfieldschema.addVarint(obj, i, i1);
            }
            k++;
        }
        if (j != l)
        {
            list.subList(j, l).clear();
        }
        return obj;
    }

    static Object filterUnknownEnumList(int i, List list, Internal.EnumVerifier enumverifier, Object obj, UnknownFieldSchema unknownfieldschema)
    {
        if (enumverifier == null)
        {
            return obj;
        }
        if (list instanceof RandomAccess)
        {
            int i1 = list.size();
            int l = 0;
            int j = 0;
            while (l < i1) 
            {
                int j1 = ((Integer)list.get(l)).intValue();
                if (enumverifier.isInRange(j1))
                {
                    if (l != j)
                    {
                        list.set(j, Integer.valueOf(j1));
                    }
                    j++;
                } else
                {
                    if (obj == null)
                    {
                        obj = unknownfieldschema.newBuilder();
                    }
                    unknownfieldschema.addVarint(obj, i, j1);
                }
                l++;
            }
            enumverifier = ((Internal.EnumVerifier) (obj));
            if (j != i1)
            {
                list.subList(j, i1).clear();
                enumverifier = ((Internal.EnumVerifier) (obj));
            }
        } else
        {
            Iterator iterator = list.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                int k = ((Integer)iterator.next()).intValue();
                if (!enumverifier.isInRange(k))
                {
                    list = ((List) (obj));
                    if (obj == null)
                    {
                        list = ((List) (unknownfieldschema.newBuilder()));
                    }
                    unknownfieldschema.addVarint(list, i, k);
                    iterator.remove();
                    obj = list;
                }
            } while (true);
            enumverifier = ((Internal.EnumVerifier) (obj));
        }
        return enumverifier;
    }

    private static Class getGeneratedMessageClass()
    {
        Class class1;
        try
        {
            class1 = Class.forName("com.google.protobuf.GeneratedMessage");
        }
        catch (Throwable throwable)
        {
            return null;
        }
        return class1;
    }

    private static UnknownFieldSchema getUnknownFieldSetSchema(boolean flag)
    {
        Object obj;
        try
        {
            obj = getUnknownFieldSetSchemaClass();
        }
        catch (Throwable throwable)
        {
            return null;
        }
        if (obj == null)
        {
            return null;
        }
        obj = (UnknownFieldSchema)((Class) (obj)).getConstructor(new Class[] {
            Boolean.TYPE
        }).newInstance(new Object[] {
            Boolean.valueOf(flag)
        });
        return ((UnknownFieldSchema) (obj));
    }

    private static Class getUnknownFieldSetSchemaClass()
    {
        Class class1;
        try
        {
            class1 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        }
        catch (Throwable throwable)
        {
            return null;
        }
        return class1;
    }

    static void mergeExtensions(ExtensionSchema extensionschema, Object obj, Object obj1)
    {
        obj1 = extensionschema.getExtensions(obj1);
        if (!((FieldSet) (obj1)).fields.isEmpty())
        {
            obj = extensionschema.getMutableExtensions(obj);
            for (int i = 0; i < ((FieldSet) (obj1)).fields.entryList.size(); i++)
            {
                ((FieldSet) (obj)).mergeFromField((java.util.Map.Entry)((FieldSet) (obj1)).fields.entryList.get(i));
            }

            extensionschema = ((FieldSet) (obj1)).fields;
            if (((SmallSortedMap) (extensionschema)).overflowEntries.isEmpty())
            {
                extensionschema = SmallSortedMap.EmptySet.ITERABLE;
            } else
            {
                extensionschema = ((SmallSortedMap) (extensionschema)).overflowEntries.entrySet();
            }
            for (extensionschema = extensionschema.iterator(); extensionschema.hasNext(); ((FieldSet) (obj)).mergeFromField((java.util.Map.Entry)extensionschema.next())) { }
        }
    }

    static void mergeMap(MapFieldSchema mapfieldschema, Object obj, Object obj1, long l)
    {
        mapfieldschema = ((MapFieldSchema) (mapfieldschema.mergeFrom(UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj, l), UnsafeUtil.MEMORY_ACCESSOR.unsafe.getObject(obj1, l))));
        UnsafeUtil.MEMORY_ACCESSOR.unsafe.putObject(obj, l, mapfieldschema);
    }

    static void mergeUnknownFields(UnknownFieldSchema unknownfieldschema, Object obj, Object obj1)
    {
        unknownfieldschema.setToMessage(obj, unknownfieldschema.merge(unknownfieldschema.getFromMessage(obj), unknownfieldschema.getFromMessage(obj1)));
    }

    public static void requireGeneratedMessage(Class class1)
    {
        if (!com/google/protobuf/GeneratedMessageLite.isAssignableFrom(class1) && GENERATED_MESSAGE_CLASS != null && !GENERATED_MESSAGE_CLASS.isAssignableFrom(class1))
        {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        } else
        {
            return;
        }
    }

    static boolean safeEquals(Object obj, Object obj1)
    {
        return obj == obj1 || obj != null && obj.equals(obj1);
    }

    static Object storeUnknownEnum(int i, int j, Object obj, UnknownFieldSchema unknownfieldschema)
    {
        Object obj1 = obj;
        if (obj == null)
        {
            obj1 = unknownfieldschema.newBuilder();
        }
        unknownfieldschema.addVarint(obj1, i, j);
        return obj1;
    }

    public static void writeBoolList(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeBoolList(i, list, flag);
        }
    }

    public static void writeBytesList(int i, List list, Writer writer)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeBytesList(i, list);
        }
    }

    public static void writeDoubleList(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeDoubleList(i, list, flag);
        }
    }

    public static void writeEnumList(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeEnumList(i, list, flag);
        }
    }

    public static void writeFixed32List(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeFixed32List(i, list, flag);
        }
    }

    public static void writeFixed64List(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeFixed64List(i, list, flag);
        }
    }

    public static void writeFloatList(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeFloatList(i, list, flag);
        }
    }

    public static void writeGroupList(int i, List list, Writer writer, Schema schema)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeGroupList(i, list, schema);
        }
    }

    public static void writeInt32List(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeInt32List(i, list, flag);
        }
    }

    public static void writeInt64List(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeInt64List(i, list, flag);
        }
    }

    public static void writeMessageList(int i, List list, Writer writer, Schema schema)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeMessageList(i, list, schema);
        }
    }

    public static void writeSFixed32List(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeSFixed32List(i, list, flag);
        }
    }

    public static void writeSFixed64List(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeSFixed64List(i, list, flag);
        }
    }

    public static void writeSInt32List(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeSInt32List(i, list, flag);
        }
    }

    public static void writeSInt64List(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeSInt64List(i, list, flag);
        }
    }

    public static void writeStringList(int i, List list, Writer writer)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeStringList(i, list);
        }
    }

    public static void writeUInt32List(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeUInt32List(i, list, flag);
        }
    }

    public static void writeUInt64List(int i, List list, Writer writer, boolean flag)
        throws IOException
    {
        if (list != null && !list.isEmpty())
        {
            writer.writeUInt64List(i, list, flag);
        }
    }

}
