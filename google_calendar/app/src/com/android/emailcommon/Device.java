// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.emailcommon;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.util.regex.Pattern;

public abstract class Device
{

    private static String deviceId = null;
    private static Device singleton = new DeviceImpl();

    Device()
    {
    }

    private static String generateDeviceId(Context context, String s, boolean flag)
        throws IOException
    {
        com/android/emailcommon/Device;
        JVM INSTR monitorenter ;
        if (deviceId != null) goto _L2; else goto _L1
_L1:
        Device device = singleton;
        if (context != null)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        throw new IllegalStateException("getDeviceId requires a Context");
        context;
        com/android/emailcommon/Device;
        JVM INSTR monitorexit ;
        throw context;
        s = device.getDeviceIdFromStorage(context);
        if (s == null) goto _L4; else goto _L3
_L3:
        deviceId = s;
_L2:
        context = deviceId;
        com/android/emailcommon/Device;
        JVM INSTR monitorexit ;
        return context;
_L4:
        if (!TextUtils.isEmpty(null))
        {
            break MISSING_BLOCK_LABEL_145;
        }
        s = device.getConsistentDeviceId(context);
        if (s == null) goto _L6; else goto _L5
_L5:
        s = String.valueOf(s);
        if (s.length() == 0) goto _L8; else goto _L7
_L7:
        s = "androidc".concat(s);
_L9:
        device.storeDeviceIdToStorage(context, s);
          goto _L3
_L8:
        s = new String("androidc");
          goto _L9
_L6:
        long l = System.currentTimeMillis();
        s = (new StringBuilder(27)).append("android").append(l).toString();
          goto _L9
        s = null;
          goto _L9
    }

    public static String getDeviceId(Context context)
        throws IOException
    {
        return generateDeviceId(context, null, false);
    }

    protected abstract String getConsistentDeviceId(Context context);

    protected abstract String getDeviceIdFromStorage(Context context)
        throws IOException;

    protected abstract void storeDeviceIdToStorage(Context context, String s)
        throws IOException;

    static 
    {
        Pattern.compile("[a-zA-Z0-9]{1,32}");
    }

    private class DeviceImpl extends Device
    {

        protected final String getConsistentDeviceId(Context context)
        {
            try
            {
                context = android.provider.Settings.Secure.getString(context.getContentResolver(), "android_id");
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

        DeviceImpl()
        {
        }
    }

}
