# PermissionsPager
A simple solution for requesting multiple permissions at once, namely during onboarding. 

### Usage
To launch the PermissionPager:
```java 
PermissionsPager.buildPermissionsPager(this)
  .with(Manifest.permission.ACCESS_FINE_LOCATION, "We need to see where u at")
  .and(Manifest.permission.WRITE_EXTERNAL_STORAGE, "Maybe this is a file explorer")
  .and(Manifest.permission.READ_CALENDAR, "Or maybe this is a calendar app")
  .show();
```
Don't forget, to receive the results of the permission requests, you must implement onActivityResult in whatever activity you used for the buildPermissionPager() parameter. 
```java
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK) {
      String[] denied = data.getStringArrayExtra(PermissionsPager.DENIED_ARRAY_INTENT_KEY);
      String[] granted = data.getStringArrayExtra(PermissionsPager.GRANTED_ARRAY_INTENT_KEY);
    }
}
```
### Current Issues/TODO
- Support for custom pager layout
- Handling permission groups
- Backwards compatibility with pre-marshmallow
- Package as Android library (duh)


