package co.com.script.ciclos.notix;

import org.json.JSONArray;
import org.json.JSONObject;


public interface AlarmListener {

    void onAlarm(JSONObject alarm);

    void onShowAlarm(JSONArray alarms);
}
