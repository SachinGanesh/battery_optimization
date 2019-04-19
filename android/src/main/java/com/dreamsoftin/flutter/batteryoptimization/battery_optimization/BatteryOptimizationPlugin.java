package com.dreamsoftin.flutter.batteryoptimization.battery_optimization;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import android.os.PowerManager;
import android.content.Intent;
import static android.content.Context.POWER_SERVICE;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.net.Uri;

/** BatteryOptimizationPlugin */
public class BatteryOptimizationPlugin implements MethodCallHandler {
  /** Plugin registration. */
  private PowerManager mPowerManager;
  private Registrar mRegistrar;
  public static void registerWith(Registrar registrar) {

    if (registrar.activity() == null) {
      return;
    }
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "battery_optimization");
    channel.setMethodCallHandler(new BatteryOptimizationPlugin(registrar));
  }

  BatteryOptimizationPlugin(Registrar registrar) {
    mRegistrar = registrar;
  }
  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } 
    else if (call.method.equals("isIgnoringBatteryOptimizations")) {
      boolean reading = isIgnoringBatteryOptimizations();
      result.success(reading);
      return;
    }
    else if (call.method.equals("openBatteryOptimizationSettings")) {
      String reading = openBatteryOptimizationSettings();
      result.success(reading);
      return;
    }
    else {
      result.notImplemented();
    }
  }

  boolean isIgnoringBatteryOptimizations() {
    Intent intent = new Intent();
    String packageName = mRegistrar.activeContext().getPackageName();
    mPowerManager = (PowerManager) (mRegistrar.activeContext().getSystemService(POWER_SERVICE));

    // ---- If ignore go to settings, else request ----

    if(mPowerManager.isIgnoringBatteryOptimizations(packageName)) {
      return true;
    } else {
      return false;
    }
  }

  String openBatteryOptimizationSettings() {
    Intent intent = new Intent();
    intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
    mRegistrar.activeContext().startActivity(intent);
    return "Success";
  }
}
