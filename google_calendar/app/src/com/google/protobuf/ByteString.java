// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.protobuf:
//            Internal, Android, RopeByteString, ByteOutput

public abstract class ByteString
    implements Serializable, Iterable
{

    public static final ByteString EMPTY;
    private static final ByteArrayCopier byteArrayCopier;
    public int hash;

    ByteString()
    {
        hash = 0;
    }

    private static ByteString balancedConcat(Iterator iterator1, int i)
    {
        if (i <= 0)
        {
            throw new IllegalArgumentException(String.format("length (%s) must be >= 1", new Object[] {
                Integer.valueOf(i)
            }));
        }
        if (i == 1)
        {
            return (ByteString)iterator1.next();
        }
        int j = i >>> 1;
        ByteString bytestring = balancedConcat(iterator1, j);
        iterator1 = balancedConcat(iterator1, i - j);
        if (0x7fffffff - bytestring.size() < iterator1.size())
        {
            i = bytestring.size();
            int k = iterator1.size();
            throw new IllegalArgumentException((new StringBuilder(53)).append("ByteString would be too long: ").append(i).append("+").append(k).toString());
        } else
        {
            return RopeByteString.concatenate(bytestring, iterator1);
        }
    }

    static void checkIndex(int i, int j)
    {
        if ((j - (i + 1) | i) < 0)
        {
            if (i < 0)
            {
                throw new ArrayIndexOutOfBoundsException((new StringBuilder(22)).append("Index < 0: ").append(i).toString());
            } else
            {
                throw new ArrayIndexOutOfBoundsException((new StringBuilder(40)).append("Index > length: ").append(i).append(", ").append(j).toString());
            }
        } else
        {
            return;
        }
    }

    static int checkRange(int i, int j, int k)
    {
        int l = j - i;
        if ((i | j | l | k - j) < 0)
        {
            if (i < 0)
            {
                throw new IndexOutOfBoundsException((new StringBuilder(32)).append("Beginning index: ").append(i).append(" < 0").toString());
            }
            if (j < i)
            {
                throw new IndexOutOfBoundsException((new StringBuilder(66)).append("Beginning index larger than ending index: ").append(i).append(", ").append(j).toString());
            } else
            {
                throw new IndexOutOfBoundsException((new StringBuilder(37)).append("End index: ").append(j).append(" >= ").append(k).toString());
            }
        } else
        {
            return l;
        }
    }

    public static ByteString copyFrom(Iterable iterable)
    {
        int i = ((Collection)iterable).size();
        if (i == 0)
        {
            return EMPTY;
        } else
        {
            return balancedConcat(iterable.iterator(), i);
        }
    }

    public static ByteString copyFrom(byte abyte0[])
    {
        int i = abyte0.length;
        checkRange(0, 0 + i, abyte0.length);
        return new LiteralByteString(byteArrayCopier.copyFrom(abyte0, 0, i));
    }

    public static ByteString copyFrom(byte abyte0[], int i, int j)
    {
        checkRange(i, i + j, abyte0.length);
        return new LiteralByteString(byteArrayCopier.copyFrom(abyte0, i, j));
    }

    public static ByteString copyFromUtf8(String s)
    {
        return new LiteralByteString(s.getBytes(Internal.UTF_8));
    }

    static CodedBuilder newCodedBuilder(int i)
    {
        return new CodedBuilder(i);
    }

    static int toInt(byte byte0)
    {
        return byte0 & 0xff;
    }

    static ByteString wrap(byte abyte0[])
    {
        return new LiteralByteString(abyte0);
    }

    static ByteString wrap(byte abyte0[], int i, int j)
    {
        return new BoundedByteString(abyte0, i, j);
    }

    public abstract byte byteAt(int i);

    public final void copyTo(byte abyte0[], int i, int j, int k)
    {
        checkRange(i, i + k, size());
        checkRange(j, j + k, abyte0.length);
        if (k > 0)
        {
            copyToInternal(abyte0, i, j, k);
        }
    }

    public abstract void copyToInternal(byte abyte0[], int i, int j, int k);

    public abstract boolean equals(Object obj);

    protected abstract int getTreeDepth();

    public final int hashCode()
    {
        int j = hash;
        int i = j;
        if (j == 0)
        {
            i = size();
            int k = partialHash(i, 0, i);
            i = k;
            if (k == 0)
            {
                i = 1;
            }
            hash = i;
        }
        return i;
    }

    protected abstract boolean isBalanced();

    public abstract boolean isValidUtf8();

    public Iterator iterator()
    {
        return new _cls1();
    }

    protected abstract int partialHash(int i, int j, int k);

    protected abstract int partialIsValidUtf8(int i, int j, int k);

    public abstract int size();

    public abstract ByteString substring(int i, int j);

    public final String toString()
    {
        return String.format("<ByteString@%s size=%d>", new Object[] {
            Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())
        });
    }

    protected abstract String toStringInternal(Charset charset);

    abstract void writeTo(ByteOutput byteoutput)
        throws IOException;

    static 
    {
        EMPTY = new LiteralByteString(Internal.EMPTY_BYTE_ARRAY);
        Object obj;
        boolean flag;
        if (Android.MEMORY_CLASS != null && !Android.IS_ROBOLECTRIC)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = new SystemByteArrayCopier();
        } else
        {
            obj = new ArraysByteArrayCopier();
        }
        byteArrayCopier = ((ByteArrayCopier) (obj));
        new _cls2();
    }

    private class LiteralByteString extends LeafByteString
    {
        private class LeafByteString extends ByteString
        {

            abstract boolean equalsRange(ByteString bytestring, int i, int j);

            protected final int getTreeDepth()
            {
                return 0;
            }

            protected final boolean isBalanced()
            {
                return true;
            }

            LeafByteString()
            {
            }
        }


        public static final long serialVersionUID = 1L;
        public final byte bytes[];

        public byte byteAt(int i)
        {
            return bytes[i];
        }

        protected void copyToInternal(byte abyte0[], int i, int j, int k)
        {
            System.arraycopy(bytes, i, abyte0, j, k);
        }

        public final boolean equals(Object obj)
        {
            if (obj == this)
            {
                return true;
            }
            if (!(obj instanceof ByteString))
            {
                return false;
            }
            if (size() != ((ByteString)obj).size())
            {
                return false;
            }
            if (size() == 0)
            {
                return true;
            }
            if (obj instanceof LiteralByteString)
            {
                LiteralByteString literalbytestring = (LiteralByteString)obj;
                int i = hash;
                int j = ((ByteString) (literalbytestring)).hash;
                if (i != 0 && j != 0 && i != j)
                {
                    return false;
                } else
                {
                    return equalsRange((LiteralByteString)obj, 0, size());
                }
            } else
            {
                return obj.equals(this);
            }
        }

        final boolean equalsRange(ByteString bytestring, int i, int j)
        {
            if (j > bytestring.size())
            {
                i = size();
                throw new IllegalArgumentException((new StringBuilder(40)).append("Length too large: ").append(j).append(i).toString());
            }
            if (i + j > bytestring.size())
            {
                int k = bytestring.size();
                throw new IllegalArgumentException((new StringBuilder(59)).append("Ran off end of other: ").append(i).append(", ").append(j).append(", ").append(k).toString());
            }
            if (bytestring instanceof LiteralByteString)
            {
                bytestring = (LiteralByteString)bytestring;
                byte abyte0[] = bytes;
                byte abyte1[] = ((LiteralByteString) (bytestring)).bytes;
                int i1 = getOffsetIntoBytes();
                int l = getOffsetIntoBytes();
                for (i = bytestring.getOffsetIntoBytes() + i; l < i1 + j; i++)
                {
                    if (abyte0[l] != abyte1[i])
                    {
                        return false;
                    }
                    l++;
                }

                return true;
            } else
            {
                return bytestring.substring(i, i + j).equals(substring(0, j));
            }
        }

        protected int getOffsetIntoBytes()
        {
            return 0;
        }

        public final boolean isValidUtf8()
        {
            boolean flag = false;
            int i = getOffsetIntoBytes();
            byte abyte0[] = bytes;
            int j = size();
            if (Utf8.processor.partialIsValidUtf8(0, abyte0, i, j + i) == 0)
            {
                flag = true;
            }
            return flag;
        }

        protected final int partialHash(int i, int j, int k)
        {
            return Internal.partialHash(i, bytes, getOffsetIntoBytes() + j, k);
        }

        protected final int partialIsValidUtf8(int i, int j, int k)
        {
            j = getOffsetIntoBytes() + j;
            byte abyte0[] = bytes;
            return Utf8.processor.partialIsValidUtf8(i, abyte0, j, j + k);
        }

        public int size()
        {
            return bytes.length;
        }

        public final ByteString substring(int i, int j)
        {
            j = checkRange(i, j, size());
            if (j == 0)
            {
                return ByteString.EMPTY;
            } else
            {
                return new BoundedByteString(bytes, getOffsetIntoBytes() + i, j);
            }
        }

        protected final String toStringInternal(Charset charset)
        {
            return new String(bytes, getOffsetIntoBytes(), size(), charset);
        }

        final void writeTo(ByteOutput byteoutput)
            throws IOException
        {
            byteoutput.writeLazy(bytes, getOffsetIntoBytes(), size());
        }

        LiteralByteString(byte abyte0[])
        {
            bytes = abyte0;
        }
    }


    private class ByteArrayCopier
    {

        public abstract byte[] copyFrom(byte abyte0[], int i, int j);
    }


    private class CodedBuilder
    {

        public final byte buffer[];
        public final CodedOutputStream output;

        public final ByteString build()
        {
            if (output.spaceLeft() != 0)
            {
                throw new IllegalStateException("Did not write as much data as expected.");
            } else
            {
                return new LiteralByteString(buffer);
            }
        }

        CodedBuilder(int i)
        {
            buffer = new byte[i];
            output = CodedOutputStream.newInstance(buffer);
        }
    }


    private class BoundedByteString extends LiteralByteString
    {

        public static final long serialVersionUID = 1L;
        private final int bytesLength;
        private final int bytesOffset;

        private final void readObject(ObjectInputStream objectinputstream)
            throws IOException
        {
            throw new InvalidObjectException("BoundedByteStream instances are not to be serialized directly");
        }

        public final byte byteAt(int i)
        {
            checkIndex(i, size());
            return bytes[bytesOffset + i];
        }

        protected final void copyToInternal(byte abyte0[], int i, int j, int k)
        {
            System.arraycopy(bytes, getOffsetIntoBytes() + i, abyte0, j, k);
        }

        protected final int getOffsetIntoBytes()
        {
            return bytesOffset;
        }

        public final int size()
        {
            return bytesLength;
        }

        final Object writeReplace()
        {
            int i = size();
            byte abyte0[];
            if (i == 0)
            {
                abyte0 = Internal.EMPTY_BYTE_ARRAY;
            } else
            {
                abyte0 = new byte[i];
                copyToInternal(abyte0, 0, 0, i);
            }
            return new LiteralByteString(abyte0);
        }

        BoundedByteString(byte abyte0[], int i, int j)
        {
            super(abyte0);
            checkRange(i, i + j, abyte0.length);
            bytesOffset = i;
            bytesLength = j;
        }
    }


    private class _cls1
        implements ByteIterator
    {

        private final int limit;
        private int position;
        private final ByteString this$0;

        public final boolean hasNext()
        {
            return position < limit;
        }

        public final Object next()
        {
            return Byte.valueOf(nextByte());
        }

        public final byte nextByte()
        {
            byte byte0;
            try
            {
                ByteString bytestring = ByteString.this;
                int i = position;
                position = i + 1;
                byte0 = bytestring.byteAt(i);
            }
            catch (IndexOutOfBoundsException indexoutofboundsexception)
            {
                throw new NoSuchElementException(indexoutofboundsexception.getMessage());
            }
            return byte0;
        }

        public final void remove()
        {
            throw new UnsupportedOperationException();
        }

        _cls1()
        {
            this$0 = ByteString.this;
            super();
            position = 0;
            limit = size();
        }
    }


    private class SystemByteArrayCopier
        implements ByteArrayCopier
    {

        public final byte[] copyFrom(byte abyte0[], int i, int j)
        {
            byte abyte1[] = new byte[j];
            System.arraycopy(abyte0, i, abyte1, 0, j);
            return abyte1;
        }

        SystemByteArrayCopier()
        {
        }
    }


    private class _cls2
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (ByteString)obj;
            obj1 = (ByteString)obj1;
            ByteIterator byteiterator = (ByteIterator)((ByteString) (obj)).iterator();
            for (ByteIterator byteiterator1 = (ByteIterator)((ByteString) (obj1)).iterator(); byteiterator.hasNext() && byteiterator1.hasNext();)
            {
                int i = Integer.compare(ByteString.toInt(byteiterator.nextByte()), ByteString.toInt(byteiterator1.nextByte()));
                if (i != 0)
                {
                    return i;
                }
            }

            return Integer.compare(((ByteString) (obj)).size(), ((ByteString) (obj1)).size());
        }

        _cls2()
        {
        }

        private class ByteIterator
            implements Iterator
        {

            public abstract byte nextByte();
        }

    }


    private class ArraysByteArrayCopier
        implements ByteArrayCopier
    {

        public final byte[] copyFrom(byte abyte0[], int i, int j)
        {
            return Arrays.copyOfRange(abyte0, i, i + j);
        }

        ArraysByteArrayCopier()
        {
        }
    }

}
