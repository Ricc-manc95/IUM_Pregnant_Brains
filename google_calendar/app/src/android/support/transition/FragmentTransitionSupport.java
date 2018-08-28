// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.Rect;
import android.support.v4.app.FragmentTransitionImpl;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.transition:
//            Transition, TransitionSet, TransitionManager, Scene

public class FragmentTransitionSupport extends FragmentTransitionImpl
{

    public FragmentTransitionSupport()
    {
    }

    private static boolean hasSimpleTarget(Transition transition)
    {
label0:
        {
            boolean flag1 = false;
            transition = transition.mTargetIds;
            boolean flag;
            if (transition == null || transition.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (false)
                {
                    throw new NullPointerException();
                }
                if (true)
                {
                    if (false)
                    {
                        throw new NullPointerException();
                    }
                    if (true)
                    {
                        break label0;
                    }
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public final void addTarget(Object obj, View view)
    {
        if (obj != null)
        {
            ((Transition)obj).addTarget(view);
        }
    }

    public final void addTargets(Object obj, ArrayList arraylist)
    {
        obj = (Transition)obj;
        if (obj != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!(obj instanceof TransitionSet))
        {
            break; /* Loop/switch isn't completed */
        }
        TransitionSet transitionset = (TransitionSet)obj;
        int k = transitionset.mTransitions.size();
        int i = 0;
        while (i < k) 
        {
            if (i < 0 || i >= transitionset.mTransitions.size())
            {
                obj = null;
            } else
            {
                obj = (Transition)transitionset.mTransitions.get(i);
            }
            addTargets(obj, arraylist);
            i++;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (!hasSimpleTarget(((Transition) (obj))))
        {
            ArrayList arraylist1 = ((Transition) (obj)).mTargets;
            int j;
            if (arraylist1 == null || arraylist1.isEmpty())
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                int l = arraylist.size();
                j = 0;
                while (j < l) 
                {
                    ((Transition) (obj)).addTarget((View)arraylist.get(j));
                    j++;
                }
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public final void beginDelayedTransition(ViewGroup viewgroup, Object obj)
    {
        Object obj1 = (Transition)obj;
        if (!TransitionManager.sPendingTransitions.contains(viewgroup) && ViewCompat.isLaidOut(viewgroup))
        {
            TransitionManager.sPendingTransitions.add(viewgroup);
            obj = obj1;
            if (obj1 == null)
            {
                obj = TransitionManager.sDefaultTransition;
            }
            obj = (Transition)((Transition) (obj)).clone();
            obj1 = (ArrayList)TransitionManager.getRunningTransitions().get(viewgroup);
            if (obj1 != null && ((ArrayList) (obj1)).size() > 0)
            {
                obj1 = (ArrayList)obj1;
                int j = ((ArrayList) (obj1)).size();
                for (int i = 0; i < j;)
                {
                    Object obj2 = ((ArrayList) (obj1)).get(i);
                    i++;
                    ((Transition)obj2).pause(viewgroup);
                }

            }
            if (obj != null)
            {
                ((Transition) (obj)).captureValues(viewgroup, true);
            }
            if ((Scene)viewgroup.getTag(0x7f100048) != null)
            {
                throw new NoSuchMethodError();
            }
            viewgroup.setTag(0x7f100048, null);
            TransitionManager.sceneChangeRunTransition(viewgroup, ((Transition) (obj)));
        }
    }

    public final boolean canHandle(Object obj)
    {
        return obj instanceof Transition;
    }

    public final Object cloneTransition(Object obj)
    {
        Transition transition = null;
        if (obj != null)
        {
            transition = (Transition)((Transition)obj).clone();
        }
        return transition;
    }

    public final Object mergeTransitionsInSequence(Object obj, Object obj1, Object obj2)
    {
        Object obj3 = null;
        obj = (Transition)obj;
        obj1 = (Transition)obj1;
        obj2 = (Transition)obj2;
        if (obj != null && obj1 != null)
        {
            obj = (new TransitionSet()).addTransition(((Transition) (obj))).addTransition(((Transition) (obj1)));
            obj.mPlayTogether = false;
        } else
        if (obj == null)
        {
            obj = obj3;
            if (obj1 != null)
            {
                obj = obj1;
            }
        }
        if (obj2 != null)
        {
            obj1 = new TransitionSet();
            if (obj != null)
            {
                ((TransitionSet) (obj1)).addTransition(((Transition) (obj)));
            }
            ((TransitionSet) (obj1)).addTransition(((Transition) (obj2)));
            return obj1;
        } else
        {
            return obj;
        }
    }

    public final Object mergeTransitionsTogether(Object obj, Object obj1, Object obj2)
    {
        TransitionSet transitionset = new TransitionSet();
        if (obj != null)
        {
            transitionset.addTransition((Transition)obj);
        }
        if (obj1 != null)
        {
            transitionset.addTransition((Transition)obj1);
        }
        if (obj2 != null)
        {
            transitionset.addTransition((Transition)obj2);
        }
        return transitionset;
    }

    public final void removeTarget(Object obj, View view)
    {
        if (obj != null)
        {
            ((Transition)obj).removeTarget(view);
        }
    }

    public final void replaceTargets(Object obj, ArrayList arraylist, ArrayList arraylist1)
    {
        obj = (Transition)obj;
        if (obj instanceof TransitionSet)
        {
            TransitionSet transitionset = (TransitionSet)obj;
            int l = transitionset.mTransitions.size();
            int i = 0;
            while (i < l) 
            {
                if (i < 0 || i >= transitionset.mTransitions.size())
                {
                    obj = null;
                } else
                {
                    obj = (Transition)transitionset.mTransitions.get(i);
                }
                replaceTargets(obj, arraylist, arraylist1);
                i++;
            }
        } else
        if (!hasSimpleTarget(((Transition) (obj))))
        {
            ArrayList arraylist2 = ((Transition) (obj)).mTargets;
            if (arraylist2.size() == arraylist.size() && arraylist2.containsAll(arraylist))
            {
                int j;
                int i1;
                if (arraylist1 == null)
                {
                    j = 0;
                } else
                {
                    j = arraylist1.size();
                }
                for (i1 = 0; i1 < j; i1++)
                {
                    ((Transition) (obj)).addTarget((View)arraylist1.get(i1));
                }

                for (int k = arraylist.size() - 1; k >= 0; k--)
                {
                    ((Transition) (obj)).removeTarget((View)arraylist.get(k));
                }

            }
        }
    }

    public final void scheduleHideFragmentView(Object obj, final View fragmentView, final ArrayList exitingViews)
    {
        ((Transition)obj).addListener(new _cls2());
    }

    public final void scheduleRemoveTargets(Object obj, final Object enterTransition, final ArrayList enteringViews, final Object exitTransition, final ArrayList exitingViews, final Object sharedElementTransition, final ArrayList sharedElementsIn)
    {
        ((Transition)obj).addListener(new _cls3());
    }

    public final void setEpicenter(Object obj, Rect rect)
    {
        if (obj != null)
        {
            ((Transition)obj).setEpicenterCallback(new _cls4(rect));
        }
    }

    public final void setEpicenter(Object obj, View view)
    {
        if (view != null)
        {
            obj = (Transition)obj;
            Rect rect = new Rect();
            getBoundsOnScreen(view, rect);
            ((Transition) (obj)).setEpicenterCallback(new _cls1(rect));
        }
    }

    public final void setSharedElementTargets(Object obj, View view, ArrayList arraylist)
    {
        obj = (TransitionSet)obj;
        ArrayList arraylist1 = ((Transition) (obj)).mTargets;
        arraylist1.clear();
        int j = arraylist.size();
        for (int i = 0; i < j; i++)
        {
            bfsAddViewChildren(arraylist1, (View)arraylist.get(i));
        }

        arraylist1.add(view);
        arraylist.add(view);
        addTargets(obj, arraylist);
    }

    public final void swapSharedElementTargets(Object obj, ArrayList arraylist, ArrayList arraylist1)
    {
        obj = (TransitionSet)obj;
        if (obj != null)
        {
            ((Transition) (obj)).mTargets.clear();
            ((Transition) (obj)).mTargets.addAll(arraylist1);
            replaceTargets(obj, arraylist, arraylist1);
        }
    }

    public final Object wrapTransitionInSet(Object obj)
    {
        if (obj == null)
        {
            return null;
        } else
        {
            TransitionSet transitionset = new TransitionSet();
            transitionset.addTransition((Transition)obj);
            return transitionset;
        }
    }

    private class _cls2
        implements Transition.TransitionListener
    {

        private final ArrayList val$exitingViews;
        private final View val$fragmentView;

        public final void onTransitionEnd(Transition transition)
        {
            transition.removeListener(this);
            fragmentView.setVisibility(8);
            int j = exitingViews.size();
            for (int i = 0; i < j; i++)
            {
                ((View)exitingViews.get(i)).setVisibility(0);
            }

        }

        public final void onTransitionPause$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
        }

        public final void onTransitionResume$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
        }

        public final void onTransitionStart$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
        }

        _cls2()
        {
            fragmentView = view;
            exitingViews = arraylist;
            super();
        }
    }


    private class _cls3
        implements Transition.TransitionListener
    {

        private final FragmentTransitionSupport this$0;
        private final Object val$enterTransition;
        private final ArrayList val$enteringViews;
        private final Object val$exitTransition;
        private final ArrayList val$exitingViews;
        private final Object val$sharedElementTransition;
        private final ArrayList val$sharedElementsIn;

        public final void onTransitionEnd(Transition transition)
        {
        }

        public final void onTransitionPause$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
        }

        public final void onTransitionResume$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
        }

        public final void onTransitionStart$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0()
        {
            if (enterTransition != null)
            {
                replaceTargets(enterTransition, enteringViews, null);
            }
            if (exitTransition != null)
            {
                replaceTargets(exitTransition, exitingViews, null);
            }
            if (sharedElementTransition != null)
            {
                replaceTargets(sharedElementTransition, sharedElementsIn, null);
            }
        }

        _cls3()
        {
            this$0 = FragmentTransitionSupport.this;
            enterTransition = obj;
            enteringViews = arraylist;
            exitTransition = obj1;
            exitingViews = arraylist1;
            sharedElementTransition = obj2;
            sharedElementsIn = arraylist2;
            super();
        }
    }


    private class _cls4 extends Transition.EpicenterCallback
    {

        _cls4(Rect rect)
        {
        }
    }


    private class _cls1 extends Transition.EpicenterCallback
    {

        _cls1(Rect rect)
        {
        }
    }

}
