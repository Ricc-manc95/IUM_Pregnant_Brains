// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis.services.json;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.AbstractGoogleClient;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

// Referenced classes of package com.google.api.client.googleapis.services.json:
//            AbstractGoogleJsonClient

public class AbstractGoogleJsonClientRequest extends AbstractGoogleClientRequest
{

    public AbstractGoogleJsonClientRequest(AbstractGoogleJsonClient abstractgooglejsonclient, String s, String s1, Object obj, Class class1)
    {
        if (obj == null)
        {
            obj = null;
        } else
        {
            JsonHttpContent jsonhttpcontent = new JsonHttpContent(((JsonObjectParser)abstractgooglejsonclient.getObjectParser()).jsonFactory, obj);
            if (Collections.unmodifiableSet(((JsonObjectParser)abstractgooglejsonclient.getObjectParser()).wrapperKeys).isEmpty())
            {
                obj = null;
            } else
            {
                obj = "data";
            }
            jsonhttpcontent.wrapperKey = ((String) (obj));
            obj = jsonhttpcontent;
        }
        super(abstractgooglejsonclient, s, s1, ((com.google.api.client.http.HttpContent) (obj)), class1);
    }

    public volatile AbstractGoogleClient getAbstractGoogleClient()
    {
        return getAbstractGoogleClient();
    }

    public AbstractGoogleJsonClient getAbstractGoogleClient()
    {
        return (AbstractGoogleJsonClient)super.getAbstractGoogleClient();
    }

    protected final IOException newExceptionOnError(HttpResponse httpresponse)
    {
        return GoogleJsonResponseException.from(((JsonObjectParser)((AbstractGoogleJsonClient)getAbstractGoogleClient()).getObjectParser()).jsonFactory, httpresponse);
    }

    public volatile AbstractGoogleClientRequest set(String s, Object obj)
    {
        return (AbstractGoogleJsonClientRequest)set(s, obj);
    }

    public AbstractGoogleJsonClientRequest set(String s, Object obj)
    {
        return (AbstractGoogleJsonClientRequest)super.set(s, obj);
    }

    public volatile GenericData set(String s, Object obj)
    {
        return set(s, obj);
    }
}
