// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class AbstractIterator
    implements Iterator
{

    private Object next;
    public int state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0;

    protected AbstractIterator()
    {
        state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.NOT_READY$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0;
    }

    protected abstract Object computeNext();

    public final boolean hasNext()
    {
        boolean flag;
        if (state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0 != android.support.v4.content.ModernAsyncTask.Status.FAILED$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0)
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
        state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0 - 1;
        JVM INSTR tableswitch 0 2: default 60
    //                   0 92
    //                   1 60
    //                   2 94;
           goto _L1 _L2 _L1 _L3
_L1:
        state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.FAILED$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0;
        next = computeNext();
        if (state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0 == android.support.v4.content.ModernAsyncTask.Status.DONE$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0) goto _L5; else goto _L4
_L4:
        state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.READY$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0;
_L2:
        return true;
_L3:
        return false;
_L5:
        return false;
    }

    public final Object next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        } else
        {
            state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.NOT_READY$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0;
            Object obj = next;
            next = null;
            return obj;
        }
    }

    public final void remove()
    {
        throw new UnsupportedOperationException();
    }
}
