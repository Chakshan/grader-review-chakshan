CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission 2> git-output.txt
echo 'Finished cloning'


# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests

find student-submission -name "*.java" -exec cp {} grading-area \;
# cp student-submission/*.java grading-area
cp *.java grading-area
cp -r lib grading-area

cd grading-area

if ! [ -f ListExamples.java ]
then
	echo "Missing ListExamples.java. Did you forget the file or misname it?"
	echo "Score: 0"
	exit 1
fi 

javac -cp $CPATH *.java

if [ $? -ne 0 ]
then 
	echo "Could not compile. See compiler errors above."
	echo "Score: 0"
	exit 1
fi

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > test-output.txt

RESULT_LINE=$(grep "Tests run:" test-output.txt)

if [ -n "$RESULT_LINE" ]
then
	TESTS_RUN=$(echo $RESULT_LINE | awk -F'[, ]' '{ print $3 }')
	FAILURES=$(echo $RESULT_LINE | awk -F'[, ]' '{ print $6 }')
	SUCCESSES=$((TESTS_RUN-FAILURES))

	SCORE=$(( 100 * $SUCCESSES / $TESTS_RUN ))

	echo "Passed $SUCCESSES out of $TESTS_RUN tests"
	echo "Score: $SCORE"
	exit 0
fi

RESULT_LINE=$(grep "OK (" test-output.txt)
if [ -n "$RESULT_LINE" ]
then
	TESTS_RUN=$(echo $RESULT_LINE | awk -F'[( ]' '{ print $3 }')
	echo "Passed $TESTS_RUN out of $TESTS_RUN tests"
	echo "Score: 100"
	exit 0
fi

echo "Error. Invalid tests output."
exit 1


