// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            MessageInfoFactory, GeneratedMessageLite, MessageInfo

final class GeneratedMessageInfoFactory
    implements MessageInfoFactory
{

    public static final GeneratedMessageInfoFactory instance = new GeneratedMessageInfoFactory();

    private GeneratedMessageInfoFactory()
    {
    }

    public final boolean isSupported(Class class1)
    {
        return com/google/protobuf/GeneratedMessageLite.isAssignableFrom(class1);
    }

    public final MessageInfo messageInfoFor(Class class1)
    {
        if (!com/google/protobuf/GeneratedMessageLite.isAssignableFrom(class1))
        {
            class1 = String.valueOf(class1.getName());
            if (class1.length() != 0)
            {
                class1 = "Unsupported message type: ".concat(class1);
            } else
            {
                class1 = new String("Unsupported message type: ");
            }
            throw new IllegalArgumentException(class1);
        }
        MessageInfo messageinfo;
        try
        {
            messageinfo = (MessageInfo)GeneratedMessageLite.getDefaultInstance(class1.asSubclass(com/google/protobuf/GeneratedMessageLite)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.BUILD_MESSAGE_INFO$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        }
        catch (Exception exception)
        {
            class1 = String.valueOf(class1.getName());
            if (class1.length() != 0)
            {
                class1 = "Unable to get message info for ".concat(class1);
            } else
            {
                class1 = new String("Unable to get message info for ");
            }
            throw new RuntimeException(class1, exception);
        }
        return messageinfo;
    }

}
