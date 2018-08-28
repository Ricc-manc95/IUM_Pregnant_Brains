// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.customtabs;

import android.content.Intent;
import android.os.Bundle;

public final class CustomTabsIntent
{

    public final Intent intent;
    public final Bundle startAnimationBundle;

    CustomTabsIntent(Intent intent1, Bundle bundle)
    {
        intent = intent1;
        startAnimationBundle = bundle;
    }
}
