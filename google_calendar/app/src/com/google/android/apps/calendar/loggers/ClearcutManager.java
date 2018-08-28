// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.apps.calendar.loggers.rlz.RlzConfig;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.events.GrowthKitEventManager;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKit;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.apps.docs.diagnostics.impressions.proto.sessioninvariants.DistributionInvariants;
import com.google.common.base.Platform;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;

// Referenced classes of package com.google.android.apps.calendar.loggers:
//            CalendarClearcutLogger

public final class ClearcutManager
{

    private static ClearcutManager clearcutManager;
    public final CalendarClearcutLogger calendarClearcutLogger;
    public final ClearcutLogger clearcutLogger;
    public final Context context;
    public final RlzConfig rlzConfig;

    private ClearcutManager(Context context1, CalendarClearcutLogger calendarclearcutlogger, RlzConfig rlzconfig)
    {
        calendarClearcutLogger = calendarclearcutlogger;
        rlzConfig = rlzconfig;
        clearcutLogger = new ClearcutLogger(context1, "CALENDAR", null);
        context = context1;
    }

    public static com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto createExtensionProto(RlzConfig rlzconfig, com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType actiontype)
    {
        com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.Builder builder = (com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        builder.copyOnWrite();
        com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto androidcalendarextensionproto = (com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto)builder.instance;
        if (actiontype == null)
        {
            throw new NullPointerException();
        }
        androidcalendarextensionproto.bitField0_ = androidcalendarextensionproto.bitField0_ | 1;
        androidcalendarextensionproto.actionType_ = actiontype.value;
        boolean flag;
        if (!TextUtils.isEmpty(rlzconfig.brandCode) && !rlzconfig.accessPoints.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            actiontype = (com.google.apps.docs.diagnostics.impressions.proto.sessioninvariants.DistributionInvariants.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)DistributionInvariants.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            Object obj = Platform.nullToEmpty(rlzconfig.brandCode);
            actiontype.copyOnWrite();
            DistributionInvariants distributioninvariants = (DistributionInvariants)((com.google.apps.docs.diagnostics.impressions.proto.sessioninvariants.DistributionInvariants.Builder) (actiontype)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            distributioninvariants.bitField0_ = distributioninvariants.bitField0_ | 1;
            distributioninvariants.rlzBrandCode_ = ((String) (obj));
            if (rlzconfig.isPreInstalled)
            {
                rlzconfig = com.google.apps.docs.diagnostics.impressions.proto.sessioninvariants.DistributionInvariants.DistributionType.ANDROID_SYSTEM_PARTITION;
            } else
            {
                rlzconfig = com.google.apps.docs.diagnostics.impressions.proto.sessioninvariants.DistributionInvariants.DistributionType.UNKNOWN_DISTRIBUTION_TYPE;
            }
            actiontype.copyOnWrite();
            obj = (DistributionInvariants)((com.google.apps.docs.diagnostics.impressions.proto.sessioninvariants.DistributionInvariants.Builder) (actiontype)).instance;
            if (rlzconfig == null)
            {
                throw new NullPointerException();
            }
            obj.bitField0_ = ((DistributionInvariants) (obj)).bitField0_ | 2;
            obj.distributionType_ = ((com.google.apps.docs.diagnostics.impressions.proto.sessioninvariants.DistributionInvariants.DistributionType) (rlzconfig)).value;
            builder.copyOnWrite();
            rlzconfig = (com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto)builder.instance;
            rlzconfig.distributionInvariants_ = (DistributionInvariants)(GeneratedMessageLite)actiontype.build();
            rlzconfig.bitField0_ = ((com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto) (rlzconfig)).bitField0_ | 2;
        }
        return (com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto)(GeneratedMessageLite)builder.build();
    }

    public static ClearcutManager getInstance(Context context1, boolean flag)
    {
        if (clearcutManager == null)
        {
            Context context2 = context1.getApplicationContext();
            if (CalendarClearcutLogger.calendarClearcutLogger == null)
            {
                CalendarClearcutLogger.calendarClearcutLogger = new CalendarClearcutLogger(context2);
            }
            clearcutManager = new ClearcutManager(context1, CalendarClearcutLogger.calendarClearcutLogger, RlzConfig.getInstance(flag));
        }
        return clearcutManager;
    }

    public final void logAction(com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType actiontype, String s)
    {
        Object obj;
        Object obj1;
        obj = null;
        com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto androidcalendarextensionproto = createExtensionProto(rlzConfig, actiontype);
        obj1 = new com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder(clearcutLogger, androidcalendarextensionproto.toByteArray());
        int i = actiontype.value;
        ((com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder) (obj1)).zzaHz.eventCode = i;
        if (TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        Account aaccount[];
        int j;
        int k;
        aaccount = AccountsUtil.getGoogleAccounts(context);
        k = aaccount.length;
        j = 0;
_L4:
        if (j < k)
        {
label0:
            {
                {
                    Account account = aaccount[j];
                    if (!s.equals(account.name))
                    {
                        break label0;
                    }
                    calendarClearcutLogger.logEvent(((com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder) (obj1)), account);
                    obj1 = context;
                    j = actiontype.value;
                    s = account.name;
                    try
                    {
                        actiontype = GrowthKit.get(((Context) (obj1)));
                    }
                    // Misplaced declaration of an exception variable
                    catch (com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType actiontype)
                    {
                        actiontype = obj;
                    }
                    if (actiontype != null)
                    {
                        actiontype.getGrowthKitEventManager().reportClearCutEventAsync(111, j, s);
                    }
                }
                return;
            }
            j++;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        calendarClearcutLogger.logEvent(((com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder) (obj1)));
        s = context;
        j = actiontype.value;
        try
        {
            actiontype = GrowthKit.get(s);
        }
        // Misplaced declaration of an exception variable
        catch (com.google.android.calendar.CalendarLoggingExtension.AndroidCalendarExtensionProto.ActionType actiontype)
        {
            actiontype = null;
        }
        if (actiontype == null)
        {
            break MISSING_BLOCK_LABEL_152;
        }
        actiontype.getGrowthKitEventManager().reportClearCutEventAsync(111, j, null);
        return;
        if (true) goto _L4; else goto _L3
_L3:
    }
}
