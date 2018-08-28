// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.protobuf:
//            ExtensionRegistryFactory, GeneratedExtensionRegistryLoader, MessageLite

public class ExtensionRegistryLite
{
    static final class ObjectIntPair
    {

        private final int number;
        private final Object object;

        public final boolean equals(Object obj)
        {
            if (obj instanceof ObjectIntPair)
            {
                if (object == ((ObjectIntPair) (obj = (ObjectIntPair)obj)).object && number == ((ObjectIntPair) (obj)).number)
                {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode()
        {
            return System.identityHashCode(object) * 65535 + number;
        }

        ObjectIntPair(Object obj, int i)
        {
            object = obj;
            number = i;
        }
    }


    public static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = new ExtensionRegistryLite((byte)0);
    private static volatile boolean eagerlyParseMessageSets = false;
    private static volatile ExtensionRegistryLite generatedRegistry;
    private final Map extensionsByNumber;

    ExtensionRegistryLite()
    {
        extensionsByNumber = new HashMap();
    }

    ExtensionRegistryLite(byte byte0)
    {
        extensionsByNumber = Collections.emptyMap();
    }

    public static ExtensionRegistryLite getEmptyRegistry()
    {
        return ExtensionRegistryFactory.createEmpty();
    }

    public static ExtensionRegistryLite getGeneratedRegistry()
    {
        Object obj;
        obj = generatedRegistry;
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        com/google/protobuf/ExtensionRegistryLite;
        JVM INSTR monitorenter ;
        ExtensionRegistryLite extensionregistrylite = generatedRegistry;
        obj = extensionregistrylite;
        if (extensionregistrylite != null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        obj = ExtensionRegistryFactory.loadGeneratedRegistry();
        generatedRegistry = ((ExtensionRegistryLite) (obj));
        com/google/protobuf/ExtensionRegistryLite;
        JVM INSTR monitorexit ;
        return ((ExtensionRegistryLite) (obj));
        obj;
        com/google/protobuf/ExtensionRegistryLite;
        JVM INSTR monitorexit ;
        throw obj;
        return ((ExtensionRegistryLite) (obj));
    }

    static ExtensionRegistryLite loadGeneratedRegistry()
    {
        return GeneratedExtensionRegistryLoader.load(com/google/protobuf/ExtensionRegistryLite);
    }

    private static Class resolveExtensionClass()
    {
        Class class1;
        try
        {
            class1 = Class.forName("com.google.protobuf.Extension");
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            return null;
        }
        return class1;
    }

    public GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber(MessageLite messagelite, int i)
    {
        return (GeneratedMessageLite.GeneratedExtension)extensionsByNumber.get(new ObjectIntPair(messagelite, i));
    }

    static 
    {
        resolveExtensionClass();
    }
}
