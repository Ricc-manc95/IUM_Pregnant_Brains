// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon;

import android.content.Context;
import com.android.emailcommon.utility.Utility;
import com.android.mail.log.LogUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Referenced classes of package com.android.emailcommon:
//            Device, Logging

final class  extends Device
{

    protected final String getConsistentDeviceId(Context context)
    {
        try
        {
            context = android.provider.tString(context.getContentResolver(), "android_id");
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.e(Logging.LOG_TAG, context, "Error getting AndroidId", new Object[0]);
            return null;
        }
        if (context == null)
        {
            return null;
        } else
        {
            return Utility.getSmallHash(context);
        }
    }

    protected final String getDeviceIdFromStorage(Context context)
        throws IOException
    {
        File file = context.getFileStreamPath("deviceName");
        if (!file.exists()) goto _L2; else goto _L1
_L1:
        if (!file.canRead()) goto _L4; else goto _L3
_L3:
        String s;
        context = new BufferedReader(new FileReader(file), 128);
        s = context.readLine();
        context.close();
        context = s;
        if (s != null) goto _L6; else goto _L5
_L5:
        if (!file.delete())
        {
            LogUtils.e(Logging.LOG_TAG, "Can't delete null deviceName file; try overwrite.", new Object[0]);
        }
_L2:
        context = null;
_L6:
        return context;
_L4:
        LogUtils.w(Logging.LOG_TAG, String.valueOf(file.getAbsolutePath()).concat(": File exists, but can't read?  Trying to remove."), new Object[0]);
        if (!file.delete())
        {
            LogUtils.w(Logging.LOG_TAG, "Remove failed. Tring to overwrite.", new Object[0]);
        }
        if (true) goto _L2; else goto _L7
_L7:
    }

    protected final void storeDeviceIdToStorage(Context context, String s)
        throws IOException
    {
        context = new BufferedWriter(new FileWriter(context.getFileStreamPath("deviceName")), 128);
        context.write(s);
        context.close();
    }

    ()
    {
    }
}
