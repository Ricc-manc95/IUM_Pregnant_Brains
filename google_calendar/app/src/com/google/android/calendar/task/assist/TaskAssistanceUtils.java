// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.assist;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.net.taskassist.TaskAssistService;
import com.google.android.calendar.task.CalendarAssistance;
import com.google.common.base.Platform;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.personalization.assist.annotate.ClientType;
import com.google.personalization.assist.annotate.GrammarRuleType;
import com.google.personalization.assist.annotate.api.Assistance;
import com.google.personalization.assist.annotate.api.AssistanceRequestData;
import com.google.personalization.assist.annotate.api.BillAssistance;
import com.google.personalization.assist.annotate.api.CallAction;
import com.google.personalization.assist.annotate.api.DeadlineAssistance;
import com.google.personalization.assist.annotate.api.EmailAction;
import com.google.personalization.assist.annotate.api.EmailAddress;
import com.google.personalization.assist.annotate.api.EventTime;
import com.google.personalization.assist.annotate.api.FinanceAssistance;
import com.google.personalization.assist.annotate.api.OrganizationAssistance;
import com.google.personalization.assist.annotate.api.PhoneNumber;
import com.google.personalization.assist.annotate.api.TaskAssistance;
import com.google.personalization.assist.annotate.api.TaskAssistanceRequest;
import com.google.personalization.assist.annotate.api.TaskAssistanceResponse;
import com.google.personalization.assist.annotate.api.ViewUrlAction;
import com.google.personalization.assist.annotate.api.WatchYoutubeAction;
import com.google.personalization.assist.annotate.api.WeatherAssistance;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Protobuf;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.task.assist:
//            TaskAssistHolder, CalendarTaskAssist, CallOrganizationTaskAssist, CallTaskAssist, 
//            DeadlineTaskAssist, EmailTaskAssist, FinanceTaskAssist, ReturnProductTaskAssist, 
//            UrlTaskAssist, WeatherTaskAssist, WatchVideoTaskAssist, PayBillTaskAssist

public final class TaskAssistanceUtils
{

    private static final String TAG;
    public static final Map grammarRuleTypeToFlair;

    public static byte[] createEmptyTaskAssist()
    {
        return ((CalendarAssistance)(GeneratedMessageLite)((com.google.android.calendar.task.CalendarAssistance.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarAssistance.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).setTaskAnnotationHint("").build()).toByteArray();
    }

    public static TaskAssistHolder createTaskAssistHolder(byte abyte0[], String s, String s1)
    {
        GrammarRuleType grammarruletype;
        Object obj;
        ArrayList arraylist;
        Assistance assistance;
        int i;
        boolean flag;
        int j;
        abyte0 = getAssistance(abyte0);
        if (abyte0 == null)
        {
            abyte0 = null;
        } else
        if (((CalendarAssistance) (abyte0)).taskAssistance_ == null)
        {
            abyte0 = TaskAssistance.DEFAULT_INSTANCE;
        } else
        {
            abyte0 = ((CalendarAssistance) (abyte0)).taskAssistance_;
        }
        if (abyte0 == null) goto _L2; else goto _L1
_L1:
        j = ((TaskAssistance) (abyte0)).assistance_.size();
        obj = GrammarRuleType.forNumber(((TaskAssistance) (abyte0)).grammarRuleType_);
        grammarruletype = ((GrammarRuleType) (obj));
        if (obj == null)
        {
            grammarruletype = GrammarRuleType.UNKNOWN_RULE;
        }
        arraylist = new ArrayList(j);
        i = 0;
_L18:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_1258;
        }
        assistance = (Assistance)((TaskAssistance) (abyte0)).assistance_.get(i);
        if (grammarruletype != GrammarRuleType.MEET_CONTACT) goto _L4; else goto _L3
_L3:
        if (assistance.calendarAssistance_ == null)
        {
            obj = com.google.personalization.assist.annotate.api.CalendarAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = assistance.calendarAssistance_;
        }
        if ((((com.google.personalization.assist.annotate.api.CalendarAssistance) (obj)).bitField0_ & 1) != 1) goto _L4; else goto _L5
_L5:
        if (assistance.calendarAssistance_ == null)
        {
            obj = com.google.personalization.assist.annotate.api.CalendarAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj = assistance.calendarAssistance_;
        }
        if ((((com.google.personalization.assist.annotate.api.CalendarAssistance) (obj)).bitField0_ & 8) != 8) goto _L4; else goto _L6
_L6:
        flag = true;
_L7:
        if (flag)
        {
            arraylist.add(new CalendarTaskAssist(assistance, grammarruletype, s, s1));
        } else
        {
            Object obj1;
            if (assistance.organizationAssistance_ == null)
            {
                obj1 = OrganizationAssistance.DEFAULT_INSTANCE;
            } else
            {
                obj1 = assistance.organizationAssistance_;
            }
            if (((OrganizationAssistance) (obj1)).phoneNumber_ == null)
            {
                obj1 = PhoneNumber.DEFAULT_INSTANCE;
            } else
            {
                obj1 = ((OrganizationAssistance) (obj1)).phoneNumber_;
            }
            if (!((PhoneNumber) (obj1)).phoneNumber_.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                arraylist.add(new CallOrganizationTaskAssist(assistance, grammarruletype, s, s1));
                if (i + 1 < j && hasCallAction((Assistance)((TaskAssistance) (abyte0)).assistance_.get(i + 1), grammarruletype))
                {
                    i++;
                }
            } else
            {
label0:
                {
                    if (!hasCallAction(assistance, grammarruletype))
                    {
                        break label0;
                    }
                    arraylist.add(new CallTaskAssist(assistance, grammarruletype, s, s1));
                }
            }
        }
_L8:
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        flag = false;
          goto _L7
        if (grammarruletype != GrammarRuleType.PAY_TAX)
        {
            break MISSING_BLOCK_LABEL_542;
        }
        Object obj2;
        if (assistance.deadlineAssistance_ == null)
        {
            obj2 = DeadlineAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj2 = assistance.deadlineAssistance_;
        }
        if (((DeadlineAssistance) (obj2)).eventTime_ == null)
        {
            obj2 = EventTime.DEFAULT_INSTANCE;
        } else
        {
            obj2 = ((DeadlineAssistance) (obj2)).eventTime_;
        }
        if ((((EventTime) (obj2)).bitField0_ & 1) != 1)
        {
            break MISSING_BLOCK_LABEL_542;
        }
        if (assistance.deadlineAssistance_ == null)
        {
            obj2 = DeadlineAssistance.DEFAULT_INSTANCE;
        } else
        {
            obj2 = assistance.deadlineAssistance_;
        }
        if (((DeadlineAssistance) (obj2)).eventTime_ == null)
        {
            obj2 = EventTime.DEFAULT_INSTANCE;
        } else
        {
            obj2 = ((DeadlineAssistance) (obj2)).eventTime_;
        }
        if ((((EventTime) (obj2)).bitField0_ & 2) != 2)
        {
            break MISSING_BLOCK_LABEL_542;
        }
        flag = true;
_L9:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_548;
        }
        arraylist.add(new DeadlineTaskAssist(assistance, grammarruletype, s, s1));
          goto _L8
        flag = false;
          goto _L9
        if (grammarruletype != GrammarRuleType.EMAIL_CONTACT)
        {
            break MISSING_BLOCK_LABEL_668;
        }
        EmailAction emailaction;
        if (assistance.emailAction_ == null)
        {
            emailaction = EmailAction.DEFAULT_INSTANCE;
        } else
        {
            emailaction = assistance.emailAction_;
        }
        if (emailaction.emailAddress_.isEmpty())
        {
            break MISSING_BLOCK_LABEL_668;
        }
        if (assistance.emailAction_ == null)
        {
            emailaction = EmailAction.DEFAULT_INSTANCE;
        } else
        {
            emailaction = assistance.emailAction_;
        }
        if ((((EmailAddress)emailaction.emailAddress_.get(0)).bitField0_ & 1) != 1)
        {
            break MISSING_BLOCK_LABEL_668;
        }
        flag = true;
_L10:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_674;
        }
        arraylist.add(new EmailTaskAssist(assistance, grammarruletype, s, s1));
          goto _L8
        flag = false;
          goto _L10
        if (grammarruletype != GrammarRuleType.BUY_STOCK && grammarruletype != GrammarRuleType.SELL_STOCK)
        {
            break MISSING_BLOCK_LABEL_795;
        }
        FinanceAssistance financeassistance;
        if (assistance.financeAssistance_ == null)
        {
            financeassistance = FinanceAssistance.DEFAULT_INSTANCE;
        } else
        {
            financeassistance = assistance.financeAssistance_;
        }
        if ((financeassistance.bitField0_ & 0x1000) != 4096)
        {
            break MISSING_BLOCK_LABEL_795;
        }
        if (assistance.financeAssistance_ == null)
        {
            financeassistance = FinanceAssistance.DEFAULT_INSTANCE;
        } else
        {
            financeassistance = assistance.financeAssistance_;
        }
        if ((financeassistance.bitField0_ & 0x2000) != 8192)
        {
            break MISSING_BLOCK_LABEL_795;
        }
        flag = true;
_L11:
        if (flag)
        {
            arraylist.add(new FinanceTaskAssist(assistance, grammarruletype, s, s1));
        } else
        {
            if (grammarruletype == GrammarRuleType.RETURN_PRODUCT && (assistance.bitField0_ & 0x20) == 32)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && i + 1 < j && hasViewUrlAction((Assistance)((TaskAssistance) (abyte0)).assistance_.get(i + 1)))
            {
                arraylist.add(new ReturnProductTaskAssist(assistance, (Assistance)((TaskAssistance) (abyte0)).assistance_.get(i + 1), grammarruletype, s, s1));
                i++;
            } else
            {
label1:
                {
                    if (!hasViewUrlAction(assistance))
                    {
                        break label1;
                    }
                    arraylist.add(new UrlTaskAssist(assistance, grammarruletype, s, s1));
                }
            }
        }
          goto _L8
        flag = false;
          goto _L11
        if (grammarruletype != GrammarRuleType.PACK_FOR_PLACE)
        {
            break MISSING_BLOCK_LABEL_1056;
        }
        WeatherAssistance weatherassistance;
        if (assistance.weatherAssistance_ == null)
        {
            weatherassistance = WeatherAssistance.DEFAULT_INSTANCE;
        } else
        {
            weatherassistance = assistance.weatherAssistance_;
        }
        if ((weatherassistance.bitField0_ & 0x400) != 1024)
        {
            break MISSING_BLOCK_LABEL_1056;
        }
        if (assistance.weatherAssistance_ == null)
        {
            weatherassistance = WeatherAssistance.DEFAULT_INSTANCE;
        } else
        {
            weatherassistance = assistance.weatherAssistance_;
        }
        if ((weatherassistance.bitField0_ & 0x800) != 2048)
        {
            break MISSING_BLOCK_LABEL_1056;
        }
        flag = true;
_L12:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_1062;
        }
        arraylist.add(new WeatherTaskAssist(assistance, grammarruletype, s, s1));
          goto _L8
        flag = false;
          goto _L12
        if (grammarruletype != GrammarRuleType.WATCH_VIDEO)
        {
            break MISSING_BLOCK_LABEL_1136;
        }
        WatchYoutubeAction watchyoutubeaction;
        if (assistance.watchYoutubeAction_ == null)
        {
            watchyoutubeaction = WatchYoutubeAction.DEFAULT_INSTANCE;
        } else
        {
            watchyoutubeaction = assistance.watchYoutubeAction_;
        }
        if ((watchyoutubeaction.bitField0_ & 0x20) != 32)
        {
            break MISSING_BLOCK_LABEL_1136;
        }
        flag = true;
_L13:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_1142;
        }
        arraylist.add(new WatchVideoTaskAssist(assistance, grammarruletype, s, s1));
          goto _L8
        flag = false;
          goto _L13
        if (grammarruletype != GrammarRuleType.PAY_BILL)
        {
            break MISSING_BLOCK_LABEL_1252;
        }
        BillAssistance billassistance;
        if (assistance.billAssistance_ == null)
        {
            billassistance = BillAssistance.DEFAULT_INSTANCE;
        } else
        {
            billassistance = assistance.billAssistance_;
        }
        if ((billassistance.bitField0_ & 0x10) != 16)
        {
            break MISSING_BLOCK_LABEL_1252;
        }
        if (assistance.billAssistance_ == null)
        {
            billassistance = BillAssistance.DEFAULT_INSTANCE;
        } else
        {
            billassistance = assistance.billAssistance_;
        }
        if ((billassistance.bitField0_ & 8) != 8)
        {
            break MISSING_BLOCK_LABEL_1252;
        }
        flag = true;
_L14:
        if (flag)
        {
            arraylist.add(new PayBillTaskAssist(assistance, grammarruletype, s, s1));
        }
          goto _L8
        flag = false;
          goto _L14
        abyte0 = arraylist;
_L16:
        if (abyte0.size() > 0)
        {
            return (TaskAssistHolder)abyte0.get(0);
        }
        break; /* Loop/switch isn't completed */
_L2:
        abyte0 = Collections.emptyList();
        if (true) goto _L16; else goto _L15
_L15:
        return null;
        if (true) goto _L18; else goto _L17
_L17:
    }

    public static void fetchAssistance(Context context, TaskAssistService taskassistservice, String s, String s1, Consumer consumer)
    {
        if (context == null)
        {
            return;
        }
        s1 = Platform.nullToEmpty(s1);
        com.google.personalization.assist.annotate.api.AssistanceRequestData.Builder builder = (com.google.personalization.assist.annotate.api.AssistanceRequestData.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)AssistanceRequestData.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        builder.copyOnWrite();
        Object obj = (AssistanceRequestData)builder.instance;
        if (s == null)
        {
            throw new NullPointerException();
        }
        obj.bitField0_ = ((AssistanceRequestData) (obj)).bitField0_ | 1;
        obj.query_ = s;
        builder.copyOnWrite();
        s = (AssistanceRequestData)builder.instance;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        s.bitField0_ = ((AssistanceRequestData) (s)).bitField0_ | 2;
        s.annotationHint_ = s1;
        context = context.getResources().getConfiguration().locale;
        if (context != null)
        {
            s = context.getLanguage();
            builder.copyOnWrite();
            obj = (AssistanceRequestData)builder.instance;
            if (s == null)
            {
                throw new NullPointerException();
            }
            obj.bitField0_ = ((AssistanceRequestData) (obj)).bitField0_ | 0x10;
            obj.language_ = s;
            context = context.getCountry();
            builder.copyOnWrite();
            s = (AssistanceRequestData)builder.instance;
            if (context == null)
            {
                throw new NullPointerException();
            }
            s.bitField0_ = ((AssistanceRequestData) (s)).bitField0_ | 0x20;
            s.country_ = context;
        }
        context = (com.google.personalization.assist.annotate.api.TaskAssistanceRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)TaskAssistanceRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        s = ClientType.TIMELY;
        context.copyOnWrite();
        obj = (TaskAssistanceRequest)((com.google.personalization.assist.annotate.api.TaskAssistanceRequest.Builder) (context)).instance;
        if (s == null)
        {
            throw new NullPointerException();
        }
        obj.bitField0_ = ((TaskAssistanceRequest) (obj)).bitField0_ | 1;
        obj.clientType_ = ((ClientType) (s)).value;
        context.copyOnWrite();
        s = (TaskAssistanceRequest)((com.google.personalization.assist.annotate.api.TaskAssistanceRequest.Builder) (context)).instance;
        if (!((TaskAssistanceRequest) (s)).requestData_.isModifiable())
        {
            s.requestData_ = GeneratedMessageLite.mutableCopy(((TaskAssistanceRequest) (s)).requestData_);
        }
        ((TaskAssistanceRequest) (s)).requestData_.add((AssistanceRequestData)(GeneratedMessageLite)builder.build());
        class .Lambda._cls0
            implements Function
        {

            private final String arg$1;

            public final Object apply(Object obj1)
            {
                return TaskAssistanceUtils.lambda$fetchAssistance$0$TaskAssistanceUtils(arg$1, (TaskAssistanceResponse)obj1);
            }

            .Lambda._cls0(String s)
            {
                arg$1 = s;
            }
        }

        context = AbstractTransformFuture.create(taskassistservice.startRequest(new com.google.android.calendar.net.taskassist.TaskAssistService..Lambda._cls1((TaskAssistanceRequest)(GeneratedMessageLite)context.build())), new .Lambda._cls0(s1), CalendarExecutor.NET);
        taskassistservice = LogUtils.newFailureLoggingCallback(consumer, TAG, "Error while retrieving assist.", new Object[0]);
        s = CalendarExecutor.MAIN;
        if (taskassistservice == null)
        {
            throw new NullPointerException();
        } else
        {
            context.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(context, taskassistservice), s);
            return;
        }
    }

    private static CalendarAssistance getAssistance(byte abyte0[])
    {
        if (abyte0 == null)
        {
            return null;
        }
        GeneratedMessageLite generatedmessagelite = GeneratedMessageLite.parsePartialFrom(CalendarAssistance.DEFAULT_INSTANCE, abyte0);
        if (generatedmessagelite == null)
        {
            break MISSING_BLOCK_LABEL_155;
        }
        int i;
        boolean flag2;
        flag2 = Boolean.TRUE.booleanValue();
        i = ((Byte)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
        if (i != 1) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        if (flag)
        {
            break MISSING_BLOCK_LABEL_155;
        }
        abyte0 = new InvalidProtocolBufferException((new UninitializedMessageException()).getMessage());
        if (abyte0 == null)
        {
            try
            {
                throw null;
            }
            // Misplaced declaration of an exception variable
            catch (byte abyte0[])
            {
                LogUtils.wtf(TAG, abyte0, "Unable to parse assistance protobuf", new Object[0]);
            }
            return null;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (i == 0)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        boolean flag1 = Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).isInitialized(generatedmessagelite);
        flag = flag1;
        if (!flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
        if (flag1)
        {
            abyte0 = generatedmessagelite;
        } else
        {
            abyte0 = null;
        }
        generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, abyte0);
        flag = flag1;
        if (true) goto _L4; else goto _L3
_L3:
        throw abyte0;
        abyte0 = (CalendarAssistance)generatedmessagelite;
        return abyte0;
    }

    public static boolean hasAssist(byte abyte0[])
    {
        if (abyte0 != null)
        {
            abyte0 = getAssistance(abyte0);
            if (abyte0 == null)
            {
                abyte0 = null;
            } else
            if (((CalendarAssistance) (abyte0)).taskAssistance_ == null)
            {
                abyte0 = TaskAssistance.DEFAULT_INSTANCE;
            } else
            {
                abyte0 = ((CalendarAssistance) (abyte0)).taskAssistance_;
            }
            if (abyte0 != null && !((TaskAssistance) (abyte0)).assistance_.isEmpty())
            {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCallAction(Assistance assistance, GrammarRuleType grammarruletype)
    {
        if (grammarruletype != GrammarRuleType.CALL_CONTACT)
        {
            return false;
        }
        if (assistance.callAction_ == null)
        {
            grammarruletype = CallAction.DEFAULT_INSTANCE;
        } else
        {
            grammarruletype = assistance.callAction_;
        }
        if (!((CallAction) (grammarruletype)).phoneNumber_.isEmpty())
        {
            if (assistance.callAction_ == null)
            {
                assistance = CallAction.DEFAULT_INSTANCE;
            } else
            {
                assistance = assistance.callAction_;
            }
            if ((((PhoneNumber)((CallAction) (assistance)).phoneNumber_.get(0)).bitField0_ & 1) == 1)
            {
                return true;
            }
        }
        return false;
    }

    private static boolean hasViewUrlAction(Assistance assistance)
    {
        ViewUrlAction viewurlaction;
        if (assistance.viewUrlAction_ == null)
        {
            viewurlaction = ViewUrlAction.DEFAULT_INSTANCE;
        } else
        {
            viewurlaction = assistance.viewUrlAction_;
        }
        if ((viewurlaction.bitField0_ & 1) == 1)
        {
            if (assistance.viewUrlAction_ == null)
            {
                assistance = ViewUrlAction.DEFAULT_INSTANCE;
            } else
            {
                assistance = assistance.viewUrlAction_;
            }
            if ((((ViewUrlAction) (assistance)).bitField0_ & 2) == 2)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isAssistanceQueryValid(String s, String s1)
    {
        while (TextUtils.isEmpty(s) || !TextUtils.isEmpty(s1) && !s.toLowerCase(Locale.getDefault()).trim().startsWith(s1.toLowerCase(Locale.getDefault()).trim())) 
        {
            return false;
        }
        return true;
    }

    static final byte[] lambda$fetchAssistance$0$TaskAssistanceUtils(String s, TaskAssistanceResponse taskassistanceresponse)
    {
        if (taskassistanceresponse == null || taskassistanceresponse.taskAssistance_.isEmpty())
        {
            throw new IllegalStateException("Empty TaskAssist response, assistance not created");
        }
        taskassistanceresponse = (TaskAssistance)taskassistanceresponse.taskAssistance_.get(0);
        if (taskassistanceresponse == null)
        {
            return createEmptyTaskAssist();
        }
        s = ((com.google.android.calendar.task.CalendarAssistance.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarAssistance.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).setTaskAnnotationHint(s);
        s.copyOnWrite();
        CalendarAssistance calendarassistance = (CalendarAssistance)((com.google.android.calendar.task.CalendarAssistance.Builder) (s)).instance;
        if (taskassistanceresponse == null)
        {
            throw new NullPointerException();
        } else
        {
            calendarassistance.taskAssistance_ = taskassistanceresponse;
            calendarassistance.bitField0_ = calendarassistance.bitField0_ | 2;
            return ((CalendarAssistance)(GeneratedMessageLite)s.build()).toByteArray();
        }
    }

    static 
    {
        TAG = TaskAssistHolder.TAG;
        grammarRuleTypeToFlair = (new com.google.common.collect.ImmutableMap.Builder()).put(GrammarRuleType.APPLY_LICENSE, "documents").put(GrammarRuleType.APPLY_PASSPORT, "documents").put(GrammarRuleType.BOOK_HOTEL, "hotel").put(GrammarRuleType.BOOK_FLIGHT, "flight").put(GrammarRuleType.COMPLETE_GOVERNMENT_FORM, "documents").put(GrammarRuleType.CHECKIN_FLIGHT, "flight").put(GrammarRuleType.PAY_CONTACT, "pay").put(GrammarRuleType.PAY_TAX, "pay").put(GrammarRuleType.RENEW_GREEN_CARD, "documents").put(GrammarRuleType.RENEW_LICENSE, "documents").put(GrammarRuleType.RENEW_PASSPORT, "documents").build();
    }
}
