#!/usr/bin/env bash

jarName="data-extractor-*-jar-with-dependencies.jar"
mainClass="ro.gov.ithub.infotranspub.extraction.Main"

jarFile=$( find . -name ${jarName} | tail -n 1 )
if [[ -z ${jarFile} && -d target ]]; then
    jarFile=$( find target/ -name ${jarName} | tail -n 1 )
fi
if [[ -z ${jarFile} && -d ../target ]]; then
    jarFile=$( find ../target/ -name ${jarName} | tail -n 1 )
fi
if [ -z ${jarFile} ]; then
    echo "Could not find ${jarName}"
    exit 1
fi

java -cp ${jarFile} ${mainClass} $@
