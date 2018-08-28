// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            ByteString

public final class JavaType extends Enum
{

    private static final JavaType $VALUES[];
    public static final JavaType BOOLEAN;
    public static final JavaType BYTE_STRING;
    public static final JavaType DOUBLE;
    public static final JavaType ENUM;
    public static final JavaType FLOAT;
    public static final JavaType INT;
    public static final JavaType LONG;
    public static final JavaType MESSAGE;
    public static final JavaType STRING;
    public static final JavaType VOID;
    public final Class boxedType;

    private JavaType(String s, int i, Class class1, Class class2, Object obj)
    {
        super(s, i);
        boxedType = class2;
    }

    public static JavaType[] values()
    {
        return (JavaType[])$VALUES.clone();
    }

    static 
    {
        VOID = new JavaType("VOID", 0, java/lang/Void, java/lang/Void, null);
        INT = new JavaType("INT", 1, Integer.TYPE, java/lang/Integer, Integer.valueOf(0));
        LONG = new JavaType("LONG", 2, Long.TYPE, java/lang/Long, Long.valueOf(0L));
        FLOAT = new JavaType("FLOAT", 3, Float.TYPE, java/lang/Float, Float.valueOf(0.0F));
        DOUBLE = new JavaType("DOUBLE", 4, Double.TYPE, java/lang/Double, Double.valueOf(0.0D));
        BOOLEAN = new JavaType("BOOLEAN", 5, Boolean.TYPE, java/lang/Boolean, Boolean.valueOf(false));
        STRING = new JavaType("STRING", 6, java/lang/String, java/lang/String, "");
        BYTE_STRING = new JavaType("BYTE_STRING", 7, com/google/protobuf/ByteString, com/google/protobuf/ByteString, ByteString.EMPTY);
        ENUM = new JavaType("ENUM", 8, Integer.TYPE, java/lang/Integer, null);
        MESSAGE = new JavaType("MESSAGE", 9, java/lang/Object, java/lang/Object, null);
        $VALUES = (new JavaType[] {
            VOID, INT, LONG, FLOAT, DOUBLE, BOOLEAN, STRING, BYTE_STRING, ENUM, MESSAGE
        });
    }
}
