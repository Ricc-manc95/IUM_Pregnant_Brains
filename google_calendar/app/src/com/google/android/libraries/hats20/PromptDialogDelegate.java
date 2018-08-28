// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.libraries.hats20.answer.AnswerBeacon;
import com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter;
import com.google.android.libraries.hats20.cookie.HatsCookieManager;
import com.google.android.libraries.hats20.inject.HatsModule;
import com.google.android.libraries.hats20.model.ProtoParcels;
import com.google.android.libraries.hats20.model.QuestionMetrics;
import com.google.android.libraries.hats20.util.LayoutDimensions;
import com.google.android.libraries.hats20.view.RatingView;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.QuestionRating;
import com.google.devrel.hats.proto.SurveyPayload;
import com.google.protobuf.AbstractMessageLite;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.libraries.hats20:
//            HatsController, DimensionConfigurationHelper

final class PromptDialogDelegate
{

    public AnswerBeacon answerBeacon;
    public boolean areDimensionsValid;
    public DimensionConfigurationHelper configurationHelper;
    private View contentView;
    public Context context;
    public final DialogFragmentInterface dialogFragment;
    public boolean isBottomSheet;
    public boolean isRatingBanner;
    public boolean isStartingSurvey;
    private LayoutDimensions layoutDimensions;
    public QuestionMetrics questionMetrics;
    public String selectedResponse;
    public com.google.hats.protos.HatsSurveyData.Survey survey;
    public SurveyPayload surveyPayload;

    public PromptDialogDelegate(DialogFragmentInterface dialogfragmentinterface)
    {
        isBottomSheet = false;
        isStartingSurvey = false;
        areDimensionsValid = false;
        dialogFragment = dialogfragmentinterface;
    }

    public static Bundle createArgs(String s, com.google.hats.protos.HatsSurveyData.Survey survey1, SurveyPayload surveypayload, AnswerBeacon answerbeacon, Integer integer, boolean flag, boolean flag1, int i)
    {
        Bundle bundle = new Bundle();
        bundle.putString("SiteId", s);
        bundle.putByteArray("Survey", survey1.toByteArray());
        bundle.putByteArray("SurveyPayload", surveypayload.toByteArray());
        bundle.putParcelable("AnswerBeacon", answerbeacon);
        if (integer != null)
        {
            bundle.putInt("RequestCode", integer.intValue());
        }
        bundle.putBoolean("IsRatingBanner", flag1);
        bundle.putBoolean("BottomSheet", flag);
        bundle.putInt("hatsDisplayLogo", i);
        return bundle;
    }

    public final View onCreateView$51662RJ4E9NMIP1FEPKMATPF9HGNIRRLEH4MSPJCC5Q6ASHR9HGMSP3IDTKM8BRMD5INEBQMD5INEHRIDTQN0EQCC5N68SJFD5I2URRJ5T17ARJ4DHIJMAACC5N68SJFD5I2UTJ9CLRIULJ9CLRJM___0(LayoutInflater layoutinflater, final ViewGroup noThanksButton)
    {
        context = dialogFragment.getActivity();
        layoutDimensions = new LayoutDimensions(context);
        Object obj = dialogFragment.getArguments();
        final String siteId = ((Bundle) (obj)).getString("SiteId");
        final int requestCode = ((Bundle) (obj)).getInt("RequestCode", -1);
        survey = (com.google.hats.protos.HatsSurveyData.Survey)ProtoParcels.getMessage(com.google.hats.protos.HatsSurveyData.Survey.DEFAULT_INSTANCE, ((Bundle) (obj)).getByteArray("Survey"));
        surveyPayload = (SurveyPayload)ProtoParcels.getMessage(SurveyPayload.DEFAULT_INSTANCE, ((Bundle) (obj)).getByteArray("SurveyPayload"));
        answerBeacon = (AnswerBeacon)((Bundle) (obj)).getParcelable("AnswerBeacon");
        isBottomSheet = ((Bundle) (obj)).getBoolean("BottomSheet");
        isRatingBanner = ((Bundle) (obj)).getBoolean("IsRatingBanner");
        final int hatsDisplayLogo = ((Bundle) (obj)).getInt("hatsDisplayLogo", 0);
        if (dialogFragment.getShowsDialog())
        {
            dialogFragment.getDialog().requestWindowFeature(1);
        }
        obj = answerBeacon;
        if ("sv" == null)
        {
            throw new NullPointerException("Beacon type cannot be null.");
        }
        AnswerBeacon answerbeacon;
        if ("sv" == null)
        {
            ((AnswerBeacon) (obj)).parameterBundle.remove("t");
        } else
        {
            ((AnswerBeacon) (obj)).parameterBundle.putString("t", "sv");
        }
        obj = new AnswerBeaconTransmitter(survey.answerUrl_, HatsCookieManager.getInstance(context));
        answerbeacon = answerBeacon;
        ((AnswerBeaconTransmitter) (obj)).executor.execute(new com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter.TransmitTask(((AnswerBeaconTransmitter) (obj)), answerbeacon.exportAllParametersInQueryString(true)));
        HatsModule.get().getHatsController().markSurveyRunning();
        contentView = layoutinflater.inflate(0x7f05008e, noThanksButton, false);
        layoutinflater = (ImageView)contentView.findViewById(0x7f100202);
        if (hatsDisplayLogo > 0)
        {
            layoutinflater.setImageResource(hatsDisplayLogo);
            layoutinflater.setVisibility(0);
        } else
        {
            layoutinflater.setVisibility(8);
        }
        configurationHelper = new DimensionConfigurationHelper((CardView)contentView, dialogFragment.getDialog(), layoutDimensions, isBottomSheet);
        if (isRatingBanner)
        {
            layoutinflater = contentView;
            noThanksButton = ((Question)surveyPayload.question_.get(0)).questionText_;
            ((TextView)layoutinflater.findViewById(0x7f100206)).setText(noThanksButton);
            layoutinflater = contentView;
            noThanksButton = layoutinflater.findViewById(0x7f100205);
            obj = context.getResources();
            noThanksButton.setPadding(0, 0, ((Resources) (obj)).getDimensionPixelSize(0x7f0e020e) - ((Resources) (obj)).getDimensionPixelSize(0x7f0e021e), 0);
            ((ImageButton)layoutinflater.findViewById(0x7f1001fe)).setOnClickListener(new _cls5());
            noThanksButton = layoutinflater.findViewById(0x7f1001fd);
            noThanksButton.post(new com.google.android.libraries.hats20.util.LayoutUtils._cls1(layoutinflater.findViewById(0x7f1001fe), 0x7f0e020f, 0, 0x7f0e020f, 0, noThanksButton));
            questionMetrics = new QuestionMetrics();
            noThanksButton = questionMetrics;
            boolean flag;
            if (((QuestionMetrics) (noThanksButton)).delayStartMs >= 0L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                noThanksButton.delayStartMs = SystemClock.elapsedRealtime();
            }
            noThanksButton = answerBeacon;
            obj = (new StringBuilder(15)).append("r.s-").append(0).toString();
            ((AnswerBeacon) (noThanksButton)).parameterBundle.putString(((String) (obj)), "1");
            noThanksButton = (RatingView)layoutinflater.findViewById(0x7f10020c);
            noThanksButton.setVisibility(0);
            obj = (Question)surveyPayload.question_.get(0);
            if (((Question) (obj)).questionRating_ == null)
            {
                layoutinflater = QuestionRating.DEFAULT_INSTANCE;
            } else
            {
                layoutinflater = ((Question) (obj)).questionRating_;
            }
            noThanksButton.setupRatingView(layoutinflater, ((Question) (obj)).isSmiley_);
            noThanksButton.onRatingClickListener = new _cls6();
        } else
        {
            layoutinflater = contentView;
            noThanksButton = survey.promptMessage_;
            ((TextView)layoutinflater.findViewById(0x7f100206)).setText(noThanksButton);
            layoutinflater = contentView;
            layoutinflater.findViewById(0x7f100207).setVisibility(0);
            layoutinflater.findViewById(0x7f1001fd).setVisibility(8);
            noThanksButton = (Button)layoutinflater.findViewById(0x7f100209);
            final Button takeSurveyButton = (Button)layoutinflater.findViewById(0x7f10020b);
            View view = contentView.findViewById(0x7f100207);
            view.post(new com.google.android.libraries.hats20.util.LayoutUtils._cls1(noThanksButton, 0x7f0e020c, 0, 0x7f0e020c, 0, view));
            view = contentView.findViewById(0x7f100207);
            view.post(new com.google.android.libraries.hats20.util.LayoutUtils._cls1(takeSurveyButton, 0x7f0e020c, 0, 0x7f0e020c, 0, view));
            layoutinflater.findViewById(0x7f100208).setOnTouchListener(new _cls1());
            layoutinflater.findViewById(0x7f10020a).setOnTouchListener(new _cls2());
            takeSurveyButton.setOnClickListener(new _cls3());
            noThanksButton.setOnClickListener(new _cls4());
        }
        return contentView;
    }

    private class DialogFragmentInterface
    {

        public abstract void dismissAllowingStateLoss();

        public abstract Activity getActivity();

        public abstract Bundle getArguments();

        public abstract Dialog getDialog();

        public abstract boolean getShowsDialog();
    }


    private class _cls5
        implements android.view.View.OnClickListener
    {

        private final PromptDialogDelegate this$0;

        public final void onClick(View view)
        {
            view = PromptDialogDelegate.this;
            Object obj = ((PromptDialogDelegate) (view)).answerBeacon;
            if ("o" == null)
            {
                throw new NullPointerException("Beacon type cannot be null.");
            }
            if ("o" == null)
            {
                ((AnswerBeacon) (obj)).parameterBundle.remove("t");
            } else
            {
                ((AnswerBeacon) (obj)).parameterBundle.putString("t", "o");
            }
            obj = new AnswerBeaconTransmitter(((PromptDialogDelegate) (view)).survey.answerUrl_, HatsCookieManager.getInstance(((PromptDialogDelegate) (view)).context));
            view = ((PromptDialogDelegate) (view)).answerBeacon;
            ((AnswerBeaconTransmitter) (obj)).executor.execute(new com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter.TransmitTask(((AnswerBeaconTransmitter) (obj)), view.exportAllParametersInQueryString(true)));
            dialogFragment.dismissAllowingStateLoss();
        }

        _cls5()
        {
            this$0 = PromptDialogDelegate.this;
            super();
        }
    }


    private class _cls6
        implements com.google.android.libraries.hats20.view.RatingView.OnRatingClickListener
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
            obj1 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
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
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                obj2 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj1)).instance;
                obj2.bitField0_ = ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj2)).bitField0_ | 2;
                obj2.delayMs_ = l;
                if (((PromptDialogDelegate) (obj)).questionMetrics.delayEndMs >= 0L)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                obj2 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj1)).instance;
                obj2.bitField0_ = ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj2)).bitField0_ | 1;
                obj2.isAnswered_ = flag;
                if (((PromptDialogDelegate) (obj)).selectedResponse != null)
                {
                    Object obj3 = ((PromptDialogDelegate) (obj)).selectedResponse;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                    Object obj4 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj1)).instance;
                    if (obj3 == null)
                    {
                        throw new NullPointerException();
                    }
                    if (!((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj4)).responses_.isModifiable())
                    {
                        obj4.responses_ = GeneratedMessageLite.mutableCopy(((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj4)).responses_);
                    }
                    ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj4)).responses_.add(obj3);
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
            obj1 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
            if (obj1 != null)
            {
                obj3 = (Question)((PromptDialogDelegate) (obj)).surveyPayload.question_.get(0);
                ((PromptDialogDelegate) (obj)).answerBeacon.setQuestionResponse(0, ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)), ((Question) (obj3)));
                obj3 = ((PromptDialogDelegate) (obj)).answerBeacon.responses;
                obj = obj1;
                if (AnswerBeacon.isSpammy(0, ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)).delayMs_))
                {
                    obj = (com.google.protobuf.GeneratedMessageLite.Builder)((GeneratedMessageLite) (obj1)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                    obj4 = ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).instance;
                    Protobuf.INSTANCE.schemaFor(obj4.getClass()).mergeFrom(obj4, obj1);
                    obj = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)obj;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                    obj1 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj)).instance;
                    obj1.bitField0_ = ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)).bitField0_ | 8;
                    obj1.isSpammy_ = true;
                    obj = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
                }
                ((List) (obj3)).add(obj);
            }
            SurveyPromptActivity.startSurveyActivity(dialogFragment.getActivity(), siteId, survey, surveyPayload, answerBeacon, Integer.valueOf(requestCode), isBottomSheet, isRatingBanner, hatsDisplayLogo);
            isStartingSurvey = true;
            dialogFragment.dismissAllowingStateLoss();
        }

        _cls6()
        {
            this$0 = PromptDialogDelegate.this;
            siteId = s;
            requestCode = i;
            hatsDisplayLogo = j;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnTouchListener
    {

        private final Button val$noThanksButton;

        public final boolean onTouch(View view, MotionEvent motionevent)
        {
            noThanksButton.onTouchEvent(motionevent);
            return true;
        }

        _cls1()
        {
            noThanksButton = button;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnTouchListener
    {

        private final Button val$takeSurveyButton;

        public final boolean onTouch(View view, MotionEvent motionevent)
        {
            takeSurveyButton.onTouchEvent(motionevent);
            return true;
        }

        _cls2()
        {
            takeSurveyButton = button;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        private final PromptDialogDelegate this$0;
        private final int val$hatsDisplayLogo;
        private final int val$requestCode;
        private final String val$siteId;

        public final void onClick(View view)
        {
            SurveyPromptActivity.startSurveyActivity(dialogFragment.getActivity(), siteId, survey, surveyPayload, answerBeacon, Integer.valueOf(requestCode), isBottomSheet, isRatingBanner, hatsDisplayLogo);
            isStartingSurvey = true;
            dialogFragment.dismissAllowingStateLoss();
        }

        _cls3()
        {
            this$0 = PromptDialogDelegate.this;
            siteId = s;
            requestCode = i;
            hatsDisplayLogo = j;
            super();
        }
    }


    private class _cls4
        implements android.view.View.OnClickListener
    {

        private final PromptDialogDelegate this$0;

        public final void onClick(View view)
        {
            view = PromptDialogDelegate.this;
            Object obj = ((PromptDialogDelegate) (view)).answerBeacon;
            if ("o" == null)
            {
                throw new NullPointerException("Beacon type cannot be null.");
            }
            if ("o" == null)
            {
                ((AnswerBeacon) (obj)).parameterBundle.remove("t");
            } else
            {
                ((AnswerBeacon) (obj)).parameterBundle.putString("t", "o");
            }
            obj = new AnswerBeaconTransmitter(((PromptDialogDelegate) (view)).survey.answerUrl_, HatsCookieManager.getInstance(((PromptDialogDelegate) (view)).context));
            view = ((PromptDialogDelegate) (view)).answerBeacon;
            ((AnswerBeaconTransmitter) (obj)).executor.execute(new com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter.TransmitTask(((AnswerBeaconTransmitter) (obj)), view.exportAllParametersInQueryString(true)));
            dialogFragment.dismissAllowingStateLoss();
        }

        _cls4()
        {
            this$0 = PromptDialogDelegate.this;
            super();
        }
    }

}
