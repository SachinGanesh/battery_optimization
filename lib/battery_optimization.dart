import 'dart:async';

import 'package:flutter/services.dart';

class BatteryOptimization {
  static const MethodChannel _channel =
      const MethodChannel('battery_optimization');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<bool?> isIgnoringBatteryOptimizations() async {
    final reading = await _channel.invokeMethod('isIgnoringBatteryOptimizations');
    return reading;
  }

  static Future<String?> openBatteryOptimizationSettings() async {
    final reading = await _channel.invokeMethod('openBatteryOptimizationSettings');
    return reading;
  }
}
