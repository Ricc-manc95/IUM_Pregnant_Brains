// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

// Referenced classes of package com.android.volley.toolbox:
//            BaseHttpStack, HttpResponse

public final class HurlStack extends BaseHttpStack
{

    private final SSLSocketFactory mSslSocketFactory;
    private final UrlRewriter mUrlRewriter;

    public HurlStack()
    {
        this(null);
    }

    public HurlStack(UrlRewriter urlrewriter)
    {
        this(urlrewriter, null);
    }

    private HurlStack(UrlRewriter urlrewriter, SSLSocketFactory sslsocketfactory)
    {
        mUrlRewriter = urlrewriter;
        mSslSocketFactory = null;
    }

    private static void addBody(HttpURLConnection httpurlconnection, Request request, byte abyte0[])
        throws IOException
    {
        httpurlconnection.setDoOutput(true);
        if (!httpurlconnection.getRequestProperties().containsKey("Content-Type"))
        {
            httpurlconnection.setRequestProperty("Content-Type", request.getBodyContentType());
        }
        httpurlconnection = new DataOutputStream(httpurlconnection.getOutputStream());
        httpurlconnection.write(abyte0);
        httpurlconnection.close();
    }

    private static List convertHeaders(Map map)
    {
        ArrayList arraylist = new ArrayList(map.size());
        map = map.entrySet().iterator();
        do
        {
            if (!map.hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)map.next();
            if (entry.getKey() != null)
            {
                Iterator iterator = ((List)entry.getValue()).iterator();
                while (iterator.hasNext()) 
                {
                    String s = (String)iterator.next();
                    arraylist.add(new Header((String)entry.getKey(), s));
                }
            }
        } while (true);
        return arraylist;
    }

    static InputStream inputStreamFromConnection(HttpURLConnection httpurlconnection)
    {
        InputStream inputstream;
        try
        {
            inputstream = httpurlconnection.getInputStream();
        }
        catch (IOException ioexception)
        {
            return httpurlconnection.getErrorStream();
        }
        return inputstream;
    }

    public final HttpResponse executeRequest(Request request, Map map)
        throws IOException, AuthFailureError
    {
        HashMap hashmap;
        Object obj = request.mUrl;
        hashmap = new HashMap();
        hashmap.putAll(map);
        hashmap.putAll(request.getHeaders());
        if (mUrlRewriter != null)
        {
            String s1 = mUrlRewriter.rewriteUrl(((String) (obj)));
            map = s1;
            if (s1 == null)
            {
                request = String.valueOf(obj);
                if (request.length() != 0)
                {
                    request = "URL blocked by rewriter: ".concat(request);
                } else
                {
                    request = new String("URL blocked by rewriter: ");
                }
                throw new IOException(request);
            }
        } else
        {
            map = ((Map) (obj));
        }
        obj = new URL(map);
        map = (HttpURLConnection)((URL) (obj)).openConnection();
        map.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int i = request.mRetryPolicy.getCurrentTimeout();
        map.setConnectTimeout(i);
        map.setReadTimeout(i);
        map.setUseCaches(false);
        map.setDoInput(true);
        if ("https".equals(((URL) (obj)).getProtocol()) && mSslSocketFactory != null)
        {
            ((HttpsURLConnection)map).setSSLSocketFactory(mSslSocketFactory);
        }
        String s;
        for (Iterator iterator = hashmap.keySet().iterator(); iterator.hasNext(); map.setRequestProperty(s, (String)hashmap.get(s)))
        {
            s = (String)iterator.next();
        }

          goto _L1
        request;
        boolean flag = false;
_L21:
        if (!flag)
        {
            map.disconnect();
        }
        throw request;
_L1:
        request.mMethod;
        JVM INSTR tableswitch -1 7: default 587
    //                   -1 314
    //                   0 365
    //                   1 385
    //                   2 409
    //                   3 375
    //                   4 434
    //                   5 444
    //                   6 454
    //                   7 464;
           goto _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L2:
        throw new IllegalStateException("Unknown method type.");
_L3:
        if (true) goto _L13; else goto _L12
_L12:
        throw new NullPointerException();
_L13:
        if (true)
        {
            break; /* Loop/switch isn't completed */
        }
        map.setRequestMethod("POST");
        addBody(map, request, null);
_L16:
        int j = map.getResponseCode();
        if (j != -1) goto _L15; else goto _L14
_L14:
        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
_L4:
        map.setRequestMethod("GET");
          goto _L16
_L7:
        map.setRequestMethod("DELETE");
          goto _L16
_L5:
        byte abyte0[];
        map.setRequestMethod("POST");
        abyte0 = request.getBody();
        if (abyte0 == null) goto _L16; else goto _L17
_L17:
        addBody(map, request, abyte0);
          goto _L16
_L6:
        map.setRequestMethod("PUT");
        abyte0 = request.getBody();
        if (abyte0 == null) goto _L16; else goto _L18
_L18:
        addBody(map, request, abyte0);
          goto _L16
_L8:
        map.setRequestMethod("HEAD");
          goto _L16
_L9:
        map.setRequestMethod("OPTIONS");
          goto _L16
_L10:
        map.setRequestMethod("TRACE");
          goto _L16
_L11:
        map.setRequestMethod("PATCH");
        abyte0 = request.getBody();
        if (abyte0 == null) goto _L16; else goto _L19
_L19:
        addBody(map, request, abyte0);
          goto _L16
_L15:
        if (request.mMethod == 4 || 100 <= j && j < 200 || j == 204 || j == 304)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_549;
        }
        request = new HttpResponse(j, convertHeaders(map.getHeaderFields()));
        map.disconnect();
        return request;
        request = new HttpResponse(j, convertHeaders(map.getHeaderFields()), map.getContentLength(), new UrlConnectionInputStream(map));
        return request;
        request;
        flag = true;
        if (true) goto _L21; else goto _L20
_L20:
    }

    private class UrlRewriter
    {

        public abstract String rewriteUrl(String s);
    }


    private class UrlConnectionInputStream extends FilterInputStream
    {

        private final HttpURLConnection mConnection;

        public final void close()
            throws IOException
        {
            super.close();
            mConnection.disconnect();
        }

        UrlConnectionInputStream(HttpURLConnection httpurlconnection)
        {
            super(HurlStack.inputStreamFromConnection(httpurlconnection));
            mConnection = httpurlconnection;
        }
    }

}
