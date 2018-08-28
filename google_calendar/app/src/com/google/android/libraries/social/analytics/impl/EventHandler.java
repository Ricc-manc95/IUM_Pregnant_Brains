// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.impl;

import android.content.Context;
import android.os.Bundle;
import com.google.android.libraries.social.analytics.AnalyticsEvent;

public interface EventHandler
{

    public abstract boolean handleEvent(AnalyticsEvent analyticsevent, Bundle bundle);

    public abstract void populateBundle(Context context, AnalyticsEvent analyticsevent, Bundle bundle);
}
