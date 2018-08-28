// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

// Referenced classes of package android.support.v4.app:
//            Fragment

public class ListFragment extends Fragment
{

    private ListAdapter mAdapter;
    private View mEmptyView;
    private final Handler mHandler = new Handler();
    public ListView mList;
    private View mListContainer;
    private boolean mListShown;
    private final android.widget.AdapterView.OnItemClickListener mOnClickListener = new _cls2();
    private View mProgressContainer;
    private final Runnable mRequestFocus = new _cls1();
    private TextView mStandardEmptyView;

    public ListFragment()
    {
    }

    public static void onListItemClick$51662RJ4E9NMIP1FETKM8PR5EGNKOQBJEHB6IPBN7D662RJ4E9NMIP1FEPKMATPFAPKMATPR9552ILG_0()
    {
    }

    private final void setListShown(boolean flag, boolean flag1)
    {
        ensureList();
        if (mProgressContainer == null)
        {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (mListShown == flag)
        {
            return;
        }
        mListShown = flag;
        if (flag)
        {
            if (flag1)
            {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 0x10a0001));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 0x10a0000));
            } else
            {
                mProgressContainer.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(8);
            mListContainer.setVisibility(0);
            return;
        }
        if (flag1)
        {
            mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 0x10a0000));
            mListContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 0x10a0001));
        } else
        {
            mProgressContainer.clearAnimation();
            mListContainer.clearAnimation();
        }
        mProgressContainer.setVisibility(0);
        mListContainer.setVisibility(8);
    }

    public final void ensureList()
    {
        boolean flag1 = false;
        if (mList != null)
        {
            return;
        }
        Object obj = super.mView;
        if (obj == null)
        {
            throw new IllegalStateException("Content view not yet created");
        }
        if (obj instanceof ListView)
        {
            mList = (ListView)obj;
        } else
        {
            mStandardEmptyView = (TextView)((View) (obj)).findViewById(0xff0001);
            if (mStandardEmptyView == null)
            {
                mEmptyView = ((View) (obj)).findViewById(0x1020004);
            } else
            {
                mStandardEmptyView.setVisibility(8);
            }
            mProgressContainer = ((View) (obj)).findViewById(0xff0002);
            mListContainer = ((View) (obj)).findViewById(0xff0003);
            obj = ((View) (obj)).findViewById(0x102000a);
            if (!(obj instanceof ListView))
            {
                if (obj == null)
                {
                    throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
                } else
                {
                    throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                }
            }
            mList = (ListView)obj;
            if (mEmptyView != null)
            {
                mList.setEmptyView(mEmptyView);
            }
        }
        mListShown = true;
        mList.setOnItemClickListener(mOnClickListener);
        if (mAdapter != null)
        {
            obj = mAdapter;
            mAdapter = null;
            boolean flag;
            if (mAdapter != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mAdapter = ((ListAdapter) (obj));
            if (mList != null)
            {
                mList.setAdapter(((ListAdapter) (obj)));
                if (!mListShown && !flag)
                {
                    if (super.mView.getWindowToken() != null)
                    {
                        flag1 = true;
                    }
                    setListShown(true, flag1);
                }
            }
        } else
        if (mProgressContainer != null)
        {
            setListShown(false, false);
        }
        mHandler.post(mRequestFocus);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        viewgroup = getContext();
        layoutinflater = new FrameLayout(viewgroup);
        bundle = new LinearLayout(viewgroup);
        bundle.setId(0xff0002);
        bundle.setOrientation(1);
        bundle.setVisibility(8);
        bundle.setGravity(17);
        bundle.addView(new ProgressBar(viewgroup, null, 0x101007a), new android.widget.FrameLayout.LayoutParams(-2, -2));
        layoutinflater.addView(bundle, new android.widget.FrameLayout.LayoutParams(-1, -1));
        bundle = new FrameLayout(viewgroup);
        bundle.setId(0xff0003);
        TextView textview = new TextView(viewgroup);
        textview.setId(0xff0001);
        textview.setGravity(17);
        bundle.addView(textview, new android.widget.FrameLayout.LayoutParams(-1, -1));
        viewgroup = new ListView(viewgroup);
        viewgroup.setId(0x102000a);
        viewgroup.setDrawSelectorOnTop(false);
        bundle.addView(viewgroup, new android.widget.FrameLayout.LayoutParams(-1, -1));
        layoutinflater.addView(bundle, new android.widget.FrameLayout.LayoutParams(-1, -1));
        layoutinflater.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -1));
        return layoutinflater;
    }

    public final void onDestroyView()
    {
        mHandler.removeCallbacks(mRequestFocus);
        mList = null;
        mListShown = false;
        mListContainer = null;
        mProgressContainer = null;
        mEmptyView = null;
        mStandardEmptyView = null;
        super.onDestroyView();
    }

    public final void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        ensureList();
    }

    private class _cls1
        implements Runnable
    {

        private final ListFragment this$0;

        public final void run()
        {
            mList.focusableViewAvailable(mList);
        }

        _cls1()
        {
            this$0 = ListFragment.this;
            super();
        }
    }


    private class _cls2
        implements android.widget.AdapterView.OnItemClickListener
    {

        private final ListFragment this$0;

        public final void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            ListFragment.onListItemClick$51662RJ4E9NMIP1FETKM8PR5EGNKOQBJEHB6IPBN7D662RJ4E9NMIP1FEPKMATPFAPKMATPR9552ILG_0();
        }

        _cls2()
        {
            this$0 = ListFragment.this;
            super();
        }
    }

}
