// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http.json;

import com.google.api.client.http.AbstractHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import java.io.IOException;
import java.io.OutputStream;

public final class JsonHttpContent extends AbstractHttpContent
{

    private final Object data;
    private final JsonFactory jsonFactory;
    public String wrapperKey;

    public JsonHttpContent(JsonFactory jsonfactory, Object obj)
    {
        super("application/json; charset=UTF-8");
        if (jsonfactory == null)
        {
            throw new NullPointerException();
        }
        jsonFactory = (JsonFactory)jsonfactory;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            data = obj;
            return;
        }
    }

    public final void writeTo(OutputStream outputstream)
        throws IOException
    {
        outputstream = jsonFactory.createJsonGenerator(outputstream, getCharset());
        if (wrapperKey != null)
        {
            outputstream.writeStartObject();
            outputstream.writeFieldName(wrapperKey);
        }
        outputstream.serialize(false, data);
        if (wrapperKey != null)
        {
            outputstream.writeEndObject();
        }
        outputstream.flush();
    }
}
