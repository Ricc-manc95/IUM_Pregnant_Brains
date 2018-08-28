// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


// Referenced classes of package com.google.common.base:
//            Predicate, Preconditions

public abstract class CharMatcher
    implements Predicate
{

    protected CharMatcher()
    {
    }

    public static CharMatcher is(char c)
    {
        return new Is(c);
    }

    public static CharMatcher none()
    {
        return None.INSTANCE;
    }

    public static CharMatcher whitespace()
    {
        return Whitespace.INSTANCE;
    }

    public final boolean apply(Object obj)
    {
        return matches(((Character)obj).charValue());
    }

    public int countIn(CharSequence charsequence)
    {
        int i = 0;
        int j;
        int k;
        for (j = 0; i < charsequence.length(); j = k)
        {
            k = j;
            if (matches(charsequence.charAt(i)))
            {
                k = j + 1;
            }
            i++;
        }

        return j;
    }

    public int indexIn(CharSequence charsequence, int i)
    {
        int j = charsequence.length();
        if (i < 0 || i > j)
        {
            throw new IndexOutOfBoundsException(Preconditions.badPositionIndex(i, j, "index"));
        }
        for (; i < j; i++)
        {
            if (matches(charsequence.charAt(i)))
            {
                return i;
            }
        }

        return -1;
    }

    public abstract boolean matches(char c);

    private class Is extends FastMatcher
    {
        private class FastMatcher extends CharMatcher
        {

            FastMatcher()
            {
            }
        }


        private final char match;

        public final boolean matches(char c)
        {
            return c == match;
        }

        public final String toString()
        {
            char c = match;
            char ac[] = new char[6];
            char[] _tmp = ac;
            ac[0] = '\\';
            ac[1] = 'u';
            ac[2] = '\0';
            ac[3] = '\0';
            ac[4] = '\0';
            ac[5] = '\0';
            for (int i = 0; i < 4; i++)
            {
                ac[5 - i] = "0123456789ABCDEF".charAt(c & 0xf);
                c >>= '\004';
            }

            String s = String.copyValueOf(ac);
            return (new StringBuilder(String.valueOf(s).length() + 18)).append("CharMatcher.is('").append(s).append("')").toString();
        }

        Is(char c)
        {
            match = c;
        }
    }


    private class None extends NamedFastMatcher
    {
        private class NamedFastMatcher extends FastMatcher
        {

            private final String description;

            public final String toString()
            {
                return description;
            }

            NamedFastMatcher(String s)
            {
                if (s == null)
                {
                    throw new NullPointerException();
                } else
                {
                    description = (String)s;
                    return;
                }
            }
        }


        public static final None INSTANCE = new None();

        public final int countIn(CharSequence charsequence)
        {
            if (charsequence == null)
            {
                throw new NullPointerException();
            } else
            {
                return 0;
            }
        }

        public final int indexIn(CharSequence charsequence, int i)
        {
            int j = charsequence.length();
            if (i < 0 || i > j)
            {
                throw new IndexOutOfBoundsException(Preconditions.badPositionIndex(i, j, "index"));
            } else
            {
                return -1;
            }
        }

        public final boolean matches(char c)
        {
            return false;
        }


        private None()
        {
            super("CharMatcher.none()");
        }
    }


    private class Whitespace extends NamedFastMatcher
    {

        public static final Whitespace INSTANCE = new Whitespace();
        private static final int SHIFT = Integer.numberOfLeadingZeros(31);

        public final boolean matches(char c)
        {
            return "\u2002\u3000\r\205\u200A\u2005\u2000\u3000\u2029\013\u3000\u2008\u2003\u205F\u3000\u1680\t \u2006\u2001\u202F\240\f\u2009\u3000\u2004\u3000\u3000\u2028\n\u2007\u3000".charAt(0x6449bf0a * c >>> SHIFT) == c;
        }


        Whitespace()
        {
            super("CharMatcher.whitespace()");
        }
    }

}
