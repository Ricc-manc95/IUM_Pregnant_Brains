// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import com.google.android.calendar.common.view.NinjaEditText;
import com.google.android.calendar.newapi.common.ShinobiEditText;
import com.google.android.calendar.newapi.quickcreate.annotation.Annotator;
import com.google.android.calendar.newapi.quickcreate.annotation.DurationRecorder;
import com.google.android.calendar.newapi.quickcreate.annotation.RequestFactory;
import com.google.android.calendar.newapi.quickcreate.suggestion.SuggestionAdapter;
import com.google.android.calendar.newapi.quickcreate.text.AnnotatedText;
import com.google.android.calendar.newapi.quickcreate.text.FragmentList;
import com.google.android.calendar.newapi.quickcreate.text.TextFormatter;
import com.google.android.calendar.newapi.quickcreate.text.TextPresenter;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.personalization.assist.annotate.AnnotationType;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestion;
import com.google.personalization.assist.annotate.api.Annotation;
import com.google.personalization.assist.annotate.api.AnnotationFragment;
import com.google.personalization.assist.annotate.api.EventTime;
import com.google.personalization.assist.annotate.api.SuggestionClick;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import com.google.protobuf.Protobuf;
import com.google.protobuf.Schema;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            ResultCreator, StatisticsLogger, TaskAssistUtils

public class QuickCreatePresenter
    implements com.google.android.calendar.newapi.quickcreate.annotation.Annotator.Listener, com.google.android.calendar.newapi.quickcreate.suggestion.SuggestionViewHolder.Listener, com.google.android.calendar.newapi.quickcreate.text.TextPresenter.Listener
{

    private final Annotator annotator;
    private Annotation lastAnnotation;
    private String lastAnnotationHint;
    private final ResultCreator resultCreator;
    private final SuggestionAdapter suggestionAdapter;
    private SuggestionClick suggestionClick;
    private boolean suggestionReceived;
    private final TextPresenter textPresenter;

    QuickCreatePresenter(Annotator annotator1, SuggestionAdapter suggestionadapter, TextPresenter textpresenter, ResultCreator resultcreator)
    {
        suggestionReceived = false;
        annotator = annotator1;
        suggestionAdapter = suggestionadapter;
        textPresenter = textpresenter;
        resultCreator = resultcreator;
    }

    public final Object createResult()
    {
        return resultCreator.createResult(textPresenter.text.getText().toString(), textPresenter.fragmentList.fragments, suggestionReceived);
    }

    public final void finish(Context context)
    {
        TextPresenter textpresenter = textPresenter;
        textpresenter.text.removeTextChangedListener(textpresenter.textWatcher);
        textpresenter.text.onDeletePressedListener = null;
        StatisticsLogger.logStatistics(context, new ArrayList(annotator.durationRecorder.durations));
    }

    public final Bundle getState()
    {
        Bundle bundle = new Bundle(com/google/android/calendar/newapi/quickcreate/QuickCreatePresenter.getClassLoader());
        TextPresenter textpresenter = textPresenter;
        Bundle bundle1 = new Bundle(textpresenter.getClass().getClassLoader());
        bundle1.putParcelable("FragmentList", textpresenter.fragmentList);
        bundle1.putParcelableArrayList("UndoStack", new ArrayList(textpresenter.undoStack));
        bundle1.putCharSequence("Text", textpresenter.text.getText());
        bundle.putBundle("TextPresenter", bundle1);
        bundle.putBoolean("SuggestionReceived", suggestionReceived);
        bundle.putString("LastAnnotationHint", lastAnnotationHint);
        return bundle;
    }

    public final void onSuggestionChosen(AnnotatedSuggestion annotatedsuggestion)
    {
        Object obj;
        Object obj1;
        TextPresenter textpresenter;
        TextFormatter textformatter;
        StringBuilder stringbuilder;
        ArrayList arraylist;
        ArrayList arraylist1;
        int j;
        int j1;
        if (annotatedsuggestion.annotation_ == null)
        {
            obj = Annotation.DEFAULT_INSTANCE;
        } else
        {
            obj = annotatedsuggestion.annotation_;
        }
        lastAnnotation = ((Annotation) (obj));
        lastAnnotationHint = annotatedsuggestion.annotationHint_;
        if (annotatedsuggestion.suggestionClick_ == null)
        {
            obj = SuggestionClick.DEFAULT_INSTANCE;
        } else
        {
            obj = annotatedsuggestion.suggestionClick_;
        }
        suggestionClick = ((SuggestionClick) (obj));
        if (TextUtils.isEmpty(annotatedsuggestion.displayString_))
        {
            obj = annotatedsuggestion.query_;
        } else
        {
            obj = annotatedsuggestion.displayString_;
        }
        if (annotatedsuggestion.annotation_ == null)
        {
            obj1 = Annotation.DEFAULT_INSTANCE;
        } else
        {
            obj1 = annotatedsuggestion.annotation_;
        }
        obj1 = ((Annotation) (obj1)).fragment_;
        resultCreator.processSelectedSuggestion(((List) (obj1)), annotatedsuggestion.query_, annotatedsuggestion.annotationHint_);
        textpresenter = textPresenter;
        textformatter = textpresenter.textFormatter;
        annotatedsuggestion = new ArrayList(((Collection) (obj1)));
        Collections.sort(annotatedsuggestion, com.google.android.calendar.newapi.quickcreate.text.TextFormatter..Lambda._cls0.$instance);
        stringbuilder = new StringBuilder(((String) (obj)));
        j = 0;
        arraylist = new ArrayList();
        arraylist1 = (ArrayList)annotatedsuggestion;
        j1 = arraylist1.size();
        for (int i = 0; i < j1;)
        {
            AnnotationFragment annotationfragment = (AnnotationFragment)arraylist1.get(i);
            int k1 = annotationfragment.beginPos_ + j;
            int i1 = annotationfragment.endPos_ + j;
            annotatedsuggestion = annotationfragment.text_;
            obj1 = AnnotationType.forNumber(annotationfragment.annotationType_);
            obj = obj1;
            if (obj1 == null)
            {
                obj = AnnotationType.TEXT;
            }
            int l = i1;
            int k = j;
            if (obj == AnnotationType.EVENT_TIME)
            {
                obj = textformatter.context;
                if (annotationfragment.eventTime_ == null)
                {
                    annotatedsuggestion = EventTime.DEFAULT_INSTANCE;
                } else
                {
                    annotatedsuggestion = annotationfragment.eventTime_;
                }
                annotatedsuggestion = TaskAssistUtils.getTimeLabel(((Context) (obj)), annotatedsuggestion, false);
                k = annotationfragment.endPos_;
                l = annotatedsuggestion.length();
                k = j + (l - (k - k1));
                stringbuilder.replace(k1, i1, annotatedsuggestion);
                l = k1 + l;
            }
            obj = (com.google.protobuf.GeneratedMessageLite.Builder)annotationfragment.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            obj1 = ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).instance;
            Protobuf.INSTANCE.schemaFor(obj1.getClass()).mergeFrom(obj1, annotationfragment);
            obj = (com.google.personalization.assist.annotate.api.AnnotationFragment.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)obj;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            obj1 = (AnnotationFragment)((com.google.personalization.assist.annotate.api.AnnotationFragment.Builder) (obj)).instance;
            obj1.bitField0_ = ((AnnotationFragment) (obj1)).bitField0_ | 4;
            obj1.beginPos_ = k1;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            obj1 = (AnnotationFragment)((com.google.personalization.assist.annotate.api.AnnotationFragment.Builder) (obj)).instance;
            obj1.bitField0_ = ((AnnotationFragment) (obj1)).bitField0_ | 8;
            obj1.endPos_ = l;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            obj1 = (AnnotationFragment)((com.google.personalization.assist.annotate.api.AnnotationFragment.Builder) (obj)).instance;
            if (annotatedsuggestion == null)
            {
                throw new NullPointerException();
            }
            obj1.bitField0_ = ((AnnotationFragment) (obj1)).bitField0_ | 1;
            obj1.text_ = annotatedsuggestion;
            arraylist.add((AnnotationFragment)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build());
            i++;
            j = k;
        }

        annotatedsuggestion = AnnotatedText.create(stringbuilder.toString(), arraylist);
        textpresenter.undoStack.add(new AnnotatedText(textpresenter.text.getText().toString(), textpresenter.fragmentList));
        textpresenter.applyAnnotatedText(annotatedsuggestion);
    }

    public final void onSuggestionsLoaded$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FELQ6IR1F9HKN6T1R55B0____0(List list)
    {
        suggestionReceived = true;
        SuggestionAdapter suggestionadapter = suggestionAdapter;
        suggestionadapter.suggestions = list;
        suggestionadapter.topmostPositionCalled = false;
        ((android.support.v7.widget.RecyclerView.Adapter) (suggestionadapter)).mObservable.notifyChanged();
        if (suggestionadapter.recyclerView != null)
        {
            suggestionadapter.recyclerView.scrollToPosition(0);
        }
    }

    public final void onTextChanged(String s, List list)
    {
        if (lastAnnotation != null)
        {
            Object obj = lastAnnotation;
            Object obj1 = (com.google.protobuf.GeneratedMessageLite.Builder)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
            GeneratedMessageLite generatedmessagelite = ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).instance;
            Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).mergeFrom(generatedmessagelite, obj);
            obj = (com.google.personalization.assist.annotate.api.Annotation.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)obj1;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            ((Annotation)((com.google.personalization.assist.annotate.api.Annotation.Builder) (obj)).instance).fragment_ = Annotation.emptyProtobufList();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            obj1 = (Annotation)((com.google.personalization.assist.annotate.api.Annotation.Builder) (obj)).instance;
            if (!((Annotation) (obj1)).fragment_.isModifiable())
            {
                obj1.fragment_ = GeneratedMessageLite.mutableCopy(((Annotation) (obj1)).fragment_);
            }
            obj1 = ((Annotation) (obj1)).fragment_;
            Internal.checkNotNull(list);
            if (list instanceof LazyStringList)
            {
                List list1 = ((LazyStringList)list).getUnderlyingElements();
                list = (LazyStringList)obj1;
                int j1 = ((List) (obj1)).size();
                for (obj1 = list1.iterator(); ((Iterator) (obj1)).hasNext();)
                {
                    Object obj2 = ((Iterator) (obj1)).next();
                    if (obj2 == null)
                    {
                        int i = list.size();
                        s = (new StringBuilder(37)).append("Element at index ").append(i - j1).append(" is null.").toString();
                        for (int j = list.size() - 1; j >= j1; j--)
                        {
                            list.remove(j);
                        }

                        throw new NullPointerException(s);
                    }
                    if (obj2 instanceof ByteString)
                    {
                        list.add((ByteString)obj2);
                    } else
                    {
                        list.add((String)obj2);
                    }
                }

            } else
            if (list instanceof PrimitiveNonBoxingCollection)
            {
                ((List) (obj1)).addAll((Collection)list);
            } else
            {
                if ((obj1 instanceof ArrayList) && (list instanceof Collection))
                {
                    ArrayList arraylist = (ArrayList)obj1;
                    int k = ((List) (obj1)).size();
                    arraylist.ensureCapacity(((Collection)list).size() + k);
                }
                int k1 = ((List) (obj1)).size();
                list = list.iterator();
                while (list.hasNext()) 
                {
                    Object obj3 = list.next();
                    if (obj3 == null)
                    {
                        int l = ((List) (obj1)).size();
                        s = (new StringBuilder(37)).append("Element at index ").append(l - k1).append(" is null.").toString();
                        for (int i1 = ((List) (obj1)).size() - 1; i1 >= k1; i1--)
                        {
                            ((List) (obj1)).remove(i1);
                        }

                        throw new NullPointerException(s);
                    }
                    ((List) (obj1)).add(obj3);
                }
            }
            lastAnnotation = (Annotation)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
        }
        list = annotator;
        obj = lastAnnotation;
        obj1 = suggestionClick;
        s = ((Annotator) (list)).requestFactory.createRequest(s, ((Annotation) (obj)), ((SuggestionClick) (obj1)), ((Annotator) (list)).sessionId);
        if (((Annotator) (list)).currentFuture != null)
        {
            ((Annotator) (list)).currentFuture.cancel(true);
        }
        ((Annotator) (list)).throttlingExecutorMain.execute(new com.google.android.calendar.newapi.quickcreate.annotation.Annotator..Lambda._cls0(list, s));
        suggestionClick = null;
    }

    public final void restoreState(Bundle bundle)
    {
        suggestionReceived = bundle.getBoolean("SuggestionReceived", false);
        TextPresenter textpresenter = textPresenter;
        Object obj = bundle.getBundle("TextPresenter");
        textpresenter.fragmentList = (FragmentList)((Bundle) (obj)).getParcelable("FragmentList");
        textpresenter.undoStack.clear();
        textpresenter.undoStack.addAll(((Bundle) (obj)).getParcelableArrayList("UndoStack"));
        ShinobiEditText shinobiedittext = textpresenter.text;
        obj = ((Bundle) (obj)).getCharSequence("Text");
        shinobiedittext.stealth = true;
        shinobiedittext.setText(((CharSequence) (obj)));
        shinobiedittext.stealth = false;
        textpresenter.updateTextListener();
        lastAnnotationHint = bundle.getString("LastAnnotationHint");
        resultCreator.processSelectedSuggestion(textPresenter.fragmentList.fragments, textPresenter.text.getText().toString(), lastAnnotationHint);
    }
}
