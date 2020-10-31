#! /bin/sh -v

export CLASSPATH=$CLASSPATH:./build
time java edu.umb.cs.threads.basics.MCTest 10000000000 1
time java edu.umb.cs.threads.basics.MCTest 10000000000 2
time java edu.umb.cs.threads.basics.MCTest 10000000000 4
time java edu.umb.cs.threads.basics.MCTest 10000000000 8
time java edu.umb.cs.threads.basics.MCTest 10000000000 16
