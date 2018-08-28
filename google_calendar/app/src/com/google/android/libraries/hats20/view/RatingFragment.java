// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.libraries.hats20.model.QuestionMetrics;
import com.google.android.libraries.hats20.util.TextFormatUtil;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.QuestionAnswer;
import com.google.devrel.hats.proto.QuestionRating;
import com.google.devrel.hats.proto.QuestionResponse;
import com.google.devrel.hats.proto.QuestionResponseStatus;
import com.google.protobuf.GeneratedMessageLite;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            BaseFragment, FragmentViewDelegate, RatingView, OnQuestionProgressableChangeListener

public final class RatingFragment extends BaseFragment
{

    private FragmentViewDelegate fragmentViewDelegate;
    public QuestionMetrics questionMetrics;
    private TextView questionTextView;
    public int selectedIndex;
    public String selectedResponse;

    public RatingFragment()
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
            i = (int)questionMetrics.getDelayMs();
            builder.copyOnWrite();
            ((QuestionResponse)builder.instance).responseDelayMs_ = i;
            if (selectedResponse != null)
            {
                Object obj = QuestionResponseStatus.ANSWERED;
                builder.copyOnWrite();
                Object obj1 = (QuestionResponse)builder.instance;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (obj == QuestionResponseStatus.UNRECOGNIZED)
                {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                obj1.responseStatus_ = ((QuestionResponseStatus) (obj)).value;
                obj = (com.google.devrel.hats.proto.QuestionAnswer.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)QuestionAnswer.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                i = selectedIndex;
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                ((QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj)).instance).answerIndex_ = i;
                float f = selectedIndex;
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                ((QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj)).instance).rating_ = f;
                obj1 = selectedResponse;
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                QuestionAnswer questionanswer = (QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj)).instance;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                questionanswer.predefinedAnswerText_ = ((String) (obj1));
                obj = (QuestionAnswer)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
                builder.copyOnWrite();
                obj1 = (QuestionResponse)builder.instance;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (!((QuestionResponse) (obj1)).answer_.isModifiable())
                {
                    obj1.answer_ = GeneratedMessageLite.mutableCopy(((QuestionResponse) (obj1)).answer_);
                }
                ((QuestionResponse) (obj1)).answer_.add(obj);
                obj = (GeneratedMessageLite)builder.build();
                obj = String.valueOf(selectedResponse);
                if (((String) (obj)).length() != 0)
                {
                    "Selected response: ".concat(((String) (obj)));
                } else
                {
                    new String("Selected response: ");
                }
            }
        }
        return (QuestionResponse)(GeneratedMessageLite)builder.build();
    }

    public final String getCurrentQuestionText()
    {
        return questionTextView.getText().toString();
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle != null)
        {
            selectedResponse = bundle.getString("SelectedResponse", null);
            questionMetrics = (QuestionMetrics)bundle.getParcelable("QuestionMetrics");
        }
        if (questionMetrics == null)
        {
            questionMetrics = new QuestionMetrics();
        }
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        viewgroup = layoutinflater.inflate(0x7f050096, viewgroup, false);
        viewgroup.setContentDescription(question.questionText_);
        int i = getArguments().getInt("DispalyLogoResId", 0);
        layoutinflater = (ImageView)viewgroup.findViewById(0x7f100218);
        if (i > 0)
        {
            layoutinflater.setImageResource(i);
            layoutinflater.setVisibility(0);
        } else
        {
            layoutinflater.setVisibility(8);
        }
        questionTextView = (TextView)viewgroup.findViewById(0x7f100210);
        questionTextView.setText(TextFormatUtil.format(question.questionText_));
        questionTextView.setContentDescription(question.questionText_);
        bundle = (RatingView)viewgroup.findViewById(0x7f100219);
        layoutinflater = question;
        if (((Question) (layoutinflater)).questionRating_ == null)
        {
            layoutinflater = QuestionRating.DEFAULT_INSTANCE;
        } else
        {
            layoutinflater = ((Question) (layoutinflater)).questionRating_;
        }
        bundle.setupRatingView(layoutinflater, question.isSmiley_);
        bundle.onRatingClickListener = new _cls1();
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
        boolean flag1 = true;
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
        obj = (OnQuestionProgressableChangeListener)obj;
        if (selectedResponse == null)
        {
            flag1 = false;
        }
        ((OnQuestionProgressableChangeListener) (obj)).onQuestionProgressableChanged(flag1, this);
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putString("SelectedResponse", selectedResponse);
        bundle.putParcelable("QuestionMetrics", questionMetrics);
    }

    public final void updateQuestionText(String s)
    {
        questionTextView.setText(TextFormatUtil.format(s));
        questionTextView.setContentDescription(s);
    }

    private class _cls1
        implements RatingView.OnRatingClickListener
    {

        private final RatingFragment this$0;

        public final void onClickRating(int i)
        {
            Object obj1 = null;
            Object obj = null;
            selectedResponse = Integer.toString(i);
            selectedIndex = i;
            questionMetrics.markAsAnswered();
            RatingFragment ratingfragment = RatingFragment.this;
            if (question.isSmiley_)
            {
                if (((Fragment) (ratingfragment)).mHost != null)
                {
                    obj = (FragmentActivity)((Fragment) (ratingfragment)).mHost.mActivity;
                }
                ((NextPageOrSubmitActionable)obj).nextPageOrSubmit();
                return;
            }
            boolean flag;
            if (((Fragment) (ratingfragment)).mHost == null)
            {
                obj = obj1;
            } else
            {
                obj = (FragmentActivity)((Fragment) (ratingfragment)).mHost.mActivity;
            }
            obj = (OnQuestionProgressableChangeListener)obj;
            if (ratingfragment.selectedResponse != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            ((OnQuestionProgressableChangeListener) (obj)).onQuestionProgressableChanged(flag, ratingfragment);
        }

        _cls1()
        {
            this$0 = RatingFragment.this;
            super();
        }
    }

}
