pipeline {
    agent any

    tools {
        maven 'maven3.9.6'
    }

    stages {
        stage('Voting build') {
            steps {
                echo 'Compiling app'
                dir('voting') {
                    sh 'mvn compile'
                }
            }
        }
    }

    post {
        always {
            echo 'Craftista completes'
        }
    }
}

