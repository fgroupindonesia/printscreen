
# Java PrintScreen (tm)

`is a very lightweight yet powerful, for everyone who wants screencapture on desktop.

## Feature :
You could customize almost everything such as:
- the logo (tray)
- the location (saving path)
- the format of image
- the cleanup (cache)
- the copy-to-clipboard (automatically)

We made this for java anthusiast... so use it accordingly! But if you need more customization, feel free to post into the discussion *issues* sections!

https://github.com/fgroupindonesia/printscreen/issues

## How to Use:

1) Grab the last release jar 
https://github.com/fgroupindonesia/printscreen/releases

2) Run the following code:

```
import fgroupindonesia.printscreen.*;

PrintScreen prEngine = new PrintScreen();
prEngine.start();

// if you need to screencapture whole without marking
prEngine.takeCapture();

// if you need to screencapture with snipping
prEngine.setSnippingMode(true);
prEngine.takeCapture();
```

Incase you need some other functions, try these :

### Case 01: Customizing The Tray Logo
to enable the tray we have the default logo, but if you need yours here you could customize:

```
prEngine.setLogo(new File("somefile.png"));
prEngine.setLogoVisibility(false);
```

### Case 02: Choosing another path
to help the printscreen save to some other directory you could use these steps:

```
// this will automatically save to that path
prEngine.setDirectoryPath("D:\\somedir");

// or you could also make asking-directory-mode for saving
prEngine.setAlwaysAskDirectory(true);

```

Why should we use alwaysAskDirectory? Because some users love to save it into different directory each time printscreen's taken.

But where is the default path if nobody change the path?
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
// PSImageFormat.PNG

prEngine.setImageFormat(PSImageFormat.JPG);

```

### Case 04: Snipping Mode

As default the snipping mode is always to be true. Otherwise, you could switch it to take entire screen for every printscreen's task .
``` 
// this will make the app work for taking screenshot entire screen
prEngine.setSnippingMode(false);
```

### Case 05: Cleanup Cache
you could trace the snipping history everytime screenshot was made. As default the cache is never cleaned, thus to see the details right click the tray and choose history... And the location is located at...

``` 
C://users/AppData/Roaming/fgroupindonesia/printscreen/cache/
```

to clean up those files, you could easily...
``` 
prEngine.setAutoCleanup(true);
```