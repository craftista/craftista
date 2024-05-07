pipeline {
  agent any
  stages {
    stage('Voting Build') {
      steps {
        echo 'compiling the voting app...'
        dir(path: 'voting') {
          sh 'mvn compile'
        }

      }
    }

    stage('Voting Test') {
      steps {
        dir(path: 'voting') {
          sh 'mvn clean test '
        }

      }
    }

    stage('Voting Package') {
      steps {
        dir(path: 'voting') {
          sh 'mvn package -DskipTests'
        }

        archiveArtifacts '**/target/*.jar'
      }
    }

  }
  tools {
    maven 'Maven 3.9.6'
  }
  post {
    always {
      echo 'completed the pipeline for craftista....'
    }

  }
}