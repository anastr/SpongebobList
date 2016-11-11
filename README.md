# SpongebobList
Fun RecyclerView with Spongebob character, Spongebob move his Hand and Eyes as ScrollBar for Android.<br>
you can use this list for children Applications or for Games... you know.

`minSdkVersion=11`

Library Size ~ 220 Kb.

[![API](https://img.shields.io/badge/API-+11-red.svg?style=flat)](#)
[![Bintray](https://img.shields.io/bintray/v/anastr/maven/SpongebobList.svg)](https://bintray.com/anastr/maven/SpongebobList)

<img src="/images/spongebob_list.gif" width="40%" />

### Requirement
* SDK build-tools 24 , download it from SDK Manager.

## Download

**add this line to** `build.gradle`

```gradle

dependencies {
	    compile 'com.github.anastr:spongeboblistview:1.0.0'
}

```

for **maven**

```maven
<dependency>
  <groupId>com.github.anastr</groupId>
  <artifactId>spongeboblistview</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

## Usage
add Speedometer to your `Layout.xml`.<br>
```xml

<com.github.anastr.spongeboblistview.SpongebobList
        android:id="@+id/spongebob_list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

```

use `getRecyclerView()` to get RecyclerView, and add your adapter:
```java
SpongebobList spongeList = (SpongebobList) findViewById(R.id.spongebob_list);

spongeList.getRecyclerView().setHasFixedSize(true);
LinearLayoutManager llm = new LinearLayoutManager(this);
spongeList.getRecyclerView().setLayoutManager(llm);
spongeList.getRecyclerView().setAdapter(yourAdapter);
```

# Attributes :
<br>
======================
Name | Function
--- | ---
spongebob_width | the width of spongebob character
hand_width | the width of spongebob's hand
scroll_line_width | the width of scroll bar
scroll_line_color | the color of scroll bar

# LICENSE
```

Copyright 2016 Anas ALtair

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
