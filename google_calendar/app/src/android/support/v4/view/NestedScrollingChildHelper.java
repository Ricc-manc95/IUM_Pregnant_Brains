// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

// Referenced classes of package android.support.v4.view:
//            ViewParentCompat, NestedScrollingParent2

public final class NestedScrollingChildHelper
{

    public boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParentNonTouch;
    private ViewParent mNestedScrollingParentTouch;
    private int mTempNestedScrollConsumed[];
    public final View mView;

    public NestedScrollingChildHelper(View view)
    {
        mView = view;
    }

    public final boolean dispatchNestedFling(float f, float f1, boolean flag)
    {
        boolean flag2 = false;
        boolean flag1 = flag2;
        if (mIsNestedScrollingEnabled)
        {
            ViewParent viewparent = getNestedScrollingParentForType(0);
            flag1 = flag2;
            if (viewparent != null)
            {
                flag1 = ViewParentCompat.onNestedFling(viewparent, mView, f, f1, flag);
            }
        }
        return flag1;
    }

    public final boolean dispatchNestedPreFling(float f, float f1)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (mIsNestedScrollingEnabled)
        {
            ViewParent viewparent = getNestedScrollingParentForType(0);
            flag = flag1;
            if (viewparent != null)
            {
                flag = ViewParentCompat.onNestedPreFling(viewparent, mView, f, f1);
            }
        }
        return flag;
    }

    public final boolean dispatchNestedPreScroll(int i, int j, int ai[], int ai1[], int k)
    {
        if (mIsNestedScrollingEnabled)
        {
            ViewParent viewparent = getNestedScrollingParentForType(k);
            if (viewparent == null)
            {
                return false;
            }
            if (i != 0 || j != 0)
            {
                View view;
                int l;
                int i1;
                if (ai1 != null)
                {
                    mView.getLocationInWindow(ai1);
                    i1 = ai1[0];
                    l = ai1[1];
                } else
                {
                    l = 0;
                    i1 = 0;
                }
                if (ai == null)
                {
                    if (mTempNestedScrollConsumed == null)
                    {
                        mTempNestedScrollConsumed = new int[2];
                    }
                    ai = mTempNestedScrollConsumed;
                }
                ai[0] = 0;
                ai[1] = 0;
                view = mView;
                if (viewparent instanceof NestedScrollingParent2)
                {
                    ((NestedScrollingParent2)viewparent).onNestedPreScroll(view, i, j, ai, k);
                } else
                if (k == 0)
                {
                    try
                    {
                        viewparent.onNestedPreScroll(view, i, j, ai);
                    }
                    catch (AbstractMethodError abstractmethoderror)
                    {
                        Log.e("ViewParentCompat", (new StringBuilder("ViewParent ")).append(viewparent).append(" does not implement interface method onNestedPreScroll").toString(), abstractmethoderror);
                    }
                }
                if (ai1 != null)
                {
                    mView.getLocationInWindow(ai1);
                    ai1[0] = ai1[0] - i1;
                    ai1[1] = ai1[1] - l;
                }
                return ai[0] != 0 || ai[1] != 0;
            }
            if (ai1 != null)
            {
                ai1[0] = 0;
                ai1[1] = 0;
            }
        }
        return false;
    }

    public final boolean dispatchNestedScroll(int i, int j, int k, int l, int ai[], int i1)
    {
        ViewParent viewparent;
        if (!mIsNestedScrollingEnabled)
        {
            break MISSING_BLOCK_LABEL_208;
        }
        viewparent = getNestedScrollingParentForType(i1);
        if (viewparent == null)
        {
            return false;
        }
        if (i == 0 && j == 0 && k == 0 && l == 0) goto _L2; else goto _L1
_L1:
        View view;
        AbstractMethodError abstractmethoderror;
        int j1;
        int k1;
        if (ai != null)
        {
            mView.getLocationInWindow(ai);
            k1 = ai[0];
            j1 = ai[1];
        } else
        {
            j1 = 0;
            k1 = 0;
        }
        view = mView;
        if (!(viewparent instanceof NestedScrollingParent2)) goto _L4; else goto _L3
_L3:
        ((NestedScrollingParent2)viewparent).onNestedScroll(view, i, j, k, l, i1);
_L5:
        if (ai != null)
        {
            mView.getLocationInWindow(ai);
            ai[0] = ai[0] - k1;
            ai[1] = ai[1] - j1;
        }
        return true;
_L4:
        if (i1 == 0)
        {
            try
            {
                viewparent.onNestedScroll(view, i, j, k, l);
            }
            // Misplaced declaration of an exception variable
            catch (AbstractMethodError abstractmethoderror)
            {
                Log.e("ViewParentCompat", (new StringBuilder("ViewParent ")).append(viewparent).append(" does not implement interface method onNestedScroll").toString(), abstractmethoderror);
            }
        }
        if (true) goto _L5; else goto _L2
_L2:
        if (ai != null)
        {
            ai[0] = 0;
            ai[1] = 0;
        }
        return false;
    }

    public final ViewParent getNestedScrollingParentForType(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return mNestedScrollingParentTouch;

        case 1: // '\001'
            return mNestedScrollingParentNonTouch;
        }
    }

    public final void setNestedScrollingParentForType(int i, ViewParent viewparent)
    {
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            mNestedScrollingParentTouch = viewparent;
            return;

        case 1: // '\001'
            mNestedScrollingParentNonTouch = viewparent;
            break;
        }
    }

    public final boolean startNestedScroll(int i, int j)
    {
        ViewParent viewparent;
        Object obj;
        boolean flag;
        if (getNestedScrollingParentForType(j) != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return true;
        }
        if (!mIsNestedScrollingEnabled)
        {
            break MISSING_BLOCK_LABEL_175;
        }
        viewparent = mView.getParent();
        obj = mView;
_L8:
        if (viewparent == null) goto _L2; else goto _L1
_L1:
        if (!ViewParentCompat.onStartNestedScroll(viewparent, ((View) (obj)), mView, i, j)) goto _L4; else goto _L3
_L3:
        View view;
        setNestedScrollingParentForType(j, viewparent);
        view = mView;
        if (!(viewparent instanceof NestedScrollingParent2)) goto _L6; else goto _L5
_L5:
        ((NestedScrollingParent2)viewparent).onNestedScrollAccepted(((View) (obj)), view, i, j);
_L7:
        return true;
_L6:
        if (j == 0)
        {
            try
            {
                viewparent.onNestedScrollAccepted(((View) (obj)), view, i);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                Log.e("ViewParentCompat", (new StringBuilder("ViewParent ")).append(viewparent).append(" does not implement interface method onNestedScrollAccepted").toString(), ((Throwable) (obj)));
            }
        }
        if (true) goto _L7; else goto _L4
_L4:
        if (viewparent instanceof View)
        {
            obj = (View)viewparent;
        }
        viewparent = viewparent.getParent();
          goto _L8
_L2:
        return false;
    }

    public final void stopNestedScroll(int i)
    {
        ViewParent viewparent = getNestedScrollingParentForType(i);
        if (viewparent == null) goto _L2; else goto _L1
_L1:
        View view = mView;
        if (!(viewparent instanceof NestedScrollingParent2)) goto _L4; else goto _L3
_L3:
        ((NestedScrollingParent2)viewparent).onStopNestedScroll(view, i);
_L6:
        setNestedScrollingParentForType(i, null);
_L2:
        return;
_L4:
        if (i == 0)
        {
            try
            {
                viewparent.onStopNestedScroll(view);
            }
            catch (AbstractMethodError abstractmethoderror)
            {
                Log.e("ViewParentCompat", (new StringBuilder("ViewParent ")).append(viewparent).append(" does not implement interface method onStopNestedScroll").toString(), abstractmethoderror);
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }
}
