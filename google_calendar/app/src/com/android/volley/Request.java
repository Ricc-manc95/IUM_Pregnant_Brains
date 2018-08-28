// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.android.volley:
//            DefaultRetryPolicy, RequestQueue, AuthFailureError, RetryPolicy, 
//            VolleyError, Response, NetworkResponse

public abstract class Request
    implements Comparable
{

    public Cache.Entry mCacheEntry;
    private boolean mCanceled;
    public final int mDefaultTrafficStatsTag;
    private Response.ErrorListener mErrorListener;
    public final VolleyLog.MarkerLog mEventLog;
    public final Object mLock;
    public final int mMethod;
    public NetworkRequestCompleteListener mRequestCompleteListener;
    public RequestQueue mRequestQueue;
    public boolean mResponseDelivered;
    public RetryPolicy mRetryPolicy;
    public Integer mSequence;
    public boolean mShouldCache;
    private boolean mShouldRetryServerErrors;
    public Object mTag;
    public final String mUrl;

    public Request(int i, String s, Response.ErrorListener errorlistener)
    {
        VolleyLog.MarkerLog markerlog;
        if (VolleyLog.MarkerLog.ENABLED)
        {
            markerlog = new VolleyLog.MarkerLog();
        } else
        {
            markerlog = null;
        }
        mEventLog = markerlog;
        mLock = new Object();
        mShouldCache = true;
        mCanceled = false;
        mResponseDelivered = false;
        mShouldRetryServerErrors = false;
        mCacheEntry = null;
        mMethod = i;
        mUrl = s;
        mErrorListener = errorlistener;
        mRetryPolicy = new DefaultRetryPolicy();
        if (TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        s = Uri.parse(s);
        if (s == null) goto _L2; else goto _L3
_L3:
        s = s.getHost();
        if (s == null) goto _L2; else goto _L4
_L4:
        i = s.hashCode();
_L6:
        mDefaultTrafficStatsTag = i;
        return;
_L2:
        i = 0;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static byte[] encodeParameters(Map map, String s)
    {
        Object obj = new StringBuilder();
        map = map.entrySet().iterator();
_L1:
        java.util.Map.Entry entry;
        if (!map.hasNext())
        {
            break MISSING_BLOCK_LABEL_175;
        }
        entry = (java.util.Map.Entry)map.next();
        if (entry.getKey() == null || entry.getValue() == null)
        {
            throw new IllegalArgumentException(String.format("Request#getParams() or Request#getPostParams() returned a map containing a null key or value: (%s, %s). All keys and values must be non-null.", new Object[] {
                entry.getKey(), entry.getValue()
            }));
        }
        try
        {
            ((StringBuilder) (obj)).append(URLEncoder.encode((String)entry.getKey(), s));
            ((StringBuilder) (obj)).append('=');
            ((StringBuilder) (obj)).append(URLEncoder.encode((String)entry.getValue(), s));
            ((StringBuilder) (obj)).append('&');
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            map = String.valueOf(s);
            if (map.length() != 0)
            {
                map = "Encoding not supported: ".concat(map);
            } else
            {
                map = new String("Encoding not supported: ");
            }
            throw new RuntimeException(map, ((Throwable) (obj)));
        }
          goto _L1
        map = ((StringBuilder) (obj)).toString().getBytes(s);
        return map;
    }

    public void cancel()
    {
        synchronized (mLock)
        {
            mCanceled = true;
            mErrorListener = null;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int compareTo(Object obj)
    {
        obj = (Request)obj;
        Priority priority = getPriority();
        Priority priority1 = ((Request) (obj)).getPriority();
        if (priority == priority1)
        {
            return mSequence.intValue() - ((Request) (obj)).mSequence.intValue();
        } else
        {
            return priority1.ordinal() - priority.ordinal();
        }
    }

    public void deliverError(VolleyError volleyerror)
    {
        Response.ErrorListener errorlistener;
        synchronized (mLock)
        {
            errorlistener = mErrorListener;
        }
        if (errorlistener != null)
        {
            errorlistener.onErrorResponse(volleyerror);
        }
        return;
        volleyerror;
        obj;
        JVM INSTR monitorexit ;
        throw volleyerror;
    }

    public abstract void deliverResponse(Object obj);

    final void finish(final String tag)
    {
        if (mRequestQueue == null) goto _L2; else goto _L1
_L1:
        Object obj1;
        obj1 = mRequestQueue;
        synchronized (((RequestQueue) (obj1)).mCurrentRequests)
        {
            ((RequestQueue) (obj1)).mCurrentRequests.remove(this);
        }
        obj = ((RequestQueue) (obj1)).mFinishedListeners;
        obj;
        JVM INSTR monitorenter ;
        for (obj1 = ((RequestQueue) (obj1)).mFinishedListeners.iterator(); ((Iterator) (obj1)).hasNext(); ((RequestQueue.RequestFinishedListener)((Iterator) (obj1)).next()).onRequestFinished$51666RRD5TGMSP3IDTKM8BRMDTM6OPBP5T96ASBLCLPN8EP9AO______0()) { }
        break MISSING_BLOCK_LABEL_85;
        tag;
        obj;
        JVM INSTR monitorexit ;
        throw tag;
        tag;
        obj;
        JVM INSTR monitorexit ;
        throw tag;
        obj;
        JVM INSTR monitorexit ;
_L2:
        final long threadId;
label0:
        {
            if (VolleyLog.MarkerLog.ENABLED)
            {
                threadId = Thread.currentThread().getId();
                if (Looper.myLooper() == Looper.getMainLooper())
                {
                    break label0;
                }
                (new Handler(Looper.getMainLooper())).post(new _cls1());
            }
            return;
        }
        mEventLog.add(tag, threadId);
        mEventLog.finish(toString());
        return;
    }

    public byte[] getBody()
        throws AuthFailureError
    {
        return null;
    }

    public String getBodyContentType()
    {
        String s = String.valueOf("UTF-8");
        if (s.length() != 0)
        {
            return "application/x-www-form-urlencoded; charset=".concat(s);
        } else
        {
            return new String("application/x-www-form-urlencoded; charset=");
        }
    }

    public final String getCacheKey()
    {
        String s = mUrl;
        int i = mMethod;
        if (i == 0 || i == -1)
        {
            return s;
        } else
        {
            String s1 = Integer.toString(i);
            return (new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(s).length())).append(s1).append('-').append(s).toString();
        }
    }

    public Map getHeaders()
        throws AuthFailureError
    {
        return Collections.emptyMap();
    }

    public Priority getPriority()
    {
        return Priority.NORMAL;
    }

    public final boolean hasHadResponseDelivered()
    {
        boolean flag;
        synchronized (mLock)
        {
            flag = mResponseDelivered;
        }
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean isCanceled()
    {
        boolean flag;
        synchronized (mLock)
        {
            flag = mCanceled;
        }
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void markDelivered()
    {
        synchronized (mLock)
        {
            mResponseDelivered = true;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final void notifyListenerResponseNotUsable()
    {
        NetworkRequestCompleteListener networkrequestcompletelistener;
        synchronized (mLock)
        {
            networkrequestcompletelistener = mRequestCompleteListener;
        }
        if (networkrequestcompletelistener != null)
        {
            networkrequestcompletelistener.onNoUsableResponseReceived(this);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final void notifyListenerResponseReceived(Response response)
    {
        NetworkRequestCompleteListener networkrequestcompletelistener;
        synchronized (mLock)
        {
            networkrequestcompletelistener = mRequestCompleteListener;
        }
        if (networkrequestcompletelistener != null)
        {
            networkrequestcompletelistener.onResponseReceived(this, response);
        }
        return;
        response;
        obj;
        JVM INSTR monitorexit ;
        throw response;
    }

    public abstract Response parseNetworkResponse(NetworkResponse networkresponse);

    public String toString()
    {
        String s = String.valueOf(Integer.toHexString(mDefaultTrafficStatsTag));
        String s1;
        String s2;
        String s3;
        String s4;
        if (s.length() != 0)
        {
            s = "0x".concat(s);
        } else
        {
            s = new String("0x");
        }
        if (isCanceled())
        {
            s1 = "[X] ";
        } else
        {
            s1 = "[ ] ";
        }
        s2 = mUrl;
        s3 = String.valueOf(getPriority());
        s4 = String.valueOf(mSequence);
        return (new StringBuilder(String.valueOf(s1).length() + 3 + String.valueOf(s2).length() + String.valueOf(s).length() + String.valueOf(s3).length() + String.valueOf(s4).length())).append(s1).append(s2).append(" ").append(s).append(" ").append(s3).append(" ").append(s4).toString();
    }

    private class Priority extends Enum
    {

        private static final Priority $VALUES[];
        private static final Priority HIGH;
        public static final Priority IMMEDIATE;
        public static final Priority LOW;
        public static final Priority NORMAL;

        public static Priority[] values()
        {
            return (Priority[])$VALUES.clone();
        }

        static 
        {
            LOW = new Priority("LOW", 0);
            NORMAL = new Priority("NORMAL", 1);
            HIGH = new Priority("HIGH", 2);
            IMMEDIATE = new Priority("IMMEDIATE", 3);
            $VALUES = (new Priority[] {
                LOW, NORMAL, HIGH, IMMEDIATE
            });
        }

        private Priority(String s, int i)
        {
            super(s, i);
        }
    }


    private class _cls1
        implements Runnable
    {

        private final Request this$0;
        private final String val$tag;
        private final long val$threadId;

        public final void run()
        {
            mEventLog.add(tag, threadId);
            mEventLog.finish(toString());
        }

        _cls1()
        {
            this$0 = Request.this;
            tag = s;
            threadId = l;
            super();
        }
    }


    private class NetworkRequestCompleteListener
    {

        public abstract void onNoUsableResponseReceived(Request request);

        public abstract void onResponseReceived(Request request, Response response);
    }

}
