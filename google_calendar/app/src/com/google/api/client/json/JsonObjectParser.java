// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.json;

import com.google.api.client.util.ObjectParser;
import com.google.common.base.Strings;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.api.client.json:
//            JsonParser, JsonToken, JsonFactory

public final class JsonObjectParser
    implements ObjectParser
{

    public final JsonFactory jsonFactory;
    public final Set wrapperKeys;

    public JsonObjectParser(Builder builder)
    {
        jsonFactory = builder.jsonFactory;
        wrapperKeys = new HashSet(builder.wrapperKeys);
    }

    private final void initializeParser(JsonParser jsonparser)
        throws IOException
    {
        boolean flag = true;
        if (!wrapperKeys.isEmpty()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Set set;
        Exception exception;
        if (jsonparser.skipToKey(wrapperKeys) == null || jsonparser.getCurrentToken() == JsonToken.END_OBJECT)
        {
            flag = false;
        }
        set = wrapperKeys;
        if (flag) goto _L1; else goto _L3
_L3:
        throw new IllegalArgumentException(Strings.lenientFormat("wrapper key(s) not found: %s", new Object[] {
            set
        }));
        exception;
        jsonparser.close();
        throw exception;
    }

    public final Object parseAndClose(InputStream inputstream, Charset charset, Class class1)
        throws IOException
    {
        inputstream = jsonFactory.createJsonParser(inputstream, charset);
        initializeParser(inputstream);
        return inputstream.parse(class1, true, null);
    }

    private class Builder
    {

        public final JsonFactory jsonFactory;
        public Collection wrapperKeys;

        public Builder(JsonFactory jsonfactory)
        {
            wrapperKeys = new HashSet();
            if (jsonfactory == null)
            {
                throw new NullPointerException();
            } else
            {
                jsonFactory = (JsonFactory)jsonfactory;
                return;
            }
        }
    }

}
