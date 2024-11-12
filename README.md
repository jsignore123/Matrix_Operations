README

This is a java code file containing methods that perform matrix operations such as Guass Jordan elimination, matrix multiplication, and raising a matrix to a power. You can define matrices in main() and use these methods on them and print the result.

-Open command prompt 
   search cmd in search bar and run as administrator

 -Install JDK (compiler) 
   install to default directory in installer - C:\ProgramFiles\Java\jdk-17
   JDK download: 
   https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.exe

 -Set path for JDK in command prompt:
   Enter these commands into command prompt:
    setx -m JAVA_HOME "C:\Program Files\Java\jdk-17"
    setx PATH "%PATH%;%JAVA_HOME%\bin";

 -Navigate to folder containing in command prompt:
   cd ..             move to parent directory
   cd folder         move to folder in current directory 
   cd name\name      go down two levels of documents at once. For example: cd Admin\Downloads
   dir               displays contents of current directory

 -COMPILE:
   javac MatrixOperations.java

 -RUN:
   java MatrixOperations
