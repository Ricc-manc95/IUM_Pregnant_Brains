// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.extensions.android.json;

import android.util.JsonWriter;
import com.google.api.client.json.JsonGenerator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package com.google.api.client.extensions.android.json:
//            AndroidJsonFactory

final class AndroidJsonGenerator extends JsonGenerator
{

    private final JsonWriter writer;

    AndroidJsonGenerator(AndroidJsonFactory androidjsonfactory, JsonWriter jsonwriter)
    {
        writer = jsonwriter;
        jsonwriter.setLenient(true);
    }

    public final void enablePrettyPrint()
        throws IOException
    {
        writer.setIndent("  ");
    }

    public final void flush()
        throws IOException
    {
        writer.flush();
    }

    public final void writeBoolean(boolean flag)
        throws IOException
    {
        writer.value(flag);
    }

    public final void writeEndArray()
        throws IOException
    {
        writer.endArray();
    }

    public final void writeEndObject()
        throws IOException
    {
        writer.endObject();
    }

    public final void writeFieldName(String s)
        throws IOException
    {
        writer.name(s);
    }

    public final void writeNull()
        throws IOException
    {
        writer.nullValue();
    }

    public final void writeNumber(double d)
        throws IOException
    {
        writer.value(d);
    }

    public final void writeNumber(float f)
        throws IOException
    {
        writer.value(f);
    }

    public final void writeNumber(int i)
        throws IOException
    {
        writer.value(i);
    }

    public final void writeNumber(long l)
        throws IOException
    {
        writer.value(l);
    }

    public final void writeNumber(BigDecimal bigdecimal)
        throws IOException
    {
        writer.value(bigdecimal);
    }

    public final void writeNumber(BigInteger biginteger)
        throws IOException
    {
        writer.value(biginteger);
    }

    public final void writeStartArray()
        throws IOException
    {
        writer.beginArray();
    }

    public final void writeStartObject()
        throws IOException
    {
        writer.beginObject();
    }

    public final void writeString(String s)
        throws IOException
    {
        writer.value(s);
    }
}
