// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

// Referenced classes of package android.support.transition:
//            Transition, TransitionValues, ViewGroupOverlayApi18, ViewGroupOverlayImpl, 
//            TransitionUtils, ViewUtils

public class Visibility extends Transition
{

    private static final String sTransitionProperties[] = {
        "android:visibility:visibility", "android:visibility:parent"
    };
    public int mMode;

    public Visibility()
    {
        mMode = 3;
    }

    private static void captureValues(TransitionValues transitionvalues)
    {
        int i = transitionvalues.view.getVisibility();
        transitionvalues.values.put("android:visibility:visibility", Integer.valueOf(i));
        transitionvalues.values.put("android:visibility:parent", transitionvalues.view.getParent());
        int ai[] = new int[2];
        transitionvalues.view.getLocationOnScreen(ai);
        transitionvalues.values.put("android:visibility:screenLocation", ai);
    }

    private static VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        VisibilityInfo visibilityinfo;
        visibilityinfo = new VisibilityInfo();
        visibilityinfo.mVisibilityChange = false;
        visibilityinfo.mFadeIn = false;
        if (transitionvalues != null && transitionvalues.values.containsKey("android:visibility:visibility"))
        {
            visibilityinfo.mStartVisibility = ((Integer)transitionvalues.values.get("android:visibility:visibility")).intValue();
            visibilityinfo.mStartParent = (ViewGroup)transitionvalues.values.get("android:visibility:parent");
        } else
        {
            visibilityinfo.mStartVisibility = -1;
            visibilityinfo.mStartParent = null;
        }
        if (transitionvalues1 != null && transitionvalues1.values.containsKey("android:visibility:visibility"))
        {
            visibilityinfo.mEndVisibility = ((Integer)transitionvalues1.values.get("android:visibility:visibility")).intValue();
            visibilityinfo.mEndParent = (ViewGroup)transitionvalues1.values.get("android:visibility:parent");
        } else
        {
            visibilityinfo.mEndVisibility = -1;
            visibilityinfo.mEndParent = null;
        }
        if (transitionvalues == null || transitionvalues1 == null) goto _L2; else goto _L1
_L1:
        if (visibilityinfo.mStartVisibility == visibilityinfo.mEndVisibility && visibilityinfo.mStartParent == visibilityinfo.mEndParent)
        {
            return visibilityinfo;
        }
        if (visibilityinfo.mStartVisibility == visibilityinfo.mEndVisibility) goto _L4; else goto _L3
_L3:
        if (visibilityinfo.mStartVisibility != 0) goto _L6; else goto _L5
_L5:
        visibilityinfo.mFadeIn = false;
        visibilityinfo.mVisibilityChange = true;
_L8:
        return visibilityinfo;
_L6:
        if (visibilityinfo.mEndVisibility == 0)
        {
            visibilityinfo.mFadeIn = true;
            visibilityinfo.mVisibilityChange = true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (visibilityinfo.mEndParent == null)
        {
            visibilityinfo.mFadeIn = false;
            visibilityinfo.mVisibilityChange = true;
        } else
        if (visibilityinfo.mStartParent == null)
        {
            visibilityinfo.mFadeIn = true;
            visibilityinfo.mVisibilityChange = true;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (transitionvalues == null && visibilityinfo.mEndVisibility == 0)
        {
            visibilityinfo.mFadeIn = true;
            visibilityinfo.mVisibilityChange = true;
        } else
        if (transitionvalues1 == null && visibilityinfo.mStartVisibility == 0)
        {
            visibilityinfo.mFadeIn = false;
            visibilityinfo.mVisibilityChange = true;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public final Animator createAnimator(final ViewGroup overlay, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        VisibilityInfo visibilityinfo = getVisibilityChangeInfo(transitionvalues, transitionvalues1);
        if (!visibilityinfo.mVisibilityChange || visibilityinfo.mStartParent == null && visibilityinfo.mEndParent == null) goto _L2; else goto _L1
_L1:
        int i;
        if (visibilityinfo.mFadeIn)
        {
            if ((mMode & 1) != 1 || transitionvalues1 == null)
            {
                return null;
            }
            if (transitionvalues == null)
            {
                overlay = (View)transitionvalues1.view.getParent();
                if (getVisibilityChangeInfo(getMatchedTransitionValues(overlay, false), getTransitionValues(overlay, false)).mVisibilityChange)
                {
                    return null;
                }
            }
            return onAppear$51662RJ4E9NMIP1FEPKMATPFAPKMATQ7E9NNAS1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EP99HGMSP3IDTKM8BR1DPKMQOBKD5NMSBQ1DPKMQOBKDTP3M___0(transitionvalues1.view, transitionvalues);
        }
        i = visibilityinfo.mEndVisibility;
        if ((mMode & 2) != 2) goto _L4; else goto _L3
_L3:
        final Object finalOverlayView;
        if (transitionvalues != null)
        {
            finalOverlayView = transitionvalues.view;
        } else
        {
            finalOverlayView = null;
        }
        if (transitionvalues1 != null)
        {
            transitionvalues1 = transitionvalues1.view;
        } else
        {
            transitionvalues1 = null;
        }
        if (transitionvalues1 == null || transitionvalues1.getParent() == null)
        {
            int j;
            if (transitionvalues1 != null)
            {
                Object obj = null;
                finalOverlayView = transitionvalues1;
                transitionvalues1 = obj;
                break MISSING_BLOCK_LABEL_163;
            }
            if (finalOverlayView != null)
            {
                if (((View) (finalOverlayView)).getParent() == null)
                {
                    transitionvalues1 = null;
                    continue; /* Loop/switch isn't completed */
                }
                if (((View) (finalOverlayView)).getParent() instanceof View)
                {
                    transitionvalues1 = (View)((View) (finalOverlayView)).getParent();
                    if (!getVisibilityChangeInfo(getTransitionValues(transitionvalues1, true), getMatchedTransitionValues(transitionvalues1, true)).mVisibilityChange)
                    {
                        finalOverlayView = TransitionUtils.copyViewImage(overlay, ((View) (finalOverlayView)), transitionvalues1);
                        transitionvalues1 = null;
                    } else
                    {
                        if (transitionvalues1.getParent() == null)
                        {
                            j = transitionvalues1.getId();
                            if (j != -1)
                            {
                                overlay.findViewById(j);
                            }
                        }
                        transitionvalues1 = null;
                        finalOverlayView = null;
                    }
                    continue; /* Loop/switch isn't completed */
                }
            }
            transitionvalues1 = null;
            finalOverlayView = null;
        } else
        {
            if (i == 4)
            {
                finalOverlayView = null;
            } else
            if (finalOverlayView == transitionvalues1)
            {
                finalOverlayView = null;
            } else
            {
                finalOverlayView = TransitionUtils.copyViewImage(overlay, ((View) (finalOverlayView)), (View)((View) (finalOverlayView)).getParent());
                transitionvalues1 = null;
            }
            continue; /* Loop/switch isn't completed */
        }
          goto _L5
_L7:
        if (finalOverlayView != null && transitionvalues != null)
        {
            transitionvalues1 = (int[])transitionvalues.values.get("android:visibility:screenLocation");
            i = transitionvalues1[0];
            j = transitionvalues1[1];
            transitionvalues1 = new int[2];
            overlay.getLocationOnScreen(transitionvalues1);
            ((View) (finalOverlayView)).offsetLeftAndRight(i - transitionvalues1[0] - ((View) (finalOverlayView)).getLeft());
            ((View) (finalOverlayView)).offsetTopAndBottom(j - transitionvalues1[1] - ((View) (finalOverlayView)).getTop());
            overlay = new ViewGroupOverlayApi18(overlay);
            overlay.add(((View) (finalOverlayView)));
            transitionvalues = onDisappear$51662RJ4E9NMIP1FEPKMATPFAPKMATQ7E9NNAS1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EP99HGMSP3IDTKM8BR1DPKMQOBKD5NMSBQ1DPKMQOBKDTP3M___0(((View) (finalOverlayView)), transitionvalues);
            if (transitionvalues == null)
            {
                overlay.remove(((View) (finalOverlayView)));
                return transitionvalues;
            } else
            {
                transitionvalues.addListener(new _cls1());
                return transitionvalues;
            }
        }
        if (transitionvalues1 != null)
        {
            j = transitionvalues1.getVisibility();
            ViewUtils.setTransitionVisibility(transitionvalues1, 0);
            overlay = onDisappear$51662RJ4E9NMIP1FEPKMATPFAPKMATQ7E9NNAS1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EP99HGMSP3IDTKM8BR1DPKMQOBKD5NMSBQ1DPKMQOBKDTP3M___0(transitionvalues1, transitionvalues);
            if (overlay != null)
            {
                transitionvalues = new DisappearListener(transitionvalues1, i, true);
                overlay.addListener(transitionvalues);
                overlay.addPauseListener(transitionvalues);
                addListener(transitionvalues);
                return overlay;
            } else
            {
                ViewUtils.setTransitionVisibility(transitionvalues1, j);
                return overlay;
            }
        }
_L4:
        return null;
_L2:
        return null;
_L5:
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }

    public final boolean isTransitionRequired(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        if ((transitionvalues != null || transitionvalues1 != null) && (transitionvalues == null || transitionvalues1 == null || transitionvalues1.values.containsKey("android:visibility:visibility") == transitionvalues.values.containsKey("android:visibility:visibility")))
        {
            transitionvalues = getVisibilityChangeInfo(transitionvalues, transitionvalues1);
            if (((VisibilityInfo) (transitionvalues)).mVisibilityChange && (((VisibilityInfo) (transitionvalues)).mStartVisibility == 0 || ((VisibilityInfo) (transitionvalues)).mEndVisibility == 0))
            {
                return true;
            }
        }
        return false;
    }

    public Animator onAppear$51662RJ4E9NMIP1FEPKMATPFAPKMATQ7E9NNAS1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EP99HGMSP3IDTKM8BR1DPKMQOBKD5NMSBQ1DPKMQOBKDTP3M___0(View view, TransitionValues transitionvalues)
    {
        return null;
    }

    public Animator onDisappear$51662RJ4E9NMIP1FEPKMATPFAPKMATQ7E9NNAS1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EP99HGMSP3IDTKM8BR1DPKMQOBKD5NMSBQ1DPKMQOBKDTP3M___0(View view, TransitionValues transitionvalues)
    {
        return null;
    }


    private class VisibilityInfo
    {

        public ViewGroup mEndParent;
        public int mEndVisibility;
        public boolean mFadeIn;
        public ViewGroup mStartParent;
        public int mStartVisibility;
        public boolean mVisibilityChange;

        VisibilityInfo()
        {
        }
    }


    private class _cls1 extends AnimatorListenerAdapter
    {

        private final View val$finalOverlayView;
        private final ViewGroupOverlayImpl val$overlay;

        public final void onAnimationEnd(Animator animator)
        {
            overlay.remove(finalOverlayView);
        }

        _cls1()
        {
            overlay = viewgroupoverlayimpl;
            finalOverlayView = view;
            super();
        }
    }


    private class DisappearListener extends AnimatorListenerAdapter
        implements Transition.TransitionListener
    {

        private boolean mCanceled;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout = true;
        private final View mView;

        private final void hideViewWhenNotCanceled()
        {
            if (!mCanceled)
            {
                ViewUtils.setTransitionVisibility(mView, mFinalVisibility);
                if (mParent != null)
                {
                    mParent.invalidate();
                }
            }
            suppressLayout(false);
        }

        private final void suppressLayout(boolean flag)
        {
            if (mSuppressLayout && mLayoutSuppressed != flag && mParent != null)
            {
                mLayoutSuppressed = flag;
                ViewGroupUtils.suppressLayout(mParent, flag);
            }
        }

        public final void onAnimationCancel(Animator animator)
        {
            mCanceled = true;
        }

        public final void onAnimationEnd(Animator animator)
        {
            hideViewWhenNotCanceled();
        }

        public final void onAnimationPause(Animator animator)
        {
            if (!mCanceled)
            {
                ViewUtils.setTransitionVisibility(mView, mFinalVisibility);
            }
        }

        public final void onAnimationRepeat(Animator animator)
        {
        }

        public final void onAnimationResume(Animator animator)
        {
            if (!mCanceled)
            {
                ViewUtils.setTransitionVisibility(mView, 0);
            }
        }

        public final void onAnimationStart(Animator animator)
        {
        }

        public final void onTransitionEnd(Transition transition)
        {
            hideViewWhenNotCanceled();
            transition.removeListener(this);
        }

        public final void onTransitionPause$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
            suppressLayout(false);
        }

        public final void onTransitionResume$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
            suppressLayout(true);
        }

        public final void onTransitionStart$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
        }

        DisappearListener(View view, int i, boolean flag)
        {
            mCanceled = false;
            mView = view;
            mFinalVisibility = i;
            mParent = (ViewGroup)view.getParent();
            suppressLayout(true);
        }
    }

}
