void call(app_env){
  stage "API Testing", {
    unstash "workspace"
    env_variable = app_env.appName + "_Run_Newman_Tests"
    run_tests = config[env_variable] ?:
          false
    if (run_tests){
      echo "running api tests"
      docker.image("aleckeller13/newman").inside{
        try{
          dir('collections') {
            def files = findFiles(glob: '*.json')
            files.each{
              echo "Running ${it.name} collection"
              sh "newman run ${it.name}"
            }
          }
        }
        catch(ex){
          error "Newman tests failed with: ${ex}"

        }
      }
    }
    else{
      echo "skipping running api tests"
    }
  }
}
