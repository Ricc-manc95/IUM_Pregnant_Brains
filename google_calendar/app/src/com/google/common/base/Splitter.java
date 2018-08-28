// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.common.base:
//            CharMatcher

public final class Splitter
{

    public final int limit;
    public final boolean omitEmptyStrings;
    public final Strategy strategy;
    public final CharMatcher trimmer;

    public Splitter(Strategy strategy1)
    {
        this(strategy1, false, CharMatcher.none(), 0x7fffffff);
    }

    public Splitter(Strategy strategy1, boolean flag, CharMatcher charmatcher, int i)
    {
        strategy = strategy1;
        omitEmptyStrings = flag;
        trimmer = charmatcher;
        limit = i;
    }

    public static Splitter on(char c)
    {
        final CharMatcher separatorMatcher = CharMatcher.is(c);
        if (separatorMatcher == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Splitter(new _cls1());
        }
    }

    public final List splitToList(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            throw new NullPointerException();
        }
        charsequence = strategy.iterator(this, charsequence);
        ArrayList arraylist = new ArrayList();
        for (; charsequence.hasNext(); arraylist.add((String)charsequence.next())) { }
        return Collections.unmodifiableList(arraylist);
    }

    private class _cls1
        implements Strategy
    {

        public final CharMatcher val$separatorMatcher;

        public final Iterator iterator(Splitter splitter, CharSequence charsequence)
        {
            class _cls1 extends SplittingIterator
            {
                private class SplittingIterator extends AbstractIterator
                {

                    private int limit;
                    private int offset;
                    private final boolean omitEmptyStrings;
                    public final CharSequence toSplit;
                    private final CharMatcher trimmer;

                    protected final Object computeNext()
                    {
                        int j = offset;
                        do
                        {
                            if (offset == -1)
                            {
                                break;
                            }
                            int i = separatorStart(offset);
                            if (i == -1)
                            {
                                i = toSplit.length();
                                offset = -1;
                            } else
                            {
                                offset = separatorEnd(i);
                            }
                            if (offset == j)
                            {
                                offset = offset + 1;
                                if (offset > toSplit.length())
                                {
                                    offset = -1;
                                }
                            } else
                            {
                                for (; j < i && trimmer.matches(toSplit.charAt(j)); j++) { }
                                for (; i > j && trimmer.matches(toSplit.charAt(i - 1)); i--) { }
                                if (omitEmptyStrings && j == i)
                                {
                                    j = offset;
                                } else
                                {
                                    int k;
                                    if (limit == 1)
                                    {
                                        i = toSplit.length();
                                        offset = -1;
                                        do
                                        {
                                            k = i;
                                            if (i <= j)
                                            {
                                                break;
                                            }
                                            k = i;
                                            if (!trimmer.matches(toSplit.charAt(i - 1)))
                                            {
                                                break;
                                            }
                                            i--;
                                        } while (true);
                                    } else
                                    {
                                        limit = limit - 1;
                                        k = i;
                                    }
                                    return toSplit.subSequence(j, k).toString();
                                }
                            }
                        } while (true);
                        super.state$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0 = android.support.v4.content.ModernAsyncTask.Status.DONE$9HHMUR9FCTNMUPRCCKNM6RRDDLNMSBR2C5PMABQ1C9PN8SJ1CDQ4IT35E9GN8RRI4H9N8OBKCKTG____0;
                        return null;
                    }

                    abstract int separatorEnd(int i);

                    abstract int separatorStart(int i);

                    protected SplittingIterator(CharSequence charsequence)
                    {
                        offset = 0;
                        trimmer = Splitter.this.trimmer;
                        omitEmptyStrings = Splitter.this.omitEmptyStrings;
                        limit = Splitter.this.limit;
                        toSplit = charsequence;
                    }
                }


                private final _cls1 this$0;

                final int separatorEnd(int i)
                {
                    return i + 1;
                }

                final int separatorStart(int i)
                {
                    return separatorMatcher.indexIn(toSplit, i);
                }

                _cls1(CharSequence charsequence)
                {
                    this$0 = _cls1.this;
                    super(charsequence);
                }
            }

            return splitter. new _cls1(charsequence);
        }

        public _cls1()
        {
            separatorMatcher = charmatcher;
            super();
        }
    }


    private class Strategy
    {

        public abstract Iterator iterator(Splitter splitter, CharSequence charsequence);
    }

}
