// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.lang.reflect.Method;

// Referenced classes of package com.google.protobuf:
//            ExtensionRegistryLite

final class ExtensionRegistryFactory
{

    private static final Class EXTENSION_REGISTRY_CLASS = reflectExtensionRegistry();

    public static ExtensionRegistryLite createEmpty()
    {
        if (EXTENSION_REGISTRY_CLASS == null)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        ExtensionRegistryLite extensionregistrylite = (ExtensionRegistryLite)EXTENSION_REGISTRY_CLASS.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
        return extensionregistrylite;
        Exception exception;
        exception;
        return ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
    }

    static ExtensionRegistryLite loadGeneratedRegistry()
    {
        if (EXTENSION_REGISTRY_CLASS == null) goto _L2; else goto _L1
_L1:
        ExtensionRegistryLite extensionregistrylite1 = (ExtensionRegistryLite)EXTENSION_REGISTRY_CLASS.getDeclaredMethod("loadGeneratedRegistry", new Class[0]).invoke(null, new Object[0]);
_L4:
        ExtensionRegistryLite extensionregistrylite = extensionregistrylite1;
        if (extensionregistrylite1 == null)
        {
            extensionregistrylite = ExtensionRegistryLite.loadGeneratedRegistry();
        }
        extensionregistrylite1 = extensionregistrylite;
        if (extensionregistrylite == null)
        {
            extensionregistrylite1 = createEmpty();
        }
        return extensionregistrylite1;
        Exception exception;
        exception;
_L2:
        extensionregistrylite1 = null;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static Class reflectExtensionRegistry()
    {
        Class class1;
        try
        {
            class1 = Class.forName("com.google.protobuf.ExtensionRegistry");
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            return null;
        }
        return class1;
    }

}
