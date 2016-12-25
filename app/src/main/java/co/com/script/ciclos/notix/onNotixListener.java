package co.com.script.ciclos.notix;

import org.json.JSONObject;

/**
 * Created by pico on 15/11/2016.
 */

public interface onNotixListener {

    public void onNotix(JSONObject data);

    public void onVisited(JSONObject data);
}
