// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

// Referenced classes of package com.google.protobuf:
//            ByteString, Internal, ByteOutput

final class RopeByteString extends ByteString
{

    public static final int minLengthByDepth[];
    public static final long serialVersionUID = 1L;
    public final ByteString left;
    private final int leftLength;
    public final ByteString right;
    private final int totalLength;
    private final int treeDepth;

    RopeByteString(ByteString bytestring, ByteString bytestring1)
    {
        left = bytestring;
        right = bytestring1;
        leftLength = bytestring.size();
        totalLength = leftLength + bytestring1.size();
        treeDepth = Math.max(bytestring.getTreeDepth(), bytestring1.getTreeDepth()) + 1;
    }

    static ByteString concatenate(ByteString bytestring, ByteString bytestring1)
    {
        if (bytestring1.size() == 0)
        {
            return bytestring;
        }
        if (bytestring.size() == 0)
        {
            return bytestring1;
        }
        int i = bytestring.size();
        i = bytestring1.size() + i;
        if (i < 128)
        {
            return concatenateBytes(bytestring, bytestring1);
        }
        if (bytestring instanceof RopeByteString)
        {
            RopeByteString ropebytestring = (RopeByteString)bytestring;
            if (ropebytestring.right.size() + bytestring1.size() < 128)
            {
                bytestring = concatenateBytes(ropebytestring.right, bytestring1);
                return new RopeByteString(ropebytestring.left, bytestring);
            }
            if (ropebytestring.left.getTreeDepth() > ropebytestring.right.getTreeDepth() && ropebytestring.getTreeDepth() > bytestring1.getTreeDepth())
            {
                bytestring = new RopeByteString(ropebytestring.right, bytestring1);
                return new RopeByteString(ropebytestring.left, bytestring);
            }
        }
        int j = Math.max(bytestring.getTreeDepth(), bytestring1.getTreeDepth());
        if (i >= minLengthByDepth[j + 1])
        {
            return new RopeByteString(bytestring, bytestring1);
        }
        Balancer balancer = new Balancer();
        balancer.doBalance(bytestring);
        balancer.doBalance(bytestring1);
        for (bytestring = (ByteString)balancer.prefixesStack.pop(); !balancer.prefixesStack.isEmpty(); bytestring = new RopeByteString((ByteString)balancer.prefixesStack.pop(), bytestring)) { }
        return bytestring;
    }

    private static ByteString concatenateBytes(ByteString bytestring, ByteString bytestring1)
    {
        int i = bytestring.size();
        int j = bytestring1.size();
        byte abyte0[] = new byte[i + j];
        bytestring.copyTo(abyte0, 0, 0, i);
        bytestring1.copyTo(abyte0, 0, i, j);
        return new ByteString.LiteralByteString(abyte0);
    }

    private final void readObject(ObjectInputStream objectinputstream)
        throws IOException
    {
        throw new InvalidObjectException("RopeByteStream instances are not to be serialized directly");
    }

    public final byte byteAt(int i)
    {
        checkIndex(i, totalLength);
        if (i < leftLength)
        {
            return left.byteAt(i);
        } else
        {
            return right.byteAt(i - leftLength);
        }
    }

    protected final void copyToInternal(byte abyte0[], int i, int j, int k)
    {
        if (i + k <= leftLength)
        {
            left.copyToInternal(abyte0, i, j, k);
            return;
        }
        if (i >= leftLength)
        {
            right.copyToInternal(abyte0, i - leftLength, j, k);
            return;
        } else
        {
            int l = leftLength - i;
            left.copyToInternal(abyte0, i, j, l);
            right.copyToInternal(abyte0, 0, j + l, k - l);
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (obj != this) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        int i;
        int k;
        flag = flag2;
        if (!(obj instanceof ByteString))
        {
            continue;
        }
        obj = (ByteString)obj;
        flag = flag2;
        if (totalLength != ((ByteString) (obj)).size())
        {
            continue;
        }
        if (totalLength == 0)
        {
            return true;
        }
        i = super.hash;
        k = ((ByteString) (obj)).hash;
        if (i == 0 || k == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag2;
        if (i != k) goto _L4; else goto _L3
_L3:
        PieceIterator pieceiterator = new PieceIterator(this);
        ByteString.LeafByteString leafbytestring = (ByteString.LeafByteString)pieceiterator.next();
        PieceIterator pieceiterator1 = new PieceIterator(((ByteString) (obj)));
        obj = (ByteString.LeafByteString)pieceiterator1.next();
        int j = 0;
        int l = 0;
        int i1 = 0;
        do
        {
            int l1 = leafbytestring.size() - l;
            int j1 = ((ByteString) (obj)).size() - j;
            int k1 = Math.min(l1, j1);
            boolean flag1;
            if (l == 0)
            {
                flag1 = leafbytestring.equalsRange(((ByteString) (obj)), j, k1);
            } else
            {
                flag1 = ((ByteString.LeafByteString) (obj)).equalsRange(leafbytestring, l, k1);
            }
            flag = flag2;
            if (!flag1)
            {
                continue;
            }
            i1 += k1;
            if (i1 >= totalLength)
            {
                if (i1 == totalLength)
                {
                    return true;
                } else
                {
                    throw new IllegalStateException();
                }
            }
            if (k1 == l1)
            {
                leafbytestring = (ByteString.LeafByteString)pieceiterator.next();
                l = 0;
            } else
            {
                l += k1;
            }
            if (k1 == j1)
            {
                obj = (ByteString.LeafByteString)pieceiterator1.next();
                j = 0;
            } else
            {
                j += k1;
            }
        } while (true);
        if (true) goto _L4; else goto _L5
_L5:
    }

    protected final int getTreeDepth()
    {
        return treeDepth;
    }

    protected final boolean isBalanced()
    {
        return totalLength >= minLengthByDepth[treeDepth];
    }

    public final boolean isValidUtf8()
    {
        boolean flag = false;
        int i = left.partialIsValidUtf8(0, 0, leftLength);
        if (right.partialIsValidUtf8(i, 0, right.size()) == 0)
        {
            flag = true;
        }
        return flag;
    }

    protected final int partialHash(int i, int j, int k)
    {
        if (j + k <= leftLength)
        {
            return left.partialHash(i, j, k);
        }
        if (j >= leftLength)
        {
            return right.partialHash(i, j - leftLength, k);
        } else
        {
            int l = leftLength - j;
            i = left.partialHash(i, j, l);
            return right.partialHash(i, 0, k - l);
        }
    }

    protected final int partialIsValidUtf8(int i, int j, int k)
    {
        if (j + k <= leftLength)
        {
            return left.partialIsValidUtf8(i, j, k);
        }
        if (j >= leftLength)
        {
            return right.partialIsValidUtf8(i, j - leftLength, k);
        } else
        {
            int l = leftLength - j;
            i = left.partialIsValidUtf8(i, j, l);
            return right.partialIsValidUtf8(i, 0, k - l);
        }
    }

    public final int size()
    {
        return totalLength;
    }

    public final ByteString substring(int i, int j)
    {
        int k = checkRange(i, j, totalLength);
        Object obj;
        if (k == 0)
        {
            obj = ByteString.EMPTY;
        } else
        {
            obj = this;
            if (k != totalLength)
            {
                if (j <= leftLength)
                {
                    return left.substring(i, j);
                }
                if (i >= leftLength)
                {
                    return right.substring(i - leftLength, j - leftLength);
                } else
                {
                    ByteString bytestring = left;
                    return new RopeByteString(bytestring.substring(i, bytestring.size()), right.substring(0, j - leftLength));
                }
            }
        }
        return ((ByteString) (obj));
    }

    protected final String toStringInternal(Charset charset)
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
        return new String(abyte0, charset);
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
        return new ByteString.LiteralByteString(abyte0);
    }

    final void writeTo(ByteOutput byteoutput)
        throws IOException
    {
        left.writeTo(byteoutput);
        right.writeTo(byteoutput);
    }

    static 
    {
        int i = 1;
        ArrayList arraylist = new ArrayList();
        int k = 1;
        do
        {
            int l = k;
            if (i <= 0)
            {
                break;
            }
            arraylist.add(Integer.valueOf(i));
            k = i;
            i = l + i;
        } while (true);
        arraylist.add(Integer.valueOf(0x7fffffff));
        minLengthByDepth = new int[arraylist.size()];
        for (int j = 0; j < minLengthByDepth.length; j++)
        {
            minLengthByDepth[j] = ((Integer)arraylist.get(j)).intValue();
        }

    }

    private class Balancer
    {

        public final Stack prefixesStack = new Stack();

        final void doBalance(ByteString bytestring)
        {
            if (bytestring.isBalanced())
            {
                int i = bytestring.size();
                i = Arrays.binarySearch(RopeByteString.minLengthByDepth, i);
                if (i < 0)
                {
                    i = -(i + 1) - 1;
                }
                int k = RopeByteString.minLengthByDepth[i + 1];
                if (prefixesStack.isEmpty() || ((ByteString)prefixesStack.peek()).size() >= k)
                {
                    prefixesStack.push(bytestring);
                    return;
                }
                i = RopeByteString.minLengthByDepth[i];
                Object obj;
                for (obj = (ByteString)prefixesStack.pop(); !prefixesStack.isEmpty() && ((ByteString)prefixesStack.peek()).size() < i; obj = new RopeByteString((ByteString)prefixesStack.pop(), ((ByteString) (obj)))) { }
                bytestring = new RopeByteString(((ByteString) (obj)), bytestring);
                do
                {
                    if (prefixesStack.isEmpty())
                    {
                        break;
                    }
                    int j = bytestring.size();
                    int l = Arrays.binarySearch(RopeByteString.minLengthByDepth, j);
                    j = l;
                    if (l < 0)
                    {
                        j = -(l + 1) - 1;
                    }
                    j = RopeByteString.minLengthByDepth[j + 1];
                    if (((ByteString)prefixesStack.peek()).size() >= j)
                    {
                        break;
                    }
                    bytestring = new RopeByteString((ByteString)prefixesStack.pop(), bytestring);
                } while (true);
                prefixesStack.push(bytestring);
                return;
            }
            if (bytestring instanceof RopeByteString)
            {
                bytestring = (RopeByteString)bytestring;
                doBalance(((RopeByteString) (bytestring)).left);
                doBalance(((RopeByteString) (bytestring)).right);
                return;
            } else
            {
                bytestring = String.valueOf(bytestring.getClass());
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(bytestring).length() + 49)).append("Has a new type of ByteString been created? Found ").append(bytestring).toString());
            }
        }

        Balancer()
        {
        }
    }


    private class PieceIterator
        implements Iterator
    {

        private final Stack breadCrumbs = new Stack();
        private ByteString.LeafByteString next;

        private final ByteString.LeafByteString getLeafByLeft(ByteString bytestring)
        {
            for (; bytestring instanceof RopeByteString; bytestring = ((RopeByteString) (bytestring)).left)
            {
                bytestring = (RopeByteString)bytestring;
                breadCrumbs.push(bytestring);
            }

            return (ByteString.LeafByteString)bytestring;
        }

        public final boolean hasNext()
        {
            return next != null;
        }

        public final Object next()
        {
            ByteString.LeafByteString leafbytestring1;
            if (next == null)
            {
                throw new NoSuchElementException();
            }
            leafbytestring1 = next;
_L4:
            if (!breadCrumbs.isEmpty()) goto _L2; else goto _L1
_L1:
            ByteString.LeafByteString leafbytestring = null;
_L3:
            next = leafbytestring;
            return leafbytestring1;
_L2:
            leafbytestring = getLeafByLeft(((RopeByteString)breadCrumbs.pop()).right);
            boolean flag;
            if (leafbytestring.size() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag) goto _L4; else goto _L3
        }

        public final void remove()
        {
            throw new UnsupportedOperationException();
        }

        PieceIterator(ByteString bytestring)
        {
            next = getLeafByLeft(bytestring);
        }
    }

}
