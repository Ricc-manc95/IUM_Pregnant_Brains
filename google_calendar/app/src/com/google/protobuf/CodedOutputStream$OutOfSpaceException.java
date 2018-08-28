// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;

// Referenced classes of package com.google.protobuf:
//            CodedOutputStream

public static final class  extends IOException
{

    public static final long serialVersionUID = 0x9f95917c52ce6e25L;

    ()
    {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    (String s)
    {
        String s1 = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = s1.concat(s);
        } else
        {
            s = new String(s1);
        }
        super(s);
    }

    (String s, Throwable throwable)
    {
        String s1 = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = s1.concat(s);
        } else
        {
            s = new String(s1);
        }
        super(s, throwable);
    }

    (Throwable throwable)
    {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", throwable);
    }
}
