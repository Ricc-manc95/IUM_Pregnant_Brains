// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            ExtensionLite, GeneratedMessageLite, MessageLite

public static final class descriptor extends ExtensionLite
{

    public final MessageLite containingTypeDefaultInstance;
    public final Object defaultValue;
    public final  descriptor;
    public final MessageLite messageDefaultInstance;

    public final Object singularFromFieldSetType(Object obj)
    {
        Object obj1 = obj;
        if (descriptor.type.type == .type)
        {
            obj1 = descriptor.enumTypeMap.enumTypeMap(((Integer)obj).intValue());
        }
        return obj1;
    }

    (MessageLite messagelite, Object obj, MessageLite messagelite1,  )
    {
        if (messagelite == null)
        {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        }
        if (.type == .type && messagelite1 == null)
        {
            throw new IllegalArgumentException("Null messageDefaultInstance");
        } else
        {
            containingTypeDefaultInstance = messagelite;
            defaultValue = obj;
            messageDefaultInstance = messagelite1;
            descriptor = ;
            return;
        }
    }
}
