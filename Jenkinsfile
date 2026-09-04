pipeline {

    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3.9'
        nodejs 'NodeJS22'
    }

    options {
        disableConcurrentBuilds(abortPrevious: true)
        timeout(time: 30, unit: 'MINUTES')
    }

    stages {

        stage('Checkout Backend Repository') {
            steps {
                checkout scm
            }
        }

        stage('Checkout Frontend') {
            steps {
                bat '''
                    if exist study-tracker-ui rmdir /s /q study-tracker-ui
                    git clone --branch main https://github.com/akhileshkumar4953/study-tracker-ui.git study-tracker-ui
                '''
            }
        }

        stage('Verify Files') {
            steps {
                bat '''
                    echo ===== BACKEND =====
                    dir study-tracker
                    echo ===== FRONTEND =====
                    dir study-tracker-ui
                    echo ===== FRONTEND PACKAGE =====
                    if not exist study-tracker-ui\\package.json exit /b 1
                '''
            }
        }

        stage('Build Backend') {
            steps {
                dir('study-tracker') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('study-tracker-ui') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        stage('Docker Compose Build') {
            steps {
                bat 'docker compose build --no-cache'
            }
        }

        stage('Deploy') {
            steps {
                bat 'docker compose down'
                bat 'docker compose up -d'
            }
        }

        stage('Verify Deployment') {
            steps {
                bat 'docker compose ps'
            }
        }
    }

    post {

        success {
            echo 'Application Successfully Deployed'
        }

        failure {
            echo 'Deployment Failed'
        }

        aborted {
            echo 'Build Aborted'
        }
    }
}