// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.report;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.syncadapters.calendar.timely.DebugReportingConstants;
import com.google.android.syncadapters.calendar.timely.consistency.ConsistencyChecker;

// Referenced classes of package com.google.android.calendar.timely.report:
//            ViewDebugReportActivity

public class DebugReportingReceiver extends BroadcastReceiver
{

    public static final String ACTION_DELETE_REPORT = String.valueOf(com/google/android/calendar/timely/report/DebugReportingReceiver.getCanonicalName()).concat(".DeleteReport");
    public static final String ACTION_NOTIFY_REPORT;
    public static final String ACTION_SEND_REPORT = String.valueOf(com/google/android/calendar/timely/report/DebugReportingReceiver.getCanonicalName()).concat(".SendReport");
    public static final String ACTION_VIEW_REPORT = String.valueOf(com/google/android/calendar/timely/report/DebugReportingReceiver.getCanonicalName()).concat(".ViewReport");
    public static final String EXTRA_DONT_ASK_AGAIN = String.valueOf(com/google/android/calendar/timely/report/DebugReportingReceiver.getCanonicalName()).concat(".DoNotAsk");
    public static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/timely/consistency/ConsistencyChecker);
    public static Bitmap dogfoodIcon;
    public GoogleApiClient apiClient;

    public DebugReportingReceiver()
    {
    }

    static void deleteReportFile(Context context, String s)
    {
        if (context.deleteFile(s))
        {
            LogUtils.d(TAG, "Deleted report file: %s", new Object[] {
                s
            });
        } else
        {
            LogUtils.w(TAG, "Could not delete report file: %s", new Object[] {
                s
            });
        }
        ((NotificationManager)context.getSystemService("notification")).cancel(s, 0);
    }

    static PendingIntent intentForReportCalendarAndAction(Context context, String s, String s1, String s2, int i)
    {
        if (s2.equals(ACTION_VIEW_REPORT))
        {
            return PendingIntent.getActivity(context, 0, (new Intent(s2, Uri.parse(s))).addFlags(0x10008000).putExtra(DebugReportingConstants.EXTRA_CALENDAR_ID, s1).putExtra("report_type", i).setClass(context, com/google/android/calendar/timely/report/ViewDebugReportActivity), 0);
        } else
        {
            return PendingIntent.getBroadcast(context, 0, (new Intent(s2, Uri.parse(s))).putExtra(DebugReportingConstants.EXTRA_CALENDAR_ID, s1).putExtra("report_type", i).setClass(context, com/google/android/calendar/timely/report/DebugReportingReceiver), 0);
        }
    }

    static final void lambda$notifyUserAboutStatus$1$DebugReportingReceiver(Context context, String s)
    {
        Toast.makeText(context, s, 1).show();
    }

    public void onReceive(Context context, Intent intent)
    {
        android.content.BroadcastReceiver.PendingResult pendingresult = goAsync();
        class .Lambda._cls0
            implements Runnable
        {

            private final DebugReportingReceiver arg$1;
            private final Context arg$2;
            private final Intent arg$3;
            private final android.content.BroadcastReceiver.PendingResult arg$4;

            public final void run()
            {
                Object obj;
                final String fileName;
                Object obj1;
                final Context context;
                .Lambda._cls2 _lcls2;
                String s;
                Object obj2;
                Object obj3;
                Object obj4;
                int i;
                obj1 = arg$1;
                context = arg$2;
                obj = arg$3;
                fileName = arg$4;
                context = context.getApplicationContext();
                fileName.getClass();
                class .Lambda._cls2
                    implements Runnable
                {

                    private final android.content.BroadcastReceiver.PendingResult arg$1;

                    public final void run()
                    {
                        arg$1.finish();
                    }

                        .Lambda._cls2(android.content.BroadcastReceiver.PendingResult pendingresult)
                        {
                            arg$1 = pendingresult;
                        }
                }

                _lcls2 = new .Lambda._cls2(fileName);
                s = ((Intent) (obj)).getAction();
                if (((Intent) (obj)).getData() == null)
                {
                    fileName = null;
                } else
                {
                    fileName = ((Intent) (obj)).getData().getPath();
                }
                obj2 = ((Intent) (obj)).getStringExtra(DebugReportingConstants.EXTRA_CALENDAR_ID);
                i = ((Intent) (obj)).getIntExtra("report_type", 0);
                obj3 = context.getSharedPreferences(String.valueOf(obj2).concat("_preferences"), 0);
                if (!s.equals(DebugReportingReceiver.ACTION_SEND_REPORT)) goto _L2; else goto _L1
_L1:
                if (((Intent) (obj)).getBooleanExtra(DebugReportingReceiver.EXTRA_DONT_ASK_AGAIN, false))
                {
                    ((SharedPreferences) (obj3)).edit().putInt("ALLOW_CONSISTENCY_REPORTING", 1).apply();
                }
                obj4 = new ByteArrayOutputStream();
                IOUtils.copy(context.openFileInput(fileName), ((java.io.OutputStream) (obj4)), true);
                if (((DebugReportingReceiver) (obj1)).apiClient != null) goto _L4; else goto _L3
_L3:
                obj = (new com.google.android.gms.common.api.GoogleApiClient.Builder(context)).addApi(Feedback.API);
                obj3 = new _cls2();
                if (obj3 != null)
                {
                    break MISSING_BLOCK_LABEL_277;
                }
                try
                {
                    throw new NullPointerException(String.valueOf("Listener must not be null"));
                }
                // Misplaced declaration of an exception variable
                catch (Object obj) { }
                finally
                {
                    _lcls2.run();
                }
                LogUtils.e(DebugReportingReceiver.TAG, ((Throwable) (obj)), "While performing %s %s", new Object[] {
                    s, fileName
                });
                if (!s.equals(DebugReportingReceiver.ACTION_DELETE_REPORT))
                {
                    DebugReportingReceiver.deleteReportFile(context, fileName);
                }
                _lcls2.run();
                return;
                ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj)).zzaJl.add(obj3);
                obj3 = new _cls1();
                if (obj3 != null)
                {
                    break MISSING_BLOCK_LABEL_324;
                }
                throw new NullPointerException(String.valueOf("Listener must not be null"));
                throw obj;
                ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj)).zzaJm.add(obj3);
                obj1.apiClient = ((com.google.android.gms.common.api.GoogleApiClient.Builder) (obj)).build();
_L4:
                if (!((DebugReportingReceiver) (obj1)).apiClient.isConnected())
                {
                    ((DebugReportingReceiver) (obj1)).apiClient.connect();
                }
                obj3 = new com.google.android.gms.feedback.FeedbackOptions.Builder();
                i;
                JVM INSTR tableswitch 0 0: default 1379
            //                           0 538;
                   goto _L5 _L6
_L5:
                LogUtils.wtf(DebugReportingReceiver.TAG, "Unknown report type.", new Object[0]);
                obj = "";
_L9:
                obj3.mDescription = ((String) (obj));
                obj3.mAccountInUse = ((String) (obj2));
                obj = ((ByteArrayOutputStream) (obj4)).toByteArray();
                ((com.google.android.gms.feedback.FeedbackOptions.Builder) (obj3)).mFileTeleporters.add(new FileTeleporter(((byte []) (obj)), "text/plain", "REPORT"));
                i;
                JVM INSTR tableswitch 0 0: default 1382
            //                           0 1385;
                   goto _L7 _L8
_L7:
                LogUtils.wtf(DebugReportingReceiver.TAG, "Unknown report type.", new Object[0]);
                obj = "";
_L30:
                obj3.mCategoryTag = ((String) (obj));
                GoogleFeedbackUtils.addEssentialDataToFeedback(((com.google.android.gms.feedback.FeedbackOptions.Builder) (obj3)), context);
                Feedback.startFeedback(((DebugReportingReceiver) (obj1)).apiClient, ((com.google.android.gms.feedback.FeedbackOptions.Builder) (obj3)).build()).setResultCallback(((_cls3) (obj1)). new _cls3());
_L12:
                _lcls2.run();
                return;
_L6:
                obj = "Consistency Report";
                  goto _L9
_L2:
                if (!s.equals(DebugReportingReceiver.ACTION_DELETE_REPORT)) goto _L11; else goto _L10
_L10:
                if (((Intent) (obj)).getBooleanExtra(DebugReportingReceiver.EXTRA_DONT_ASK_AGAIN, false))
                {
                    ((SharedPreferences) (obj3)).edit().putInt("ALLOW_CONSISTENCY_REPORTING", -1).apply();
                }
                DebugReportingReceiver.deleteReportFile(context, fileName);
                  goto _L12
_L11:
                if (!s.equals(DebugReportingReceiver.ACTION_NOTIFY_REPORT)) goto _L14; else goto _L13
_L13:
                if (((SharedPreferences) (obj3)).getInt("ALLOW_CONSISTENCY_REPORTING", 0) != -1) goto _L16; else goto _L15
_L15:
                LogUtils.d(DebugReportingReceiver.TAG, "User denied sending reports", new Object[0]);
                DebugReportingReceiver.deleteReportFile(context, fileName);
                  goto _L12
_L16:
                PendingIntent pendingintent;
                LogUtils.d(DebugReportingReceiver.TAG, "Will ask user for permission", new Object[0]);
                if (DebugReportingReceiver.dogfoodIcon == null)
                {
                    DebugReportingReceiver.dogfoodIcon = BitmapFactory.decodeResource(context.getResources(), 0x7f0201e0);
                }
                pendingintent = DebugReportingReceiver.intentForReportCalendarAndAction(context, fileName, ((String) (obj2)), DebugReportingReceiver.ACTION_VIEW_REPORT, i);
                obj4 = DebugReportingReceiver.intentForReportCalendarAndAction(context, fileName, ((String) (obj2)), DebugReportingReceiver.ACTION_SEND_REPORT, i);
                obj = DebugReportingReceiver.intentForReportCalendarAndAction(context, fileName, ((String) (obj2)), DebugReportingReceiver.ACTION_DELETE_REPORT, i);
                obj3 = new android.support.v4.app.NotificationCompat.Builder(context);
                i;
                JVM INSTR tableswitch 0 0: default 1392
            //                           0 791;
                   goto _L17 _L18
_L17:
                throw new IllegalArgumentException((new StringBuilder(36)).append("Unsupported report type: ").append(i).toString());
_L18:
                obj1 = ((android.support.v4.app.NotificationCompat.Builder) (obj3)).setContentTitle(context.getString(0x7f13030d)).setContentText(context.getString(0x7f1300ed));
                android.support.v4.app.NotificationCompat.BigTextStyle bigtextstyle = new android.support.v4.app.NotificationCompat.BigTextStyle();
                String s1 = context.getString(0x7f1300ed);
                obj2 = context.getString(0x7f1300ec, new Object[] {
                    obj2
                });
                obj1 = ((android.support.v4.app.NotificationCompat.Builder) (obj1)).setStyle(bigtextstyle.bigText((new StringBuilder(String.valueOf(s1).length() + 2 + String.valueOf(obj2).length())).append(s1).append("\n\n").append(((String) (obj2))).toString()));
                obj1.mContentIntent = pendingintent;
                ((android.support.v4.app.NotificationCompat.Builder) (obj1)).mNotification.deleteIntent = ((PendingIntent) (obj));
                obj2 = ((android.support.v4.app.NotificationCompat.Builder) (obj1)).setAutoCancel(false);
                ((android.support.v4.app.NotificationCompat.Builder) (obj2)).mNotification.icon = 0x7f020133;
                obj1 = DebugReportingReceiver.dogfoodIcon;
                obj = obj1;
                if (obj1 == null) goto _L20; else goto _L19
_L19:
                if (android.os.Build.VERSION.SDK_INT < 27) goto _L22; else goto _L21
_L21:
                obj = obj1;
_L20:
                obj2.mLargeIcon = ((Bitmap) (obj));
                obj = context.getString(0x7f1303f1);
                ((android.support.v4.app.NotificationCompat.Builder) (obj2)).mActions.add(new android.support.v4.app.NotificationCompat.Action(0x108009b, ((CharSequence) (obj)), pendingintent));
                obj = context.getString(0x7f1303f0);
                ((android.support.v4.app.NotificationCompat.Builder) (obj2)).mActions.add(new android.support.v4.app.NotificationCompat.Action(0x1080029, ((CharSequence) (obj)), ((PendingIntent) (obj4))));
                obj = (NotificationManager)context.getSystemService("notification");
                if (android.os.Build.VERSION.SDK_INT >= 26)
                {
                    NotificationChannels.initialize(context, NotificationChannels.channelsCreated);
                    obj3.mChannelId = "DEBUG";
                }
                NotificationUtil.notify(((NotificationManager) (obj)), fileName, 0, ((android.support.v4.app.NotificationCompat.Builder) (obj3)).build());
                  goto _L12
_L22:
                int j;
                obj = ((android.support.v4.app.NotificationCompat.Builder) (obj2)).mContext.getResources();
                i = ((Resources) (obj)).getDimensionPixelSize(0x7f0e00b8);
                j = ((Resources) (obj)).getDimensionPixelSize(0x7f0e00b7);
                if (((Bitmap) (obj1)).getWidth() > i) goto _L24; else goto _L23
_L23:
                obj = obj1;
                if (((Bitmap) (obj1)).getHeight() <= j) goto _L20; else goto _L24
_L24:
                double d = Math.min((double)i / (double)Math.max(1, ((Bitmap) (obj1)).getWidth()), (double)j / (double)Math.max(1, ((Bitmap) (obj1)).getHeight()));
                obj = Bitmap.createScaledBitmap(((Bitmap) (obj1)), (int)Math.ceil((double)((Bitmap) (obj1)).getWidth() * d), (int)Math.ceil(d * (double)((Bitmap) (obj1)).getHeight()), true);
                  goto _L20
_L14:
                if (!s.equals(DebugReportingConstants.ACTION_SHORT_STATUS) || !((Intent) (obj)).hasExtra(DebugReportingConstants.EXTRA_STATUS_CODE)) goto _L12; else goto _L25
_L25:
                j = ((Intent) (obj)).getIntExtra(DebugReportingConstants.EXTRA_STATUS_CODE, -1);
                i;
                JVM INSTR tableswitch 0 0: default 1395
            //                           0 1400;
                   goto _L26 _L27
_L29:
                if (obj == null) goto _L12; else goto _L28
_L28:
                class .Lambda._cls1
                    implements Runnable
                {

                    private final Context arg$1;
                    private final String arg$2;

                    public final void run()
                    {
                        DebugReportingReceiver.lambda$notifyUserAboutStatus$1$DebugReportingReceiver(arg$1, arg$2);
                    }

                        .Lambda._cls1(Context context, String s)
                        {
                            arg$1 = context;
                            arg$2 = s;
                        }
                }

                (new Handler(context.getMainLooper())).post(new .Lambda._cls1(context, ((String) (obj))));
                  goto _L12
_L31:
                LogUtils.wtf(DebugReportingReceiver.TAG, "Unsupported consistency check status code: %d", new Object[] {
                    Integer.valueOf(j)
                });
                obj = null;
                  goto _L29
_L32:
                obj = context.getString(0x7f1300ef, new Object[] {
                    obj2
                });
                  goto _L29
_L33:
                obj = context.getString(0x7f1300f0, new Object[] {
                    obj2
                });
                  goto _L29
_L34:
                obj = context.getString(0x7f1300eb, new Object[] {
                    obj2
                });
                  goto _L29
_L8:
                obj = "com.google.android.calendar.CONSISTENCY_REPORT";
                  goto _L30
_L26:
                obj = null;
                  goto _L29
_L27:
                j;
                JVM INSTR tableswitch 0 2: default 1428
            //                           0 1316
            //                           1 1337
            //                           2 1358;
                   goto _L31 _L32 _L33 _L34
            }

            .Lambda._cls0(Context context, Intent intent, android.content.BroadcastReceiver.PendingResult pendingresult)
            {
                arg$1 = DebugReportingReceiver.this;
                arg$2 = context;
                arg$3 = intent;
                arg$4 = pendingresult;
            }

            private class _cls2
                implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
            {

                public final void onConnected(Bundle bundle)
                {
                    LogUtils.v(DebugReportingReceiver.TAG, "Feedback API client connected", new Object[0]);
                }

                public final void onConnectionSuspended(int i)
                {
                    LogUtils.v(DebugReportingReceiver.TAG, "Feedback API client disconnected: %d", new Object[] {
                        Integer.valueOf(i)
                    });
                }

                _cls2()
                {
                }
            }


            private class _cls1
                implements com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
            {

                public final void onConnectionFailed(ConnectionResult connectionresult)
                {
                    LogUtils.e(DebugReportingReceiver.TAG, "Feedback API client failed to connect: %d", new Object[] {
                        Integer.valueOf(connectionresult.zzaEP)
                    });
                }

                _cls1()
                {
                }
            }


            private class _cls3
                implements ResultCallback
            {

                private final DebugReportingReceiver this$0;
                private final Context val$context;
                private final String val$fileName;

                public final void onResult(Result result)
                {
                    result = (Status)result;
                    apiClient.disconnect();
                    boolean flag;
                    if (((Status) (result)).zzaEP <= 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
                        LogUtils.v(DebugReportingReceiver.TAG, "Sending Feedback: Success", new Object[0]);
                        result = DebugReportingReceiver.this;
                        DebugReportingReceiver.deleteReportFile(context, fileName);
                        return;
                    } else
                    {
                        LogUtils.w(DebugReportingReceiver.TAG, "Sending Feedback: %s", new Object[] {
                            ((Status) (result)).zzaIk
                        });
                        return;
                    }
                }

                _cls3()
                {
                    this$0 = DebugReportingReceiver.this;
                    context = context1;
                    fileName = s;
                    super();
                }
            }

        }

        CalendarExecutor.BACKGROUND.execute(new .Lambda._cls0(context, intent, pendingresult));
    }

    static 
    {
        ACTION_NOTIFY_REPORT = DebugReportingConstants.ACTION_NOTIFY_REPORT;
    }
}
