// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.LoggingStreamingContent;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Sleeper;
import com.google.api.client.util.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.api.client.http:
//            HttpHeaders, HttpMediaType, HttpResponse, HttpExecuteInterceptor, 
//            GenericUrl, HttpTransport, HttpContent, LowLevelHttpRequest, 
//            HttpUnsuccessfulResponseHandler, LowLevelHttpResponse, HttpEncoding, HttpEncodingStreamingContent, 
//            HttpIOExceptionHandler, HttpResponseInterceptor, HttpResponseException

public final class HttpRequest
{

    private int connectTimeout;
    public HttpContent content;
    public int contentLoggingLimit;
    private boolean curlLoggingEnabled;
    public HttpEncoding encoding;
    public HttpExecuteInterceptor executeInterceptor;
    private boolean followRedirects;
    public HttpHeaders headers;
    public HttpIOExceptionHandler ioExceptionHandler;
    public boolean loggingEnabled;
    public int numRetries;
    public ObjectParser objectParser;
    private int readTimeout;
    public String requestMethod;
    public HttpHeaders responseHeaders;
    public HttpResponseInterceptor responseInterceptor;
    private boolean retryOnExecuteIOException;
    private Sleeper sleeper;
    public boolean throwExceptionOnExecuteError;
    public final HttpTransport transport;
    public HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler;
    public GenericUrl url;

    HttpRequest(HttpTransport httptransport, String s)
    {
        boolean flag = false;
        super();
        headers = new HttpHeaders();
        responseHeaders = new HttpHeaders();
        numRetries = 10;
        contentLoggingLimit = 16384;
        loggingEnabled = true;
        curlLoggingEnabled = true;
        connectTimeout = 20000;
        readTimeout = 20000;
        followRedirects = true;
        throwExceptionOnExecuteError = true;
        retryOnExecuteIOException = false;
        sleeper = Sleeper.DEFAULT;
        transport = httptransport;
        if (true || HttpMediaType.TOKEN_REGEX.matcher(null).matches())
        {
            flag = true;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            requestMethod = null;
            return;
        }
    }

    public final HttpResponse execute()
        throws IOException
    {
        Object obj;
        int i;
        boolean flag;
        if (numRetries >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        i = numRetries;
        obj = null;
        if (requestMethod == null)
        {
            throw new NullPointerException();
        }
        if (url == null)
        {
            throw new NullPointerException();
        }
          goto _L1
_L32:
        i--;
_L1:
        Object obj1;
        Object obj2;
        Logger logger;
        int k;
        int l;
        boolean flag2;
        boolean flag3;
        if (obj != null)
        {
            obj = ((HttpResponse) (obj)).getContent();
            if (obj != null)
            {
                ((InputStream) (obj)).close();
            }
        }
        Object obj3 = null;
        if (executeInterceptor != null)
        {
            executeInterceptor.intercept(this);
        }
        String s1 = url.build();
        LowLevelHttpRequest lowlevelhttprequest = transport.buildRequest(requestMethod, s1);
        logger = HttpTransport.LOGGER;
        StringBuilder stringbuilder1;
        if (loggingEnabled && logger.isLoggable(Level.CONFIG))
        {
            k = 1;
        } else
        {
            k = 0;
        }
        obj2 = null;
        obj = null;
        stringbuilder1 = ((StringBuilder) (obj));
        if (k != 0)
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("-------------- REQUEST  --------------").append(StringUtils.LINE_SEPARATOR);
            stringbuilder.append(requestMethod).append(' ').append(s1).append(StringUtils.LINE_SEPARATOR);
            obj2 = stringbuilder;
            stringbuilder1 = ((StringBuilder) (obj));
            if (curlLoggingEnabled)
            {
                obj = new StringBuilder("curl -v --compressed");
                obj2 = stringbuilder;
                stringbuilder1 = ((StringBuilder) (obj));
                if (!requestMethod.equals("GET"))
                {
                    ((StringBuilder) (obj)).append(" -X ").append(requestMethod);
                    stringbuilder1 = ((StringBuilder) (obj));
                    obj2 = stringbuilder;
                }
            }
        }
        obj = headers.userAgent;
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((List) (obj)).get(0);
        }
        obj = (String)obj;
        if (obj == null)
        {
            headers.setUserAgent("Google-HTTP-Java-Client/1.24.0-SNAPSHOT (gzip)");
        } else
        {
            headers.setUserAgent((new StringBuilder(String.valueOf(obj).length() + 47)).append(((String) (obj))).append(" Google-HTTP-Java-Client/1.24.0-SNAPSHOT (gzip)").toString());
        }
        HttpHeaders.serializeHeaders(headers, ((StringBuilder) (obj2)), stringbuilder1, logger, lowlevelhttprequest, null);
        headers.setUserAgent(((String) (obj)));
        obj = content;
        if (obj == null || content.retrySupported())
        {
            l = 1;
        } else
        {
            l = 0;
        }
        obj1 = obj;
        if (obj != null)
        {
            String s2 = content.getType();
            if (k != 0)
            {
                obj = new LoggingStreamingContent(((com.google.api.client.util.StreamingContent) (obj)), HttpTransport.LOGGER, Level.CONFIG, contentLoggingLimit);
            }
            long l1;
            if (encoding == null)
            {
                l1 = content.getLength();
                obj1 = null;
            } else
            {
                obj1 = encoding.getName();
                obj = new HttpEncodingStreamingContent(((com.google.api.client.util.StreamingContent) (obj)), encoding);
                if (l != 0)
                {
                    l1 = IOUtils.computeLength(((com.google.api.client.util.StreamingContent) (obj)));
                } else
                {
                    l1 = -1L;
                }
            }
            if (k != 0)
            {
                String s;
                if (s2 != null)
                {
                    s = String.valueOf(s2);
                    if (s.length() != 0)
                    {
                        s = "Content-Type: ".concat(s);
                    } else
                    {
                        s = new String("Content-Type: ");
                    }
                    ((StringBuilder) (obj2)).append(s).append(StringUtils.LINE_SEPARATOR);
                    if (stringbuilder1 != null)
                    {
                        stringbuilder1.append((new StringBuilder(String.valueOf(s).length() + 6)).append(" -H '").append(s).append("'").toString());
                    }
                }
                if (obj1 != null)
                {
                    s = String.valueOf(obj1);
                    if (s.length() != 0)
                    {
                        s = "Content-Encoding: ".concat(s);
                    } else
                    {
                        s = new String("Content-Encoding: ");
                    }
                    ((StringBuilder) (obj2)).append(s).append(StringUtils.LINE_SEPARATOR);
                    if (stringbuilder1 != null)
                    {
                        stringbuilder1.append((new StringBuilder(String.valueOf(s).length() + 6)).append(" -H '").append(s).append("'").toString());
                    }
                }
                if (l1 >= 0L)
                {
                    ((StringBuilder) (obj2)).append((new StringBuilder(36)).append("Content-Length: ").append(l1).toString()).append(StringUtils.LINE_SEPARATOR);
                }
            }
            if (stringbuilder1 != null)
            {
                stringbuilder1.append(" -d '@-'");
            }
            lowlevelhttprequest.contentType = s2;
            lowlevelhttprequest.contentEncoding = ((String) (obj1));
            lowlevelhttprequest.contentLength = l1;
            lowlevelhttprequest.streamingContent = ((com.google.api.client.util.StreamingContent) (obj));
            obj1 = obj;
        }
        if (k != 0)
        {
            logger.logp(Level.CONFIG, "com.google.api.client.http.HttpRequest", "execute", ((StringBuilder) (obj2)).toString());
            if (stringbuilder1 != null)
            {
                stringbuilder1.append(" -- '");
                stringbuilder1.append(s1.replaceAll("'", "'\"'\"'"));
                stringbuilder1.append("'");
                if (obj1 != null)
                {
                    stringbuilder1.append(" << $$$");
                }
                logger.logp(Level.CONFIG, "com.google.api.client.http.HttpRequest", "execute", stringbuilder1.toString());
            }
        }
        if (l != 0 && i > 0)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        lowlevelhttprequest.setTimeout(connectTimeout, readTimeout);
        obj1 = lowlevelhttprequest.execute();
        obj = new HttpResponse(this, ((LowLevelHttpResponse) (obj1)));
        obj1 = obj3;
_L18:
        if (obj == null) goto _L3; else goto _L2
_L2:
        k = ((HttpResponse) (obj)).statusCode;
        if (k >= 200 && k < 300)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0) goto _L3; else goto _L4
_L4:
        flag2 = false;
        if (unsuccessfulResponseHandler != null)
        {
            flag2 = unsuccessfulResponseHandler.handleResponse(this, ((HttpResponse) (obj)), flag3);
        }
        if (flag2) goto _L6; else goto _L5
_L5:
        l = ((HttpResponse) (obj)).statusCode;
        obj2 = ((HttpResponse) (obj)).request.responseHeaders.location;
        if (obj2 != null) goto _L8; else goto _L7
_L7:
        obj2 = null;
_L19:
        obj2 = (String)obj2;
        if (!followRedirects) goto _L10; else goto _L9
_L9:
        l;
        JVM INSTR tableswitch 301 307: default 1641
    //                   301 1647
    //                   302 1647
    //                   303 1647
    //                   304 1641
    //                   305 1641
    //                   306 1641
    //                   307 1647;
           goto _L11 _L12 _L12 _L12 _L11 _L11 _L11 _L12
_L42:
        if (k == 0 || obj2 == null) goto _L10; else goto _L13
_L13:
        obj2 = new GenericUrl(url.toURL(((String) (obj2))));
        if (obj2 != null) goto _L15; else goto _L14
_L14:
        throw new NullPointerException();
        obj1;
        if (obj != null)
        {
            obj2 = ((HttpResponse) (obj)).getContent();
            if (obj2 != null)
            {
                ((InputStream) (obj2)).close();
            }
            ((HttpResponse) (obj)).response.disconnect();
        }
        throw obj1;
        obj;
        obj1 = ((LowLevelHttpResponse) (obj1)).getContent();
        if (obj1 == null) goto _L17; else goto _L16
_L16:
        ((InputStream) (obj1)).close();
_L17:
        throw obj;
        obj1;
        if (ioExceptionHandler == null || !ioExceptionHandler.handleIOException(this, flag3))
        {
            throw obj1;
        }
        if (k != 0)
        {
            logger.logp(Level.WARNING, "com.google.api.client.http.HttpRequest", "execute", "exception thrown while executing request", ((Throwable) (obj1)));
        }
        obj = null;
          goto _L18
_L8:
        obj2 = ((List) (obj2)).get(0);
          goto _L19
_L15:
        url = (GenericUrl)obj2;
        if (l != 303) goto _L21; else goto _L20
_L20:
        setRequestMethod("GET");
        content = null;
_L21:
        HttpHeaders httpheaders;
        headers.setAuthorization(null);
        headers.setIfMatch(null);
        httpheaders = headers;
        if (false) goto _L23; else goto _L22
_L22:
        obj2 = null;
_L34:
        httpheaders.ifNoneMatch = ((List) (obj2));
        httpheaders = headers;
        if (false) goto _L25; else goto _L24
_L24:
        obj2 = null;
_L35:
        httpheaders.ifModifiedSince = ((List) (obj2));
        httpheaders = headers;
        if (false) goto _L27; else goto _L26
_L26:
        obj2 = null;
_L36:
        httpheaders.ifUnmodifiedSince = ((List) (obj2));
        httpheaders = headers;
        if (false) goto _L29; else goto _L28
_L28:
        obj2 = null;
_L37:
        httpheaders.ifRange = ((List) (obj2));
        k = 1;
          goto _L30
_L6:
        l = flag2 & flag3;
        k = l;
        if (!l)
        {
            break MISSING_BLOCK_LABEL_1418;
        }
        obj2 = ((HttpResponse) (obj)).getContent();
        k = l;
        if (obj2 == null)
        {
            break MISSING_BLOCK_LABEL_1418;
        }
        ((InputStream) (obj2)).close();
        k = l;
_L38:
        if (obj == null);
        if (k != 0) goto _L32; else goto _L31
_L31:
        if (obj == null)
        {
            throw obj1;
        }
        if (responseInterceptor != null)
        {
            responseInterceptor.interceptResponse(((HttpResponse) (obj)));
        }
          goto _L33
_L23:
        obj2 = new ArrayList();
        ((List) (obj2)).add(null);
          goto _L34
_L25:
        obj2 = new ArrayList();
        ((List) (obj2)).add(null);
          goto _L35
_L27:
        obj2 = new ArrayList();
        ((List) (obj2)).add(null);
          goto _L36
_L29:
        obj2 = new ArrayList();
        ((List) (obj2)).add(null);
          goto _L37
_L10:
        k = 0;
          goto _L30
_L3:
        if (obj == null)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        k &= ((flag3) ? 1 : 0);
          goto _L38
_L33:
        if (!throwExceptionOnExecuteError) goto _L40; else goto _L39
_L39:
        int j = ((HttpResponse) (obj)).statusCode;
        Exception exception;
        InputStream inputstream;
        boolean flag1;
        if (j >= 200 && j < 300)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L40; else goto _L41
_L41:
        throw new HttpResponseException(((HttpResponse) (obj)));
        exception;
        inputstream = ((HttpResponse) (obj)).getContent();
        if (inputstream != null)
        {
            inputstream.close();
        }
        ((HttpResponse) (obj)).response.disconnect();
        throw exception;
_L40:
        return ((HttpResponse) (obj));
_L11:
        k = 0;
          goto _L42
_L12:
        k = 1;
          goto _L42
_L30:
        if (k == 0) goto _L6; else goto _L43
_L43:
        flag2 = true;
          goto _L6
    }

    public final HttpRequest setRequestMethod(String s)
    {
        boolean flag;
        if (s == null || HttpMediaType.TOKEN_REGEX.matcher(s).matches())
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
            requestMethod = s;
            return this;
        }
    }
}
