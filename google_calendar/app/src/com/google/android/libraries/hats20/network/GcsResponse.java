// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.network;


public final class GcsResponse
{

    public final long expirationDateUnix;
    public final int responseCode;
    public final String surveyJson;

    public GcsResponse(int i, long l, String s)
    {
        responseCode = i;
        expirationDateUnix = l;
        surveyJson = s;
    }
}
