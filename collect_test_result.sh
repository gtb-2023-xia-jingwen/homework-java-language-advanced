#!/usr/bin/env bash

./gradlew build
zip -r ./test-result.zip ./build/test-results/test
