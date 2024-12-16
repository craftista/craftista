pipeline {
  agent none
  stages {
    stage('Voting build') {
      agent {
        docker {
          image 'maven:3.9.6-eclipse-temurin-17'
        }

      }
      steps {
        echo 'Compiling app'
        dir(path: 'voting') {
          sh 'mvn compile'
        }

      }
    }

    stage('voting test') {
      agent {
        docker {
          image 'maven:3.9.6-eclipse-temurin-17'
        }

      }
      steps {
        dir(path: 'voting') {
          sh 'mvn clean test'
        }

      }
    }

    stage('voting package') {
      parallel {
        stage('voting package') {
          agent {
            docker {
              image 'maven:3.9.6-eclipse-temurin-17'
            }

          }
          steps {
            dir(path: 'voting') {
              sh 'mvn package -DskipTests'
            }

          }
        }

        stage('voting Image') {
          agent any
          steps {
            script {
              docker.withRegistry('https://docker.io/v1', 'dockerlogin') {
                def commitHash = env.GIT_COMMIT.take(7)
                def dockerImage = docker.build("willywan/craftista-voting:${commitHash}", "./voting")
                dockerImage.push()
                dockerImage.push("latest")
                dockerImage.push("dev")
              }
            }

          }
        }

      }
    }

    stage('Frontend Build') {
      agent {
        docker {
          image 'node:latest'
        }

      }
      steps {
        dir(path: 'frontend') {
          sh 'npm install'
        }

      }
    }

    stage('Frontend test') {
      agent {
        docker {
          image 'node:latest'
        }

      }
      steps {
        dir(path: 'frontend') {
          sh '''npm install
npm test'''
        }

      }
    }

    stage('Frontend Image') {
      agent any
      steps {
        script {
          docker.withRegistry('https://docker.io/v1', 'dockerlogin') {
            def commitHash = env.GIT_COMMIT.take(7)
            def dockerImage = docker.build("willywan/craftista-frontend:${commitHash}", "./frontend")
            dockerImage.push()
            dockerImage.push("latest")
            dockerImage.push("dev")
          }
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