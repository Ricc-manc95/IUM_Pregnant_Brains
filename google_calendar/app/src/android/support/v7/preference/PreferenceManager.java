// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.SharedPreferences;

// Referenced classes of package android.support.v7.preference:
//            PreferenceDataStore, PreferenceScreen

public final class PreferenceManager
{

    private Context mContext;
    public android.content.SharedPreferences.Editor mEditor;
    private long mNextId;
    public boolean mNoCommit;
    public OnDisplayPreferenceDialogListener mOnDisplayPreferenceDialogListener;
    public OnNavigateToScreenListener mOnNavigateToScreenListener;
    public OnPreferenceTreeClickListener mOnPreferenceTreeClickListener;
    public PreferenceDataStore mPreferenceDataStore;
    public PreferenceScreen mPreferenceScreen;
    private SharedPreferences mSharedPreferences;
    private String mSharedPreferencesName;
    private int mStorage;

    public PreferenceManager(Context context)
    {
        mNextId = 0L;
        mStorage = 0;
        mContext = context;
        mSharedPreferencesName = (new StringBuilder()).append(context.getPackageName()).append("_preferences").toString();
        mSharedPreferences = null;
    }

    public final android.content.SharedPreferences.Editor getEditor()
    {
        if (mPreferenceDataStore != null)
        {
            return null;
        }
        if (mNoCommit)
        {
            if (mEditor == null)
            {
                mEditor = getSharedPreferences().edit();
            }
            return mEditor;
        } else
        {
            return getSharedPreferences().edit();
        }
    }

    final long getNextId()
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        l = mNextId;
        mNextId = 1L + l;
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final SharedPreferences getSharedPreferences()
    {
        if (mPreferenceDataStore != null)
        {
            return null;
        }
        if (mSharedPreferences == null)
        {
            mSharedPreferences = mContext.getSharedPreferences(mSharedPreferencesName, 0);
        }
        return mSharedPreferences;
    }
}
