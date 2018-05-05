    package com.example.pbp22.dogbreed;

    import android.app.LoaderManager;
    import android.content.Loader;
    import android.os.AsyncTask;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.widget.ListView;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.net.URL;
    import java.net.URLConnection;
    import java.util.ArrayList;
    import java.util.List;

    public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Product>> {

        ArrayList<Product> arrayList;
        ListView list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getLoaderManager().initLoader(1, null, this);
            arrayList = new ArrayList<>();
            list = (ListView) findViewById(R.id.list);
        }

        @Override
        public Loader<List<Product>> onCreateLoader(int i, Bundle bundle) {
            return new DogLoader(this);
        }

        @Override
        public void onLoadFinished(Loader<List<Product>> loader, List<Product> products) {

            if (products != null && !products.isEmpty()) {
                Log.v("DogXXX", products.toString());
                CustomAdapter adapter = new CustomAdapter(
                        getApplicationContext(), R.layout.list_row, products
                );
                list.setAdapter(adapter);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<Product>> loader) {

        }
    }
