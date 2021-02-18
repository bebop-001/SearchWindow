# Custom Attributes

Custom attributes are used in this app.  I had a hard time finding documentation
on them and some of the best I found was
<a href="https://stackoverflow.com/questions/3441396/defining-custom-attrs">
from this reply to a question on Stack overflow</a>.  Also attrs.xml in the
data/res/values/attrs.xml of the SDK is useful for examples.

You can define attributes in the top <code>&lt;resources&gt;</code> element or
inside of a <code>&lt;declare-styleable&gt;</code> element. If I'm going to
use an attr in more than one place I put it in the root element. Note,
all attributes share the same global namespace. That means that even if you
create a new attribute inside of a <code>&lt;declare-styleable&gt;</code>
element it can be used outside of it and you cannot create
another attribute with the same name of a different type.

An <code>&lt;attr&gt;</code> element has two xml attributes <code>name</code>
and <code>format</code>. <code>name</code> lets you call it something and this
is how you end up referring to it in code, e.g., <code>R.attr.my_attribute</code>.
The <code>format</code> attribute can have different values depending on
the 'type' of attribute you want.
*    reference - if it references another resource id
(e.g, "@color/my_color", "@layout/my_layout")</li>
*    color
*    boolean
*    dimension
*    float
*    integer
*    string
*    fraction
*    enum - normally implicitly defined
*    flag - normally implicitly defined

You can set the format to multiple types by using <code>|</code>,
e.g., <code>format="reference|color"</code>.

<code>enum</code> attributes can be defined as follows:
<pre><code>&lt;attr name="my_enum_attr"&gt;
  &lt;enum name="value1" value="1" /&gt;
  &lt;enum name="value2" value="2" /&gt;
&lt;/attr&gt;
</code></pre>

<code>flag</code> attributes are similar except the values need
to be defined so they can be bit ored together:
<pre><code>&lt;attr name="my_flag_attr"&gt;
  &lt;flag name="fuzzy" value="0x01" /&gt;
  &lt;flag name="cold" value="0x02" /&gt;
&lt;/attr&gt;
</code></pre>

In addition to attributes there is the <code>&lt;declare-styleable&gt;</code>
element. This allows you to define attributes a custom view can use. You do
this by specifying an <code>&lt;attr&gt;</code> element, if it was previously
defined you do not specify the <code>format</code>. If you wish to reuse an
android attr, for example, android:gravity, then you can do that in the
<code>name</code>, as follows:
<code>&lt;declare-styleable&gt;</code>:

<pre><code>&lt;declare-styleable name="MyCustomView"&gt;
  &lt;attr name="my_custom_attribute" /&gt;
  &lt;attr name="android:gravity" /&gt;
&lt;/declare-styleable&gt;
</code></pre>

When defining your custom attributes in XML on your custom view
you need to do a few things. First you must declare a namespace
to find your attributes. You do this on the root layout element.
Normally there is only
<code>xmlns:android="http://schemas.android.com/apk/res/android"</code>.
You must now also add
<code>xmlns:whatever="http://schemas.android.com/apk/res-auto"</code>.

For example:
<pre><code>&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:whatever="http://schemas.android.com/apk/res-auto"
  android:orientation="vertical"
  android:layout_width="fill_parent"
  android:layout_height="fill_parent"&gt;

    &lt;org.example.mypackage.MyCustomView
      android:layout_width="fill_parent"
      android:layout_height="wrap_content"
      android:gravity="center"
      whatever:my_custom_attribute="Hello, world!" /&gt;
&lt;/LinearLayout&gt;
</code></pre>

And finally, you normally access your custom view in the view constructor
of your custom view as follows:
<pre><code>public MyCustomView(Context context, AttributeSet attrs, int defStyle) {
  super(context, attrs, defStyle);

  TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView, defStyle, 0);

  String str = a.getString(R.styleable.MyCustomView_my_custom_attribute);

  //do something with str

  a.recycle();
}
</code></pre>
