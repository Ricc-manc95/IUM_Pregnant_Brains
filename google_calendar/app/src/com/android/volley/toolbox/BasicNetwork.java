// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.os.SystemClock;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.Header;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

// Referenced classes of package com.android.volley.toolbox:
//            ByteArrayPool, PoolingByteArrayOutputStream, BaseHttpStack, HttpResponse

public final class BasicNetwork
    implements Network
{

    private static final boolean DEBUG;
    private final BaseHttpStack mBaseHttpStack;
    private final ByteArrayPool mPool;

    public BasicNetwork(BaseHttpStack basehttpstack)
    {
        this(basehttpstack, new ByteArrayPool(4096));
    }

    private BasicNetwork(BaseHttpStack basehttpstack, ByteArrayPool bytearraypool)
    {
        mBaseHttpStack = basehttpstack;
        mPool = bytearraypool;
    }

    private static void attemptRetryOnException(String s, Request request, VolleyError volleyerror)
        throws VolleyError
    {
        RetryPolicy retrypolicy = request.mRetryPolicy;
        int i = request.mRetryPolicy.getCurrentTimeout();
        try
        {
            retrypolicy.retry(volleyerror);
        }
        // Misplaced declaration of an exception variable
        catch (VolleyError volleyerror)
        {
            s = String.format("%s-timeout-giveup [timeout=%s]", new Object[] {
                s, Integer.valueOf(i)
            });
            if (com.android.volley.VolleyLog.MarkerLog.ENABLED)
            {
                request.mEventLog.add(s, Thread.currentThread().getId());
            }
            throw volleyerror;
        }
        s = String.format("%s-retry [timeout=%s]", new Object[] {
            s, Integer.valueOf(i)
        });
        if (com.android.volley.VolleyLog.MarkerLog.ENABLED)
        {
            request.mEventLog.add(s, Thread.currentThread().getId());
        }
    }

    private final byte[] inputStreamToBytes(InputStream inputstream, int i)
        throws IOException, ServerError
    {
        byte abyte0[];
        PoolingByteArrayOutputStream poolingbytearrayoutputstream;
        poolingbytearrayoutputstream = new PoolingByteArrayOutputStream(mPool, i);
        abyte0 = null;
        if (inputstream != null)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        throw new ServerError();
        Exception exception;
        exception;
        byte abyte1[];
        byte abyte2[];
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (InputStream inputstream)
            {
                VolleyLog.v("Error occurred when closing InputStream", new Object[0]);
            }
        }
        mPool.returnBuf(abyte0);
        poolingbytearrayoutputstream.close();
        throw exception;
        abyte1 = mPool.getBuf(1024);
_L2:
        abyte0 = abyte1;
        i = inputstream.read(abyte1);
        if (i == -1)
        {
            break; /* Loop/switch isn't completed */
        }
        abyte0 = abyte1;
        poolingbytearrayoutputstream.write(abyte1, 0, i);
        if (true) goto _L2; else goto _L1
_L1:
        abyte0 = abyte1;
        abyte2 = poolingbytearrayoutputstream.toByteArray();
        if (inputstream != null)
        {
            try
            {
                inputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch (InputStream inputstream)
            {
                VolleyLog.v("Error occurred when closing InputStream", new Object[0]);
            }
        }
        mPool.returnBuf(abyte1);
        poolingbytearrayoutputstream.close();
        return abyte2;
    }

    public final NetworkResponse performRequest(Request request)
        throws VolleyError
    {
        long l = SystemClock.elapsedRealtime();
_L9:
        Object obj2;
        Object obj3;
        obj2 = null;
        obj3 = Collections.emptyList();
        Object obj4 = request.mCacheEntry;
        if (obj4 != null) goto _L2; else goto _L1
_L1:
        Object obj = Collections.emptyMap();
_L8:
        Object obj1 = mBaseHttpStack.executeRequest(request, ((Map) (obj)));
        obj = obj3;
        int i = ((HttpResponse) (obj1)).mStatusCode;
        obj = obj3;
        obj3 = Collections.unmodifiableList(((HttpResponse) (obj1)).mHeaders);
        if (i != 304) goto _L4; else goto _L3
_L3:
        obj = obj3;
        obj2 = request.mCacheEntry;
        if (obj2 != null) goto _L6; else goto _L5
_L5:
        obj = obj3;
        obj2 = new NetworkResponse(304, null, true, SystemClock.elapsedRealtime() - l, ((List) (obj3)));
        return ((NetworkResponse) (obj2));
_L2:
        obj1 = new HashMap();
        if (((com.android.volley.Cache.Entry) (obj4)).etag != null)
        {
            ((Map) (obj1)).put("If-None-Match", ((com.android.volley.Cache.Entry) (obj4)).etag);
        }
        obj = obj1;
        if (((com.android.volley.Cache.Entry) (obj4)).lastModified <= 0L) goto _L8; else goto _L7
_L7:
        long l1 = ((com.android.volley.Cache.Entry) (obj4)).lastModified;
        obj = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        ((SimpleDateFormat) (obj)).setTimeZone(TimeZone.getTimeZone("GMT"));
        ((Map) (obj1)).put("If-Modified-Since", ((SimpleDateFormat) (obj)).format(new Date(l1)));
        obj = obj1;
          goto _L8
        obj;
        attemptRetryOnException("socket", request, new TimeoutError());
          goto _L9
_L6:
        obj = obj3;
        obj4 = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        obj = obj3;
        if (((List) (obj3)).isEmpty())
        {
            break MISSING_BLOCK_LABEL_338;
        }
        obj = obj3;
        Iterator iterator = ((List) (obj3)).iterator();
_L10:
        obj = obj3;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_338;
        }
        obj = obj3;
        ((Set) (obj4)).add(((Header)iterator.next()).mName);
          goto _L10
        obj;
        request = String.valueOf(request.mUrl);
        IOException ioexception1;
        ArrayList arraylist;
        Iterator iterator1;
        Object obj5;
        long l2;
        if (request.length() != 0)
        {
            request = "Bad URL ".concat(request);
        } else
        {
            request = new String("Bad URL ");
        }
        throw new RuntimeException(request, ((Throwable) (obj)));
        obj = obj3;
        arraylist = new ArrayList(((java.util.Collection) (obj3)));
        obj = obj3;
        if (((com.android.volley.Cache.Entry) (obj2)).allResponseHeaders == null) goto _L12; else goto _L11
_L11:
        obj = obj3;
        if (((com.android.volley.Cache.Entry) (obj2)).allResponseHeaders.isEmpty()) goto _L14; else goto _L13
_L13:
        obj = obj3;
        iterator1 = ((com.android.volley.Cache.Entry) (obj2)).allResponseHeaders.iterator();
_L17:
        obj = obj3;
        if (!iterator1.hasNext()) goto _L14; else goto _L15
_L15:
        obj = obj3;
        obj5 = (Header)iterator1.next();
        obj = obj3;
        if (((Set) (obj4)).contains(((Header) (obj5)).mName)) goto _L17; else goto _L16
_L16:
        obj = obj3;
        arraylist.add(obj5);
          goto _L17
        obj4;
        obj2 = null;
        obj3 = obj;
        obj = obj2;
        obj2 = obj1;
        obj1 = obj4;
_L26:
        IOException ioexception;
        if (obj2 != null)
        {
            i = ((HttpResponse) (obj2)).mStatusCode;
            obj1 = request.mUrl;
            Log.e(VolleyLog.TAG, VolleyLog.buildMessage("Unexpected response code %d for %s", new Object[] {
                Integer.valueOf(i), obj1
            }));
            if (obj != null)
            {
                obj = new NetworkResponse(i, ((byte []) (obj)), false, SystemClock.elapsedRealtime() - l, ((List) (obj3)));
                if (i == 401 || i == 403)
                {
                    attemptRetryOnException("auth", request, new AuthFailureError(((NetworkResponse) (obj))));
                } else
                {
                    if (i >= 400 && i <= 499)
                    {
                        throw new ClientError(((NetworkResponse) (obj)));
                    }
                    if (i >= 500 && i <= 599)
                    {
                        throw new ServerError(((NetworkResponse) (obj)));
                    } else
                    {
                        throw new ServerError(((NetworkResponse) (obj)));
                    }
                }
            } else
            {
                attemptRetryOnException("network", request, new NetworkError());
            }
        } else
        {
            throw new NoConnectionError(ioexception);
        }
          goto _L9
_L12:
        obj = obj3;
        if (((com.android.volley.Cache.Entry) (obj2)).responseHeaders.isEmpty()) goto _L14; else goto _L18
_L18:
        obj = obj3;
        iterator1 = ((com.android.volley.Cache.Entry) (obj2)).responseHeaders.entrySet().iterator();
_L19:
        obj = obj3;
        if (!iterator1.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = obj3;
        obj5 = (java.util.Map.Entry)iterator1.next();
        obj = obj3;
        if (((Set) (obj4)).contains(((java.util.Map.Entry) (obj5)).getKey()))
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = obj3;
        arraylist.add(new Header((String)((java.util.Map.Entry) (obj5)).getKey(), (String)((java.util.Map.Entry) (obj5)).getValue()));
        if (true) goto _L19; else goto _L14
_L14:
        obj = obj3;
        return new NetworkResponse(304, ((com.android.volley.Cache.Entry) (obj2)).data, true, SystemClock.elapsedRealtime() - l, arraylist);
_L4:
        obj = obj3;
        obj2 = ((HttpResponse) (obj1)).mContent;
        if (obj2 == null) goto _L21; else goto _L20
_L20:
        obj = obj3;
        obj2 = inputStreamToBytes(((InputStream) (obj2)), ((HttpResponse) (obj1)).mContentLength);
        obj = obj2;
_L27:
        l2 = SystemClock.elapsedRealtime() - l;
        if (!DEBUG && l2 <= 3000L) goto _L23; else goto _L22
_L22:
        if (obj == null) goto _L25; else goto _L24
_L24:
        obj2 = Integer.valueOf(obj.length);
_L28:
        VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", new Object[] {
            request, Long.valueOf(l2), obj2, Integer.valueOf(i), Integer.valueOf(request.mRetryPolicy.getCurrentRetryCount())
        });
          goto _L23
_L29:
        throw new IOException();
        ioexception1;
        obj2 = obj1;
        obj1 = ioexception1;
          goto _L26
_L21:
        obj = obj3;
        obj2 = new byte[0];
        obj = obj2;
          goto _L27
_L25:
        obj2 = "null";
          goto _L28
_L30:
        obj2 = new NetworkResponse(i, ((byte []) (obj)), false, SystemClock.elapsedRealtime() - l, ((List) (obj3)));
        return ((NetworkResponse) (obj2));
        ioexception;
        obj = null;
          goto _L26
_L23:
        if (i >= 200 && i <= 299) goto _L30; else goto _L29
    }

    static 
    {
        DEBUG = VolleyLog.DEBUG;
    }
}
