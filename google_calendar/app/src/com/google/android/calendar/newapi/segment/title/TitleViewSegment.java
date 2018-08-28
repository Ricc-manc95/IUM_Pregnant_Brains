// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.event.image.helper.ImageHelper;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.utils.SystemWindowInsetApplier;
import com.google.android.calendar.utils.animation.QuantumInterpolators;
import java.util.Map;

public class TitleViewSegment extends FrameLayout
    implements android.view.View.OnClickListener, ViewSegment
{

    private boolean attributeVisible;
    public TextView attributionText;
    public boolean hasAttribution;
    public ImageView headerView;
    public final ViewScreenModel model;
    public int windowTopInset;

    public TitleViewSegment(Context context, ViewScreenModel viewscreenmodel)
    {
        super(context);
        attributeVisible = false;
        hasAttribution = false;
        model = viewscreenmodel;
        inflate(context, 0x7f0500f1, this);
        headerView = new ImageView(getContext());
        attributionText = (TextView)findViewById(0x7f1002a3);
        addView(headerView, 0);
        updateHeaderSize();
        if (Utils.getConfigBool(getContext(), 0x7f0c0014))
        {
            context = new SystemWindowInsetApplier();
            context.addView(findViewById(0x7f1002a2), 2, 1);
            viewscreenmodel = headerView;
            _cls1 _lcls1 = new _cls1();
            if (viewscreenmodel == null)
            {
                throw new NullPointerException();
            }
            if (_lcls1 == null)
            {
                throw new NullPointerException();
            }
            class .Lambda._cls0
                implements android.view.View.OnTouchListener
            {

                private final TitleViewSegment arg$1;

                public final boolean onTouch(View view, MotionEvent motionevent)
                {
                    arg$1.findViewById(0x7f1002a4).onTouchEvent(motionevent);
                    return true;
                }

            .Lambda._cls0()
            {
                arg$1 = TitleViewSegment.this;
            }
            }

            if (((SystemWindowInsetApplier) (context)).customHandledViews.containsKey(viewscreenmodel))
            {
                LogUtils.w(SystemWindowInsetApplier.TAG, "Received addView for view %s, which was already present. Ignoring.", new Object[] {
                    viewscreenmodel
                });
            } else
            {
                ((SystemWindowInsetApplier) (context)).customHandledViews.put(viewscreenmodel, _lcls1);
            }
            ViewCompat.setOnApplyWindowInsetsListener(this, context);
        }
        setId(generateViewId());
        setFocusable(true);
        setImportantForAccessibility(1);
        setDescendantFocusability(0x20000);
        requestFocus();
        if (Utils.getConfigBool(getContext(), 0x7f0c0014))
        {
            findViewById(0x7f1002a2).setOnTouchListener(new .Lambda._cls0());
        }
    }

    private final void updateHeaderSize()
    {
        View view = findViewById(0x7f1002a2);
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
        Context context = getContext();
        int i;
        if (model.hasImage(context))
        {
            i = context.getResources().getDimensionPixelOffset(0x7f0e034b);
        } else
        {
            i = context.getResources().getDimensionPixelOffset(0x7f0e024d);
        }
        layoutparams.height = i;
        view.setLayoutParams(layoutparams);
        updateHeaderImageSize(headerView);
    }

    public void onClick(View view)
    {
        float f1 = 0.0F;
        if (hasAttribution)
        {
            view = (TextView)findViewById(0x7f100047);
            TextView textview = attributionText;
            boolean flag = attributeVisible;
            view.clearAnimation();
            textview.clearAnimation();
            int i = textview.getMeasuredHeight();
            Object obj = View.TRANSLATION_Y;
            float f;
            Object obj1;
            AnimatorSet animatorset;
            if (flag)
            {
                f = 0.0F;
            } else
            {
                f = -i;
            }
            view = ObjectAnimator.ofFloat(view, ((android.util.Property) (obj)), new float[] {
                f
            }).setDuration(225L);
            view.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            obj = View.ALPHA;
            if (flag)
            {
                f = f1;
            } else
            {
                f = 1.0F;
            }
            obj = ObjectAnimator.ofFloat(textview, ((android.util.Property) (obj)), new float[] {
                f
            }).setDuration(300L);
            ((ObjectAnimator) (obj)).setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            animatorset = new AnimatorSet();
            if (flag)
            {
                obj1 = obj;
            } else
            {
                obj1 = view;
            }
            if (!flag)
            {
                view = ((View) (obj));
            }
            animatorset.playSequentially(new Animator[] {
                obj1, view
            });
            animatorset.addListener(new HeaderAttributeUtils._cls1(flag, textview));
            animatorset.start();
            if (!attributeVisible)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            attributeVisible = flag;
        }
    }

    protected void updateHeaderImageSize(ImageView imageview)
    {
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)imageview.getLayoutParams();
        layoutparams.width = -1;
        Context context = getContext();
        int i;
        if (model.hasImage(context))
        {
            i = context.getResources().getDimensionPixelOffset(0x7f0e034b);
        } else
        {
            i = context.getResources().getDimensionPixelOffset(0x7f0e024d);
        }
        layoutparams.height = i + windowTopInset;
        imageview.setLayoutParams(layoutparams);
    }

    public final void updateImage()
    {
        boolean flag;
        if (model.hasImage(getContext()) && ImageHelper.shouldHaveImage(getResources()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            headerView.setImageDrawable(model.getBackgroundDrawable(getContext(), new _cls2()));
            return;
        } else
        {
            headerView.setImageDrawable(null);
            hasAttribution = false;
            attributionText.setVisibility(4);
            return;
        }
    }

    public void updateUi()
    {
        headerView.setBackgroundColor(model.getColor(getContext()));
        updateImage();
        Object obj1 = (TextView)findViewById(0x7f100047);
        Object obj = model.getTitle();
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = getContext();
            int i;
            if (model.hideDetails())
            {
                i = 0x7f1300e6;
            } else
            {
                i = 0x7f130358;
            }
            obj = ((Context) (obj)).getString(i);
        }
        ((TextView) (obj1)).setText(((CharSequence) (obj)));
        obj = (TextView)findViewById(0x7f100047);
        obj1 = model;
        getContext();
        ((TextView) (obj)).setContentDescription(((ViewScreenModel) (obj1)).getTitle());
        updateHeaderSize();
    }

    private class _cls1
        implements com.google.android.calendar.utils.SystemWindowInsetApplier.CustomInsetHandler
    {

        private final TitleViewSegment this$0;

        public final void onInsetsChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(View view, int i)
        {
            windowTopInset = i;
            updateHeaderImageSize((ImageView)view);
        }

        _cls1()
        {
            this$0 = TitleViewSegment.this;
            super();
        }
    }


    private class _cls2
        implements FutureCallback
    {

        private final TitleViewSegment this$0;

        public final void onFailure(Throwable throwable)
        {
            throwable = TitleViewSegment.this;
            throwable.hasAttribution = false;
            ((TitleViewSegment) (throwable)).attributionText.setVisibility(4);
        }

        public final void onSuccess(Object obj)
        {
            obj = (CharSequence)obj;
            if (!TextUtils.isEmpty(((CharSequence) (obj))))
            {
                hasAttribution = true;
                attributionText.setText(((CharSequence) (obj)));
                attributionText.setMovementMethod(LinkMovementMethod.getInstance());
                return;
            } else
            {
                obj = TitleViewSegment.this;
                obj.hasAttribution = false;
                ((TitleViewSegment) (obj)).attributionText.setVisibility(4);
                return;
            }
        }

        _cls2()
        {
            this$0 = TitleViewSegment.this;
            super();
        }
    }

}
