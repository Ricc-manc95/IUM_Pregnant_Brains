// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import java.util.BitSet;
import java.util.Locale;

// Referenced classes of package io.grpc:
//            Metadata

public static abstract class e.Charsets.US_ASCII
{

    private static final BitSet VALID_T_CHARS;
    public final String name;
    public final byte nameBytes[];
    private final String originalName;

    public static iMarshaller of(String s, iMarshaller imarshaller)
    {
        return new iKey(s, false, imarshaller);
    }

    public static ryMarshaller of(String s, ryMarshaller rymarshaller)
    {
        return new ryKey(s, rymarshaller);
    }

    static tedAsciiMarshaller of(String s, boolean flag, tedAsciiMarshaller tedasciimarshaller)
    {
        return new tedAsciiKey(s, flag, tedasciimarshaller);
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        } else
        {
            obj = (lass)obj;
            return name.equals(((ls) (obj)).name);
        }
    }

    public int hashCode()
    {
        return name.hashCode();
    }

    abstract Object parseBytes(byte abyte0[]);

    abstract byte[] toBytes(Object obj);

    public String toString()
    {
        String s = name;
        return (new StringBuilder(String.valueOf(s).length() + 12)).append("Key{name='").append(s).append("'}").toString();
    }

    static 
    {
        BitSet bitset = new BitSet(127);
        bitset.set(45);
        bitset.set(95);
        bitset.set(46);
        for (char c = '0'; c <= 57; c++)
        {
            bitset.set(c);
        }

        for (char c1 = 'a'; c1 <= 122; c1++)
        {
            bitset.set(c1);
        }

        VALID_T_CHARS = bitset;
    }

    set(String s, boolean flag)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("name"));
        }
        originalName = (String)s;
        s = originalName.toLowerCase(Locale.ROOT);
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("name"));
        }
        boolean flag1;
        if (!s.isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf("token must have at least 1 tchar"));
        }
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if ((!flag || c != ':' || i != 0) && !VALID_T_CHARS.get(c))
            {
                throw new IllegalArgumentException(Strings.lenientFormat("Invalid character '%s' in key name '%s'", new Object[] {
                    Character.valueOf(c), s
                }));
            }
        }

        name = s;
        nameBytes = name.getBytes(Charsets.US_ASCII);
    }
}
