// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.stream;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

// Referenced classes of package com.google.gson.stream:
//            MalformedJsonException, JsonToken

public final class JsonReader
    implements Closeable
{

    private static final char NON_EXECUTE_PREFIX[] = ")]}'\n".toCharArray();
    public final char buffer[] = new char[1024];
    private final Reader in;
    private boolean lenient;
    private int limit;
    private int lineNumber;
    private int lineStart;
    public int pathIndices[];
    public String pathNames[];
    public int peeked;
    public long peekedLong;
    public int peekedNumberLength;
    public String peekedString;
    public int pos;
    private int stack[];
    public int stackSize;

    public JsonReader(Reader reader)
    {
        lenient = false;
        pos = 0;
        limit = 0;
        lineNumber = 0;
        lineStart = 0;
        peeked = 0;
        stack = new int[32];
        stackSize = 0;
        int ai[] = stack;
        int i = stackSize;
        stackSize = i + 1;
        ai[i] = 6;
        pathNames = new String[32];
        pathIndices = new int[32];
        in = reader;
    }

    private final boolean fillBuffer(int i)
        throws IOException
    {
        boolean flag1 = false;
        char ac[] = buffer;
        lineStart = lineStart - pos;
        boolean flag;
        if (limit != pos)
        {
            limit = limit - pos;
            System.arraycopy(ac, pos, ac, 0, limit);
        } else
        {
            limit = 0;
        }
        pos = 0;
        do
        {
            int j = in.read(ac, limit, ac.length - limit);
            flag = flag1;
            if (j == -1)
            {
                break;
            }
            limit = j + limit;
            j = i;
            if (lineNumber == 0)
            {
                j = i;
                if (lineStart == 0)
                {
                    j = i;
                    if (limit > 0)
                    {
                        j = i;
                        if (ac[0] == '\uFEFF')
                        {
                            pos = pos + 1;
                            lineStart = lineStart + 1;
                            j = i + 1;
                        }
                    }
                }
            }
            i = j;
            if (limit < j)
            {
                continue;
            }
            flag = true;
            break;
        } while (true);
        return flag;
    }

    private final boolean isLiteral(char c)
        throws IOException
    {
        switch (c)
        {
        default:
            return true;

        case 35: // '#'
        case 47: // '/'
        case 59: // ';'
        case 61: // '='
        case 92: // '\\'
            throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());

        case 9: // '\t'
        case 10: // '\n'
        case 12: // '\f'
        case 13: // '\r'
        case 32: // ' '
        case 44: // ','
        case 58: // ':'
        case 91: // '['
        case 93: // ']'
        case 123: // '{'
        case 125: // '}'
            return false;
        }
    }

    private final int nextNonWhitespace(boolean flag)
        throws IOException
    {
        char ac[] = buffer;
        int i = pos;
        int k = limit;
        do
        {
            int j;
label0:
            {
label1:
                {
                    j = k;
                    int l = i;
                    if (i == k)
                    {
                        pos = i;
                        if (!fillBuffer(1))
                        {
                            break label1;
                        }
                        l = pos;
                        j = limit;
                    }
                    i = l + 1;
                    k = ac[l];
                    if (k == '\n')
                    {
                        lineNumber = lineNumber + 1;
                        lineStart = i;
                        k = j;
                        continue;
                    }
                    if (k != 32 && k != 13 && k != 9)
                    {
                        if (k == 47)
                        {
                            pos = i;
                            if (i == j)
                            {
                                pos = pos - 1;
                                flag = fillBuffer(2);
                                pos = pos + 1;
                                if (!flag)
                                {
                                    return k;
                                }
                            }
                            throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());
                        }
                        if (k == 35)
                        {
                            pos = i;
                            throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());
                        } else
                        {
                            pos = i;
                            return k;
                        }
                    }
                    break label0;
                }
                if (flag)
                {
                    throw new EOFException((new StringBuilder("End of input")).append(locationString()).toString());
                } else
                {
                    return -1;
                }
            }
            k = j;
        } while (true);
    }

    public final void close()
        throws IOException
    {
        peeked = 0;
        stack[0] = 8;
        stackSize = 1;
        in.close();
    }

    public final int doPeek()
        throws IOException
    {
        int i = stack[stackSize - 1];
        if (i != 1) goto _L2; else goto _L1
_L1:
        stack[stackSize - 1] = 2;
_L20:
        nextNonWhitespace(true);
        JVM INSTR lookupswitch 7: default 104
    //                   34: 926
    //                   39: 896
    //                   44: 824
    //                   59: 824
    //                   91: 935
    //                   93: 811
    //                   123: 942;
           goto _L3 _L4 _L5 _L6 _L6 _L7 _L8 _L9
_L3:
        pos = pos - 1;
        i = buffer[pos];
        if (i != 116 && i != 84) goto _L11; else goto _L10
_L10:
        String s;
        String s1;
        s1 = "true";
        s = "TRUE";
        i = 5;
_L28:
        int j;
        int k;
        k = s1.length();
        j = 1;
_L34:
        if (j >= k) goto _L13; else goto _L12
_L12:
        if (pos + j < limit || fillBuffer(j + 1)) goto _L15; else goto _L14
_L14:
        i = 0;
_L31:
        if (i == 0) goto _L17; else goto _L16
_L16:
        j = i;
_L47:
        return j;
_L2:
        if (i != 2) goto _L19; else goto _L18
_L18:
        switch (nextNonWhitespace(true))
        {
        default:
            throw new MalformedJsonException((new StringBuilder()).append("Unterminated array").append(locationString()).toString());

        case 93: // ']'
            peeked = 4;
            return 4;

        case 59: // ';'
            throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());

        case 44: // ','
            break;
        }
          goto _L20
_L19:
        if (i == 3 || i == 5)
        {
            stack[stackSize - 1] = 4;
            if (i == 5)
            {
                switch (nextNonWhitespace(true))
                {
                default:
                    throw new MalformedJsonException((new StringBuilder()).append("Unterminated object").append(locationString()).toString());

                case 125: // '}'
                    peeked = 2;
                    return 2;

                case 59: // ';'
                    throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());

                case 44: // ','
                    break;
                }
            }
            switch (nextNonWhitespace(true))
            {
            default:
                throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());

            case 34: // '"'
                peeked = 13;
                return 13;

            case 39: // '\''
                throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());

            case 125: // '}'
                break;
            }
            if (i != 5)
            {
                peeked = 2;
                return 2;
            } else
            {
                throw new MalformedJsonException((new StringBuilder()).append("Expected name").append(locationString()).toString());
            }
        }
        if (i != 4) goto _L22; else goto _L21
_L21:
        stack[stackSize - 1] = 5;
        switch (nextNonWhitespace(true))
        {
        case 59: // ';'
        case 60: // '<'
        default:
            throw new MalformedJsonException((new StringBuilder()).append("Expected ':'").append(locationString()).toString());

        case 61: // '='
            throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());

        case 58: // ':'
            break;
        }
          goto _L20
_L22:
        if (i != 6) goto _L24; else goto _L23
_L23:
        stack[stackSize - 1] = 7;
          goto _L20
_L24:
        if (i == 7)
        {
            if (nextNonWhitespace(false) == -1)
            {
                peeked = 17;
                return 17;
            } else
            {
                throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());
            }
        }
        if (i != 8) goto _L20; else goto _L25
_L25:
        throw new IllegalStateException("JsonReader is closed");
_L8:
        if (i == 1)
        {
            peeked = 4;
            return 4;
        }
_L6:
        if (i == 1 || i == 2)
        {
            throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());
        } else
        {
            throw new MalformedJsonException((new StringBuilder()).append("Unexpected value").append(locationString()).toString());
        }
_L5:
        throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());
_L4:
        peeked = 9;
        return 9;
_L7:
        peeked = 3;
        return 3;
_L9:
        peeked = 1;
        return 1;
_L11:
        if (i != 102 && i != 70) goto _L27; else goto _L26
_L26:
        s1 = "false";
        s = "FALSE";
        i = 6;
          goto _L28
_L27:
        if (i != 110 && i != 78) goto _L30; else goto _L29
_L29:
        s1 = "null";
        s = "NULL";
        i = 7;
          goto _L28
_L30:
        i = 0;
          goto _L31
_L15:
        int l = buffer[pos + j];
        if (l == s1.charAt(j) || l == s.charAt(j)) goto _L33; else goto _L32
_L32:
        i = 0;
          goto _L31
_L33:
        j++;
          goto _L34
_L13:
        if ((pos + k < limit || fillBuffer(k + 1)) && isLiteral(buffer[pos + k]))
        {
            i = 0;
        } else
        {
            pos = pos + k;
            peeked = i;
        }
          goto _L31
_L17:
        char ac[];
        int l1;
        int j2;
        long l2;
        ac = buffer;
        l1 = pos;
        int i1 = limit;
        l2 = 0L;
        i = 0;
        j = 1;
        k = 0;
        l = 0;
        j2 = i1;
_L50:
        int j1;
        int k1;
        k1 = j2;
        j1 = l1;
        if (l1 + l != j2) goto _L36; else goto _L35
_L35:
        if (l == ac.length)
        {
            break MISSING_BLOCK_LABEL_1781;
        }
        if (!fillBuffer(l + 1)) goto _L38; else goto _L37
_L37:
        j1 = pos;
        k1 = limit;
_L36:
        char c = ac[j1 + l];
        c;
        JVM INSTR lookupswitch 5: default 1272
    //                   43: 1421
    //                   45: 1350
    //                   46: 1480
    //                   69: 1448
    //                   101: 1448;
           goto _L39 _L40 _L41 _L42 _L43 _L43
_L39:
        if (c >= '0' && c <= '9') goto _L45; else goto _L44
_L44:
        if (!isLiteral(c)) goto _L38; else goto _L46
_L46:
        i = 0;
_L54:
        j = i;
        int k2;
        long l3;
        if (i == 0)
        {
            if (!isLiteral(buffer[pos]))
            {
                throw new MalformedJsonException((new StringBuilder()).append("Expected value").append(locationString()).toString());
            } else
            {
                throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());
            }
        }
          goto _L47
_L41:
        if (k != 0) goto _L49; else goto _L48
_L48:
        i = 1;
        k = 1;
_L53:
        k2 = l + 1;
        l = k;
        j2 = k1;
        l1 = j1;
        k = i;
        i = l;
        l = k2;
          goto _L50
_L49:
        if (k != 5) goto _L52; else goto _L51
_L51:
        l1 = 6;
        k = i;
        i = l1;
          goto _L53
_L52:
        i = 0;
          goto _L54
_L40:
        if (k != 5) goto _L56; else goto _L55
_L55:
        l1 = 6;
        k = i;
        i = l1;
          goto _L53
_L56:
        i = 0;
          goto _L54
_L43:
        if (k != 2 && k != 4) goto _L58; else goto _L57
_L57:
        l1 = 5;
        k = i;
        i = l1;
          goto _L53
_L58:
        i = 0;
          goto _L54
_L42:
        if (k != 2) goto _L60; else goto _L59
_L59:
        l1 = 3;
        k = i;
        i = l1;
          goto _L53
_L60:
        i = 0;
          goto _L54
_L45:
        if (k == 1 || k == 0)
        {
            l2 = -(c - 48);
            l1 = 2;
            k = i;
            i = l1;
        } else
        if (k == 2)
        {
            if (l2 == 0L)
            {
                break MISSING_BLOCK_LABEL_1781;
            }
            l3 = 10L * l2 - (long)(c - 48);
            if (l2 > 0xf333333333333334L || l2 == 0xf333333333333334L && l3 < l2)
            {
                j2 = 1;
            } else
            {
                j2 = 0;
            }
            l1 = i;
            l2 = l3;
            j = j2 & j;
            i = k;
            k = l1;
        } else
        if (k == 3)
        {
            l1 = 4;
            k = i;
            i = l1;
        } else
        if (k == 5 || k == 6)
        {
            l1 = 7;
            k = i;
            i = l1;
        } else
        {
            int i2 = i;
            i = k;
            k = i2;
        }
          goto _L53
_L38:
        if (k == 2 && j != 0 && (l2 != 0x8000000000000000L || i != 0))
        {
            if (i == 0)
            {
                l2 = -l2;
            }
            peekedLong = l2;
            pos = pos + l;
            i = 15;
            peeked = 15;
        } else
        {
            if (k != 2 && k != 4 && k != 7)
            {
                break MISSING_BLOCK_LABEL_1781;
            }
            peekedNumberLength = l;
            i = 16;
            peeked = 16;
        }
          goto _L54
        i = 0;
          goto _L54
    }

    public final String getPath()
    {
        StringBuilder stringbuilder;
        int i;
        int j;
        stringbuilder = new StringBuilder("$");
        i = 0;
        j = stackSize;
_L2:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        switch (stack[i])
        {
        default:
            break;

        case 1: // '\001'
        case 2: // '\002'
            break; /* Loop/switch isn't completed */

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            break;
        }
        break MISSING_BLOCK_LABEL_95;
_L3:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        stringbuilder.append('[').append(pathIndices[i]).append(']');
          goto _L3
        stringbuilder.append('.');
        if (pathNames[i] != null)
        {
            stringbuilder.append(pathNames[i]);
        }
          goto _L3
        return stringbuilder.toString();
    }

    public final String locationString()
    {
        int i = lineNumber;
        int j = pos;
        int k = lineStart;
        return (new StringBuilder(" at line ")).append(i + 1).append(" column ").append((j - k) + 1).append(" path ").append(getPath()).toString();
    }

    public final String nextQuotedValue(char c)
        throws IOException
    {
        char ac[];
        StringBuilder stringbuilder;
        ac = buffer;
        stringbuilder = new StringBuilder();
_L16:
        int i;
        int j;
        int k;
        j = pos;
        k = limit;
        i = j;
_L14:
        int l;
        if (i >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        l = i + 1;
        i = ac[i];
        if (i == c)
        {
            pos = l;
            stringbuilder.append(ac, j, l - j - 1);
            return stringbuilder.toString();
        }
        if (i != 92) goto _L2; else goto _L1
_L1:
        char c1;
        char c2;
        pos = l;
        stringbuilder.append(ac, j, l - j - 1);
        if (pos == limit && !fillBuffer(1))
        {
            throw new MalformedJsonException((new StringBuilder()).append("Unterminated escape sequence").append(locationString()).toString());
        }
        char ac1[] = buffer;
        i = pos;
        pos = i + 1;
        c2 = ac1[i];
        c1 = c2;
        c2;
        JVM INSTR lookupswitch 11: default 292
    //                   10: 607
    //                   34: 551
    //                   39: 551
    //                   47: 551
    //                   92: 551
    //                   98: 583
    //                   102: 601
    //                   110: 589
    //                   114: 595
    //                   116: 577
    //                   117: 322;
           goto _L3 _L4 _L5 _L5 _L5 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L5:
        break; /* Loop/switch isn't completed */
_L3:
        throw new MalformedJsonException((new StringBuilder()).append("Invalid escape sequence").append(locationString()).toString());
_L11:
        if (pos + 4 > limit && !fillBuffer(4))
        {
            throw new MalformedJsonException((new StringBuilder()).append("Unterminated escape sequence").append(locationString()).toString());
        }
        j = pos;
        c1 = '\0';
        i = j;
        while (i < j + 4) 
        {
            k = buffer[i];
            l = (char)(c1 << 4);
            if (k >= '0' && k <= '9')
            {
                c1 = (char)(l + (k - 48));
            } else
            if (k >= 'a' && k <= 'f')
            {
                c1 = (char)(l + ((k - 97) + 10));
            } else
            if (k >= 'A' && k <= 'F')
            {
                c1 = (char)(l + ((k - 65) + 10));
            } else
            {
                throw new NumberFormatException((new StringBuilder("\\u")).append(new String(buffer, pos, 4)).toString());
            }
            i++;
        }
        pos = pos + 4;
_L12:
        stringbuilder.append(c1);
        j = pos;
        k = limit;
        i = j;
        continue; /* Loop/switch isn't completed */
_L10:
        c1 = '\t';
        continue; /* Loop/switch isn't completed */
_L6:
        c1 = '\b';
        continue; /* Loop/switch isn't completed */
_L8:
        c1 = '\n';
        continue; /* Loop/switch isn't completed */
_L9:
        c1 = '\r';
        continue; /* Loop/switch isn't completed */
_L7:
        c1 = '\f';
        continue; /* Loop/switch isn't completed */
_L4:
        lineNumber = lineNumber + 1;
        lineStart = pos;
        c1 = c2;
        if (true) goto _L12; else goto _L2
_L2:
        if (i == 10)
        {
            lineNumber = lineNumber + 1;
            lineStart = l;
        }
        i = l;
        if (true) goto _L14; else goto _L13
_L13:
        stringbuilder.append(ac, j, i - j);
        pos = i;
        if (!fillBuffer(1))
        {
            throw new MalformedJsonException((new StringBuilder()).append("Unterminated string").append(locationString()).toString());
        }
        if (true) goto _L16; else goto _L15
_L15:
    }

    public final String nextUnquotedValue()
        throws IOException
    {
        Object obj;
        int i;
        obj = null;
        i = 0;
_L2:
        Object obj1;
        int j;
        if (pos + i < limit)
        {
            obj1 = obj;
            j = i;
            switch (buffer[pos + i])
            {
            default:
                i++;
                continue; /* Loop/switch isn't completed */

            case 35: // '#'
            case 47: // '/'
            case 59: // ';'
            case 61: // '='
            case 92: // '\\'
                throw new MalformedJsonException((new StringBuilder()).append("Use JsonReader.setLenient(true) to accept malformed JSON").append(locationString()).toString());

            case 9: // '\t'
            case 10: // '\n'
            case 12: // '\f'
            case 13: // '\r'
            case 32: // ' '
            case 44: // ','
            case 58: // ':'
            case 91: // '['
            case 93: // ']'
            case 123: // '{'
            case 125: // '}'
                break;
            }
        } else
        if (i < buffer.length)
        {
            if (fillBuffer(i + 1))
            {
                continue; /* Loop/switch isn't completed */
            }
            j = i;
            obj1 = obj;
        } else
        {
label0:
            {
                obj1 = obj;
                if (obj == null)
                {
                    obj1 = new StringBuilder();
                }
                ((StringBuilder) (obj1)).append(buffer, pos, i);
                pos = i + pos;
                if (fillBuffer(1))
                {
                    break label0;
                }
                j = 0;
            }
        }
        if (obj1 == null)
        {
            obj = new String(buffer, pos, j);
        } else
        {
            ((StringBuilder) (obj1)).append(buffer, pos, j);
            obj = ((StringBuilder) (obj1)).toString();
        }
        pos = j + pos;
        return ((String) (obj));
        i = 0;
        obj = obj1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final JsonToken peek()
        throws IOException
    {
        int j = peeked;
        int i = j;
        if (j == 0)
        {
            i = doPeek();
        }
        switch (i)
        {
        default:
            throw new AssertionError();

        case 1: // '\001'
            return JsonToken.BEGIN_OBJECT;

        case 2: // '\002'
            return JsonToken.END_OBJECT;

        case 3: // '\003'
            return JsonToken.BEGIN_ARRAY;

        case 4: // '\004'
            return JsonToken.END_ARRAY;

        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
            return JsonToken.NAME;

        case 5: // '\005'
        case 6: // '\006'
            return JsonToken.BOOLEAN;

        case 7: // '\007'
            return JsonToken.NULL;

        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
            return JsonToken.STRING;

        case 15: // '\017'
        case 16: // '\020'
            return JsonToken.NUMBER;

        case 17: // '\021'
            return JsonToken.END_DOCUMENT;
        }
    }

    public final void push(int i)
    {
        if (stackSize == stack.length)
        {
            int ai[] = new int[stackSize << 1];
            int ai2[] = new int[stackSize << 1];
            String as[] = new String[stackSize << 1];
            System.arraycopy(stack, 0, ai, 0, stackSize);
            System.arraycopy(pathIndices, 0, ai2, 0, stackSize);
            System.arraycopy(pathNames, 0, as, 0, stackSize);
            stack = ai;
            pathIndices = ai2;
            pathNames = as;
        }
        int ai1[] = stack;
        int j = stackSize;
        stackSize = j + 1;
        ai1[j] = i;
    }

    public final String toString()
    {
        return (new StringBuilder()).append(getClass().getSimpleName()).append(locationString()).toString();
    }

    static 
    {
        new _cls1();
    }

    private class _cls1 extends JsonReaderInternalAccess
    {

        _cls1()
        {
        }
    }

}
