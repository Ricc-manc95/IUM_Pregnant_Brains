// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.suggestion;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.calendar.newapi.quickcreate.TaskAssistUtils;
import com.google.android.calendar.newapi.segment.attendee.ContactTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.personalization.assist.annotate.AnnotationType;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestion;
import com.google.personalization.assist.annotate.api.AnnotationFragment;
import com.google.personalization.assist.annotate.api.Contact;
import com.google.personalization.assist.annotate.api.EmailAddress;
import com.google.personalization.assist.annotate.api.EventTime;
import com.google.personalization.assist.annotate.api.RenderingData;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.suggestion:
//            SuggestionViewHolder

final class ContactSuggestionViewHolder extends SuggestionViewHolder
{

    private final String sourceAccount;

    ContactSuggestionViewHolder(ViewGroup viewgroup, String s, SuggestionViewHolder.Listener listener)
    {
        viewgroup = new ContactTileView(viewgroup.getContext());
        viewgroup.treatAsButton(true);
        super(viewgroup, listener);
        sourceAccount = s;
    }

    public final void bind(AnnotatedSuggestion annotatedsuggestion)
    {
        Object obj;
        android.content.Context context;
        AnnotationFragment annotationfragment;
        super.bind(annotatedsuggestion);
        Object obj1;
        boolean flag;
        if (annotatedsuggestion.renderingData_ == null)
        {
            obj = RenderingData.DEFAULT_INSTANCE;
        } else
        {
            obj = annotatedsuggestion.renderingData_;
        }
        obj1 = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.forNumber(((RenderingData) (obj)).assistanceType_);
        obj = obj1;
        if (obj1 == null)
        {
            obj = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.NONE;
        }
        if (obj == com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.CONTACT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        obj = TaskAssistUtils.getLastFragment(annotatedsuggestion);
        com.google.personalization.assist.annotate.api.RenderingData.AssistanceType assistancetype;
        if (obj == null)
        {
            obj1 = "";
        } else
        {
            if (((AnnotationFragment) (obj)).contact_ == null)
            {
                obj = Contact.DEFAULT_INSTANCE;
            } else
            {
                obj = ((AnnotationFragment) (obj)).contact_;
            }
            obj = ((Contact) (obj)).emailAddress_;
            if (((List) (obj)).isEmpty())
            {
                obj1 = "";
            } else
            {
                obj1 = ((EmailAddress)((List) (obj)).get(0)).emailAddress_;
            }
        }
        context = this.context;
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
                annotationfragment = TaskAssistUtils.getLastFragment(annotatedsuggestion);
                if (annotationfragment != null)
                {
                    AnnotationType annotationtype = AnnotationType.forNumber(annotationfragment.annotationType_);
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
_L1:
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = annotatedsuggestion.query_.replace(" ...", "\u2026");
        }
        ((ContactTileView)view).setData(sourceAccount, ((CharSequence) (obj1)), ((CharSequence) (obj)), null);
        ((ContactTileView)view).getIcon().setContentDescription(((CharSequence) (obj)));
        return;
        if (annotationfragment.eventTime_ == null)
        {
            obj = EventTime.DEFAULT_INSTANCE;
        } else
        {
            obj = annotationfragment.eventTime_;
        }
        obj = TaskAssistUtils.getTimeLabel(context, ((EventTime) (obj)), true);
          goto _L1
    }
}
