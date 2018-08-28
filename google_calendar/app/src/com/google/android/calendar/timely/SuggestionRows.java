// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            SuggestionRow

public final class SuggestionRows
{

    public final int bestTimesCount;
    public final ImmutableList suggestions;
    public ImmutableList timelineSuggestions;

    public SuggestionRows(List list, int i)
    {
        suggestions = ImmutableList.copyOf(list);
        bestTimesCount = i;
        list = ImmutableList.builder();
        ImmutableList immutablelist = (ImmutableList)suggestions;
        int k = immutablelist.size();
        i = 0;
        UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
        do
        {
            if (i >= k)
            {
                break;
            }
            Object obj = immutablelist.get(i);
            int j = i + 1;
            obj = (SuggestionRow)obj;
            i = j;
            if (((SuggestionRow) (obj)).type == 0)
            {
                obj = (com.google.common.collect.ImmutableList.Builder)list.add(((SuggestionRow) (obj)).suggestion);
                i = j;
            }
        } while (true);
        list.forceCopy = true;
        timelineSuggestions = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (list)).contents, ((com.google.common.collect.ImmutableList.Builder) (list)).size);
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof SuggestionRows)
        {
            obj = (SuggestionRows)obj;
            ImmutableList immutablelist = suggestions;
            ImmutableList immutablelist1 = ((SuggestionRows) (obj)).suggestions;
            boolean flag;
            if (immutablelist == immutablelist1 || immutablelist != null && immutablelist.equals(immutablelist1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && bestTimesCount == ((SuggestionRows) (obj)).bestTimesCount)
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            suggestions, Integer.valueOf(bestTimesCount)
        });
    }
}
