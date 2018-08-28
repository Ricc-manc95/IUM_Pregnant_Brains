// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.color.EventColor;
import com.google.api.client.util.Data;
import com.google.api.services.calendar.model.Contract;
import com.google.api.services.calendar.model.DailyPattern;
import com.google.api.services.calendar.model.Habit;
import com.google.api.services.calendar.model.HabitData;
import com.google.api.services.calendar.model.ReminderInstance;
import com.google.api.services.calendar.model.Reminders;
import com.google.api.services.calendar.model.TimePattern;
import com.google.common.base.Platform;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.api.habit:
//            Habit, HabitDescriptor, HabitContract, HabitReminders, 
//            HabitIdTypeUtil, HabitModifications, HabitContractModifications

public class HabitSyncUtils
{

    private static final String HABIT_INSTANCE_COUNT_PROJECTION[] = {
        "IFNULL(COUNT(sync_data8),0) AS _count"
    };
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/habit/HabitSyncUtils);

    public HabitSyncUtils()
    {
    }

    public static Habit apiToServerHabit(com.google.android.calendar.api.habit.Habit habit)
    {
        Object obj;
        Contract contract;
        Object obj1;
        Habit habit1 = new Habit();
        habit1.id = habit.getDescriptor().habitId;
        EventColor eventcolor;
        int i;
        if (habit.getFingerprint() != null)
        {
            obj = habit.getFingerprint();
        } else
        {
            obj = Data.NULL_STRING;
        }
        habit1.etag = ((String) (obj));
        obj1 = new DailyPattern();
        obj1.any = Boolean.valueOf(habit.getContract().isAnyDayTimeAcceptable());
        obj1.morning = Boolean.valueOf(habit.getContract().isMorningPreferable());
        obj1.afternoon = Boolean.valueOf(habit.getContract().isAfternoonPreferable());
        obj1.evening = Boolean.valueOf(habit.getContract().isEveningPreferable());
        contract = new Contract();
        if (habit.getContract().getInterval() == 0) goto _L2; else goto _L1
_L1:
        i = habit.getContract().getInterval();
        i;
        JVM INSTR tableswitch 0 3: default 196
    //                   0 1165
    //                   1 1171
    //                   2 1177
    //                   3 1184;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        LogUtils.e(TAG, "Unrecognized habit api interval, falling back to unknown: %d", new Object[] {
            Integer.valueOf(i)
        });
        obj = "unknown";
_L82:
        contract.interval = ((String) (obj));
_L2:
        contract.durationMinutes = Integer.valueOf(habit.getContract().getDurationMinutes());
        contract.numInstancesPerInterval = Integer.valueOf(habit.getContract().getNumInstancesPerInterval());
        obj = new TimePattern();
        obj.dailyPattern = ((DailyPattern) (obj1));
        contract.timePattern = ((TimePattern) (obj));
        if (habit.getContract().getUntilMillisUtc() > 0L)
        {
            contract.untilMillisUtc = Long.valueOf(habit.getContract().getUntilMillisUtc());
        } else
        {
            contract.untilMillisUtc = Data.NULL_LONG;
        }
        eventcolor = habit.getColorOverride();
        obj1 = new HabitData();
        obj1.customName = habit.getSummary();
        obj1.summary = habit.getSummary();
        i = habit.getType();
        i;
        JVM INSTR lookupswitch 69: default 924
    //                   0: 1201
    //                   256: 1207
    //                   257: 1214
    //                   258: 1221
    //                   259: 1228
    //                   260: 1235
    //                   261: 1242
    //                   262: 1256
    //                   263: 1249
    //                   264: 1263
    //                   265: 1270
    //                   266: 1277
    //                   267: 1284
    //                   268: 1291
    //                   269: 1298
    //                   270: 1305
    //                   512: 1312
    //                   513: 1319
    //                   514: 1326
    //                   515: 1340
    //                   516: 1333
    //                   517: 1347
    //                   518: 1354
    //                   519: 1361
    //                   520: 1368
    //                   521: 1375
    //                   522: 1382
    //                   523: 1389
    //                   524: 1396
    //                   768: 1403
    //                   769: 1410
    //                   770: 1417
    //                   771: 1424
    //                   772: 1431
    //                   773: 1438
    //                   774: 1445
    //                   775: 1452
    //                   776: 1459
    //                   777: 1466
    //                   778: 1473
    //                   779: 1480
    //                   780: 1487
    //                   1024: 1494
    //                   1025: 1501
    //                   1026: 1508
    //                   1027: 1515
    //                   1028: 1522
    //                   1029: 1529
    //                   1030: 1536
    //                   1031: 1543
    //                   1032: 1550
    //                   1033: 1557
    //                   1034: 1564
    //                   1035: 1571
    //                   1036: 1578
    //                   1037: 1585
    //                   1280: 1592
    //                   1281: 1599
    //                   1282: 1606
    //                   1283: 1613
    //                   1284: 1620
    //                   1285: 1627
    //                   1286: 1634
    //                   1287: 1641
    //                   1288: 1648
    //                   1289: 1655
    //                   1290: 1662
    //                   1291: 1669
    //                   1292: 1676;
           goto _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70 _L71 _L72 _L73 _L74 _L75 _L76 _L77
_L8:
        LogUtils.e(TAG, "Unrecognized habit api type, falling back to unknown: %d", new Object[] {
            Integer.valueOf(i)
        });
        obj = "unknown";
_L83:
        obj1.type = ((String) (obj));
        if (eventcolor != null)
        {
            obj = eventcolor.getKey();
        } else
        {
            obj = Data.NULL_STRING;
        }
        obj1.color = ((String) (obj));
        i = habit.getVisibility();
        i;
        JVM INSTR tableswitch 0 2: default 1008
    //                   0 1690
    //                   1 1696
    //                   2 1703;
           goto _L78 _L79 _L80 _L81
_L81:
        break MISSING_BLOCK_LABEL_1703;
_L78:
        LogUtils.e(TAG, "Unrecognized habit api visibility, falling back to default: %d", new Object[] {
            Integer.valueOf(i)
        });
        obj = "default";
_L84:
        obj1.visibility = ((String) (obj));
        obj1.contract = contract;
        if (habit.getReminders() != null)
        {
            obj = new Reminders();
            obj.useDefaultReminders = Boolean.valueOf(habit.getReminders().useDefaultReminders);
            obj.enableFollowup = Boolean.valueOf(habit.getReminders().enableFollowup);
            obj.enableRecommit = Boolean.valueOf(habit.getReminders().enableRecommit);
            obj1.reminders = ((Reminders) (obj));
            if (!habit.getReminders().useDefaultReminders)
            {
                if (habit.getReminders().overrideMinutes == null)
                {
                    ((HabitData) (obj1)).reminders.reminderOverrides = Collections.emptyList();
                } else
                {
                    Reminders reminders = ((HabitData) (obj1)).reminders;
                    ReminderInstance reminderinstance = new ReminderInstance();
                    reminderinstance.method = "popup";
                    reminderinstance.minutes = habit.getReminders().overrideMinutes;
                    reminders.reminderOverrides = Collections.singletonList(reminderinstance);
                }
            }
        } else
        {
            obj1.reminders = (Reminders)Data.nullOf(com/google/api/services/calendar/model/Reminders);
        }
        habit1.habitData = ((HabitData) (obj1));
        return habit1;
_L4:
        obj = "unknown";
          goto _L82
_L5:
        obj = "daily";
          goto _L82
_L6:
        obj = "weekly";
          goto _L82
_L7:
        obj = "monthly";
          goto _L82
_L9:
        obj = "unknown";
          goto _L83
_L10:
        obj = "exercise";
          goto _L83
_L11:
        obj = "exerciseWorkout";
          goto _L83
_L12:
        obj = "exerciseRun";
          goto _L83
_L13:
        obj = "exerciseWalk";
          goto _L83
_L14:
        obj = "exerciseYoga";
          goto _L83
_L15:
        obj = "exerciseHike";
          goto _L83
_L17:
        obj = "exerciseSwim";
          goto _L83
_L16:
        obj = "exerciseBike";
          goto _L83
_L18:
        obj = "exerciseRockClimb";
          goto _L83
_L19:
        obj = "exercisePlayTennis";
          goto _L83
_L20:
        obj = "exercisePlayBadminton";
          goto _L83
_L21:
        obj = "exercisePlayBaseball";
          goto _L83
_L22:
        obj = "exercisePlayBasketball";
          goto _L83
_L23:
        obj = "exercisePlaySoccer";
          goto _L83
_L24:
        obj = "exerciseWiggleEars";
          goto _L83
_L25:
        obj = "buildSkill";
          goto _L83
_L26:
        obj = "buildSkillPracticeLanguageCustom";
          goto _L83
_L27:
        obj = "buildSkillLearnToCode";
          goto _L83
_L29:
        obj = "buildSkillMakeArtCustom";
          goto _L83
_L28:
        obj = "buildSkillLearnInstrumentCustom";
          goto _L83
_L30:
        obj = "buildSkillPracticePhotography";
          goto _L83
_L31:
        obj = "buildSkillHoneCarpentrySkills";
          goto _L83
_L32:
        obj = "buildSkillSing";
          goto _L83
_L33:
        obj = "buildSkillLearnKnot";
          goto _L83
_L34:
        obj = "buildSkillLearnNewSoftware";
          goto _L83
_L35:
        obj = "buildSkillCookSomethingNew";
          goto _L83
_L36:
        obj = "buildSkillLearnToDrive";
          goto _L83
_L37:
        obj = "buildSkillLearnToFly";
          goto _L83
_L38:
        obj = "friendsAndFamily";
          goto _L83
_L39:
        obj = "friendsAndFamilyReachOut";
          goto _L83
_L40:
        obj = "friendsAndFamilyEatWithFamily";
          goto _L83
_L41:
        obj = "friendsAndFamilyCallMom";
          goto _L83
_L42:
        obj = "friendsAndFamilyCallDad";
          goto _L83
_L43:
        obj = "friendsAndFamilyPlanDate";
          goto _L83
_L44:
        obj = "friendsAndFamilyGetDinnerWithFriends";
          goto _L83
_L45:
        obj = "friendsAndFamilyVisitFamily";
          goto _L83
_L46:
        obj = "friendsAndFamilyHaveBbq";
          goto _L83
_L47:
        obj = "friendsAndFamilyPlayBoardGame";
          goto _L83
_L48:
        obj = "friendsAndFamilyPlanReunion";
          goto _L83
_L49:
        obj = "friendsAndFamilyPlanFamilyVacation";
          goto _L83
_L50:
        obj = "friendsAndFamilyWalkTheDog";
          goto _L83
_L51:
        obj = "meTime";
          goto _L83
_L52:
        obj = "meTimeRead";
          goto _L83
_L53:
        obj = "meTimeMeditate";
          goto _L83
_L54:
        obj = "meTimeHobbyCustom";
          goto _L83
_L55:
        obj = "meTimeCook";
          goto _L83
_L56:
        obj = "meTimeJournal";
          goto _L83
_L57:
        obj = "meTimePray";
          goto _L83
_L58:
        obj = "meTimeWatchMovie";
          goto _L83
_L59:
        obj = "meTimeTakeNap";
          goto _L83
_L60:
        obj = "meTimeGetMassage";
          goto _L83
_L61:
        obj = "meTimeSitInTheGrass";
          goto _L83
_L62:
        obj = "meTimeTakeTheBoatOut";
          goto _L83
_L63:
        obj = "meTimeLieInHammock";
          goto _L83
_L64:
        obj = "meTimeTakeSelfie";
          goto _L83
_L65:
        obj = "organizeMyLife";
          goto _L83
_L66:
        obj = "organizeMyLifePlanMyDay";
          goto _L83
_L67:
        obj = "organizeMyLifeClean";
          goto _L83
_L68:
        obj = "organizeMyLifeChores";
          goto _L83
_L69:
        obj = "organizeMyLifeMakeTodoList";
          goto _L83
_L70:
        obj = "organizeMyLifeBuyGroceries";
          goto _L83
_L71:
        obj = "organizeMyLifeStudy";
          goto _L83
_L72:
        obj = "organizeMyLifeDoLaundry";
          goto _L83
_L73:
        obj = "organizeMyLifeDoFinances";
          goto _L83
_L74:
        obj = "organizeMyLifePlanTheWeek";
          goto _L83
_L75:
        obj = "organizeMyLifePlanTheMonth";
          goto _L83
_L76:
        obj = "organizeMyLifeClearEmailInbox";
          goto _L83
_L77:
        obj = "organizeMyLifeCleanTheHouse";
          goto _L83
_L79:
        obj = "default";
          goto _L84
_L80:
        obj = "public";
          goto _L84
        obj = "private";
          goto _L84
    }

    public static int countHabitInstances(ContentProviderClient contentproviderclient, String s, String as[])
        throws RemoteException
    {
        Object obj;
        obj = null;
        as = contentproviderclient.query(android.provider.CalendarContract.Events.CONTENT_URI, HABIT_INSTANCE_COUNT_PROJECTION, s, as, null);
        if (as == null) goto _L2; else goto _L1
_L1:
        if (as.moveToFirst()) goto _L3; else goto _L2
_L2:
        throw new RemoteException("Could not count habit instances");
        s;
        throw s;
        contentproviderclient;
_L5:
        int i;
        if (as != null)
        {
            if (s != null)
            {
                try
                {
                    as.close();
                }
                // Misplaced declaration of an exception variable
                catch (String as[])
                {
                    ThrowableExtension.STRATEGY.addSuppressed(s, as);
                }
            } else
            {
                as.close();
            }
        }
        throw contentproviderclient;
_L3:
        i = as.getInt(0);
        if (as != null)
        {
            as.close();
        }
        return i;
        contentproviderclient;
        s = obj;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static String getHabitIdAndTypeString(String s, String s1)
    {
        if (s1 == null)
        {
            return s;
        } else
        {
            return HabitIdTypeUtil.createHabitIdTypeStringFromApiType(s, serverTypeToApiType(s1));
        }
    }

    public static HabitModifications serverHabitToApi(CalendarDescriptor calendardescriptor, Habit habit)
    {
        Object obj;
        HabitImpl.Modification modification;
        Object obj1;
        HabitContractModifications habitcontractmodifications;
        obj = null;
        boolean flag;
        if (!Platform.stringIsNullOrEmpty(habit.id))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (!Boolean.TRUE.equals(habit.deleted))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        modification = new HabitImpl.Modification(calendardescriptor, habit.id);
        if (habit.etag != null)
        {
            modification.setFingerprint(habit.etag);
        }
        obj1 = habit.habitData;
        habitcontractmodifications = modification.getContractModifications();
        if (obj1 == null) goto _L2; else goto _L1
_L1:
        int i;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if (!Platform.stringIsNullOrEmpty(((HabitData) (obj1)).summary))
        {
            modification.setSummary(((HabitData) (obj1)).summary);
        } else
        {
            habit = ((HabitData) (obj1)).customName;
            calendardescriptor = habit;
            if (habit == null)
            {
                calendardescriptor = "";
            }
            modification.setSummary(calendardescriptor);
        }
        calendardescriptor = ((HabitData) (obj1)).visibility;
        if (calendardescriptor != null) goto _L4; else goto _L3
_L3:
        i = 0;
_L25:
        modification.setVisibility(i);
        modification.setType(serverTypeToApiType(((HabitData) (obj1)).type));
        if (TextUtils.isEmpty(((HabitData) (obj1)).color))
        {
            calendardescriptor = null;
        } else
        {
            calendardescriptor = ((HabitData) (obj1)).color;
        }
        if (calendardescriptor != null)
        {
            calendardescriptor = CalendarApi.getColorFactory().createGoogleEventColor(calendardescriptor);
        } else
        {
            calendardescriptor = null;
        }
        modification.setColorOverride(calendardescriptor);
        calendardescriptor = ((HabitData) (obj1)).contract;
        if (calendardescriptor == null) goto _L6; else goto _L5
_L5:
        if (((Contract) (calendardescriptor)).durationMinutes != null)
        {
            habitcontractmodifications.setDurationMinutes(((Contract) (calendardescriptor)).durationMinutes.intValue());
        }
        if (((Contract) (calendardescriptor)).interval == null) goto _L8; else goto _L7
_L7:
        habit = ((Contract) (calendardescriptor)).interval;
        i = -1;
        habit.hashCode();
        JVM INSTR lookupswitch 4: default 308
    //                   -791707519: 1000
    //                   -284840886: 970
    //                   95346201: 985
    //                   1236635661: 1016;
           goto _L9 _L10 _L11 _L12 _L13
_L9:
        i;
        JVM INSTR tableswitch 0 3: default 340
    //                   0 1032
    //                   1 1038
    //                   2 1044
    //                   3 1050;
           goto _L14 _L15 _L16 _L17 _L18
_L14:
        LogUtils.e(TAG, "Unrecognized habit server interval, falling back to UNKNOWN: %s", new Object[] {
            habit
        });
        i = 0;
_L23:
        habitcontractmodifications.setInterval(i);
_L8:
        if (((Contract) (calendardescriptor)).numInstancesPerInterval != null)
        {
            habitcontractmodifications.setNumInstancesPerInterval(((Contract) (calendardescriptor)).numInstancesPerInterval.intValue());
        }
        if (((Contract) (calendardescriptor)).untilMillisUtc != null)
        {
            habitcontractmodifications.setUntilMillisUtc(((Contract) (calendardescriptor)).untilMillisUtc.longValue());
        }
        if (((Contract) (calendardescriptor)).timePattern != null && ((Contract) (calendardescriptor)).timePattern.dailyPattern != null)
        {
            calendardescriptor = ((Contract) (calendardescriptor)).timePattern.dailyPattern;
            if (((DailyPattern) (calendardescriptor)).morning != null)
            {
                habitcontractmodifications.setMorningPreferable(((DailyPattern) (calendardescriptor)).morning.booleanValue());
            }
            if (((DailyPattern) (calendardescriptor)).afternoon != null)
            {
                habitcontractmodifications.setAfternoonPreferable(((DailyPattern) (calendardescriptor)).afternoon.booleanValue());
            }
            if (((DailyPattern) (calendardescriptor)).evening != null)
            {
                habitcontractmodifications.setEveningPreferable(((DailyPattern) (calendardescriptor)).evening.booleanValue());
            }
            if (((DailyPattern) (calendardescriptor)).any != null && ((DailyPattern) (calendardescriptor)).any.booleanValue())
            {
                if (habitcontractmodifications.isDailyPatternModified() && (habitcontractmodifications.isMorningPreferable() || habitcontractmodifications.isAfternoonPreferable() || habitcontractmodifications.isEveningPreferable()))
                {
                    LogUtils.e(TAG, "Overriding previously set preference: %b, %b, %b", new Object[] {
                        Boolean.valueOf(habitcontractmodifications.isMorningPreferable()), Boolean.valueOf(habitcontractmodifications.isAfternoonPreferable()), Boolean.valueOf(habitcontractmodifications.isEveningPreferable())
                    });
                }
                habitcontractmodifications.setAnyDayTimeAcceptable();
            }
        }
_L6:
        obj1 = ((HabitData) (obj1)).reminders;
        if (obj1 != null)
        {
            calendardescriptor = ((Reminders) (obj1)).useDefaultReminders;
            habit = Boolean.valueOf(false);
            if (calendardescriptor == null)
            {
                if (habit != null)
                {
                    calendardescriptor = habit;
                } else
                {
                    throw new NullPointerException("Both parameters are null");
                }
            }
            flag1 = ((Boolean)calendardescriptor).booleanValue();
            calendardescriptor = ((Reminders) (obj1)).enableRecommit;
            habit = Boolean.valueOf(false);
            if (calendardescriptor == null)
            {
                if (habit != null)
                {
                    calendardescriptor = habit;
                } else
                {
                    throw new NullPointerException("Both parameters are null");
                }
            }
            flag2 = ((Boolean)calendardescriptor).booleanValue();
            calendardescriptor = ((Reminders) (obj1)).enableFollowup;
            habit = Boolean.valueOf(false);
            if (calendardescriptor == null)
            {
                if (habit != null)
                {
                    calendardescriptor = habit;
                } else
                {
                    throw new NullPointerException("Both parameters are null");
                }
            }
            flag3 = ((Boolean)calendardescriptor).booleanValue();
            habit = ((Reminders) (obj1)).reminderOverrides;
            calendardescriptor = obj;
            if (!flag1)
            {
                calendardescriptor = obj;
                if (habit != null)
                {
                    calendardescriptor = obj;
                    if (!habit.isEmpty())
                    {
                        calendardescriptor = ((ReminderInstance)habit.get(0)).minutes;
                    }
                }
            }
            modification.setReminders(new HabitReminders(flag1, calendardescriptor, flag2, flag3));
        }
_L2:
        return modification;
_L4:
        i = -1;
        calendardescriptor.hashCode();
        JVM INSTR lookupswitch 3: default 844
    //                   -977423767: 911
    //                   -314497661: 927
    //                   1544803905: 896;
           goto _L19 _L20 _L21 _L22
_L19:
        switch (i)
        {
        default:
            LogUtils.e(TAG, "Unrecognized habit server visibility, falling back to default: %s", new Object[] {
                calendardescriptor
            });
            i = 0;
            break;

        case 0: // '\0'
            i = 0;
            break;

        case 1: // '\001'
            i = 1;
            break;

        case 2: // '\002'
            i = 2;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L22:
        if (calendardescriptor.equals("default"))
        {
            i = 0;
        }
          goto _L19
_L20:
        if (calendardescriptor.equals("public"))
        {
            i = 1;
        }
          goto _L19
_L21:
        if (calendardescriptor.equals("private"))
        {
            i = 2;
        }
          goto _L19
_L11:
        if (habit.equals("unknown"))
        {
            i = 0;
        }
          goto _L9
_L12:
        if (habit.equals("daily"))
        {
            i = 1;
        }
          goto _L9
_L10:
        if (habit.equals("weekly"))
        {
            i = 2;
        }
          goto _L9
_L13:
        if (habit.equals("monthly"))
        {
            i = 3;
        }
          goto _L9
_L15:
        i = 0;
          goto _L23
_L16:
        i = 1;
          goto _L23
_L17:
        i = 2;
          goto _L23
_L18:
        i = 3;
          goto _L23
        if (true) goto _L25; else goto _L24
_L24:
    }

    public static int serverTypeToApiType(String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        return 0;
_L2:
        byte byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 69: default 576
    //                   -1937999625: 1329
    //                   -1899146562: 1489
    //                   -1814187595: 1105
    //                   -1794455257: 1041
    //                   -1750342461: 1937
    //                   -1736809721: 1889
    //                   -1575485905: 1905
    //                   -1486054604: 1761
    //                   -1309709911: 1265
    //                   -1287940863: 1361
    //                   -1141051425: 1217
    //                   -1091647844: 1793
    //                   -1078507387: 1553
    //                   -936871215: 1681
    //                   -873315918: 1249
    //                   -811512078: 1633
    //                   -636216802: 1601
    //                   -614425418: 1745
    //                   -576222121: 1233
    //                   -509627395: 1841
    //                   -500451933: 1185
    //                   -481761149: 1137
    //                   -471919091: 1969
    //                   -471058938: 1665
    //                   -466883111: 1009
    //                   -466704365: 977
    //                   -466363264: 993
    //                   -466265151: 947
    //                   -466192280: 962
    //                   -305928535: 1457
    //                   -284840886: 888
    //                   -228578847: 1825
    //                   -174418523: 917
    //                   -159115503: 1089
    //                   -133701800: 1025
    //                   51466729: 1169
    //                   61226193: 1297
    //                   172335469: 1617
    //                   172725215: 1649
    //                   172772283: 1569
    //                   204026727: 1153
    //                   217128230: 1281
    //                   273463305: 1729
    //                   281767683: 1713
    //                   320741946: 1057
    //                   359467345: 1921
    //                   390513453: 1505
    //                   766462688: 1585
    //                   816238995: 932
    //                   962566846: 1809
    //                   966710413: 1441
    //                   977596990: 1873
    //                   1131180688: 1521
    //                   1142820058: 1537
    //                   1161007622: 1345
    //                   1239581657: 1201
    //                   1426948208: 1857
    //                   1448067741: 1425
    //                   1520165451: 1777
    //                   1521645788: 1377
    //                   1522850746: 1697
    //                   1596486926: 1313
    //                   1679100316: 1073
    //                   1714942624: 1121
    //                   1781981027: 1409
    //                   1781990119: 1393
    //                   1933556931: 1473
    //                   2056323544: 902
    //                   2074686636: 1953;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67 _L68 _L69 _L70 _L71 _L72
_L3:
        switch (byte0)
        {
        default:
            LogUtils.e(TAG, "Unrecognized habit server type, falling back to UNKNOWN: %s", new Object[] {
                s
            });
            return 0;

        case 1: // '\001'
            return 256;

        case 2: // '\002'
            return 257;

        case 3: // '\003'
            return 258;

        case 4: // '\004'
            return 259;

        case 5: // '\005'
            return 260;

        case 6: // '\006'
            return 261;

        case 7: // '\007'
            return 263;

        case 8: // '\b'
            return 262;

        case 9: // '\t'
            return 264;

        case 10: // '\n'
            return 265;

        case 11: // '\013'
            return 266;

        case 12: // '\f'
            return 267;

        case 13: // '\r'
            return 268;

        case 14: // '\016'
            return 269;

        case 15: // '\017'
            return 270;

        case 16: // '\020'
            return 512;

        case 17: // '\021'
            return 513;

        case 18: // '\022'
            return 514;

        case 19: // '\023'
            return 516;

        case 20: // '\024'
            return 515;

        case 21: // '\025'
            return 517;

        case 22: // '\026'
            return 518;

        case 23: // '\027'
            return 519;

        case 24: // '\030'
            return 520;

        case 25: // '\031'
            return 521;

        case 26: // '\032'
            return 522;

        case 27: // '\033'
            return 523;

        case 28: // '\034'
            return 524;

        case 29: // '\035'
            return 768;

        case 30: // '\036'
            return 769;

        case 31: // '\037'
            return 770;

        case 32: // ' '
            return 771;

        case 33: // '!'
            return 772;

        case 34: // '"'
            return 773;

        case 35: // '#'
            return 774;

        case 36: // '$'
            return 775;

        case 37: // '%'
            return 776;

        case 38: // '&'
            return 777;

        case 39: // '\''
            return 778;

        case 40: // '('
            return 779;

        case 41: // ')'
            return 780;

        case 42: // '*'
            return 1024;

        case 43: // '+'
            return 1025;

        case 44: // ','
            return 1026;

        case 45: // '-'
            return 1027;

        case 46: // '.'
            return 1028;

        case 47: // '/'
            return 1029;

        case 48: // '0'
            return 1030;

        case 49: // '1'
            return 1031;

        case 50: // '2'
            return 1032;

        case 51: // '3'
            return 1033;

        case 52: // '4'
            return 1034;

        case 53: // '5'
            return 1035;

        case 54: // '6'
            return 1036;

        case 55: // '7'
            return 1037;

        case 56: // '8'
            return 1280;

        case 57: // '9'
            return 1281;

        case 58: // ':'
            return 1282;

        case 59: // ';'
            return 1283;

        case 60: // '<'
            return 1284;

        case 61: // '='
            return 1285;

        case 62: // '>'
            return 1286;

        case 63: // '?'
            return 1287;

        case 64: // '@'
            return 1288;

        case 65: // 'A'
            return 1289;

        case 66: // 'B'
            return 1290;

        case 67: // 'C'
            return 1291;

        case 68: // 'D'
            return 1292;

        case 0: // '\0'
            break;
        }
        if (true) goto _L1; else goto _L34
_L34:
        if (s.equals("unknown"))
        {
            byte0 = 0;
        }
          goto _L3
_L71:
        if (s.equals("exercise"))
        {
            byte0 = 1;
        }
          goto _L3
_L36:
        if (s.equals("exerciseWorkout"))
        {
            byte0 = 2;
        }
          goto _L3
_L52:
        if (s.equals("exerciseRun"))
        {
            byte0 = 3;
        }
          goto _L3
_L31:
        if (s.equals("exerciseWalk"))
        {
            byte0 = 4;
        }
          goto _L3
_L32:
        if (s.equals("exerciseYoga"))
        {
            byte0 = 5;
        }
          goto _L3
_L29:
        if (s.equals("exerciseHike"))
        {
            byte0 = 6;
        }
          goto _L3
_L30:
        if (s.equals("exerciseSwim"))
        {
            byte0 = 7;
        }
          goto _L3
_L28:
        if (s.equals("exerciseBike"))
        {
            byte0 = 8;
        }
          goto _L3
_L38:
        if (s.equals("exerciseRockClimb"))
        {
            byte0 = 9;
        }
          goto _L3
_L7:
        if (s.equals("exercisePlayTennis"))
        {
            byte0 = 10;
        }
          goto _L3
_L48:
        if (s.equals("exercisePlayBadminton"))
        {
            byte0 = 11;
        }
          goto _L3
_L66:
        if (s.equals("exercisePlayBaseball"))
        {
            byte0 = 12;
        }
          goto _L3
_L37:
        if (s.equals("exercisePlayBasketball"))
        {
            byte0 = 13;
        }
          goto _L3
_L6:
        if (s.equals("exercisePlaySoccer"))
        {
            byte0 = 14;
        }
          goto _L3
_L67:
        if (s.equals("exerciseWiggleEars"))
        {
            byte0 = 15;
        }
          goto _L3
_L25:
        if (s.equals("buildSkill"))
        {
            byte0 = 16;
        }
          goto _L3
_L44:
        if (s.equals("buildSkillPracticeLanguageCustom"))
        {
            byte0 = 17;
        }
          goto _L3
_L39:
        if (s.equals("buildSkillLearnToCode"))
        {
            byte0 = 18;
        }
          goto _L3
_L24:
        if (s.equals("buildSkillMakeArtCustom"))
        {
            byte0 = 19;
        }
          goto _L3
_L59:
        if (s.equals("buildSkillLearnInstrumentCustom"))
        {
            byte0 = 20;
        }
          goto _L3
_L14:
        if (s.equals("buildSkillPracticePhotography"))
        {
            byte0 = 21;
        }
          goto _L3
_L22:
        if (s.equals("buildSkillHoneCarpentrySkills"))
        {
            byte0 = 22;
        }
          goto _L3
_L18:
        if (s.equals("buildSkillSing"))
        {
            byte0 = 23;
        }
          goto _L3
_L12:
        if (s.equals("buildSkillLearnKnot"))
        {
            byte0 = 24;
        }
          goto _L3
_L45:
        if (s.equals("buildSkillLearnNewSoftware"))
        {
            byte0 = 25;
        }
          goto _L3
_L40:
        if (s.equals("buildSkillCookSomethingNew"))
        {
            byte0 = 26;
        }
          goto _L3
_L65:
        if (s.equals("buildSkillLearnToDrive"))
        {
            byte0 = 27;
        }
          goto _L3
_L4:
        if (s.equals("buildSkillLearnToFly"))
        {
            byte0 = 28;
        }
          goto _L3
_L58:
        if (s.equals("friendsAndFamily"))
        {
            byte0 = 29;
        }
          goto _L3
_L13:
        if (s.equals("friendsAndFamilyReachOut"))
        {
            byte0 = 30;
        }
          goto _L3
_L63:
        if (s.equals("friendsAndFamilyEatWithFamily"))
        {
            byte0 = 31;
        }
          goto _L3
_L69:
        if (s.equals("friendsAndFamilyCallMom"))
        {
            byte0 = 32;
        }
          goto _L3
_L68:
        if (s.equals("friendsAndFamilyCallDad"))
        {
            byte0 = 33;
        }
          goto _L3
_L61:
        if (s.equals("friendsAndFamilyPlanDate"))
        {
            byte0 = 34;
        }
          goto _L3
_L54:
        if (s.equals("friendsAndFamilyGetDinnerWithFriends"))
        {
            byte0 = 35;
        }
          goto _L3
_L33:
        if (s.equals("friendsAndFamilyVisitFamily"))
        {
            byte0 = 36;
        }
          goto _L3
_L70:
        if (s.equals("friendsAndFamilyHaveBbq"))
        {
            byte0 = 37;
        }
          goto _L3
_L5:
        if (s.equals("friendsAndFamilyPlayBoardGame"))
        {
            byte0 = 38;
        }
          goto _L3
_L50:
        if (s.equals("friendsAndFamilyPlanReunion"))
        {
            byte0 = 39;
        }
          goto _L3
_L56:
        if (s.equals("friendsAndFamilyPlanFamilyVacation"))
        {
            byte0 = 40;
        }
          goto _L3
_L57:
        if (s.equals("friendsAndFamilyWalkTheDog"))
        {
            byte0 = 41;
        }
          goto _L3
_L16:
        if (s.equals("meTime"))
        {
            byte0 = 42;
        }
          goto _L3
_L43:
        if (s.equals("meTimeRead"))
        {
            byte0 = 43;
        }
          goto _L3
_L51:
        if (s.equals("meTimeMeditate"))
        {
            byte0 = 44;
        }
          goto _L3
_L20:
        if (s.equals("meTimeHobbyCustom"))
        {
            byte0 = 45;
        }
          goto _L3
_L41:
        if (s.equals("meTimeCook"))
        {
            byte0 = 46;
        }
          goto _L3
_L19:
        if (s.equals("meTimeJournal"))
        {
            byte0 = 47;
        }
          goto _L3
_L42:
        if (s.equals("meTimePray"))
        {
            byte0 = 48;
        }
          goto _L3
_L27:
        if (s.equals("meTimeWatchMovie"))
        {
            byte0 = 49;
        }
          goto _L3
_L17:
        if (s.equals("meTimeTakeNap"))
        {
            byte0 = 50;
        }
          goto _L3
_L64:
        if (s.equals("meTimeGetMassage"))
        {
            byte0 = 51;
        }
          goto _L3
_L47:
        if (s.equals("meTimeSitInTheGrass"))
        {
            byte0 = 52;
        }
          goto _L3
_L46:
        if (s.equals("meTimeTakeTheBoatOut"))
        {
            byte0 = 53;
        }
          goto _L3
_L21:
        if (s.equals("meTimeLieInHammock"))
        {
            byte0 = 54;
        }
          goto _L3
_L11:
        if (s.equals("meTimeTakeSelfie"))
        {
            byte0 = 55;
        }
          goto _L3
_L62:
        if (s.equals("organizeMyLife"))
        {
            byte0 = 56;
        }
          goto _L3
_L15:
        if (s.equals("organizeMyLifePlanMyDay"))
        {
            byte0 = 57;
        }
          goto _L3
_L53:
        if (s.equals("organizeMyLifeClean"))
        {
            byte0 = 58;
        }
          goto _L3
_L35:
        if (s.equals("organizeMyLifeChores"))
        {
            byte0 = 59;
        }
          goto _L3
_L23:
        if (s.equals("organizeMyLifeMakeTodoList"))
        {
            byte0 = 60;
        }
          goto _L3
_L60:
        if (s.equals("organizeMyLifeBuyGroceries"))
        {
            byte0 = 61;
        }
          goto _L3
_L55:
        if (s.equals("organizeMyLifeStudy"))
        {
            byte0 = 62;
        }
          goto _L3
_L9:
        if (s.equals("organizeMyLifeDoLaundry"))
        {
            byte0 = 63;
        }
          goto _L3
_L10:
        if (s.equals("organizeMyLifeDoFinances"))
        {
            byte0 = 64;
        }
          goto _L3
_L49:
        if (s.equals("organizeMyLifePlanTheWeek"))
        {
            byte0 = 65;
        }
          goto _L3
_L8:
        if (s.equals("organizeMyLifePlanTheMonth"))
        {
            byte0 = 66;
        }
          goto _L3
_L72:
        if (s.equals("organizeMyLifeClearEmailInbox"))
        {
            byte0 = 67;
        }
          goto _L3
_L26:
        if (s.equals("organizeMyLifeCleanTheHouse"))
        {
            byte0 = 68;
        }
          goto _L3
    }

    public static void setHabitExtrasFlags(com.google.android.apps.calendar.commonsync.constants.EventExtrasFlags.Builder builder, Boolean boolean1, String s)
    {
        boolean flag;
        if (boolean1 != null)
        {
            flag = boolean1.booleanValue();
        } else
        {
            flag = false;
        }
        if (s.equals("complete"))
        {
            builder.flags = builder.flags | 0x80;
        } else
        {
            builder.flags = builder.flags & 0xffffff7f;
        }
        if (s.equals("deferralRequested"))
        {
            builder.flags = builder.flags | 0x100;
        } else
        {
            builder.flags = builder.flags & 0xfffffeff;
        }
        if (flag)
        {
            builder.flags = builder.flags | 0x200;
            return;
        } else
        {
            builder.flags = builder.flags & 0xfffffdff;
            return;
        }
    }

}
