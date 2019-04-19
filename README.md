# battery_optimization

A simple plugin to check if the app is ignoring battery optimizations (Android Only)

## Getting Started

### Check if app is ignoring battery optimization

```dart
BatteryOptimization.isIgnoringBatteryOptimizations().then((onValue) {
    setState(() {
        if (onValue) {
            // Igonring Battery Optimization
        } else {
            // App is under battery optimization
        }
    });
});
```

### Take user to battery optimization settings
this opens the battery optimization settings.  Apps can be placed on the whitelist through the settings UI

```dart
BatteryOptimization.openBatteryOptimizationSettings();
```