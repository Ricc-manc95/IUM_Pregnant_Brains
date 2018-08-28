// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.VolleyLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public final class DiskBasedCache
    implements Cache
{

    private final Map mEntries = new LinkedHashMap(16, 0.75F, true);
    private final int mMaxCacheSizeInBytes = 0x1400000;
    private final File mRootDirectory;
    private long mTotalSize;

    public DiskBasedCache(File file, int i)
    {
        mTotalSize = 0L;
        mRootDirectory = file;
    }

    private static String getFilenameForKey(String s)
    {
        int i = s.length() / 2;
        String s1 = String.valueOf(String.valueOf(s.substring(0, i).hashCode()));
        s = String.valueOf(String.valueOf(s.substring(i).hashCode()));
        if (s.length() != 0)
        {
            return s1.concat(s);
        } else
        {
            return new String(s1);
        }
    }

    private final void putEntry(String s, CacheHeader cacheheader)
    {
        if (!mEntries.containsKey(s))
        {
            mTotalSize = mTotalSize + cacheheader.size;
        } else
        {
            CacheHeader cacheheader1 = (CacheHeader)mEntries.get(s);
            long l = mTotalSize;
            mTotalSize = (cacheheader.size - cacheheader1.size) + l;
        }
        mEntries.put(s, cacheheader);
    }

    static int readInt(InputStream inputstream)
        throws IOException
    {
        int i = inputstream.read();
        if (i == -1)
        {
            throw new EOFException();
        }
        int j = inputstream.read();
        if (j == -1)
        {
            throw new EOFException();
        }
        int k = inputstream.read();
        if (k == -1)
        {
            throw new EOFException();
        }
        int l = inputstream.read();
        if (l == -1)
        {
            throw new EOFException();
        } else
        {
            return i | 0 | j << 8 | k << 16 | l << 24;
        }
    }

    static long readLong(InputStream inputstream)
        throws IOException
    {
        int i = inputstream.read();
        if (i == -1)
        {
            throw new EOFException();
        }
        long l = i;
        i = inputstream.read();
        if (i == -1)
        {
            throw new EOFException();
        }
        long l1 = i;
        i = inputstream.read();
        if (i == -1)
        {
            throw new EOFException();
        }
        long l2 = i;
        i = inputstream.read();
        if (i == -1)
        {
            throw new EOFException();
        }
        long l3 = i;
        i = inputstream.read();
        if (i == -1)
        {
            throw new EOFException();
        }
        long l4 = i;
        i = inputstream.read();
        if (i == -1)
        {
            throw new EOFException();
        }
        long l5 = i;
        i = inputstream.read();
        if (i == -1)
        {
            throw new EOFException();
        }
        long l6 = i;
        i = inputstream.read();
        if (i == -1)
        {
            throw new EOFException();
        } else
        {
            return 0L | l & 255L | (l1 & 255L) << 8 | (l2 & 255L) << 16 | (l3 & 255L) << 24 | (l4 & 255L) << 32 | (l5 & 255L) << 40 | (l6 & 255L) << 48 | ((long)i & 255L) << 56;
        }
    }

    private final void remove(String s)
    {
        this;
        JVM INSTR monitorenter ;
        CacheHeader cacheheader;
        boolean flag;
        flag = (new File(mRootDirectory, getFilenameForKey(s))).delete();
        cacheheader = (CacheHeader)mEntries.remove(s);
        if (cacheheader == null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        mTotalSize = mTotalSize - cacheheader.size;
        if (flag)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", new Object[] {
            s, getFilenameForKey(s)
        });
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    static byte[] streamToBytes(CountingInputStream countinginputstream, long l)
        throws IOException
    {
        long l1 = countinginputstream.length - countinginputstream.bytesRead;
        if (l < 0L || l > l1 || (long)(int)l != l)
        {
            throw new IOException((new StringBuilder(73)).append("streamToBytes length=").append(l).append(", maxLength=").append(l1).toString());
        } else
        {
            byte abyte0[] = new byte[(int)l];
            (new DataInputStream(countinginputstream)).readFully(abyte0);
            return abyte0;
        }
    }

    static void writeInt(OutputStream outputstream, int i)
        throws IOException
    {
        outputstream.write(i & 0xff);
        outputstream.write(i >> 8 & 0xff);
        outputstream.write(i >> 16 & 0xff);
        outputstream.write(i >>> 24);
    }

    static void writeLong(OutputStream outputstream, long l)
        throws IOException
    {
        outputstream.write((byte)(int)l);
        outputstream.write((byte)(int)(l >>> 8));
        outputstream.write((byte)(int)(l >>> 16));
        outputstream.write((byte)(int)(l >>> 24));
        outputstream.write((byte)(int)(l >>> 32));
        outputstream.write((byte)(int)(l >>> 40));
        outputstream.write((byte)(int)(l >>> 48));
        outputstream.write((byte)(int)(l >>> 56));
    }

    public final com.android.volley.Cache.Entry get(String s)
    {
        this;
        JVM INSTR monitorenter ;
        CacheHeader cacheheader = (CacheHeader)mEntries.get(s);
        if (cacheheader != null) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return s;
_L2:
        File file = new File(mRootDirectory, getFilenameForKey(s));
        CountingInputStream countinginputstream = new CountingInputStream(new BufferedInputStream(new FileInputStream(file)), file.length());
        Object obj;
        obj = CacheHeader.readHeader(countinginputstream);
        if (TextUtils.equals(s, ((CacheHeader) (obj)).key))
        {
            break MISSING_BLOCK_LABEL_157;
        }
        VolleyLog.d("%s: key=%s, found=%s", new Object[] {
            file.getAbsolutePath(), s, ((CacheHeader) (obj)).key
        });
        obj = (CacheHeader)mEntries.remove(s);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_147;
        }
        mTotalSize = mTotalSize - ((CacheHeader) (obj)).size;
        countinginputstream.close();
        s = null;
        continue; /* Loop/switch isn't completed */
        TreeMap treemap;
        byte abyte0[] = streamToBytes(countinginputstream, countinginputstream.length - countinginputstream.bytesRead);
        obj = new com.android.volley.Cache.Entry();
        obj.data = abyte0;
        obj.etag = cacheheader.etag;
        obj.serverDate = cacheheader.serverDate;
        obj.lastModified = cacheheader.lastModified;
        obj.ttl = cacheheader.ttl;
        obj.softTtl = cacheheader.softTtl;
        Object obj1 = cacheheader.allResponseHeaders;
        treemap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        Header header;
        for (obj1 = ((List) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); treemap.put(header.mName, header.mValue))
        {
            header = (Header)((Iterator) (obj1)).next();
        }

        break MISSING_BLOCK_LABEL_348;
        obj;
        try
        {
            countinginputstream.close();
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        finally
        {
            this;
        }
        VolleyLog.d("%s: %s", new Object[] {
            file.getAbsolutePath(), ((IOException) (obj)).toString()
        });
        remove(s);
        s = null;
        continue; /* Loop/switch isn't completed */
        obj.responseHeaders = treemap;
        obj.allResponseHeaders = Collections.unmodifiableList(cacheheader.allResponseHeaders);
        countinginputstream.close();
        s = ((String) (obj));
        if (true) goto _L4; else goto _L3
_L3:
        JVM INSTR monitorexit ;
        throw s;
    }

    public final void initialize()
    {
        this;
        JVM INSTR monitorenter ;
        if (mRootDirectory.exists()) goto _L2; else goto _L1
_L1:
        if (!mRootDirectory.mkdirs())
        {
            String s = mRootDirectory.getAbsolutePath();
            Log.e(VolleyLog.TAG, VolleyLog.buildMessage("Unable to create cache dir %s", new Object[] {
                s
            }));
        }
_L6:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        File afile[] = mRootDirectory.listFiles();
        if (afile == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        int j = afile.length;
        int i = 0;
_L4:
        File file;
        if (i >= j)
        {
            continue; /* Loop/switch isn't completed */
        }
        file = afile[i];
        CountingInputStream countinginputstream;
        long l;
        l = file.length();
        countinginputstream = new CountingInputStream(new BufferedInputStream(new FileInputStream(file)), l);
        CacheHeader cacheheader = CacheHeader.readHeader(countinginputstream);
        cacheheader.size = l;
        putEntry(cacheheader.key, cacheheader);
        countinginputstream.close();
        break MISSING_BLOCK_LABEL_170;
        Exception exception1;
        exception1;
        Exception exception;
        try
        {
            countinginputstream.close();
            throw exception1;
        }
        catch (IOException ioexception) { }
        finally
        {
            this;
        }
        file.delete();
        if (true)
        {
            break MISSING_BLOCK_LABEL_170;
        }
        JVM INSTR monitorexit ;
        throw exception;
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void invalidate(String s, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        com.android.volley.Cache.Entry entry = get(s);
        if (entry == null)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        entry.softTtl = 0L;
        entry.ttl = 0L;
        put(s, entry);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public final void put(String s, com.android.volley.Cache.Entry entry)
    {
        this;
        JVM INSTR monitorenter ;
        int i = entry.data.length;
        if (mTotalSize + (long)i < (long)mMaxCacheSizeInBytes) goto _L2; else goto _L1
_L1:
        Object obj;
        SystemClock.elapsedRealtime();
        obj = mEntries.entrySet().iterator();
_L5:
        Object obj1;
        Object obj2;
        if (!((Iterator) (obj)).hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = (CacheHeader)((java.util.Map.Entry)((Iterator) (obj)).next()).getValue();
        obj2 = ((CacheHeader) (obj1)).key;
        if (!(new File(mRootDirectory, getFilenameForKey(((String) (obj2))))).delete()) goto _L4; else goto _L3
_L3:
        mTotalSize = mTotalSize - ((CacheHeader) (obj1)).size;
_L6:
        ((Iterator) (obj)).remove();
        if ((float)(mTotalSize + (long)i) >= (float)mMaxCacheSizeInBytes * 0.9F) goto _L5; else goto _L2
_L2:
        obj = new File(mRootDirectory, getFilenameForKey(s));
        try
        {
            obj1 = new BufferedOutputStream(new FileOutputStream(((File) (obj))));
            obj2 = new CacheHeader(s, entry);
            if (!((CacheHeader) (obj2)).writeHeader(((OutputStream) (obj1))))
            {
                ((BufferedOutputStream) (obj1)).close();
                VolleyLog.d("Failed to write header for %s", new Object[] {
                    ((File) (obj)).getAbsolutePath()
                });
                throw new IOException();
            }
            break MISSING_BLOCK_LABEL_292;
        }
        // Misplaced declaration of an exception variable
        catch (String s) { }
        if (!((File) (obj)).delete())
        {
            VolleyLog.d("Could not clean up file %s", new Object[] {
                ((File) (obj)).getAbsolutePath()
            });
        }
_L7:
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", new Object[] {
            ((CacheHeader) (obj1)).key, getFilenameForKey(((CacheHeader) (obj1)).key)
        });
          goto _L6
        s;
        throw s;
        ((BufferedOutputStream) (obj1)).write(entry.data);
        ((BufferedOutputStream) (obj1)).close();
        putEntry(s, ((CacheHeader) (obj2)));
          goto _L7
    }

    private class CacheHeader
    {

        public final List allResponseHeaders;
        public final String etag;
        public final String key;
        public final long lastModified;
        public final long serverDate;
        public long size;
        public final long softTtl;
        public final long ttl;

        static CacheHeader readHeader(CountingInputStream countinginputstream)
            throws IOException
        {
            if (DiskBasedCache.readInt(countinginputstream) != 0x20150306)
            {
                throw new IOException();
            }
            String s = new String(DiskBasedCache.streamToBytes(countinginputstream, DiskBasedCache.readLong(countinginputstream)), "UTF-8");
            String s1 = new String(DiskBasedCache.streamToBytes(countinginputstream, DiskBasedCache.readLong(countinginputstream)), "UTF-8");
            long l = DiskBasedCache.readLong(countinginputstream);
            long l1 = DiskBasedCache.readLong(countinginputstream);
            long l2 = DiskBasedCache.readLong(countinginputstream);
            long l3 = DiskBasedCache.readLong(countinginputstream);
            int j = DiskBasedCache.readInt(countinginputstream);
            if (j < 0)
            {
                throw new IOException((new StringBuilder(31)).append("readHeaderList size=").append(j).toString());
            }
            Object obj;
            int i;
            if (j == 0)
            {
                obj = Collections.emptyList();
            } else
            {
                obj = new ArrayList();
            }
            for (i = 0; i < j; i++)
            {
                ((List) (obj)).add(new Header((new String(DiskBasedCache.streamToBytes(countinginputstream, DiskBasedCache.readLong(countinginputstream)), "UTF-8")).intern(), (new String(DiskBasedCache.streamToBytes(countinginputstream, DiskBasedCache.readLong(countinginputstream)), "UTF-8")).intern()));
            }

            return new CacheHeader(s, s1, l, l1, l2, l3, ((List) (obj)));
        }

        final boolean writeHeader(OutputStream outputstream)
        {
            DiskBasedCache.writeInt(outputstream, 0x20150306);
            byte abyte0[] = key.getBytes("UTF-8");
            DiskBasedCache.writeLong(outputstream, abyte0.length);
            outputstream.write(abyte0, 0, abyte0.length);
            if (etag != null)
            {
                break MISSING_BLOCK_LABEL_216;
            }
            Object obj = "";
_L1:
            Header header;
            byte abyte1[];
            byte abyte2[];
            try
            {
                obj = ((String) (obj)).getBytes("UTF-8");
                DiskBasedCache.writeLong(outputstream, obj.length);
                outputstream.write(((byte []) (obj)), 0, obj.length);
                DiskBasedCache.writeLong(outputstream, serverDate);
                DiskBasedCache.writeLong(outputstream, lastModified);
                DiskBasedCache.writeLong(outputstream, ttl);
                DiskBasedCache.writeLong(outputstream, softTtl);
                obj = allResponseHeaders;
            }
            // Misplaced declaration of an exception variable
            catch (OutputStream outputstream)
            {
                VolleyLog.d("%s", new Object[] {
                    outputstream.toString()
                });
                return false;
            }
            if (obj == null)
            {
                break MISSING_BLOCK_LABEL_224;
            }
            DiskBasedCache.writeInt(outputstream, ((List) (obj)).size());
            for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); outputstream.write(abyte1, 0, abyte1.length))
            {
                header = (Header)((Iterator) (obj)).next();
                abyte2 = header.mName.getBytes("UTF-8");
                DiskBasedCache.writeLong(outputstream, abyte2.length);
                outputstream.write(abyte2, 0, abyte2.length);
                abyte1 = header.mValue.getBytes("UTF-8");
                DiskBasedCache.writeLong(outputstream, abyte1.length);
            }

            break MISSING_BLOCK_LABEL_229;
            obj = etag;
              goto _L1
            DiskBasedCache.writeInt(outputstream, 0);
            outputstream.flush();
            return true;
        }

        CacheHeader(String s, com.android.volley.Cache.Entry entry)
        {
            String s1;
            long l;
            long l1;
            long l2;
            long l3;
            s1 = entry.etag;
            l = entry.serverDate;
            l1 = entry.lastModified;
            l2 = entry.ttl;
            l3 = entry.softTtl;
            if (entry.allResponseHeaders == null) goto _L2; else goto _L1
_L1:
            Object obj = entry.allResponseHeaders;
_L4:
            this(s, s1, l, l1, l2, l3, ((List) (obj)));
            size = entry.data.length;
            return;
_L2:
            obj = entry.responseHeaders;
            ArrayList arraylist = new ArrayList(((Map) (obj)).size());
            Iterator iterator = ((Map) (obj)).entrySet().iterator();
            do
            {
                obj = arraylist;
                if (!iterator.hasNext())
                {
                    continue;
                }
                obj = (java.util.Map.Entry)iterator.next();
                arraylist.add(new Header((String)((java.util.Map.Entry) (obj)).getKey(), (String)((java.util.Map.Entry) (obj)).getValue()));
            } while (true);
            if (true) goto _L4; else goto _L3
_L3:
        }

        private CacheHeader(String s, String s1, long l, long l1, long l2, long l3, List list)
        {
            key = s;
            s = s1;
            if ("".equals(s1))
            {
                s = null;
            }
            etag = s;
            serverDate = l;
            lastModified = l1;
            ttl = l2;
            softTtl = l3;
            allResponseHeaders = list;
        }
    }


    private class CountingInputStream extends FilterInputStream
    {

        public long bytesRead;
        public final long length;

        public final int read()
            throws IOException
        {
            int i = super.read();
            if (i != -1)
            {
                bytesRead = bytesRead + 1L;
            }
            return i;
        }

        public final int read(byte abyte0[], int i, int j)
            throws IOException
        {
            i = super.read(abyte0, i, j);
            if (i != -1)
            {
                bytesRead = bytesRead + (long)i;
            }
            return i;
        }

        CountingInputStream(InputStream inputstream, long l)
        {
            super(inputstream);
            length = l;
        }
    }

}
