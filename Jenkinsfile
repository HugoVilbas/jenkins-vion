pipeline {
    agent any

    tools {
        gradle 'Default' // Configure Gradle in Jenkins
    }

    environment {
        SONAR_SCANNER_HOME = tool 'sonarqube' // Tool configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') { // SonarQube server name in Jenkins
                    sh "./gradlew sonarqube"
                }
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t bookmanager:latest .'
            }
        }
    }
}
