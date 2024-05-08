def call() {
  if (!env.sonar_extra_opts) {
    env.sonar_extra_opts =""
  }
  pipeline {
    agent any

    stages {

      stage('compile/build') {
        steps {
          mail bcc: '', body: 'test', cc: '', from: 'friendscreations634@gmail.com', replyTo: '', subject: 'Test', to: 'friendscreations634@gmail.com'
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
  }
}

