// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class JsonParser
{

    private static final Logger logger = Logger.getLogger(io/grpc/internal/JsonParser.getName());

    private JsonParser()
    {
    }

    public static Object parse(String s)
        throws IOException
    {
        s = new JsonReader(new StringReader(s));
        Object obj = parseRecursive(s);
        try
        {
            s.close();
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            logger.logp(Level.WARNING, "io.grpc.internal.JsonParser", "parse", "Failed to close", s);
            return obj;
        }
        return obj;
        Exception exception;
        exception;
        try
        {
            s.close();
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            logger.logp(Level.WARNING, "io.grpc.internal.JsonParser", "parse", "Failed to close", s);
        }
        throw exception;
    }

    private static Object parseRecursive(JsonReader jsonreader)
        throws IOException
    {
        int k;
        int l;
        boolean flag2;
        k = 1;
        flag2 = true;
        l = 1;
        int i1 = jsonreader.peeked;
        int i = i1;
        if (i1 == 0)
        {
            i = jsonreader.doPeek();
        }
        boolean flag;
        if (i != 2 && i != 4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("unexpected end of JSON"));
        }
        jsonreader.peek().ordinal();
        JVM INSTR tableswitch 0 8: default 128
    //                   0 159
    //                   1 128
    //                   2 499
    //                   3 128
    //                   4 128
    //                   5 961
    //                   6 1183
    //                   7 1495
    //                   8 1637;
           goto _L1 _L2 _L1 _L3 _L1 _L1 _L4 _L5 _L6 _L7
_L1:
        jsonreader = String.valueOf(jsonreader.getPath());
        double d;
        char c;
        String s;
        int ai[];
        Object obj;
        int ai1[];
        int j;
        boolean flag1;
        if (jsonreader.length() != 0)
        {
            jsonreader = "Bad token: ".concat(jsonreader);
        } else
        {
            jsonreader = new String("Bad token: ");
        }
        throw new IllegalStateException(jsonreader);
_L2:
        k = jsonreader.peeked;
        j = k;
        if (k == 0)
        {
            j = jsonreader.doPeek();
        }
        if (j == 3)
        {
            jsonreader.push(1);
            jsonreader.pathIndices[jsonreader.stackSize - 1] = 0;
            jsonreader.peeked = 0;
            obj = new ArrayList();
            do
            {
                k = jsonreader.peeked;
                j = k;
                if (k == 0)
                {
                    j = jsonreader.doPeek();
                }
                if (j != 2 && j != 4)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    ((List) (obj)).add(parseRecursive(jsonreader));
                } else
                {
                    if (jsonreader.peek() == JsonToken.END_ARRAY)
                    {
                        flag1 = l;
                    } else
                    {
                        flag1 = false;
                    }
                    s = String.valueOf(jsonreader.getPath());
                    if (s.length() != 0)
                    {
                        s = "Bad token: ".concat(s);
                    } else
                    {
                        s = new String("Bad token: ");
                    }
                    if (!flag1)
                    {
                        throw new IllegalStateException(String.valueOf(s));
                    }
                    k = jsonreader.peeked;
                    flag1 = k;
                    if (k == 0)
                    {
                        flag1 = jsonreader.doPeek();
                    }
                    if (flag1 == 4)
                    {
                        jsonreader.stackSize = jsonreader.stackSize - 1;
                        ai = jsonreader.pathIndices;
                        flag1 = jsonreader.stackSize - 1;
                        ai[flag1] = ai[flag1] + 1;
                        jsonreader.peeked = 0;
                        return Collections.unmodifiableList(((List) (obj)));
                    } else
                    {
                        throw new IllegalStateException((new StringBuilder("Expected END_ARRAY but was ")).append(jsonreader.peek()).append(jsonreader.locationString()).toString());
                    }
                }
            } while (true);
        } else
        {
            throw new IllegalStateException((new StringBuilder("Expected BEGIN_ARRAY but was ")).append(jsonreader.peek()).append(jsonreader.locationString()).toString());
        }
_L3:
        l = jsonreader.peeked;
        flag1 = l;
        if (l == 0)
        {
            flag1 = jsonreader.doPeek();
        }
        if (flag1 == 1)
        {
            jsonreader.push(3);
            jsonreader.peeked = 0;
            obj = new LinkedHashMap();
            do
            {
                l = jsonreader.peeked;
                flag1 = l;
                if (l == 0)
                {
                    flag1 = jsonreader.doPeek();
                }
                if (flag1 != 2 && flag1 != 4)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1 == 0)
                {
                    break;
                }
                l = jsonreader.peeked;
                flag1 = l;
                if (l == 0)
                {
                    flag1 = jsonreader.doPeek();
                }
                if (flag1 == 14)
                {
                    ai = jsonreader.nextUnquotedValue();
                } else
                if (flag1 == 12)
                {
                    ai = jsonreader.nextQuotedValue('\'');
                } else
                if (flag1 == 13)
                {
                    ai = jsonreader.nextQuotedValue('"');
                } else
                {
                    throw new IllegalStateException((new StringBuilder("Expected a name but was ")).append(jsonreader.peek()).append(jsonreader.locationString()).toString());
                }
                jsonreader.peeked = 0;
                jsonreader.pathNames[jsonreader.stackSize - 1] = ai;
                ((Map) (obj)).put(ai, parseRecursive(jsonreader));
            } while (true);
        } else
        {
            throw new IllegalStateException((new StringBuilder("Expected BEGIN_OBJECT but was ")).append(jsonreader.peek()).append(jsonreader.locationString()).toString());
        }
        if (jsonreader.peek() == JsonToken.END_OBJECT)
        {
            flag1 = k;
        } else
        {
            flag1 = false;
        }
        ai = String.valueOf(jsonreader.getPath());
        if (ai.length() != 0)
        {
            ai = "Bad token: ".concat(ai);
        } else
        {
            ai = new String("Bad token: ");
        }
        if (flag1 == 0)
        {
            throw new IllegalStateException(String.valueOf(ai));
        }
        k = jsonreader.peeked;
        flag1 = k;
        if (k == 0)
        {
            flag1 = jsonreader.doPeek();
        }
        if (flag1 == 2)
        {
            jsonreader.stackSize = jsonreader.stackSize - 1;
            jsonreader.pathNames[jsonreader.stackSize] = null;
            ai = jsonreader.pathIndices;
            flag1 = jsonreader.stackSize - 1;
            ai[flag1] = ai[flag1] + 1;
            jsonreader.peeked = 0;
            return Collections.unmodifiableMap(((Map) (obj)));
        } else
        {
            throw new IllegalStateException((new StringBuilder("Expected END_OBJECT but was ")).append(jsonreader.peek()).append(jsonreader.locationString()).toString());
        }
_L4:
        k = jsonreader.peeked;
        flag1 = k;
        if (k == 0)
        {
            flag1 = jsonreader.doPeek();
        }
        if (flag1 == 10)
        {
            ai = jsonreader.nextUnquotedValue();
        } else
        if (flag1 == 8)
        {
            ai = jsonreader.nextQuotedValue('\'');
        } else
        if (flag1 == 9)
        {
            ai = jsonreader.nextQuotedValue('"');
        } else
        if (flag1 == 11)
        {
            ai = jsonreader.peekedString;
            jsonreader.peekedString = null;
        } else
        if (flag1 == 15)
        {
            ai = Long.toString(jsonreader.peekedLong);
        } else
        if (flag1 == 16)
        {
            ai = new String(jsonreader.buffer, jsonreader.pos, jsonreader.peekedNumberLength);
            jsonreader.pos = jsonreader.pos + jsonreader.peekedNumberLength;
        } else
        {
            throw new IllegalStateException((new StringBuilder("Expected a string but was ")).append(jsonreader.peek()).append(jsonreader.locationString()).toString());
        }
        jsonreader.peeked = 0;
        ai1 = jsonreader.pathIndices;
        flag1 = jsonreader.stackSize - 1;
        ai1[flag1] = ai1[flag1] + 1;
        return ai;
_L5:
        k = jsonreader.peeked;
        flag1 = k;
        if (k == 0)
        {
            flag1 = jsonreader.doPeek();
        }
        if (flag1 != 15) goto _L9; else goto _L8
_L8:
        jsonreader.peeked = 0;
        ai = jsonreader.pathIndices;
        flag1 = jsonreader.stackSize - 1;
        ai[flag1] = ai[flag1] + 1;
        d = jsonreader.peekedLong;
_L12:
        return Double.valueOf(d);
_L9:
        if (flag1 == 16)
        {
            jsonreader.peekedString = new String(jsonreader.buffer, jsonreader.pos, jsonreader.peekedNumberLength);
            jsonreader.pos = jsonreader.pos + jsonreader.peekedNumberLength;
        } else
        if (flag1 == 8 || flag1 == 9)
        {
            if (flag1 == 8)
            {
                c = '\'';
            } else
            {
                c = '"';
            }
            jsonreader.peekedString = jsonreader.nextQuotedValue(c);
        } else
        {
            if (flag1 != 10)
            {
                continue; /* Loop/switch isn't completed */
            }
            jsonreader.peekedString = jsonreader.nextUnquotedValue();
        }
          goto _L10
        if (flag1 == 11) goto _L10; else goto _L11
_L11:
        throw new IllegalStateException((new StringBuilder("Expected a double but was ")).append(jsonreader.peek()).append(jsonreader.locationString()).toString());
_L10:
        jsonreader.peeked = 11;
        d = Double.parseDouble(jsonreader.peekedString);
        if (Double.isNaN(d) || Double.isInfinite(d))
        {
            throw new MalformedJsonException((new StringBuilder("JSON forbids NaN and infinities: ")).append(d).append(jsonreader.locationString()).toString());
        }
        jsonreader.peekedString = null;
        jsonreader.peeked = 0;
        ai = jsonreader.pathIndices;
        flag1 = jsonreader.stackSize - 1;
        ai[flag1] = ai[flag1] + 1;
          goto _L12
_L6:
        k = jsonreader.peeked;
        flag1 = k;
        if (k == 0)
        {
            flag1 = jsonreader.doPeek();
        }
        if (flag1 == 5)
        {
            jsonreader.peeked = 0;
            ai = jsonreader.pathIndices;
            flag1 = jsonreader.stackSize - 1;
            ai[flag1] = ai[flag1] + 1;
        } else
        if (flag1 == 6)
        {
            jsonreader.peeked = 0;
            ai = jsonreader.pathIndices;
            flag1 = jsonreader.stackSize - 1;
            ai[flag1] = ai[flag1] + 1;
            flag2 = false;
        } else
        {
            throw new IllegalStateException((new StringBuilder("Expected a boolean but was ")).append(jsonreader.peek()).append(jsonreader.locationString()).toString());
        }
        return Boolean.valueOf(flag2);
_L7:
        k = jsonreader.peeked;
        flag1 = k;
        if (k == 0)
        {
            flag1 = jsonreader.doPeek();
        }
        if (flag1 == 7)
        {
            jsonreader.peeked = 0;
            ai = jsonreader.pathIndices;
            flag1 = jsonreader.stackSize - 1;
            ai[flag1] = ai[flag1] + 1;
            return null;
        } else
        {
            throw new IllegalStateException((new StringBuilder("Expected null but was ")).append(jsonreader.peek()).append(jsonreader.locationString()).toString());
        }
    }

}
