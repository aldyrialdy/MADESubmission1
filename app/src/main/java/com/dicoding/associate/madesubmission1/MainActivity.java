package com.dicoding.associate.madesubmission1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String TAG = MainActivity.class.getSimpleName();
    EditText editSearch;
    Button btnSearch;


    public final static String API_KEY = "fd88576790824d10424d8af542f423ed";
    public final static String baseUrlImage = "http://image.tmdb.org/t/p/w185";
    private final static String lang = "";
    private String query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editSearch = findViewById(R.id.search);
        btnSearch = findViewById(R.id.btn_cari);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        if(editSearch.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please write your query search", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            query = editSearch.getText().toString();
        }

        Bundle bundle = new Bundle();
        bundle.putString("queryget", query);
        ListMovie fragobj = new ListMovie();
        fragobj.setArguments(bundle);
        replaceFragment(fragobj);
    }
    /**
     * for replace fragment
     *
     * @param fragment {@link Fragment}
     */
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_list, fragment)
                .commit();
    }
}
