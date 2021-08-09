package com.zerotechy.pregnancyfitness;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    String TAG="TAG";
    RecyclerView recyclerView;
    List<Tips> tips=new ArrayList<>();
    ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View v=inflater.inflate(R.layout.fragment_home,container,false);
         progressBar=(ProgressBar)v.findViewById(R.id.progress_circular);
         recyclerView=(RecyclerView)v.findViewById(R.id.recyclerview);




        // Inflate the layout for this fragment
        DataExtraction(utils.getURL());


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        recyclerView.setAdapter(new TipsAdapter(tips));
        Log.d(TAG,String.valueOf(tips.size()));





        return v;
    }

    public void DataExtraction(String url){
        Thread thread=new Thread(){
            @Override
            public void run() {
                RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
                JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i=0;i<response.length();i++){
                            try {
                                Tips t=new Tips();
                                Tips a=new Tips();
                                JSONObject objectData=response.getJSONObject(i);
                                JSONObject titleobject=objectData.getJSONObject("title");

                                t.setTitle(titleobject.getString("rendered"));


                                JSONObject contentobject=objectData.getJSONObject("content");
                                t.setContent(contentobject.getString("rendered"));

                                t.setThumb(objectData.getString("jetpack_featured_media_url"));
                                t.setCategory(objectData.getString("date"));

//                        t.setCategory((String) obnjectData.getJSONArray("categories").get(0).toString());
//                        Toast.makeText(getActivity().getApplicationContext(), t.getCategory(), Toast.LENGTH_SHORT).show();


                                Log.d("Date",response.toString());

                                tips.add(t);




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            recyclerView.setAdapter(new TipsAdapter(tips));
                            progressBar.setVisibility(View.GONE);
                            queue.getCache().clear();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



                queue.add(request);


            }
        };

        thread.start();





    }


}