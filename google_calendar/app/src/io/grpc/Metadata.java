// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.BaseEncoding;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Locale;

public final class Metadata
{
    static final class AsciiKey extends Key
    {

        private final AsciiMarshaller marshaller;

        final Object parseBytes(byte abyte0[])
        {
            return marshaller.parseAsciiString(new String(abyte0, Charsets.US_ASCII));
        }

        final byte[] toBytes(Object obj)
        {
            return marshaller.toAsciiString(obj).getBytes(Charsets.US_ASCII);
        }

        AsciiKey(String s, boolean flag, AsciiMarshaller asciimarshaller)
        {
            super(s, flag);
            boolean flag1;
            if (!s.endsWith("-bin"))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalArgumentException(Strings.lenientFormat("ASCII header is named %s.  Only binary headers may end with %s", new Object[] {
                    s, "-bin"
                }));
            }
            if (asciimarshaller == null)
            {
                throw new NullPointerException(String.valueOf("marshaller"));
            } else
            {
                marshaller = (AsciiMarshaller)asciimarshaller;
                return;
            }
        }
    }

    public static interface AsciiMarshaller
    {

        public abstract Object parseAsciiString(String s);

        public abstract String toAsciiString(Object obj);
    }

    static final class BinaryKey extends Key
    {

        private final BinaryMarshaller marshaller;

        final Object parseBytes(byte abyte0[])
        {
            return marshaller.parseBytes(abyte0);
        }

        final byte[] toBytes(Object obj)
        {
            return marshaller.toBytes(obj);
        }

        BinaryKey(String s, BinaryMarshaller binarymarshaller)
        {
            boolean flag = true;
            super(s, false);
            if (!s.endsWith("-bin"))
            {
                throw new IllegalArgumentException(Strings.lenientFormat("Binary header is named %s. It must end with %s", new Object[] {
                    s, "-bin"
                }));
            }
            if (s.length() <= 4)
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("empty key name"));
            }
            if (binarymarshaller == null)
            {
                throw new NullPointerException(String.valueOf("marshaller is null"));
            } else
            {
                marshaller = (BinaryMarshaller)binarymarshaller;
                return;
            }
        }
    }

    public static interface BinaryMarshaller
    {

        public abstract Object parseBytes(byte abyte0[]);

        public abstract byte[] toBytes(Object obj);
    }

    public static abstract class Key
    {

        private static final BitSet VALID_T_CHARS;
        public final String name;
        public final byte nameBytes[];
        private final String originalName;

        public static Key of(String s, AsciiMarshaller asciimarshaller)
        {
            return new AsciiKey(s, false, asciimarshaller);
        }

        public static Key of(String s, BinaryMarshaller binarymarshaller)
        {
            return new BinaryKey(s, binarymarshaller);
        }

        static Key of(String s, boolean flag, TrustedAsciiMarshaller trustedasciimarshaller)
        {
            return new TrustedAsciiKey(s, flag, trustedasciimarshaller);
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
                obj = (Key)obj;
                return name.equals(((Key) (obj)).name);
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

        Key(String s, boolean flag)
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

    static final class TrustedAsciiKey extends Key
    {

        private final TrustedAsciiMarshaller marshaller;

        final Object parseBytes(byte abyte0[])
        {
            return marshaller.parseAsciiString(abyte0);
        }

        final byte[] toBytes(Object obj)
        {
            return marshaller.toAsciiString(obj);
        }

        TrustedAsciiKey(String s, boolean flag, TrustedAsciiMarshaller trustedasciimarshaller)
        {
            super(s, flag);
            boolean flag1;
            if (!s.endsWith("-bin"))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalArgumentException(Strings.lenientFormat("ASCII header is named %s.  Only binary headers may end with %s", new Object[] {
                    s, "-bin"
                }));
            }
            if (trustedasciimarshaller == null)
            {
                throw new NullPointerException(String.valueOf("marshaller"));
            } else
            {
                marshaller = (TrustedAsciiMarshaller)trustedasciimarshaller;
                return;
            }
        }
    }

    static interface TrustedAsciiMarshaller
    {

        public abstract Object parseAsciiString(byte abyte0[]);

        public abstract byte[] toAsciiString(Object obj);
    }


    public static final AsciiMarshaller ASCII_STRING_MARSHALLER = new _cls2();
    public static final BinaryMarshaller BINARY_BYTE_MARSHALLER = new _cls1();
    public byte namesAndValues[][];
    public int size;

    public Metadata()
    {
    }

    private transient Metadata(int i, byte abyte0[][])
    {
        size = i;
        namesAndValues = abyte0;
    }

    transient Metadata(byte abyte0[][])
    {
        this(abyte0.length / 2, abyte0);
    }

    private final void expand(int i)
    {
        byte abyte0[][] = new byte[i][];
        if (size == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            System.arraycopy(namesAndValues, 0, abyte0, 0, size << 1);
        }
        namesAndValues = abyte0;
    }

    public final void discardAll(Key key)
    {
        int j = 0;
        boolean flag;
        if (size == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return;
        }
        for (int i = 0; i < size;)
        {
            int k = j;
            if (!Arrays.equals(key.nameBytes, namesAndValues[i << 1]))
            {
                byte abyte0[] = namesAndValues[i << 1];
                namesAndValues[j << 1] = abyte0;
                abyte0 = namesAndValues[(i << 1) + 1];
                namesAndValues[(j << 1) + 1] = abyte0;
                k = j + 1;
            }
            i++;
            j = k;
        }

        Arrays.fill(namesAndValues, j << 1, size << 1, null);
        size = j;
    }

    public final Object get(Key key)
    {
        for (int i = size - 1; i >= 0; i--)
        {
            if (Arrays.equals(key.nameBytes, namesAndValues[i << 1]))
            {
                return key.parseBytes(namesAndValues[(i << 1) + 1]);
            }
        }

        return null;
    }

    public final void merge(Metadata metadata)
    {
        boolean flag;
        if (metadata.size == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return;
        }
        int i;
        boolean flag1;
        int j;
        if (namesAndValues != null)
        {
            i = namesAndValues.length;
        } else
        {
            i = 0;
        }
        j = size;
        if (size == 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 || i - (j << 1) < metadata.size << 1)
        {
            expand((size << 1) + (metadata.size << 1));
        }
        System.arraycopy(metadata.namesAndValues, 0, namesAndValues, size << 1, metadata.size << 1);
        size = size + metadata.size;
    }

    public final void put(Key key, Object obj)
    {
label0:
        {
            if (key == null)
            {
                throw new NullPointerException(String.valueOf("key"));
            }
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("value"));
            }
            int i;
            if (size << 1 != 0)
            {
                int j = size;
                Object obj1;
                if (namesAndValues != null)
                {
                    i = namesAndValues.length;
                } else
                {
                    i = 0;
                }
                if (j << 1 != i)
                {
                    break label0;
                }
            }
            obj1 = new byte[Math.max(size << 1 << 1, 8)][];
            if (size == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                System.arraycopy(namesAndValues, 0, obj1, 0, size << 1);
            }
            namesAndValues = ((byte [][]) (obj1));
        }
        i = size;
        obj1 = key.nameBytes;
        namesAndValues[i << 1] = ((byte []) (obj1));
        i = size;
        key = key.toBytes(obj);
        namesAndValues[(i << 1) + 1] = key;
        size = size + 1;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("Metadata(");
        int i = 0;
        while (i < size) 
        {
            if (i != 0)
            {
                stringbuilder.append(',');
            }
            String s = new String(namesAndValues[i << 1], Charsets.US_ASCII);
            stringbuilder.append(s).append('=');
            if (s.endsWith("-bin"))
            {
                BaseEncoding baseencoding = BaseEncoding.BASE64;
                byte abyte0[] = namesAndValues[(i << 1) + 1];
                stringbuilder.append(baseencoding.encode(abyte0, 0, abyte0.length));
            } else
            {
                stringbuilder.append(new String(namesAndValues[(i << 1) + 1], Charsets.US_ASCII));
            }
            i++;
        }
        return stringbuilder.append(')').toString();
    }


    private class _cls1
        implements BinaryMarshaller
    {

        public final Object parseBytes(byte abyte0[])
        {
            return abyte0;
        }

        public final byte[] toBytes(Object obj)
        {
            return (byte[])obj;
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements AsciiMarshaller
    {

        public final Object parseAsciiString(String s)
        {
            return s;
        }

        public final String toAsciiString(Object obj)
        {
            return (String)obj;
        }

        _cls2()
        {
        }
    }

}
