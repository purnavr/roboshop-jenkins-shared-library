def call() {
  pipeline {
    agent any

    stages {

      stage('compile/build') {
        steps {
          script {
            common.compile()
          }
        }
      }

      stage('test cases') {
        steps {
          script {
            common.testcases()
          }
        }
      }
    }
  }
}

