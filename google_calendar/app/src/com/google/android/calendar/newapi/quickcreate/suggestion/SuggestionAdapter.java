// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.suggestion;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestion;
import java.util.Collections;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.suggestion:
//            SuggestionViewHolder

public final class SuggestionAdapter extends android.support.v7.widget.RecyclerView.Adapter
{

    private final boolean hintsEnabled;
    public SuggestionViewHolder.Listener listener;
    public RecyclerView recyclerView;
    private final String sourceAccount;
    public List suggestions;
    public boolean topmostPositionCalled;

    public SuggestionAdapter(boolean flag, String s)
    {
        suggestions = Collections.emptyList();
        topmostPositionCalled = false;
        hintsEnabled = flag;
        sourceAccount = s;
    }

    public final int getItemCount()
    {
        return suggestions.size();
    }

    public final int getItemViewType(int i)
    {
        return SuggestionViewHolder.getViewType((AnnotatedSuggestion)suggestions.get(i));
    }

    public final void onAttachedToRecyclerView(RecyclerView recyclerview)
    {
        recyclerView = recyclerview;
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        viewholder = (SuggestionViewHolder)viewholder;
        viewholder.bind((AnnotatedSuggestion)suggestions.get(i));
        if (i == 0)
        {
            boolean flag;
            if (!topmostPositionCalled)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            viewholder.topmostPosition(flag);
            topmostPositionCalled = true;
        }
    }

    public final android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        return SuggestionViewHolder.create(viewgroup, listener, i, hintsEnabled, sourceAccount);
    }

    public final void onDetachedFromRecyclerView(RecyclerView recyclerview)
    {
        recyclerView = null;
    }
}
