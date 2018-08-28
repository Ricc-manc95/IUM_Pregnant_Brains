// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.suggestion;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Pair;
import com.google.android.calendar.newapi.quickcreate.TaskAssistUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.personalization.assist.annotate.AnnotationType;
import com.google.personalization.assist.annotate.Availability;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestion;
import com.google.personalization.assist.annotate.api.AnnotationFragment;
import com.google.personalization.assist.annotate.api.EventTime;
import com.google.personalization.assist.annotate.api.FormattedText;
import com.google.personalization.assist.annotate.api.RenderingData;

public final class SuggestionFormatter
{

    static Pair extractStringPair(Resources resources, int i)
    {
        resources = resources.getStringArray(i);
        if (resources.length == 2)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalStateException();
        } else
        {
            return new Pair(resources[0], resources[1]);
        }
    }

    static CharSequence formatDescription(Context context, AnnotatedSuggestion annotatedsuggestion)
    {
        String s = annotatedsuggestion.subtext_;
        boolean flag;
        if (annotatedsuggestion.subsubtext_ == null)
        {
            annotatedsuggestion = FormattedText.DEFAULT_INSTANCE;
        } else
        {
            annotatedsuggestion = annotatedsuggestion.subsubtext_;
        }
        if ((((FormattedText) (annotatedsuggestion)).bitField0_ & 1) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        context = null;
_L8:
        return TextTileView.concatenate(new CharSequence[] {
            s, context
        });
_L2:
        Availability availability;
        SpannableString spannablestring;
        spannablestring = new SpannableString(((FormattedText) (annotatedsuggestion)).text_);
        Availability availability1 = Availability.forNumber(((FormattedText) (annotatedsuggestion)).availability_);
        availability = availability1;
        if (availability1 == null)
        {
            availability = Availability.UNKNOWN_AVAILABILITY;
        }
        availability.ordinal();
        JVM INSTR tableswitch 1 3: default 132
    //                   1 173
    //                   2 180
    //                   3 187;
           goto _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_187;
_L4:
        break; /* Loop/switch isn't completed */
_L3:
        int i = -1;
_L9:
        if (i != -1)
        {
            spannablestring.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, i)), 0, ((FormattedText) (annotatedsuggestion)).text_.length(), 33);
        }
        context = spannablestring;
        if (true) goto _L8; else goto _L7
_L7:
        i = 0x7f0d02da;
          goto _L9
_L5:
        i = 0x7f0d02db;
          goto _L9
        i = 0x7f0d02de;
          goto _L9
    }

    static String formatTitle(Context context, AnnotatedSuggestion annotatedsuggestion)
    {
        AnnotationFragment annotationfragment;
        Object obj;
        com.google.personalization.assist.annotate.api.RenderingData.AssistanceType assistancetype;
        if (annotatedsuggestion.renderingData_ == null)
        {
            obj = RenderingData.DEFAULT_INSTANCE;
        } else
        {
            obj = annotatedsuggestion.renderingData_;
        }
        assistancetype = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.forNumber(((RenderingData) (obj)).assistanceType_);
        obj = assistancetype;
        if (assistancetype == null)
        {
            obj = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.NONE;
        }
        if (obj != com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.DATE_TIME)
        {
            context = null;
        } else
        {
label0:
            {
                annotationfragment = TaskAssistUtils.getLastFragment(annotatedsuggestion);
                if (annotationfragment != null)
                {
                    AnnotationType annotationtype1 = AnnotationType.forNumber(annotationfragment.annotationType_);
                    AnnotationType annotationtype = annotationtype1;
                    if (annotationtype1 == null)
                    {
                        annotationtype = AnnotationType.TEXT;
                    }
                    if (annotationtype == AnnotationType.EVENT_TIME)
                    {
                        break label0;
                    }
                }
                context = null;
            }
        }
_L1:
        EventTime eventtime;
        if (!TextUtils.isEmpty(context))
        {
            return context;
        } else
        {
            return annotatedsuggestion.query_.replace(" ...", "\u2026");
        }
        if (annotationfragment.eventTime_ == null)
        {
            eventtime = EventTime.DEFAULT_INSTANCE;
        } else
        {
            eventtime = annotationfragment.eventTime_;
        }
        context = TaskAssistUtils.getTimeLabel(context, eventtime, true);
          goto _L1
    }

    static int getIcon(AnnotatedSuggestion annotatedsuggestion)
    {
        com.google.personalization.assist.annotate.api.RenderingData.AssistanceType assistancetype;
        if (annotatedsuggestion.renderingData_ == null)
        {
            annotatedsuggestion = RenderingData.DEFAULT_INSTANCE;
        } else
        {
            annotatedsuggestion = annotatedsuggestion.renderingData_;
        }
        assistancetype = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.forNumber(((RenderingData) (annotatedsuggestion)).assistanceType_);
        annotatedsuggestion = assistancetype;
        if (assistancetype == null)
        {
            annotatedsuggestion = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.NONE;
        }
        switch (annotatedsuggestion.ordinal())
        {
        case 12: // '\f'
        case 17: // '\021'
        case 27: // '\033'
        default:
            return 0x7f0201dd;

        case 5: // '\005'
            return 0x7f0201c8;

        case 7: // '\007'
            return 0x7f0201cc;

        case 6: // '\006'
            return 0x7f0201f1;

        case 24: // '\030'
            return 0x7f020124;

        case 25: // '\031'
            return 0x7f02020e;

        case 26: // '\032'
            return 0x7f02022d;

        case 1: // '\001'
            return 0x7f02022d;

        case 9: // '\t'
            return 0x7f0201f1;

        case 10: // '\n'
            return 0x7f0201e5;

        case 8: // '\b'
            return 0x7f0201e9;

        case 11: // '\013'
            return 0x7f0201fd;

        case 13: // '\r'
            return 0x7f020208;

        case 14: // '\016'
            return 0x7f020220;

        case 28: // '\034'
            return 0x7f020220;

        case 2: // '\002'
            return 0x7f020207;

        case 3: // '\003'
            return 0x7f02024e;

        case 15: // '\017'
            return 0x7f02023c;

        case 16: // '\020'
            return 0x7f020219;

        case 4: // '\004'
            return 0x7f020206;

        case 18: // '\022'
            return 0x7f020235;

        case 19: // '\023'
            return 0x7f02023b;

        case 20: // '\024'
            return 0x7f02023f;

        case 21: // '\025'
            return 0x7f020221;

        case 22: // '\026'
            return 0x7f020221;

        case 23: // '\027'
            return 0x7f020241;
        }
    }

    static int getIconContentDescription(AnnotatedSuggestion annotatedsuggestion)
    {
        com.google.personalization.assist.annotate.api.RenderingData.AssistanceType assistancetype;
        if (annotatedsuggestion.renderingData_ == null)
        {
            annotatedsuggestion = RenderingData.DEFAULT_INSTANCE;
        } else
        {
            annotatedsuggestion = annotatedsuggestion.renderingData_;
        }
        assistancetype = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.forNumber(((RenderingData) (annotatedsuggestion)).assistanceType_);
        annotatedsuggestion = assistancetype;
        if (assistancetype == null)
        {
            annotatedsuggestion = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.NONE;
        }
        switch (annotatedsuggestion.ordinal())
        {
        default:
            return 0x7f1303ca;

        case 25: // '\031'
            return 0x7f1303c9;

        case 26: // '\032'
            return 0x7f1303c8;
        }
    }

    static boolean isContact(AnnotatedSuggestion annotatedsuggestion)
    {
        com.google.personalization.assist.annotate.api.RenderingData.AssistanceType assistancetype;
        if (annotatedsuggestion.renderingData_ == null)
        {
            annotatedsuggestion = RenderingData.DEFAULT_INSTANCE;
        } else
        {
            annotatedsuggestion = annotatedsuggestion.renderingData_;
        }
        assistancetype = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.forNumber(((RenderingData) (annotatedsuggestion)).assistanceType_);
        annotatedsuggestion = assistancetype;
        if (assistancetype == null)
        {
            annotatedsuggestion = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.NONE;
        }
        return annotatedsuggestion == com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.CONTACT;
    }
}
