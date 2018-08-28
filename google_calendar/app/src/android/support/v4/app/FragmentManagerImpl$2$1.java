// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;


// Referenced classes of package android.support.v4.app:
//            Fragment, FragmentManagerImpl

final class this._cls1
    implements Runnable
{

    private final ToState this$1;

    public final void run()
    {
        Object obj = this._cls1.this.fragment;
        if (((Fragment) (obj)).mAnimationInfo == null)
        {
            obj = null;
        } else
        {
            obj = ((Fragment) (obj)).mAnimationInfo.AnimatingAway;
        }
        if (obj != null)
        {
            obj = this._cls1.this.fragment;
            if (((Fragment) (obj)).mAnimationInfo == null)
            {
                obj.mAnimationInfo = new init>();
            }
            ((Fragment) (obj)).mAnimationInfo.AnimatingAway = null;
            obj = _fld0;
            Fragment fragment = this._cls1.this.fragment;
            Fragment fragment1 = this._cls1.this.fragment;
            int i;
            if (fragment1.mAnimationInfo == null)
            {
                i = 0;
            } else
            {
                i = fragment1.mAnimationInfo.StateAfterAnimating;
            }
            ((FragmentManagerImpl) (obj)).moveToState(fragment, i, 0, 0, false);
        }
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
