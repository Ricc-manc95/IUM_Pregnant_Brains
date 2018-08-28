// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.auth.oauth2;


// Referenced classes of package com.google.auth.oauth2:
//            OAuth2Credentials, DefaultCredentialsProvider, AccessToken

public class GoogleCredentials extends OAuth2Credentials
{

    public static final long serialVersionUID = 0xeaddbdc5a2e15f25L;

    protected GoogleCredentials()
    {
        this(null);
    }

    public GoogleCredentials(AccessToken accesstoken)
    {
        super(accesstoken);
    }

    static 
    {
        new DefaultCredentialsProvider();
    }
}
