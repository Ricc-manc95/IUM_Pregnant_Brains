// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.directboot;

import android.content.Context;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;

public class DirectBootUtils
{

    private static volatile boolean isUserUnlocked;
    private static volatile UserManager userManager;

    private DirectBootUtils()
    {
    }

    public static boolean checkUserUnlocked(Context context)
    {
        boolean flag;
        boolean flag1;
        flag1 = isUserUnlocked;
        flag = flag1;
        if (flag1) goto _L2; else goto _L1
_L1:
        int i;
        i = 1;
        flag = flag1;
_L8:
        if (i > 2) goto _L4; else goto _L3
_L3:
        UserManager usermanager = getUserManager(context);
        if (usermanager != null) goto _L6; else goto _L5
_L5:
        isUserUnlocked = true;
        flag = true;
_L2:
        return flag;
_L6:
        flag1 = flag;
        if (usermanager.isUserUnlocked())
        {
            break MISSING_BLOCK_LABEL_126;
        }
        flag1 = flag;
        break MISSING_BLOCK_LABEL_53;
_L7:
        flag1 = flag;
        isUserUnlocked = flag;
        flag1 = flag;
_L9:
        flag = flag1;
        if (flag1)
        {
            userManager = null;
            return flag1;
        }
          goto _L2
        if (!usermanager.isUserRunning(Process.myUserHandle()))
        {
            break MISSING_BLOCK_LABEL_126;
        }
        flag = false;
          goto _L7
        NullPointerException nullpointerexception;
        nullpointerexception;
        Log.w("DirectBootUtils", "Failed to check if user is unlocked", nullpointerexception);
        userManager = null;
        i++;
        flag = flag1;
          goto _L8
_L4:
        flag1 = flag;
          goto _L9
        flag = true;
          goto _L7
    }

    private static UserManager getUserManager(Context context)
    {
        UserManager usermanager;
        usermanager = userManager;
        if (usermanager != null)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        com/google/android/libraries/directboot/DirectBootUtils;
        JVM INSTR monitorenter ;
        UserManager usermanager1 = userManager;
        usermanager = usermanager1;
        if (usermanager1 != null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        usermanager = (UserManager)context.getSystemService(android/os/UserManager);
        userManager = usermanager;
        com/google/android/libraries/directboot/DirectBootUtils;
        JVM INSTR monitorexit ;
        return usermanager;
        context;
        com/google/android/libraries/directboot/DirectBootUtils;
        JVM INSTR monitorexit ;
        throw context;
        return usermanager;
    }

    public static boolean supportsDirectBoot()
    {
        return android.os.Build.VERSION.SDK_INT >= 24;
    }

    static 
    {
        boolean flag1 = true;
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            flag1 = false;
        }
        isUserUnlocked = flag1;
    }
}
