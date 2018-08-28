// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis.services;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.ObjectParser;
import com.google.common.base.Platform;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package com.google.api.client.googleapis.services:
//            GoogleClientRequestInitializer, AbstractGoogleClientRequest

public class AbstractGoogleClient
{
    public static class Builder
    {

        public String applicationName;
        public String batchPath;
        public HttpRequestInitializer httpRequestInitializer;
        public final ObjectParser objectParser;
        public String rootUrl;
        public String servicePath;
        public final HttpTransport transport;

        public Builder setApplicationName(String s)
        {
            applicationName = s;
            return this;
        }

        public Builder setBatchPath(String s)
        {
            batchPath = s;
            return this;
        }

        public Builder setRootUrl(String s)
        {
            rootUrl = AbstractGoogleClient.normalizeRootUrl(s);
            return this;
        }

        public Builder setServicePath(String s)
        {
            servicePath = AbstractGoogleClient.normalizeServicePath(s);
            return this;
        }

        public Builder(HttpTransport httptransport, String s, String s1, ObjectParser objectparser, HttpRequestInitializer httprequestinitializer)
        {
            if (httptransport == null)
            {
                throw new NullPointerException();
            } else
            {
                transport = (HttpTransport)httptransport;
                objectParser = objectparser;
                setRootUrl(s);
                setServicePath(s1);
                httpRequestInitializer = httprequestinitializer;
                return;
            }
        }
    }


    private static final Logger logger = Logger.getLogger(com/google/api/client/googleapis/services/AbstractGoogleClient.getName());
    public final String applicationName;
    private final GoogleClientRequestInitializer googleClientRequestInitializer = null;
    private final ObjectParser objectParser;
    public final HttpRequestFactory requestFactory;
    public final String rootUrl;
    public final String servicePath;
    public final boolean suppressRequiredParameterChecks = false;

    public AbstractGoogleClient(Builder builder)
    {
        String s = builder.rootUrl;
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("root URL cannot be null."));
        }
        Object obj = s;
        if (!s.endsWith("/"))
        {
            obj = String.valueOf(s).concat("/");
        }
        rootUrl = ((String) (obj));
        servicePath = normalizeServicePath(builder.servicePath);
        if (Platform.stringIsNullOrEmpty(builder.applicationName))
        {
            logger.logp(Level.WARNING, "com.google.api.client.googleapis.services.AbstractGoogleClient", "<init>", "Application name is not set. Call Builder#setApplicationName.");
        }
        applicationName = builder.applicationName;
        if (builder.httpRequestInitializer == null)
        {
            obj = new HttpRequestFactory(builder.transport, null);
        } else
        {
            obj = new HttpRequestFactory(builder.transport, builder.httpRequestInitializer);
        }
        requestFactory = ((HttpRequestFactory) (obj));
        objectParser = builder.objectParser;
    }

    static String normalizeRootUrl(String s)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("root URL cannot be null."));
        }
        String s1 = s;
        if (!s.endsWith("/"))
        {
            s1 = String.valueOf(s).concat("/");
        }
        return s1;
    }

    static String normalizeServicePath(String s)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("service path cannot be null"));
        }
        String s1;
        if (s.length() == 1)
        {
            if (!"/".equals(s))
            {
                throw new IllegalArgumentException(String.valueOf("service path must equal \"/\" if it is of length 1."));
            }
            s1 = "";
        } else
        {
            s1 = s;
            if (s.length() > 0)
            {
                String s2 = s;
                if (!s.endsWith("/"))
                {
                    s2 = String.valueOf(s).concat("/");
                }
                s1 = s2;
                if (s2.startsWith("/"))
                {
                    return s2.substring(1);
                }
            }
        }
        return s1;
    }

    public ObjectParser getObjectParser()
    {
        return objectParser;
    }

    public void initialize(AbstractGoogleClientRequest abstractgoogleclientrequest)
        throws IOException
    {
        if (googleClientRequestInitializer != null)
        {
            googleClientRequestInitializer.initialize$51666RRD5TJMURR7DHIIUOBGD4NM6R39CLN78BR7DTNMER35C5O6ISPFEDIN4TJ9CDIN6BQ1C9PN8SJ1CDQ4ERRFCTM6AGRCD5IMST2ICLONAPBJEGTIILG_0();
        }
    }

}
