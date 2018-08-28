// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.extensions.android.json;

import android.util.JsonReader;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.api.client.extensions.android.json:
//            AndroidJsonFactory

final class AndroidJsonParser extends JsonParser
{

    private List currentNameStack;
    private String currentText;
    private JsonToken currentToken;
    private final AndroidJsonFactory factory;
    private final JsonReader reader;

    AndroidJsonParser(AndroidJsonFactory androidjsonfactory, JsonReader jsonreader)
    {
        currentNameStack = new ArrayList();
        factory = androidjsonfactory;
        reader = jsonreader;
        jsonreader.setLenient(true);
    }

    public final void close()
        throws IOException
    {
        reader.close();
    }

    public final BigInteger getBigIntegerValue()
    {
        boolean flag;
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return new BigInteger(currentText);
        }
    }

    public final byte getByteValue()
    {
        boolean flag;
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return Byte.parseByte(currentText);
        }
    }

    public final String getCurrentName()
    {
        if (currentNameStack.isEmpty())
        {
            return null;
        } else
        {
            return (String)currentNameStack.get(currentNameStack.size() - 1);
        }
    }

    public final JsonToken getCurrentToken()
    {
        return currentToken;
    }

    public final BigDecimal getDecimalValue()
    {
        boolean flag;
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return new BigDecimal(currentText);
        }
    }

    public final double getDoubleValue()
    {
        boolean flag;
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return Double.parseDouble(currentText);
        }
    }

    public final JsonFactory getFactory()
    {
        return factory;
    }

    public final float getFloatValue()
    {
        boolean flag;
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return Float.parseFloat(currentText);
        }
    }

    public final int getIntValue()
    {
        boolean flag;
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return Integer.parseInt(currentText);
        }
    }

    public final long getLongValue()
    {
        boolean flag;
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return Long.parseLong(currentText);
        }
    }

    public final short getShortValue()
    {
        boolean flag;
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return Short.parseShort(currentText);
        }
    }

    public final String getText()
    {
        return currentText;
    }

    public final JsonToken nextToken()
        throws IOException
    {
        if (currentToken == null) goto _L2; else goto _L1
_L1:
        currentToken.ordinal();
        JVM INSTR tableswitch 0 2: default 40
    //                   0 123
    //                   1 40
    //                   2 144;
           goto _L2 _L3 _L2 _L4
_L2:
        android.util.JsonToken jsontoken;
        try
        {
            jsontoken = reader.peek();
        }
        catch (EOFException eofexception)
        {
            eofexception = android.util.JsonToken.END_DOCUMENT;
        }
        _cls1..SwitchMap.android.util.JsonToken[jsontoken.ordinal()];
        JVM INSTR tableswitch 1 9: default 108
    //                   1 173
    //                   2 189
    //                   3 233
    //                   4 249
    //                   5 293
    //                   6 335
    //                   7 358
    //                   8 379
    //                   9 422;
           goto _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L14:
        break MISSING_BLOCK_LABEL_422;
_L5:
        currentText = null;
        currentToken = null;
_L15:
        return currentToken;
_L3:
        reader.beginArray();
        currentNameStack.add(null);
          goto _L2
_L4:
        reader.beginObject();
        currentNameStack.add(null);
          goto _L2
_L6:
        currentText = "[";
        currentToken = JsonToken.START_ARRAY;
          goto _L15
_L7:
        currentText = "]";
        currentToken = JsonToken.END_ARRAY;
        currentNameStack.remove(currentNameStack.size() - 1);
        reader.endArray();
          goto _L15
_L8:
        currentText = "{";
        currentToken = JsonToken.START_OBJECT;
          goto _L15
_L9:
        currentText = "}";
        currentToken = JsonToken.END_OBJECT;
        currentNameStack.remove(currentNameStack.size() - 1);
        reader.endObject();
          goto _L15
_L10:
        if (reader.nextBoolean())
        {
            currentText = "true";
            currentToken = JsonToken.VALUE_TRUE;
        } else
        {
            currentText = "false";
            currentToken = JsonToken.VALUE_FALSE;
        }
          goto _L15
_L11:
        currentText = "null";
        currentToken = JsonToken.VALUE_NULL;
        reader.nextNull();
          goto _L15
_L12:
        currentText = reader.nextString();
        currentToken = JsonToken.VALUE_STRING;
          goto _L15
_L13:
        currentText = reader.nextString();
        JsonToken jsontoken1;
        if (currentText.indexOf('.') == -1)
        {
            jsontoken1 = JsonToken.VALUE_NUMBER_INT;
        } else
        {
            jsontoken1 = JsonToken.VALUE_NUMBER_FLOAT;
        }
        currentToken = jsontoken1;
          goto _L15
        currentText = reader.nextName();
        currentToken = JsonToken.FIELD_NAME;
        currentNameStack.set(currentNameStack.size() - 1, currentText);
          goto _L15
    }

    public final JsonParser skipChildren()
        throws IOException
    {
        if (currentToken == null) goto _L2; else goto _L1
_L1:
        currentToken.ordinal();
        JVM INSTR tableswitch 0 2: default 40
    //                   0 42
    //                   1 40
    //                   2 64;
           goto _L2 _L3 _L2 _L4
_L2:
        return this;
_L3:
        reader.skipValue();
        currentText = "]";
        currentToken = JsonToken.END_ARRAY;
        return this;
_L4:
        reader.skipValue();
        currentText = "}";
        currentToken = JsonToken.END_OBJECT;
        return this;
    }

    private class _cls1
    {

        public static final int $SwitchMap$android$util$JsonToken[];
        public static final int $SwitchMap$com$google$api$client$json$JsonToken[];

        static 
        {
            $SwitchMap$android$util$JsonToken = new int[android.util.JsonToken.values().length];
            try
            {
                $SwitchMap$android$util$JsonToken[android.util.JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror10) { }
            try
            {
                $SwitchMap$android$util$JsonToken[android.util.JsonToken.END_ARRAY.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror9) { }
            try
            {
                $SwitchMap$android$util$JsonToken[android.util.JsonToken.BEGIN_OBJECT.ordinal()] = 3;
            }
            catch (NoSuchFieldError nosuchfielderror8) { }
            try
            {
                $SwitchMap$android$util$JsonToken[android.util.JsonToken.END_OBJECT.ordinal()] = 4;
            }
            catch (NoSuchFieldError nosuchfielderror7) { }
            try
            {
                $SwitchMap$android$util$JsonToken[android.util.JsonToken.BOOLEAN.ordinal()] = 5;
            }
            catch (NoSuchFieldError nosuchfielderror6) { }
            try
            {
                $SwitchMap$android$util$JsonToken[android.util.JsonToken.NULL.ordinal()] = 6;
            }
            catch (NoSuchFieldError nosuchfielderror5) { }
            try
            {
                $SwitchMap$android$util$JsonToken[android.util.JsonToken.STRING.ordinal()] = 7;
            }
            catch (NoSuchFieldError nosuchfielderror4) { }
            try
            {
                $SwitchMap$android$util$JsonToken[android.util.JsonToken.NUMBER.ordinal()] = 8;
            }
            catch (NoSuchFieldError nosuchfielderror3) { }
            try
            {
                $SwitchMap$android$util$JsonToken[android.util.JsonToken.NAME.ordinal()] = 9;
            }
            catch (NoSuchFieldError nosuchfielderror2) { }
            $SwitchMap$com$google$api$client$json$JsonToken = new int[JsonToken.values().length];
            try
            {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.START_ARRAY.ordinal()] = 1;
            }
            catch (NoSuchFieldError nosuchfielderror1) { }
            try
            {
                $SwitchMap$com$google$api$client$json$JsonToken[JsonToken.START_OBJECT.ordinal()] = 2;
            }
            catch (NoSuchFieldError nosuchfielderror)
            {
                return;
            }
        }
    }

}
