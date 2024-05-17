Pipeline{

agent any

tools{
  maven 'maven3.9.6'
}
stages{
  stage('Voting build'){
    steps{
      echo 'compiling app'
       dir('voting') {
      sh 'mvn compile'
       }
    }
  }
}

post{
  always{
    echo 'craftista completes'
  }
}




}
