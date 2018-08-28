// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.json;

import com.google.api.client.util.Charsets;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

// Referenced classes of package com.google.api.client.json:
//            JsonGenerator, JsonParser

public abstract class JsonFactory
{

    public JsonFactory()
    {
    }

    public abstract JsonGenerator createJsonGenerator(OutputStream outputstream, Charset charset)
        throws IOException;

    public abstract JsonParser createJsonParser(InputStream inputstream)
        throws IOException;

    public abstract JsonParser createJsonParser(InputStream inputstream, Charset charset)
        throws IOException;

    public abstract JsonParser createJsonParser(String s)
        throws IOException;

    public final String toString(Object obj)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        JsonGenerator jsongenerator = createJsonGenerator(bytearrayoutputstream, Charsets.UTF_8);
        jsongenerator.serialize(false, obj);
        jsongenerator.flush();
        return bytearrayoutputstream.toString("UTF-8");
    }
}
