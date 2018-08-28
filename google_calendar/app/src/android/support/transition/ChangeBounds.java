// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

// Referenced classes of package android.support.transition:
//            Transition, RectEvaluator, TransitionValues, ViewUtils, 
//            ViewUtilsBase, PathMotion, ViewGroupUtilsApi18

public final class ChangeBounds extends Transition
{

    private static final Property BOTTOM_RIGHT_ONLY_PROPERTY = new _cls4(android/graphics/PointF, "bottomRight");
    private static final Property BOTTOM_RIGHT_PROPERTY = new _cls3(android/graphics/PointF, "bottomRight");
    private static final Property DRAWABLE_ORIGIN_PROPERTY = new _cls1(android/graphics/PointF, "boundsOrigin");
    private static final Property POSITION_PROPERTY = new _cls6(android/graphics/PointF, "position");
    private static final Property TOP_LEFT_ONLY_PROPERTY = new _cls5(android/graphics/PointF, "topLeft");
    private static final Property TOP_LEFT_PROPERTY = new _cls2(android/graphics/PointF, "topLeft");
    private static RectEvaluator sRectEvaluator = new RectEvaluator();
    private static final String sTransitionProperties[] = {
        "android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"
    };
    private boolean mReparent;
    private boolean mResizeClip;
    private int mTempLocation[];

    public ChangeBounds()
    {
        mTempLocation = new int[2];
        mResizeClip = false;
        mReparent = false;
    }

    private final void captureValues(TransitionValues transitionvalues)
    {
        View view = transitionvalues.view;
        if (ViewCompat.isLaidOut(view) || view.getWidth() != 0 || view.getHeight() != 0)
        {
            transitionvalues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            transitionvalues.values.put("android:changeBounds:parent", transitionvalues.view.getParent());
        }
    }

    public final void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public final void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public final Animator createAnimator(ViewGroup viewgroup, final TransitionValues parent, TransitionValues transitionvalues)
    {
        if (parent != null && transitionvalues != null) goto _L2; else goto _L1
_L1:
        parent = null;
_L4:
        return parent;
_L2:
        Object obj;
        int i;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
label0:
        {
            obj = parent.values;
            viewgroup = transitionvalues.values;
            obj = (ViewGroup)((Map) (obj)).get("android:changeBounds:parent");
            viewgroup = (ViewGroup)viewgroup.get("android:changeBounds:parent");
            if (obj == null || viewgroup == null)
            {
                return null;
            }
            obj = transitionvalues.view;
            viewgroup = (Rect)parent.values.get("android:changeBounds:bounds");
            Rect rect = (Rect)transitionvalues.values.get("android:changeBounds:bounds");
            k = ((Rect) (viewgroup)).left;
            l = rect.left;
            i1 = ((Rect) (viewgroup)).top;
            j1 = rect.top;
            k1 = ((Rect) (viewgroup)).right;
            l1 = rect.right;
            i2 = ((Rect) (viewgroup)).bottom;
            j2 = rect.bottom;
            k2 = k1 - k;
            l2 = i2 - i1;
            i3 = l1 - l;
            j3 = j2 - j1;
            viewgroup = (Rect)parent.values.get("android:changeBounds:clip");
            parent = (Rect)transitionvalues.values.get("android:changeBounds:clip");
            boolean flag = false;
            int j = 0;
            if (k2 == 0 || l2 == 0)
            {
                i = ((flag) ? 1 : 0);
                if (i3 == 0)
                {
                    break label0;
                }
                i = ((flag) ? 1 : 0);
                if (j3 == 0)
                {
                    break label0;
                }
            }
            if (k != l || i1 != j1)
            {
                j = 1;
            }
            if (k1 == l1)
            {
                i = j;
                if (i2 == j2)
                {
                    break label0;
                }
            }
            i = j + 1;
        }
        if (viewgroup != null && !viewgroup.equals(parent) || viewgroup == null && parent != null)
        {
            i++;
        }
        if (i > 0)
        {
            ViewUtils.IMPL.setLeftTopRightBottom(((View) (obj)), k, i1, k1, i2);
            if (i == 2)
            {
                if (k2 == i3 && l2 == j3)
                {
                    viewgroup = super.mPathMotion.getPath(k, i1, l, j1);
                    viewgroup = ObjectAnimator.ofObject(obj, POSITION_PROPERTY, null, viewgroup);
                } else
                {
                    parent = new ViewBounds(((View) (obj)));
                    viewgroup = super.mPathMotion.getPath(k, i1, l, j1);
                    transitionvalues = ObjectAnimator.ofObject(parent, TOP_LEFT_PROPERTY, null, viewgroup);
                    viewgroup = super.mPathMotion.getPath(k1, i2, l1, j2);
                    ObjectAnimator objectanimator = ObjectAnimator.ofObject(parent, BOTTOM_RIGHT_PROPERTY, null, viewgroup);
                    viewgroup = new AnimatorSet();
                    viewgroup.playTogether(new Animator[] {
                        transitionvalues, objectanimator
                    });
                    viewgroup.addListener(new _cls7());
                }
            } else
            if (k != l || i1 != j1)
            {
                viewgroup = super.mPathMotion.getPath(k, i1, l, j1);
                viewgroup = ObjectAnimator.ofObject(obj, TOP_LEFT_ONLY_PROPERTY, null, viewgroup);
            } else
            {
                viewgroup = super.mPathMotion.getPath(k1, i2, l1, j2);
                viewgroup = ObjectAnimator.ofObject(obj, BOTTOM_RIGHT_ONLY_PROPERTY, null, viewgroup);
            }
            parent = viewgroup;
            if (((View) (obj)).getParent() instanceof ViewGroup)
            {
                parent = (ViewGroup)((View) (obj)).getParent();
                ViewGroupUtilsApi18.suppressLayout(parent, true);
                addListener(new _cls9());
                return viewgroup;
            }
        } else
        {
            return null;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }


    private class _cls9 extends TransitionListenerAdapter
    {

        private boolean mCanceled;
        private final ViewGroup val$parent;

        public final void onTransitionEnd(Transition transition)
        {
            ViewGroupUtilsApi18.suppressLayout(parent, false);
            transition.removeListener(this);
        }

        public final void onTransitionPause$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
            ViewGroupUtilsApi18.suppressLayout(parent, false);
        }

        public final void onTransitionResume$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
            ViewGroupUtilsApi18.suppressLayout(parent, true);
        }

        _cls9()
        {
            parent = viewgroup;
            super();
            mCanceled = false;
        }
    }


    private class ViewBounds
    {

        public int mBottom;
        public int mBottomRightCalls;
        public int mLeft;
        public int mRight;
        public int mTop;
        public int mTopLeftCalls;
        private View mView;

        final void setLeftTopRightBottom()
        {
            View view = mView;
            int i = mLeft;
            int j = mTop;
            int k = mRight;
            int l = mBottom;
            ViewUtils.IMPL.setLeftTopRightBottom(view, i, j, k, l);
            mTopLeftCalls = 0;
            mBottomRightCalls = 0;
        }

        ViewBounds(View view)
        {
            mView = view;
        }
    }


    private class _cls7 extends AnimatorListenerAdapter
    {

        private final ViewBounds val$viewBounds;

        _cls7()
        {
            viewBounds = viewbounds;
            super();
        }
    }


    private class _cls1 extends Property
    {

        private Rect mBounds;

        public final Object get(Object obj)
        {
            ((Drawable)obj).copyBounds(mBounds);
            return new PointF(mBounds.left, mBounds.top);
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (Drawable)obj;
            obj1 = (PointF)obj1;
            ((Drawable) (obj)).copyBounds(mBounds);
            mBounds.offsetTo(Math.round(((PointF) (obj1)).x), Math.round(((PointF) (obj1)).y));
            ((Drawable) (obj)).setBounds(mBounds);
        }

        _cls1(Class class1, String s)
        {
            super(class1, s);
            mBounds = new Rect();
        }
    }


    private class _cls2 extends Property
    {

        public final volatile Object get(Object obj)
        {
            return null;
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (ViewBounds)obj;
            obj1 = (PointF)obj1;
            obj.mLeft = Math.round(((PointF) (obj1)).x);
            obj.mTop = Math.round(((PointF) (obj1)).y);
            obj.mTopLeftCalls = ((ViewBounds) (obj)).mTopLeftCalls + 1;
            if (((ViewBounds) (obj)).mTopLeftCalls == ((ViewBounds) (obj)).mBottomRightCalls)
            {
                ((ViewBounds) (obj)).setLeftTopRightBottom();
            }
        }

        _cls2(Class class1, String s)
        {
            super(class1, s);
        }
    }


    private class _cls3 extends Property
    {

        public final volatile Object get(Object obj)
        {
            return null;
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (ViewBounds)obj;
            obj1 = (PointF)obj1;
            obj.mRight = Math.round(((PointF) (obj1)).x);
            obj.mBottom = Math.round(((PointF) (obj1)).y);
            obj.mBottomRightCalls = ((ViewBounds) (obj)).mBottomRightCalls + 1;
            if (((ViewBounds) (obj)).mTopLeftCalls == ((ViewBounds) (obj)).mBottomRightCalls)
            {
                ((ViewBounds) (obj)).setLeftTopRightBottom();
            }
        }

        _cls3(Class class1, String s)
        {
            super(class1, s);
        }
    }


    private class _cls4 extends Property
    {

        public final volatile Object get(Object obj)
        {
            return null;
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (View)obj;
            obj1 = (PointF)obj1;
            int i = ((View) (obj)).getLeft();
            int j = ((View) (obj)).getTop();
            int k = Math.round(((PointF) (obj1)).x);
            int l = Math.round(((PointF) (obj1)).y);
            ViewUtils.IMPL.setLeftTopRightBottom(((View) (obj)), i, j, k, l);
        }

        _cls4(Class class1, String s)
        {
            super(class1, s);
        }
    }


    private class _cls5 extends Property
    {

        public final volatile Object get(Object obj)
        {
            return null;
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (View)obj;
            obj1 = (PointF)obj1;
            int i = Math.round(((PointF) (obj1)).x);
            int j = Math.round(((PointF) (obj1)).y);
            int k = ((View) (obj)).getRight();
            int l = ((View) (obj)).getBottom();
            ViewUtils.IMPL.setLeftTopRightBottom(((View) (obj)), i, j, k, l);
        }

        _cls5(Class class1, String s)
        {
            super(class1, s);
        }
    }


    private class _cls6 extends Property
    {

        public final volatile Object get(Object obj)
        {
            return null;
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (View)obj;
            obj1 = (PointF)obj1;
            int i = Math.round(((PointF) (obj1)).x);
            int j = Math.round(((PointF) (obj1)).y);
            int k = ((View) (obj)).getWidth();
            int l = ((View) (obj)).getHeight();
            ViewUtils.IMPL.setLeftTopRightBottom(((View) (obj)), i, j, i + k, j + l);
        }

        _cls6(Class class1, String s)
        {
            super(class1, s);
        }
    }

}
