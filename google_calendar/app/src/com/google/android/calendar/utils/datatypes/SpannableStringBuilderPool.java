// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.datatypes;

import android.text.SpannableStringBuilder;
import java.util.TreeSet;

public final class SpannableStringBuilderPool
{

    public static final TreeSet pool;
    private static final Entry searchPoolEntry = new Entry();

    public static Entry get(int i)
    {
        TreeSet treeset = pool;
        treeset;
        JVM INSTR monitorenter ;
        Entry entry1;
        if (pool.isEmpty())
        {
            break MISSING_BLOCK_LABEL_67;
        }
        searchPoolEntry.size = i;
        entry1 = (Entry)pool.ceiling(searchPoolEntry);
        Entry entry;
        entry = entry1;
        if (entry1 != null)
        {
            break MISSING_BLOCK_LABEL_51;
        }
        entry = (Entry)pool.last();
        if (entry == null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        pool.remove(entry);
        treeset;
        JVM INSTR monitorexit ;
        return entry;
        treeset;
        JVM INSTR monitorexit ;
        return new Entry(new SpannableStringBuilder());
        Exception exception;
        exception;
        treeset;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static final int lambda$static$0$SpannableStringBuilderPool(Entry entry, Entry entry1)
    {
        if (entry != entry1)
        {
            if (entry.size != entry1.size)
            {
                return entry.size - entry1.size;
            }
            if (entry.string != null && entry1.string != null)
            {
                return System.identityHashCode(entry) - System.identityHashCode(entry1);
            }
        }
        return 0;
    }

    static 
    {
        class .Lambda._cls0
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls0();

            public final int compare(Object obj, Object obj1)
            {
                return SpannableStringBuilderPool.lambda$static$0$SpannableStringBuilderPool((Entry)obj, (Entry)obj1);
            }


            private .Lambda._cls0()
            {
            }
        }

        pool = new TreeSet(.Lambda._cls0..instance);
    }

    private class Entry
    {

        public int size;
        public final SpannableStringBuilder string;

        Entry()
        {
            string = null;
        }

        Entry(SpannableStringBuilder spannablestringbuilder)
        {
            string = spannablestringbuilder;
            size = spannablestringbuilder.length();
        }
    }

}
