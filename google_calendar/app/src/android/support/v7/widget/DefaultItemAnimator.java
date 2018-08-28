// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            SimpleItemAnimator

public final class DefaultItemAnimator extends SimpleItemAnimator
{

    private static TimeInterpolator sDefaultInterpolator;
    public ArrayList mAddAnimations;
    public ArrayList mAdditionsList;
    public ArrayList mChangeAnimations;
    public ArrayList mChangesList;
    public ArrayList mMoveAnimations;
    public ArrayList mMovesList;
    private ArrayList mPendingAdditions;
    private ArrayList mPendingChanges;
    private ArrayList mPendingMoves;
    private ArrayList mPendingRemovals;
    public ArrayList mRemoveAnimations;

    public DefaultItemAnimator()
    {
        mPendingRemovals = new ArrayList();
        mPendingAdditions = new ArrayList();
        mPendingMoves = new ArrayList();
        mPendingChanges = new ArrayList();
        mAdditionsList = new ArrayList();
        mMovesList = new ArrayList();
        mChangesList = new ArrayList();
        mAddAnimations = new ArrayList();
        mMoveAnimations = new ArrayList();
        mRemoveAnimations = new ArrayList();
        mChangeAnimations = new ArrayList();
    }

    private static void cancelAll(List list)
    {
        for (int i = list.size() - 1; i >= 0; i--)
        {
            ((RecyclerView.ViewHolder)list.get(i)).itemView.animate().cancel();
        }

    }

    private final void endChangeAnimation(List list, RecyclerView.ViewHolder viewholder)
    {
        for (int i = list.size() - 1; i >= 0; i--)
        {
            ChangeInfo changeinfo = (ChangeInfo)list.get(i);
            if (endChangeAnimationIfNecessary(changeinfo, viewholder) && changeinfo.oldHolder == null && changeinfo.newHolder == null)
            {
                list.remove(changeinfo);
            }
        }

    }

    private final void endChangeAnimationIfNecessary(ChangeInfo changeinfo)
    {
        if (changeinfo.oldHolder != null)
        {
            endChangeAnimationIfNecessary(changeinfo, changeinfo.oldHolder);
        }
        if (changeinfo.newHolder != null)
        {
            endChangeAnimationIfNecessary(changeinfo, changeinfo.newHolder);
        }
    }

    private final boolean endChangeAnimationIfNecessary(ChangeInfo changeinfo, RecyclerView.ViewHolder viewholder)
    {
        if (changeinfo.newHolder == viewholder)
        {
            changeinfo.newHolder = null;
        } else
        if (changeinfo.oldHolder == viewholder)
        {
            changeinfo.oldHolder = null;
        } else
        {
            return false;
        }
        viewholder.itemView.setAlpha(1.0F);
        viewholder.itemView.setTranslationX(0.0F);
        viewholder.itemView.setTranslationY(0.0F);
        if (super.mListener != null)
        {
            super.mListener.onAnimationFinished(viewholder);
        }
        return true;
    }

    private final void resetAnimation(RecyclerView.ViewHolder viewholder)
    {
        if (sDefaultInterpolator == null)
        {
            sDefaultInterpolator = (new ValueAnimator()).getInterpolator();
        }
        viewholder.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(viewholder);
    }

    public final boolean animateAdd(RecyclerView.ViewHolder viewholder)
    {
        resetAnimation(viewholder);
        viewholder.itemView.setAlpha(0.0F);
        mPendingAdditions.add(viewholder);
        return true;
    }

    public final boolean animateChange(RecyclerView.ViewHolder viewholder, RecyclerView.ViewHolder viewholder1, int i, int j, int k, int l)
    {
        if (viewholder == viewholder1)
        {
            return animateMove(viewholder, i, j, k, l);
        }
        float f = viewholder.itemView.getTranslationX();
        float f1 = viewholder.itemView.getTranslationY();
        float f2 = viewholder.itemView.getAlpha();
        resetAnimation(viewholder);
        int i1 = (int)((float)(k - i) - f);
        int j1 = (int)((float)(l - j) - f1);
        viewholder.itemView.setTranslationX(f);
        viewholder.itemView.setTranslationY(f1);
        viewholder.itemView.setAlpha(f2);
        if (viewholder1 != null)
        {
            resetAnimation(viewholder1);
            viewholder1.itemView.setTranslationX(-i1);
            viewholder1.itemView.setTranslationY(-j1);
            viewholder1.itemView.setAlpha(0.0F);
        }
        mPendingChanges.add(new ChangeInfo(viewholder, viewholder1, i, j, k, l));
        return true;
    }

    public final boolean animateMove(RecyclerView.ViewHolder viewholder, int i, int j, int k, int l)
    {
        View view = viewholder.itemView;
        i += (int)viewholder.itemView.getTranslationX();
        j += (int)viewholder.itemView.getTranslationY();
        resetAnimation(viewholder);
        int i1 = k - i;
        int j1 = l - j;
        if (i1 == 0 && j1 == 0)
        {
            if (super.mListener != null)
            {
                super.mListener.onAnimationFinished(viewholder);
            }
            return false;
        }
        if (i1 != 0)
        {
            view.setTranslationX(-i1);
        }
        if (j1 != 0)
        {
            view.setTranslationY(-j1);
        }
        mPendingMoves.add(new MoveInfo(viewholder, i, j, k, l));
        return true;
    }

    public final boolean animateRemove(RecyclerView.ViewHolder viewholder)
    {
        resetAnimation(viewholder);
        mPendingRemovals.add(viewholder);
        return true;
    }

    public final boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewholder, List list)
    {
        return !list.isEmpty() || super.canReuseUpdatedViewHolder(viewholder, list);
    }

    public final void endAnimation(RecyclerView.ViewHolder viewholder)
    {
        View view = viewholder.itemView;
        view.animate().cancel();
        for (int i = mPendingMoves.size() - 1; i >= 0; i--)
        {
            if (((MoveInfo)mPendingMoves.get(i)).holder != viewholder)
            {
                continue;
            }
            view.setTranslationY(0.0F);
            view.setTranslationX(0.0F);
            if (super.mListener != null)
            {
                super.mListener.onAnimationFinished(viewholder);
            }
            mPendingMoves.remove(i);
        }

        endChangeAnimation(mPendingChanges, viewholder);
        if (mPendingRemovals.remove(viewholder))
        {
            view.setAlpha(1.0F);
            if (super.mListener != null)
            {
                super.mListener.onAnimationFinished(viewholder);
            }
        }
        if (mPendingAdditions.remove(viewholder))
        {
            view.setAlpha(1.0F);
            if (super.mListener != null)
            {
                super.mListener.onAnimationFinished(viewholder);
            }
        }
        for (int j = mChangesList.size() - 1; j >= 0; j--)
        {
            ArrayList arraylist = (ArrayList)mChangesList.get(j);
            endChangeAnimation(arraylist, viewholder);
            if (arraylist.isEmpty())
            {
                mChangesList.remove(j);
            }
        }

        int k = mMovesList.size() - 1;
label0:
        do
        {
            if (k >= 0)
            {
                ArrayList arraylist1 = (ArrayList)mMovesList.get(k);
                int i1 = arraylist1.size() - 1;
                do
                {
label1:
                    {
                        if (i1 >= 0)
                        {
                            if (((MoveInfo)arraylist1.get(i1)).holder != viewholder)
                            {
                                break label1;
                            }
                            view.setTranslationY(0.0F);
                            view.setTranslationX(0.0F);
                            if (super.mListener != null)
                            {
                                super.mListener.onAnimationFinished(viewholder);
                            }
                            arraylist1.remove(i1);
                            if (arraylist1.isEmpty())
                            {
                                mMovesList.remove(k);
                            }
                        }
                        k--;
                        continue label0;
                    }
                    i1--;
                } while (true);
            }
            for (int l = mAdditionsList.size() - 1; l >= 0; l--)
            {
                ArrayList arraylist2 = (ArrayList)mAdditionsList.get(l);
                if (!arraylist2.remove(viewholder))
                {
                    continue;
                }
                view.setAlpha(1.0F);
                if (super.mListener != null)
                {
                    super.mListener.onAnimationFinished(viewholder);
                }
                if (arraylist2.isEmpty())
                {
                    mAdditionsList.remove(l);
                }
            }

            mRemoveAnimations.remove(viewholder);
            mAddAnimations.remove(viewholder);
            mChangeAnimations.remove(viewholder);
            mMoveAnimations.remove(viewholder);
            if (!isRunning())
            {
                dispatchAnimationsFinished();
            }
            return;
        } while (true);
    }

    public final void endAnimations()
    {
        for (int i = mPendingMoves.size() - 1; i >= 0; i--)
        {
            Object obj = (MoveInfo)mPendingMoves.get(i);
            View view = ((MoveInfo) (obj)).holder.itemView;
            view.setTranslationY(0.0F);
            view.setTranslationX(0.0F);
            obj = ((MoveInfo) (obj)).holder;
            if (super.mListener != null)
            {
                super.mListener.onAnimationFinished(((RecyclerView.ViewHolder) (obj)));
            }
            mPendingMoves.remove(i);
        }

        for (int j = mPendingRemovals.size() - 1; j >= 0; j--)
        {
            RecyclerView.ViewHolder viewholder = (RecyclerView.ViewHolder)mPendingRemovals.get(j);
            if (super.mListener != null)
            {
                super.mListener.onAnimationFinished(viewholder);
            }
            mPendingRemovals.remove(j);
        }

        for (int k = mPendingAdditions.size() - 1; k >= 0; k--)
        {
            RecyclerView.ViewHolder viewholder1 = (RecyclerView.ViewHolder)mPendingAdditions.get(k);
            viewholder1.itemView.setAlpha(1.0F);
            if (super.mListener != null)
            {
                super.mListener.onAnimationFinished(viewholder1);
            }
            mPendingAdditions.remove(k);
        }

        for (int l = mPendingChanges.size() - 1; l >= 0; l--)
        {
            endChangeAnimationIfNecessary((ChangeInfo)mPendingChanges.get(l));
        }

        mPendingChanges.clear();
        if (!isRunning())
        {
            return;
        }
        for (int i1 = mMovesList.size() - 1; i1 >= 0; i1--)
        {
            ArrayList arraylist = (ArrayList)mMovesList.get(i1);
            for (int l1 = arraylist.size() - 1; l1 >= 0; l1--)
            {
                Object obj1 = (MoveInfo)arraylist.get(l1);
                View view1 = ((MoveInfo) (obj1)).holder.itemView;
                view1.setTranslationY(0.0F);
                view1.setTranslationX(0.0F);
                obj1 = ((MoveInfo) (obj1)).holder;
                if (super.mListener != null)
                {
                    super.mListener.onAnimationFinished(((RecyclerView.ViewHolder) (obj1)));
                }
                arraylist.remove(l1);
                if (arraylist.isEmpty())
                {
                    mMovesList.remove(arraylist);
                }
            }

        }

        for (int j1 = mAdditionsList.size() - 1; j1 >= 0; j1--)
        {
            ArrayList arraylist1 = (ArrayList)mAdditionsList.get(j1);
            for (int i2 = arraylist1.size() - 1; i2 >= 0; i2--)
            {
                RecyclerView.ViewHolder viewholder2 = (RecyclerView.ViewHolder)arraylist1.get(i2);
                viewholder2.itemView.setAlpha(1.0F);
                if (super.mListener != null)
                {
                    super.mListener.onAnimationFinished(viewholder2);
                }
                arraylist1.remove(i2);
                if (arraylist1.isEmpty())
                {
                    mAdditionsList.remove(arraylist1);
                }
            }

        }

        for (int k1 = mChangesList.size() - 1; k1 >= 0; k1--)
        {
            ArrayList arraylist2 = (ArrayList)mChangesList.get(k1);
            for (int j2 = arraylist2.size() - 1; j2 >= 0; j2--)
            {
                endChangeAnimationIfNecessary((ChangeInfo)arraylist2.get(j2));
                if (arraylist2.isEmpty())
                {
                    mChangesList.remove(arraylist2);
                }
            }

        }

        cancelAll(mRemoveAnimations);
        cancelAll(mMoveAnimations);
        cancelAll(mAddAnimations);
        cancelAll(mChangeAnimations);
        dispatchAnimationsFinished();
    }

    public final boolean isRunning()
    {
        return !mPendingAdditions.isEmpty() || !mPendingChanges.isEmpty() || !mPendingMoves.isEmpty() || !mPendingRemovals.isEmpty() || !mMoveAnimations.isEmpty() || !mRemoveAnimations.isEmpty() || !mAddAnimations.isEmpty() || !mChangeAnimations.isEmpty() || !mMovesList.isEmpty() || !mAdditionsList.isEmpty() || !mChangesList.isEmpty();
    }

    public final void runPendingAnimations()
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if (!mPendingRemovals.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!mPendingMoves.isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!mPendingChanges.isEmpty())
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!mPendingAdditions.isEmpty())
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (flag || flag1 || flag3 || flag2)
        {
            ArrayList arraylist = (ArrayList)mPendingRemovals;
            int j = arraylist.size();
            for (int i = 0; i < j;)
            {
                final RecyclerView.ViewHolder holder = ((RecyclerView.ViewHolder) (arraylist.get(i)));
                i++;
                holder = (RecyclerView.ViewHolder)holder;
                final View view = holder.itemView;
                final ViewPropertyAnimator animation = view.animate();
                mRemoveAnimations.add(holder);
                animation.setDuration(super.mRemoveDuration).alpha(0.0F).setListener(new _cls4()).start();
            }

            mPendingRemovals.clear();
            Object obj;
            if (flag1)
            {
                final ArrayList moves = new ArrayList();
                moves.addAll(mPendingMoves);
                mMovesList.add(moves);
                mPendingMoves.clear();
                obj = new _cls1();
                final ArrayList changes;
                final ArrayList additions;
                if (flag)
                {
                    ViewCompat.postOnAnimationDelayed(((MoveInfo)moves.get(0)).holder.itemView, ((Runnable) (obj)), super.mRemoveDuration);
                } else
                {
                    ((Runnable) (obj)).run();
                }
            }
            if (flag2)
            {
                changes = new ArrayList();
                changes.addAll(mPendingChanges);
                mChangesList.add(changes);
                mPendingChanges.clear();
                obj = new _cls2();
                if (flag)
                {
                    ViewCompat.postOnAnimationDelayed(((ChangeInfo)changes.get(0)).oldHolder.itemView, ((Runnable) (obj)), super.mRemoveDuration);
                } else
                {
                    ((Runnable) (obj)).run();
                }
            }
            if (flag3)
            {
                additions = new ArrayList();
                additions.addAll(mPendingAdditions);
                mAdditionsList.add(additions);
                mPendingAdditions.clear();
                obj = new _cls3();
                if (flag || flag1 || flag2)
                {
                    long l;
                    long l1;
                    long l2;
                    if (flag)
                    {
                        l = super.mRemoveDuration;
                    } else
                    {
                        l = 0L;
                    }
                    if (flag1)
                    {
                        l1 = super.mMoveDuration;
                    } else
                    {
                        l1 = 0L;
                    }
                    if (flag2)
                    {
                        l2 = super.mChangeDuration;
                    } else
                    {
                        l2 = 0L;
                    }
                    l1 = Math.max(l1, l2);
                    ViewCompat.postOnAnimationDelayed(((RecyclerView.ViewHolder)additions.get(0)).itemView, ((Runnable) (obj)), l + l1);
                    return;
                } else
                {
                    ((Runnable) (obj)).run();
                    return;
                }
            }
        }
    }

    private class ChangeInfo
    {

        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder newHolder;
        public RecyclerView.ViewHolder oldHolder;
        public int toX;
        public int toY;

        public final String toString()
        {
            return (new StringBuilder("ChangeInfo{oldHolder=")).append(oldHolder).append(", newHolder=").append(newHolder).append(", fromX=").append(fromX).append(", fromY=").append(fromY).append(", toX=").append(toX).append(", toY=").append(toY).append('}').toString();
        }

        private ChangeInfo(RecyclerView.ViewHolder viewholder, RecyclerView.ViewHolder viewholder1)
        {
            oldHolder = viewholder;
            newHolder = viewholder1;
        }

        ChangeInfo(RecyclerView.ViewHolder viewholder, RecyclerView.ViewHolder viewholder1, int i, int j, int k, int l)
        {
            this(viewholder, viewholder1);
            fromX = i;
            fromY = j;
            toX = k;
            toY = l;
        }
    }


    private class MoveInfo
    {

        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder holder;
        public int toX;
        public int toY;

        MoveInfo(RecyclerView.ViewHolder viewholder, int i, int j, int k, int l)
        {
            holder = viewholder;
            fromX = i;
            fromY = j;
            toX = k;
            toY = l;
        }
    }


    private class _cls4 extends AnimatorListenerAdapter
    {

        private final DefaultItemAnimator this$0;
        private final ViewPropertyAnimator val$animation;
        private final RecyclerView.ViewHolder val$holder;
        private final View val$view;

        public final void onAnimationEnd(Animator animator)
        {
            animation.setListener(null);
            view.setAlpha(1.0F);
            animator = DefaultItemAnimator.this;
            RecyclerView.ViewHolder viewholder = holder;
            if (((RecyclerView.ItemAnimator) (animator)).mListener != null)
            {
                ((RecyclerView.ItemAnimator) (animator)).mListener.onAnimationFinished(viewholder);
            }
            mRemoveAnimations.remove(holder);
            animator = DefaultItemAnimator.this;
            if (!animator.isRunning())
            {
                animator.dispatchAnimationsFinished();
            }
        }

        public final void onAnimationStart(Animator animator)
        {
        }

        _cls4()
        {
            this$0 = DefaultItemAnimator.this;
            holder = viewholder;
            animation = viewpropertyanimator;
            view = view1;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final DefaultItemAnimator this$0;
        private final ArrayList val$moves;

        public final void run()
        {
            ArrayList arraylist = (ArrayList)moves;
            int j = arraylist.size();
            for (int i = 0; i < j; i++)
            {
                final View view = (MoveInfo)arraylist.get(i);
                DefaultItemAnimator defaultitemanimator = DefaultItemAnimator.this;
                final RecyclerView.ViewHolder holder = ((MoveInfo) (view)).holder;
                final int deltaX = ((MoveInfo) (view)).fromX;
                final int deltaY = ((MoveInfo) (view)).fromY;
                int l = ((MoveInfo) (view)).toX;
                int k = ((MoveInfo) (view)).toY;
                view = holder.itemView;
                deltaX = l - deltaX;
                deltaY = k - deltaY;
                if (deltaX != 0)
                {
                    view.animate().translationX(0.0F);
                }
                if (deltaY != 0)
                {
                    view.animate().translationY(0.0F);
                }
                final ViewPropertyAnimator animation = view.animate();
                defaultitemanimator.mMoveAnimations.add(holder);
                animation.setDuration(((RecyclerView.ItemAnimator) (defaultitemanimator)).mMoveDuration).setListener(defaultitemanimator. new _cls6()).start();
            }

            moves.clear();
            mMovesList.remove(moves);
        }

        _cls1()
        {
            this$0 = DefaultItemAnimator.this;
            moves = arraylist;
            super();
        }

        private class _cls6 extends AnimatorListenerAdapter
        {

            private final DefaultItemAnimator this$0;
            private final ViewPropertyAnimator val$animation;
            private final int val$deltaX;
            private final int val$deltaY;
            private final RecyclerView.ViewHolder val$holder;
            private final View val$view;

            public final void onAnimationCancel(Animator animator)
            {
                if (deltaX != 0)
                {
                    view.setTranslationX(0.0F);
                }
                if (deltaY != 0)
                {
                    view.setTranslationY(0.0F);
                }
            }

            public final void onAnimationEnd(Animator animator)
            {
                animation.setListener(null);
                animator = DefaultItemAnimator.this;
                RecyclerView.ViewHolder viewholder = holder;
                if (((RecyclerView.ItemAnimator) (animator)).mListener != null)
                {
                    ((RecyclerView.ItemAnimator) (animator)).mListener.onAnimationFinished(viewholder);
                }
                mMoveAnimations.remove(holder);
                animator = DefaultItemAnimator.this;
                if (!animator.isRunning())
                {
                    animator.dispatchAnimationsFinished();
                }
            }

            public final void onAnimationStart(Animator animator)
            {
            }

            _cls6()
            {
                this$0 = DefaultItemAnimator.this;
                holder = viewholder;
                deltaX = i;
                view = view1;
                deltaY = j;
                animation = viewpropertyanimator;
                super();
            }
        }

    }


    private class _cls2
        implements Runnable
    {

        private final DefaultItemAnimator this$0;
        private final ArrayList val$changes;

        public final void run()
        {
            ArrayList arraylist = (ArrayList)changes;
            int j = arraylist.size();
            int i = 0;
            while (i < j) 
            {
                final ChangeInfo changeInfo = (ChangeInfo)arraylist.get(i);
                DefaultItemAnimator defaultitemanimator = DefaultItemAnimator.this;
                final Object view = changeInfo.oldHolder;
                final View newView;
                if (view == null)
                {
                    view = null;
                } else
                {
                    view = ((RecyclerView.ViewHolder) (view)).itemView;
                }
                newView = changeInfo.newHolder;
                if (newView != null)
                {
                    newView = ((RecyclerView.ViewHolder) (newView)).itemView;
                } else
                {
                    newView = null;
                }
                if (view != null)
                {
                    final ViewPropertyAnimator oldViewAnim = ((View) (view)).animate().setDuration(((RecyclerView.ItemAnimator) (defaultitemanimator)).mChangeDuration);
                    defaultitemanimator.mChangeAnimations.add(changeInfo.oldHolder);
                    oldViewAnim.translationX(changeInfo.toX - changeInfo.fromX);
                    oldViewAnim.translationY(changeInfo.toY - changeInfo.fromY);
                    oldViewAnim.alpha(0.0F).setListener(defaultitemanimator. new _cls7()).start();
                }
                if (newView != null)
                {
                    view = newView.animate();
                    defaultitemanimator.mChangeAnimations.add(changeInfo.newHolder);
                    ((ViewPropertyAnimator) (view)).translationX(0.0F).translationY(0.0F).setDuration(((RecyclerView.ItemAnimator) (defaultitemanimator)).mChangeDuration).alpha(1.0F).setListener(defaultitemanimator. new _cls8()).start();
                }
                i++;
            }
            changes.clear();
            mChangesList.remove(changes);
        }

        _cls2()
        {
            this$0 = DefaultItemAnimator.this;
            changes = arraylist;
            super();
        }

        private class _cls7 extends AnimatorListenerAdapter
        {

            private final DefaultItemAnimator this$0;
            private final ChangeInfo val$changeInfo;
            private final ViewPropertyAnimator val$oldViewAnim;
            private final View val$view;

            public final void onAnimationEnd(Animator animator)
            {
                oldViewAnim.setListener(null);
                view.setAlpha(1.0F);
                view.setTranslationX(0.0F);
                view.setTranslationY(0.0F);
                animator = DefaultItemAnimator.this;
                RecyclerView.ViewHolder viewholder = changeInfo.oldHolder;
                if (((RecyclerView.ItemAnimator) (animator)).mListener != null)
                {
                    ((RecyclerView.ItemAnimator) (animator)).mListener.onAnimationFinished(viewholder);
                }
                mChangeAnimations.remove(changeInfo.oldHolder);
                animator = DefaultItemAnimator.this;
                if (!animator.isRunning())
                {
                    animator.dispatchAnimationsFinished();
                }
            }

            public final void onAnimationStart(Animator animator)
            {
            }

            _cls7()
            {
                this$0 = DefaultItemAnimator.this;
                changeInfo = changeinfo;
                oldViewAnim = viewpropertyanimator;
                view = view1;
                super();
            }
        }


        private class _cls8 extends AnimatorListenerAdapter
        {

            private final DefaultItemAnimator this$0;
            private final ChangeInfo val$changeInfo;
            private final View val$newView;
            private final ViewPropertyAnimator val$newViewAnimation;

            public final void onAnimationEnd(Animator animator)
            {
                newViewAnimation.setListener(null);
                newView.setAlpha(1.0F);
                newView.setTranslationX(0.0F);
                newView.setTranslationY(0.0F);
                animator = DefaultItemAnimator.this;
                RecyclerView.ViewHolder viewholder = changeInfo.newHolder;
                if (((RecyclerView.ItemAnimator) (animator)).mListener != null)
                {
                    ((RecyclerView.ItemAnimator) (animator)).mListener.onAnimationFinished(viewholder);
                }
                mChangeAnimations.remove(changeInfo.newHolder);
                animator = DefaultItemAnimator.this;
                if (!animator.isRunning())
                {
                    animator.dispatchAnimationsFinished();
                }
            }

            public final void onAnimationStart(Animator animator)
            {
            }

            _cls8()
            {
                this$0 = DefaultItemAnimator.this;
                changeInfo = changeinfo;
                newViewAnimation = viewpropertyanimator;
                newView = view;
                super();
            }
        }

    }


    private class _cls3
        implements Runnable
    {

        private final DefaultItemAnimator this$0;
        private final ArrayList val$additions;

        public final void run()
        {
            ArrayList arraylist = (ArrayList)additions;
            int j = arraylist.size();
            for (int i = 0; i < j;)
            {
                final RecyclerView.ViewHolder holder = ((RecyclerView.ViewHolder) (arraylist.get(i)));
                i++;
                holder = (RecyclerView.ViewHolder)holder;
                DefaultItemAnimator defaultitemanimator = DefaultItemAnimator.this;
                final View view = holder.itemView;
                final ViewPropertyAnimator animation = view.animate();
                defaultitemanimator.mAddAnimations.add(holder);
                animation.alpha(1.0F).setDuration(((RecyclerView.ItemAnimator) (defaultitemanimator)).mAddDuration).setListener(defaultitemanimator. new _cls5()).start();
            }

            additions.clear();
            mAdditionsList.remove(additions);
        }

        _cls3()
        {
            this$0 = DefaultItemAnimator.this;
            additions = arraylist;
            super();
        }

        private class _cls5 extends AnimatorListenerAdapter
        {

            private final DefaultItemAnimator this$0;
            private final ViewPropertyAnimator val$animation;
            private final RecyclerView.ViewHolder val$holder;
            private final View val$view;

            public final void onAnimationCancel(Animator animator)
            {
                view.setAlpha(1.0F);
            }

            public final void onAnimationEnd(Animator animator)
            {
                animation.setListener(null);
                animator = DefaultItemAnimator.this;
                RecyclerView.ViewHolder viewholder = holder;
                if (((RecyclerView.ItemAnimator) (animator)).mListener != null)
                {
                    ((RecyclerView.ItemAnimator) (animator)).mListener.onAnimationFinished(viewholder);
                }
                mAddAnimations.remove(holder);
                animator = DefaultItemAnimator.this;
                if (!animator.isRunning())
                {
                    animator.dispatchAnimationsFinished();
                }
            }

            public final void onAnimationStart(Animator animator)
            {
            }

            _cls5()
            {
                this$0 = DefaultItemAnimator.this;
                holder = viewholder;
                view = view1;
                animation = viewpropertyanimator;
                super();
            }
        }

    }

}
