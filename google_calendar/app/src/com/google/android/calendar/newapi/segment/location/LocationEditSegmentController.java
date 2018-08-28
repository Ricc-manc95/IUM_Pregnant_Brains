// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.android.bitmap.RequestKey;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.location.Address;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.GeoCoordinates;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.event.image.PlacePageOrMapRequestKey;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.segment.common.FullScreenEditSegmentController;
import com.google.android.calendar.newapi.segment.common.IconAnimator;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.newapi.segment.location.fullscreen.LocationEditFullScreenController;
import com.google.android.calendar.newapi.segment.room.RoomUtils;
import com.google.android.calendar.newapi.utils.LegacyUtils;
import com.google.android.calendar.newapi.utils.LocationUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.timely.settings.PreferencesUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import com.google.common.base.Joiner;
import com.google.common.base.Platform;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.newapi.segment.location:
//            LocationEditSegment, PlacePageOrMapRequestKeyFactory

public class LocationEditSegmentController extends FullScreenEditSegmentController
    implements com.google.android.calendar.newapi.segment.common.fullscreen.EditFullScreenController.OnFullScreenResultListener, LocationEditSegment.Listener
{
    public static class LocationActionDialog extends DialogFragment
        implements android.content.DialogInterface.OnClickListener
    {

        public static final String TAG = com/google/android/calendar/newapi/segment/location/LocationEditSegmentController$LocationActionDialog.getSimpleName();

        public void onClick(DialogInterface dialoginterface, int i)
        {
            LocationEditSegmentController locationeditsegmentcontroller = (LocationEditSegmentController)super.mTarget;
            Object obj = locationeditsegmentcontroller.getLocation();
            switch (i)
            {
            default:
                return;

            case 0: // '\0'
                ActivityUtils.startActivityForUrl(locationeditsegmentcontroller.getContext(), ((EventLocation) (obj)).url, LocationEditSegmentController.TAG);
                return;

            case 1: // '\001'
                break;
            }
            Joiner joiner;
            String s;
            Object aobj[];
            if (((EventLocation) (obj)).address == null)
            {
                dialoginterface = null;
            } else
            {
                dialoginterface = ((EventLocation) (obj)).address.formattedAddress;
            }
            s = ((EventLocation) (obj)).name;
            obj = new com.google.android.calendar.api.event.location.EventLocation.Builder();
            joiner = (new Joiner(", ")).skipNulls();
            s = Platform.emptyToNull(s);
            dialoginterface = Platform.emptyToNull(dialoginterface);
            aobj = new Object[0];
            if (aobj == null)
            {
                throw new NullPointerException();
            }
            dialoginterface = (new com.google.common.base.Joiner._cls3(aobj, s, dialoginterface)).iterator();
            dialoginterface = joiner.appendTo(new StringBuilder(), dialoginterface).toString();
            if (dialoginterface == null)
            {
                throw new NullPointerException();
            } else
            {
                obj.name = (String)dialoginterface;
                dialoginterface = new EventLocation(((com.google.android.calendar.api.event.location.EventLocation.Builder) (obj)));
                locationeditsegmentcontroller.clearLocations();
                ((EventModificationsHolder)(EditScreenModel)((SegmentController) (locationeditsegmentcontroller)).model).getEventModifications().getLocationModification().addEventLocation(dialoginterface);
                locationeditsegmentcontroller.updateUi();
                return;
            }
        }

        public final Dialog onCreateDialog(Bundle bundle)
        {
            String s = requireContext().getResources().getString(0x7f130378);
            String s1 = requireContext().getResources().getString(0x7f130377);
            if (super.mHost == null)
            {
                bundle = null;
            } else
            {
                bundle = (FragmentActivity)super.mHost.mActivity;
            }
            return (new android.app.AlertDialog.Builder(bundle)).setItems(new CharSequence[] {
                s, s1
            }, this).create();
        }


        public LocationActionDialog()
        {
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/location/LocationEditSegmentController);

    public LocationEditSegmentController()
    {
    }

    private final void openFullScreenExperience()
    {
        Object obj = getLocation();
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((EventLocation) (obj)).name;
        }
        openInFullScreen(LocationEditFullScreenController.newInstance(((String) (obj)), ((EditScreenModel)super.model).getColor().getValue()));
    }

    final void clearLocations()
    {
        StructuredLocationModifications structuredlocationmodifications = ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getLocationModification();
        for (Iterator iterator = structuredlocationmodifications.getEventLocations().iterator(); iterator.hasNext(); structuredlocationmodifications.removeEventLocation((EventLocation)iterator.next())) { }
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (LocationEditSegment)layoutinflater.inflate(0x7f0500d9, null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    final EventLocation getLocation()
    {
        Object obj = ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getLocationModification().getEventLocations().iterator();
        Object obj2;
        if (((Iterator) (obj)).hasNext())
        {
            obj = ((Iterator) (obj)).next();
        } else
        {
            obj = null;
        }
        obj2 = (EventLocation)obj;
        if (obj2 != null)
        {
            Object obj1 = Platform.nullToEmpty(RoomUtils.removeRoomsFromLocation(((EventLocation) (obj2)).name, RoomUtils.getOriginalRooms(((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications())));
            obj2 = new com.google.android.calendar.api.event.location.EventLocation.Builder(((EventLocation) (obj2)));
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            obj2.name = (String)obj1;
            obj1 = new EventLocation(((com.google.android.calendar.api.event.location.EventLocation.Builder) (obj2)));
            boolean flag;
            if (LegacyUtils.isLegacyLocationOrEmpty(((EventLocation) (obj1))) && TextUtils.isEmpty(((EventLocation) (obj1)).name))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return ((EventLocation) (obj1));
            }
        }
        return null;
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        Object obj1 = getLocation();
        if (!AccountUtil.isGoogleAccount(((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getCalendar().account) && obj1 != null)
        {
            Object obj;
            Joiner joiner;
            String s;
            Object aobj[];
            if (((EventLocation) (obj1)).address == null)
            {
                obj = null;
            } else
            {
                obj = ((EventLocation) (obj1)).address.formattedAddress;
            }
            s = ((EventLocation) (obj1)).name;
            obj1 = new com.google.android.calendar.api.event.location.EventLocation.Builder();
            joiner = (new Joiner(", ")).skipNulls();
            s = Platform.emptyToNull(s);
            obj = Platform.emptyToNull(((String) (obj)));
            aobj = new Object[0];
            if (aobj == null)
            {
                throw new NullPointerException();
            }
            obj = (new com.google.common.base.Joiner._cls3(aobj, s, obj)).iterator();
            obj = joiner.appendTo(new StringBuilder(), ((Iterator) (obj))).toString();
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj1.name = (String)obj;
            obj = new EventLocation(((com.google.android.calendar.api.event.location.EventLocation.Builder) (obj1)));
            clearLocations();
            ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getLocationModification().addEventLocation(((EventLocation) (obj)));
        }
        updateUi();
    }

    public final void onFullScreenResult(Object obj)
    {
        Joiner joiner = null;
        Object obj1 = (EventLocation)obj;
        boolean flag;
        if (obj1 == null)
        {
            clearLocations();
        } else
        if (AccountUtil.isGoogleAccount(((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getCalendar().account))
        {
            clearLocations();
            ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getLocationModification().addEventLocation(((EventLocation) (obj1)));
        } else
        {
            com.google.android.calendar.api.event.location.EventLocation.Builder builder;
            Joiner joiner1;
            String s1;
            Object aobj1[];
            if (((EventLocation) (obj1)).address == null)
            {
                obj = null;
            } else
            {
                obj = ((EventLocation) (obj1)).address.formattedAddress;
            }
            s1 = ((EventLocation) (obj1)).name;
            builder = new com.google.android.calendar.api.event.location.EventLocation.Builder();
            joiner1 = (new Joiner(", ")).skipNulls();
            s1 = Platform.emptyToNull(s1);
            obj = Platform.emptyToNull(((String) (obj)));
            aobj1 = new Object[0];
            if (aobj1 == null)
            {
                throw new NullPointerException();
            }
            obj = (new com.google.common.base.Joiner._cls3(aobj1, s1, obj)).iterator();
            obj = joiner1.appendTo(new StringBuilder(), ((Iterator) (obj))).toString();
            if (obj == null)
            {
                throw new NullPointerException();
            }
            builder.name = (String)obj;
            obj = new EventLocation(builder);
            clearLocations();
            ((EventModificationsHolder)(EditScreenModel)super.model).getEventModifications().getLocationModification().addEventLocation(((EventLocation) (obj)));
        }
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (obj1 == null)
            {
                ((LocationEditSegment)super.view).announceForAccessibility(requireContext().getResources().getString(0x7f13000a));
            } else
            {
                String s = ((EventLocation) (obj1)).name;
                Object aobj[];
                if (((EventLocation) (obj1)).address == null)
                {
                    obj = joiner;
                } else
                {
                    obj = ((EventLocation) (obj1)).address.formattedAddress;
                }
                joiner = (new Joiner(", ")).skipNulls();
                obj1 = Platform.emptyToNull(s);
                obj = Platform.emptyToNull(((String) (obj)));
                aobj = new Object[0];
                if (aobj == null)
                {
                    throw new NullPointerException();
                }
                obj = (new com.google.common.base.Joiner._cls3(aobj, obj1, obj)).iterator();
                obj = joiner.appendTo(new StringBuilder(), ((Iterator) (obj))).toString();
                obj = requireContext().getResources().getString(0x7f13000b, new Object[] {
                    obj
                });
                ((LocationEditSegment)super.view).announceForAccessibility(((CharSequence) (obj)));
            }
        }
        updateUi();
    }

    protected final void onInitialize()
    {
        String s = null;
        int j = 0;
        Object obj = super.view;
        boolean flag1 = LocationUtils.allowsUpdateLocation(((PermissionHolder)(EditScreenModel)super.model).getPermissions());
        int i;
        if (obj != null)
        {
            if (flag1)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            ((View) (obj)).setVisibility(i);
        }
        if (flag1)
        {
            LocationEditSegment locationeditsegment = (LocationEditSegment)super.view;
            Object obj2 = getLocation();
            if (obj2 == null)
            {
                i = 1;
            } else
            if (TextUtils.isEmpty(((EventLocation) (obj2)).name) && ((EventLocation) (obj2)).address == null && ((EventLocation) (obj2)).geo == null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                obj = locationeditsegment.tile;
                ((TextTileView) (obj)).setPrimaryText(new CharSequence[] {
                    ((TextTileView) (obj)).getResources().getString(0x7f1301b5, new Object[0])
                });
                ((TextTileView) (obj)).setSecondaryText(new CharSequence[] {
                    null
                }).setPrimaryTextColor(locationeditsegment.getResources().getColor(0x7f0d00ab));
                obj = locationeditsegment.imagePreview;
                if (obj != null)
                {
                    ((View) (obj)).setVisibility(8);
                }
            } else
            {
                locationeditsegment.tile.setPrimaryTextColor(locationeditsegment.getResources().getColor(0x7f0d0309));
                TextTileView texttileview = locationeditsegment.tile;
                String s2 = ((EventLocation) (obj2)).name;
                Object obj1 = ((EventLocation) (obj2)).address;
                boolean flag;
                if (obj1 == null)
                {
                    obj1 = null;
                } else
                {
                    obj1 = ((Address) (obj1)).formattedAddress;
                }
                texttileview.setTextAdaptively(s2, ((CharSequence) (obj1)));
                obj1 = locationeditsegment.imagePreview;
                if (!TextUtils.isEmpty(((EventLocation) (obj2)).mapsClusterId) || ((EventLocation) (obj2)).geo != null || ((EventLocation) (obj2)).address != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (obj1 != null)
                {
                    String s1;
                    GeoCoordinates geocoordinates;
                    if (!flag)
                    {
                        j = 8;
                    }
                    ((View) (obj1)).setVisibility(j);
                }
                if (flag)
                {
                    obj1 = ((EventLocation) (obj2)).address;
                    if (obj1 != null && !TextUtils.isEmpty(((Address) (obj1)).formattedAddress))
                    {
                        obj1 = ((Address) (obj1)).formattedAddress;
                    } else
                    {
                        obj1 = null;
                    }
                    s1 = ((EventLocation) (obj2)).mapsClusterId;
                    geocoordinates = ((EventLocation) (obj2)).geo;
                    if (geocoordinates != null)
                    {
                        obj2 = Double.valueOf(geocoordinates.latitude).toString();
                        s = Double.valueOf(geocoordinates.longitude).toString();
                    } else
                    {
                        obj2 = null;
                    }
                    locationeditsegment.drawable.setDecodeDimensions(locationeditsegment.previewImageWidth, locationeditsegment.previewImageHeight);
                    obj1 = locationeditsegment.requestKeyFactory.createRequestKey(s1, ((String) (obj2)), s, ((String) (obj1)), locationeditsegment.previewImageWidth, locationeditsegment.previewImageHeight);
                    if (obj1 instanceof PlacePageOrMapRequestKey)
                    {
                        locationeditsegment.drawable.bind((RequestKey)obj1);
                        return;
                    }
                }
            }
        }
    }

    public final void onLocationChanged(boolean flag)
    {
        updateUi();
        if (flag)
        {
            LocationEditSegment locationeditsegment = (LocationEditSegment)super.view;
            int i = ((EditScreenModel)model).getColor().getValue();
            IconAnimator.animate(locationeditsegment.tile.getIcon(), i);
        }
    }

    public final void onLocationTileClicked()
    {
        if (!AndroidPermissionUtils.hasLocationPermissions(getContext()))
        {
            requestPermissions(new String[] {
                "android.permission.ACCESS_COARSE_LOCATION"
            }, 42);
            return;
        } else
        {
            openFullScreenExperience();
            return;
        }
    }

    public final void onMapPreviewClicked()
    {
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        if (this == null)
        {
            break MISSING_BLOCK_LABEL_107;
        }
        fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L3; else goto _L2
_L2:
        flag = false;
_L4:
        FragmentActivity fragmentactivity;
        if (!flag)
        {
            return;
        } else
        {
            LocationActionDialog locationactiondialog = new LocationActionDialog();
            locationactiondialog.setTargetFragment(this, 0);
            locationactiondialog.show(super.mFragmentManager, LocationActionDialog.TAG);
            return;
        }
_L3:
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
        } else
        {
            if (fragmentmanagerimpl == null || fragmentmanagerimpl.isDestroyed())
            {
                break MISSING_BLOCK_LABEL_107;
            }
            flag = true;
        }
          goto _L4
        flag = false;
          goto _L4
    }

    public final void onRequestPermissionsResult(int i, String as[], int ai[])
    {
        if (i != 42)
        {
            super.onRequestPermissionsResult(i, as, ai);
            return;
        } else
        {
            PreferencesUtils.incrementLocationPermissionRequest(getContext());
            openFullScreenExperience();
            return;
        }
    }

    final void updateUi()
    {
        String s;
        int j;
        s = null;
        j = 0;
        View view = super.view;
        boolean flag2 = LocationUtils.allowsUpdateLocation(((PermissionHolder)(EditScreenModel)super.model).getPermissions());
        if (view != null)
        {
            int i;
            if (flag2)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            view.setVisibility(i);
        }
        if (flag2) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj2;
        LocationEditSegment locationeditsegment;
        locationeditsegment = (LocationEditSegment)super.view;
        obj2 = getLocation();
        Object obj;
        boolean flag;
        if (obj2 == null)
        {
            flag = true;
        } else
        if (TextUtils.isEmpty(((EventLocation) (obj2)).name) && ((EventLocation) (obj2)).address == null && ((EventLocation) (obj2)).geo == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = locationeditsegment.tile;
        ((TextTileView) (obj)).setPrimaryText(new CharSequence[] {
            ((TextTileView) (obj)).getResources().getString(0x7f1301b5, new Object[0])
        });
        ((TextTileView) (obj)).setSecondaryText(new CharSequence[] {
            null
        }).setPrimaryTextColor(locationeditsegment.getResources().getColor(0x7f0d00ab));
        obj = locationeditsegment.imagePreview;
        if (obj != null)
        {
            ((View) (obj)).setVisibility(8);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        locationeditsegment.tile.setPrimaryTextColor(locationeditsegment.getResources().getColor(0x7f0d0309));
        TextTileView texttileview = locationeditsegment.tile;
        String s2 = ((EventLocation) (obj2)).name;
        Object obj1 = ((EventLocation) (obj2)).address;
        boolean flag1;
        if (obj1 == null)
        {
            obj1 = null;
        } else
        {
            obj1 = ((Address) (obj1)).formattedAddress;
        }
        texttileview.setTextAdaptively(s2, ((CharSequence) (obj1)));
        obj1 = locationeditsegment.imagePreview;
        if (!TextUtils.isEmpty(((EventLocation) (obj2)).mapsClusterId) || ((EventLocation) (obj2)).geo != null || ((EventLocation) (obj2)).address != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (obj1 != null)
        {
            String s1;
            GeoCoordinates geocoordinates;
            if (!flag1)
            {
                j = 8;
            }
            ((View) (obj1)).setVisibility(j);
        }
        if (flag1)
        {
            obj1 = ((EventLocation) (obj2)).address;
            if (obj1 != null && !TextUtils.isEmpty(((Address) (obj1)).formattedAddress))
            {
                obj1 = ((Address) (obj1)).formattedAddress;
            } else
            {
                obj1 = null;
            }
            s1 = ((EventLocation) (obj2)).mapsClusterId;
            geocoordinates = ((EventLocation) (obj2)).geo;
            if (geocoordinates != null)
            {
                obj2 = Double.valueOf(geocoordinates.latitude).toString();
                s = Double.valueOf(geocoordinates.longitude).toString();
            } else
            {
                obj2 = null;
            }
            locationeditsegment.drawable.setDecodeDimensions(locationeditsegment.previewImageWidth, locationeditsegment.previewImageHeight);
            obj1 = locationeditsegment.requestKeyFactory.createRequestKey(s1, ((String) (obj2)), s, ((String) (obj1)), locationeditsegment.previewImageWidth, locationeditsegment.previewImageHeight);
            if (obj1 instanceof PlacePageOrMapRequestKey)
            {
                locationeditsegment.drawable.bind((RequestKey)obj1);
                return;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

}
