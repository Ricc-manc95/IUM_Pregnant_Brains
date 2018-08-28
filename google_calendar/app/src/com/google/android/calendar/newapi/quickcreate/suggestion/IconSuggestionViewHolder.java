// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.suggestion;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.calendar.newapi.quickcreate.TaskAssistUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.personalization.assist.annotate.AnnotationType;
import com.google.personalization.assist.annotate.Availability;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestion;
import com.google.personalization.assist.annotate.api.AnnotationFragment;
import com.google.personalization.assist.annotate.api.EventTime;
import com.google.personalization.assist.annotate.api.FormattedText;
import com.google.personalization.assist.annotate.api.RenderingData;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.suggestion:
//            SuggestionViewHolder, SuggestionFormatter

final class IconSuggestionViewHolder extends SuggestionViewHolder
{

    IconSuggestionViewHolder(ViewGroup viewgroup, SuggestionViewHolder.Listener listener)
    {
        viewgroup = new TextTileView(viewgroup.getContext());
        viewgroup.treatAsButton(true);
        super(viewgroup, listener);
    }

    public final void bind(AnnotatedSuggestion annotatedsuggestion)
    {
        Object obj;
        Object obj2;
        Object obj3;
        Context context;
        boolean flag;
        Object obj1 = null;
        super.bind(annotatedsuggestion);
        TextTileView texttileview = (TextTileView)view;
        obj2 = this.context;
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
            obj = null;
        } else
        {
label0:
            {
                obj3 = TaskAssistUtils.getLastFragment(annotatedsuggestion);
                if (obj3 != null)
                {
                    AnnotationType annotationtype = AnnotationType.forNumber(((AnnotationFragment) (obj3)).annotationType_);
                    obj = annotationtype;
                    if (annotationtype == null)
                    {
                        obj = AnnotationType.TEXT;
                    }
                    if (obj == AnnotationType.EVENT_TIME)
                    {
                        break label0;
                    }
                }
                obj = null;
            }
        }
_L3:
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = annotatedsuggestion.query_.replace(" ...", "\u2026");
        }
        texttileview.setPrimaryText(new CharSequence[] {
            obj
        });
        obj2 = (TextTileView)view;
        context = this.context;
        obj3 = annotatedsuggestion.subtext_;
        if (annotatedsuggestion.subsubtext_ == null)
        {
            obj = FormattedText.DEFAULT_INSTANCE;
        } else
        {
            obj = annotatedsuggestion.subsubtext_;
        }
        if ((((FormattedText) (obj)).bitField0_ & 1) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        obj = obj1;
_L8:
        ((TextTileView) (obj2)).setSecondaryText(new CharSequence[] {
            TextTileView.concatenate(new CharSequence[] {
                obj3, obj
            })
        });
        ((TextTileView)view).setIconDrawable(SuggestionFormatter.getIcon(annotatedsuggestion));
        annotatedsuggestion = this.context.getString(SuggestionFormatter.getIconContentDescription(annotatedsuggestion));
        ((TextTileView)view).getIcon().setContentDescription(annotatedsuggestion);
        return;
        if (((AnnotationFragment) (obj3)).eventTime_ == null)
        {
            obj = EventTime.DEFAULT_INSTANCE;
        } else
        {
            obj = ((AnnotationFragment) (obj3)).eventTime_;
        }
        obj = TaskAssistUtils.getTimeLabel(((Context) (obj2)), ((EventTime) (obj)), true);
          goto _L3
_L2:
        Availability availability;
        SpannableString spannablestring;
        spannablestring = new SpannableString(((FormattedText) (obj)).text_);
        Availability availability1 = Availability.forNumber(((FormattedText) (obj)).availability_);
        availability = availability1;
        if (availability1 == null)
        {
            availability = Availability.UNKNOWN_AVAILABILITY;
        }
        availability.ordinal();
        JVM INSTR tableswitch 1 3: default 384
    //                   1 428
    //                   2 435
    //                   3 442;
           goto _L4 _L5 _L6 _L7
_L7:
        break MISSING_BLOCK_LABEL_442;
_L4:
        int i = -1;
_L9:
        if (i != -1)
        {
            spannablestring.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, i)), 0, ((FormattedText) (obj)).text_.length(), 33);
        }
        obj = spannablestring;
          goto _L8
_L5:
        i = 0x7f0d02da;
          goto _L9
_L6:
        i = 0x7f0d02db;
          goto _L9
        i = 0x7f0d02de;
          goto _L9
    }
}
