// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.animation;


public abstract class ChoreographerCompat
{

    public static final ThreadLocal threadInstance = new _cls1();

    public ChoreographerCompat()
    {
    }

    public abstract void postFrameCallback(FrameCallback framecallback);


    private class _cls1 extends ThreadLocal
    {

        protected final Object initialValue()
        {
            return new RealChoreographer();
        }

        _cls1()
        {
        }

        private class RealChoreographer extends ChoreographerCompat
        {

            private Choreographer choreographer;

            public final void postFrameCallback(FrameCallback framecallback)
            {
                Choreographer choreographer1 = choreographer;
                class FrameCallback._cls1
                    implements android.view.Choreographer.FrameCallback
                {

                    private final FrameCallback this$0;

                    public final void doFrame(long l)
                    {
                        _mth5152ILG_0();
                    }

                    FrameCallback._cls1()
                    {
                        this$0 = FrameCallback.this;
                        super();
                    }
                }

                if (framecallback.realCallback == null)
                {
                    framecallback.realCallback = framecallback. new FrameCallback._cls1();
                }
                choreographer1.postFrameCallback(framecallback.realCallback);
            }

            public RealChoreographer()
            {
                choreographer = Choreographer.getInstance();
            }

            private class FrameCallback
            {

                public android.view.Choreographer.FrameCallback realCallback;

                public abstract void doFrame$5152ILG_0();

                public FrameCallback()
                {
                }
            }

        }

    }

}
