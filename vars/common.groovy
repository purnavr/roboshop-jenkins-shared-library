def compile() {
  if (app_lang == 'nodejs') {
    sh 'install npm'
  }
  if (app_lang == 'maven') {
    sh 'mvn package'
  }
}

def testcases() {
  if (app_lang == 'nodejs') {
    sh 'echo test'
  }
  if (app_lang == 'maven') {
    sh 'echo test'
  }
}