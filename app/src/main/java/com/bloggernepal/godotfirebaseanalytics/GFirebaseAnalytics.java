package com.bloggernepal.godotfirebaseanalytics;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.godotengine.godot.Dictionary;
import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

import java.util.HashSet;
import java.util.Set;

// Godot FirebaseAnalytics
public class GFirebaseAnalytics extends GodotPlugin {
    final String TAG = "Godot FirebaseAnalytics";

    private FirebaseAnalytics mFirebaseAnalytics;

    //    Signals
    static SignalInfo FIRST_TEST = new SignalInfo("on_first_test", Boolean.class);

    public GFirebaseAnalytics(Godot godot) {
        super(godot);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
    }

    @NonNull
    @Override
    public String getPluginName() {
        return "GodotFirebaseAnalytics";
    }

    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals() {
        Set<SignalInfo> pluginSignals = new HashSet<>();
        pluginSignals.add(FIRST_TEST);
        return pluginSignals;
    }

    @UsedByGodot
    public void firstTest() {
        Log.i(TAG, "First Test Called");
        emitSignal(FIRST_TEST.getName(), true);
    }

    @UsedByGodot
    public void showToast(String message, String duration) {
        Log.i(TAG, "Showing Toast, Duration " + duration);
        final int durationInt = duration.equals("short") ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;

        getActivity().runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), message, durationInt).show();
                    }
                });
    }

    @UsedByGodot
    public void initializeFirebaseManual(String applicationId, String apiKey, String projectId) {
        // we used this when we could not figure out how to use that google-services.json
        Log.e(TAG, "Firebaseapp initialication called");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId(applicationId)
                .setApiKey(apiKey)
                .setProjectId(projectId)
                .build();

        if (FirebaseApp.getApps(getActivity()).size() == 0) {
            FirebaseApp.initializeApp(getActivity(), options);
        } else {
            Log.e(TAG, FirebaseApp.getApps(getActivity()).toString());
        }
    }

    @UsedByGodot
    public void initializeFirebase() {
        Log.i(TAG, "initializing firebase");
        if (FirebaseApp.getApps(getActivity()).size() == 0) {
            Log.i(TAG, "initializing...");
            FirebaseApp.initializeApp(getActivity());
        } else {
            Log.i(TAG, "already initialized");
            Log.e(TAG, FirebaseApp.getApps(getActivity()).toString());
        }
    }

    @UsedByGodot
    public void logEvent(final String event, final Dictionary params) {
        // Code snippet taken from /DrMoriarty/godot-firebase-analytics
        if(params == null)
            mFirebaseAnalytics.logEvent(event, null);
        else {
            Bundle bundle = new Bundle();
            for(String key: params.get_keys()) {
                bundle.putString(key, params.get(key).toString());
            }
            mFirebaseAnalytics.logEvent(event, bundle);
        }
    }

}
