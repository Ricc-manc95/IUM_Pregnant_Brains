// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.media.impl.basic;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.SerializingExecutor;
import com.google.android.libraries.internal.growth.growthkit.internal.media.ImageCache;
import com.google.android.libraries.social.filecache.FileCache;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

public final class BasicImageCache
    implements ImageCache
{

    public static final HashFunction HASH_FUNCTION = Hashing.murmur3_32();
    public static final Logger logger = new Logger();
    private final ListeningExecutorService executor;
    public final FileCache fileCache;
    private final SerializingExecutor serializingExecutor;
    private final ListeningExecutorService uiExecutor = MoreExecutors.listeningDecorator(new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.UiExecutor.HandlerExecutorService(new Handler(Looper.getMainLooper())));
    public final URL.Factory urlFactory;

    public BasicImageCache(FileCache filecache, URL.Factory factory, ListeningExecutorService listeningexecutorservice)
    {
        fileCache = filecache;
        urlFactory = factory;
        executor = listeningexecutorservice;
        serializingExecutor = new SerializingExecutor(listeningexecutorservice);
        factory = serializingExecutor;
        filecache.getClass();
        class .Lambda._cls0
            implements Runnable
        {

            private final FileCache arg$1;

            public final void run()
            {
                FileCache filecache1;
                long l2;
                filecache1 = arg$1;
                l2 = System.currentTimeMillis();
                if (filecache1.getCacheDir().exists()) goto _L2; else goto _L1
_L1:
                return;
_L2:
                ArrayList arraylist1 = new ArrayList();
                filecache1.collectCacheFiles(filecache1.getCacheDir(), arraylist1);
                if (!arraylist1.isEmpty())
                {
                    ArrayList arraylist = new ArrayList();
                    long l1 = 0L;
                    arraylist1 = (ArrayList)arraylist1;
                    int k = arraylist1.size();
                    int i = 0;
                    while (i < k) 
                    {
                        Object obj = arraylist1.get(i);
                        i++;
                        obj = new com.google.android.libraries.social.filecache.FileCache.CacheFile((File)obj);
                        boolean flag;
                        if (l2 - ((com.google.android.libraries.social.filecache.FileCache.CacheFile) (obj)).timestamp < 0x1b7740L)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        obj.recent = flag;
                        l1 += ((com.google.android.libraries.social.filecache.FileCache.CacheFile) (obj)).size;
                        arraylist.add(obj);
                    }
                    l2 = filecache1.getCapacity();
                    if (l1 > l2)
                    {
                        Collections.sort(arraylist);
                        int i1 = arraylist.size();
                        int j = 0;
                        int l = 0;
                        while (l < i1 && l1 > l2) 
                        {
                            com.google.android.libraries.social.filecache.FileCache.CacheFile cachefile = (com.google.android.libraries.social.filecache.FileCache.CacheFile)arraylist.get(l);
                            if (cachefile.file.delete())
                            {
                                l1 -= cachefile.size;
                                j++;
                            }
                            l++;
                        }
                    }
                }
                if (true) goto _L1; else goto _L3
_L3:
            }

            .Lambda._cls0(FileCache filecache)
            {
                arg$1 = filecache;
            }
        }

        factory.execute(new .Lambda._cls0(filecache));
    }

    static byte[] readStream(HttpURLConnection httpurlconnection, boolean flag)
        throws Exception
    {
        if (!flag) goto _L2; else goto _L1
_L1:
        Object obj = new GZIPInputStream(httpurlconnection.getInputStream());
        httpurlconnection = ((HttpURLConnection) (obj));
_L5:
        obj = new ByteArrayOutputStream();
        byte abyte0[] = new byte[1024];
_L3:
        int i = httpurlconnection.read(abyte0);
        if (i < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        ((ByteArrayOutputStream) (obj)).write(abyte0, 0, i);
          goto _L3
        Exception exception;
        exception;
        httpurlconnection.close();
        ((ByteArrayOutputStream) (obj)).close();
        throw exception;
_L2:
        obj = httpurlconnection.getInputStream();
        httpurlconnection = ((HttpURLConnection) (obj));
        continue; /* Loop/switch isn't completed */
        obj;
        httpurlconnection = httpurlconnection.getErrorStream();
        if (true) goto _L5; else goto _L4
_L4:
        exception = ((ByteArrayOutputStream) (obj)).toByteArray();
        httpurlconnection.close();
        ((ByteArrayOutputStream) (obj)).close();
        return exception;
    }

    public final ListenableFuture downloadImage(String s, int i, int j)
    {
        SettableFuture settablefuture = new SettableFuture();
        class .Lambda._cls1
            implements Runnable
        {

            private final BasicImageCache arg$1;
            private final String arg$2;
            private final int arg$3;
            private final int arg$4;
            private final SettableFuture arg$5;

            public final void run()
            {
                List list;
                HashMap hashmap;
                SettableFuture settablefuture1;
                String s1;
                BasicImageCache basicimagecache;
                int k;
                int l;
                list = null;
                hashmap = null;
                basicimagecache = arg$1;
                s1 = arg$2;
                k = arg$3;
                l = arg$4;
                settablefuture1 = arg$5;
                Object obj;
                String s2;
                s2 = BasicImageCache.HASH_FUNCTION.hashString(s1, Charset.forName("UTF-8")).toString();
                obj = new File(basicimagecache.fileCache.getCacheFilePath(s2));
                Object obj1;
                String s3;
                String s4;
                if (!((File) (obj)).exists())
                {
                    obj = null;
                }
                if (obj != null) goto _L2; else goto _L1
_L1:
                if (k != -1 || l != -1) goto _L4; else goto _L3
_L3:
                obj1 = "";
_L9:
                obj = list;
                obj1 = (HttpURLConnection)basicimagecache.urlFactory.create(s1.replace("=%@", ((CharSequence) (obj1)))).url.openConnection();
                ((HttpURLConnection) (obj1)).setDoInput(true);
                hashmap = new HashMap();
                k = 0;
_L6:
                s3 = ((HttpURLConnection) (obj1)).getHeaderFieldKey(k);
                s4 = ((HttpURLConnection) (obj1)).getHeaderField(k);
                if (s3 == null && s4 == null)
                {
                    break; /* Loop/switch isn't completed */
                }
                if (s3 == null)
                {
                    break MISSING_BLOCK_LABEL_224;
                }
                list = (List)hashmap.get(s3);
                obj = list;
                if (list != null)
                {
                    break MISSING_BLOCK_LABEL_215;
                }
                obj = new ArrayList();
                hashmap.put(s3, obj);
                ((List) (obj)).add(s4);
                k++;
                if (true) goto _L6; else goto _L5
_L4:
                if (k == -1 || l == -1) goto _L8; else goto _L7
_L7:
                Object obj2;
                boolean flag;
                try
                {
                    obj1 = (new StringBuilder(26)).append("=w").append(l).append("-h").append(l).toString();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    settablefuture1.setException(((Throwable) (obj)));
                    return;
                }
                  goto _L9
_L26:
                obj1 = (new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(obj1).length())).append("=").append(((String) (obj))).append(((String) (obj1))).toString();
                  goto _L9
_L23:
                obj = (new StringBuilder(12)).append("w").append(k).toString();
                  goto _L10
_L25:
                obj1 = (new StringBuilder(12)).append("h").append(l).toString();
                break MISSING_BLOCK_LABEL_286;
_L5:
                if (((HttpURLConnection) (obj1)).getResponseCode() != 200) goto _L12; else goto _L11
_L11:
                obj = (List)hashmap.get("Content-Encoding");
                if (obj == null) goto _L14; else goto _L13
_L13:
                if (((List) (obj)).isEmpty()) goto _L14; else goto _L15
_L15:
                flag = ((String)((List) (obj)).get(0)).equalsIgnoreCase("gzip");
_L17:
                obj = BasicImageCache.readStream(((HttpURLConnection) (obj1)), flag);
                basicimagecache.fileCache.write(s2, ByteBuffer.wrap(((byte []) (obj))));
                if (obj1 == null) goto _L2; else goto _L16
_L16:
                ((HttpURLConnection) (obj1)).disconnect();
_L2:
                settablefuture1.set(null);
                return;
_L14:
                flag = false;
                  goto _L17
_L12:
                throw new DownloadException(((HttpURLConnection) (obj1)).getResponseMessage(), ((HttpURLConnection) (obj1)).getResponseCode());
                obj2;
_L21:
                obj = obj1;
                BasicImageCache.logger.w(((Throwable) (obj2)), "Failed to retrieve fife URL %s", new Object[] {
                    s1
                });
                if (obj1 == null) goto _L2; else goto _L18
_L18:
                ((HttpURLConnection) (obj1)).disconnect();
                  goto _L2
                obj2;
                obj1 = obj;
                obj = obj2;
_L20:
                if (obj1 == null)
                {
                    break MISSING_BLOCK_LABEL_548;
                }
                ((HttpURLConnection) (obj1)).disconnect();
                throw obj;
                obj;
                if (true) goto _L20; else goto _L19
_L19:
                obj2;
                obj1 = hashmap;
                  goto _L21
_L8:
                if (k != -1) goto _L23; else goto _L22
_L22:
                obj = "";
_L10:
                if (l != -1) goto _L25; else goto _L24
_L24:
                obj1 = "";
                  goto _L26
            }

            .Lambda._cls1(String s, int i, int j, SettableFuture settablefuture)
            {
                arg$1 = BasicImageCache.this;
                arg$2 = s;
                arg$3 = i;
                arg$4 = j;
                arg$5 = settablefuture;
            }

            private class DownloadException extends Exception
            {

                public DownloadException(String s, int i)
                {
                    super(String.format("%s, CODE: %s", new Object[] {
                        s, Integer.valueOf(i)
                    }));
                }
            }

        }

        serializingExecutor.execute(new .Lambda._cls1(s, i, j, settablefuture));
        return settablefuture;
    }

    public final void loadImageToView(String s, final ImageView imageView)
    {
        if (imageView == null)
        {
            return;
        }
        class .Lambda._cls2
            implements Callable
        {

            private final BasicImageCache arg$1;
            private final String arg$2;

            public final Object call()
            {
                BasicImageCache basicimagecache = arg$1;
                String s1 = arg$2;
                String s2 = BasicImageCache.HASH_FUNCTION.hashString(s1, Charset.forName("UTF-8")).toString();
                File file = new File(basicimagecache.fileCache.getCacheFilePath(s2));
                if (!file.exists())
                {
                    file = null;
                }
                if (file != null)
                {
                    return BitmapFactory.decodeFile(basicimagecache.fileCache.getCacheFilePath(s2));
                } else
                {
                    BasicImageCache.logger.w("Couldn't find image %s in cache", new Object[] {
                        s1
                    });
                    return null;
                }
            }

            .Lambda._cls2(String s)
            {
                arg$1 = BasicImageCache.this;
                arg$2 = s;
            }
        }

        s = executor.submit(new .Lambda._cls2(s));
        imageView = new _cls1();
        ListeningExecutorService listeningexecutorservice = uiExecutor;
        if (imageView == null)
        {
            throw new NullPointerException();
        } else
        {
            s.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(s, imageView), listeningexecutorservice);
            return;
        }
    }


    private class _cls1
        implements FutureCallback
    {

        private final ImageView val$imageView;

        public final void onFailure(Throwable throwable)
        {
            BasicImageCache.logger.w(throwable, "Failed to load image", new Object[0]);
            imageView.setVisibility(8);
        }

        public final void onSuccess(Object obj)
        {
            obj = (Bitmap)obj;
            if (obj != null)
            {
                imageView.setImageBitmap(((Bitmap) (obj)));
                imageView.setVisibility(0);
                return;
            } else
            {
                imageView.setVisibility(8);
                return;
            }
        }

        _cls1()
        {
            imageView = imageview;
            super();
        }
    }

}
