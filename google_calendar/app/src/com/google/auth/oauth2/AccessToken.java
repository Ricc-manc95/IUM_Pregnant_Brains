// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.auth.oauth2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public final class AccessToken
    implements Serializable
{

    public static final long serialVersionUID = 0x89d75850f3d82237L;
    public final Long expirationTimeMillis = null;
    public final String tokenValue;

    public AccessToken(String s, Date date)
    {
        tokenValue = s;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof AccessToken)
        {
            if (Objects.equals(tokenValue, ((AccessToken) (obj = (AccessToken)obj)).tokenValue) && Objects.equals(expirationTimeMillis, ((AccessToken) (obj)).expirationTimeMillis))
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Objects.hash(new Object[] {
            tokenValue, expirationTimeMillis
        });
    }

    public final String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName());
        Object obj = tokenValue;
        com.google.common.base.MoreObjects.ToStringHelper.ValueHolder valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("tokenValue" == null)
        {
            throw new NullPointerException();
        }
        valueholder.name = (String)"tokenValue";
        obj = expirationTimeMillis;
        valueholder = new com.google.common.base.MoreObjects.ToStringHelper.ValueHolder();
        tostringhelper.holderTail.next = valueholder;
        tostringhelper.holderTail = valueholder;
        valueholder.value = obj;
        if ("expirationTimeMillis" == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)"expirationTimeMillis";
            return tostringhelper.toString();
        }
    }
}
