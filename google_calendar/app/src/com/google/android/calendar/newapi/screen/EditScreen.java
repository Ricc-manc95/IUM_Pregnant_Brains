// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.calendar.Utils;
import com.google.android.calendar.utils.SystemWindowInsetApplier;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            HeaderElevator

public final class EditScreen extends FrameLayout
    implements android.view.View.OnClickListener
{

    public final View cancelButton = findViewById(0x7f100266);
    public Listener listener;
    public final RecyclerView quickCreateList = (RecyclerView)findViewById(0x7f10026a);
    public final TextView saveButton = (TextView)findViewById(0x7f100267);
    public final ViewGroup segmentContainer = (ViewGroup)findViewById(0x7f100231);
    private final View segmentsScroll = findViewById(0x7f100269);

    public EditScreen(Context context)
    {
        super(context);
        inflate(context, 0x7f0500cd, this);
        setVisibility(8);
        cancelButton.requestFocus();
        saveButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        context = findViewById(0x7f100264);
        Object obj1 = getResources();
        Object obj = (ScrollView)findViewById(0x7f100269);
        obj1 = new HeaderElevator(((android.content.res.Resources) (obj1)), context);
        ((ScrollView) (obj)).getViewTreeObserver().addOnScrollChangedListener(new HeaderElevator..Lambda._cls0(((HeaderElevator) (obj1)), ((ScrollView) (obj))));
        obj1 = getResources();
        obj = quickCreateList;
        context = new HeaderElevator._cls1(new HeaderElevator(((android.content.res.Resources) (obj1)), context));
        if (((RecyclerView) (obj)).mScrollListeners == null)
        {
            obj.mScrollListeners = new ArrayList();
        }
        ((RecyclerView) (obj)).mScrollListeners.add(context);
        class .Lambda._cls0
            implements android.view.ViewTreeObserver.OnGlobalLayoutListener
        {

            private final EditScreen arg$1;

            public final void onGlobalLayout()
            {
                EditScreen editscreen = arg$1;
                int k = editscreen.segmentContainer.getChildCount();
                int j = 0;
                int i = 0;
                while (j < k) 
                {
                    View view = editscreen.segmentContainer.getChildAt(j);
                    if (view instanceof EditSegmentDivider)
                    {
                        if (view != null)
                        {
                            if (i != 0)
                            {
                                i = 0;
                            } else
                            {
                                i = 8;
                            }
                            view.setVisibility(i);
                        }
                        i = 0;
                    } else
                    {
                        boolean flag;
                        if (view.getVisibility() == 0)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        i = flag | i;
                    }
                    j++;
                }
            }

            .Lambda._cls0()
            {
                arg$1 = EditScreen.this;
            }
        }

        segmentContainer.getViewTreeObserver().addOnGlobalLayoutListener(new .Lambda._cls0());
        if (Utils.getConfigBool(getContext(), 0x7f0c0013))
        {
            context = new SystemWindowInsetApplier();
            setSystemUiVisibility(1280);
            context.addView(saveButton, 2, 1);
            context.addView(cancelButton, 2, 1);
            context.addView(findViewById(0x7f100269), 8, 1);
            context.addView(quickCreateList, 8, 1);
            context.addView(findViewById(0x7f10026b), 2, 1);
            ViewCompat.setOnApplyWindowInsetsListener(this, context);
        }
    }

    public final void onClick(View view)
    {
        if (listener == null) goto _L2; else goto _L1
_L1:
        if (0x7f100267 != view.getId()) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (quickCreateList.getVisibility() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L5
_L5:
        listener.onDismissQuickCreateClicked();
_L2:
        return;
_L6:
        listener.onSaveClicked();
        return;
_L4:
        if (0x7f100266 == view.getId())
        {
            listener.onCancelClicked();
            return;
        }
        if (true) goto _L2; else goto _L7
_L7:
    }

    public final void setQuickCreateVisible(boolean flag)
    {
        if (flag)
        {
            quickCreateList.setVisibility(0);
            segmentsScroll.setVisibility(4);
            saveButton.setText(0x7f13018f);
            return;
        } else
        {
            quickCreateList.setVisibility(8);
            segmentsScroll.setVisibility(0);
            saveButton.setText(0x7f130142);
            return;
        }
    }

    private class Listener
    {

        public abstract void onCancelClicked();

        public abstract void onDismissQuickCreateClicked();

        public abstract void onSaveClicked();
    }

}
