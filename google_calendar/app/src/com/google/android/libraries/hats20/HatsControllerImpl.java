// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.libraries.hats20.answer.AnswerBeacon;
import com.google.android.libraries.hats20.cookie.HatsCookieManager;
import com.google.android.libraries.hats20.model.MalformedSurveyException;
import com.google.android.libraries.hats20.network.GcsRequest;
import com.google.android.libraries.hats20.storage.HatsDataStore;
import com.google.android.libraries.hats20.util.LayoutDimensions;
import com.google.devrel.hats.proto.AnswerChoice;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.QuestionRating;
import com.google.devrel.hats.proto.QuestionType;
import com.google.devrel.hats.proto.SurveyPayload;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.libraries.hats20:
//            HatsController, HatsDownloadRequest, NetworkExecutor, HatsShowRequest, 
//            SurveyPromptActivity, PromptDialogFragment, PromptDialogDelegate, PlatformPromptDialogFragment

public final class HatsControllerImpl
    implements HatsController
{

    private static final AtomicBoolean isSurveyRunning = new AtomicBoolean(false);

    public HatsControllerImpl()
    {
    }

    static void sendBroadcast(Context context, String s, int i)
    {
        Intent intent = new Intent("com.google.android.libraries.hats20.SURVEY_DOWNLOADED");
        intent.putExtra("SiteId", s);
        intent.putExtra("ResponseCode", i);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public final void downloadSurvey(final HatsDownloadRequest downloadRequest)
    {
        boolean flag;
        flag = true;
        if ("-1".equals(downloadRequest.siteId))
        {
            return;
        }
        synchronized (isSurveyRunning)
        {
            if (!isSurveyRunning.get())
            {
                break MISSING_BLOCK_LABEL_39;
            }
        }
        return;
        downloadRequest;
        atomicboolean;
        JVM INSTR monitorexit ;
        throw downloadRequest;
        final HatsDataStore hatsDataStore;
        String s;
        int i;
        hatsDataStore = new HatsDataStore(HatsDataStore.getSharedPreferences(downloadRequest.context.getApplicationContext()));
        hatsDataStore.removeSurveyIfExpired(downloadRequest.siteId);
        s = downloadRequest.siteId;
        i = hatsDataStore.sharedPreferences.getInt(HatsDataStore.getKeyForPrefSuffix(s, "RESPONSE_CODE"), -1);
        if (i != -1) goto _L2; else goto _L1
_L1:
        String.format("Checking if survey exists, Site ID %s was not in shared preferences.", new Object[] {
            s
        });
_L4:
        if (i == -1)
        {
            break; /* Loop/switch isn't completed */
        }
        atomicboolean;
        JVM INSTR monitorexit ;
        return;
_L2:
        String.format("Checking if survey exists, Site ID %s has response code %d in shared preferences.", new Object[] {
            s, Integer.valueOf(i)
        });
        if (true) goto _L4; else goto _L3
_L3:
        android.net.Uri.Builder builder;
        if (downloadRequest.context.checkCallingOrSelfPermission("android.permission.INTERNET") != 0)
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_174;
        }
        Log.e("HatsLibClient", "Application does not have internet permission. Cannot make network request.");
        atomicboolean;
        JVM INSTR monitorexit ;
        return;
        hatsDataStore = new _cls1();
        builder = Uri.parse(downloadRequest.baseDownloadUrl).buildUpon().appendQueryParameter("lang", "EN").appendQueryParameter("site", downloadRequest.siteId).appendQueryParameter("adid", downloadRequest.advertisingId);
        if (downloadRequest.siteContext != null)
        {
            builder.appendQueryParameter("sc", downloadRequest.siteContext);
        }
        downloadRequest = new GcsRequest(hatsDataStore, builder.build(), HatsCookieManager.getInstance(downloadRequest.context));
        NetworkExecutor.getNetworkExecutor().execute(new _cls2());
        atomicboolean;
        JVM INSTR monitorexit ;
    }

    public final void markSurveyFinished()
    {
        synchronized (isSurveyRunning)
        {
            if (!isSurveyRunning.get())
            {
                Log.e("HatsLibClient", "Notified that survey was destroyed when it wasn't marked as running.");
            }
            isSurveyRunning.set(false);
        }
        return;
        exception;
        atomicboolean;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void markSurveyRunning()
    {
        synchronized (isSurveyRunning)
        {
            isSurveyRunning.set(true);
        }
        return;
        exception;
        atomicboolean;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final boolean showSurveyIfAvailable(HatsShowRequest hatsshowrequest)
    {
        if ("-1".equals(hatsshowrequest.siteId))
        {
            return false;
        }
        AtomicBoolean atomicboolean = isSurveyRunning;
        atomicboolean;
        JVM INSTR monitorenter ;
        if (!isSurveyRunning.get())
        {
            break MISSING_BLOCK_LABEL_42;
        }
        return false;
        atomicboolean;
        JVM INSTR monitorexit ;
        throw hatsshowrequest;
        Activity activity;
        boolean flag;
        activity = hatsshowrequest.clientActivity;
        flag = activity.isDestroyed();
        if (activity == null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        if (!activity.isFinishing() && !flag)
        {
            break MISSING_BLOCK_LABEL_86;
        }
        Log.w("HatsLibClient", "Cancelling show request, activity was null, destroyed or finishing.");
        atomicboolean;
        JVM INSTR monitorexit ;
        return false;
        String s;
        Integer integer;
        Object obj3;
        int i;
        s = hatsshowrequest.siteId;
        integer = hatsshowrequest.requestCode;
        obj3 = new HatsDataStore(HatsDataStore.getSharedPreferences(hatsshowrequest.clientActivity.getApplicationContext()));
        ((HatsDataStore) (obj3)).removeSurveyIfExpired(s);
        i = ((HatsDataStore) (obj3)).sharedPreferences.getInt(HatsDataStore.getKeyForPrefSuffix(s, "RESPONSE_CODE"), -1);
        if (i != -1) goto _L2; else goto _L1
_L1:
        String.format("Checking for survey to show, Site ID %s was not in shared preferences.", new Object[] {
            s
        });
          goto _L3
_L216:
        if (i != 0) goto _L5; else goto _L4
_L4:
        atomicboolean;
        JVM INSTR monitorexit ;
        return false;
_L2:
        String.format("Checking for survey to show, Site ID %s has response code %d in shared preferences.", new Object[] {
            s, Integer.valueOf(i)
        });
          goto _L3
_L5:
        Object obj = ((HatsDataStore) (obj3)).sharedPreferences.getString(HatsDataStore.getKeyForPrefSuffix(s, "CONTENT"), null);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_234;
        }
        if (!((String) (obj)).isEmpty())
        {
            break MISSING_BLOCK_LABEL_260;
        }
        Log.e("HatsLibClient", String.format("Attempted to start survey with site ID %s, but the json in the shared preferences was not found or was empty.", new Object[] {
            s
        }));
        atomicboolean;
        JVM INSTR monitorexit ;
        return false;
        Object obj2;
        Object obj4;
        Object obj5;
        Object obj6;
        obj5 = activity.getResources();
        obj4 = (new JSONObject(((String) (obj)))).getJSONObject("params");
        obj2 = (com.google.hats.protos.HatsSurveyData.Survey.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.hats.protos.HatsSurveyData.Survey.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj6 = ((JSONObject) (obj4)).getJSONArray("tags");
        int j = 0;
_L217:
        if (j >= ((JSONArray) (obj6)).length()) goto _L7; else goto _L6
_L6:
        String as[] = ((JSONArray) (obj6)).getString(j).split("=", 2);
        if (as.length == 2) goto _L9; else goto _L8
_L8:
        Log.e("Surveys", String.format("Tag couldn't be split: %s", new Object[] {
            ((JSONArray) (obj6)).getString(j)
        }));
          goto _L10
_L9:
        Object obj7;
        Object obj8;
        obj8 = as[0];
        obj7 = as[1];
        i = -1;
        ((String) (obj8)).hashCode();
        JVM INSTR lookupswitch 9: default 5211
    //                   -1979035557: 572
    //                   -1765207296: 659
    //                   -1505536394: 521
    //                   -1336354446: 555
    //                   -1268779017: 606
    //                   -1224386186: 641
    //                   -1179592925: 623
    //                   -453401085: 538
    //                   1616797635: 589;
           goto _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L218:
        Log.w("Surveys", String.format("Skipping unknown tag '%s'", new Object[] {
            obj8
        }));
          goto _L10
        hatsshowrequest;
        Log.e("HatsLibClient", hatsshowrequest.getMessage());
        atomicboolean;
        JVM INSTR monitorexit ;
        return false;
_L14:
        if (((String) (obj8)).equals("showInvitation"))
        {
            i = 0;
        }
          goto _L11
_L19:
        if (((String) (obj8)).equals("promptMessage"))
        {
            i = 1;
        }
          goto _L11
_L15:
        if (((String) (obj8)).equals("thankYouMessage"))
        {
            i = 2;
        }
          goto _L11
_L12:
        if (((String) (obj8)).equals("followupMessage"))
        {
            i = 3;
        }
          goto _L11
_L20:
        if (((String) (obj8)).equals("followupUrl"))
        {
            i = 4;
        }
          goto _L11
_L16:
        if (((String) (obj8)).equals("format"))
        {
            i = 5;
        }
          goto _L11
_L18:
        if (((String) (obj8)).equals("hatsClient"))
        {
            i = 6;
        }
          goto _L11
_L17:
        if (((String) (obj8)).equals("hats20"))
        {
            i = 7;
        }
          goto _L11
_L13:
        if (((String) (obj8)).equals("hatsNoRateLimiting"))
        {
            i = 8;
        }
          goto _L11
_L219:
        flag = Boolean.parseBoolean(((String) (obj7)));
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj7 = (com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance;
        obj7.bitField0_ = ((com.google.hats.protos.HatsSurveyData.Survey) (obj7)).bitField0_ | 4;
        obj7.showInvitation_ = flag;
          goto _L10
        hatsshowrequest;
        Log.e("HatsLibClient", (new StringBuilder(String.valueOf(s).length() + 46)).append("Failed to parse JSON for survey with site ID ").append(s).append(".").toString(), hatsshowrequest);
        atomicboolean;
        JVM INSTR monitorexit ;
        return false;
_L220:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj8 = (com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance;
        if (obj7 != null) goto _L22; else goto _L21
_L21:
        throw new NullPointerException();
_L22:
        obj8.bitField0_ = ((com.google.hats.protos.HatsSurveyData.Survey) (obj8)).bitField0_ | 8;
        obj8.promptMessage_ = ((String) (obj7));
          goto _L10
_L221:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj8 = (com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance;
        if (obj7 != null) goto _L24; else goto _L23
_L23:
        throw new NullPointerException();
_L24:
        obj8.bitField0_ = ((com.google.hats.protos.HatsSurveyData.Survey) (obj8)).bitField0_ | 0x10;
        obj8.thankYouMessage_ = ((String) (obj7));
          goto _L10
_L222:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj8 = (com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance;
        if (obj7 != null) goto _L26; else goto _L25
_L25:
        throw new NullPointerException();
_L26:
        obj8.bitField0_ = ((com.google.hats.protos.HatsSurveyData.Survey) (obj8)).bitField0_ | 0x80;
        obj8.followUpMessage_ = ((String) (obj7));
          goto _L10
_L223:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj8 = (com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance;
        if (obj7 != null) goto _L28; else goto _L27
_L27:
        throw new NullPointerException();
_L28:
        obj8.bitField0_ = ((com.google.hats.protos.HatsSurveyData.Survey) (obj8)).bitField0_ | 0x100;
        obj8.followUpUrl_ = ((String) (obj7));
          goto _L10
_L7:
        if (!((com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance).showInvitation_ && !TextUtils.isEmpty(((com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance).promptMessage_))
        {
            Log.w("Surveys", String.format("Survey is promptless but a prompt message was parsed: %s", new Object[] {
                ((com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance).promptMessage_
            }));
        }
        if (!((com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance).showInvitation_ || !TextUtils.isEmpty(((com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance).promptMessage_)) goto _L30; else goto _L29
_L29:
        obj6 = ((Resources) (obj5)).getString(0x7f1302e8);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj7 = (com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance;
        if (obj6 != null) goto _L32; else goto _L31
_L31:
        throw new NullPointerException();
_L32:
        obj7.bitField0_ = ((com.google.hats.protos.HatsSurveyData.Survey) (obj7)).bitField0_ | 8;
        obj7.promptMessage_ = ((String) (obj6));
_L30:
        if (!TextUtils.isEmpty(((com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance).thankYouMessage_)) goto _L34; else goto _L33
_L33:
        obj5 = ((Resources) (obj5)).getString(0x7f1302ea);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj6 = (com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance;
        if (obj5 != null) goto _L36; else goto _L35
_L35:
        throw new NullPointerException();
_L36:
        obj6.bitField0_ = ((com.google.hats.protos.HatsSurveyData.Survey) (obj6)).bitField0_ | 0x10;
        obj6.thankYouMessage_ = ((String) (obj5));
_L34:
        obj5 = ((JSONObject) (obj4)).optString("promptParams");
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj6 = (com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance;
        if (obj5 != null) goto _L38; else goto _L37
_L37:
        throw new NullPointerException();
_L38:
        obj6.bitField0_ = ((com.google.hats.protos.HatsSurveyData.Survey) (obj6)).bitField0_ | 0x20;
        obj6.promptParams_ = ((String) (obj5));
        obj4 = ((JSONObject) (obj4)).optString("answerUrl");
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj5 = (com.google.hats.protos.HatsSurveyData.Survey)((com.google.hats.protos.HatsSurveyData.Survey.Builder) (obj2)).instance;
        if (obj4 != null) goto _L40; else goto _L39
_L39:
        throw new NullPointerException();
_L40:
        obj5.bitField0_ = ((com.google.hats.protos.HatsSurveyData.Survey) (obj5)).bitField0_ | 0x40;
        obj5.answerUrl_ = ((String) (obj4));
        obj4 = (com.google.hats.protos.HatsSurveyData.Survey)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).build();
        if (TextUtils.isEmpty(((com.google.hats.protos.HatsSurveyData.Survey) (obj4)).answerUrl_))
        {
            throw new MalformedSurveyException("Survey did not have an AnswerUrl, this is a GCS issue.");
        }
        if (TextUtils.isEmpty(((com.google.hats.protos.HatsSurveyData.Survey) (obj4)).promptParams_))
        {
            throw new MalformedSurveyException("Survey did not have prompt params, this is a GCS issue.");
        }
        obj = (new JSONObject(((String) (obj)))).getJSONObject("params");
        obj5 = (com.google.devrel.hats.proto.SurveyPayload.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)SurveyPayload.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj7 = ((JSONObject) (obj)).getJSONObject("payload").getJSONArray("longform_questions");
        obj6 = new ArrayList();
        j = 0;
_L150:
        if (j >= ((JSONArray) (obj7)).length()) goto _L42; else goto _L41
_L41:
        JSONObject jsonobject;
        jsonobject = ((JSONArray) (obj7)).getJSONObject(j);
        obj8 = (com.google.devrel.hats.proto.Question.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)Question.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj = jsonobject.optString("question");
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
        obj2 = (Question)((com.google.devrel.hats.proto.Question.Builder) (obj8)).instance;
        if (obj != null) goto _L44; else goto _L43
_L43:
        Object obj9;
        Object obj10;
        String s1;
        AnswerChoice answerchoice;
        int k;
        try
        {
            throw new NullPointerException();
        }
        // Misplaced declaration of an exception variable
        catch (HatsShowRequest hatsshowrequest) { }
        // Misplaced declaration of an exception variable
        catch (HatsShowRequest hatsshowrequest) { }
        finally { }
        Log.e("HatsLibClient", (new StringBuilder(String.valueOf(s).length() + 54)).append("Failed to parse JSON for survey payload with site ID ").append(s).append(".").toString(), hatsshowrequest);
        atomicboolean;
        JVM INSTR monitorexit ;
        return false;
_L44:
        obj2.questionText_ = ((String) (obj));
        obj = jsonobject.getString("type");
        i = -1;
        ((String) (obj)).hashCode();
        JVM INSTR lookupswitch 4: default 5267
    //                   -938102371: 1670
    //                   104256825: 1622
    //                   1123690512: 1638
    //                   1500159696: 1654;
           goto _L45 _L46 _L47 _L48 _L49
_L224:
        throw new MalformedSurveyException(String.format("Question string %s was not found in the json to QuestionType map", new Object[] {
            obj
        }));
_L47:
        if (((String) (obj)).equals("multi"))
        {
            i = 0;
        }
          goto _L45
_L48:
        if (((String) (obj)).equals("multi-select"))
        {
            i = 1;
        }
          goto _L45
_L49:
        if (((String) (obj)).equals("open-text"))
        {
            i = 2;
        }
          goto _L45
_L46:
        if (((String) (obj)).equals("rating"))
        {
            i = 3;
        }
          goto _L45
_L225:
        obj = QuestionType.MULTIPLE_CHOICE;
_L52:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
        obj2 = (Question)((com.google.devrel.hats.proto.Question.Builder) (obj8)).instance;
        if (obj != null) goto _L51; else goto _L50
_L50:
        throw new NullPointerException();
_L226:
        obj = QuestionType.MULTIPLE_SELECT;
          goto _L52
_L227:
        obj = QuestionType.OPEN_TEXT;
          goto _L52
_L228:
        obj = QuestionType.RATING;
          goto _L52
_L51:
        if (obj == QuestionType.UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        obj2.questionType_ = ((QuestionType) (obj)).value;
        obj2 = jsonobject.optJSONArray("threshold_answers");
        if (obj2 != null) goto _L54; else goto _L53
_L53:
        obj1 = Collections.emptyList();
_L66:
        obj9 = jsonobject.optJSONArray("answers");
        if (obj9 != null) goto _L56; else goto _L55
_L55:
        obj1 = Collections.emptyList();
_L229:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
        obj2 = (Question)((com.google.devrel.hats.proto.Question.Builder) (obj8)).instance;
        if (!((Question) (obj2)).answerChoice_.isModifiable())
        {
            obj2.answerChoice_ = GeneratedMessageLite.mutableCopy(((Question) (obj2)).answerChoice_);
        }
        obj2 = ((Question) (obj2)).answerChoice_;
        Internal.checkNotNull(obj1);
        if (!(obj1 instanceof LazyStringList)) goto _L58; else goto _L57
_L57:
        obj9 = ((LazyStringList)obj1).getUnderlyingElements();
        obj1 = (LazyStringList)obj2;
        k = ((List) (obj2)).size();
        obj2 = ((List) (obj9)).iterator();
_L79:
        if (!((Iterator) (obj2)).hasNext()) goto _L60; else goto _L59
_L59:
        obj9 = ((Iterator) (obj2)).next();
        if (obj9 != null) goto _L62; else goto _L61
_L61:
        i = ((LazyStringList) (obj1)).size();
        hatsshowrequest = (new StringBuilder(37)).append("Element at index ").append(i - k).append(" is null.").toString();
        i = ((LazyStringList) (obj1)).size() - 1;
_L64:
        if (i < k)
        {
            break; /* Loop/switch isn't completed */
        }
        ((LazyStringList) (obj1)).remove(i);
        i--;
        if (true) goto _L64; else goto _L63
_L54:
        obj1 = new ArrayList();
        i = 0;
_L68:
        if (i >= ((JSONArray) (obj2)).length()) goto _L66; else goto _L65
_L65:
        ((List) (obj1)).add(((JSONArray) (obj2)).getString(i));
        i++;
        if (true) goto _L68; else goto _L67
_L67:
          goto _L66
_L56:
        obj2 = new ArrayList();
        i = 0;
_L76:
        if (i >= ((JSONArray) (obj9)).length()) goto _L70; else goto _L69
_L69:
        if (obj1 == null) goto _L72; else goto _L71
_L71:
        if (((List) (obj1)).isEmpty() || ((List) (obj1)).contains(((JSONArray) (obj9)).getString(i))) goto _L72; else goto _L73
_L73:
        k = -1;
_L215:
        obj10 = (com.google.devrel.hats.proto.AnswerChoice.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)AnswerChoice.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        s1 = ((JSONArray) (obj9)).getString(i);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj10)).copyOnWrite();
        answerchoice = (AnswerChoice)((com.google.devrel.hats.proto.AnswerChoice.Builder) (obj10)).instance;
        if (s1 != null) goto _L75; else goto _L74
_L74:
        throw new NullPointerException();
_L75:
        answerchoice.choiceText_ = s1;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj10)).copyOnWrite();
        ((AnswerChoice)((com.google.devrel.hats.proto.AnswerChoice.Builder) (obj10)).instance).skipTo_ = k;
        ((List) (obj2)).add((AnswerChoice)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj10)).build());
        i++;
          goto _L76
_L63:
        throw new NullPointerException(hatsshowrequest);
_L62:
        if (!(obj9 instanceof ByteString)) goto _L78; else goto _L77
_L77:
        ((LazyStringList) (obj1)).add((ByteString)obj9);
          goto _L79
_L78:
        ((LazyStringList) (obj1)).add((String)obj9);
          goto _L79
_L58:
        if (!(obj1 instanceof PrimitiveNonBoxingCollection)) goto _L81; else goto _L80
_L80:
        ((List) (obj2)).addAll((Collection)obj1);
_L60:
        obj9 = jsonobject.optJSONArray("ordering");
        if (obj9 != null) goto _L83; else goto _L82
_L82:
        obj1 = Collections.emptyList();
_L100:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
        obj2 = (Question)((com.google.devrel.hats.proto.Question.Builder) (obj8)).instance;
        if (!((Question) (obj2)).ordering_.isModifiable())
        {
            obj2.ordering_ = GeneratedMessageLite.mutableCopy(((Question) (obj2)).ordering_);
        }
        obj2 = ((Question) (obj2)).ordering_;
        Internal.checkNotNull(obj1);
        if (!(obj1 instanceof LazyStringList)) goto _L85; else goto _L84
_L84:
        obj9 = ((LazyStringList)obj1).getUnderlyingElements();
        obj1 = (LazyStringList)obj2;
        k = ((List) (obj2)).size();
        obj2 = ((List) (obj9)).iterator();
_L104:
        if (!((Iterator) (obj2)).hasNext()) goto _L87; else goto _L86
_L86:
        obj9 = ((Iterator) (obj2)).next();
        if (obj9 != null) goto _L89; else goto _L88
_L88:
        i = ((LazyStringList) (obj1)).size();
        hatsshowrequest = (new StringBuilder(37)).append("Element at index ").append(i - k).append(" is null.").toString();
        i = ((LazyStringList) (obj1)).size() - 1;
_L91:
        if (i < k)
        {
            break; /* Loop/switch isn't completed */
        }
        ((LazyStringList) (obj1)).remove(i);
        i--;
        if (true) goto _L91; else goto _L90
_L81:
        if ((obj2 instanceof ArrayList) && (obj1 instanceof Collection))
        {
            obj9 = (ArrayList)obj2;
            i = ((List) (obj2)).size();
            ((ArrayList) (obj9)).ensureCapacity(((Collection)obj1).size() + i);
        }
        k = ((List) (obj2)).size();
        obj1 = ((Iterable) (obj1)).iterator();
_L98:
        if (!((Iterator) (obj1)).hasNext()) goto _L60; else goto _L92
_L92:
        obj9 = ((Iterator) (obj1)).next();
        if (obj9 != null) goto _L94; else goto _L93
_L93:
        i = ((List) (obj2)).size();
        hatsshowrequest = (new StringBuilder(37)).append("Element at index ").append(i - k).append(" is null.").toString();
        i = ((List) (obj2)).size() - 1;
_L96:
        if (i < k)
        {
            break; /* Loop/switch isn't completed */
        }
        ((List) (obj2)).remove(i);
        i--;
        if (true) goto _L96; else goto _L95
_L95:
        throw new NullPointerException(hatsshowrequest);
_L94:
        ((List) (obj2)).add(obj9);
        if (true) goto _L98; else goto _L97
_L97:
          goto _L60
_L83:
        obj2 = new ArrayList();
        i = 0;
_L101:
        obj1 = obj2;
        if (i >= ((JSONArray) (obj9)).length()) goto _L100; else goto _L99
_L99:
        ((List) (obj2)).add(Integer.valueOf(((JSONArray) (obj9)).getInt(i)));
        i++;
          goto _L101
_L90:
        throw new NullPointerException(hatsshowrequest);
_L89:
        if (!(obj9 instanceof ByteString)) goto _L103; else goto _L102
_L102:
        ((LazyStringList) (obj1)).add((ByteString)obj9);
          goto _L104
_L103:
        ((LazyStringList) (obj1)).add((String)obj9);
          goto _L104
_L85:
        if (!(obj1 instanceof PrimitiveNonBoxingCollection)) goto _L106; else goto _L105
_L105:
        ((List) (obj2)).addAll((Collection)obj1);
_L87:
        obj2 = QuestionType.forNumber(((Question)((com.google.devrel.hats.proto.Question.Builder) (obj8)).instance).questionType_);
        obj1 = obj2;
        if (obj2 != null) goto _L108; else goto _L107
_L107:
        obj1 = QuestionType.UNRECOGNIZED;
_L108:
        if (obj1 != QuestionType.RATING) goto _L110; else goto _L109
_L109:
        obj1 = (com.google.devrel.hats.proto.QuestionRating.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)QuestionRating.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj2 = jsonobject.optString("low_value");
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
        obj9 = (QuestionRating)((com.google.devrel.hats.proto.QuestionRating.Builder) (obj1)).instance;
        if (obj2 != null) goto _L112; else goto _L111
_L111:
        throw new NullPointerException();
_L106:
        if ((obj2 instanceof ArrayList) && (obj1 instanceof Collection))
        {
            obj9 = (ArrayList)obj2;
            i = ((List) (obj2)).size();
            ((ArrayList) (obj9)).ensureCapacity(((Collection)obj1).size() + i);
        }
        k = ((List) (obj2)).size();
        obj1 = ((Iterable) (obj1)).iterator();
_L119:
        if (!((Iterator) (obj1)).hasNext()) goto _L87; else goto _L113
_L113:
        obj9 = ((Iterator) (obj1)).next();
        if (obj9 != null) goto _L115; else goto _L114
_L114:
        i = ((List) (obj2)).size();
        hatsshowrequest = (new StringBuilder(37)).append("Element at index ").append(i - k).append(" is null.").toString();
        i = ((List) (obj2)).size() - 1;
_L117:
        if (i < k)
        {
            break; /* Loop/switch isn't completed */
        }
        ((List) (obj2)).remove(i);
        i--;
        if (true) goto _L117; else goto _L116
_L116:
        throw new NullPointerException(hatsshowrequest);
_L115:
        ((List) (obj2)).add(obj9);
        if (true) goto _L119; else goto _L118
_L118:
          goto _L87
_L112:
        if (!((QuestionRating) (obj9)).label_.isModifiable())
        {
            obj9.label_ = GeneratedMessageLite.mutableCopy(((QuestionRating) (obj9)).label_);
        }
        ((QuestionRating) (obj9)).label_.add(obj2);
        obj2 = jsonobject.optString("high_value");
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
        obj9 = (QuestionRating)((com.google.devrel.hats.proto.QuestionRating.Builder) (obj1)).instance;
        if (obj2 != null) goto _L121; else goto _L120
_L120:
        throw new NullPointerException();
_L121:
        if (!((QuestionRating) (obj9)).label_.isModifiable())
        {
            obj9.label_ = GeneratedMessageLite.mutableCopy(((QuestionRating) (obj9)).label_);
        }
        ((QuestionRating) (obj9)).label_.add(obj2);
        i = jsonobject.optInt("num_stars");
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
        ((QuestionRating)((com.google.devrel.hats.proto.QuestionRating.Builder) (obj1)).instance).numStars_ = i;
        obj2 = jsonobject.optJSONArray("threshold_answers");
        obj9 = Arrays.asList(new Integer[jsonobject.optInt("num_stars")]);
        Collections.fill(((List) (obj9)), Integer.valueOf(0));
        if (obj2 == null) goto _L123; else goto _L122
_L122:
        if (((JSONArray) (obj2)).length() != 0) goto _L124; else goto _L123
_L123:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
        obj2 = (QuestionRating)((com.google.devrel.hats.proto.QuestionRating.Builder) (obj1)).instance;
        if (!((QuestionRating) (obj2)).skipTo_.isModifiable())
        {
            obj2.skipTo_ = GeneratedMessageLite.mutableCopy(((QuestionRating) (obj2)).skipTo_);
        }
        obj2 = ((QuestionRating) (obj2)).skipTo_;
        Internal.checkNotNull(obj9);
        if (!(obj9 instanceof LazyStringList)) goto _L126; else goto _L125
_L125:
        obj10 = ((LazyStringList)obj9).getUnderlyingElements();
        obj9 = (LazyStringList)obj2;
        k = ((List) (obj2)).size();
        obj2 = ((List) (obj10)).iterator();
_L138:
        if (!((Iterator) (obj2)).hasNext()) goto _L128; else goto _L127
_L127:
        obj10 = ((Iterator) (obj2)).next();
        if (obj10 != null) goto _L130; else goto _L129
_L129:
        i = ((LazyStringList) (obj9)).size();
        hatsshowrequest = (new StringBuilder(37)).append("Element at index ").append(i - k).append(" is null.").toString();
        i = ((LazyStringList) (obj9)).size() - 1;
_L132:
        if (i < k)
        {
            break; /* Loop/switch isn't completed */
        }
        ((LazyStringList) (obj9)).remove(i);
        i--;
        if (true) goto _L132; else goto _L131
_L124:
        Collections.fill(((List) (obj9)), Integer.valueOf(-1));
        i = 0;
_L135:
        if (i >= ((JSONArray) (obj2)).length()) goto _L123; else goto _L133
_L133:
        ((List) (obj9)).set(Integer.parseInt(((JSONArray) (obj2)).getString(i)) - 1, Integer.valueOf(0));
        i++;
        if (true) goto _L135; else goto _L134
_L134:
          goto _L123
_L131:
        throw new NullPointerException(hatsshowrequest);
_L130:
        if (!(obj10 instanceof ByteString)) goto _L137; else goto _L136
_L136:
        ((LazyStringList) (obj9)).add((ByteString)obj10);
          goto _L138
_L137:
        ((LazyStringList) (obj9)).add((String)obj10);
          goto _L138
_L126:
        if (!(obj9 instanceof PrimitiveNonBoxingCollection)) goto _L140; else goto _L139
_L139:
        ((List) (obj2)).addAll((Collection)obj9);
_L128:
        obj1 = (QuestionRating)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
        obj2 = (Question)((com.google.devrel.hats.proto.Question.Builder) (obj8)).instance;
        if (obj1 != null)
        {
            break; /* Loop/switch isn't completed */
        }
        throw new NullPointerException();
_L140:
        if ((obj2 instanceof ArrayList) && (obj9 instanceof Collection))
        {
            obj10 = (ArrayList)obj2;
            i = ((List) (obj2)).size();
            ((ArrayList) (obj10)).ensureCapacity(((Collection)obj9).size() + i);
        }
        k = ((List) (obj2)).size();
        obj9 = ((Iterable) (obj9)).iterator();
_L146:
        if (!((Iterator) (obj9)).hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        obj10 = ((Iterator) (obj9)).next();
        if (obj10 != null) goto _L142; else goto _L141
_L141:
        i = ((List) (obj2)).size();
        hatsshowrequest = (new StringBuilder(37)).append("Element at index ").append(i - k).append(" is null.").toString();
        i = ((List) (obj2)).size() - 1;
_L144:
        if (i < k)
        {
            break; /* Loop/switch isn't completed */
        }
        ((List) (obj2)).remove(i);
        i--;
        if (true) goto _L144; else goto _L143
_L143:
        throw new NullPointerException(hatsshowrequest);
_L142:
        ((List) (obj2)).add(obj10);
        if (true) goto _L146; else goto _L145
_L145:
        if (true) goto _L128; else goto _L147
_L147:
        obj2.questionRating_ = ((QuestionRating) (obj1));
_L110:
        if (!"smileys".equals(jsonobject.optString("sprite_name"))) goto _L149; else goto _L148
_L148:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
        ((Question)((com.google.devrel.hats.proto.Question.Builder) (obj8)).instance).isSmiley_ = true;
_L154:
        ((List) (obj6)).add((Question)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).build());
        String.format(Locale.US, "Parsed question %d of %d with content %s", new Object[] {
            Integer.valueOf(j + 1), Integer.valueOf(((JSONArray) (obj7)).length()), obj8.toString()
        });
        j++;
          goto _L150
_L149:
        obj2 = QuestionType.forNumber(((Question)((com.google.devrel.hats.proto.Question.Builder) (obj8)).instance).questionType_);
        obj1 = obj2;
        if (obj2 != null) goto _L152; else goto _L151
_L151:
        obj1 = QuestionType.UNRECOGNIZED;
_L152:
        if (obj1 != QuestionType.RATING) goto _L154; else goto _L153
_L153:
        obj1 = (Question)((com.google.devrel.hats.proto.Question.Builder) (obj8)).instance;
        if (((Question) (obj1)).questionRating_ != null) goto _L156; else goto _L155
_L155:
        obj1 = QuestionRating.DEFAULT_INSTANCE;
_L157:
        if (((QuestionRating) (obj1)).numStars_ == 5)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj8)).copyOnWrite();
        ((Question)((com.google.devrel.hats.proto.Question.Builder) (obj8)).instance).isSmiley_ = flag;
          goto _L154
_L156:
        obj1 = ((Question) (obj1)).questionRating_;
          goto _L157
_L42:
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj5)).copyOnWrite();
        obj1 = (SurveyPayload)((com.google.devrel.hats.proto.SurveyPayload.Builder) (obj5)).instance;
        if (!((SurveyPayload) (obj1)).question_.isModifiable())
        {
            obj1.question_ = GeneratedMessageLite.mutableCopy(((SurveyPayload) (obj1)).question_);
        }
        obj1 = ((SurveyPayload) (obj1)).question_;
        Internal.checkNotNull(obj6);
        if (!(obj6 instanceof LazyStringList)) goto _L159; else goto _L158
_L158:
        obj6 = ((LazyStringList)obj6).getUnderlyingElements();
        obj2 = (LazyStringList)obj1;
        j = ((List) (obj1)).size();
        obj1 = ((List) (obj6)).iterator();
_L168:
        if (!((Iterator) (obj1)).hasNext()) goto _L161; else goto _L160
_L160:
        obj6 = ((Iterator) (obj1)).next();
        if (obj6 != null) goto _L163; else goto _L162
_L162:
        i = ((LazyStringList) (obj2)).size();
        hatsshowrequest = (new StringBuilder(37)).append("Element at index ").append(i - j).append(" is null.").toString();
        i = ((LazyStringList) (obj2)).size() - 1;
_L165:
        if (i < j)
        {
            break; /* Loop/switch isn't completed */
        }
        ((LazyStringList) (obj2)).remove(i);
        i--;
        if (true) goto _L165; else goto _L164
_L164:
        throw new NullPointerException(hatsshowrequest);
_L163:
        if (!(obj6 instanceof ByteString)) goto _L167; else goto _L166
_L166:
        ((LazyStringList) (obj2)).add((ByteString)obj6);
          goto _L168
_L167:
        ((LazyStringList) (obj2)).add((String)obj6);
          goto _L168
_L159:
        if (!(obj6 instanceof PrimitiveNonBoxingCollection)) goto _L170; else goto _L169
_L169:
        ((List) (obj1)).addAll((Collection)obj6);
_L161:
        obj5 = (SurveyPayload)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj5)).build();
        if (obj5 == null) goto _L172; else goto _L171
_L171:
        if (((SurveyPayload) (obj5)).question_.size() != 0)
        {
            break; /* Loop/switch isn't completed */
        }
_L172:
        throw new MalformedSurveyException("Survey has no questions.");
_L170:
        if ((obj1 instanceof ArrayList) && (obj6 instanceof Collection))
        {
            obj2 = (ArrayList)obj1;
            i = ((List) (obj1)).size();
            ((ArrayList) (obj2)).ensureCapacity(((Collection)obj6).size() + i);
        }
        j = ((List) (obj1)).size();
        obj2 = ((Iterable) (obj6)).iterator();
_L178:
        if (!((Iterator) (obj2)).hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        obj6 = ((Iterator) (obj2)).next();
        if (obj6 != null) goto _L174; else goto _L173
_L173:
        i = ((List) (obj1)).size();
        hatsshowrequest = (new StringBuilder(37)).append("Element at index ").append(i - j).append(" is null.").toString();
        i = ((List) (obj1)).size() - 1;
_L176:
        if (i < j)
        {
            break; /* Loop/switch isn't completed */
        }
        ((List) (obj1)).remove(i);
        i--;
        if (true) goto _L176; else goto _L175
_L175:
        throw new NullPointerException(hatsshowrequest);
_L174:
        ((List) (obj1)).add(obj6);
        if (true) goto _L178; else goto _L177
_L177:
        if (true) goto _L161; else goto _L179
_L198:
        if (i >= ((SurveyPayload) (obj5)).question_.size()) goto _L181; else goto _L180
_L180:
        obj6 = (Question)((SurveyPayload) (obj5)).question_.get(i);
        if (TextUtils.isEmpty(((Question) (obj6)).questionText_))
        {
            throw new MalformedSurveyException((new StringBuilder(43)).append("Question #").append(i + 1).append(" had no question text.").toString());
        }
        obj2 = QuestionType.forNumber(((Question) (obj6)).questionType_);
        obj1 = obj2;
        if (obj2 != null) goto _L183; else goto _L182
_L182:
        obj1 = QuestionType.UNRECOGNIZED;
_L183:
        if (obj1 == QuestionType.MULTIPLE_SELECT) goto _L185; else goto _L184
_L184:
        obj2 = QuestionType.forNumber(((Question) (obj6)).questionType_);
        obj1 = obj2;
        if (obj2 != null) goto _L187; else goto _L186
_L186:
        obj1 = QuestionType.UNRECOGNIZED;
_L187:
        if (obj1 != QuestionType.MULTIPLE_CHOICE) goto _L188; else goto _L185
_L185:
        if (((Question) (obj6)).answerChoice_.size() == 0)
        {
            throw new MalformedSurveyException((new StringBuilder(42)).append("Question #").append(i + 1).append(" was missing answers.").toString());
        }
        if (((Question) (obj6)).ordering_.size() == 0)
        {
            throw new MalformedSurveyException((new StringBuilder(74)).append("Question #").append(i + 1).append(" was missing an ordering, this likely is a GCS issue.").toString());
        }
_L188:
        obj2 = QuestionType.forNumber(((Question) (obj6)).questionType_);
        obj1 = obj2;
        if (obj2 != null) goto _L190; else goto _L189
_L189:
        obj1 = QuestionType.UNRECOGNIZED;
_L190:
        if (obj1 != QuestionType.RATING) goto _L192; else goto _L191
_L191:
        if (((Question) (obj6)).questionRating_ == null)
        {
            obj1 = QuestionRating.DEFAULT_INSTANCE;
        } else
        {
            obj1 = ((Question) (obj6)).questionRating_;
        }
        if (TextUtils.isEmpty((String)((QuestionRating) (obj1)).label_.get(0))) goto _L194; else goto _L193
_L193:
        if (((Question) (obj6)).questionRating_ != null) goto _L196; else goto _L195
_L195:
        obj1 = QuestionRating.DEFAULT_INSTANCE;
_L197:
        if (!TextUtils.isEmpty((String)((QuestionRating) (obj1)).label_.get(1))) goto _L192; else goto _L194
_L194:
        throw new MalformedSurveyException("A rating question was missing its high/low text.");
_L196:
        obj1 = ((Question) (obj6)).questionRating_;
          goto _L197
_L192:
        i++;
          goto _L198
_L181:
        synchronized (isSurveyRunning)
        {
            isSurveyRunning.set(true);
        }
        ((HatsDataStore) (obj3)).removeSurvey(s);
        obj3 = new AnswerBeacon();
        obj1 = ((com.google.hats.protos.HatsSurveyData.Survey) (obj4)).promptParams_;
        if (obj1 != null) goto _L200; else goto _L199
_L199:
        ((AnswerBeacon) (obj3)).parameterBundle.remove("p");
_L206:
        obj2 = QuestionType.forNumber(((Question)((SurveyPayload) (obj5)).question_.get(0)).questionType_);
        obj1 = obj2;
        if (obj2 != null) goto _L202; else goto _L201
_L201:
        obj1 = QuestionType.UNRECOGNIZED;
_L202:
        if (obj1 == QuestionType.RATING && ((Question)((SurveyPayload) (obj5)).question_.get(0)).isSmiley_)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (hatsshowrequest.showSurveyWithoutPrompt || !((com.google.hats.protos.HatsSurveyData.Survey) (obj4)).showInvitation_ && !flag) goto _L204; else goto _L203
_L203:
        if ((new LayoutDimensions(activity)).context.getResources().getBoolean(0x7f0c000b)) goto _L205; else goto _L204
_L204:
        SurveyPromptActivity.startSurveyActivity(activity, s, ((com.google.hats.protos.HatsSurveyData.Survey) (obj4)), ((SurveyPayload) (obj5)), ((AnswerBeacon) (obj3)), integer, hatsshowrequest.bottomSheet, false, hatsshowrequest.hatsDisplayLogo);
        atomicboolean;
        JVM INSTR monitorexit ;
        return true;
        hatsshowrequest;
        obj1;
        JVM INSTR monitorexit ;
        throw hatsshowrequest;
_L200:
        ((AnswerBeacon) (obj3)).parameterBundle.putString("p", ((String) (obj1)));
          goto _L206
_L205:
        if (!(activity instanceof FragmentActivity)) goto _L208; else goto _L207
_L207:
        obj1 = ((FragmentActivity)activity).mFragments.mHost.mFragmentManager;
        if (((FragmentManager) (obj1)).findFragmentByTag("com.google.android.libraries.hats20.PromptDialogFragment") != null) goto _L210; else goto _L209
_L209:
        boolean flag1 = hatsshowrequest.bottomSheet;
        i = hatsshowrequest.hatsDisplayLogo;
        obj2 = new PromptDialogFragment();
        ((Fragment) (obj2)).setArguments(PromptDialogDelegate.createArgs(s, ((com.google.hats.protos.HatsSurveyData.Survey) (obj4)), ((SurveyPayload) (obj5)), ((AnswerBeacon) (obj3)), integer, flag1, flag, i));
        ((FragmentManager) (obj1)).beginTransaction().add(hatsshowrequest.parentResId, ((Fragment) (obj2)), "com.google.android.libraries.hats20.PromptDialogFragment").commitAllowingStateLoss();
_L211:
        atomicboolean;
        JVM INSTR monitorexit ;
        return true;
_L210:
        Log.w("HatsLibClient", "PromptDialog was already open, bailing out.");
          goto _L211
_L208:
        if (!(activity instanceof Activity)) goto _L211; else goto _L212
_L212:
        obj1 = activity.getFragmentManager();
        if (((android.app.FragmentManager) (obj1)).findFragmentByTag("com.google.android.libraries.hats20.PromptDialogFragment") != null) goto _L214; else goto _L213
_L213:
        boolean flag2 = hatsshowrequest.bottomSheet;
        i = hatsshowrequest.hatsDisplayLogo;
        obj2 = new PlatformPromptDialogFragment();
        ((PlatformPromptDialogFragment) (obj2)).setArguments(PromptDialogDelegate.createArgs(s, ((com.google.hats.protos.HatsSurveyData.Survey) (obj4)), ((SurveyPayload) (obj5)), ((AnswerBeacon) (obj3)), integer, flag2, flag, i));
        ((android.app.FragmentManager) (obj1)).beginTransaction().add(hatsshowrequest.parentResId, ((android.app.Fragment) (obj2)), "com.google.android.libraries.hats20.PromptDialogFragment").commitAllowingStateLoss();
          goto _L211
_L214:
        Log.w("HatsLibClient", "PromptDialog was already open, bailing out.");
          goto _L211
_L72:
        k = 0;
          goto _L215
_L3:
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L216
_L10:
        j++;
          goto _L217
_L11:
        i;
        JVM INSTR tableswitch 0 8: default 5264
    //                   0 677
    //                   1 770
    //                   2 819
    //                   3 868
    //                   4 918
    //                   5 5202
    //                   6 5202
    //                   7 5202
    //                   8 5202;
           goto _L218 _L219 _L220 _L221 _L222 _L223 _L10 _L10 _L10 _L10
_L45:
        i;
        JVM INSTR tableswitch 0 3: default 5300
    //                   0 1686
    //                   1 1716
    //                   2 1723
    //                   3 1730;
           goto _L224 _L225 _L226 _L227 _L228
_L70:
        obj1 = obj2;
          goto _L229
_L179:
        i = 0;
          goto _L198
    }


    private class _cls1
        implements com.google.android.libraries.hats20.network.GcsRequest.ResponseListener
    {

        private final HatsDownloadRequest val$downloadRequest;
        private final HatsDataStore val$hatsDataStore;

        public final void onError(Exception exception)
        {
            Log.w("HatsLibClient", String.format("Site ID %s failed to download with error: %s", new Object[] {
                downloadRequest.siteId, exception.toString()
            }));
            exception = hatsDataStore;
            String s = downloadRequest.siteId;
            long l = (System.currentTimeMillis() + HatsDataStore.MILLIS_TO_CACHE_FAILED_DOWNLOAD) / 1000L;
            ((HatsDataStore) (exception)).sharedPreferences.edit().putInt(HatsDataStore.getKeyForPrefSuffix(s, "RESPONSE_CODE"), 4).putLong(HatsDataStore.getKeyForPrefSuffix(s, "EXPIRATION_DATE"), l).putString(HatsDataStore.getKeyForPrefSuffix(s, "CONTENT"), "").apply();
        }

        public final void onSuccess(GcsResponse gcsresponse)
        {
            String.format("Site ID %s downloaded with response code: %s", new Object[] {
                downloadRequest.siteId, Integer.valueOf(gcsresponse.responseCode)
            });
            HatsDataStore hatsdatastore = hatsDataStore;
            int j = gcsresponse.responseCode;
            long l = gcsresponse.expirationDateUnix;
            String s = gcsresponse.surveyJson;
            String s1 = downloadRequest.siteId;
            int i = j;
            if (j != 0)
            {
                i = j;
                if (j != 1)
                {
                    i = j;
                    if (j != 2)
                    {
                        i = j;
                        if (j != 3)
                        {
                            i = 5;
                        }
                    }
                }
            }
            long l1 = (System.currentTimeMillis() + 0x240c8400L) / 1000L;
            hatsdatastore.sharedPreferences.edit().putInt(HatsDataStore.getKeyForPrefSuffix(s1, "RESPONSE_CODE"), i).putLong(HatsDataStore.getKeyForPrefSuffix(s1, "EXPIRATION_DATE"), Math.min(l1, l)).putString(HatsDataStore.getKeyForPrefSuffix(s1, "CONTENT"), s).apply();
            HatsControllerImpl.sendBroadcast(downloadRequest.context, downloadRequest.siteId, gcsresponse.responseCode);
        }

        _cls1()
        {
            downloadRequest = hatsdownloadrequest;
            hatsDataStore = hatsdatastore;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final GcsRequest val$gcsRequest;

        public final void run()
        {
            GcsRequest gcsrequest = gcsRequest;
            byte abyte0[] = gcsrequest.postData.getBytes(Constants.UTF_8);
            ArrayMap arraymap = new ArrayMap();
            arraymap.put("Content-Type", "application/x-www-form-urlencoded");
            arraymap.put("Content-Length", Integer.toString(abyte0.length));
            arraymap.put("charset", "utf-8");
            arraymap.put("Connection", "close");
            arraymap.put("User-Agent", HatsModule.get().getUserAgent());
            String s = gcsrequest.hatsCookieManager.getCookie(gcsrequest.requestUriWithNoParams);
            if (!TextUtils.isEmpty(s))
            {
                arraymap.put("Cookie", s);
            }
            HatsModule.get().getGcsConnection().send(gcsrequest.requestUriWithNoParams, abyte0, arraymap, new com.google.android.libraries.hats20.network.GcsRequest._cls1(gcsrequest));
        }

        _cls2()
        {
            gcsRequest = gcsrequest;
            super();
        }
    }

}
