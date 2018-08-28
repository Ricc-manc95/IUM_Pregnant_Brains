// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;

// Referenced classes of package com.google.android.calendar:
//            UpgradeReceiver, Utils

public class SyncUpgradeReceiver extends UpgradeReceiver
{

    public SyncUpgradeReceiver()
    {
    }

    protected final void doUpgrade(Context context)
    {
        context = new Bundle();
        context.putBoolean("force", true);
        Utils.appendSyncFlags(context);
        String s = android.provider.CalendarContract.Calendars.CONTENT_URI.getAuthority();
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            ((FeatureConfig)featureconfig).fishfood_debug();
            ContentResolver.requestSync(null, s, context);
            return;
        }
    }
}
