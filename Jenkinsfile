pipeline{
  agent any
  tools{
    maven 'maven 3.9.6'
  }
  stages{
    stage("voting Build"){
      steps{
        echo 'compilation of voting begins....'
        dir('voting') {
          sh 'mvn compile'
        }
      }
    }
  }
  post{
    always{
      echo 'done with compilation'
    }
  }
}
