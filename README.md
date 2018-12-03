# scaling-recyclerview


__Demo__


![](20181203_182931.gif)


__``scaling-recyclerview ``` developed with Kotlin and AndroidX.__

__Note:__ for use it you most migrate your project to [AndroidX](https://developer.android.com/jetpack/androidx/migrate).

__you can add snap feature to your S.R.V. with setSnap(true)__


---

## How to add?

First of all change ```build.gradle(project)```, add below maven
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
And on ```build.gradle(app)```, add below line
```
  implementation 'com.github.rvhamed:scaling-recyclerview:v1.0.0-beta.1'
```
__FINISH ;)__

---

## How to use?

Add ```ScalinRecyclerView``` to your ```xml``` file
```
 <com.hamedrahimvand.scalingrecyclerview.ScalingRecyclerView
        android:id="@+id/src_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

```

After that on your ```java/kotlin class``` 

__Note:__ this sample is kotlin.

```
   //find and initialize
   val srcMain = findViewById<ScalingRecyclerView>(R.id.src_main)
        
```
___How to set adapter? How to create ScalingRecyclerModel?___

With below code you can create an object of your item with ```ScalingRecyclerModel```. Notice these fields are optional.
```
    ScalingRecyclerModel.Builder()
                .firstTitle("Hamed" /*item primary text*/)  
                .secondTitle("120 Score" /*item secondry text*/)
                .imageUrl("http://myImageUrl.com/.../hamed.jpg"  /*item image url*/) 
                .visibility(View.VISIBLE)
                .build()

```
Create ```ArrayList``` of ```ScalingRecyclerModel``` and setAdapter

```
   srcMain.setAdapter(srmList)

```
At least  you can add snap feature with
```
srcMain.setSnap(true)
```

__Note:__ Remember for use Glide to load images you need to set Internet Permission in manifest
```
<uses-permission android:name="android.permission.INTERNET"/>

```

cool It was all that ;)

__You can Fork it and Development it :)__

---

## Methods

- ```srcMain.setAdapter(srmList)```
- ```srcMain.setSnap(boolean)```
- ```srcMain.setLayoutOrientation(RecyclerView.VERTICAL/HORIZONTAL)```
- ```srcMain.addItem(srm)```
- ```srcMain.addItem(srmList)```
- ```srcMain.setFirstTitleTextAppearance(styleResId)```
- ```srcMain.setSecondTitleTextAppearance(styleResId)```
- ```srcMain.setFirstLayoutBackground(resId)```
- ```srcMain.setSecondLayoutBackground(resId)```
- ```srcMain.setThirdLayoutBackground(resId)```




        
