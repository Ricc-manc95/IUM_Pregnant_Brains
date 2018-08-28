// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.android.libraries.hats20.model.QuestionMetrics;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.QuestionAnswer;
import com.google.devrel.hats.proto.QuestionResponse;
import com.google.devrel.hats.proto.QuestionResponseStatus;
import com.google.devrel.hats.proto.QuestionType;
import com.google.protobuf.GeneratedMessageLite;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            ScrollableAnswerFragment, FragmentViewDelegate, OnQuestionProgressableChangeListener

public final class OpenTextFragment extends ScrollableAnswerFragment
{

    public EditText editTextField;
    private FragmentViewDelegate fragmentViewDelegate;
    private QuestionMetrics questionMetrics;

    public OpenTextFragment()
    {
        fragmentViewDelegate = new FragmentViewDelegate();
    }

    public final QuestionResponse computeQuestionResponse()
    {
        com.google.devrel.hats.proto.QuestionResponse.Builder builder = (com.google.devrel.hats.proto.QuestionResponse.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)QuestionResponse.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        int i;
        if (questionMetrics.delayStartMs >= 0L)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            questionMetrics.markAsAnswered();
            i = (int)questionMetrics.getDelayMs();
            builder.copyOnWrite();
            ((QuestionResponse)builder.instance).responseDelayMs_ = i;
            QuestionType questiontype = QuestionType.OPEN_TEXT;
            builder.copyOnWrite();
            Object obj2 = (QuestionResponse)builder.instance;
            if (questiontype == null)
            {
                throw new NullPointerException();
            }
            if (questiontype == QuestionType.UNRECOGNIZED)
            {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            obj2.questionType_ = questiontype.value;
            i = questionIndex;
            builder.copyOnWrite();
            ((QuestionResponse)builder.instance).questionIndex_ = i;
            obj2 = editTextField.getText().toString();
            if (((String) (obj2)).trim().isEmpty())
            {
                Object obj = (com.google.devrel.hats.proto.QuestionAnswer.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)QuestionAnswer.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                obj2 = (QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj)).instance;
                if ("skipped" == null)
                {
                    throw new NullPointerException();
                }
                obj2.userAnswerText_ = "skipped";
                obj = (QuestionAnswer)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
                builder.copyOnWrite();
                obj2 = (QuestionResponse)builder.instance;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (!((QuestionResponse) (obj2)).answer_.isModifiable())
                {
                    obj2.answer_ = GeneratedMessageLite.mutableCopy(((QuestionResponse) (obj2)).answer_);
                }
                ((QuestionResponse) (obj2)).answer_.add(obj);
                obj = QuestionResponseStatus.NOT_ANSWERED;
                builder.copyOnWrite();
                obj2 = (QuestionResponse)builder.instance;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (obj == QuestionResponseStatus.UNRECOGNIZED)
                {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                obj2.responseStatus_ = ((QuestionResponseStatus) (obj)).value;
            } else
            {
                Object obj1 = (com.google.devrel.hats.proto.QuestionAnswer.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)QuestionAnswer.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                obj2 = ((String) (obj2)).trim();
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                QuestionAnswer questionanswer = (QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj1)).instance;
                if (obj2 == null)
                {
                    throw new NullPointerException();
                }
                questionanswer.userAnswerText_ = ((String) (obj2));
                obj1 = (QuestionAnswer)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
                builder.copyOnWrite();
                obj2 = (QuestionResponse)builder.instance;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                if (!((QuestionResponse) (obj2)).answer_.isModifiable())
                {
                    obj2.answer_ = GeneratedMessageLite.mutableCopy(((QuestionResponse) (obj2)).answer_);
                }
                ((QuestionResponse) (obj2)).answer_.add(obj1);
                obj1 = QuestionResponseStatus.ANSWERED;
                builder.copyOnWrite();
                obj2 = (QuestionResponse)builder.instance;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                if (obj1 == QuestionResponseStatus.UNRECOGNIZED)
                {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                obj2.responseStatus_ = ((QuestionResponseStatus) (obj1)).value;
            }
        }
        return (QuestionResponse)(GeneratedMessageLite)builder.build();
    }

    final View createScrollViewContents()
    {
        LayoutInflater layoutinflater = LayoutInflater.from(getContext());
        Object obj = layoutinflater.inflate(0x7f05009a, null);
        ((View) (obj)).setMinimumHeight(requireContext().getResources().getDimensionPixelSize(0x7f0e0218));
        obj = (LinearLayout)((View) (obj)).findViewById(0x7f100220);
        layoutinflater.inflate(0x7f050095, ((ViewGroup) (obj)), true);
        editTextField = (EditText)((LinearLayout) (obj)).findViewById(0x7f100215);
        editTextField.setSingleLine(false);
        editTextField.setHint(requireContext().getResources().getString(0x7f1302ed));
        return ((View) (obj));
    }

    final String getQuestionText()
    {
        return question.questionText_;
    }

    public final void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        ((OnQuestionProgressableChangeListener)bundle).onQuestionProgressableChanged(true, this);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle == null)
        {
            questionMetrics = new QuestionMetrics();
            return;
        } else
        {
            questionMetrics = (QuestionMetrics)bundle.getParcelable("QuestionMetrics");
            return;
        }
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        viewgroup = super.onCreateView(layoutinflater, viewgroup, bundle);
        viewgroup.setContentDescription(question.questionText_);
        if (!super.mDetached)
        {
            bundle = fragmentViewDelegate;
            if (super.mHost == null)
            {
                layoutinflater = null;
            } else
            {
                layoutinflater = (FragmentActivity)super.mHost.mActivity;
            }
            bundle.measurementSurrogate = (FragmentViewDelegate.MeasurementSurrogate)layoutinflater;
            bundle.fragmentView = viewgroup;
            viewgroup.getViewTreeObserver().addOnGlobalLayoutListener(bundle);
        }
        return viewgroup;
    }

    public final void onDetach()
    {
        FragmentViewDelegate fragmentviewdelegate = fragmentViewDelegate;
        if (fragmentviewdelegate.fragmentView != null)
        {
            fragmentviewdelegate.fragmentView.getViewTreeObserver().removeOnGlobalLayoutListener(fragmentviewdelegate);
        }
        fragmentviewdelegate.fragmentView = null;
        fragmentviewdelegate.measurementSurrogate = null;
        super.onDetach();
    }

    public final void onPageScrolledIntoView()
    {
        Object obj = questionMetrics;
        boolean flag;
        if (((QuestionMetrics) (obj)).delayStartMs >= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            obj.delayStartMs = SystemClock.elapsedRealtime();
        }
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        ((OnQuestionProgressableChangeListener)obj).onQuestionProgressableChanged(true, this);
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("QuestionMetrics", questionMetrics);
    }
}
