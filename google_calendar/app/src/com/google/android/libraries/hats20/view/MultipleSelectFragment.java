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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.google.android.libraries.hats20.inject.HatsModule;
import com.google.android.libraries.hats20.model.QuestionMetrics;
import com.google.devrel.hats.proto.AnswerChoice;
import com.google.devrel.hats.proto.AnswerChoiceType;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.QuestionAnswer;
import com.google.devrel.hats.proto.QuestionResponse;
import com.google.devrel.hats.proto.QuestionResponseStatus;
import com.google.devrel.hats.proto.QuestionType;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Protobuf;
import com.google.protobuf.Schema;
import java.util.List;
import java.util.Random;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            ScrollableAnswerFragment, FragmentViewDelegate, OnQuestionProgressableChangeListener

public final class MultipleSelectFragment extends ScrollableAnswerFragment
{

    public ViewGroup answersContainer;
    private FragmentViewDelegate fragmentViewDelegate;
    public boolean isNoneOfTheAboveChecked;
    private QuestionMetrics questionMetrics;
    public boolean responses[];

    public MultipleSelectFragment()
    {
        fragmentViewDelegate = new FragmentViewDelegate();
    }

    private final void addCheckboxToAnswersContainer(String s, boolean flag, int i, String s1)
    {
        LayoutInflater.from(getContext()).inflate(0x7f050094, answersContainer, true);
        FrameLayout framelayout = (FrameLayout)answersContainer.getChildAt(i);
        final CheckBox newlyAddedCheckbox = (CheckBox)framelayout.findViewById(0x7f100214);
        newlyAddedCheckbox.setText(s);
        newlyAddedCheckbox.setContentDescription(s);
        newlyAddedCheckbox.setChecked(flag);
        newlyAddedCheckbox.setOnCheckedChangeListener(new CheckboxChangeListener(i));
        framelayout.setOnClickListener(new _cls1());
        if (s1 != null)
        {
            newlyAddedCheckbox.setTag(s1);
        }
    }

    public final void animateFadeIn()
    {
        if (!HatsModule.get().isTestMode() && answersContainer != null)
        {
            for (int i = 0; i < answersContainer.getChildCount(); i++)
            {
                View view = answersContainer.getChildAt(i);
                view.setAlpha(0.0F);
                view.animate().alpha(1.0F).setDuration(150L).setStartDelay((i + 1) * 80);
            }

        }
    }

    public final QuestionResponse computeQuestionResponse()
    {
        boolean flag1 = false;
        com.google.devrel.hats.proto.QuestionResponse.Builder builder = (com.google.devrel.hats.proto.QuestionResponse.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)QuestionResponse.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        boolean flag;
        if (questionMetrics.delayStartMs >= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (isNoneOfTheAboveChecked)
            {
                Object obj = (com.google.devrel.hats.proto.QuestionAnswer.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)QuestionAnswer.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                Object obj3 = AnswerChoiceType.NONE_OF_ABOVE;
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                QuestionAnswer questionanswer = (QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj)).instance;
                if (obj3 == null)
                {
                    throw new NullPointerException();
                }
                if (obj3 == AnswerChoiceType.UNRECOGNIZED)
                {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                questionanswer.choiceType_ = ((AnswerChoiceType) (obj3)).value;
                obj = (QuestionAnswer)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
                builder.copyOnWrite();
                obj3 = (QuestionResponse)builder.instance;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (!((QuestionResponse) (obj3)).answer_.isModifiable())
                {
                    obj3.answer_ = GeneratedMessageLite.mutableCopy(((QuestionResponse) (obj3)).answer_);
                }
                ((QuestionResponse) (obj3)).answer_.add(obj);
                questionMetrics.markAsAnswered();
            } else
            {
                obj1 = question.answerChoice_;
                for (int i = 0; i < responses.length; i++)
                {
                    if (!responses[i])
                    {
                        continue;
                    }
                    obj4 = (com.google.devrel.hats.proto.QuestionAnswer.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)QuestionAnswer.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                    ((QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj4)).instance).answerIndex_ = i;
                    Object obj5 = AnswerChoiceType.USER_DEFINED;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                    QuestionAnswer questionanswer1 = (QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj4)).instance;
                    if (obj5 == null)
                    {
                        throw new NullPointerException();
                    }
                    if (obj5 == AnswerChoiceType.UNRECOGNIZED)
                    {
                        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                    }
                    questionanswer1.choiceType_ = ((AnswerChoiceType) (obj5)).value;
                    obj5 = ((AnswerChoice)((List) (obj1)).get(i)).choiceText_;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                    questionanswer1 = (QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj4)).instance;
                    if (obj5 == null)
                    {
                        throw new NullPointerException();
                    }
                    questionanswer1.predefinedAnswerText_ = ((String) (obj5));
                    obj4 = (QuestionAnswer)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).build();
                    builder.copyOnWrite();
                    obj5 = (QuestionResponse)builder.instance;
                    if (obj4 == null)
                    {
                        throw new NullPointerException();
                    }
                    if (!((QuestionResponse) (obj5)).answer_.isModifiable())
                    {
                        obj5.answer_ = GeneratedMessageLite.mutableCopy(((QuestionResponse) (obj5)).answer_);
                    }
                    ((QuestionResponse) (obj5)).answer_.add(obj4);
                    questionMetrics.markAsAnswered();
                }

                if (((QuestionResponse)builder.instance).answer_.size() > 0)
                {
                    int j = HatsModule.get().getHatsLibRandomNumberGenerator().nextInt(((QuestionResponse)builder.instance).answer_.size());
                    obj1 = (QuestionAnswer)((QuestionResponse)builder.instance).answer_.get(j);
                    obj4 = (com.google.protobuf.GeneratedMessageLite.Builder)((GeneratedMessageLite) (obj1)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                    GeneratedMessageLite generatedmessagelite = ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).instance;
                    Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).mergeFrom(generatedmessagelite, obj1);
                    obj1 = (com.google.devrel.hats.proto.QuestionAnswer.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)obj4;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                    ((QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj1)).instance).forPiping_ = true;
                    obj1 = (QuestionAnswer)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
                    builder.copyOnWrite();
                    obj4 = (QuestionResponse)builder.instance;
                    if (!((QuestionResponse) (obj4)).answer_.isModifiable())
                    {
                        obj4.answer_ = GeneratedMessageLite.mutableCopy(((QuestionResponse) (obj4)).answer_);
                    }
                    ((QuestionResponse) (obj4)).answer_.remove(j);
                    builder.copyOnWrite();
                    obj4 = (QuestionResponse)builder.instance;
                    if (obj1 == null)
                    {
                        throw new NullPointerException();
                    }
                    if (!((QuestionResponse) (obj4)).answer_.isModifiable())
                    {
                        obj4.answer_ = GeneratedMessageLite.mutableCopy(((QuestionResponse) (obj4)).answer_);
                    }
                    ((QuestionResponse) (obj4)).answer_.add(j, obj1);
                }
            }
            flag = flag1;
            if (questionMetrics.delayEndMs >= 0L)
            {
                flag = true;
            }
            if (flag)
            {
                Object obj1 = QuestionResponseStatus.ANSWERED;
                builder.copyOnWrite();
                Object obj4 = (QuestionResponse)builder.instance;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                if (obj1 == QuestionResponseStatus.UNRECOGNIZED)
                {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                obj4.responseStatus_ = ((QuestionResponseStatus) (obj1)).value;
            }
            int k = questionIndex;
            builder.copyOnWrite();
            ((QuestionResponse)builder.instance).questionIndex_ = k;
            Object obj2 = QuestionType.MULTIPLE_SELECT;
            builder.copyOnWrite();
            QuestionResponse questionresponse = (QuestionResponse)builder.instance;
            if (obj2 == null)
            {
                throw new NullPointerException();
            }
            if (obj2 == QuestionType.UNRECOGNIZED)
            {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            questionresponse.questionType_ = ((QuestionType) (obj2)).value;
            k = (int)questionMetrics.getDelayMs();
            builder.copyOnWrite();
            ((QuestionResponse)builder.instance).responseDelayMs_ = k;
            obj2 = (GeneratedMessageLite)builder.build();
        }
        return (QuestionResponse)(GeneratedMessageLite)builder.build();
    }

    public final View createScrollViewContents()
    {
        answersContainer = (LinearLayout)LayoutInflater.from(getContext()).inflate(0x7f05009a, null).findViewById(0x7f100220);
        com.google.protobuf.Internal.ProtobufList protobuflist = question.answerChoice_;
        for (int i = 0; i < protobuflist.size(); i++)
        {
            addCheckboxToAnswersContainer(((AnswerChoice)protobuflist.get(i)).choiceText_, responses[i], i, null);
        }

        addCheckboxToAnswersContainer(requireContext().getResources().getString(0x7f1302ec), isNoneOfTheAboveChecked, protobuflist.size(), "NoneOfTheAbove");
        return answersContainer;
    }

    final String getQuestionText()
    {
        return question.questionText_;
    }

    public final boolean isResponseSatisfactory()
    {
        if (!isNoneOfTheAboveChecked) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        boolean aflag[] = responses;
        int j = aflag.length;
        int i = 0;
label0:
        do
        {
label1:
            {
                if (i >= j)
                {
                    break label1;
                }
                if (aflag[i])
                {
                    break label0;
                }
                i++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return false;
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
        ((OnQuestionProgressableChangeListener)bundle).onQuestionProgressableChanged(isResponseSatisfactory(), this);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            isNoneOfTheAboveChecked = bundle.getBoolean("NoneOfTheAboveAsBoolean", false);
            questionMetrics = (QuestionMetrics)bundle.getParcelable("QuestionMetrics");
            responses = bundle.getBooleanArray("ResponsesAsArray");
        }
        if (questionMetrics == null)
        {
            questionMetrics = new QuestionMetrics();
        }
        if (responses == null)
        {
            responses = new boolean[question.answerChoice_.size()];
        } else
        if (responses.length != question.answerChoice_.size())
        {
            int i = responses.length;
            Log.e("HatsLibMultiSelectFrag", (new StringBuilder(64)).append("Saved instance state responses had incorrect length: ").append(i).toString());
            responses = new boolean[question.answerChoice_.size()];
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
        ((OnQuestionProgressableChangeListener)obj).onQuestionProgressableChanged(isResponseSatisfactory(), this);
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("NoneOfTheAboveAsBoolean", isNoneOfTheAboveChecked);
        bundle.putParcelable("QuestionMetrics", questionMetrics);
        bundle.putBooleanArray("ResponsesAsArray", responses);
    }

    private class CheckboxChangeListener
        implements android.widget.CompoundButton.OnCheckedChangeListener
    {

        private final int index;
        private final MultipleSelectFragment this$0;

        public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            if ("NoneOfTheAbove".equals(compoundbutton.getTag()))
            {
                isNoneOfTheAboveChecked = flag;
                if (flag)
                {
                    if (answersContainer.getChildCount() != responses.length + 1)
                    {
                        Log.e("HatsLibMultiSelectFrag", "Number of children (checkboxes) contained in the answers container was not equal to the number of possible responses including \"None of the Above\". Note this is not expected to happen in prod.");
                    }
                    for (int i = 0; i < answersContainer.getChildCount(); i++)
                    {
                        compoundbutton = (CheckBox)answersContainer.getChildAt(i).findViewById(0x7f100214);
                        if (!"NoneOfTheAbove".equals(compoundbutton.getTag()))
                        {
                            compoundbutton.setChecked(false);
                        }
                    }

                }
            } else
            {
                responses[index] = flag;
                if (flag)
                {
                    ((CheckBox)answersContainer.findViewWithTag("NoneOfTheAbove")).setChecked(false);
                }
            }
            compoundbutton = MultipleSelectFragment.this;
            if (((Fragment) (compoundbutton)).mHost == null)
            {
                compoundbutton = null;
            } else
            {
                compoundbutton = (FragmentActivity)((Fragment) (compoundbutton)).mHost.mActivity;
            }
            compoundbutton = (OnQuestionProgressableChangeListener)compoundbutton;
            if (compoundbutton != null)
            {
                compoundbutton.onQuestionProgressableChanged(isResponseSatisfactory(), MultipleSelectFragment.this);
            }
        }

        CheckboxChangeListener(int i)
        {
            this$0 = MultipleSelectFragment.this;
            super();
            index = i;
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final CheckBox val$newlyAddedCheckbox;

        public final void onClick(View view)
        {
            newlyAddedCheckbox.performClick();
        }

        _cls1()
        {
            newlyAddedCheckbox = checkbox;
            super();
        }
    }

}
