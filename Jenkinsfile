pipeline {
  agent none
  stages {
    stage('voting Build') {
      agent {
        docker {
          image 'maven:3.9-eclipse-temurin-22-alpine'
        }

      }
      steps {
        echo 'compilation of voting begins....'
        dir(path: 'voting') {
          sh 'mvn compile'
        }

      }
    }

    stage('Voting Test') {
      parallel {
        stage('Voting Test') {
          agent {
            docker {
              image 'maven:3.9-eclipse-temurin-22-alpine'
            }

          }
          steps {
            dir(path: 'voting') {
              sh 'echo "Testing"'
            }

          }
        }

        stage('voting build & publish') {
          steps {
            script {
              docker.withRegistry('https://index.docker.io/v1','dockerlogin'){
                def commithHash = env.GIT_COMMIT.take(7)
                def dockerImage = docker.build("eshietiy/craftista-voting:${commitHash}", "./voting")
                dockerImage.push()
                dockerImage.push("latest")
              }
            }

          }
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