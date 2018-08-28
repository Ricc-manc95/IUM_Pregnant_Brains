// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.libraries.hats20.inject.HatsModule;
import com.google.android.libraries.hats20.model.QuestionMetrics;
import com.google.devrel.hats.proto.AnswerChoice;
import com.google.devrel.hats.proto.AnswerChoiceType;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.QuestionAnswer;
import com.google.devrel.hats.proto.QuestionResponse;
import com.google.devrel.hats.proto.QuestionResponseStatus;
import com.google.devrel.hats.proto.QuestionType;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.hats20.view:
//            ScrollableAnswerFragment, FragmentViewDelegate, OnQuestionProgressableChangeListener

public final class MultipleChoiceFragment extends ScrollableAnswerFragment
{

    private static final Map MULTI_CHOICE_SMILEY_ICON_RESOURCE_MAP;
    private LinearLayout answersContainer;
    private FragmentViewDelegate fragmentViewDelegate;
    public QuestionMetrics questionMetrics;
    public int selectedIndex;
    public String selectedResponse;

    public MultipleChoiceFragment()
    {
        fragmentViewDelegate = new FragmentViewDelegate();
        selectedIndex = -1;
    }

    static void removeOnClickListenersAndDisableClickEvents(View aview[])
    {
        int j = aview.length;
        for (int i = 0; i < j; i++)
        {
            View view = aview[i];
            view.setOnClickListener(null);
            view.setClickable(false);
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
        Object obj = (com.google.devrel.hats.proto.QuestionResponse.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)QuestionResponse.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        boolean flag;
        if (questionMetrics.delayEndMs >= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            QuestionResponseStatus questionresponsestatus = QuestionResponseStatus.ANSWERED;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            QuestionResponse questionresponse = (QuestionResponse)((com.google.devrel.hats.proto.QuestionResponse.Builder) (obj)).instance;
            if (questionresponsestatus == null)
            {
                throw new NullPointerException();
            }
            if (questionresponsestatus == QuestionResponseStatus.UNRECOGNIZED)
            {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            questionresponse.responseStatus_ = questionresponsestatus.value;
        }
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
            if (selectedResponse != null)
            {
                Object obj1 = (com.google.devrel.hats.proto.QuestionAnswer.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)QuestionAnswer.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                i = selectedIndex;
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                ((QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj1)).instance).answerIndex_ = i;
                Object obj3 = AnswerChoiceType.USER_DEFINED;
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                QuestionAnswer questionanswer = (QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj1)).instance;
                if (obj3 == null)
                {
                    throw new NullPointerException();
                }
                if (obj3 == AnswerChoiceType.UNRECOGNIZED)
                {
                    throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
                }
                questionanswer.choiceType_ = ((AnswerChoiceType) (obj3)).value;
                obj3 = selectedResponse;
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                questionanswer = (QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj1)).instance;
                if (obj3 == null)
                {
                    throw new NullPointerException();
                }
                questionanswer.predefinedAnswerText_ = ((String) (obj3));
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                ((QuestionAnswer)((com.google.devrel.hats.proto.QuestionAnswer.Builder) (obj1)).instance).forPiping_ = true;
                obj1 = (QuestionAnswer)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                obj3 = (QuestionResponse)((com.google.devrel.hats.proto.QuestionResponse.Builder) (obj)).instance;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                if (!((QuestionResponse) (obj3)).answer_.isModifiable())
                {
                    obj3.answer_ = GeneratedMessageLite.mutableCopy(((QuestionResponse) (obj3)).answer_);
                }
                ((QuestionResponse) (obj3)).answer_.add(obj1);
            }
            i = questionIndex;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            ((QuestionResponse)((com.google.devrel.hats.proto.QuestionResponse.Builder) (obj)).instance).questionIndex_ = i;
            Object obj2 = QuestionType.MULTIPLE_CHOICE;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            Object obj4 = (QuestionResponse)((com.google.devrel.hats.proto.QuestionResponse.Builder) (obj)).instance;
            if (obj2 == null)
            {
                throw new NullPointerException();
            }
            if (obj2 == QuestionType.UNRECOGNIZED)
            {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            obj4.questionType_ = ((QuestionType) (obj2)).value;
            i = (int)questionMetrics.getDelayMs();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            ((QuestionResponse)((com.google.devrel.hats.proto.QuestionResponse.Builder) (obj)).instance).responseDelayMs_ = i;
            obj4 = question.ordering_;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            obj2 = (QuestionResponse)((com.google.devrel.hats.proto.QuestionResponse.Builder) (obj)).instance;
            if (!((QuestionResponse) (obj2)).displayOrder_.isModifiable())
            {
                obj2.displayOrder_ = GeneratedMessageLite.mutableCopy(((QuestionResponse) (obj2)).displayOrder_);
            }
            obj2 = ((QuestionResponse) (obj2)).displayOrder_;
            Internal.checkNotNull(obj4);
            if (obj4 instanceof LazyStringList)
            {
                List list = ((LazyStringList)obj4).getUnderlyingElements();
                obj4 = (LazyStringList)obj2;
                int k1 = ((List) (obj2)).size();
                for (obj2 = list.iterator(); ((Iterator) (obj2)).hasNext();)
                {
                    Object obj5 = ((Iterator) (obj2)).next();
                    if (obj5 == null)
                    {
                        int j = ((LazyStringList) (obj4)).size();
                        obj = (new StringBuilder(37)).append("Element at index ").append(j - k1).append(" is null.").toString();
                        for (int k = ((LazyStringList) (obj4)).size() - 1; k >= k1; k--)
                        {
                            ((LazyStringList) (obj4)).remove(k);
                        }

                        throw new NullPointerException(((String) (obj)));
                    }
                    if (obj5 instanceof ByteString)
                    {
                        ((LazyStringList) (obj4)).add((ByteString)obj5);
                    } else
                    {
                        ((LazyStringList) (obj4)).add((String)obj5);
                    }
                }

            } else
            if (obj4 instanceof PrimitiveNonBoxingCollection)
            {
                ((List) (obj2)).addAll((Collection)obj4);
            } else
            {
                if ((obj2 instanceof ArrayList) && (obj4 instanceof Collection))
                {
                    ArrayList arraylist = (ArrayList)obj2;
                    int l = ((List) (obj2)).size();
                    arraylist.ensureCapacity(((Collection)obj4).size() + l);
                }
                int l1 = ((List) (obj2)).size();
                obj4 = ((Iterable) (obj4)).iterator();
                while (((Iterator) (obj4)).hasNext()) 
                {
                    Object obj6 = ((Iterator) (obj4)).next();
                    if (obj6 == null)
                    {
                        int i1 = ((List) (obj2)).size();
                        String s = (new StringBuilder(37)).append("Element at index ").append(i1 - l1).append(" is null.").toString();
                        for (int j1 = ((List) (obj2)).size() - 1; j1 >= l1; j1--)
                        {
                            ((List) (obj2)).remove(j1);
                        }

                        throw new NullPointerException(s);
                    }
                    ((List) (obj2)).add(obj6);
                }
            }
        }
        return (QuestionResponse)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
    }

    public final View createScrollViewContents()
    {
        LayoutInflater layoutinflater;
        View view;
        final View newlyAddedAnswers[];
        com.google.protobuf.Internal.IntList intlist;
        final int idx;
        idx = 0;
        layoutinflater = LayoutInflater.from(getContext());
        view = layoutinflater.inflate(0x7f05009a, null);
        answersContainer = (LinearLayout)view.findViewById(0x7f100220);
        newlyAddedAnswers = new View[question.answerChoice_.size()];
        intlist = question.ordering_;
        if (intlist != null && intlist.size() == question.answerChoice_.size()) goto _L2; else goto _L1
_L1:
        final Object answers = question.answerChoice_;
_L4:
        int i;
        if (question.isSmiley_ && ((List) (answers)).size() == 5)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        while (idx < ((List) (answers)).size()) 
        {
            ArrayList arraylist;
            com.google.protobuf.Internal.ProtobufList protobuflist;
            if (i != 0)
            {
                layoutinflater.inflate(0x7f050093, answersContainer, true);
                newlyAddedAnswers[idx] = answersContainer.getChildAt(answersContainer.getChildCount() - 1);
                TextView textview = (TextView)newlyAddedAnswers[idx].findViewById(0x7f100213);
                textview.setText(((AnswerChoice)((List) (answers)).get(idx)).choiceText_);
                textview.setContentDescription(((AnswerChoice)((List) (answers)).get(idx)).choiceText_);
                ((ImageView)newlyAddedAnswers[idx].findViewById(0x7f100212)).setImageDrawable(VectorDrawableCompat.create(requireContext().getResources(), ((Integer)MULTI_CHOICE_SMILEY_ICON_RESOURCE_MAP.get(Integer.valueOf(idx))).intValue(), null));
            } else
            {
                layoutinflater.inflate(0x7f050092, answersContainer, true);
                newlyAddedAnswers[idx] = answersContainer.getChildAt(answersContainer.getChildCount() - 1);
                ((Button)newlyAddedAnswers[idx]).setText(((AnswerChoice)((List) (answers)).get(idx)).choiceText_);
                ((Button)newlyAddedAnswers[idx]).setContentDescription(((AnswerChoice)((List) (answers)).get(idx)).choiceText_);
            }
            newlyAddedAnswers[idx].setOnClickListener(new _cls1());
            idx++;
        }
        break MISSING_BLOCK_LABEL_464;
_L2:
        arraylist = new ArrayList();
        protobuflist = question.answerChoice_;
        i = 0;
        do
        {
            answers = arraylist;
            if (i >= protobuflist.size())
            {
                break;
            }
            arraylist.add(i, (AnswerChoice)protobuflist.get(intlist.indexOf(Integer.valueOf(i))));
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        return view;
    }

    final String getQuestionText()
    {
        return question.questionText_;
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
        ((OnQuestionProgressableChangeListener)obj).onQuestionProgressableChanged(false, this);
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putString("SelectedResponse", selectedResponse);
        bundle.putParcelable("QuestionMetrics", questionMetrics);
    }

    static 
    {
        ArrayMap arraymap = new ArrayMap();
        arraymap.put(Integer.valueOf(0), Integer.valueOf(0x7f020140));
        arraymap.put(Integer.valueOf(1), Integer.valueOf(0x7f02013e));
        arraymap.put(Integer.valueOf(2), Integer.valueOf(0x7f02013d));
        arraymap.put(Integer.valueOf(3), Integer.valueOf(0x7f02013c));
        arraymap.put(Integer.valueOf(4), Integer.valueOf(0x7f02013f));
        MULTI_CHOICE_SMILEY_ICON_RESOURCE_MAP = Collections.unmodifiableMap(arraymap);
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        public final MultipleChoiceFragment this$0;
        public final List val$answers;
        public final int val$idx;
        private final View val$newlyAddedAnswers[];

        public final void onClick(View view)
        {
            MultipleChoiceFragment multiplechoicefragment = MultipleChoiceFragment.this;
            MultipleChoiceFragment.removeOnClickListenersAndDisableClickEvents(newlyAddedAnswers);
            HatsModule.get().getIdleResourceManager().setIsMultipleChoiceSelectionAnimating(true);
            class _cls1
                implements Runnable
            {

                private final _cls1 this$1;

                public final void run()
                {
                    Object obj = _fld0;
                    boolean flag;
                    if (((Fragment) (obj)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                    }
                    flag = ((FragmentActivity) (obj)).isDestroyed();
                    if (obj == null || ((FragmentActivity) (obj)).isFinishing() || flag)
                    {
                        Log.w("HatsLibMultiChoiceFrag", "Activity was null, finishing or destroyed while attempting to navigate to the next next page. Likely the user rotated the device before the Runnable executed.");
                        return;
                    } else
                    {
                        selectedResponse = ((AnswerChoice)answers.get(idx)).choiceText_;
                        selectedIndex = idx;
                        questionMetrics.markAsAnswered();
                        String s = String.valueOf(answers.get(idx));
                        (new StringBuilder(String.valueOf(s).length() + 24)).append("User selected response: ").append(s);
                        ((NextPageOrSubmitActionable)obj).nextPageOrSubmit();
                        HatsModule.get().getIdleResourceManager().setIsMultipleChoiceSelectionAnimating(false);
                        return;
                    }
                }

                _cls1()
                {
                    this$1 = _cls1.this;
                    super();
                }
            }

            view.postOnAnimationDelayed(new _cls1(), 200L);
        }

        _cls1()
        {
            this$0 = MultipleChoiceFragment.this;
            newlyAddedAnswers = aview;
            answers = list;
            idx = i;
            super();
        }
    }

}
