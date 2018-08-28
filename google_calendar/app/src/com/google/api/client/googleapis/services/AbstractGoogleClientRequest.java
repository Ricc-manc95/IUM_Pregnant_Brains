// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis.services;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.util.GenericData;
import com.google.common.base.Strings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.api.client.googleapis.services:
//            AbstractGoogleClient

public class AbstractGoogleClientRequest extends GenericData
{

    public final AbstractGoogleClient abstractGoogleClient;
    public final HttpContent httpContent;
    public HttpHeaders requestHeaders;
    public final String requestMethod;
    public Class responseClass;
    public final String uriTemplate;

    public AbstractGoogleClientRequest(AbstractGoogleClient abstractgoogleclient, String s, String s1, HttpContent httpcontent, Class class1)
    {
        Object obj = null;
        super();
        requestHeaders = new HttpHeaders();
        if (class1 == null)
        {
            throw new NullPointerException();
        }
        responseClass = (Class)class1;
        if (abstractgoogleclient == null)
        {
            throw new NullPointerException();
        }
        abstractGoogleClient = (AbstractGoogleClient)abstractgoogleclient;
        if (s == null)
        {
            throw new NullPointerException();
        }
        requestMethod = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        uriTemplate = (String)s1;
        httpContent = httpcontent;
        abstractgoogleclient = abstractgoogleclient.applicationName;
        if (abstractgoogleclient != null)
        {
            s = requestHeaders;
            s1 = (new StringBuilder(String.valueOf(abstractgoogleclient).length() + 23)).append(abstractgoogleclient).append(" Google-API-Java-Client").toString();
            if (s1 == null)
            {
                abstractgoogleclient = null;
            } else
            {
                abstractgoogleclient = new ArrayList();
                abstractgoogleclient.add(s1);
            }
            s.userAgent = abstractgoogleclient;
            return;
        }
        s = requestHeaders;
        if ("Google-API-Java-Client" == null)
        {
            abstractgoogleclient = obj;
        } else
        {
            abstractgoogleclient = new ArrayList();
            abstractgoogleclient.add("Google-API-Java-Client");
        }
        s.userAgent = abstractgoogleclient;
    }

    public final void checkRequiredParameter(Object obj, String s)
    {
        boolean flag;
        if (abstractGoogleClient.suppressRequiredParameterChecks || obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("Required parameter %s must be specified", new Object[] {
                s
            }));
        } else
        {
            return;
        }
    }

    public AbstractGoogleClient getAbstractGoogleClient()
    {
        return abstractGoogleClient;
    }

    public IOException newExceptionOnError(HttpResponse httpresponse)
    {
        return new HttpResponseException(httpresponse);
    }

    public AbstractGoogleClientRequest set(String s, Object obj)
    {
        return (AbstractGoogleClientRequest)super.set(s, obj);
    }

    public volatile GenericData set(String s, Object obj)
    {
        return set(s, obj);
    }
}
