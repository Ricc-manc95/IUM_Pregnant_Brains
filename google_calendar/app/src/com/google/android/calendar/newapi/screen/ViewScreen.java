// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.newapi.commandbar.BottomBar;
import com.google.android.calendar.newapi.model.ViewScreenModel;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.newapi.segment.title.TitleViewSegment;
import com.google.android.calendar.swipeclosing.DraggableScrollView;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.SystemWindowInsetApplier;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            SegmentViews, HeaderElevator

public class ViewScreen extends FrameLayout
    implements android.view.View.OnClickListener, com.google.android.calendar.swipeclosing.DraggableScrollView.ActionListener, com.google.android.calendar.swipeclosing.DraggableScrollView.SingleClickListener
{
    public static interface Callback
    {

        public abstract void onCancelClicked();

        public abstract void onEditClicked();
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/screen/ViewScreen);
    public Callback callback;
    public BottomBar commandBar;
    public final View contentView = findViewById(0x7f1002a9);
    public View editButton;
    public Animator editButtonPulseAnimator;
    public android.view.View.OnLayoutChangeListener headerLayoutChangeListener;
    public SystemWindowInsetApplier insetApplier;
    public ViewScreenModel model;
    public View overflowMenuView;
    public final DraggableScrollView scrollView = (DraggableScrollView)findViewById(0x7f100269);
    public final SegmentViews segmentViews = new SegmentViews();

    public ViewScreen(Context context)
    {
        super(context);
        insetApplier = new SystemWindowInsetApplier();
        LayoutInflater.from(context).inflate(0x7f0500f4, this, true);
        editButton = findViewById(0x7f1002ae);
        findViewById(0x7f100266).setOnClickListener(this);
        Object obj = getResources();
        View view = findViewById(0x7f1002aa);
        context = scrollView;
        obj = new HeaderElevator(((Resources) (obj)), view);
        context.getViewTreeObserver().addOnScrollChangedListener(new HeaderElevator..Lambda._cls1(((HeaderElevator) (obj)), context));
        if (Utils.getConfigBool(getContext(), 0x7f0c0014))
        {
            setSystemUiVisibility(1280);
            insetApplier.addView(findViewById(0x7f100266), 2, 1);
            ViewCompat.setOnApplyWindowInsetsListener(this, insetApplier);
        }
    }

    public final void adjustExtraCommandBarPadding()
    {
        int i = 0;
        boolean flag;
        if (commandBar != null && commandBar.getVisibility() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            BottomBar bottombar = commandBar;
            i = bottombar.getMeasuredHeight() - (int)((View)bottombar.getParent()).getTranslationY();
        }
        scrollView.setPaddingRelative(scrollView.getPaddingStart(), scrollView.getPaddingTop(), scrollView.getPaddingEnd(), i);
    }

    public final void onActionTriggered()
    {
        callback.onCancelClicked();
    }

    public void onClick(View view)
    {
        while (callback == null || 0x7f100266 != view.getId()) 
        {
            return;
        }
        callback.onCancelClicked();
    }

    public final void onSingleClick(View view)
    {
        TitleViewSegment titleviewsegment = (TitleViewSegment)segmentViews.headerView;
        if (titleviewsegment != null)
        {
            titleviewsegment.onClick(view);
        }
    }

    final void updateEditButton()
    {
        Object obj = findViewById(0x7f1002ad);
        int i;
        if (model.isEditable())
        {
            i = 0;
        } else
        {
            i = 4;
        }
        ((View) (obj)).setVisibility(i);
        if (!model.isEditable())
        {
            return;
        }
        obj = (FrameLayout)findViewById(0x7f1002ae);
        Object obj1;
        ShapeDrawable shapedrawable;
        if (model.hasImage(getContext()))
        {
            i = model.getColor(getContext());
        } else
        {
            i = ColorUtils.adjustLightness(model.getColor(getContext()), 1.15F);
        }
        obj1 = new PorterDuffColorFilter(i, android.graphics.PorterDuff.Mode.SRC_ATOP);
        shapedrawable = new ShapeDrawable(new OvalShape());
        i = getResources().getDimensionPixelOffset(0x7f0e0266);
        shapedrawable.setIntrinsicHeight(i);
        shapedrawable.setIntrinsicWidth(i);
        shapedrawable.setColorFilter(((android.graphics.ColorFilter) (obj1)));
        obj1 = new LayerDrawable(new ShapeDrawable[] {
            shapedrawable
        });
        ((FrameLayout) (obj)).setBackground(new RippleDrawable(ColorStateList.valueOf(ColorUtils.adjustLightness(model.getColor(getContext()), 0.85F)), ((android.graphics.drawable.Drawable) (obj1)), null));
        ((FrameLayout) (obj)).setOnClickListener(new _cls1());
    }

    protected final void updateSegmentViews()
    {
        if (segmentViews != null)
        {
            updateEditButton();
            Object obj = (TitleViewSegment)segmentViews.headerView;
            if (obj != null)
            {
                ((TitleViewSegment) (obj)).updateUi();
                ((TitleViewSegment) (obj)).requestLayout();
            }
            obj = (ArrayList)segmentViews.bodyViews;
            int k = ((ArrayList) (obj)).size();
            int i = 0;
            while (i < k) 
            {
                Object obj1 = ((ArrayList) (obj)).get(i);
                int j = i + 1;
                obj1 = (View)obj1;
                i = j;
                if (obj1 instanceof ViewSegment)
                {
                    ((ViewSegment)obj1).updateUi();
                    i = j;
                }
            }
        }
    }


    private class _cls1 extends PreventDoubleClick
    {

        private final ViewScreen this$0;

        public final void onFirstClick$51662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0()
        {
            if (callback != null)
            {
                callback.onEditClicked();
                ViewScreen viewscreen = ViewScreen.this;
                Animator animator = viewscreen.editButtonPulseAnimator;
                View view = viewscreen.findViewById(0x7f1002ad);
                if (animator != null)
                {
                    animator.cancel();
                    view.setScaleX(1.0F);
                    view.setScaleY(1.0F);
                    view.setRotation(0.0F);
                }
                viewscreen.editButtonPulseAnimator = null;
            }
        }

        _cls1()
        {
            this$0 = ViewScreen.this;
            super();
        }
    }

}
