// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.suggestion;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestion;
import com.google.personalization.assist.annotate.api.RenderingData;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.suggestion:
//            IconSuggestionViewHolder, ContactSuggestionViewHolder, UsageHintSuggestionViewHolder, SuggestionFormatter

public class SuggestionViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
    implements android.view.View.OnClickListener
{

    public final Context context;
    private Listener listener;
    private AnnotatedSuggestion suggestion;
    public final View view;

    SuggestionViewHolder(View view1, Listener listener1)
    {
        super(view1);
        context = view1.getContext();
        view = view1;
        view.setOnClickListener(this);
        listener = listener1;
    }

    public static SuggestionViewHolder create(ViewGroup viewgroup, Listener listener1, int i, boolean flag, String s)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder(29)).append("Unknown view type ").append(i).toString());

        case 0: // '\0'
            return new IconSuggestionViewHolder(viewgroup, listener1);

        case 1: // '\001'
            return new ContactSuggestionViewHolder(viewgroup, s, listener1);

        case 2: // '\002'
            break;
        }
        if (flag)
        {
            return new UsageHintSuggestionViewHolder(viewgroup, listener1);
        } else
        {
            return new IconSuggestionViewHolder(viewgroup, listener1);
        }
    }

    static int getViewType(AnnotatedSuggestion annotatedsuggestion)
    {
        boolean flag1 = true;
        if (SuggestionFormatter.isContact(annotatedsuggestion))
        {
            return 1;
        }
        com.google.personalization.assist.annotate.api.RenderingData.AssistanceType assistancetype;
        boolean flag;
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
        flag = flag1;
        if (annotatedsuggestion != com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.CONNECTOR_CONTACT)
        {
            if (annotatedsuggestion == com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.CONNECTOR_LOCATION)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
        }
        return !flag ? 0 : 2;
    }

    public void bind(AnnotatedSuggestion annotatedsuggestion)
    {
        suggestion = annotatedsuggestion;
    }

    public void onClick(View view1)
    {
        listener.onSuggestionChosen(suggestion);
    }

    public void topmostPosition(boolean flag)
    {
    }

    private class Listener
    {

        public abstract void onSuggestionChosen(AnnotatedSuggestion annotatedsuggestion);
    }

}
