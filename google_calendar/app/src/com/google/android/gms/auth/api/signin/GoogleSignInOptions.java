// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzf;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.auth.api.signin:
//            zzb

public class GoogleSignInOptions extends zza
    implements com.google.android.gms.common.api.Api.ApiOptions, com.google.android.gms.common.api.Api.ApiOptions.HasOptions, ReflectedParcelable
{
    public static final class Builder
    {

        public Set zzalx;

        public Builder()
        {
            zzalx = new HashSet();
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new zzb();
    public static final Scope zzalo;
    public static final Scope zzalq;
    public final int versionCode;
    public Account zzafe;
    public final ArrayList zzalr;
    public boolean zzals;
    public final boolean zzalt;
    public final boolean zzalu;
    public String zzalv;
    public String zzalw;

    GoogleSignInOptions(int i, ArrayList arraylist, Account account, boolean flag, boolean flag1, boolean flag2, String s, 
            String s1)
    {
        versionCode = i;
        zzalr = arraylist;
        zzafe = account;
        zzals = flag;
        zzalt = flag1;
        zzalu = flag2;
        zzalv = s;
        zzalw = s1;
    }

    GoogleSignInOptions(Set set, Account account, boolean flag, boolean flag1, boolean flag2, String s, String s1)
    {
        this(2, new ArrayList(set), account, flag, flag1, flag2, s, s1);
    }

    public static GoogleSignInOptions zzbW(String s)
        throws JSONException
    {
        if (TextUtils.isEmpty(s))
        {
            return null;
        }
        JSONObject jsonobject = new JSONObject(s);
        HashSet hashset = new HashSet();
        s = jsonobject.getJSONArray("scopes");
        int j = s.length();
        for (int i = 0; i < j; i++)
        {
            hashset.add(new Scope(s.getString(i)));
        }

        s = jsonobject.optString("accountName", null);
        if (!TextUtils.isEmpty(s))
        {
            s = new Account(s, "com.google");
        } else
        {
            s = null;
        }
        return new GoogleSignInOptions(hashset, s, jsonobject.getBoolean("idTokenRequested"), jsonobject.getBoolean("serverAuthRequested"), jsonobject.getBoolean("forceCodeForRefreshToken"), jsonobject.optString("serverClientId", null), jsonobject.optString("hostedDomain", null));
    }

    public boolean equals(Object obj)
    {
        if (obj != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        if (zzalr.size() != (new ArrayList(((GoogleSignInOptions) (obj = (GoogleSignInOptions)obj)).zzalr)).size() || !zzalr.containsAll(new ArrayList(((GoogleSignInOptions) (obj)).zzalr))) goto _L1; else goto _L3
_L3:
        if (zzafe != null) goto _L5; else goto _L4
_L4:
        if (((GoogleSignInOptions) (obj)).zzafe != null) goto _L1; else goto _L6
_L6:
        if (!TextUtils.isEmpty(zzalv)) goto _L8; else goto _L7
_L7:
        if (!TextUtils.isEmpty(((GoogleSignInOptions) (obj)).zzalv))
        {
            break; /* Loop/switch isn't completed */
        }
_L10:
        if (zzalu == ((GoogleSignInOptions) (obj)).zzalu && zzals == ((GoogleSignInOptions) (obj)).zzals && zzalt == ((GoogleSignInOptions) (obj)).zzalt)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L5:
        if (!zzafe.equals(((GoogleSignInOptions) (obj)).zzafe)) goto _L9; else goto _L6
_L9:
        break; /* Loop/switch isn't completed */
_L8:
        boolean flag;
        try
        {
            flag = zzalv.equals(((GoogleSignInOptions) (obj)).zzalv);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            return false;
        }
        if (!flag) goto _L1; else goto _L10
    }

    public int hashCode()
    {
        boolean flag = true;
        Object obj = new ArrayList();
        Object obj1 = (ArrayList)zzalr;
        int k = ((ArrayList) (obj1)).size();
        for (int i = 0; i < k;)
        {
            Object obj2 = ((ArrayList) (obj1)).get(i);
            i++;
            ((ArrayList) (obj)).add(((Scope)obj2).zzaJs);
        }

        Collections.sort(((java.util.List) (obj)));
        obj1 = new zzf();
        k = zzf.zzalJ;
        int l = ((zzf) (obj1)).zzalK;
        int j;
        boolean flag1;
        if (obj == null)
        {
            j = 0;
        } else
        {
            j = obj.hashCode();
        }
        obj1.zzalK = j + l * k;
        obj = zzafe;
        k = zzf.zzalJ;
        l = ((zzf) (obj1)).zzalK;
        if (obj == null)
        {
            j = 0;
        } else
        {
            j = obj.hashCode();
        }
        obj1.zzalK = j + k * l;
        obj = zzalv;
        k = zzf.zzalJ;
        l = ((zzf) (obj1)).zzalK;
        if (obj == null)
        {
            j = 0;
        } else
        {
            j = obj.hashCode();
        }
        obj1.zzalK = j + k * l;
        flag1 = zzalu;
        k = zzf.zzalJ;
        l = ((zzf) (obj1)).zzalK;
        if (flag1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        obj1.zzalK = j + k * l;
        flag1 = zzals;
        k = zzf.zzalJ;
        l = ((zzf) (obj1)).zzalK;
        if (flag1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        obj1.zzalK = j + k * l;
        flag1 = zzalt;
        k = zzf.zzalJ;
        l = ((zzf) (obj1)).zzalK;
        if (flag1)
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            j = 0;
        }
        obj1.zzalK = k * l + j;
        return ((zzf) (obj1)).zzalK;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int l = parcel.dataPosition();
        int i1 = versionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(i1);
        zzc.zzc(parcel, 2, new ArrayList(zzalr), false);
        Object obj = zzafe;
        if (obj != null)
        {
            parcel.writeInt(-65533);
            parcel.writeInt(0);
            int j1 = parcel.dataPosition();
            ((Parcelable) (obj)).writeToParcel(parcel, i);
            i = parcel.dataPosition();
            parcel.setDataPosition(j1 - 4);
            parcel.writeInt(i - j1);
            parcel.setDataPosition(i);
        }
        boolean flag1 = zzals;
        zzc.zzb(parcel, 4, 4);
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        flag1 = zzalt;
        zzc.zzb(parcel, 5, 4);
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        flag1 = zzalu;
        zzc.zzb(parcel, 6, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        obj = zzalv;
        if (obj != null)
        {
            parcel.writeInt(-65529);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
        }
        obj = zzalw;
        if (obj != null)
        {
            parcel.writeInt(-65528);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int k = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(k - i);
            parcel.setDataPosition(k);
        }
        i = parcel.dataPosition();
        parcel.setDataPosition(l - 4);
        parcel.writeInt(i - l);
        parcel.setDataPosition(i);
    }

    static 
    {
        zzalo = new Scope("profile");
        new Scope("email");
        zzalq = new Scope("openid");
        Builder builder = new Builder();
        builder.zzalx.add(zzalq);
        builder.zzalx.add(zzalo);
        new GoogleSignInOptions(builder.zzalx, null, false, false, false, null, null);
        new _cls1();
    }

    private class _cls1
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (Scope)obj;
            obj1 = (Scope)obj1;
            return ((Scope) (obj)).zzaJs.compareTo(((Scope) (obj1)).zzaJs);
        }

        _cls1()
        {
        }
    }

}
