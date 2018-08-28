// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.MapCollections;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionCompat21, Fragment, BackStackRecord, FragmentManagerImpl, 
//            FragmentTransitionImpl, FragmentContainer, FragmentHostCallback, OneShotPreDrawListener

final class FragmentTransition
{

    private static final int INVERSE_OPS[] = {
        0, 3, 0, 1, 5, 4, 7, 6, 9, 8
    };
    public static final FragmentTransitionImpl PLATFORM_IMPL = new FragmentTransitionCompat21();
    public static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();

    private static void addSharedElementsWithMatchingNames(ArrayList arraylist, ArrayMap arraymap, Collection collection)
    {
        for (int i = arraymap.size() - 1; i >= 0; i--)
        {
            View view = (View)((SimpleArrayMap) (arraymap)).mArray[(i << 1) + 1];
            if (collection.contains(ViewCompat.getTransitionName(view)))
            {
                arraylist.add(view);
            }
        }

    }

    private static void addToFirstInLastOut(BackStackRecord backstackrecord, BackStackRecord.Op op, SparseArray sparsearray, boolean flag, boolean flag1)
    {
        Fragment fragment = op.fragment;
        if (fragment != null) goto _L2; else goto _L1
_L1:
        int j;
        return;
_L2:
        if ((j = fragment.mContainerId) == 0) goto _L1; else goto _L3
_L3:
        int i;
        boolean flag2;
        boolean flag3;
        boolean flag5;
        if (flag)
        {
            i = INVERSE_OPS[op.cmd];
        } else
        {
            i = op.cmd;
        }
        i;
        JVM INSTR tableswitch 1 7: default 84
    //                   1 387
    //                   2 84
    //                   3 525
    //                   4 439
    //                   5 327
    //                   6 525
    //                   7 387;
           goto _L4 _L5 _L4 _L6 _L7 _L8 _L6 _L5
_L6:
        break MISSING_BLOCK_LABEL_525;
_L4:
        i = 0;
        flag2 = false;
        flag3 = false;
        flag5 = false;
_L10:
        Object obj = (FragmentContainerTransition)sparsearray.get(j);
        boolean flag4;
        if (flag5)
        {
            op = ((BackStackRecord.Op) (obj));
            if (obj == null)
            {
                op = new FragmentContainerTransition();
                sparsearray.put(j, op);
            }
            op.lastIn = fragment;
            op.lastInIsPop = flag;
            op.lastInTransaction = backstackrecord;
        } else
        {
            op = ((BackStackRecord.Op) (obj));
        }
        if (!flag1 && i != 0)
        {
            if (op != null && ((FragmentContainerTransition) (op)).firstOut == fragment)
            {
                op.firstOut = null;
            }
            obj = backstackrecord.mManager;
            if (fragment.mState <= 0 && ((FragmentManagerImpl) (obj)).mCurState > 0 && !backstackrecord.mReorderingAllowed)
            {
                ((FragmentManagerImpl) (obj)).makeActive(fragment);
                ((FragmentManagerImpl) (obj)).moveToState(fragment, 1, 0, 0, false);
            }
        }
        obj = op;
        if (!flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (op != null)
        {
            obj = op;
            if (((FragmentContainerTransition) (op)).firstOut != null)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        if (op == null)
        {
            op = new FragmentContainerTransition();
            sparsearray.put(j, op);
        }
        op.firstOut = fragment;
        op.firstOutIsPop = flag;
        op.firstOutTransaction = backstackrecord;
        obj = op;
        if (flag1 || !flag3 || obj == null || ((FragmentContainerTransition) (obj)).lastIn != fragment) goto _L1; else goto _L9
_L9:
        obj.lastIn = null;
        return;
_L8:
        if (flag1)
        {
            if (fragment.mHiddenChanged && !fragment.mHidden && fragment.mAdded)
            {
                flag5 = true;
            } else
            {
                flag5 = false;
            }
        } else
        {
            flag5 = fragment.mHidden;
        }
        i = 1;
        flag2 = false;
        flag3 = false;
          goto _L10
_L5:
        if (flag1)
        {
            flag5 = fragment.mIsNewlyAdded;
        } else
        if (!fragment.mAdded && !fragment.mHidden)
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        i = 1;
        flag2 = false;
        flag3 = false;
          goto _L10
_L7:
        if (flag1)
        {
            if (fragment.mHiddenChanged && fragment.mAdded && fragment.mHidden)
            {
                i = 1;
            } else
            {
                i = 0;
            }
        } else
        if (fragment.mAdded && !fragment.mHidden)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        flag4 = false;
        flag2 = i;
        flag3 = true;
        flag5 = false;
        i = ((flag4) ? 1 : 0);
          goto _L10
        if (flag1)
        {
            if (!fragment.mAdded && fragment.mView != null && fragment.mView.getVisibility() == 0 && fragment.mPostponedAlpha >= 0.0F)
            {
                i = 1;
            } else
            {
                i = 0;
            }
        } else
        if (fragment.mAdded && !fragment.mHidden)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        flag4 = false;
        flag2 = i;
        flag3 = true;
        flag5 = false;
        i = ((flag4) ? 1 : 0);
          goto _L10
    }

    static void callSharedElementStartEnd(Fragment fragment, Fragment fragment1, boolean flag, ArrayMap arraymap, boolean flag1)
    {
        int j = 0;
        if (flag)
        {
            fragment = fragment1.mAnimationInfo;
        } else
        {
            fragment = fragment.mAnimationInfo;
        }
        if (false)
        {
            fragment = new ArrayList();
            fragment1 = new ArrayList();
            int i;
            if (arraymap == null)
            {
                i = 0;
            } else
            {
                i = arraymap.size();
            }
            for (; j < i; j++)
            {
                fragment1.add(((SimpleArrayMap) (arraymap)).mArray[j << 1]);
                fragment.add(((SimpleArrayMap) (arraymap)).mArray[(j << 1) + 1]);
            }

            if (flag1)
            {
                throw new NoSuchMethodError();
            } else
            {
                throw new NoSuchMethodError();
            }
        } else
        {
            return;
        }
    }

    private static boolean canHandleAll(FragmentTransitionImpl fragmenttransitionimpl, List list)
    {
        int j = list.size();
        for (int i = 0; i < j; i++)
        {
            if (!fragmenttransitionimpl.canHandle(list.get(i)))
            {
                return false;
            }
        }

        return true;
    }

    static ArrayMap captureInSharedElements(FragmentTransitionImpl fragmenttransitionimpl, ArrayMap arraymap, Object obj, FragmentContainerTransition fragmentcontainertransition)
    {
        Fragment fragment = fragmentcontainertransition.lastIn;
        View view = fragment.mView;
        if (arraymap.isEmpty() || obj == null || view == null)
        {
            arraymap.clear();
            return null;
        }
        obj = new ArrayMap();
        fragmenttransitionimpl.findNamedViews(((Map) (obj)), view);
        fragmenttransitionimpl = fragmentcontainertransition.lastInTransaction;
        if (fragmentcontainertransition.lastInIsPop)
        {
            fragmentcontainertransition = fragment.mAnimationInfo;
            fragmenttransitionimpl = ((BackStackRecord) (fragmenttransitionimpl)).mSharedElementSourceNames;
        } else
        {
            fragmentcontainertransition = fragment.mAnimationInfo;
            fragmenttransitionimpl = ((BackStackRecord) (fragmenttransitionimpl)).mSharedElementTargetNames;
        }
        if (fragmenttransitionimpl != null)
        {
            MapCollections.retainAllHelper(((Map) (obj)), fragmenttransitionimpl);
            MapCollections.retainAllHelper(((Map) (obj)), arraymap.values());
        }
        if (false)
        {
            throw new NoSuchMethodError();
        }
        for (int i = arraymap.size() - 1; i >= 0; i--)
        {
            if (!((SimpleArrayMap) (obj)).containsKey((String)((SimpleArrayMap) (arraymap)).mArray[(i << 1) + 1]))
            {
                arraymap.removeAt(i);
            }
        }

        return ((ArrayMap) (obj));
    }

    private static ArrayMap captureOutSharedElements(FragmentTransitionImpl fragmenttransitionimpl, ArrayMap arraymap, Object obj, FragmentContainerTransition fragmentcontainertransition)
    {
        if (arraymap.isEmpty() || obj == null)
        {
            arraymap.clear();
            return null;
        }
        Fragment fragment = fragmentcontainertransition.firstOut;
        obj = new ArrayMap();
        fragmenttransitionimpl.findNamedViews(((Map) (obj)), fragment.mView);
        fragmenttransitionimpl = fragmentcontainertransition.firstOutTransaction;
        if (fragmentcontainertransition.firstOutIsPop)
        {
            fragmentcontainertransition = fragment.mAnimationInfo;
            fragmenttransitionimpl = ((BackStackRecord) (fragmenttransitionimpl)).mSharedElementTargetNames;
        } else
        {
            fragmentcontainertransition = fragment.mAnimationInfo;
            fragmenttransitionimpl = ((BackStackRecord) (fragmenttransitionimpl)).mSharedElementSourceNames;
        }
        MapCollections.retainAllHelper(((Map) (obj)), fragmenttransitionimpl);
        if (false)
        {
            throw new NoSuchMethodError();
        } else
        {
            MapCollections.retainAllHelper(arraymap, ((ArrayMap) (obj)).keySet());
            return ((ArrayMap) (obj));
        }
    }

    private static FragmentTransitionImpl chooseImpl(Fragment fragment, Fragment fragment1)
    {
        ArrayList arraylist = new ArrayList();
        if (fragment != null)
        {
            Object obj;
            if (fragment.mAnimationInfo == null)
            {
                obj = null;
            } else
            {
                obj = fragment.mAnimationInfo.mExitTransition;
            }
            if (obj != null)
            {
                arraylist.add(obj);
            }
            obj = fragment.getReturnTransition();
            if (obj != null)
            {
                arraylist.add(obj);
            }
            fragment = ((Fragment) (fragment.getSharedElementReturnTransition()));
            if (fragment != null)
            {
                arraylist.add(fragment);
            }
        }
        if (fragment1 != null)
        {
            if (fragment1.mAnimationInfo == null)
            {
                fragment = null;
            } else
            {
                fragment = ((Fragment) (fragment1.mAnimationInfo.mEnterTransition));
            }
            if (fragment != null)
            {
                arraylist.add(fragment);
            }
            fragment = ((Fragment) (fragment1.getReenterTransition()));
            if (fragment != null)
            {
                arraylist.add(fragment);
            }
            if (fragment1.mAnimationInfo == null)
            {
                fragment = null;
            } else
            {
                fragment = ((Fragment) (fragment1.mAnimationInfo.mSharedElementEnterTransition));
            }
            if (fragment != null)
            {
                arraylist.add(fragment);
            }
        }
        if (!arraylist.isEmpty())
        {
            if (PLATFORM_IMPL != null && canHandleAll(PLATFORM_IMPL, arraylist))
            {
                return PLATFORM_IMPL;
            }
            if (SUPPORT_IMPL != null && canHandleAll(SUPPORT_IMPL, arraylist))
            {
                return SUPPORT_IMPL;
            }
            if (PLATFORM_IMPL != null || SUPPORT_IMPL != null)
            {
                throw new IllegalArgumentException("Invalid Transition types");
            }
        }
        return null;
    }

    static ArrayList configureEnteringExitingViews(FragmentTransitionImpl fragmenttransitionimpl, Object obj, Fragment fragment, ArrayList arraylist, View view)
    {
        ArrayList arraylist1 = null;
        if (obj != null)
        {
            ArrayList arraylist2 = new ArrayList();
            fragment = fragment.mView;
            if (fragment != null)
            {
                fragmenttransitionimpl.captureTransitioningViews(arraylist2, fragment);
            }
            if (arraylist != null)
            {
                arraylist2.removeAll(arraylist);
            }
            arraylist1 = arraylist2;
            if (!arraylist2.isEmpty())
            {
                arraylist2.add(view);
                fragmenttransitionimpl.addTargets(obj, arraylist2);
                arraylist1 = arraylist2;
            }
        }
        return arraylist1;
    }

    static View getInEpicenterView(ArrayMap arraymap, FragmentContainerTransition fragmentcontainertransition, Object obj, boolean flag)
    {
        fragmentcontainertransition = fragmentcontainertransition.lastInTransaction;
        if (obj != null && arraymap != null && ((BackStackRecord) (fragmentcontainertransition)).mSharedElementSourceNames != null && !((BackStackRecord) (fragmentcontainertransition)).mSharedElementSourceNames.isEmpty())
        {
            if (flag)
            {
                fragmentcontainertransition = (String)((BackStackRecord) (fragmentcontainertransition)).mSharedElementSourceNames.get(0);
            } else
            {
                fragmentcontainertransition = (String)((BackStackRecord) (fragmentcontainertransition)).mSharedElementTargetNames.get(0);
            }
            return (View)arraymap.get(fragmentcontainertransition);
        } else
        {
            return null;
        }
    }

    private static Object getSharedElementTransition(FragmentTransitionImpl fragmenttransitionimpl, Fragment fragment, Fragment fragment1, boolean flag)
    {
        Object obj;
        obj = null;
        if (fragment == null || fragment1 == null)
        {
            return null;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        fragment1 = ((Fragment) (fragment1.getSharedElementReturnTransition()));
_L4:
        return fragmenttransitionimpl.wrapTransitionInSet(fragmenttransitionimpl.cloneTransition(fragment1));
_L2:
        fragment1 = obj;
        if (fragment.mAnimationInfo != null)
        {
            fragment1 = ((Fragment) (fragment.mAnimationInfo.mSharedElementEnterTransition));
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static Object mergeTransitions(FragmentTransitionImpl fragmenttransitionimpl, Object obj, Object obj1, Object obj2, Fragment fragment, boolean flag)
    {
        if (obj == null || obj1 == null || fragment == null) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        if (fragment.mAnimationInfo != null)
        {
            fragment = fragment.mAnimationInfo;
        }
_L2:
        return fragmenttransitionimpl.mergeTransitionsTogether(obj1, obj, obj2);
_L4:
        if (fragment.mAnimationInfo != null)
        {
            fragment = fragment.mAnimationInfo;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    private static FragmentTransitionImpl resolveSupportImpl()
    {
        FragmentTransitionImpl fragmenttransitionimpl;
        try
        {
            fragmenttransitionimpl = (FragmentTransitionImpl)Class.forName("android.support.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception exception)
        {
            return null;
        }
        return fragmenttransitionimpl;
    }

    private static void setOutEpicenter(FragmentTransitionImpl fragmenttransitionimpl, Object obj, Object obj1, ArrayMap arraymap, boolean flag, BackStackRecord backstackrecord)
    {
        if (backstackrecord.mSharedElementSourceNames != null && !backstackrecord.mSharedElementSourceNames.isEmpty())
        {
            if (flag)
            {
                backstackrecord = (String)backstackrecord.mSharedElementTargetNames.get(0);
            } else
            {
                backstackrecord = (String)backstackrecord.mSharedElementSourceNames.get(0);
            }
            arraymap = (View)arraymap.get(backstackrecord);
            fragmenttransitionimpl.setEpicenter(obj, arraymap);
            if (obj1 != null)
            {
                fragmenttransitionimpl.setEpicenter(obj1, arraymap);
            }
        }
    }

    static void setViewVisibility(ArrayList arraylist, int i)
    {
        if (arraylist != null)
        {
            int j = arraylist.size() - 1;
            while (j >= 0) 
            {
                ((View)arraylist.get(j)).setVisibility(i);
                j--;
            }
        }
    }

    static void startTransitions(FragmentManagerImpl fragmentmanagerimpl, ArrayList arraylist, ArrayList arraylist1, int i, int j, boolean flag)
    {
        if (fragmentmanagerimpl.mCurState > 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        SparseArray sparsearray;
        sparsearray = new SparseArray();
        for (int k = i; k < j; k++)
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(k);
            if (((Boolean)arraylist1.get(k)).booleanValue())
            {
                if (!backstackrecord.mManager.mContainer.onHasView())
                {
                    continue;
                }
                for (int i1 = backstackrecord.mOps.size() - 1; i1 >= 0; i1--)
                {
                    addToFirstInLastOut(backstackrecord, (BackStackRecord.Op)backstackrecord.mOps.get(i1), sparsearray, true, flag);
                }

                continue;
            }
            int j2 = backstackrecord.mOps.size();
            for (int j1 = 0; j1 < j2; j1++)
            {
                addToFirstInLastOut(backstackrecord, (BackStackRecord.Op)backstackrecord.mOps.get(j1), sparsearray, false, flag);
            }

        }

        if (sparsearray.size() == 0) goto _L1; else goto _L3
_L3:
        final View nonExistentView;
        int l;
        int i3;
        nonExistentView = new View(fragmentmanagerimpl.mHost.mContext);
        i3 = sparsearray.size();
        l = 0;
_L9:
        if (l >= i3) goto _L1; else goto _L4
_L4:
        int j3 = sparsearray.keyAt(l);
        final ArrayMap nameOverrides = new ArrayMap();
label0:
        for (int k1 = j - 1; k1 >= i; k1--)
        {
            Object obj = (BackStackRecord)arraylist.get(k1);
            if (!((BackStackRecord) (obj)).interactsWith(j3))
            {
                continue;
            }
            boolean flag1 = ((Boolean)arraylist1.get(k1)).booleanValue();
            if (((BackStackRecord) (obj)).mSharedElementSourceNames == null)
            {
                continue;
            }
            int k3 = ((BackStackRecord) (obj)).mSharedElementSourceNames.size();
            ArrayList arraylist2;
            ArrayList arraylist3;
            int k2;
            if (flag1)
            {
                arraylist2 = ((BackStackRecord) (obj)).mSharedElementSourceNames;
                arraylist3 = ((BackStackRecord) (obj)).mSharedElementTargetNames;
            } else
            {
                arraylist3 = ((BackStackRecord) (obj)).mSharedElementSourceNames;
                arraylist2 = ((BackStackRecord) (obj)).mSharedElementTargetNames;
            }
            k2 = 0;
            do
            {
                if (k2 >= k3)
                {
                    continue label0;
                }
                obj = (String)arraylist3.get(k2);
                String s = (String)arraylist2.get(k2);
                String s1 = (String)nameOverrides.remove(s);
                if (s1 != null)
                {
                    nameOverrides.put(obj, s1);
                } else
                {
                    nameOverrides.put(obj, s);
                }
                k2++;
            } while (true);
        }

        final Object enteringViews = (FragmentContainerTransition)sparsearray.valueAt(l);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_1506;
        }
        final Object exitTransition;
        final Object finalSharedElementTransition;
        Object obj1;
        final Object enterTransition;
        final Object epicenterView;
        final ArrayList exitingViews;
        final Object inEpicenter;
        final Object exitingViews;
        final FragmentTransitionImpl impl;
        final FragmentTransitionImpl impl;
        final Fragment inFragment;
        Object obj2;
        final ArrayList sharedElementsIn;
        final ArrayList sharedElementsOut;
        final Fragment inFragment;
        final Fragment inFragment;
        final Fragment outFragment;
        final Fragment outFragment;
        int i2;
        int l2;
        final boolean inIsPop;
        final boolean inIsPop;
        if (fragmentmanagerimpl.mContainer.onHasView())
        {
            finalSharedElementTransition = (ViewGroup)fragmentmanagerimpl.mContainer.onFindViewById(j3);
        } else
        {
            finalSharedElementTransition = null;
        }
        if (finalSharedElementTransition == null)
        {
            break MISSING_BLOCK_LABEL_1497;
        }
        inFragment = ((FragmentContainerTransition) (enteringViews)).lastIn;
        sharedElementsOut = ((FragmentContainerTransition) (enteringViews)).firstOut;
        impl = chooseImpl(sharedElementsOut, inFragment);
        if (impl == null)
        {
            break MISSING_BLOCK_LABEL_1497;
        }
        inIsPop = ((FragmentContainerTransition) (enteringViews)).lastInIsPop;
        inIsPop = ((FragmentContainerTransition) (enteringViews)).firstOutIsPop;
        obj2 = new ArrayList();
        sharedElementsIn = new ArrayList();
        if (inFragment == null)
        {
            obj1 = null;
        } else
        {
            if (inIsPop)
            {
                exitTransition = inFragment.getReenterTransition();
            } else
            if (inFragment.mAnimationInfo == null)
            {
                exitTransition = null;
            } else
            {
                exitTransition = inFragment.mAnimationInfo.mEnterTransition;
            }
            obj1 = impl.cloneTransition(exitTransition);
        }
        if (sharedElementsOut == null)
        {
            enterTransition = null;
        } else
        {
            if (inIsPop)
            {
                exitTransition = sharedElementsOut.getReturnTransition();
            } else
            if (((Fragment) (sharedElementsOut)).mAnimationInfo == null)
            {
                exitTransition = null;
            } else
            {
                exitTransition = ((Fragment) (sharedElementsOut)).mAnimationInfo.mExitTransition;
            }
            enterTransition = impl.cloneTransition(exitTransition);
        }
        inFragment = ((FragmentContainerTransition) (enteringViews)).lastIn;
        outFragment = ((FragmentContainerTransition) (enteringViews)).firstOut;
        if (inFragment != null)
        {
            inFragment.mView.setVisibility(0);
        }
        if (inFragment == null || outFragment == null)
        {
            exitTransition = null;
        } else
        {
            inIsPop = ((FragmentContainerTransition) (enteringViews)).lastInIsPop;
            final ArrayMap inSharedElements;
            if (nameOverrides.isEmpty())
            {
                exitTransition = null;
            } else
            {
                exitTransition = getSharedElementTransition(impl, inFragment, outFragment, inIsPop);
            }
            exitingViews = captureOutSharedElements(impl, nameOverrides, exitTransition, ((FragmentContainerTransition) (enteringViews)));
            inSharedElements = captureInSharedElements(impl, nameOverrides, exitTransition, ((FragmentContainerTransition) (enteringViews)));
            if (nameOverrides.isEmpty())
            {
                epicenterView = null;
                if (exitingViews != null)
                {
                    ((SimpleArrayMap) (exitingViews)).clear();
                }
                exitTransition = epicenterView;
                if (inSharedElements != null)
                {
                    inSharedElements.clear();
                    exitTransition = epicenterView;
                }
            } else
            {
                addSharedElementsWithMatchingNames(sharedElementsIn, ((ArrayMap) (exitingViews)), nameOverrides.keySet());
                addSharedElementsWithMatchingNames(((ArrayList) (obj2)), inSharedElements, nameOverrides.values());
            }
            if (obj1 == null && enterTransition == null && exitTransition == null)
            {
                exitTransition = null;
            } else
            {
                callSharedElementStartEnd(inFragment, outFragment, inIsPop, ((ArrayMap) (exitingViews)), true);
                if (exitTransition != null)
                {
                    ((ArrayList) (obj2)).add(nonExistentView);
                    impl.setSharedElementTargets(exitTransition, nonExistentView, sharedElementsIn);
                    setOutEpicenter(impl, exitTransition, enterTransition, ((ArrayMap) (exitingViews)), ((FragmentContainerTransition) (enteringViews)).firstOutIsPop, ((FragmentContainerTransition) (enteringViews)).firstOutTransaction);
                    impl = new Rect();
                    enteringViews = getInEpicenterView(inSharedElements, ((FragmentContainerTransition) (enteringViews)), obj1, inIsPop);
                    epicenterView = enteringViews;
                    exitingViews = impl;
                    if (enteringViews != null)
                    {
                        impl.setEpicenter(obj1, impl);
                        exitingViews = impl;
                        epicenterView = enteringViews;
                    }
                } else
                {
                    exitingViews = null;
                    epicenterView = null;
                }
                epicenterView = new OneShotPreDrawListener(((View) (finalSharedElementTransition)), new _cls3());
                ((View) (finalSharedElementTransition)).getViewTreeObserver().addOnPreDrawListener(((android.view.ViewTreeObserver.OnPreDrawListener) (epicenterView)));
                ((View) (finalSharedElementTransition)).addOnAttachStateChangeListener(((android.view.View.OnAttachStateChangeListener) (epicenterView)));
            }
        }
        if (obj1 == null && exitTransition == null && enterTransition == null)
        {
            break MISSING_BLOCK_LABEL_1497;
        }
        exitingViews = configureEnteringExitingViews(impl, enterTransition, sharedElementsOut, sharedElementsIn, nonExistentView);
        epicenterView = configureEnteringExitingViews(impl, obj1, inFragment, ((ArrayList) (obj2)), nonExistentView);
        setViewVisibility(((ArrayList) (epicenterView)), 4);
        enteringViews = mergeTransitions(impl, obj1, enterTransition, exitTransition, inFragment, inIsPop);
        if (enteringViews == null)
        {
            break MISSING_BLOCK_LABEL_1497;
        }
        if (sharedElementsOut != null && enterTransition != null && ((Fragment) (sharedElementsOut)).mAdded && ((Fragment) (sharedElementsOut)).mHidden && ((Fragment) (sharedElementsOut)).mHiddenChanged)
        {
            if (((Fragment) (sharedElementsOut)).mAnimationInfo == null)
            {
                sharedElementsOut.mAnimationInfo = new Fragment.AnimationInfo();
            }
            ((Fragment) (sharedElementsOut)).mAnimationInfo.mIsHideReplaced = true;
            impl.scheduleHideFragmentView(enterTransition, ((Fragment) (sharedElementsOut)).mView, ((ArrayList) (exitingViews)));
            ViewGroup viewgroup = ((Fragment) (sharedElementsOut)).mContainer;
            sharedElementsOut = new OneShotPreDrawListener(viewgroup, new _cls1());
            viewgroup.getViewTreeObserver().addOnPreDrawListener(sharedElementsOut);
            viewgroup.addOnAttachStateChangeListener(sharedElementsOut);
        }
        impl = new ArrayList();
        l2 = ((ArrayList) (obj2)).size();
        for (int l1 = 0; l1 < l2; l1++)
        {
            sharedElementsOut = (View)((ArrayList) (obj2)).get(l1);
            impl.add(ViewCompat.getTransitionName(sharedElementsOut));
            ViewCompat.setTransitionName(sharedElementsOut, null);
        }

        impl.scheduleRemoveTargets(enteringViews, obj1, ((ArrayList) (epicenterView)), enterTransition, ((ArrayList) (exitingViews)), exitTransition, ((ArrayList) (obj2)));
        impl.beginDelayedTransition(((ViewGroup) (finalSharedElementTransition)), enteringViews);
        j3 = ((ArrayList) (obj2)).size();
        obj1 = new ArrayList();
        i2 = 0;
        if (i2 >= j3)
        {
            break MISSING_BLOCK_LABEL_1433;
        }
        exitingViews = (View)sharedElementsIn.get(i2);
        enterTransition = ViewCompat.getTransitionName(((View) (exitingViews)));
        ((ArrayList) (obj1)).add(enterTransition);
        if (enterTransition == null) goto _L6; else goto _L5
_L5:
        ViewCompat.setTransitionName(((View) (exitingViews)), null);
        exitingViews = (String)nameOverrides.get(enterTransition);
        l2 = 0;
_L8:
        if (l2 < j3)
        {
            if (!((String) (exitingViews)).equals(impl.get(l2)))
            {
                break MISSING_BLOCK_LABEL_1424;
            }
            ViewCompat.setTransitionName((View)((ArrayList) (obj2)).get(l2), ((String) (enterTransition)));
        }
_L6:
        i2++;
        break MISSING_BLOCK_LABEL_1316;
        l2++;
        if (true) goto _L8; else goto _L7
_L7:
        obj1 = new OneShotPreDrawListener(((View) (finalSharedElementTransition)), new FragmentTransitionImpl._cls1(impl, j3, ((ArrayList) (obj2)), impl, sharedElementsIn, ((ArrayList) (obj1))));
        ((View) (finalSharedElementTransition)).getViewTreeObserver().addOnPreDrawListener(((android.view.ViewTreeObserver.OnPreDrawListener) (obj1)));
        ((View) (finalSharedElementTransition)).addOnAttachStateChangeListener(((android.view.View.OnAttachStateChangeListener) (obj1)));
        setViewVisibility(((ArrayList) (epicenterView)), 0);
        impl.swapSharedElementTargets(exitTransition, sharedElementsIn, ((ArrayList) (obj2)));
_L10:
        l++;
          goto _L9
          goto _L1
        if (fragmentmanagerimpl.mContainer.onHasView())
        {
            obj1 = (ViewGroup)fragmentmanagerimpl.mContainer.onFindViewById(j3);
        } else
        {
            obj1 = null;
        }
        if (obj1 != null)
        {
            inFragment = ((FragmentContainerTransition) (enteringViews)).lastIn;
            obj2 = ((FragmentContainerTransition) (enteringViews)).firstOut;
            impl = chooseImpl(((Fragment) (obj2)), inFragment);
            if (impl != null)
            {
                inIsPop = ((FragmentContainerTransition) (enteringViews)).lastInIsPop;
                inIsPop = ((FragmentContainerTransition) (enteringViews)).firstOutIsPop;
                if (inFragment == null)
                {
                    enterTransition = null;
                } else
                {
                    if (inIsPop)
                    {
                        exitTransition = inFragment.getReenterTransition();
                    } else
                    if (inFragment.mAnimationInfo == null)
                    {
                        exitTransition = null;
                    } else
                    {
                        exitTransition = inFragment.mAnimationInfo.mEnterTransition;
                    }
                    enterTransition = impl.cloneTransition(exitTransition);
                }
                if (obj2 == null)
                {
                    exitTransition = null;
                } else
                {
                    if (inIsPop)
                    {
                        exitTransition = ((Fragment) (obj2)).getReturnTransition();
                    } else
                    if (((Fragment) (obj2)).mAnimationInfo == null)
                    {
                        exitTransition = null;
                    } else
                    {
                        exitTransition = ((Fragment) (obj2)).mAnimationInfo.mExitTransition;
                    }
                    exitTransition = impl.cloneTransition(exitTransition);
                }
                sharedElementsOut = new ArrayList();
                sharedElementsIn = new ArrayList();
                inFragment = ((FragmentContainerTransition) (enteringViews)).lastIn;
                outFragment = ((FragmentContainerTransition) (enteringViews)).firstOut;
                if (inFragment == null || outFragment == null)
                {
                    finalSharedElementTransition = null;
                } else
                {
                    inIsPop = ((FragmentContainerTransition) (enteringViews)).lastInIsPop;
                    if (nameOverrides.isEmpty())
                    {
                        finalSharedElementTransition = null;
                    } else
                    {
                        finalSharedElementTransition = getSharedElementTransition(impl, inFragment, outFragment, inIsPop);
                    }
                    exitingViews = captureOutSharedElements(impl, nameOverrides, finalSharedElementTransition, ((FragmentContainerTransition) (enteringViews)));
                    if (nameOverrides.isEmpty())
                    {
                        finalSharedElementTransition = null;
                    } else
                    {
                        sharedElementsOut.addAll(exitingViews.values());
                    }
                    if (enterTransition == null && exitTransition == null && finalSharedElementTransition == null)
                    {
                        finalSharedElementTransition = null;
                    } else
                    {
                        callSharedElementStartEnd(inFragment, outFragment, inIsPop, exitingViews, true);
                        if (finalSharedElementTransition != null)
                        {
                            exitingViews = new Rect();
                            impl.setSharedElementTargets(finalSharedElementTransition, nonExistentView, sharedElementsOut);
                            setOutEpicenter(impl, finalSharedElementTransition, exitTransition, exitingViews, ((FragmentContainerTransition) (enteringViews)).firstOutIsPop, ((FragmentContainerTransition) (enteringViews)).firstOutTransaction);
                            inEpicenter = exitingViews;
                            if (enterTransition != null)
                            {
                                impl.setEpicenter(enterTransition, ((Rect) (exitingViews)));
                                inEpicenter = exitingViews;
                            }
                        } else
                        {
                            inEpicenter = null;
                        }
                        inEpicenter = new OneShotPreDrawListener(((View) (obj1)), new _cls4());
                        ((View) (obj1)).getViewTreeObserver().addOnPreDrawListener(((android.view.ViewTreeObserver.OnPreDrawListener) (inEpicenter)));
                        ((View) (obj1)).addOnAttachStateChangeListener(((android.view.View.OnAttachStateChangeListener) (inEpicenter)));
                    }
                }
                if (enterTransition != null || finalSharedElementTransition != null || exitTransition != null)
                {
                    exitingViews = configureEnteringExitingViews(impl, exitTransition, ((Fragment) (obj2)), sharedElementsOut, nonExistentView);
                    if (exitingViews == null || exitingViews.isEmpty())
                    {
                        exitTransition = null;
                    }
                    impl.addTarget(enterTransition, nonExistentView);
                    exitingViews = mergeTransitions(impl, enterTransition, exitTransition, finalSharedElementTransition, inFragment, ((FragmentContainerTransition) (enteringViews)).lastInIsPop);
                    if (exitingViews != null)
                    {
                        enteringViews = new ArrayList();
                        impl.scheduleRemoveTargets(exitingViews, enterTransition, ((ArrayList) (enteringViews)), exitTransition, exitingViews, finalSharedElementTransition, sharedElementsIn);
                        exitTransition = new OneShotPreDrawListener(((View) (obj1)), new _cls2());
                        ((View) (obj1)).getViewTreeObserver().addOnPreDrawListener(((android.view.ViewTreeObserver.OnPreDrawListener) (exitTransition)));
                        ((View) (obj1)).addOnAttachStateChangeListener(((android.view.View.OnAttachStateChangeListener) (exitTransition)));
                        exitTransition = new OneShotPreDrawListener(((View) (obj1)), new FragmentTransitionImpl._cls2(impl, sharedElementsIn, nameOverrides));
                        ((View) (obj1)).getViewTreeObserver().addOnPreDrawListener(((android.view.ViewTreeObserver.OnPreDrawListener) (exitTransition)));
                        ((View) (obj1)).addOnAttachStateChangeListener(((android.view.View.OnAttachStateChangeListener) (exitTransition)));
                        impl.beginDelayedTransition(((ViewGroup) (obj1)), exitingViews);
                        exitTransition = new OneShotPreDrawListener(((View) (obj1)), new FragmentTransitionImpl._cls3(impl, sharedElementsIn, nameOverrides));
                        ((View) (obj1)).getViewTreeObserver().addOnPreDrawListener(((android.view.ViewTreeObserver.OnPreDrawListener) (exitTransition)));
                        ((View) (obj1)).addOnAttachStateChangeListener(((android.view.View.OnAttachStateChangeListener) (exitTransition)));
                    }
                }
            }
        }
          goto _L10
    }


    private class FragmentContainerTransition
    {

        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition()
        {
        }
    }


    private class _cls1
        implements Runnable
    {

        private final ArrayList val$exitingViews;

        public final void run()
        {
            FragmentTransition.setViewVisibility(exitingViews, 4);
        }

        _cls1()
        {
            exitingViews = arraylist;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        private final Rect val$epicenter;
        private final View val$epicenterView;
        private final FragmentTransitionImpl val$impl;
        private final Fragment val$inFragment;
        private final boolean val$inIsPop;
        private final ArrayMap val$inSharedElements;
        private final Fragment val$outFragment;

        public final void run()
        {
            FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, inSharedElements, false);
            if (epicenterView != null)
            {
                FragmentTransitionImpl.getBoundsOnScreen(epicenterView, epicenter);
            }
        }

        _cls3()
        {
            inFragment = fragment;
            outFragment = fragment1;
            inIsPop = flag;
            inSharedElements = arraymap;
            epicenterView = view;
            impl = fragmenttransitionimpl;
            epicenter = rect;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final Object val$enterTransition;
        private final ArrayList val$enteringViews;
        private final Object val$exitTransition;
        private final ArrayList val$exitingViews;
        private final FragmentTransitionImpl val$impl;
        private final Fragment val$inFragment;
        private final View val$nonExistentView;
        private final ArrayList val$sharedElementsIn;

        public final void run()
        {
            if (enterTransition != null)
            {
                impl.removeTarget(enterTransition, nonExistentView);
                ArrayList arraylist = FragmentTransition.configureEnteringExitingViews(impl, enterTransition, inFragment, sharedElementsIn, nonExistentView);
                enteringViews.addAll(arraylist);
            }
            if (exitingViews != null)
            {
                if (exitTransition != null)
                {
                    ArrayList arraylist1 = new ArrayList();
                    arraylist1.add(nonExistentView);
                    impl.replaceTargets(exitTransition, exitingViews, arraylist1);
                }
                exitingViews.clear();
                exitingViews.add(nonExistentView);
            }
        }

        _cls2()
        {
            enterTransition = obj;
            impl = fragmenttransitionimpl;
            nonExistentView = view;
            inFragment = fragment;
            sharedElementsIn = arraylist;
            enteringViews = arraylist1;
            exitingViews = arraylist2;
            exitTransition = obj1;
            super();
        }
    }


    private class _cls4
        implements Runnable
    {

        private final Object val$enterTransition;
        private final Object val$finalSharedElementTransition;
        private final FragmentContainerTransition val$fragments;
        private final FragmentTransitionImpl val$impl;
        private final Rect val$inEpicenter;
        private final Fragment val$inFragment;
        private final boolean val$inIsPop;
        private final ArrayMap val$nameOverrides;
        private final View val$nonExistentView;
        private final Fragment val$outFragment;
        private final ArrayList val$sharedElementsIn;
        private final ArrayList val$sharedElementsOut;

        public final void run()
        {
            Object obj = FragmentTransition.captureInSharedElements(impl, nameOverrides, finalSharedElementTransition, fragments);
            if (obj != null)
            {
                sharedElementsIn.addAll(((ArrayMap) (obj)).values());
                sharedElementsIn.add(nonExistentView);
            }
            FragmentTransition.callSharedElementStartEnd(inFragment, outFragment, inIsPop, ((ArrayMap) (obj)), false);
            if (finalSharedElementTransition != null)
            {
                impl.swapSharedElementTargets(finalSharedElementTransition, sharedElementsOut, sharedElementsIn);
                obj = FragmentTransition.getInEpicenterView(((ArrayMap) (obj)), fragments, enterTransition, inIsPop);
                if (obj != null)
                {
                    FragmentTransitionImpl.getBoundsOnScreen(((View) (obj)), inEpicenter);
                }
            }
        }

        _cls4()
        {
            impl = fragmenttransitionimpl;
            nameOverrides = arraymap;
            finalSharedElementTransition = obj;
            fragments = fragmentcontainertransition;
            sharedElementsIn = arraylist;
            nonExistentView = view;
            inFragment = fragment;
            outFragment = fragment1;
            inIsPop = flag;
            sharedElementsOut = arraylist1;
            enterTransition = obj1;
            inEpicenter = rect;
            super();
        }
    }

}
