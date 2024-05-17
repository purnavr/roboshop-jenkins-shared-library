def call() {
  if (!env.sonar_extra_opts) {
    env.sonar_extra_opts =""
  }
  node('workstation') {

    try {
      stage('compile/build') {
        sh 'env'
        common.compile()
      }

      stage('test cases') {
        common.testcases()
      }

      stage('code quality') {
        common.codequality()
      }
    } catch (e) {
      mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'friendscreations634@gmail.com', mimeType: 'text/html', subject: "${component} - Pipeline Failed", to: 'friendscreations634@gmail.com'
    }

  }
}

