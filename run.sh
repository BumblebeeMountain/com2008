#!/bin/bash

# Stuff left over from the Java assignment in Year 1

# fileName=$1
# if [ $# -eq 0 ]
# then
#     fileName=resources/data/
# fi

# Sort out wierd classpath stuffs
path=$(pwd)
export CLASSPATH=$path/src/;

# delete all classfiles
rm $(find bin/ -name *.class);

# javac -d bin --release 8 src/App.java;
javac -d bin --release 8 src/database/Setup.java;

# class path is both the binaries, and the lib directories
export CLASSPATH=$path/bin/:$path/lib/*;

# java App;
java database.Setup;

export CLASSPATH=$path/src/;
