package com.rishabhrawat.devlight.Account_Activity.Dashboard;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.rishabhrawat.devlight.Adapters.dashboard_card_Adapter;

import com.rishabhrawat.devlight.R;
import com.rishabhrawat.devlight.addprojectActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Response;


public class DashboardFragment extends Fragment {

    private static final String TAG = "DashboardFragment";

    private List<Dashboarddata> dashboarddataList;
    private dashboard_card_Adapter adapter;
    private GridLayoutManager glm;

    String name;
    String email;
    Uri photoUrl;
    String uid;

    FloatingActionButton addbtn;


    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        Log.d(TAG, "onCreateView: starting rootview");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
            photoUrl = user.getPhotoUrl();
            uid = user.getUid();

        }

        View rootview = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView rv = (RecyclerView) rootview.findViewById(R.id.dashboard_recyclerview);
        rv.hasFixedSize();

        dashboarddataList = new ArrayList<>();
        glm = new GridLayoutManager(getActivity(), 2);

        rv.setLayoutManager(glm);


        load_data_from_server(uid);

        rv.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new dashboard_card_Adapter(dashboarddataList, getActivity().getBaseContext());
        rv.setAdapter(adapter);


        /*floating action btn */
        addbtn = (FloatingActionButton) rootview.findViewById(R.id.add_Project);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity().getBaseContext(), addprojectActivity.class);
                startActivity(intent);
            }
        });

        return rootview;
    }

    //load data from server

    public void load_data_from_server(final String uid) {

        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {

                Log.d(TAG, "doInBackground: requesting server");

                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://devlight.schoolstein.com/dash.php?Authid=" + uid)
                        .build();


                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());


                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        Dashboarddata dashboarddata = new Dashboarddata(object.getInt("id"), object.getString("projectImage"), object.getString("projectHeader"), object.getString("devname"), object.getString("Authid"), object.getInt("count"));

                        dashboarddataList.add(dashboarddata);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };
        task.execute(uid);
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
