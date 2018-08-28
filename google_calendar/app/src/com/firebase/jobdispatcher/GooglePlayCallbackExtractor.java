// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;

// Referenced classes of package com.firebase.jobdispatcher:
//            GooglePlayJobCallback

final class GooglePlayCallbackExtractor
{

    private static Boolean shouldReadKeysAsStringsCached = null;

    GooglePlayCallbackExtractor()
    {
    }

    static Pair extractWrappedBinderFromParcel(Bundle bundle)
    {
        Parcel parcel;
        Bundle bundle1;
        bundle1 = new Bundle();
        parcel = Parcel.obtain();
        bundle.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        if (parcel.readInt() > 0)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        Log.w("FJD.GooglePlayReceiver", "No callback received, terminating");
        parcel.recycle();
        return null;
        if (parcel.readInt() == 0x4c444e42)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        Log.w("FJD.GooglePlayReceiver", "No callback received, terminating");
        parcel.recycle();
        return null;
        int j = parcel.readInt();
        int i;
        i = 0;
        bundle = null;
_L6:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_356;
        }
        if (!shouldReadKeysAsStrings()) goto _L2; else goto _L1
_L1:
        Object obj = parcel.readString();
_L4:
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_386;
        }
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        if ("callback".equals(obj))
        {
            break MISSING_BLOCK_LABEL_286;
        }
        Object obj1;
        obj1 = parcel.readValue(null);
        if (obj1 instanceof String)
        {
            bundle1.putString(((String) (obj)), (String)obj1);
            break MISSING_BLOCK_LABEL_386;
        }
        break; /* Loop/switch isn't completed */
_L2:
        obj = parcel.readValue(null);
        if (obj instanceof String)
        {
            break MISSING_BLOCK_LABEL_167;
        }
        Log.w("FJD.GooglePlayReceiver", "Bad callback received, terminating");
        obj = null;
        continue; /* Loop/switch isn't completed */
        obj = (String)obj;
        if (true) goto _L4; else goto _L3
_L3:
        if (obj1 instanceof Boolean)
        {
            bundle1.putBoolean(((String) (obj)), ((Boolean)obj1).booleanValue());
            break MISSING_BLOCK_LABEL_386;
        }
        if (obj1 instanceof Integer)
        {
            bundle1.putInt(((String) (obj)), ((Integer)obj1).intValue());
            break MISSING_BLOCK_LABEL_386;
        }
        if (obj1 instanceof ArrayList)
        {
            bundle1.putParcelableArrayList(((String) (obj)), (ArrayList)obj1);
            break MISSING_BLOCK_LABEL_386;
        }
        if (obj1 instanceof Bundle)
        {
            bundle1.putBundle(((String) (obj)), (Bundle)obj1);
            break MISSING_BLOCK_LABEL_386;
        }
        if (obj1 instanceof Parcelable)
        {
            bundle1.putParcelable(((String) (obj)), (Parcelable)obj1);
        }
        break MISSING_BLOCK_LABEL_386;
        if (parcel.readInt() == 4)
        {
            break MISSING_BLOCK_LABEL_308;
        }
        Log.w("FJD.GooglePlayReceiver", "Bad callback received, terminating");
        parcel.recycle();
        return null;
        if ("com.google.android.gms.gcm.PendingCallback".equals(parcel.readString()))
        {
            break MISSING_BLOCK_LABEL_334;
        }
        Log.w("FJD.GooglePlayReceiver", "Bad callback received, terminating");
        parcel.recycle();
        return null;
        bundle = new GooglePlayJobCallback(parcel.readStrongBinder());
        break MISSING_BLOCK_LABEL_386;
        bundle;
        parcel.recycle();
        throw bundle;
        if (bundle != null)
        {
            break MISSING_BLOCK_LABEL_374;
        }
        Log.w("FJD.GooglePlayReceiver", "No callback received, terminating");
        parcel.recycle();
        return null;
        bundle = Pair.create(bundle, bundle1);
        parcel.recycle();
        return bundle;
        i++;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private static boolean shouldReadKeysAsStrings()
    {
        boolean flag3 = true;
        com/firebase/jobdispatcher/GooglePlayCallbackExtractor;
        JVM INSTR monitorenter ;
        if (shouldReadKeysAsStringsCached != null) goto _L2; else goto _L1
_L1:
        Object obj;
        Bundle bundle = new Bundle();
        bundle.putString("key", "value");
        obj = Parcel.obtain();
        bundle.writeToParcel(((Parcel) (obj)), 0);
        ((Parcel) (obj)).setDataPosition(0);
        boolean flag;
        boolean flag4;
        if (((Parcel) (obj)).readInt() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L4; else goto _L3
_L3:
        try
        {
            throw new IllegalStateException();
        }
        catch (RuntimeException runtimeexception) { }
        finally { }
        shouldReadKeysAsStringsCached = Boolean.FALSE;
        ((Parcel) (obj)).recycle();
_L2:
        flag4 = shouldReadKeysAsStringsCached.booleanValue();
        com/firebase/jobdispatcher/GooglePlayCallbackExtractor;
        JVM INSTR monitorexit ;
        return flag4;
_L4:
        Exception exception;
        boolean flag1;
        if (((Parcel) (obj)).readInt() == 0x4c444e42)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_134;
        }
        throw new IllegalStateException();
        ((Parcel) (obj)).recycle();
        throw exception;
        obj;
        com/firebase/jobdispatcher/GooglePlayCallbackExtractor;
        JVM INSTR monitorexit ;
        throw obj;
        boolean flag2;
        if (((Parcel) (obj)).readInt() == 1)
        {
            flag2 = flag3;
        } else
        {
            flag2 = false;
        }
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_156;
        }
        throw new IllegalStateException();
        shouldReadKeysAsStringsCached = Boolean.valueOf("key".equals(((Parcel) (obj)).readString()));
        ((Parcel) (obj)).recycle();
          goto _L2
    }

}
