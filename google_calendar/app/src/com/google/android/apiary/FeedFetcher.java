// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apiary;

import android.os.Process;
import android.text.format.Time;
import android.util.Log;
import android.util.TimeFormatException;
import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GZipEncoding;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.UriTemplate;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

// Referenced classes of package com.google.android.apiary:
//            AuthenticationException

public class FeedFetcher
    implements Runnable
{

    public volatile boolean authenticationFailed;
    public final CountDownLatch envelopeParsedLatch = new CountDownLatch(1);
    public volatile boolean forcedClosed;
    public volatile boolean ioException;
    private final Class itemClass;
    private final Object itemEndMarker;
    private final BlockingQueue itemQueue;
    private String itemsName;
    private final JsonFactory jsonFactory;
    private final String mLogTag;
    private final AbstractGoogleJsonClientRequest mRequest;
    public volatile boolean partialSyncUnavailable;
    public volatile boolean resourceUnavailable;
    public volatile long retryAfter;
    public volatile Thread thread;

    public FeedFetcher(JsonFactory jsonfactory, AbstractGoogleJsonClientRequest abstractgooglejsonclientrequest, String s, Class class1, BlockingQueue blockingqueue, Object obj, String s1)
    {
        authenticationFailed = false;
        partialSyncUnavailable = false;
        resourceUnavailable = false;
        retryAfter = -1L;
        forcedClosed = false;
        jsonFactory = jsonfactory;
        mRequest = abstractgooglejsonclientrequest;
        itemsName = s;
        itemClass = class1;
        itemQueue = blockingqueue;
        itemEndMarker = obj;
        mLogTag = s1;
    }

    public HttpResponse executeUnparsed(AbstractGoogleJsonClientRequest abstractgooglejsonclientrequest)
        throws IOException
    {
        String s = ((AbstractGoogleClientRequest) (abstractgooglejsonclientrequest)).requestMethod;
        HttpRequestFactory httprequestfactory = abstractgooglejsonclientrequest.getAbstractGoogleClient().requestFactory;
        Object obj1 = ((AbstractGoogleClientRequest) (abstractgooglejsonclientrequest)).abstractGoogleClient;
        Object obj = String.valueOf(((AbstractGoogleClient) (obj1)).rootUrl);
        obj1 = String.valueOf(((AbstractGoogleClient) (obj1)).servicePath);
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        obj = httprequestfactory.buildRequest(s, new GenericUrl(UriTemplate.expand(((String) (obj)), ((AbstractGoogleClientRequest) (abstractgooglejsonclientrequest)).uriTemplate, abstractgooglejsonclientrequest, true)), ((AbstractGoogleClientRequest) (abstractgooglejsonclientrequest)).httpContent);
        (new MethodOverride()).intercept(((HttpRequest) (obj)));
        obj.objectParser = abstractgooglejsonclientrequest.getAbstractGoogleClient().getObjectParser();
        if (((AbstractGoogleClientRequest) (abstractgooglejsonclientrequest)).httpContent == null && (((AbstractGoogleClientRequest) (abstractgooglejsonclientrequest)).requestMethod.equals("POST") || ((AbstractGoogleClientRequest) (abstractgooglejsonclientrequest)).requestMethod.equals("PUT") || ((AbstractGoogleClientRequest) (abstractgooglejsonclientrequest)).requestMethod.equals("PATCH")))
        {
            obj.content = new EmptyContent();
        }
        ((HttpRequest) (obj)).headers.putAll(((AbstractGoogleClientRequest) (abstractgooglejsonclientrequest)).requestHeaders);
        obj.encoding = new GZipEncoding();
        obj.responseInterceptor = new com.google.api.client.googleapis.services.AbstractGoogleClientRequest._cls1(abstractgooglejsonclientrequest, ((HttpRequest) (obj)).responseInterceptor, ((HttpRequest) (obj)));
        return ((HttpRequest) (obj)).execute();
    }

    public void onExecute(AbstractGoogleJsonClientRequest abstractgooglejsonclientrequest)
    {
    }

    public boolean parseField(JsonParser jsonparser, String s)
        throws IOException, InterruptedException
    {
        return false;
    }

    public void run()
    {
        thread = Thread.currentThread();
        Process.setThreadPriority(10);
        Object obj = executeUnparsed(mRequest);
_L37:
        Object obj1 = obj;
        if (forcedClosed) goto _L2; else goto _L1
_L1:
        obj1 = obj;
        onExecute(mRequest);
        obj1 = obj;
        Object obj3 = jsonFactory;
        obj1 = obj;
        Object obj4 = ((HttpResponse) (obj)).getContent();
        obj1 = obj;
        if (((HttpResponse) (obj)).mediaType == null) goto _L4; else goto _L3
_L3:
        obj1 = obj;
        Object obj2 = (String)((HttpResponse) (obj)).mediaType.parameters.get("charset".toLowerCase());
        if (obj2 != null) goto _L6; else goto _L5
_L5:
        obj1 = null;
          goto _L7
_L4:
        obj1 = obj;
        obj2 = Charsets.ISO_8859_1;
_L12:
        obj1 = obj;
        JsonParser jsonparser = ((JsonFactory) (obj3)).createJsonParser(((InputStream) (obj4)), ((Charset) (obj2)));
        obj1 = obj;
        jsonparser.nextToken();
        obj1 = obj;
        jsonparser.nextToken();
        obj1 = obj;
        obj3 = jsonparser.getCurrentToken();
        obj2 = null;
_L18:
        obj1 = obj;
        if (obj3 != JsonToken.FIELD_NAME) goto _L9; else goto _L8
_L8:
        obj1 = obj;
        boolean flag1 = forcedClosed;
        if (!flag1) goto _L11; else goto _L10
_L10:
        envelopeParsedLatch.countDown();
        if (!forcedClosed)
        {
            itemQueue.put(itemEndMarker);
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_210;
        }
        obj = ((HttpResponse) (obj)).getContent();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_210;
        }
        ((InputStream) (obj)).close();
_L13:
        thread = null;
        if (forcedClosed)
        {
            boolean flag2 = forcedClosed;
            (new StringBuilder(47)).append("FeedFetcher thread ended: forcedClosed is ").append(flag2);
        }
_L15:
        return;
_L6:
        obj1 = obj;
        obj2 = Charset.forName(((String) (obj2)));
        obj1 = obj2;
          goto _L7
_L66:
        obj1 = obj;
        obj2 = (String)((HttpResponse) (obj)).mediaType.parameters.get("charset".toLowerCase());
label0:
        {
            if (obj2 != null)
            {
                break label0;
            }
            obj2 = null;
        }
          goto _L12
        obj1 = obj;
        obj2 = Charset.forName(((String) (obj2)));
          goto _L12
        obj;
        Log.e(mLogTag, "IOException, while closing connection", ((Throwable) (obj)));
          goto _L13
        obj;
        thread = null;
        if (!forcedClosed) goto _L15; else goto _L14
_L14:
        boolean flag3 = forcedClosed;
        (new StringBuilder(47)).append("FeedFetcher thread ended: forcedClosed is ").append(flag3);
        return;
_L11:
        obj1 = obj;
        obj4 = jsonparser.getText();
        obj1 = obj;
        jsonparser.nextToken();
        obj1 = obj;
        if (!"nextPageToken".equals(obj4)) goto _L17; else goto _L16
_L16:
        obj1 = obj;
        obj3 = (String)jsonparser.parse(java/lang/String, false, null);
_L22:
        obj1 = obj;
        obj4 = jsonparser.nextToken();
        obj2 = obj3;
        obj3 = obj4;
          goto _L18
_L17:
        obj1 = obj;
        if (!itemsName.equals(obj4)) goto _L20; else goto _L19
_L19:
        obj1 = obj;
        envelopeParsedLatch.countDown();
_L23:
        obj1 = obj;
        jsonparser.nextToken();
        obj3 = obj2;
        obj1 = obj;
        if (jsonparser.getCurrentToken() == JsonToken.END_ARRAY) goto _L22; else goto _L21
_L21:
        obj1 = obj;
        itemQueue.put(jsonparser.parse(itemClass, false, null));
          goto _L23
        obj2;
_L65:
        obj1 = obj;
        Log.e(mLogTag, "GoogleJsonResponseException, ignoring rest of feed", ((Throwable) (obj2)));
        obj1 = obj;
        ((HttpResponseException) (obj2)).statusCode;
        JVM INSTR lookupswitch 4: default 1373
    //                   400: 911
    //                   401: 1057
    //                   410: 1067
    //                   503: 1077;
           goto _L24 _L25 _L26 _L27 _L28
_L24:
        obj1 = obj;
        ioException = true;
_L48:
        envelopeParsedLatch.countDown();
        if (!forcedClosed)
        {
            itemQueue.put(itemEndMarker);
        }
        if (obj == null) goto _L13; else goto _L29
_L29:
        obj = ((HttpResponse) (obj)).getContent();
        if (obj == null) goto _L13; else goto _L30
_L30:
        ((InputStream) (obj)).close();
          goto _L13
        obj;
        Log.e(mLogTag, "IOException, while closing connection", ((Throwable) (obj)));
          goto _L13
        obj;
        thread = null;
        if (forcedClosed)
        {
            boolean flag4 = forcedClosed;
            (new StringBuilder(47)).append("FeedFetcher thread ended: forcedClosed is ").append(flag4);
        }
        throw obj;
_L20:
        obj3 = obj2;
        obj1 = obj;
        if (parseField(jsonparser, ((String) (obj4)))) goto _L22; else goto _L31
_L31:
        obj1 = obj;
        jsonparser.skipChildren();
        obj3 = obj2;
          goto _L22
        obj2;
_L64:
        obj1 = obj;
        Log.e(mLogTag, "AuthenticationException", ((Throwable) (obj2)));
        obj1 = obj;
        authenticationFailed = true;
        envelopeParsedLatch.countDown();
        if (!forcedClosed)
        {
            itemQueue.put(itemEndMarker);
        }
        if (obj == null) goto _L13; else goto _L32
_L32:
        obj = ((HttpResponse) (obj)).getContent();
        if (obj == null) goto _L13; else goto _L33
_L33:
        ((InputStream) (obj)).close();
          goto _L13
        obj;
        Log.e(mLogTag, "IOException, while closing connection", ((Throwable) (obj)));
          goto _L13
_L9:
        if (obj2 == null) goto _L2; else goto _L34
_L34:
        obj1 = obj;
        obj3 = ((HttpResponse) (obj)).getContent();
        if (obj3 == null) goto _L36; else goto _L35
_L35:
        obj1 = obj;
        ((InputStream) (obj3)).close();
_L36:
        obj1 = obj;
        obj2 = (AbstractGoogleJsonClientRequest)mRequest.set("pageToken", obj2);
        obj1 = obj;
        obj2 = executeUnparsed(mRequest);
        obj = obj2;
          goto _L37
_L2:
        envelopeParsedLatch.countDown();
        if (!forcedClosed)
        {
            itemQueue.put(itemEndMarker);
        }
        if (obj == null) goto _L13; else goto _L38
_L38:
        obj = ((HttpResponse) (obj)).getContent();
        if (obj == null) goto _L13; else goto _L39
_L39:
        ((InputStream) (obj)).close();
          goto _L13
        obj;
        Log.e(mLogTag, "IOException, while closing connection", ((Throwable) (obj)));
          goto _L13
_L25:
        obj1 = obj;
        obj2 = ((GoogleJsonResponseException) (obj2)).details;
        if (obj2 == null) goto _L41; else goto _L40
_L40:
        obj1 = obj;
        obj2 = ((GoogleJsonError) (obj2)).errors;
        if (obj2 == null) goto _L41; else goto _L42
_L42:
        obj1 = obj;
        obj2 = ((List) (obj2)).iterator();
_L45:
        obj1 = obj;
        if (!((Iterator) (obj2)).hasNext()) goto _L41; else goto _L43
_L43:
        obj1 = obj;
        if (!"updatedMinTooLongAgo".equals(((com.google.api.client.googleapis.json.GoogleJsonError.ErrorInfo)((Iterator) (obj2)).next()).reason)) goto _L45; else goto _L44
_L44:
        boolean flag = true;
_L49:
        if (!flag) goto _L47; else goto _L46
_L46:
        obj1 = obj;
        partialSyncUnavailable = true;
          goto _L48
        obj;
_L62:
        envelopeParsedLatch.countDown();
        if (!forcedClosed)
        {
            itemQueue.put(itemEndMarker);
        }
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_1039;
        }
        obj1 = ((HttpResponse) (obj1)).getContent();
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_1039;
        }
        ((InputStream) (obj1)).close();
_L61:
        throw obj;
_L41:
        flag = false;
          goto _L49
_L47:
        obj1 = obj;
        ioException = true;
          goto _L48
_L26:
        obj1 = obj;
        authenticationFailed = true;
          goto _L48
_L27:
        obj1 = obj;
        partialSyncUnavailable = true;
          goto _L48
_L28:
        obj1 = obj;
        obj2 = ((HttpResponseException) (obj2)).headers;
        if (obj2 == null) goto _L51; else goto _L50
_L50:
        obj1 = obj;
        obj2 = ((HttpHeaders) (obj2)).retryAfter;
        if (obj2 != null) goto _L53; else goto _L52
_L52:
        obj2 = null;
_L54:
        obj1 = obj;
        obj2 = (String)obj2;
_L55:
        if (obj2 == null)
        {
            break MISSING_BLOCK_LABEL_1130;
        }
        obj1 = obj;
        retryAfter = Long.parseLong(((String) (obj2))) + System.currentTimeMillis() / 1000L;
_L56:
        obj1 = obj;
        resourceUnavailable = true;
          goto _L48
_L53:
        obj1 = obj;
        obj2 = ((List) (obj2)).get(0);
          goto _L54
_L51:
        obj2 = null;
          goto _L55
        obj1;
        obj1 = obj;
        obj3 = new Time();
        obj1 = obj;
        ((Time) (obj3)).parse3339(((String) (obj2)));
        obj1 = obj;
        retryAfter = ((Time) (obj3)).toMillis(false) / 1000L;
          goto _L56
        obj1;
        obj1 = obj;
        obj2 = String.valueOf(obj2);
        obj1 = obj;
        if (((String) (obj2)).length() == 0) goto _L58; else goto _L57
_L57:
        obj1 = obj;
        "Unable to parse ".concat(((String) (obj2)));
          goto _L56
_L58:
        obj1 = obj;
        new String("Unable to parse ");
          goto _L56
_L63:
        obj1 = obj;
        Log.e(mLogTag, "IOException, ignoring rest of feed", ((Throwable) (obj2)));
        obj1 = obj;
        ioException = true;
        envelopeParsedLatch.countDown();
        if (!forcedClosed)
        {
            itemQueue.put(itemEndMarker);
        }
        if (obj == null) goto _L13; else goto _L59
_L59:
        obj = ((HttpResponse) (obj)).getContent();
        if (obj == null) goto _L13; else goto _L60
_L60:
        ((InputStream) (obj)).close();
          goto _L13
        obj;
        Log.e(mLogTag, "IOException, while closing connection", ((Throwable) (obj)));
          goto _L13
        obj1;
        Log.e(mLogTag, "IOException, while closing connection", ((Throwable) (obj1)));
          goto _L61
        obj;
        obj1 = null;
          goto _L62
        obj2;
          goto _L63
        obj2;
        obj = null;
          goto _L64
        obj2;
        obj = null;
          goto _L65
_L7:
        if (obj1 != null) goto _L66; else goto _L4
        obj2;
        obj = null;
          goto _L63
    }
}
