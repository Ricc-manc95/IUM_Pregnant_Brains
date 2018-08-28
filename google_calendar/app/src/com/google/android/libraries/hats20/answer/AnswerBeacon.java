// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.answer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.libraries.hats20.model.ProtoParcels;
import com.google.devrel.hats.proto.Question;
import com.google.devrel.hats.proto.QuestionType;
import com.google.protobuf.AbstractMessageLite;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class AnswerBeacon
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private List openTextResponseKeys;
    public final Bundle parameterBundle;
    public final List responses;

    public AnswerBeacon()
    {
        openTextResponseKeys = new ArrayList();
        parameterBundle = new Bundle();
        parameterBundle.putString("m.v", "3");
        responses = new ArrayList();
    }

    AnswerBeacon(Parcel parcel)
    {
        openTextResponseKeys = new ArrayList();
        parameterBundle = parcel.readBundle(getClass().getClassLoader());
        if (parameterBundle == null)
        {
            throw new NullPointerException("Parcel did not contain required Bundle while unparceling an AnswerBeacon.");
        }
        int j = parcel.readInt();
        responses = new ArrayList(j);
        for (int i = 0; i < j; i++)
        {
            responses.add((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)ProtoParcels.getMessage(com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse.DEFAULT_INSTANCE, parcel.createByteArray()));
        }

    }

    public static boolean isSpammy(int i, long l)
    {
        return i == 0 && l < 1500L;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof AnswerBeacon)) goto _L2; else goto _L1
_L1:
        Bundle bundle;
        bundle = parameterBundle;
        obj = ((AnswerBeacon)obj).parameterBundle;
        if (bundle.size() == ((Bundle) (obj)).size()) goto _L4; else goto _L3
_L3:
        boolean flag = false;
_L5:
        if (flag)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L4:
label0:
        {
            Object obj1 = bundle.keySet();
            if (!((Set) (obj1)).containsAll(((Bundle) (obj)).keySet()))
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            obj1 = ((Set) (obj1)).iterator();
            Object obj2;
            Object obj3;
label1:
            do
            {
                do
                {
                    if (!((Iterator) (obj1)).hasNext())
                    {
                        break label0;
                    }
                    obj3 = (String)((Iterator) (obj1)).next();
                    obj2 = bundle.get(((String) (obj3)));
                    obj3 = ((Bundle) (obj)).get(((String) (obj3)));
                    if (obj2 != null)
                    {
                        continue label1;
                    }
                } while (obj3 == null);
                flag = false;
                continue; /* Loop/switch isn't completed */
            } while (obj2.equals(obj3));
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        flag = true;
        if (true) goto _L5; else goto _L2
_L2:
        return false;
    }

    public final Uri exportAllParametersInQueryString(boolean flag)
    {
        android.net.Uri.Builder builder = new android.net.Uri.Builder();
        long l = System.currentTimeMillis() / 1000L;
        Iterator iterator;
        if (l < 0L)
        {
            parameterBundle.remove("m.lt");
        } else
        {
            parameterBundle.putLong("m.lt", l);
        }
        iterator = parameterBundle.keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s = (String)iterator.next();
            if (flag || !openTextResponseKeys.contains(s))
            {
                Object obj = parameterBundle.get(s);
                if (obj instanceof List)
                {
                    obj = ((List)obj).iterator();
                    while (((Iterator) (obj)).hasNext()) 
                    {
                        builder.appendQueryParameter(s, String.valueOf(((Iterator) (obj)).next()));
                    }
                } else
                if (obj != null)
                {
                    builder.appendQueryParameter(s, String.valueOf(obj));
                }
            }
        } while (true);
        if ("o".equals(parameterBundle.getString("t")))
        {
            builder.appendQueryParameter("m.sh", "close");
        }
        builder.appendQueryParameter("d", "1");
        return builder.build();
    }

    public final int hashCode()
    {
        return parameterBundle.keySet().hashCode();
    }

    public final AnswerBeacon setQuestionResponse(int i, com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse surveyquestionresponse, Question question)
    {
label0:
        {
            Object obj;
            Object obj1;
            boolean flag;
            long l;
            if ((surveyquestionresponse.bitField0_ & 2) == 2)
            {
                l = surveyquestionresponse.delayMs_;
            } else
            {
                l = -1L;
            }
            parameterBundle.remove((new StringBuilder(16)).append("m.sc-").append(i).toString());
            parameterBundle.remove((new StringBuilder(15)).append("m.d-").append(i).toString());
            if (i == 0 && l < 1500L)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                (new StringBuilder(63)).append("First question delay ").append(l).append(" is considered spammy.");
                obj = (new StringBuilder(16)).append("m.sc-").append(i).toString();
                boolean flag1;
                if (l < 0L)
                {
                    parameterBundle.remove(((String) (obj)));
                } else
                {
                    parameterBundle.putLong(((String) (obj)), l);
                }
            } else
            {
                obj = (new StringBuilder(15)).append("m.d-").append(i).toString();
                if (l < 0L)
                {
                    parameterBundle.remove(((String) (obj)));
                } else
                {
                    parameterBundle.putLong(((String) (obj)), l);
                }
            }
            obj1 = question.ordering_;
            if (((List) (obj1)).isEmpty())
            {
                obj = (new StringBuilder(15)).append("r.o-").append(i).toString();
                if (true)
                {
                    parameterBundle.remove(((String) (obj)));
                } else
                {
                    parameterBundle.putString(((String) (obj)), null);
                }
            } else
            {
                obj = (new StringBuilder(15)).append("r.o-").append(i).toString();
                obj1 = TextUtils.join(".", ((Iterable) (obj1)));
                if (obj1 == null)
                {
                    parameterBundle.remove(((String) (obj)));
                } else
                {
                    parameterBundle.putString(((String) (obj)), ((String) (obj1)));
                }
            }
            flag1 = surveyquestionresponse.hasWriteIn_;
            obj = (new StringBuilder(15)).append("r.t-").append(i).toString();
            if (flag1)
            {
                parameterBundle.putString(((String) (obj)), "1");
            } else
            {
                parameterBundle.remove(((String) (obj)));
            }
            obj1 = surveyquestionresponse.responses_;
            obj = QuestionType.forNumber(question.questionType_);
            question = ((Question) (obj));
            if (obj == null)
            {
                question = QuestionType.UNRECOGNIZED;
            }
            if (question == QuestionType.OPEN_TEXT)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                openTextResponseKeys.add((new StringBuilder(15)).append("r.r-").append(i).toString());
            }
            parameterBundle.putStringArrayList((new StringBuilder(15)).append("r.r-").append(i).toString(), new ArrayList(((java.util.Collection) (obj1))));
            if ((surveyquestionresponse.bitField0_ & 0x10) == 16)
            {
                surveyquestionresponse = surveyquestionresponse.candidateForPipedResponse_;
                question = (new StringBuilder(16)).append("m.pa-").append(i).toString();
                if (surveyquestionresponse != null)
                {
                    break label0;
                }
                parameterBundle.remove(question);
            }
            return this;
        }
        parameterBundle.putString(question, surveyquestionresponse);
        return this;
    }

    public final String toString()
    {
        String s = exportAllParametersInQueryString(true).toString().replace("&", "\n");
        return (new StringBuilder(String.valueOf(s).length() + 14)).append("AnswerBeacon{").append(s).append("}").toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeBundle(parameterBundle);
        parcel.writeInt(responses.size());
        for (Iterator iterator = responses.iterator(); iterator.hasNext(); parcel.writeByteArray(((com.google.hats.protos.HatsSurveyData.SurveyQuestionResponse)iterator.next()).toByteArray())) { }
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AnswerBeacon(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new AnswerBeacon[i];
        }

        _cls1()
        {
        }
    }

}
