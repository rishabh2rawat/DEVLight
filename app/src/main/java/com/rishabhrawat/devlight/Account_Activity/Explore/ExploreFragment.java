package com.rishabhrawat.devlight.Account_Activity.Explore;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.PreloadTarget;
import com.rishabhrawat.devlight.Adapters.explore_card_Adapter;
import com.rishabhrawat.devlight.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.OkHttpClient;
import okhttp3.Response;

import static android.R.string.ok;


public class ExploreFragment extends Fragment {

    private List<exploredata> exploredataList;
    private explore_card_Adapter adapter;
    private LinearLayoutManager llm;


    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance() {
        ExploreFragment fragment = new ExploreFragment();
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


        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.Explore_RecyclerView);


        rv.setHasFixedSize(true);

        llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        exploredataList = new ArrayList<>();

        load_data_from_server(0);

        adapter = new explore_card_Adapter(exploredataList, getActivity().getBaseContext());
        rv.setAdapter(adapter);




       /* rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (llm.findLastCompletelyVisibleItemPosition()==exploredataList.size()-1)
                {
System.out.println("loadin on scroll view item*******************************************************************************");
                    load_data_from_server(exploredataList.get(exploredataList.size()-1).getId());

                }
            }
        });
        */
        return rootView;


    }

    private void load_data_from_server(final int id) {
        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {


            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("http://devlight.schoolstein.com/explore.php?id=" + id)
                        .build();


                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        exploredata exploredata = new exploredata(object.getInt("id"), object.getString("projectImage"), object.getString("projectHeader"), object.getString("projectText"), object.getString("devname"), object.getString("devemail"));

                        exploredataList.add(exploredata);

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
        task.execute(id);
    }
}
