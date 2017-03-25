# Manual configuration handling

This sample describes how the developer can manually handle orientation or other screen changes so the entire view hierarchy is not re-created.

## Examples
| Example | Description |
| ------------- | ------------- |
| MainActivity | No configuration change handled. In this case, the VideoView is recreated and the video starts the playback from the beginning (undesired behavior). |
| SecondActivity | Manifest specifies that this Activity will handle the configuration change on its own, thus the view hierarchy is not recreated (onCreated is not called on every orientation change). Therefore, the video continues to play on every orientation change. |