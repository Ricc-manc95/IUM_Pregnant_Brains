// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.IOException;
import java.util.Iterator;

// Referenced classes of package com.google.common.base:
//            Joiner

final class nit> extends Joiner
{

    private final Joiner this$0;

    public final Appendable appendTo(Appendable appendable, Iterator iterator)
        throws IOException
    {
        if (appendable == null)
        {
            throw new NullPointerException(String.valueOf("appendable"));
        }
        if (iterator == null)
        {
            throw new NullPointerException(String.valueOf("parts"));
        }
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = iterator.next();
            if (obj == null)
            {
                continue;
            }
            appendable.append(toString(obj));
            break;
        } while (true);
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj1 = iterator.next();
            if (obj1 != null)
            {
                appendable.append(separator);
                appendable.append(toString(obj1));
            }
        } while (true);
        return appendable;
    }

    (Joiner joiner1)
    {
        this$0 = Joiner.this;
        super(joiner1);
    }
}
