// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.lang.reflect.Constructor;

// Referenced classes of package com.google.protobuf:
//            ExtensionSchemaLite, ExtensionSchema

final class ExtensionSchemas
{

    public static final ExtensionSchema FULL_SCHEMA = loadSchemaForFullRuntime();
    public static final ExtensionSchema LITE_SCHEMA = new ExtensionSchemaLite();

    private static ExtensionSchema loadSchemaForFullRuntime()
    {
        ExtensionSchema extensionschema;
        try
        {
            extensionschema = (ExtensionSchema)Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception exception)
        {
            return null;
        }
        return extensionschema;
    }

}
