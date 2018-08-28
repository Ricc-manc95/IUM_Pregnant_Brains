// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.AbsSavedState;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Referenced classes of package android.support.v7.preference:
//            PreferenceManager, PreferenceDataStore, PreferenceGroup, PreferenceViewHolder

public class Preference
    implements Comparable
{
    public static class BaseSavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();


        public BaseSavedState(Parcel parcel)
        {
            super(parcel);
        }

        public BaseSavedState(Parcelable parcelable)
        {
            super(parcelable);
        }

        class _cls1
            implements android.os.Parcelable.Creator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new BaseSavedState(parcel);
            }

            public final Object[] newArray(int i)
            {
                return new BaseSavedState[i];
            }

                _cls1()
                {
                }
        }

    }

    public static interface OnPreferenceChangeInternalListener
    {

        public abstract void onPreferenceChange(Preference preference);

        public abstract void onPreferenceHierarchyChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();

        public abstract void onPreferenceVisibilityChange(Preference preference);
    }

    public static interface OnPreferenceChangeListener
    {

        public abstract boolean onPreferenceChange(Preference preference, Object obj);
    }

    public static interface OnPreferenceClickListener
    {

        public abstract boolean onPreferenceClick(Preference preference);
    }


    private boolean mAllowDividerAbove;
    private boolean mAllowDividerBelow;
    private boolean mBaseMethodCalled;
    private final android.view.View.OnClickListener mClickListener;
    public Context mContext;
    private Object mDefaultValue;
    private String mDependencyKey;
    private boolean mDependencyMet;
    private List mDependents;
    private boolean mEnabled;
    public Bundle mExtras;
    public String mFragment;
    public boolean mHasId;
    private boolean mHasSingleLineTitleAttr;
    private Drawable mIcon;
    public int mIconResId;
    private boolean mIconSpaceReserved;
    public long mId;
    public Intent mIntent;
    public String mKey;
    public int mLayoutResId;
    public OnPreferenceChangeInternalListener mListener;
    public OnPreferenceChangeListener mOnChangeListener;
    public OnPreferenceClickListener mOnClickListener;
    public int mOrder;
    private boolean mParentDependencyMet;
    public PreferenceGroup mParentGroup;
    public boolean mPersistent;
    public PreferenceManager mPreferenceManager;
    private boolean mRequiresKey;
    public boolean mSelectable;
    private boolean mShouldDisableView;
    private boolean mSingleLineTitle;
    private CharSequence mSummary;
    public CharSequence mTitle;
    private int mViewId;
    public boolean mVisible;
    public int mWidgetLayoutResId;

    public Preference(Context context)
    {
        this(context, null);
    }

    public Preference(Context context, AttributeSet attributeset)
    {
        int i = 0x7f010228;
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x7f010228, typedvalue, true);
        if (typedvalue.resourceId == 0)
        {
            i = 0x101008e;
        }
        this(context, attributeset, i);
    }

    private Preference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public Preference(Context context, AttributeSet attributeset, int i, int j)
    {
        TypedArray typedarray;
        mOrder = 0x7fffffff;
        mViewId = 0;
        mEnabled = true;
        mSelectable = true;
        mPersistent = true;
        mDependencyMet = true;
        mParentDependencyMet = true;
        mVisible = true;
        mAllowDividerAbove = true;
        mAllowDividerBelow = true;
        mSingleLineTitle = true;
        mShouldDisableView = true;
        mLayoutResId = 0x7f05010e;
        mClickListener = new _cls1();
        mContext = context;
        typedarray = context.obtainStyledAttributes(attributeset, R.styleable.Preference, i, j);
        mIconResId = typedarray.getResourceId(R.styleable.Preference_icon, typedarray.getResourceId(R.styleable.Preference_android_icon, 0));
        i = R.styleable.Preference_key;
        j = R.styleable.Preference_android_key;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mKey = context;
        i = R.styleable.Preference_title;
        j = R.styleable.Preference_android_title;
        attributeset = typedarray.getText(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getText(j);
        }
        mTitle = context;
        i = R.styleable.Preference_summary;
        j = R.styleable.Preference_android_summary;
        attributeset = typedarray.getText(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getText(j);
        }
        mSummary = context;
        mOrder = typedarray.getInt(R.styleable.Preference_order, typedarray.getInt(R.styleable.Preference_android_order, 0x7fffffff));
        i = R.styleable.Preference_fragment;
        j = R.styleable.Preference_android_fragment;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mFragment = context;
        mLayoutResId = typedarray.getResourceId(R.styleable.Preference_layout, typedarray.getResourceId(R.styleable.Preference_android_layout, 0x7f05010e));
        mWidgetLayoutResId = typedarray.getResourceId(R.styleable.Preference_widgetLayout, typedarray.getResourceId(R.styleable.Preference_android_widgetLayout, 0));
        mEnabled = typedarray.getBoolean(R.styleable.Preference_enabled, typedarray.getBoolean(R.styleable.Preference_android_enabled, true));
        mSelectable = typedarray.getBoolean(R.styleable.Preference_selectable, typedarray.getBoolean(R.styleable.Preference_android_selectable, true));
        mPersistent = typedarray.getBoolean(R.styleable.Preference_persistent, typedarray.getBoolean(R.styleable.Preference_android_persistent, true));
        i = R.styleable.Preference_dependency;
        j = R.styleable.Preference_android_dependency;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mDependencyKey = context;
        mAllowDividerAbove = typedarray.getBoolean(R.styleable.Preference_allowDividerAbove, typedarray.getBoolean(R.styleable.Preference_allowDividerAbove, mSelectable));
        mAllowDividerBelow = typedarray.getBoolean(R.styleable.Preference_allowDividerBelow, typedarray.getBoolean(R.styleable.Preference_allowDividerBelow, mSelectable));
        if (!typedarray.hasValue(R.styleable.Preference_defaultValue)) goto _L2; else goto _L1
_L1:
        mDefaultValue = onGetDefaultValue(typedarray, R.styleable.Preference_defaultValue);
_L4:
        mShouldDisableView = typedarray.getBoolean(R.styleable.Preference_shouldDisableView, typedarray.getBoolean(R.styleable.Preference_android_shouldDisableView, true));
        mHasSingleLineTitleAttr = typedarray.hasValue(R.styleable.Preference_singleLineTitle);
        if (mHasSingleLineTitleAttr)
        {
            mSingleLineTitle = typedarray.getBoolean(R.styleable.Preference_singleLineTitle, typedarray.getBoolean(R.styleable.Preference_android_singleLineTitle, true));
        }
        mIconSpaceReserved = typedarray.getBoolean(R.styleable.Preference_iconSpaceReserved, typedarray.getBoolean(R.styleable.Preference_android_iconSpaceReserved, false));
        mVisible = typedarray.getBoolean(R.styleable.Preference_isPreferenceVisible, typedarray.getBoolean(R.styleable.Preference_isPreferenceVisible, true));
        typedarray.recycle();
        return;
_L2:
        if (typedarray.hasValue(R.styleable.Preference_android_defaultValue))
        {
            mDefaultValue = onGetDefaultValue(typedarray, R.styleable.Preference_android_defaultValue);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final void onDependencyChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMMH9AO______0(boolean flag)
    {
        if (mDependencyMet == flag)
        {
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mDependencyMet = flag;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    private final void setEnabledStateOnViews(View view, boolean flag)
    {
        view.setEnabled(flag);
        if (view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            for (int i = view.getChildCount() - 1; i >= 0; i--)
            {
                setEnabledStateOnViews(view.getChildAt(i), flag);
            }

        }
    }

    public int compareTo(Object obj)
    {
        obj = (Preference)obj;
        if (mOrder != ((Preference) (obj)).mOrder)
        {
            return mOrder - ((Preference) (obj)).mOrder;
        }
        if (mTitle == ((Preference) (obj)).mTitle)
        {
            return 0;
        }
        if (mTitle == null)
        {
            return 1;
        }
        if (((Preference) (obj)).mTitle == null)
        {
            return -1;
        } else
        {
            return mTitle.toString().compareToIgnoreCase(((Preference) (obj)).mTitle.toString());
        }
    }

    void dispatchRestoreInstanceState(Bundle bundle)
    {
        boolean flag;
        if (!TextUtils.isEmpty(mKey))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            bundle = bundle.getParcelable(mKey);
            if (bundle != null)
            {
                mBaseMethodCalled = false;
                onRestoreInstanceState(bundle);
                if (!mBaseMethodCalled)
                {
                    throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
                }
            }
        }
    }

    void dispatchSaveInstanceState(Bundle bundle)
    {
        boolean flag;
        if (!TextUtils.isEmpty(mKey))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            mBaseMethodCalled = false;
            Parcelable parcelable = onSaveInstanceState();
            if (!mBaseMethodCalled)
            {
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            }
            if (parcelable != null)
            {
                bundle.putParcelable(mKey, parcelable);
            }
        }
    }

    long getId()
    {
        return mId;
    }

    protected final boolean getPersistedBoolean(boolean flag)
    {
        if (!shouldPersist())
        {
            return flag;
        }
        PreferenceDataStore preferencedatastore;
        if (mPreferenceManager != null)
        {
            preferencedatastore = mPreferenceManager.mPreferenceDataStore;
        } else
        {
            preferencedatastore = null;
        }
        if (preferencedatastore != null)
        {
            return preferencedatastore.getBoolean(mKey, flag);
        } else
        {
            return mPreferenceManager.getSharedPreferences().getBoolean(mKey, flag);
        }
    }

    protected final int getPersistedInt(int i)
    {
        if (!shouldPersist())
        {
            return i;
        }
        PreferenceDataStore preferencedatastore;
        if (mPreferenceManager != null)
        {
            preferencedatastore = mPreferenceManager.mPreferenceDataStore;
        } else
        {
            preferencedatastore = null;
        }
        if (preferencedatastore != null)
        {
            return preferencedatastore.getInt(mKey, i);
        } else
        {
            return mPreferenceManager.getSharedPreferences().getInt(mKey, i);
        }
    }

    protected final String getPersistedString(String s)
    {
        if (!shouldPersist())
        {
            return s;
        }
        PreferenceDataStore preferencedatastore;
        if (mPreferenceManager != null)
        {
            preferencedatastore = mPreferenceManager.mPreferenceDataStore;
        } else
        {
            preferencedatastore = null;
        }
        if (preferencedatastore != null)
        {
            return preferencedatastore.getString(mKey, s);
        } else
        {
            return mPreferenceManager.getSharedPreferences().getString(mKey, s);
        }
    }

    public final Set getPersistedStringSet(Set set)
    {
        if (!shouldPersist())
        {
            return set;
        }
        PreferenceDataStore preferencedatastore;
        if (mPreferenceManager != null)
        {
            preferencedatastore = mPreferenceManager.mPreferenceDataStore;
        } else
        {
            preferencedatastore = null;
        }
        if (preferencedatastore != null)
        {
            return preferencedatastore.getStringSet(mKey, set);
        } else
        {
            return mPreferenceManager.getSharedPreferences().getStringSet(mKey, set);
        }
    }

    public CharSequence getSummary()
    {
        return mSummary;
    }

    public boolean isEnabled()
    {
        return mEnabled && mDependencyMet && mParentDependencyMet;
    }

    public void notifyChanged()
    {
        if (mListener != null)
        {
            mListener.onPreferenceChange(this);
        }
    }

    public void notifyDependencyChange(boolean flag)
    {
        List list = mDependents;
        if (list != null)
        {
            int j = list.size();
            int i = 0;
            while (i < j) 
            {
                ((Preference)list.get(i)).onDependencyChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMMH9AO______0(flag);
                i++;
            }
        }
    }

    public void onAttached()
    {
        Object obj = null;
        if (TextUtils.isEmpty(mDependencyKey)) goto _L2; else goto _L1
_L1:
        Preference preference;
        String s;
        s = mDependencyKey;
        preference = obj;
        if (TextUtils.isEmpty(s)) goto _L4; else goto _L3
_L3:
        if (mPreferenceManager != null) goto _L6; else goto _L5
_L5:
        preference = obj;
_L4:
        if (preference == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (preference.mDependents == null)
        {
            preference.mDependents = new ArrayList();
        }
        preference.mDependents.add(this);
        onDependencyChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMMH9AO______0(preference.shouldDisableDependents());
_L2:
        return;
_L6:
        PreferenceManager preferencemanager = mPreferenceManager;
        preference = obj;
        if (preferencemanager.mPreferenceScreen != null)
        {
            preference = preferencemanager.mPreferenceScreen.findPreference(s);
        }
        if (true) goto _L4; else goto _L7
_L7:
        throw new IllegalStateException((new StringBuilder("Dependency \"")).append(mDependencyKey).append("\" not found for preference \"").append(mKey).append("\" (title: \"").append(mTitle).append("\"").toString());
    }

    public final void onAttachedToHierarchy(PreferenceManager preferencemanager)
    {
        mPreferenceManager = preferencemanager;
        if (!mHasId)
        {
            mId = preferencemanager.getNextId();
        }
        if (mPreferenceManager != null)
        {
            preferencemanager = mPreferenceManager.mPreferenceDataStore;
        } else
        {
            preferencemanager = null;
        }
        if (preferencemanager == null) goto _L2; else goto _L1
_L1:
        onSetInitialValue(true, mDefaultValue);
_L4:
        return;
_L2:
        if (!shouldPersist())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mPreferenceManager != null)
        {
            if (mPreferenceManager != null)
            {
                preferencemanager = mPreferenceManager.mPreferenceDataStore;
            } else
            {
                preferencemanager = null;
            }
            if (preferencemanager == null)
            {
                break MISSING_BLOCK_LABEL_124;
            }
        }
        preferencemanager = null;
_L5:
        if (preferencemanager.contains(mKey))
        {
            break MISSING_BLOCK_LABEL_135;
        }
        if (mDefaultValue == null) goto _L4; else goto _L3
_L3:
        onSetInitialValue(false, mDefaultValue);
        return;
        preferencemanager = mPreferenceManager.getSharedPreferences();
          goto _L5
        onSetInitialValue(true, null);
        return;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceviewholder)
    {
        boolean flag = false;
        preferenceviewholder.itemView.setOnClickListener(mClickListener);
        preferenceviewholder.itemView.setId(0);
        Object obj = (TextView)preferenceviewholder.findViewById(0x1020016);
        int i;
        if (obj != null)
        {
            Object obj1 = mTitle;
            boolean flag1;
            if (!TextUtils.isEmpty(((CharSequence) (obj1))))
            {
                ((TextView) (obj)).setText(((CharSequence) (obj1)));
                ((TextView) (obj)).setVisibility(0);
                if (mHasSingleLineTitleAttr)
                {
                    ((TextView) (obj)).setSingleLine(mSingleLineTitle);
                }
            } else
            {
                ((TextView) (obj)).setVisibility(8);
            }
        }
        obj = (TextView)preferenceviewholder.findViewById(0x1020010);
        if (obj != null)
        {
            obj1 = getSummary();
            if (!TextUtils.isEmpty(((CharSequence) (obj1))))
            {
                ((TextView) (obj)).setText(((CharSequence) (obj1)));
                ((TextView) (obj)).setVisibility(0);
            } else
            {
                ((TextView) (obj)).setVisibility(8);
            }
        }
        obj = (ImageView)preferenceviewholder.findViewById(0x1020006);
        if (obj != null)
        {
            if (mIconResId != 0 || mIcon != null)
            {
                if (mIcon == null)
                {
                    mIcon = ContextCompat.getDrawable(mContext, mIconResId);
                }
                if (mIcon != null)
                {
                    ((ImageView) (obj)).setImageDrawable(mIcon);
                }
            }
            if (mIcon != null)
            {
                i = 0;
            } else
            if (mIconSpaceReserved)
            {
                i = 4;
            } else
            {
                i = 8;
            }
            ((ImageView) (obj)).setVisibility(i);
        }
        obj1 = preferenceviewholder.findViewById(0x7f10018f);
        obj = obj1;
        if (obj1 == null)
        {
            obj = preferenceviewholder.findViewById(0x102003e);
        }
        if (obj != null)
        {
            if (mIcon != null)
            {
                i = ((flag) ? 1 : 0);
            } else
            if (mIconSpaceReserved)
            {
                i = 4;
            } else
            {
                i = 8;
            }
            ((View) (obj)).setVisibility(i);
        }
        if (mShouldDisableView)
        {
            setEnabledStateOnViews(preferenceviewholder.itemView, isEnabled());
        } else
        {
            setEnabledStateOnViews(preferenceviewholder.itemView, true);
        }
        flag1 = mSelectable;
        preferenceviewholder.itemView.setFocusable(flag1);
        preferenceviewholder.itemView.setClickable(flag1);
        preferenceviewholder.mDividerAllowedAbove = mAllowDividerAbove;
        preferenceviewholder.mDividerAllowedBelow = mAllowDividerBelow;
    }

    protected void onClick()
    {
    }

    public void onDetached()
    {
        Object obj = null;
        if (mDependencyKey == null) goto _L2; else goto _L1
_L1:
        Preference preference;
        String s;
        s = mDependencyKey;
        preference = obj;
        if (TextUtils.isEmpty(s)) goto _L4; else goto _L3
_L3:
        if (mPreferenceManager != null) goto _L6; else goto _L5
_L5:
        preference = obj;
_L4:
        if (preference != null && preference.mDependents != null)
        {
            preference.mDependents.remove(this);
        }
_L2:
        return;
_L6:
        PreferenceManager preferencemanager = mPreferenceManager;
        preference = obj;
        if (preferencemanager.mPreferenceScreen != null)
        {
            preference = preferencemanager.mPreferenceScreen.findPreference(s);
        }
        if (true) goto _L4; else goto _L7
_L7:
    }

    public Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return null;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
    }

    public final void onParentChanged$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMMH9AO______0(boolean flag)
    {
        if (mParentDependencyMet == flag)
        {
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mParentDependencyMet = flag;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        mBaseMethodCalled = true;
        if (parcelable != BaseSavedState.EMPTY_STATE && parcelable != null)
        {
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        } else
        {
            return;
        }
    }

    public Parcelable onSaveInstanceState()
    {
        mBaseMethodCalled = true;
        return BaseSavedState.EMPTY_STATE;
    }

    public void onSetInitialValue(boolean flag, Object obj)
    {
    }

    public void performClick(View view)
    {
label0:
        {
            if (!isEnabled())
            {
                break label0;
            }
            onClick();
            if (mOnClickListener != null && mOnClickListener.onPreferenceClick(this))
            {
                break label0;
            }
            view = mPreferenceManager;
            if (view != null)
            {
                view = ((PreferenceManager) (view)).mOnPreferenceTreeClickListener;
                if (view != null && view.onPreferenceTreeClick(this))
                {
                    break label0;
                }
            }
            if (mIntent != null)
            {
                mContext.startActivity(mIntent);
            }
        }
    }

    public final boolean persistString(String s)
    {
        PreferenceDataStore preferencedatastore;
        boolean flag;
        preferencedatastore = null;
        flag = false;
        if (!shouldPersist())
        {
            return false;
        }
        if (TextUtils.equals(s, getPersistedString(null)))
        {
            return true;
        }
        if (mPreferenceManager != null)
        {
            preferencedatastore = mPreferenceManager.mPreferenceDataStore;
        }
        if (preferencedatastore == null) goto _L2; else goto _L1
_L1:
        preferencedatastore.putString(mKey, s);
_L4:
        return true;
_L2:
        android.content.SharedPreferences.Editor editor = mPreferenceManager.getEditor();
        editor.putString(mKey, s);
        if (!mPreferenceManager.mNoCommit)
        {
            flag = true;
        }
        if (flag)
        {
            editor.apply();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void setIcon(Drawable drawable)
    {
        if (drawable == null && mIcon != null || drawable != null && mIcon != drawable)
        {
            mIcon = drawable;
            mIconResId = 0;
            notifyChanged();
        }
    }

    public final void setKey(String s)
    {
        mKey = s;
        if (mRequiresKey)
        {
            boolean flag;
            if (!TextUtils.isEmpty(mKey))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                if (TextUtils.isEmpty(mKey))
                {
                    throw new IllegalStateException("Preference does not have a key assigned.");
                }
                mRequiresKey = true;
            }
        }
    }

    public void setSummary(CharSequence charsequence)
    {
        if (charsequence == null && mSummary != null || charsequence != null && !charsequence.equals(mSummary))
        {
            mSummary = charsequence;
            notifyChanged();
        }
    }

    public final void setTitle(CharSequence charsequence)
    {
        if (charsequence == null && mTitle != null || charsequence != null && !charsequence.equals(mTitle))
        {
            mTitle = charsequence;
            notifyChanged();
        }
    }

    public boolean shouldDisableDependents()
    {
        return !isEnabled();
    }

    protected final boolean shouldPersist()
    {
        if (mPreferenceManager != null && mPersistent)
        {
            boolean flag;
            if (!TextUtils.isEmpty(mKey))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        CharSequence charsequence = mTitle;
        if (!TextUtils.isEmpty(charsequence))
        {
            stringbuilder.append(charsequence).append(' ');
        }
        charsequence = getSummary();
        if (!TextUtils.isEmpty(charsequence))
        {
            stringbuilder.append(charsequence).append(' ');
        }
        if (stringbuilder.length() > 0)
        {
            stringbuilder.setLength(stringbuilder.length() - 1);
        }
        return stringbuilder.toString();
    }

    final void unregisterDependency()
    {
        Object obj = null;
        if (mDependencyKey == null) goto _L2; else goto _L1
_L1:
        Preference preference;
        String s;
        s = mDependencyKey;
        preference = obj;
        if (TextUtils.isEmpty(s)) goto _L4; else goto _L3
_L3:
        if (mPreferenceManager != null) goto _L6; else goto _L5
_L5:
        preference = obj;
_L4:
        if (preference != null && preference.mDependents != null)
        {
            preference.mDependents.remove(this);
        }
_L2:
        return;
_L6:
        PreferenceManager preferencemanager = mPreferenceManager;
        preference = obj;
        if (preferencemanager.mPreferenceScreen != null)
        {
            preference = preferencemanager.mPreferenceScreen.findPreference(s);
        }
        if (true) goto _L4; else goto _L7
_L7:
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final Preference this$0;

        public final void onClick(View view)
        {
            performClick(view);
        }

        _cls1()
        {
            this$0 = Preference.this;
            super();
        }
    }

}
