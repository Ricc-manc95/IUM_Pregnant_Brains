// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common;

import android.content.Intent;

public class GcoreUserRecoverableException extends Exception
{

    public GcoreUserRecoverableException(String s, Intent intent, Throwable throwable)
    {
        super(s, throwable);
    }
}
