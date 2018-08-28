// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.Context;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            OnLocationSelectedListener

final class SuggestionLocationViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
{

    private final OnLocationSelectedListener listener;
    public Suggestion suggestion;
    public final TextTileView view;

    private SuggestionLocationViewHolder(TextTileView texttileview, OnLocationSelectedListener onlocationselectedlistener)
    {
        super(texttileview);
        view = texttileview;
        listener = onlocationselectedlistener;
    }

    public static SuggestionLocationViewHolder create(Context context, OnLocationSelectedListener onlocationselectedlistener)
    {
        context = new TextTileView(context);
        context.treatAsButton(true).setIconDrawable(0x7f02022c).indentContent();
        onlocationselectedlistener = new SuggestionLocationViewHolder(context, onlocationselectedlistener);
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final SuggestionLocationViewHolder arg$1;

            public final void onClick(View view1)
            {
                SuggestionLocationViewHolder.lambda$create$0$SuggestionLocationViewHolder$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFDPINEOBGD4NN6PB7DLIMST1FDHNM6OBKD5NMSBR6ELM6OSR3E9IMARHFADQMEPR5EDQ6IRRE9HNM6OBKD5NMSLJ9CLRKGRRCCHIN4EQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMAAM0(arg$1);
            }

            .Lambda._cls0()
            {
                arg$1 = SuggestionLocationViewHolder.this;
            }
        }

        context.setOnClickListener(onlocationselectedlistener. new .Lambda._cls0());
        return onlocationselectedlistener;
    }

    static final void lambda$create$0$SuggestionLocationViewHolder$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFDPINEOBGD4NN6PB7DLIMST1FDHNM6OBKD5NMSBR6ELM6OSR3E9IMARHFADQMEPR5EDQ6IRRE9HNM6OBKD5NMSLJ9CLRKGRRCCHIN4EQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMAAM0(SuggestionLocationViewHolder suggestionlocationviewholder)
    {
        suggestionlocationviewholder.listener.onSuggestionSelected(suggestionlocationviewholder.suggestion);
    }
}
