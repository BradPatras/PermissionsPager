package com.iboism.permissionspager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brad on 12/17/2016.
 */

public class PermissionsPager {
    public static final String GRANTED_ARRAY_INTENT_KEY = "kGrantedPermissionsArray";
    public static final String DENIED_ARRAY_INTENT_KEY = "kDeniedPermissionsArray";
    public static final String PERMISSION_ARRAY_INTENT_KEY = "kPermissionArray";
    public static final String RATIONALE_ARRAY_INTENT_KEY = "kRationaleArray";

    private ArrayList<String> permissions;
    private ArrayList<String> rationales;
    private Activity resultResponder;

    private PermissionsPager(Activity activity){
        this.permissions = new ArrayList<>();
        this.rationales = new ArrayList<>();
        this.resultResponder = activity;
    }

    /**
     * First step to build and show permission pager.
     * Append with .and or .with to add permission-rationale pairs
     * @param activity Activity used for launching the permission pager activity.
     *                 NOTE: This activity MUST implement onActivityResult in order to receive the results of the permission requests
     * @return
     */
    public static PermissionsPager buildPermissionsPager(Activity activity){
        PermissionsPager p = new PermissionsPager(activity);
        return p;
    }

    /**
     * Adds permission/rationale pair to the list of subjects.
     * Append with .show() to complete the construction
     * Synonymous to and()
     * @param permission Permission to be requested. Should be retrieved from Manifest.permission
     * @param rationale Rationale for requesting the permission
     * @return
     */
    public PermissionsPager with(String permission, String rationale){
        this.permissions.add(permission);
        this.rationales.add(rationale);
        return this;
    }

    /**
     * Adds permission/rationale pair to the list of subjects.
     * Append with .show() to complete the construction
     * Synonymous to with()
     * @param permission Permission to be requested. Should be retrieved from Manifest.permission
     * @param rationale Rationale for requesting the permission
     * @return
     */
    public PermissionsPager and(String permission, String rationale){
        return this.with(permission, rationale);
    }

    /**
     * Final step of construction. Must be preceded by and() or with()
     */
    public void show(){
        //Don't launch the activity if there aren't any permissions to request
        if (permissions.size() == 0) { return; }

        Intent permissionIntent = new Intent(resultResponder, PagerActivity.class);
        permissionIntent.putExtra(PERMISSION_ARRAY_INTENT_KEY, permissions.toArray(new String[0]));
        permissionIntent.putExtra(RATIONALE_ARRAY_INTENT_KEY, rationales.toArray(new String[0]));
        resultResponder.startActivityForResult(permissionIntent,1738);
    }

    /**
     *
     * @param permissions Permission strings acquired from "Manifest.permissions"
     * @param rationales Rationale strings for the corresponding permissions, each permission needs at least a blank String
     * @param activity The Activity that will handle the response of the PagerActivity, MUST implement OnActivityResult in order to receive the results
     */
    @Deprecated
    public static void showPermissionsPager(String[] permissions, String[] rationales, Activity activity){
        Intent permissionIntent = new Intent(activity, PagerActivity.class);
        permissionIntent.putExtra(PERMISSION_ARRAY_INTENT_KEY, permissions);
        permissionIntent.putExtra(RATIONALE_ARRAY_INTENT_KEY, rationales);
        activity.startActivityForResult(permissionIntent,1738);
    }
}
