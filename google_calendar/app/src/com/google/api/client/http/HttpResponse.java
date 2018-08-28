// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.IOUtils;
import com.google.api.client.util.LoggingInputStream;
import com.google.api.client.util.StringUtils;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

// Referenced classes of package com.google.api.client.http:
//            HttpRequest, LowLevelHttpResponse, HttpTransport, HttpHeaders, 
//            HttpMediaType

public final class HttpResponse
{

    private InputStream content;
    private final String contentEncoding;
    private int contentLoggingLimit;
    private boolean contentRead;
    public final String contentType;
    private boolean loggingEnabled;
    public final HttpMediaType mediaType;
    public final HttpRequest request;
    public LowLevelHttpResponse response;
    public final int statusCode;
    public final String statusMessage;

    HttpResponse(HttpRequest httprequest, LowLevelHttpResponse lowlevelhttpresponse)
        throws IOException
    {
        Object obj1 = null;
        super();
        request = httprequest;
        contentLoggingLimit = httprequest.contentLoggingLimit;
        loggingEnabled = httprequest.loggingEnabled;
        response = lowlevelhttpresponse;
        contentEncoding = lowlevelhttpresponse.getContentEncoding();
        int j = lowlevelhttpresponse.getStatusCode();
        int i = j;
        if (j < 0)
        {
            i = 0;
        }
        statusCode = i;
        Object obj = lowlevelhttpresponse.getReasonPhrase();
        statusMessage = ((String) (obj));
        Logger logger = HttpTransport.LOGGER;
        StringBuilder stringbuilder;
        boolean flag;
        if (loggingEnabled && logger.isLoggable(Level.CONFIG))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            stringbuilder = new StringBuilder();
            stringbuilder.append("-------------- RESPONSE --------------").append(StringUtils.LINE_SEPARATOR);
            Object obj2 = lowlevelhttpresponse.getStatusLine();
            int k;
            int l;
            if (obj2 != null)
            {
                stringbuilder.append(((String) (obj2)));
            } else
            {
                stringbuilder.append(statusCode);
                if (obj != null)
                {
                    stringbuilder.append(' ').append(((String) (obj)));
                }
            }
            stringbuilder.append(StringUtils.LINE_SEPARATOR);
        } else
        {
            stringbuilder = null;
        }
        obj2 = httprequest.responseHeaders;
        if (flag)
        {
            obj = stringbuilder;
        } else
        {
            obj = null;
        }
        ((HttpHeaders) (obj2)).clear();
        obj = new HttpHeaders.ParseHeaderState(((HttpHeaders) (obj2)), ((StringBuilder) (obj)));
        l = lowlevelhttpresponse.getHeaderCount();
        for (k = 0; k < l; k++)
        {
            ((HttpHeaders) (obj2)).parseHeader(lowlevelhttpresponse.getHeaderName(k), lowlevelhttpresponse.getHeaderValue(k), ((HttpHeaders.ParseHeaderState) (obj)));
        }

        ((HttpHeaders.ParseHeaderState) (obj)).arrayValueMap.setValues();
        obj = lowlevelhttpresponse.getContentType();
        lowlevelhttpresponse = ((LowLevelHttpResponse) (obj));
        if (obj == null)
        {
            httprequest = httprequest.responseHeaders.contentType;
            if (httprequest == null)
            {
                httprequest = null;
            } else
            {
                httprequest = ((HttpRequest) (httprequest.get(0)));
            }
            lowlevelhttpresponse = (String)httprequest;
        }
        contentType = lowlevelhttpresponse;
        if (lowlevelhttpresponse == null)
        {
            httprequest = obj1;
        } else
        {
            httprequest = new HttpMediaType(lowlevelhttpresponse);
        }
        mediaType = httprequest;
        if (flag)
        {
            logger.logp(Level.CONFIG, "com.google.api.client.http.HttpResponse", "<init>", stringbuilder.toString());
        }
    }

    public final InputStream getContent()
        throws IOException
    {
        Object obj1;
        Object obj2;
        if (contentRead)
        {
            break MISSING_BLOCK_LABEL_139;
        }
        obj1 = response.getContent();
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_134;
        }
        obj2 = obj1;
        Object obj3 = contentEncoding;
        Object obj;
        obj = obj1;
        if (obj3 == null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        obj = obj1;
        obj2 = obj1;
        if (!((String) (obj3)).contains("gzip"))
        {
            break MISSING_BLOCK_LABEL_59;
        }
        obj2 = obj1;
        obj = new GZIPInputStream(((InputStream) (obj1)));
        obj2 = obj;
        obj3 = obj;
        Logger logger = HttpTransport.LOGGER;
        obj1 = obj;
        obj2 = obj;
        obj3 = obj;
        if (!loggingEnabled)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        obj1 = obj;
        obj2 = obj;
        obj3 = obj;
        if (!logger.isLoggable(Level.CONFIG))
        {
            break MISSING_BLOCK_LABEL_124;
        }
        obj2 = obj;
        obj3 = obj;
        obj1 = new LoggingInputStream(((InputStream) (obj)), logger, Level.CONFIG, contentLoggingLimit);
        obj2 = obj1;
        obj3 = obj1;
        Exception exception;
        try
        {
            content = ((InputStream) (obj1));
        }
        catch (EOFException eofexception)
        {
            ((InputStream) (obj2)).close();
        }
        finally
        {
            obj1 = obj3;
        }
        contentRead = true;
        return content;
        exception;
        ((InputStream) (obj1)).close();
        throw exception;
    }

    public final String parseAsString()
        throws IOException
    {
        Object obj = getContent();
        if (obj == null)
        {
            return "";
        }
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        IOUtils.copy(((InputStream) (obj)), bytearrayoutputstream, true);
        if (mediaType == null || mediaType.getCharsetParameter() == null)
        {
            obj = Charsets.ISO_8859_1;
        } else
        {
            obj = mediaType.getCharsetParameter();
        }
        return bytearrayoutputstream.toString(((Charset) (obj)).name());
    }
}
