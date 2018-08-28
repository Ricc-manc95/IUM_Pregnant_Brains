// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import android.content.Context;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apiary.GoogleRequestInitializer;
import com.google.android.syncadapters.calendar.Utils;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.MethodOverride;
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
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarRequest;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventHabitInstance;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.calendar.model.PrivateEventData;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.Callable;

public class BackendEventFetcher
    implements Callable
{

    private static final String FIELDS = String.valueOf(TextUtils.join(",", new String[] {
        "nextPageToken", "items/id", "items/end", "items/status", "items/organizer", "items/recurringEventId", "items/start", "items/endTimeUnspecified", "items/originalStartTime", "items/sequence", 
        "items/location", "items/etag", "items/updated", "items/phantom", "items/description", "items/summary", "items/privateEventData/smartMailInfo/source"
    })).concat(",items/habitInstance/parentId");
    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/timely/consistency/BackendEventFetcher);
    private final String calendarId;
    private final Context context;
    private final long endTimeMs;
    public ImmutableList executedRequests;
    private final boolean noLimit;
    private final long startTimeMs;

    public BackendEventFetcher(Context context1, String s, long l, long l1, boolean flag)
    {
        context = context1;
        calendarId = s;
        startTimeMs = l;
        endTimeMs = l1;
        noLimit = flag;
    }

    private final List call()
    {
        Object obj;
        com.google.common.collect.ImmutableList.Builder builder;
        ArrayList arraylist;
        obj = new GoogleRequestInitializer(context, "oauth2:https://www.googleapis.com/auth/calendar", TAG);
        Utils.setUserAgentFromContext(((GoogleRequestInitializer) (obj)), context);
        obj.accountName = calendarId;
        obj.authToken = null;
        obj = new Calendar(new NetHttpTransport(), new AndroidJsonFactory(), ((com.google.api.client.http.HttpRequestInitializer) (obj)));
        arraylist = new ArrayList();
        builder = new com.google.common.collect.ImmutableList.Builder();
        com.google.api.services.calendar.Calendar.Events.List list;
        obj = new com.google.api.services.calendar.Calendar.Events(((Calendar) (obj)));
        list = new com.google.api.services.calendar.Calendar.Events.List(((com.google.api.services.calendar.Calendar.Events) (obj)), calendarId);
        ((com.google.api.services.calendar.Calendar.Events) (obj)).this$0.initialize(list);
        list.timeMin = new DateTime(startTimeMs, 0);
        list.timeMax = new DateTime(endTimeMs, 0);
        list.timeZone = "UTC";
        list.maxAttendees = Integer.valueOf(1);
        list.singleEvents = Boolean.valueOf(true);
        list.maxResults = Integer.valueOf(200);
        list = (com.google.api.services.calendar.Calendar.Events.List)list.setFields(FIELDS);
        int i = 0;
_L23:
        Object obj1;
        Object obj2;
        Object obj3;
        obj = (com.google.common.collect.ImmutableList.Builder)builder.add((GenericData)list.clone());
        obj1 = ((AbstractGoogleClientRequest) (list)).requestMethod;
        obj2 = list.getAbstractGoogleClient().requestFactory;
        obj3 = ((AbstractGoogleClientRequest) (list)).abstractGoogleClient;
        obj = String.valueOf(((AbstractGoogleClient) (obj3)).rootUrl);
        obj3 = String.valueOf(((AbstractGoogleClient) (obj3)).servicePath);
        if (((String) (obj3)).length() == 0) goto _L2; else goto _L1
_L1:
        obj = ((String) (obj)).concat(((String) (obj3)));
_L12:
        HttpResponse httpresponse;
        int j;
        obj = ((HttpRequestFactory) (obj2)).buildRequest(((String) (obj1)), new GenericUrl(UriTemplate.expand(((String) (obj)), ((AbstractGoogleClientRequest) (list)).uriTemplate, list, true)), ((AbstractGoogleClientRequest) (list)).httpContent);
        (new MethodOverride()).intercept(((HttpRequest) (obj)));
        obj.objectParser = list.getAbstractGoogleClient().getObjectParser();
        if (((AbstractGoogleClientRequest) (list)).httpContent == null && (((AbstractGoogleClientRequest) (list)).requestMethod.equals("POST") || ((AbstractGoogleClientRequest) (list)).requestMethod.equals("PUT") || ((AbstractGoogleClientRequest) (list)).requestMethod.equals("PATCH")))
        {
            obj.content = new EmptyContent();
        }
        ((HttpRequest) (obj)).headers.putAll(((AbstractGoogleClientRequest) (list)).requestHeaders);
        obj.encoding = new GZipEncoding();
        obj.responseInterceptor = new com.google.api.client.googleapis.services.AbstractGoogleClientRequest._cls1(list, ((HttpRequest) (obj)).responseInterceptor, ((HttpRequest) (obj)));
        httpresponse = ((HttpRequest) (obj)).execute();
        obj1 = ((AbstractGoogleClientRequest) (list)).responseClass;
        j = httpresponse.statusCode;
        if (!httpresponse.request.requestMethod.equals("HEAD") && j / 100 != 1 && j != 204 && j != 304) goto _L4; else goto _L3
_L3:
        obj = httpresponse.getContent();
        if (obj == null) goto _L6; else goto _L5
_L5:
        ((InputStream) (obj)).close();
          goto _L6
_L19:
        obj = (Events)obj;
        obj1 = ((Events) (obj)).items.iterator();
_L11:
        if (!((Iterator) (obj1)).hasNext()) goto _L8; else goto _L7
_L7:
        obj2 = (Event)((Iterator) (obj1)).next();
        if (obj2 != null) goto _L10; else goto _L9
_L9:
        LogUtils.w(TAG, "Fetched null object from the server.", new Object[0]);
          goto _L11
        obj;
        LogUtils.e(TAG, ((Throwable) (obj)), "Exception while fetching events for %s", new Object[] {
            LogUtils.sanitizeName(TAG, calendarId)
        });
        builder.forceCopy = true;
        executedRequests = ImmutableList.asImmutableList(builder.contents, builder.size);
        return null;
_L2:
        obj = new String(((String) (obj)));
          goto _L12
        obj;
        builder.forceCopy = true;
        executedRequests = ImmutableList.asImmutableList(builder.contents, builder.size);
        throw obj;
_L4:
        j = 1;
          goto _L13
_L25:
        obj2 = httpresponse.request.objectParser;
        obj3 = httpresponse.getContent();
        if (httpresponse.mediaType == null) goto _L15; else goto _L14
_L14:
        obj = (String)httpresponse.mediaType.parameters.get("charset".toLowerCase());
        if (obj != null) goto _L17; else goto _L16
_L16:
        obj = null;
          goto _L18
_L15:
        obj = Charsets.ISO_8859_1;
_L20:
        obj = ((ObjectParser) (obj2)).parseAndClose(((InputStream) (obj3)), ((Charset) (obj)), ((Class) (obj1)));
          goto _L19
_L17:
        obj = Charset.forName(((String) (obj)));
          goto _L18
_L26:
        obj = (String)httpresponse.mediaType.parameters.get("charset".toLowerCase());
label0:
        {
            if (obj != null)
            {
                break label0;
            }
            obj = null;
        }
          goto _L20
        obj = Charset.forName(((String) (obj)));
          goto _L20
_L10:
label1:
        {
            if (((Event) (obj2)).id != null)
            {
                break label1;
            }
            LogUtils.w(TAG, "Fetched object with null id from the server.", new Object[0]);
        }
          goto _L11
        if (((Event) (obj2)).privateEventData != null && ((Event) (obj2)).privateEventData.smartMailInfo != null)
        {
            obj2.description = null;
        }
        if (((Event) (obj2)).habitInstance != null && !TextUtils.isEmpty(((Event) (obj2)).habitInstance.parentId))
        {
            obj2.description = null;
        }
        arraylist.add(obj2);
        j = i + 1;
        boolean flag = noLimit;
        i = j;
        if (flag) goto _L11; else goto _L21
_L21:
        i = j;
        if (j < 400) goto _L11; else goto _L22
_L22:
        builder.forceCopy = true;
        executedRequests = ImmutableList.asImmutableList(builder.contents, builder.size);
        return null;
_L8:
        obj = ((Events) (obj)).nextPageToken;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_942;
        }
        list.pageToken = ((String) (obj));
          goto _L23
        builder.forceCopy = true;
        executedRequests = ImmutableList.asImmutableList(builder.contents, builder.size);
        return arraylist;
_L6:
        j = 0;
_L13:
        if (j != 0) goto _L25; else goto _L24
_L24:
        obj = null;
          goto _L19
_L18:
        if (obj != null) goto _L26; else goto _L15
    }

    public volatile Object call()
        throws Exception
    {
        return call();
    }

}
