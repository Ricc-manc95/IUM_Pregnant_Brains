// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v4.content:
//            ContextCompat

public class FileProvider extends ContentProvider
{
    static interface PathStrategy
    {

        public abstract File getFileForUri(Uri uri);

        public abstract Uri getUriForFile(File file);
    }

    static final class SimplePathStrategy
        implements PathStrategy
    {

        private final String mAuthority;
        public final HashMap mRoots = new HashMap();

        final void addRoot(String s, File file)
        {
            if (TextUtils.isEmpty(s))
            {
                throw new IllegalArgumentException("Name must not be empty");
            }
            File file1;
            try
            {
                file1 = file.getCanonicalFile();
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                throw new IllegalArgumentException((new StringBuilder("Failed to resolve canonical path for ")).append(file).toString(), s);
            }
            mRoots.put(s, file1);
        }

        public final File getFileForUri(Uri uri)
        {
            Object obj1 = uri.getEncodedPath();
            int i = ((String) (obj1)).indexOf('/', 1);
            Object obj = Uri.decode(((String) (obj1)).substring(1, i));
            obj1 = Uri.decode(((String) (obj1)).substring(i + 1));
            obj = (File)mRoots.get(obj);
            if (obj == null)
            {
                throw new IllegalArgumentException((new StringBuilder("Unable to find configured root for ")).append(uri).toString());
            }
            uri = new File(((File) (obj)), ((String) (obj1)));
            try
            {
                obj1 = uri.getCanonicalFile();
            }
            catch (IOException ioexception)
            {
                throw new IllegalArgumentException((new StringBuilder("Failed to resolve canonical path for ")).append(uri).toString());
            }
            if (!((File) (obj1)).getPath().startsWith(((File) (obj)).getPath()))
            {
                throw new SecurityException("Resolved path jumped beyond configured root");
            } else
            {
                return ((File) (obj1));
            }
        }

        public final Uri getUriForFile(File file)
        {
            Object obj;
            String s;
            java.util.Map.Entry entry;
            Iterator iterator;
            String s1;
            try
            {
                s = file.getCanonicalPath();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                throw new IllegalArgumentException((new StringBuilder("Failed to resolve canonical path for ")).append(file).toString());
            }
            file = null;
            iterator = mRoots.entrySet().iterator();
            if (!iterator.hasNext())
            {
                break; /* Loop/switch isn't completed */
            }
            entry = (java.util.Map.Entry)iterator.next();
            s1 = ((File)entry.getValue()).getPath();
            if (!s.startsWith(s1))
            {
                break MISSING_BLOCK_LABEL_271;
            }
            obj = entry;
            if (file != null)
            {
                if (s1.length() <= ((File)file.getValue()).getPath().length())
                {
                    break MISSING_BLOCK_LABEL_271;
                }
                obj = entry;
            }
_L4:
            file = ((File) (obj));
            if (true) goto _L2; else goto _L1
_L2:
            break MISSING_BLOCK_LABEL_22;
_L1:
            if (file == null)
            {
                throw new IllegalArgumentException((new StringBuilder("Failed to find configured root that contains ")).append(s).toString());
            }
            obj = ((File)file.getValue()).getPath();
            if (((String) (obj)).endsWith("/"))
            {
                obj = s.substring(((String) (obj)).length());
            } else
            {
                obj = s.substring(((String) (obj)).length() + 1);
            }
            file = (new StringBuilder()).append(Uri.encode((String)file.getKey())).append('/').append(Uri.encode(((String) (obj)), "/")).toString();
            return (new android.net.Uri.Builder()).scheme("content").authority(mAuthority).encodedPath(file).build();
            obj = file;
            if (true) goto _L4; else goto _L3
_L3:
        }

        SimplePathStrategy(String s)
        {
            mAuthority = s;
        }
    }


    private static final String COLUMNS[] = {
        "_display_name", "_size"
    };
    private static final File DEVICE_ROOT = new File("/");
    private static HashMap sCache = new HashMap();
    private PathStrategy mStrategy;

    public FileProvider()
    {
    }

    private static PathStrategy getPathStrategy(Context context, String s)
    {
        HashMap hashmap = sCache;
        hashmap;
        JVM INSTR monitorenter ;
        Object obj1 = (PathStrategy)sCache.get(s);
        Object obj = obj1;
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        XmlResourceParser xmlresourceparser;
        obj1 = new SimplePathStrategy(s);
        xmlresourceparser = context.getPackageManager().resolveContentProvider(s, 128).loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
        if (xmlresourceparser != null) goto _L4; else goto _L3
_L3:
        throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
        context;
        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", context);
        context;
        hashmap;
        JVM INSTR monitorexit ;
        throw context;
_L12:
        String s1;
        ((SimplePathStrategy) (obj1)).addRoot(s1, ((File) (obj)));
_L4:
        int i = xmlresourceparser.next();
        if (i == 1) goto _L6; else goto _L5
_L5:
        if (i != 2) goto _L4; else goto _L7
_L7:
        String s2;
        obj = xmlresourceparser.getName();
        s1 = xmlresourceparser.getAttributeValue(null, "name");
        s2 = xmlresourceparser.getAttributeValue(null, "path");
        if (!"root-path".equals(obj)) goto _L9; else goto _L8
_L8:
        obj = DEVICE_ROOT;
_L13:
        if (obj == null) goto _L4; else goto _L10
_L10:
        String as[] = new String[1];
        as[0] = s2;
        int j = as.length;
        i = 0;
_L17:
        if (i >= j) goto _L12; else goto _L11
_L11:
        s2 = as[i];
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_394;
        }
        obj = new File(((File) (obj)), s2);
        break MISSING_BLOCK_LABEL_394;
_L9:
label0:
        {
            if (!"files-path".equals(obj))
            {
                break label0;
            }
            obj = context.getFilesDir();
        }
          goto _L13
label1:
        {
            if (!"cache-path".equals(obj))
            {
                break label1;
            }
            obj = context.getCacheDir();
        }
          goto _L13
label2:
        {
            if (!"external-path".equals(obj))
            {
                break label2;
            }
            obj = Environment.getExternalStorageDirectory();
        }
          goto _L13
        if (!"external-files-path".equals(obj))
        {
            break MISSING_BLOCK_LABEL_296;
        }
        obj = ContextCompat.getExternalFilesDirs(context, null);
        if (obj.length > 0)
        {
            obj = obj[0];
        } else
        {
            obj = null;
        }
          goto _L13
        if (!"external-cache-path".equals(obj))
        {
            break MISSING_BLOCK_LABEL_322;
        }
        obj = ContextCompat.getExternalCacheDirs(context);
        if (obj.length > 0)
        {
            obj = obj[0];
        } else
        {
            obj = null;
        }
          goto _L13
        if (!"external-media-path".equals(obj)) goto _L15; else goto _L14
_L14:
        obj = context.getExternalMediaDirs();
        if (obj.length <= 0) goto _L15; else goto _L16
_L16:
        obj = obj[0];
          goto _L13
        context;
        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", context);
_L6:
        sCache.put(s, obj1);
        obj = obj1;
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return ((PathStrategy) (obj));
_L15:
        obj = null;
          goto _L13
        i++;
          goto _L17
    }

    public static Uri getUriForFile(Context context, String s, File file)
    {
        return getPathStrategy(context, s).getUriForFile(file);
    }

    public void attachInfo(Context context, ProviderInfo providerinfo)
    {
        super.attachInfo(context, providerinfo);
        if (providerinfo.exported)
        {
            throw new SecurityException("Provider must not be exported");
        }
        if (!providerinfo.grantUriPermissions)
        {
            throw new SecurityException("Provider must grant uri permissions");
        } else
        {
            mStrategy = getPathStrategy(context, providerinfo.authority);
            return;
        }
    }

    public int delete(Uri uri, String s, String as[])
    {
        return !mStrategy.getFileForUri(uri).delete() ? 0 : 1;
    }

    public String getType(Uri uri)
    {
        uri = mStrategy.getFileForUri(uri);
        int i = uri.getName().lastIndexOf('.');
        if (i >= 0)
        {
            uri = uri.getName().substring(i + 1);
            uri = MimeTypeMap.getSingleton().getMimeTypeFromExtension(uri);
            if (uri != null)
            {
                return uri;
            }
        }
        return "application/octet-stream";
    }

    public Uri insert(Uri uri, ContentValues contentvalues)
    {
        throw new UnsupportedOperationException("No external inserts");
    }

    public boolean onCreate()
    {
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String s)
        throws FileNotFoundException
    {
        uri = mStrategy.getFileForUri(uri);
        int i;
        if ("r".equals(s))
        {
            i = 0x10000000;
        } else
        if ("w".equals(s) || "wt".equals(s))
        {
            i = 0x2c000000;
        } else
        if ("wa".equals(s))
        {
            i = 0x2a000000;
        } else
        if ("rw".equals(s))
        {
            i = 0x38000000;
        } else
        if ("rwt".equals(s))
        {
            i = 0x3c000000;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder("Invalid mode: ")).append(s).toString());
        }
        return ParcelFileDescriptor.open(uri, i);
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        s = mStrategy.getFileForUri(uri);
        uri = as;
        if (as == null)
        {
            uri = COLUMNS;
        }
        as1 = new String[uri.length];
        as = ((String []) (new Object[uri.length]));
        int i1 = uri.length;
        int j = 0;
        int i = 0;
        while (j < i1) 
        {
            s1 = uri[j];
            if ("_display_name".equals(s1))
            {
                as1[i] = "_display_name";
                int k = i + 1;
                as[i] = s.getName();
                i = k;
            } else
            if ("_size".equals(s1))
            {
                as1[i] = "_size";
                int l = i + 1;
                as[i] = Long.valueOf(s.length());
                i = l;
            }
            j++;
        }
        uri = new String[i];
        System.arraycopy(as1, 0, uri, 0, i);
        s = ((String) (new Object[i]));
        System.arraycopy(as, 0, s, 0, i);
        uri = new MatrixCursor(uri, 1);
        uri.addRow(s);
        return uri;
    }

    public int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        throw new UnsupportedOperationException("No external updates");
    }

}
