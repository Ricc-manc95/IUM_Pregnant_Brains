// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            JavaType

public final class FieldType extends Enum
{

    private static final FieldType $VALUES[];
    private static final FieldType BOOL;
    private static final FieldType BOOL_LIST;
    private static final FieldType BOOL_LIST_PACKED;
    private static final FieldType BYTES;
    private static final FieldType BYTES_LIST;
    private static final FieldType DOUBLE;
    private static final FieldType DOUBLE_LIST;
    public static final FieldType DOUBLE_LIST_PACKED;
    public static final FieldType ENUM;
    public static final FieldType ENUM_LIST;
    public static final FieldType ENUM_LIST_PACKED;
    private static final FieldType FIXED32;
    private static final FieldType FIXED32_LIST;
    private static final FieldType FIXED32_LIST_PACKED;
    private static final FieldType FIXED64;
    private static final FieldType FIXED64_LIST;
    private static final FieldType FIXED64_LIST_PACKED;
    private static final FieldType FLOAT;
    private static final FieldType FLOAT_LIST;
    private static final FieldType FLOAT_LIST_PACKED;
    public static final FieldType GROUP;
    public static final FieldType GROUP_LIST;
    private static final FieldType INT32;
    private static final FieldType INT32_LIST;
    private static final FieldType INT32_LIST_PACKED;
    private static final FieldType INT64;
    private static final FieldType INT64_LIST;
    private static final FieldType INT64_LIST_PACKED;
    public static final FieldType MAP;
    public static final FieldType MESSAGE;
    public static final FieldType MESSAGE_LIST;
    private static final FieldType SFIXED32;
    private static final FieldType SFIXED32_LIST;
    private static final FieldType SFIXED32_LIST_PACKED;
    private static final FieldType SFIXED64;
    private static final FieldType SFIXED64_LIST;
    private static final FieldType SFIXED64_LIST_PACKED;
    private static final FieldType SINT32;
    private static final FieldType SINT32_LIST;
    private static final FieldType SINT32_LIST_PACKED;
    private static final FieldType SINT64;
    private static final FieldType SINT64_LIST;
    public static final FieldType SINT64_LIST_PACKED;
    private static final FieldType STRING;
    private static final FieldType STRING_LIST;
    private static final FieldType UINT32;
    private static final FieldType UINT32_LIST;
    private static final FieldType UINT32_LIST_PACKED;
    private static final FieldType UINT64;
    private static final FieldType UINT64_LIST;
    private static final FieldType UINT64_LIST_PACKED;
    private static final FieldType VALUES[];
    public final Collection collection;
    public final int id;

    private FieldType(String s, int i, int j, Collection collection1, JavaType javatype)
    {
        super(s, i);
        id = j;
        collection = collection1;
        collection1.ordinal();
        JVM INSTR tableswitch 1 3: default 48
    //                   1 72
    //                   2 48
    //                   3 63;
           goto _L1 _L2 _L1 _L3
_L1:
        if (collection1 == Collection.SCALAR)
        {
            javatype.ordinal();
        }
        return;
_L3:
        s = javatype.boxedType;
        continue; /* Loop/switch isn't completed */
_L2:
        s = javatype.boxedType;
        if (true) goto _L1; else goto _L4
_L4:
    }

    public static FieldType[] values()
    {
        return (FieldType[])$VALUES.clone();
    }

    static 
    {
        int i = 0;
        DOUBLE = new FieldType("DOUBLE", 0, 0, Collection.SCALAR, JavaType.DOUBLE);
        FLOAT = new FieldType("FLOAT", 1, 1, Collection.SCALAR, JavaType.FLOAT);
        INT64 = new FieldType("INT64", 2, 2, Collection.SCALAR, JavaType.LONG);
        UINT64 = new FieldType("UINT64", 3, 3, Collection.SCALAR, JavaType.LONG);
        INT32 = new FieldType("INT32", 4, 4, Collection.SCALAR, JavaType.INT);
        FIXED64 = new FieldType("FIXED64", 5, 5, Collection.SCALAR, JavaType.LONG);
        FIXED32 = new FieldType("FIXED32", 6, 6, Collection.SCALAR, JavaType.INT);
        BOOL = new FieldType("BOOL", 7, 7, Collection.SCALAR, JavaType.BOOLEAN);
        STRING = new FieldType("STRING", 8, 8, Collection.SCALAR, JavaType.STRING);
        MESSAGE = new FieldType("MESSAGE", 9, 9, Collection.SCALAR, JavaType.MESSAGE);
        BYTES = new FieldType("BYTES", 10, 10, Collection.SCALAR, JavaType.BYTE_STRING);
        UINT32 = new FieldType("UINT32", 11, 11, Collection.SCALAR, JavaType.INT);
        ENUM = new FieldType("ENUM", 12, 12, Collection.SCALAR, JavaType.ENUM);
        SFIXED32 = new FieldType("SFIXED32", 13, 13, Collection.SCALAR, JavaType.INT);
        SFIXED64 = new FieldType("SFIXED64", 14, 14, Collection.SCALAR, JavaType.LONG);
        SINT32 = new FieldType("SINT32", 15, 15, Collection.SCALAR, JavaType.INT);
        SINT64 = new FieldType("SINT64", 16, 16, Collection.SCALAR, JavaType.LONG);
        GROUP = new FieldType("GROUP", 17, 17, Collection.SCALAR, JavaType.MESSAGE);
        DOUBLE_LIST = new FieldType("DOUBLE_LIST", 18, 18, Collection.VECTOR, JavaType.DOUBLE);
        FLOAT_LIST = new FieldType("FLOAT_LIST", 19, 19, Collection.VECTOR, JavaType.FLOAT);
        INT64_LIST = new FieldType("INT64_LIST", 20, 20, Collection.VECTOR, JavaType.LONG);
        UINT64_LIST = new FieldType("UINT64_LIST", 21, 21, Collection.VECTOR, JavaType.LONG);
        INT32_LIST = new FieldType("INT32_LIST", 22, 22, Collection.VECTOR, JavaType.INT);
        FIXED64_LIST = new FieldType("FIXED64_LIST", 23, 23, Collection.VECTOR, JavaType.LONG);
        FIXED32_LIST = new FieldType("FIXED32_LIST", 24, 24, Collection.VECTOR, JavaType.INT);
        BOOL_LIST = new FieldType("BOOL_LIST", 25, 25, Collection.VECTOR, JavaType.BOOLEAN);
        STRING_LIST = new FieldType("STRING_LIST", 26, 26, Collection.VECTOR, JavaType.STRING);
        MESSAGE_LIST = new FieldType("MESSAGE_LIST", 27, 27, Collection.VECTOR, JavaType.MESSAGE);
        BYTES_LIST = new FieldType("BYTES_LIST", 28, 28, Collection.VECTOR, JavaType.BYTE_STRING);
        UINT32_LIST = new FieldType("UINT32_LIST", 29, 29, Collection.VECTOR, JavaType.INT);
        ENUM_LIST = new FieldType("ENUM_LIST", 30, 30, Collection.VECTOR, JavaType.ENUM);
        SFIXED32_LIST = new FieldType("SFIXED32_LIST", 31, 31, Collection.VECTOR, JavaType.INT);
        SFIXED64_LIST = new FieldType("SFIXED64_LIST", 32, 32, Collection.VECTOR, JavaType.LONG);
        SINT32_LIST = new FieldType("SINT32_LIST", 33, 33, Collection.VECTOR, JavaType.INT);
        SINT64_LIST = new FieldType("SINT64_LIST", 34, 34, Collection.VECTOR, JavaType.LONG);
        DOUBLE_LIST_PACKED = new FieldType("DOUBLE_LIST_PACKED", 35, 35, Collection.PACKED_VECTOR, JavaType.DOUBLE);
        FLOAT_LIST_PACKED = new FieldType("FLOAT_LIST_PACKED", 36, 36, Collection.PACKED_VECTOR, JavaType.FLOAT);
        INT64_LIST_PACKED = new FieldType("INT64_LIST_PACKED", 37, 37, Collection.PACKED_VECTOR, JavaType.LONG);
        UINT64_LIST_PACKED = new FieldType("UINT64_LIST_PACKED", 38, 38, Collection.PACKED_VECTOR, JavaType.LONG);
        INT32_LIST_PACKED = new FieldType("INT32_LIST_PACKED", 39, 39, Collection.PACKED_VECTOR, JavaType.INT);
        FIXED64_LIST_PACKED = new FieldType("FIXED64_LIST_PACKED", 40, 40, Collection.PACKED_VECTOR, JavaType.LONG);
        FIXED32_LIST_PACKED = new FieldType("FIXED32_LIST_PACKED", 41, 41, Collection.PACKED_VECTOR, JavaType.INT);
        BOOL_LIST_PACKED = new FieldType("BOOL_LIST_PACKED", 42, 42, Collection.PACKED_VECTOR, JavaType.BOOLEAN);
        UINT32_LIST_PACKED = new FieldType("UINT32_LIST_PACKED", 43, 43, Collection.PACKED_VECTOR, JavaType.INT);
        ENUM_LIST_PACKED = new FieldType("ENUM_LIST_PACKED", 44, 44, Collection.PACKED_VECTOR, JavaType.ENUM);
        SFIXED32_LIST_PACKED = new FieldType("SFIXED32_LIST_PACKED", 45, 45, Collection.PACKED_VECTOR, JavaType.INT);
        SFIXED64_LIST_PACKED = new FieldType("SFIXED64_LIST_PACKED", 46, 46, Collection.PACKED_VECTOR, JavaType.LONG);
        SINT32_LIST_PACKED = new FieldType("SINT32_LIST_PACKED", 47, 47, Collection.PACKED_VECTOR, JavaType.INT);
        SINT64_LIST_PACKED = new FieldType("SINT64_LIST_PACKED", 48, 48, Collection.PACKED_VECTOR, JavaType.LONG);
        GROUP_LIST = new FieldType("GROUP_LIST", 49, 49, Collection.VECTOR, JavaType.MESSAGE);
        MAP = new FieldType("MAP", 50, 50, Collection.MAP, JavaType.VOID);
        $VALUES = (new FieldType[] {
            DOUBLE, FLOAT, INT64, UINT64, INT32, FIXED64, FIXED32, BOOL, STRING, MESSAGE, 
            BYTES, UINT32, ENUM, SFIXED32, SFIXED64, SINT32, SINT64, GROUP, DOUBLE_LIST, FLOAT_LIST, 
            INT64_LIST, UINT64_LIST, INT32_LIST, FIXED64_LIST, FIXED32_LIST, BOOL_LIST, STRING_LIST, MESSAGE_LIST, BYTES_LIST, UINT32_LIST, 
            ENUM_LIST, SFIXED32_LIST, SFIXED64_LIST, SINT32_LIST, SINT64_LIST, DOUBLE_LIST_PACKED, FLOAT_LIST_PACKED, INT64_LIST_PACKED, UINT64_LIST_PACKED, INT32_LIST_PACKED, 
            FIXED64_LIST_PACKED, FIXED32_LIST_PACKED, BOOL_LIST_PACKED, UINT32_LIST_PACKED, ENUM_LIST_PACKED, SFIXED32_LIST_PACKED, SFIXED64_LIST_PACKED, SINT32_LIST_PACKED, SINT64_LIST_PACKED, GROUP_LIST, 
            MAP
        });
        FieldType afieldtype[] = values();
        VALUES = new FieldType[afieldtype.length];
        for (int j = afieldtype.length; i < j; i++)
        {
            FieldType fieldtype = afieldtype[i];
            VALUES[fieldtype.id] = fieldtype;
        }

    }

    private class Collection extends Enum
    {

        private static final Collection $VALUES[];
        public static final Collection MAP;
        public static final Collection PACKED_VECTOR;
        public static final Collection SCALAR;
        public static final Collection VECTOR;
        public final boolean isList;

        public static Collection[] values()
        {
            return (Collection[])$VALUES.clone();
        }

        static 
        {
            SCALAR = new Collection("SCALAR", 0, false);
            VECTOR = new Collection("VECTOR", 1, true);
            PACKED_VECTOR = new Collection("PACKED_VECTOR", 2, true);
            MAP = new Collection("MAP", 3, false);
            $VALUES = (new Collection[] {
                SCALAR, VECTOR, PACKED_VECTOR, MAP
            });
        }

        private Collection(String s, int i, boolean flag)
        {
            super(s, i);
            isList = flag;
        }
    }

}
