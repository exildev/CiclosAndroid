package co.com.script.ciclos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.softw4re.views.InfiniteListAdapter;
import com.softw4re.views.InfiniteListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.com.script.ciclos.models.Campania;

public class ListCampaniaActivity extends AppCompatActivity {

    private int page;
    private String search = "";
    private ArrayList<Campania> itemList;
    private InfiniteListView infiniteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_campania);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setInfiniteList();
    }

    void setInfiniteList() {
        infiniteListView = (InfiniteListView) findViewById(R.id.infiniteListCampania);

        itemList = new ArrayList<>();
        InfiniteListAdapter adapter = new InfiniteListAdapter<Campania>(this, R.layout.cliente, itemList) {

            @Override
            public void onNewLoadRequired() {
                getCampanias();
            }

            @Override
            public void onRefresh() {
                infiniteListView.clearList();
                page = 1;
                getCampanias();
            }

            @Override
            public void onItemClick(int i) {
                int id = itemList.get(i).getId();
                //launchCliente(id);
            }

            @Override
            public void onItemLongClick(int i) {
                Toast.makeText(getContext(), "long click", Toast.LENGTH_LONG).show();
            }

            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {

                ClienteFragment.ViewHolder holder;

                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.cliente, parent, false);
                    holder = new ClienteFragment.ViewHolder();
                    holder.title = (TextView) convertView.findViewById(R.id.cliente);
                    holder.subtitle = (TextView) convertView.findViewById(R.id.cliente_subtitle);

                    convertView.setTag(holder);

                } else {
                    holder = (ClienteFragment.ViewHolder) convertView.getTag();
                }

                Campania cliente = itemList.get(position);
                if (cliente != null) {
                    holder.title.setText(cliente.getNombre());
                    holder.subtitle.setText(cliente.getDescripcion());
                }

                return convertView;
            }
        };

        infiniteListView.setAdapter(adapter);
        getCampanias();
    }

    private void getCampanias() {
        infiniteListView.startLoading();
        int id = getIntent().getIntExtra("id", -1);
        String serviceUrl = getString(R.string.get_campanias, id);
        String url = getString(R.string.url, serviceUrl);

        JsonObjectRequest clientesRequest =
                new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            infiniteListView.stopLoading();
                            JSONArray object_list = response.getJSONArray("object_list");
                            int count = response.getInt("count");
                            if (response.has("next")) {
                                page = response.getInt("next");
                            }
                            for (int i = 0; i < object_list.length(); i++) {
                                JSONObject campo = object_list.getJSONObject(i);
                                int id = campo.getInt("id");
                                String name = campo.getString("nombre");
                                String descripcion = campo.getString("descripcion");
                                infiniteListView.addNewItem(
                                        new Campania(id, name, descripcion));
                            }
                            if (itemList.size() == count) {
                                infiniteListView.hasMore(false);
                            } else {
                                infiniteListView.hasMore(true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        infiniteListView.stopLoading();
                        CardView container = (CardView) findViewById(R.id.error_container);
                        VolleySingleton.manageError(ListCampaniaActivity.this, error, container, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.i("rety", "rety");
                            }
                        });
                        Log.e("Activities", error.toString());
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(clientesRequest);
    }
}
