package com.example.techtask_unsplash.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.techtask_unsplash.R;
import com.example.techtask_unsplash.classes.DataAdapter;
import com.example.techtask_unsplash.classes.GetData;
import com.example.techtask_unsplash.classes.SingleObj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataAdapter.OnItemClickListener {

    RecyclerView recView;
    ArrayList<GetData> dataModelArrayList = new ArrayList<>();
    DataAdapter dataAdapter;

    String url = "https://api.unsplash.com/photos/?client_id=896d4f52c589547b2134bd75ed48742db637fa51810b49b607e37e46ab2c0043";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = findViewById(R.id.recyclerView);
        recView.setLayoutManager(new GridLayoutManager(this, 1));
        recView.setHasFixedSize(true);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int k = response.length() * 4;
                for (int i = 0; i < k; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        String urlImg = obj.getJSONObject("urls").getString("regular");
                        String imgCreator = obj.getJSONObject("user").getString("name");
                        String imgName = obj.getString("description");
                        if (imgName.equals("null"))
                            imgName = "The title is missing";
                        dataModelArrayList.add(new GetData(urlImg, imgCreator, imgName));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dataAdapter = new DataAdapter(MainActivity.this, dataModelArrayList);
                    recView.setAdapter(dataAdapter);
                    dataAdapter.setOnItemClickListener(MainActivity.this);

                }
            }
        }, error -> Toast.makeText(MainActivity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show());
        SingleObj.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onItemClick(int pos) {
        Intent fView = new Intent(this, SingleActivity.class);
        GetData clickedItem = dataModelArrayList.get(pos);
        fView.putExtra("imageUrl", clickedItem.getImageUrl());
        startActivity(fView);
    }
}