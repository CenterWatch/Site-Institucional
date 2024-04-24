#!/bin/bash

# echo "Fazendo update"
# sudo apt update

# echo "Fazendo upgrade"
# sudo apt upgrade

java -version

if [ $? = 0 ]
    then echo "Existe java"
    versao_desejada="17"
    java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
    java_version=$(echo "$java_version" | cut -c1-2)
    if [ $java_version = $versao_desejada ]
        then
        echo "Versão instalada OK"
    else 
        echo "Versão instalada inválida"
        sudo apt install openjdk-17-jre
    fi
else
    echo "Não existe java"
    sudo apt install openjdk-17-jre
fi

wget https://github.com/CenterWatch/cwatching/blob/main/out/artifacts/cwatching_jar/cwatching.jar
java -jar cwatching.jar