package com.iboism.permissionspager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Brad on 12/17/2016.
 */

public class PermissionsPager {
    /**
     *
     * @param permissions Permission strings acquired from "Manifest.permissions"
     * @param rationales Rationale strings for the corresponding permissions, each permission needs at least a blank String
     * @param activity The Activity that will handle the response of the PagerActivity, MUST implement OnActivityResult in order to receive the results
     */
    public static void showPermissionsPager(String[] permissions, String[] rationales, Activity activity){
        Intent permissionIntent = new Intent(activity, PagerActivity.class);
        permissionIntent.putExtra(PagerActivity.PERMISSION_ARRAY_INTENT_KEY, permissions);
        permissionIntent.putExtra(PagerActivity.RATIONALE_ARRAY_INTENT_KEY, rationales);
        activity.startActivityForResult(permissionIntent,1738);
    }
}
