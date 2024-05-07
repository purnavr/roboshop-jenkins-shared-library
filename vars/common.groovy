def compile() {
  if (app_lang == 'nodejs') {
    sh 'install npm'
  }
  if (app_lang == 'maven') {
    sh 'mvn package'
  }
}