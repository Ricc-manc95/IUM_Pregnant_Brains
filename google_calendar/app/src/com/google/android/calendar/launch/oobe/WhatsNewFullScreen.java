// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.utils.AccessibilityUtils;
import com.google.android.calendar.utils.SystemWindowInsetApplier;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import com.google.android.calendar.utils.viewpager.LayoutDirectionAwareViewPager;

// Referenced classes of package com.google.android.calendar.launch.oobe:
//            OobePagesAdapter

public class WhatsNewFullScreen extends CalendarSupportActivity
    implements android.support.v4.view.ViewPager.OnPageChangeListener
{
    final class PaginationView extends View
    {

        private int currentPage;
        private final int dotColor;
        public final int dotMargin;
        public final int dotRadius;
        public ShapeDrawable dots[];
        public float fadeValue;
        private int lastPage;
        private final WhatsNewFullScreen this$0;

        protected final void onDraw(Canvas canvas)
        {
            if (dots != null)
            {
                int j = 0;
                while (j < dots.length) 
                {
                    ShapeDrawable shapedrawable = dots[j];
                    int i;
                    if (j == lastPage)
                    {
                        i = 255 - (int)(fadeValue * 204F);
                    } else
                    if (j == currentPage)
                    {
                        i = (int)(fadeValue * 204F) + 51;
                    } else
                    {
                        i = 51;
                    }
                    shapedrawable.getPaint().setColor(dotColor);
                    shapedrawable.getPaint().setAlpha(i);
                    shapedrawable.draw(canvas);
                    j++;
                }
            }
        }

        public final void setCurrentPage(int i)
        {
            clearAnimation();
            int j = i;
            if (RtlUtils.isLayoutDirectionRtl(getContext()))
            {
                j = 3 - i;
            }
            lastPage = currentPage;
            currentPage = j;
            ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
                1.0F - fadeValue, 1.0F
            });
            class _cls2
                implements android.animation.ValueAnimator.AnimatorUpdateListener
            {

                private final PaginationView this$1;

                public final void onAnimationUpdate(ValueAnimator valueanimator1)
                {
                    fadeValue = ((Float)valueanimator1.getAnimatedValue()).floatValue();
                    invalidate();
                }

                _cls2()
                {
                    this$1 = PaginationView.this;
                    super();
                }
            }

            valueanimator.addUpdateListener(new _cls2());
            valueanimator.start();
        }

        PaginationView(Context context)
        {
            this$0 = WhatsNewFullScreen.this;
            super(context);
            context = context.getResources();
            dotRadius = context.getDimensionPixelSize(0x7f0e0304);
            dotMargin = context.getDimensionPixelSize(0x7f0e0303);
            dotColor = context.getColor(0x7f0d0169);
            lastPage = -1;
            currentPage = 0;
            fadeValue = 1.0F;
            class _cls1
                implements android.view.ViewTreeObserver.OnPreDrawListener
            {

                private final PaginationView this$1;

                public final boolean onPreDraw()
                {
                    PaginationView paginationview = PaginationView.this;
                    int i = paginationview.getWidth();
                    if (i == 0)
                    {
                        i = 0;
                    } else
                    {
                        int j = paginationview.getHeight() / 2;
                        paginationview.dots = new ShapeDrawable[4];
                        int k = i / 2;
                        int l = paginationview.dotMargin / 2;
                        int i1 = paginationview.dotRadius;
                        int j1 = paginationview.dotRadius;
                        int k1 = paginationview.dotMargin;
                        for (i = 0; i < 4; i++)
                        {
                            ShapeDrawable shapedrawable = new ShapeDrawable(new OvalShape());
                            int l1 = (paginationview.dotRadius * 2 + paginationview.dotMargin) * i + (k - l - i1 - (j1 * 2 + k1) * 1);
                            shapedrawable.setBounds(l1 - paginationview.dotRadius, j - paginationview.dotRadius, l1 + paginationview.dotRadius, paginationview.dotRadius + j);
                            paginationview.dots[i] = shapedrawable;
                        }

                        i = 1;
                    }
                    if (i == 0)
                    {
                        requestLayout();
                        return false;
                    } else
                    {
                        getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                }

                _cls1()
                {
                    this$1 = PaginationView.this;
                    super();
                }
            }

            getViewTreeObserver().addOnPreDrawListener(new _cls1());
        }
    }


    public OobePagesAdapter adapter;
    public ViewGroup basePage;
    public Button doneButton;
    private int fadeDuration;
    public FrameLayout footer;
    private FrameLayout mainFrame;
    public PaginationView paginationView;
    public ImageView rightArrow;
    public LayoutDirectionAwareViewPager viewPager;

    public WhatsNewFullScreen()
    {
    }

    final ViewPropertyAnimator alphaFade(View view, float f)
    {
        view.clearAnimation();
        float f1 = view.getAlpha();
        int i;
        if (f1 == f)
        {
            i = 0;
        } else
        {
            i = (int)(Math.abs(f - f1) * (float)fadeDuration);
        }
        return view.animate().alpha(f).setDuration(i);
    }

    final void finishWhatsNew()
    {
        OobePagesAdapter oobepagesadapter = adapter;
        if (viewPager.getCurrentItem() == 3)
        {
            setResult(-1);
        }
        viewPager.setEnabled(false);
        finish();
    }

    final void logPage(int i)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackView(this, (new StringBuilder(15)).append("oobe").append(i + 1).toString());
            return;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        class .Lambda._cls0
            implements OobePagesAdapter.Callback
        {

            private final WhatsNewFullScreen arg$1;

            public final void onComplete()
            {
                arg$1.finishWhatsNew();
            }

            .Lambda._cls0()
            {
                arg$1 = WhatsNewFullScreen.this;
            }
        }

        adapter = new OobePagesAdapter(this, new .Lambda._cls0());
        if (!getResources().getBoolean(0x7f0c0016))
        {
            setRequestedOrientation(1);
        }
        setContentView(0x7f050186);
        mainFrame = (FrameLayout)findViewById(0x7f10035d);
        basePage = (ViewGroup)findViewById(0x7f100395);
        footer = (FrameLayout)findViewById(0x7f1001a9);
        rightArrow = (ImageView)findViewById(0x7f100397);
        Object obj = findViewById(0x7f100294);
        if (obj instanceof Button)
        {
            doneButton = (Button)obj;
        }
        viewPager = (LayoutDirectionAwareViewPager)findViewById(0x7f1001a1);
        paginationView = new PaginationView(this);
        obj = new android.widget.LinearLayout.LayoutParams(-1, -2);
        paginationView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
        footer.addView(paginationView);
        paginationView.setAlpha(0.0F);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setEnabled(false);
        basePage.setBackgroundColor(adapter.splashBackgroundColor);
        adapter.setViews(basePage, -1);
        fadeDuration = getResources().getInteger(0x10e0001);
        if (doneButton != null)
        {
            doneButton.setEnabled(false);
            doneButton.setOnClickListener(new _cls1());
            Button button = doneButton;
            Object obj1;
            if (Material.robotoMedium != null)
            {
                obj1 = Material.robotoMedium;
            } else
            {
                obj1 = Typeface.create("sans-serif-medium", 0);
                Material.robotoMedium = ((Typeface) (obj1));
            }
            button.setTypeface(((Typeface) (obj1)));
        }
        footer.setEnabled(false);
        findViewById(0x7f100396).setOnClickListener(new _cls2());
        class .Lambda._cls1
            implements Runnable
        {

            private final WhatsNewFullScreen arg$1;

            public final void run()
            {
                WhatsNewFullScreen whatsnewfullscreen = arg$1;
                whatsnewfullscreen.alphaFade(whatsnewfullscreen.basePage.findViewById(0x7f100158), 0.0F);
                OobePagesAdapter oobepagesadapter = whatsnewfullscreen.adapter;
                final boolean sameTop;
                if (OobePagesAdapter.SPLASH_LOGO == whatsnewfullscreen.adapter.topResources[0])
                {
                    sameTop = true;
                } else
                {
                    sameTop = false;
                }
                if (!sameTop)
                {
                    whatsnewfullscreen.alphaFade(whatsnewfullscreen.basePage.findViewById(0x7f1000d1), 0.0F);
                    View view = whatsnewfullscreen.basePage.findViewById(0x7f1002ca);
                    if (view != null)
                    {
                        whatsnewfullscreen.alphaFade(view, 0.0F);
                    }
                }
                if (whatsnewfullscreen.doneButton != null)
                {
                    whatsnewfullscreen.doneButton.setAlpha(0.0F);
                    whatsnewfullscreen.doneButton.setEnabled(false);
                }
                whatsnewfullscreen.paginationView.setAlpha(0.0F);
                whatsnewfullscreen.alphaFade(whatsnewfullscreen.paginationView, 1.0F);
                whatsnewfullscreen.alphaFade(whatsnewfullscreen.rightArrow, 1.0F);
                whatsnewfullscreen.viewPager.setAlpha(0.0F);
                whatsnewfullscreen.viewPager.setVisibility(0);
                whatsnewfullscreen.alphaFade(whatsnewfullscreen.viewPager, 1.0F).setListener(whatsnewfullscreen. new _cls3());
                if (!RtlUtils.isLayoutDirectionRtl(whatsnewfullscreen))
                {
                    whatsnewfullscreen.logPage(0);
                }
            }

            .Lambda._cls1()
            {
                arg$1 = WhatsNewFullScreen.this;
            }

            private class _cls3 extends AnimatorListenerAdapter
            {

                private final WhatsNewFullScreen this$0;
                private final boolean val$sameTop;

                public final void onAnimationEnd(Animator animator)
                {
                    super.onAnimationEnd(animator);
                    if (sameTop)
                    {
                        basePage.findViewById(0x7f1000d1).setAlpha(0.0F);
                        animator = basePage.findViewById(0x7f1002ca);
                        if (animator != null)
                        {
                            animator.setAlpha(0.0F);
                        }
                    }
                    viewPager.setEnabled(true);
                    footer.setEnabled(true);
                    animator = WhatsNewFullScreen.this;
                    if (AccessibilityUtils.isAccessibilityEnabled(animator.getApplicationContext()))
                    {
                        ((WhatsNewFullScreen) (animator)).viewPager.post(animator. new _cls5());
                    }
                }

                _cls3()
                {
                    this$0 = WhatsNewFullScreen.this;
                    sameTop = flag;
                    super();
                }

                private class _cls5
                    implements Runnable
                {

                    private final WhatsNewFullScreen this$0;

                    public final void run()
                    {
                        if (viewPager.hasFocus())
                        {
                            viewPager.clearFocus();
                        }
                        viewPager.requestFocus();
                    }

                    _cls5()
                    {
                        this$0 = WhatsNewFullScreen.this;
                        super();
                    }
                }

            }

        }

        if (bundle != null && bundle.getInt("key_current_page") >= 0)
        {
            paginationView.setAlpha(1.0F);
            rightArrow.setAlpha(1.0F);
            viewPager.setAlpha(1.0F);
            if (doneButton != null)
            {
                doneButton.setAlpha(0.0F);
                doneButton.setEnabled(false);
            }
            basePage.findViewById(0x7f100158).setAlpha(0.0F);
            basePage.findViewById(0x7f1000d1).setAlpha(0.0F);
            bundle = basePage.findViewById(0x7f1002ca);
            if (bundle != null)
            {
                bundle.setAlpha(0.0F);
            }
            viewPager.setVisibility(0);
            viewPager.setEnabled(true);
            footer.setEnabled(true);
        } else
        {
            viewPager.postDelayed(new .Lambda._cls1(), 1000L);
        }
        bundle = getWindow();
        obj1 = bundle.getDecorView();
        ((View) (obj1)).setSystemUiVisibility(((View) (obj1)).getSystemUiVisibility() | 0x400 | 0x100);
        obj1 = new SystemWindowInsetApplier(true);
        ((SystemWindowInsetApplier) (obj1)).addView(basePage, 2, 2);
        ((SystemWindowInsetApplier) (obj1)).addView(viewPager, 2, 2);
        ViewCompat.setOnApplyWindowInsetsListener(mainFrame, ((android.support.v4.view.OnApplyWindowInsetsListener) (obj1)));
        StatusbarAnimatorCompat.createInstance(bundle).tryApplyLightStatusbar(true, getResources().getColor(0x7f0d031f), getResources().getColor(0x7f0d0320), 0);
    }

    public final void onPageScrollStateChanged(int i)
    {
    }

    public final void onPageScrolled(int i, float f, int j)
    {
        OobePagesAdapter oobepagesadapter;
        boolean flag;
        flag = false;
        Object obj;
        if (RtlUtils.isLayoutDirectionRtl(this))
        {
            j = i - 1;
            f = -f;
        } else
        {
            f = 1.0F - f;
            j = i;
        }
        obj = mainFrame;
        oobepagesadapter = adapter;
        i = j + 1;
        if (i == -1)
        {
            i = oobepagesadapter.splashBackgroundColor;
        } else
        if (i >= 4)
        {
            i = 0;
        } else
        {
            i = oobepagesadapter.backgroundColors[i];
        }
        ((FrameLayout) (obj)).setBackgroundColor(i);
        obj = basePage;
        oobepagesadapter = adapter;
        if (j != -1) goto _L2; else goto _L1
_L1:
        i = oobepagesadapter.splashBackgroundColor;
_L4:
        ((ViewGroup) (obj)).setBackgroundColor(i);
        i = (int)(f * 255F);
        basePage.getBackground().setAlpha(i);
        return;
_L2:
        i = ((flag) ? 1 : 0);
        if (j < 4)
        {
            i = oobepagesadapter.backgroundColors[j];
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onPageSelected(int i)
    {
        logPage(i);
        paginationView.setCurrentPage(i);
        OobePagesAdapter oobepagesadapter = adapter;
        if (i != 3) goto _L2; else goto _L1
_L1:
        alphaFade(rightArrow, 0.0F);
        if (doneButton != null)
        {
            alphaFade(footer, 0.0F);
            alphaFade(doneButton, 1.0F).setListener(new _cls4());
        }
_L4:
        if (AccessibilityUtils.isAccessibilityEnabled(getApplicationContext()))
        {
            viewPager.post(new _cls5());
        }
        return;
_L2:
        alphaFade(rightArrow, 1.0F);
        if (doneButton != null)
        {
            alphaFade(footer, 1.0F);
            alphaFade(doneButton, 0.0F);
            doneButton.setEnabled(false);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        bundle.putInt("key_current_page", viewPager.getCurrentItem());
        super.onSaveInstanceState(bundle);
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final WhatsNewFullScreen this$0;

        public final void onClick(View view)
        {
            finishWhatsNew();
        }

        _cls1()
        {
            this$0 = WhatsNewFullScreen.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final WhatsNewFullScreen this$0;

        public final void onClick(View view)
        {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }

        _cls2()
        {
            this$0 = WhatsNewFullScreen.this;
            super();
        }
    }


    private class _cls4 extends AnimatorListenerAdapter
    {

        private final WhatsNewFullScreen this$0;

        public final void onAnimationEnd(Animator animator)
        {
            super.onAnimationEnd(animator);
            doneButton.setEnabled(true);
        }

        _cls4()
        {
            this$0 = WhatsNewFullScreen.this;
            super();
        }
    }

}
