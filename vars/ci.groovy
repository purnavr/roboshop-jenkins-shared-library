def call() {
  pipeline {
    agent any

    stages {

      stage('compile/build') {
        steps {
          script {
            if (app_lang == 'nodejs') {
              sh 'install npm'
            }
            if (app_lang == 'maven') {
              sh 'mvn package'
            }
          }
        }
      }

      stage('test cases') {
        steps {
          echo 'test cases'
        }
      }
    }
  }
}

