// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ContainerHelpers;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package android.support.transition:
//            TransitionValuesMaps, TransitionValues, TransitionPropagation, ViewUtils, 
//            PathMotion, TransitionSet

public abstract class Transition
    implements Cloneable
{

    private static final int DEFAULT_MATCH_ORDER[] = {
        2, 1, 3, 4
    };
    private static final PathMotion STRAIGHT_PATH_MOTION = new _cls1();
    public static ThreadLocal sRunningAnimators = new ThreadLocal();
    private ArrayList mAnimators;
    public boolean mCanRemoveViews;
    public ArrayList mCurrentAnimators;
    public long mDuration;
    public TransitionValuesMaps mEndValues;
    public ArrayList mEndValuesList;
    private boolean mEnded;
    public EpicenterCallback mEpicenterCallback;
    public TimeInterpolator mInterpolator;
    private ArrayList mListeners;
    public int mMatchOrder[];
    private String mName;
    private int mNumInstances;
    public TransitionSet mParent;
    public PathMotion mPathMotion;
    private boolean mPaused;
    public TransitionPropagation mPropagation;
    public long mStartDelay;
    public TransitionValuesMaps mStartValues;
    public ArrayList mStartValuesList;
    private ArrayList mTargetChildExcludes;
    private ArrayList mTargetExcludes;
    private ArrayList mTargetIdChildExcludes;
    private ArrayList mTargetIdExcludes;
    public ArrayList mTargetIds;
    private ArrayList mTargetNameExcludes;
    private ArrayList mTargetNames;
    private ArrayList mTargetTypeChildExcludes;
    private ArrayList mTargetTypeExcludes;
    private ArrayList mTargetTypes;
    public ArrayList mTargets;

    public Transition()
    {
        mName = getClass().getName();
        mStartDelay = -1L;
        mDuration = -1L;
        mInterpolator = null;
        mTargetIds = new ArrayList();
        mTargets = new ArrayList();
        mTargetNames = null;
        mTargetTypes = null;
        mTargetIdExcludes = null;
        mTargetExcludes = null;
        mTargetTypeExcludes = null;
        mTargetNameExcludes = null;
        mTargetIdChildExcludes = null;
        mTargetChildExcludes = null;
        mTargetTypeChildExcludes = null;
        mStartValues = new TransitionValuesMaps();
        mEndValues = new TransitionValuesMaps();
        mParent = null;
        mMatchOrder = DEFAULT_MATCH_ORDER;
        mCanRemoveViews = false;
        mCurrentAnimators = new ArrayList();
        mNumInstances = 0;
        mPaused = false;
        mEnded = false;
        mListeners = null;
        mAnimators = new ArrayList();
        mPathMotion = STRAIGHT_PATH_MOTION;
    }

    private static void addViewValues(TransitionValuesMaps transitionvaluesmaps, View view, TransitionValues transitionvalues)
    {
        long l;
label0:
        {
            transitionvaluesmaps.mViewValues.put(view, transitionvalues);
            int i = view.getId();
            if (i >= 0)
            {
                if (transitionvaluesmaps.mIdValues.indexOfKey(i) >= 0)
                {
                    transitionvaluesmaps.mIdValues.put(i, null);
                } else
                {
                    transitionvaluesmaps.mIdValues.put(i, view);
                }
            }
            transitionvalues = ViewCompat.getTransitionName(view);
            if (transitionvalues != null)
            {
                if (transitionvaluesmaps.mNameValues.containsKey(transitionvalues))
                {
                    transitionvaluesmaps.mNameValues.put(transitionvalues, null);
                } else
                {
                    transitionvaluesmaps.mNameValues.put(transitionvalues, view);
                }
            }
            if (view.getParent() instanceof ListView)
            {
                transitionvalues = (ListView)view.getParent();
                if (transitionvalues.getAdapter().hasStableIds())
                {
                    l = transitionvalues.getItemIdAtPosition(transitionvalues.getPositionForView(view));
                    transitionvalues = transitionvaluesmaps.mItemIdValues;
                    if (((LongSparseArray) (transitionvalues)).mGarbage)
                    {
                        transitionvalues.gc();
                    }
                    if (ContainerHelpers.binarySearch(((LongSparseArray) (transitionvalues)).mKeys, ((LongSparseArray) (transitionvalues)).mSize, l) < 0)
                    {
                        break label0;
                    }
                    view = transitionvaluesmaps.mItemIdValues;
                    i = ContainerHelpers.binarySearch(((LongSparseArray) (view)).mKeys, ((LongSparseArray) (view)).mSize, l);
                    if (i < 0 || ((LongSparseArray) (view)).mValues[i] == LongSparseArray.DELETED)
                    {
                        view = null;
                    } else
                    {
                        view = ((View) (((LongSparseArray) (view)).mValues[i]));
                    }
                    view = (View)view;
                    if (view != null)
                    {
                        ViewCompat.setHasTransientState(view, false);
                        transitionvaluesmaps.mItemIdValues.put(l, null);
                    }
                }
            }
            return;
        }
        ViewCompat.setHasTransientState(view, true);
        transitionvaluesmaps.mItemIdValues.put(l, view);
    }

    private final void captureHierarchy(View view, boolean flag)
    {
        if (view != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        view.getId();
        if (view.getParent() instanceof ViewGroup)
        {
            TransitionValues transitionvalues = new TransitionValues();
            transitionvalues.view = view;
            int i;
            if (flag)
            {
                captureStartValues(transitionvalues);
            } else
            {
                captureEndValues(transitionvalues);
            }
            transitionvalues.mTargetedTransitions.add(this);
            capturePropagationValues(transitionvalues);
            if (flag)
            {
                addViewValues(mStartValues, view, transitionvalues);
            } else
            {
                addViewValues(mEndValues, view, transitionvalues);
            }
        }
        if (view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            i = 0;
            while (i < view.getChildCount()) 
            {
                captureHierarchy(view.getChildAt(i), flag);
                i++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    private static boolean isValueChanged(TransitionValues transitionvalues, TransitionValues transitionvalues1, String s)
    {
        transitionvalues = ((TransitionValues) (transitionvalues.values.get(s)));
        for (transitionvalues1 = ((TransitionValues) (transitionvalues1.values.get(s))); (transitionvalues != null || transitionvalues1 != null) && (transitionvalues == null || transitionvalues1 == null || !transitionvalues.equals(transitionvalues1));)
        {
            return true;
        }

        return false;
    }

    public Transition addListener(TransitionListener transitionlistener)
    {
        if (mListeners == null)
        {
            mListeners = new ArrayList();
        }
        mListeners.add(transitionlistener);
        return this;
    }

    public Transition addTarget(View view)
    {
        mTargets.add(view);
        return this;
    }

    public abstract void captureEndValues(TransitionValues transitionvalues);

    void capturePropagationValues(TransitionValues transitionvalues)
    {
        boolean flag = false;
        if (mPropagation == null || transitionvalues.values.isEmpty()) goto _L2; else goto _L1
_L1:
        String as[] = mPropagation.getPropagationProperties();
        if (as != null) goto _L3; else goto _L2
_L2:
        return;
_L3:
        int i = 0;
_L6:
        if (i >= as.length)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        if (transitionvalues.values.containsKey(as[i])) goto _L5; else goto _L4
_L4:
        i = ((flag) ? 1 : 0);
_L7:
        if (i == 0)
        {
            mPropagation.captureValues$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURIMC5M7APBJ7CKLC___0();
            return;
        }
          goto _L2
_L5:
        i++;
          goto _L6
        i = 1;
          goto _L7
    }

    public abstract void captureStartValues(TransitionValues transitionvalues);

    final void captureValues(ViewGroup viewgroup, boolean flag)
    {
        boolean flag1 = false;
        clearValues(flag);
        if (mTargetIds.size() > 0 || mTargets.size() > 0)
        {
            int i = 0;
            int j;
            do
            {
                j = ((flag1) ? 1 : 0);
                if (i >= mTargetIds.size())
                {
                    break;
                }
                View view = viewgroup.findViewById(((Integer)mTargetIds.get(i)).intValue());
                if (view != null)
                {
                    TransitionValues transitionvalues1 = new TransitionValues();
                    transitionvalues1.view = view;
                    if (flag)
                    {
                        captureStartValues(transitionvalues1);
                    } else
                    {
                        captureEndValues(transitionvalues1);
                    }
                    transitionvalues1.mTargetedTransitions.add(this);
                    capturePropagationValues(transitionvalues1);
                    if (flag)
                    {
                        addViewValues(mStartValues, view, transitionvalues1);
                    } else
                    {
                        addViewValues(mEndValues, view, transitionvalues1);
                    }
                }
                i++;
            } while (true);
            while (j < mTargets.size()) 
            {
                viewgroup = (View)mTargets.get(j);
                TransitionValues transitionvalues = new TransitionValues();
                transitionvalues.view = viewgroup;
                if (flag)
                {
                    captureStartValues(transitionvalues);
                } else
                {
                    captureEndValues(transitionvalues);
                }
                transitionvalues.mTargetedTransitions.add(this);
                capturePropagationValues(transitionvalues);
                if (flag)
                {
                    addViewValues(mStartValues, viewgroup, transitionvalues);
                } else
                {
                    addViewValues(mEndValues, viewgroup, transitionvalues);
                }
                j++;
            }
        } else
        {
            captureHierarchy(viewgroup, flag);
        }
    }

    final void clearValues(boolean flag)
    {
        if (flag)
        {
            mStartValues.mViewValues.clear();
            mStartValues.mIdValues.clear();
            mStartValues.mItemIdValues.clear();
            return;
        } else
        {
            mEndValues.mViewValues.clear();
            mEndValues.mIdValues.clear();
            mEndValues.mItemIdValues.clear();
            return;
        }
    }

    public Transition clone()
    {
        Transition transition;
        try
        {
            transition = (Transition)super.clone();
            transition.mAnimators = new ArrayList();
            transition.mStartValues = new TransitionValuesMaps();
            transition.mEndValues = new TransitionValuesMaps();
            transition.mStartValuesList = null;
            transition.mEndValuesList = null;
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            return null;
        }
        return transition;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return null;
    }

    protected void createAnimators(ViewGroup viewgroup, TransitionValuesMaps transitionvaluesmaps, TransitionValuesMaps transitionvaluesmaps1, ArrayList arraylist, ArrayList arraylist1)
    {
        ArrayMap arraymap;
        SparseIntArray sparseintarray;
        int i;
        int j1;
        long l1;
        arraymap = (ArrayMap)sRunningAnimators.get();
        if (arraymap == null)
        {
            arraymap = new ArrayMap();
            sRunningAnimators.set(arraymap);
        }
        l1 = 0x7fffffffffffffffL;
        sparseintarray = new SparseIntArray();
        j1 = arraylist.size();
        i = 0;
_L16:
        Object obj1;
        if (i >= j1)
        {
            break MISSING_BLOCK_LABEL_580;
        }
        transitionvaluesmaps = (TransitionValues)arraylist.get(i);
        TransitionValues transitionvalues = (TransitionValues)arraylist1.get(i);
        if (transitionvaluesmaps != null && !((TransitionValues) (transitionvaluesmaps)).mTargetedTransitions.contains(this))
        {
            transitionvaluesmaps = null;
        }
        obj1 = transitionvalues;
        if (transitionvalues != null)
        {
            obj1 = transitionvalues;
            if (!transitionvalues.mTargetedTransitions.contains(this))
            {
                obj1 = null;
            }
        }
        if (transitionvaluesmaps != null) goto _L2; else goto _L1
_L1:
        long l2 = l1;
        if (obj1 == null) goto _L3; else goto _L2
_L2:
        Object obj;
        TransitionValues transitionvalues1;
        String as[];
        int k;
        if (transitionvaluesmaps == null || obj1 == null || isTransitionRequired(transitionvaluesmaps, ((TransitionValues) (obj1))))
        {
            k = 1;
        } else
        {
            k = 0;
        }
        l2 = l1;
        if (k == 0) goto _L3; else goto _L4
_L4:
        obj = createAnimator(viewgroup, transitionvaluesmaps, ((TransitionValues) (obj1)));
        l2 = l1;
        if (obj == null) goto _L3; else goto _L5
_L5:
        transitionvalues1 = null;
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_561;
        }
        obj1 = ((TransitionValues) (obj1)).view;
        as = getTransitionProperties();
        transitionvaluesmaps = transitionvalues1;
        if (obj1 == null) goto _L7; else goto _L6
_L6:
        transitionvaluesmaps = transitionvalues1;
        if (as == null) goto _L7; else goto _L8
_L8:
        transitionvaluesmaps = transitionvalues1;
        if (as.length <= 0) goto _L7; else goto _L9
_L9:
        int l;
        int k1;
        transitionvaluesmaps = new TransitionValues();
        transitionvaluesmaps.view = ((View) (obj1));
        transitionvalues1 = (TransitionValues)transitionvaluesmaps1.mViewValues.get(obj1);
        if (transitionvalues1 != null)
        {
            for (k = 0; k < as.length; k++)
            {
                ((TransitionValues) (transitionvaluesmaps)).values.put(as[k], transitionvalues1.values.get(as[k]));
            }

        }
        k1 = arraymap.size();
        l = 0;
_L13:
        if (l >= k1) goto _L7; else goto _L10
_L10:
        AnimationInfo animationinfo = (AnimationInfo)arraymap.get((Animator)((SimpleArrayMap) (arraymap)).mArray[l << 1]);
        if (animationinfo.mValues == null || animationinfo.mView != obj1 || !animationinfo.mName.equals(mName) || !animationinfo.mValues.equals(transitionvaluesmaps)) goto _L12; else goto _L11
_L11:
        obj = null;
        Object obj2 = obj1;
        obj1 = obj;
        obj = transitionvaluesmaps;
        transitionvaluesmaps = ((TransitionValuesMaps) (obj2));
_L14:
        l2 = l1;
        if (obj1 != null)
        {
            if (mPropagation != null)
            {
                l2 = mPropagation.getStartDelay$51662RJ4E9NMIP1FEPKMATPFAPKMATQ7E9NNAS1R9HGMSP3IDTKM8BRJELO70RRIEGNN8SJ1DPPMIT39DTN2UL3IC5N76QBKD5NMSEQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EQCC5N68SJFD5I2USRLE1O6USJK5TQ74OBEEDKN8QBFDONL8SJ1DPPMIT39DTN5COBCELIN6EP998______0();
                sparseintarray.put(mAnimators.size(), (int)l2);
                l1 = Math.min(l2, l1);
            }
            arraymap.put(obj1, new AnimationInfo(transitionvaluesmaps, mName, this, ViewUtils.getWindowId(viewgroup), ((TransitionValues) (obj))));
            mAnimators.add(obj1);
            l2 = l1;
        }
_L3:
        i++;
        l1 = l2;
        continue; /* Loop/switch isn't completed */
_L12:
        l++;
          goto _L13
_L7:
        Object obj3 = obj1;
        obj1 = obj;
        obj = transitionvaluesmaps;
        transitionvaluesmaps = ((TransitionValuesMaps) (obj3));
          goto _L14
        transitionvaluesmaps = ((TransitionValues) (transitionvaluesmaps)).view;
        Object obj4 = null;
        obj1 = obj;
        obj = obj4;
          goto _L14
        if (l1 != 0L)
        {
            for (int j = 0; j < sparseintarray.size(); j++)
            {
                int i1 = sparseintarray.keyAt(j);
                viewgroup = (Animator)mAnimators.get(i1);
                viewgroup.setStartDelay(((long)sparseintarray.valueAt(j) - l1) + viewgroup.getStartDelay());
            }

        }
        return;
        if (true) goto _L16; else goto _L15
_L15:
    }

    protected final void end()
    {
        mNumInstances = mNumInstances - 1;
        if (mNumInstances == 0)
        {
            if (mListeners != null && mListeners.size() > 0)
            {
                ArrayList arraylist = (ArrayList)mListeners.clone();
                int k = arraylist.size();
                for (int i = 0; i < k; i++)
                {
                    ((TransitionListener)arraylist.get(i)).onTransitionEnd(this);
                }

            }
            int j = 0;
            do
            {
                Object obj = mStartValues.mItemIdValues;
                if (((LongSparseArray) (obj)).mGarbage)
                {
                    ((LongSparseArray) (obj)).gc();
                }
                if (j >= ((LongSparseArray) (obj)).mSize)
                {
                    break;
                }
                obj = mStartValues.mItemIdValues;
                if (((LongSparseArray) (obj)).mGarbage)
                {
                    ((LongSparseArray) (obj)).gc();
                }
                obj = (View)((LongSparseArray) (obj)).mValues[j];
                if (obj != null)
                {
                    ViewCompat.setHasTransientState(((View) (obj)), false);
                }
                j++;
            } while (true);
            j = 0;
            do
            {
                Object obj1 = mEndValues.mItemIdValues;
                if (((LongSparseArray) (obj1)).mGarbage)
                {
                    ((LongSparseArray) (obj1)).gc();
                }
                if (j >= ((LongSparseArray) (obj1)).mSize)
                {
                    break;
                }
                obj1 = mEndValues.mItemIdValues;
                if (((LongSparseArray) (obj1)).mGarbage)
                {
                    ((LongSparseArray) (obj1)).gc();
                }
                obj1 = (View)((LongSparseArray) (obj1)).mValues[j];
                if (obj1 != null)
                {
                    ViewCompat.setHasTransientState(((View) (obj1)), false);
                }
                j++;
            } while (true);
            mEnded = true;
        }
    }

    final TransitionValues getMatchedTransitionValues(View view, boolean flag)
    {
        Object obj;
        ArrayList arraylist;
        for (obj = this; ((Transition) (obj)).mParent != null; obj = ((Transition) (obj)).mParent) { }
        if (flag)
        {
            arraylist = ((Transition) (obj)).mStartValuesList;
        } else
        {
            arraylist = ((Transition) (obj)).mEndValuesList;
        }
        if (arraylist != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        int i;
        int j;
        j = arraylist.size();
        i = 0;
_L6:
        TransitionValues transitionvalues;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_134;
        }
        transitionvalues = (TransitionValues)arraylist.get(i);
        if (transitionvalues == null) goto _L1; else goto _L3
_L3:
        if (transitionvalues.view != view) goto _L5; else goto _L4
_L4:
        if (i >= 0)
        {
            if (flag)
            {
                view = ((Transition) (obj)).mEndValuesList;
            } else
            {
                view = ((Transition) (obj)).mStartValuesList;
            }
            view = (TransitionValues)view.get(i);
        } else
        {
            view = null;
        }
        return view;
_L5:
        i++;
          goto _L6
        i = -1;
          goto _L4
    }

    public String[] getTransitionProperties()
    {
        return null;
    }

    public final TransitionValues getTransitionValues(View view, boolean flag)
    {
        Object obj;
        for (obj = this; ((Transition) (obj)).mParent != null; obj = ((Transition) (obj)).mParent) { }
        if (flag)
        {
            obj = ((Transition) (obj)).mStartValues;
        } else
        {
            obj = ((Transition) (obj)).mEndValues;
        }
        return (TransitionValues)((TransitionValuesMaps) (obj)).mViewValues.get(view);
    }

    public boolean isTransitionRequired(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
label0:
        {
            if (transitionvalues == null || transitionvalues1 == null)
            {
                break label0;
            }
            String as[] = getTransitionProperties();
            if (as != null)
            {
                int j = as.length;
                for (int i = 0; i < j; i++)
                {
                    if (isValueChanged(transitionvalues, transitionvalues1, as[i]))
                    {
                        return true;
                    }
                }

                return false;
            }
            Iterator iterator = transitionvalues.values.keySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
            } while (!isValueChanged(transitionvalues, transitionvalues1, (String)iterator.next()));
            return true;
        }
        return false;
    }

    final boolean isValidTarget(View view)
    {
        for (int i = view.getId(); mTargetIds.size() == 0 && mTargets.size() == 0 || mTargetIds.contains(Integer.valueOf(i)) || mTargets.contains(view);)
        {
            return true;
        }

        return false;
    }

    public void pause(View view)
    {
        if (!mEnded)
        {
            ArrayMap arraymap = (ArrayMap)sRunningAnimators.get();
            if (arraymap == null)
            {
                arraymap = new ArrayMap();
                sRunningAnimators.set(arraymap);
            }
            int i = arraymap.size();
            view = ViewUtils.getWindowId(view);
            for (i--; i >= 0; i--)
            {
                AnimationInfo animationinfo = (AnimationInfo)((SimpleArrayMap) (arraymap)).mArray[(i << 1) + 1];
                if (animationinfo.mView != null && view.equals(animationinfo.mWindowId))
                {
                    ((Animator)((SimpleArrayMap) (arraymap)).mArray[i << 1]).pause();
                }
            }

            if (mListeners != null && mListeners.size() > 0)
            {
                view = (ArrayList)mListeners.clone();
                int k = view.size();
                for (int j = 0; j < k; j++)
                {
                    ((TransitionListener)view.get(j))._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0();
                }

            }
            mPaused = true;
        }
    }

    public Transition removeListener(TransitionListener transitionlistener)
    {
        if (mListeners != null)
        {
            mListeners.remove(transitionlistener);
            if (mListeners.size() == 0)
            {
                mListeners = null;
                return this;
            }
        }
        return this;
    }

    public Transition removeTarget(View view)
    {
        mTargets.remove(view);
        return this;
    }

    public void resume(View view)
    {
        if (mPaused)
        {
            if (!mEnded)
            {
                ArrayMap arraymap = (ArrayMap)sRunningAnimators.get();
                if (arraymap == null)
                {
                    arraymap = new ArrayMap();
                    sRunningAnimators.set(arraymap);
                }
                int i = arraymap.size();
                view = ViewUtils.getWindowId(view);
                for (i--; i >= 0; i--)
                {
                    AnimationInfo animationinfo = (AnimationInfo)((SimpleArrayMap) (arraymap)).mArray[(i << 1) + 1];
                    if (animationinfo.mView != null && view.equals(animationinfo.mWindowId))
                    {
                        ((Animator)((SimpleArrayMap) (arraymap)).mArray[i << 1]).resume();
                    }
                }

                if (mListeners != null && mListeners.size() > 0)
                {
                    view = (ArrayList)mListeners.clone();
                    int k = view.size();
                    for (int j = 0; j < k; j++)
                    {
                        ((TransitionListener)view.get(j))._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0();
                    }

                }
            }
            mPaused = false;
        }
    }

    protected void runAnimators()
    {
        start();
        final ArrayMap runningAnimators = (ArrayMap)sRunningAnimators.get();
        if (runningAnimators == null)
        {
            runningAnimators = new ArrayMap();
            sRunningAnimators.set(runningAnimators);
        }
        ArrayList arraylist = (ArrayList)mAnimators;
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
            obj = (Animator)obj;
            i = j;
            if (runningAnimators.containsKey(obj))
            {
                start();
                i = j;
                if (obj != null)
                {
                    ((Animator) (obj)).addListener(new _cls2());
                    if (obj == null)
                    {
                        end();
                        i = j;
                    } else
                    {
                        if (mDuration >= 0L)
                        {
                            ((Animator) (obj)).setDuration(mDuration);
                        }
                        if (mStartDelay >= 0L)
                        {
                            ((Animator) (obj)).setStartDelay(mStartDelay);
                        }
                        if (mInterpolator != null)
                        {
                            ((Animator) (obj)).setInterpolator(mInterpolator);
                        }
                        ((Animator) (obj)).addListener(new _cls3());
                        ((Animator) (obj)).start();
                        i = j;
                    }
                }
            }
        } while (true);
        mAnimators.clear();
        end();
    }

    public Transition setDuration(long l)
    {
        mDuration = l;
        return this;
    }

    public void setEpicenterCallback(EpicenterCallback epicentercallback)
    {
        mEpicenterCallback = epicentercallback;
    }

    public Transition setInterpolator(TimeInterpolator timeinterpolator)
    {
        mInterpolator = timeinterpolator;
        return this;
    }

    public void setPathMotion(PathMotion pathmotion)
    {
        if (pathmotion == null)
        {
            mPathMotion = STRAIGHT_PATH_MOTION;
            return;
        } else
        {
            mPathMotion = pathmotion;
            return;
        }
    }

    public void setPropagation(TransitionPropagation transitionpropagation)
    {
        mPropagation = transitionpropagation;
    }

    public Transition setStartDelay(long l)
    {
        mStartDelay = l;
        return this;
    }

    protected final void start()
    {
        if (mNumInstances == 0)
        {
            if (mListeners != null && mListeners.size() > 0)
            {
                ArrayList arraylist = (ArrayList)mListeners.clone();
                int j = arraylist.size();
                for (int i = 0; i < j; i++)
                {
                    ((TransitionListener)arraylist.get(i))._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0();
                }

            }
            mEnded = false;
        }
        mNumInstances = mNumInstances + 1;
    }

    public String toString()
    {
        return toString("");
    }

    String toString(String s)
    {
        String s1;
label0:
        {
            boolean flag = false;
            s1 = (new StringBuilder()).append(s).append(getClass().getSimpleName()).append("@").append(Integer.toHexString(hashCode())).append(": ").toString();
            s = s1;
            if (mDuration != -1L)
            {
                s = (new StringBuilder()).append(s1).append("dur(").append(mDuration).append(") ").toString();
            }
            s1 = s;
            if (mStartDelay != -1L)
            {
                s1 = (new StringBuilder()).append(s).append("dly(").append(mStartDelay).append(") ").toString();
            }
            s = s1;
            if (mInterpolator != null)
            {
                s = (new StringBuilder()).append(s1).append("interp(").append(mInterpolator).append(") ").toString();
            }
            if (mTargetIds.size() <= 0)
            {
                s1 = s;
                if (mTargets.size() <= 0)
                {
                    break label0;
                }
            }
            s = (new StringBuilder()).append(s).append("tgts(").toString();
            if (mTargetIds.size() > 0)
            {
                int i = 0;
                do
                {
                    s1 = s;
                    if (i >= mTargetIds.size())
                    {
                        break;
                    }
                    s1 = s;
                    if (i > 0)
                    {
                        s1 = (new StringBuilder()).append(s).append(", ").toString();
                    }
                    s = (new StringBuilder()).append(s1).append(mTargetIds.get(i)).toString();
                    i++;
                } while (true);
            } else
            {
                s1 = s;
            }
            s = s1;
            if (mTargets.size() > 0)
            {
                int j = ((flag) ? 1 : 0);
                do
                {
                    s = s1;
                    if (j >= mTargets.size())
                    {
                        break;
                    }
                    s = s1;
                    if (j > 0)
                    {
                        s = (new StringBuilder()).append(s1).append(", ").toString();
                    }
                    s1 = (new StringBuilder()).append(s).append(mTargets.get(j)).toString();
                    j++;
                } while (true);
            }
            s1 = (new StringBuilder()).append(s).append(")").toString();
        }
        return s1;
    }


    private class AnimationInfo
    {

        public String mName;
        public Transition mTransition;
        public TransitionValues mValues;
        public View mView;
        public WindowIdImpl mWindowId;

        AnimationInfo(View view, String s, Transition transition, WindowIdImpl windowidimpl, TransitionValues transitionvalues)
        {
            mView = view;
            mName = s;
            mValues = transitionvalues;
            mWindowId = windowidimpl;
            mTransition = transition;
        }
    }


    private class TransitionListener
    {

        public abstract void onTransitionEnd(Transition transition);

        public abstract void onTransitionPause$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0();

        public abstract void onTransitionResume$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0();

        public abstract void onTransitionStart$51662RJ4E9NMIP1FEDQN0S3FE9Q2UT3IC5N76QBKD5NMSBQKE9GMSSR9EHKMURHR55B0____0();
    }


    private class _cls2 extends AnimatorListenerAdapter
    {

        private final Transition this$0;
        private final ArrayMap val$runningAnimators;

        public final void onAnimationEnd(Animator animator)
        {
            runningAnimators.remove(animator);
            mCurrentAnimators.remove(animator);
        }

        public final void onAnimationStart(Animator animator)
        {
            mCurrentAnimators.add(animator);
        }

        _cls2()
        {
            this$0 = Transition.this;
            runningAnimators = arraymap;
            super();
        }
    }


    private class _cls3 extends AnimatorListenerAdapter
    {

        private final Transition this$0;

        public final void onAnimationEnd(Animator animator)
        {
            end();
            animator.removeListener(this);
        }

        _cls3()
        {
            this$0 = Transition.this;
            super();
        }
    }


    private class _cls1 extends PathMotion
    {

        public final Path getPath(float f, float f1, float f2, float f3)
        {
            Path path = new Path();
            path.moveTo(f, f1);
            path.lineTo(f2, f3);
            return path;
        }

        _cls1()
        {
        }
    }

}
