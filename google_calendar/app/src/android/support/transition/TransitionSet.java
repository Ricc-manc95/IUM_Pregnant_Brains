// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

// Referenced classes of package android.support.transition:
//            Transition, TransitionValues, TransitionValuesMaps, PathMotion, 
//            TransitionPropagation

public class TransitionSet extends Transition
{

    private int mChangeFlags;
    public int mCurrentListeners;
    public boolean mPlayTogether;
    public boolean mStarted;
    public ArrayList mTransitions;

    public TransitionSet()
    {
        mTransitions = new ArrayList();
        mPlayTogether = true;
        mStarted = false;
        mChangeFlags = 0;
    }

    public final Transition addListener(Transition.TransitionListener transitionlistener)
    {
        return (TransitionSet)super.addListener(transitionlistener);
    }

    public final Transition addTarget(View view)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).addTarget(view);
        }

        return (TransitionSet)super.addTarget(view);
    }

    public final TransitionSet addTransition(Transition transition)
    {
        mTransitions.add(transition);
        transition.mParent = this;
        if (mDuration >= 0L)
        {
            transition.setDuration(mDuration);
        }
        if ((mChangeFlags & 1) != 0)
        {
            transition.setInterpolator(super.mInterpolator);
        }
        if ((mChangeFlags & 2) != 0)
        {
            transition.setPropagation(super.mPropagation);
        }
        if ((mChangeFlags & 4) != 0)
        {
            transition.setPathMotion(super.mPathMotion);
        }
        if ((mChangeFlags & 8) != 0)
        {
            transition.setEpicenterCallback(super.mEpicenterCallback);
        }
        return this;
    }

    public final void captureEndValues(TransitionValues transitionvalues)
    {
        if (isValidTarget(transitionvalues.view))
        {
            ArrayList arraylist = (ArrayList)mTransitions;
            int k = arraylist.size();
            int i = 0;
            do
            {
                if (i >= k)
                {
                    break;
                }
                Object obj = arraylist.get(i);
                int j = i + 1;
                obj = (Transition)obj;
                i = j;
                if (((Transition) (obj)).isValidTarget(transitionvalues.view))
                {
                    ((Transition) (obj)).captureEndValues(transitionvalues);
                    transitionvalues.mTargetedTransitions.add(obj);
                    i = j;
                }
            } while (true);
        }
    }

    final void capturePropagationValues(TransitionValues transitionvalues)
    {
        super.capturePropagationValues(transitionvalues);
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            ((Transition)mTransitions.get(i)).capturePropagationValues(transitionvalues);
        }

    }

    public final void captureStartValues(TransitionValues transitionvalues)
    {
        if (isValidTarget(transitionvalues.view))
        {
            ArrayList arraylist = (ArrayList)mTransitions;
            int k = arraylist.size();
            int i = 0;
            do
            {
                if (i >= k)
                {
                    break;
                }
                Object obj = arraylist.get(i);
                int j = i + 1;
                obj = (Transition)obj;
                i = j;
                if (((Transition) (obj)).isValidTarget(transitionvalues.view))
                {
                    ((Transition) (obj)).captureStartValues(transitionvalues);
                    transitionvalues.mTargetedTransitions.add(obj);
                    i = j;
                }
            } while (true);
        }
    }

    public final Transition clone()
    {
        TransitionSet transitionset = (TransitionSet)super.clone();
        transitionset.mTransitions = new ArrayList();
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            transitionset.addTransition((Transition)((Transition)mTransitions.get(i)).clone());
        }

        return transitionset;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    protected final void createAnimators(ViewGroup viewgroup, TransitionValuesMaps transitionvaluesmaps, TransitionValuesMaps transitionvaluesmaps1, ArrayList arraylist, ArrayList arraylist1)
    {
        long l = super.mStartDelay;
        int j = mTransitions.size();
        int i = 0;
        while (i < j) 
        {
            Transition transition = (Transition)mTransitions.get(i);
            if (l > 0L && (mPlayTogether || i == 0))
            {
                long l1 = transition.mStartDelay;
                if (l1 > 0L)
                {
                    transition.setStartDelay(l1 + l);
                } else
                {
                    transition.setStartDelay(l);
                }
            }
            transition.createAnimators(viewgroup, transitionvaluesmaps, transitionvaluesmaps1, arraylist, arraylist1);
            i++;
        }
    }

    public final void pause(View view)
    {
        super.pause(view);
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            ((Transition)mTransitions.get(i)).pause(view);
        }

    }

    public final Transition removeListener(Transition.TransitionListener transitionlistener)
    {
        return (TransitionSet)super.removeListener(transitionlistener);
    }

    public final Transition removeTarget(View view)
    {
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).removeTarget(view);
        }

        return (TransitionSet)super.removeTarget(view);
    }

    public final void resume(View view)
    {
        super.resume(view);
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            ((Transition)mTransitions.get(i)).resume(view);
        }

    }

    protected final void runAnimators()
    {
        if (!mTransitions.isEmpty()) goto _L2; else goto _L1
_L1:
        start();
        end();
_L4:
        return;
_L2:
        TransitionSetListener transitionsetlistener = new TransitionSetListener();
        ArrayList arraylist1 = (ArrayList)mTransitions;
        int l = arraylist1.size();
        for (int i = 0; i < l;)
        {
            Object obj1 = arraylist1.get(i);
            i++;
            ((Transition)obj1).addListener(transitionsetlistener);
        }

        mCurrentListeners = mTransitions.size();
        if (!mPlayTogether)
        {
            for (int j = 1; j < mTransitions.size(); j++)
            {
                ((Transition)mTransitions.get(j - 1)).addListener(new _cls1());
            }

            Transition transition = (Transition)mTransitions.get(0);
            if (transition != null)
            {
                transition.runAnimators();
                return;
            }
        } else
        {
            ArrayList arraylist = (ArrayList)mTransitions;
            int i1 = arraylist.size();
            int k = 0;
            while (k < i1) 
            {
                Object obj = arraylist.get(k);
                k++;
                ((Transition)obj).runAnimators();
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final Transition setDuration(long l)
    {
        super.setDuration(l);
        if (mDuration >= 0L)
        {
            int j = mTransitions.size();
            for (int i = 0; i < j; i++)
            {
                ((Transition)mTransitions.get(i)).setDuration(l);
            }

        }
        return this;
    }

    public final void setEpicenterCallback(Transition.EpicenterCallback epicentercallback)
    {
        super.setEpicenterCallback(epicentercallback);
        mChangeFlags = mChangeFlags | 8;
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            ((Transition)mTransitions.get(i)).setEpicenterCallback(epicentercallback);
        }

    }

    public final Transition setInterpolator(TimeInterpolator timeinterpolator)
    {
        mChangeFlags = mChangeFlags | 1;
        if (mTransitions != null)
        {
            int j = mTransitions.size();
            for (int i = 0; i < j; i++)
            {
                ((Transition)mTransitions.get(i)).setInterpolator(timeinterpolator);
            }

        }
        return (TransitionSet)super.setInterpolator(timeinterpolator);
    }

    public final void setPathMotion(PathMotion pathmotion)
    {
        super.setPathMotion(pathmotion);
        mChangeFlags = mChangeFlags | 4;
        for (int i = 0; i < mTransitions.size(); i++)
        {
            ((Transition)mTransitions.get(i)).setPathMotion(pathmotion);
        }

    }

    public final void setPropagation(TransitionPropagation transitionpropagation)
    {
        super.setPropagation(transitionpropagation);
        mChangeFlags = mChangeFlags | 2;
        int j = mTransitions.size();
        for (int i = 0; i < j; i++)
        {
            ((Transition)mTransitions.get(i)).setPropagation(transitionpropagation);
        }

    }

    public final Transition setStartDelay(long l)
    {
        return (TransitionSet)super.setStartDelay(l);
    }

    final String toString(String s)
    {
        String s1 = super.toString(s);
        for (int i = 0; i < mTransitions.size(); i++)
        {
            s1 = (new StringBuilder()).append(s1).append("\n").append(((Transition)mTransitions.get(i)).toString((new StringBuilder()).append(s).append("  ").toString())).toString();
        }

        return s1;
    }

    private class TransitionSetListener extends TransitionListenerAdapter
    {

        private TransitionSet mTransitionSet;

        public final void onTransitionEnd(Transition transition)
        {
            TransitionSet transitionset = mTransitionSet;
            transitionset.mCurrentListeners = transitionset.mCurrentListeners - 1;
            if (mTransitionSet.mCurrentListeners == 0)
            {
                mTransitionSet.mStarted = false;
                mTransitionSet.end();
            }
            transition.removeListener(this);
        }

        public final void onTransitionStart$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
            if (!mTransitionSet.mStarted)
            {
                mTransitionSet.start();
                mTransitionSet.mStarted = true;
            }
        }

        TransitionSetListener()
        {
            mTransitionSet = TransitionSet.this;
        }
    }


    private class _cls1 extends TransitionListenerAdapter
    {

        private final Transition val$nextTransition;

        public final void onTransitionEnd(Transition transition)
        {
            nextTransition.runAnimators();
            transition.removeListener(this);
        }

        _cls1()
        {
            nextTransition = transition;
            super();
        }
    }

}
