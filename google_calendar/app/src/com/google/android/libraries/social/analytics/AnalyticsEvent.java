// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics;

import android.content.Context;

// Referenced classes of package com.google.android.libraries.social.analytics:
//            AuthenticationProvider

public interface AnalyticsEvent
{

    public abstract String getAccountName(Context context, AuthenticationProvider authenticationprovider);
}
