// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate;
import java.util.Iterator;
import java.util.List;

public final class InstalledAppsPredicate
    implements PartialTriggeringConditionsPredicate
{

    private static final Logger logger = new Logger();
    private final PackageManager packageManager;

    InstalledAppsPredicate(Context context)
    {
        packageManager = context.getPackageManager();
    }

    private final boolean apply$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH4AHP6IPR7CLP6IRJ7A9QMOP94AHP6IPR7CLP6IRJ78DNMSP39EHKMURJJ7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5TO74PB4D5HM2T35ECNL8SJ9CTJMASJ9DPJK6RRECHKN8QBFDPPL0SJ5CHKM6OBKCKI58SJ9CTJMASJ9DPJK6RRECHKN8QBFDPPKATJ1DH1MURJKCLS78EP9B8______0(com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringConditions triggeringconditions)
    {
        Iterator iterator;
        if (triggeringconditions.installedApp_.isEmpty())
        {
            return true;
        }
        iterator = triggeringconditions.installedApp_.iterator();
_L7:
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        com.google.identity.growth.proto.Promotion.InstalledAppCondition installedappcondition = (com.google.identity.growth.proto.Promotion.InstalledAppCondition)iterator.next();
        Object obj;
        com.google.identity.growth.proto.Promotion.InstalledAppCondition.InstallStatus installstatus;
        Object obj1;
        int i;
        if (installedappcondition.appId_ == null)
        {
            triggeringconditions = com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier.DEFAULT_INSTANCE;
        } else
        {
            triggeringconditions = installedappcondition.appId_;
        }
        if (((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier) (triggeringconditions)).appIdCase_ == 4)
        {
            triggeringconditions = (String)((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier) (triggeringconditions)).appId_;
        } else
        {
            triggeringconditions = "";
        }
        if (installedappcondition.appId_ == null)
        {
            obj = com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier.DEFAULT_INSTANCE;
        } else
        {
            obj = installedappcondition.appId_;
        }
        if (TextUtils.isEmpty(((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier) (obj)).appVersion_))
        {
            i = 0;
        } else
        {
            if (installedappcondition.appId_ == null)
            {
                obj = com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier.DEFAULT_INSTANCE;
            } else
            {
                obj = installedappcondition.appId_;
            }
            i = Integer.parseInt(((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier) (obj)).appVersion_);
        }
        try
        {
            obj = packageManager.getPackageInfo(triggeringconditions, 0);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj = null;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            logger.w(((Throwable) (obj)), "getPackageInfo(%s) failed", new Object[] {
                triggeringconditions
            });
            return false;
        }
        obj1 = com.google.identity.growth.proto.Promotion.InstalledAppCondition.InstallStatus.forNumber(installedappcondition.requiredInstallStatus_);
        installstatus = ((com.google.identity.growth.proto.Promotion.InstalledAppCondition.InstallStatus) (obj1));
        if (obj1 == null)
        {
            installstatus = com.google.identity.growth.proto.Promotion.InstalledAppCondition.InstallStatus.UNKNOWN;
        }
        installstatus.ordinal();
        JVM INSTR tableswitch 1 2: default 160
    //                   1 298
    //                   2 283;
           goto _L1 _L2 _L3
_L2:
        continue; /* Loop/switch isn't completed */
_L1:
        obj1 = logger;
        installstatus = com.google.identity.growth.proto.Promotion.InstalledAppCondition.InstallStatus.forNumber(installedappcondition.requiredInstallStatus_);
        obj = installstatus;
        if (installstatus == null)
        {
            obj = com.google.identity.growth.proto.Promotion.InstalledAppCondition.InstallStatus.UNKNOWN;
        }
        ((Logger) (obj1)).w("Invalid InstallStatus for %s: %s", new Object[] {
            triggeringconditions, obj
        });
        break; /* Loop/switch isn't completed */
_L3:
        if (obj != null && ((PackageInfo) (obj)).versionCode >= i) goto _L5; else goto _L4
_L5:
        if (true) goto _L7; else goto _L6
_L4:
        return false;
        if (obj == null || ((PackageInfo) (obj)).versionCode < i) goto _L7; else goto _L8
_L8:
        return false;
_L6:
        return true;
    }

    public final volatile boolean apply(Object obj, Object obj1)
    {
        return apply$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH4AHP6IPR7CLP6IRJ7A9QMOP94AHP6IPR7CLP6IRJ78DNMSP39EHKMURJJ7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5TO74PB4D5HM2T35ECNL8SJ9CTJMASJ9DPJK6RRECHKN8QBFDPPL0SJ5CHKM6OBKCKI58SJ9CTJMASJ9DPJK6RRECHKN8QBFDPPKATJ1DH1MURJKCLS78EP9B8______0((com.google.identity.growth.proto.Promotion.TriggeringRule.TriggeringConditions)obj);
    }

    public final com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType getTriggeringConditionType()
    {
        return com.google.android.libraries.internal.growth.growthkit.internal.predicates.PartialTriggeringConditionsPredicate.TriggeringConditionType.INSTALLED_APPS;
    }

}
