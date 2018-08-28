// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.zzg;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.gms.auth.api.signin:
//            zza

public class GoogleSignInAccount extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.auth.api.signin.zza();
    private static Clock zzalh;
    private static Comparator zzaln = new _cls1();
    public final int versionCode;
    public String zzGm;
    public String zzahI;
    public List zzajT;
    public String zzakG;
    public String zzakt;
    public String zzaku;
    public String zzali;
    public Uri zzalj;
    public String zzalk;
    public long zzall;
    public String zzalm;

    GoogleSignInAccount(int i, String s, String s1, String s2, String s3, Uri uri, String s4, 
            long l, String s5, List list, String s6, String s7)
    {
        versionCode = i;
        zzGm = s;
        zzakG = s1;
        zzali = s2;
        zzahI = s3;
        zzalj = uri;
        zzalk = s4;
        zzall = l;
        zzalm = s5;
        zzajT = list;
        zzakt = s6;
        zzaku = s7;
    }

    public static GoogleSignInAccount zzbU(String s)
        throws JSONException
    {
        if (TextUtils.isEmpty(s))
        {
            return null;
        }
        JSONObject jsonobject = new JSONObject(s);
        s = null;
        Object obj = jsonobject.optString("photoUrl", null);
        if (!TextUtils.isEmpty(((CharSequence) (obj))))
        {
            s = Uri.parse(((String) (obj)));
        }
        long l = Long.parseLong(jsonobject.getString("expirationTime"));
        HashSet hashset = new HashSet();
        obj = jsonobject.getJSONArray("grantedScopes");
        int j = ((JSONArray) (obj)).length();
        for (int i = 0; i < j; i++)
        {
            hashset.add(new Scope(((JSONArray) (obj)).getString(i)));
        }

        String s1 = jsonobject.optString("id");
        String s2 = jsonobject.optString("tokenId", null);
        String s3 = jsonobject.optString("email", null);
        String s4 = jsonobject.optString("displayName", null);
        String s5 = jsonobject.optString("givenName", null);
        String s6 = jsonobject.optString("familyName", null);
        obj = Long.valueOf(l);
        String s7 = jsonobject.getString("obfuscatedIdentifier");
        if (obj == null)
        {
            obj = Long.valueOf(zzalh.currentTimeMillis() / 1000L);
        }
        l = ((Long) (obj)).longValue();
        if (TextUtils.isEmpty(s7))
        {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        if (hashset == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            s = new GoogleSignInAccount(3, s1, s2, s3, s4, s, null, l, s7, new ArrayList((Collection)hashset), s5, s6);
            s.zzalk = jsonobject.optString("serverAuthCode", null);
            return s;
        }
    }

    private final JSONObject zzqe()
    {
        Object obj;
        JSONArray jsonarray;
        obj = new JSONObject();
        try
        {
            if (zzGm != null)
            {
                ((JSONObject) (obj)).put("id", zzGm);
            }
            if (zzakG != null)
            {
                ((JSONObject) (obj)).put("tokenId", zzakG);
            }
            if (zzali != null)
            {
                ((JSONObject) (obj)).put("email", zzali);
            }
            if (zzahI != null)
            {
                ((JSONObject) (obj)).put("displayName", zzahI);
            }
            if (zzakt != null)
            {
                ((JSONObject) (obj)).put("givenName", zzakt);
            }
            if (zzaku != null)
            {
                ((JSONObject) (obj)).put("familyName", zzaku);
            }
            if (zzalj != null)
            {
                ((JSONObject) (obj)).put("photoUrl", zzalj.toString());
            }
            if (zzalk != null)
            {
                ((JSONObject) (obj)).put("serverAuthCode", zzalk);
            }
            ((JSONObject) (obj)).put("expirationTime", zzall);
            ((JSONObject) (obj)).put("obfuscatedIdentifier", zzalm);
            jsonarray = new JSONArray();
            Collections.sort(zzajT, zzaln);
            for (Iterator iterator = zzajT.iterator(); iterator.hasNext(); jsonarray.put(((Scope)iterator.next()).zzaJs)) { }
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw new RuntimeException(((Throwable) (obj)));
        }
        ((JSONObject) (obj)).put("grantedScopes", jsonarray);
        return ((JSONObject) (obj));
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof GoogleSignInAccount))
        {
            return false;
        } else
        {
            return ((GoogleSignInAccount)obj).zzqe().toString().equals(zzqe().toString());
        }
    }

    public int hashCode()
    {
        return zzqe().toString().hashCode();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = versionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        Object obj = zzGm;
        if (obj != null)
        {
            parcel.writeInt(-65534);
            parcel.writeInt(0);
            int l = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int i3 = parcel.dataPosition();
            parcel.setDataPosition(l - 4);
            parcel.writeInt(i3 - l);
            parcel.setDataPosition(i3);
        }
        obj = zzakG;
        if (obj != null)
        {
            parcel.writeInt(-65533);
            parcel.writeInt(0);
            int i1 = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int j3 = parcel.dataPosition();
            parcel.setDataPosition(i1 - 4);
            parcel.writeInt(j3 - i1);
            parcel.setDataPosition(j3);
        }
        obj = zzali;
        if (obj != null)
        {
            parcel.writeInt(-65532);
            parcel.writeInt(0);
            int j1 = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int k3 = parcel.dataPosition();
            parcel.setDataPosition(j1 - 4);
            parcel.writeInt(k3 - j1);
            parcel.setDataPosition(k3);
        }
        obj = zzahI;
        if (obj != null)
        {
            parcel.writeInt(-65531);
            parcel.writeInt(0);
            int k1 = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int l3 = parcel.dataPosition();
            parcel.setDataPosition(k1 - 4);
            parcel.writeInt(l3 - k1);
            parcel.setDataPosition(l3);
        }
        obj = zzalj;
        if (obj != null)
        {
            parcel.writeInt(-65530);
            parcel.writeInt(0);
            int l1 = parcel.dataPosition();
            ((Parcelable) (obj)).writeToParcel(parcel, i);
            i = parcel.dataPosition();
            parcel.setDataPosition(l1 - 4);
            parcel.writeInt(i - l1);
            parcel.setDataPosition(i);
        }
        obj = zzalk;
        if (obj != null)
        {
            parcel.writeInt(-65529);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int i2 = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(i2 - i);
            parcel.setDataPosition(i2);
        }
        long l4 = zzall;
        zzc.zzb(parcel, 8, 8);
        parcel.writeLong(l4);
        obj = zzalm;
        if (obj != null)
        {
            parcel.writeInt(-65527);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int j2 = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j2 - i);
            parcel.setDataPosition(j2);
        }
        zzc.zzc(parcel, 10, zzajT, false);
        obj = zzakt;
        if (obj != null)
        {
            parcel.writeInt(-65525);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int k2 = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(k2 - i);
            parcel.setDataPosition(k2);
        }
        obj = zzaku;
        if (obj != null)
        {
            parcel.writeInt(-65524);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int l2 = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(l2 - i);
            parcel.setDataPosition(l2);
        }
        i = parcel.dataPosition();
        parcel.setDataPosition(j - 4);
        parcel.writeInt(i - j);
        parcel.setDataPosition(i);
    }

    static 
    {
        zzalh = zzg.zzaTf;
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
