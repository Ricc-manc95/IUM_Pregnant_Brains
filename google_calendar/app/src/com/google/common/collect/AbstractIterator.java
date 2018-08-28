// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.NoSuchElementException;

// Referenced classes of package com.google.common.collect:
//            UnmodifiableIterator

public abstract class AbstractIterator extends UnmodifiableIterator
{

    private Object next;
    public int state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0;

    protected AbstractIterator()
    {
        state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0 = android.support.v4.content.ModernAsyncTask.Status.NOT_READY$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0;
    }

    protected abstract Object computeNext();

    public final boolean hasNext()
    {
        boolean flag2 = false;
        boolean flag;
        if (state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0 != android.support.v4.content.ModernAsyncTask.Status.FAILED$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        boolean flag1 = flag2;
        switch (state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0 - 1)
        {
        case 1: // '\001'
        default:
            state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0 = android.support.v4.content.ModernAsyncTask.Status.FAILED$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0;
            next = computeNext();
            flag1 = flag2;
            if (state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0 != android.support.v4.content.ModernAsyncTask.Status.DONE$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0)
            {
                state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0 = android.support.v4.content.ModernAsyncTask.Status.READY$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0;
                flag1 = true;
            }
            // fall through

        case 2: // '\002'
            return flag1;

        case 0: // '\0'
            return true;
        }
    }

    public final Object next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        } else
        {
            state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0 = android.support.v4.content.ModernAsyncTask.Status.NOT_READY$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR3DTM6OPB3EGNK2OJJEHP62ORK95Q6ASJ1EHNN492JEHGN8P9R0;
            Object obj = next;
            next = null;
            return obj;
        }
    }
}
