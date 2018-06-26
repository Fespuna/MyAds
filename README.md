# MyAds

This is a "private" project so will not work for you at all
------------------

*At the project build.gradle file*

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
      
		}
	}
```

*At the inner build.gradle file*

```
  dependencies {
  
	        implementation 'com.github.Fespuna:MyAds:3.0'
		
		}
```

How it works?

1) Load the ad
```
   MyAds.Load(this);
```
2) Call the ad
```

    Button btn = (Button)findViewById(R.id.button);

    btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyAds.ShowInterstitial(MainActivity.this);

            }
     });
```
        
3) You also can call this method to call and show at the same time.
```
MyAds.LoadAndShowSplash(this);
```
