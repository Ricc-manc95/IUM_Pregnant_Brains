// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            MessageInfoFactory, MessageInfo

final class factories
    implements MessageInfoFactory
{

    private MessageInfoFactory factories[];

    public final boolean isSupported(Class class1)
    {
        boolean flag1 = false;
        MessageInfoFactory amessageinfofactory[] = factories;
        int j = amessageinfofactory.length;
        int i = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if (i < j)
                {
                    if (!amessageinfofactory[i].isSupported(class1))
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            i++;
        } while (true);
    }

    public final MessageInfo messageInfoFor(Class class1)
    {
        MessageInfoFactory amessageinfofactory[] = factories;
        int j = amessageinfofactory.length;
        for (int i = 0; i < j; i++)
        {
            MessageInfoFactory messageinfofactory = amessageinfofactory[i];
            if (messageinfofactory.isSupported(class1))
            {
                return messageinfofactory.messageInfoFor(class1);
            }
        }

        class1 = String.valueOf(class1.getName());
        if (class1.length() != 0)
        {
            class1 = "No factory is available for message type: ".concat(class1);
        } else
        {
            class1 = new String("No factory is available for message type: ");
        }
        throw new UnsupportedOperationException(class1);
    }

    transient (MessageInfoFactory amessageinfofactory[])
    {
        factories = amessageinfofactory;
    }
}
