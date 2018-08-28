// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


public final class MapEntryLite
{

    public final Metadata metadata;

    public MapEntryLite(WireFormat.FieldType fieldtype, Object obj, WireFormat.FieldType fieldtype1, Object obj1)
    {
        metadata = new Metadata(fieldtype, obj, fieldtype1, obj1);
    }

    private class Metadata
    {

        public final Object defaultKey;
        public final Object defaultValue;
        public final WireFormat.FieldType keyType;
        public final WireFormat.FieldType valueType;

        public Metadata(WireFormat.FieldType fieldtype, Object obj, WireFormat.FieldType fieldtype1, Object obj1)
        {
            keyType = fieldtype;
            defaultKey = obj;
            valueType = fieldtype1;
            defaultValue = obj1;
        }
    }

}
