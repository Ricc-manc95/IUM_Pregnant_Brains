// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.suggestion;

import android.content.Context;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestion;
import com.google.personalization.assist.annotate.api.RenderingData;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.suggestion:
//            SuggestionViewHolder, ArrowDrawable, SuggestionFormatter

final class UsageHintSuggestionViewHolder extends SuggestionViewHolder
    implements android.view.ViewTreeObserver.OnPreDrawListener
{

    private final View hint;
    private final TextView hintDescription;
    private boolean hintEnabled;
    private final TextView hintTitle;
    private final TextTileView tile;

    UsageHintSuggestionViewHolder(ViewGroup viewgroup, SuggestionViewHolder.Listener listener)
    {
        super(LayoutInflater.from(viewgroup.getContext()).inflate(0x7f050137, viewgroup, false), listener);
        tile = (TextTileView)view.findViewById(0x7f100175);
        hintTitle = (TextView)view.findViewById(0x7f100312);
        hintDescription = (TextView)view.findViewById(0x7f100313);
        hint = view.findViewById(0x7f100311);
        hint.setBackground(new ArrowDrawable(viewgroup.getContext()));
    }

    public final void bind(AnnotatedSuggestion annotatedsuggestion)
    {
        android.content.res.Resources resources;
        super.bind(annotatedsuggestion);
        tile.setPrimaryText(new CharSequence[] {
            SuggestionFormatter.formatTitle(context, annotatedsuggestion)
        });
        Object obj = SuggestionFormatter.formatDescription(context, annotatedsuggestion);
        hintEnabled = TextUtils.isEmpty(((CharSequence) (obj)));
        tile.setSecondaryText(new CharSequence[] {
            obj
        });
        tile.setIconDrawable(SuggestionFormatter.getIcon(annotatedsuggestion));
        obj = context.getString(SuggestionFormatter.getIconContentDescription(annotatedsuggestion));
        tile.getIcon().setContentDescription(((CharSequence) (obj)));
        hint.getViewTreeObserver().removeOnPreDrawListener(this);
        hint.animate().cancel();
        hint.setVisibility(8);
        hint.setTranslationX(0.0F);
        resources = view.getResources();
        if (annotatedsuggestion.renderingData_ == null)
        {
            annotatedsuggestion = RenderingData.DEFAULT_INSTANCE;
        } else
        {
            annotatedsuggestion = annotatedsuggestion.renderingData_;
        }
        obj = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.forNumber(((RenderingData) (annotatedsuggestion)).assistanceType_);
        annotatedsuggestion = ((AnnotatedSuggestion) (obj));
        if (obj == null)
        {
            annotatedsuggestion = com.google.personalization.assist.annotate.api.RenderingData.AssistanceType.NONE;
        }
        annotatedsuggestion.ordinal();
        JVM INSTR tableswitch 24 25: default 196
    //                   24 245
    //                   25 255;
           goto _L1 _L2 _L3
_L1:
        annotatedsuggestion = new Pair("", "");
_L5:
        hintTitle.setText((CharSequence)((Pair) (annotatedsuggestion)).first);
        hintDescription.setText((CharSequence)((Pair) (annotatedsuggestion)).second);
        return;
_L2:
        annotatedsuggestion = SuggestionFormatter.extractStringPair(resources, 0x7f0b0022);
        continue; /* Loop/switch isn't completed */
_L3:
        annotatedsuggestion = SuggestionFormatter.extractStringPair(resources, 0x7f0b0023);
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final boolean onPreDraw()
    {
        hint.getViewTreeObserver().removeOnPreDrawListener(this);
        View view = hint;
        int j = view.getWidth();
        int i = j;
        if (RtlUtils.isLayoutDirectionRtl(view.getContext()))
        {
            i = -j;
        }
        view.setTranslationX(i);
        view.animate().setDuration(225L).translationX(0.0F).setInterpolator(new LinearOutSlowInInterpolator()).start();
        return true;
    }

    public final void topmostPosition(boolean flag)
    {
        if (hintEnabled)
        {
            hint.setVisibility(0);
            if (flag)
            {
                hint.getViewTreeObserver().addOnPreDrawListener(this);
            }
        }
    }
}
