// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class ActionBarDrawerToggle
    implements android.support.v4.widget.DrawerLayout.DrawerListener
{

    public final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    public boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mDrawerSlideAnimationEnabled;
    private final int mOpenDrawerContentDescRes;
    public DrawerArrowDrawable mSlider;
    private boolean mWarnedForDisplayHomeAsUp;

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerlayout, int i, int j)
    {
        this(activity, null, drawerlayout, null, i, j);
    }

    private ActionBarDrawerToggle(Activity activity, Toolbar toolbar, DrawerLayout drawerlayout, DrawerArrowDrawable drawerarrowdrawable, int i, int j)
    {
        mDrawerSlideAnimationEnabled = true;
        mDrawerIndicatorEnabled = true;
        mWarnedForDisplayHomeAsUp = false;
        if (activity instanceof DelegateProvider)
        {
            mActivityImpl = ((DelegateProvider)activity).getDrawerToggleDelegate();
        } else
        {
            mActivityImpl = new FrameworkActionBarDelegate(activity);
        }
        mDrawerLayout = drawerlayout;
        mOpenDrawerContentDescRes = i;
        mCloseDrawerContentDescRes = j;
        mSlider = new DrawerArrowDrawable(mActivityImpl.getActionBarThemedContext());
        mActivityImpl.getThemeUpIndicator();
    }

    private final void setPosition(float f)
    {
        if (f != 1.0F) goto _L2; else goto _L1
_L1:
        DrawerArrowDrawable drawerarrowdrawable = mSlider;
        if (!drawerarrowdrawable.mVerticalMirror)
        {
            drawerarrowdrawable.mVerticalMirror = true;
            drawerarrowdrawable.invalidateSelf();
        }
_L4:
        mSlider.setProgress(f);
        return;
_L2:
        if (f == 0.0F)
        {
            DrawerArrowDrawable drawerarrowdrawable1 = mSlider;
            if (drawerarrowdrawable1.mVerticalMirror)
            {
                drawerarrowdrawable1.mVerticalMirror = false;
                drawerarrowdrawable1.invalidateSelf();
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onDrawerClosed(View view)
    {
        setPosition(0.0F);
        if (mDrawerIndicatorEnabled)
        {
            int i = mOpenDrawerContentDescRes;
            mActivityImpl.setActionBarDescription(i);
        }
    }

    public void onDrawerOpened(View view)
    {
        setPosition(1.0F);
        if (mDrawerIndicatorEnabled)
        {
            int i = mCloseDrawerContentDescRes;
            mActivityImpl.setActionBarDescription(i);
        }
    }

    public void onDrawerSlide$51662RJ4E9NMIP1FEPKMATPFAPKMATPR8OKLC___0(float f)
    {
        if (mDrawerSlideAnimationEnabled)
        {
            setPosition(Math.min(1.0F, Math.max(0.0F, f)));
            return;
        } else
        {
            setPosition(0.0F);
            return;
        }
    }

    public final void onDrawerStateChanged$514IILG_0()
    {
    }

    public final void syncState()
    {
        boolean flag1 = false;
        DrawerLayout drawerlayout = mDrawerLayout;
        View view = drawerlayout.findDrawerWithGravity(0x800003);
        boolean flag;
        if (view != null)
        {
            flag = drawerlayout.isDrawerOpen(view);
        } else
        {
            flag = false;
        }
        if (flag)
        {
            setPosition(1.0F);
        } else
        {
            setPosition(0.0F);
        }
        if (mDrawerIndicatorEnabled)
        {
            DrawerArrowDrawable drawerarrowdrawable = mSlider;
            DrawerLayout drawerlayout1 = mDrawerLayout;
            View view1 = drawerlayout1.findDrawerWithGravity(0x800003);
            flag = flag1;
            if (view1 != null)
            {
                flag = drawerlayout1.isDrawerOpen(view1);
            }
            int i;
            if (flag)
            {
                i = mCloseDrawerContentDescRes;
            } else
            {
                i = mOpenDrawerContentDescRes;
            }
            if (!mWarnedForDisplayHomeAsUp && !mActivityImpl.isNavigationVisible())
            {
                Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
                mWarnedForDisplayHomeAsUp = true;
            }
            mActivityImpl.setActionBarUpIndicator(drawerarrowdrawable, i);
        }
    }

    public final void toggle()
    {
        int i = mDrawerLayout.getDrawerLockMode(0x800003);
        DrawerLayout drawerlayout = mDrawerLayout;
        View view = drawerlayout.findDrawerWithGravity(0x800003);
        boolean flag;
        if (view != null)
        {
            flag = drawerlayout.isDrawerVisible(view);
        } else
        {
            flag = false;
        }
        if (flag && i != 2)
        {
            mDrawerLayout.closeDrawer(0x800003);
        } else
        if (i != 1)
        {
            mDrawerLayout.openDrawer(0x800003);
            return;
        }
    }

    private class DelegateProvider
    {

        public abstract Delegate getDrawerToggleDelegate();
    }


    private class Delegate
    {

        public abstract Context getActionBarThemedContext();

        public abstract Drawable getThemeUpIndicator();

        public abstract boolean isNavigationVisible();

        public abstract void setActionBarDescription(int i);

        public abstract void setActionBarUpIndicator(Drawable drawable, int i);
    }


    private class FrameworkActionBarDelegate
        implements Delegate
    {

        private final Activity mActivity;

        public final Context getActionBarThemedContext()
        {
            ActionBar actionbar = mActivity.getActionBar();
            if (actionbar != null)
            {
                return actionbar.getThemedContext();
            } else
            {
                return mActivity;
            }
        }

        public final Drawable getThemeUpIndicator()
        {
            Object obj = mActivity.getActionBar();
            Drawable drawable;
            if (obj != null)
            {
                obj = ((ActionBar) (obj)).getThemedContext();
            } else
            {
                obj = mActivity;
            }
            obj = ((Context) (obj)).obtainStyledAttributes(null, new int[] {
                0x101030b
            }, 0x10102ce, 0);
            drawable = ((TypedArray) (obj)).getDrawable(0);
            ((TypedArray) (obj)).recycle();
            return drawable;
        }

        public final boolean isNavigationVisible()
        {
            ActionBar actionbar = mActivity.getActionBar();
            return actionbar != null && (actionbar.getDisplayOptions() & 4) != 0;
        }

        public final void setActionBarDescription(int i)
        {
            ActionBar actionbar = mActivity.getActionBar();
            if (actionbar != null)
            {
                actionbar.setHomeActionContentDescription(i);
            }
        }

        public final void setActionBarUpIndicator(Drawable drawable, int i)
        {
            ActionBar actionbar = mActivity.getActionBar();
            if (actionbar != null)
            {
                actionbar.setHomeAsUpIndicator(drawable);
                actionbar.setHomeActionContentDescription(i);
            }
        }

        FrameworkActionBarDelegate(Activity activity)
        {
            mActivity = activity;
        }
    }

}
