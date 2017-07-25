#!/usr/bin/groovy
node('BUILD') {
    checkout scm

    stage('Build Docker image') {
        sh 'docker-compose build'
    }

    stage('Run Test'){
        sh 'docker-compose run test'
    }
}
