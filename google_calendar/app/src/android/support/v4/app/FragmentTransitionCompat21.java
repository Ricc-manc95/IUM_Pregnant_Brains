// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionImpl

final class FragmentTransitionCompat21 extends FragmentTransitionImpl
{

    FragmentTransitionCompat21()
    {
    }

    private static boolean hasSimpleTarget(Transition transition)
    {
label0:
        {
            boolean flag1 = false;
            List list = transition.getTargetIds();
            boolean flag;
            if (list == null || list.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                List list1 = transition.getTargetNames();
                if (list1 == null || list1.isEmpty())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    transition = transition.getTargetTypes();
                    if (transition == null || transition.isEmpty())
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
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
        int i;
        i = 0;
        obj = (Transition)obj;
        if (obj != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!(obj instanceof TransitionSet))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (TransitionSet)obj;
        int k = ((TransitionSet) (obj)).getTransitionCount();
        while (i < k) 
        {
            addTargets(((TransitionSet) (obj)).getTransitionAt(i), arraylist);
            i++;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (!hasSimpleTarget(((Transition) (obj))))
        {
            List list = ((Transition) (obj)).getTargets();
            int j;
            if (list == null || list.isEmpty())
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
        TransitionManager.beginDelayedTransition(viewgroup, (Transition)obj);
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
            transition = ((Transition)obj).clone();
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
            obj = (new TransitionSet()).addTransition(((Transition) (obj))).addTransition(((Transition) (obj1))).setOrdering(1);
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
        int i = 0;
        obj = (Transition)obj;
        if (obj instanceof TransitionSet)
        {
            obj = (TransitionSet)obj;
            for (int l = ((TransitionSet) (obj)).getTransitionCount(); i < l; i++)
            {
                replaceTargets(((TransitionSet) (obj)).getTransitionAt(i), arraylist, arraylist1);
            }

        } else
        if (!hasSimpleTarget(((Transition) (obj))))
        {
            List list = ((Transition) (obj)).getTargets();
            if (list != null && list.size() == arraylist.size() && list.containsAll(arraylist))
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

    public final void setEpicenter(Object obj, final Rect epicenter)
    {
        if (obj != null)
        {
            ((Transition)obj).setEpicenterCallback(new _cls4());
        }
    }

    public final void setEpicenter(Object obj, View view)
    {
        if (view != null)
        {
            obj = (Transition)obj;
            final Rect epicenter = new Rect();
            getBoundsOnScreen(view, epicenter);
            ((Transition) (obj)).setEpicenterCallback(new _cls1());
        }
    }

    public final void setSharedElementTargets(Object obj, View view, ArrayList arraylist)
    {
        obj = (TransitionSet)obj;
        List list = ((TransitionSet) (obj)).getTargets();
        list.clear();
        int j = arraylist.size();
        for (int i = 0; i < j; i++)
        {
            bfsAddViewChildren(list, (View)arraylist.get(i));
        }

        list.add(view);
        arraylist.add(view);
        addTargets(obj, arraylist);
    }

    public final void swapSharedElementTargets(Object obj, ArrayList arraylist, ArrayList arraylist1)
    {
        obj = (TransitionSet)obj;
        if (obj != null)
        {
            ((TransitionSet) (obj)).getTargets().clear();
            ((TransitionSet) (obj)).getTargets().addAll(arraylist1);
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
        implements android.transition.Transition.TransitionListener
    {

        private final ArrayList val$exitingViews;
        private final View val$fragmentView;

        public final void onTransitionCancel(Transition transition)
        {
        }

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

        public final void onTransitionPause(Transition transition)
        {
        }

        public final void onTransitionResume(Transition transition)
        {
        }

        public final void onTransitionStart(Transition transition)
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
        implements android.transition.Transition.TransitionListener
    {

        private final FragmentTransitionCompat21 this$0;
        private final Object val$enterTransition;
        private final ArrayList val$enteringViews;
        private final Object val$exitTransition;
        private final ArrayList val$exitingViews;
        private final Object val$sharedElementTransition;
        private final ArrayList val$sharedElementsIn;

        public final void onTransitionCancel(Transition transition)
        {
        }

        public final void onTransitionEnd(Transition transition)
        {
        }

        public final void onTransitionPause(Transition transition)
        {
        }

        public final void onTransitionResume(Transition transition)
        {
        }

        public final void onTransitionStart(Transition transition)
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
            this$0 = FragmentTransitionCompat21.this;
            enterTransition = obj;
            enteringViews = arraylist;
            exitTransition = obj1;
            exitingViews = arraylist1;
            sharedElementTransition = obj2;
            sharedElementsIn = arraylist2;
            super();
        }
    }


    private class _cls4 extends android.transition.Transition.EpicenterCallback
    {

        private final Rect val$epicenter;

        public final Rect onGetEpicenter(Transition transition)
        {
            if (epicenter == null || epicenter.isEmpty())
            {
                return null;
            } else
            {
                return epicenter;
            }
        }

        _cls4()
        {
            epicenter = rect;
            super();
        }
    }


    private class _cls1 extends android.transition.Transition.EpicenterCallback
    {

        private final Rect val$epicenter;

        public final Rect onGetEpicenter(Transition transition)
        {
            return epicenter;
        }

        _cls1()
        {
            epicenter = rect;
            super();
        }
    }

}
