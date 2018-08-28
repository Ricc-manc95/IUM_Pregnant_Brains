// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.textfield;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;
import android.text.TextUtils;

// Referenced classes of package android.support.design.textfield:
//            TextInputLayout

static final class isPasswordToggledVisible extends AbsSavedState
{

    public static final android.os.nputLayout.SavedState._cls1 CREATOR = new _cls1();
    public CharSequence error;
    public boolean isPasswordToggledVisible;

    public final String toString()
    {
        String s = Integer.toHexString(System.identityHashCode(this));
        String s1 = String.valueOf(error);
        return (new StringBuilder(String.valueOf(s).length() + 35 + String.valueOf(s1).length())).append("TextInputLayout.SavedState{").append(s).append(" error=").append(s1).append("}").toString();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        TextUtils.writeToParcel(error, parcel, i);
        if (isPasswordToggledVisible)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
    }


    _cls1(Parcel parcel, ClassLoader classloader)
    {
        super(parcel, classloader);
        error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.omParcel(parcel);
        boolean flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isPasswordToggledVisible = flag;
    }

    isPasswordToggledVisible(Parcelable parcelable)
    {
        super(parcelable);
    }

    class _cls1
        implements android.os.Parcelable.ClassLoaderCreator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TextInputLayout.SavedState(parcel, null);
        }

        public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return new TextInputLayout.SavedState(parcel, classloader);
        }

        public final Object[] newArray(int i)
        {
            return new TextInputLayout.SavedState[i];
        }

            _cls1()
            {
            }
    }

}
