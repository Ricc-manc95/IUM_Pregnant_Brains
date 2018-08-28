// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import com.google.api.client.util.escape.Escaper;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.api.client.http:
//            AbstractHttpContent, UrlEncodedParser

public final class UrlEncodedContent extends AbstractHttpContent
{

    private Object data;

    public UrlEncodedContent(Object obj)
    {
        super(UrlEncodedParser.MEDIA_TYPE);
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            data = obj;
            return;
        }
    }

    private static boolean appendParam(boolean flag, Writer writer, String s, Object obj)
        throws IOException
    {
        boolean flag1 = flag;
        if (obj != null)
        {
            if (Data.isNull(obj))
            {
                flag1 = flag;
            } else
            {
                if (flag)
                {
                    flag = false;
                } else
                {
                    writer.write("&");
                }
                writer.write(s);
                if (obj instanceof Enum)
                {
                    s = FieldInfo.of((Enum)obj).name;
                } else
                {
                    s = obj.toString();
                }
                s = CharEscapers.URI_ESCAPER.escape(s);
                flag1 = flag;
                if (s.length() != 0)
                {
                    writer.write("=");
                    writer.write(s);
                    return flag;
                }
            }
        }
        return flag1;
    }

    public final void writeTo(OutputStream outputstream)
        throws IOException
    {
        outputstream = new BufferedWriter(new OutputStreamWriter(outputstream, getCharset()));
        Iterator iterator = Data.mapOf(data).entrySet().iterator();
        boolean flag = true;
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj1 = (java.util.Map.Entry)iterator.next();
            Object obj = ((java.util.Map.Entry) (obj1)).getValue();
            if (obj != null)
            {
                obj1 = (String)((java.util.Map.Entry) (obj1)).getKey();
                obj1 = CharEscapers.URI_ESCAPER.escape(((String) (obj1)));
                Class class1 = obj.getClass();
                if ((obj instanceof Iterable) || class1.isArray())
                {
                    obj = Types.iterableOf(obj).iterator();
                    while (((Iterator) (obj)).hasNext()) 
                    {
                        flag = appendParam(flag, outputstream, ((String) (obj1)), ((Iterator) (obj)).next());
                    }
                } else
                {
                    flag = appendParam(flag, outputstream, ((String) (obj1)), obj);
                }
            }
        } while (true);
        outputstream.flush();
    }
}
