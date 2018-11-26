# BlackPearl 

[![Build Status](https://travis-ci.com/YahiaAngelo/BlackPearl.svg?branch=master)](https://github.com/YahiaAngelo/BlackPearl)

BlackPearl app gets you the latest roms and updates by the rom developer.

**[ScreenShots](https://imgur.com/a/OErKsuM)**

  - Get latest roms threads
  - Get notifications when dev. publishes new rom update
  - Get OTA updates of your current rom (Coming soon)

### Libraries

BlackPearl uses a number of open source projects to work properly:

* [MaterialDrawer](https://github.com/mikepenz/MaterialDrawer) - DrawerLayourt with material design.
* [recyclerview-animators](https://github.com/wasabeef/recyclerview-animators) - An Android Animation library which easily add itemanimator to RecyclerView items.
* [Android-Iconics](https://github.com/mikepenz/Android-Iconics) - Android-Iconics - Use any icon font, or vector (.svg) as drawable in your application. 
* [glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling.

And of course Dillinger itself is open source with a [public repository](https://github.com/YahiaAngelo/BlackPearl) on GitHub.

### Want to build your BlackPearl app?

Just clone the project and add your firebase's google-service.json into /app folder.

And make sure that your firebase database looks like this:

```json
{
  "hamza" : {
    "post0" : {
      "desc" : "desc",
      "img" : "img link",
      "link" : "thread link",
      "title" : "title"
    },
    "post1" : {
      "desc" : "desc",
      "img" : "img link",
      "link" : "thread link",
      "title" : "title"
    }
  }
}
```


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

### Todos

 - Add OTA 
 - Add Night Mode

License
----

MIT


**Free Software, Hell Yeah!**
