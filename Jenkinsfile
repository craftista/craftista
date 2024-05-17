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

    stage('voting test') {
      steps {
        dir(path: 'voting') {
          sh 'mvn clean test'
        }

      }
    }

    stage('voting package') {
      steps {
        dir(path: 'voting') {
          sh 'mvn package -DskipTests'
        }

      }
    }

    stage('archive artifacts') {
      steps {
        archiveArtifacts '**/targets/*.jar'
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