// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.lang.reflect.Constructor;

// Referenced classes of package com.google.protobuf:
//            MapFieldSchemaLite, MapFieldSchema

final class MapFieldSchemas
{

    public static final MapFieldSchema FULL_SCHEMA = loadSchemaForFullRuntime();
    public static final MapFieldSchema LITE_SCHEMA = new MapFieldSchemaLite();

    private static MapFieldSchema loadSchemaForFullRuntime()
    {
        MapFieldSchema mapfieldschema;
        try
        {
            mapfieldschema = (MapFieldSchema)Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception exception)
        {
            return null;
        }
        return mapfieldschema;
    }

}
