// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.backup;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.SharedPreferences;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.libraries.backup:
//            BackupKeyPredicate

public abstract class PersistentBackupAgentHelper extends BackupAgentHelper
{

    public PersistentBackupAgentHelper()
    {
    }

    private static boolean isSupportedSharedPreferencesName(String s)
    {
        return !s.contains(File.separator) && !s.contains("/") && !"persistent_backup_agent_helper".equals(s);
    }

    private static void putSharedPreference(android.content.SharedPreferences.Editor editor, String s, Object obj)
    {
        Iterator iterator = null;
        if (obj instanceof Boolean)
        {
            editor.putBoolean(s, ((Boolean)obj).booleanValue());
            return;
        }
        if (obj instanceof Float)
        {
            editor.putFloat(s, ((Float)obj).floatValue());
            return;
        }
        if (obj instanceof Integer)
        {
            editor.putInt(s, ((Integer)obj).intValue());
            return;
        }
        if (obj instanceof Long)
        {
            editor.putLong(s, ((Long)obj).longValue());
            return;
        }
        if (obj instanceof String)
        {
            editor.putString(s, (String)obj);
            return;
        }
        if (obj instanceof Set)
        {
            for (iterator = ((Set)obj).iterator(); iterator.hasNext();)
            {
                if (!(iterator.next() instanceof String))
                {
                    if (obj == null)
                    {
                        editor = null;
                    } else
                    {
                        editor = obj.getClass();
                    }
                    editor = String.valueOf(editor);
                    Log.w("PersistentBackupAgentHe", (new StringBuilder(String.valueOf(s).length() + 82 + String.valueOf(editor).length())).append("Skipping restore of key ").append(s).append(" because its value is a set containing an object of type ").append(editor).append(".").toString());
                    return;
                }
            }

            editor.putStringSet(s, (Set)obj);
            return;
        } else
        {
            if (obj == null)
            {
                editor = iterator;
            } else
            {
                editor = obj.getClass();
            }
            editor = String.valueOf(editor);
            Log.w("PersistentBackupAgentHe", (new StringBuilder(String.valueOf(s).length() + 69 + String.valueOf(editor).length())).append("Skipping restore of key ").append(s).append(" because its value is the unrecognized type ").append(editor).append(".").toString());
            return;
        }
    }

    public abstract Map getBackupSpecification();

    public void onBackup(ParcelFileDescriptor parcelfiledescriptor, BackupDataOutput backupdataoutput, ParcelFileDescriptor parcelfiledescriptor1)
        throws IOException
    {
        Object obj = getBackupSpecification();
        android.content.SharedPreferences.Editor editor = getSharedPreferences("persistent_backup_agent_helper", 0).edit();
        editor.clear();
        for (obj = ((Map) (obj)).entrySet().iterator(); ((Iterator) (obj)).hasNext();)
        {
            Object obj1 = (java.util.Map.Entry)((Iterator) (obj)).next();
            String s = (String)((java.util.Map.Entry) (obj1)).getKey();
            obj1 = (BackupKeyPredicate)((java.util.Map.Entry) (obj1)).getValue();
            if (!isSupportedSharedPreferencesName(s))
            {
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 43)).append("Unsupported shared preferences file name \"").append(s).append("\"").toString());
            }
            Iterator iterator = getSharedPreferences(s, 0).getAll().entrySet().iterator();
            while (iterator.hasNext()) 
            {
                Object obj2 = (java.util.Map.Entry)iterator.next();
                String s1 = (String)((java.util.Map.Entry) (obj2)).getKey();
                obj2 = ((java.util.Map.Entry) (obj2)).getValue();
                if (((BackupKeyPredicate) (obj1)).shouldBeBackedUp(s1))
                {
                    putSharedPreference(editor, (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append("/").append(s1).toString(), obj2);
                }
            }
        }

        editor.apply();
        super.onBackup(parcelfiledescriptor, backupdataoutput, parcelfiledescriptor1);
        getSharedPreferences("persistent_backup_agent_helper", 0).edit().clear().apply();
    }

    public void onCreate()
    {
        addHelper("persistent_backup_agent_helper_prefs", new SharedPreferencesBackupHelper(this, new String[] {
            "persistent_backup_agent_helper"
        }));
    }

    public void onPreferencesRestored$5166KOBMC4NNAT39DGNL6PBK7D4IILG_0()
    {
    }

    public void onRestore(BackupDataInput backupdatainput, int i, ParcelFileDescriptor parcelfiledescriptor)
        throws IOException
    {
        super.onRestore(backupdatainput, i, parcelfiledescriptor);
        backupdatainput = getSharedPreferences("persistent_backup_agent_helper", 0);
        HashMap hashmap = new HashMap();
        Iterator iterator = backupdatainput.getAll().entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            parcelfiledescriptor = (java.util.Map.Entry)iterator.next();
            backupdatainput = (String)parcelfiledescriptor.getKey();
            Object obj = parcelfiledescriptor.getValue();
            i = backupdatainput.indexOf("/");
            if (i < 0 || i >= backupdatainput.length() - 1)
            {
                Log.w("PersistentBackupAgentHe", (new StringBuilder(String.valueOf(backupdatainput).length() + 57)).append("Format of key \"").append(backupdatainput).append("\" not understood, so skipping its restore.").toString());
                continue;
            }
            String s = backupdatainput.substring(0, i);
            String s1 = backupdatainput.substring(i + 1);
            parcelfiledescriptor = (android.content.SharedPreferences.Editor)hashmap.get(s);
            backupdatainput = parcelfiledescriptor;
            if (parcelfiledescriptor == null)
            {
                if (!isSupportedSharedPreferencesName(s))
                {
                    Log.w("PersistentBackupAgentHe", (new StringBuilder(String.valueOf(s).length() + 52)).append("Skipping unsupported shared preferences file name \"").append(s).append("\"").toString());
                    continue;
                }
                backupdatainput = getSharedPreferences(s, 0).edit();
                hashmap.put(s, backupdatainput);
            }
            putSharedPreference(backupdatainput, s1, obj);
        } while (true);
        for (backupdatainput = hashmap.values().iterator(); backupdatainput.hasNext(); ((android.content.SharedPreferences.Editor)backupdatainput.next()).apply()) { }
        hashmap.keySet();
        onPreferencesRestored$5166KOBMC4NNAT39DGNL6PBK7D4IILG_0();
        getSharedPreferences("persistent_backup_agent_helper", 0).edit().clear().apply();
    }
}
