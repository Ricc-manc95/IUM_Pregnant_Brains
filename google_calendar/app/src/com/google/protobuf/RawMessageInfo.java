// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            MessageInfo, MessageLite

public final class RawMessageInfo
    implements MessageInfo
{

    public final MessageLite defaultInstance;
    public final int flags;
    public final String info;
    public final Object objects[];

    public RawMessageInfo(MessageLite messagelite, String s, Object aobj[])
    {
        defaultInstance = messagelite;
        info = s;
        objects = aobj;
        int i = 1;
        int j = s.charAt(0);
        if (j < 55296)
        {
            flags = j;
            return;
        }
        int k = j & 0x1fff;
        j = 13;
        do
        {
            char c = s.charAt(i);
            if (c >= '\uD800')
            {
                k |= (c & 0x1fff) << j;
                j += 13;
                i++;
            } else
            {
                flags = c << j | k;
                return;
            }
        } while (true);
    }

    public final MessageLite getDefaultInstance()
    {
        return defaultInstance;
    }

    public final int getSyntax$50KKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UK3IDTQ6UKRPDPQ62U1R0()
    {
        if ((flags & 1) == 1)
        {
            return android.support.v4.content.ModernAsyncTask.Status.PROTO2$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T874RRKDT9NIRJKC5S3M___0;
        } else
        {
            return android.support.v4.content.ModernAsyncTask.Status.PROTO3$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T874RRKDT9NIRJKC5S3M___0;
        }
    }

    public final boolean isMessageSetWireFormat()
    {
        return (flags & 2) == 2;
    }
}
