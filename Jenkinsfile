#!/usr/bin/groovy
node {
    checkout scm

    stage('Build Docker image') {
        sh 'docker-compose build'
    }

    stage('Run Test'){
        sh 'docker-compose run test'
    }
}
