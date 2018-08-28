// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

// Referenced classes of package com.google.analytics.tracking.android:
//            Dispatcher, Utils, Log, Hit, 
//            HttpClientFactory

final class SimpleNetworkDispatcher
    implements Dispatcher
{

    private final Context ctx;
    private final HttpClientFactory httpClientFactory;
    private final String userAgent;

    SimpleNetworkDispatcher(HttpClientFactory httpclientfactory, Context context)
    {
        this(httpclientfactory, context, (byte)0);
    }

    private SimpleNetworkDispatcher(HttpClientFactory httpclientfactory, Context context, byte byte0)
    {
        ctx = context.getApplicationContext();
        userAgent = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[] {
            "GoogleAnalytics", "2.0", android.os.Build.VERSION.RELEASE, Utils.getLanguage(Locale.getDefault()), Build.MODEL, Build.ID
        });
        httpClientFactory = httpclientfactory;
    }

    private final HttpEntityEnclosingRequest buildRequest(String s, String s1)
    {
        String s2;
        if (TextUtils.isEmpty(s))
        {
            Log.w("Empty hit, discarding.");
            return null;
        }
        s2 = (new StringBuilder()).append(s1).append("?").append(s).toString();
        if (s2.length() >= 2036) goto _L2; else goto _L1
_L1:
        s = new BasicHttpEntityEnclosingRequest("GET", s2);
_L4:
        s.addHeader("User-Agent", userAgent);
        return s;
_L2:
        s1 = new BasicHttpEntityEnclosingRequest("POST", s1);
        try
        {
            s1.setEntity(new StringEntity(s));
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            Log.w("Encoding error, discarding hit");
            return null;
        }
        s = s1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static URL getUrl(Hit hit)
    {
        if (TextUtils.isEmpty(hit.mHitUrl))
        {
            return null;
        }
        try
        {
            hit = new URL(hit.mHitUrl);
        }
        // Misplaced declaration of an exception variable
        catch (Hit hit)
        {
            try
            {
                hit = new URL("http://www.google-analytics.com/collect");
            }
            // Misplaced declaration of an exception variable
            catch (Hit hit)
            {
                return null;
            }
            return hit;
        }
        return hit;
    }

    public final int dispatchHits(List list)
    {
        int i;
        int j;
        int k;
        k = Math.min(list.size(), 40);
        j = 0;
        i = 0;
_L2:
        Object obj;
        HttpClient httpclient;
        Object obj1;
        if (j >= k)
        {
            break MISSING_BLOCK_LABEL_392;
        }
        httpclient = httpClientFactory.newInstance();
        obj = (Hit)list.get(j);
        obj1 = getUrl(((Hit) (obj)));
        if (obj1 != null)
        {
            break; /* Loop/switch isn't completed */
        }
        Log.w("No destination: discarding hit.");
        i++;
_L3:
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        HttpHost httphost;
label0:
        {
            httphost = new HttpHost(((URL) (obj1)).getHost(), ((URL) (obj1)).getPort(), ((URL) (obj1)).getProtocol());
            obj1 = ((URL) (obj1)).getPath();
            if (TextUtils.isEmpty(((Hit) (obj)).mHitString))
            {
                obj = "";
            } else
            {
                long l = System.currentTimeMillis();
                StringBuilder stringbuilder = new StringBuilder();
                stringbuilder.append(((Hit) (obj)).mHitString);
                if (((Hit) (obj)).mHitTime > 0L)
                {
                    l -= ((Hit) (obj)).mHitTime;
                    if (l >= 0L)
                    {
                        stringbuilder.append("&qt").append("=").append(l);
                    }
                }
                stringbuilder.append("&z").append("=").append(((Hit) (obj)).mHitId);
                obj = stringbuilder.toString();
            }
            obj1 = buildRequest(((String) (obj)), ((String) (obj1)));
            if (obj1 != null)
            {
                break label0;
            }
            i++;
        }
          goto _L3
        ((HttpEntityEnclosingRequest) (obj1)).addHeader("Host", httphost.toHostString());
        if (((String) (obj)).length() <= 8192) goto _L5; else goto _L4
_L4:
        Log.w("Hit too long (> 8192 bytes)--not sent");
_L7:
        i++;
          goto _L3
_L5:
        obj = httpclient.execute(httphost, ((org.apache.http.HttpRequest) (obj1)));
        if (((HttpResponse) (obj)).getStatusLine().getStatusCode() == 200) goto _L7; else goto _L6
_L6:
        Log.w((new StringBuilder("Bad response: ")).append(((HttpResponse) (obj)).getStatusLine().getStatusCode()).toString());
        return i;
        ClientProtocolException clientprotocolexception;
        clientprotocolexception;
        Log.w("ClientProtocolException sending hit; discarding hit...");
          goto _L7
        list;
        Log.w((new StringBuilder("Exception sending hit: ")).append(list.getClass().getSimpleName()).toString());
        Log.w(list.getMessage());
        return i;
        return i;
    }

    public final boolean okToDispatch()
    {
        NetworkInfo networkinfo = ((ConnectivityManager)ctx.getSystemService("connectivity")).getActiveNetworkInfo();
        return networkinfo != null && networkinfo.isConnected();
    }
}
