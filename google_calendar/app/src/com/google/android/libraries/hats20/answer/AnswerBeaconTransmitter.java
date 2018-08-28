// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.answer;

import android.os.AsyncTask;
import com.google.android.libraries.hats20.cookie.HatsCookieManager;
import java.util.concurrent.Executor;

public final class AnswerBeaconTransmitter
{

    public final String answerUrl;
    public final Executor executor;
    public final HatsCookieManager hatsCookieManager;

    public AnswerBeaconTransmitter(String s, HatsCookieManager hatscookiemanager)
    {
        this(s, hatscookiemanager, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private AnswerBeaconTransmitter(String s, HatsCookieManager hatscookiemanager, Executor executor1)
    {
        if (s == null)
        {
            throw new NullPointerException("Answer URL was missing");
        }
        if (hatscookiemanager == null)
        {
            throw new NullPointerException("HaTS cookie store was missing");
        }
        if (executor1 == null)
        {
            throw new NullPointerException("Executor was missing");
        } else
        {
            answerUrl = s;
            hatsCookieManager = hatscookiemanager;
            executor = executor1;
            return;
        }
    }
}
