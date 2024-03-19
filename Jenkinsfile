pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile test'
            }
        }
        stage('Check Test Coverage: ') {
            steps {
                jacoco(
                    execPattern: '**/target/jacoco/*.exec',
                    classPattern: '**/target/classes/java/main',
                    sourcePattern: '**/src/main'
                    )
            }
        }
    }
}