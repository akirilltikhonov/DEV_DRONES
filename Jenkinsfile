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
                    execPattern: '**/jacoco/*.exec',
                    classPattern: '**/classes/java/main',
                    sourcePattern: '**/src/main'
                    )
            }
        }
        stage('Check Test Coverage2: ') {
            steps {
                jacoco(
                    execPattern: '**/jacoco/*.exec',
                    classPattern: '**/classes/java/main',
                    sourcePattern: '**/src/main'
                    )
            }
        }
    }
}