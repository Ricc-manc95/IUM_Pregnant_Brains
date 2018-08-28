// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

// Referenced classes of package okio:
//            Util, Base64, Buffer

public class ByteString
    implements Serializable, Comparable
{

    public static final ByteString EMPTY;
    private static final char HEX_DIGITS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };
    public static final long serialVersionUID = 1L;
    public final byte data[];
    public transient int hashCode;
    private transient String utf8;

    public ByteString(byte abyte0[])
    {
        data = abyte0;
    }

    public static ByteString encodeUtf8(String s)
    {
        if (s == null)
        {
            throw new IllegalArgumentException("s == null");
        } else
        {
            ByteString bytestring = new ByteString(s.getBytes(Util.UTF_8));
            bytestring.utf8 = s;
            return bytestring;
        }
    }

    public static transient ByteString of(byte abyte0[])
    {
        if (abyte0 == null)
        {
            throw new IllegalArgumentException("data == null");
        } else
        {
            return new ByteString((byte[])abyte0.clone());
        }
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException
    {
        int j = objectinputstream.readInt();
        if (objectinputstream == null)
        {
            throw new IllegalArgumentException("in == null");
        }
        if (j < 0)
        {
            throw new IllegalArgumentException((new StringBuilder("byteCount < 0: ")).append(j).toString());
        }
        byte abyte0[] = new byte[j];
        int k;
        for (int i = 0; i < j; i += k)
        {
            k = objectinputstream.read(abyte0, i, j - i);
            if (k == -1)
            {
                throw new EOFException();
            }
        }

        objectinputstream = new ByteString(abyte0);
        try
        {
            Field field = okio/ByteString.getDeclaredField("data");
            field.setAccessible(true);
            field.set(this, ((ByteString) (objectinputstream)).data);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (ObjectInputStream objectinputstream)
        {
            throw new AssertionError();
        }
        // Misplaced declaration of an exception variable
        catch (ObjectInputStream objectinputstream)
        {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.writeInt(data.length);
        objectoutputstream.write(data);
    }

    public String base64()
    {
        return Base64.encode(data);
    }

    public int compareTo(Object obj)
    {
        int i;
        int j;
        int k;
        int l;
        obj = (ByteString)obj;
        j = size();
        k = ((ByteString) (obj)).size();
        l = Math.min(j, k);
        i = 0;
_L9:
        if (i >= l) goto _L2; else goto _L1
_L1:
        int i1;
        int j1;
        i1 = getByte(i) & 0xff;
        j1 = ((ByteString) (obj)).getByte(i) & 0xff;
        if (i1 == j1) goto _L4; else goto _L3
_L3:
        if (i1 >= j1) goto _L6; else goto _L5
_L5:
        return -1;
_L6:
        return 1;
_L4:
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        if (j == k)
        {
            return 0;
        }
        if (j < k) goto _L5; else goto _L7
_L7:
        return 1;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        return (obj instanceof ByteString) && ((ByteString)obj).size() == data.length && ((ByteString)obj).rangeEquals(0, data, 0, data.length);
    }

    public byte getByte(int i)
    {
        return data[i];
    }

    public int hashCode()
    {
        int i = hashCode;
        if (i != 0)
        {
            return i;
        } else
        {
            int j = Arrays.hashCode(data);
            hashCode = j;
            return j;
        }
    }

    public String hex()
    {
        int i = 0;
        char ac[] = new char[data.length << 1];
        byte abyte0[] = data;
        int k = abyte0.length;
        int j = 0;
        for (; i < k; i++)
        {
            byte byte0 = abyte0[i];
            int l = j + 1;
            ac[j] = HEX_DIGITS[byte0 >> 4 & 0xf];
            j = l + 1;
            ac[l] = HEX_DIGITS[byte0 & 0xf];
        }

        return new String(ac);
    }

    public boolean rangeEquals(int i, ByteString bytestring, int j, int k)
    {
        return bytestring.rangeEquals(0, data, 0, k);
    }

    public boolean rangeEquals(int i, byte abyte0[], int j, int k)
    {
        return i >= 0 && i <= data.length - k && j >= 0 && j <= abyte0.length - k && Util.arrayRangeEquals(data, i, abyte0, j, k);
    }

    public int size()
    {
        return data.length;
    }

    public ByteString substring(int i, int j)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if (j > data.length)
        {
            throw new IllegalArgumentException((new StringBuilder("endIndex > length(")).append(data.length).append(")").toString());
        }
        int k = j - i;
        if (k < 0)
        {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i == 0 && j == data.length)
        {
            return this;
        } else
        {
            byte abyte0[] = new byte[k];
            System.arraycopy(data, i, abyte0, 0, k);
            return new ByteString(abyte0);
        }
    }

    public ByteString toAsciiLowercase()
    {
        int i = 0;
        do
        {
label0:
            {
                Object obj = this;
                if (i < data.length)
                {
                    byte byte0 = data[i];
                    if (byte0 < 65 || byte0 > 90)
                    {
                        break label0;
                    }
                    obj = (byte[])data.clone();
                    obj[i] = (byte)(byte0 + 32);
                    for (i++; i < obj.length; i++)
                    {
                        byte byte1 = obj[i];
                        if (byte1 >= 65 && byte1 <= 90)
                        {
                            obj[i] = (byte)(byte1 + 32);
                        }
                    }

                    obj = new ByteString(((byte []) (obj)));
                }
                return ((ByteString) (obj));
            }
            i++;
        } while (true);
    }

    public byte[] toByteArray()
    {
        return (byte[])data.clone();
    }

    public String toString()
    {
        if (data.length == 0)
        {
            return "[size=0]";
        }
        String s = utf8();
        int k = s.length();
        int j = 0;
        int i = 0;
        do
        {
            if (i < k)
            {
                if (j != 64)
                {
                    int l = s.codePointAt(i);
                    if (Character.isISOControl(l) && l != 10 && l != 13 || l == 65533)
                    {
                        i = -1;
                    } else
                    {
                        j++;
                        i += Character.charCount(l);
                        continue;
                    }
                }
            } else
            {
                i = s.length();
            }
            if (i == -1)
            {
                if (data.length <= 64)
                {
                    return (new StringBuilder("[hex=")).append(hex()).append("]").toString();
                } else
                {
                    return (new StringBuilder("[size=")).append(data.length).append(" hex=").append(substring(0, 64).hex()).append("\u2026]").toString();
                }
            }
            String s1 = s.substring(0, i).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (i < s.length())
            {
                return (new StringBuilder("[size=")).append(data.length).append(" text=").append(s1).append("\u2026]").toString();
            }
            return (new StringBuilder("[text=")).append(s1).append("]").toString();
        } while (true);
    }

    public String utf8()
    {
        String s = utf8;
        if (s != null)
        {
            return s;
        } else
        {
            String s1 = new String(data, Util.UTF_8);
            utf8 = s1;
            return s1;
        }
    }

    public void write(Buffer buffer)
    {
        buffer.write(data, 0, data.length);
    }

    static 
    {
        byte abyte0[] = new byte[0];
        if (abyte0 == null)
        {
            throw new IllegalArgumentException("data == null");
        } else
        {
            EMPTY = new ByteString((byte[])abyte0.clone());
        }
    }
}
