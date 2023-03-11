
# Java PrintScreen ™

![Logo](src/fgroupindonesia/images/logo.png)

`is a very lightweight yet powerful, for everyone who wants screencapture on desktop.

## Features :
You could customize almost everything such as:
- the logo (tray)
- the location (saving path)
- the format of image
- the cleanup (cache)
- the copy-to-clipboard (automatically)

And yes please take note....

| Requirement   | Details       | Stability 				|
| ------------- |:-------------:| :-----------------------:	|
| JDK	       	| 1.8 			| :heavy_check_mark: 		|
| OS      		| Windows      	| :heavy_check_mark: 		|
| Size			| 52 kb      	| :heavy_heart_exclamation: |

We made this for java anthusiast... so use it accordingly! But if you need more customization, feel free to post into the discussion [*issues* sections!](https://github.com/fgroupindonesia/printscreen/issues)



## How to Use:

1) Grab the [last release jar](https://github.com/fgroupindonesia/printscreen/releases) 

2) Run the following code:

```
import fgroupindonesia.printscreen.engine.*;

PrintScreen psEngine = new PrintScreen();

// 1st if you need to screencapture with snipping
psEngine.takeCapture();

// or 2nd if you need to screencapture without snipping
psEngine.setSnippingMode(false);
psEngine.takeCapture();
```

Incase you need some other functions, try each of these cases :

### Case 01: Customizing The Tray Logo
to enable the tray we have the default logo, but if you need yours here you could customize:

```
// here if you want to customize the icon
psEngine.setLogo(new File("somefile.png"));

// but also if you dont want to show the icon on tray
psEngine.setLogoVisibility(false);
```

### Case 02: Choosing another path
to help the printscreen save to some other directory you could use these steps:

```
// this will automatically save to that path
psEngine.setDirectoryPath("D:\\somedir");

// or you could also make asking-directory-mode for saving
// as default is false
psEngine.setAlwaysAskDirectory(true);

```

Why should we use *alwaysAskDirectory?* Because some users love to save it into different directory each time printscreen's taken. But where is the default path if nobody change the path?
Here it goes...

``` 
C://users/AppData/Roaming/fgroupindonesia/printscreen/
```

### Case 03: Changing format of image
Some cases need to switch between JPG or PNG or even grayscale for better purposes here we are :

```
// you could use : 
// PSImageFormat.PNG or 
// PSImageFormat.GREYSCALE or
// PSImageFormat.JPG

psEngine.setImageFormat(PSImageFormat.JPG);

```

as FYI the default is the PNG format.

### Case 04: Snipping Mode

As default the snipping mode is always to be true. Otherwise, you could switch it to take entire screen for every printscreen's task .
``` 
// this will make the app work for taking screenshot entire screen instead of snipping
psEngine.setSnippingMode(false);
```

### Case 05: Cleanup Cache
you could trace the snipping history everytime screenshot was made. As default the cache is never cleaned, thus to see the details right click the tray and choose history... And the location is located at...

``` 
C://users/AppData/Roaming/fgroupindonesia/printscreen/
```

to clean up those files, you could easily...
``` 
psEngine.setAutoCleanup(true);
```

### Case 06: Copy to Clipboard (automatically)
as we all know, this feature is *True* by default. Incase you want to turn it off just use :

```
psEngine.setCopyToClipboard(false);
```

Okay, you may try Pressing (CTRL + V) after taking screenshot to see the picture!


### Case 07: Disable the opening saved directory 
FYI, this feature is *True* by default. Incase you want to turn it off just use :

```
psEngine.setOpenSavedDirectoryFeature(false);
```



### Want to explore the real examples?
Sure here we go:
- [*Example 1*](src/fgroupindonesia/printscreen/examples/Example1.java) : run the default quick run!
- [*Example 2*](src/fgroupindonesia/printscreen/examples/Example2.java) : run the entirescreen screenshot taking & copy-paste from clipboard
- [*Example 3*](src/fgroupindonesia/printscreen/examples/Example3.java) : run the snipping screenshot taking & ask the user where to save
- [*Example 4*](src/fgroupindonesia/printscreen/examples/Example4.java) : run the snipping screenshot taking & save to custom path (programatically)
- [*Example 5*](src/fgroupindonesia/printscreen/examples/Example5.java) : run the tray only until the user clicking the menu for screenshot


### Thanks!

That's it...! Feel free to use this into your projects.... 
and if you want to integrate other similar functions for your Software, grab our Leading Experts on social medias for better future with the best technology:

- [LinkedIn](https://id.linkedin.com/in/gumuruh)
- [Projects](https://projects.co.id/public/browse_users/view/250512/gumuruh)

If you want to contribute in different languages / platform. 
Please fork it out and let me know for it...

Thanks everyone! :raising_hand_man:


© 2023 - FGroupIndonesia.