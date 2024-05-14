pipeline {
  agent any
  stages {
    stage('voting Build') {
      steps {
        echo 'compilation of voting begins....'
        dir(path: 'voting') {
          sh 'mvn compile'
        }

      }
    }

    stage('Voting Test') {
      steps {
        dir(path: 'voting') {
          sh 'echo "Testing"'
        }

      }
    }

  }
  tools {
    maven 'maven 3.9.6'
  }
  post {
    always {
      echo 'done with compilation'
    }

  }
}