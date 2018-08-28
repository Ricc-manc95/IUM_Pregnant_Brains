// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.lang.reflect.Constructor;

// Referenced classes of package com.google.protobuf:
//            NewInstanceSchemaLite, NewInstanceSchema

final class NewInstanceSchemas
{

    public static final NewInstanceSchema FULL_SCHEMA = loadSchemaForFullRuntime();
    public static final NewInstanceSchema LITE_SCHEMA = new NewInstanceSchemaLite();

    private static NewInstanceSchema loadSchemaForFullRuntime()
    {
        NewInstanceSchema newinstanceschema;
        try
        {
            newinstanceschema = (NewInstanceSchema)Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception exception)
        {
            return null;
        }
        return newinstanceschema;
    }

}
