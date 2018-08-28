// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.json;

import com.google.api.client.util.Charsets;
import com.google.api.client.util.GenericData;
import com.google.common.base.Throwables;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package com.google.api.client.json:
//            JsonFactory, JsonGenerator

public class GenericJson extends GenericData
    implements Cloneable
{

    public JsonFactory jsonFactory;

    public GenericJson()
    {
    }

    public GenericJson clone()
    {
        return (GenericJson)super.clone();
    }

    public volatile GenericData clone()
    {
        return (GenericJson)clone();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public GenericJson set(String s, Object obj)
    {
        return (GenericJson)super.set(s, obj);
    }

    public volatile GenericData set(String s, Object obj)
    {
        return set(s, obj);
    }

    public final String toPrettyString()
        throws IOException
    {
        if (jsonFactory != null)
        {
            Object obj = jsonFactory;
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            obj = ((JsonFactory) (obj)).createJsonGenerator(bytearrayoutputstream, Charsets.UTF_8);
            ((JsonGenerator) (obj)).enablePrettyPrint();
            ((JsonGenerator) (obj)).serialize(false, this);
            ((JsonGenerator) (obj)).flush();
            return bytearrayoutputstream.toString("UTF-8");
        } else
        {
            return super.toString();
        }
    }

    public String toString()
    {
        if (jsonFactory != null)
        {
            Object obj;
            try
            {
                Object obj1 = jsonFactory;
                obj = new ByteArrayOutputStream();
                obj1 = ((JsonFactory) (obj1)).createJsonGenerator(((java.io.OutputStream) (obj)), Charsets.UTF_8);
                ((JsonGenerator) (obj1)).serialize(false, this);
                ((JsonGenerator) (obj1)).flush();
                obj = ((ByteArrayOutputStream) (obj)).toString("UTF-8");
            }
            catch (IOException ioexception)
            {
                throw Throwables.propagate(ioexception);
            }
            return ((String) (obj));
        } else
        {
            return super.toString();
        }
    }
}
