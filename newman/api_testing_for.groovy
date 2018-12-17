void call(app_env){
  stage "API Testing", {
    env_variable = app_env.appName + "_Run_Newman_Tests"
    echo "env varaible: " + env_variable
    echo "config: " + config[env_variable]
    run_tests = config.env_variable ?:
          false
    if (run_tests){
      echo "running api tests"
    }
    else{
      echo "skipping running api tests"
    }
  }
}
