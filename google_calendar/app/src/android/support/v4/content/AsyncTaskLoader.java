// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.content.Context;
import android.os.SystemClock;
import android.support.v4.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package android.support.v4.content:
//            Loader, ModernAsyncTask

public abstract class AsyncTaskLoader extends Loader
{

    private volatile LoadTask mCancellingTask;
    private final Executor mExecutor;
    public long mLastLoadCompleteTime;
    public volatile LoadTask mTask;

    public AsyncTaskLoader(Context context)
    {
        this(context, ModernAsyncTask.THREAD_POOL_EXECUTOR);
    }

    private AsyncTaskLoader(Context context, Executor executor)
    {
        super(context);
        mLastLoadCompleteTime = -10000L;
        mExecutor = executor;
    }

    final void dispatchOnCancelled(LoadTask loadtask, Object obj)
    {
        onCanceled(obj);
        if (mCancellingTask == loadtask)
        {
            if (super.mProcessingChange)
            {
                if (super.mStarted)
                {
                    onForceLoad();
                } else
                {
                    super.mContentChanged = true;
                }
            }
            mLastLoadCompleteTime = SystemClock.uptimeMillis();
            mCancellingTask = null;
            executePendingTask();
        }
    }

    public final void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        super.dump(s, filedescriptor, printwriter, as);
        if (mTask != null)
        {
            printwriter.print(s);
            printwriter.print("mTask=");
            printwriter.print(mTask);
            printwriter.print(" waiting=");
            printwriter.println(mTask.waiting);
        }
        if (mCancellingTask != null)
        {
            printwriter.print(s);
            printwriter.print("mCancellingTask=");
            printwriter.print(mCancellingTask);
            printwriter.print(" waiting=");
            printwriter.println(mCancellingTask.waiting);
        }
        if (0L != 0L)
        {
            printwriter.print(s);
            printwriter.print("mUpdateThrottle=");
            TimeUtils.formatDuration(0L, printwriter);
            printwriter.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(mLastLoadCompleteTime, SystemClock.uptimeMillis(), printwriter);
            printwriter.println();
        }
    }

    final void executePendingTask()
    {
        if (mCancellingTask == null && mTask != null)
        {
            if (mTask.waiting)
            {
                mTask.waiting = false;
                LoadTask loadtask = mTask;
                throw new NullPointerException();
            }
            if (0L > 0L && SystemClock.uptimeMillis() < mLastLoadCompleteTime)
            {
                mTask.waiting = true;
                LoadTask loadtask1 = mTask;
                long l = mLastLoadCompleteTime;
                throw new NullPointerException();
            }
        } else
        {
            return;
        }
        LoadTask loadtask2 = mTask;
        Executor executor = mExecutor;
        if (((ModernAsyncTask) (loadtask2)).mStatus$9HGMSP3IDTKM8BRJELO70RRIEGNNCD1FCDNMST35DPQ2UJBFCHIN4RI1EDSMSOQKC5PMM92JEHGN8TBJ7C______0 != ModernAsyncTask.Status.PENDING$9HGMSP3IDTKM8BRJELO70RRIEGNNCD1FCDNMST35DPQ2UJBFCHIN4RI1EDSMSOQKC5PMM92JEHGN8TBJ7C______0)
        {
            switch (((ModernAsyncTask) (loadtask2)).mStatus$9HGMSP3IDTKM8BRJELO70RRIEGNNCD1FCDNMST35DPQ2UJBFCHIN4RI1EDSMSOQKC5PMM92JEHGN8TBJ7C______0 - 1)
            {
            default:
                throw new IllegalStateException("We should never reach this state");

            case 1: // '\001'
                throw new IllegalStateException("Cannot execute task: the task is already running.");

            case 2: // '\002'
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        } else
        {
            loadtask2.mStatus$9HGMSP3IDTKM8BRJELO70RRIEGNNCD1FCDNMST35DPQ2UJBFCHIN4RI1EDSMSOQKC5PMM92JEHGN8TBJ7C______0 = ModernAsyncTask.Status.RUNNING$9HGMSP3IDTKM8BRJELO70RRIEGNNCD1FCDNMST35DPQ2UJBFCHIN4RI1EDSMSOQKC5PMM92JEHGN8TBJ7C______0;
            ((ModernAsyncTask) (loadtask2)).mWorker.mParams = null;
            executor.execute(((ModernAsyncTask) (loadtask2)).mFuture);
            return;
        }
    }

    public abstract Object loadInBackground();

    protected final boolean onCancelLoad()
    {
label0:
        {
            if (mTask != null)
            {
                if (!mStarted)
                {
                    mContentChanged = true;
                }
                if (mCancellingTask == null)
                {
                    break label0;
                }
                if (mTask.waiting)
                {
                    mTask.waiting = false;
                    LoadTask loadtask = mTask;
                    throw new NullPointerException();
                }
                mTask = null;
            }
            return false;
        }
        if (mTask.waiting)
        {
            mTask.waiting = false;
            LoadTask loadtask1 = mTask;
            throw new NullPointerException();
        }
        LoadTask loadtask2 = mTask;
        ((ModernAsyncTask) (loadtask2)).mCancelled.set(true);
        boolean flag = ((ModernAsyncTask) (loadtask2)).mFuture.cancel(false);
        if (flag)
        {
            mCancellingTask = mTask;
        }
        mTask = null;
        return flag;
    }

    public void onCanceled(Object obj)
    {
    }

    protected final void onForceLoad()
    {
        super.onForceLoad();
        onCancelLoad();
        mTask = new LoadTask();
        executePendingTask();
    }

    private class LoadTask extends ModernAsyncTask
        implements Runnable
    {

        private final CountDownLatch mDone = new CountDownLatch(1);
        private final AsyncTaskLoader this$0;
        public boolean waiting;

        private final transient Object doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0()
        {
            Object obj;
            try
            {
                obj = loadInBackground();
            }
            catch (OperationCanceledException operationcanceledexception)
            {
                if (!super.mCancelled.get())
                {
                    throw operationcanceledexception;
                } else
                {
                    return null;
                }
            }
            return obj;
        }

        protected final volatile Object doInBackground(Object aobj[])
        {
            return doInBackground$51DKOQJ1EPGIUR31DPJIULJFD5I3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0();
        }

        protected final void onCancelled(Object obj)
        {
            dispatchOnCancelled(this, obj);
            mDone.countDown();
            return;
            obj;
            mDone.countDown();
            throw obj;
        }

        protected final void onPostExecute(Object obj)
        {
            AsyncTaskLoader asynctaskloader = AsyncTaskLoader.this;
            if (asynctaskloader.mTask == this) goto _L2; else goto _L1
_L1:
            asynctaskloader.dispatchOnCancelled(this, obj);
_L3:
            mDone.countDown();
            return;
_L2:
            if (!((Loader) (asynctaskloader)).mAbandoned)
            {
                break MISSING_BLOCK_LABEL_52;
            }
            asynctaskloader.onCanceled(obj);
              goto _L3
            obj;
            mDone.countDown();
            throw obj;
            asynctaskloader.mProcessingChange = false;
            asynctaskloader.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            asynctaskloader.mTask = null;
            asynctaskloader.deliverResult(obj);
              goto _L3
        }

        public final void run()
        {
            waiting = false;
            executePendingTask();
        }

        LoadTask()
        {
            this$0 = AsyncTaskLoader.this;
            super();
        }
    }

}
