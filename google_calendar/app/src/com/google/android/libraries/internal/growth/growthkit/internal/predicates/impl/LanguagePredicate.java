// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public final class LanguagePredicate
    implements PartialTriggeringConditionsPredicate
{

    private static final Logger logger = new Logger();
    private final Context context;
    private final ListenableFuture sharedPrefsFuture;

    public LanguagePredicate(Context context1, ListenableFuture listenablefuture)
    {
        context = context1;
        sharedPrefsFuture = listenablefuture;
    }

    private final boolean apply$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH4AHP6IPR7CLP6IRJ7A9QMOP94AHP6IPR7CLP6IRJ78DNMSP39EHKMURJJ7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5TO74PB4D5HM2T35ECNL8SJ9CTJMASJ9DPJK6RRECHKN8QBFDPPL0SJ5CHKM6OBKCKI58SJ9CTJMASJ9DPJK6RRECHKN8QBFDPPKATJ1DH1MURJKCLS78EP9B8______0()
    {
        Locale locale = context.getResources().getConfiguration().locale;
        boolean flag = locale.toLanguageTag().equals(((SharedPreferences)sharedPrefsFuture.get()).getString("SYNC_LANGUAGE", null));
        return flag;
        Object obj;
        obj;
_L2:
        logger.w(((Throwable) (obj)), "Failed to retrieve SYNC_LANGUAGE_SHARED_PREFS_KEY from shared preferences.", new Object[0]);
        return false;
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final volatile boolean apply(Object obj, Object obj1)
    {
        return apply$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH4AHP6IPR7CLP6IRJ7A9QMOP94AHP6IPR7CLP6IRJ78DNMSP39EHKMURJJ7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5TO74PB4D5HM2T35ECNL8SJ9CTJMASJ9DPJK6RRECHKN8QBFDPPL0SJ5CHKM6OBKCKI58SJ9CTJMASJ9DPJK6RRECHKN8QBFDPPKATJ1DH1MURJKCLS78EP9B8______0();
    }

    public final com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType getTriggeringConditionType()
    {
        return com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType.LANGUAGE;
    }

}
