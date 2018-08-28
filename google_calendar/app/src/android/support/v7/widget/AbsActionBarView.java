// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuPresenter, ActionMenuView

public class AbsActionBarView extends ViewGroup
{

    public ActionMenuPresenter mActionMenuPresenter;
    public int mContentHeight;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    public ActionMenuView mMenuView;
    public final Context mPopupContext;
    private final VisibilityAnimListener mVisAnimListener;
    public ViewPropertyAnimatorCompat mVisibilityAnim;

    AbsActionBarView(Context context)
    {
        this(context, null);
    }

    AbsActionBarView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    AbsActionBarView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mVisAnimListener = new VisibilityAnimListener();
        attributeset = new TypedValue();
        if (context.getTheme().resolveAttribute(0x7f01007d, attributeset, true) && ((TypedValue) (attributeset)).resourceId != 0)
        {
            mPopupContext = new ContextThemeWrapper(context, ((TypedValue) (attributeset)).resourceId);
            return;
        } else
        {
            mPopupContext = context;
            return;
        }
    }

    protected static int positionChild(View view, int i, int j, int k, boolean flag)
    {
        int l = view.getMeasuredWidth();
        int i1 = view.getMeasuredHeight();
        j = (k - i1) / 2 + j;
        if (flag)
        {
            view.layout(i - l, j, i, i1 + j);
        } else
        {
            view.layout(i, j, i + l, i1 + j);
        }
        i = l;
        if (flag)
        {
            i = -l;
        }
        return i;
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        configuration = getContext().obtainStyledAttributes(null, android.support.v7.appcompat.R.styleable.ActionBar, 0x7f01007e, 0);
        setContentHeight(configuration.getLayoutDimension(android.support.v7.appcompat.R.styleable.ActionBar_height, 0));
        configuration.recycle();
        if (mActionMenuPresenter != null)
        {
            configuration = mActionMenuPresenter;
            configuration.mMaxItems = (new ActionBarPolicy(((ActionMenuPresenter) (configuration)).mContext)).getMaxActionButtons();
            if (((ActionMenuPresenter) (configuration)).mMenu != null)
            {
                ((ActionMenuPresenter) (configuration)).mMenu.onItemsChanged(true);
            }
        }
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if (i == 9)
        {
            mEatingHover = false;
        }
        if (!mEatingHover)
        {
            boolean flag = super.onHoverEvent(motionevent);
            if (i == 9 && !flag)
            {
                mEatingHover = true;
            }
        }
        if (i == 10 || i == 3)
        {
            mEatingHover = false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if (i == 0)
        {
            mEatingTouch = false;
        }
        if (!mEatingTouch)
        {
            boolean flag = super.onTouchEvent(motionevent);
            if (i == 0 && !flag)
            {
                mEatingTouch = true;
            }
        }
        if (i == 1 || i == 3)
        {
            mEatingTouch = false;
        }
        return true;
    }

    public void setContentHeight(int i)
    {
        mContentHeight = i;
        requestLayout();
    }

    public void setVisibility(int i)
    {
        if (i != getVisibility())
        {
            if (mVisibilityAnim != null)
            {
                View view = (View)mVisibilityAnim.mView.get();
                if (view != null)
                {
                    view.animate().cancel();
                }
            }
            super.setVisibility(i);
        }
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long l)
    {
        if (mVisibilityAnim != null)
        {
            View view = (View)mVisibilityAnim.mView.get();
            if (view != null)
            {
                view.animate().cancel();
            }
        }
        if (i == 0)
        {
            if (getVisibility() != 0)
            {
                setAlpha(0.0F);
            }
            ViewPropertyAnimatorCompat viewpropertyanimatorcompat = ViewCompat.animate(this);
            Object obj = (View)viewpropertyanimatorcompat.mView.get();
            if (obj != null)
            {
                ((View) (obj)).animate().alpha(1.0F);
            }
            obj = (View)viewpropertyanimatorcompat.mView.get();
            if (obj != null)
            {
                ((View) (obj)).animate().setDuration(l);
            }
            obj = mVisAnimListener;
            ((VisibilityAnimListener) (obj))._fld0.mVisibilityAnim = viewpropertyanimatorcompat;
            obj.mFinalVisibility = i;
            viewpropertyanimatorcompat.setListener(((ViewPropertyAnimatorListener) (obj)));
            return viewpropertyanimatorcompat;
        }
        ViewPropertyAnimatorCompat viewpropertyanimatorcompat1 = ViewCompat.animate(this);
        Object obj1 = (View)viewpropertyanimatorcompat1.mView.get();
        if (obj1 != null)
        {
            ((View) (obj1)).animate().alpha(0.0F);
        }
        obj1 = (View)viewpropertyanimatorcompat1.mView.get();
        if (obj1 != null)
        {
            ((View) (obj1)).animate().setDuration(l);
        }
        obj1 = mVisAnimListener;
        ((VisibilityAnimListener) (obj1))._fld0.mVisibilityAnim = viewpropertyanimatorcompat1;
        obj1.mFinalVisibility = i;
        viewpropertyanimatorcompat1.setListener(((ViewPropertyAnimatorListener) (obj1)));
        return viewpropertyanimatorcompat1;
    }



    private class VisibilityAnimListener
        implements ViewPropertyAnimatorListener
    {

        private boolean mCanceled;
        public int mFinalVisibility;
        public final AbsActionBarView this$0;

        public final void onAnimationCancel(View view)
        {
            mCanceled = true;
        }

        public final void onAnimationEnd(View view)
        {
            if (mCanceled)
            {
                return;
            } else
            {
                mVisibilityAnim = null;
                setVisibility(mFinalVisibility);
                return;
            }
        }

        public final void onAnimationStart(View view)
        {
            setVisibility(0);
            mCanceled = false;
        }

        protected VisibilityAnimListener()
        {
            this$0 = AbsActionBarView.this;
            super();
            mCanceled = false;
        }
    }

}
