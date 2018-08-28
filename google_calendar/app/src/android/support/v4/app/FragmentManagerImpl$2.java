// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.view.ViewGroup;
import android.view.animation.Animation;

// Referenced classes of package android.support.v4.app:
//            FragmentManagerImpl, Fragment

final class tener extends imationListenerWrapper
{

    public final FragmentManagerImpl this$0;
    private final ViewGroup val$container;
    public final Fragment val$fragment;

    public final void onAnimationEnd(Animation animation)
    {
        super.onAnimationEnd(animation);
        class _cls1
            implements Runnable
        {

            private final FragmentManagerImpl._cls2 this$1;

            public final void run()
            {
                Object obj = fragment;
                if (((Fragment) (obj)).mAnimationInfo == null)
                {
                    obj = null;
                } else
                {
                    obj = ((Fragment) (obj)).mAnimationInfo.mAnimatingAway;
                }
                if (obj != null)
                {
                    obj = fragment;
                    if (((Fragment) (obj)).mAnimationInfo == null)
                    {
                        obj.mAnimationInfo = new Fragment.AnimationInfo();
                    }
                    ((Fragment) (obj)).mAnimationInfo.mAnimatingAway = null;
                    obj = this$0;
                    Fragment fragment1 = fragment;
                    Fragment fragment2 = fragment;
                    int i;
                    if (fragment2.mAnimationInfo == null)
                    {
                        i = 0;
                    } else
                    {
                        i = fragment2.mAnimationInfo.mStateAfterAnimating;
                    }
                    ((FragmentManagerImpl) (obj)).moveToState(fragment1, i, 0, 0, false);
                }
            }

            _cls1()
            {
                this$1 = FragmentManagerImpl._cls2.this;
                super();
            }
        }

        val$container.post(new _cls1());
    }

    tener(Fragment fragment1)
    {
        this$0 = final_fragmentmanagerimpl;
        val$container = ViewGroup.this;
        val$fragment = fragment1;
        super(final_tener);
    }
}
