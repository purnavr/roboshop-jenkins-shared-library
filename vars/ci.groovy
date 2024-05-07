def call() {
  pipeline {
    agent any

    stages {

      stage('compile/build') {
        steps {
          echo 'compile'
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

