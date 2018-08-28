// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// Referenced classes of package com.google.protobuf:
//            ManifestSchemaFactory, SchemaFactory, Internal, Schema

public final class Protobuf
{

    public static final Protobuf INSTANCE = new Protobuf();
    private final ConcurrentMap schemaCache = new ConcurrentHashMap();
    private final SchemaFactory schemaFactory;

    private Protobuf()
    {
        SchemaFactory schemafactory = null;
        int i = 0;
        do
        {
            if (i > 0)
            {
                break;
            }
            SchemaFactory schemafactory1 = newSchemaFactory((new String[] {
                "com.google.protobuf.AndroidProto3SchemaFactory"
            })[0]);
            schemafactory = schemafactory1;
            if (schemafactory1 != null)
            {
                break;
            }
            i++;
            schemafactory = schemafactory1;
        } while (true);
        Object obj = schemafactory;
        if (schemafactory == null)
        {
            obj = new ManifestSchemaFactory();
        }
        schemaFactory = ((SchemaFactory) (obj));
    }

    private static SchemaFactory newSchemaFactory(String s)
    {
        try
        {
            s = (SchemaFactory)Class.forName(s).getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return null;
        }
        return s;
    }

    public final Schema schemaFor(Class class1)
    {
        Schema schema1;
label0:
        {
            Internal.checkNotNull(class1, "messageType");
            schema1 = (Schema)schemaCache.get(class1);
            Schema schema = schema1;
            if (schema1 == null)
            {
                schema1 = schemaFactory.createSchema(class1);
                Internal.checkNotNull(class1, "messageType");
                Internal.checkNotNull(schema1, "schema");
                schema = (Schema)schemaCache.putIfAbsent(class1, schema1);
                if (schema == null)
                {
                    break label0;
                }
            }
            return schema;
        }
        return schema1;
    }

}
