// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.IOException;
import java.util.Iterator;

public class Joiner
{

    public final String separator;

    Joiner(Joiner joiner)
    {
        separator = joiner.separator;
    }

    public Joiner(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            separator = (String)s;
            return;
        }
    }

    public Appendable appendTo(Appendable appendable, Iterator iterator)
        throws IOException
    {
        if (appendable == null)
        {
            throw new NullPointerException();
        }
        if (iterator.hasNext())
        {
            appendable.append(toString(iterator.next()));
            for (; iterator.hasNext(); appendable.append(toString(iterator.next())))
            {
                appendable.append(separator);
            }

        }
        return appendable;
    }

    public final StringBuilder appendTo(StringBuilder stringbuilder, Iterator iterator)
    {
        try
        {
            appendTo(((Appendable) (stringbuilder)), iterator);
        }
        // Misplaced declaration of an exception variable
        catch (StringBuilder stringbuilder)
        {
            throw new AssertionError(stringbuilder);
        }
        return stringbuilder;
    }

    public Joiner skipNulls()
    {
        return new _cls2(this);
    }

    CharSequence toString(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (obj instanceof CharSequence)
        {
            return (CharSequence)obj;
        } else
        {
            return obj.toString();
        }
    }

    private class _cls2 extends Joiner
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

        _cls2(Joiner joiner1)
        {
            this$0 = Joiner.this;
            super(joiner1);
        }
    }

}
