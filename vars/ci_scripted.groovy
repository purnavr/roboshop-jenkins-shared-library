def call() {
  if (!env.sonar_extra_opts) {
    env.sonar_extra_opts =""
  }

  if (env.GTAG_NAME ==~ ".*") {
    env.GTAG = "true"
  }
  node('workstation') {

    try {

      stage('check out code') {
        cleanWs()
        git branch: 'main', url: 'https://github.com/purnavr/cart.git'
      }

      sh 'env'

      if (env.BRANCH_NAME != "main") {
        stage('compile/build') {
          common.compile()
        }
      }

//      if (env.TAG_NAME == ".*") {
//        stage('test cases') {
//          common.testcases()
//        }
//      }

      if (env.GTAG != "true") {
        stage('test cases') {
          common.testcases()
        }
      }

      stage('code quality') {
        common.codequality()
      }
    } catch (e) {
      mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'friendscreations634@gmail.com', mimeType: 'text/html', subject: "${component} - Pipeline Failed", to: 'friendscreations634@gmail.com'
    }

  }
}

