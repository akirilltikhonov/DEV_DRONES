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
                    execPattern: '**/build/jacoco/*.exec',
                    classPattern: '**/build/classes/java/main',
                    sourcePattern: '**/src/main'
                    )
            }
        }
    }
}