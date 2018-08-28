// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.auth.oauth2;

import com.google.api.client.util.Clock;
import com.google.auth.Credentials;
import com.google.auth.RequestMetadataCallback;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.auth.oauth2:
//            AccessToken

public class OAuth2Credentials extends Credentials
{

    public static final long serialVersionUID = 0x3f3d7d7ae9a55157L;
    private transient Clock clock;
    private final Object lock;
    private Map requestMetadata;
    private AccessToken temporaryAccess;

    protected OAuth2Credentials()
    {
        this(null);
    }

    public OAuth2Credentials(AccessToken accesstoken)
    {
        lock = new byte[0];
        clock = Clock.SYSTEM;
        if (accesstoken != null)
        {
            useAccessToken(accesstoken);
        }
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        clock = Clock.SYSTEM;
    }

    private final boolean shouldRefresh()
    {
        Long long1 = null;
        if (temporaryAccess != null)
        {
            Object obj = temporaryAccess;
            if (((AccessToken) (obj)).expirationTimeMillis == null)
            {
                obj = null;
            } else
            {
                obj = new Date(((AccessToken) (obj)).expirationTimeMillis.longValue());
            }
            if (obj != null)
            {
                long1 = Long.valueOf(((Date) (obj)).getTime() - clock.currentTimeMillis());
            }
        }
        return requestMetadata == null || long1 != null && long1.longValue() <= 0x493e0L;
    }

    private final void useAccessToken(AccessToken accesstoken)
    {
        temporaryAccess = accesstoken;
        String s = String.valueOf("Bearer ");
        accesstoken = String.valueOf(accesstoken.tokenValue);
        if (accesstoken.length() != 0)
        {
            accesstoken = s.concat(accesstoken);
        } else
        {
            accesstoken = new String(s);
        }
        requestMetadata = Collections.singletonMap("Authorization", Collections.singletonList(accesstoken));
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof OAuth2Credentials)
        {
            if (Objects.equals(requestMetadata, ((OAuth2Credentials) (obj = (OAuth2Credentials)obj)).requestMetadata) && Objects.equals(temporaryAccess, ((OAuth2Credentials) (obj)).temporaryAccess))
            {
                return true;
            }
        }
        return false;
    }

    public final void getRequestMetadata(URI uri, Executor executor, RequestMetadataCallback requestmetadatacallback)
    {
label0:
        {
            synchronized (lock)
            {
                if (!shouldRefresh())
                {
                    break label0;
                }
                super.getRequestMetadata(uri, executor, requestmetadatacallback);
            }
            return;
        }
        uri = requestMetadata;
        if (uri != null)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        throw new NullPointerException(String.valueOf("cached requestMetadata"));
        uri;
        obj;
        JVM INSTR monitorexit ;
        throw uri;
        uri = (Map)uri;
        obj;
        JVM INSTR monitorexit ;
        requestmetadatacallback.onSuccess(uri);
        return;
    }

    public final Map getRequestMetadata$5166KOBMC4NMSPBK5TAL4I9R5566KOBMC4NNAT39DGNKQOBG7C______0()
        throws IOException
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
        if (!shouldRefresh())
        {
            break MISSING_BLOCK_LABEL_51;
        }
        Object obj1 = lock;
        obj1;
        JVM INSTR monitorenter ;
        requestMetadata = null;
        temporaryAccess = null;
        throw new IllegalStateException("OAuth2Credentials instance does not support refreshing the access token. An instance with a new access token should be used, or a derived type that supports refreshing.");
        Exception exception1;
        exception1;
        obj1;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        exception = requestMetadata;
        if (exception != null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        throw new NullPointerException(String.valueOf("requestMetadata"));
        exception = (Map)exception;
        obj;
        JVM INSTR monitorexit ;
        return exception;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            requestMetadata, temporaryAccess
        });
    }

    public String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName());
        Object obj = requestMetadata;
        com.google.common.base.MoreObjects.ToStringHelper.ValueHolder valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("requestMetadata" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"requestMetadata";
        obj = temporaryAccess;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("temporaryAccess" == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)"temporaryAccess";
            return tostringhelper.toString();
        }
    }
}
