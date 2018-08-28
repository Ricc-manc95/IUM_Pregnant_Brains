// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.libraries.hats20.answer.AnswerBeacon;
import com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter;
import com.google.android.libraries.hats20.cookie.HatsCookieManager;
import com.google.android.libraries.hats20.inject.HatsModule;
import com.google.android.libraries.hats20.model.ProtoParcels;
import com.google.android.libraries.hats20.support.espresso.IdleResourceManager;
import com.google.android.libraries.hats20.util.AnswerPiping;
import com.google.android.libraries.hats20.util.LayoutDimensions;
import com.google.android.libraries.hats20.view.BaseFragment;
import com.google.android.libraries.hats20.view.NextPageOrSubmitActionable;
import com.google.android.libraries.hats20.view.OnQuestionProgressableChangeListener;
import com.google.android.libraries.hats20.view.OpenTextFragment;
import com.google.android.libraries.hats20.view.SurveyViewPager;
import com.google.android.libraries.hats20.view.SurveyViewPagerAdapter;
import com.google.devrel.hats.proto.AnswerChoice;
import com.google.devrel.hats.proto.AnswerChoiceType;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.QuestionAnswer;
import com.google.devrel.hats.proto.QuestionRating;
import com.google.devrel.hats.proto.QuestionResponse;
import com.google.devrel.hats.proto.QuestionType;
import com.google.devrel.hats.proto.SurveyPayload;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.PrimitiveNonBoxingCollection;
import com.google.protobuf.Protobuf;
import com.google.protobuf.Schema;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.android.libraries.hats20:
//            HatsController

public class SurveyPromptActivity extends AppCompatActivity
    implements com.google.android.libraries.hats20.view.FragmentViewDelegate.MeasurementSurrogate, NextPageOrSubmitActionable, OnQuestionProgressableChangeListener
{

    private final Handler activityFinishHandler = new Handler();
    private AnswerBeacon answerBeacon;
    private AnswerBeaconTransmitter answerBeaconTransmitter;
    public String followupUrl;
    private TextView followupUrlTextView;
    private int hatsDisplayLogo;
    private boolean ignoreFirstQuestion;
    private boolean isFullWidth;
    public boolean isSubmitting;
    private int itemMeasureCount;
    private LayoutDimensions layoutDimensions;
    public FrameLayout overallContainer;
    private String siteId;
    private com.google.hats.protos.HatsSurveyData.Survey survey;
    public LinearLayout surveyContainer;
    private RectF surveyMargin;
    private SurveyPayload surveyPayload;
    private final Point surveyPreDrawMeasurements = new Point(0, 0);
    private SurveyViewPager surveyViewPager;
    private SurveyViewPagerAdapter surveyViewPagerAdapter;
    private LinearLayout thankYouLayout;
    private TextView thankYouTextView;
    private int thankyouLayoutHeight;

    public SurveyPromptActivity()
    {
        itemMeasureCount = 0;
        followupUrl = "";
    }

    private final String getFollowupUrl()
    {
label0:
        {
            if ((survey.bitField0_ & 0x100) == 256)
            {
                String s = survey.followUpUrl_;
                if (Patterns.WEB_URL.matcher(s.toLowerCase()).matches())
                {
                    break label0;
                }
            }
            return "";
        }
        if (!URLUtil.isHttpUrl(survey.followUpUrl_) && !URLUtil.isHttpsUrl(survey.followUpUrl_)) goto _L2; else goto _L1
_L1:
        Uri uri = Uri.parse(survey.followUpUrl_);
        String s1;
        if (uri.getQuery() == null)
        {
            break MISSING_BLOCK_LABEL_122;
        }
        s1 = URLEncoder.encode(uri.getQuery(), "utf-8");
_L3:
        return (new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), s1)).toString();
        s1 = "";
          goto _L3
        Object obj;
        obj;
_L5:
        Log.e("HatsLibSurveyActivity", ((Exception) (obj)).getMessage());
_L2:
        return "";
        obj;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private final boolean isScreenedOut(int i)
    {
        if (i < surveyPayload.question_.size()) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        Object obj;
        Object obj2;
        Object obj3;
        obj3 = (Question)surveyPayload.question_.get(i);
        obj2 = new ArrayList();
        QuestionType questiontype = QuestionType.forNumber(((Question) (obj3)).questionType_);
        obj = questiontype;
        if (questiontype == null)
        {
            obj = QuestionType.UNRECOGNIZED;
        }
        ((QuestionType) (obj)).ordinal();
        JVM INSTR tableswitch 1 4: default 100
    //                   1 277
    //                   2 277
    //                   3 100
    //                   4 196;
           goto _L3 _L4 _L4 _L3 _L5
_L3:
        if (((List) (obj2)).isEmpty()) goto _L1; else goto _L6
_L6:
        Iterator iterator1;
        obj = ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)answerBeacon.responses.get(i)).responses_;
        iterator1 = ((List) (obj2)).iterator();
_L10:
        if (!iterator1.hasNext()) goto _L8; else goto _L7
_L7:
        obj2 = (String)iterator1.next();
        obj3 = ((List) (obj)).iterator();
_L12:
        if (!((Iterator) (obj3)).hasNext()) goto _L10; else goto _L9
_L9:
        if (!((String) (obj2)).equals((String)((Iterator) (obj3)).next())) goto _L12; else goto _L11
_L11:
        return false;
_L5:
        Object obj1;
        int j;
        if (((Question) (obj3)).questionRating_ == null)
        {
            obj1 = QuestionRating.DEFAULT_INSTANCE;
        } else
        {
            obj1 = ((Question) (obj3)).questionRating_;
        }
        obj1 = ((QuestionRating) (obj1)).skipTo_;
        j = 0;
        while (j < ((List) (obj1)).size()) 
        {
            if (((Integer)((List) (obj1)).get(j)).intValue() == 0)
            {
                ((List) (obj2)).add(String.valueOf(j + 1));
            }
            j++;
        }
          goto _L3
_L4:
        Iterator iterator = ((Question) (obj3)).answerChoice_.iterator();
        while (iterator.hasNext()) 
        {
            AnswerChoice answerchoice = (AnswerChoice)iterator.next();
            if (answerchoice.skipTo_ == 0)
            {
                ((List) (obj2)).add(answerchoice.choiceText_);
            }
        }
          goto _L3
_L8:
        return true;
    }

    private final void setNextButtonEnabled(boolean flag)
    {
        Button button = (Button)findViewById(0x7f10020e);
        if (button != null && button.isEnabled() != flag)
        {
            float f;
            if (flag)
            {
                f = 1.0F;
            } else
            {
                f = 0.3F;
            }
            button.setAlpha(f);
            button.setEnabled(flag);
        }
    }

    private final void showThankYouAndClose(boolean flag)
    {
        boolean flag2 = true;
        int i;
        if (flag)
        {
            i = 700;
        } else
        {
            i = 0;
        }
        thankYouTextView.announceForAccessibility(thankYouTextView.getContentDescription());
        thankYouTextView.animate().alpha(1.0F).setDuration(350L).setStartDelay(i);
        thankYouTextView.setVisibility(0);
        if (!followupUrl.isEmpty())
        {
            followupUrlTextView.animate().alpha(1.0F).setDuration(350L).setStartDelay(i);
            followupUrlTextView.setVisibility(0);
            return;
        }
        IdleResourceManager idleresourcemanager = HatsModule.get().getIdleResourceManager();
        boolean flag1;
        if (!idleresourcemanager.isMultipleChoiceSelectionAnimating && !idleresourcemanager.isThankYouAnimating)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        idleresourcemanager.isThankYouAnimating = true;
        if (!flag1)
        {
            if (!idleresourcemanager.isMultipleChoiceSelectionAnimating && !idleresourcemanager.isThankYouAnimating)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (!flag1);
        }
        activityFinishHandler.postDelayed(new _cls6(), 2400L);
    }

    static void startSurveyActivity(Activity activity, String s, com.google.hats.protos.HatsSurveyData.Survey survey1, SurveyPayload surveypayload, AnswerBeacon answerbeacon, Integer integer, boolean flag, boolean flag1, 
            int i)
    {
        Intent intent = new Intent();
        intent.setClassName(activity, "com.google.android.libraries.hats20.SurveyPromptActivity");
        intent.putExtra("SiteId", s);
        intent.putExtra("Survey", survey1.toByteArray());
        intent.putExtra("SurveyPayload", surveypayload.toByteArray());
        intent.putExtra("AnswerBeacon", answerbeacon);
        intent.putExtra("IsFullWidth", flag);
        intent.putExtra("IgnoreFirstQuestion", flag1);
        intent.putExtra("PromplessRatingLogo", i);
        String.format("Starting survey for client activity: %s", new Object[] {
            activity.getClass().getCanonicalName()
        });
        if (integer == null)
        {
            activity.startActivity(intent);
            return;
        } else
        {
            activity.startActivityForResult(intent, integer.intValue());
            return;
        }
    }

    private final void switchNextTextToSubmitIfNeeded()
    {
        Button button = (Button)findViewById(0x7f10020e);
        if (button != null)
        {
            SurveyViewPager surveyviewpager = surveyViewPager;
            if (surveyviewpager.getCurrentItem() == surveyviewpager.getAdapter().getCount() - 1)
            {
                button.setText(0x7f1302f1);
            }
        }
    }

    private final void updateSurveyLayoutParameters()
    {
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)overallContainer.getLayoutParams();
        Object obj = ((WindowManager)getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        ((Display) (obj)).getSize(point);
        int j = point.x;
        obj = ((WindowManager)getSystemService("window")).getDefaultDisplay();
        point = new Point();
        ((Display) (obj)).getSize(point);
        int k = point.y;
        obj = getResources();
        int i = ((Resources) (obj)).getIdentifier("status_bar_height", "dimen", "android");
        int l;
        if (i > 0)
        {
            i = ((Resources) (obj)).getDimensionPixelSize(i);
        } else
        {
            i = 0;
        }
        l = Math.round(surveyMargin.top + surveyMargin.bottom);
        if (!isFullWidth)
        {
            j = layoutDimensions.getSurveyWidth();
        }
        obj = new Point(j, Math.min(k - i - l, surveyPreDrawMeasurements.y));
        layoutparams.width = ((Point) (obj)).x - Math.round(surveyMargin.left + surveyMargin.right);
        if (((Point) (obj)).y > 0)
        {
            i = ((Point) (obj)).y;
        } else
        {
            i = thankyouLayoutHeight;
        }
        layoutparams.height = i;
        overallContainer.setAlpha(1.0F);
        layoutparams.setMargins(Math.round(surveyMargin.left), Math.round(surveyMargin.top), Math.round(surveyMargin.right), Math.round(surveyMargin.bottom));
        overallContainer.setLayoutParams(layoutparams);
    }

    final void closeKeyboardIfOpenTextQuestion()
    {
        if (surveyViewPager != null && (surveyViewPager.getCurrentItemFragment() instanceof OpenTextFragment))
        {
            OpenTextFragment opentextfragment = (OpenTextFragment)surveyViewPager.getCurrentItemFragment();
            FragmentActivity fragmentactivity;
            if (((Fragment) (opentextfragment)).mHost == null)
            {
                fragmentactivity = null;
            } else
            {
                fragmentactivity = (FragmentActivity)((Fragment) (opentextfragment)).mHost.mActivity;
            }
            ((InputMethodManager)fragmentactivity.getSystemService("input_method")).hideSoftInputFromWindow(opentextfragment.editTextField.getWindowToken(), 0);
        }
    }

    public void finish()
    {
        if (getCallingActivity() != null)
        {
            Object obj1 = (com.google.hats.protos.HatsSurveyData.SurveyResponse.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.hats.protos.HatsSurveyData.SurveyResponse.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            Object obj = survey;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
            Object obj2 = (com.google.hats.protos.HatsSurveyData.SurveyResponse)((com.google.hats.protos.HatsSurveyData.SurveyResponse.Builder) (obj1)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj2.survey_ = ((com.google.hats.protos.HatsSurveyData.Survey) (obj));
            obj2.bitField0_ = ((com.google.hats.protos.HatsSurveyData.SurveyResponse) (obj2)).bitField0_ | 2;
            obj2 = answerBeacon.responses;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
            obj = (com.google.hats.protos.HatsSurveyData.SurveyResponse)((com.google.hats.protos.HatsSurveyData.SurveyResponse.Builder) (obj1)).instance;
            if (!((com.google.hats.protos.HatsSurveyData.SurveyResponse) (obj)).questionResponse_.isModifiable())
            {
                obj.questionResponse_ = GeneratedMessageLite.mutableCopy(((com.google.hats.protos.HatsSurveyData.SurveyResponse) (obj)).questionResponse_);
            }
            obj = ((com.google.hats.protos.HatsSurveyData.SurveyResponse) (obj)).questionResponse_;
            Internal.checkNotNull(obj2);
            if (obj2 instanceof LazyStringList)
            {
                List list = ((LazyStringList)obj2).getUnderlyingElements();
                obj2 = (LazyStringList)obj;
                int j1 = ((List) (obj)).size();
                for (obj = list.iterator(); ((Iterator) (obj)).hasNext();)
                {
                    Object obj3 = ((Iterator) (obj)).next();
                    if (obj3 == null)
                    {
                        int i = ((LazyStringList) (obj2)).size();
                        obj = (new StringBuilder(37)).append("Element at index ").append(i - j1).append(" is null.").toString();
                        for (int j = ((LazyStringList) (obj2)).size() - 1; j >= j1; j--)
                        {
                            ((LazyStringList) (obj2)).remove(j);
                        }

                        throw new NullPointerException(((String) (obj)));
                    }
                    if (obj3 instanceof ByteString)
                    {
                        ((LazyStringList) (obj2)).add((ByteString)obj3);
                    } else
                    {
                        ((LazyStringList) (obj2)).add((String)obj3);
                    }
                }

            } else
            if (obj2 instanceof PrimitiveNonBoxingCollection)
            {
                ((List) (obj)).addAll((Collection)obj2);
            } else
            {
                if ((obj instanceof ArrayList) && (obj2 instanceof Collection))
                {
                    ArrayList arraylist = (ArrayList)obj;
                    int k = ((List) (obj)).size();
                    arraylist.ensureCapacity(((Collection)obj2).size() + k);
                }
                int k1 = ((List) (obj)).size();
                obj2 = ((Iterable) (obj2)).iterator();
                while (((Iterator) (obj2)).hasNext()) 
                {
                    Object obj4 = ((Iterator) (obj2)).next();
                    if (obj4 == null)
                    {
                        int l = ((List) (obj)).size();
                        obj1 = (new StringBuilder(37)).append("Element at index ").append(l - k1).append(" is null.").toString();
                        for (int i1 = ((List) (obj)).size() - 1; i1 >= k1; i1--)
                        {
                            ((List) (obj)).remove(i1);
                        }

                        throw new NullPointerException(((String) (obj1)));
                    }
                    ((List) (obj)).add(obj4);
                }
            }
            if ("a".equals(answerBeacon.parameterBundle.getString("t")))
            {
                obj = com.google.hats.protos.HatsSurveyData.ResponseStatus.COMPLETE_ANSWER;
            } else
            {
                obj = com.google.hats.protos.HatsSurveyData.ResponseStatus.PARTIAL_ANSWER;
            }
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
            obj2 = (com.google.hats.protos.HatsSurveyData.SurveyResponse)((com.google.hats.protos.HatsSurveyData.SurveyResponse.Builder) (obj1)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj2.bitField0_ = ((com.google.hats.protos.HatsSurveyData.SurveyResponse) (obj2)).bitField0_ | 1;
            obj2.responseStatus_ = ((com.google.hats.protos.HatsSurveyData.ResponseStatus) (obj)).value;
            obj = (com.google.hats.protos.HatsSurveyData.SurveyResponse)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
            setResult(-1, (new Intent()).putExtra("ExtraResultSurveyResponse", ((AbstractMessageLite) (obj)).toByteArray()).putExtra("ExtraResultAnswerBeaconString", answerBeacon.exportAllParametersInQueryString(false).getQuery()));
        }
        super.finish();
    }

    public final Point getMeasureSpecs()
    {
        Display display = ((WindowManager)getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int i = layoutDimensions.getSurveyWidth();
        int j = Math.round(surveyMargin.left + surveyMargin.right);
        point.x = Math.min(point.x, i - j);
        return new Point(android.view.View.MeasureSpec.makeMeasureSpec(point.x, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(point.y, 0x80000000));
    }

    public final void nextPageOrSubmit()
    {
label0:
        {
            closeKeyboardIfOpenTextQuestion();
            Object obj = surveyViewPager;
            if (((SurveyViewPager) (obj)).getCurrentItemFragment() == null)
            {
                obj = null;
            } else
            {
                obj = ((SurveyViewPager) (obj)).getCurrentItemFragment().computeQuestionResponse();
            }
            if (obj != null)
            {
                Object obj4 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                long l = ((QuestionResponse) (obj)).responseDelayMs_;
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                Object obj1 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj4)).instance;
                obj1.bitField0_ = ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)).bitField0_ | 2;
                obj1.delayMs_ = l;
                Iterator iterator = ((QuestionResponse) (obj)).answer_.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    QuestionAnswer questionanswer = (QuestionAnswer)iterator.next();
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                    obj1 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj4)).instance;
                    obj1.bitField0_ = ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)).bitField0_ | 1;
                    obj1.isAnswered_ = true;
                    Object obj2 = QuestionType.forNumber(((QuestionResponse) (obj)).questionType_);
                    obj1 = obj2;
                    if (obj2 == null)
                    {
                        obj1 = QuestionType.UNRECOGNIZED;
                    }
                    if (obj1 == QuestionType.OPEN_TEXT)
                    {
                        obj1 = questionanswer.userAnswerText_;
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                        obj2 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj4)).instance;
                        if (obj1 == null)
                        {
                            throw new NullPointerException();
                        }
                        if (!((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj2)).responses_.isModifiable())
                        {
                            obj2.responses_ = GeneratedMessageLite.mutableCopy(((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj2)).responses_);
                        }
                        ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj2)).responses_.add(obj1);
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                        obj1 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj4)).instance;
                        obj1.bitField0_ = ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)).bitField0_ | 4;
                        obj1.hasWriteIn_ = true;
                        continue;
                    }
                    obj2 = QuestionType.forNumber(((QuestionResponse) (obj)).questionType_);
                    obj1 = obj2;
                    if (obj2 == null)
                    {
                        obj1 = QuestionType.UNRECOGNIZED;
                    }
                    if (obj1 == QuestionType.MULTIPLE_SELECT)
                    {
                        obj2 = AnswerChoiceType.forNumber(((QuestionAnswer)((QuestionResponse) (obj)).answer_.get(0)).choiceType_);
                        obj1 = obj2;
                        if (obj2 == null)
                        {
                            obj1 = AnswerChoiceType.UNRECOGNIZED;
                        }
                        if (obj1 == AnswerChoiceType.NONE_OF_ABOVE)
                        {
                            continue;
                        }
                    }
                    obj1 = questionanswer.predefinedAnswerText_;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                    obj2 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj4)).instance;
                    if (obj1 == null)
                    {
                        throw new NullPointerException();
                    }
                    if (!((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj2)).responses_.isModifiable())
                    {
                        obj2.responses_ = GeneratedMessageLite.mutableCopy(((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj2)).responses_);
                    }
                    ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj2)).responses_.add(obj1);
                    if (questionanswer.forPiping_)
                    {
                        obj1 = questionanswer.predefinedAnswerText_;
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                        com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse surveyquestionresponse = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj4)).instance;
                        if (obj1 == null)
                        {
                            throw new NullPointerException();
                        }
                        surveyquestionresponse.bitField0_ = surveyquestionresponse.bitField0_ | 0x10;
                        surveyquestionresponse.candidateForPipedResponse_ = ((String) (obj1));
                    }
                } while (true);
                obj1 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).build();
                int i;
                if (surveyViewPager != null)
                {
                    i = surveyViewPager.getCurrentItem();
                    if (ignoreFirstQuestion)
                    {
                        i++;
                    }
                } else
                {
                    i = 0;
                }
                obj = (Question)surveyPayload.question_.get(i);
                answerBeacon.setQuestionResponse(i, ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)), ((Question) (obj)));
                for (obj4 = answerBeacon.responses; i < ((List) (obj4)).size(); ((List) (obj4)).add(com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.DEFAULT_INSTANCE)) { }
                if (i == ((List) (obj4)).size())
                {
                    Object obj3 = QuestionType.forNumber(((Question) (obj)).questionType_);
                    obj = obj3;
                    if (obj3 == null)
                    {
                        obj = QuestionType.UNRECOGNIZED;
                    }
                    String s;
                    int j;
                    if (obj == QuestionType.OPEN_TEXT)
                    {
                        obj = (com.google.protobuf.GeneratedMessageLite.Builder)((GeneratedMessageLite) (obj1)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                        obj3 = ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).instance;
                        Protobuf.INSTANCE.schemaFor(obj3.getClass()).mergeFrom(obj3, obj1);
                        obj = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)obj;
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                        ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj)).instance).responses_ = GeneratedMessageLite.emptyProtobufList();
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                        obj1 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj)).instance;
                        if ("" == null)
                        {
                            throw new NullPointerException();
                        }
                        if (!((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)).responses_.isModifiable())
                        {
                            obj1.responses_ = GeneratedMessageLite.mutableCopy(((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)).responses_);
                        }
                        ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)).responses_.add("");
                        obj = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
                    } else
                    {
                        obj = obj1;
                    }
                    if (AnswerBeacon.isSpammy(i, ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj)).delayMs_))
                    {
                        obj1 = (com.google.protobuf.GeneratedMessageLite.Builder)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                        obj3 = ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).instance;
                        Protobuf.INSTANCE.schemaFor(obj3.getClass()).mergeFrom(obj3, obj);
                        obj = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)obj1;
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                        obj1 = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.Builder) (obj)).instance;
                        obj1.bitField0_ = ((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse) (obj1)).bitField0_ | 8;
                        obj1.isSpammy_ = true;
                        obj = (com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
                    }
                    ((List) (obj4)).add(obj);
                }
            }
            obj = surveyViewPager;
            if (((ViewPager) (obj)).getCurrentItem() == ((ViewPager) (obj)).getAdapter().getCount() - 1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                if (surveyViewPager != null)
                {
                    j = surveyViewPager.getCurrentItem();
                    i = j;
                    if (ignoreFirstQuestion)
                    {
                        i = j + 1;
                    }
                } else
                {
                    i = 0;
                }
                if (!isScreenedOut(i))
                {
                    break label0;
                }
            }
            obj = answerBeacon;
            if ("a" == null)
            {
                throw new NullPointerException("Beacon type cannot be null.");
            }
            if ("a" == null)
            {
                ((AnswerBeacon) (obj)).parameterBundle.remove("t");
            } else
            {
                ((AnswerBeacon) (obj)).parameterBundle.putString("t", "a");
            }
            obj = answerBeaconTransmitter;
            obj1 = answerBeacon;
            ((AnswerBeaconTransmitter) (obj)).executor.execute(new com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter.TransmitTask(((AnswerBeaconTransmitter) (obj)), ((AnswerBeacon) (obj1)).exportAllParametersInQueryString(true)));
            isSubmitting = true;
            setNextButtonEnabled(false);
            obj = new AnimatorSet();
            obj1 = ObjectAnimator.ofFloat(surveyContainer, "alpha", new float[] {
                0.0F
            }).setDuration(350L);
            ((ObjectAnimator) (obj1)).addListener(new _cls4());
            obj3 = ValueAnimator.ofInt(new int[] {
                overallContainer.getHeight(), thankyouLayoutHeight
            }).setDuration(350L);
            ((ValueAnimator) (obj3)).setStartDelay(350L);
            ((ValueAnimator) (obj3)).addUpdateListener(new _cls5());
            ((AnimatorSet) (obj)).playTogether(new Animator[] {
                obj1, obj3
            });
            ((AnimatorSet) (obj)).start();
            showThankYouAndClose(true);
            return;
        }
        obj = answerBeacon;
        if ("pa" == null)
        {
            throw new NullPointerException("Beacon type cannot be null.");
        }
        if ("pa" == null)
        {
            ((AnswerBeacon) (obj)).parameterBundle.remove("t");
        } else
        {
            ((AnswerBeacon) (obj)).parameterBundle.putString("t", "pa");
        }
        obj = answerBeaconTransmitter;
        obj1 = answerBeacon;
        ((AnswerBeaconTransmitter) (obj)).executor.execute(new com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter.TransmitTask(((AnswerBeaconTransmitter) (obj)), ((AnswerBeacon) (obj1)).exportAllParametersInQueryString(true)));
        obj = surveyViewPager;
        ((ViewPager) (obj)).setCurrentItem(((ViewPager) (obj)).getCurrentItem() + 1, true);
        ((SurveyViewPager) (obj)).getCurrentItemFragment().animateFadeIn();
        obj = surveyViewPager.getCurrentItemFragment().getCurrentQuestionText();
        new AnswerPiping();
        if (AnswerPiping.PATTERN.matcher(((CharSequence) (obj))).find())
        {
            obj1 = answerBeacon.responses;
            obj3 = AnswerPiping.PATTERN.matcher(((CharSequence) (obj)));
            do
            {
                if (!((Matcher) (obj3)).find())
                {
                    break;
                }
                obj4 = ((Matcher) (obj3)).group();
                s = AnswerPiping.getPipedResponse(Integer.parseInt(((Matcher) (obj3)).group(1)) - 1, ((List) (obj1)));
                if (s != null)
                {
                    obj = ((String) (obj)).replace(((CharSequence) (obj4)), s);
                }
            } while (true);
            surveyViewPager.getCurrentItemFragment().updateQuestionText(((String) (obj)));
        }
        obj = answerBeacon;
        if (surveyViewPager != null)
        {
            j = surveyViewPager.getCurrentItem();
            i = j;
            if (ignoreFirstQuestion)
            {
                i = j + 1;
            }
        } else
        {
            i = 0;
        }
        obj1 = (new StringBuilder(15)).append("r.s-").append(i).toString();
        ((AnswerBeacon) (obj)).parameterBundle.putString(((String) (obj1)), "1");
        switchNextTextToSubmitIfNeeded();
        ((Fragment) (surveyViewPager.getCurrentItemFragment())).mView.sendAccessibilityEvent(32);
        String.format("Showing question: %d", new Object[] {
            Integer.valueOf(surveyViewPager.getCurrentItem() + 1)
        });
    }

    public void onBackPressed()
    {
        Object obj = answerBeacon;
        if ("o" == null)
        {
            throw new NullPointerException("Beacon type cannot be null.");
        }
        AnswerBeacon answerbeacon;
        if ("o" == null)
        {
            ((AnswerBeacon) (obj)).parameterBundle.remove("t");
        } else
        {
            ((AnswerBeacon) (obj)).parameterBundle.putString("t", "o");
        }
        obj = answerBeaconTransmitter;
        answerbeacon = answerBeacon;
        ((AnswerBeaconTransmitter) (obj)).executor.execute(new com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter.TransmitTask(((AnswerBeaconTransmitter) (obj)), answerbeacon.exportAllParametersInQueryString(true)));
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setTitle("");
        layoutDimensions = new LayoutDimensions(this);
        Intent intent = getIntent();
        siteId = intent.getStringExtra("SiteId");
        survey = (com.google.hats.protos.HatsSurveyData.Survey)ProtoParcels.getMessage(com.google.hats.protos.HatsSurveyData.Survey.DEFAULT_INSTANCE, intent.getByteArrayExtra("Survey"));
        surveyPayload = (SurveyPayload)ProtoParcels.getMessage(SurveyPayload.DEFAULT_INSTANCE, intent.getByteArrayExtra("SurveyPayload"));
        AnswerBeacon answerbeacon;
        boolean flag1;
        if (bundle == null)
        {
            answerbeacon = (AnswerBeacon)intent.getParcelableExtra("AnswerBeacon");
        } else
        {
            answerbeacon = (AnswerBeacon)bundle.getParcelable("AnswerBeacon");
        }
        answerBeacon = answerbeacon;
        if (bundle != null && bundle.getBoolean("IsSubmitting"))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        isSubmitting = flag1;
        isFullWidth = intent.getBooleanExtra("IsFullWidth", false);
        ignoreFirstQuestion = intent.getBooleanExtra("IgnoreFirstQuestion", false);
        hatsDisplayLogo = intent.getIntExtra("PromplessRatingLogo", 0);
        if (siteId != null && survey != null && answerBeacon != null) goto _L2; else goto _L1
_L1:
        Log.e("HatsLibSurveyActivity", "Required EXTRAS not found in the intent, bailing out.");
        finish();
_L10:
        return;
_L2:
        Object obj;
        boolean flag;
        surveyMargin = layoutDimensions.getMargin(isFullWidth);
        HatsModule.get().getHatsController().markSurveyRunning();
        if (bundle != null)
        {
            obj = "created with savedInstanceState";
        } else
        {
            obj = "created anew";
        }
        String.format("Activity %s with site ID: %s", new Object[] {
            obj, siteId
        });
        answerBeaconTransmitter = new AnswerBeaconTransmitter(survey.answerUrl_, HatsCookieManager.getInstance(this));
        setContentView(0x7f05008c);
        surveyContainer = (LinearLayout)findViewById(0x7f100200);
        overallContainer = (FrameLayout)findViewById(0x7f1001ff);
        findViewById(0x7f1001fe).setOnClickListener(new _cls2());
        obj = findViewById(0x7f1001fd);
        ((View) (obj)).post(new com.google.android.libraries.hats20.util.LayoutUtils._cls1(findViewById(0x7f1001fe), 0x7f0e020f, 0, 0x7f0e020f, 0, ((View) (obj))));
        thankYouLayout = (LinearLayout)overallContainer.findViewById(0x7f100221);
        thankYouTextView = (TextView)overallContainer.findViewById(0x7f100223);
        thankYouTextView.setText(survey.thankYouMessage_);
        thankYouTextView.setContentDescription(survey.thankYouMessage_);
        thankyouLayoutHeight = getResources().getDimensionPixelSize(0x7f0e0244);
        followupUrl = getFollowupUrl();
        if (!followupUrl.isEmpty())
        {
            thankyouLayoutHeight = getResources().getDimensionPixelSize(0x7f0e0247);
            QuestionType questiontype;
            int i;
            if ((survey.bitField0_ & 0x80) == 128)
            {
                obj = survey.followUpMessage_;
            } else
            {
                obj = getResources().getString(0x7f1302f3);
            }
            followupUrlTextView = (TextView)overallContainer.findViewById(0x7f100224);
            followupUrlTextView.setClickable(true);
            followupUrlTextView.setMovementMethod(LinkMovementMethod.getInstance());
            followupUrlTextView.setText(((CharSequence) (obj)));
            followupUrlTextView.setContentDescription(((CharSequence) (obj)));
            followupUrlTextView.setOnClickListener(new _cls3());
        }
        obj = thankYouLayout;
        i = hatsDisplayLogo;
        obj = (ImageView)((View) (obj)).findViewById(0x7f100222);
        if (i > 0)
        {
            ((ImageView) (obj)).setImageResource(i);
            ((ImageView) (obj)).setVisibility(0);
        } else
        {
            ((ImageView) (obj)).setVisibility(8);
        }
        if (surveyPayload.question_.size() > 1) goto _L4; else goto _L3
_L3:
        questiontype = QuestionType.forNumber(((Question)surveyPayload.question_.get(0)).questionType_);
        obj = questiontype;
        if (questiontype == null)
        {
            obj = QuestionType.UNRECOGNIZED;
        }
        if (obj != QuestionType.RATING) goto _L4; else goto _L5
_L5:
        obj = (Question)surveyPayload.question_.get(0);
        if (((Question) (obj)).questionRating_ == null)
        {
            obj = QuestionRating.DEFAULT_INSTANCE;
        } else
        {
            obj = ((Question) (obj)).questionRating_;
        }
        if (((QuestionRating) (obj)).numStars_ == 5) goto _L6; else goto _L4
_L4:
        flag = true;
_L8:
        if (flag)
        {
            getLayoutInflater().inflate(0x7f050090, surveyContainer);
        }
        if (!ignoreFirstQuestion || surveyPayload.question_.size() != 1 && !isScreenedOut(0))
        {
            break MISSING_BLOCK_LABEL_912;
        }
        bundle = answerBeacon;
        if ("a" == null)
        {
            throw new NullPointerException("Beacon type cannot be null.");
        }
        break; /* Loop/switch isn't completed */
_L6:
        flag = false;
        if (true) goto _L8; else goto _L7
_L7:
        AnswerBeacon answerbeacon1;
        if ("a" == null)
        {
            ((AnswerBeacon) (bundle)).parameterBundle.remove("t");
        } else
        {
            ((AnswerBeacon) (bundle)).parameterBundle.putString("t", "a");
        }
        bundle = answerBeaconTransmitter;
        answerbeacon1 = answerBeacon;
        ((AnswerBeaconTransmitter) (bundle)).executor.execute(new com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter.TransmitTask(bundle, answerbeacon1.exportAllParametersInQueryString(true)));
        updateSurveyLayoutParameters();
        surveyContainer.setVisibility(8);
        showThankYouAndClose(false);
        return;
        Object obj1;
        int j;
        if (ignoreFirstQuestion)
        {
            obj1 = answerBeacon;
            if ("pa" == null)
            {
                throw new NullPointerException("Beacon type cannot be null.");
            }
            AnswerBeacon answerbeacon2;
            int k;
            if ("pa" == null)
            {
                ((AnswerBeacon) (obj1)).parameterBundle.remove("t");
            } else
            {
                ((AnswerBeacon) (obj1)).parameterBundle.putString("t", "pa");
            }
            obj1 = answerBeaconTransmitter;
            answerbeacon2 = answerBeacon;
            ((AnswerBeaconTransmitter) (obj1)).executor.execute(new com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter.TransmitTask(((AnswerBeaconTransmitter) (obj1)), answerbeacon2.exportAllParametersInQueryString(true)));
        }
        obj1 = surveyPayload.question_;
        if (ignoreFirstQuestion)
        {
            obj1 = new ArrayList(((Collection) (obj1)));
            ((ArrayList) (obj1)).remove(0);
            surveyViewPagerAdapter = new SurveyViewPagerAdapter(super.mFragments.mHost.mFragmentManager, ((List) (obj1)), hatsDisplayLogo);
        } else
        {
            surveyViewPagerAdapter = new SurveyViewPagerAdapter(super.mFragments.mHost.mFragmentManager, ((List) (obj1)), hatsDisplayLogo);
        }
        surveyViewPager = (SurveyViewPager)findViewById(0x7f100201);
        surveyViewPager.setAdapter(surveyViewPagerAdapter);
        surveyViewPager.setImportantForAccessibility(2);
        if (bundle != null)
        {
            surveyViewPager.setCurrentItem(bundle.getInt("CurrentQuestionIndex"));
        }
        if (flag)
        {
            switchNextTextToSubmitIfNeeded();
        }
        bundle = answerBeacon;
        if (surveyViewPager != null)
        {
            k = surveyViewPager.getCurrentItem();
            j = k;
            if (ignoreFirstQuestion)
            {
                j = k + 1;
            }
        } else
        {
            j = 0;
        }
        obj1 = (new StringBuilder(15)).append("r.s-").append(j).toString();
        ((AnswerBeacon) (bundle)).parameterBundle.putString(((String) (obj1)), "1");
        surveyContainer.setVisibility(0);
        surveyContainer.forceLayout();
        if (flag)
        {
            bundle = (Button)findViewById(0x7f10020e);
            bundle.setOnClickListener(new _cls1());
            obj1 = findViewById(0x7f10020d);
            ((View) (obj1)).post(new com.google.android.libraries.hats20.util.LayoutUtils._cls1(bundle, 0x7f0e020c, 0, 0x7f0e020c, 0, ((View) (obj1))));
            return;
        }
        if (true) goto _L10; else goto _L9
_L9:
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if (isFinishing())
        {
            HatsModule.get().getHatsController().markSurveyFinished();
        }
        activityFinishHandler.removeCallbacks(null);
    }

    public final void onFragmentContentMeasurement(int i, int j)
    {
        itemMeasureCount = itemMeasureCount + 1;
        surveyPreDrawMeasurements.x = Math.max(surveyPreDrawMeasurements.x, i);
        surveyPreDrawMeasurements.y = Math.max(surveyPreDrawMeasurements.y, j);
        if (itemMeasureCount == surveyViewPagerAdapter.getCount())
        {
            itemMeasureCount = 0;
            Object obj = (FrameLayout)findViewById(0x7f10020d);
            if (obj != null)
            {
                Point point = surveyPreDrawMeasurements;
                i = point.y;
                point.y = ((FrameLayout) (obj)).getMeasuredHeight() + i;
            }
            obj = surveyViewPager;
            ((SurveyViewPager) (obj)).getCurrentItemFragment().onPageScrolledIntoView();
            ((SurveyViewPager) (obj)).getCurrentItemFragment().animateFadeIn();
            if (answerBeacon.parameterBundle.getString("t") != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                Object obj1 = answerBeacon;
                if ("sv" == null)
                {
                    throw new NullPointerException("Beacon type cannot be null.");
                }
                AnswerBeacon answerbeacon;
                if ("sv" == null)
                {
                    ((AnswerBeacon) (obj1)).parameterBundle.remove("t");
                } else
                {
                    ((AnswerBeacon) (obj1)).parameterBundle.putString("t", "sv");
                }
                obj1 = answerBeaconTransmitter;
                answerbeacon = answerBeacon;
                ((AnswerBeaconTransmitter) (obj1)).executor.execute(new com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter.TransmitTask(((AnswerBeaconTransmitter) (obj1)), answerbeacon.exportAllParametersInQueryString(true)));
            }
            updateSurveyLayoutParameters();
            obj1 = getWindow();
            ((Window) (obj1)).addFlags(2);
            ((Window) (obj1)).clearFlags(32);
            ((Window) (obj1)).addFlags(0x40000);
            ((Window) (obj1)).setDimAmount(0.4F);
            if (layoutDimensions.context.getResources().getBoolean(0x7f0c000d))
            {
                findViewById(0x7f1001fe).setVisibility(0);
            }
            ((Fragment) (surveyViewPager.getCurrentItemFragment())).mView.sendAccessibilityEvent(32);
        }
    }

    protected void onPostResume()
    {
        super.onPostResume();
        if (isSubmitting && followupUrl.isEmpty())
        {
            finish();
        }
    }

    public final void onQuestionProgressableChanged(boolean flag, Fragment fragment)
    {
        if (fragment.getArguments().getInt("QuestionIndex", -1) == surveyViewPager.getCurrentItem())
        {
            setNextButtonEnabled(flag);
        }
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        int i;
        if (surveyViewPager != null)
        {
            int j = surveyViewPager.getCurrentItem();
            i = j;
            if (ignoreFirstQuestion)
            {
                i = j + 1;
            }
        } else
        {
            i = 0;
        }
        bundle.putInt("CurrentQuestionIndex", i);
        bundle.putBoolean("IsSubmitting", isSubmitting);
        bundle.putParcelable("AnswerBeacon", answerBeacon);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (motionevent.getAction() == 0)
        {
            Rect rect = new Rect();
            overallContainer.getGlobalVisibleRect(rect);
            if (!rect.contains((int)motionevent.getX(), (int)motionevent.getY()) && isSubmitting)
            {
                finish();
                return true;
            }
        }
        return super.onTouchEvent(motionevent);
    }

    final void setBeaconTypeAndTransmit(String s)
    {
        AnswerBeacon answerbeacon = answerBeacon;
        if (s == null)
        {
            throw new NullPointerException("Beacon type cannot be null.");
        }
        if (s == null)
        {
            answerbeacon.parameterBundle.remove("t");
        } else
        {
            answerbeacon.parameterBundle.putString("t", s);
        }
        s = answerBeaconTransmitter;
        answerbeacon = answerBeacon;
        ((AnswerBeaconTransmitter) (s)).executor.execute(new com.google.android.libraries.hats20.answer.AnswerBeaconTransmitter.TransmitTask(s, answerbeacon.exportAllParametersInQueryString(true)));
    }

    private class _cls6
        implements Runnable
    {

        private final SurveyPromptActivity this$0;

        public final void run()
        {
            boolean flag1 = true;
            isSubmitting = true;
            IdleResourceManager idleresourcemanager = HatsModule.get().getIdleResourceManager();
            boolean flag;
            if (!idleresourcemanager.isMultipleChoiceSelectionAnimating && !idleresourcemanager.isThankYouAnimating)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            idleresourcemanager.isThankYouAnimating = false;
            if (!flag)
            {
                if (!idleresourcemanager.isMultipleChoiceSelectionAnimating && !idleresourcemanager.isThankYouAnimating)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (!flag);
            }
            finish();
        }

        _cls6()
        {
            this$0 = SurveyPromptActivity.this;
            super();
        }
    }


    private class _cls4 extends AnimatorListenerAdapter
    {

        private final SurveyPromptActivity this$0;

        public final void onAnimationEnd(Animator animator)
        {
            surveyContainer.setVisibility(8);
        }

        _cls4()
        {
            this$0 = SurveyPromptActivity.this;
            super();
        }
    }


    private class _cls5
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private final SurveyPromptActivity this$0;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            overallContainer.getLayoutParams().height = ((Integer)valueanimator.getAnimatedValue()).intValue();
            overallContainer.requestLayout();
        }

        _cls5()
        {
            this$0 = SurveyPromptActivity.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final SurveyPromptActivity this$0;

        public final void onClick(View view)
        {
            setBeaconTypeAndTransmit("o");
            closeKeyboardIfOpenTextQuestion();
            finish();
        }

        _cls2()
        {
            this$0 = SurveyPromptActivity.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        private final SurveyPromptActivity this$0;

        public final void onClick(View view)
        {
            view = new Intent("android.intent.action.VIEW", Uri.parse(followupUrl));
            startActivity(view);
            finish();
        }

        _cls3()
        {
            this$0 = SurveyPromptActivity.this;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final SurveyPromptActivity this$0;

        public final void onClick(View view)
        {
            nextPageOrSubmit();
        }

        _cls1()
        {
            this$0 = SurveyPromptActivity.this;
            super();
        }
    }

}
