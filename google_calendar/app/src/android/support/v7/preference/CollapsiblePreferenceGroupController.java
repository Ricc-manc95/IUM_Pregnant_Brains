// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v7.preference:
//            Preference, PreferenceGroup, PreferenceGroupAdapter

final class CollapsiblePreferenceGroupController
{

    private final Context mContext;
    public boolean mHasExpandablePreference;
    public final PreferenceGroupAdapter mPreferenceGroupAdapter;

    CollapsiblePreferenceGroupController(PreferenceGroup preferencegroup, PreferenceGroupAdapter preferencegroupadapter)
    {
        mHasExpandablePreference = false;
        mPreferenceGroupAdapter = preferencegroupadapter;
        mContext = ((Preference) (preferencegroup)).mContext;
    }

    final List createInnerVisiblePreferencesList(final PreferenceGroup group)
    {
        ArrayList arraylist;
        Object obj;
        Object obj1;
        int i;
        int j;
        boolean flag;
        j = 0;
        mHasExpandablePreference = false;
        int k;
        int l;
        if (group.mInitialExpandedChildrenCount != 0x7fffffff)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        arraylist = new ArrayList();
        obj = new ArrayList();
        l = group.mPreferenceList.size();
        k = 0;
        if (k >= l)
        {
            break MISSING_BLOCK_LABEL_281;
        }
        obj1 = (Preference)group.mPreferenceList.get(k);
        i = j;
        if (((Preference) (obj1)).mVisible)
        {
            if (!flag || j < group.mInitialExpandedChildrenCount)
            {
                arraylist.add(obj1);
            } else
            {
                ((List) (obj)).add(obj1);
            }
            if (obj1 instanceof PreferenceGroup)
            {
                break; /* Loop/switch isn't completed */
            }
            i = j + 1;
        }
_L4:
        k++;
        j = i;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_50;
_L1:
        obj1 = (PreferenceGroup)obj1;
        i = j;
        if (((PreferenceGroup) (obj1)).isOnSameScreenAsChildren())
        {
            obj1 = createInnerVisiblePreferencesList(((PreferenceGroup) (obj1)));
            if (flag && mHasExpandablePreference)
            {
                throw new IllegalArgumentException("Nested expand buttons are not supported!");
            }
            obj1 = ((List) (obj1)).iterator();
            do
            {
                i = j;
                if (!((Iterator) (obj1)).hasNext())
                {
                    break;
                }
                Preference preference = (Preference)((Iterator) (obj1)).next();
                if (!flag || j < group.mInitialExpandedChildrenCount)
                {
                    arraylist.add(preference);
                } else
                {
                    ((List) (obj)).add(preference);
                }
                j++;
            } while (true);
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (flag && j > group.mInitialExpandedChildrenCount)
        {
            obj = new ExpandButton(mContext, ((List) (obj)), group.getId());
            obj.mOnClickListener = new _cls1();
            arraylist.add(obj);
        }
        mHasExpandablePreference = mHasExpandablePreference | flag;
        return arraylist;
    }

    private class ExpandButton extends Preference
    {

        private long mId;

        public final long getId()
        {
            return mId;
        }

        public final void onBindViewHolder(PreferenceViewHolder preferenceviewholder)
        {
            super.onBindViewHolder(preferenceviewholder);
            preferenceviewholder.mDividerAllowedAbove = false;
        }

        ExpandButton(Context context, List list, long l)
        {
            super(context);
            super.mLayoutResId = 0x7f050062;
            setIcon(ContextCompat.getDrawable(super.mContext, 0x7f02011f));
            super.mIconResId = 0x7f02011f;
            setTitle(super.mContext.getString(0x7f1301e3));
            if (999 != super.mOrder)
            {
                super.mOrder = 999;
                if (super.mListener != null)
                {
                    super.mListener.onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
                }
            }
            context = null;
            ArrayList arraylist = new ArrayList();
            Iterator iterator = list.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Preference preference = (Preference)iterator.next();
                list = preference.mTitle;
                if ((preference instanceof PreferenceGroup) && !TextUtils.isEmpty(list))
                {
                    arraylist.add((PreferenceGroup)preference);
                }
                if (arraylist.contains(preference.mParentGroup))
                {
                    if (preference instanceof PreferenceGroup)
                    {
                        arraylist.add((PreferenceGroup)preference);
                    }
                } else
                if (!TextUtils.isEmpty(list))
                {
                    if (context == null)
                    {
                        context = list;
                    } else
                    {
                        context = super.mContext.getString(0x7f130469, new Object[] {
                            context, list
                        });
                    }
                }
            } while (true);
            setSummary(context);
            mId = 0xf4240L + l;
        }
    }


    private class _cls1
        implements Preference.OnPreferenceClickListener
    {

        private final CollapsiblePreferenceGroupController this$0;
        private final PreferenceGroup val$group;

        public final boolean onPreferenceClick(Preference preference)
        {
            group.setInitialExpandedChildrenCount(0x7fffffff);
            preference = mPreferenceGroupAdapter;
            ((PreferenceGroupAdapter) (preference)).mHandler.removeCallbacks(((PreferenceGroupAdapter) (preference)).mSyncRunnable);
            ((PreferenceGroupAdapter) (preference)).mHandler.post(((PreferenceGroupAdapter) (preference)).mSyncRunnable);
            return true;
        }

        _cls1()
        {
            this$0 = CollapsiblePreferenceGroupController.this;
            group = preferencegroup;
            super();
        }
    }

}
