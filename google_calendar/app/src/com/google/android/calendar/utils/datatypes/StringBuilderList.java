// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.datatypes;

import android.text.SpannableStringBuilder;
import java.io.Closeable;
import java.util.AbstractList;
import java.util.TreeSet;

// Referenced classes of package com.google.android.calendar.utils.datatypes:
//            SpannableStringBuilderPool

public final class StringBuilderList extends AbstractList
    implements Closeable
{

    public final SpannableStringBuilderPool.Entry list[] = new SpannableStringBuilderPool.Entry[3];
    public int size;

    public StringBuilderList(int i)
    {
        size = 0;
    }

    public final boolean add(CharSequence charsequence)
    {
        if (size >= list.length)
        {
            throw new IndexOutOfBoundsException();
        }
        if (list[size] == null)
        {
            list[size] = SpannableStringBuilderPool.get(charsequence.length());
        }
        list[size].string.append(charsequence);
        size = size + 1;
        return true;
    }

    public final volatile boolean add(Object obj)
    {
        return add((CharSequence)obj);
    }

    public final void clear()
    {
        for (int i = 0; i < list.length; i++)
        {
            if (list[i] != null)
            {
                SpannableStringBuilderPool.Entry entry = list[i];
                synchronized (SpannableStringBuilderPool.pool)
                {
                    if (SpannableStringBuilderPool.pool.size() < 100)
                    {
                        entry.size = Math.max(entry.size, entry.string.length());
                        entry.string.clear();
                        entry.string.clearSpans();
                        SpannableStringBuilderPool.pool.add(entry);
                    }
                }
                list[i] = null;
            }
        }

        break MISSING_BLOCK_LABEL_105;
        exception;
        treeset;
        JVM INSTR monitorexit ;
        throw exception;
        size = 0;
        return;
    }

    public final void close()
    {
        clear();
    }

    public final Object get(int i)
    {
        if (i >= size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            return list[i].string;
        }
    }

    public final int size()
    {
        return size;
    }
}
