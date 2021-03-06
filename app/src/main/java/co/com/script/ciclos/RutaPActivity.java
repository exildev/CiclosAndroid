package co.com.script.ciclos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;

import co.com.script.ciclos.helper.SimpleItemTouchHelperCallback;
import co.com.script.ciclos.models.Asignacion;

public class RutaPActivity extends AppCompatActivity implements ItemAdapter.OnStartDragListener {

    private static final int MAP_SAVE = 1;
    ItemAdapter itemAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta_p);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.content_ruta_p);
        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        itemAdapter = new ItemAdapter(getApplicationContext(), this);
        recyclerView.setAdapter(itemAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(itemAdapter, this);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        loadItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ruta_p, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_map) {
            Intent intent = new Intent(this, MapRutaActivity.class);
            intent.putExtras(getIntent());
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ItemAdapter.itemList.clear();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("result", requestCode + "");
        if (requestCode == MAP_SAVE && resultCode == RESULT_OK) {
            int status = data.getIntExtra("status", -1);
            String response = data.getStringExtra("response");
            if (status == 200) {
                Snackbar.make(findViewById(R.id.content_ruta_p).findViewById(R.id.container), R.string.gps_success, 800).show();
                page = 1;
                ItemAdapter.itemList.clear();
                loadItems();
            }
            Log.i("status", status + "");
            Log.i("response", response);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadItems() {
        String serviceUrl = getString(R.string.list_asignaciones_piscinero, page);
        String url = getString(R.string.url, serviceUrl);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray object_list = response.getJSONArray("object_list");
                    int count = response.getInt("count");
                    if (response.has("next")) {
                        page = response.getInt("next");
                    }
                    for (int i = 0; i < object_list.length(); i++) {
                        JSONObject campo = object_list.getJSONObject(i);
                        Log.i("campo", campo.toString());
                        int id = campo.getInt("id");
                        int piscina_id = campo.getInt("campana");
                        String nombre = campo.getString("nombreP");
                        String cliente = campo.getString("nombreT");
                        int orden = campo.getInt("orden");
                        int casa = campo.getInt("tienda");
                        int supervisor = campo.getInt("supervisor_id");

                        boolean haveGPS = !campo.get("latitud").equals(null) && !campo.get("longitud").equals(null);

                        ItemAdapter.itemList.add((itemAdapter.getItemCount()), new Asignacion(id, piscina_id, nombre, cliente, orden, haveGPS, casa, supervisor));
                        itemAdapter.notifyDataSetChanged();
                    }
                    if (itemAdapter.getItemCount() < count) {
                        loadItems();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //infiniteListView.stopLoading();
                CardView container = (CardView) findViewById(R.id.error_container);
                VolleySingleton.manageError(RutaPActivity.this, error, container, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("rety", "rety");
                    }
                });
                Log.e("Activities", error.toString());
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    void saveOrden(int asignacion, final int orden) {
        final MaterialDialog loading = new MaterialDialog.Builder(this)
                .title(R.string.loadin_modal_title)
                .content(R.string.loadin_modal_content)
                .progress(true, 0)
                .show();

        String serviceUrl = getString(R.string.asigancion_form_orden, asignacion);
        String url = getString(R.string.url, serviceUrl);
        StringRequest request = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ItemAdapter.itemList.clear();
                        itemAdapter.notifyDataSetChanged();
                        page = 1;
                        loadItems();
                        loading.dismiss();
                        Snackbar.make(findViewById(R.id.toolbar), R.string.success_response, 800).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("login", error.toString());
                        loading.dismiss();
                        Snackbar.make(findViewById(R.id.toolbar), R.string.error_response, 800).show();
                        ItemAdapter.itemList.clear();
                        page = 1;
                        loadItems();
                        String[] errStrings = new String(error.networkResponse.data).split("\n");
                        for (String string : errStrings){
                            Log.i("web", string);
                        }
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    JSONObject obj = new JSONObject();
                    obj.put("orden", orden);
                    return obj == null ? null : obj.toString().getBytes("utf-8");
                } catch (UnsupportedEncodingException | JSONException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", "", "utf-8");
                    return null;
                }
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    void setGPS(int position) {
        Asignacion asignacion = ItemAdapter.itemList.get(position);
        Log.i("asignacion", asignacion.toString());
        if (!asignacion.isHaveGPS()) {
            Intent intent = new Intent(this, MapCasaActivity.class);
            intent.putExtra("casa", asignacion.getTienda());
            startActivityForResult(intent, MAP_SAVE);
        }
    }

}
