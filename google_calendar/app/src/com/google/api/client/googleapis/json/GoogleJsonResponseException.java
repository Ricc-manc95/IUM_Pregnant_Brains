// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis.json;

import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.StringUtils;
import com.google.common.base.Platform;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

// Referenced classes of package com.google.api.client.googleapis.json:
//            GoogleJsonError

public final class GoogleJsonResponseException extends HttpResponseException
{

    public static final long serialVersionUID = 0x5aff10c793dbb70L;
    public final transient GoogleJsonError details;

    private GoogleJsonResponseException(com.google.api.client.http.HttpResponseException.Builder builder, GoogleJsonError googlejsonerror)
    {
        super(builder);
        details = googlejsonerror;
    }

    public static GoogleJsonResponseException from(JsonFactory jsonfactory, HttpResponse httpresponse)
    {
        Object obj;
        Object obj1;
        Object obj4;
        com.google.api.client.http.HttpResponseException.Builder builder;
        obj1 = null;
        obj = null;
        obj4 = null;
        builder = new com.google.api.client.http.HttpResponseException.Builder(httpresponse.statusCode, httpresponse.statusMessage, httpresponse.request.responseHeaders);
        if (jsonfactory == null)
        {
            throw new NullPointerException();
        }
        int i = httpresponse.statusCode;
        Object obj2;
        boolean flag;
        if (i >= 200 && i < 300)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        if (!HttpMediaType.equalsIgnoreParameters("application/json; charset=UTF-8", httpresponse.contentType)) goto _L2; else goto _L3
_L3:
        obj2 = httpresponse.getContent();
        if (obj2 == null) goto _L2; else goto _L4
_L4:
        obj1 = jsonfactory.createJsonParser(httpresponse.getContent());
        obj = ((JsonParser) (obj1)).getCurrentToken();
        jsonfactory = ((JsonFactory) (obj));
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_121;
        }
        jsonfactory = ((JsonParser) (obj1)).nextToken();
        if (jsonfactory == null) goto _L6; else goto _L5
_L5:
        ((JsonParser) (obj1)).skipToKey(Collections.singleton("error"));
        if (((JsonParser) (obj1)).getCurrentToken() == JsonToken.END_OBJECT) goto _L6; else goto _L7
_L7:
        jsonfactory = (GoogleJsonError)((JsonParser) (obj1)).parseAndClose(com/google/api/client/googleapis/json/GoogleJsonError, null);
        obj2 = jsonfactory;
        obj = obj1;
        obj4 = jsonfactory.toPrettyString();
        obj2 = obj4;
        obj4 = jsonfactory;
_L25:
        if (obj1 != null) goto _L9; else goto _L8
_L8:
        obj1 = httpresponse.getContent();
        jsonfactory = ((JsonFactory) (obj2));
        obj = obj4;
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_203;
        }
        ((InputStream) (obj1)).close();
        obj = obj4;
        jsonfactory = ((JsonFactory) (obj2));
_L11:
        httpresponse = HttpResponseException.computeMessageBuffer(httpresponse);
        if (!Platform.stringIsNullOrEmpty(jsonfactory))
        {
            httpresponse.append(StringUtils.LINE_SEPARATOR).append(jsonfactory);
            builder.content = jsonfactory;
        }
        builder.message = httpresponse.toString();
        return new GoogleJsonResponseException(builder, ((GoogleJsonError) (obj)));
_L9:
        jsonfactory = ((JsonFactory) (obj2));
        obj = obj4;
        if (obj4 != null) goto _L11; else goto _L10
_L10:
        ((JsonParser) (obj1)).close();
        jsonfactory = ((JsonFactory) (obj2));
        obj = obj4;
          goto _L11
        obj1;
        obj = obj4;
        jsonfactory = ((JsonFactory) (obj2));
_L22:
        ThrowableExtension.STRATEGY.printStackTrace(((Throwable) (obj1)));
          goto _L11
        IOException ioexception;
        ioexception;
        obj1 = null;
        jsonfactory = null;
_L24:
        obj2 = jsonfactory;
        obj = obj1;
        ThrowableExtension.STRATEGY.printStackTrace(ioexception);
        if (obj1 != null) goto _L13; else goto _L12
_L12:
        obj = httpresponse.getContent();
        if (obj == null) goto _L15; else goto _L14
_L14:
        ((InputStream) (obj)).close();
          goto _L15
_L13:
        if (jsonfactory != null) goto _L17; else goto _L16
_L16:
        ((JsonParser) (obj1)).close();
        obj = jsonfactory;
        jsonfactory = null;
          goto _L11
        obj2;
        obj = null;
        jsonfactory = null;
_L23:
        if (obj != null) goto _L19; else goto _L18
_L18:
        obj = httpresponse.getContent();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_377;
        }
        ((InputStream) (obj)).close();
_L21:
        throw obj2;
_L19:
        if (jsonfactory != null) goto _L21; else goto _L20
_L20:
        ((JsonParser) (obj)).close();
          goto _L21
_L2:
        jsonfactory = httpresponse.parseAsString();
        obj = obj1;
          goto _L11
        obj1;
        jsonfactory = null;
          goto _L22
        obj1;
        obj = jsonfactory;
        jsonfactory = null;
          goto _L22
        obj2;
        jsonfactory = null;
        obj = obj1;
          goto _L23
        Exception exception;
        exception;
        jsonfactory = ((JsonFactory) (obj2));
        obj2 = exception;
          goto _L23
        ioexception;
        jsonfactory = null;
          goto _L24
        ioexception;
          goto _L24
_L17:
        obj = jsonfactory;
        jsonfactory = null;
          goto _L11
_L6:
        obj2 = null;
          goto _L25
_L15:
        obj = jsonfactory;
        jsonfactory = null;
          goto _L11
        exception;
        Object obj3 = null;
        obj = jsonfactory;
        jsonfactory = obj3;
          goto _L22
    }
}
