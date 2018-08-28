// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.customtabs;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

// Referenced classes of package android.support.customtabs:
//            CustomTabsIntent, CustomTabsSession

public final class mIntent
{

    private ArrayList mActionButtons;
    private boolean mInstantAppsEnabled;
    public final Intent mIntent;
    private ArrayList mMenuItems;
    private Bundle mStartAnimationBundle;

    public final CustomTabsIntent build()
    {
        mIntent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", mInstantAppsEnabled);
        return new CustomTabsIntent(mIntent, null);
    }

    public _cls9()
    {
        this(null);
    }

    private <init>(CustomTabsSession customtabssession)
    {
        mIntent = new Intent("android.intent.action.VIEW");
        mMenuItems = null;
        mStartAnimationBundle = null;
        mActionButtons = null;
        mInstantAppsEnabled = true;
        customtabssession = new Bundle();
        customtabssession.putBinder("android.support.customtabs.extra.SESSION", null);
        mIntent.putExtras(customtabssession);
    }
}
