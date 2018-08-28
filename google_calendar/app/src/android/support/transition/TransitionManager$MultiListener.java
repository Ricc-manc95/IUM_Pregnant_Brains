// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ContainerHelpers;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.ArrayList;

// Referenced classes of package android.support.transition:
//            TransitionManager, Transition, TransitionValuesMaps, TransitionValues, 
//            ViewUtils

final class mSceneRoot
    implements android.view., android.view.er
{

    public ViewGroup mSceneRoot;
    private Transition mTransition;

    public final boolean onPreDraw()
    {
        mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
        mSceneRoot.removeOnAttachStateChangeListener(this);
        if (!TransitionManager.sPendingTransitions.remove(mSceneRoot))
        {
            return true;
        }
        final ArrayMap runningTransitions = TransitionManager.getRunningTransitions();
        Object obj1 = (ArrayList)runningTransitions.get(mSceneRoot);
        class _cls1 extends TransitionListenerAdapter
        {

            private final TransitionManager.MultiListener this$0;
            private final ArrayMap val$runningTransitions;

            public final void onTransitionEnd(Transition transition)
            {
                ((ArrayList)runningTransitions.get(mSceneRoot)).remove(transition);
            }

            _cls1()
            {
                this$0 = TransitionManager.MultiListener.this;
                runningTransitions = arraymap;
                super();
            }
        }

        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        View view;
        TransitionValues transitionvalues;
        TransitionValues transitionvalues1;
        int j;
        int l;
        boolean flag;
        int i1;
        int j1;
        long l1;
        if (obj1 == null)
        {
            obj1 = new ArrayList();
            runningTransitions.put(mSceneRoot, obj1);
            obj = null;
        } else
        if (((ArrayList) (obj1)).size() > 0)
        {
            obj = new ArrayList(((java.util.Collection) (obj1)));
        } else
        {
            obj = null;
        }
        ((ArrayList) (obj1)).add(mTransition);
        mTransition.addListener(new _cls1());
        mTransition.captureValues(mSceneRoot, false);
        if (obj != null)
        {
            obj = (ArrayList)obj;
            int k = ((ArrayList) (obj)).size();
            for (int i = 0; i < k;)
            {
                obj1 = ((ArrayList) (obj)).get(i);
                i++;
                ((Transition)obj1).resume(mSceneRoot);
            }

        }
        obj1 = mTransition;
        runningTransitions = mSceneRoot;
        obj1.mStartValuesList = new ArrayList();
        obj1.mEndValuesList = new ArrayList();
        obj4 = ((Transition) (obj1)).mStartValues;
        obj5 = ((Transition) (obj1)).mEndValues;
        obj3 = new ArrayMap(((TransitionValuesMaps) (obj4)).mViewValues);
        obj2 = new ArrayMap(((TransitionValuesMaps) (obj5)).mViewValues);
        j = 0;
        if (j >= ((Transition) (obj1)).mMatchOrder.length)
        {
            break MISSING_BLOCK_LABEL_1010;
        }
        switch (((Transition) (obj1)).mMatchOrder[j])
        {
        default:
            break;

        case 1: // '\001'
            break; /* Loop/switch isn't completed */

        case 2: // '\002'
            obj = ((TransitionValuesMaps) (obj4)).mNameValues;
            obj6 = ((TransitionValuesMaps) (obj5)).mNameValues;
            i1 = ((SimpleArrayMap) (obj)).size();
            l = 0;
            while (l < i1) 
            {
                obj7 = (View)((SimpleArrayMap) (obj)).mArray[(l << 1) + 1];
                if (obj7 != null && ((Transition) (obj1)).isValidTarget(((View) (obj7))))
                {
                    view = (View)((SimpleArrayMap) (obj6)).get(((SimpleArrayMap) (obj)).mArray[l << 1]);
                    if (view != null && ((Transition) (obj1)).isValidTarget(view))
                    {
                        transitionvalues = (TransitionValues)((SimpleArrayMap) (obj3)).get(obj7);
                        transitionvalues1 = (TransitionValues)((SimpleArrayMap) (obj2)).get(view);
                        if (transitionvalues != null && transitionvalues1 != null)
                        {
                            ((Transition) (obj1)).mStartValuesList.add(transitionvalues);
                            ((Transition) (obj1)).mEndValuesList.add(transitionvalues1);
                            ((SimpleArrayMap) (obj3)).remove(obj7);
                            ((SimpleArrayMap) (obj2)).remove(view);
                        }
                    }
                }
                l++;
            }
            continue; /* Loop/switch isn't completed */

        case 3: // '\003'
            obj = ((TransitionValuesMaps) (obj4)).mIdValues;
            obj6 = ((TransitionValuesMaps) (obj5)).mIdValues;
            i1 = ((SparseArray) (obj)).size();
            l = 0;
            while (l < i1) 
            {
                obj7 = (View)((SparseArray) (obj)).valueAt(l);
                if (obj7 != null && ((Transition) (obj1)).isValidTarget(((View) (obj7))))
                {
                    view = (View)((SparseArray) (obj6)).get(((SparseArray) (obj)).keyAt(l));
                    if (view != null && ((Transition) (obj1)).isValidTarget(view))
                    {
                        transitionvalues = (TransitionValues)((SimpleArrayMap) (obj3)).get(obj7);
                        transitionvalues1 = (TransitionValues)((SimpleArrayMap) (obj2)).get(view);
                        if (transitionvalues != null && transitionvalues1 != null)
                        {
                            ((Transition) (obj1)).mStartValuesList.add(transitionvalues);
                            ((Transition) (obj1)).mEndValuesList.add(transitionvalues1);
                            ((SimpleArrayMap) (obj3)).remove(obj7);
                            ((SimpleArrayMap) (obj2)).remove(view);
                        }
                    }
                }
                l++;
            }
            continue; /* Loop/switch isn't completed */

        case 4: // '\004'
            break;
        }
        break MISSING_BLOCK_LABEL_759;
_L4:
        j++;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_258;
_L1:
        l = ((SimpleArrayMap) (obj3)).size() - 1;
        while (l >= 0) 
        {
            obj = (View)((SimpleArrayMap) (obj3)).mArray[l << 1];
            if (obj != null && ((Transition) (obj1)).isValidTarget(((View) (obj))))
            {
                obj = (TransitionValues)((SimpleArrayMap) (obj2)).remove(obj);
                if (obj != null && ((TransitionValues) (obj)).view != null && ((Transition) (obj1)).isValidTarget(((TransitionValues) (obj)).view))
                {
                    obj6 = (TransitionValues)((SimpleArrayMap) (obj3)).removeAt(l);
                    ((Transition) (obj1)).mStartValuesList.add(obj6);
                    ((Transition) (obj1)).mEndValuesList.add(obj);
                }
            }
            l--;
        }
        continue; /* Loop/switch isn't completed */
        obj6 = ((TransitionValuesMaps) (obj4)).mItemIdValues;
        obj7 = ((TransitionValuesMaps) (obj5)).mItemIdValues;
        if (((LongSparseArray) (obj6)).mGarbage)
        {
            ((LongSparseArray) (obj6)).gc();
        }
        i1 = ((LongSparseArray) (obj6)).mSize;
        l = 0;
        while (l < i1) 
        {
            if (((LongSparseArray) (obj6)).mGarbage)
            {
                ((LongSparseArray) (obj6)).gc();
            }
            view = (View)((LongSparseArray) (obj6)).mValues[l];
            if (view != null && ((Transition) (obj1)).isValidTarget(view))
            {
                if (((LongSparseArray) (obj6)).mGarbage)
                {
                    ((LongSparseArray) (obj6)).gc();
                }
                l1 = ((LongSparseArray) (obj6)).mKeys[l];
                j1 = ContainerHelpers.binarySearch(((LongSparseArray) (obj7)).mKeys, ((LongSparseArray) (obj7)).mSize, l1);
                if (j1 < 0 || ((LongSparseArray) (obj7)).mValues[j1] == LongSparseArray.DELETED)
                {
                    obj = null;
                } else
                {
                    obj = ((LongSparseArray) (obj7)).mValues[j1];
                }
                obj = (View)obj;
                if (obj != null && ((Transition) (obj1)).isValidTarget(((View) (obj))))
                {
                    transitionvalues = (TransitionValues)((SimpleArrayMap) (obj3)).get(view);
                    transitionvalues1 = (TransitionValues)((SimpleArrayMap) (obj2)).get(obj);
                    if (transitionvalues != null && transitionvalues1 != null)
                    {
                        ((Transition) (obj1)).mStartValuesList.add(transitionvalues);
                        ((Transition) (obj1)).mEndValuesList.add(transitionvalues1);
                        ((SimpleArrayMap) (obj3)).remove(view);
                        ((SimpleArrayMap) (obj2)).remove(obj);
                    }
                }
            }
            l++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        for (j = 0; j < ((SimpleArrayMap) (obj3)).size(); j++)
        {
            obj = (TransitionValues)((SimpleArrayMap) (obj3)).mArray[(j << 1) + 1];
            if (((Transition) (obj1)).isValidTarget(((TransitionValues) (obj)).view))
            {
                ((Transition) (obj1)).mStartValuesList.add(obj);
                ((Transition) (obj1)).mEndValuesList.add(null);
            }
        }

        for (j = 0; j < ((SimpleArrayMap) (obj2)).size(); j++)
        {
            obj = (TransitionValues)((SimpleArrayMap) (obj2)).mArray[(j << 1) + 1];
            if (((Transition) (obj1)).isValidTarget(((TransitionValues) (obj)).view))
            {
                ((Transition) (obj1)).mEndValuesList.add(obj);
                ((Transition) (obj1)).mStartValuesList.add(null);
            }
        }

        obj = (ArrayMap)Transition.sRunningAnimators.get();
        if (obj == null)
        {
            obj = new ArrayMap();
            Transition.sRunningAnimators.set(obj);
        }
        j = ((SimpleArrayMap) (obj)).size();
        obj2 = ViewUtils.getWindowId(runningTransitions);
        j--;
        while (j >= 0) 
        {
            obj3 = (Animator)((SimpleArrayMap) (obj)).mArray[j << 1];
            if (obj3 == null)
            {
                continue;
            }
            obj4 = (es)((SimpleArrayMap) (obj)).get(obj3);
            if (obj4 == null || ((es) (obj4)).es == null || !obj2.equals(((es) (obj4)).Id))
            {
                continue;
            }
            obj5 = ((Id) (obj4)).;
            obj7 = (() (obj4)).;
            obj6 = ((Transition) (obj1)).getTransitionValues(((View) (obj7)), true);
            obj7 = ((Transition) (obj1)).getMatchedTransitionValues(((View) (obj7)), true);
            if ((obj6 != null || obj7 != null) && ((alues) (obj4)).tion.isTransitionRequired(((TransitionValues) (obj5)), ((TransitionValues) (obj7))))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (((Animator) (obj3)).isRunning() || ((Animator) (obj3)).isStarted())
                {
                    ((Animator) (obj3)).cancel();
                } else
                {
                    ((SimpleArrayMap) (obj)).remove(obj3);
                }
            }
            j--;
        }
        ((Transition) (obj1)).createAnimators(runningTransitions, ((Transition) (obj1)).mStartValues, ((Transition) (obj1)).mEndValues, ((Transition) (obj1)).mStartValuesList, ((Transition) (obj1)).mEndValuesList);
        ((Transition) (obj1)).runAnimators();
        return true;
    }

    public final void onViewAttachedToWindow(View view)
    {
    }

    public final void onViewDetachedFromWindow(View view)
    {
        mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
        mSceneRoot.removeOnAttachStateChangeListener(this);
        TransitionManager.sPendingTransitions.remove(mSceneRoot);
        view = (ArrayList)TransitionManager.getRunningTransitions().get(mSceneRoot);
        if (view != null && view.size() > 0)
        {
            view = (ArrayList)view;
            int j = view.size();
            for (int i = 0; i < j;)
            {
                Object obj = view.get(i);
                i++;
                ((Transition)obj).resume(mSceneRoot);
            }

        }
        mTransition.clearValues(true);
    }

    _cls1(Transition transition, ViewGroup viewgroup)
    {
        mTransition = transition;
        mSceneRoot = viewgroup;
    }
}
