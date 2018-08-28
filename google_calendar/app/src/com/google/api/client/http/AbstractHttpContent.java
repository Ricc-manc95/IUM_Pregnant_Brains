// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedMap;

// Referenced classes of package com.google.api.client.http:
//            HttpContent, HttpMediaType

public abstract class AbstractHttpContent
    implements HttpContent
{

    private long computedLength;
    public HttpMediaType mediaType;

    protected AbstractHttpContent(HttpMediaType httpmediatype)
    {
        computedLength = -1L;
        mediaType = httpmediatype;
    }

    public AbstractHttpContent(String s)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            s = new HttpMediaType(s);
        }
        this(((HttpMediaType) (s)));
    }

    public final Charset getCharset()
    {
label0:
        {
            if (mediaType != null)
            {
                Object obj = (String)mediaType.parameters.get("charset".toLowerCase());
                if (obj == null)
                {
                    obj = null;
                } else
                {
                    obj = Charset.forName(((String) (obj)));
                }
                if (obj != null)
                {
                    break label0;
                }
            }
            return Charsets.UTF_8;
        }
        String s = (String)mediaType.parameters.get("charset".toLowerCase());
        if (s == null)
        {
            return null;
        } else
        {
            return Charset.forName(s);
        }
    }

    public final long getLength()
        throws IOException
    {
        long l = -1L;
        if (computedLength == -1L)
        {
            if (retrySupported())
            {
                l = IOUtils.computeLength(this);
            }
            computedLength = l;
        }
        return computedLength;
    }

    public final String getType()
    {
        if (mediaType == null)
        {
            return null;
        } else
        {
            return mediaType.build();
        }
    }

    public boolean retrySupported()
    {
        return true;
    }
}
