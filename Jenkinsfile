pipeline {
agent any

tools {
    jdk 'JDK21'
    maven 'Maven3.9'
    nodejs 'NodeJS22'
}

stages {

    stage('Checkout') {
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
            bat 'docker compose build'
        }
    }

    stage('Deploy') {
        steps {
            bat 'docker compose down'
            bat 'docker compose up -d'
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

}

}
