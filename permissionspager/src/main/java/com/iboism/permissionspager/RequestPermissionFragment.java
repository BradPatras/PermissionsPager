package com.iboism.permissionspager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestPermissionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestPermissionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestPermissionFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final String ARG_PERMISSION = "param1";
    private static final String ARG_RATIONALE = "param2";

    private String mPermission;
    private String mRationale;
    private OnFragmentInteractionListener mListener;

    public RequestPermissionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PERMISSION_GRANTED) {
            this.mListener.permissionGranted(permissions[0]);
        } else {
            this.mListener.permissionDenied(permissions[0]);
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param permission Permission to be requested.
     * @return A new instance of fragment RequestPermissionFragment.
     */
    public static RequestPermissionFragment newInstance(String permission, String rationale) {
        RequestPermissionFragment fragment = new RequestPermissionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PERMISSION, permission);
        args.putString(ARG_RATIONALE, rationale);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPermission = getArguments().getString(ARG_PERMISSION);
            mRationale = getArguments().getString(ARG_RATIONALE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_request_permission, container, false);
        ((TextView) rootView.findViewById(R.id.rationaleTextView)).setText(this.mRationale);
        rootView.findViewById(R.id.requestAccessLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRequestButtonPressed();
            }
        });
        return rootView;
    }

    public void onRequestButtonPressed() {
        String[] p = {mPermission};
        this.requestPermissions(p, 0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void permissionGranted(String permission);
        void permissionDenied(String permission);

    }
}
