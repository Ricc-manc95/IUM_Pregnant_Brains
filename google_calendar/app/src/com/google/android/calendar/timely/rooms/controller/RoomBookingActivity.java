// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.controller;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.common.view.edittexttoolbar.EditTextToolbarPresenter;
import com.google.android.calendar.timely.net.BaseClientFragment;
import com.google.android.calendar.timely.net.grpc.environment.GrpcEnvironments;
import com.google.android.calendar.timely.net.pagination.PaginatingClient;
import com.google.android.calendar.timely.rooms.data.ExpandedMeetingLocation;
import com.google.android.calendar.timely.rooms.data.RoomHierarchyNode;
import com.google.android.calendar.timely.rooms.net.RoomRequest;
import com.google.android.calendar.timely.rooms.ui.RoomListView;
import com.google.android.calendar.timely.rooms.ui.RoomListViewController;
import com.google.android.calendar.timely.widgets.fullscreenerror.FullScreenErrorPage;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.accounts.api.AccountData;
import com.google.android.gms.identity.accounts.api.AccountDataUtil;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.rooms.controller:
//            RoomBookingController, RoomRequestFactory, GAnalytics

public class RoomBookingActivity extends CalendarSupportActivity
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/rooms/controller/RoomBookingActivity);
    private RoomBookingController controller;
    private final RoomBookingController.Delegate controllerDelegate = new _cls1();
    private EditTextToolbarPresenter toolbar;

    public RoomBookingActivity()
    {
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        Object obj1;
        if (accessibilityevent.getEventType() != 32)
        {
            break MISSING_BLOCK_LABEL_197;
        }
        obj1 = controller;
        boolean flag;
        if (accessibilityevent.getEventType() == 32)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        ((RoomBookingController) (obj1)).state;
        JVM INSTR tableswitch 5 8: default 80
    //                   5 97
    //                   6 97
    //                   7 111
    //                   8 97;
           goto _L1 _L2 _L2 _L3 _L2
_L3:
        break MISSING_BLOCK_LABEL_111;
_L1:
        Object obj = null;
_L4:
        FragmentActivity fragmentactivity;
        if (obj == null)
        {
            obj = null;
        } else
        {
            accessibilityevent.getText().add(obj);
            obj = Boolean.valueOf(true);
        }
        if (obj != null)
        {
            return ((Boolean) (obj)).booleanValue();
        }
        break MISSING_BLOCK_LABEL_197;
_L2:
        obj = ((RoomBookingController) (obj1)).activity.getString(0x7f130011);
          goto _L4
        fragmentactivity = ((RoomBookingController) (obj1)).activity;
        obj = ((RoomBookingController) (obj1)).activity.getResources();
        obj1 = ((RoomBookingController) (obj1)).request.getHierarchyNode();
        if (((RoomHierarchyNode) (obj1)).getType() == 0)
        {
            obj = ((RoomHierarchyNode) (obj1)).getName();
        } else
        {
            obj = ((Resources) (obj)).getString(0x7f130410);
        }
        obj = fragmentactivity.getString(0x7f130013, new Object[] {
            obj
        });
          goto _L4
        return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(0, 0);
        View view = findViewById(0x1020002);
        if (view != null)
        {
            ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        if (i != 2001)
        {
            super.onActivityResult(i, j, intent);
        } else
        if (j == -1)
        {
            intent = controller;
            switch (((RoomBookingController) (intent)).state)
            {
            default:
                return;

            case 0: // '\0'
                ((RoomBookingController) (intent)).suggestRoomClient.wipeCache();
                intent.sendRoomRequest();
                return;

            case 4: // '\004'
                ((RoomBookingController) (intent)).client.wipeCache();
                break;
            }
            RoomListViewController roomlistviewcontroller = ((RoomBookingController) (intent)).roomListViewController;
            roomlistviewcontroller.mainView.setVisibility(0);
            roomlistviewcontroller.emptyStateView.setVisibility(8);
            roomlistviewcontroller.roomList.setItems(Collections.emptyList());
            roomlistviewcontroller.hasShowMoreItem = false;
            roomlistviewcontroller.roomList.setFooterMode(2);
            intent.sendRoomsRequest(false);
            return;
        }
    }

    public void onBackPressed()
    {
        controller.onBack();
    }

    protected void onCreate(Bundle bundle)
    {
        Object obj2;
label0:
        {
            super.onCreate(bundle);
            setContentView(0x7f050141);
            Object obj3 = getIntent();
            Context context = getApplicationContext();
            Object obj = AccountDataUtil.zzbxL;
            if (context == null)
            {
                throw new NullPointerException(String.valueOf("Context must not be null."));
            }
            if (obj3 == null)
            {
                throw new NullPointerException(String.valueOf("Intent must not be null."));
            }
            if (context == null)
            {
                throw new NullPointerException(String.valueOf("Context must not be null."));
            }
            if (obj3 == null)
            {
                throw new NullPointerException(String.valueOf("Intent must not be null."));
            }
            Account aaccount[];
            int i;
            int k;
            if (!((Intent) (obj3)).hasExtra("com.google.android.gms.accounts.ACCOUNT_DATA"))
            {
                obj = null;
            } else
            {
                obj = AccountData.CREATOR;
                byte abyte0[] = ((Intent) (obj3)).getByteArrayExtra("com.google.android.gms.accounts.ACCOUNT_DATA");
                if (abyte0 == null)
                {
                    obj = null;
                } else
                {
                    if (obj == null)
                    {
                        throw new NullPointerException("null reference");
                    }
                    obj2 = Parcel.obtain();
                    ((Parcel) (obj2)).unmarshall(abyte0, 0, abyte0.length);
                    ((Parcel) (obj2)).setDataPosition(0);
                    obj = (SafeParcelable)((android.os.Parcelable.Creator) (obj)).createFromParcel(((Parcel) (obj2)));
                    ((Parcel) (obj2)).recycle();
                }
                obj = (AccountData)obj;
            }
            aaccount = AccountManager.get(context).getAccounts();
            k = aaccount.length;
            i = 0;
            do
            {
                if (i >= k)
                {
                    break;
                }
                Account account = aaccount[i];
                if (account.name.equals(((AccountData) (obj)).zzajr) && AccountUtil.isGoogleAccount(account))
                {
                    i = ((Intent) (obj3)).getIntExtra("event_color", getResources().getColor(0x7f0d0088));
                    toolbar = new EditTextToolbarPresenter((Toolbar)findViewById(0x7f100113));
                    toolbar.editText.setHint(0x7f130417);
                    toolbar.toolbar.setBackgroundDrawable(new ColorDrawable(i));
                    obj = StatusbarAnimatorCompat.createInstance(getWindow());
                    ((StatusbarAnimatorCompat) (obj)).setStatusbarColor(ColorUtils.blend(i, 0x33000000));
                    ((StatusbarAnimatorCompat) (obj)).setLightStatusbar(false);
                    obj2 = new RoomBookingController.Builder(this, controllerDelegate, account, GrpcEnvironments.DEFAULT_TARGET_ENVIRONMENT, (ViewGroup)findViewById(0x7f10013c), toolbar, i);
                    if (bundle == null)
                    {
                        RoomRequest roomrequest = RoomRequestFactory.fromIntent(((Intent) (obj3)));
                        obj = ((Intent) (obj3)).getStringArrayListExtra("key_non_removable_selected_room_emails");
                        RoomBookingController.Delegate delegate1;
                        Account account1;
                        ViewGroup viewgroup;
                        EditTextToolbarPresenter edittexttoolbarpresenter;
                        int l;
                        if (RemoteFeatureConfig.SRB.enabled())
                        {
                            bundle = roomrequest;
                        } else
                        {
                            bundle = null;
                        }
                        obj3 = RoomBookingController.getFilterParams(roomrequest);
                        GAnalytics.logScreenShown(((RoomBookingController.Builder) (obj2)).activity, 0);
                        GAnalytics.logApplyFilter(((RoomBookingController.Builder) (obj2)).activity, ((com.google.android.calendar.timely.rooms.data.RoomBookingFilterParams) (obj3)), true);
                        obj3 = ((RoomBookingController.Builder) (obj2)).activity;
                        delegate1 = ((RoomBookingController.Builder) (obj2))._flddelegate;
                        account1 = ((RoomBookingController.Builder) (obj2)).account;
                        l = ((RoomBookingController.Builder) (obj2)).rendezvousTargetEnvironment;
                        viewgroup = ((RoomBookingController.Builder) (obj2)).contentView;
                        edittexttoolbarpresenter = ((RoomBookingController.Builder) (obj2)).toolbar;
                        if (bundle == null)
                        {
                            i = 4;
                        } else
                        {
                            i = 0;
                        }
                        if (obj == null)
                        {
                            obj = Collections.emptySet();
                        } else
                        {
                            obj = new HashSet(((java.util.Collection) (obj)));
                        }
                        controller = new RoomBookingController(((FragmentActivity) (obj3)), delegate1, account1, l, viewgroup, edittexttoolbarpresenter, i, roomrequest, ((java.util.Set) (obj)), bundle, false, null, null, ((RoomBookingController.Builder) (obj2)).themeColor);
                        return;
                    }
                    break label0;
                }
                i++;
            } while (true);
            throw new IllegalArgumentException("Invalid Account");
        }
        RoomRequest roomrequest1 = (RoomRequest)bundle.getParcelable("bundle_key_request");
        FragmentActivity fragmentactivity = ((RoomBookingController.Builder) (obj2)).activity;
        RoomBookingController.Delegate delegate2 = ((RoomBookingController.Builder) (obj2))._flddelegate;
        Account account2 = ((RoomBookingController.Builder) (obj2)).account;
        int j = ((RoomBookingController.Builder) (obj2)).rendezvousTargetEnvironment;
        ViewGroup viewgroup1 = ((RoomBookingController.Builder) (obj2)).contentView;
        EditTextToolbarPresenter edittexttoolbarpresenter1 = ((RoomBookingController.Builder) (obj2)).toolbar;
        int i1 = bundle.getInt("bundle_key_state");
        Object obj1 = bundle.getStringArrayList("bundle_key_ron_removable_room_emails");
        if (obj1 == null)
        {
            obj1 = Collections.emptySet();
        } else
        {
            obj1 = new HashSet(((java.util.Collection) (obj1)));
        }
        controller = new RoomBookingController(fragmentactivity, delegate2, account2, j, viewgroup1, edittexttoolbarpresenter1, i1, roomrequest1, ((java.util.Set) (obj1)), (RoomRequest)bundle.getParcelable("bundle_key_meeting_request"), bundle.getBoolean("bundle_key_room_list_expanded"), (ExpandedMeetingLocation)bundle.getParcelable("bundle_key_expanded_location"), (RoomRequest)bundle.getParcelable("bundle_key_expanded_room_request"), ((RoomBookingController.Builder) (obj2)).themeColor);
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        RoomBookingController roombookingcontroller = controller;
        roombookingcontroller.persistViewControllerState();
        bundle.putInt("bundle_key_state", roombookingcontroller.state);
        bundle.putParcelable("bundle_key_request", roombookingcontroller.request);
        bundle.putBoolean("bundle_key_room_list_expanded", roombookingcontroller.isHierarchyExpanded);
        if (roombookingcontroller.suggestRoomRequest != null)
        {
            bundle.putParcelable("bundle_key_meeting_request", roombookingcontroller.suggestRoomRequest);
            bundle.putParcelable("bundle_key_expanded_location", roombookingcontroller.expandedMeetingLocation);
            bundle.putParcelable("bundle_key_expanded_room_request", roombookingcontroller.expandedLocationRoomRequest);
        }
        bundle.putStringArrayList("bundle_key_ron_removable_room_emails", new ArrayList(roombookingcontroller.nonRemovableRoomEmails));
    }

    protected void onStart()
    {
        super.onStart();
        RoomBookingController roombookingcontroller = controller;
        roombookingcontroller.toolbar.callback = roombookingcontroller.actionBarCallback;
        roombookingcontroller.attachView();
    }

    protected void onStop()
    {
        super.onStop();
        Object obj = controller;
        ((RoomBookingController) (obj)).detachView();
        ((RoomBookingController) (obj)).toolbar.callback = null;
        obj = (ArrayList)new ArrayList(((RoomBookingController) (obj)).runningFutures.futures);
        int j = ((ArrayList) (obj)).size();
        for (int i = 0; i < j;)
        {
            Object obj1 = ((ArrayList) (obj)).get(i);
            i++;
            ((ListenableFuture)obj1).cancel(true);
        }

    }


    private class _cls1
        implements RoomBookingController.Delegate
    {

        private final RoomBookingActivity this$0;

        public final boolean isOnline()
        {
            return NetworkUtil.isConnectedToInternet(RoomBookingActivity.this);
        }

        public final void onConnectionError(Throwable throwable)
        {
            if (AuthExceptionUtils.isUserRecoverableAuthException(throwable))
            {
                startActivityForResult(AuthExceptionUtils.getAuthIntent(throwable), 2001);
                return;
            } else
            {
                LogUtils.e(RoomBookingActivity.TAG, throwable, "Unrecoverable connection error", new Object[0]);
                return;
            }
        }

        public final void onFinish(List list)
        {
            RoomBookingActivity roombookingactivity = RoomBookingActivity.this;
            ArrayList arraylist = new ArrayList();
            ArrayList arraylist1 = new ArrayList();
            Room room;
            for (list = list.iterator(); list.hasNext(); arraylist1.add(room.getEmail()))
            {
                room = (Room)list.next();
                arraylist.add(room.getName());
            }

            roombookingactivity.setResult(-1, (new Intent()).putStringArrayListExtra("intent_key_room_emails", arraylist1).putStringArrayListExtra("intent_key_room_names", arraylist));
            finish();
        }

        public final void onWindowStateChanged()
        {
            getWindow().getDecorView().sendAccessibilityEvent(32);
        }

        _cls1()
        {
            this$0 = RoomBookingActivity.this;
            super();
        }
    }

}
