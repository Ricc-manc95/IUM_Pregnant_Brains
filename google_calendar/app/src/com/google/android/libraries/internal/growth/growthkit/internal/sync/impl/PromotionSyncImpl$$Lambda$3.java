// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.common.base.Function;
import java.util.Locale;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl:
//            PromotionSyncImpl

final class arg._cls1
    implements Function
{

    private final PromotionSyncImpl arg$1;

    public final Object apply(Object obj)
    {
        PromotionSyncImpl promotionsyncimpl = arg$1;
        obj = (SharedPreferences)obj;
        Locale locale = promotionsyncimpl.context.getResources().getConfiguration().locale;
        ((SharedPreferences) (obj)).edit().String("SYNC_LANGUAGE", locale.toLanguageTag()).Long("LAST_SYNC_TIME", promotionsyncimpl.clock.currentTimeMillis()).ly();
        return null;
    }

    (PromotionSyncImpl promotionsyncimpl)
    {
        arg$1 = promotionsyncimpl;
    }
}
