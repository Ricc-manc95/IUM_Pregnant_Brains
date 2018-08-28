// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package com.google.apps.xplat.logging:
//            AsyncFileWriter

final class AndroidAsyncFileWriter
    implements AsyncFileWriter
{

    public final BlockingQueue queue = new LinkedBlockingQueue();
    public volatile int state$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0;
    public final Writer writer;

    private AndroidAsyncFileWriter(BufferedWriter bufferedwriter)
    {
        state$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.STOPPED$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0;
        writer = bufferedwriter;
    }

    public static AsyncFileWriter createWriter(File file, String s)
    {
        try
        {
            file = new AndroidAsyncFileWriter(new BufferedWriter(new FileWriter(new File(file, s), true)));
            if (((AndroidAsyncFileWriter) (file)).state$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.RUNNING$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0)
            {
                file.state$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0 = android.support.v4.content.ModernAsyncTask.Status.RUNNING$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0;
                (new Thread(file. new QueueRunner())).start();
            }
        }
        // Misplaced declaration of an exception variable
        catch (File file)
        {
            return null;
        }
        return file;
    }

    public final void write(String s)
    {
        if (state$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.RUNNING$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0)
        {
            return;
        }
        try
        {
            queue.put(s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return;
        }
    }

    private class QueueRunner
        implements Runnable
    {

        private final AndroidAsyncFileWriter this$0;

        public final void run()
        {
_L2:
            if (state$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0 != android.support.v4.content.ModernAsyncTask.Status.RUNNING$9HHMUR9FCTNMUPRCCKNM2S3GECNNGS3CC5Q2UR3FCTJMIRJ75T0MSP3IDTKM8GBJF5N66HJ9DHILESJ9EHIN492NE9KN8PBIADQ62T357C______0)
            {
                break; /* Loop/switch isn't completed */
            }
            String s = (String)queue.poll(500L, TimeUnit.MICROSECONDS);
            if (s != null)
            {
                IOException ioexception;
                try
                {
                    writer.append(s);
                }
                catch (IOException ioexception1) { }
                catch (InterruptedException interruptedexception) { }
                continue; /* Loop/switch isn't completed */
            }
            writer.flush();
            if (true) goto _L2; else goto _L1
_L1:
            try
            {
                writer.close();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception)
            {
                return;
            }
        }

        QueueRunner()
        {
            this$0 = AndroidAsyncFileWriter.this;
            super();
        }
    }

}
