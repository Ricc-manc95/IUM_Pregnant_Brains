// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.conscrypt;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.security.ProviderInstaller;

// Referenced classes of package com.google.android.apps.calendar.util.conscrypt:
//            ConscryptInstallationException

public class ConscryptUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/util/conscrypt/ConscryptUtils);

    public ConscryptUtils()
    {
    }

    public static void installSecurityProvider(Context context)
        throws ConscryptInstallationException
    {
        try
        {
            ProviderInstaller.installIfNeeded(context);
            return;
        }
        catch (GooglePlayServicesRepairableException googleplayservicesrepairableexception)
        {
            LogUtils.e(TAG, googleplayservicesrepairableexception, "Repairable error from installIfNeeded, in installSecurityProvider", new Object[0]);
            GooglePlayServicesUtil.showErrorNotification(googleplayservicesrepairableexception.zzajN, context);
            throw new ConscryptInstallationException("Error from installSecurityProvider", googleplayservicesrepairableexception);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.e(TAG, context, "Unrecoverable error from installIfNeeded, in installSecurityProvider", new Object[0]);
        }
        throw new ConscryptInstallationException("Error from installSecurityProvider", context);
    }

}
