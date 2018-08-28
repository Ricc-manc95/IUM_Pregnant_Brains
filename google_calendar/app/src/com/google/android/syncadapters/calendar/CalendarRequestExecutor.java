// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.support.v4.util.SimpleArrayMap;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GZipEncoding;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.UriTemplate;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.services.calendar.CalendarRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            CalendarRequestExecutorBase, SyncLog

public class CalendarRequestExecutor
    implements CalendarRequestExecutorBase
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/CalendarRequestExecutor);
    private Executor executor;
    private final SimpleArrayMap futureResponses = new SimpleArrayMap();

    public CalendarRequestExecutor()
    {
    }

    static Object executeInternal(String s, CalendarRequest calendarrequest)
        throws IOException
    {
        Object obj1;
        boolean flag;
        obj1 = null;
        flag = true;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        SyncLog.start(s);
        obj2 = ((AbstractGoogleClientRequest) (calendarrequest)).requestMethod;
        obj3 = calendarrequest.getAbstractGoogleClient().requestFactory;
        obj4 = ((AbstractGoogleClientRequest) (calendarrequest)).abstractGoogleClient;
        obj = String.valueOf(((AbstractGoogleClient) (obj4)).rootUrl);
        obj4 = String.valueOf(((AbstractGoogleClient) (obj4)).servicePath);
        if (((String) (obj4)).length() == 0) goto _L2; else goto _L1
_L1:
        obj = ((String) (obj)).concat(((String) (obj4)));
_L5:
        int i;
        obj = ((HttpRequestFactory) (obj3)).buildRequest(((String) (obj2)), new GenericUrl(UriTemplate.expand(((String) (obj)), ((AbstractGoogleClientRequest) (calendarrequest)).uriTemplate, calendarrequest, true)), ((AbstractGoogleClientRequest) (calendarrequest)).httpContent);
        (new MethodOverride()).intercept(((HttpRequest) (obj)));
        obj.objectParser = calendarrequest.getAbstractGoogleClient().getObjectParser();
        if (((AbstractGoogleClientRequest) (calendarrequest)).httpContent == null && (((AbstractGoogleClientRequest) (calendarrequest)).requestMethod.equals("POST") || ((AbstractGoogleClientRequest) (calendarrequest)).requestMethod.equals("PUT") || ((AbstractGoogleClientRequest) (calendarrequest)).requestMethod.equals("PATCH")))
        {
            obj.content = new EmptyContent();
        }
        ((HttpRequest) (obj)).headers.putAll(((AbstractGoogleClientRequest) (calendarrequest)).requestHeaders);
        obj.encoding = new GZipEncoding();
        obj.responseInterceptor = new com.google.api.client.googleapis.services.AbstractGoogleClientRequest._cls1(calendarrequest, ((HttpRequest) (obj)).responseInterceptor, ((HttpRequest) (obj)));
        obj4 = ((HttpRequest) (obj)).execute();
        obj = ((AbstractGoogleClientRequest) (calendarrequest)).responseClass;
        i = ((HttpResponse) (obj4)).statusCode;
        if (!((HttpResponse) (obj4)).request.requestMethod.equals("HEAD") && i / 100 != 1 && i != 204 && i != 304)
        {
            break MISSING_BLOCK_LABEL_284;
        }
        calendarrequest = ((HttpResponse) (obj4)).getContent();
        if (calendarrequest == null)
        {
            break MISSING_BLOCK_LABEL_281;
        }
        calendarrequest.close();
        flag = false;
        if (flag) goto _L4; else goto _L3
_L3:
        calendarrequest = null;
_L11:
        SyncLog.stop(s);
        return calendarrequest;
_L2:
        obj = new String(((String) (obj)));
          goto _L5
        calendarrequest;
        SyncLog.stop(s);
        throw calendarrequest;
_L4:
        obj2 = ((HttpResponse) (obj4)).request.objectParser;
        obj3 = ((HttpResponse) (obj4)).getContent();
        if (((HttpResponse) (obj4)).mediaType == null) goto _L7; else goto _L6
_L6:
        calendarrequest = (String)((HttpResponse) (obj4)).mediaType.parameters.get("charset".toLowerCase());
        if (calendarrequest != null) goto _L9; else goto _L8
_L8:
        calendarrequest = null;
          goto _L10
_L7:
        calendarrequest = Charsets.ISO_8859_1;
_L13:
        calendarrequest = ((CalendarRequest) (((ObjectParser) (obj2)).parseAndClose(((InputStream) (obj3)), calendarrequest, ((Class) (obj)))));
          goto _L11
_L9:
        calendarrequest = Charset.forName(calendarrequest);
          goto _L10
_L14:
        obj4 = (String)((HttpResponse) (obj4)).mediaType.parameters.get("charset".toLowerCase());
        calendarrequest = obj1;
        if (obj4 == null) goto _L13; else goto _L12
_L12:
        calendarrequest = Charset.forName(((String) (obj4)));
          goto _L13
_L10:
        if (calendarrequest != null) goto _L14; else goto _L7
    }

    private final Future getAndRemoveFutureResponse(CalendarRequest calendarrequest)
    {
        Object obj;
        Object obj1 = ((AbstractGoogleClientRequest) (calendarrequest)).abstractGoogleClient;
        obj = String.valueOf(((AbstractGoogleClient) (obj1)).rootUrl);
        obj1 = String.valueOf(((AbstractGoogleClient) (obj1)).servicePath);
        if (((String) (obj1)).length() == 0)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        obj = ((String) (obj)).concat(((String) (obj1)));
_L1:
        String s;
        s = (new GenericUrl(UriTemplate.expand(((String) (obj)), ((AbstractGoogleClientRequest) (calendarrequest)).uriTemplate, calendarrequest, true))).build();
        obj = (Future)futureResponses.remove(s);
        calendarrequest = ((CalendarRequest) (obj));
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_123;
        }
        LogUtils.v(TAG, "load(%s)", new Object[] {
            s
        });
        return ((Future) (obj));
        obj = new String(((String) (obj)));
          goto _L1
        calendarrequest;
        LogUtils.wtf(TAG, calendarrequest, "Failed to get a future response for a request.", new Object[0]);
        calendarrequest = null;
        return calendarrequest;
    }

    private final boolean setFutureResponse(CalendarRequest calendarrequest, FutureTask futuretask)
    {
        String s;
        Object obj = ((AbstractGoogleClientRequest) (calendarrequest)).abstractGoogleClient;
        s = String.valueOf(((AbstractGoogleClient) (obj)).rootUrl);
        obj = String.valueOf(((AbstractGoogleClient) (obj)).servicePath);
        if (((String) (obj)).length() == 0)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        s = s.concat(((String) (obj)));
_L1:
        calendarrequest = (new GenericUrl(UriTemplate.expand(s, ((AbstractGoogleClientRequest) (calendarrequest)).uriTemplate, calendarrequest, true))).build();
        futureResponses.put(calendarrequest, futuretask);
        LogUtils.v(TAG, "save(%s)", new Object[] {
            calendarrequest
        });
        return true;
        try
        {
            s = new String(s);
        }
        // Misplaced declaration of an exception variable
        catch (CalendarRequest calendarrequest)
        {
            LogUtils.wtf(TAG, calendarrequest, "Failed to store a future response for a request.", new Object[0]);
            return false;
        }
          goto _L1
    }

    public final Object execute(String s, CalendarRequest calendarrequest)
        throws IOException
    {
        Object obj;
        if (!Thread.currentThread().getName().startsWith("SyncAdapterThread"))
        {
            throw new IllegalStateException();
        }
        obj = getAndRemoveFutureResponse(calendarrequest);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        obj = ((Future) (obj)).get();
        return obj;
        Object obj1;
        obj1;
        Throwable throwable = ((ExecutionException) (obj1)).getCause();
        if (throwable instanceof GoogleJsonResponseException)
        {
            throw (GoogleJsonResponseException)throwable;
        }
        LogUtils.w(TAG, throwable, "Failed execution for prefetched request. Executing again.", new Object[0]);
_L2:
        return executeInternal(s, calendarrequest);
        throwable;
        LogUtils.w(TAG, throwable, "Failed to get response for prefetched request. Executing again.", new Object[0]);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final Object executeWithFlags(String s, CalendarRequest calendarrequest, String s1)
        throws IOException
    {
        if (s1 != null)
        {
            s1 = TimelyUtils.mapFromJson(s1);
            if (s1 != null)
            {
                for (Iterator iterator = s1.keySet().iterator(); iterator.hasNext();)
                {
                    Object obj = (String)iterator.next();
                    obj = (CalendarRequest)calendarrequest.set(((String) (obj)), s1.get(obj));
                }

            }
        }
        return executeInternal(s, calendarrequest);
    }

    public final Future prefetch(final String tag, final CalendarRequest request)
    {
        FutureTask futuretask = new FutureTask(new _cls1());
        if (!setFutureResponse(request, futuretask))
        {
            return null;
        }
        if (executor == null)
        {
            request = new _cls2();
            LinkedBlockingQueue linkedblockingqueue = new LinkedBlockingQueue();
            request = new ThreadPoolExecutor(8, 8, 15L, TimeUnit.SECONDS, linkedblockingqueue, request);
            request.allowCoreThreadTimeOut(true);
            executor = request;
        }
        LogUtils.d(TAG, "prefetch(%s) @%s", new Object[] {
            tag, Thread.currentThread().getName()
        });
        executor.execute(futuretask);
        return futuretask;
    }

    public final void reset(boolean flag)
    {
        int i;
        if (futureResponses.size() > 0)
        {
            if (flag)
            {
                LogUtils.wtf(TAG, "Failed to execute all expected requests before reset.", new Object[0]);
            } else
            {
                LogUtils.i(TAG, "Found not executed requests on reset.", new Object[0]);
            }
        }
        for (i = 0; i < futureResponses.size(); i++)
        {
            LogUtils.d(TAG, "cancel(%s) @%s", new Object[] {
                futureResponses.mArray[i << 1], Thread.currentThread().getName()
            });
            ((Future)futureResponses.mArray[(i << 1) + 1]).cancel(true);
        }

        futureResponses.clear();
    }


    private class _cls1
        implements Callable
    {

        private final CalendarRequestExecutor this$0;
        private final CalendarRequest val$request;
        private final String val$tag;

        public final Object call()
            throws Exception
        {
            Process.setThreadPriority(10);
            LogUtils.d(CalendarRequestExecutor.TAG, "start(%s) @%s", new Object[] {
                tag, Thread.currentThread().getName()
            });
            Object obj = CalendarRequestExecutor.executeInternal(tag, request);
            LogUtils.d(CalendarRequestExecutor.TAG, "stop(%s) @%s", new Object[] {
                tag, Thread.currentThread().getName()
            });
            return obj;
        }

        _cls1()
        {
            this$0 = CalendarRequestExecutor.this;
            tag = s;
            request = calendarrequest;
            super();
        }
    }


    private class _cls2
        implements ThreadFactory
    {

        private final AtomicInteger count = new AtomicInteger(1);

        public final Thread newThread(Runnable runnable)
        {
            int i = count.getAndIncrement();
            return new Thread(runnable, (new StringBuilder(27)).append("CalendarRequest-").append(i).toString());
        }

        _cls2()
        {
        }
    }

}
