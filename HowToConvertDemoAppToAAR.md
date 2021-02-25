Tue Feb 23 2021

# How to create .aar from working demo
<a href="https://developer.android.com/studio/projects/android-library">Developer docs:</a>

* Start with branch pre-aar which is the demo before being converted an AAR and demo.
* Create the .aar build area:<br>
    File->New Module->Android Library<br>
    app:searchwindow<br>
    com.kana_tutor.utils.searchwindow<br>
    This will create the initial build area and your initial gradle files.
* Make any changes necessary to your gradle files.  I know already that I use
ViewBinding so this needs to be added to the app/build.gradle in the lib directory
area:<br><pre><code>
    buildFeatures {
        viewBinding true
    }
</code> </pre><br>
Note that at the top of this file you will see "apply plugin: 'com.android.application'
".  That's what makes this a .aar directory.
*   Move the files specific to the .aar to the correct directories.  These will
include the attr file, layout, images and SearchWindow.kt.  It will require adding 
the res directories.   Use "git mv" if you want to maintain git history.  I put
a sh script named mvFiles.sh in the root to simplify this.
This is a Shell script.  If you're on windows it will at least let
you know what files you need to move.
* I changed the package name to com.kana_tutor.utils so you need to fix SearchWindow.kt.  It must now have a package name of "com.kana_tutor.utils.searchwindow" now.  In the same file fix the path on the import of the SearchWindowBinding.
* Build the project.  At this point, SearchWindow.kt should build. And with
some luck you will also find a .aar file in 
searchwindow/build/outputs/aar/searchwindow-debug.aar
* Now we need to add the import of the aar file to the project.  User
"app -> New -> Import .JAR/AAR Package".  Use the wizard's file browser
and navigate to searchwindow/build/outputs/aar/searchwindow-debug.aar
and select the .aar file.  When completed you will find a
new directory at the project root called searchwindow-debug
containing a copy of the .aar file and a build.gradle.
* Fix the demo project
    * add the new file to the dependencies in the app/build.gradle
    <pre><code>implementation project(":searchwindow")</code></pre>
    * Open MainActivity.kt.  You will need to add an import:
    <pre><code>import com.kana_tutor.utils.searchwindow.SearchWindow</code></pre><br>
    Probably the IDE will do this for you.
    * Open activity_main.xml and fix the path to the custom view.

At this point I was able to execute the demo app.  

The build of the aar's happens with Build->Rebuild Project which places its
aar file at searchwindow/build/outputs/aar/searchwindow-debug.aa for the
debug build but the demo app gets it's .aar from searchwindow-debug/searchwindow-debug.aar
so you need to copy the searchwindow .arr into the searchwindow-debug directory after
changes.  Other than that, everything works ok.

