pipeline {
  agent any
  stages {
    stage('Voting build') {
      steps {
        echo 'Compiling app'
        dir(path: 'voting') {
          sh 'mvn compile'
        }

      }
    }

  }
  tools {
    maven 'maven3.9.6'
  }
  post {
    always {
      echo 'Craftista completes'
    }

  }
}