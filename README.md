# ZiiOZ Android App

Jetpack Compose app with drawer + bottom bar.
The Feed screen calls the local API at 127.0.0.1:3000 (via adb reverse).

Local dev loop
1) Start API:  node C:\ziioz-api\server.js
2) Bridge:     use adb reverse to map phone:3000 -> PC:3000
3) Run app in Android Studio and tap "Load /feed".

ADB quick ref
$adb = "$env:USERPROFILE\AppData\Local\Android\Sdk\platform-tools\adb.exe"
$dev = "192.168.1.116:41443"   # replace with your device id
& $adb -s $dev reverse --remove-all
& $adb -s $dev reverse tcp:3000 tcp:3000
