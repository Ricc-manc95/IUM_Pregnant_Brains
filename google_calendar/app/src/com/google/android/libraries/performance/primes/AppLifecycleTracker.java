// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


public final class AppLifecycleTracker
{

    public final Callbacks callbacks = new Callbacks();

    public AppLifecycleTracker()
    {
    }

    private class Callbacks
        implements android.app.Application.ActivityLifecycleCallbacks, ComponentCallbacks2
    {

        private final AtomicInteger createdCount = new AtomicInteger();
        private final AtomicInteger destroyedCount = new AtomicInteger();
        private Boolean lastForegroundState;
        public final List lifecycleListeners = new CopyOnWriteArrayList();
        private volatile String nameOfForegroundActivity;
        private final AtomicInteger pausedCount = new AtomicInteger();
        public final AtomicInteger resumedCount = new AtomicInteger();
        private final AtomicInteger startedCount = new AtomicInteger();
        private volatile Activity stoppedActivity;
        private final AtomicInteger stoppedCount = new AtomicInteger();

        private final void setForegroundState(Boolean boolean1, Activity activity)
        {
            if (!boolean1.equals(lastForegroundState)) goto _L2; else goto _L1
_L1:
            return;
_L2:
            lastForegroundState = boolean1;
            if (!boolean1.booleanValue())
            {
                break; /* Loop/switch isn't completed */
            }
            boolean1 = "App transition to foreground";
            Object obj = ((Object) (new Object[0]));
            if (Log.isLoggable("AppLifecycleTracker", 3))
            {
                if (obj.length != 0)
                {
                    boolean1 = String.format(Locale.US, "App transition to foreground", ((Object []) (obj)));
                }
                Log.println(3, "AppLifecycleTracker", boolean1);
            }
            boolean1 = lifecycleListeners.iterator();
            while (boolean1.hasNext()) 
            {
                obj = (AppLifecycleListener)boolean1.next();
                if (obj instanceof AppLifecycleListener.OnAppToForeground)
                {
                    ((AppLifecycleListener.OnAppToForeground)obj).onAppToForeground(activity);
                }
            }
            if (true) goto _L1; else goto _L3
_L3:
            boolean1 = "App transition to background";
            Object obj1 = ((Object) (new Object[0]));
            if (Log.isLoggable("AppLifecycleTracker", 3))
            {
                if (obj1.length != 0)
                {
                    boolean1 = String.format(Locale.US, "App transition to background", ((Object []) (obj1)));
                }
                Log.println(3, "AppLifecycleTracker", boolean1);
            }
            boolean1 = lifecycleListeners.iterator();
            while (boolean1.hasNext()) 
            {
                obj1 = (AppLifecycleListener)boolean1.next();
                if (obj1 instanceof AppLifecycleListener.OnAppToBackground)
                {
                    ((AppLifecycleListener.OnAppToBackground)obj1).onAppToBackground(activity);
                }
            }
            if (true) goto _L1; else goto _L4
_L4:
        }

        public final void onActivityCreated(Activity activity, Bundle bundle)
        {
            activity.getApplicationContext();
            createdCount.incrementAndGet();
            stoppedActivity = null;
            activity = lifecycleListeners.iterator();
            do
            {
                if (!activity.hasNext())
                {
                    break;
                }
                bundle = (AppLifecycleListener)activity.next();
                if (bundle instanceof AppLifecycleListener.OnActivityCreated)
                {
                    ((AppLifecycleListener.OnActivityCreated)bundle).onActivityCreated$51662RJ4E9NMIP1FC5O70BQ1CDQ6ITJ9EHSJMJ31DPI74RR9CGNMUSPF89QMSP3CCKTIILG_0();
                }
            } while (true);
        }

        public final void onActivityDestroyed(Activity activity)
        {
            activity.getApplicationContext();
            destroyedCount.incrementAndGet();
            stoppedActivity = null;
            Iterator iterator = lifecycleListeners.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                AppLifecycleListener applifecyclelistener = (AppLifecycleListener)iterator.next();
                if (applifecyclelistener instanceof AppLifecycleListener.OnActivityDestroyed)
                {
                    ((AppLifecycleListener.OnActivityDestroyed)applifecyclelistener).onActivityDestroyed(activity);
                }
            } while (true);
        }

        public final void onActivityPaused(Activity activity)
        {
            activity.getApplicationContext();
            pausedCount.incrementAndGet();
            nameOfForegroundActivity = null;
            Iterator iterator = lifecycleListeners.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                AppLifecycleListener applifecyclelistener = (AppLifecycleListener)iterator.next();
                if (applifecyclelistener instanceof AppLifecycleListener.OnActivityPaused)
                {
                    ((AppLifecycleListener.OnActivityPaused)applifecyclelistener).onActivityPaused(activity);
                }
            } while (true);
        }

        public final void onActivityResumed(Activity activity)
        {
            activity.getApplicationContext();
            resumedCount.incrementAndGet();
            stoppedActivity = null;
            nameOfForegroundActivity = activity.getClass().getSimpleName();
            Iterator iterator = lifecycleListeners.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                AppLifecycleListener applifecyclelistener = (AppLifecycleListener)iterator.next();
                if (applifecyclelistener instanceof AppLifecycleListener.OnActivityResumed)
                {
                    ((AppLifecycleListener.OnActivityResumed)applifecyclelistener).onActivityResumed(activity);
                }
            } while (true);
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle)
        {
            activity.getApplicationContext();
            activity = lifecycleListeners.iterator();
            do
            {
                if (!activity.hasNext())
                {
                    break;
                }
                bundle = (AppLifecycleListener)activity.next();
                if (bundle instanceof AppLifecycleListener.OnActivitySaveInstanceState)
                {
                    ((AppLifecycleListener.OnActivitySaveInstanceState)bundle).onActivitySaveInstanceState$51662RJ4E9NMIP1FC5O70BQ1CDQ6ITJ9EHSJMJ31DPI74RR9CGNMUSPF89QMSP3CCKTIILG_0();
                }
            } while (true);
        }

        public final void onActivityStarted(Activity activity)
        {
            Context context;
            Object obj;
            activity.getApplicationContext();
            startedCount.incrementAndGet();
            stoppedActivity = null;
            context = activity.getApplicationContext();
            obj = ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses();
            if (obj == null) goto _L2; else goto _L1
_L1:
            obj = ((List) (obj)).iterator();
_L5:
            if (!((Iterator) (obj)).hasNext()) goto _L2; else goto _L3
_L3:
            android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)((Iterator) (obj)).next();
            if (runningappprocessinfo.importance != 100 || !runningappprocessinfo.processName.contains(context.getPackageName())) goto _L5; else goto _L4
_L4:
            boolean flag;
            if (android.os.Build.VERSION.SDK_INT < 23)
            {
                flag = ((PowerManager)context.getSystemService("power")).isInteractive();
            } else
            {
                flag = true;
            }
            if (!flag) goto _L5; else goto _L6
_L6:
            flag = true;
_L8:
            setForegroundState(Boolean.valueOf(flag), activity);
            Iterator iterator = lifecycleListeners.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                AppLifecycleListener applifecyclelistener = (AppLifecycleListener)iterator.next();
                if (applifecyclelistener instanceof AppLifecycleListener.OnActivityStarted)
                {
                    ((AppLifecycleListener.OnActivityStarted)applifecyclelistener).onActivityStarted(activity);
                }
            } while (true);
            break; /* Loop/switch isn't completed */
_L2:
            flag = false;
            if (true) goto _L8; else goto _L7
_L7:
        }

        public final void onActivityStopped(Activity activity)
        {
            Object obj;
            Object obj1;
            activity.getApplicationContext();
            stoppedCount.incrementAndGet();
            stoppedActivity = activity;
            obj = lifecycleListeners.iterator();
            do
            {
                if (!((Iterator) (obj)).hasNext())
                {
                    break;
                }
                AppLifecycleListener applifecyclelistener = (AppLifecycleListener)((Iterator) (obj)).next();
                if (applifecyclelistener instanceof AppLifecycleListener.OnActivityStopped)
                {
                    ((AppLifecycleListener.OnActivityStopped)applifecyclelistener).onActivityStopped$51662RJ4E9NMIP1FC5O70BQ1CDQ6ITJ9EHSJMAAM0();
                }
            } while (true);
            obj = activity.getApplicationContext();
            obj1 = ((ActivityManager)((Context) (obj)).getSystemService("activity")).getRunningAppProcesses();
            if (obj1 == null) goto _L2; else goto _L1
_L1:
            obj1 = ((List) (obj1)).iterator();
_L5:
            if (!((Iterator) (obj1)).hasNext()) goto _L2; else goto _L3
_L3:
            android.app.ActivityManager.RunningAppProcessInfo runningappprocessinfo = (android.app.ActivityManager.RunningAppProcessInfo)((Iterator) (obj1)).next();
            if (runningappprocessinfo.importance != 100 || !runningappprocessinfo.processName.contains(((Context) (obj)).getPackageName())) goto _L5; else goto _L4
_L4:
            boolean flag;
            if (android.os.Build.VERSION.SDK_INT < 23)
            {
                flag = ((PowerManager)((Context) (obj)).getSystemService("power")).isInteractive();
            } else
            {
                flag = true;
            }
            if (!flag) goto _L5; else goto _L6
_L6:
            flag = true;
_L8:
            setForegroundState(Boolean.valueOf(flag), activity);
            return;
_L2:
            flag = false;
            if (true) goto _L8; else goto _L7
_L7:
        }

        public final void onConfigurationChanged(Configuration configuration)
        {
        }

        public final void onLowMemory()
        {
        }

        public final void onTrimMemory(int i)
        {
            Iterator iterator = lifecycleListeners.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                AppLifecycleListener applifecyclelistener = (AppLifecycleListener)iterator.next();
                if (applifecyclelistener instanceof AppLifecycleListener.OnTrimMemory)
                {
                    ((AppLifecycleListener.OnTrimMemory)applifecyclelistener).onTrimMemory$514IILG_0();
                }
            } while (true);
            if (i >= 20 && stoppedActivity != null)
            {
                setForegroundState(Boolean.valueOf(false), stoppedActivity);
            }
            stoppedActivity = null;
        }

        Callbacks()
        {
        }
    }

}