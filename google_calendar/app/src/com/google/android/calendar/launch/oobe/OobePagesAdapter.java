// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.calendar.material.Material;
import java.util.ArrayList;

final class OobePagesAdapter extends PagerAdapter
{

    public static final int SPLASH_LOGO = 0x7f0201b9;
    public final int backgroundColors[];
    private final CharSequence bodyTexts[];
    public final Callback callback;
    private final Context context;
    private final CharSequence disclaimerTexts[];
    private final ArrayList itemsToAdd = new ArrayList();
    private final ArrayList itemsToRemove = new ArrayList();
    private final ArrayList recycledViews = new ArrayList();
    public final int splashBackgroundColor;
    public final int topResources[] = new int[4];
    private final CharSequence topTexts[];

    OobePagesAdapter(Context context1, Callback callback1)
    {
        context = context1;
        callback = callback1;
        context1 = context1.getResources();
        callback1 = context1.obtainTypedArray(0x7f0b0041);
        for (int i = 0; i < 4; i++)
        {
            topResources[i] = callback1.getResourceId(i, 0);
        }

        topTexts = context1.getTextArray(0x7f0b003f);
        bodyTexts = context1.getTextArray(0x7f0b0040);
        disclaimerTexts = context1.getTextArray(0x7f0b003e);
        splashBackgroundColor = context1.getColor(0x7f0d016c);
        backgroundColors = (new int[] {
            context1.getColor(0x7f0d016c), context1.getColor(0x7f0d016a), context1.getColor(0x7f0d0167), context1.getColor(0x7f0d016b)
        });
    }

    private final View getTextsView(int i)
    {
        if (i == -1 || i == 4)
        {
            return null;
        }
        View view = LayoutInflater.from(context).inflate(0x7f050108, null);
        TextView textview = (TextView)view.findViewById(0x7f1002a2);
        Object obj = (TextView)view.findViewById(0x7f1002c6);
        Object obj1 = (TextView)view.findViewById(0x7f1002c5);
        TextView textview1 = (TextView)view.findViewById(0x7f1002c7);
        Button button;
        int j;
        if (i == 0)
        {
            ((TextView) (obj1)).setVisibility(8);
            j = 17;
        } else
        {
            ((TextView) (obj)).setVisibility(8);
            obj = obj1;
            j = 0x800013;
        }
        ((TextView) (obj)).setVisibility(0);
        textview.setText(topTexts[i]);
        ((TextView) (obj)).setText(bodyTexts[i]);
        if (disclaimerTexts[i].length() == 0)
        {
            textview1.setVisibility(8);
        } else
        {
            textview1.setVisibility(0);
            textview1.setText(disclaimerTexts[i]);
        }
        button = (Button)view.findViewById(0x7f1002c8);
        if (button != null)
        {
            if (i == 3)
            {
                class .Lambda._cls0
                    implements android.view.View.OnClickListener
                {

                    private final OobePagesAdapter arg$1;

                    public final void onClick(View view1)
                    {
                        arg$1.callback.onComplete();
                    }

            .Lambda._cls0()
            {
                arg$1 = OobePagesAdapter.this;
            }

                    private class Callback
                    {

                        public abstract void onComplete();
                    }

                }

                button.setOnClickListener(new .Lambda._cls0());
                if (Material.robotoMedium != null)
                {
                    obj1 = Material.robotoMedium;
                } else
                {
                    obj1 = Typeface.create("sans-serif-medium", 0);
                    Material.robotoMedium = ((Typeface) (obj1));
                }
                button.setTypeface(((Typeface) (obj1)));
                button.setVisibility(0);
            } else
            {
                button.setVisibility(8);
            }
        }
        textview.setGravity(j);
        ((TextView) (obj)).setGravity(j);
        textview1.setGravity(j);
        obj = new android.widget.FrameLayout.LayoutParams(-2, -2);
        obj.gravity = j;
        view.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
        return view;
    }

    public final void destroyItem(ViewGroup viewgroup, int i, Object obj)
    {
        viewgroup = (View)obj;
        recycledViews.add(viewgroup);
        itemsToRemove.add(viewgroup);
    }

    public final void finishUpdate(ViewGroup viewgroup)
    {
        boolean flag = false;
        ArrayList arraylist = (ArrayList)itemsToRemove;
        int k = arraylist.size();
        for (int i = 0; i < k;)
        {
            Object obj = arraylist.get(i);
            i++;
            viewgroup.removeView((View)obj);
        }

        arraylist = (ArrayList)itemsToAdd;
        k = arraylist.size();
        for (int j = ((flag) ? 1 : 0); j < k;)
        {
            Object obj1 = arraylist.get(j);
            j++;
            viewgroup.addView((View)obj1);
        }

    }

    public final int getCount()
    {
        return 4;
    }

    public final Object instantiateItem(ViewGroup viewgroup, int i)
    {
        if (recycledViews.size() > 0)
        {
            viewgroup = (View)recycledViews.remove(0);
        } else
        {
            viewgroup = LayoutInflater.from(context).inflate(0x7f05010a, null);
            viewgroup.setBackgroundResource(0x106000d);
        }
        setViews(viewgroup, i);
        itemsToAdd.add(viewgroup);
        return viewgroup;
    }

    public final boolean isViewFromObject(View view, Object obj)
    {
        return view == obj;
    }

    final void setViews(View view, int i)
    {
        FrameLayout framelayout;
        final View imageFrame;
        View view2;
        FrameLayout framelayout1;
        imageFrame = view.findViewById(0x7f100157);
        view2 = view.findViewById(0x7f1002c9);
        framelayout = (FrameLayout)view.findViewById(0x7f100158);
        framelayout1 = (FrameLayout)view.findViewById(0x7f1002ca);
        if (framelayout1 == null) goto _L2; else goto _L1
_L1:
        View view1;
        if (i == -1 || i == 0)
        {
            view1 = LayoutInflater.from(context).inflate(0x7f050039, null);
            Object obj1 = view1.findViewById(0x7f1000d1);
            View view3;
            int j;
            if (i == -1)
            {
                j = SPLASH_LOGO;
            } else
            if (i == 4)
            {
                j = 0x106000d;
            } else
            {
                j = topResources[i];
            }
            if (j == 0)
            {
                ((View) (obj1)).setVisibility(8);
            } else
            {
                ((View) (obj1)).setBackgroundResource(j);
            }
            obj1 = (FrameLayout)view1.findViewById(0x7f100158);
            view3 = getTextsView(i);
            if (view3 != null)
            {
                ((FrameLayout) (obj1)).addView(view3);
            }
        } else
        {
            view1 = null;
        }
        if (view1 == null) goto _L4; else goto _L3
_L3:
        framelayout1.setVisibility(0);
        imageFrame.setVisibility(8);
        framelayout.setVisibility(8);
        framelayout1.addView(view1);
_L6:
        return;
_L4:
        framelayout1.setVisibility(8);
_L2:
        final float imageHeightRatio;
        Object obj;
        int k;
        boolean flag;
        if (i == -1)
        {
            k = SPLASH_LOGO;
        } else
        if (i == 4)
        {
            k = 0x106000d;
        } else
        {
            k = topResources[i];
        }
        obj = imageFrame.getTag();
        if (obj instanceof String)
        {
            imageHeightRatio = Float.parseFloat((String)obj);
        } else
        {
            imageHeightRatio = 0.0F;
        }
        if (imageHeightRatio != 0.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (k == 0)
        {
            if (view2 == null)
            {
                imageFrame.setVisibility(8);
                flag = false;
            } else
            {
                imageFrame.setVisibility(0);
                view2.setVisibility(8);
            }
        } else
        {
            if (view2 == null)
            {
                imageFrame.setVisibility(0);
            } else
            {
                imageFrame.setVisibility(0);
                view2.setVisibility(0);
            }
            view.findViewById(0x7f1000d1).setBackgroundResource(k);
        }
        if (flag)
        {
            imageFrame.getViewTreeObserver().addOnPreDrawListener(new _cls1());
        }
        framelayout.setVisibility(0);
        framelayout.removeAllViews();
        view = getTextsView(i);
        if (view != null)
        {
            framelayout.addView(view);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void startUpdate(ViewGroup viewgroup)
    {
        itemsToAdd.clear();
        itemsToRemove.clear();
    }


    private class _cls1
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        private final View val$imageFrame;
        private final float val$imageHeightRatio;

        public final boolean onPreDraw()
        {
            ViewTreeObserver viewtreeobserver = imageFrame.getViewTreeObserver();
            if (!viewtreeobserver.isAlive())
            {
                return true;
            }
            int i = imageFrame.getWidth();
            if (i == 0)
            {
                imageFrame.requestLayout();
                return false;
            } else
            {
                float f = imageHeightRatio;
                android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, (int)((float)i * f));
                layoutparams.gravity = 16;
                imageFrame.setLayoutParams(layoutparams);
                viewtreeobserver.removeOnPreDrawListener(this);
                return false;
            }
        }

        _cls1()
        {
            imageFrame = view;
            imageHeightRatio = f;
            super();
        }
    }

}
