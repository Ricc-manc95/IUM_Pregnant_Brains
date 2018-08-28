// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apiary;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.android.common.http.Rule;
import com.google.android.common.http.RuleMatcher;
import com.google.android.common.http.UrlRules;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.util.GenericData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.apiary:
//            AuthenticationException

public final class GoogleRequestInitializer
    implements HttpExecuteInterceptor, HttpRequestInitializer, HttpUnsuccessfulResponseHandler
{

    public String accountName;
    public String authToken;
    private final Context context;
    private final String logTag;
    private int requestConnectTimeout;
    private int requestReadTimeout;
    private final String scope;
    private final String syncAuthority;
    public String userAgentString;

    public GoogleRequestInitializer(Context context1, String s, String s1)
    {
        this(context1, s, s1, null);
    }

    public GoogleRequestInitializer(Context context1, String s, String s1, String s2)
    {
        authToken = null;
        requestConnectTimeout = 0;
        requestReadTimeout = 0;
        context = context1;
        scope = s;
        logTag = s1;
        syncAuthority = s2;
    }

    public final String getAuthToken()
        throws AuthenticationException
    {
        if (authToken != null) goto _L2; else goto _L1
_L1:
        if (syncAuthority == null) goto _L4; else goto _L3
_L3:
        authToken = GoogleAuthUtil.getTokenWithNotification(context, accountName, scope, null, syncAuthority, null);
_L2:
        return authToken;
_L4:
        try
        {
            authToken = GoogleAuthUtil.getTokenWithNotification(context, accountName, scope, null);
        }
        catch (IOException ioexception)
        {
            throw new AuthenticationException("Could not get an auth token", ioexception);
        }
        catch (GoogleAuthException googleauthexception)
        {
            throw new AuthenticationException("Could not get an auth token", googleauthexception);
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public final boolean handleResponse(HttpRequest httprequest, HttpResponse httpresponse, boolean flag)
        throws IOException
    {
        if (httpresponse.statusCode == 401)
        {
            GoogleAuthUtil.invalidateToken(context, authToken);
            authToken = null;
            return true;
        } else
        {
            return false;
        }
    }

    public final void initialize(HttpRequest httprequest)
    {
        httprequest.executeInterceptor = this;
        httprequest.unsuccessfulResponseHandler = this;
        httprequest.numRetries = 1;
    }

    public final void intercept(HttpRequest httprequest)
        throws IOException
    {
        String s = null;
        Object obj1 = getAuthToken();
        HttpHeaders httpheaders = httprequest.headers;
        Object obj = String.valueOf("OAuth ");
        obj1 = String.valueOf(obj1);
        Object obj2;
        String s1;
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj1 = new ArrayList();
            ((List) (obj1)).add(obj);
            obj = obj1;
        }
        httpheaders.authorization = ((List) (obj));
        obj1 = httprequest.url;
        s1 = ((GenericUrl) (obj1)).build();
        obj = context.getContentResolver();
        obj = com.google.android.common.http.UrlRules.UrlRuleFetcher.instance.getRules(((android.content.ContentResolver) (obj)));
        if (((UrlRules) (obj)).ruleMatcher == null)
        {
            obj.ruleMatcher = new RuleMatcher(((UrlRules) (obj)).rules);
        }
        obj2 = ((UrlRules) (obj)).ruleMatcher.match(s1);
        obj = obj2;
        if (obj2 == null)
        {
            obj = Rule.DEFAULT;
        }
        obj2 = ((Rule) (obj)).apply(s1);
        if (obj2 == null)
        {
            httprequest = logTag;
            s = ((Rule) (obj)).name;
            Log.w(httprequest, (new StringBuilder(String.valueOf(s).length() + 13 + String.valueOf(s1).length())).append("Blocked by ").append(s).append(": ").append(s1).toString());
            throw new BlockedRequestException(((Rule) (obj)));
        }
        obj = obj1;
        if (!((String) (obj2)).equals(s1))
        {
            obj = new GenericUrl(((String) (obj2)));
            if (obj == null)
            {
                throw new NullPointerException();
            }
            httprequest.url = (GenericUrl)obj;
        }
        obj1 = (String)((GenericUrl) (obj)).getFirst("ifmatch");
        if (obj1 != null)
        {
            if (obj1 == null)
            {
                httprequest = null;
            } else
            {
                httprequest = new ArrayList();
                httprequest.add(obj1);
            }
            httpheaders.ifMatch = httprequest;
            ((GenericData) (obj)).remove("ifmatch");
        }
        httprequest = (new StringBuilder(Build.FINGERPRINT)).append(":");
        if (userAgentString != null)
        {
            httprequest.append(userAgentString);
        } else
        {
            httprequest.append(logTag);
        }
        obj1 = (String)((GenericUrl) (obj)).getFirst("userAgentPackage");
        if (obj1 != null)
        {
            httprequest.append(":").append(((String) (obj1)));
            ((GenericData) (obj)).remove("userAgentPackage");
        }
        obj = httprequest.toString();
        if (obj == null)
        {
            httprequest = s;
        } else
        {
            httprequest = new ArrayList();
            httprequest.add(obj);
        }
        httpheaders.userAgent = httprequest;
    }

    private class BlockedRequestException extends IOException
    {

        BlockedRequestException(Rule rule)
        {
            rule = String.valueOf(rule.name);
            if (rule.length() != 0)
            {
                rule = "Blocked by rule: ".concat(rule);
            } else
            {
                rule = new String("Blocked by rule: ");
            }
            super(rule);
        }
    }

}
