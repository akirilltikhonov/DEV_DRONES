pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile test'
            }
        }
        stage('Check Test Coverage1: dev-drones-api') {
            steps {
                jacoco(
                    execPattern: 'dev-drones-api/target/jacoco/*.exec',
                    classPattern: 'dev-drones-api/target/classes/com',
                    sourcePattern: 'dev-drones-api/src/main'
                    )
            }
        }
        stage('Check Test Coverage2: dev-drones-application') {
            steps {
                jacoco(
                    execPattern: 'dev-drones-application/target/jacoco/*.exec',
                    classPattern: 'dev-drones-application/target/classes/com',
                    sourcePattern: 'dev-drones-application/src/main'
                    )
            }
        stage('Check Test Coverage3: dev-drones-client') {
            steps {
                jacoco(
                    execPattern: 'dev-drones-client/target/jacoco/*.exec',
                    classPattern: 'dev-drones-client/target/classes/com',
                    sourcePattern: 'dev-drones-client/src/main'
                    )
            }
        }
    }
}