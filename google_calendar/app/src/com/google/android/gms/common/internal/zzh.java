// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.zzabx;
import com.google.android.gms.internal.zzaby;

public final class zzh
{

    private static final SimpleArrayMap zzaPR = new SimpleArrayMap();

    private static String zzaE(Context context)
    {
        String s = context.getApplicationInfo().name;
        Object obj = s;
        if (TextUtils.isEmpty(s))
        {
            String s1 = context.getPackageName();
            try
            {
                obj = zzaby.zzaTJ.zzaS(context);
                context = context.getPackageName();
                obj = ((zzabx) (obj)).mContext.getPackageManager().getApplicationLabel(((zzabx) (obj)).mContext.getPackageManager().getApplicationInfo(context, 0)).toString();
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                return s1;
            }
        }
        return ((String) (obj));
    }

    public static String zzf(Context context, int i)
    {
        Resources resources = context.getResources();
        switch (i)
        {
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 19: // '\023'
        default:
            Log.e("GoogleApiAvailability", (new StringBuilder(33)).append("Unexpected error code ").append(i).toString());
            // fall through

        case 4: // '\004'
        case 6: // '\006'
        case 18: // '\022'
            return null;

        case 1: // '\001'
            return resources.getString(0x7f130115);

        case 3: // '\003'
            return resources.getString(0x7f130112);

        case 2: // '\002'
            return resources.getString(0x7f13011b);

        case 9: // '\t'
            Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
            return null;

        case 7: // '\007'
            Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
            return zzv(context, "common_google_play_services_network_error_title");

        case 8: // '\b'
            Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
            return null;

        case 10: // '\n'
            Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
            return null;

        case 5: // '\005'
            Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
            return zzv(context, "common_google_play_services_invalid_account_title");

        case 11: // '\013'
            Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
            return null;

        case 16: // '\020'
            Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
            return null;

        case 17: // '\021'
            Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
            return zzv(context, "common_google_play_services_sign_in_failed_title");

        case 20: // '\024'
            Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
            break;
        }
        return zzv(context, "common_google_play_services_restricted_profile_title");
    }

    public static String zzg(Context context, int i)
    {
        String s;
        String s1;
        if (i == 6)
        {
            s = zzv(context, "common_google_play_services_resolution_required_title");
        } else
        {
            s = zzf(context, i);
        }
        s1 = s;
        if (s == null)
        {
            s1 = context.getResources().getString(0x7f130116);
        }
        return s1;
    }

    private static String zzg(Context context, String s, String s1)
    {
        Resources resources = context.getResources();
        s = zzv(context, s);
        context = s;
        if (s == null)
        {
            context = resources.getString(0x7f130117);
        }
        return String.format(resources.getConfiguration().locale, context, new Object[] {
            s1
        });
    }

    public static String zzh(Context context, int i)
    {
        Resources resources = context.getResources();
        String s = zzaE(context);
        switch (i)
        {
        case 4: // '\004'
        case 6: // '\006'
        case 8: // '\b'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 19: // '\023'
        default:
            return resources.getString(0x7f130117, new Object[] {
                s
            });

        case 1: // '\001'
            return resources.getString(0x7f130114, new Object[] {
                s
            });

        case 3: // '\003'
            return resources.getString(0x7f130111, new Object[] {
                s
            });

        case 18: // '\022'
            return resources.getString(0x7f13011c, new Object[] {
                s
            });

        case 2: // '\002'
            if (android.os.Build.VERSION.SDK_INT >= 24)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0 && com.google.android.gms.common.util.zzh.zzaL(context))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                return resources.getString(0x7f13011d);
            } else
            {
                return resources.getString(0x7f13011a, new Object[] {
                    s
                });
            }

        case 9: // '\t'
            return resources.getString(0x7f130118, new Object[] {
                s
            });

        case 7: // '\007'
            return zzg(context, "common_google_play_services_network_error_text", s);

        case 5: // '\005'
            return zzg(context, "common_google_play_services_invalid_account_text", s);

        case 16: // '\020'
            return zzg(context, "common_google_play_services_api_unavailable_text", s);

        case 17: // '\021'
            return zzg(context, "common_google_play_services_sign_in_failed_text", s);

        case 20: // '\024'
            return zzg(context, "common_google_play_services_restricted_profile_text", s);
        }
    }

    public static String zzi(Context context, int i)
    {
        if (i == 6)
        {
            return zzg(context, "common_google_play_services_resolution_required_text", zzaE(context));
        } else
        {
            return zzh(context, i);
        }
    }

    public static String zzj(Context context, int i)
    {
        context = context.getResources();
        switch (i)
        {
        default:
            return context.getString(0x104000a);

        case 1: // '\001'
            return context.getString(0x7f130113);

        case 3: // '\003'
            return context.getString(0x7f130110);

        case 2: // '\002'
            return context.getString(0x7f130119);
        }
    }

    private static String zzv(Context context, String s)
    {
        SimpleArrayMap simplearraymap = zzaPR;
        simplearraymap;
        JVM INSTR monitorenter ;
        String s1 = (String)zzaPR.get(s);
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        simplearraymap;
        JVM INSTR monitorexit ;
        return s1;
        context = GooglePlayServicesUtil.getRemoteResource(context);
        if (context != null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        simplearraymap;
        JVM INSTR monitorexit ;
        return null;
        int i = context.getIdentifier(s, "string", "com.google.android.gms");
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        context = String.valueOf(s);
        if (context.length() == 0)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        context = "Missing resource: ".concat(context);
_L1:
        Log.w("GoogleApiAvailability", context);
        simplearraymap;
        JVM INSTR monitorexit ;
        return null;
        context = new String("Missing resource: ");
          goto _L1
        context;
        simplearraymap;
        JVM INSTR monitorexit ;
        throw context;
        context = context.getString(i);
        if (!TextUtils.isEmpty(context))
        {
            break MISSING_BLOCK_LABEL_159;
        }
        context = String.valueOf(s);
        if (context.length() == 0)
        {
            break MISSING_BLOCK_LABEL_146;
        }
        context = "Got empty resource: ".concat(context);
_L2:
        Log.w("GoogleApiAvailability", context);
        simplearraymap;
        JVM INSTR monitorexit ;
        return null;
        context = new String("Got empty resource: ");
          goto _L2
        zzaPR.put(s, context);
        simplearraymap;
        JVM INSTR monitorexit ;
        return context;
    }

}
