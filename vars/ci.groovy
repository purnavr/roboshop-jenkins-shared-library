def call() {
  if (!env.sonar_extra_opts) {
    env.sonar_extra_opts =""
  }
  pipeline {
    agent any

    stages {

      stage('compile/build') {
        steps {
          sh 'env'
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

      stage('Code Quality') {
        steps {
          script {
            common.codequality()
          }
        }
      }
    }

    post {
      failure {
        mail body: '${component} - Pipeline Failed \n {BUILD_URL}', from: 'friendscreations634@gmail.com', subject: '${component} - Pipeline Failed', to: 'friendscreations634@gmail.com'
      }
    }
  }
}

