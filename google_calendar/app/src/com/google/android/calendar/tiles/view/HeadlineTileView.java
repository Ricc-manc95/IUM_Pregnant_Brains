// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.tiles.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.calendar.material.Material;

// Referenced classes of package com.google.android.calendar.tiles.view:
//            TileView

public class HeadlineTileView extends TileView
{

    public TextView text;

    public HeadlineTileView(Context context)
    {
        super(context);
    }

    public HeadlineTileView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected final void applyAttributes(TypedArray typedarray)
    {
        super.applyAttributes(typedarray);
        int i = R.styleable.TileView_tile_text;
        String s1 = typedarray.getNonResourceString(i);
        String s = s1;
        if (s1 == null)
        {
            s = typedarray.getString(i);
        }
        text.setText(s);
    }

    protected final View createContentView(LayoutInflater layoutinflater)
    {
        return layoutinflater.inflate(0x7f050171, this, false);
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        text = (TextView)view;
        TextView textview = text;
        if (Material.robotoMedium != null)
        {
            view = Material.robotoMedium;
        } else
        {
            view = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = view;
        }
        textview.setTypeface(view);
        setImportantForAccessibility(1);
        setFocusableInTouchMode(true);
        setFocusable(true);
    }
}
