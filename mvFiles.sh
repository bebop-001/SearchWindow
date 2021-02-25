#!/bin/sh -x

srcRoot="app/src/main"
destRoot="searchwindow/src/main"
mvFiles="
res/drawable-ldpi/clear.png
res/drawable-xhdpi/clear.png
res/drawable-mdpi/clear.png
res/drawable-hdpi/clear.png
res/drawable-ldpi/clear_disabled.png
res/drawable/clear_selector.xml
res/drawable-xhdpi/clear_disabled.png
res/drawable-mdpi/clear_disabled.png
res/drawable-hdpi/clear_disabled.png
res/drawable-ldpi/menu_search_disabled.png
res/drawable-ldpi/menu_search.png
res/drawable/menu_search_selector.xml
res/drawable-xhdpi/menu_search_disabled.png
res/drawable-xhdpi/menu_search.png
res/drawable-xxhdpi/menu_search_disabled.png
res/drawable-xxhdpi/menu_search.png
res/drawable-mdpi/menu_search_disabled.png
res/drawable-mdpi/menu_search.png
res/drawable-hdpi/menu_search_disabled.png
res/drawable-hdpi/menu_search.png
res/layout/search_window.xml
res/values/searchWindowAttrs.xml
res/values/searchWindowStrings.xml
"
for file in $mvFiles; do
    srcFile="$srcRoot/$file";
    destFile="$destRoot/$file";
    if [ ! -d $destRoot ]; then
        echo "Destination $destFile doesn't exist."
        exit 1
    fi
    destDir=`dirname $destFile`;
    if [ !  -f $destFile ] ; then
        if [ ! -d $destDir ]; then  mkdir -p $destDir; fi
        git mv $srcFile $destFile;
    fi
done
if [ -f "$srcRoot/java/com/kana_tutor/example/searchwindow/SearchWindow.kt" ];
    then
    destFile="$destRoot/java/com/kana_tutor/utils/searchwindow/SearchWindow.kt"
    destDir=`dirname $destFile`;
    if [ ! -d $destDir ]; then mkdir -p $destDir; fi
    git mv \
        app/src/main/java/com/kana_tutor/example/searchwindow/SearchWindow.kt\
        $destFile
fi
