// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.os.SystemClock;
import android.util.Log;
import com.google.android.libraries.hats20.answer.AnswerBeacon;
import com.google.android.libraries.hats20.model.QuestionMetrics;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.SurveyPayload;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Protobuf;
import com.google.protobuf.Schema;
import java.util.List;

// Referenced classes of package com.google.android.libraries.hats20:
//            PromptDialogDelegate, SurveyPromptActivity

final class val.hatsDisplayLogo
    implements com.google.android.libraries.hats20.view.kListener
{

    private final PromptDialogDelegate this$0;
    private final int val$hatsDisplayLogo;
    private final int val$requestCode;
    private final String val$siteId;

    public final void onClickRating(int i)
    {
        selectedResponse = Integer.toString(i);
        Object obj = questionMetrics;
        Object obj1;
        if (((QuestionMetrics) (obj)).delayStartMs >= 0L)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            Log.e("HatsLibQuestionMetrics", "Question was marked as answered but was never marked as shown.");
        } else
        {
            if (((QuestionMetrics) (obj)).delayEndMs >= 0L)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                obj.delayEndMs = SystemClock.elapsedRealtime();
            }
        }
        obj = PromptDialogDelegate.this;
        obj1 = (com.google.hats.protos.estionResponse.Builder)(com.google.protobuf.ilder)com.google.hats.protos.estionResponse.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.NEW_BUILDER._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        if (((PromptDialogDelegate) (obj)).questionMetrics.delayStartMs >= 0L)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            Object obj2 = ((PromptDialogDelegate) (obj)).questionMetrics;
            long l;
            boolean flag;
            if (((QuestionMetrics) (obj2)).delayEndMs >= 0L)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                l = ((QuestionMetrics) (obj2)).delayEndMs - ((QuestionMetrics) (obj2)).delayStartMs;
            } else
            {
                l = -1L;
            }
            ((com.google.protobuf.ilder) (obj1)).copyOnWrite();
            obj2 = (com.google.hats.protos.estionResponse)((com.google.hats.protos.estionResponse.Builder) (obj1)).instance;
            obj2.bitField0_ = ((com.google.hats.protos.estionResponse) (obj2)).bitField0_ | 2;
            obj2.delayMs_ = l;
            if (((PromptDialogDelegate) (obj)).questionMetrics.delayEndMs >= 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            ((com.google.protobuf.ilder) (obj1)).copyOnWrite();
            obj2 = (com.google.hats.protos.estionResponse)((com.google.hats.protos.estionResponse.Builder) (obj1)).instance;
            obj2.bitField0_ = ((com.google.hats.protos.estionResponse) (obj2)).bitField0_ | 1;
            obj2.isAnswered_ = flag;
            if (((PromptDialogDelegate) (obj)).selectedResponse != null)
            {
                Object obj3 = ((PromptDialogDelegate) (obj)).selectedResponse;
                ((com.google.protobuf.ilder) (obj1)).copyOnWrite();
                Object obj4 = (com.google.hats.protos.estionResponse)((com.google.hats.protos.estionResponse.Builder) (obj1)).instance;
                if (obj3 == null)
                {
                    throw new NullPointerException();
                }
                if (!((com.google.hats.protos.estionResponse) (obj4)).responses_.sModifiable())
                {
                    obj4.responses_ = GeneratedMessageLite.mutableCopy(((com.google.hats.protos.estionResponse) (obj4)).responses_);
                }
                ((com.google.hats.protos.estionResponse) (obj4)).responses_.dd(obj3);
                obj3 = String.valueOf(((PromptDialogDelegate) (obj)).selectedResponse);
                if (((String) (obj3)).length() != 0)
                {
                    "Selected response: ".concat(((String) (obj3)));
                } else
                {
                    new String("Selected response: ");
                }
            }
        }
        obj1 = (com.google.hats.protos.estionResponse)(GeneratedMessageLite)((com.google.protobuf.ilder) (obj1)).build();
        if (obj1 != null)
        {
            obj3 = (Question)((PromptDialogDelegate) (obj)).surveyPayload.question_.et(0);
            ((PromptDialogDelegate) (obj)).answerBeacon.setQuestionResponse(0, ((com.google.hats.protos.estionResponse) (obj1)), ((Question) (obj3)));
            obj3 = ((PromptDialogDelegate) (obj)).answerBeacon.responses;
            obj = obj1;
            if (AnswerBeacon.isSpammy(0, ((com.google.hats.protos.estionResponse) (obj1)).delayMs_))
            {
                obj = (com.google.protobuf.ilder)((GeneratedMessageLite) (obj1)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.NEW_BUILDER._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                ((com.google.protobuf.ilder) (obj)).copyOnWrite();
                obj4 = ((com.google.protobuf.ilder) (obj)).instance;
                Protobuf.INSTANCE.schemaFor(obj4.getClass()).mergeFrom(obj4, obj1);
                obj = (com.google.hats.protos.estionResponse.Builder)(com.google.protobuf.ilder)obj;
                ((com.google.protobuf.ilder) (obj)).copyOnWrite();
                obj1 = (com.google.hats.protos.estionResponse)((com.google.hats.protos.estionResponse.Builder) (obj)).instance;
                obj1.bitField0_ = ((com.google.hats.protos.estionResponse) (obj1)).bitField0_ | 8;
                obj1.isSpammy_ = true;
                obj = (com.google.hats.protos.estionResponse)(GeneratedMessageLite)((com.google.protobuf.ilder) (obj)).build();
            }
            ((List) (obj3)).add(obj);
        }
        SurveyPromptActivity.startSurveyActivity(dialogFragment.getActivity(), val$siteId, survey, surveyPayload, answerBeacon, Integer.valueOf(val$requestCode), isBottomSheet, isRatingBanner, val$hatsDisplayLogo);
        isStartingSurvey = true;
        dialogFragment.dismissAllowingStateLoss();
    }

    alogFragmentInterface()
    {
        this$0 = final_promptdialogdelegate;
        val$siteId = s;
        val$requestCode = i;
        val$hatsDisplayLogo = I.this;
        super();
    }
}
