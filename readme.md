# Local JAR Manager
LJM provides a simple way to locally manage JAR files without having to store files in Git repositories. Unlike
Gradle and Maven, which can both require internet access to build, LJM ensures that you only need internet access
one time - when you download the JAR files. LJM itself is an incredibly small application - in total, a production
version of the application is less than 10kb. There are no dependencies.

## Basic Use
LJM uses a proprietary file format. LJM operates by reading data from a file, parsing that data, and then determining
what JAR files need to go where. 

### LJM File Format
Here's an example of an LJM file.
```
(gson, gson.jar, https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.7/gson-2.8.7.jar);
(apache-commons-lang3, apache-commons-lang3.jar, https://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.12.0/commons-lang3-3.12.0.jar);
```
Any valid URL should work, so long the URL points to a JAR file. Typically, addresses ending in `.jar` will always
work, and addresses ending in anything else won't.

To better explain what that LJM file means, here's a more descriptive example.
```
(<library name>, <JAR file name>, <JAR URL>);
```
It's important that these parameters are kept in order.

#### Library Name
This is the library's name. Future releases of LJM will make use of this name, but for now, it's pretty much just for
show. Very cool.

#### JAR File Name
This is the name of the JAR file that will be downloaded. For example, if this JAR file name is "gson.jar", a file
named "gson.jar" will be created in the "ljm" directory - all in all, that's "ljm/gson.jar".

#### JAR URL
This is the web location where the library can be downloaded from. LJM will download this JAR file and install it
locally.

## Using with Gradle
LJM can easily integrate with Gradle. In order to access libraries managed through LJM via Gradle, you need to add
a local dependency as follows.
```
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'ljm')
    
    // your other dependencies go here...
}
```
This will include any file with a `.jar` file extension in the directory ljm, which is where LJM downloads JAR files.

## `.gitignore`
Obviously, it's a bad idea to store binaries in a repository. It's strongly suggested that you use a `.gitignore`
file to ignore everything LJM downloads.
```
ljm/
```
Thankfully, it's rather simple to ignore LJM libraries - just add what you see above to your `.gitignore` file and
you'll be on your way!

## LJM Sources
LJM reads a file to determine what JAR files should be downloaded.

### Default Source
LJM, by default, will look for a file named `ljm-sources.txt`.

### Custom Source
If you'd like to change the source file LJM looks for, you can do so as follows.
- Open an instance of your terminal.
- Navigate to the directory `ljm.jar` is contained in.
- Execute the following command: `java -jar ljm.jar <your source here>`

Your source does not have to be a `.txt` file, but it should still adhere to the same syntax guidelines.