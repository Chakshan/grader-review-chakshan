#!/bin/bash

while IFS= read -r line
do
    echo "$line"
    bash grade.sh $line
    echo -e "\n"
done < repos.txt
